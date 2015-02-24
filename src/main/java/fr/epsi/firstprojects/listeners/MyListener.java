package fr.epsi.firstprojects.listeners;

import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import fr.epsi.firstprojects.beans.Utilisateur;
import fr.epsi.firstprojects.jmx.LoggerMBean;

/**
 * Application Lifecycle Listener implementation class MyListener
 *
 */
@WebListener
public class MyListener implements ServletContextListener {

	private final static String dbDriver = "org.hsqldb.jdbcDriver";
	private final static String dbUrl = "jdbc:hsqldb:hsql://localhost:9003";
	private final static String dbUser = "SA";
	private final static String dbPassword = "";

	public static Connection getConnection() throws Exception {
		Class.forName(dbDriver);
		Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		return con;
	}

	private static List<Utilisateur> listOfUtilisateurs;

	public static List<Utilisateur> getListOfUtilisateurs() {
		return listOfUtilisateurs;
	}

	/**
	 * Default constructor. 
	 */
	public MyListener() {
		listOfUtilisateurs = new ArrayList<Utilisateur>();
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setLogin("ADMIN");
		utilisateur.setPassword("admin");
		utilisateur.setName("Administrateur");
		utilisateur.setAdministrateur(true);
		listOfUtilisateurs.add(utilisateur);

		utilisateur = new Utilisateur();
		utilisateur.setLogin("USER");
		utilisateur.setPassword("user");
		utilisateur.setName("Utilisateur");
		utilisateur.setAdministrateur(false);
		listOfUtilisateurs.add(utilisateur);

	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		boolean ok = true;
		try {
			Connection conn = getConnection();
			conn.close();
		} catch (Exception e) {
			ok = false;
			Logger.getRootLogger().error("Erreur lors de la connexion à la base de données",e);
		}

		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = null;

		try {
			name = new ObjectName("fr.epsi.firstprojects.jmx:type=LoggerMBean");
			LoggerMBean mbean = new fr.epsi.firstprojects.jmx.Logger();

			mbs.registerMBean(mbean, name);

		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
			ok = false;
		} catch (NullPointerException e) {
			e.printStackTrace();
			ok = false;
		} catch (InstanceAlreadyExistsException e) {
			e.printStackTrace();
			ok = false;
		} catch (MBeanRegistrationException e) {
			e.printStackTrace();
			ok = false;
		} catch (NotCompliantMBeanException e) {
			e.printStackTrace();
			ok = false;
		}

		if (ok) {
			Logger.getRootLogger().error("Démarrage application OK");
		} else {
			Logger.getRootLogger().error("L'application n'est pas démarrée correctement");
		}
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		Logger.getRootLogger().error("Arret de l'application");
	}

}

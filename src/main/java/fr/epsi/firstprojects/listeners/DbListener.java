package fr.epsi.firstprojects.listeners;

import fr.epsi.firstprojects.beans.User;
import fr.epsi.firstprojects.jmx.LoggerMBean;
import org.apache.log4j.Logger;

import javax.management.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Application Lifecycle Listener implementation class
 *
 */
@WebListener
public class DbListener implements ServletContextListener {

	private final static String dbDriver = "org.hsqldb.jdbcDriver";
	private final static String dbUrl = "jdbc:hsqldb:hsql://localhost:9003";
	private final static String dbUser = "SA";
	private final static String dbPassword = "";

	public static Connection getConnection() throws Exception {
		Class.forName(dbDriver);
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    private static List<User> listOfUsers;

    public static List<User> getListOfUsers() {
        return listOfUsers;
    }

	/**
	 * Default constructor. 
	 */
    public DbListener() {
        listOfUsers = new ArrayList<User>();
        User user = new User();
        user.setLogin("ADMIN");
        user.setPassword("admin");
        listOfUsers.add(user);

        user = new User();
        user.setLogin("USER");
        user.setPassword("user");
        listOfUsers.add(user);

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
            Logger.getRootLogger().error("Erreur lors de la connexion à la base de données", e);
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

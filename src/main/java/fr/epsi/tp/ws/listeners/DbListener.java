package fr.epsi.tp.ws.listeners;

import fr.epsi.tp.ws.beans.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
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

    Logger logger = Logger.getLogger(DbListener.class);

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
        logger.info("Servlet context initialization");
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
    }

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
    public void contextDestroyed(ServletContextEvent arg0) {
    }

}

package fr.epsi.tests.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDBConnexion {

	public static void main(String[] args) {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
			
			ResultSet resultats = null;
			String requete = "SELECT * FROM CONTACTS";

			try {
			    Statement stmt = con.createStatement();
			    resultats = stmt.executeQuery(requete);

			    ResultSetMetaData rsmd = resultats.getMetaData();
			    int nbCols = rsmd.getColumnCount();

			    while (resultats.next()) {
			        for (int i = 1; i <= nbCols; i++) {
			            System.out.print(resultats.getString(i) + " ");
			         }
			         System.out.println();
			     }

			    resultats.close();

			} catch (SQLException e) {
			//traitement de l'exception
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

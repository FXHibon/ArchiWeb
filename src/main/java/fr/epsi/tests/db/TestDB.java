package fr.epsi.tests.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDB {

	public static void main(String[] args) {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
			System.out.println("connection=" + con.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

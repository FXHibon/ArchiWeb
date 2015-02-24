package fr.epsi.firstprojects.services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import fr.epsi.firstprojects.beans.Contact;
import fr.epsi.firstprojects.listeners.MyListener;
import fr.epsi.firstprojects.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Override
	public List<Contact> getContacts() {
		List<Contact> contacts = new ArrayList<Contact>();

		try {
			ResultSet resultats = null;
			String requete = "SELECT * FROM USERS";

			try {
				Statement stmt = MyListener.getConnection().createStatement();
				resultats = stmt.executeQuery(requete);

				while (resultats.next()) {
					Contact contact = new Contact();
					contact.setLogin(resultats.getString(1));
					contact.setName(resultats.getString(2));
					contact.setPassword(resultats.getString(3));
					contacts.add(contact);
				}

				resultats.close();
				MyListener.getConnection().close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return contacts;
	}

	@Override
	public boolean addContact(Contact contact) {
		try {
			Connection conn = MyListener.getConnection();
			PreparedStatement stmt = conn
					.prepareStatement("INSERT INTO USERS VALUES (?, ?, ?)");
			stmt.setString(1, contact.getLogin());
			stmt.setString(2, contact.getName());
			stmt.setString(3, contact.getPassword());
			stmt.executeUpdate();
			conn.close();
			
			return true;
		} catch (Exception e) {
			Logger.getRootLogger().error("Erreur lors d'insertion en base de données",e);
			return false;
		}
	}

	@Override
	public boolean updateContact(Contact contact) {
		try {
			Connection conn = MyListener.getConnection();
			PreparedStatement stmt = conn
					.prepareStatement("UPDATE USERS SET NOM=?, PASSWORD=? WHERE ID=?");
			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getPassword());
			stmt.setString(3, contact.getLogin());
			stmt.executeUpdate();
			conn.close();
			return true;
			
		} catch (Exception e) {
			Logger.getRootLogger().error("Erreur lors de la mise à jour en base de données",e);
			return false;
		}
	}

	@Override
	public boolean deleteContact(Contact contact) {
		try {
			Connection conn = MyListener.getConnection();
			PreparedStatement stmt = conn
					.prepareStatement("DELETE FROM USERS WHERE ID=?");
			stmt.setString(1, contact.getLogin());
			stmt.executeUpdate();
			conn.close();
			
			return true;
		} catch (Exception e) {
			Logger.getRootLogger().error("Erreur lors de la suppression en base de données",e);
			return false;
		}
	}

	@Override
	public Contact getContact(String id) {
		Contact contact = null;

		try {
			ResultSet resultats = null;

			try {
				Connection conn = MyListener.getConnection();
				PreparedStatement stmt = conn
						.prepareStatement("SELECT * FROM USERS WHERE ID=?");
				stmt.setString(1, id);
				resultats = stmt.executeQuery();
				if (resultats.next()) {
					contact = new Contact();
					contact.setLogin(resultats.getString(1));
					contact.setName(resultats.getString(2));
					contact.setPassword(resultats.getString(3));
				}

				resultats.close();
				MyListener.getConnection().close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return contact;
	}

}

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

import fr.epsi.firstprojects.beans.Product;
import fr.epsi.firstprojects.listeners.MyListener;
import fr.epsi.firstprojects.services.ContactService;

public class ContactServiceImpl implements ContactService {

	@Override
    public List<Product> getContacts() {
        List<Product> products = new ArrayList<Product>();

		try {
			ResultSet resultats = null;
			String requete = "SELECT * FROM USERS";

			try {
				Statement stmt = MyListener.getConnection().createStatement();
				resultats = stmt.executeQuery(requete);

				while (resultats.next()) {
                    Product product = new Product();
                    product.setLogin(resultats.getString(1));
                    product.setName(resultats.getString(2));
                    product.setPassword(resultats.getString(3));
                    products.add(product);
                }

				resultats.close();
				MyListener.getConnection().close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

        return products;
    }

	@Override
    public boolean addContact(Product product) {
        try {
			Connection conn = MyListener.getConnection();
			PreparedStatement stmt = conn
					.prepareStatement("INSERT INTO USERS VALUES (?, ?, ?)");
            stmt.setString(1, product.getLogin());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getPassword());
            stmt.executeUpdate();
			conn.close();
			
			return true;
		} catch (Exception e) {
			Logger.getRootLogger().error("Erreur lors d'insertion en base de donn�es",e);
			return false;
		}
	}

	@Override
    public boolean updateContact(Product product) {
        try {
			Connection conn = MyListener.getConnection();
			PreparedStatement stmt = conn
					.prepareStatement("UPDATE USERS SET NOM=?, PASSWORD=? WHERE ID=?");
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getPassword());
            stmt.setString(3, product.getLogin());
            stmt.executeUpdate();
			conn.close();
			return true;
			
		} catch (Exception e) {
			Logger.getRootLogger().error("Erreur lors de la mise � jour en base de donn�es",e);
			return false;
		}
	}

	@Override
    public boolean deleteContact(Product product) {
        try {
			Connection conn = MyListener.getConnection();
			PreparedStatement stmt = conn
					.prepareStatement("DELETE FROM USERS WHERE ID=?");
            stmt.setString(1, product.getLogin());
            stmt.executeUpdate();
			conn.close();
			
			return true;
		} catch (Exception e) {
			Logger.getRootLogger().error("Erreur lors de la suppression en base de donn�es",e);
			return false;
		}
	}

	@Override
    public Product getContact(String id) {
        Product product = null;

		try {
			ResultSet resultats = null;

			try {
				Connection conn = MyListener.getConnection();
				PreparedStatement stmt = conn
						.prepareStatement("SELECT * FROM USERS WHERE ID=?");
				stmt.setString(1, id);
				resultats = stmt.executeQuery();
				if (resultats.next()) {
                    product = new Product();
                    product.setLogin(resultats.getString(1));
                    product.setName(resultats.getString(2));
                    product.setPassword(resultats.getString(3));
                }

				resultats.close();
				MyListener.getConnection().close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

        return product;
    }

}

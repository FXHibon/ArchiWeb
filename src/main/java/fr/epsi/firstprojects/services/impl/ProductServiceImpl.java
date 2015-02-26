package fr.epsi.firstprojects.services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import fr.epsi.firstprojects.beans.Product;
import fr.epsi.firstprojects.listeners.MyListener;
import fr.epsi.firstprojects.services.ProductService;

public class ProductServiceImpl implements ProductService {

	@Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<Product>();

		try {
            ResultSet results;
            String request = "SELECT * FROM PRODUCTS";

			try {
				Statement stmt = MyListener.getConnection().createStatement();
                results = stmt.executeQuery(request);

                while (results.next()) {
                    Product product = new Product();
                    product.setName(results.getString("name"));
                    product.setDescription(results.getString("description"));
                    product.setAmount(results.getInt("amount"));
                    product.setImage(results.getString("image"));

                    products.add(product);
                }

                results.close();
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
    public boolean addProduct(Product product) {
        try {
			Connection conn = MyListener.getConnection();
            PreparedStatement insertStatement = conn
                    .prepareStatement("INSERT INTO PRODUCTS VALUES (?, ?, ?, ?)");
            insertStatement.setString(1, product.getName());
            insertStatement.setString(2, product.getDescription());
            insertStatement.setInt(3, product.getAmount());
            insertStatement.setString(4, product.getImage());
            insertStatement.execute();
            conn.close();
			
			return true;
		} catch (Exception e) {
            Logger.getRootLogger().error("Erreur lors d'insertion en base de données", e);
            return false;
		}
	}

	@Override
    public boolean updateProduct(Product product) {
        try {
			Connection conn = MyListener.getConnection();
			PreparedStatement stmt = conn
                    .prepareStatement("UPDATE PRODUCTS SET NAME=?, DESCRIPTION=?, AMOUNT=?, IMAGE=? WHERE NAME=?");
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getAmount());
            stmt.setString(4, product.getImage());
            stmt.executeUpdate();
			conn.close();
			return true;
			
		} catch (Exception e) {
            Logger.getRootLogger().error("Erreur lors de la mise à jour en base de données", e);
            return false;
		}
	}

	@Override
    public boolean deleteProduct(Product product) {
        try {
			Connection conn = MyListener.getConnection();
			PreparedStatement stmt = conn
                    .prepareStatement("DELETE FROM PRODUCTS WHERE NAME=?");
            stmt.setString(1, product.getName());
            stmt.executeUpdate();
			conn.close();
			
			return true;
		} catch (Exception e) {
            Logger.getRootLogger().error("Erreur lors de la suppression en base de données", e);
            return false;
		}
	}

	@Override
    public Product getProduct(String id) {
        Product product = null;

		try {
            ResultSet results = null;

			try {
				Connection conn = MyListener.getConnection();
				PreparedStatement stmt = conn
                        .prepareStatement("SELECT * FROM USERS WHERE NAME=?");
                stmt.setString(1, id);
                results = stmt.executeQuery();
                if (results.next()) {
                    product = new Product();
                    product.setName(results.getString("name"));
                    product.setDescription(results.getString("description"));
                    product.setAmount(results.getInt("amount"));
                    product.setImage(results.getString("image"));
                }

                results.close();
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

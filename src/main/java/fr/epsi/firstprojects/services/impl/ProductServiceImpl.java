package fr.epsi.firstprojects.services.impl;

import fr.epsi.firstprojects.beans.Product;
import fr.epsi.firstprojects.listeners.DbListener;
import fr.epsi.firstprojects.services.ProductService;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

	@Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<Product>();

		try {
            ResultSet results;
            String request = "SELECT * FROM PRODUCTS";

			try {
                Statement stmt = DbListener.getConnection().createStatement();
                results = stmt.executeQuery(request);

                while (results.next()) {
                    Product product = new Product();
                    product.setId(results.getString("id"));
                    product.setName(results.getString("name"));
                    product.setDescription(results.getString("description"));
                    product.setAmount(results.getInt("amount"));
                    product.setImage(results.getString("image"));

                    products.add(product);
                }

                results.close();
                DbListener.getConnection().close();

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
            Connection conn = DbListener.getConnection();
            PreparedStatement insertStatement = conn
                    .prepareStatement("INSERT INTO PRODUCTS VALUES (?, ?, ?, ?, ?)");
            insertStatement.setString(1, product.getName());
            insertStatement.setString(2, product.getId());
            insertStatement.setString(3, product.getDescription());
            insertStatement.setInt(4, product.getAmount());
            insertStatement.setString(5, product.getImage());
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
            Connection conn = DbListener.getConnection();
            PreparedStatement stmt = conn
                    .prepareStatement("UPDATE PRODUCTS SET NAME=?, DESCRIPTION=?, AMOUNT=?, IMAGE=? WHERE ID=?");
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getAmount());
            stmt.setString(4, product.getImage());
            stmt.setString(5, product.getId());
            stmt.executeUpdate();
			conn.close();
			return true;
			
		} catch (Exception e) {
            Logger.getRootLogger().error("Erreur lors de la mise à jour en base de données", e);
            return false;
		}
	}

	@Override
    public boolean deleteProduct(String id) {
        try {
            Connection conn = DbListener.getConnection();
            PreparedStatement stmt = conn
                    .prepareStatement("DELETE FROM PRODUCTS WHERE ID=?");
            stmt.setString(1, id);
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
                Connection conn = DbListener.getConnection();
                PreparedStatement stmt = conn
                        .prepareStatement("SELECT * FROM PRODUCTS WHERE ID=?");
                stmt.setString(1, id);
                results = stmt.executeQuery();
                if (results.next()) {
                    product = new Product();
                    product.setId(results.getString("id"));
                    product.setName(results.getString("name"));
                    product.setDescription(results.getString("description"));
                    product.setAmount(results.getInt("amount"));
                    product.setImage(results.getString("image"));
                }

                results.close();
                DbListener.getConnection().close();

            } catch (SQLException e) {
				e.printStackTrace();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

        return product;
    }

}

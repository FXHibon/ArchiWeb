package fr.epsi.tp.ws.services.impl;

/**
 * Created by Baptiste on 10/03/2015.
 */

import fr.epsi.tp.ws.beans.Product;
import fr.epsi.tp.ws.listeners.DbListener;
import fr.epsi.tp.ws.services.CartService;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {

    @Override
    public List<Product> getCartProducts(String user) {
        List<Product> products = new ArrayList<Product>();

        try {
            ResultSet results;
            String request = "SELECT ID, NAME, IMAGE, DESCRIPTION, CARTS.AMOUNT FROM PRODUCTS, CARTS WHERE CARTS.USER = '" + user + "' AND CARTS.PRODUCT = PRODUCTS.ID";

            try {
                Statement stmt = DbListener.getConnection().createStatement();
                results = stmt.executeQuery(request);

                while (results.next()) {
                    Product product = new Product();
                    product.setId(results.getString("id"));
                    product.setName(results.getString("name"));
                    product.setImage(results.getString("image"));
                    product.setDescription(results.getString("description"));
                    product.setAmount(results.getInt("amount"));
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
    public boolean addCartProduct(String user, String productId, int amount) {
        try {
            Connection conn = DbListener.getConnection();
            PreparedStatement insertStatement = conn
                    .prepareStatement("INSERT INTO CARTS VALUES (?, ?, ?)");
            insertStatement.setString(1, user);
            insertStatement.setString(2, productId);
            insertStatement.setInt(3, amount);
            insertStatement.execute();
            conn.close();

            return true;
        } catch (Exception e) {
            Logger.getRootLogger().error("Erreur lors d'insertion en base de données", e);
            return false;
        }
    }

    @Override
    public boolean updateCartProduct(String user, String productId, int amount) {
        try {
            Connection conn = DbListener.getConnection();
            PreparedStatement stmt = conn
                    .prepareStatement("UPDATE CARTS SET AMOUNT=? WHERE USER=? AND PRODUCT=?");
            stmt.setInt(1, amount);
            stmt.setString(2, user);
            stmt.setString(3, productId);
            stmt.executeUpdate();
            conn.close();
            return true;

        } catch (Exception e) {
            Logger.getRootLogger().error("Erreur lors de la mise à jour en base de données", e);
            return false;
        }
    }

    @Override
    public boolean deleteCartProduct(String user, String productId) {
        try {
            Connection conn = DbListener.getConnection();
            PreparedStatement stmt = conn
                    .prepareStatement("DELETE FROM CARTS WHERE USER=? AND PRODUCT=?");
            stmt.setString(1, user);
            stmt.setString(1, productId);
            stmt.executeUpdate();
            conn.close();

            return true;
        } catch (Exception e) {
            Logger.getRootLogger().error("Erreur lors de la suppression en base de données", e);
            return false;
        }
    }

    @Override
    public Product getCartProduct(String user, String productId) {
        Product product = null;

        try {
            ResultSet results = null;

            try {
                Connection conn = DbListener.getConnection();
                PreparedStatement stmt = conn
                        .prepareStatement("SELECT ID, NAME, IMAGE, DESCRIPTION, CARTS.AMOUNT FROM PRODUCTS, CARTS WHERE CARTS.USER =? AND CARTS.PRODUCT=? AND CARTS.PRODUCT = PRODUCTS.ID");
                stmt.setString(1, user);
                stmt.setString(2, productId);
                results = stmt.executeQuery();
                if (results.next()) {
                    product = new Product();
                    product.setId(results.getString("id"));
                    product.setName(results.getString("name"));
                    product.setImage(results.getString("image"));
                    product.setDescription(results.getString("description"));
                    product.setAmount(results.getInt("amount"));
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


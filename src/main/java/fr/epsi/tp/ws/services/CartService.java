package fr.epsi.tp.ws.services;

import fr.epsi.tp.ws.beans.Product;

import java.util.List;

/**
 * Created by Baptiste on 10/03/2015.
 */

public interface CartService {
    public List<Product> getCartProducts(String user);

    public Product getCartProduct(String user, String productId);

    public boolean addCartProduct(String user, String productId, int amount);

    public boolean updateCartProduct(String user, String productId, int amount);

    public boolean deleteCartProduct(String user, String productId);
}


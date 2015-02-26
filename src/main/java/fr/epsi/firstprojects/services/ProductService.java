package fr.epsi.firstprojects.services;

import java.util.List;

import fr.epsi.firstprojects.beans.Product;

public interface ProductService {

    public List<Product> getProducts();

    public Product getProduct(String id);

    public boolean addProduct(Product product);

    public boolean updateProduct(Product product);

    public boolean deleteProduct(Product product);
}

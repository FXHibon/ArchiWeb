package fr.epsi.firstprojects.services;

import java.util.List;

import fr.epsi.firstprojects.beans.Product;

public interface ContactService {

    public List<Product> getContacts();

    public Product getContact(String id);

    public boolean addContact(Product product);

    public boolean updateContact(Product product);

    public boolean deleteContact(Product product);
}

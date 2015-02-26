package fr.epsi.firstprojects.controllers.soap;

import fr.epsi.firstprojects.beans.Product;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.Resource;

public class ProductServiceImpl extends SpringBeanAutowiringSupport
        implements ProductService {

    @Resource
    private fr.epsi.firstprojects.services.ProductService productService;

    @Override
    public Product getProduct(String id) {
        return productService.getProduct(id);
    }

}

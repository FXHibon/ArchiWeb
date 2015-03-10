package fr.epsi.tp.ws.controllers.soap;

import fr.epsi.tp.ws.beans.Product;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.Resource;

public class ProductServiceImpl extends SpringBeanAutowiringSupport
        implements ProductService {

    @Resource
    private fr.epsi.tp.ws.services.ProductService productService;

    @Override
    public Product getProduct(String id) {
        return productService.getProduct(id);
    }

}

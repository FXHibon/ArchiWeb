package fr.epsi.firstprojects.controllers.rest;

import fr.epsi.firstprojects.beans.Product;
import fr.epsi.firstprojects.services.ConnectionService;
import fr.epsi.firstprojects.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource
    private ConnectionService connectionService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Product> getProducts(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return productService.getProducts();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Product getProduct(@PathVariable("id") String id, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        Product product = productService.getProduct(id);
        if (product == null) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            return product;
        }
        return null;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.POST)
    public Product setProduct(@PathVariable("id") String id,
                           @RequestBody Product product,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) {

        int httpCode;

        if (product != null) {
            try {
                productService.updateProduct(product);
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                return product;
            } catch (Exception e) {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return null;
            }
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void deleteDelete(@PathVariable String id, HttpServletRequest httpServletRequest) {
        productService.deleteProduct(id);
    }
}

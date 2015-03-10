package fr.epsi.tp.ws.controllers.rest;

import fr.epsi.tp.ws.beans.Product;
import fr.epsi.tp.ws.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ProductController {

    @Resource
    private ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Product> getProducts() {
        return productService.getProducts();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Product getProduct(@PathVariable("id") String id, HttpServletResponse httpServletResponse) {

        Product product = productService.getProduct(id);
        if (product == null) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            return product;
        }
        return null;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.POST)
    public Product setProduct(@PathVariable("id") String id, @RequestBody Product product, HttpServletResponse httpServletResponse) {
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
    public void deleteDelete(@PathVariable String id, HttpServletResponse httpServletResponse) {
        try {
            productService.deleteProduct(id);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

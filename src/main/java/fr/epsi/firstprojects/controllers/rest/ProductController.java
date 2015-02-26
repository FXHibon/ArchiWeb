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

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Product> getProducts(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        if (tokenPresent(httpServletRequest) && tokenValid(httpServletRequest)) {
            return productService.getProducts();
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return null;
    }

    private boolean tokenValid(HttpServletRequest httpServletRequest) {
        String tokenVal = "";
        for (Cookie cookie : httpServletRequest.getCookies()) {
            if (cookie.getName().equals("token")) {
                tokenVal = cookie.getValue();
                break;
            }
        }
        return connectionService.isConnected(tokenVal);
    }

    private boolean tokenPresent(HttpServletRequest httpServletRequest) {
        for (Cookie cookie : httpServletRequest.getCookies()) {
            if (cookie.getName().equals("token")) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Product getProduct(@PathVariable("id") String id, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        if (tokenPresent(httpServletRequest) && tokenValid(httpServletRequest)) {
            Product product = productService.getProduct(id);
            if (product == null) {
                httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } else {
                return product;
            }
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return null;
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public void setContact(@PathVariable("id") String id,
                           @RequestBody Product product,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) {
        if (tokenPresent(httpServletRequest) && tokenValid(httpServletRequest)) {
            if (product != null) {
                productService.updateProduct(product);
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @RequestMapping(value = "/product", method = RequestMethod.DELETE)
    public void deleteContact(@RequestBody Product product, HttpServletRequest httpServletRequest) {
        if (tokenPresent(httpServletRequest) && tokenValid(httpServletRequest)) {
            productService.deleteProduct(product);
        }
    }
}

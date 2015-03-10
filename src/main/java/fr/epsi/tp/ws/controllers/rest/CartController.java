package fr.epsi.tp.ws.controllers.rest;

import fr.epsi.tp.ws.beans.Product;
import fr.epsi.tp.ws.beans.User;
import fr.epsi.tp.ws.services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CartController {

    @Resource
    private CartService cartService;

    @RequestMapping(value = "/carts", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Product> getCartProducts(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        return cartService.getCartProducts(user.getLogin());
    }

    @RequestMapping(value = "/carts/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Product getProduct(@PathVariable("id") String id, HttpServletResponse httpServletResponse, HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        Product product = cartService.getCartProduct(user.getLogin(), id);
        if (product == null) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            return product;
        }
        return null;
    }

    @RequestMapping(value = "/carts/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    Product setProduct(@PathVariable("id") String id, @RequestBody Product product, HttpServletResponse httpServletResponse, HttpServletRequest req) {
        if (product != null) {
            try {
                User user = (User) req.getSession().getAttribute("user");
                Product tmp = cartService.getCartProduct(user.getLogin(), id);
                if (tmp == null) {
                    cartService.addCartProduct(user.getLogin(), product.getId(), product.getAmount());
                } else {
                    cartService.updateCartProduct(user.getLogin(), product.getId(), (product.getAmount() + tmp.getAmount()));
                }
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

    @RequestMapping(value = "/carts/{id}", method = RequestMethod.DELETE)
    public void deleteDelete(@PathVariable String id, HttpServletResponse httpServletResponse, HttpServletRequest req) {
        try {
            User user = (User) req.getSession().getAttribute("user");
            cartService.deleteCartProduct(user.getLogin(), id);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

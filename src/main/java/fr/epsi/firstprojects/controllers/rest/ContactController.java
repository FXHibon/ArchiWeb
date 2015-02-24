package fr.epsi.firstprojects.controllers.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.epsi.firstprojects.beans.Product;
import fr.epsi.firstprojects.services.ContactService;

public class ContactController {

    @Resource
    private ContactService contactService;

	@RequestMapping(value="/contact", method=RequestMethod.GET)
    public
    @ResponseBody
    List<Product> getContacts(
            HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		if (httpServletRequest.getSession(false) == null) {
			httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		} else if (httpServletRequest.getSession().getAttribute("token") == null){ 
			httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		} else if (httpServletRequest.getParameter("token") == null){ 
			httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

		} else {
			String token = httpServletRequest.getSession().getAttribute("token").toString();
			String tokenRecu = httpServletRequest.getParameter("token");

			if (token.equals(tokenRecu)) {
                return contactService.getContacts();
            } else {
				httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		return null;
	}

	@RequestMapping(value="/contact/{id}", method=RequestMethod.GET)
    public
    @ResponseBody
    Product getContact(@PathVariable("id") String id, HttpServletResponse httpServletResponse) {
        Product product = contactService.getContact(id);
        if (product == null) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
        return product;
    }

	@RequestMapping(value="/contact/{id}/{name}", method=RequestMethod.GET)
	public void setContact(@PathVariable("id") String id, 
			@PathVariable("name") String name, 
			HttpServletResponse httpServletResponse) {
        Product product = contactService.getContact(id);
        if (product != null) {
            product.setName(name);
            contactService.updateContact(product);
        } else {
			httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@RequestMapping(value="/contact/{id}", method=RequestMethod.POST)
    public void setContact(@RequestBody Product product) {

	}

	@RequestMapping(value="/contact/{id}", method=RequestMethod.DELETE)
	public void deleteContact(@PathVariable("id") String id) {

	}
}

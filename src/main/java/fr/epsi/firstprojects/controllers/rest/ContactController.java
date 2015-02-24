package fr.epsi.firstprojects.controllers.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.epsi.firstprojects.beans.Contact;
import fr.epsi.firstprojects.services.ContactService;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping(value="/contact", method=RequestMethod.GET)
	public @ResponseBody List<Contact> getContacts(
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
				List<Contact> contacts = contactService.getContacts();
				return contacts;
			} else {
				httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		return null;
	}

	@RequestMapping(value="/contact/{id}", method=RequestMethod.GET)
	public @ResponseBody Contact getContact(@PathVariable("id") String id, HttpServletResponse httpServletResponse) { 
		Contact contact = contactService.getContact(id);
		if (contact == null) {
			httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return contact;
	}

	@RequestMapping(value="/contact/{id}/{name}", method=RequestMethod.GET)
	public void setContact(@PathVariable("id") String id, 
			@PathVariable("name") String name, 
			HttpServletResponse httpServletResponse) {
		Contact contact = contactService.getContact(id);
		if (contact != null) {
			contact.setName(name);
			contactService.updateContact(contact);
		} else {
			httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@RequestMapping(value="/contact/{id}", method=RequestMethod.POST)
	public void setContact( @RequestBody Contact contact) {

	}

	@RequestMapping(value="/contact/{id}", method=RequestMethod.DELETE)
	public void deleteContact(@PathVariable("id") String id) {

	}
}

package fr.epsi.firstprojects.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.epsi.firstprojects.beans.Contact;
import fr.epsi.firstprojects.services.ContactService;
import fr.epsi.firstprojects.services.impl.ContactServiceImpl;

/**
 * Servlet implementation class ContactListServlet
 */
@WebServlet("/ContactListServlet")
public class ContactListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String action = request.getParameter("action");
		ContactService service = new ContactServiceImpl();

		if (action != null && action.equals("suppression")) {
			Contact contact = new Contact();
			contact.setLogin(login);
			if  (!service.deleteContact(contact)) {
				Logger.getRootLogger().error("Erreur lors de la suppression de " + login);
			}
			request.setAttribute("contacts", service.getContacts());
			request.getRequestDispatcher("contacts.jsp").forward(request, response);
			return;
		} else {
			if  (login != null) {
				Contact contactAModifier = service.getContact(login);
				if (contactAModifier != null) {
					request.setAttribute("login", contactAModifier.getLogin());
					request.setAttribute("nom", contactAModifier.getName());
					request.setAttribute("password", contactAModifier.getPassword());
				}
				request.getRequestDispatcher("contactForm.jsp").forward(request, response);
			} else {
				request.setAttribute("contacts", service.getContacts());
				request.getRequestDispatcher("contacts.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ContactService service = new ContactServiceImpl();
		String login = request.getParameter("login");

		if (login != null && !login.equals("")) {
			String name = request.getParameter("nom");
			String password = request.getParameter("password");
			Contact contact = new Contact();
			contact.setLogin(login);
			contact.setName(name);
			contact.setPassword(password);
			if (service.getContact(login) != null) {
				if (!service.updateContact(contact)) {
					Logger.getRootLogger().error("Erreur lors de la mise à jour du contact " + login);
				}
			} else {
				if (!service.addContact(contact)) {
					Logger.getRootLogger().error("Erreur lors de l'ajout du contact " + login);
				}
			}
		}
		request.setAttribute("contacts", service.getContacts());
		request.getRequestDispatcher("contacts.jsp").forward(request, response);
	}

}

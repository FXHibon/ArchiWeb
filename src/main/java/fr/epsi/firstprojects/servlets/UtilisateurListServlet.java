package fr.epsi.firstprojects.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epsi.firstprojects.beans.Utilisateur;
import fr.epsi.firstprojects.listeners.MyListener;

/**
 * Servlet implementation class UtilisateurListServlet
 */
@WebServlet("/UtilisateurListServlet")
public class UtilisateurListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UtilisateurListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String action = request.getParameter("action");

		if (action != null && action.equals("suppression")) {
			for (Utilisateur utilisateur : MyListener.getListOfUtilisateurs()) {
				if (utilisateur.getLogin().equals(login)) {
					MyListener.getListOfUtilisateurs().remove(utilisateur);
					request.setAttribute("utilisateurs", MyListener.getListOfUtilisateurs());
					request.getRequestDispatcher("utilisateurs.jsp").forward(request, response);
					return;
				}
			}
		} else {
			if  (login != null) {
				Utilisateur utilisateurAModifier = null;
				for (Utilisateur utilisateur : MyListener.getListOfUtilisateurs()) {
					if (utilisateur.getLogin().equals(login)) {
						utilisateurAModifier = utilisateur;
					}
				}
				if (utilisateurAModifier != null) {
					request.setAttribute("login", utilisateurAModifier.getLogin());
					request.setAttribute("nom", utilisateurAModifier.getName());
					request.setAttribute("password", utilisateurAModifier.getPassword());
				}
				request.getRequestDispatcher("utilisateurForm.jsp").forward(request, response);
			} else {
				request.setAttribute("utilisateurs", MyListener.getListOfUtilisateurs());
				request.getRequestDispatcher("utilisateurs.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login");
		if (login != null && !login.equals("")) {
			String name = request.getParameter("nom");
			String password = request.getParameter("password");
			String administrateur = request.getParameter("administrateur");
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setLogin(login);
			utilisateur.setName(name);
			utilisateur.setPassword(password);
			utilisateur.setAdministrateur(administrateur != null);
			MyListener.getListOfUtilisateurs().add(utilisateur);
		}
		request.setAttribute("utilisateurs", MyListener.getListOfUtilisateurs());
		request.getRequestDispatcher("utilisateurs.jsp").forward(request, response);
	}

}

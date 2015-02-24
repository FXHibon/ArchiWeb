package fr.epsi.firstprojects.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculServlet
 */
@WebServlet("/CalculServlet")
public class CalculServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = request.getParameter("valeur");
		
		Double valueDouble = calcul(value);
		
		if (valueDouble != null) {
			response.getWriter().append("valeur x 2= " + valueDouble);
			response.flushBuffer();
		} else {
			response.getWriter().append("valeur non numérique: " + value);
			response.flushBuffer();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = request.getParameter("valeur");
		
		Double valueDouble = calcul(value);
		
		if (valueDouble != null) {
			response.getWriter().append("valeur x 2= " + valueDouble);
			response.flushBuffer();
		} else {
			response.getWriter().append("valeur non numérique: " + value);
			response.flushBuffer();
		}
	}
	
	private Double calcul(String value) {
		try {
			Double valueDouble = Double.valueOf(value) * 2;
			return valueDouble;
		} catch (Exception e) {
			return null;
		}
	}

}

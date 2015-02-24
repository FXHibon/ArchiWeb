<%@page import="fr.epsi.firstprojects.beans.Utilisateur"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% 
		Utilisateur utilisateurConnecte = (Utilisateur)request.getSession().getAttribute("utilisateur");
		out.print(utilisateurConnecte.getName());
	 %>
	<br />
	<table width="400px" align="center" border="1">
		<tr>
			<th><b><a href="accueil.jsp">Accueil</a></b></th>
			<th><b><a href="ContactListServlet">Contacts</a></b></th>
			<% if (((Utilisateur)request.getSession().getAttribute("utilisateur")).isAdministrateur()) { %>
			<th><b><a href="UtilisateurListServlet">Utilisateurs</a></b></th>
			 <% } %>
			<th><b><a href="LogoutServlet">Deconnexion</a></b></th>
		</tr>
	</table>
	<br/>
	<table width="400px" align="center" border="1">
		<tr>
			<th><b>Admin</b></th>
			<th><b>Login</b></th>
			<th><b>Nom</b></th>
			<th><b>&nbsp;</b></th>
		</tr>
		<%
			if (request.getAttribute("utilisateurs") != null) {
				List<Utilisateur> utilisateurs = (List<Utilisateur>) request.getAttribute("utilisateurs");
				for (Utilisateur utilisateur : utilisateurs) {
					out.print("<tr>");
					out.print("<td align='center'>");
					if (utilisateur.isAdministrateur()) {
						out.print("<img src='user_admin.gif'>");
					} else {
						out.print("&nbsp;");
					}
					out.print("</td>");
					out.print("<td>");
					if (utilisateurConnecte.getLogin().equals(utilisateur.getLogin())) {
						out.print(utilisateur.getLogin());
					} else {
						out.print("<a href=\"UtilisateurListServlet?login="
								+ utilisateur.getLogin() + "\">" + utilisateur.getLogin()
								+ "</a>");
					}
					out.print("</td>");
					out.print("<td>");
					out.print(utilisateur.getName());
					out.print("</td>");
					out.print("<td>");
					if (utilisateurConnecte.getLogin().equals(utilisateur.getLogin())) {
						out.print("&nbsp;");
					} else {
						out.print("<a href=\"UtilisateurListServlet?action=suppression&login="
								+ utilisateur.getLogin() + "\">" + "<img src='supprimer.png' border='0'>"
								+ "</a>");
					}
					out.print("</td>");
					out.print("</tr>");
				}
			}
		%>
		<tr>
			<td colspan="4" align="right"><a href="utilisateurForm.jsp"><input
					type="button" value="Ajouter un utilisateur" /></a></td>
		</tr>
	</table>
</body>
</html>
<%@page import="fr.epsi.firstprojects.beans.Utilisateur"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% 
		out.print(((Utilisateur)request.getSession().getAttribute("utilisateur")).getName());
	 %>
<br/>
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
</body>
</html>
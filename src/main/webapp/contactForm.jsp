<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
String login = null;
String nom = "";
String password = "";
if (request.getAttribute("login") != null) {
	login = (String)request.getAttribute("login");
}
if (request.getAttribute("nom") != null) {
	nom = (String)request.getAttribute("nom");
}
if (request.getAttribute("password") != null) {
	password = (String)request.getAttribute("password");
}
%>
</head>
<body>
<form action="ContactListServlet" method="post">
	<% if (login == null) {%>
		Login<input type="text" name="login"/><br>
	<% } else { %>
		Login<input type="text" name="login" value="<%=login%>" disabled/><br>
	<% } %>
	Nom <input type="text" name="nom" value="<%=nom%>"/><br>
	Password <input type="password" name="password" value="<%=password%>"/><br>
	<% if (login == null) {%>
		<input type="submit" value="Ajouter">
		<% } else { %>
		<input type="submit" value="Modifier">
		<% } %>
	<a href="javascript:history.back();"><input type="button" value="Annuler"></a>
</form>
</body>
</html>
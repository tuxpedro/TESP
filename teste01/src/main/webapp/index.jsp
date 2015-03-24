<%@page contentType="text/html" import="java.util.*, java.text.*"
	pageEncoding="ISO-8859-1"%>
<html>
<body>
	<h2>Teste01</h2>
	<%
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		out.println("Hoje é dia: " + sdf.format(new Date()));
	%>
</body>
</html>

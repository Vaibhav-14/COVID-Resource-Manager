<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Object error = request.getAttribute("error");
	%>
<h1>Something went wrong</h1>
<p>${error.message }</p>
</body>
</html>
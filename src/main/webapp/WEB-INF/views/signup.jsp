<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="sf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User registration</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/home" > Home </a>
	<p>
		Already have an account? 
		<a href="${pageContext.request.contextPath}/user/login" > log in </a>
		here
	</p>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>log in</title>
</head>
<body>
	<h3>Log in Page</h3>
	<sf:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
		<c:if test="${param.error != null}">
			<b>invalid username or password</b>
		</c:if>
		
		<c:if test="${param.logout != null}">
			<b>you have been logged out</b>
		</c:if>
		<p>
			Username: <input type="text" name="username" />
		</p>
		<p>
			Password: <input type="password" name="password" />
		</p>
		<input type="submit" value="Login">
	</sf:form>
	<p>
		Don't have an account? 
		<a href="${pageContext.request.contextPath}/user/register" > Sign Up </a>
		here
	</p>
	
</body>

</html>
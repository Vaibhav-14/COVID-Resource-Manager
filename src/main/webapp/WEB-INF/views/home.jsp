<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
		<title>Home Page</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	</head>
	<body>
		<nav class="navbar navbar-inverse">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="#">Covid Resource Manager</a>
		    </div>
		    <ul class="nav navbar-nav">
		      <li class="active"><a href="#">Home</a></li>
		      <li><a href="#">Page 1</a></li>
		      <li><a href="#">Page 2</a></li>
		    </ul>
		    <c:if test="${pageContext.request.userPrincipal.name == null}">
		    	<ul class="nav navbar-nav navbar-right">
			      <li><a href="${pageContext.request.contextPath}/user/register"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
			      <li><a href="${pageContext.request.contextPath}/user/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
			    </ul>
			</c:if>
			<c:if test="${pageContext.request.userPrincipal.name != null}">
		    	<ul class="nav navbar-nav navbar-right">
			      <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${pageContext.request.userPrincipal.name}</a></li>
			      <li><a href="${pageContext.request.contextPath}/user/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
			    </ul>
			</c:if>
		    
		  </div>
		</nav>
		<h1>Welcome to Covid-Resouce-Manager</h1>
	</body>
</html>
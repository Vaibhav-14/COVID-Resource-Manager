<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="sf" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
	      <sec:authorize access="hasRole('USER')">
	      	<li><a href="${pageContext.request.contextPath}/${pageContext.request.userPrincipal.name}/createpost">Create Post</a></li>
	      </sec:authorize>
	      <li><a href="#">Page 2</a></li>
	    </ul>
	    <sec:authorize access="!isAuthenticated()">
	    	<ul class="nav navbar-nav navbar-right">
		      <li><a href="${pageContext.request.contextPath}/user/register"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
		      <li><a href="${pageContext.request.contextPath}/user/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
		    </ul>
	    </sec:authorize>
	
	    <sec:authorize access="isAuthenticated()">
	    	<ul class="nav navbar-nav navbar-right">
		      <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${pageContext.request.userPrincipal.name}</a></li>
		      <li><a href="${pageContext.request.contextPath}/user/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
		    </ul>
	    </sec:authorize>
	    
	  </div>
	</nav>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
</head>
<%@ include file="parts/header.jsp"%>
<body>
	<%
		Object IsUsername = request.getAttribute("IsUsername");
		Object tag = request.getAttribute("tag");
		Object username = request.getAttribute("username");
		Object user = request.getAttribute("user");
	%>
	
	<%
		if (tag != null) {
	%>  	Tag: ${tag}
	<%
		} else {
	%>
		<b>Profile Info</b>
		<br> Username: ${username }
	<% } %>
	<%
		if (user != null && IsUsername == null) {
	%>
	<a href="/user/update/${post.id }">
		<button>Edit Profile</button>
	</a>
	<br>
	<br> First Name: ${user.firstname }
	<br> Last Name: ${user.lastname }
	<br> Mobile No.: ${user.mobile }
	<br> Email: ${user.email }
	<br> Date of Birth: ${user.dateOfBirth }
	<br> Gender: ${user.gender }
	<br>
	<br>
	<b>My Posts</b>
	<%
		} else {
	%>
	<br>
	<br>
	<b>Posts</b>
	<%
		}
	%>
	<br>
	<br>
	
	<%@ include file="parts/posts.jsp"%>


</body>
<%@ include file="parts/footer.jsp"%>
</html>
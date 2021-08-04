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
		Object username = request.getAttribute("username");
	%>
	<b>Profile Info</b>
	<br> Username: ${user.username }
	<%
		if (username == null) {
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
	<c:choose>
	<c:when test="${empty posts}">
    	    <h3>You don't have any posts</h3>
	</c:when>
	
	<c:otherwise>
	<c:forEach items="${posts }" var="post" varStatus="tagStatus">                                 
Post Type: ${post.type }
<%
	if (username == null) {
%>
		<a href="/post/update/${post.id }">
			<button>Update Post</button>
		</a>
		<a href="/post/delete/${post.id }">
			<button>Delete Post</button>
		</a>
		<%
			}
		%>
		<br>
Post Message: ${post.message }<br>
Post Tags: 
<c:forEach items="${post.tags }" var="tag" varStatus="tagStatus">
${tag.name }
</c:forEach>
		<br>
		<br>
		<br>
	</c:forEach>
	
</c:otherwise>
</c:choose>

<%-- Back button to get back to home page --%>
<a href="/home">
			<button>Back</button>

</body>
<%@ include file="parts/footer.jsp"%>
</html>
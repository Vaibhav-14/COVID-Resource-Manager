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
	<b>Profile Info</b>
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
	<br>
	<br>
	<c:forEach items="${posts }" var="post" varStatus="tagStatus">                                 
Post Type: ${post.type }
<a href="/post/update/${post.id }">
			<button>Update Post</button>
		</a>
		<a href="/post/delete/${post.id }">
			<button>Delete Post</button>
		</a>
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

</body>
<%@ include file="parts/footer.jsp"%>
</html>
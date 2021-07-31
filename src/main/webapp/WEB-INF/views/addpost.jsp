<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="sf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Post</title>
</head>
<body>
<sf:form modelAttribute = "postform" method = "POST" >
Type: <sf:input path="post.type"/><br>
Message: <sf:textarea path="post.message"/><br>
Tags: <sf:input path="tags"/>
<sf:button name="Submit" value="Submit">Add Post</sf:button>
</sf:form>


</body>
</html>
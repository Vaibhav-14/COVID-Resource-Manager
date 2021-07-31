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
<sf:form id="form1" modelAttribute = "post" method = "POST" >
	Type: <sf:input path="type"/><br>
	Message: <sf:textarea path="message"/><br>
	Tags: <sf:input path="tagStr"/>
	<sf:button value="Submit"> Create Post</sf:button>
</sf:form>

</body>
</html>
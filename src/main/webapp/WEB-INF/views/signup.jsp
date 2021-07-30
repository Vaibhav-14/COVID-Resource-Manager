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

<b>User Registration Form</b> <br><br>

<sf:form  modelAttribute="user" method="POST">
	
	<b>First Name</b>: <sf:input path="firstname"/> <sf:errors path="firstname"/> <br>
	<b>Last Name</b>: <sf:input path="lastname"/> <sf:errors path="lastname"/> <br>
	<b>Username</b>: <sf:input path="username"/> <sf:errors path="username"/> <br>
	<b>Password</b>: <sf:input path="password"/> <sf:errors path="password"/> <br>
	<b>Email</b>: <sf:input path="email"/> <sf:errors path="email"/> <br>
	<b>Mobile Number</b>: <sf:input path="mobile"/> <sf:errors path="mobile"/> <br>
	<b>Date of Birth</b>: <sf:input path="dateOfBirth"/> <sf:errors path="dateOfBirth"/> <br>
	<b>Gender</b>: <sf:input path="gender"/> <sf:errors path="gender"/> <br>
	
	<sf:button name="Submit" value="Submit">Register</sf:button>


</sf:form>


</body>
</html>
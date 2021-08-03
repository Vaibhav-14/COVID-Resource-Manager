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

	<b>User SignUp</b> <br><br>
	
	<sf:form  modelAttribute="user" method="POST">
		
		<b>First Name</b>: <sf:input path="firstname"/> <sf:errors path="firstname"/> <br>
		<b>Last Name</b>: <sf:input path="lastname"/> <sf:errors path="lastname"/> <br>
		<b>Username</b>: <sf:input path="username"/> <sf:errors path="username"/><br>
		<b>Password</b>: <sf:input path="password"/> <sf:errors path="password"/> <br>
		<b>Confirm Password</b>: <sf:input path="retypepassword"/> <sf:errors path="retypepassword"/> <br>
		<b>Email</b>: <sf:input path="email"/> <sf:errors path="email"/> <br>
		<b>Mobile Number</b>: +91 <sf:input path="mobile"/> <sf:errors path="mobile"/> <br>
		<b>Date of Birth</b>: <sf:input path="dateOfBirth" type= "date"/> <sf:errors path="dateOfBirth"/> <br>
		<b>Gender</b>:  Male <sf:radiobutton path="Gender" value="M"/>   Female <sf:radiobutton path="Gender" value="F"/> <br> 
		<sf:errors path="gender"/> 
		<br>
		
		<sf:button name="Submit" value="Submit">Register</sf:button>
	
	
	</sf:form>

	<p>
		Already have an account? 
		<a href="${pageContext.request.contextPath}/user/login" > log in </a>
		here
	</p>


</body>
</html>
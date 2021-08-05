<%@ include file="parts/meta.jsp" %> 

<title>log in</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/home" > Home </a>
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
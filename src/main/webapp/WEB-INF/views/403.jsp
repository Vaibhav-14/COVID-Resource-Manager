<%@ include file="parts/meta.jsp"%>

<title>Access Denied</title>
</head>
<body>
<center>

<br><a href="/">HOMEPAGE</a>
<br><br>
<%
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	if (auth != null) {
		out.println("User '" + auth.getName() + "' attempted to access the protected URL: ");
	}
%>
</center>
</body>
</html>
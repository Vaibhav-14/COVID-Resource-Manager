<%@ include file="parts/meta.jsp"%>

<title>Access Denied</title>
</head>
<body>
<br>
<center>

<a class="btn btn-primary" href="/" role="button">HOMEPAGE</a>

<br><br>
<div>
<h2>
<%
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	if (auth != null) {
		out.println("User '" + auth.getName() + "' attempted to access the protected URL: ");
	}
%>
</h2>

</div>
</center>
</body>
</html>
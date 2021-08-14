<%@ include file="parts/meta.jsp"%>
<title>Ugh!</title>
</head>
<body>
	<%
		Object error = request.getAttribute("error");
	%>
<h1>Something went wrong</h1>
<p>${error.message }</p>
<a href="/home">HomePage</a>
</body>
</html>
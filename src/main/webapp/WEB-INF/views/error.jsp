<%@ include file="parts/meta.jsp"%>
<title>Ugh!</title>
</head>
<body>
	<%
		Object error = request.getAttribute("error");
	%>
<h1 class="text-center">Something went wrong</h1>
<br>
<p class="text-center" style="color:red;">${error.message }</p>
<br>
<a class="btn btn-primary" href="/home" role="button">Home Page</a>
</body>
</html>
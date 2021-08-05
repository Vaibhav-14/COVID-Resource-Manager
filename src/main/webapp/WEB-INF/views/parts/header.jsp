<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="#">Covid Resource Manager</a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li class="active"><a href="/home">Home</a></li>
	      <sec:authorize access="hasAuthority('USER')">
	      	<li><a href="/post/create">Create Post</a></li>
	      </sec:authorize>
	      
	      <li><a href="#">Page 2</a></li>
	      
	    </ul>
	    
	    <form class="form-inline my-2 my-lg-0 navbar-nav navbar-center" method = "POST" action = "/post/searchresult">
	      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	      <input name="searchentry" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
	      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
	    </form>
	    
	    <sec:authorize access="!isAuthenticated()">
	    	<ul class="nav navbar-nav navbar-right">
		      <li><a href="${pageContext.request.contextPath}/user/register"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
		      <li><a href="${pageContext.request.contextPath}/user/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
		    </ul>
	    </sec:authorize>
	
	    <sec:authorize access="isAuthenticated()">
	    	<ul class="nav navbar-nav navbar-right">
		      <li><a href="/user/profile"><span class="glyphicon glyphicon-user"></span> ${pageContext.request.userPrincipal.name}</a></li>
		      <li><a href="${pageContext.request.contextPath}/user/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
		    </ul>
	    </sec:authorize>
	    
	  </div>
	</nav>
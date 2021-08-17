<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<link href="${pageContext.request.contextPath }/css/auto_complete.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/post.css" rel="stylesheet" type="text/css" />
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top shadow bg-body rounded">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Covid Resource Manager</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" href="/home">Home</a>
        </li>
        <sec:authorize access="hasAuthority('USER')">
        <li class="nav-item"> 
          <a class="nav-link active" href="/post/create">Create Post</a>
        </li>
        </sec:authorize>
        <sec:authorize access="hasAuthority('ADMIN')">
        <li class="nav-item"> 
          <a class="nav-link active" href="/tag/create">Add Tag</a>
        </li>
        </sec:authorize>
        </ul>
        
        <ul class="navbar-nav mx-auto mb-2 mb-lg-0">
	        <li class="nav-item">
		      <form class="d-flex"  method = "POST" action = "/post/searchresult">
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		        <input name="searchentry" class="form-control autocomplete" type="search" placeholder="start with '@' or '#'" required>
		        <button class="btn btn-outline-success" type="submit">Search</button>  
		      </form>   
	        </li> 
      	</ul>

      	<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
      	<sec:authorize access="!isAuthenticated()">
        <li class="nav-item">
          <a class="nav-link active" href="${pageContext.request.contextPath}/user/register"><i class="material-icons align-middle">person_add</i>SignUp</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="${pageContext.request.contextPath}/user/login"><i class="material-icons align-middle">login</i>LogIn</a>
        </li>     
        </sec:authorize>  
        
        

      	<sec:authorize access="isAuthenticated()">
          <li class="nav-item">
            <a class="nav-link active" href="${pageContext.request.contextPath}/notifications/">
            <i class="material-icons align-middle">
			  notifications
            </i> 
            </a>
          </li>  
        <li class="nav-item">
          <a class="nav-link active" href="/user/profile"><i class="material-icons align-middle">account_circle</i> ${pageContext.request.userPrincipal.name}</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="${pageContext.request.contextPath}/user/logout"><i class="material-icons align-middle">logout</i> Logout</a>
        </li>     
        </sec:authorize>  
                
      	</ul>
    </div>
  </div>
</nav>

  <br>
  <br>
  <br>
  <br>
  <br>
  <br>

<%@ include file="parts/meta.jsp" %> 
<title>User registration</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
	
  <div class="formcontainer w-100 h-100 p-5 d-flex justify-content-center">	
	<sf:form modelAttribute="user" method="POST">
		<div class="container bg-white rounded shadow-lg ">
		  <div class="row p-2">
		      <div class="col text-center">
				<h1>User SignUp</h1>
        	  </div>		  
		  </div>
		  
		  <div class="row p-2" >
		    <div class="col">
		      
		      <sf:input path="firstname" class="w-100 p-2" placeholder="First Name"/>
		              <div class="small text-danger">
				    	  <sf:errors path="firstname"/>
        	 	      </div>       
		    </div>
		    <div class="col">
		      
		      <sf:input path="lastname" class="w-100 p-2" placeholder="Last Name"/>
		              <div class="small text-danger">
				    	  <sf:errors path="lastname"/>
        	 	      </div>       
		    </div>		    
		  </div>
		  <div class="row p-2">
		      <div class="col">
		      <sf:input path="username" class="w-100 p-2" placeholder="Username"/>
		              <div class="small text-danger">
				    	  <sf:errors path="username"/>
        	 	      </div>
        	  </div>		  
		  </div>
		  <div class="row p-2">
		      <div class="col">
		      <sf:input path="password" type="password" class="w-100 p-2" placeholder="Password"/>
		              <div class="small text-danger">
				    	  <sf:errors path="password"/>
        	 	      </div>
        	  </div>		  
		  </div>	
		  <div class="row p-2">
		      <div class="col">
		      <sf:input path="retypepassword" type="password" class="w-100 p-2" placeholder="Confirm Password"/>
		              <div class="small text-danger">
				    	  <sf:errors path="retypepassword"/>
        	 	      </div>
        	  </div>		  
		  </div>	  
		  <div class="row p-2">
		      <div class="col">
		      <sf:input path="email" class="w-100 p-2" placeholder="Email"/>
		              <div class="small text-danger">
				    	  <sf:errors path="email"/>
        	 	      </div>
        	  </div>		  
		  </div>	
		  <div class="row p-2">
		      <div class="col">
		      <sf:input path="mobile" class="w-100 p-2" placeholder="Mobile Number"/>
		              <div class="small text-danger">
				    	  <sf:errors path="mobile"/>
        	 	      </div>
        	  </div>		  
		  </div>
		  <div class="row p-2">
		      <div class="col">
		      <sf:input path="dateOfBirth" class="w-100 p-2" type="date" placeholder="Mobile Number"/>
		              <div class="small text-danger">
				    	  <sf:errors path="dateOfBirth"/>
        	 	      </div>
        	  </div>		  
		  </div>
		  <div class="row p-2">
		      <div class="col">
					
					<div class="form-check">
					  <sf:radiobutton class="form-check-input" path="Gender" value="M"/>
					  <label class="form-check-label">
					    Male
					  </label>
					</div>
					<div class="form-check">
					  <sf:radiobutton class="form-check-input" path="Gender" value="F"/>
					  <label class="form-check-label">
					    Female
					  </label>
					</div>

		              <div class="small text-danger">
				    	  <sf:errors path="gender"/>
        	 	      </div>
        	  </div>		  
		  </div>
		<sf:button name="Submit" class="btn btn-primary w-100 text-center p-2" value="Submit">Register</sf:button>
		
		<div class= "text-center p-2">
		<p>
		Already have an account? Log In
		<a href="${pageContext.request.contextPath}/user/login" style="text-decoration:none;"> here </a><br>
		<a href="${pageContext.request.contextPath}/home" style="text-decoration:none;" > Homepage </a>
		</p>
		</div>
		</div>
	</sf:form>
	
	</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<%@ include file="parts/footer.jsp" %> 
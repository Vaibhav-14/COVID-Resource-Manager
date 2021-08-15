<%@ include file="parts/meta.jsp" %> 
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<div
	class="formcontainer w-100 h-100 p-5 d-flex justify-content-center vh-200">

	<sf:form
		action="${pageContext.request.contextPath}/authenticateTheUser"
		method="POST">
		<div class="container bg-white rounded shadow-lg ">
		<img src="https://www.smarteyeapps.com/demo/educational-bootstrap-5-login-page-tempalte/assets/images/user.png" class="rounded mx-auto d-block" alt="picture">
			<div class="row p
			-2">
				<div class="col text-center">
					<h1>Login Page</h1>
				</div>
			</div>

			<c:if test="${param.error != null}">
				<b>invalid username or password</b>
			</c:if>
			
			<c:if test="${param.disabled != null}">
				<b>Your account has been suspended</b>
			</c:if>
			
			<c:if test="${param.notfound != null}">
				<div class="text-center" style="color:red;">
				<b>No Such User exists</b>
				</div>
			</c:if>

			<c:if test="${param.logout != null}">
				<div class="text-center" style="color:red;">
				<b>you have been logged out</b>
				</div>
			</c:if>

			<div class="row p-2">

				<label for="username" class="form-label">Username</label> <input
					name="username" class="form-control" id="username">
				<div id="username" class="form-text"></div>
			</div>
			
			
			<div class="row p-2">

				<label for="Password" class="form-label">Password</label> <input type="password"
					name="password" class="form-control" id="password">
			</div>


			<div class="text-center" style="margin-top:5px">
			<button type="submit" class="btn btn-primary">Submit</button>
			</div>

			<div class="text-center p-2">
				<p>
					Don't have an account? <a
						href="${pageContext.request.contextPath}/user/register"> Sign
						Up </a> here<br>
						<a href="${pageContext.request.contextPath}/home" style="text-decoration:none;" > Homepage </a>
				</p>
			</div>
		</div>
	</sf:form>
</div>

<%@ include file="parts/footer.jsp" %>
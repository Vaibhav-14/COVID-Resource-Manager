<%@ include file="parts/meta.jsp" %> 
<title>Login</title>
<script src="${pageContext.request.contextPath }/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-ui.min.js"></script>

<%@ include file="parts/header.jsp" %>
<div
	class="formcontainer w-100 h-100 p-5 d-flex justify-content-center vh-200">

	<sf:form
		action="${pageContext.request.contextPath}/authenticateTheUser"
		method="POST">
		<div class="container bg-white rounded shadow-lg ">
			<div class="row p
			-2">
				<div class="col text-center">
					<h1>Login Page</h1>
				</div>
			</div>

			<c:if test="${param.error != null}">
				<b>invalid username or password</b>
			</c:if>

			<c:if test="${param.logout != null}">
				<b>you have been logged out</b>
			</c:if>

			<div class="row p-2">

				<label for="username" class="form-label">Username</label> <input
					name="username" class="form-control" id="username">
				<div id="username" class="form-text"></div>
			</div>

			<div class="row p-2">

				<label for="Password" class="form-label">Password</label> <input
					name="password" class="form-control" id="password">
			</div>


			<button type="submit" class="btn btn-primary">Submit</button>

			<div class="text-center p-2">
				<p>
					Don't have an account? <a
						href="${pageContext.request.contextPath}/user/register"> Sign
						Up </a> here
				</p>
			</div>
		</div>
	</sf:form>
</div>


<%@ include file="parts/auto.jsp" %>
<%@ include file="parts/footer.jsp" %>
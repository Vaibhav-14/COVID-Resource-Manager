<%@ include file="parts/meta.jsp" %> 
<title>Profile</title>
<%@ include file="parts/header.jsp"%>


<script type="text/javascript">
function confirmation() {
    var answer = confirm("Are you sure you want to delete your account?")
    if (!answer){
        return false;
    }
}
</script>


	<%
		Object IsUsername = request.getAttribute("IsUsername");
		Object tag = request.getAttribute("tag");
		Object username = request.getAttribute("username");
		Object user = request.getAttribute("user");
	%>
	
	<%
		if (tag != null) {
	%> <div class="text-center" style="color:blue;">
		<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-hash" viewBox="0 0 16 16">
  <path d="M8.39 12.648a1.32 1.32 0 0 0-.015.18c0 .305.21.508.5.508.266 0 .492-.172.555-.477l.554-2.703h1.204c.421 0 .617-.234.617-.547 0-.312-.188-.53-.617-.53h-.985l.516-2.524h1.265c.43 0 .618-.227.618-.547 0-.313-.188-.524-.618-.524h-1.046l.476-2.304a1.06 1.06 0 0 0 .016-.164.51.51 0 0 0-.516-.516.54.54 0 0 0-.539.43l-.523 2.554H7.617l.477-2.304c.008-.04.015-.118.015-.164a.512.512 0 0 0-.523-.516.539.539 0 0 0-.531.43L6.53 5.484H5.414c-.43 0-.617.22-.617.532 0 .312.187.539.617.539h.906l-.515 2.523H4.609c-.421 0-.609.219-.609.531 0 .313.188.547.61.547h.976l-.516 2.492c-.008.04-.015.125-.015.18 0 .305.21.508.5.508.265 0 .492-.172.554-.477l.555-2.703h2.242l-.515 2.492zm-1-6.109h2.266l-.515 2.563H6.859l.532-2.563z"/>
</svg>
	</div>  	
	<h2 class="text-center" style="color:blue;">${tag}</h2>
	
	<%
		} else {
	%>
		
	<% } %>
	<c:if test="${user.enabled == true}">
	<c:if test="${pageContext.request.userPrincipal.name == username}">
	<div class="bg-image" style="background-image: url('https://mdbootstrap.com/img/Photos/Others/images/77.webp');">
	<div class="container">
	<div class="container">
	<div class="card mb-3 border-dark shadow-lg p-3 mb-5 text-light bg-dark">
	<div class="bg-image" style="background-image: url('https://amymhaddad.s3.amazonaws.com/oriental-tiles.png');">
	<div class="card-body">
	
		<h1 class="text-center">${username }</h1>
		<br>
		<div class="d-grid gap-2 d-md-block text-center">
			<a href="/user/update/${post.id }">
				<button class="btn btn-primary" type="button">Edit Profile</button>
			</a>
		</div>
		<br>
		<div class="d-grid gap-2 d-md-block text-center">
			<a href="/user/checkPassword">
				<button class="btn btn-primary" type="button">Change Password</button>
			</a>
		</div>
		<br>
		<div class="d-grid gap-2 d-md-block text-center">
		<form method="post" action = "/user/delete">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="hidden" name="username" value="${user.username }"/>
		<button onclick="return confirmation()" name="submit" type="submit" class ="btn btn-primary btn-danger">Delete Account</button>
	</form>
		</div>
		<br>
		<span class="rounded-3">
		<table class="table text-center table-dark table-striped table-hover table-borderless">
			<thead>
				<tr>
					<th scope="col">First Name</th>
					<th scope="col">${user.firstname}</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">Last Name</th>
					<td>${user.lastname }</td>
				</tr>
				
				<tr>
					<th scope="row">Contact Number</th>
					<td>${user.mobile }</td>
				</tr>
				
				<tr>
					<th scope="row">Email</th>
					<td>${user.email }</td>
				</tr>
				
				<tr>
					<th scope="row">Date of Birth</th>
					<td>${user.dateOfBirth }</td>
				</tr>
				
				<tr>
					<th scope="row">Gender</th>
					<td>${user.gender }</td>
				</tr>
			
			</tbody>
		
		</table>
		</span>
		</div>
		</div>
		</div>
		</div>
		</div>
	</div>
	
	
	<br>
	
	<br>
	<br>
	<div class="container text-center">
		<h2>My Posts</h2>
	</div>
	</c:if>
	
	
	<c:if test="${pageContext.request.userPrincipal.name != username}">
	<div class="text-center">
		<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
  		<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
  		<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
		</svg>
	</div>
	<br>
	<h1 class="text-center" style="color:blue;">${username }</h1>
	<br>
	<sec:authorize access="hasAuthority('ADMIN')">
		<div class="text-center">
		<a href="/user/block/${user.username }">
			<button class="btn btn-danger">Block User</button>
		</a>
		</div>
	</sec:authorize>
	<br>
	
	<br>
	<h2 class="text-center"><b>Posts</b></h2>
	</c:if>
	<br>
	<br>
	<%@ include file="parts/posts.jsp"%>
	</c:if>
	<%
		if (tag != null) {
	%>
		<br><br>
		<h2 class="text-center"><b>Posts</b></h2><br>  	
		<%@ include file="parts/posts.jsp"%>
	
	<%
		} else if (user == null) {
	%>
		<br><br>
		<h2 class="text-center"><b>No such user Exists</b></h2>
		<br>
	<% } else { %>
	
		<c:if test="${user.enabled != true}">
	<div class="text-center">
	<sec:authorize access="hasAuthority('ADMIN')">
		<a href="/user/unblock/${user.username }">
			<button class="btn btn-primary btn-lg">Unblock User</button>
		</a>
	</sec:authorize>
	<br>
	<br>
	<h3 padding-left:200px;>This Account is Suspended</h3>
	<br>
	<br>
	</div>
	</c:if>
	<% } %>




<%@ include file="parts/footer.jsp"%>

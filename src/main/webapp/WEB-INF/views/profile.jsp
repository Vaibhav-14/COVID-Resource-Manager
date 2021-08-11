<%@ include file="parts/meta.jsp" %> 
<title>Profile</title>
<%@ include file="parts/header.jsp"%>

	<%
		Object IsUsername = request.getAttribute("IsUsername");
		Object tag = request.getAttribute("tag");
		Object username = request.getAttribute("username");
		Object user = request.getAttribute("user");
	%>
	
	<%
		if (tag != null) {
	%>  	Tag: ${tag}
	
	<%
		} else {
	%>
		<b>Profile Info</b>
		<br> Username: ${username }
	<% } %>
	<c:if test="${user.enabled == true}">
	<c:if test="${pageContext.request.userPrincipal.name == username}">
	<a href="/user/update">
		<button>Edit Profile</button>
	</a>
	
	<form method="post" action = "/user/delete">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="hidden" name="username" value="${user.username }"/>
		<button name="submit" type="submit" class ="btn btn-primary btn-danger">Delete Account</button>
	</form>	
	
	<br>
	<br> First Name: ${user.firstname }
	<br> Last Name: ${user.lastname }
	<br> Mobile No.: ${user.mobile }
	<br> Email: ${user.email }
	<br> Date of Birth: ${user.dateOfBirth }
	<br> Gender: ${user.gender }
	<br>
	<br>
	<b>My Posts</b>
	</c:if>
	<c:if test="${pageContext.request.userPrincipal.name != username}">
	<sec:authorize access="hasAuthority('ADMIN')">
		<a href="/user/block/${user.username }">
			<button>Block User</button>
		</a>
	</sec:authorize>
	<br>
	<br>
	<b>Posts</b>
	</c:if>
	<br>
	<br>
	<%@ include file="parts/posts.jsp"%>
	</c:if>
	<%
		if (tag != null) {
	%>
		<br><br>
		<b>Posts:</b><br>  	
		<%@ include file="parts/posts.jsp"%>
	
	<%
		} else if (user == null) {
	%>
		<br><br>
		<b>No such user Exists</b>
		<br>
	<% } else { %>
	
		<c:if test="${user.enabled != true}">
			<br>
			<b>This account is suspended</b>
			<br>
			<sec:authorize access="hasAuthority('ADMIN')">
				<a href="/user/unblock/${user.username }">
					<button>Unblock User</button>
				</a>
			</sec:authorize>
		</c:if>
	<% } %>

<a href="/home">
			<button>Back</button>
</a>


<%@ include file="parts/footer.jsp"%>

<%@ include file="parts/meta.jsp"%>

<title>Home Page</title>

<%@ include file="parts/header.jsp"%>
<h1>Welcome to Covid Resource Manager</h1>

<c:choose>
	<c:when test="${empty posts}">
    	    <h3>No posts available</h3>
	</c:when>
	
<c:otherwise>
<c:forEach items="${posts }" var="post" varStatus="tagStatus">

	<c:if test="${username != post.user.username }">
		<a href="/user/profile?username=${post.user.username }"><b>@${post.user.username }</b>
		</a>
	</c:if>
	<c:if test="${username == post.user.username }">
		<a href="/user/profile"><b>@${post.user.username }</b> </a>
	</c:if>

	<br>                               
Post Type: ${post.type }
<%-- Here I have commented the delete and update buttons in home page --%>
<%--
<c:if test="${username == post.user.username }">
		<a href="/post/update/${post.id }">
			<button>Update Post</button>
		</a>
		<a href="/post/delete/${post.id }">
			<button>Delete Post</button>
		</a>
	</c:if>
	<br> --%>
Post Message: ${post.message }<br>
Post Tags: 
<c:forEach items="${post.tags }" var="tag" varStatus="tagStatus">
${tag.name }
</c:forEach>
	<br>
	<br>
	<br>
</c:forEach>
</c:otherwise>
</c:choose>

<%@ include file="parts/footer.jsp"%>

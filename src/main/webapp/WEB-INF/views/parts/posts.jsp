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
<c:if test="${pageContext.request.userPrincipal.name == post.user.username }">
		<a href="/post/update/${post.id }">
			<button>Update Post</button>
		</a>
		<a href="/post/delete/${post.id }">
			<button>Delete Post</button>
		</a>
	</c:if>
	<br>
Post Message: ${post.message }<br>
Post Tags: 
<c:forEach items="${post.tags }" var="tag" varStatus="tagStatus">

<form id="form-id" method = "POST" action = "/post/searchresult">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="searchentry" value="${tag.name }">
  	<a href="#" onclick="document.getElementById('form-id').submit();"> ${tag.name } </a>

</form>

</c:forEach>
	<br>
	<br>
	<br>
</c:forEach>
</c:otherwise>
</c:choose>
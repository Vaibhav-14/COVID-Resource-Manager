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
<c:if test="${pageContext.request.userPrincipal.name != post.user.username }">
		<sec:authorize access="hasAuthority('ADMIN')">
		<a href="/post/delete/${post.id }">
			<button>Delete Post</button>
		</a>
		</sec:authorize>
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
Post Comments:<br>
	<c:forEach items="${post.comments }" var="comment"
		varStatus="tagStatus">
Comment By: <c:if test="${username != comment.user.username }">
			<a href="/user/profile?username=${comment.user.username }"><b>@${comment.user.username }</b>
			</a>
		</c:if>
		<c:if test="${username == comment.user.username }">
			<a href="/user/profile"><b>@${comment.user.username }</b> </a>
			<a href="/comment/delete/${comment.id }">
			<button>Delete Comment</button>
		</a>
		</c:if>
		<br>
Comment Comment: ${comment.content }<br>
Comment At: ${comment.dateTime }<br>
		<br>
	</c:forEach>
	<c:if test="${isLoggedIn == true }">
		<sf:form modelAttribute="comment" action="/comment/create" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<sf:input type="hidden" path="post" value="${ post.id}" />
			<sf:input path="content" />
			<sf:button name="Submit" value="Add Comment">Add Comment</sf:button>
		</sf:form>
	</c:if>
	<br>
	<br>
	<br>
</c:forEach>
</c:otherwise>
</c:choose>
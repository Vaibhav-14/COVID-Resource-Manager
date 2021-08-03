<%@ include file="parts/meta.jsp"%>

<title>Home Page</title>

<%@ include file="parts/header.jsp"%>

<h1>Welcome to Covid Resource Manager</h1>

<c:forEach items="${posts }" var="post" varStatus="tagStatus">
	<b>@${post.user.username }</b>
	<br>                               
Post Type: ${post.type }
	<br>
Post Message: ${post.message }<br>
Post Tags: 
<c:forEach items="${post.tags }" var="tag" varStatus="tagStatus">
${tag.name }
</c:forEach>
	<br>
	<br>
	<br>
</c:forEach>

<%@ include file="parts/footer.jsp"%>

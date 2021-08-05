<div class="d-flex flex-row bd-highlight mb-3 justify-content-center">
  <div class="p-2 w-50 bd-highlight" style="min-width: 500px;">
  
<c:choose>
	<c:when test="${empty posts}">
    	    <h3>No posts available</h3>
	</c:when>
<c:otherwise>
<c:forEach items="${posts }" var="post" varStatus="tagStatus">

  
	  <div class="card p-2">
	  <div class="card-body">
	    <div class= "bg-light p-2">
	    
			<c:if test="${username != post.user.username }">
				<a class="card-link" href="/user/profile?username=${post.user.username }"><h5 class="p1 card-title"><i class="p-1 material-icons align-middle">account_circle</i>@${post.user.username }</h5>
				</a>
			</c:if>
			<c:if test="${username == post.user.username }">
			 <a class="card-link" href="/user/profile?"><h5 class="p1 card-title"><i class="p-1 material-icons align-middle">account_circle</i>@${post.user.username }</h5>
				</a>
			</c:if>	    
	    
	    

	    <h6 class="card-subtitle mb-2 text-muted">${post.type }</h6>
		<c:if test="${pageContext.request.userPrincipal.name == post.user.username }">
				<a href="/post/update/${post.id }">
					<button class="btn btn-primary btn-sm">Update Post</button>
				</a>
				<a href="/post/delete/${post.id }">
					<button class="btn btn-primary btn-sm btn-danger">Delete Post</button>
				</a>
			</c:if>	 
			   
		<br><br>	   
	    <p class="card-text">${post.message }</p>
	    <p class="card-text">
	    	<small class="text-muted">
				<c:forEach items="${post.tags }" var="tag" varStatus="tagStatus">
				<form id="form-id" method = "POST" action = "/post/searchresult">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="hidden" name="searchentry" value="${tag.name }">
				  	<a href="#" onclick="document.getElementById('form-id').submit();"> ${tag.name } </a>
				</form>
				</c:forEach>	    		
	    	</small>
	    </p>
	    </div>
	    
	    <p class="text-center fw-bold">Comments</p>
	    
	    
	    
			<c:forEach items="${post.comments }" var="comment" varStatus="tagStatus">
			
			 <c:if test="${username != comment.user.username }">	 
			 	<p class="card-text fw-light">
					<a href="/user/profile?username=${comment.user.username }"><b>@${comment.user.username }</b></a>
					:${comment.content }
				</c:if>
				<c:if test="${username == comment.user.username }">
					<a href="/user/profile"><b>@${comment.user.username }</b> </a>
					<a style="text-decoration: none; color: black;" href="/comment/delete/${comment.id }">
					:${comment.content }
					<button class ="btn btn-primary btn-sm btn-danger">Delete Comment</button>
					</a>
				</c:if>
				 </p>
		    Comment At: ${comment.dateTime }<br>
			</c:forEach>
	    
  
	     
	  </div>
	  
	  	<c:if test="${isLoggedIn == true }">
		<sf:form modelAttribute="comment" action="/comment/create" method="post">

			  <div class="container">
		         <div class="row">
		            <div class="col-9">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<sf:input type="hidden" path="post" value="${ post.id}" />
						<sf:input path="content" class="form-control" />
		    		</div>
		    		<div class="col-1">
		      		<sf:button type="submit" class="btn btn-primary">Comment</sf:button>
		    		</div>
		  		 </div>
			 </div>
			 
		</sf:form>
		</c:if>
	  
 
	</div>
	
	
	
	</c:forEach>
</c:otherwise>
</c:choose>
	
  </div>
</div>













	






	



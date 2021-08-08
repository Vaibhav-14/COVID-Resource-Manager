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

	    	    
<div class="container">
  <div class="row">
    <div class="col">

	    	<c:if test="${post.user.enabled == true}">
			<c:if test="${username != post.user.username }">
				<a class="card-link" href="/user/profile?username=${post.user.username }"><h5 class="p1 card-title"><i class="p-1 material-icons align-middle">account_circle</i>@${post.user.username }</h5>
				</a>
			</c:if>
			<c:if test="${username == post.user.username }">
			 <a class="card-link" href="/user/profile?"><h5 class="p1 card-title"><i class="p-1 material-icons align-middle">account_circle</i>@${post.user.username }</h5>
				</a>
			</c:if>	    
    

    </div>
    <c:if test="${pageContext.request.userPrincipal.name == post.user.username }">
    <div class="col">
			<div class="dropdown">
			  <button class="btn btn-secondary btn-sm dropdown-toggle float-end bg-primary bg-gradient" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
			  </button>
			  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
			  
			    <li>
					<form method="post" action = "/post/updatepost">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="hidden" name="id" value="${post.id }"/>
					<button  class="dropdown-item" name="submit" type="submit" class ="btn btn-primary btn-sm btn-danger">Update Post</button>
					</form>			    
			    </li>
			    
			    <li>
				
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-primary dropdown-item" data-bs-toggle="modal" data-bs-target="#deletePostModal">
					Delete Post
				</button>					

			    </li>

			  </ul>
	</div>	 
	</div>
	</c:if>   
	
			 <c:if test="${pageContext.request.userPrincipal.name != post.user.username }">
				<sec:authorize access="hasAuthority('ADMIN')">

		<div class="col"> 
			<div class="dropdown">
			  <button class="btn btn-secondary btn-sm dropdown-toggle float-end bg-primary bg-gradient" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
			  </button>
			  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
			  
			    <li>
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-primary dropdown-item" data-bs-toggle="modal" data-bs-target="#exampleModal">
					Delete Post
				</button>		 
			    </li>

			  </ul>
			</div>	
			</div>
				</sec:authorize>
			</c:if>  
  </div>
</div>

 <h6 class="card-subtitle mb-2 text-muted">${post.type }</h6>
		
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
			<br>
			<c:if test="${comment.user.enabled == true}">

			 <c:if test="${pageContext.request.userPrincipal.name != comment.user.username }">	 
			 	<p class="card-text fw-light"></p>


					<a href="/user/profile?username=${comment.user.username }"><b>@${comment.user.username }</b></a> :${comment.content }<br>
					<sec:authorize access="hasAuthority('ADMIN')">
							<!-- Button trigger modal -->
							<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#deleteCommentModal">
								<i class="material-icons align-middle">delete</i>
							</button>	
					</sec:authorize>


				</c:if>

				<c:if test="${pageContext.request.userPrincipal.name == comment.user.username }">

					<div class="container">
						<div class="row">
						  <div class="col-9">
							<a href="/user/profile"><b>@${comment.user.username }</b> </a> :${comment.content }<br>
						  </div>
						  <sec:authorize access="hasAuthority('USER')">
						  <div class="col-1"> 

							<!-- Button trigger modal -->
							<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#deleteCommentModal">
								<i class="material-icons align-middle">delete</i>
							</button>	

						  </div>
						  </sec:authorize>
						  <sec:authorize access="hasAuthority('ADMIN')">
							<div class="col-1"> 

							<!-- Button trigger modal -->
							<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#deleteCommentModal">
								<i class="material-icons align-middle">delete</i>
							</button>	
							</div>
							</sec:authorize>

						</div>
					</div>
				</c:if>

		    </c:if>

			<!-- Comment Modal -->
			<div class="modal fade" id="deleteCommentModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Confirm Deletion</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
					Do you really want to delete this Comment?
					</div>
					<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>

					<sf:form  modelAttribute="comment" method="post" action = "/comment/delete">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<sf:input type="hidden" path="id" value="${comment.id }"/>
					<sf:button name="submit" type="submit" class ="btn btn-primary btn-danger float-end">Delete Comment</sf:button>
					</sf:form>
					</div>
				</div>
				</div>
			</div>

			</c:forEach>
   
	  </div>
	  	<c:if test="${pageContext.request.userPrincipal.name != null}">
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

	</c:if>
	
<br>
<br>
<br>

  <!-- Post Modal -->
  <div class="modal fade" id="deletePostModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
	  <div class="modal-content">
		<div class="modal-header">
		  <h5 class="modal-title" id="exampleModalLabel">Confirm Deletion</h5>
		  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		</div>
		<div class="modal-body">
		  Do you really want to delete this post?
		</div>
		<div class="modal-footer">
		  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
				<form method="post" action = "/post/delete">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="hidden" name="id" value="${post.id }"/>
					<button name="submit" type="submit" class ="btn btn-primary btn-danger">Delete Post</button>
				</form>			    		     
		</div>
	  </div>
	</div>
  </div>

	</c:forEach>

</c:otherwise>
</c:choose>
	
  </div>
</div>

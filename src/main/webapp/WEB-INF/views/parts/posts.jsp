
<div class="d-flex flex-row bd-highlight mb-3 justify-content-center">
	<div class="p-2 w-50 bd-highlight" style="min-width: 500px;">
		<c:choose>
			<c:when test="${empty posts}">
				<h3 style=" text-align: center;" >No more posts available for now</h3>
			</c:when>
			<c:otherwise>
				<c:forEach items="${posts }" var="post" varStatus="vs">
					<br>
					<c:if test="${post.user.enabled == true}">

						<div class="card p-2 shadow-sm p-3 mb-5 bg-body rounded">
							<div class="card-body">
								<!-- here -->

								<c:if test = "${post.type == 'Required'}">
									<div class="p-2" style="background-color: #FFEBEE;">
								</c:if>
								<c:if test = "${post.type == 'Available'}">
									<div class="p-2" style="background-color: #E0F2F1;">
								</c:if>
								<c:if test = "${post.type == 'Other'}">
									<div class="p-2">
								</c:if>
								


									<div class="container">
										<div class="row">
											<div class="col">


												<c:if test="${username != post.user.username }">
													
													<a class="card-link"
														href="/user/profile?username=${post.user.username }"
														style="text-decoration: none;">
														<h3 class="p1 card-title"><i
																class="p-1 material-icons align-middle"
																style="font-size: 36px; ">account_circle</i>${post.user.username
															}</h3>
													</a>
													
												</c:if>
												<c:if test="${username == post.user.username }">
													<a class="card-link" href="/user/profile?"
														style="text-decoration: none;">
														<h3 class="p1 card-title"><i
																class="p-1 material-icons align-middle"
																style="font-size: 36px; ">account_circle</i>${post.user.username
															}</h3>
													</a>

												</c:if>


											</div>
											<c:if
												test="${pageContext.request.userPrincipal.name == post.user.username }">
												<div class="col">
													<div class="dropdown">
														<div class="float-end" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
															<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-three-dots-vertical" viewBox="0 0 16 16">
  																<path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
															</svg>
														</div>
														<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">

															<li>
																<form method="GET" action="/post/update">
																	<input type="hidden" name="${_csrf.parameterName}"
																		value="${_csrf.token}" />
																	<input type="hidden" name="id"
																		value="${post.id }" />
																	<button class="dropdown-item" name="submit"
																		type="submit"
																		class="btn btn-primary btn-sm btn-danger">Update
																		Post</button>
																</form>
															</li>

															<li>

																<!-- Button trigger modal -->
																<button type="button"
																	class="btn btn-primary dropdown-item"
																	data-bs-toggle="modal"
																	data-bs-target="#deletePostModal${vs.index }">
																	Delete Post
																</button>

															</li>

														</ul>
													</div>
												</div>
											</c:if>
											<c:if
												test="${pageContext.request.userPrincipal.name != post.user.username }">

												<sec:authorize access="hasAuthority('USER') or hasAuthority('ADMIN')">


													<!-- Stuff goes here -->
													<div class="col">
														<div class="dropdown">
														<div class="float-end" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
															<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-three-dots-vertical" viewBox="0 0 16 16">
  																<path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
															</svg>
														</div>
															<ul class="dropdown-menu"
																aria-labelledby="dropdownMenuButton1">
																<sec:authorize access="hasAuthority('USER')">

																<li>
																	
																		<form method="post" action = "/post/share">
																			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
																			<input type="hidden" name="username" value="${pageContext.request.userPrincipal.name}"/>
																			<input type="hidden" name="postID" value="${post.id}"/>
																			<button name="submit" type="submit" class ="btn btn-light dropdown-item"> <i class="p-1 material-icons align-middle">share</i> Share</button>
																		</form>	
																		
																</li>



																<li>

																	<!-- Button trigger modal -->
																	<button type="button"
																		class="btn btn-primary dropdown-item"
																		data-bs-toggle="modal"
																		data-bs-target="#reportPostModal${vs.index }">
																		Report Post
																	</button>

																</li>
																</sec:authorize>


																<sec:authorize access="hasAuthority('ADMIN')">
																<li>

																	<!-- Button trigger modal -->
																	<button type="button"
																		class="btn btn-primary dropdown-item"
																		data-bs-toggle="modal"
																		data-bs-target="#deletePostModal${vs.index }">
																		Delete Post
																	</button>
																</li>
																</sec:authorize>


															</ul>
														</div>
													</div>



												</sec:authorize>

												
											</c:if>
										</div>

										
										<div class="row">
											<div class="col">
											
												<p class="card-subtitle mb-2 text-muted fw-lighter" style="text-align: right;">Posted At:<cite title="Source Title"> <fmt:formatDate type = "time" value = "${post.dateTime}" /> On <fmt:formatDate value="${post.dateTime}" pattern="dd-MM-yyyy" /></cite></p>
											
											</div>	
										</div>
										<br>

										

										<div class="row">
											<div class="col">
												<c:if test = "${post.type == 'Required'}">
													<h6 class="card-subtitle mb-2 text-danger">${post.type }</h6>
												</c:if>
												<c:if test = "${post.type == 'Available'}">
													<h6 class="card-subtitle mb-2 text-success">${post.type }</h6>
												</c:if>
											</div>
										</div>
										

										<div class="row">
											<div class="col">
												<p class="card-text">${post.message }</p>
											</div>
										</div>

										<div class="row">
											<div class="col">
												<p class="card-text">
													<small class="text-muted">
													<div class="conatiner">
														<c:forEach items="${post.tags }" var="tag" varStatus="tagStatus">
															
															<span style="padding:3px">
															  <a href="<c:url value="/post/searchresult" ><c:param name="searchentry" value="#${tag.name }" /></c:url>">
															  	#${tag.name }
															  </a>
														  	</span>
														</c:forEach>
														</div>
													</small>
												</p>
											</div>
										</div>
									</div>
								</div>




								<div class="container p-2">
									<div class="row justify-content-md-center">
										<div class="col col-md-auto">
													<button class="btn btn-light text-center" type="button" data-bs-toggle="collapse" data-bs-target="#collapseComments${vs.index }">
														<i class="p-1 material-icons align-middle">comment</i>Comments 
													</button>			
										</div>
									</div>
									<div class="row collapse" id="collapseComments${vs.index }">

									<c:forEach items="${post.comments }" var="comment" varStatus="tagStatus">
										<c:if test="${comment.user.enabled == true}">
											<c:if
												test="${pageContext.request.userPrincipal.name != comment.user.username && pageContext.request.userPrincipal.name != post.user.username}">


												<div class="row p-2">
													<div class="col-10">
														<a href="/user/profile?username=${comment.user.username }"
															style="text-decoration: none;"><b>${comment.user.username
																}</b></a> :&nbsp;${comment.content }
													</div>
								
													<sec:authorize access="hasAuthority('ADMIN')">
														<div class="col">

															<div class="d-flex flex-row-reverse bd-highlight">
																<div class="bd-highlight">

																	<!-- Button trigger modal -->
																	<button type="button"
																		class="btn btn-sm btn-outline-danger"
																		data-bs-toggle="modal"
																		data-bs-target="#deleteCommentModal${comment.id }">
																		<i class="material-icons align-middle "
																			style="font-size: 16px; ">delete</i>
																	</button>


																</div>
															</div>


														</div>
													</sec:authorize>
													<div class="col-10">
													<p class="card-subtitle mb-2 text-muted fw-lighter" style="text-align: right; font-size: 10px;"><cite title="Source Title"> <fmt:formatDate type = "time" value = "${comment.dateTime}" /> On <fmt:formatDate value="${comment.dateTime}" pattern="dd-MM-yyyy" /></cite></p>
													</div>
												</div>


											</c:if>
											<c:if
												test="${pageContext.request.userPrincipal.name == comment.user.username || pageContext.request.userPrincipal.name == post.user.username}">
												<div class="row p-2">
													<div class="col-10">
														<a href="/user/profile"
															style="text-decoration: none;"><b>${comment.user.username
																}</b> </a> :&nbsp; ${comment.content }
													</div>
													
													<sec:authorize access="hasAuthority('USER')">
														<div class="col">

															<div class="d-flex flex-row-reverse bd-highlight">
																<div class="bd-highlight">

																	<!-- Button trigger modal -->
																	<button type="button"
																		class="btn btn-sm btn-outline-danger"
																		data-bs-toggle="modal"
																		data-bs-target="#deleteCommentModal${comment.id}">
																		<i class="material-icons align-middle "
																			style="font-size: 16px; ">delete</i>
																	</button>


																</div>
															</div>

														</div>
													</sec:authorize>
													<div class="col-10">
													<p class="card-subtitle mb-2 text-muted fw-lighter" style="text-align: right; font-size: 10px;"><cite title="Source Title"> <fmt:formatDate type = "time" value = "${comment.dateTime}" /> On <fmt:formatDate value="${comment.dateTime}" pattern="dd-MM-yyyy" /></cite></p>
													</div>
												</div>
											</c:if>
										</c:if>

										<!-- Comment Modal -->
										<div class="modal fade" id="deleteCommentModal${comment.id}" tabindex="-1"
											aria-labelledby="exampleModalLabel" aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel">Confirm Deletion
														</h5>
														<button type="button" class="btn-close" data-bs-dismiss="modal"
															aria-label="Close"></button>
													</div>
													<div class="modal-body">
														Do you really want to delete this Comment?
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">Close</button>

														<sf:form modelAttribute="comment" method="post"
															action="/comment/delete">
															<input type="hidden" name="${_csrf.parameterName}"
																value="${_csrf.token}" />
															<sf:input type="hidden" path="id" value="${comment.id }" />
															<sf:button name="submit" type="submit"
																class="btn btn-primary btn-danger float-end">Delete
																Comment</sf:button>
														</sf:form>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
									</div>

									<br>
									<br>
									<c:if test="${pageContext.request.userPrincipal.name != null}">
									<sec:authorize access="hasAuthority('USER')">
										<sf:form modelAttribute="comment" action="/comment/create" method="post">

											<div class="row">
												<div class="col-9">
													<input type="hidden" name="${_csrf.parameterName}"
														value="${_csrf.token}" />
													<sf:input type="hidden" path="post" value="${ post.id}" />
													<sf:input path="content" class="form-control autocomplete" required="required"/>
												</div>
												<div class="col-1">
													<sf:button type="submit" class="btn btn-primary">Comment</sf:button>
												</div>
											</div>
										</sf:form>
									</sec:authorize>
									</c:if>

								</div>

							</div>
						</div>


						<!-- Post Delete Modal -->
						<div class="modal fade" id="deletePostModal${vs.index }" tabindex="-1" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Confirm Deletion</h5>
										<button type="button" class="btn-close" data-bs-dismiss="modal"
											aria-label="Close"></button>
									</div>
									<div class="modal-body">
										Do you really want to delete this post?
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Close</button>
										<form method="post" action="/post/delete">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
											<input type="hidden" name="id" value="${post.id }" />
											<button name="submit" type="submit"
												class="btn btn-primary btn-danger">Delete Post</button>
										</form>
									</div>
								</div>
							</div>
						</div>

						<!-- Report Modal -->
						<div class="modal fade" id="reportPostModal${vs.index }" tabindex="-1" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Report Post</h5>
										<button type="button" class="btn-close" data-bs-dismiss="modal"
											aria-label="Close"></button>
									</div>
									<div class="modal-body">
										Do you really want to report this post?
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Close</button>
										<form method="post" action="/post/report">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
											<input type="hidden" name="id" value="${post.id }" />
											<button name="submit" type="submit"
												class="btn btn-primary btn-danger">Report Post</button>
										</form>
									</div>
								</div>
							</div>
						</div>



					</c:if>

				</c:forEach>

			</c:otherwise>
		</c:choose>

	</div>
</div>
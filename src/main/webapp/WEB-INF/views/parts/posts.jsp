<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="d-flex flex-row bd-highlight mb-3 justify-content-center">
	<div class="p-2 w-50 bd-highlight" style="min-width: 500px;">
		<c:choose>
			<c:when test="${empty posts}">
				<h3>No posts available</h3>
			</c:when>
			<c:otherwise>
				<c:forEach items="${posts }" var="post" varStatus="tagStatus">
					<br>
					<c:if test="${post.user.enabled == true}">

						<div class="card p-2 shadow-sm p-3 mb-5 bg-body rounded">
							<div class="card-body">
								<div class="bg-light p-2">


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
														<button
															class="btn btn-secondary btn-sm dropdown-toggle float-end bg-primary bg-gradient"
															style="border-radius: 35px;" type="button"
															data-bs-toggle="dropdown" aria-expanded="false">
														</button>
														<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">

															<li>
																<form method="post" action="/post/updatepost">
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
																	data-bs-target="#deletePostModal">
																	Delete Post
																</button>

															</li>

														</ul>
													</div>
												</div>
											</c:if>
											<c:if
												test="${pageContext.request.userPrincipal.name != post.user.username }">

												<sec:authorize access="hasAuthority('USER')">


													<!-- Stuff goes here -->
													<div class="col">
														<div class="dropdown">
															<button
																class="btn btn-secondary btn-sm dropdown-toggle float-end bg-primary bg-gradient"
																style="border-radius: 35px;" type="button"
																data-bs-toggle="dropdown" aria-expanded="false">
															</button>
															<ul class="dropdown-menu"
																aria-labelledby="dropdownMenuButton1">

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
																		data-bs-target="#reportPostModal">
																		Report Post
																	</button>

																</li>


																<sec:authorize access="hasAuthority('ADMIN')">
																<li>

																	<!-- Button trigger modal -->
																	<button type="button"
																		class="btn btn-primary dropdown-item"
																		data-bs-toggle="modal"
																		data-bs-target="#deletePostModal">
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
												<p class="card-subtitle mb-2 text-muted fw-lighter">Posted At: <fmt:formatDate type = "time" value = "${post.dateTime}" /> On <fmt:formatDate value="${post.dateTime}" pattern="dd-MM-yyyy" /></p>
											</div>

											
										</div>


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
														<c:forEach items="${post.tags }" var="tag" varStatus="tagStatus">
															<span style="padding:3px">
															  <a href="<c:url value="/post/searchresult" ><c:param name="searchentry" value="#${tag.name }" /></c:url>">
															  	#${tag.name }
															  </a>
														  </span>
														</c:forEach>
													</small>
												</p>
											</div>
										</div>
									</div>
								</div>




								<div class="container p-2">
									<div class="row">									
										<div class="col">
											<p class="text-center p-2"><i
													class="p-1 material-icons align-middle">comment</i>Comments</p>
										</div>
										
										
									</div>
									<c:forEach items="${post.comments }" var="comment" varStatus="tagStatus">
										<c:if test="${comment.user.enabled == true}">
											<c:if
												test="${pageContext.request.userPrincipal.name != comment.user.username }">


												<div class="row p-2">
													<div class="col-9">
														<a href="/user/profile?username=${comment.user.username }"
															style="text-decoration: none;"><b>${comment.user.username
																}</b></a> :&nbsp;${comment.content }
														<sec:authorize access="hasAuthority('ADMIN')">
															<!-- Button trigger modal -->
															<button type="button" class="btn btn-primary btn-sm"
																data-bs-toggle="modal"
																data-bs-target="#deleteCommentModal">
																<i class="material-icons align-middle"
																	style="font-size: 16px; ">delete</i>
															</button>
														</sec:authorize>
													</div>
												</div>


											</c:if>
											<c:if
												test="${pageContext.request.userPrincipal.name == comment.user.username }">
												<div class="row p-2">
													<div class="col-9">
														<a href="/user/profile"
															style="text-decoration: none;"><b>${comment.user.username
																}</b> </a> :&nbsp; ${comment.content }
													</div>
													<sec:authorize access="hasAuthority('USER')">
														<div class="col-1">
															<!-- Button trigger modal -->
															<button type="button" class="btn btn-sm btn-outline-danger"
																data-bs-toggle="modal"
																data-bs-target="#deleteCommentModal">
																<i class="material-icons align-middle "
																	style="font-size: 16px; ">delete</i>
															</button>

														</div>
													</sec:authorize>
													<sec:authorize access="hasAuthority('ADMIN')">
														<div class="col-1">
															<!-- Button trigger modal -->
															<button type="button" class="btn btn-primary btn-sm"
																data-bs-toggle="modal"
																data-bs-target="#deleteCommentModal">
																<i class="material-icons align-middle"
																	style="font-size: 16px; ">delete</i>
															</button>
														</div>
													</sec:authorize>
												</div>
											</c:if>
										</c:if>

										<!-- Comment Modal -->
										<div class="modal fade" id="deleteCommentModal" tabindex="-1"
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

									<c:if test="${pageContext.request.userPrincipal.name != null}">
										<sf:form modelAttribute="comment" action="/comment/create" method="post">

											<div class="row">
												<div class="col-9">
													<input type="hidden" name="${_csrf.parameterName}"
														value="${_csrf.token}" />
													<sf:input type="hidden" path="post" value="${ post.id}" />
													<sf:input path="content" class="form-control autocomplete" />
												</div>
												<div class="col-1">
													<sf:button type="submit" class="btn btn-primary">Comment</sf:button>
												</div>
											</div>
										</sf:form>
									</c:if>

								</div>

							</div>
						</div>


						<!-- Post Delete Modal -->
						<div class="modal fade" id="deletePostModal" tabindex="-1" aria-labelledby="exampleModalLabel"
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
						<div class="modal fade" id="reportPostModal" tabindex="-1" aria-labelledby="exampleModalLabel"
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
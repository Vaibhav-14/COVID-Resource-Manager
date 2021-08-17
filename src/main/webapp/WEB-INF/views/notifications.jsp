<%@ include file="parts/meta.jsp" %> 

<title>Notifications</title>
<%@ include file="parts/header.jsp" %> 

<%--
<div class="container bg-secondary text-white text-center" style="height:300px">
<h1 style=font-size:5vw>NOTIFICATIONS</h1>
</div> --%>

<div class="container">
	<div class="row justify-content-md-center p-2">
	  <div class="col-md-auto">

		<h1 style="text-align: center;">Notifications</h1>
		
<c:choose>
	<c:when test="${empty notifications}">
		<h3>No notifications available</h3>
	</c:when>
<c:otherwise>
<c:forEach items="${notifications }" var="notification" varStatus="tagStatus">

	<br>
	  <div class="card p-2">
	  <div class="card-body">
	    <div class= "bg-light p-2">

			
				<div class="row">
				  <div class="col-9">
					<a class="card-link" href="${notification.objectURL}" style="text-decoration: none;"> ${notification.activityType }</a> 
				  </div>
				  <div class="col-1">
					<a href="/notifications/delete/${notification.id }">
						<button class="btn btn-primary btn-sm btn-danger"><i class="material-icons align-middle">delete</i></button>
					</a>
				  </div>

				</div>   
	    </div>
	    
	  </div>
	 </div>
</c:forEach>
</c:otherwise>
</c:choose>


	  </div>
	</div>
</div>

	    
<%@ include file="parts/footer.jsp" %> 

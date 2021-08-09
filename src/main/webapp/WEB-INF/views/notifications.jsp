<%@ include file="parts/meta.jsp" %> 

<title>Notifications</title>
<%@ include file="parts/header.jsp" %> 

<c:choose>
	<c:when test="${empty notifications}">
		<h3>No notifications available</h3>
	</c:when>
<c:otherwise>
<c:forEach items="${notifications }" var="notification" varStatus="tagStatus">

  
	  <div class="card p-2">
	  <div class="card-body">
	    <div class= "bg-light p-2">
			<a class="card-link" href="${notification.objectURL}"> ${notification.activityType }</a> 

			<a href="/notifications/delete/${notification.id }">
				<button class="btn btn-primary btn-sm btn-danger">Delete</button>
			</a>
		<br><br>	   
	    </div>
	    
	  </div>
	 </div>
</c:forEach>
</c:otherwise>
</c:choose>
	    
<%@ include file="parts/footer.jsp" %> 

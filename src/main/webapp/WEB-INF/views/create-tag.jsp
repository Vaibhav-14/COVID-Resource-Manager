<%@ include file="parts/meta.jsp" %> 

	<title>Add Post</title>
	<script src="${pageContext.request.contextPath }/js/jquery-3.6.0.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
<%@ include file="parts/header.jsp" %> 
	<sf:form id="form1" modelAttribute="tag" method= "POST" >
		Tag: <sf:textarea id="tags" path="name"/>
		<sf:button value="Submit">Add Tags</sf:button>
	</sf:form>
	
	<%@ include file="parts/auto.jsp" %> 
<%@ include file="parts/footer.jsp" %> 
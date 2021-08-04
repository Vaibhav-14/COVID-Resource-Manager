<%@ include file="parts/meta.jsp" %> 

	<title>Add Post</title>
	<script src="${pageContext.request.contextPath }/js/jquery-3.6.0.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
<%@ include file="parts/header.jsp" %> 
	<sf:form id="form1" modelAttribute = "post" method = "POST" >
		<sf:hidden path="id"/>
		Type: <sf:input path="type"/><br>
		Message: <sf:textarea path="message"/><br>
		Tags: <sf:textarea id="tags" path="tagStr"/>
		<sf:hidden path="dateTime"/>
		<sf:hidden path="user" />  
		<sf:button value="Submit">Update Post</sf:button>
	</sf:form>
	<%@ include file="parts/auto.jsp" %>
<%@ include file="parts/footer.jsp" %> 
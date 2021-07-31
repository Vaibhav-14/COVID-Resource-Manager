<%@ include file="parts/meta.jsp" %> 

	<title>Add Post</title>

<%@ include file="parts/header.jsp" %> 
	<sf:form id="form1" modelAttribute = "post" method = "POST" >
		Type: <sf:input path="type"/><br>
		Message: <sf:textarea path="message"/><br>
		Tags: <sf:input path="tagStr"/>
		<sf:button value="Submit">Create Post</sf:button>
	</sf:form>

<%@ include file="parts/footer.jsp" %> 
<%@ include file="parts/meta.jsp" %> 

	<title>Add Post</title>

<%@ include file="parts/header.jsp" %> 
	<sf:form id="form1" modelAttribute = "post" method = "POST" >
		<sf:hidden path="id"/>
		Type: <sf:input path="type"/><br>
		Message: <sf:textarea path="message"/><br>
		Tags: <sf:input path="tagStr"/>
		<sf:hidden path="dateTime"/>
		<sf:hidden path="user" />  
		<sf:button value="Submit">Update Post</sf:button>
	</sf:form>

<%@ include file="parts/footer.jsp" %> 
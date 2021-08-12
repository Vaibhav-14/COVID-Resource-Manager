<%@ include file="parts/meta.jsp" %> 

	<title>Add Tag</title>
<%@ include file="parts/header.jsp" %> 
	<h6>Create New Tag</h6>
	<sf:form id="form1" modelAttribute="tag" method= "POST" >
		Tag: <sf:textarea class="autocomplete" path="name"/>
		<sf:button value="Submit">Add Tags</sf:button>
	</sf:form>
	
	<%@ include file="parts/auto.jsp" %> 
<%@ include file="parts/footer.jsp" %> 
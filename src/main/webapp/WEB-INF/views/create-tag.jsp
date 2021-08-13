<%@ include file="parts/meta.jsp" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<title>Add Tag</title>
<%@ include file="parts/header.jsp" %> 
	<h6>Create New Tag</h6>
	<sf:form id="form1" modelAttribute="tag" method= "POST" >
		Tag: <sf:textarea class="autocomplete" path="name"/>
		<sf:button value="Submit">Add Tags</sf:button>
	</sf:form>
	
	
	 <br/>
		<h6> AllTags</h6>
		
		
	<table>
	<thead>
		<tr>
			<th>Tag</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="tag" items="${tags}">
		<tr>
			<td>${tag.name}</td>
			<td>
			
				<sf:form modelAttribute="tag" method= "POST" action="/tag/delete">
				<input type="hidden" name="name" value="${ tag.name}"/>
				<sf:button value="Submit">Delete Tag</sf:button>
			</td>
			</sf:form>
			
	
		</tr>
		</c:forEach>
		</tbody>
	</table>
	
	
	
	<%@ include file="parts/auto.jsp" %> 
<%@ include file="parts/footer.jsp" %> 
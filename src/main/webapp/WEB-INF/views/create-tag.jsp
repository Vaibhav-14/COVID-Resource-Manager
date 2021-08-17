<%@ include file="parts/meta.jsp" %> 

	<title>Add Tag</title>
<%@ include file="parts/header.jsp" %> 
	
	
	<div class="formcontainer w-100 d-flex justify-content-center">
		<div class="container w-50 p-3 bg-white rounded shadow-lg ">
	<div class = "row">
		<div class="col text-center">
			<h1> Create New Tag </h1>
		</div>
	</div>
	<br>
	<br>
	<div class="text-center">
	<sf:form id="form1" modelAttribute="tag" method= "POST" >
		<div class="form-floating">
		<sf:textarea id="tags" class="autocomplete form-control" style="height: 100px" path="name"/>
		<label for="floatingTextarea2">Add tags here</label>
		</div>
		<br>
		<sf:button class="btn btn-primary" style="margin-bottom:20px" value="Submit">Add Tags</sf:button>
	</sf:form>
	</div>
	</div>
	</div>
	
	<br>
	<br>
	<br>
	<br>
	
	<h2 class="text-center"> All Available Tags</h2>
	<br>
	<br>
	
	<div class="conatiner w-50 p-3 text-center" style = "horizontal-align: middle;margin-left: auto;
    margin-right: auto;">
	
	<table class="table table-dark table-striped">
	<thead>
		<tr>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="tag" items="${tags}">
		<tr>
			<td>${tag.name}</td>
			<td> <a href="/tag/delete/${tag.name}"><i class="material-icons align-middle">delete</i></a>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	
	
	
	
	
	<%@ include file="parts/auto.jsp" %> 
<%@ include file="parts/footer.jsp" %> 
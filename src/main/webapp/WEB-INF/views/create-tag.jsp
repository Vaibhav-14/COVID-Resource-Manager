<%@ include file="parts/meta.jsp" %> 
	<title>Add Tag</title>
<%@ include file="parts/header.jsp" %> 



	<div class="formcontainer w-100 d-flex justify-content-center">
		<div class="container bg-white rounded shadow-lg ">
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
		<sf:textarea class="autocomplete form-control" style="height: 100px" path="name"/>
		<label for="floatingTextarea2">Add tags here</label>
		</div>
		<br>
		<sf:button type="button" class="btn btn-primary" style="margin-bottom:20px" value="Submit">Add Tags</sf:button>
	</sf:form>
	</div>
	</div>
	</div>
	
	<%@ include file="parts/auto.jsp" %> 
<%@ include file="parts/footer.jsp" %> 
<%@ include file="parts/meta.jsp" %> 
<title>Update Post</title>
<link href="${pageContext.request.contextPath }/css/auto_complete.css" rel="stylesheet" type="text/css" />

<%@ include file="parts/header.jsp" %> 
	<div class="formcontainer w-100 d-flex justify-content-center">
		<div class="container bg-white rounded shadow-lg ">
			<sf:form id="form1" modelAttribute = "post" method = "POST" action="/post/update">
				<sf:hidden path="id"/>
				
				<div class = "row">
					<div class="col text-center">
						<h1> Update Post </h1>
					</div>
				</div>
				
				<div class = "row">
					<div class="col-3 p-1 m-1 text-end fw-bold">
						<h5>Type</h5>
					</div>
					<div class="col-6 p-1 m-1 fw-normal">
						<sf:select path="type" class="form-select" >
						  <sf:option value="Required" label="Required"/>  
						  <sf:option value="Available" label="Available"/>  
						  <sf:option value="Other" label="Other"/>  
						</sf:select>
						
					</div>
				</div>
				
				<div class = "row">
					<div class="col-3 p-1 m-1 text-end fw-bold">
						<h5>Message</h5>
					</div>
					<div class="col-6 p-1 m-1 fw-normal">
						<sf:textarea path="message" class="form-control autocomplete" rows="5"/>
					</div>
				</div>
				
				<div class = "row">
					<div class="col-3 p-1 m-1 text-end fw-bold">
						<h5>Tags</h5>
					</div>
					<div class="col-6 p-1 m-1 fw-normal">
						<sf:textarea path="tagStr" class="form-control autocomplete" rows="2" />
					</div>
				</div>
				
				<sf:hidden path="user" />  
				<div class = "row p-2 m-2">
					<div class="col text-center">
						<sf:button value="Submit" class="btn btn-success">Update Post</sf:button>
					</div>
				</div>
			</sf:form>
		</div>
	  </div>
	
<%@ include file="parts/footer.jsp" %> 
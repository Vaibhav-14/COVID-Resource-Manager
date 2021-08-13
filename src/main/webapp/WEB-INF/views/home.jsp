<%@ include file="parts/meta.jsp"%>

<title>Home Page</title>

<%@ include file="parts/header.jsp"%>

<div class="container">
	<div class="row">

	  <div class="col">
		<%@ include file="parts/posts.jsp"%>
	  </div>

	</div>

	<div class="row justify-content-md-center">
		<div class="col col-lg-2">
			
			
			<div class="d-flex flex-row-reverse bd-highlight mb-3">

				<a href='/home?pageNumber=<% int backward = 0;
				if(request.getParameter("pageNumber")!= null)
					backward=Integer.parseInt(request.getParameter("pageNumber"));
			   out.print(Math.max(0,backward-1));%>'>


				<button type="button " class="btn btn-outline-primary btn-lg"><i
					class="p-1 material-icons align-middle"
					style="font-size: 36px;">arrow_left</i></button>
				</a> 
			</div>	
			
		</div>
		<div class="col col-lg-2">
			
			
			<div class="d-flex flex-row bd-highlight mb-3">

				<a href='/home?pageNumber=<% int forward = 0;
				if(request.getParameter("pageNumber")!= null)
					forward=Integer.parseInt(request.getParameter("pageNumber"));
			   out.print(forward+1);%>'>


				<button type="button " class="btn btn-outline-primary btn-lg"><i
					class="p-1 material-icons align-middle"
					style="font-size: 36px; ">arrow_right</i></button>
				</a> 
			</div>	
			
		</div>
		

</div>



<%@ include file="parts/footer.jsp"%>
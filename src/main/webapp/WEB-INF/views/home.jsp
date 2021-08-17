<%@ include file="parts/meta.jsp"%>

<title>Home Page</title>

<%@ include file="parts/header.jsp"%>

<div class="container">
	<div class="row">

	  <div class="col">

		<div class="d-none d-lg-block h-100 sticky-top" style="position:fixed; width:25%;">

			<div class="container d-flex h-100">
				<div class="row  align-self-start" style="margin-top: 100px;">


					<div id="carouselControls" class="carousel carousel-dark slide border" data-bs-ride="carousel">
						<div class="carousel-inner">
							<div class="carousel-item active">
								<img src="https://www.who.int/images/default-source/health-topics/coronavirus/infographics/are-you-a-healthworker_8_3.png?sfvrsn=910144da_5"
									class="d-block w-100">
							</div>
							<div class="carousel-item">
								<img src="https://www.who.int/images/default-source/health-topics/coronavirus/infographics/are-you-pregnant_11_3.png?sfvrsn=71ea572b_5"
									class="d-block w-100">
							</div>
							<div class="carousel-item">
								<img src="https://www.who.int/images/default-source/health-topics/coronavirus/infographics/do-you-have-chronic-health-conditions_8_3.png?sfvrsn=6d8ddaa3_5"
									class="d-block w-100">
							</div>
						</div>
						<button class="carousel-control-prev" type="button"
							data-bs-target="#carouselControls" data-bs-slide="prev">
							<span class="carousel-control-prev-icon"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						<button class="carousel-control-next" type="button"
							data-bs-target="#carouselControls" data-bs-slide="next">
							<span class="carousel-control-next-icon"></span>
							<span class="visually-hidden">Next</span>
						</button>
					</div>

					<!-- //http://localhost:8080/vaccination?pincode=492001&date=31-06-2021 -->
					<div class = "p-2 border shadow-sm" style="margin-top: 25px;">
						Find The Nearest Vaccination Centers to You by entering your Pincode here:
						<br> 
						<form class="d-flex"  method = "GET" action = "/vaccination">
							<input name="pincode" class="form-control" placeholder="Enter Pincode" required>
							<br>
							<input name="date" type=date class="form-control" required>
							<br>
							<button class="btn btn-outline-success" type="submit">Search</button> 
							<br>
						  </form> 
	
					</div>


				</div>
		


			</div>


		</div>
		
		

	  </div>
	  <div class="col-9">
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
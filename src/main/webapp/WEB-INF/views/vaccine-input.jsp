<%@ include file="parts/meta.jsp"%>
<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<div
	class="formcontainer w-100 h-100 p-5 d-flex justify-content-center vh-200">

	<sf:form action="/vaccinationdetails" method="GET">
		<div class="container bg-white rounded shadow-lg ">
			<div class="row p
			-2">
				<div class="col text-center">
					<h1>Vaccination Slots</h1>
				</div>
			</div>

			<div class="row p-2">

				<label for="pincode" class="form-label">Pincode</label> <input
					name="pincode" class="form-control" id="pincode">
				<div id="pincode" class="form-text"></div>
			</div>

			<div class="row p-2">

				<label for="date" class="form-label">date</label> <input name="date"
					class="form-control">
				<div id="date" class="form-text"></div>
			</div>


			<div class="text-center" style="margin-top: 5px">
				<button type="submit" class="btn btn-primary">Search</button>
			</div>
			<br>
			<br>
		</div>
	</sf:form>
</div>
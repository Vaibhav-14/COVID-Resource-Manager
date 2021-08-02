<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Auto Complete with Spring MVC</title>



</head>
<body>
	${pageContext.request.contextPath }
	Search Product
	
	<textarea name="" id="tags" cols="30" rows="10"></textarea>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
	<script type="text/javascript">
		function split(val) {
			return val.split(/,\s*/);
		}
		function extractLast(term) {
			return split(term).pop();
		}

		$(document).ready(function() {

			$("#tags").autocomplete({
				source: function (request, response) {
					$.getJSON("${pageContext.request.contextPath}/tags", {
						term: extractLast(request.term)
					}, response);
				},
				search: function () {
					// custom minLength
					console.log(term);
					var term = extractLast(this.value);
					if (term.length < 1) {
						return false;
					}
				},
				focus: function () {
					// prevent value inserted on focus
					return false;
				},
				select: function (event, ui) {
					var terms = split(this.value);
					// remove the current input
					terms.pop();
					// add the selected item
					var a = document.createElement('a');
					var linkText = document.createTextNode(ui.item.value);
					a.appendChild(linkText);
					a.title = ui.item.value;
					a.href = "#";
					console.log(a);
					terms.push(a);
					// add placeholder to get the comma-and-space at the end
					terms.push("");
					console.log(terms);
					this.value = terms.join(", ");
					return false;
				}
			});
			
		});
	</script>
</body>
</html>
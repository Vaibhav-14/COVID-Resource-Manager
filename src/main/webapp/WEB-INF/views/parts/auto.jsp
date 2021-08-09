
<script>
	function split(val) {
		return val.split(/ \s*/);
	}
	function extractLast(term) {
		return split(term).pop();
	}

	$(document).ready(function() {

		$(".autocomplete").autocomplete({
			source: function (request, response) {
				$.getJSON("${pageContext.request.contextPath}/autocomplete", {
					term: extractLast(request.term)
				}, response);
			},
			search: function () {
				// custom minLength
				var term = extractLast(this.value);
				return (term.length > 1) && (term.startsWith("@") || term.startsWith("#"));
			},
			focus: function () {
				// prevent value inserted on focus
				return false;
			},
			select: function (event, ui) {
				var terms = split(this.value);
				// remove the current input
				let removedTerm = terms.pop();
				// add the selected item
				terms.push(removedTerm[0] + ui.item.value);
				// add placeholder to get the comma-and-space at the end
				terms.push("");
				this.value = terms.join(" ");
				return false;
			}
		});
		
	});
</script>
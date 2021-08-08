function split(val) {
	return val.split(/,\s*/);
}
function extractLast(term) {
	return split(term).pop();
}

$(document).ready(function() {

	$("#tags").autocomplete({
		minLength : 1, 
		autoFocus : true , 
		maxHeight : 100 , 
		width : auto , 
		
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
			terms.push(ui.item.value);
			// add placeholder to get the comma-and-space at the end
			terms.push("");
			console.log(terms);
			this.value = terms.join(", ");
			return false;
		}
		
	});
	
});
 
 
window.onload = function() {
	$("#orderForm").on("submit", function(e) {
		checkForm(e);
	});
	

}
function checkForm(e) {
	
	if ($("input:checkbox[id=selectCoffeeType]:checked").length==0) {
		e.preventDefault();
		alert($('#orderForm').data("empty-select-error"));
	}
}
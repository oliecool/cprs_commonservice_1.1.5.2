/**
 * 清除指定form下的查询表单值
 */

$(document).ready(function(){
	$("#searchForm").trigger('reset');
	$('#searchForm').find("#clearInput").click(function(){
		$(':input','#searchForm') 
		.not(':button, :submit, :reset, :hidden') 
		.val('') 
		.removeAttr('checked') 
		.removeAttr('selected');
	});
});
//Prevent form submission
$( "#searchForm" ).submit(function( event ) {
  event.preventDefault();
});

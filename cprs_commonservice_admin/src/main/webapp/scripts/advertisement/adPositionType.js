var flag=true;
var result=true;
function validateParam(){
	var name=$("#name").val().trim();
	var code=$("#code").val();
	if(name==null || name==""){
		$("#nameInfo").html("请填写名称");
		flag=false;
		return;
	}else{
		flag=true;
		$("#nameInfo").html("");
	}
	if(code==null || code==""){
		$("#codeInfo").html("请填写编号");
		flag=false;
		return;
	}else{
		flag=true;
		if(result){
			$("#codeInfo").html("");
		}
	}
}
function sub(){
	validateParam();
	if(flag && result){
		$("#searchForm").submit();
	}		
}
function checkCode(value){
	if(value != $("#oldCode").val()){
		jQuery.ajax({
			   type:"post",
			   url:appServer + "/adPositionType/ajax/validateCode.htm",
			   dataType:"json",	
			   data:{"code":value},	
			   success:function(message){
			   	  if(message.info=="No"){
			   		$("#codeInfo").html("编号已存在");
			   		result=false;
			   	  }else{
			   		$("#codeInfo").html("");
			   		result=true;
			   	  }
			   },
			   error:function(xhr, message, e){
			      
			   }
		   });
	}else{
		$("#codeInfo").html("");
		result=true;
	}
}
var flag=true;
var result=true;
function validateParam(){
	var name=$("#smsType").val().trim();
	var code=$("#smsCode").val();
	var content=$("#smsContent").val().trim();
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
			var reg = new RegExp("^[0-9]{6}$");
	    	if (!reg.test(code)){
	    		$("#codeInfo").html("只能输入6位数字！");
	    		flag=false;
				 return false ;
			 }else{
				 $("#codeInfo").html("");
				 flag=true;
			}
		}
	}
	if(content==null || content==""){
		$("#contentInfo").html("请填写内容");
		flag=false;
		return;
	}else{
		flag=true;
		$("#contentInfo").html("");
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
			   url:appServer + "/smsModel/ajax/validateCode.htm",
			   dataType:"json",	
			   data:{"code":value},	
			   success:function(message){
			   	  if(message.info=="No"){
			   		$("#codeInfo").html("编码已存在");
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
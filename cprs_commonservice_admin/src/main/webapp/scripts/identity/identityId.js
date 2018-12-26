/**
 * 保存识别标识
 * 
 * @returns
 */
function saveIdentityId() {
	var modifiedFlag = $('#modifiedFlag').val().trim();
	var identityName = $('#identityName').val().trim();
	var modelTypeId = $('#modelTypeId').val().trim();
	var identityId = $('#identityId').val().trim();
	var algorithmService = $('#algorithmSelect').find("option:selected").val().trim();
	var data;
	var itemJson = {
		"identityId" : identityId,
		"modelTypeId" : modelTypeId,
		"modifiedFlag" : modifiedFlag,
		"identityName" : identityName,
		"algorithmService" : algorithmService
	};
	data = itemJson;
	$.ajax({
		type : 'post',
		url : appServer + "/identity/id/add.json",
		data : JSON.stringify(data),
		contentType : 'application/json;charset=utf-8',
		async : false,
		cache : false,
		success : function(result) {
			if (result.errorNo != 0) {
				$("#dialog").text(result.errorDesc);
				$("#dialog").dialog({
					close:function(event, ui) {
						$("#dialog").text("");
					}
				});
			} else {
				$("#dialog").text("保存成功!");
				$("#dialog").dialog({
					close:function(event, ui) {
						$("#dialog").text("");
						window.location.href="/identity/id/list.htm";
					}
				});
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#dialog").text("处理失败，请联系管理员！")
			$("#dialog").dialog({
				close:function(event, ui) {
					$("#dialog").text("");
				}
			});
			return;
		}
	});
}

/**
 * 更新识别标识
 * 
 * @returns
 */
function updateIdentityId() {
	var modifiedFlag = $('#modifiedFlag').val().trim();
	var id = $('#id').val().trim();
	var identityName = $('#identityName').val().trim();
	var modelTypeId = $('#modelTypeId').val().trim();
	var algorithmService = $('#algorithmSelect').find("option:selected").val().trim();
	var data;
	var itemJson = {
		"id" : id,
		"modelTypeId" : modelTypeId,
		"modifiedFlag" : modifiedFlag,
		"identityName" : identityName,
		"algorithmService" : algorithmService
	};
	data = itemJson;
	$.ajax({
		type : 'post',
		url : appServer + "/identity/id/modify.json",
		data : JSON.stringify(data),
		contentType : 'application/json;charset=utf-8',
		async : false,
		cache : false,
		success : function(result) {
			if (result.errorNo != 0) {
				$("#dialog").text(result.errorDesc);
				$("#dialog").dialog({
					close:function(event, ui) {
						$("#dialog").text("");
					}
				});
			} else {
				$("#dialog").text("保存成功!");
				$("#dialog").dialog({
					close:function(event, ui) {
						$("#dialog").text("");
						window.location.href="/identity/id/list.htm";
					}
				});
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#dialog").text("处理失败，请联系管理员！")
			$("#dialog").dialog({
				close:function(event, ui) {
					$("#dialog").text("");
				}
			});
			return;
		}
	});
}

function bindAlgorithmChange(){
	$('#algorithmSelect').change(function(){
		var desc = $(this).find("option:selected").attr("desc");
		var $algorithmSelectSpan = $(this).next();
		$algorithmSelectSpan.text(desc)
	})
}
$(document).ready(function() {
	bindAlgorithmChange();
	$('#modifyTypeForm').find("#updateIdentityId").click(function() {
		$("#dialog:ui-dialog").dialog("destroy");
		$("#msg").show();
		$("#dialog-confirm").dialog({
			resizable : false,
			height : 150,
			modal : true,
			close:function(event, ui) {
				$("#dialog").text("");
			},
			buttons : {
				"确定" : function() {
					$(this).dialog("close");
					updateIdentityId();
				},
				"取消" : function() {
					$(this).dialog("close");
				}
			}
		});

	});

	$('#addTypeForm').find("#saveIdentityId").click(function() {
		$("#dialog:ui-dialog").dialog("destroy");
		$("#msg").show();
		$("#dialog-confirm").dialog({
			resizable : false,
			height : 150,
			modal : true,
			close:function(event, ui) {
				$("#dialog").text("");
			},
			buttons : {
				"确定" : function() {
					$(this).dialog("close");
					saveIdentityId();
				},
				"取消" : function() {
					$(this).dialog("close");
				}
			}
		});

	});
});
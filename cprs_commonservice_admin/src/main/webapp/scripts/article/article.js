var flag=true;
var result=true;
var response=true;
var fileInfo;
$(function() {
    // textarea
    var editor = KindEditor.create(
        'textarea[name="articleText"]', {
            resizeType:1,
            width : "700px",
            pasteType : 1,
            items:[
                'source', '|', 'undo', 'redo', '|', 'preview',  'template',  'cut', 'copy', 'paste',
                'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                'superscript','|',
                'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
                'table', 'hr', 'emoticons', 'baidumap', 'pagebreak', 'link', 'unlink','|','clearhtml', 'quickformat', 'selectall', 'fullscreen'
            ],
            uploadJson : appServer+'/advertisement/kindeditor-image-upload.htm',
            resizeMode : 0,
            afterBlur : function() {
                this.sync();
                if (this.isEmpty()) {
                    $("#contentInfo").html("请填写内容");
                }else{
                    $("#contentInfo").html("");
                }
            },
            afterChange : function() {
                $('.word_count1').html(this.count()); //字数统计包含HTML代码
                $('.word_count2').html(this.count('text'));  //字数统计包含纯文本、IMG、EMBED，不包含换行符，IMG和EMBED算一个文字
                //限制字数
                var limitNum = 20000;  //设定限制字数
                var pattern = '还可以输入' + limitNum + '字';
                $('.word_surplus').html(pattern); //输入显示
                if(this.count('text') > limitNum) {
                    pattern = ('字数超过限制，请适当删除部分内容');
                    //超过字数限制自动截取
                    var strValue = editor.text();
                    strValue = strValue.substring(0,limitNum);
                    editor.text(strValue);
                } else {
                    //计算剩余字数
                    var result = limitNum - this.count('text');
                    pattern = '还可以输入' +  result + '字';
                }
                $('.word_surplus').html(pattern); //输入显示
            }

        });
});

function save(saveType){
    validateParam(saveType);
    if(flag && result && response){
        document.getElementById("searchForm").action=appServer+"/article/add.htm";
        $("#searchForm").submit();
    }
}

function publish(saveType){
    validateParam(saveType);
    if(flag && result && response){
        document.getElementById("searchForm").action=appServer+"/article/publish.htm";
        $("#searchForm").submit();
    }
}

function edit(saveType){
    validateParam(saveType);
    if(flag && result && response){
        $("#searchForm").submit();
    }
}

function validateParam(saveType){
    var title=$("#title").val().trim();
    var articalTypeId=$("#articalTypeId").val();
    var articleAuthor=$("#articleAuthor").val().trim();
    var articleRemark=$("#articleRemark").val().trim();
    var orders=$("#orders").val();
    var articleText=$("#articleText").val();
    var uploadFile=fileInfo;
    if(title==null || title==""){
        $("#titleInfo").html("请填写标题");
        flag=false;
        return;
    }else{
        flag=true;
        $("#titleInfo").html("");
    }
    if(articalTypeId==null || articalTypeId==""){
        $("#articalTypeIdInfo").html("请选择分类");
        flag=false;
        return;
    }else{
        flag=true;
        $("#articalTypeIdInfo").html("");
    }
    /*if(listingStartTime==null || listingStartTime==""){
        $("#beginDateInfo").html("请选择开始时间");
        flag=false;
        return;
    }else{
        flag=true;
        $("#beginDateInfo").html("");
    }
    if(listingEndTime==null || listingEndTime==""){
        $("#endDateInfo").html("请选择结束时间");
        flag=false;
        return;
    }else{
        flag=true;
        $("#endDateInfo").html("");
    }*/
    if(articleAuthor==null || articleAuthor==""){
        $("#articleAuthorInfo").html("请填写作者");
        flag=false;
        return;
    }else{
        flag=true;
        if(result){
            $("#articleAuthorInfo").html("");
        }
    }
    if(orders==null || orders==""){
        $("#ordersInfo").html("请填写排序号");
        flag=false;
        return;
    }else{
        flag=true;
        if(response){
            $("#ordersInfo").html("");
        }
    }


    if(articleRemark==null || articleRemark==""){
        $("#articleRemarkInfo").html("请填写文章简介");
        flag=false;
        return;
    }else{
        flag=true;
        $("#articleRemarkInfo").html("");
    }

    if(saveType == 'add'){
        if(uploadFile==null || uploadFile==""){
            $("#uploadFileInfo").html("请上传图片");
            flag=false;
            return;
        }else{
            flag=true;
            $("#uploadFileInfo").html("");
        }
    }

    var contentStr = articleText.replace(/&nbsp;/ig,'');
    var content = $.trim(contentStr);

    if(content==null || content==""){
        $("#contentInfo").html("请填写内容");
        flag=false;
        return;
    }else{
        flag=true;
        $("#contentInfo").html("");
    }


}

function checkOrders(){
    var orders=$("#orders").val();
    var articalTypeId=$("#articalTypeId").val();
    var oldOrder=$("#oldOrdcer").val();
    if(orders!="" && articalTypeId!="" && orders != oldOrder){
        jQuery.ajax({
            type:"post",
            url:appServer + "/article/ajax/validateOrders.htm",
            dataType:"json",
            data:{"orders":orders,"articalTypeId":articalTypeId},
            success:function(message){
                if(message.info=="No"){
                    $("#ordersInfo").html("排序号已存在");
                    response=false;
                }else{
                    $("#ordersInfo").html("");
                    response=true;
                }
            },
            error:function(xhr, message, e){

            }
        });
    }else{
        $("#ordersInfo").html("");
        response=true;
    }
}

function checkCode(value){
    if(value != $("#oldCode").val() && value != ""){
        jQuery.ajax({
            type:"post",
            url:appServer + "/article/ajax/validateCode.htm",
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


function uploadLogo() {
    fileInfo=$("#uploadFile").val();
    var arr = fileInfo.split('\\');
    //$("#fileInfo").html(arr[arr.length-1]);
    var id=$("#articleId").val();
    $.ajaxFileUpload({
        type:"post",
        url:appServer + "/ajax/article/updateLogo.htm", //用于文件上传的服务器端请求地址
        secureuri : false, //是否需要安全协议，一般设置为false
        fileElementId : 'uploadFile', //文件上传域的ID
        data:{"id":id},
        dataType : 'json', //返回值类型 一般设置为json
        success : function(result) {
            $("#fileInfo").html(arr[arr.length-1]);
            $('#fileImage').empty();
            $('#fileImageTile').empty();
            $("#fileImageTile").append("<span style='color:red'>*</span>logo缩略图：");
            $("#fileImage").append("<img src='"+imageServer+"/"+result.data+"'/>");
            if (result.uuid != null){
                $("#uuid").val(result.uuid);
            }
        },
        error: function (data, status, e){
            alert('导入logo失败！');
        }
    });
}
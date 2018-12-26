var flag=true;
var result=true;
$(function() {
    // textarea
    var editor = KindEditor.create(
        'textarea[name="content"]', {
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

function updateLogo() {
    var fileInfo=$("#uploadFile").val();
    var arr = fileInfo.split('\\');
    $("#fileInfo").html(arr[arr.length-1]);
}
function sub(){
    validateParam();
    if(flag && result){
        $("#searchForm").submit();
    }
}
function checkValue(){
    var options= $("#type option:selected");
    if(options.val()=="1"){
        $("#kinderEdit").hide();
        $("#picture").show();
    }else if(options.val()=="2"){
        $("#kinderEdit").show();
        $("#picture").hide();
    }
}
function validateParam(){
    var title=$("#title").val().trim();
    var adPositionId=$("#adPositionId").val();
    var listingStartTime=$("#listingStartTime").val();
    var listingEndTime=$("#listingEndTime").val();
    var orders=$("#orders").val();
    if(title==null || title==""){
        $("#titleInfo").html("请填写标题");
        flag=false;
        return;
    }else{
        flag=true;
        $("#titleInfo").html("");
    }
    if(adPositionId==null || adPositionId==""){
        $("#adPositionIdInfo").html("请选择广告位");
        flag=false;
        return;
    }else{
        flag=true;
        $("#adPositionIdInfo").html("");
    }
    if(listingStartTime==null || listingStartTime==""){
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
    }
    if(orders==null || orders==""){
        $("#ordersInfo").html("请填写排序号");
        flag=false;
        return;
    }else{
        flag=true;
        if(result){
            $("#ordersInfo").html("");
        }
    }
    var type=$("#type").val();
    if(type=="1"){

        var uploadFile=$("#uploadFile").val();
        if(uploadFile==null || uploadFile==""){
            $("#uploadFileInfo").html("请上传图片");
            flag=false;
            return;
        }else{
            flag=true;
            $("#uploadFileInfo").html("");
        }
    }else if(type=="2"){
        var str = $.trim($("#content").val());
        var contentStr = str.replace(/&nbsp;/ig,'');
        var content = $.trim(contentStr);
        if(content == ""){
            $("#contentInfo").html("请填写内容");
            flag=false;
            return;
        }else{
            flag=true;
            $("#contentInfo").html("");
        }

    }
}

function checkOrders(){
    var orders=$("#orders").val();
    var adPositionId=$("#adPositionId").val();
    var oldOrder=$("#oldOrdcer").val();
    if(orders!="" && adPositionId!="" && orders != oldOrder){
        jQuery.ajax({
            type:"post",
            url:appServer + "/advertisement/ajax/validateOrders.htm",
            dataType:"json",
            data:{"orders":orders,"adPositionId":adPositionId},
            success:function(message){
                if(message.info=="No"){
                    $("#ordersInfo").html("排序号已存在");
                    result=false;
                }else{
                    $("#ordersInfo").html("");
                    result=true;
                }
            },
            error:function(xhr, message, e){

            }
        });
    }else{
        $("#ordersInfo").html("");
        result=true;
    }
}
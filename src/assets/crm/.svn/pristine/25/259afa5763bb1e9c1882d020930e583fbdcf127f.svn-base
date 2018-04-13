
$(function() {
    $("#rdcList li").bind("click",function (e) {
        $(".active").removeClass("active");
        $(this).addClass("active");
        showArea($(this).attr("data"));
    })
    $(".btn-grant").bind("click",doGrant)
});
function showArea(code){
    $.getJSON("company/listComOrg",{"id":code},function (data) {
        if(data.errorCode == 0){
            var ids = data.data;
            if(ids.length > 0){
                $(".radio input[value='"+ids+"']").prop("checked", true);
            }else{
                $(".radio input").prop("checked", false);
            }

            // for(var i=0;i<ids.length;i++){
            //     $(".checkbox input[value='"+ids[i]+"']").prop("checked", true);
            // }
        }
    })
}

function doGrant(){
    var currentData = $("#rdcList li[class='active']").first().attr("data");
    if(currentData) {
        if(currentData) {
            var areaNodes = $("#brandForm").serializeObject().brand
            if (areaNodes && areaNodes.length > 0) {
                var params = "";
                // for (var i = 0; i < areaNodes.length; i++) {
                //     params += "aid=" + areaNodes[i] + "&";
                // }
                params += "cid=" + areaNodes+"&aid="+currentData;
                $.post("company/grant", params, function (data) {
                    if (data.errorCode == 0) {
                        $.messager.popup("授权成功")
                    } else {
                        $.messager.alert("错误", "授权失败");
                    }
                })
            } else {
                $.messager.popup("请选择进行设置")
            }
        }
    }
}

function checkNodes(id){
    a.checkAllNodes(false);
    $.getJSON("Org/getAuth?id="+id,function(data){
        if(data.errorCode == 0){
            var ids = data.data;
            for(var i=0;i<ids.length;i++){
                a.checkNode(a.getNodeByParam("id",ids[i]), true, false);
            }
        }
    });
}
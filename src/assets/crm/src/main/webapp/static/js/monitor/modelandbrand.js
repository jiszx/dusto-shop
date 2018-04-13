$(function() {
    $("#rdcList li").bind("click",function (e) {
        $(".active").removeClass("active");
        $(this).addClass("active");
        showArea($(this).attr("data"));
    })
    $(".btn-grant").bind("click",doGrant)
});
function showArea(code){
    $(".checkbox input[type='checkbox']").attr("checked",false);
    $.getJSON("config/ModelBrand/brands",{"model":code},function (data) {
        if(data.errorCode == 0){
            var ids = data.data;
            for(var i=0;i<ids.length;i++){
                $(".checkbox input[value='"+ids[i]+"']").prop("checked", true);
                // if(a.getNodeByParam("id",ids[i])){
                //     a.checkNode(a.getNodeByParam("id",ids[i]), true, false);
                // }
            }
        }
    })
}

function doGrant(){
    var currentData = $("#rdcList li[class='active']").first().attr("data");
    if(currentData) {
        var areaNodes = $("#brandForm").serializeObject().brand
        var params = "";
        if (areaNodes && areaNodes.length > 0) {
            for (var i = 0; i < areaNodes.length; i++) {
                params += "aid=" + areaNodes[i] + "&";
            }
        } else {
            $.messager.popup("请选择生意模式进行设置")
        }
        params += "rdc=" + currentData;
        $.post("config/ModelBrand/grant", params, function (data) {
            if (data.errorCode == 0) {
                $.messager.popup("授权成功")
            } else {
                $.messager.alert("错误", "授权失败");
            }
        })
    }
    console.debug(currentData);
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
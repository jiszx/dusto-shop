var settings = {
    check : {
        enable : true,
        chkStyle : "checkbox"
    },
    view : {
        dblClickExpand : false,
        showLine : true,
        selectedMulti : false
    },
    async : {
        dataType : "json",
        enable : true,
        type : "post",
        url : "pub/area/list?level=3",
        dataFilter: function(treeId, parentNode, responseData){
            var jsonData = responseData.data;
            // for(var i=0;i<jsonData.length;i++){
            //     if(jsonData[i].areaLevel/1 <2){
            //         jsonData[i].chkDisabled=true;
            //     }
            // }
            // console.debug(jsonData)
            return jsonData
        }
    },
    data : {
        key : {
            name : "name"
        },
        simpleData : {
            enable : true,
            idKey : "id",
            pIdKey : "pid",
            rootPId : "0"
        }
    },
    callback : {
    }
};
var a = $("#areatree");
$(function() {
    a = $.fn.zTree.init(a,settings);
    $("#rdcList li").bind("click",function (e) {
        $(".active").removeClass("active");
        $(this).addClass("active");
        showArea($(this).attr("data"));
    })
    $(".btn-grant").bind("click",doGrant)
});

function showArea(code){
    $.getJSON("company/area/listComArea",{"id":code},function (data) {
        a.checkAllNodes(false);
        if(data.errorCode == 0){
            var ids = data.data;
            for(var i=0;i<ids.length;i++){
                if(a.getNodeByParam("id",ids[i])){
                    a.checkNode(a.getNodeByParam("id",ids[i]), true, false);
                }
            }
        }
    })
}

function doGrant(){
    var currentData = $("#rdcList li[class='active']").first().attr("data");
    if(currentData) {
        var areaNodes = a.getCheckedNodes(true);
        var params = "";
        if (areaNodes && areaNodes.length > 0) {
            for (var i = 0; i < areaNodes.length; i++) {
                params += "aid=" + areaNodes[i].id + "&";
            }
        }
        params += "cid=" + currentData;
        $.post("company/area/grant", params, function (data) {
            if (data.errorCode == 0) {
                $.messager.popup("区域授权成功")
            } else {
                $.messager.alert("错误", "授权失败");
            }
        })
    }
}
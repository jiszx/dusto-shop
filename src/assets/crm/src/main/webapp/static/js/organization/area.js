var setting = {
    check : {
        enable : false
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
        url : "Org/list",
        dataFilter: function(treeId, parentNode, responseData){
            return responseData.data;
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
        onClick : function(event, treeId, treeNode, clickFlag) {
            checkNodes(treeNode.id);
        }
    }
};

var settings = {
    check : {
        enable : true
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
        url : "pub/area/list",
        dataFilter: function(treeId, parentNode, responseData){
            return responseData.data;
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
        // onClick : function(event, treeId, treeNode, clickFlag) {
        //     showNodeInfo(treeNode);
        // }
    }
};
var t = $("#tree");
var a = $("#areatree");
$(function() {
    t = $.fn.zTree.init(t, setting);
    a = $.fn.zTree.init(a,settings);
    $(".btn-grant").bind("click",doGrant)
});
function showNodeInfo(treeNode,validate) {
    if (treeNode.pid == "0") {
        $("#baseInfo").addClass("hidden");
    } else {
        autoEdit(treeNode);

        if (treeNode.mgrLevel == 2) {
            if (treeNode.branchType == 0) {
                $("#editmgrLevel").val("-1");
            } else {
                $("#editmgrLevel").val("1");
            }
        }
        $("#baseInfo").removeClass("hidden");
    }
}

function doGrant(){
    if(t.getSelectedNodes()[0] && t.getSelectedNodes()[0].levels == "4"){
        var orgNode = t.getSelectedNodes()[0];
        var areaNodes = a.getCheckedNodes(true);
        if(areaNodes && areaNodes.length > 0){
            var params = "";
            for(var i=0;i<areaNodes.length;i++){
                params+="aid="+areaNodes[i].id+"&";
            }
            params+="orgId="+orgNode.id;
            $.post("Org/grant",params,function(data){
                if(data.errorCode == 0){
                    $.messager.popup("区域授权成功")
                }else{
                    $.messager.alert("错误","授权失败");
                }
            })
        }
    }else{
        $.messager.alert("提示","只有业务省才能指定行政区域");
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
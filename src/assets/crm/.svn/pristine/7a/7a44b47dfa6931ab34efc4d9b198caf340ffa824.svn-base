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
        url : "system/auth/list",
        dataFilter: function(treeId, parentNode, responseData){
            var data = responseData.data;
            for(var i=0;i<data.length;i++){
                if(data[i].pid){
                    data[i].text = data[i].resId + data[i].resName + "("+data[i].pid+")"
                }else{
                    data[i].text = data[i].resId + data[i].resName;
                }
            }
            return responseData.data;
        }
    },
    data : {
        key : {
            name : "text"
        },
        simpleData : {
            enable : true,
            idKey : "resId",
            pIdKey : "pid",
            rootPId : "0"
        }
    },
    callback : {
        onClick : function(event, treeId, treeNode, clickFlag) {
            showNodeInfo(treeNode);
        },
        onDblClick:function(event, treeId, treeNode){
            $("#editEmpForm").resetForm();
            $("#ppid").val(treeNode.resId);
            $("#res").val(treeNode.resId)
            $("#pidName").val(treeNode.resName);
            $("#showAuthModal").modal("show")
        }
    }
};
$(function() {
    var t = $("#tree");
    t = $.fn.zTree.init(t, setting);
    $("#editAuthForm").ajaxForm({
        target:'#btn-edit',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            //return editValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
        	if(responseText.errorCode != 0){
                $.messager.alert("修改信息失败",responseText.errorMessage);
            }else{
            	t.refresh();
            }
        }
    });
    $("#btn-del").bind("click",function(){
        $.ajax({
            url:"system/auth/del",
            data:"resId="+$("#editresId").val(),
            dataType:"json",
            type:"post",
            success:function(data){
                if(data && data.errorCode && data.errorCode == 0){
                    $.messager.popup("删除成功")
                }
            }
        });
    });

    $("#editEmpForm").ajaxForm({
        target:'#btn-edit-save',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            //return editValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
            if(responseText.errorCode != 0){
                $.messager.alert("修改信息失败",responseText.errorMessage);
            }else{
                $("#showAuthModal").modal("hide");
                t.refresh();
            }
        }
    });

    $("#uploadForm").ajaxForm({
        target:'#btn-upload-save',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            //return editValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
            if(responseText.errorCode != 0){
                $.messager.alert("修改信息失败",responseText.errorMessage);
            }else{
                $("#showUploadModal").modal("hide");
                t.refresh();
            }
        }
    });

    $("#btn-upload").click(function(){
        $("#showUploadModal").modal("show")
    });

});
function showNodeInfo(treeNode,validate) {
	$("#editAuthForm").resetForm();
	autoEdit(treeNode);
}
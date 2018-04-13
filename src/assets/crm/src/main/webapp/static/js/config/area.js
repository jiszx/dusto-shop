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
        url : "pub/area/list?level=4",
        dataFilter: function(treeId, parentNode, responseData){
            var data = responseData.data;
            return data;
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
            showNodeInfo(treeNode);
        }
    }
};
$(function() {
    var t = $("#tree");
    t = $.fn.zTree.init(t, setting);
    var editValidator = initValidate("#editAreaForm");
    $("#editAreaForm").ajaxForm({
        target:'#btn-edit',   // target element(s) to be updated with server response
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return editValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
            if(responseText.errorCode != 0){
                $.messager.alert("修改失败",responseText.errorMessage);
            }else{
                alert(1)
            }
        }
    });
});
function showNodeInfo(treeNode,validate) {
	$("#editAuthForm").resetForm();
	autoEdit(treeNode);
}
function initValidate(formId){
    var validator = $(formId).validate({
        rules: {
            name:{
                required:true
            }
        }
    });
    return validator;
}
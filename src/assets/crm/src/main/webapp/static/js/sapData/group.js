$(function(){
    using(['treegrid'], function(){
        $('#groupTree').treegrid({
            url:'product/categroy/list',
            idField:'id',
            treeField:'name',
            sortName:"id",
            columns:[[
                {title:'类型名称',field:'name',width:180},
                {field:'id',title:'类型编码',width:60,align:'right'}
            ]],
            onBeforeLoad : function(row, param) {
                if (row) {
                    $(this).treegrid('options').url = 'product/categroy/list?pid=' + row.id;
                } else {
                    $(this).treegrid('options').url = 'product/categroy/list';
                }
            },
            onLoadSuccess:function(){
                //$(this).treegrid("select","T");
            },
            fitColumns:true,
            loadFilter:function(dd){
                var data = dd.data;
                for (var int = 0; int < data.length; int++) {
                    if(data[int].id.length == 5){

                    }else{
                        data[int].children=[];
                        data[int].state="closed"
                    }
                }
                return data;
            }
        });
    });
    $(".btn-edit").bind("click",doEdit);
    $(".btn-add").bind("click",function(){
        var node = $('#groupTree').treegrid("getSelected");
        if(node){
            $("#pid").val(node.id);
            $("#showPid").val(node.name);
        }
        $("#addCateModal").modal("show");
    });
    $(".btn-del").bind("click",doDel);

    var addValidator = initValidate("#addCateForm");
    var editValidator = initValidate("#editCateForm");
    $("#editCateForm").ajaxForm({
        target:'#btn-edit-save',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return editValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
            $("#editCateModal").modal("hide");
            $('#groupTree').treegrid("reload");
        }
    });
    $("#addCateForm").ajaxForm({
        target:'#btn-add',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return addValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
            $("#addCateModal").modal("hide");
            $('#groupTree').treegrid("reload");
        }
    });

});
function doEdit(){
    var node = $('#groupTree').treegrid("getSelected");
    if(node){
        autoEdit(node);
        $("#editCateModal").modal("show")
    }else{
        $.messager.alert("提示", "请选择要编辑的记录!");
    }
}
function doDel(){
    var node = $('#groupTree').treegrid("getSelected");
    if(node){
        $.messager.confirm("警告", "您确认要删除此记录吗?", function() {
            $.post("product/categroy/del",{"id":node.id},function(){
            	$('#groupTree').treegrid("unselectAll");
            	$('#groupTree').treegrid("reload");
            	})
        });
    }else{
        $.messager.alert("提示", "请选择要删除的记录!");
    }
}

function initValidate(formId){
    var validator = $(formId).validate({
        rules: {
            name: "required"
        }
    });
    return validator;
}
$(function(){
    using(['treegrid'], function(){
        $('#orgTable').treegrid({
            url:'Org/listPid',
            idField:'id',
            treeField:'name',
            columns:[[
                {title:'组织名称',field:'name',width:180},
                {field:'id',title:'组织代码',width:60,align:'right'},
                {field:'sapId',title:'SAP组织',width:200,formatter:function (value) {
                    if(value){
                        return getDictValue(value)+"("+value+")";
                    }
                }},
                {field:'levels',title:'级别',width:80},
                {field:'type',title:'状态',width:80},
                {field:'contactName',title:'联系人',width:80},
                {field:'tel',title:'联系电话',width:80},
                {field:'attribute1',title:'最大货补比例',width:80,formatter:function(value){
                	if(value){
                		return value+'%';
                	}
                }},
                {field:'attribute2',title:'最大计提比例',width:80,formatter:function(value){
                	if(value){
                		return value+'%';
                	}
                }},
                {field:'attribute3',title:'收款人',width:60},
                {field:'attribute4',title:'复核',width:60}
            ]],
            onBeforeLoad : function(row, param) {
                if (row) {
                    $(this).treegrid('options').url = 'Org/listPid?pid=' + row.id;
                } else {
                    $(this).treegrid('options').url = 'Org/listPid';
                }
            },
            onLoadSuccess:function(){
                $(this).treegrid("select","T");
            },
            fitColumns:true,
            loadFilter:function(dd){
                var data = dd.data;
                for (var int = 0; int < data.length; int++) {
                    if(data[int].levels == "4"){
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
        var node = $('#orgTable').treegrid("getSelected");
        $("#pid").val(node.id);
        $("#showPid").val(node.name);
        $("#addOrgModal").modal("show");
    })
    var addValidator = initValidate("#addOrgForm");
    var editValidator = initValidate("#editOrgForm");
    $("#editOrgForm").ajaxForm({
        target:'#btn-edit-save',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return editValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
        	if(responseText.errorCode != 0){
                $.messager.alert("添加失败",responseText.errorMessage);
            }else{
            	$("#editOrgModal").modal("hide");
                $('#orgTable').treegrid("reload");
            }
        }
    });
    $("#addOrgForm").ajaxForm({
        target:'#btn-add',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return addValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
        	if(responseText.errorCode != 0){
                $.messager.alert("添加失败",responseText.errorMessage);
            }else{
            	$("#addOrgModal").modal("hide");
                $('#orgTable').treegrid("reload");
            }
        }
    });
    $(".btn-del").bind("click",doDel);
});

function initValidate(formId){
    var validator = $(formId).validate({
        rules: {
            name: "required",
            contactName: {required: true,minlength: 2},
            tel: {required: true, number: 11}
        }
    });
    return validator;
}
// /**
//  * 编辑按钮
//  */
function doEdit(){
    var node = $('#orgTable').treegrid("getSelected");
    if(node){
        autoEdit(node);
        $("#editOrgModal").modal("show")
    }else{
        $.messager.alert("提示", "请选择要编辑的记录!");
    }
}
function doDel(){
    var node = $('#orgTable').treegrid("getSelected");
    
    if(node){
    	if(node.id.length>9){
    		 $.messager.confirm("警告", "您确认要删除此记录吗?", function() {
    	            $.post("Org/del",{"id":node.id},function(data){
    	            	if(data.errorCode == "0"){
    	            		$.messager.popup("删除成功")
    	            		$('#orgTable').treegrid("reload");
    	            	}else{
    	            		$.messager.alert("提示", "删除失败!");
    	            	}
    	            	})
    	        });
    	}else{
    		$.messager.alert("提示", "费用相关项目，不允许删除!");
    	}
    }else{
        $.messager.alert("提示", "请选择要删除的记录!");
    }
}
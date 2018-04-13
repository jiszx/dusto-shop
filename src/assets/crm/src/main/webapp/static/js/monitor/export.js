var table=null;
$(function(){
    table = $('#dictTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'monitor/export/list.json',
        cache: false,
        toolbar:"#dictTool",
        striped: true,
        pagination: true,
        searchOnEnterKey:true,
        sidePagination:"server",
        idField:"id",
        sortName:"colName",
        smartDisplay:true,
        pageSize: 10,
        pageList:["10","20","50","100"],
        search: false,
        showColumns: true,
        showRefresh: true,
        clickToSelect: true,
        singleSelect:true,
        columns: [{
            field: 'ck',
            title: '编号',
            radio:true
        }, {
            field: 'description',
            title: '说明',
            align: 'left'
        }, {
            field: 'key',
            title: 'key',
            align: 'left'
        }, {
            field: 'mapping',
            title: '映射',
            align: 'left'
        }, {
            field: 'sql',
            title: 'sql',
            align: 'left'
        }]
    });
    var editValidator = initValidate("#editDictForm");
    $("#editDictForm").ajaxForm({
        target:'#btn-edit-save',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return editValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
        	if(responseText.errorCode != 0){
                $.messager.alert("修改字典信息失败",responseText.errorMessage);
            }else{
            	$("#editDictModal").modal("hide");
               table.bootstrapTable("refresh");
            }
        }
    });
    $(".btn-edit").bind("click",doEdit);
    $("#exportBtn").bind('click',doExport);
    $("#addBtn").bind('click',doAdd);
    $("#deleteBtn").bind('click',deleteConfig);
    
});

function initValidate(formId){
    var validator = $(formId).validate({
        rules: {
            colName: "required",
            chooseVal: "required",
            showText: {required: true,minlength: 2},
            orders: {required: true, number: 5}
        }
    });
    return validator;
}
/**
 * 编辑按钮
 */
function doEdit(){
    var rows =table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
    	$("#editdictLabel").html('修改');
        autoEdit(rows[0]);
        $("#editDictModal").modal("show")
    }else{
        $.messager.popup("请选择要编辑的记录!");
    }
}

function doAdd(){
	$("#editdictLabel").html('新增');
	$("#editDictForm").find(':input').not(':button, :submit, :reset').val('')
		.removeAttr('checked').removeAttr('selected');
	$("#editid").val("0");
    $("#editDictModal").modal("show")
}

function deleteConfig(){
	var rows =table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
    	$.messager.confirm("警告", "删除后不可恢复，请确认删除?", function() {
    		$.post("monitor/export/delete.json",{"id":rows[0].id},function(res){
        		if(res.data && res.data!=0){
        			$.messager.popup("删除成功!");
        			table.bootstrapTable("refresh");
        		}else{
        			$.messager.popup("删除失败!");
        		}
        	});
         });
    	
    }else{
        $.messager.popup("请选择要编辑的记录!");
    }
}

function doExport(){
	var rows =table.bootstrapTable("getSelections");
    if(!rows || rows.length !=1){
    	$.messager.popup("选择记录")
    	return;
    }
    var key = rows[0].key;
	$("#generateFile").html("正在生成excel，请耐心等待...");
	$("#exportModal").modal();
	$.ajax({
		url:'monitor/export/generate.json?key='+key,
		timeout:300000,
		error:function(){
		},
		success:function(res, textStatus, XMLHttpRequest){
		    if(res.data.length<2){
		    	$.messager.alert("提示","数据太多，无法导出");
		    }else{
		    	$("#generateFile").html('<a href="'+window.IMAGE_BASEURI+res.data+'" target="_blank">生成成功，点击下载</a>')
		    }
		} 
	});
}
var table=null;
var t = $("#categoryTree");
$(function(){
    table = $('#knowledgeTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'monitor/app/list?search='+version,
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
        showRefresh: false,
        clickToSelect: true,
        singleSelect:true,
        columns: [{
            field: 'ck',
            title: '编号',
            radio:true
        }, {
            field: 'objectKey',
            title: '名称',
            align: 'left'
        }, {
            field: 'attachmentName',
            title: '更新说明',
            align: 'left'
        }, {
            field: 'attachmentType',
            title: '更新要求',
            align: 'left',
            formatter:function(val){
                return val=='1'?'强制更新':'可选更新';
            }
        }, {
            field: 'uploadTs',
            title: '创建时间',
            align: 'left'
        }, {
            field: 'isAttachment',
            title: '附件',
            align: 'left',
            formatter:function(value,row){
                return "<a href='"+window.IMAGE_BASEURI+row.fileName+"'>下载</a>"
            }
        }]
    });
     var addValidator = initValidate("#addKnowledgeForm");
    var editValidator= $("#editKnowledgeForm").validate({
        rules: {
            name: "required"
        }
    });
    $("#addKnowledgeForm").ajaxForm({
        target:'#btn-save',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
        	var result = addValidator.valid();
        	if(result){
        		$('#btn-add').attr('disabled',true);
        	}
            return result;
        },
        success:function(responseText, statusText, xhr, $form){
            if(responseText.errorCode == 0){
            	$('#btn-add').removeAttr('disabled');
                $("#addKnowLedgeModal").modal("hide");
                window.location.reload();
            }
        }
    });
    $("#editKnowledgeForm").ajaxForm({
        target:'#btn-edit-save',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return editValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
            if(responseText.errorCode == 0 && responseText.data!='0'){
                $("#editKnowLedgeModal").modal("hide");
                window.location.reload();
            }else{
            	$.messager.alert("上传失败","无法上传文件");
            }
        }
    });
    // $("#addKnowledgeForm").ajaxForm({
    //     target:'#btn-add',   // target element(s) to be updated with server response
    //     clearForm:true,
    //     dataType:'json',
    //     beforeSubmit:  function(formData, jqForm, options){
    //         return addValidator.valid();
    //     },
    //     success:function(responseText, statusText, xhr, $form){
    //         if(responseText.errorCode == 0){
    //             $("#addKnowLedgeModal").modal("hide");
    //             table.bootstrapTable("refresh")
    //         }
    //     }
    //     // other available options:
    //     //url:       url         // override for form's 'action' attribute
    //     //type:      type        // 'get' or 'post', override for form's 'method' attribute
    //     //dataType:  null        // 'xml', 'script', or 'json' (expected server response type)
    //     //clearForm: true        // clear all form fields after successful submit
    //     //resetForm: true        // reset the form after successful submit
    //     //timeout:   3000
    // });
    $(".btn-addFile").bind("click",doAdd);
    $(".btn-edit").bind("click",doEdit);
    $(".btn-del").bind("click",doDel);
});
function doAdd() {
    $("#addKnowLedgeModal").modal("show");
}
function initValidate(formId){
    var validator = $(formId).validate({
        rules: {
            name: "required",
            upload: "required"
        }
    });
    return validator;
}
/**
 * 编辑按钮
 */
function doEdit(){
    var rows = table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        autoEdit(rows[0]);
        var node = t.getNodesByParam("id",rows[0].objectName)
        $("#editshowName").val(node[0].name);
        $("#editKnowLedgeModal").modal("show")
    }else{
        $.messager.alert("提示", "请选择要编辑的记录!");
    }
}
function doShow(){
    var rows = table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        showDetail("knowledge/detail?id="+rows[0].id);
    }else{
        $.messager.alert("提示", "请选择记录!");
    }
}
function doDel(){
    var rows = table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        $.messager.confirm("警告", "您确认要删除此记录吗?", function() {
            $.post("knowledge/del",{"id":rows[0].id},function(){
//            	table.bootstrapTable("refresh");
            	window.location.reload();
            })
        });
    }else{
        $.messager.alert("提示", "请选择要删除的记录!");
    }
}
function realodTable(id){
    table.bootstrapTable("refresh",{"category":id});
}
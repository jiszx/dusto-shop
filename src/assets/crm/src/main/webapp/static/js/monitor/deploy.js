var table=null;
$(function(){
    table = $('#deployTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'config/process/list',
        cache: false,
        toolbar:"#tools",
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
            field: 'id',
            title: '编号',
            radio:true
        }, {
            field: 'deployId',
            title: '部署编号',
            align: 'left'
        }, {
            field: 'pkey',
            title: '流程key',
            align: 'left'
        }, {
            field: 'name',
            title: '流程名称',
            align: 'left'
        }, {
            field: 'version',
            title: '版本',
            align: 'left'
        }, {
            field: 'deployDes',
            title: '部署说明',
            align: 'left'
        }]
    });
    var editValidator = initValidate("#deployForm");
    $("#deployForm").ajaxForm({
        target:'#btn-edit-save',   // target element(s) to be updated with server response
        resetForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            var lock = editValidator.valid();
            if(lock){
                window.parent.processBar(lock);
            }
            return lock
        },
        error:function(data){
            window.parent.processBar(false);
        },
        success:function(responseText, statusText, xhr, $form){
            window.parent.processBar(false);
        	if(responseText.errorCode != 0){
                $.messager.alert("部署失败",responseText.errorMessage);
            }else{
                $("#addDeployerModal").modal("hide")
               table.bootstrapTable("refresh");
            }
        }
    });
    $(".btn-edit").bind("click",doEdit);
});

function initValidate(formId){
    var validator = $(formId).validate({
        rules: {
            upload: "required"
        }
    });
    return validator;
}
/**
 * 编辑按钮
 */
function doEdit(){
    $("#addDeployerModal").modal("show")
}
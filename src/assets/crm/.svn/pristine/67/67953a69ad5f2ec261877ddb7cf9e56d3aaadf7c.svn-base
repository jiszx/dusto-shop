var table=null;
$(function(){
    table = $('#dictTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'system/dict/list.json?search=APPLICATION',
        cache: false,
        toolbar:"#dictTool",
        striped: true,
        pagination: false,
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
            field: 'colDescription',
            title: '配置说明',
            align: 'left'
        }, {
            field: 'chooseVal',
            title: '配置属性',
            align: 'left'
        }, {
            field: 'showText',
            title: '配置值',
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
    $("#btn-reInit").bind("click",doReInit);
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
        autoEdit(rows[0]);
        $("#editDictModal").modal("show")
    }else{
        $.messager.alert("提示", "请选择要编辑的记录!");
    }
}
function doReInit() {
    $.messager.confirm("提示","重新加载字典时间较长，请耐心等待",function () {
        $.post("system/dict/init",{},function(data){
            if(data.errorCode == "0"){
                $.messager.popup("加载成功")
            }else{
                $.messager.alert("提示","加载失败");
            }
        })
    })
}
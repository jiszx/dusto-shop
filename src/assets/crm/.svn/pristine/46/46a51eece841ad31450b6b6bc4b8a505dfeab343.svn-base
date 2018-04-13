/**
 * Created by Administrator on 2016/7/6.
 */
var treeObj=null;
$(function(){
    $('#companyTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url:'',// 'system/role/list',
        cache: false,
        toolbar:"#dictTool",
        striped: true,
        pagination: true,
        searchOnEnterKey:true,
        sidePagination:"server",
        idField:"id",
        sortName:"companyName",
        smartDisplay:true,
        pageSize: 10,
        pageList:["10","20","50","100"],
        search: true,
        showColumns: true,
        showRefresh: true,
        clickToSelect: true,
        singleSelect:true,
        onCheck:function(row,element){
            element.parents("table").find(".bg-blue").removeClass("bg-blue");
            element.parents("tr").children("td").addClass("bg-blue");
        },
        columns: [{
            field: 'ck',
            title: '编号',
            radio:true
        }, {
            field: 'companyName',
            title: '公司名称',
            align: 'left'
        }, {
            field: 'roleDesc',
            title: '公司级别',
            align: 'left'
        }, {
            field: 'companyName',
            title: '公司属性',
            align: 'left'
        }, {
            field: 'companyName',
            title: '经营产品',
            align: 'left'
        }, {
            field: 'companyName',
            title: '创建时间',
            align: 'left'
        }, {
            field: 'companyName',
            title: '子公司数量',
            align: 'left'
        }, {
            field: 'companyName',
            title: '上级公司',
            align: 'left'
        }, {
            field: 'companyName',
            title: '登录公司名',
            align: 'left'
        }, {
            field: 'companyName',
            title: '已授权账号',
            align: 'left'
        }]
    });
    var addValidator = initValidate("#addCompanyForm");
    var editValidator = initValidate("#editCompanyForm");
    $("#editCompanyModal").ajaxForm({
        target:'#btn-edit-save',   // target element(s) to be updated with server response
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return editValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
            if(responseText.errorCode != 0){
                $.messager.alert("修改组织信息失败",responseText.errorMessage);
            }else{
                $("#editCompanyModal").modal("hide");
                $("#editCompanyForm").resetForm();
                $("#companyTable").bootstrapTable("refresh")
            }
        }
    });
    $("#addRoleForm").ajaxForm({
        target:'#btn-add',   // target element(s) to be updated with server response
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return addValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
            if(responseText.errorCode != 0){
                $.messager.alert("添加组织信息失败",responseText.errorMessage);
            }else{
                $("#editCompanyModal").modal("hide");
                $("#addCompanyForm").resetForm();
                $("#companyTable").bootstrapTable("refresh")
            }
        }
    });
    $(".btn-edit").bind("click",doEdit);
    $(".btn-del").bind("click",doDel);
    initTree();
});
function initTree(){
    var setting = {
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
            url : "",//"system/auth/list",
            dataFilter: function(treeId, parentNode, responseData){
                return responseData.data;
            }
        },
        data : {
            key : {
                name : "companyName"
            },
            simpleData : {
                enable : true,
                idKey : "resId",
                pIdKey : "pid",
                rootPId : "0"
            }
        }
    };
    var t = $("#authTree");
    t = $.fn.zTree.init(t, setting);
    treeObj = t;
}

function initValidate(formId){
    var validator = $(formId).validate({
        rules: {
            companyName: "required",
            loginName: {required: true,maxlength: 20}
        }
    });
    return validator;
}
/**
 * 编辑按钮
 */
function doEdit(){
    var rows = $("#companyTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        autoEdit(rows[0]);
        $("#editCompanyModal").modal("show")
    }else{
        $.messager.alert("提示", "请选择要编辑的记录!");
    }
}
function doDel(){
    var rows = $("#companyTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        $.messager.confirm("警告", "您确认要删除此记录吗?", function() {
            $.post("system/role/del",{"id":rows[0].id},function(data){
                if(data.errorCode == 0){
                    $("#companyTable").bootstrapTable("refresh");
                }else{
                    $.messager.alert("删除失败,"+data.errorMessage);
                }
            })
        });
    }else{
        $.messager.alert("提示", "请选择要删除的记录!");
    }
}
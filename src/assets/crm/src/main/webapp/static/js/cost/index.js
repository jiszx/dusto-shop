var areas = {};
$(function(){
    $('#costTable').bootstrapTable({
    	method: 'get',
        classes:"table table-hover table-condensed",
        url: 'budget/list.json',
        cache: false,
         toolbar:"#dictTool",
         striped: true,
         pagination: true,
         searchOnEnterKey:true,
         sidePagination:"server",
         idField:"id",
         sortName:"orgname",
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
            field: 'orgname',
            title: '销售组织',
            align: 'left'
        }, {
            field: 'regionname',
            title: '大区',
            align: 'left'
        }, {
            field: 'provincename',
            title: '省区',
            align: 'left'
        }, {
            field: 'custname',
            title: '客户',
            align: 'left'
        }, {
            field: 'costType',
            title: '费用类型',
            align: 'left',
            formatter : function(value) {
				return getCostType(value + "");
			}
        }, {
            field: 'costSubject',
            title: '费用科目',
            align: 'left'
        },{
            field: 'period',
            title: '期间',
            align: 'left'
        }, {
            field: 'amt',
            title: '合计',
            align: 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
        }]
    });
    initSelect();
    
    var addValidator = initValidate("#addDictForm");
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
                $.messager.alert("修改信息失败",responseText.errorMessage);
            }else{
            	$("#editDictModal").modal("hide");
                $("#dictTable").bootstrapTable("refresh");
            }
        }
    });
    $("#importForm").ajaxForm({
        target:'#btn-add',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        success:function(responseText, statusText, xhr, $form){
        	if(responseText.errorCode != 0){
                $.messager.alert("导入失败",'请确认表格数据');
            }else{
            	$("#addDictModal").modal("hide");
                $("#costTable").bootstrapTable("refresh");
            }
        }
        // other available options:
        //url:       url         // override for form's 'action' attribute
        //type:      type        // 'get' or 'post', override for form's 'method' attribute
        //dataType:  null        // 'xml', 'script', or 'json' (expected server response type)
        //clearForm: true        // clear all form fields after successful submit
        //resetForm: true        // reset the form after successful submit
        //timeout:   3000
    });
    $(".btn-edit").bind("click",doEdit);
    $(".btn-del").bind("click",doDel);
    $("#btn-detail").bind("click",doShow);
    $("#searchButton").bind('click', search);
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
    var rows = $("#dictTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        autoEdit(rows[0]);
        $("#editDictModal").modal("show")
    }else{
        $.messager.alert("提示", "请选择要编辑的记录!");
    }
}
function doShow(){
    var rows = $("#dictTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        showDetail("system/dict/detail?id="+rows[0].id);
    }else{
        $.messager.alert("提示", "请选择记录!");
    }
}
function doDel(){
    var rows = $("#dictTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        $.messager.confirm("警告", "您确认要删除此记录吗?", function() {
            $.post("system/dict/del",{"id":rows[0].id},function(){$("#dictTable").bootstrapTable("refresh");})
        });
    }else{
        $.messager.alert("提示", "请选择要删除的记录!");
    }
}

function search(){
	var prov = $("#selectprov").val();
	var cust = $("#selectcust").val();
	var regin = $("#selectregin").val();
	var org = $("#selectorg").val();
	var period = $("#selectperiod").val();
	var url = "budget/list.json?organizationId="+org+"&regionId="+regin+"&provinceId="+prov+"&custname="+cust+"&period="+period;
	$('#costTable').bootstrapTable('refresh', {'url':url});
}

function initSelect(){
	$.get('Org/list.json', function(res){
		areas = res.data;
//		var orgHtml = "";
//		$.each(res.data, function(n, value){
//			if(value && value.levels==2){
//				orgHtml += '<option value="'+value.id+'">'+value.name+'</option>';
//			}
//		});
//		$("#selectorg").append(orgHtml);
	});
}

function changeRegin(pid){
	var reginHtml = '<option value="">全部</option>';
	$.each(areas, function(n, value){
		if(value && value.pid==pid){
			reginHtml += '<option value="'+value.id+'">'+value.name+'</option>';
		}
	});
	$("#selectregin").html(reginHtml);
}

function changeProv(pid){
	var provHtml = '<option value="">全部</option>';
	$.each(areas, function(n, value){
		if(value && value.pid==pid){
			provHtml += '<option value="'+value.id+'">'+value.name+'</option>';
		}
	});
	$("#selectprov").html(provHtml);
}
$(document).ready(function(){
	$("#selectperiod").datetimepicker({
    	format: 'yyyy-mm',
    	minView:3,
    	startView:3,
    	language:'zh-CN',
    	autoclose:true
    });
});
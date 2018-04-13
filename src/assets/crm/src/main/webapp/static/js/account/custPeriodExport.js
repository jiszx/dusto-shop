$(function() {
	var data = [ {
		"id" : "1",
		"source" : "线下打款",
		"d_amt" : "20,000",
		"orders" : "资金上账ID：20160106004",
		"createts" : "2016-01-06",
		"createoid" : "财务"
	},{
		"id" : "2",
		"source" : "手工调整",
		"d_amt" : "10,000",
		"orders" : "调整ID：20160110223",
		"createts" : "2016-01-10",
		"createoid" : "财务",
		"memo":"内部结算"
	},{
		"id" : "3",
		"source" : "订单扣款",
		"c_amt" : "50,000",
		"orders" : "销售订单号:20160110250",
		"createts" : "2016-01-10",
		"createoid" : "admin",
		"sku":"精制岩盐350G*60(漠河MH)"
	},{
		"id" : "4",
		"source" : "政策奖励",
		"d_amt" : "500",
		"orders" : "调整ID:20160110101",
		"createts" : "2016-01-10",
		"createoid" : "财务",
		"memo":"销售政策进货奖励--现金奖励"
	},{
		"id" : "5",
		"source" : "订单扣款",
		"c_amt" : "20,000",
		"orders" : "销售订单号：20160120114",
		"createts" : "2016-01-20",
		"createoid" : "admin",
		"sku":"精制岩盐350G*60(漠河MH)"
	},{
		"id" : "6",
		"source" : "订单取消调整",
		"d_amt" : "20,000",
		"orders" : "销售订单号：20160120114",
		"createts" : "2016-01-20",
		"createoid" : "销售内务"
	},{
		"id" : "7",
		"source" : "线下打款",
		"d_amt" : "39,500",
		"orders" : "资金上账ID:20160125044",
		"createts" : "2016-01-25",
		"createoid" : "财务"
	}
//	,{
//		"id" : "8",
//		"source" : "政策奖励",
//		"d_amt" : "5,000",
//		//"orders" : "",
//		"createts" : "2016-01-25",
//		"createoid" : "admin"
//	}
	,{
		"id" : "8",
		"source" : "合计",
		"d_amt" : "90,000",
		"c_amt":"50,000"
	} ];
	$('#custPeriod').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		//url : 'system/dict/list',
		data:data,
		cache : false,
		toolbar : "#dictTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "colName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		//search : true,
		//showColumns : true,
		//showRefresh : true,
		//clickToSelect : true,
		//singleSelect : true,
		onCheck : function() {
			$(".btn-edit,.btn-del").show();
		},
		onUncheck : function() {
			$(".btn-edit,.btn-del").hide();
		},
		columns : [ {
			field : 'id',
			title : '编号'
			//radio : true
		}, {
			field : 'source',
			title : '资金来源',
			align : 'left'
		}, {
			field : 'd_amt',
			title : '增加金额',
			align : 'left'
		}, {
			field : 'c_amt',
			title : '减少金额',
			align : 'left'
		}, {
			field : 'orders',
			title : '编号',
			align : 'left'
		}, {
			field : 'sku',
			title : 'sku',
			align : 'left'
		}, {
			field : 'createts',
			title : '发生时间',
			align : 'left'
		}, {
			field : 'createoid',
			title : '创建人',
			align : 'left'
		}, {
			field : 'memo',
			title : '备注',
			align : 'left'
		} ]
	});
	var addValidator = initValidate("#addDictForm");
	var editValidator = initValidate("#editDictForm");
	$("#editDictForm").ajaxForm({
		target : '#btn-edit-save', // target element(s) to be updated with
									// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return editValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("修改失败",responseText.errorMessage);
            }else{
            	$("#editDictModal").modal("hide");
    			$("#dictTable").bootstrapTable("refresh");
            }
		}
	});
	$("#addDictForm").ajaxForm({
		target : '#btn-add', // target element(s) to be updated with server
								// response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return addValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("添加失败",responseText.errorMessage);
            }else{
            	$("#addDictModal").modal("hide");
    			$("#dictTable").bootstrapTable("refresh");
            }
		}
	// other available options:
	// url: url // override for form's 'action' attribute
	// type: type // 'get' or 'post', override for form's 'method' attribute
	// dataType: null // 'xml', 'script', or 'json' (expected server response
	// type)
	// clearForm: true // clear all form fields after successful submit
	// resetForm: true // reset the form after successful submit
	// timeout: 3000
	});
	$(".btn-edit").bind("click", doEdit);
	$(".btn-del").bind("click", doDel);
	$("#btn-detail").bind("click", doShow);
});

function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			colName : "required",
			chooseVal : "required",
			showText : {
				required : true,
				minlength : 2
			},
			orders : {
				required : true,
				number : 5
			}
		}
	});
	return validator;
}
/**
 * 编辑按钮
 */
function doEdit() {
	var rows = $("#dictTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		autoEdit(rows[0]);
		$("#editDictModal").modal("show")
	} else {
		$.messager.alert("提示", "请选择要编辑的记录!");
	}
}
function doShow() {
	var rows = $("#dictTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		showDetail("system/dict/detail?id=" + rows[0].id);
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}
function doDel() {
	var rows = $("#dictTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
			$.post("system/dict/del", {
				"id" : rows[0].id
			}, function() {
				$("#dictTable").bootstrapTable("refresh");
			})
		});
	} else {
		$.messager.alert("提示", "请选择要删除的记录!");
	}
}
$(function() {
	$('#orgInvoicesTable').bootstrapTable({
		url : 'Org/infoInvoicesList',
		method : 'get',
		classes : "table table-hover table-condensed",
		// data:data,
		cache : false,
		toolbar : "#addorgInvoicesTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "organizationId",
		sortName : "orgname",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : '',
			title : '',
			radio : true
		}, {
			field : 'orgname',
			title : '销售组织',
			align : 'left'
		},{
			field : 'salecompany',
			title : '开票名称',
			align : 'left'
		},{
			field : 'saletaxno',
			title : '税号',
			align : 'left'
		},{
			field : 'taxItem',
			title : '税目',
			align : 'left'
		},{
			field : 'salebank',
			title : '开票账户',
			align : 'left'
		},
		{
			field : 'salebankno',
			title : '开票账号',
			align : 'left'
		}, {
			field : 'saleaddr',
			title : '开票地址',
			align : 'left'
		}, {
			field : 'saletel',
			title : '开票电话',
			align : 'left'
		}, {
			field : 'tax',
			title : '税率',
			align : 'left',
			formatter:function(value){
				if(value){					
					return value+"%";
				}else{
					return null;
				}
            }
		} , {
			field : 'checker',
			title : '复核人',
			align : 'left'
		}  ]
	});
	
	var maintainValidator = initValidate("#maintainInvoicesForm");
	$("#maintainInvoicesForm").ajaxForm({
		target : '.btn-add', // target element(s) to be updated with server
		// response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return  maintainValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("新增录入信息失败",responseText.errorMessage);
            }else{
            	$.messager.popup("维护成功!");
            	$("#maintainInvoicesModal").modal("hide");
            	$("#orgInvoicesTable").bootstrapTable("refresh")
            }
   
		}
	});
	$(".btn-edit").click(function(){
		var row = $("#orgInvoicesTable").bootstrapTable("getSelections");
		if(row && row.length >0){
			autoEdit(row[0]);
			$("#maintainInvoicesModal").modal("show");
		}else{
			$.messager.popup("请选择记录!");
		}
	});
});

function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			salecompany:"required",
			salebank:"required",
			saleaddr:"required",
			saletel:{
				required:true,
				maxlength:20
			},
			tax:{
				required:true,
				number :true,
				max :100
			},
			checker:"required",
			saletaxno:"required",
			salebankno:{
				required:true,
				number :true,
				maxlength:20
			}
		}
	});
	return validator;
}
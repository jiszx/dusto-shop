$(function() {
	$('#promotionPurTable').bootstrapTable({
		url : 'promotion/Pur/list',
		method : 'get',
		classes : "table table-hover table-condensed",
		// data:data,
		cache : false,
		toolbar : "#promotionPurTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "name",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		// search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : 'ck',
			title : '编号',
			radio : true
		}, {
			field : 'id',
			title : '编号',
			visible : false
		}, {
			field : 'orgname',
			title : '所属销售组织',
			align : 'left'
		}, {
			field : 'name',
			title : '采购方名称',
			align : 'left'
		}, {
			field : 'contact',
			title : '联系人',
			align : 'left'
		}, {
			field : 'contactTel',
			title : '联系电话',
			align : 'left'
		}, {
			field : 'creater',
			title : '创建人',
			align : 'left'
		}, {
			field : 'createTs',
			title : '创建时间',
			align : 'left'
		} ]
	});

	var addValidator = initValidate("#addPromotionPurForm");
	var editValidator = initValidate("#editPromotionPurForm");
	$("#editPromotionPurForm").ajaxForm({
		target : '#btn-edit-save', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return editValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if (responseText.errorCode != 0) {
				$.messager.alert("修改录入信息失败", responseText.errorMessage);
			} else {
				$("#editpromotionPurModal").modal("hide");
				$("#promotionPurTable").bootstrapTable("refresh")
			}
			// $("#editpromotionPurModal").modal("hide");
			// $("#promotionPurTable").bootstrapTable("refresh")
		}
	});
	$("#addPromotionPurForm").ajaxForm({
		target : '#btn-add', // target element(s) to be updated with server
		// response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return addValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if (responseText.errorCode != 0) {
				$.messager.alert("新增录入信息失败", responseText.errorMessage);
			} else {
				$("#addpromotionPurModal").modal("hide");
				$("#promotionPurTable").bootstrapTable("refresh")
			}
		}
	});
	$(".btn-edit").bind("click", doEdit);
	$(".btn-del").bind("click", doDel);
	$("#btn-search").bind("click",dosearch);
	//获取销售组织数据
	/*var url="promotion/util/org.json";
	$('#searchorgid').append("<option value=''>全部</option>");
	$.get(url, function(data) {
		if (data.rows && data.rows.length > 0) {
			for (var i = 0; i < data.rows.length; i++) {
				$('#searchorgid').append("<option value='"+data.rows[i].id+"'>"+data.rows[i].name+"</option>");
	
			}
		}
	});*/
});

function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			organizationId : "required",
			contactTel : {
				required : true,
				number : true
			},
			name : "required",
			contact : "required"
		}
	});
	return validator;
}

function doEdit() {
	var rows = $("#promotionPurTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		autoEdit(rows[0]);
		$("#editpromotionPurModal").modal("show")
	} else {
		$.messager.alert("提示", "请选择要编辑的记录!");
	}
}
function doDel() {
	var rows = $("#promotionPurTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
			$.post("promotion/Pur/del", {
				"id" : rows[0].id
			}, function() {
				$("#promotionPurTable").bootstrapTable("refresh");
			})
		});
	} else {
		$.messager.alert("提示", "请选择要删除的记录!");
	}
}

function dosearch(){
	var name =$("#searchname").val();
	name =encodeURI(name);	
	name =encodeURI(name);	
	$("#promotionPurTable").bootstrapTable("refresh", {
		'url' : 'promotion/Pur/list?name='+name+"&orgid="+$("#searchorgid").val()
	});
	
}

$(function() {
	$('#vwEntryTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'vw/entry/list',
		cache : false,
		toolbar : "#vwEntryTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		search : false,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		onCheck : function() {
			$(".btn-edit,.btn-del").show();
		},
		onUncheck : function() {
			$(".btn-edit,.btn-del").hide();
		},
		columns : [ {
			field : 'ck',
			title : '',
			radio : true
		}, {
			field : 'id',
			title : 'id',
			align : 'left',
			visible : false
		}, {
			field : 'custType',
			title : '仓库名称',
			align : 'left',
			formatter : function(value) {
				var text = $('#custType').find('option[value="'+value+'"]').text();
				return text;
			}
		}, {
			field : 'materialId',
			title : '商品编号',
			align : 'left'
		}, {
			field : 'amt',
			title : '入库数量',
			align : 'left'
		}, {
			field : 'createOid',
			title : '申请人',
			align : 'left'
		}, {
			field : 'createTs',
			title : '申请时间',
			align : 'left'
		}, {
			field : 'status',
			title : '状态',
			align : 'left',
			formatter : function(value) {
				var text = $('#vwStatus').find('option[value="'+value+'"]').text();
				return text;
			}
		} ]
	});
	var addValidator = initValidate("#addVWEntryForm");
	var editValidator = initValidate("#editVWEntryForm");
	$("#editVWEntryForm").ajaxForm({
		target : '#btn-edit-save', // target element(s) to be updated with
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return editValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			$("#editvwEntryModal").modal("hide");
			$("#vwEntryTable").bootstrapTable("refresh");
			try {
				if (responseText.data != '0') {
					$.messager.alert("提示", responseText.data);
				}
			} catch (e) {}
		}
	});
	$("#addVWEntryForm").ajaxForm({
		target : '#btn-add', // target element(s) to be updated with server
								// response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return addValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			$("#addvwEntryModal").modal("hide");
			$("#vwEntryTable").bootstrapTable("refresh");
			try {
				if (responseText.data != '0') {
					$.messager.alert("提示", responseText.data);
				}
			} catch (e) {}
		}
	});
	$(".btn-edit").bind("click", doEdit);
	$(".btn-del").bind("click", doDel);
	$("#btn-audit").bind("click", doAudit);
	$("#btnSearch").bind("click", doSearch);
	initdata();
});

/**
 * 验证数据
 * 
 * @param formId
 * @returns
 */
function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			materialId : "required",
			custType : "required",
			amt : {
				required : true,
				number : true
			}
		}
	});
	return validator;
}

/**
 * 编辑按钮
 */
function doEdit() {
	var rows = $("#vwEntryTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		autoEdit(rows[0]);
		$("#editvwEntryModal").modal("show")
	} else {
		$.messager.alert("提示", "请选择要编辑的记录!");
	}
}

/**
 * 删除
 */
function doDel() {
	var rows = $("#vwEntryTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
			$.post("vw/entry/del", {
				"id" : rows[0].id
			}, function() {
				$("#vwEntryTable").bootstrapTable("refresh");
			})
		});
	} else {
		$.messager.alert("提示", "请选择要删除的记录!");
	}
}

/**
 * 查询
 */
function doSearch() {
	var params = $("#searchForm").serializeArray();
	var param = {};
	for ( var i in params) {
		var prop = params[i].name;
		var val = $.trim(params[i].value);
		param[prop] = val;
	}
	$("#vwEntryTable").bootstrapTable("refresh",
		{
		'url' : 'vw/entry/list',
		query : param
		}
	);
}

function initdata() {
	// 物料
	$.getJSON("product/list.json?limit=100",{},function (data) {
        if(data.rows){
        	var item = data.rows;
        	var options;
            for(var i=0;i<item.length;i++){
            	options = options + "\r <option value='"+item[i].sapId+"'>"+item[i].sku+"</option>";
            }
            $("[name='materialId']").html(options);
        }
    })
}


function doAudit() {
	var rows = $("#vwEntryTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		$.messager.confirm("警告", "您确认要提交此记录吗?", function() {
			$.post("vw/entry/apply", {
				"id" : rows[0].id
			}, function(data) {
				if(data.data=="0"){	
					$.messager.popup("提交成功!");
					$("#vwEntryTable").bootstrapTable("refresh");
				}else{
					$.messager.alert("提示", "提交失敗!"+data.data);
				}
			})
		});
	} else {
		$.messager.alert("提示", "请选择要提交的记录!");
	}
}

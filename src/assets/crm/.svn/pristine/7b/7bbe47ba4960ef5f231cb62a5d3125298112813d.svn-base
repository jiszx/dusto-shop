$(function() {
	$('#invoiceTable').bootstrapTable({
		// url : 'invoiceSecurity/list?type='+$("#type").val(),
		method : 'get',
		classes : "table table-hover table-condensed",
		cache : false,
		toolbar : "#invoiceTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "orgname",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		// search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : false,
		columns : [ {
			field : 'ck',
			title : 'ck',
			align : 'center',
			valign : 'middle',
			checkbox : true
		}, {
			field : 'id',
			title : '编号',
			visible : false
		}, {
			field : 'orgname',
			title : '所属销售组织',
			align : 'left'
		}, {
			field : 'custname',
			title : '客户名称',
			align : 'left'
		}, {
			field : 'customerId',
			title : 'sap客户编码',
			align : 'left'
		}, {
			field : 'invoiceNo',
			title : '应收发票编号/订单编号',
			align : 'left'
		}, {
			field : 'amt',
			title : '发票金额(不含税)元',
			align : 'left',
			formatter : function(value) {
				return value.formatMoney();
			}
		}, {
			field : 'tax',
			title : '税额(元)',
			align : 'left',
			formatter : function(value) {
				return value.formatMoney();
			}
		}, {
			field : 'invoiceDate',
			title : '应收发票生成日期/订单创建日期',
			align : 'left'
		}, {
			field : 'period',
			title : '所属期间',
			align : 'left'
		} ]
	});

	$("#btn-search").bind("click", doSearch);
	$("#btn-verifie").bind("click", doVerifie);
	//$("#btn-details").bind("click", doDetails);
	$("#btn-verifieall").bind("click", doVerifieAll);
});

/**
 * 查询
 */
function doSearch() {
	var custname = $("#custname").val();
	var type = $("#type").val();
	var sourcesType = $("#sourcesType").val();
	var sapCustomerId = $("#sapCustomerId").val();
	if (!custname  && !sapCustomerId) {
		$.messager.popup("请输入查询客户信息");
		return;
	}
	var url = "invoiceSecurity/list?custname=" + custname + "&type=" + type
			+ "&sapCustomerId=" + sapCustomerId + "&sourcesType=" + sourcesType;
	$("#invoiceTable").bootstrapTable('refresh', {
		'url' : url
	});
}

function doVerifie() {
	var rows = $("#invoiceTable").bootstrapTable("getSelections");
	var ids = [];
	if (rows && rows.length < 1) {
		$.messager.popup("请选择记录");
		return;
	}
	$.each(rows, function(i, val) {
		ids.push(val.id);
	});
	window.location.href = "/invoiceSecurity/billingInvoice.html?ids=" + ids
			+ "&invoiceType=" + $("#type").val() + "&sourcesType="
			+ $("#sourcesType").val()+"&IsMerge=1";
}

function doVerifieAll() {
	var rows = $("#invoiceTable").bootstrapTable("getSelections");
	var ids = [];
	if (rows && rows.length < 2) {
		$.messager.popup("请选择至少2条记录");
		return;
	}
	$.each(rows, function(i, val) {
		ids.push(val.id);
	});
	window.location.href = "/invoiceSecurity/billingInvoice.html?ids=" + ids
			+ "&invoiceType=" + $("#type").val() + "&sourcesType="
			+ $("#sourcesType").val()+"&IsMerge=2";
}

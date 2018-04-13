$(function() {
	$('#invoiceTable').bootstrapTable({
	    url : 'invoiceSecurity/aisionInvoicesList?invoicesType='+$("#invoicesType").val()+"&sourcesType="+$("#sourcesType").val(),
		method : 'get',
		classes : "table table-hover table-condensed",
		cache : false,
		toolbar : "#invoiceTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "payName",
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
			field : 'sapCustomerId',
			title : 'sap客户编码',
			align : 'left'
		}, {
			field : 'sourcesNo',
			title : '应收发票编号/订单编号',
			align : 'left'
		}, {
			field : 'infoAmount',
			title : '金额(不含税)元',
			align : 'left',
			formatter : function(value) {
				return value.formatMoney();
			}
		}, {
			field : 'infoTaxAmount',
			title : '税额(元)',
			align : 'left',
			formatter : function(value) {
				return value.formatMoney();
			}
		}, {
			field : 'infoDate',
			title : '开票日期',
			align : 'left'
		}, {
			field : 'infoNumber',
			title : '发票编号',
			align : 'left'
		}, {
			field : 'infoTypeCode',
			title : '10位代码',
			align : 'left'
		}, {
			field : 'creater',
			title : '开票人',
			align : 'left'
		} ]
	});

	$("#btn-search").bind("click", doSearch);
});

/**
 * 查询
 */
function doSearch() {
	var custname = $("#custname").val();
	var invoicesType = $("#invoicesType").val();
	var sourcesType = $("#sourcesType").val();
	var sapCustomerId = $("#sapCustomerId").val();
	var url = "invoiceSecurity/aisionInvoicesList?custname=" + custname + "&invoicesType=" + invoicesType
			+ "&sapCustomerId=" + sapCustomerId + "&sourcesType=" + sourcesType;
	$("#invoiceTable").bootstrapTable('refresh', {
		'url' : url
	});
}



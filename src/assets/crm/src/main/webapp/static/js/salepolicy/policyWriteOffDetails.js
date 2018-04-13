$(function() {
	$('#policyWriteOffTable').bootstrapTable({
		url : 'salePolicy/writeofflist?policyid='+$("#policyid").val(),
		method : 'get',
		classes : "table table-hover table-condensed",
		// data:data,
		cache : false,
		toolbar : "#policyWriteOffTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "payName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		//search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
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
			field : 'sapcustid',
			title : 'SAP客户编号',
			align : 'left'
		}, {
			field : 'orderid',
			title : '订单编号',
			align : 'left'
		}, {
			field : 'sku',
			title : 'SKU',
			align : 'left'
		}, {
			field : 'price',
			title : '单价',
			align : 'left'
		}, {
			field : 'num',
			title : '数量',
			align : 'left'
		}, {
			field : 'amt',
			title : '核销金额',
			align : 'left'
		} ]
	});
});

$(function() {
	$('#custRebateDetails').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'account/rebate/orders.json?id='+$("#id").val(),
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
		pageList : [ "50", "100", "200"],
		//search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : 'headid',
			title : '销售订单号',
			align : 'left'
		}, {
			field : 'lineid',
			title : '订单行号',
			align : 'left'
		},{
			field : 'price',
			title : '单价',
			align : 'left'
		}, {
			field : 'num',
			title : '订单数量',
			align : 'left'
		},{
			field : 'orderAmt',
			title : '订单金额',
			align : 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'hbNum',
			title : '货补数量',
			align : 'left'
		},{
			field : 'hbAmt',
			title : '货补金额',
			align : 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
		},{
			field : 'sku',
			title : 'sku',
			align : 'left'
		}, {
			field : 'bdate',
			title : '下单时间',
			align : 'left'
		}, {
			field : 'edate',
			title : '完成时间',
			align : 'left'
		}, {
			field : 'salerep',
			title : '销售人员',
			align : 'left'
		}, {
			field : 'memo',
			title : '备注',
			align : 'left'
		} ]
	});
});

$(function() {
	$('#orderTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'order/orderMakeSurelist',
		cache : false,
		toolbar : "#orderTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "org",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : 'ck',
			title : '',
			radio : true
		}, {
			field : 'name',
			title : '客户名称',
			align : 'left'
		}, {
			field : 'id',
			title : 'CRM调拨单编码',
			align : 'left'
		}, {
			field : 'orderType',
			title : '订单类型',
			align : 'left',
			formatter : function(value) {
				return getOrderType(value + "");
			}
		}, {
			field : 'custType',
			title : '客户类型',
			align : 'left',
			formatter : function(value) {
				return getCustType(value + "");
			}
		}, {
			field : 'orderAmt',
			title : '订单金额',
			align : 'left'
		}, {
			field : 'orderNum',
			title : '订单数量',
			align : 'left'
		}, {
			field : 'orderWeigth',
			title : '订单重量',
			align : 'left'
		}, {
			field : 'sendNum',
			title : '发货数量',
			align : 'left'
		}, {
			field : 'sendWeigth',
			title : '发货重量',
			align : 'left'
		} ]
	});
	$("#makesureBtn").click(function(){
		var rows = $('#orderTable').bootstrapTable("getSelections");
		if(rows && rows.length==1){
			if(rows[0].custType=='70'||rows[0].custType=='4'||rows[0].custType=='9'){
				//调拨单转换订单
				window.location.href="order/makeSure.html?taskId="+rows[0].taskinstId;
			}else if(rows[0].custType=='7' || rows[0].custType=='8'){
				window.location.href="order/allocationMakeSure.html?orderId="+rows[0].id;
			}
		}else{
			$.messager.popup("请选择记录");
		}
	});
	$("#btn-search").click(function(){
		var custname = $("#custname").val();
		var orderId = $("#orderId").val();
		var sapOrderId = $("#sapOrderId").val();
		var url = "order/orderMakeSurelist?custname=" + custname+ "&orderId=" + orderId + "&sapOrderId=" + sapOrderId;
		$("#orderTable").bootstrapTable('refresh', {
			'url' : url
		});
	});
	$("#exportBtn").bind("click", doExport);
});
function constructUrl(){
	var url = "order/orderMakeSurelist?"+getSearchParams();
	return url;
}
function getSearchParams(){
	var custname = $("#custname").val();
	var orderId = $("#orderId").val();
	var sapOrderId = $("#sapOrderId").val();
	var param = "custname=" + custname+ "&orderId="+orderId+"&sapOrderId="+sapOrderId;
	return param;
}
function doExport(){
	var url = constructUrl().replace("orderMakeSurelist","mkexportOrder")+"&offset=0&limit=650000";
	window.open(url);
}

var type = '5,7';
$(function() {
	$('#orderTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'order/orderlist?orderType=0,4,5,7&states=5,7',
		// data : data,
		cache : false,
		toolbar : "#ordersearchTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "org",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		//search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : 'ck',
			title : '编号',
			radio : true
		}, {
			field : 'orgname',
			title : '销售组织',
			align : 'left'
		}, {
			field : 'regionname',
			title : '大区',
			align : 'left'
		}, {
			field : 'provname',
			title : '业务省',
			align : 'left'
		}, {
			field : 'merchname',
			title : '客户名称',
			align : 'left'
		}, {
			field : 'sapCustomerId',
			title : 'SAP客户编码',
			align : 'left'
		}, {
			field : 'orderHeaderId',
			title : 'CRM订单编号',
			align : 'left'
		}, {
			field : 'saporderid',
			title : 'SAP订单编号',
			align : 'left'
		}, {
			field : 'createTs',
			title : '下单时间',
			align : 'left'
		}, {
			field : 'salesman',
			title : '销售人员',
			align : 'left'
		}, /*{
			field : 'orderAmt',
			title : '订单金额(元)',
			align : 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
		}, {
			field : 'sendAmt',
			title : '已发货金额(元)',
			align : 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
		}, */{
			field : 'states',
			title : '订单状态',
			align : 'left',
			formatter : function(value) {
				return getorderType(value + "");
			}
		} ]
	});
	$("#btn-search").bind("click", doSearch);
	$("#btn-saveagain").bind("click", doSaveAgain);
	$("#exportBtn").bind("click", doExport);
});
$(document).ready(function() {
	$("#startTime").datetimepicker({
		format : 'yyyy-mm-dd',
		minView : 2,
		language : 'zh-CN',
		autoclose:true
	});
	$("#endTime").datetimepicker({
		format : 'yyyy-mm-dd',
		minView : 2,
		language : 'zh-CN',
		autoclose:true
	});
});
/**
 * 查询
 */
function doSearch() {
	var customer = $("#scustomer").val();
	//customer = encodeURI(customer);
	//customer = encodeURI(customer);
	var bdate = $("#startTime").val();
	var edate = $("#endTime").val();
	var crmorderid = $("#crmorderid").val();
	var url = "order/orderlist?orderType=0,4,5,7&states=" + type + "&custname=" + customer+ "&bdate=" + bdate + "&edate=" + edate +"&crmorderid=" + crmorderid;
	$("#orderTable").bootstrapTable('refresh', {
		'url' : url
	});
}


function doEdit() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && rows[0].states=='1') {
		window.location.href = "order/orderedit.html?id="+rows[0].orderHeaderId;
	} else {
		$.messager.alert("提示", "请选择编辑状态的记录!");
	}
}
function doDel() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && rows[0].states=='1') {
		$.post("order/delorder", {"id":rows[0].orderHeaderId}, function(res){
			if(res.data=="200"){
				$.messager.popup("删除成功");
			}else{
				$.messager.alert("提示","删除失败");
			}
			$("#orderTable").bootstrapTable("refresh");
		});
	} else {
		$.messager.alert("提示", "请选择编辑状态记录!");
	}
}
function doAudit() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && rows[0].states=='1') {
		$.post("order/orderAudit", {"headerid":rows[0].orderHeaderId,"states":"2"}, function(res){
			if(res.data=="200"){
				$.messager.popup("提交审批成功");
			}else{
				$.messager.alert("提示","提交审批失败");
			}
			$("#orderTable").bootstrapTable("refresh");
		});
	} else {
		$.messager.alert("提示", "请选择编辑状态记录!");
	}
}
function doSaveAgain() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		window.location.href = "order/appendWL.html?orderId="+rows[0].orderHeaderId;
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}
function constructUrl(){
	var url = "order/orderlist?orderType=0,4,5,7&states=5,7&"+getSearchParams();
	return url;
}
function getSearchParams(){
	var customer = $("#scustomer").val();
	var bdate = $("#startTime").val();
	var edate = $("#endTime").val();
	var crmorderid = $("#crmorderid").val();
	var param = "customer=" + customer+ "&bdate="+bdate+"&edate="+edate+"&crmorderid="+crmorderid;
	return param;
}
function doExport(){
	var url = constructUrl().replace("orderlist","wlexportOrder")+"&offset=0&limit=650000";
	window.open(url);
}
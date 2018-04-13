var type = 0;
$(function() {
	$('#orderTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'order/cancel/cancelList',
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
			field : 'shipname',
			title : '送达方名称',
			align : 'left'
		}, {
			field : 'sapCustomerId',
			title : 'SAP客户编码',
			align : 'left'
		}, {
			field : 'orderType',
			title : '订单类型',
			align : 'left',
			formatter : function(value) {
				return getorderType(value + "");
			}
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
		}, {
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
		}, {
			field : 'states',
			title : '订单状态',
			align : 'left',
			formatter : function(value,data) {
				if(data.attribute10){
					var res = '<a href="javascript:void(0);" onclick="doSAP(\''+data.id+'\',\''+data.attribute10+'\');">错误信息</a>';
					return res;
				}else{					
					return getorderStates(value + "");
				}
			}
		} ]
	});
	
	$("#btn-search").bind("click", doSearch);
	$("#btn-cancel").bind("click", doCancel);
	$("#btn-detalis").bind("click",doDetail);
	$("#btn-pass-agin").bind("click",doSendSap);
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
	var crmorderid =$("#crmorderid").val();
	var saporderid =$("#saporderid").val();
	var orderType =$("#orderType").val();
	var salesman =$("#salesman").val();
	var url = "order/cancel/cancelList?custname=" + customer+ "&bdate=" + bdate +
				"&edate=" + edate+"&crmorderid="+crmorderid+"&saporderid="+saporderid
				+"&orderType="+orderType+"&salesman="+salesman;
	$("#orderTable").bootstrapTable('refresh', {
		'url' : url
	});
}
/**
 * 查看订单详情
 */
function doDetail() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		window.location.href = "order/orderdetails.html?id="+rows[0].orderHeaderId;
	} else {
		$.messager.alert("提示", "请选择要记录!");
	}
}

/**
 * 取消订单
 */
function doCancel() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 ) {
		var url="order/cancel/doCancel?id="+rows[0].orderHeaderId;
		$.post(url,function(res){
			$.messager.alert("提示", res.data.msg);
			$("#orderTable").bootstrapTable("refresh");
		});
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}
function doSAP(id,msg){
			$("#sapmsg").html(msg);
			$("#orderid").val(id);
			$("#msgModal").modal("show");
}
function doSendSap(){
	$.post("order/cancel/doCancel?id="+$("#orderid").val(), function(res){
		$.messager.alert("提示",res.data.msg);
		$("#orderTable").bootstrapTable("refresh");
	});
}

var type = 0;
$(function() {
	$('#orderTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'logisticsOrder/orderlist',
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
			field : 'prov',
			title : '行政省',
			align : 'left'
		}, {
			field : 'defaultRdc',
			title : '默认RDC',
			align : 'left'
		}, {
			field : 'sendRdc',
			title : '发货RDC',
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
			field : 'address',
			title : '地址',
			align : 'left'
		}, {
			field : 'sapCustomerId',
			title : 'SAP客户编码',
			align : 'left'
		}, {
			field : 'contact',
			title : '联系人',
			align : 'left'
		}, {
			field : 'contactModel',
			title : '联系电话',
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
			field : 'transferOrderId',
			title : '调拨单单号',
			align : 'left'
		}, {
			field : 'createTs',
			title : '下单时间',
			align : 'left'
		}, {
			field : 'salesman',
			title : '创建人',
			align : 'left'
		}, {
			field : 'salesPerson',
			title : '销售人员',
			align : 'left'
		}, {
			field : 'salesPersonModel',
			title : '销售人员电话',
			align : 'left'
		}, {
			field : 'weight',
			title : '重量',
			align : 'left'
		}, {
			field : 'states',
			title : '订单状态',
			align : 'left',
			formatter : function(value) {
				return getorderStates(value + "");
			}
		} ]
	});
	$("#btn-search").bind("click", doSearch);
	$("#btn-detail").bind("click", doDetail);
	//$("#exportBtn").bind("click", doExport);
	$("#exportBtn2").bind("click", doExport2);
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
	var url = constructUrl();
	$("#orderTable").bootstrapTable('refresh', {
		'url' : url
	});
	return false;
}

function constructUrl(){
	var customer = $("#custname").val();
	//customer = encodeURI(customer);
	//customer = encodeURI(customer);
	var bdate = $("#startTime").val();
	var edate = $("#endTime").val();
	var crmorderid =$("#crmorderid").val();
	var saporderid =$("#saporderid").val();
	var shipname =$("#shipname").val();
	var transferOrderId =$("#transferOrderid").val();
	var saler =$("#saler").val();
	var type =$("#orderState").val();
	var url = "logisticsOrder/orderlist?states=" + type + "&custname=" + customer+ "&bdate=" + bdate 
			+ "&edate=" + edate+"&crmorderid="+crmorderid+"&saporderid="+saporderid+
			"&shipname="+shipname+"&saler="+saler+"&transferOrderId="+transferOrderId;
	return url;
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

/*function doExport(){
	$("#generateFile").html("正在生成excel，请耐心等待...");
	$("#exportModal").modal();
	$.ajax({
		url:'monitor/export/generate.json?key=stdOrderDetail',
		timeout:300000,
		error:function(){
		},
		success:function(res, textStatus, XMLHttpRequest){
		    if(res.data.length<2){
		    	$.messager.alert("提示","数据太多，无法导出");
		    }else{
		    	$("#generateFile").html('<a href="'+window.IMAGE_BASEURI+res.data+'" target="_blank">生成成功，点击下载</a>')
		    }
		} 
	});
}*/

function doExport2(){
	var url = constructUrl().replace("orderlist","exportOrder")+"&offset=0&limit=650000";
	window.open(url);
	//$("#generateFile").html("正在生成excel，请耐心等待...");
}
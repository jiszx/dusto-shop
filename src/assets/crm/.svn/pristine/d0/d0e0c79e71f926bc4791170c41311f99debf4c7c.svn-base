var type = 0;
$(function() {
	$('#orderTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'deployment/list?custType=8',
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
			align : 'left',
			visible	:false
		}, {
			field : 'reginname',
			title : '大区',
			align : 'left',
			visible	:false
		}, {
			field : 'provname',
			title : '业务省',
			align : 'left'
		}, {
			field : 'prov',
			title : '行政省',
			align : 'left'
		},{
			field : 'defaultRdc',
			title : '默认RDC',
			align : 'left'
		},{
			field : 'RdcName',
			title : '发货RDC',
			align : 'left'
		}, {
			field : 'custname',
			title : '送达方名称',
			align : 'left'
		}, {
			field : 'address',
			title : '地址',
			align : 'left',
			width : 150
		}, {
			field : 'contact',
			title : '联系人',
			align : 'left'
		}, {
			field : 'contactModel',
			title : '联系电话',
			align : 'left'
		}, {
			field : 'custType',
			title : '客户类型',
			formatter : function(value) {
				if(!value){
					return "-";
				}
				if('7'==value){
					return '仓储服务商';
				}
				if('70'==value){
					return '合作仓储服务商';
				}
				if('8'==value){
					return '物流商';
				}
				return '其他';
			}
		}, 
		{
			field : 'sapCustomerId',
			title : 'SAP客户编码',
			align : 'left'
		}, 
		{
			field : 'id',
			title : 'CRM调拨单编号',
			align : 'left'
		}, {
			field : 'orderId',
			title : 'CRM订单号',
			align : 'left',
			visible	:false
		}, {
			field : 'createTs',
			title : '创建时间',
			align : 'left'
		}, {
			field : 'creater',
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
			title : '重量(吨)',
			align : 'left'
		}, {
			field : 'allocationNum',
			title : '已调拨数量',
			align : 'left'
		}, {
			field : 'states',
			title : '状态',
			align : 'left',
			formatter : function(value) {
				return getorderStates(value + "");
			}
		} ]
	});
	$("#btn-search").bind("click", doSearch);
	$("#btn-detail").bind("click", doDetail);
	/*$("#exportBtn").bind("click", doExport);*/
	$("#exportBtn").bind("click", doExport2);
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
	var url = "deployment/list?"+getSearchParams();
	return url;
}
function getSearchParams(){
	var customer = $("#custname").val();
	var id =$("#id").val();
	var sapId =$("#sapId").val();
	var startTime =$("#startTime").val();
	var endTime =$("#endTime").val();
	var addr =$("#addr").val();
	//var custType =$("#custType").val();
	var custType=8;
	var orderState =$("#orderState").val();
	var param = "custname=" + customer+ "&id="+id+"&sapId="+sapId
		+"&startTime="+startTime+"&endTime="+endTime+"&custType="+custType+"&addr="+addr+"&orderStates="+orderState;
	return param;
}
/**
 * 查看订单详情
 */
function doDetail() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		window.location.href = "order/orderdetails.html?type=1&id="+rows[0].id;
	} else {
		$.messager.alert("提示", "请选择要记录!");
	}
}

/*function doExport(){
	$("#generateFile").html("正在生成excel，请耐心等待...");
	$("#exportModal").modal();
	$.ajax({
		url:'customerInvAllocation/export?'+getSearchParams(),
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

//var xmlHttp = createXMLHttpRequest();
function doExport2(){
	var url = constructUrl().replace("list","exportOrder")+"&offset=0&limit=650000";
	/*
	xmlHttp.onreadystatechange=state_Change;
	xmlHttp.open("GET", url, true)
	xmlHttp.send(null);*/
	//window.location.href = url;
	window.open(url);
	/*$("#generateFile").html("正在生成excel，请耐心等待...");
	$("#exportModal").modal();
	window.location.href = url;
	console.log("fff")*/
}

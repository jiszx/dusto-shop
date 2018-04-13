$(function(){
    $('#invoiceItemTable').bootstrapTable({
    	method : 'get',
		classes : "table table-hover table-condensed",
		url: 'invoice/report/invoiceItemlist',
		cache : false,
		//data:data,
		toolbar : "#dictTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "colName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		search : false,
		exportDataType :'all',
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,	
        columns: [ {
            field: 'name',
            title: '客户名称',
            align: 'left'
        }, {
            field: 'customerId',
            title: '客户编号',
            align: 'left'
        }, {
            field: 'orderId',
            title: 'SAP订单编号',
            align: 'left'
        }, {
            field: 'headerId',
            title: 'CRM订单编号',
            align: 'left'
        }, {
            field: 'orderItemId',
            title: '订单行项目编号',
            align: 'left',
            visible : false
        }, {
            field: 'invoiceNo',
            title: '发票号',
            align: 'left'
        }, {
            field: 'materialId',
            title: '物料编码',
            align: 'left'
        }, {
            field: 'unit',
            title: '单位',
            align: 'left'
        }, {
            field: 'sku',
            title: '物料描述',
            align: 'left'
        }, {
            field: 'price',
            title: '单价',
            align: 'left',
            width : '100px'
        }, 
        {
            field: 'num',
            title: '数量',
            align: 'left'
        },
        {
            field: 'amt',
            title: '金额',
            align: 'left'
        }, {
            field: 'num',
            title: '数量',
            align: 'left'
        }, {
            field: 'taxRate',
            title: '税率',
            align: 'left'
        }, {
            field: 'tax',
            title: '税额',
            align: 'left'
        }]
    });
    $("#btn-search").bind("click",doSearch);
})
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
function doSearch(){
	var custname = $("#custname").val();
	var invoiceNo =$("#invoiceNo").val();
	var type =$("#type").val();
	var startTime =$("#startTime").val();
	var endTime =$("#endTime").val();
	var url ="invoice/report/list?custname="+custname+"&invoiceNo="+invoiceNo+"&type="+type+"&startTime="+startTime+"&endTime="+endTime;
    $("#invoiceItemTable").bootstrapTable('refresh', {
		'url' : url
	});
}
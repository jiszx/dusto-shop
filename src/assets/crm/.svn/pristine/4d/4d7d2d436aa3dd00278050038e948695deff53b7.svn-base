$(function(){
    $('#invoiceItemTable').bootstrapTable({
    	method : 'get',
		classes : "table table-hover table-condensed",
		url: 'invoice/report/invoicelist',
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
            field: 'orgname',
            title: '销售组织',
            align: 'left'
        }, {
            field: 'name',
            title: '客户名称',
            align: 'left'
        }, {
            field: 'customerId',
            title: '客户编号',
            align: 'left'
        }, {
            field: 'invoiceNo',
            title: '发票号',
            align: 'left'
        }, {
            field: 'totalPrice',
            title: '不含税金额',
            align: 'left'
        }, {
            field: 'totalTax',
            title: '税额',
            align: 'left'
        }, {
            field: 'period',
            title: '所属期间',
            align: 'left'
        }, {
            field: 'drawDate',
            title: '开票日期',
            align: 'left',
            width : '100px'
        }, 
        {
            field: 'verifieAmt',
            title: '已核销金额',
            align: 'left'
        },
        {
            field: 'memo',
            title: '备注',
            align: 'left'
        }, {
            field: 'writeoffInvoice',
            title: '冲销发票号',
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
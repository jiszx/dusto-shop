$(function() {
	var orgid=$("#orgid").val();
	var custid =$("#custid").val();
	var period=$("#period").val();
	var accountType =$("#accountType").val();
	$('#custPeriod').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'account/period/detail.json?orgid='+orgid+'&custid='+custid+'&period='+period+"&accountType="+accountType,
		//data:data,
		cache : false,
		toolbar : "#custPeriodTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "colName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "30", "50", "100" ],
		//search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : 'id',
			title : '编号',
			visible:false
		}, {
			field : 'type',
			title : '资金来源',
			align : 'left',
			formatter : function(value) {
				return getTypeValue(value + "");
			}
		}, {
			field : 'accountType',
			title : '账户类型',
			align : 'left',
			formatter : function(value) {
				return getAccountTypeValue(value + "");
			}
		}, {
			field : 'dAmt',
			title : '增加金额',
			align : 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'cAmt',
			title : '减少金额',
			align : 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'orderId',
			title : 'CRM编号',
			align : 'left'
		}, {
			field : 'sapVoucherId',
			title : 'SAP编号',
			align : 'left'
		},{
			field : 'createTs',
			title : '发生时间',
			align : 'left'
		}, {
			field : 'attribute5',
			title : '备注',
			align : 'left'
		} ]
	});
	
});


function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
}
$(function() {
	$('#costPeriodDetails').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'costBalance/DetailList',
		//data:data,
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
		pageList : [ "10", "20", "50", "100" ],
		//search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		onCheck : function() {
			$(".btn-edit,.btn-del").show();
		},
		onUncheck : function() {
			$(".btn-edit,.btn-del").hide();
		},
		columns : [ {
			field : 'id',
			title : '编号'
			//radio : true
		}, {
			field : 'type',
			title : '资金来源',
			align : 'left'
		},{
			field :'costTypeid',
			title :'费用类型',
			align :'left'
		}, {
			field : 'dAmt',
			title : '增加金额',
			align : 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
		}, {
			field : 'cAmt',
			title : '减少金额',
			align : 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
		},{
			field : 'memo',
			title : '备注',
			align : 'left'
		} ]
	});
});

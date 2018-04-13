$(function() {
	var data = [ {
		"id" : "001",
		"org" : "成都益盐堂",
		"custname" : "绵阳批发商",
		"orderid" : "201607110011",
		"orderts" : "2016-07-10",
		"salesrep":"陈少华",
		"amt":"19992.20",
		"states":"待审批"
	},{
		"id" : "002",
		"org" : "成都益盐堂",
		"custname" : "成都批发商",
		"orderid" : "201607110011",
		"orderts" : "2016-07-10",
		"salesrep":"陈少华",
		"amt":"19992.20",
		"states":"待审批"
	},{
		"id" : "003",
		"org" : "成都益盐堂",
		"custname" : "眉山批发商",
		"orderid" : "201607110011",
		"orderts" : "2016-07-10",
		"salesrep":"张三",
		"amt":"19992.20",
		"states":"待审批"
	},{
		"id" : "004",
		"org" : "成都益盐堂",
		"custname" : "遂宁批发商",
		"orderid" : "201607110011",
		"orderts" : "2016-07-10",
		"salesrep":"李四",
		"amt":"19992.20",
		"states":"待审批"
	}   ];
	$('#orderauditTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		// url: 'system/dict/list',
		data : data,
		cache : false,
		//toolbar : "#dictTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "org",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		search : true,
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
			field : 'ck',
			title : '编号',
			radio : true
		}, {
			field : 'id',
			title : '编号',
			visible : false
		}, {
			field : 'org',
			title : '销售组织',
			align : 'left'
		}, {
			field : 'custname',
			title : '客户名称',
			align : 'left'
		}, {
			field : 'orderid',
			title : '订单编号',
			align : 'left'
		}, {
			field : 'orderts',
			title : '下单时间',
			align : 'left'
		}, {
			field : 'salesrep',
			title : '销售人员',
			align : 'left'
		}, {
			field : 'amt',
			title : '订单金额(元)',
			align : 'left'
		}, {
			field : 'states',
			title : '订单状态',
			align : 'left'
		} ]
	});
    
});

function opendata() {
	var rows = $("#orderauditTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		window.location.href="order/auditdetails.html?id=" + rows[0].id;
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}
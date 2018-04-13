$(function() {
	$('#orderTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'order/distributor/distributorEditList?merchid='+$("#merchid").val()
				+"&shipid="+$("#shipid").val()+"&lpno="+$("#lpno").val()
				+"&states="+$("#states").val()+"&isMatched="+$("#isMatched").val(),
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
			field : 'orderid',
			title : '订单编号',
			align : 'left'
		}, 
		{
			field : 'saporderid',
			title : 'SAP订单编号',
			align : 'left'
		}, {
			field : 'merchname',
			title : '终端客户名称',
			align : 'left'
		}, {
			field : 'num',
			title : '数量',
			align : 'left'
		}, {
			field : 'amt',
			title : '金额(元)',
			align : 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
		} ]
	});
	$("#btn-back").bind("click",doBack);
	$("#btn-edit").bind("click",doEdit);
	$("#btn-del").bind("click",doDel);
});

function doBack(){
	window.location.href = "order/distributor/distributorIndex.html";
}

function doEdit(){
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 ) {
		   $.get("order/distributor/orderEditValidate?id="+rows[0].orderid,function(res){
			   if(res.data =="200"){				   
				   window.location.href = "order/distributor/orderedit.html?id="+rows[0].orderid;
			   }else{
				   $.messager.popup("订单状态发生变化，请确认后在修改");
			   }
		   });
	} else {
		$.messager.alert("提示", "请选择编辑状态的记录!");
	}
}
function doDel(){
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 ) {
		$.post("order/distributor/delorder", {"id":rows[0].orderid}, function(res){
			$.messager.popup(res.data.msg);
			$("#orderTable").bootstrapTable("refresh");
		});
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}
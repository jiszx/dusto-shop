$(function() {
	$('#orderTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'order/distributor/distributorDetailList?merchid='+$("#merchid").val()
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
			field : 'materialId',
			title : '物料编码',
			align : 'left'
		}, {
			field : 'sku',
			title : '物料描述',
			align : 'left'
		}, {
			field : 'unit',
			title : '单位',
			align : 'left'
		}, {
			field : 'price',
			title : '单价',
			align : 'left',
			formatter:function(value, row){
				if(row.amounts){
					return amtChange(value, row.amounts, true).toFixed(2);
				}
			}
		}, {
			field : 'num',
			title : '数量',
			align : 'left',
			formatter:function(value, row){
				if(row.amounts){
					return amtChange(value, row.amounts).toFixed(3);
				}
				var rows = $('#order-table').bootstrapTable('getData');
				var total = parseFloat(0);
				$.each(rows, function(i, val){
					if(val.amounts){
						total += parseFloat(amtChange(val.num, val.amounts).toFixed(3));
					}
				});
				return total;
			}
		}, {
			field : 'amt',
			title : '金额(元)',
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
			field : 'delivered',
			title : '预计发货数',
			align : 'left'
		}, {
			field : 'depo',
			title : '当前库存',
			align : 'left'
		} ]
	});
	$("#btn-back").bind("click",doBack);
	$("#makesure").bind('click', makesure);
	$("#reject").bind('click', reject);
});

function reject(){
	var taskId = $("#taskId").val();
	$.post("order/makesureOrder", {"taskId":taskId,"isPass":0}, function(res){
		window.history.back();
	});
}

function makesure(){
	var taskId = $("#taskId").val();
	$.post("order/makesureOrder", {"taskId":taskId,"isPass":1}, function(res){
		window.history.back();
	});
}

function doBack(){
	window.location.href = "order/distributor/distributorIndex.html";
}

function amtChange(price, amounts, isMulti){
	if(isMulti){
		return (parseFloat(price)*parseFloat(amounts));
	}
	return (parseFloat(price)/parseFloat(amounts));
}


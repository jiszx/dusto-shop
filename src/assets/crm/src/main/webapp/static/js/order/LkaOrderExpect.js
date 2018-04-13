$(function() {
	$("#btn-back").bind("click", doBack);
	$('#ar-table').bootstrapTable({
		url : 'account/ARbalance/arOverdueList?sapCustomerId='+$("#sapCustomerId").val(),
		method : 'get',
		classes : "table table-hover table-condensed",
		cache : false,
		//toolbar : "#ARbalanceTool",
		striped: true,
	    pagination: true,
	    searchOnEnterKey:true,
	    sidePagination:"server",
	    idField:"id",
	    sortName:"custname",
	    smartDisplay:true,
	    pageSize: 10,
	    pageList:["10","20","50","100"],
	    showColumns: true,
	    showRefresh: true,
	    clickToSelect: true,
	    singleSelect:true,
		columns : [ {
			field : 'ck',
			title : '',
			radio : true
		}, {
			field : 'id',
			title : '编号',
			visible : false
		}, {
			field : 'orgname',
			title : '所属销售组织',
			align : 'left'
		},{
			field : 'custname',
			title : '客户名称',
			align : 'left'
		},{
			field : 'sapCustomerId',
			title : '客户编码',
			align : 'left'
		},
		{
			field : 'custType',
			title : '客户类型',
			align : 'left',
			formatter:function(value){
            	return getcustTypeValue(value + "");
            }
		},{
			field : 'arPeriod',
			title : '账期(天)',
			align : 'left'
		},
		{
			field : 'aramt',
			title : '应收总额',
			align : 'left',
			formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'overdue1',
			title : '未逾期金额',
			align : 'left',
			formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'overdue2',
			title : '逾期1-30天',
			align : 'left',
			formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'overdue3',
			title : '逾期31-60天',
			align : 'left',
			formatter : function(value, data) {
				return value.formatMoney();
			}
		}, {
			field : 'overdue4',
			title : '逾期61-90天',
			align : 'left',
			formatter : function(value, data) {
				return value.formatMoney();
			}
		}, {
			field : 'overdue5',
			title : '逾期91-120天',
			align : 'left',
			formatter : function(value, data) {
				return value.formatMoney();
			}
		}, {
			field : 'overdue6',
			title : '逾期121-180天',
			align : 'left',
			formatter : function(value, data) {
				return value.formatMoney();
			}
		}, {
			field : 'overdue7',
			title : '逾期超过180天',
			align : 'left',
			formatter : function(value, data) {
				return value.formatMoney();
			}
		}]
	});
	$('#order-table')
			.bootstrapTable(
					{
						url : 'order/orderLineDetails?id='+$("#orderid").val(),
						method : 'get',
						classes : "table table-hover table-condensed",
						// data:data,
						cache : false,
						toolbar : "#upAccountTool",
						striped : true,
						pagination : true,
						searchOnEnterKey : true,
						sidePagination : "server",
						idField : "id",
						sortName : "payName",
						smartDisplay : true,
						pageSize : 10,
						pageList : [ "10", "20", "50", "100" ],
						// search : true,
						showColumns : true,
						showRefresh : true,
						clickToSelect : true,
						singleSelect : true,
						columns : [
								{
									title : '编号',
									field : 'id',
									rowspan : 1,
									align : 'center',
									valign : 'middle',
									visible : false
								},
								{
									title : '类型',
									field : 'type',
									rowspan : 1,
									align : 'center',
									valign : 'middle'
								},
								{
									title : '产品名称',
									field : 'sku',
									rowspan : 1,
									align : 'center',
									valign : 'middle',
									sortable : false,
								},
								{
									title : '物料编码',
									field : 'materialId',
									rowspan : 1,
									align : 'center',
									valign : 'middle',
									sortable : false
								},
								{
									title : '单位',
									field : 'unit',
									rowspan : 1,
									align : 'center',
									valign : 'middle',
									sortable : true,
								},
								{
									title : '单价(元)',
									field : 'orderPrice',
									rowspan : 1,
									align : 'center',
									valign : 'middle',
									sortable : true,
									formatter:function(value, row){
										if(row.amounts){
											return amtChange(value, row.amounts, true).toFixed(2);
										}
										return value;
									}
								},
								{
									title : '下单数量',
									field : 'num',
									rowspan : 1,
									align : 'center',
									valign : 'middle',
									sortable : true,
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
								},
								{
									title : '金额',
									field : 'amt',
									rowspan : 1,
									align : 'center',
									valign : 'middle',
									visible : true
								},
							    {
									title : '已发货数量',
									field : 'deliverynum',
									rowspan : 1,
									align : 'center',
									valign : 'middle',
									sortable : true,
									formatter:function(value, row){
										if(!value){
											return '0';
										}
										if(row.amounts){
											return amtChange(value, row.amounts).toFixed(3);
										}
										var rows = $('#order-table').bootstrapTable('getData');
										var total = parseFloat(0);
										$.each(rows, function(i, val){
											if(val.amounts){
												total += parseFloat(amtChange(val.deliverynum, val.amounts).toFixed(3));
											}
										});
										return total;
									}
								},{
									title : '发货工厂',
									field : 'factoryname',
									rowspan : 1,
									align : 'center',
									valign : 'middle',
									sortable : true
								} ]
					});
})
function doBack() {
	if(orderType=='1'){
		window.location.href = "order/retail/index.html";
	}else{
		window.location.href = "order/index.html";
	}
}

function amtChange(price, amounts, isMulti){
	if(isMulti){
		return (parseFloat(price)*parseFloat(amounts));
	}
	return (parseFloat(price)/parseFloat(amounts));
}
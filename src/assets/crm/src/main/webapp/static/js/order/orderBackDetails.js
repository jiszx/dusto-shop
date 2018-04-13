$(function() {
	$("#btn-back").bind("click", doBack);
	$('#order-table').bootstrapTable(
			{
				url : 'order/orderLineDetails?id='+$("#orderid").val(),
				method : 'get',
				classes : "table table-hover table-condensed",
				//data : linedata,
				cache : false,
				striped : true,
				//pagination : true,
				searchOnEnterKey : true,
				sidePagination : "server",
				idField : "id",
				sortName : "payName",
				smartDisplay : true,
				pageSize : 10,
				pageList : [ "10", "20", "50", "100" ],
				// search : true,
				//showColumns : true,
				//showRefresh : true,
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
							title : '箱内数量',
							field : 'amounts',
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
							formatter : function(value, row) {
								if (row.amounts) {
									return amtChange(value, row.amounts, true)
											.toFixed(4);
								}
								return value;
							}
						},
						{
							title : '退货数量',
							field : 'num',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							sortable : true,
							formatter : function(value, row) {
								if (row.amounts) {
									return amtChange(value, row.amounts)
											.toFixed(3);
								}
								var rows = $('#order-table').bootstrapTable('getData');
								var total = parseFloat(0);
								$.each(rows, function(i, val) {
									if (val.amounts) {
										total += parseFloat(amtChange(val.num,
												val.amounts).toFixed(3));
									}
								});
								return total;
							}
						}, {
							title : '退货金额',
							field : 'amt',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							visible : true
						} ]
			});
	$('#saltOrder-table').bootstrapTable(
			{
				url : 'order/orderLineDetails?id='+$("#saltOrderId").val(),
				method : 'get',
				classes : "table table-hover table-condensed",
				//data : linedata,
				cache : false,
				striped : true,
				//pagination : true,
				searchOnEnterKey : true,
				sidePagination : "server",
				idField : "id",
				sortName : "payName",
				smartDisplay : true,
				pageSize : 10,
				pageList : [ "10", "20", "50", "100" ],
				// search : true,
				//showColumns : true,
				//showRefresh : true,
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
							title : '箱内数量',
							field : 'amounts',
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
							formatter : function(value, row) {
								if (row.amounts) {
									return amtChange(value, row.amounts, true)
											.toFixed(4);
								}
								return value;
							}
						},
						{
							title : '退货数量',
							field : 'num',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							sortable : true,
							formatter : function(value, row) {
								if (row.amounts) {
									return amtChange(value, row.amounts)
											.toFixed(3);
								}
								var rows = $('#order-table').bootstrapTable('getData');
								var total = parseFloat(0);
								$.each(rows, function(i, val) {
									if (val.amounts) {
										total += parseFloat(amtChange(val.num,
												val.amounts).toFixed(3));
									}
								});
								return total;
							}
						}, {
							title : '退货金额',
							field : 'amt',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							visible : true
						} ]
			});
})
function doBack() {
	window.location.href = "orderBack/orderBackList.html";
}

function amtChange(price, amounts, isMulti) {
	if (isMulti) {
		return (parseFloat(price) * parseFloat(amounts));
	}
	return (parseFloat(price) / parseFloat(amounts));
}
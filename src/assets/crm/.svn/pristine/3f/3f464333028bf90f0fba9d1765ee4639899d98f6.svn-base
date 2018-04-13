$(function() {
	$("#type").val(getType($("#type").val()));
	$("#direction").val(getDirection($("#direction").val()));
	$("#btn-back").bind("click", doBack);
	$("#exportExcel").bind("click",doExport);
	$('#product_table').bootstrapTable(
			{
				url : 'customer/sku/batchmaintainDetails?id='+$("#maintenanceId").val(),
				method : 'get',
				classes : "table table-hover table-condensed",
				//data : linedata,
				cache : false,
				toolbar : "#batchMaintainTool",
				striped : true,
				pagination : true,
				searchOnEnterKey : true,
				sidePagination : "server",
				idField : "id",
				sortName : "payName",
				smartDisplay : true,
				pageSize : 10,
				pageList : [ "10", "20", "50", "100" ],
				//search : true,
				//showColumns : true,
				showRefresh : true,
				//clickToSelect : true,
				//singleSelect : true,
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
							title : '客户名称',
							field : 'custname',
							rowspan : 1,
							align : 'center',
							valign : 'middle'
						},
						{
							title : '物料编码',
							field : 'materialId',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							sortable : false,
						},
						{
							title : 'SKU',
							field : 'sku',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							sortable : false,
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
							title : '客户价格',
							field : 'price',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							sortable : true,
							formatter : function(value, row) {
								return value ? (value/100).formatMoney() : value;
							}
						},
						{
							title : '备注',
							field : 'remark',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							sortable : true,
						}
						/*,{
							title : '调整前价格',
							field : 'oldPrice',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							sortable : false,
							formatter : function(value, row) {
								if (row.amounts) {
									return amtChange(value, row.amounts, true)
											.toFixed(4);
								}
								return value;
							}
						},
						{
							title : '调整后价格',
							field : 'newPrice',
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
						} */]
			});
	
})
function doBack() {
	window.location.href = "customer/sku/batchMaintain.html";
}

function amtChange(price, amounts, isMulti) {
	if (isMulti) {
		return (parseFloat(price) * parseFloat(amounts));
	}
	return (parseFloat(price) / parseFloat(amounts));
}
function doExport(){
	window.open("customer/sku/exportDetails?id="+$("#maintenanceId").val()+"&offset=0&limit=650000");
}
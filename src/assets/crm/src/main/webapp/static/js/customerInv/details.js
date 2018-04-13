$(function() {
	$("#btn-back").bind("click", doBack);
	$('#order-table')
			.bootstrapTable(
					{
						url : 'customerInvAllocation/orderLineDetails?id='+$("#orderid").val(),
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
								/*{
									field : 'ck',
									checkbox : true,
									rowspan : 1,
									align : 'center',
									valign : 'middle'
								},*/
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
									valign : 'middle',
									sortable : false,
									formatter:function(value){
										if(value =='1'){
											return '现金';
										}else if(value=='2'){
											return '货补';
										}else if(value=='3'){
											return '销售政策';
										}else if(!value){
											return '合计';
										}
									}
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
									title : '规格',
									field : 'specifications',
									rowspan : 1,
									align : 'center',
									valign : 'middle',
									sortable : false
								},
								{
									title : '箱内数量',
									field : 'amounts',
									rowspan : 1,
									align : 'center',
									valign : 'middle',
									sortable : false
								},
								{
									title : '单价(元)',
									field : 'price',
									rowspan : 1,
									align : 'center',
									valign : 'middle',
									sortable : true,
									formatter:function(value, row){
										if(row.amounts && orderType!='4'){
											return amtChange(value, row.amounts, true).toFixed(4);
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
								},
								{
									title : '金额(元)',
									field : 'amt',
									rowspan : 1,
									align : 'center',
									valign : 'middle',
									visible : true,
									formatter:function(value, row){
										if(value){
											return value.toFixed(2);
										}
										return value;
									}
								},
							    {
									title : '下单重量(吨)',
									field : 'weight',
									rowspan : 1,
									align : 'center',
									valign : 'middle',
									sortable : true
								},{
									title : '已调拨数量',
									field : 'allocationNum',
									rowspan : 1,
									align : 'center',
									valign : 'middle',
									sortable : true
								},{
									title : '已调拨重量',
									field : 'allocationWeight',
									rowspan : 1,
									align : 'center',
									valign : 'middle',
									sortable : true
								}]
					});
})
function doBack() {
	window.location.href = "customerInvAllocation/InvAllocationList.html";
}

function amtChange(price, amounts, isMulti){
	if(isMulti){
		return (parseFloat(price)*parseFloat(amounts));
	}
	return (parseFloat(price)/parseFloat(amounts));
}
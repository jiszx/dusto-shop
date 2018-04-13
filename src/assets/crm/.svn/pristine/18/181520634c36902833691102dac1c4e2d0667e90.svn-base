var $table = $('#price-table');
$(function() {
	$table.bootstrapTable({
		url : 'customer/sku/saltPrice',
		pagination : true,
		toolbar: "#dictTool",
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		clickToSelect : true,
		singleSelect : true,
		showExport : true,
		exportDataType : 'all', 
		exportTypes : [ 'csv', 'txt', 'excel' ],
		cache: false,
		columns : [
				{
					field : 'state',
					radio : true,
					rowspan : 1,
					align : 'center',
					valign : 'middle'
				},
				{
					title : 'ID',
					field : 'merchCustId',
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
					valign : 'middle',
				},
				{
					title : '客户SAP编号',
					field : 'sapCustomerId',
					rowspan : 1,
					align : 'center',
					valign : 'middle',
				},
				{
					title : '所属销售组织',
					field : 'orgname',
					rowspan : 1,
					align : 'center',
					valign : 'middle',
				},
				{
					title : '调整价格',
					field : 'hPrice',
					rowspan : 1,
					align : 'center',
					valign : 'middle',
					editable : {
						type : 'text',
						title : '修改调整价格:',
						validate : function(value) {
							value = $.trim(value);
							if (!value) {
								return '请输入正确的数值';
							}
							//^-?([1-9]\d*|0)(?:\.\d{1,4})?$
							if (!/^0$|(^(-?)(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,4})?$)/.test(value)) {
								return '价格只能保留4位小数'
							}
							var data = $table.bootstrapTable('getData'), index = $(
									this).parents('tr').data('index');
							var rows = $table.bootstrapTable('getSelections');
							editSkuPrice(rows[0].merchCustId, value);
							return '';
						}
					},
					formatter : function(value) {
						if(!value){
							return '0';
						}
						var price = parseFloat(value);
						price /= 100;
						return price.formatPrice();
					}
				},
				]
	});
	$("#searchButton").bind('click', search);
});

function search() {
	var custname = $("#selectcust").val();
	var url = 'customer/sku/saltPrice?custname=' + custname;
	$("#price-table").bootstrapTable("refresh", {
		'url' : url
	});
}
function editSkuPrice(id,price){
	$.post('customer/sku/editsaltPrice',{'id':id, 'price':price}, function(res){
		if(res.errorCode!=0){
			$.messager.alert('错误','更改失败');
		}
	});
}


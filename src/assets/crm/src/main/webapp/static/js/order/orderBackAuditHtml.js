var $table = $('#order-table');
$(function() {
	$table.bootstrapTable({
		url : 'orderBack/auditlinedata?headerid=' + $("#orderid").val(),
		method : 'get',
		classes : "table table-hover table-condensed",
		cache : false,
		//toolbar : "#upAccountTool",
		striped : true,
		pagination : true,
		sidePagination : "server",
		idField : "id",
		sortName : "payName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [{
				title : '编号',
				field : 'id',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				visible : false
			}, {
				title : '产品名称',
				field : 'sku',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : false,
			}, {
				title : '物料编码',
				field : 'materialId',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : false
			}, {
				title : '单位',
				field : 'unit',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true,
			}, {
				title : '规格',
				field : 'specifications',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true,
			}, {
				title : '箱内数量',
				field : 'amounts',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true,
			}, {
				title : '单价(元)',
				field : 'orderPrice',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true,
				editable : {
					type : 'text',
					title : '修改单价:',
					validate : function(value) {
						value = $.trim(value);
						if (!value) {
							return '请输入正确的数值';
						}
						if (!/^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,4})?$)/.test(value)) {
							return '请输入大于0的4位小数'
						}
						var data = $table.bootstrapTable('getData');
						index = $(this).parents('tr').data('index');
						var hbamt = amtChange(value, data[index].amounts, true).toFixed(4)*data[index].hbNum;
						var orderAmt = amtChange(value, data[index].amounts, true).toFixed(4)*data[index].num;
						$table.bootstrapTable('updateCell', {
							index : index,
							field : "hbAmt",
							value :hbamt
						});
						$table.bootstrapTable('updateCell', {
							index : index,
							field : "orderAmt",
							value :orderAmt
						});
						editPrice(data[index].id,((value/data[index].amounts)*100).toFixed(4));
						return '';
					}
				},
				formatter : function(value, row) {
					if(value==0){
						return '-';
					}else if(! value){
						return '-';
					}
					if(row.amounts){
						return amtChange(value, row.amounts, true).toFixed(4);
					}
				}
			}, {
				title : '标准退货数量',
				field : 'num',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true
			},{
				title : '货补退货数量',
				field : 'hbNum',
				rowspan : 1,
				align : 'center',
				valign : 'middle'
			}, {
				title : '下单退货金额',
				field : 'orderAmt',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true,
				formatter : function(value, row) {
					if(value==0){
						return '-';
					}else if(! value){
						return '-';
					}
					return value.toFixed(2);
				}
			}, {
				title : '货补退货金额',
				field : 'hbAmt',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				formatter : function(value, row) {
					if(value==0){
						return '-';
					}else if(! value){
						return '-';
					}
					return value.toFixed(2);
				}
			}, {
				title : '总额',
				field : 'amt',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				formatter : function(value, row) {
					if(value==0){
						return '-';
					}else if(! value){
						return '-';
					}
					return value.toFixed(2);
				}
			} ] 
	});
})

function amtChange(price, amounts, isMulti) {
	if (isMulti) {
		return (parseFloat(price) * parseFloat(amounts));
	}
	return (parseFloat(price) / parseFloat(amounts));
}
function editPrice(id, price){
	$.post('orderBack/editAllocatePrice.json',{'id':id, 'price':price}, function(res){
		if(res.errorCode!=0){
			$.messager.alert('错误','更改失败');
		}else{
			$table.bootstrapTable('refresh',{
				'url':'orderBack/auditlinedata?headerid=' + $("#orderid").val()
			});
		}
	});
}


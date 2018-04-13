$(function() {
	$('.tokenfield').tokenfield();
	$('#policysku-table').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'salePolicy/policyLines.json?id='+$("#policyid").val(),
		// data:data,
		cache : false,
		//toolbar : "#dictTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		//sortName : "colName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [  {
			title : '编号',
			field : 'id',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '核销方式',
			field : 'verification',
			rowspan : 1,
			align : 'center',
			valign : 'middle'
		}, {
			title : '订单SKU',
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
			valign : 'middle'
		}, {
			title : '单位',
			field : 'unit',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
		}, {
			title : '单价(元)',
			field : 'price',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true

		}, {
			title : '目标',
			field : 'primary',
			rowspan : 1,
			align : 'center',
			valign : 'middle'
		}, {
			title : '政策奖励bi',
			field : 'discount',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '政策奖励',
			field : 'discountsku',
			rowspan : 1,
			align : 'center',
			valign : 'middle'
		}, {
			title : '政策奖励力度',
			field : 'discountIntensity',
			rowspan : 1,
			align : 'center',
			valign : 'middle'
		}, {
			title : '政策奖励限制',
			field : 'limit',
			rowspan : 1,
			align : 'center',
			valign : 'middle'
		} ]
	});
	$("#btn-back").bind("click", doBack);
	initpolicyarea();
	if($("#verification").val()=='1'|| $("#verification").val()=='5'){
		$('#policysku-table').bootstrapTable('showColumn', 'primary');
		$('#policysku-table').bootstrapTable('showColumn', 'discountsku');
		$('#policysku-table').bootstrapTable('showColumn', 'limit');
	}else{
		$('#policysku-table').bootstrapTable('hideColumn', 'primary');
		$('#policysku-table').bootstrapTable('hideColumn', 'discountsku');
		$('#policysku-table').bootstrapTable('hideColumn', 'limit');
	}
});
function doBack() {
	window.location.href = "salePolicy/index.html";
}

function initpolicyarea(){
	var tokenArray = new Array();
	//获取组织数据
	var areaurl="salePolicy/edit/policyarea?id="+$("#policyid").val();
	$.get(areaurl,function(data){
		if(data.rows && data.rows.length>0){
			for(var i=0;i<data.rows.length;i++){
				tokenArray.push({ value: data.rows[i].id, label: data.rows[i].name })
			}
			$('#agentArea').tokenfield('setTokens',tokenArray);
		}
	})
}

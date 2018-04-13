$(function() {
	$('#vwTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'vw/list',
		cache : false,
		toolbar : "#vwTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		search : false,
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
			title : '',
			radio : true
		}, {
			field : 'factoryCode',
			title : '工厂',
			align : 'left',
			formatter : function(value) {
				var text = $('#factoryCode').find('option[value="'+value+'"]').text();
				return text;
			}
		}, {
			field : 'materialName',
			title : '产品名称',
			align : 'left',
			formatter : function(value) {
				return value;
			}
		}, {
			field : 'materialId',
			title : '产品编码',
			align : 'left'
		}, {
			field : 'custType',
			title : '仓库名称',
			align : 'left',
			formatter : function(value) {
				var text = $('#custType').find('option[value="'+value+'"]').text();
				return text;
			}
		}, {
			field : 'amt',
			title : '库存数量',
			align : 'left'
		}, {
			field : 'updateTs',
			title : '更新时间',
			align : 'left'
		} ]
	});
	$("#btnSearch").bind("click", doSearch);
	$("#exportBtn").bind('click', exportExcel);
});

/**
 * 查询
 */
function doSearch() {
	/*var params = $("#searchForm").serializeArray();
	var param = {};
	for ( var i in params) {
		var prop = params[i].name;
		var val = $.trim(params[i].value);
		param[prop] = val;
	}*/
	$("#vwTable").bootstrapTable("refresh",
		{
		'url' : 'vw/list?'+$("#searchForm").serialize()
		}
	);
}

function exportExcel(){
	$("#generateFile").html("正在生成excel，请耐心等待...");
	$("#exportModal").modal();
	$.ajax({
		url:'vw/export.json',
		timeout:300000,
		error:function(){
		},
		success:function(res, textStatus, XMLHttpRequest){
		    if(res.data.length<2){
		    	$.messager.alert("提示","数据太多，无法导出");
		    }else{
		    	$("#generateFile").html('<a href="'+window.IMAGE_BASEURI+res.data+'" target="_blank">生成成功，点击下载</a>')
		    }
		} 
	});
}
$(function() {
	$('#invTable').bootstrapTable({
		url : 'customerInvSearch/invList',
		method : 'get',
		classes : "table table-hover table-condensed",
		cache : false,
		//toolbar : "#upAccountTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
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
		columns : [  {
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
		},{
			field : 'sku',
			title : '物料',
			align : 'left'
		},
		{
			field : 'materialId',
			title : '物料编码',
			align : 'left'
		}, {
			field : 'rdcname',
			title : '虚拟仓',
			align : 'left'
		},{
			field : 'specifications',
			title : '规格',
			align : 'left'
		}, {
			field : 'amounts',
			title : '箱内数量',
			align : 'left'
		}, {
			field : 'invNum',
			title : '库存数量(包/袋/瓶)',
			align : 'left'
			/*formatter:function(value, row){
				if(row.amounts){
					return amtChange(value, row.amounts).toFixed(3);
				}
			}*/
		}, {
			field : 'invNum',
			title : '库存数量(箱/吨)',
			align : 'left',
			formatter:function(value, row){
				if(row.amounts){
					return amtChange(value, row.amounts).toFixed(3);
				}
			}
		}, {
			field : 'frozenNum',
			title : '冻结数量(包/袋/瓶)',
			align : 'left'
		},{
			field : 'frozenNum',
			title : '冻结数量(箱/吨)',
			align : 'left',
			formatter:function(value, row){
				if(row.amounts){
					return amtChange(value, row.amounts).toFixed(3);
				}
			}
		} ]
	});
	//搜索
	$("#btn-search").click(function(){
		$('#invTable').bootstrapTable('refresh',{
			'url':"customerInvSearch/invList?custname="+$("#custname").val()+"&sku="+$("#sku").val()+
			"&materialId="+$("#materialId").val()+"&organizationId="+$("#organizationId").val()
			+"&rdcCode="+$("#rdcCode").val()
		});
	})
	
	$("#btn-update").click(function(){
		$.post("customerInvSearch/updateInv",function(res){
			if(res.data=='S'){
				$('#invTable').bootstrapTable('refresh');
			}else{
				$.messager.popup("更新出错");
			}
		})
	});
	
	$("#exportBtn").click(exportExcel);
});

function amtChange(price, amounts, isMulti){
	if(isMulti){
		return (parseFloat(price)*parseFloat(amounts));
	}
	return (parseFloat(price)/parseFloat(amounts));
}

function exportExcel(){
	$("#generateFile").html("正在生成excel，请耐心等待...");
	$("#exportModal").modal();
	$.ajax({
		url:'customerInvSearch/export.json',
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

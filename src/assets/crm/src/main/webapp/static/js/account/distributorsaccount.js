$(function() {
	$('#distributorsTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'account/distributors/list',
		// data:data,
		cache : false,
		toolbar : "#distributorsTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "merchCustId",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		// search: true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		onCheck:function(row){
            if(row.allocationAmt >0){
            	$("#btn-allocation-details").removeClass("hide");
            }else{
            	$("#btn-allocation-details").addClass("hide");
            }
        },
        onUncheck:function(){
        	$("#btn-allocation-details").addClass("hide");
        },
		columns : [ {
			field : 'ck',
			title : '',
			radio : true
		}, {
			field : 'sapCustomerId',
			title : 'sap客户编号',
			align : 'left'
		}, {
			field : 'custname',
			title : '客户名称',
			align : 'left'
		}, {
			field : 'custType',
			title : '客户类型',
			align : 'left',
			formatter :function(value){
				return getcustType(value + "");
			}
		}, {
			field : 'orgname',
			title : '销售组织',
			align : 'left'
		}, {
			field : 'createTs',
			title : '录入时间',
			align : 'left'
		}, {
			field : 'payType',
			title : '打款类型',
			align : 'left',
			formatter : function(value) {
				return getUpamtType(value + "");
			}
		}, {
			field : 'payAmt',
			title : '打款金额',
			align : 'left',
			formatter:function(value){
            	return value.formatMoney();
            }
		},{
			field : 'bankSerial',
			title : '流水号',
			align : 'left'
		}, {
			field : 'unreceviceAmt',
			title : '未核销金额',
			align : 'left',
			formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'allocationAmt',
			title : '未分配金额',
			align : 'left',
			formatter : function(value,data){
				return  (data.payAmt-value).formatMoney();
			}
		}, {
			field : 'states',
			title : '状态',
			align : 'left',
			formatter : function(value) {
				if(value =='3'){
					return "已确认";
				}else{
					return getUpStatesValue(value + "");
				}
			}
		}]
	});
	$("#allocationDetailTable").bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		//url : 'account/distributors/list',
		cache : false,
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "merchCustId",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		search: true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : 'ck',
			title : '',
			radio : true
		}, {
			field : 'sapCustomerId',
			title : 'sap客户编号',
			align : 'left'
		}, {
			field : 'merchName',
			title : '零售商名称',
			align : 'left'
		}, {
			field : 'allocationAmt',
			title : '金额',
			align : 'left',
			formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'allocationDate',
			title : '分配时间',
			align : 'left'
		}, {
			field : 'operator',
			title : '操作人',
			align : 'left'
		}, {
			field : 'sapVouderId',
			title : 'SAP凭证编号',
			align : 'left'
		}]
	});
	
	$("#btn-search").bind('click', doSearch);
	$("#btn-allocation-details").bind("click",doDetails);
	$("#exportBtn").bind("click", doExport);
});

function doSearch() {
	var custname = $("#custname").val();
	var orgid = $("#orgid").val();
	var sbankSerial =$("#sbankSerial").val();
	var url = 'account/distributors/list?custname=' + custname + "&orgid=" + orgid+"&bankSerial="+sbankSerial;
	$('#distributorsTable').bootstrapTable('refresh', {
		'url' : url
	});
}

function doDetails(){
	var rows = $("#distributorsTable").bootstrapTable("getSelections");
	if(!rows || rows.length<1){
		$.messager.popup("请选择记录");
		return;
	}
	var upaccountId = rows[0].id;
	$("#allocationDetailTable").bootstrapTable("refresh",{
		'url':"account/allocation/allocationDetail?upaccountId="+upaccountId
	});
	$("#allocationDetailModal").modal("show");
}

function doExport(){
	$("#generateFile").html("正在生成excel，请耐心等待...");
	$("#exportModal").modal();
	$.ajax({
		url:'monitor/export/generate.json?key=replacePayment',
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
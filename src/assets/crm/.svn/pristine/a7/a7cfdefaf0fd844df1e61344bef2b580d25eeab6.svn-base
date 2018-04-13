$(function() {
	$('#custRebateTable').bootstrapTable({
		url : 'account/rebate/list?states=1',
		method : 'get',
		classes : "table table-hover table-condensed",
		// data:data,
		cache : false,
		toolbar : "#CustRebateTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "custname",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		//search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : 'uk',
			title : '编号',
			radio : true
		}, {
			field : 'id',
			title : '编号',
			visible : false
		}, {
			field : 'custname',
			title : '客户名称',
			align : 'left'
		},{
			field : 'sapCustomerId',
			title : 'SAP客户编码',
			align : 'left'
		}, {
			field : 'orgname',
			title : '销售组织',
			align : 'left'
		}, {
			field : 'contractid',
			title : '客户合同ID',
			align : 'left'
		}, {
			field : 'rebatetype',
			title : '代理类型',
			align : 'left',
			formatter : function(value) {
				return getrebateTypeValue(value + "");
			}
		}, {
			field : 'description',
			title : '代理描述',
			align : 'left'
		}, {
			field : 'type',
			title : '年/季',
			align : 'left'
		}, {
			field : 'amt',
			title : '进货目标(元)',
			align : 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'ratio',
			title : '返利比例',
			align : 'left'
		}, {
			field : 'purchaseAmt',
			title : '进货金额(元)',
			align : 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'rebateAmt',
			title : '返利金额(元)',
			align : 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'createTs',
			title : '创建时间',
			align : 'left'
		}, {
			field : 'oid',
			title : '审批人',
			align : 'left'
		}, {
			field : 'states',
			title : '状态',
			align : 'left'
		} ]
	});
	//$("#btn-detail").bind("click", submitUpaccount);
	$("#btn-search").bind("click", doSearch);
	$("#rebateDetails").bind("click",doDetails);
	$("#exportBtn").bind("click", doExport);
});

/**
 * 查询
 */
function doSearch() {
	var custname = $("#custname").val();
	var spayBankNo = $("#spayBankNo").val();
	var spayCity = $("#spayCity").val();
	var sorgid = $("#sorgid").val();
	var spayType = $("#spayType").val();
	var url = "account/upaccount/list?states=1&organizationId=" + sorgid
			+ "&payType=" + spayType + "&payCity=" + spayCity + "&payBankNo="
			+ spayBankNo + "&custname=" + custname;
	$("#custRebateTable").bootstrapTable('refresh', {
		'url' : url
	});
}

function doDetails(){
	var rows = $("#custRebateTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		window.location.href="account/rebate/rebateDetails.html?id="+rows[0].id;
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}

function doExport(){
	$("#generateFile").html("正在生成excel，请耐心等待...");
	$("#exportModal").modal();
	$.ajax({
		url:'monitor/export/generate.json?key=contractRebate',
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
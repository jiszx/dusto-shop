$(function() {
	$('#orderBackTable').bootstrapTable({
		url : 'orderBack/list',
		method : 'get',
		classes : "table table-hover table-condensed",
		// data:data,
		cache : false,
		toolbar : "#orderBackTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "orgname",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : 'ck',
			title : '编号',
			radio : true
		}, {
			field : 'orgname',
			title : '所属销售组织',
			align : 'left'
		}, {
			field : 'merchCustId',
			title : '客户ID',
			align : 'left',
			visible : false
		}, {
			field : 'custname',
			title : '客户名称',
			align : 'left'
		}, {
			field : 'custType',
			title : '客户类型',
			align : 'left',
			formatter:function(value){
				return getCustType(value + "");
			}
		}, {
			field : 'sapCustomerId',
			title : 'SAP客户编号',
			align : 'left'
		}, {
			field : 'id',
			title : 'CRM退货订单编号'
		}, {
			field : 'oldOrderId',
			title : 'CRM原订单编号'
		}, {
			field : 'sapOrderId',
			title : 'SAP订单编号'
		}, {
			field : 'backNum',
			title : '退货数量',
			align : 'left'
		}, {
			field : 'backAmt',
			title : '退货金额',
			align : 'left'
		}, {
			field : 'backReason',
			title : '退货原因',
			align : 'left',
			formatter:function(value){
				return getBackReason(value + "");
			}
		}, {
			field : 'creater',
			title : '创建人',
			align : 'left'
		}, {
			field : 'createTs',
			title : '创建时间',
			align : 'left'
		},{
			field : 'states',
			title : '状态',
			align : 'left',
			formatter : function(value) {
				return getStatesValue(value + "");
			}
		} ]
	});
	$("#back_add").click(function(){
		window.location.href="orderBack/addOrderBack.html";
	});
	$(".btn-del").click(function(){
		var rows = $("#orderBackTable").bootstrapTable("getSelections");
		if (rows && rows.length == 1 && (rows[0].states=='1' || rows[0].states=='4')) {
			$.post("order/delorder", {"id":rows[0].id}, function(res){
				if(res.errorCode !=0){
					$.messager.alert(res.errorMessage);
				}else{
					$.messager.popup("删除成功");
				}
				$("#orderBackTable").bootstrapTable("refresh");
			});
		} else {
			$.messager.popup("提示", "请选择出编辑或者审批拨号状态记录!");
		}
	})
	$(".btn-detail").click(function(){
		var rows = $("#orderBackTable").bootstrapTable("getSelections");
		if (rows && rows.length == 1) {
			window.location.href = "orderBack/orderBackDetails.html?id="+rows[0].id;
		} else {
			$.messager.alert("提示", "请选择要记录!");
		}
	});
	$(".btn-edit").bind("click",doEdit);
	$("#btn-audit").bind("click",doAudit);
});


/**
 * 查询
 */
function doSearch(){
	var custname =$("#custname").val();
	var sorgid =$("#sorgid").val();
	var states =$("#states").val();
	var url="orderBack/list?states="+states+"&orgid="+sorgid+"&custname="+custname;
	$("#orderBackTable").bootstrapTable('refresh',{
		'url':url
	});
}
//提交审批
function doAudit(){
	var rows = $("#orderBackTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && (rows[0].states=='1'||rows[0].states=='4')) {
		$("#tbn-audit").attr("disabled","true");
		$.messager.confirm("提示", "请确定是否要提交审批？", function() {
			$.post("orderBack/doAudit",{"id":rows[0].id,"states":"2"},function(res){
				if(res.data !='S'){
					$.messager.alert("错误提示", res.data);
				}
				$("#tbn-audit").removeAttr("disabled");
				$("#orderBackTable").bootstrapTable("refresh");
			});
		});
	} else {
		$.messager.alert("提示", "请选择编辑或审批驳回的记录!");
	}
}

function doEdit() {
	var rows = $("#orderBackTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && (rows[0].states=='1'||rows[0].states=='4')) {
		window.location.href = "orderBack/orderBackEdit.html?id="+rows[0].id;
	} else {
		$.messager.alert("提示", "请选择编辑或审批驳回的记录!");
	}
}
function doDel() {
	var rows = $("#orderBackTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1&& rows[0].states=='1') {
			$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
				$.post("orderBack/del", {
					"id" : rows[0].id
				}, function(data) {
					if(data.data=="200"){
						$.messager.popup("删除成功!");
						$("#orderBackTable").bootstrapTable("refresh");						
					}else{
						$.messager.alert("提示", "删除失败!");
					}
				})
			});
	} else {
		$.messager.alert("提示", "请选择要编辑状态的记录!");
	}
}

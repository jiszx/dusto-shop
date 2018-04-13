var type = 0;
$(function() {
	$('#orderTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'report/order/retail/invoice',
		// data : data,
		cache : false,
		toolbar : "#ordersearchTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "org",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		//search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
//		onCheck : function() {
//			$(".btn-edit,.btn-del").show();
//		},
//		onUncheck : function() {
//			$(".btn-edit,.btn-del").hide();
//		},
		columns : [ {
			field : 'ck',
			title : '编号',
			radio : true
		}, {
			field : 'headerId',
			title : '所属订单',
			align : 'left'
		}, {
			field : 'orgName',
			title : '销售组织',
			align : 'left'
		},{
			field : 'merchName',
			title : '客户名称',
			align : 'left'
		}, {
			field : 'sku',
			title : '产品',
			align : 'left'
		}, {
			field : 'createTime',
			title : '下单时间',
			align : 'left'
		}, {
			field : 'amt',
			title : '订单金额(元)',
			align : 'left'
		}, {
			field : 'tax',
			title : '税额(元)',
			align : 'left'
		}
		]
	});
	/*$("#orderStates").on("click", "li", function() {
		alert("111");
		type = $(this).val();
		doSearch();
	});*/
	/*$("#orderStates li").click(function(){
		//alert("111");
		type = $(this).val();
		doSearch();
	})*/
	$("#btn-search").bind("click", doSearch);
	$("#btn-detail").bind("click", doDetail);
	$("#btn-edit").bind("click", doEdit);
	$("#btn-del").bind("click", doDel);
	$("#tbn-audit").bind("click", doAudit);
	$("#btn-saveagain").bind("click", doSaveAgain);
	$("#markBtn").bind("click", markInvoice);
	$("#exportBtn").bind("click", doExport);
});
$(document).ready(function() {
	$("#startTime").datetimepicker({
		format : 'yyyy-mm-dd',
		minView : 2,
		language : 'zh-CN',
		autoclose:true
	});
	$("#endTime").datetimepicker({
		format : 'yyyy-mm-dd',
		minView : 2,
		language : 'zh-CN',
		autoclose:true
	});
});
/**
 * 查看接口信息并重新推送
 * @param o
 * @param msg
 */
function viewFeedback(o,msg){
	if($("#orderTable").data("sendSap"+o) == '1'){
		$.messager.alert("提示", "正在重新推送，请勿频繁点击!");
		return;
	}
	$.messager.confirm("信息", "接口信息："+msg+"<br/>是否需要重新发送SAP?", function() {
		$("#orderTable").data("sendSap"+o,1);
        $.post("order/manualSend",{"id":$.trim(o)},function(result){
        	if(result.errorCode != '-1' && $.trim(result.data) == 'S'){
        		$.messager.popup("重新发送成功！");
        	}else{
        		$.messager.alert("提示", "重新推送SAP错误！");
        	}
        	$("#orderTable").bootstrapTable("refresh");
        	$("#orderTable").data("sendSap"+o,0);
    	})
    });
}

/**
 * 查询
 */
function doSearch() {
	var customer = $("#custname").val();
	//customer = encodeURI(customer);
	//customer = encodeURI(customer);
	var bdate = $("#startTime").val();
	var edate = $("#endTime").val();
	var crmorderid =$("#crmorderid").val();
	var saporderid =$("#saporderid").val();
	var shipname =$("#shipname").val();
	var saler =$("#saler").val();
	var type =$("#orderState").val();
	var url = "order/orderlist?states=" + type + "&custname=" + customer+ "&bdate=" + bdate 
			+ "&edate=" + edate+"&crmorderid="+crmorderid+"&saporderid="+saporderid+
			"&shipname="+shipname+"&saler="+saler;
	$("#orderTable").bootstrapTable('refresh', {
		'url' : url
	});
	return false;
}
/**
 * 查看订单详情
 */
function doDetail() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		window.location.href = "order/orderdetails.html?id="+rows[0].headerId;
	} else {
		$.messager.alert("提示", "请选择要记录!");
	}
}


function doEdit() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && (rows[0].states=='1'|| rows[0].states=='4')) {
		if(rows[0].orderType=="4"){
			window.location.href = "order/specialorderEdit.html?id="+rows[0].orderHeaderId;
		}else if(rows[0].orderType=="0"){			
			window.location.href = "order/orderedit.html?id="+rows[0].orderHeaderId;
		}else if(rows[0].orderType=="7"){
			$.messager.popup("调拨单转化订单不允许修改");
		}
	} else {
		$.messager.alert("提示", "请选择编辑状态的记录!");
	}
}
function doDel() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && (rows[0].states=='1' || rows[0].states=='4')) {
		$.post("order/delorder", {"id":rows[0].orderHeaderId}, function(res){
			/*if(res.data=="200"){
				$.messager.popup("删除成功");
			}else{
				$.messager.alert("提示","删除失败");
			}*/
			if(res.errorCode !=0){
				$.messager.alert(res.errorMessage);
			}else{
				$.messager.popup("删除成功");
			}
			$("#orderTable").bootstrapTable("refresh");
		});
	} else {
		$.messager.alert("提示", "请选择编辑状态记录!");
	}
}
function doAudit() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && (rows[0].states=='1' || rows[0].states=='4')) {
		//验证金额
		$.post("order/saveAginValidateAmt?id="+rows[0].orderHeaderId,function(res){
			if(res.data.type=="200" || rows[0].orderType=='7'){
				$("#tbn-audit").attr("disabled","true");
				$.post("order/orderAudit", {"headerid":rows[0].orderHeaderId,"states":"2","orderType":rows[0].orderType}, function(res){
					if(res.data=="200"){
						$.messager.popup("提交审批成功");
					}else{
						$.messager.alert("提示","提交审批失败");
					}
					$("#orderTable").removeAttr("disabled");
					$("#tbn-audit").attr("disabled","true");
				});
			}else{
				$.messager.popup(res.data.msg);
			}
		})
	} else {
		$.messager.alert("提示", "请选择编辑状态记录!");
	}
}
function doSaveAgain() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		$.post("order/saveAginValidateAmt?id="+rows[0].orderHeaderId,function(res){
			if(res.data.type =="200"){
				$.messager.confirm("提示", "再次购买,当前用户如果不存在岗位会默认当前订单所在岗位" +
						"为新订单岗位，直接生成新订单数据，并跳转到新订单的修改页面", function() {
					window.location.href = "order/orderSaveAgain.html?id="+rows[0].orderHeaderId;
				});
			}else{
				$.messager.popup(res.data.msg);
			}
		})
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}

function markInvoice(){
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		if(rows[0].states=='1'){
			$.messager.popup("状态不允许");
			return;
		}
		$.post("order/markInvoice?id="+rows[0].orderHeaderId,function(res){
			if(res.data ==1){
				$.messager.popup("标记成功");
			}else{
				$.messager.popup("标记失败");
			}
		});
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}

function doExport(){
	$("#generateFile").html("正在生成excel，请耐心等待...");
	$("#exportModal").modal();
	$.ajax({
		url:'report/order/retail/generate',
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
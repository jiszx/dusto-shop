var type = 0;
$(function() {
	$('#orderTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'order/orderlist?orderType=6&batch='+batchNum,
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
		columns : [ {
			field : 'ck',
			title : '编号',
			radio:true
		}, {
			field : 'batchnum',
			title : '批次号',
			align : 'left',
			visible : false
		}, {
			field : 'orgname',
			title : '销售组织',
			align : 'left'
		}, {
			field : 'merchname',
			title : '客户名称',
			align : 'left'
		}, {
			field : 'shipname',
			title : '所属仓储商',
			align : 'left'
		}, {
			field : 'shipType',
			title : '仓储商类型',
			align : 'left',
			formatter : function(value) {
				if(!value){
					return "-";
				}
				if('7'==value){
					return '仓储服务商';
				}
				if('70'==value){
					return '合作仓储服务商';
				}
				if('8'==value){
					return '物流商';
				}
				return '其他';
			}
		}, {
			field : 'sapCustomerId',
			title : 'SAP客户编码',
			align : 'left'
		}, {
			field : 'orderHeaderId',
			title : 'CRM订单编号',
			align : 'left'
		}, {
			field : 'saporderid',
			title : 'SAP订单编号',
			align : 'left',
            formatter : function(value,data) {
            	var containerId = "container"+data.orderHeaderId;
            	var container = $('<div>').attr("id",containerId);
            	if(typeof value == 'undefined' || $.trim(value) == ''){
            		$.ajax({  
            	          type : "get",  
            	          url : "order/feedback?orderId="+data.orderHeaderId,  
            	          success : function(result){
            	        	if(typeof result.data != 'undefined' && $.trim(result.data) != ''){
            	        		var alink = '<a href="javascript:void(0);" onclick="viewFeedback(\''+data.orderHeaderId+'\',\''+result.data+'\');">有接口消息</a>';
            	        		container.html(alink);
            	        		if($('#'+containerId)[0]){
            	        			$('#'+containerId).html(alink);
            	        		}else{
            	        			return container.prop("outerHTML");
            	        		}
                  			}
            	          }  
            	    });
            		return container.prop("outerHTML");
            	}else{
            		return value;
            	}
			}
		}, {
			field : 'createTs',
			title : '下单时间',
			align : 'left'
		}, {
			field : 'salesman',
			title : '销售人员',
			align : 'left'
		}, {
			field : 'orderAmt',
			title : '订单金额(元)',
			align : 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
		}, {
			field : 'sendAmt',
			title : '已发货金额(元)',
			align : 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
		}, {
			field : 'states',
			title : '订单状态',
			align : 'left',
			formatter : function(value) {
				return getorderStates(value + "");
			}
		} ]
	});
	/*$("#orderStates").on("click", "li", function() {
		alert("111");
		type = $(this).val();
		doSearch();
	});*/
	$("#orderStates li").click(function(){
		//alert("111");
		type = $(this).val();
		doSearch();
	})
	$("#btn-search").bind("click", doSearch);
	$("#btn-detail").bind("click", doDetail);
	$("#btn-edit").bind("click", doEdit);
	$("#btn-del").bind("click", doDel);
	$("#generateBatchBtn").bind("click", censor);
	$("#importBtn").bind('click', showImport);
	$("#cancelOrder").bind('click', cancelOrder);
	$("#exportBtn").bind("click", doExport);
	$("#exportBtn2").bind("click", doExport2);
	
	$("#importForm").ajaxForm({
        target:'#btn-add',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit : function(formData, jqForm, options) {
			return initValidate("#importForm").form();
		},
        success:function(responseText, statusText, xhr, $form){
        	if(responseText.errorCode != 0){
                $.messager.alert("导入失败",responseText.errorMessage);
            }else{
            	$.messager.alert("导入成功","请记录并确认文件的md5码："+responseText.data);
            	$("#importModal").modal("hide");
                $("#orderTable").bootstrapTable("refresh");
            }
        }
    });
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
	var url = constructUrl();
	$("#orderTable").bootstrapTable('refresh', {
		'url' : url
	});
}
function constructUrl(){
	var customer = $("#scustomer").val();
	//customer = encodeURI(customer);
	//customer = encodeURI(customer);
	var bdate = $("#startTime").val();
	var edate = $("#endTime").val();
	var crmorderid =$("#crmorderid").val();
	var saporderid =$("#saporderid").val();
	var url = "order/orderlist?orderType=6&batch="+batchNum+"&states=" + type + "&custname=" + customer+ "&bdate=" + bdate 
			+ "&edate=" + edate+"&crmorderid="+crmorderid+"&saporderid="+saporderid;
	return url;
}

/**
 * 查看订单详情
 */
function doDetail() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		window.location.href = "order/orderdetails.html?id="+rows[0].orderHeaderId;
	} else {
		$.messager.alert("提示", "请选择一条记录!");
	}
}


function doEdit() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && (rows[0].states=='1'|| rows[0].states=='4')) {
		window.location.href = "order/distributor/orderedit.html?id="+rows[0].orderHeaderId;
	} else {
		$.messager.alert("提示", "请选择编辑状态的一条记录!");
	}
}
function doDel() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if(!rows || rows.length<1){
		$.messager.alert("提示", "请选择记录!");
		return;
	}
	var isPass = true;
	var ids = [];
	$.each(rows, function(i, val){
		if(val.states!='1'){
			isPass = false;
			return false;
		}
		ids.push(val.orderHeaderId);
	});
	if(!isPass){
		$.messager.alert("提示", "所有记录必须为编辑状态!");
		return;
	}
	$.messager.confirm("警告", "您确认要删除记录吗?", function() {
		$.post("order/delorder", {"id":ids.join()}, function(res){
			if(res.errorCode !=0){
				$.messager.alert(res.errorMessage);
			}else{
				$.messager.popup("删除成功");
			}
			$("#orderTable").bootstrapTable("refresh");
		});
	});
	
}

function censor() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && (rows[0].states=='1' || rows[0].states=='4')) {
		$("#generateBatchBtn").attr("disabled","disabled");
		$.post("order/orderAudit", {"headerid":rows[0].orderHeaderId,"states":"2","orderType":6}, function(res){
			if(res.data=="200"){
				$.messager.popup("提交审批成功");
				$("#orderTable").bootstrapTable("refresh");
			}else{
				$.messager.alert("提示","提交审批失败");
			}
			$("#generateBatchBtn").removeAttr("disabled");
		});
	} else {
		$.messager.alert("提示", "请选择编辑状态记录!");
	}
}

function cancelOrder(){
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && (rows[0].states=='1' || rows[0].states=='4')) {
		$.post("order/transfer/cancel.json", {"headerid":rows[0].orderHeaderId}, function(res){
			if(res.data=="200"){
				$.messager.popup("取消完成");
				$("#orderTable").bootstrapTable("refresh");
			}else{
				$.messager.alert("提示","操作失败");
			}
		});
	} else {
		$.messager.alert("提示", "请选择编辑状态记录!");
	}
}

function showImport(){
	/*$.get("customer/list.json?custType=70&limit=100&status=3",function(res){
		if(res && res.rows && res.rows.length>0){
			$("#orgid").val(res.rows[0].salesOrgId);
			$("#stationid").val(res.rows[0].positionId)
			var html = "<option></option>";
			$.each(res.rows, function(i, val){
				html += '<option value="'+val.id+'">'+val.name+'</option>'
			});
			$("#merchCustId").html(html);
		}
		$("#importModal").modal();
	});*/
	$("#importModal").modal();
}

function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			merchCustId : {
				required : true
			},
			file:"required",
		}
	});
	return validator;
}

function doExport(){
	$("#generateFile").html("正在生成excel，请耐心等待...");
	$("#exportModal").modal();
	$.ajax({
		url:'monitor/export/generate.json?key=retailOrderDetail',
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

function doExport2(){
	var url = constructUrl().replace("orderlist","exportOrder")+"&offset=0&limit=650000";
	window.location.href = url;
}
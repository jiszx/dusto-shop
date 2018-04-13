var type = 0;
$(function() {
	$('#orderTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'order/orderlist?orderType=1&batch='+batchNum,
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
		clickToSelect : false,
		singleSelect : false,
		onCheck : function() {
		},
		onUncheck : function() {
		},
		columns : [ {
			field : 'ck',
			title : '编号',
			checkbox:true
		}, {
			field : 'batchnum',
			title : '批次号',
			align : 'left'
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
			title : '所属配送商',
			align : 'left'
		}, {
			field : 'shipname',
			title : '送达方名称',
			align : 'left'
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
	var customer = $("#scustomer").val();
	//customer = encodeURI(customer);
	//customer = encodeURI(customer);
	var bdate = $("#startTime").val();
	var edate = $("#endTime").val();
	var crmorderid =$("#crmorderid").val();
	var saporderid =$("#saporderid").val();
	var url = "order/orderlist?orderType=1&batch="+batchNum+"&states=" + type + "&custname=" + customer+ "&bdate=" + bdate 
			+ "&edate=" + edate+"&crmorderid="+crmorderid+"&saporderid="+saporderid;
	$("#orderTable").bootstrapTable('refresh', {
		'url' : url
	});
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
	if (rows && rows.length > 0) {
		var validate = true;
		var isOneShip = true;
		var shipname = null;
		var merch = "0";
		var ids = [];
		$.each(rows, function(i, val){
			if(val.states!='1'){
				validate=false;
			}
			if(val.orderAmt==0 || val.orderAmt>10000){
				validate=false;
			}
			if(!shipname){
				shipname = val.shipname;
			}
			if(val.shipname!=shipname){
				isOneShip = false;
			}
			ids.push(val.orderHeaderId);
		});
		if(validate==false){
			$.messager.popup("所选订单状态必须为编辑且金额大于0并不超过10000");
			return;
		}
		if(isOneShip==false){
			$.messager.popup("所选订单必须属于同一个配送商");
			return;
		}
		$.post("order/retail/verifyDepo.json", {'orderids':ids.join()}, function(res){
			if(res.errorCode==0){
				$.post("order/retail/censor.json", {'orderids':ids.join()}, function(res){
					if(res.data && res.data >0){
						$.messager.popup('送审成功');
					}else{
						$.messager.alert('送审失败','订单总和不足起订量。');
					}
					$("#orderTable").bootstrapTable("refresh");
				});
			}else{
				$.messager.confirm("警告", res.data+"您确认要提交送审吗?", function() {
					$.post("order/retail/censor.json", {'orderids':ids.join()}, function(res){
						if(res.data && res.data >0){
							$.messager.popup('送审成功');
						}else{
							$.messager.alert('送审失败','订单总和不足起订量。');
						}
						$("#orderTable").bootstrapTable("refresh");
					});
				});
			}
		});
		
	} else {
		$.messager.alert("提示", "请选择订单!");
	}
}

function showImport(){
	$.get("customer/list.json?custType=2&limit=1000&status=3",function(res){
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
	});
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
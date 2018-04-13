var type = 0;
$(function() {
	$('#orderTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'customerInvAllocation/list',
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
		onCheck:function(row){
            if(row.allocationAmt =='0'&& row.custType =='8' &&(row.states =='7' || row.states =='5')){
            	$("#closeOrder").removeClass("hide");
            }else{
            	$("#closeOrder").addClass("hide");
            }
        },
        onUncheck:function(){
        	$("#closeOrder").addClass("hide");
        },
		columns : [ {
			field : 'ck',
			title : '编号',
			radio : true
		}, {
			field : 'orgname',
			title : '销售组织',
			align : 'left'
		}, {
			field : 'reginname',
			title : '大区',
			align : 'left'
		}, {
			field : 'provname',
			title : '业务省',
			align : 'left'
		}, {
			field : 'prov',
			title : '行政省',
			align : 'left'
		},{
			field : 'defaultRdc',
			title : '默认RDC',
			align : 'left'
		},{
			field : 'RdcName',
			title : '发货RDC',
			align : 'left'
		}, {
			field : 'custname',
			title : '送达方名称',
			align : 'left'
		}, {
			field : 'address',
			title : '地址',
			align : 'left'
		}, {
			field : 'custType',
			title : '客户类型',
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
		}, 
		{
			field : 'sapCustomerId',
			title : 'SAP客户编码',
			align : 'left'
		}, 
		{
			field : 'id',
			title : 'CRM调拨单编号',
			align : 'left'
		}, {
			field : 'orderId',
			title : 'CRM订单号',
			align : 'left'
		}, {
			field : 'createTs',
			title : '创建时间',
			align : 'left'
		}, {
			field : 'creater',
			title : '创建人',
			align : 'left'
		}, {
			field : 'amt',
			title : '金额(元)',
			align : 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
		}, {
			field : 'weight',
			title : '重量',
			align : 'left'
		}, {
			field : 'weight',
			title : '吨均价',
			align : 'left',
			formatter : function(value,data) {
				return (data.amt/value).toFixed(4);;
			}
		}, {
			field : 'allocationNum',
			title : '已调拨数量',
			align : 'left'
		}, {
			field : 'allocationAmt',
			title : '已调拨金额',
			align : 'left'
		}, {
			field : 'states',
			title : '状态',
			align : 'left',
			formatter : function(value,data) {
				if(data.sapError){
					return '<a href="javascript:void(0);" onclick="sapSendError(\''+data.rfcSerialNo+'\',\''+data.sapError+'\');">有接口消息</a>';
				}else{					
					return getorderStates(value + "");
				}
			}
		}
		/*, {
			field : 'deliveryType',
			title : '发货方式',
			align : 'left',
			formatter : function(value) {
				return getorderDeliveryType(value + "");
			}
		}*/
		, {
			field : 'freight',
			title : '运费(元)',
			align : 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
		} ]
	});
	$("#btn-search").bind("click", doSearch);
	$("#btn-detail").bind("click", doDetail);
	$("#btn-edit").bind("click", doEdit);
	$("#btn-del").bind("click", doDel);
	$("#tbn-audit").bind("click", doAudit);
	//$("#makesureBtn").bind("click", gotoMakesurePage);
	$("#exportBtn").bind("click", doExport);
	$("#exportBtn2").bind("click", doExport2);
	$("#sendAgian").click(function(){
		var rfcSerialNo=$("#sapSerialNo").val();
		$.post("monitor/rfc/current/manual",{"serialNo":$.trim(rfcSerialNo)},function(result){
        	if(result.errorCode != '-1' && $.trim(result.data) == 'S'){
        		$.messager.popup("已加入执行队列！");
        		$("#sapSendAgianModal").modal("hide");
        	}else{
        		$.messager.alert("提示","加入执行队列失败！<br/>"+"错误信息："+(result.data||result.errorMessage));
        	}
        	$("orderTable").bootstrapTable("refresh");
    	})
	})
	$("#closeOrder").click(function() {
		var rows = $("#orderTable").bootstrapTable("getSelections");
		if (rows && rows.length == 1 && rows[0].custType == '8'
				&& (rows[0].states == '5' || rows[0].states == '7')
				&& rows[0].allocationAmt == '0') {
				$.post("customerInvAllocation/closeOrder?id="+ rows[0].id,function(res){
					doSearch();
				});;
			} else {
				$.messager.popup("请选择物流商已发货金额为0的记录!");
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

function sapSendError(rfcSerialNo,sapError){
	$("#sapErrorText").html(sapError+',请问是否重新推送');
	$("#sapSerialNo").val(rfcSerialNo);
	$("#sapSendAgianModal").modal("show");
}
/**
 * 查询
 */
function doSearch() {
	var url = constructUrl();
	$("#orderTable").bootstrapTable('refresh', {
		'url' : url
	});
	return false;
}
function constructUrl(){
	var url = "customerInvAllocation/list?"+getSearchParams();
	return url;
}
function getSearchParams(){
	var customer = $("#custname").val();
	var id =$("#id").val();
	var sapId =$("#sapId").val();
	var startTime =$("#startTime").val();
	var endTime =$("#endTime").val();
	var addr =$("#addr").val();
	var custType =$("#custType").val();
	var param = "custname=" + customer+ "&id="+id+"&sapId="+sapId
		+"&startTime="+startTime+"&endTime="+endTime+"&custType="+custType+"&addr="+addr;
	return param;
}
/**
 * 查看订单详情
 */
function doDetail() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		window.location.href = "order/orderdetails.html?type=1&id="+rows[0].id;
	} else {
		$.messager.alert("提示", "请选择要记录!");
	}
}


function doEdit() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && (rows[0].states=='1'|| rows[0].states=='4')) {
		window.location.href = "order/orderedit.html?id="+rows[0].id;
	} else {
		$.messager.alert("提示", "请选择编辑状态的记录!");
	}
}
function doDel() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && (rows[0].states=='1' || row[0].states=='4')) {
		$.post("order/delorder", {"id":rows[0].id}, function(res){
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
		/*//验证金额
		$.post("customerInvAllocation/ValidateAmt?id="+rows[0].id,function(res){
			if(res.errorCode ==0 && res.data=="S"){
				$.post("customerInvAllocation/doAudit", {"id":rows[0].id,"states":"2"}, function(res){
					if(res.errorCode =='0'){
						$.messager.popup("提交审批成功");
					}else{
						$.messager.alert("提示","提交审批失败");
					}
					$("#orderTable").bootstrapTable("refresh");
				});
			}else{
				$.messager.popup(res.errorMessage);
			}
		})*/
		var str = rows[0].custType=='70'?"合资盐业公司下的仓储服务商调拨单不推送SAP，会自动生成销售订单":"提交不需要经过审批，会自动添加客户库存";
		$("#tbn-audit").attr("disabled","true");
		$.messager.confirm("警告", str, function() {
			$.post("customerInvAllocation/ValidateAmtAndNum?id="+rows[0].id,function(res){
				if(res.errorCode ==0 && res.data.type=="S"){
					$.post("customerInvAllocation/doAudit", {"id":rows[0].id,"states":"2"}, function(res){
						/*if(res.errorCode =='0' && res.data.type =="S"){
							if(rows[0].custType=='70'){
								//订单提交审批
								$.post("customerInvAllocation/orderAudit?id="+rows[0].id,function(res){
									window.location.href="order/index.html";
								})
							}
							$.messager.popup("调拨单生成订单完成");
						}else{
							$.messager.popup(res.data.msg);
						}*/
						$.messager.popup("调拨单已提交");
						$("#orderTable").bootstrapTable("refresh");
						$("#tbn-audit").removeAttr("disabled");
					});
				}else if(res.errorCode ==0 && res.data !="S"){
					$.messager.alert(res.data.msg);
					$("#orderTable").bootstrapTable("refresh");
					$("#tbn-audit").removeAttr("disabled");
				}
			});
		});
	} else {
		$.messager.alert("提示", "请选择编辑状态记录!");
	}
}

/*function gotoMakesurePage(){
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		if(rows[0].states=='8'){
			$.messager.popup("已经确认");
			return;
		}
		if(rows[0].custType!='7'){
			$.messager.popup("请选择仓储服务商调拨单");
			return;
		}
		if(rows[0].states!='7'){
			$.messager.popup("未发货订单不能确认");
			return;
		}
		window.location.href = "customerInvAllocation/makesure.html?id="+rows[0].id;
	} else {
		$.messager.popup("请选择要记录!");
	}
}*/

function doExport(){
	$("#generateFile").html("正在生成excel，请耐心等待...");
	$("#exportModal").modal();
	$.ajax({
		url:'customerInvAllocation/export?'+getSearchParams(),
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
	var url = constructUrl().replace("list","exportOrder")+"&offset=0&limit=650000";
	window.location.href = url;
}
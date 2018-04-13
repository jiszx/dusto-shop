$(function() {
	$('#invoiceTable').bootstrapTable({
		url : 'account/receive/List?type='+$("#type").val(),
		method : 'get',
		classes : "table table-hover table-condensed",
		// data:data,
		cache : false,
		toolbar : "#invoiceTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "payName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		//search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		onCheck:function(row){
            if(row.verifieAmt >0){
            	$("#verifie-details").removeClass("hide");
            }else{
            	$("#verifie-details").addClass("hide");
            }
        },
        onUncheck:function(){
        	$("#verifie-details").addClass("hide");
        },
		columns : [ {
			field : 'ck',
			title : '编号',
			radio : true
		}, {
			field : 'id',
			title : '编号',
			visible : false
		}, {
			field : 'orgname',
			title : '所属销售组织',
			align : 'left'
		}, {
			field : 'name',
			title : '客户名称',
			align : 'left'
		}, {
			field : 'customerId',
			title : 'sap客户编码',
			align : 'left'
		}, {
			field : 'invoiceNo',
			title : '应收发票编号',
			align : 'left'
		}, {
			field : 'totalPrice',
			title : '发票金额(不含税)元',
			align : 'left'
		}, {
			field : 'totalTax',
			title : '税额(元)',
			align : 'left'
		}, {
			field : 'drawDate',
			title : '开票日期',
			align : 'left'
		}, {
			field : 'period',
			title : '所属期间',
			align : 'left'
		}, {
			field : 'memo',
			title : '备注',
			align : 'left'
		}, {
			field : 'verifieAmt',
			title : '已核销金额',
			align : 'left'
		}, {
			field : 'writeoffInvoice',
			title : '冲销发票编号',
			align : 'left'
		} ]
	});

	$('#upaccounts').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		//url : 'account/receive/upAccountList?sapCustomerId=',
		cache : false,
		striped : true,
		pagination : true,
		sidePagination : "server",
		searchOnEnterKey : true,
		idField : "upid",
		sortName : "upid",
		smartDisplay : true,
		search : false,
		showColumns : true,
		//showRefresh : true,
		clickToSelect : true,
		singleSelect : false,
		columns : [ {
			field : 'ck',
			title : '编号',
			checkbox : true
		}, {
			field : 'upid',
			title : '收款id',
			align : 'left'
		}, {
			field : 'payAmt',
			title : '收款金额(元)',
			align : 'left'
		}, {
			field : 'unreceiveAmt',
			title : '未核销金额(元)',
			align : 'left'
		} ]
	});
	$('#verifieTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		cache : false,
		striped : true,
		pagination : true,
		sidePagination : "server",
		searchOnEnterKey : true,
		idField : "upid",
		sortName : "upid",
		smartDisplay : true,
		search : false,
		showColumns : true,
		//showRefresh : true,
		clickToSelect : true,
		singleSelect : false,
		columns : [ {
			field : '',
			title : '编号',
			checkbox : false,
			visible : false
		}, {
			field : 'type',
			title : '描述',
			align : 'left',
			formatter : function(value) {
				return getVerifieType(value + "");
			}
		}, {
			field : 'verifieAmt',
			title : '金额(元)',
			align : 'center'
		}, {
			field : 'invoiceNo',
			title : '编号',
			align : 'left'
		}, {
			field : 'MSG',
			title : '接口信息',
			align : 'left',
			formatter : function(value,data) {
				//return getUpStatesValue(value + "");
				if(data.states =='2'){
					var res = '<a href="javascript:void(0);" onclick="doSAP(\''+data.id+'\',\''+data.msg+'\');">接口信息</a>';
					return res;
				}
			}
		} ]
	});
	$('#invoiceItemTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		//url : 'account/receive/upAccountList?sapCustomerId=',
		cache : false,
		striped : true,
		pagination : true,
		sidePagination : "server",
		searchOnEnterKey : true,
		idField : "upid",
		sortName : "upid",
		smartDisplay : true,
		search : false,
		showColumns : true,
		//showRefresh : true,
		clickToSelect : true,
		//singleSelect : false,
		columns : [ {
			field : 'ck',
			title : '编号',
			checkbox : false,
			visible : false
		}, {
			field : 'orderId',
			title : 'SAP销售订单编号',
			align : 'left'
		}, {
			field : 'headerId',
			title : 'CRM销售订单编号',
			align : 'left'
		}, {
			field : 'materialId',
			title : '物料编码',
			align : 'left'
		}, {
			field : 'materialId',
			title : '物料编码',
			align : 'left'
		}, {
			field : 'sku',
			title : '物料名称',
			align : 'left'
		}, {
			field : 'unit',
			title : '单位',
			align : 'left'
		}, {
			field : 'unm',
			title : '数量',
			align : 'left'
		}, {
			field : 'price',
			title : '单价(元)',
			align : 'left'
		}, {
			field : 'amt',
			title : '含税金额(元)',
			align : 'left'
		}, {
			field : 'taxRate',
			title : '税率',
			align : 'left'
		}, {
			field : 'tax',
			title : '税额(元)',
			align : 'left'
		} ]
	});
	$("#btn-search").bind("click", doSearch);
	$("#btn-verifie").bind("click", doVerifie);
	$("#verifie-details").bind("click", doDetails);
	$("#btn-invoice").bind("click", doInvoice);
	$("#btn-write-off").bind("click", doWirteOff);
	$("#chooseUpaccount").bind("click", doChoose);
	//$("#btn-verifieAll").bind("click",verifieAll);
});
//核销明细
function doDetails(){
	var rows = $("#invoiceTable").bootstrapTable("getSelections");
	if(!rows || rows.length <1){
		$.messager.popup("请选择记录");
		return;
	}
	$("#verifieTable").bootstrapTable("refresh", {
		'url' : 'account/receive/verifieList?invoiceNo=' + rows[0].invoiceNo+"&invoiceId="+rows[0].id,
		method : 'get'
	});
	$("#verifieDetailModal").modal("show");
}

function doVerifie() {
	var rows = $("#invoiceTable").bootstrapTable("getSelections");
	var tax = rows[0].totalTax;
	var amt = rows[0].totalPrice;
	var verifieAmt = rows[0].verifieAmt;
	if (parseFloat(verifieAmt) == (parseFloat(amt) + parseFloat(tax))) {
		// 已核销金额等于发票金额
		$.messager.alert("提示", "已核销金额等于发票金额");
		return;
	}
	if(amt <0 && rows[0].writeoffInvoice){
		$.messager.confirm("警告", "负数发票将红冲正数应收发票", function() {
			$.post("account/receive/verifieByneinvoice?invoiceNo="+rows[0].invoiceNo+"&invoiceId="+rows[0].id,function(res){
				$.messager.popup(res.data.msg);
				$('#invoiceTable').bootstrapTable("refresh");
			});
		});
	}else{		
		upaccount(rows[0].customerId);
	}
}
function doChoose() {
	var row = $("#invoiceTable").bootstrapTable("getSelections");
	var accounts = $("#upaccounts").bootstrapTable("getSelections");
	if (accounts && accounts.length < 1) {
		$.messager.popup("请选择收款记录");
		return;
	}
	// 应收发票金额
	var invoiceamt = parseFloat(row[0].totalPrice)
			+ parseFloat(row[0].totalTax);
	var verifieAmt = parseFloat(row[0].verifieAmt);
	var ids = [];
	$.each(accounts, function(i, val) {
		ids.push(val.upid);
	});
	$.post("account/receive/doReceive?invoiceId=" + row[0].id + "&accountIds="
			+ ids.join(), function(res) {
		if(res.errorCode == '-1'){
			$.messager.popup(res.errorMessage);
		}else{			
			if(res.data.type =="200"){
				$.messager.popup(res.data.msg);
				$("#chooseUpaccountModal").modal("hide");
				$('#invoiceTable').bootstrapTable("refresh");
			}else{
				$.messager.popup(res.data.msg);
			}
		}
	})
}

function doInvoice() {
	var rows = $("#invoiceTable").bootstrapTable("getSelections");
	if(!rows || rows.length <1){
		$.messager.popup("请选择记录");
		return;
	}
	$("#invoiceItemTable").bootstrapTable("refresh", {
		'url' : 'account/receive/invoiceItemList?invoiceNo=' + rows[0].invoiceNo,
		method : 'get'
	});
	$("#invoiceDetailModal").modal("show");
}

function upaccount(value) {
	$("#upaccounts").bootstrapTable("refresh", {
		'url' : 'account/receive/upAccountList?sapCustomerId=' + value,
		method : 'get'
	});
	$("#chooseUpaccountModal").modal("show");
}
/**
 * 查询
 */
function doSearch() {
	var custname = $("#custname").val();
	var type = $("#type").val();
	var invoiceNo =$("#invoiceNo").val();
	if(type =='1'){
		$("#verifie-details").addClass("hide");//核销明细
		$("#btn-verifie").removeClass("hide");//核销
		$("#btn-write-off").addClass("hide");//冲销
		//$("#verifieAll").removeClass("hide");
	}else if(type=='2'){
		//查询已核销
		$("#btn-verifie").addClass("hide");
		$("#verifie-details").removeClass("hide");
		$("#btn-write-off").removeClass("hide");
		//$("#verifieAll").addClass("hide");
	}
	var url = "account/receive/List?name="+custname+"&type="+type+"&invoiceNo="+invoiceNo;
	$("#invoiceTable").bootstrapTable('refresh', {
		'url' : url
	});
}

function doWirteOff(){
	var rows = $("#invoiceTable").bootstrapTable("getSelections");
	var amt = rows[0].totalPrice;
	if(amt <0){
		$.messager.popup("请选择正数应收发票记录");
		return;
	}
	$.messager.confirm("警告", "请确定要冲销收款核销吗？", function() {
		$.post("account/receive/writeOff?invoiceId="+rows[0].id+"&invoiceNo="+rows[0].invoiceNo,function(res){
			$.messager.popup(res.data.msg);
			 $("#invoiceTable").bootstrapTable("refresh");
		})
	});
}

function verifieAll(){
	$.messager.confirm("警告", "会自动核销所有应收发票，确定要执行吗？", function() {
		$.post("account/receive/verifieAll?p_org_id="+$("#p_orgid").val(),function(res){
			$.messager.popup(res.data.msg);
			$("#verifieAllModal").modal("hide");
			$("#invoiceTable").bootstrapTable("refresh");
		})
	});
}

function doSAP(id,str){
	//获取资金上账错误信息
	$.messager.confirm("警告", str+",点击确定重新发送", function() {
		$.post("account/receive/sendSap", {"id":id}, function(res){
			$.messager.popup(res.data.msg);
			$("#verifieDetailModal").modal("hide");
			$("#invoiceTable").bootstrapTable("refresh");
		});
	});
}
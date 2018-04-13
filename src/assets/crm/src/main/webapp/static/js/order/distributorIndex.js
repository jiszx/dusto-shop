var type = 0;
$(function() {
	$('#orderTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'order/distributor/distributorOrderList?orderType=1',
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
		onCheck : function() {
			$(".btn-edit,.btn-del").show();
		},
		onUncheck : function() {
			$(".btn-edit,.btn-del").hide();
		},
		columns : [ {
			field : 'ck',
			title : '编号',
			radio : true
		}, {
			field : 'orgname',
			title : '销售组织',
			align : 'left'
		}, 
		 {
			field : 'shipname',
			title : '交货地点',
			align : 'left'
		}, 
		/*{
			field : 'regionname',
			title : '大区',
			align : 'left'
		}, {
			field : 'provname',
			title : '业务省',
			align : 'left'
		},*/
		{
			field : 'merchname',
			title : '配送商名称',
			align : 'left'
		}, {
			field : 'lpno',
			title : '批次号',
			align : 'left'
		}, {
			field : 'censortime',
			title : '送审时间',
			align : 'left',
			formatter : function(value) {
				var date = new Date(parseInt(value));
				Y = date.getFullYear() + '-';
				M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
				D = date.getDate() + ' ';
				h = date.getHours() + ':';
				m = date.getMinutes() + ':';
				s = date.getSeconds(); 
				return Y+M+D+h+m+s;
			}
		}, {
			field : 'amt',
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
		},
		/*{
			field : 'isMatched',
			title : '是否匹配资金',
			align : 'left',
			formatter : function(value) {
				if(value =="1"){
					return "已匹配";
				}else{
					return "未匹配";
				}
			}
		},*/
		{
			field : 'states',
			title : '订单状态',
			align : 'left',
			formatter : function(value) {
				return getorderType(value + "");
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
	$("#tbn-audit").bind("click", doAudit);
	$("#importBtn").bind('click', showImport);
	$("#matchBtn").bind('click', showUpaccount);
	$("#chooseUpacBtn").bind('click', relateUpaccount);
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
 * 查询
 */
function doSearch() {
	var customer = $("#scustomer").val();
	var bdate = $("#startTime").val();
	var edate = $("#endTime").val();
	var url = "order/distributor/distributorOrderList?orderType=1&states=" + type + "&custname=" + customer+ "&bdate=" + bdate + "&edate=" + edate;
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
		window.location.href = "order/distributor/distributeOrderDetails?lpno="+rows[0].lpno
							   +"&states="+rows[0].states+"&amt="+rows[0].amt
							   +"&merchname="+rows[0].merchname+"&shipid="+rows[0].shipid
							   +"&shipname="+rows[0].shipname+"&merchid="+rows[0].merchid
							   +"&isMatched="+rows[0].isMatched;
	} else {
		$.messager.alert("提示", "请选择要记录!");
	}
}


function doEdit() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && rows[0].states=='1' ) {
		window.location.href = "order/retail/index.html?batch="+rows[0].lpno;
//		window.location.href = "order/distributor/distributeOrderDetails?lpno="+rows[0].lpno
//		   +"&states="+rows[0].states+"&amt="+rows[0].amt
//		   +"&merchname="+rows[0].merchname+"&shipid="+rows[0].shipid
//		   +"&shipname="+rows[0].shipname+"&merchid="+rows[0].merchid
//		   +"&isMatched="+rows[0].isMatched+"&type=1";
	} else {
		$.messager.alert("提示", "请选择可编辑的记录!");
	}
}
/*function doDel() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && rows[0].states=='1') {
		$.post("order/delorder", {"id":rows[0].orderHeaderId}, function(res){
			if(res.data=="200"){
				$.messager.popup("删除成功");
			}else{
				$.messager.alert("提示","删除失败");
			}
			$("#orderTable").bootstrapTable("refresh");
		});
	} else {
		$.messager.alert("提示", "请选择编辑状态记录!");
	}
}*/
function doAudit() {
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && rows[0].states=='1') {
		$.post("order/orderAudit", {"headerid":rows[0].lpno,"states":"2","orderType":"1"}, function(res){
			if(res.data=="200"){
				$.messager.popup("提交审批成功");
			}else{
				$.messager.alert("提示","提交审批失败");
			}
			$("#orderTable").bootstrapTable("refresh");
		});
	} else {
		$.messager.alert("提示", "请选择编辑状态记录!");
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

function changeShip(id){
	var url = "order/util/customerShip?merchid="+id+"&orgid="+$("#orgid").val();
	$.get(url, function(res){
		if(res && res.rows){
			var html = "";
			$.each(res.rows, function(i, val){
				html += '<option value="'+val.id+'">'+val.name+'</option>';
			});
			$("#shipid").html(html);
		}
	});
}

function relateUpaccount(){
	var rows = $("#orderTable").bootstrapTable("getSelections");
	var upaccounts = $("#upaccounts").bootstrapTable("getSelections");
	if(upaccounts && upaccounts.length<1){
		$.messager.popup("请选择记录");
		return;
	}
	
	var upAmt = 0.0;
	var ids = [];
	$.each(upaccounts, function(i, val){
		ids.push(val.id);
		upAmt += val.payAmt;
	});
	var merchid = $("#transformMerch").val();
	var addAmt = upAmt-rows[0].amt;
	if(addAmt<0){
		$.messager.popup("资金不足");
		return;
	}else if(addAmt>0){
		if(merchid){
			$.post("order/distributor/match.json", {'batchNum':rows[0].lpno,'upaccounts':ids.join(),'merchid':merchid}, function(res){
				if(res.data && res.data>0){
					$("#chooseUpaccount").modal("hide");
					$.messager.popup("匹配完成");
					$("#transformMerch").empty();
					$("#tomerch").addClass("hide");
				}else if(res.data && res.data==-1){
					$.messager.popup('已经匹配过，请勿重复提交');
				}else {
					$.messager.popup('匹配失败');
				}
			});
		}else{
			$.get("customer/retails.json?limit=1000&states=3&merchid="+rows[0].merchid, function(res){
				var html = "<option></option>";
				if(res.rows){
					$.each(res.rows, function(i, val){
						html += '<option value="'+val.id+'">'+val.name+'</option>';
					});
					$("#transformMerch").html(html);
					$("#tomerch").removeClass("hide");
				}
			});
			$.messager.popup("请选择转移客户");
		}
	}else{
		$.post("order/distributor/match.json", {'batchNum':rows[0].lpno,'upaccounts':ids.join()}, function(res){
			if(res.data && res.data>0){
				$("#chooseUpaccount").modal("hide");
				$.messager.popup("匹配完成");
				$("#transformMerch").empty();
				$("#tomerch").addClass("hide");
			}else if(res.data && res.data==-1){
				$.messager.popup('已经匹配过，请勿重复提交');
			}else {
				$.messager.popup('匹配失败');
			}
		});
	}
}

function showUpaccount(){
	var rows = $("#orderTable").bootstrapTable("getSelections");
	if(rows && rows.length ==1){
		if(rows[0].states!='2'){
			$.messager.popup("待审批订单才能匹配资金");
			return ;
		}
		$("#chooseUpaccount").modal();
		loadUpaccount();
	}else{
		$.messager.alert("提示", "请选择一条记录!");
	}
}

function loadUpaccount(salesOrgId,salesOrgName){
	if($('#upaccounts').data("loaded")){
		$("#upaccounts").bootstrapTable("refresh",
			{
			'url' : 'account/distributors/list',
			method:'get'
			}
		);
	}else{
		$('#upaccounts').bootstrapTable({
			method: 'get',
			classes:"table table-hover table-condensed",
			url: 'account/distributors/list?states=3',
			cache: false,
			striped: true,
			pagination: true,
			sidePagination:"server",
			searchOnEnterKey:true,
			idField:"id",
			sortName:"colName",
			smartDisplay:true,
			search: false,
			showColumns: true,
			showRefresh: true,
			clickToSelect: true,
			singleSelect:false,
			columns: [{
				field: 'ck',
				title: '编号',
				checkbox:true
			}, {
				field: 'id',
				title: 'id',
				align: 'left',
				visible : false
			}, {
				field: 'sap编号',
				title: '客户sap编号',
				align: 'left',
				visible : false
			}, {
				field: 'custname',
				title: '配送商',
				align: 'left'
			}, {
				field: 'orgname',
				title: '销售组织',
				align: 'left'
			}, {
				field: 'payName',
				title: '打款人',
				align: 'left'
			}, {
				field: 'payBank',
				title: '打款银行',
				align: 'left'
			}, {
				field: 'payBankNo',
				title: '卡号',
				align: 'left'
			}, {
				field: 'payAmt',
				title: '金额',
				align: 'left'
			}]
		});
		$('#upaccounts').data("loaded",true);
	}
}
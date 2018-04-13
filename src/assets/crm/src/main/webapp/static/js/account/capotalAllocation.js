var receivedata;
var merchdata;
var custType;
$(function() {
	//初始化表格
	initCustomerTable("#assigned-table", {});
	
	//初始化配送商数据
	var merchurl ="account/allocation/customer";
	$.get(merchurl,function(data){
		if(data.rows && data.rows.length>0){
			merchdata =data.rows;
			$("#merchCustId").append('<option></option>');
			for(var i=0;i<data.rows.length;i++){
				$("#merchCustId").append('<option value="'+data.rows[i].id+'">'+data.rows[i].sapCustomerId+data.rows[i].name+'</option>');
			}
			$("#merchCustId").trigger("chosen:updated");
		}else{
			$.messager.popup("配送商数据为空，请查看职位或者验证是否是配送商用户");
		}
	});
	// chosen 初始化
	$('#merchCustId').chosen({
		no_results_text : "没有找到.",
		placeholder_text : "请选择配送商...",
		search_contains : true
		//disable_search_threshold : 10
	});
	$('#receivables').chosen({
		no_results_text : "没有找到.",
		placeholder_text : "请选择打款记录...",
		search_contains : true
		//disable_search_threshold : 10
	});
	// 配送商数据修改获取打款金额数据
	$("#merchCustId").change(function() {
		//清空上账记录
		$("#receivables").html('');
		$("#receivables").trigger("chosen:updated");
		$("#payAmt").val(0);
		$("#allocationAmt").val(0);
		//清空分配数据
		$("#assigned-table").bootstrapTable('removeAll');
		var merchCustId = $("#merchCustId").val();
		
		var receiveurl ="account/allocation/receives?merchCustId="+merchCustId;
		$.get(receiveurl,function(data){
			if(data.rows && data.rows.length>0){
				$("#receivables").append('<option></option>');
				receivedata = data.rows;
				for(var i=0;i<data.rows.length;i++){
					$("#receivables").append('<option value="'+data.rows[i].id+'">'+data.rows[i].payType+'-'
							+data.rows[i].bankSerial+'-'+data.rows[i].payAmt+'</option>');
				}
				$("#receivables").trigger("chosen:updated");
				$("#btn-select-user").removeAttr("disabled");
				$("#btn-select-order").removeAttr("disabled");
			}else{
				$("#btn-select-user").attr("disabled","true");
				$("#btn-select-order").attr("disabled","true");
				$.messager.popup("配送商没有未分配资金的收款记录");
			}
		});
		for (var i=0;i<merchdata.length;i++){
			if(merchdata[i].id==merchCustId){
				custType=merchdata[i].custType;
			}
		}
	});
	
	//获取收款分配情况
	$("#receivables").change(function() {
		// 选择打款记录
		var receiveId =$("#receivables").val();
		for(var i=0;i<receivedata.length;i++){
			if(receiveId==receivedata[i].id){
				$("#payAmt").val(receivedata[i].payAmt);
				$("#allocationAmt").val(receivedata[i].payAmt-receivedata[i].allocationAmt);
			}
		}
		$("#assigned-table").bootstrapTable('refresh');
		$("#row-balance").removeClass("hidden");
		$("#cuntomer-table-box").removeClass("hidden");
	});
	
	//初始化客户表格
	initChooseCustomerTable();
	initCapoCustomerTable();
	//选择客户按钮事件
	$("#btn-select-user").click(function() {
		// 打开客户选择框
		// initChooseCustomerTable("#choose-customer-table", DATA1);
		$("#choose-customer-table").bootstrapTable('refresh',{
			'url' : 'account/allocation/retailer?merchCustId='+ $("#merchCustId").val()
		});
	});
	
	//初始化批次选择
	initChooseRecordTable("#choose-record-table");
	initChooseOrderTable("#choose-order-table");
	//选择订单按钮事件
	$("#btn-select-order").click(function() {
		// 打开订单选择框
		if(custType=='7'){
			$("#md-choose-order").modal("show");
			$("#choose-order-table").bootstrapTable('refresh',{
				'url':"account/allocation/orders?merchCustId="+ $("#merchCustId").val()
			})
		}else{
			$("#md-choose-record").modal("show");
			$("#choose-record-table").bootstrapTable('refresh',{
				'url':"account/allocation/records?merchCustId="+ $("#merchCustId").val()
			});
		}
	});

	$("#btn-confirm-customer").click(
			function() {
				var receiveId =$("#receivables").val();
				for(var i=0;i<receivedata.length;i++){
					if(receiveId==receivedata[i].id){
						$("#payAmt").val(receivedata[i].payAmt);
						$("#allocationAmt").val(receivedata[i].payAmt-receivedata[i].allocationAmt);
					}
				}
				// 获取选择用户数据
				var tb_choose_customer = $("#choose-customer-table").bootstrapTable("getSelections");
				var ids = [];
				if(tb_choose_customer && tb_choose_customer.length<1){
					$.messager.popup("请选择记录");
					return;
				}
				$.each(tb_choose_customer, function(i, val){
					ids.push(val.id);
				});
				$('#assigned-table').bootstrapTable('hideColumn', 'orderAmt');
				/*$('#assigned-table').bootstrapTable('hideColumn', 'orderId');
				$('#assigned-table').bootstrapTable('showColumn', 'cashAmt');
				$('#assigned-table').bootstrapTable('showColumn', 'totalAmt');*/
				// 加载数据
				$("#assigned-table").bootstrapTable('refresh', {
					'url':'account/allocation/retailerAccount?ids='+ids
				});
				$("#row-btn-submit").removeClass("hidden");
			});
	
	/**
	 * 选择批次
	 */
	$("#btn-confirm-record").click(
			function() {
				var receiveId =$("#receivables").val();
				for(var i=0;i<receivedata.length;i++){
					if(receiveId==receivedata[i].id){
						$("#payAmt").val(receivedata[i].payAmt);
						$("#allocationAmt").val(receivedata[i].payAmt-receivedata[i].allocationAmt);
					}
				}
				// 获取选择订单数据
				var tb_choose_order = $("#choose-record-table").bootstrapTable("getSelections");

				var ids = [];
				if(tb_choose_order && tb_choose_order.length<1){
					$.messager.popup("请选择记录");
					return;
				}
				$.each(tb_choose_order, function(i, val){
					ids.push(val.lpno);
				});
				$('#assigned-table').bootstrapTable('showColumn', 'orderAmt');
				/*
				 * $('#assigned-table').bootstrapTable('showColumn', 'orderId');
				 * $('#assigned-table').bootstrapTable('hideColumn', 'cashAmt');
				 * $('#assigned-table').bootstrapTable('hideColumn',
				 * 'totalAmt');
				 */
				$("#assigned-table").bootstrapTable('refresh', {
					'url':'account/allocation/retailerOrders?ids='+ids+"&type=1"
				});
				$("#row-btn-submit").removeClass("hidden");
				
			});
	$("#btn-confirm-order").click(
			function() {
				var receiveId =$("#receivables").val();
				for(var i=0;i<receivedata.length;i++){
					if(receiveId==receivedata[i].id){
						$("#payAmt").val(receivedata[i].payAmt);
						$("#allocationAmt").val(receivedata[i].payAmt-receivedata[i].allocationAmt);
					}
				}
				// 获取选择订单数据
				var tb_choose_order = $("#choose-order-table").bootstrapTable("getSelections");

				var ids = [];
				if(tb_choose_order && tb_choose_order.length<1){
					$.messager.popup("请选择记录");
					return;
				}
				$.each(tb_choose_order, function(i, val){
					ids.push(val.orderId);
				});
				$('#assigned-table').bootstrapTable('showColumn', 'orderAmt');
				$("#assigned-table").bootstrapTable('refresh', {
					'url':'account/allocation/retailerOrders?ids='+ids+"&type=2"
				});
				$("#row-btn-submit").removeClass("hidden");
				
			});
	//确认分配按钮事件，提交分配金额
	$("#btn-submit").click(function() {
		if($("#allocationAmt").val() < 0){
			$.messager.popup("分配资金超过上账资金");
			return;
		}
		//验证金额
		if($("#allocationAmt").val() > 0){
			$.messager.confirm("警告", "资金未完全分配，请选择一个客户添加剩余资金", function() {
				// 打开客户选择框
				// initChooseCustomerTable("#choose-customer-table", DATA1);
				$("#capo-customer-table").bootstrapTable('refresh',{
					'url' : 'account/allocation/retailer?merchCustId='+ $("#merchCustId").val()
				});
				$("#capo-choose-customer").modal("show");
			});
			return ;
		}
		$("#btn-submit").attr("disabled","true");
		//获取金额
		var data = $("#assigned-table").bootstrapTable('getData');
		if(data && data.length >0){
			var str =JSON.stringify(data);
			var receiveId =$("#receivables").val();
			//调用后台更新零售商资金
			$.post("account/allocation/doAllocation?data="+str+"&receiveId="+receiveId,function(res){
				if(res.data.type =="200"){
					window.location.href="account/accountDetails.html?merchCustId="+$("#merchCustId").val();
				}else{
					$("#btn-submit").removeAttr("disabled");
					$.messager.popup(res.data.msg);
				}
			})
		}else{
			$.messager.popup("没有资金分配");
			return 
		}
		//toggleTips(".tips", "提交信息");
	});
    $("#btn-capo-customer").click(function(){
    	$("#btn-submit").attr("disabled","true");
    	var rows =$("#capo-customer-table").bootstrapTable("getSelections");
		//获取金额
		var data = $("#assigned-table").bootstrapTable('getData');
		var  str={
				"allocationAmt":$("#allocationAmt").val(),
				"cashAmt":0,
				"custname":rows[0].name,
				"merchCustId":rows[0].id,
				"orderAmt":0,
				"sapCustomerId":rows[0].sapCustomerId,
				"totalAmt":$("#allocationAmt").val()
			};
		data.push(str);
		if(data && data.length >0){
			var str =JSON.stringify(data);
			var receiveId =$("#receivables").val();
			//调用后台更新零售商资金
			$.post("account/allocation/doAllocation?data="+str+"&receiveId="+receiveId,function(res){
				if(res.data.type =="200"){
					window.location.href="account/accountDetails.html?merchCustId="+$("#merchCustId").val();
				}else{
					$("#btn-submit").removeAttr("disabled");
					$.messager.popup(res.data.msg);
				}
			})
		}else{
			$.messager.popup("没有资金分配");
			return 
		}
    });
	// EXCEL文件选择框
	$("#file-input-excel").change(function(e) {
		var file = $(this)[0].files[0];
		alert("文件已选择:" + file);
		// 加载数据
		$("#assigned-table").bootstrapTable('load', DATA);
		$("#row-btn-submit").removeClass("hidden");
	});
});

function addOptions(obj, data) {
	if (data.length > 0) {
		var select = $(obj);
		select.html("");
		select.append($("<option></option>"));
		for (var i = 0; i < data.length; i++) {
			var option = $("<option></option>");
			var val = data[i].toString();
			option.attr("value", val);
			option.html(val);
			select.append(option);
		}
	} else {
		console.info("Failure:data length less than 0.");
	}
}


function toggleTips(obj, msg) {
	if (!($(obj).length > 0)) {
		console.info("create tips.")
		$("body").append("<p class='tips'></p>");
	}
	var tip = $(obj);
	tip.html(msg);
	tip.css("margin-left", -1 * (tip.width() + 30) / 2);
	tip.addClass("show");
	setTimeout(function() {
		tip.removeClass("show");
	}, 3000);
}

function initCustomerTable(obj, data) {
	var table = $(obj);
	table
			.bootstrapTable({
				//data : data,
				//url:'account/allocation/retailerAccount',
				classes : "table table-hover table-condensed",
				// data:data,
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
				search : true,
				showColumns: true,
				showRefresh: true,
				clickToSelect: true,
				singleSelect:false,
				columns : [
						{
							title : '编号',
							field : 'id',
							align : 'center',
							valign : 'middle',
							visible : false
						},
						{
							title : '客户',
							field : 'custname',
							align : 'center',
							valign : 'middle',
							sortable : false,
							footerFormatter : totalTextFormatter
						},
						{
							title : '客户编码',
							field : 'sapCustomerId',
							align : 'center',
							valign : 'middle',
							sortable : false
						},
						{
							title : '客户余额',
							field : 'cashAmt',
							align : 'center',
							valign : 'middle',
							sortable : false,
							//visible : false,
							footerFormatter : totalPriceFormatter
						},
						/*{
							title : '销售订单编码',
							field : 'orderId',
							align : 'center',
							valign : 'middle',
							sortable : false,
							visible : false
						},*/
						{
							title : '销售订单金额',
							field : 'orderAmt',
							align : 'center',
							valign : 'middle',
							sortable : false,
							visible : false,
							footerFormatter : totalOrderFormatter
						},
						{
							title : '分配金额',
							field : 'allocationAmt',
							align : 'center',
							valign : 'middle',
							sortable : false,
							/*formatter : function(value) {
								$("#allocationAmt").val($("#allocationAmt").val()-value);
							},*/
							editable : {
								type : 'text',
								title : '修改分配金额',
								validate : function(value,s) {
									value = $.trim(value);
									var data = table.bootstrapTable('getData'), index = $(
											this).parents('tr').data('index');
									if (!value) {
										return 'This field is required';
									}
									if (!/^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$)/
											.test(value)) {
										return '请输入正确的数值,并保留2位小数'
									}
									var amt =parseFloat($("#allocationAmt").val())+parseFloat(data[index].allocationAmt);
									if(value >parseFloat(amt)){
										return '当前未分配金额为：'+amt+',可分配金额不足，请重新分配'
									}
									data[index].allocationAmt = value;
									data[index].totalAmt = (parseFloat(value)+parseFloat(data[index].cashAmt)).toFixed(2);
									table.bootstrapTable("updateRow",{
										index:index,
										row:data[index]
									})
									//console.log(data[index]);
									return '';
								},
							},
							footerFormatter : totalBalanceFormatter
						}
						, {
							title : '总计',
							field : 'totalAmt',
							align : 'center',
							valign : 'middle',
							sortable : false,
							//visible : false,
							footerFormatter : totalAmtFormatter
						}
						/*, {
							title : '联系人',
							field : 'contact',
							align : 'center',
							valign : 'middle',
							sortable : false
						} */
						]
			});
}
function totalTextFormatter(data) {
	return '总计';
}

function totalPriceFormatter(data) {
	var field = this.field;
	var total = data.reduce(function(sum, row) {
		total = sum + (+row[field]);
		return sum + (+row[field]);
	}, 0);
	return total.toFixed(2);
}

function totalAmtFormatter(data){
	var field = this.field;
	var total = data.reduce(function(sum, row) {
		total = sum + (+row[field]);
		return sum + (+row[field]);
	}, 0);
	return total.toFixed(2);
}

function totalOrderFormatter(data){
	var field = this.field;
	var total = data.reduce(function(sum, row) {
		total = sum + (+row[field]);
		return sum + (+row[field]);
	}, 0);
	return total.toFixed(2);
}
function totalBalanceFormatter(data) {
	var field = this.field;
	total = data.reduce(function(sum, row) {
		total = sum + (+row[field]);
		return sum + (+row[field]);
	}, 0);
	checkBalance(total.toFixed(2));
	return total.toFixed(2);
}

function checkBalance(useMoney) {
	var totalBox = $("#payAmt");
	var balanceBox = $("#allocationAmt");
	var balance = (totalBox.val() - useMoney).toFixed(2);
	balanceBox.val(balance);
	balanceBox.css("color", balance < 0 ? "red" : "green");
	if(balance <0){
		$.messager.popup("可分配金额不足，请重新分配");
	}
}

function initChooseCustomerTable() {
	var table = $("#choose-customer-table");
	table.bootstrapTable({
		//url :'account/allocation/retailer?merchCustId='+$("#merchCustId").val(),
		method : 'get',
		classes : "table table-hover table-condensed",
		cache: false,
		striped: true,
		pagination: true,
		sidePagination:"server",
		searchOnEnterKey:true,
		idField:"id",
		sortName:"colName",
		smartDisplay:true,
		search: true,
		showColumns: true,
		showRefresh: true,
		clickToSelect: true,
		singleSelect:false,
		columns : [ {
			title : 'state',
			field : 'state',
			align : 'center',
			valign : 'middle',
			checkbox : true
		}, {
			title : 'ID',
			field : 'id',
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '客户',
			field : 'name',
			align : 'center',
			valign : 'middle',
			sortable : false
		}, {
			title : '编号',
			field : 'sapCustomerId',
			align : 'center',
			valign : 'middle',
			sortable : false
		}, {
			title : '联系人',
			field : 'contactName',
			align : 'center',
			valign : 'middle',
			sortable : false
		} ]
	});
}

function initCapoCustomerTable() {
	var table = $("#capo-customer-table");
	table.bootstrapTable({
		//url :'account/allocation/retailer?merchCustId='+$("#merchCustId").val(),
		method : 'get',
		classes : "table table-hover table-condensed",
		cache: false,
		striped: true,
		pagination: true,
		sidePagination:"server",
		searchOnEnterKey:true,
		idField:"id",
		sortName:"colName",
		smartDisplay:true,
		search: true,
		showColumns: true,
		showRefresh: true,
		clickToSelect: true,
		singleSelect:true,
		columns : [ {
			title : 'state',
			field : 'state',
			radio : true
		}, {
			title : 'ID',
			field : 'id',
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '客户',
			field : 'name',
			align : 'center',
			valign : 'middle',
			sortable : false
		}, {
			title : '编号',
			field : 'sapCustomerId',
			align : 'center',
			valign : 'middle',
			sortable : false
		}, {
			title : '联系人',
			field : 'contactName',
			align : 'center',
			valign : 'middle',
			sortable : false
		} ]
	});
}
function initChooseRecordTable(obj) {
	var table = $(obj);
	table.bootstrapTable({
		//data : data,
		method : 'get',
		classes : "table table-hover table-condensed",
		cache: false,
		striped: true,
		pagination: true,
		sidePagination:"server",
		searchOnEnterKey:true,
		idField:"id",
		sortName:"colName",
		smartDisplay:true,
		search: true,
		showColumns: true,
		showRefresh: true,
		clickToSelect: true,
		singleSelect:false,
		columns : [ {
			title : 'state',
			field : 'state',
			align : 'center',
			valign : 'middle',
			checkbox : true
		}, {
			title : '订单批次号',
			field : 'lpno',
			align : 'center',
			valign : 'middle',
			sortable : false,
			visible : false
		},{
			title : '订单编号',
			field : 'orderId',
			align : 'center',
			valign : 'middle',
			sortable : false,
			visible : false
		},{
			title : '零售商名称',
			field : 'custname',
			align : 'center',
			valign : 'middle',
			sortable : false,
			visible : false
		}, {
			title : '订单金额',
			field : 'amt',
			align : 'center',
			valign : 'middle',
			sortable : false
		}, {
			title : '状态',
			field : 'states',
			align : 'center',
			valign : 'middle',
			sortable : false,
			formatter : function(value) {
				return getorderStates(value +"%");
			}
		} ]
	});
}

function initChooseOrderTable(obj) {
	var table = $(obj);
	table.bootstrapTable({
		//data : data,
		method : 'get',
		classes : "table table-hover table-condensed",
		cache: false,
		striped: true,
		pagination: true,
		sidePagination:"server",
		searchOnEnterKey:true,
		idField:"id",
		sortName:"colName",
		smartDisplay:true,
		search: true,
		showColumns: true,
		showRefresh: true,
		clickToSelect: true,
		singleSelect:false,
		columns : [ {
			title : 'state',
			field : 'state',
			align : 'center',
			valign : 'middle',
			checkbox : true
		},{
			title : '订单编号',
			field : 'orderId',
			align : 'center',
			valign : 'middle',
			sortable : false
		},{
			title : '零售商名称',
			field : 'custname',
			align : 'center',
			valign : 'middle',
			sortable : false
		}, {
			title : '订单金额',
			field : 'amt',
			align : 'center',
			valign : 'middle',
			sortable : false
		}, {
			title : '状态',
			field : 'states',
			align : 'center',
			valign : 'middle',
			sortable : false,
			formatter : function(value) {
				return getorderStates(value +"%");
			}
		} ]
	});
}
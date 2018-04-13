var $table = $('#order-table');
var dataid = 1;
var lineamt = 0;
var linenum = 0;
var linehNum = 0;
var linehbamt = 0;
var lineorderamt = 0;
var linediscountamt = 0;
var customerdata;
var states = '1';
var flag = "0";
var editrow;
var custType;
var materialdata;
var upfiles = [];
var data = [ {
	"id" : '0',
	"sku" : "合计",
	"num" : "0",
	"hbNum" : '0',
	"amt" : '0',
	"discountAmt" : '0',
	"orderAmt" : '0'
} ];

$(function() {
	$("#endTime").datetimepicker({
		format : 'yyyy-mm-dd',
		minView : 2,
		language : 'zh-CN',
		autoclose : true
	});
	$('#add-bstb-row').click(function() {
		$('.add-bstb-box').addClass("add-bstb-box-open");
	});
	$('#btn-hide-bstb-add-box').click(function() {
		$('.add-bstb-box').removeClass("add-bstb-box-open");
	});
	$("#del-bstb-row").bind("click", doDelLine);// 删除行
	$("#edit-bstb-row").bind("click", doEdit);// 修改行
	$("#num").blur(function() {
		numchangeamt()
	});
	$("#hbNum").blur(function() {
		numchangeamt()
	});
	$("#editnum").blur(function() {
		editnumchangeamt()
	});
	$("#edithbNum").blur(function() {
		editnumchangeamt()
	});
	$("#btn-add-agent").bind("click", function(){
		setTimeout(btnaddagent(),1000);
	});

	initTable();

	// 初始化客户数据
	$('#merchCustId').chosen({
		no_results_text : "没有找到",
		placeholder_text : "请选择客户信息...",
		allow_single_de : true,
		search_contains : true
	});
	var url = 'order/util/customer?custType=3&type=1';
	$.get(url, function(data) {
		if (data.rows && data.rows.length > 0) {
			$('#merchCustId').html('');
			$('#merchCustId').append("<option></option>");
			customerdata = data.rows;
			for (var i = 0; i < data.rows.length; i++) {
				$('#merchCustId').append(
						"<option value='" + data.rows[i].id + "'>"
								+ data.rows[i].custname + "</option>");
			}
			$("#merchCustId").trigger("chosen:updated");
		}
	});

	// 初始化送达方
	$('#shipId').chosen({
		no_results_text : "没有找到",
		search_contains : true,
		allow_single_de : true
	});
	// 初始化物料选择
	$('#materialId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains : true,
		width : "100%"
	});
	// 初始化付款方
	$('#billto').chosen({
		no_results_text : "没有找到",
		placeholder_text : "请选择付款信息...",
		allow_single_de : true,
		search_contains : true
	});
	$("#deliveryType").change(function(){
		var deliveryType =$("#deliveryType").val();
		if(deliveryType =='2'){
			$("#freighthide").removeClass("hide");			
		}else{
			$("#freight").val('0');
			$("#freighthide").addClass("hide");	
		}
		$('#order-table').bootstrapTable("removeAll");
		lineamt = 0;
		linenum = 0;
		linehNum = 0;
		linehbamt = 0;
		lineorderamt = 0;
		linediscountamt = 0;
		$('#order-table').bootstrapTable('insertRow', {
			index : 0,
			row :  {
				"id" : '0',
				"sku" : "合计",
				"num" : "0",
				"amt" : '0',
				"hbNum":'0',
				"hbAmt":'0',
				"orderAmt" : '0',
				"weight":'0'
			}
		});
		$("#materialId").val("");
		$("#materialId").trigger("chosen:updated");
		cleanLine();
	})
	// 选择客户
	$("#merchCustId").change(
			function() {
				// 客户变更删除所有物料
				$('#order-table').bootstrapTable("removeAll");
				$("#logistics").html('');
				$("#site").html('');
				$("#contacter").html('');
				$("#mobile").html('');
				$("#address").html('');
				$("#cashAmt").html('');
				$("#subsidyAmt").html('');
				$("#creditAmt").html('');
				$("#bondAmt").html('');
				$("#allamt").html('');
				$("#shipId").empty();// 送达方清空
				lineamt = 0;
				linenum = 0;
				linehNum = 0;
				linehbamt = 0;
				lineorderamt = 0;
				linediscountamt = 0;
				$("#shipId").trigger("chosen:updated");
				$('#order-table').bootstrapTable('insertRow', {
					index : 0,
					row : {
						"id" : '0',
						"sku" : "合计",
						"num" : "0",
						"hbNum" : '0',
						"amt" : '0',
						"discountAmt" : '0',
						"orderAmt" : '0'
					}
				});
				cleanLine();
				var merchid = $("#merchCustId").val();
				// 获取对应的岗位ID，销售组织ID,货补比例
				for (var i = 0; i < customerdata.length; i++) {
					if (merchid == customerdata[i].id) {
						$("#stationid").val(customerdata[i].stationid);
						$("#orgid").val(customerdata[i].organizationId);
						$("#hbratio").val(customerdata[i].hbratio);
						$("#custPid").val(customerdata[i].pid);
						custType = customerdata[i].custType;
					}
				}
				// 获取送达方信息
				var url = 'order/util/customerShip2?merchid=' + merchid
						+ "&orgid=" + $("#orgid").val();
				$('#shipId').empty();
				$("#shipId").trigger("chosen:updated");
				$.get(url, function(data) {
					if (data.rows && data.rows.length > 0) {
						$('#shipId').append("<option></option>");
						for (var i = 0; i < data.rows.length; i++) {
							$('#shipId').append(
									"<option value='" + data.rows[i].id + "'>"
											+ data.rows[i].sapCustomerId + "-"
											+ data.rows[i].name + "</option>");
						}
						$("#shipId").trigger("chosen:updated");
					}
				});
				//获取付款方信息
				var billmerch="order/util/billCustomer?merchId="+merchid;
				$("#billto").empty();
				$("#billto").trigger("chosen:updated");
				$.get(billmerch,function(data){
					if(data.rows && data.rows.length >0){
						$('#billto').append("<option></option>");
						for (var i = 0; i < data.rows.length; i++) {
							$('#billto').append(
									"<option value='" + data.rows[i].id + "'>"
											+ data.rows[i].sapCustomerId + "-"
											+ data.rows[i].custname + "</option>");
						}
						$("#billto").trigger("chosen:updated");
					}
				});
				// 获取物料信息
				// 先清空
				$("#materialId").empty();
				$("#materialId").trigger("chosen:updated");
				var materialurl = "order/util/kaProduct?merchid=" + merchid
						+ "&orgid=" + $("#orgid").val();
				$.get(materialurl, function(data) {
					if (data.rows && data.rows.length > 0) {
						materialdata = data.rows;
						$("#materialId").append("<option value=''></option>");
						for (var i = 0; i < data.rows.length; i++) {
							//if (parseFloat(data.rows[i].unitprice) > 0) {
								$('#materialId').append(
										"<option value='"
												+ data.rows[i].materialId
												+ "'>"
												+ data.rows[i].materialId + '-'
												+ data.rows[i].sku
												+ "</option>");
							//}
						}
						$("#materialId").trigger("chosen:updated");
					}
				});

			})
	//获取资金数据
	$("#billto").change(function() {
		var accounturl = 'order/util/customerAccount?merchid='
			+ $("#billto").val() + "&orgid=" + $("#orgid").val();
		$.get(accounturl, function(data) {
			if (data.rows && data.rows.length > 0) {
				$("#cashAmt").html(data.rows[0].cashAmt.toFixed(2));
				$("#subsidyAmt").html(data.rows[0].subsidyAmt.toFixed(2));
				$("#creditAmt").html(data.rows[0].creditAmt.toFixed(2));
				$("#allamt").html(
					(data.rows[0].creditAmt
							+ data.rows[0].subsidyAmt
							+ data.rows[0].cashAmt).toFixed(2));
				$(".customAccount").removeClass("hide");
		}
	});
	});
	// 物料变更事件
	$("#materialId").change(function() {
		var deliveryType = $("#deliveryType").val();
		if(!deliveryType){
			$.messager.alert("请选择发货方式");
			return;
		}
		cleanLine();
		var materialid = $("#materialId").val();
		var rows = $table.bootstrapTable('getData');
		for (var i=0 ; i<rows.length;i++){
			if(rows[i].materialId == materialid){
				$.messager.popup("该物料行项目已经存在，请点击修改");
				cleanLine();
				$("#materialId").val("");
				$("#materialId").trigger("chosen:updated");
			}
		}
		for (var i = 0; i < materialdata.length; i++) {
			if (materialdata[i].materialId == materialid) {
				var price =0;
				if(deliveryType =="1"){
					price = amtChange((materialdata[i].hPrice+materialdata[i].basePrice+materialdata[i].cifPrice), materialdata[i].amounts, true).toFixed(4);
				}else{
					price = amtChange((materialdata[i].hPrice+materialdata[i].basePrice), materialdata[i].amounts, true).toFixed(4);
				}
				$("#materialName").val(materialdata[i].sku);// 物料描述
				$("#price").val(price);// 订单单价
				$("#orderPrice").val(price);
				$("#unit").val(materialdata[i].unit);// 物料基本价ID
				$("#highPrice").val((materialdata[i].hPrice).toFixed(4));// 物料高卖价
				$("#invnum").val((materialdata[i].invnum / materialdata[i].amounts).toFixed(3));// 物料库存
				$("#amounts").val(materialdata[i].amounts);
				$("#specifications").val(materialdata[i].specifications);
			}
		}
		
	});
	$("#btn-save").bind('click', doSave);
	$("#btn-save-audit").bind("click", doSaveAndAudit);
	$("#btn-edit-save").bind("click", doEditSave);
	$("#addKaOrderForm").ajaxForm({
		//target : '#btn-edit-save', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return true;
		},
		success : function(responseText, statusText, xhr, $form) {
			var result =  eval("("+responseText.data+")");  
			if (result.type == "500") {
				$.messager.alert("提示", "保存订单出错!"+data.code);
			}else if(result.type=='200'){
				if(flag=="1"){
					$.post("order/orderAudit", {"headerid":result.id,"states":"2","orderType":4}, function(res){
						if(res.data=="200"){
							window.location.href="order/index.html";
						}else{
							$.messager.alert("提示","保存成功，提交审批失败");
							window.location.href="order/index.html";
						}
					});
				}else{				
					$.messager.popup("保存订单成功!");
					window.location.href="order/index.html";
				}
			}
		}
	});
	
})
// 添加产品确认按钮事件
function btnaddagent() {
	if (!$("#materialId").val()) {
		$.messager.popup("请选择购买产品");
		return;
	}
	if($("#amt").val()==0) {
		validateNum();
	}
	if ($('#num').val() == 0 && $("#hbNum").val() == 0) {
		$.messager.popup("购买数量和货补数量为0，请填写购买数量");
		return;
	}
	if (parseFloat($("#price").val()) <= 0) {
		$.messager.popup("单价为0的订单不允许保存");
		return;
	}
	if (validateDecimal($('#num').val()) == false) {
		$.messager.popup("只能输入大于0的数字，输入小于三位小数");
		return;
	}
	if (validateDecimal($('#hbNum').val()) == false) {
		$.messager.popup("只能输入大于0的数字，输入小于三位小数");
		return;
	}
	var agentItem = {
		id : dataid,
		sku : $('#materialName').val(),
		materialId : $("#materialId").val(),
		unit : $('#unit').val(),
		price : $('#price').val(),
		orderPrice : $("#orderPrice").val(),
		highPrice : $('#highPrice').val(),
		num : $('#num').val(),
		hbNum : $("#hbNum").val(),
		amt : $("#amt").val(),
		hbAmt : $("#hbAmt").val(),
		discountAmt : $("#discountAmt").val(),
		orderAmt : $("#orderAmt").val(),
		amounts : $("#amounts").val(),
		specifications : $("#specifications").val()
	}
	addAgent(agentItem);
	$("#materialId").val("");
	$("#materialId").trigger("chosen:updated");
}
// 清空行数据
function cleanLine() {
	$('#materialName').val('');
	$('#unit').val('');
	$('#price').val('0');
	$('#highPrice').val('0');
	$('#num').val('0');
	$("#hbNum").val('0');
	$("#hbAmt").val('0');
	$("#amt").val('0');
	$("#discountAmt").val('0');
	$("#orderAmt").val('0');
	$("#orderPrice").val('0');
	$("#invnum").val('');
	$("#specifications").val('');
	$("#amounts").val('');
}
function addAgent(agent) {
	$table.bootstrapTable('insertRow', {
		index : 0,
		row : agent
	});
	dataid = dataid + 1;
	lineamt = parseFloat(lineamt) + parseFloat($("#amt").val());
	lineamt = lineamt.toFixed(2);
	
	linenum = parseFloat(linenum) + parseFloat($("#num").val());
	linenum = linenum.toFixed(3);
	
	linediscountamt = parseFloat(linediscountamt)
			+ parseFloat($("#discountAmt").val());
	linediscountamt = linediscountamt.toFixed(2);
	
	linehNum = parseFloat(linehNum) + parseFloat($("#hbNum").val());
	linehNum = linehNum.toFixed(3);
	
	lineorderamt = parseFloat(lineorderamt) + parseFloat($("#orderAmt").val());
	lineorderamt = lineorderamt.toFixed(2);
	
	linehbamt = parseFloat(linehbamt) + parseFloat($("#hbAmt").val());
	linehbamt = linehbamt.toFixed(2);
	
	changeTotalAndamt();
	cleanLine();
}

function changeTotalAndamt() {
	var totaldata = {
		"id" : '0',
		"sku" : "合计",
		"num" : linenum,
		"hbNum" : linehNum,
		"amt" : lineamt,
		"orderAmt" : lineorderamt,
		"hbAmt" : linehbamt,
		"discountAmt" : linediscountamt
	};
	$table.bootstrapTable('updateByUniqueId', {
		id : '0',
		row : totaldata
	});
	var cashAmt = $("#cashAmt").text();
	var subsidyAmt = $("#subsidyAmt").text();
	var creditAmt = $("#creditAmt").text();
	if (parseFloat(lineorderamt) < parseFloat(cashAmt)) {
		$("#XJamt").html(lineorderamt.toFixed(2));
	} else {
		$("#XJamt").html(cashAmt);
		$("#SXamt").html((lineorderamt - cashAmt).toFixed(2));
	}
	$("#HBamt").html(linehbamt);
}
function validateNum() {
	if (!$("#materialId").val()) {
		return;
	}
	if (validateDecimal($("#num").val()) == false) {
		$.messager.alert("提示", "数量只能输入大于0,并且小于三位小数");
		$("#num").val('0');
		$("#orderAmt").val('0');
		$("#amt").val($("#hbAmt").val());
		$("#discountAmt").val($("#hbAmt").val());
		//return;
	}
	if (validateDecimal($("#hbNum").val()) == false) {
		$.messager.alert("提示", "数量只能输入大于0,并且小于三位小数");
		$("#hbAmt").val('0');
		$("#hbNum").val('0');
		$("#discountAmt").val("0");
		$("#amt").val($("#orderAmt").val());
		//return;
	}
	if (parseFloat($("#invnum").val()) < (parseFloat($("#num").val()) + parseFloat($(
			"#hbNum").val()))) {
		return;
	}
}
function editvalidateNum() {
	if (validateDecimal($("#editnum").val()) == false) {
		$.messager.alert("提示", "数量只能输入大于0,并且小于三位小数");
		$("#editnum").val('0');
		$("#editorderAmt").val('0');
		$("#editamt").val($("#edithbAmt").val());
		$("#editdiscountAmt").val($("#edithbAmt").val());
		//return;
	}
	if (validateDecimal($("#edithbNum").val()) == false) {
		$.messager.alert("提示", "数量只能输入大于0,并且小于三位小数");
		$("#edithbAmt").val('0');
		$("#edithbNum").val('0');
		$("#editdiscountAmt").val("0");
		$("#editamt").val($("#editorderAmt").val());
	}
	var num = parseFloat($("#editnum").val())
			+ parseFloat($("#edithbNum").val());
	if (parseFloat($("#editinvnum").val()) < num) {
		return;
	}
	editnumchangeamt();
}
function numchangeamt() {
	var hbnum = parseFloat($("#hbNum").val());// 货补数量
	var num = parseFloat($("#num").val());// 订单数量
	if (validateDecimal($('#num').val()) == false) {
		num = 0;
	}
	if (validateDecimal($('#hbNum').val()) == false) {
		hbnum = 0;
	}
	var price = parseFloat($("#price").val());// 订单单价
	var orderprice = parseFloat($("#orderPrice").val());
	var amt = parseFloat(price * num);
	var hbamt = parseFloat(price * hbnum);
	var cashAmt = parseFloat($("#cashAmt").text());// 现金余额
	var creditAmt = parseFloat($("#creditAmt").text());// 授信余额
	var XJamt = parseFloat($("#XJamt").html());// 应扣现金金额
	var SXamt = parseFloat($("#SXamt").html());// 应扣授信金额
	if ((XJamt + SXamt + parseFloat(orderprice * num)) > (cashAmt + creditAmt)) {
		// 如果应扣现金金额和应扣授信金额加上当前订单金额超过现金和授信账户余额则提示余额不足
		$.messager.popup("可用余额不足!");
		$("#num").val("0");
		num = 0;
	}

	var HBamt = parseFloat($("#HBamt").html());// 应扣货补金额
	var subsidyAmt = parseFloat($("#subsidyAmt").text());// 货补账户金额
	if ((HBamt + parseFloat(hbnum * orderprice)) > subsidyAmt) {
		$.messager.popup("货补账户可用余额不足!");
		$("#hbNum").val('0');
		hbnum = 0;
	}
	// 订单金额
	$("#hbAmt").val((parseFloat(orderprice * hbnum)).toFixed(2));
	$("#orderAmt").val((parseFloat(orderprice * num)).toFixed(2));
	$("#amt").val((parseFloat($("#hbAmt").val())  + parseFloat($("#orderAmt").val())).toFixed(2));
	$("#discountAmt").val((parseFloat($("#amt").val()) - parseFloat($("#orderAmt").val())).toFixed(2));
}
function editnumchangeamt() {
	var hbnum = parseFloat($("#edithbNum").val());// 货补数量
	var num = parseFloat($("#editnum").val());// 订单数量
	if (validateDecimal($('#editnum').val()) == false) {
		num = 0;
	}
	if (validateDecimal($('#edithbNum').val()) == false) {
		hbnum = 0;
	}
	var price = parseFloat($("#editprice").val());// 订单单价
	var orderprice = parseFloat($("#editorderPrice").val());
	var amt = parseFloat(price * num);
	var hbamt = parseFloat(price * hbnum);
	var cashAmt = parseFloat($("#cashAmt").text());// 现金余额
	var creditAmt = parseFloat($("#creditAmt").text());// 授信余额
	var XJamt = parseFloat($("#XJamt").html());// 应扣现金金额
	var SXamt = parseFloat($("#SXamt").html());// 应扣授信金额
	if ((XJamt + SXamt - parseFloat(editrow[0].orderAmt) + parseFloat(orderprice
			* num)) > (cashAmt + creditAmt)) {
		// 如果应扣现金金额和应扣授信金额加上当前订单金额超过现金和授信账户余额则提示余额不足
		$("#editnum").val('0');
		num=0;
	}
	var HBamt = parseFloat($("#HBamt").html());// 应扣货补金额
	var subsidyAmt = parseFloat($("#subsidyAmt").text());// 货补账户金额
	if ((HBamt - editrow[0].hbAmt + parseFloat(hbnum * orderprice)) > subsidyAmt) {
		$("#edithbNum").val('0');
		hbnum=0;
	}
	// 订单金额
	$("#edithbAmt").val((parseFloat(orderprice * hbnum)).toFixed(2));
	$("#editorderAmt").val((parseFloat(orderprice * num)).toFixed(2));
	$("#editamt").val((parseFloat($("#edithbAmt").val())  + parseFloat($("#editorderAmt").val())).toFixed(2));
	$("#editdiscountAmt").val((parseFloat($("#editamt").val()) - parseFloat($("#editorderAmt").val())).toFixed(2));
}

function initTable() {
	$table.bootstrapTable({
		// url : 'static/table/new/data.json',
		data : data,
		idField : 'id',
		uniqueId : 'id',
		showFooter : false,
		clickToSelect : true,
		search : false,
		showRefresh : false,
		columns : [ {
			field : 'ck',
			radio : true,
			rowspan : 1,
			align : 'center',
			valign : 'middle'
		}, {
			title : '编号',
			field : 'id',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '产品名称',
			field : 'sku',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : false,
		}, {
			title : '物料编码',
			field : 'materialId',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true
		}, {
			title : '规格',
			field : 'specifications',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true
		}, {
			title : '箱内数量',
			field : 'amounts',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true
		}, {
			title : '单位',
			field : 'unit',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
		}, {
			title : '订价(元)',
			field : 'price',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
			visible : false
		}, {
			title : '高卖差价(元)',
			field : 'highPrice',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
			visible : false
		}, {
			title : '单价(元)',
			field : 'orderPrice',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true
		}, {
			title : '下单数量',
			field : 'num',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true
		}, {
			title : '销售政策',
			field : 'policy',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : true
		}, {
			title : '销售政策奖励',
			field : 'discountname',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : true
		}, {
			title : '销售政策头ID',
			field : 'policyHeaderId',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '销售政策行ID',
			field : 'policyLineId',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '政策奖励',
			field : 'policyDiscount',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '政策奖励力度',
			field : 'policyDiscountIntensity',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '政策奖励方式',
			field : 'policyVerfication',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '政策目标数',
			field : 'policyprimary',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '政策价格',
			field : 'policyprice',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '',
			field : 'policylimit',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '政策奖励金额',
			field : 'policyamt',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '货补数量',
			field : 'hbNum',
			rowspan : 1,
			align : 'center',
			valign : 'middle'
		}, {
			title : '货补金额',
			field : 'hbAmt',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '应收金额',
			field : 'amt',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true
		}, {
			title : '折扣金额',
			field : 'discountAmt',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true
		}, {
			title : '实收金额',
			field : 'orderAmt',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true
		} ]
	});
}

function doEdit() {
	var row = $table.bootstrapTable('getSelections');
	if (row && row.length > 0 && row[0].id != "0") {
		autoEdit(row[0]);
		editrow = row;
		// 获取当前可售库存
		var invnum = "order/util/invnum?materialId=" + row[0].materialId
				+ "&merchCustId=" + $("#merchCustId").val() + "&orgid="
				+ $("#orgid").val();
		$.get(invnum, function(data) {
			if (data.data) {
				$("#editinvnum").val(data.data);
			}
		})
		$("#editLineModal").modal("show");
	} else {
		$.messager.alert("请选择要出合计外的行。");
	}
}
function doEditSave() {
	var hbnum = parseFloat($("#edithbNum").val());// 货补数量
	var num = parseFloat($("#editnum").val());// 订单数量
	if (validateDecimal($("#editnum").val()) == false) {
		$.messager.popup("只能输入大于0数字，输入小于三位小数");
		return;
	}
	if (validateDecimal($("#edithbNum").val()) == false) {
		$.messager.popup("只能输入大于0数字，输入小于三位小数");
		return;
	}
	if (num == 0 && hbnum == 0) {
		$.messager.popup("两个数量不能同时为0");
		return;
	}
	$table.bootstrapTable('remove', {
		field : 'id',
		values : [ editrow[0].id ]
	});
	lineamt = parseFloat(lineamt) - parseFloat(editrow[0].amt);
	lineamt = lineamt.toFixed(2);
	
	linenum = parseFloat(linenum) - parseFloat(editrow[0].num);
	linenum = linenum.toFixed(3);
	
	linediscountamt = parseFloat(linediscountamt)
			- parseFloat(editrow[0].discountAmt);
	linediscountamt = linediscountamt.toFixed(2);
	
	linehNum = parseFloat(linehNum) - parseFloat(editrow[0].hbNum);
	linehNum = linehNum.toFixed(3);
	
	lineorderamt = parseFloat(lineorderamt) - parseFloat(editrow[0].orderAmt);
	lineorderamt = lineorderamt.toFixed(2);
	
	linehbamt = parseFloat(linehbamt) - parseFloat(editrow[0].hbAmt);
	linehbamt = linehbamt.toFixed(2);
	
	changeTotalAndamt();
	var agentItem = {
		id : dataid,
		sku : $('#editsku').val(),
		materialId : $("#editmaterialId").val(),
		unit : $('#editunit').val(),
		price : $('#editprice').val(),
		orderPrice : $("#editorderPrice").val(),
		highPrice : $('#edithighPrice').val(),
		num : $('#editnum').val(),
		hbNum : $("#edithbNum").val(),
		amt : $("#editamt").val(),
		hbAmt : $("#edithbAmt").val(),
		discountAmt : $("#editdiscountAmt").val(),
		orderAmt : $("#editorderAmt").val(),
		amounts : $("#editamounts").val(),
		specifications : $("#editspecifications").val()
	}
	$table.bootstrapTable('insertRow', {
		index : 0,
		row : agentItem
	});
	dataid = dataid + 1;
	lineamt = parseFloat(lineamt) + parseFloat($("#editamt").val());
	lineamt = lineamt.toFixed(2);

	linenum = parseFloat(linenum) + parseFloat($("#editnum").val());
	linenum = linenum.toFixed(3);

	linediscountamt = parseFloat(linediscountamt)
			+ parseFloat($("#editdiscountAmt").val());
	linediscountamt = linediscountamt.toFixed(2);

	linehNum = parseFloat(linehNum) + parseFloat($("#edithbNum").val());
	linehNum = linehNum.toFixed(3);

	lineorderamt = parseFloat(lineorderamt)
			+ parseFloat($("#editorderAmt").val());
	lineorderamt = lineorderamt.toFixed(2);

	linehbamt = parseFloat(linehbamt) + parseFloat($("#edithbAmt").val());
	linehbamt = linehbamt.toFixed(2);
	changeTotalAndamt();
	$("#editLineModal").modal("hide");
}
function doDelLine() {
	var row = $table.bootstrapTable('getSelections');
	if (row && row.length > 0 && row[0].id != 0) {
		$table.bootstrapTable('remove', {
			field : 'id',
			values : [ row[0].id ]
		});
		$("#del-bstb-row").prop('disabled', true);

		lineamt = parseFloat(lineamt) - parseFloat(row[0].amt);
		lineamt = lineamt.toFixed(2);
		
		linenum = parseFloat(linenum) - parseFloat(row[0].num);
		linenum = linenum.toFixed(3);
		
		linediscountamt = parseFloat(linediscountamt)
				- parseFloat(row[0].discountAmt);
		linediscountamt = linediscountamt.toFixed(2);
		
		linehNum = parseFloat(linehNum) - parseFloat(row[0].hbNum);
		linehNum = linehNum.toFixed(3);
		
		lineorderamt = parseFloat(lineorderamt) - parseFloat(row[0].orderAmt);
		lineorderamt = lineorderamt.toFixed(2);
		
		linehbamt = parseFloat(linehbamt) - parseFloat(row[0].hbAmt);
		linehbamt = linehbamt.toFixed(2);
		changeTotalAndamt();
		/*
		 * $table.bootstrapTable('remove', { field : 'id', values : [ row[0].id ] //
		 * must set the delete col id by our data col id. });
		 */
	} else {
		$.messager.alert("请选择要出合计外的行。");
	}
}

// 保存销售订单
function doSave() {
	var data = $table.bootstrapTable('getData');
	var merchCustId = $("#merchCustId").val();
	var shipId = $("#shipId").val();
	var rdcCode = $("#rdcCode").val();
	var othersOrderId =$("#othersOrderId").val();
	var deliveryType =$("#deliveryType").val();
	var freight =$("#freight").val();
	if (!merchCustId) {
		$.messager.alert("客户不能为空");
		return;
	}
	if (!shipId) {
		$.messager.alert("送达方不能为空");
		return;
	}
	if (data.length < 2) {
		$.messager.alert("订单行不能为空");
		return;
	}
	if(!othersOrderId){
		$.messager.alert("对方订单编号不能为空");
		return;
	}
	if(!deliveryType){
		$.messager.alert("发货方式不能为空");
		return ;
	}
	if(deliveryType=='2' && !freight){
		$.messager.alert("不包邮，请输入运费金额");
		return ;
	}
	if(deliveryType=='2' && freight){
		if (!/^0$|(^(-?)(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$)/.test(freight) || parseFloat(freight) <=0 ) {
			$.messager.alert("运费只能保留大于0的2位小数");
			return ;
		}
	}
	var stationid = $("#stationid").val();
	var orgid = $("#orgid").val();
	var hbratio = $("#hbratio").val();
	var XJamt = parseFloat($("#XJamt").html());// 应扣现金金额
	var SXamt = parseFloat($("#SXamt").html());// 应扣授信金额
	var HBamt = parseFloat($("#HBamt").html());// 应扣货补金额
	if (hbratio) {
		if (parseFloat(HBamt) / parseFloat(XJamt + SXamt + HBamt) > parseFloat(hbratio / 100)) {
			$.messager.alert("提示", "货补金额不能大于" + hbratio + "%");
			return;
		}
	}
	for (var i = 0; i < data.length; i++) {
		if (data[i].sku != '合计') {
			data[i].num = data[i].amounts * data[i].num;
			data[i].hbNum = data[i].amounts*data[i].hbNum;
			data[i].price = (data[i].price / data[i].amounts).toFixed(4);
			data[i].orderPrice = (data[i].orderPrice / data[i].amounts).toFixed(4);
		}else{
			data.splice(i,1);
		}
	}
	$("#lines").val(JSON.stringify(data));
	$("#addKaOrderForm").attr("action","order/addOrder").submit();
}
function validateDecimal(value) {
	var ir = /^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,3})?$)/;
	return ir.test(value);
}
function doSaveAndAudit() {
	flag = '1';
	doSave();
}

/**
 * 添加待上传的文件
 */
function addFiles() {
	var newFileId = '' + new Date().getTime() + Math.floor(Math.random() * 10);
	var fileBox = $("#file-select");
	var fileBoxNew = fileBox.clone();
	fileBox.after(fileBoxNew);
	var file = fileBox.prop('files')[0];
	var filePath = fileBox.val()
	var fileName = file.name;
	//upfiles = upfiles.push(0, filePath);
	fileBox.attr("id", "file_" + newFileId).removeAttr("onchange").attr("name",
			"files");
	fileBox.hide();
	if (file) {
		var item = '<li>'
				+ '<span class="file-name" data-file-src="'
				+ filePath
				+ '">'
				+ fileName
				+ '</span>'
				+ '<a href="javascript:;" class="pull-right del-file" id="del_file_'
				+ newFileId
				+ '" title="删除附件"><i class="icon icon-remove"></i></a>'
				+ $('<div>').append().html() + '</li>';
		$(".file-list").append(item);

	} else {
		$.messager.alert('文件读取错误。');
	}
	checkFile();
}
function checkFile() {
	var fileList = $(".file-list>li");
	if (fileList.length > 0) {
		$(".file-list-box").fadeIn();
	} else {
		$(".file-list-box").fadeOut();
	}
}
function amtChange(price, amounts, isMulti){
	if(isMulti){
		return (parseFloat(price)*parseFloat(amounts));
	}
	return (parseFloat(price)/parseFloat(amounts));
}
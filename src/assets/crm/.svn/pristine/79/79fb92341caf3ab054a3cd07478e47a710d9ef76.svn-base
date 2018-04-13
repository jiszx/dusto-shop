var $table = $('#order-table');
var editdata;
var editpolicydata;
$(function() {
	initTable();
	var materialdata;
	var policydata;
	// 获取用户当前的资金情况
	var account_url = "order/util/customerAccount?merchid="
			+ $("#merchCustId").val() + "&orgid=" + $("#orgid").val();
	$.get(account_url, function(data) {
		if (data.rows && data.rows.length == 1) {
			$("#cashAmt").html(data.rows[0].cashAmt);
			$("#subsidyAmt").html(data.rows[0].subsidyAmt);
			$("#creditAmt").html(data.rows[0].creditAmt);
			$("#allamt").html(
					data.rows[0].creditAmt + data.rows[0].subsidyAmt
							+ data.rows[0].cashAmt);
			changeKJamt();
		}
	});
	
	// 初始化物料选择
	/*$('#material').chosen({
		no_results_text : "没有找到",
		allow_single_de : true
	});*/
	// 获取用户的产品信息
	$("#material").empty();
	var materialurl = "order/util/custProduct?merchid="
			+ $("#merchCustId").val() + "&orgid=" + $("#orgid").val();
	$.get(materialurl, function(data) {
		if (data.rows && data.rows.length > 0) {
			materialdata = data.rows;
			$("#material").append("<option value=''></option>");
			$("#editmaterialId").append("<option value=''></option>");
			for (var i = 0; i < data.rows.length; i++) {
				$('#material').append("<option value='" + data.rows[i].materialId + "'>"+ data.rows[i].sku + "</option>");
				$('#editmaterialId').append("<option value='" + data.rows[i].materialId + "'>"+ data.rows[i].sku + "</option>");
			}
			/*$("#material").trigger("chosen:updated");
			$('#material').chosen({
				no_results_text : "没有找到",
				allow_single_de : true
			});*/
		}
	});
	$("#material").change(
			function() {
				$('#policy').html('');
				var materialid = $("#material").val();
				for (var i = 0; i < materialdata.length; i++) {
					if (materialdata[i].materialId == materialid) {
						$("#materialName").val(materialdata[i].sku);// 物料描述
						$("#price").val(materialdata[i].unitprice);// 订单单价
						$("#unit").val(materialdata[i].unit);// 物料基本价ID
						$("#wlPrice").val(materialdata[i].price);// 物料物流价
						$("#hPrice").val(materialdata[i].hPrice);// 物料高卖价
					}
				}
				// 获取物料的销售政策信息
				var merchid = $("#merchCustId").val();
				var orderpolicy = "order/util/orderPolicy?merchid=" + merchid
						+ "&materialid=" + materialid + "&orgid="
						+ $("#orgid").val();
				$.get(orderpolicy, function(data) {
					if (data.rows && data.rows.length > 0) {
						policydata = data.rows;
						$('#policy').append("<option></option>");
						for (var i = 0; i < data.rows.length; i++) {
							$('#policy').append(
									"<option value='" + data.rows[i].headerId
											+ "'>" + data.rows[i].description
											+ "</option>");
						}
					}
				});

			});
	$("#editmaterialId").change(
			function() {
				$('#editpolicy').html('');
				var materialid = $("#editmaterial").val();
				for (var i = 0; i < materialdata.length; i++) {
					if (materialdata[i].materialId == materialid) {
						$("#editsku").val(materialdata[i].sku);// 物料描述
						$("#editprice").val(materialdata[i].unitprice);// 订单单价
						$("#editunit").val(materialdata[i].unit);// 物料基本价ID
						$("#editwlPrice").val(materialdata[i].price);// 物料物流价
						$("#edithPrice").val(materialdata[i].hPrice);// 物料高卖价
					}
				}
				// 获取物料的销售政策信息
				var merchid = $("#merchCustId").val();
				var orderpolicy = "order/util/orderPolicy?merchid=" + merchid
						+ "&materialid=" + materialid + "&orgid="
						+ $("#orgid").val();
				$.get(orderpolicy, function(data) {
					if (data.rows && data.rows.length > 0) {
						editpolicydata = data.rows;
						$('#editpolicy').append("<option></option>");
						for (var i = 0; i < data.rows.length; i++) {
							$('#editpolicy').append("<option value='" + data.rows[i].headerId+ "'>" + data.rows[i].description+ "</option>");
						}
					}
				});

			});
	$("#policy")
			.change(
					function() {
						var policyid = $("#policy").val();
						$("#discountname").val('');
						$("#policyDiscountIntensity").val('');
						for (var i = 0; i < policydata.length; i++) {
							if (policydata[i].headerId == policyid) {
								$("#discountname").val(
										policydata[i].discountname);
								if (policydata[i].verification != '1'
										&& policydata[i].verification != '5') {
									$('#policyDiscountIntensity').val(
											policydata[i].discountIntensity
													+ "%");
								} else {
									$('#policyDiscountIntensity').val(
											policydata[i].discountIntensity);
								}
								$("#policyHeaderId")
										.val(policydata[i].headerId);
								$("#policyLineId").val(policydata[i].lineid);
								$("#policyVerfication").val(
										policydata[i].verification);
								$("#policyDiscount")
										.val(policydata[i].discount);
								$("#policyprimary").val(policydata[i].primary);
								// 获取销售政策折扣
								getdiscount();
							}
						}
					});
	$("#editpolicy")
	.change(
			function() {
				var policyid = $("#editpolicy").val();
				$("#editpolicydiscountname").val('');
				$("#editpolicyDiscountIntensity").val('');
				for (var i = 0; i < editpolicydata.length; i++) {
					if (editpolicydata[i].headerId == policyid) {
						$("#editpolicydiscountname").val(
								editpolicydata[i].discountname);
						if (editpolicydata[i].verification != '1'
								&& editpolicydata[i].verification != '5') {
							$('#editpolicyDiscountIntensity').val(
									editpolicydata[i].discountIntensity
											+ "%");
						} else {
							$('#editpolicyDiscountIntensity').val(
									editpolicydata[i].discountIntensity);
						}
						$("#editpolicyHeaderId")
								.val(editpolicydata[i].headerId);
						$("#editpolicyLineId").val(editpolicydata[i].lineid);
						$("#editpolicyVerfication").val(
								editpolicydata[i].verification);
						$("#editpolicyDiscount")
								.val(editpolicydata[i].discount);
						$("#editpolicyprimary").val(editpolicydata[i].primary);
						// 获取销售政策折扣
						editgetdiscount();
					}
				}
			});
	$("#btn-save").bind('click', doSave);
	$("#btn-save-audit").bind("click", doSaveAndAudit);
	$("#btn-del").bind("click",doDel);
	$("#btn-edit").bind("click",doEdit);
	var addValidator = initValidate("#addlineForm");
	var editValidator = initValidate("#editlineForm");
	$("#addlineForm").ajaxForm({
		target : '#btn-add-save', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return addValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("修改信息失败",responseText.errorMessage);
            }else{
            	//更新扣减金额
            	changeKJamt();
            	$("#addLineModal").modal("hide");
    			$("#order-table").bootstrapTable("refresh")
    			$.messager.popup("添加SKU成功");
            }
			$("#addLineModal").modal("hide");
			$("#order-table").bootstrapTable("refresh")
		}
	});
	$("#editlineForm").ajaxForm({
		target : '#btn-edit-save', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return editValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("修改信息失败",responseText.errorMessage);
            }else{
            	//更新扣减金额
            	changeKJamt();
            	$("#editLineModal").modal("hide");
    			$("#order-table").bootstrapTable("refresh")
    			$.messager.popup("修改SKU成功");
            }
			$("#editLineModal").modal("hide");
			$("#order-table").bootstrapTable("refresh")
		}
	});
})
function changeKJamt(){
	$("#HBamt").html('0');
	$("#XJamt").html('0');
	$("#SXamt").html('0');
	var orderamturl ="order/util/orderAmt?id="+$("#orderid").val();
	$.get(orderamturl, function(data) {
		if (data.rows && data.rows.length == 1) {
			$("#HBamt").html(data.rows[0].hbAmt);
			var cashAmt = $("#cashAmt").text();
			if (parseFloat(data.rows[0].orderAmt) < parseFloat(cashAmt)) {
				$("#XJamt").html(data.rows[0].orderAmt);
			} else {
				$("#XJamt").html(cashAmt);
				$("#SXamt").html(parseFloat(data.rows[0].orderAmt)-cashAmt);
			}
			//修改销售订单金额
			var url ="order/updateOrderAmt?id="+$("#orderid").val();
			$.get(url, function(data) {
				if (data.rows && data.rows.length == 1) {
					
				}
			});
		}
	});
}
function initTable() {
	$table.bootstrapTable({
		url : 'order/util/linedata?headerid='+$("#orderid").val(),
		method : 'get',
		classes : "table table-hover table-condensed",
		// data:data,
		cache : false,
		toolbar : "#upAccountTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "payName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		// search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : 'state',
			checkbox : true,
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
			visible : false
		}, {
			title : '单位',
			field : 'unit',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
		}, {
			title : '单价(元)',
			field : 'price',
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
			field : 'policyname',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : true
		}, {
			title : '销售政策奖励',
			field : 'policydiscountname',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : true
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
			title : '货补金额',
			field : 'hbamt',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
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

function doDel(){
	var rows = $("#order-table").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
			$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
				$.post("order/edit/delLine", {
					"id" : rows[0].id
				}, function() {
					changeKJamt();
					$("#order-table").bootstrapTable("refresh");
				})
			});
	} else {
		$.messager.alert("提示", "请选择要删除的记录!");
	}
}
function doEdit(){
	var rows = $("#order-table").bootstrapTable("getSelections");
	if (rows && rows.length == 1 &&rows[0].id!=0) {
		$('#editpolicy').html('');
		var merchid = $("#merchCustId").val();
		var orderpolicy = "order/util/orderPolicy?merchid=" + merchid
				+ "&materialid=" + rows[0].materialId + "&orgid="
				+ $("#orgid").val();
		$.get(orderpolicy, function(data) {
			if (data.rows && data.rows.length > 0) {
				editpolicydata = data.rows;
				$('#editpolicy').append("<option></option>");
				for (var i = 0; i < data.rows.length; i++) {
					$('#editpolicy').append("<option value='" + data.rows[i].headerId+ "'>" + data.rows[i].description+ "</option>");
				}
				editdata = rows[0];
				$('#editpolicy').val(rows[0].policyHeaderId);
				autoEdit(rows[0]);
				$("#editLineModal").modal("show")
			}else{
				autoEdit(rows[0]);
				$("#editLineModal").modal("show")
			}
		});
	} else {
		$.messager.alert("提示", "请选择要修改的记录!");
	}
}
function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			materialId : "required",
			num : {
				required : true,
				number : true
			}
		}
	});
	return validator;
}
function numchangeamt(){
	var hbnum =parseFloat($("#hNum").val());//货补数量
	var num =parseFloat($("#num").val());//订单数量
	var price =parseFloat($("#price").val());//订单单价
	var orderprice =parseFloat($("#orderprice").val());
	var amt = parseFloat(price*num);
	var hbamt = parseFloat(price*hbnum);
	var policyVerfication =$("#policyVerfication").val();
	var policyprimary =$("#policyprimary").val();
	var discountamt =0;
	var policyamt =0;
	if(policyVerfication=='1' || policyVerfication=='5'){
		 if(parseFloat(policyprimary)<=parseFloat(hbnum+num)){
			 //搭赠目标数量小于下单和货补数量			 
			 policyamt = $("#policyamt").val();
			 if(!policyamt){
				 policyamt =0;
			 }
		 }else{
			 $.messager.alert("提示", "购买数量不足"+policyprimary+",不能享受该政策");
		 }
		 orderprice =price;
		 //$("#orderprice").val(orderprice);
	}else if(policyVerfication=='2'){
		//价格折扣销售政策
		var policyprice =$("#policyprice").val();
		if(policyprice){
			orderprice = parseFloat(policyprice);
			policyamt = parseFloat(parseFloat(price)-parseFloat(policyprice))*parseFloat(num+hbnum);
			//$("#orderprice").val(orderprice);			
		}else{
			orderprice =price;
		}
	}else{
		orderprice =price;
	}
	$("#orderprice").val(orderprice);
	var cashAmt = parseFloat($("#cashAmt").text());//现金余额
	var creditAmt = parseFloat($("#creditAmt").text());//授信余额
	var XJamt = parseFloat($("#XJamt").html());//应扣现金金额
	var SXamt = parseFloat($("#SXamt").html());//应扣授信金额
	if ((XJamt + SXamt + parseFloat(orderprice * num)) > (cashAmt + creditAmt)) {
		//如果应扣现金金额和应扣授信金额加上当前订单金额超过现金和授信账户余额则提示余额不足
		$.messager.alert("提示", "可用余额不足!");
		return;
	}
	
	var HBamt = parseFloat($("#HBamt").html());//应扣货补金额
	var subsidyAmt = parseFloat($("#subsidyAmt").text());//货补账户金额
	if ((HBamt + parseFloat(hbnum * orderprice)) > subsidyAmt) {
		$.messager.alert("提示", "货补账户可用余额不足!");
		$("#hNum").val('0');
		return;
	}
	//订单金额
	$("#hbamt").val(parseFloat(orderprice*hbnum));
	//discountamt =parseFloat(orderprice*hbnum)+parseFloat(policyamt);
	$("#amt").val(parseFloat(orderprice*num)+parseFloat(policyamt)+parseFloat(orderprice*hbnum));
	$("#orderAmt").val(parseFloat(orderprice*num));
	$("#discountAmt").val(parseFloat($("#amt").val())-parseFloat($("#orderAmt").val()));
	
}
//搭赠促销品返回单价
function getdiscount() {
	$("#policyprice").val('');
	$("#policyamt").val('');
	var merchid = $("#merchCustId").val();
	var orgid = $("#orgid").val();
	var policyVerfication=$("#policyVerfication").val();
	var policyDiscount =$("#policyDiscount").val();
	var policyDiscountIntensity =$("#policyDiscountIntensity").val();
	if (policyVerfication == '1' || policyVerfication == '5') {
		
		var url = "order/util/policydiscount?discountMaterialid="
				+ policyDiscount + "&merchid=" + merchid + "&type="
				+ policyVerfication + "&orgid=" + orgid;
		$.get(url, function(data) {
			if (data.data) {
				$("#policyamt").val(parseFloat(data.data)* parseFloat(policyDiscountIntensity));
				numchangeamt();
			}
		});
	}else if(policyVerfication== '2' ){
		//价格折扣
		var discountratio = policyDiscountIntensity;//折扣比列
		var price =parseFloat($("#price").val());
		var policyprice =parseFloat(price)*parseFloat(discountratio)/100;//折扣后单价
		$("#policyprice").val(policyprice);
		numchangeamt();
	}else{
		numchangeamt();
	}
}

function editnumchangeamt(){
	var hbnum =parseFloat($("#edithbNum").val());//货补数量
	var num =parseFloat($("#editnum").val());//订单数量
	var price =parseFloat($("#editprice").val());//订单单价
	var orderprice =parseFloat($("#editorderprice").val());
	var amt = parseFloat(price*num);
	var hbamt = parseFloat(price*hbnum);
	var policyVerfication =$("#editpolicyVerfication").val();
	var policyprimary =$("#editpolicyprimary").val();
	var discountamt =0;
	var policyamt =0;
	if(policyVerfication=='1' || policyVerfication=='5'){
		 if(parseFloat(policyprimary)<=parseFloat(hbnum+num)){
			 //搭赠目标数量小于下单和货补数量			 
			 policyamt = $("#editpolicyamt").val();
			 if(!policyamt){
				 policyamt =0;
			 }
		 }else{
			 $.messager.alert("提示", "购买数量不足"+policyprimary+",不能享受该政策");
			 $("#editpolicy").val('');
			 $("#editpolicyHeaderId").val('');
			 $("#editpolicyLineId").val('');
			 $("#editpolicyVerfication").val('');
			 $("#editpolicyDiscount").val('');
			 $("#editpolicyprimary").val('');
			 $("#editpolicyamt").val('');
			 $("#editpolicyprice").val('');
			 $("#editpolicydiscountname").val('');
			 $("#editpolicyDiscountIntensity").val('');
		 }
		 orderprice =price;
		 //$("#orderprice").val(orderprice);
	}else if(policyVerfication=='2'){
		//价格折扣销售政策
		var policyprice =$("#editpolicyprice").val();
		if(policyprice){
			orderprice = parseFloat(policyprice);
			policyamt = parseFloat(parseFloat(price)-parseFloat(policyprice))*parseFloat(num+hbnum);
			//$("#orderprice").val(orderprice);			
		}else{
			orderprice =price;
		}
	}else{
		orderprice =price;
	}
	$("#editorderprice").val(orderprice);
	var cashAmt = parseFloat($("#cashAmt").text());//现金余额
	var creditAmt = parseFloat($("#creditAmt").text());//授信余额
	var XJamt = parseFloat($("#XJamt").html());//应扣现金金额
	var SXamt = parseFloat($("#SXamt").html());//应扣授信金额
	if ((XJamt + SXamt-editdata.orderAmt + parseFloat(orderprice * num)) > (cashAmt + creditAmt)) {
		//如果应扣现金金额和应扣授信金额加上当前订单金额超过现金和授信账户余额则提示余额不足
		$.messager.alert("提示", "可用余额不足!");
		return;
	}
	var HBamt = parseFloat($("#HBamt").html());//应扣货补金额
	var subsidyAmt = parseFloat($("#subsidyAmt").text());//货补账户金额
	if ((HBamt-parseFloat(editdata.hbAmt) + parseFloat(hbnum * orderprice)) > subsidyAmt) {
		$.messager.alert("提示", "货补账户可用余额不足!");
		$("#edithbNum").val('0');
		return;
	}
	//订单金额
	$("#edithbamt").val(parseFloat(orderprice*hbnum));
	//discountamt =parseFloat(orderprice*hbnum)+parseFloat(policyamt);
	$("#editamt").val(parseFloat(orderprice*num)+parseFloat(policyamt)+parseFloat(orderprice*hbnum));
	$("#editorderAmt").val(parseFloat(orderprice*num));
	$("#editdiscountAmt").val(parseFloat($("#editamt").val())-parseFloat($("#editorderAmt").val()));
}
//搭赠促销品返回单价
function editgetdiscount() {
	$("#editpolicyprice").val('');
	$("#editpolicyamt").val('');
	var merchid = $("#merchCustId").val();
	var orgid = $("#orgid").val();
	var policyVerfication=$("#editpolicyVerfication").val();
	var policyDiscount =$("#editpolicyDiscount").val();
	var policyDiscountIntensity =$("#editpolicyDiscountIntensity").val();
	if (policyVerfication == '1' || policyVerfication == '5') {
		var url = "order/util/policydiscount?discountMaterialid="
				+ policyDiscount + "&merchid=" + merchid + "&type="
				+ policyVerfication + "&orgid=" + orgid;
		$.get(url, function(data) {
			if (data.data) {
				$("#editpolicyamt").val(parseFloat(data.data)* parseFloat(policyDiscountIntensity));
				editnumchangeamt();
			}
		});
	}else if(policyVerfication== '2' ){
		//价格折扣
		var discountratio = policyDiscountIntensity;//折扣比列
		var price =parseFloat($("#editprice").val());
		var policyprice =parseFloat(price)*parseFloat(discountratio)/100;//折扣后单价
		$("#editpolicyprice").val(policyprice);
		editnumchangeamt();
	}else{
		editnumchangeamt();
	}
}
function doSave(){
	window.location.href = "order/index.html";
}

function doSaveAndAudit(){
	$.messager.confirm("警告", "您确认要提交此销售订单吗?", function() {
		$.post("order/orderAudit", {
			"headerid" : $("#orderid").val(),
			"states":"2"
		}, function() {
			window.location.href = "order/index.html";
		})
	});
}
var $table = $('#order-table');
var editdata;
var editpolicydata;
var shipdata;
$(function() {
	$.validator.addMethod("editorderamt",function(value,element){
		var num =parseFloat(value);
		var cashAmt = parseFloat($("#cashAmt").text());//现金余额
		var creditAmt = parseFloat($("#creditAmt").text());//授信余额
		var XJamt = parseFloat($("#XJamt").html());//应扣现金金额
		var SXamt = parseFloat($("#SXamt").html());//应扣授信金额
		var orderPrice =parseFloat($("#editprice").val());
		if ((XJamt -editdata.orderAmt+ SXamt + parseFloat(orderPrice * num)) > (cashAmt + creditAmt)) {
			return false;
		}else{
			return true;
		}
		
	},"可用资金不足");
	
	$.validator.addMethod("edithbamt",function(value,element){
		var num =parseFloat(value);
		var HBamt = parseFloat($("#HBamt").html());//应扣货补金额
		var orderPrice =parseFloat($("#editprice").val());
		var subsidyAmt = parseFloat($("#subsidyAmt").text());//货补账户金额
		if ((HBamt -parseFloat(editdata.hbAmt)+ parseFloat(num * orderPrice)) > subsidyAmt) {
			return false;
		}else{
			return true;
		}
		
	},"货补可用余额不足");
	initTable();
	var materialdata;
	$("#num").blur(function(){
		validateNum();
	});
	$("#hbNum").blur(function(){
		validateNum();
	});
	$("#editnum").blur(function(){
		editvalidateNum();
	});
	$("#edithbNum").blur(function(){
		editvalidateNum();
	});
	// 获取用户当前的资金情况
	var account_url = "order/util/customerAccount?merchid="
			+ $("#merchCustId").val() + "&orgid=" + $("#orgid").val();
	$.get(account_url, function(data) {
		if (data.rows && data.rows.length == 1) {
			$("#cashAmt").html(data.rows[0].cashAmt);
			$("#subsidyAmt").html(data.rows[0].subsidyAmt);
			$("#creditAmt").html(data.rows[0].creditAmt);
			$("#allamt").html(
					(data.rows[0].creditAmt + data.rows[0].subsidyAmt
							+ data.rows[0].cashAmt).toFixed(2));
			changeKJamt();
		}
	});
	$('#shipId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"80%"
	});
	$('#rdcCode').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
	});
	$('#materialId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	/*$('#editmaterialId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});*/
	
	// 获取用户的产品信息
	$("#materialId").empty();
	$("#materialId").trigger("chosen:updated");
	var materialurl = "order/util/custProductByRdc?merchid="
			+ $("#merchCustId").val() + "&orgid=" + $("#orgid").val()+"&rdcCode="+$("#rdcCode").val();
	$.get(materialurl, function(data) {
		if (data.rows && data.rows.length > 0) {
			materialdata = data.rows;
			$("#materialId").append("<option value=''></option>");
			$("#editmaterialId").append("<option value=''></option>");
			for (var i = 0; i < data.rows.length; i++) {
				//if(parseFloat(data.rows[i].unitprice)>0){
					$('#materialId').append("<option value='" + data.rows[i].materialId + "'>"+ data.rows[i].materialId +'-'+ data.rows[i].sku + "</option>");
					$('#editmaterialId').append("<option value='" + data.rows[i].materialId + "'>"+ data.rows[i].materialId +'-'+ data.rows[i].sku + "</option>");
				//}
			}
			$("#materialId").trigger("chosen:updated");
			//$("#editmaterialId").trigger("chosen:updated");
		}
	});
	$("#materialId").change(function() {
		var materialid = $("#materialId").val();
		if(!materialid){
			cleanLine();
			return;
		}
		var rows = $("#order-table").bootstrapTable('getData');
		for (var i=0 ; i<rows.length;i++){
			if(rows[i].materialId == materialid){
				$.messager.alert("该物料行项目已经存在，请点击修改");
				return;
			}
		}
		var deliveryType = $("#deliveryType").val();
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
				$("#unit").val(materialdata[i].unit);// 物料单位
				$("#wlPrice").val(materialdata[i].price);// 物料物流价
				$("#hPrice").val(materialdata[i].hPrice.toFixed(4));// 物料高卖价
				$("#invnum").val(((materialdata[i].invnum?materialdata[i].invnum:0)/materialdata[i].amounts).toFixed(3));
				$("#amounts").val(materialdata[i].amounts);
				$("#specifications").val(materialdata[i].specifications);
			}
		}

	});
	$("#editmaterialId").change(function() {
		$('#editpolicy').html('');
		var materialid = $("#editmaterialId").val();
		for (var i = 0; i < materialdata.length; i++) {
			if (materialdata[i].materialId == materialid) {
				
				$("#editsku").val(materialdata[i].sku);// 物料描述
				$("#editprice").val(materialdata[i].unitprice);// 订单单价
				$("#editunit").val(materialdata[i].unit);// 物料基本价ID
				$("#editwlPrice").val(materialdata[i].price);// 物料物流价
				$("#edithPrice").val(materialdata[i].hPrice);// 物料高卖价
			}
		}

	});
	$("#btn-save").bind('click', doSave);
	$("#btn-save-audit").bind("click", doSaveAndAudit);
	$("#btn-del").bind("click",doDel);
	$("#btn-edit").bind("click",doEdit);
	var addValidator = initValidate("#addlineForm");
	var editValidator = editinitValidate("#editlineForm");
	$("#addlineForm").ajaxForm({
		target : '#btn-add-save', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			validateNum();
			if($("#num").val()==0 && $("#hbNum").val()==0){
				$.messager.alert("数量，货补数量同时为0");
				return flase;
			}
			var amounts = 0;
			$.each(formData, function(i, val){
				if(val.name=='amounts'){
					amounts = val.value;
				}
			});
			$.each(formData, function(i, val){
				if(val.name=='price'){
					val.value=amtChange(val.value, amounts).toFixed(4);
				}else if(val.name=='orderPrice'){
					val.value=amtChange(val.value, amounts).toFixed(4);
				}else if(val.name=='num'){
					val.value=amtChange(val.value, amounts, true).toFixed(3);
				}else if(val.name=='hbNum'){
					val.value=amtChange(val.value, amounts, true).toFixed(3);
				}
			});
			return addValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("修改信息失败",responseText.errorMessage);
            }else{
            	var rec =JSON.parse(responseText.data);
            	if(rec.type=="500"){
            		$.messager.alert("修改信息失败",rec.code);
            	}else{
            		//更新扣减金额
                	changeKJamt();
                	$("#addLineModal").modal("hide");
        			$("#order-table").bootstrapTable("refresh")
        			$.messager.popup("添加SKU成功");
            	}
            	$("#num").val('0');
    			$("#hbNum").val('0');
    			$("#addLineModal").modal("hide");
    			$("#order-table").bootstrapTable("refresh")
            }
		}
	});
	$("#editlineForm").ajaxForm({
		target : '#btn-edit-save', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			editvalidateNum();
			if($("#editnum").val()==0 && $("#edithbNum").val()==0){
				$.messager.alert("数量，货补数量同时为0");
				return flase;
			}
			var amounts = 0;
			$.each(formData, function(i, val){
				if(val.name=='amounts'){
					amounts = val.value;
				}
			});
			$.each(formData, function(i, val){
				if(val.name=='price'){
					val.value=amtChange(val.value, amounts).toFixed(4);
				}else if(val.name=='orderPrice'){
					val.value=amtChange(val.value, amounts).toFixed(4);
				}else if(val.name=='num'){
					val.value=amtChange(val.value, amounts, true).toFixed(3);
				}else if(val.name=='hbNum'){
					val.value=amtChange(val.value, amounts, true).toFixed(3);
				}
			});
			return editValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("修改信息失败",responseText.errorMessage);
            }else{
            	var rec =JSON.parse(responseText.data);
            	if(rec.type=="500"){
            		$.messager.alert("修改信息失败",rec.code);
            	}else{
            		//更新扣减金额
                	changeKJamt();
                	$("#editLineModal").modal("hide");
                	$.messager.popup("修改SKU成功");
            	}
            }
			$("#editnum").val('0');
			$("#edithbNum").val('0');
			$("#editLineModal").modal("hide");
			$("#order-table").bootstrapTable("refresh")
		}
	});
	$('#addLineModal').on('hide.bs.modal', function() {
		$('#addlineForm')[0].reset();
		$("#materialId").val('');
		$("#materialId").trigger("chosen:updated");
	})
})

function changeKJamt(){
	$("#HBamt").html('0');
	$("#XJamt").html('0');
	$("#SXamt").html('0');
	var orderamturl ="order/util/orderAmt?id="+$("#orderid").val();
	$.get(orderamturl, function(data) {
		if (data.rows && data.rows.length == 1) {
			$("#HBamt").html(data.rows[0].hbAmt.toFixed(2));
			var cashAmt = $("#cashAmt").text();
			if (parseFloat(data.rows[0].orderAmt) < parseFloat(cashAmt)) {
				$("#XJamt").html(data.rows[0].orderAmt);
			} else {
				$("#XJamt").html(cashAmt);
				$("#SXamt").html((parseFloat(data.rows[0].orderAmt)-cashAmt).toFixed(2));
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
			sortable : false
		}, {
			title : '单位',
			field : 'unit',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
		}, {
			title : '规格',
			field : 'specifications',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
		}, {
			title : '箱内数量',
			field : 'amounts',
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
			visible : false,
			formatter : function(value, row) {
				if(value==0){
					return '-';
				}
				if(row.amounts){
					return amtChange(value, row.amounts, true).toFixed(4);
				}
			}
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
			sortable : true,
			formatter : function(value, row) {
				if(value==0){
					return '-';
				}
				if(row.amounts){
					return amtChange(value, row.amounts, true).toFixed(4);
				}
			}
		}, {
			title : '下单数量',
			field : 'num',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
			formatter:function(value, row){
				if(row.amounts){
					return amtChange(value, row.amounts).toFixed(3);
				}
				var rows = $('#order-table').bootstrapTable('getData');
				var total = parseFloat(0);
				$.each(rows, function(i, val){
					if(val.amounts){
						total += parseFloat(amtChange(val.num, val.amounts).toFixed(3));
					}
				});
				return total.toFixed(3);
			}
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
			valign : 'middle',
			formatter:function(value, row){
				if(row.amounts){
					return amtChange(value, row.amounts).toFixed(3);
				}
				var rows = $('#order-table').bootstrapTable('getData');
				var total = parseFloat(0);
				$.each(rows, function(i, val){
					if(val.amounts){
						total += parseFloat(amtChange(val.hbNum, val.amounts).toFixed(3));
					}
				});
				return total.toFixed(3);
			}
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
	if (rows && rows.length == 1 && rows[0].id !=0) {
			$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
				$.post("order/edit/delLine", {
					"id" : rows[0].id
				}, function() {
					changeKJamt();
					$("#order-table").bootstrapTable("refresh");
				})
			});
	} else {
		$.messager.alert("提示", "请选出合计行外的记录!");
	}
}
function doEdit(){
	var rows = $("#order-table").bootstrapTable("getSelections");
	if (rows && rows.length == 1 &&rows[0].id!=0) {
		autoEdit(rows[0]);
		editdata = rows[0];
		//获取当前可售库存
		var invnum = "order/util/invnumByRdc?materialId="+rows[0].materialId+"&merchCustId="+$("#merchCustId").val()+"&orgid="+$("#orgid").val()+"&rdcCode="+$("#rdcCode").val();
		$.get(invnum,function(data){
			if(data.data){
				$("#editinvnum").val((data.data/rows[0].amounts).toFixed(3));
			}
		})
		$("#editprice").val(rows[0].price*rows[0].amounts);
		$("#editorderPrice").val(rows[0].orderPrice*rows[0].amounts);
		$("#editnum").val(rows[0].num/rows[0].amounts);
		$("#edithbNum").val(rows[0].hbNum/rows[0].amounts);
		$("#editLineModal").modal("show");
	} else {
		$.messager.alert("提示", "请选择出合计行外要修改的记录!");
	}
}
function cleanLine() {
	$("#material").val('');
	$("#policy").empty();
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
	$("#amounts").val('');
	$("#specifications").val('');
}
function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			materialId : "required",
			num : {
				required : true,
				number : true,
				min:0,
				num:true,
				orderamt :true
			},
			hbNum: {
				number : true,
				min:0,
				num:true,
				hbamt:true
			}
			,price:{
				required : true,
				min:0.001
			}
		}
	});
	return validator;
}
function editinitValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			materialId : "required",
			num : {
				required : true,
				number : true,
				min:0,
				num:true,
				editorderamt :true
			},
			hbNum: {
				number : true,
				min:0,
				num:true,
				edithbamt:true
			}
			,price:{
				required : true,
				min:0.001,
				price:true
			}
		}
	});
	return validator;
}
function validateNum(){
	if(!$("#materialId").val()){
		$.messager.alert("提示", "请选择物料");
		$("#num").val('0');
		$("#hbNum").val('0');
		//return;
	}
	if(validateDecimal($('#num').val()) ==false){
		$("#num").val('0');
		$("#orderAmt").val('0');
		$("#amt").val($("#hbAmt").val());
		$("#discountAmt").val($("#hbAmt").val());
		//return;
	}
	if(validateDecimal($('#hbNum').val()) ==false){
		$("#hbAmt").val('0');
		$("#hbNum").val('0');
		$("#discountAmt").val("0");
		$("#amt").val($("#orderAmt").val());
		//return;
	}
	var num =parseFloat($("#num").val())+parseFloat($("#hbNum").val());
	var url ="order/util/validateNumByRdc?materialId="+$("#materialId").val()
			+"&orgid="+$("#orgid").val()
			+"&merchCustId="+$("#merchCustId").val()
			+"&num="+num+"&rdcCode="+$("#rdcCode").val();
	$.get(url,function(res){
		if(res.data && res.data =="200"){
			numchangeamt()
		}else{
			$("#num").val('0');
			$("#hbNum").val('0');
			$("#orderAmt").val('0');
			$("#amt").val('0');
			$.messager.alert("提示", "当前可售库存不足，请联系销售内务");
			return;
		}
	});
}
function editvalidateNum(){
	if(validateDecimal($('#editnum').val()) ==false){
		$("#editnum").val('0');
		$("#editorderAmt").val('0');
		$("#editamt").val($("#edithbAmt").val());
		$("#editdiscountAmt").val($("#edithbAmt").val());
	}
	if(validateDecimal($('#edithbNum').val()) ==false){
		$("#edithbAmt").val('0');
		$("#edithbNum").val('0');
		$("#editdiscountAmt").val("0");
		$("#editamt").val($("#editorderAmt").val());
	}
	var num =parseFloat($("#editnum").val())+parseFloat($("#edithbNum").val());
	var url ="order/util/validateNumByRdc?materialId="+$("#editmaterialId").val()
			+"&orgid="+$("#orgid").val()
			+"&merchCustId="+$("#merchCustId").val()
			+"&num="+num+"&rdcCode="+$("#rdcCode").val();
	$.get(url,function(res){
		if(res.data && res.data =="200"){
			editnumchangeamt()
		}else{
			$("#editnum").val('0');
			$("#edithbNum").val('0');
			$("#editorderAmt").val('0');
			$("#editamt").val('0');
			$.messager.alert("提示", "当前可售库存不足，请联系销售内务");
			return;
		}
	});
}
function numchangeamt(){
	var hbnum =parseFloat($("#hbNum").val());//货补数量
	var num =parseFloat($("#num").val());//订单数量
	if(!hbnum){
		hbnum=0;
	}
	if(!num){
		num=0;
	}
	var orderPrice =parseFloat($("#orderPrice").val());
	var amt = parseFloat(price*num);
	var hbamt = parseFloat(price*hbnum);
	//订单金额
	$("#hbamt").val((parseFloat(orderPrice*hbnum)).toFixed(2));
	$("#orderAmt").val((parseFloat(orderPrice*num)).toFixed(2));
	$("#amt").val((parseFloat($("#hbamt").val())+parseFloat($("#orderAmt").val())).toFixed(2));
	$("#discountAmt").val((parseFloat($("#amt").val())-parseFloat($("#orderAmt").val())).toFixed(2));
	
}


function editnumchangeamt(){
	var hbnum =parseFloat($("#edithbNum").val());//货补数量
	var num =parseFloat($("#editnum").val());//订单数量
	if(!hbnum){
		hbnum=0;
	}
	if(!num){
		num=0;
	}
	var orderPrice =parseFloat($("#editorderPrice").val());
	var amt = parseFloat(price*num);
	var hbamt = parseFloat(price*hbnum);
	//订单金额
	$("#edithbamt").val((parseFloat(orderPrice*hbnum)).toFixed(2));
	$("#editorderAmt").val((parseFloat(orderPrice*num)).toFixed(2));
	$("#editamt").val((parseFloat($("#edithbamt").val())+parseFloat($("#editorderAmt").val())).toFixed(2));
	$("#editdiscountAmt").val((parseFloat($("#editamt").val())-parseFloat($("#editorderAmt").val())).toFixed(2));
}
function doSave(){
	var freight=$("#freight").val()
	if (!/^0$|(^(-?)(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$)/.test(freight) && $("#deliveryType").val()=='2') {
		$.messager.alert("运费只能保留2位小数");
		return ;
	}
	var  url ="order/updateHeader?id="+$("#orderid").val()+"&shipid="+$("#shipId").val()+"&freight="+$("#freight").val();
	$.get(url,function(res){
		window.location.href = "order/index.html";
	});
}
function validateDecimal(value){
	var ir = /^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,3})?$)/;
	return ir.test(value);
}
function doSaveAndAudit(){
	$.messager.confirm("警告", "您确认要提交此销售订单吗?", function() {
		$.post("order/orderAudit", {
			"headerid" : $("#orderid").val(),
			"states":"2",
			"orderType":"0"
		}, function() {
			window.location.href = "order/index.html";
		})
	});
}
function amtChange(price, amounts, isMulti){
	if(isMulti){
		return (parseFloat(price)*parseFloat(amounts));
	}
	return (parseFloat(price)/parseFloat(amounts));
}
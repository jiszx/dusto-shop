var materialdata;
$(function(){
	//初始化表格数据
	initTable();
	
	//自定义验证
	$.validator.addMethod("orderamt",function(value,element){
		var num =parseFloat(value);
		var creditAmt = parseFloat($("#creditAmt").text());//授信余额
		var sxamt = parseFloat($("#sxamt").val());//应扣授信金额
		var orderprice =parseFloat($("#orderPrice").val());
		if ((sxamt + parseFloat(orderprice * num)) > (creditAmt)) {
			return false;
		}else{
			return true;
		}
	},"可用资金不足");
	$.validator.addMethod("editorderamt",function(value,element){
		var num =parseFloat(value);
		var creditAmt = parseFloat($("#creditAmt").text());//授信余额
		var sxamt = parseFloat($("#sxamt").val());//应扣授信金额
		var orderprice =parseFloat($("#editorderPrice").val());
		if ((sxamt + parseFloat(orderprice * num)) > (creditAmt)) {
			return false;
		}else{
			return true;
		}
	},"可用资金不足");
	
	
	$.validator.addMethod("inv",function(value,element){
		var num =parseFloat(value);
		var invnum = parseFloat($("#invnum").val());//库存数量
		if(parseFloat(invnum)<parseFloat(num)){
			return false;
		}else{
			return true;
		}
	},"库存不足");
	
	$.validator.addMethod("editinv",function(value,element){
		var num =parseFloat(value);
		var invnum = parseFloat($("#editinvnum").val());//库存数量
		if(parseFloat(invnum)<parseFloat(num)){
			return false;
		}else{
			return true;
		}
	},"库存不足");
	
	// 获取用户当前的资金情况
	var account_url = "order/util/customerAccount?merchid="
			+ $("#merchCustId").val() + "&orgid=" + $("#orgid").val();
	$.get(account_url, function(data) {
		if (data.rows && data.rows.length == 1) {
			$("#creditAmt").html(data.rows[0].creditAmt);
		}
	});
	
	// 初始化送达方chosen
	$('#shipId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true
	});
	
	var url = 'order/util/customerShip?merchid=' + $("#merchCustId").val()+"&orgid="+$("#orgid").val();
	$.get(url, function(data) {
		if (data.rows && data.rows.length > 0) {
			shipdata=data.rows;
			$('#shipId').append("<option></option>");
			for (var i = 0; i < data.rows.length; i++) {
				$('#shipId').append(
						"<option value='" + data.rows[i].id +"'>"+ data.rows[i].address + "</option>");
			}
			$("#shipId").trigger("chosen:updated");
		}
	});
	
	$('#rdcCode').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true
	});
	loadMaterial();
	$("#rdcCode").change(function(){
		// 获取用户的产品信息
		$.messager.confirm("警告", "变更RDC会删除掉现有下单物料，请问确定要执行改操作吗?", function() {
			var lines = $("#order-table").bootstrapTable("getData");
			for(var i=0;i<lines.length;i++){
				if(lines[i].id !=0){
					$.post("order/edit/delLine", {
						"id" : lines[i].id
					}, function() {
						changeKJamt();
						$("#order-table").bootstrapTable("refresh");
					})
				}
			}
			$("#materialId").empty();
			$("#materialId").trigger("chosen:updated");
			loadMaterial();
		});
		
	});
	$('#materialId').chosen({
		no_results_text: "没有找到.",
		placeholder_text : "请选择客户信息...",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	
	//物料
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
				$("#orderPrice").val(price);					
				$("#price").val(price);// 订单单价
				$("#materialName").val(materialdata[i].sku);// 物料描述
				$("#unit").val(materialdata[i].unit);// 物料单位
				$("#amounts").val(materialdata[i].amounts);
				$("#specifications").val(materialdata[i].specifications);
				$("#invnum").val((materialdata[i].invnum/materialdata[i].amounts).toFixed(3));
				$("#materialType").val(materialdata[i].materialType);
			}
		}

	});
	
	//添加时输入数量事件
	$("#num").blur(function(){
		var num =$("#num").val();
		var orderPrice =$("#orderPrice").val();
		var amt = parseFloat(orderPrice*num);
		$("#amt").val(amt.toFixed(2));
		$("#orderAmt").val(amt.toFixed(2));
	});
	
	$("#editnum").blur(function(){
		var num =$("#editnum").val();
		var orderPrice =$("#editorderPrice").val();
		var amt = parseFloat(orderPrice*num);
		$("#editamt").val(amt.toFixed(2));
		$("#editorderAmt").val(amt.toFixed(2));
	});
	var addValidator = initValidate("#addlineForm");
	var editValidator = editinitValidate("#editlineForm");
	$("#addlineForm").ajaxForm({
		target : '#btn-add-save', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
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
        			$.messager.popup("添加SKU成功");
            	}
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
                	$.messager.popup("修改SKU成功");
            	}
            }
			$("#editLineModal").modal("hide");
			$("#order-table").bootstrapTable("refresh")
		}
	});
	$('#addLineModal').on('hide.bs.modal', function() {
		$('#addlineForm')[0].reset();
		$("#materialId").val('');
		$("#materialId").trigger("chosen:updated");
	});
	
	$("#edit-bstb-row").click(function(){
		var rows = $("#order-table").bootstrapTable("getSelections");
		if (rows && rows.length == 1 &&rows[0].id!=0) {
			editdata = rows[0];
			for(var i=0;i<materialdata.length;i++){
				if(materialdata[i].materialId=rows[0].materialId){
					$("#editinvnum").val((materialdata[i].invnum/rows[0].amounts).toFixed(3));
				}
			}
			autoEdit(rows[0]);
			$("#editprice").val((rows[0].price*rows[0].amounts).toFixed(4));
			$("#editnum").val((rows[0].num/rows[0].amounts).toFixed(3));
			$("#editorderPrice").val((rows[0].orderPrice*rows[0].amounts).toFixed(4));
			$("#editLineModal").modal("show");
			$("#editmaterialId").trigger("chosen:updated");
		} else {
			$.messager.alert("提示", "请选择出合计行外要修改的记录!");
		}
	});
	
	$("#del-bstb-row").click(function(){
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
	});
	$("#btn-save").click(function(){
		if(!$("#shipId").val()){
			$.messager.alert("送达方不能为空");
			return;
		}
		if(!$("#rdcCode").val()){
			$.messager.alert("RDC不能为空");
			return;
		}
		if (!/^0$|(^(-?)(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$)/.test($("#freight").val()) && $("#deliveryType").val()=='2') {
			$.messager.alert("运费只能保留2位小数");
			return ;
		}
		var  url ="order/updateHeader?id="+$("#orderid").val()+"&shipid="+$("#shipId").val()+"&rdcCode="+$("#rdcCode").val()+"&freight="+$("#freight").val();
		$.get(url,function(res){
			window.location.href = "customerInvAllocation/InvAllocationList.html";
		});
	})
})

function changeKJamt(){
	var orderamturl ="order/util/orderAmt?id="+$("#orderid").val();
	$.get(orderamturl, function(data) {
		if (data.rows && data.rows.length == 1) {
			$("#sxamt").val(data.rows[0].orderAmt);
		}
	});
}
function loadMaterial(){
	var merchid = $("#merchCustId").val();
	var rdcCode =$("#rdcCode").val();
	//type 等于1表示要验证库存信息
	var materialurl = "order/util/custProductByRdc?merchid=" + merchid+"&orgid="+$("#orgid").val()+"&combination=1"+"&rdcCode="+rdcCode;
	$.get(materialurl, function(data) {
		if (data.rows && data.rows.length > 0) {
			materialdata = data.rows;
			$('#materialId').append("<option></option>");
			for (var i = 0; i < data.rows.length; i++) {
				//if(parseFloat(data.rows[i].unitprice) >0){								
					$('#materialId').append(
							"<option value='" + data.rows[i].materialId+ "'>"+data.rows[i].materialId+'-'+ data.rows[i].sku+ "</option>");
				//}
			}
			$("#materialId").trigger("chosen:updated");
		}
	});
}
function initTable() {
		$("#order-table").bootstrapTable({
		url : 'order/util/linedata?headerid='+$("#orderId").val(),
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
		},
		{
			title : '规格',
			field : 'specifications',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : false
		},
		{
			title : '箱内数量',
			field : 'amounts',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : false
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
			title : '数量(箱)',
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
			title : '金额',
			field : 'amt',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true
		}]
	});

}
function cleanLine() {
	$('#materialName').val('');
	$('#unit').val('');
	$('#price').val('0');
	$('#highPrice').val('0');
	$('#num').val('0');
	$("#amt").val('0');
	$("#orderAmt").val('0');
	$("#amounts").val('');
	$("#specifications").val('');
	$("#invnum").val('');
	$("#materialType").val('');
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
				orderamt :true,
				inv:true
			},price:{
				required : true,
				min:0.001,
				price:true
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
				editorderamt :true,
				editinv:true
			},price:{
				required : true,
				min:0.001,
				price:true
			}
		}
	});
	return validator;
}
function amtChange(price, amounts, isMulti){
	if(isMulti){
		return (parseFloat(price)*parseFloat(amounts));
	}
	return (parseFloat(price)/parseFloat(amounts));
}
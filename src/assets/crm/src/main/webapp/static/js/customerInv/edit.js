var $table = $('#order-table');
var editdata;
var materialdata;
$(function() {
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
	initTable();
	
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
	// 获取用户当前的资金情况
	var account_url = "order/util/customerAccount?merchid="
			+ $("#merchCustId").val() + "&orgid=" + $("#orgid").val();
	$.get(account_url, function(data) {
		if (data.rows && data.rows.length == 1) {
			$("#creditAmt").html(data.rows[0].creditAmt);
		}
	});
	
	changeKJamt();
	
	$('#shipId').chosen({
		no_results_text : "没有找到",
		search_contains: true,
		allow_single_de : true
	});
	
	$('#materialId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	$('#editmaterialId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	// 获取用户的产品信息
	$("#materialId").empty();
	$("#materialId").trigger("chosen:updated");
	var materialurl = "order/util/custProduct?merchid="
			+ $("#merchCustId").val() + "&orgid=" + $("#orgid").val()+"&type=1"+"&combination=1";
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
			$("#editmaterialId").trigger("chosen:updated");
		}
	});
	$("#materialId").change(function() {
		var deliveryType = $("#deliveryType").val();
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
				$("#wlPrice").val(materialdata[i].price);// 物料物流价
				$("#hPrice").val(materialdata[i].hPrice);// 物料高卖价
				$("#amounts").val(materialdata[i].amounts);
				$("#specifications").val(materialdata[i].specifications);
				$("#invnum").val((materialdata[i].invnum/materialdata[i].amounts).toFixed(3));
				$("#materialType").val(materialdata[i].materialType);
			}
		}

	});
	/*$("#editmaterialId").change(function() {
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
	});*/
	$("#btn-save").bind('click', doSave);
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
	})
	
	//删除附件
    $(".file-list").on("click", "a.del-file", function () {
    	var delFileId = $(this).attr("id");
    	var fileItem = $(this).parent();
    	fileItem.remove();
    	if(typeof delFileId != 'undefined'){
    		//删除刚上传的附件
    		var fileInputEle = $("#file_"+delFileId.split("del_file_")[1]);
    		fileInputEle.remove();
    	}else{
    		//删除已存在的附件
    		var delAtts = $("#delAtts").val();
    		if(typeof delAtts != 'undefined' && $.trim(delAtts).length >0){
    			var delAttArray = delAtts.split(",");
    			delAttArray.push($(this).attr("source"));
    			$("#delAtts").val(delAttArray.join(","));
    		}else{
    			$("#delAtts").val($(this).attr("source"));
    		}
    	}
        checkFile();
    });
})
function changeKJamt(){
	var orderamturl ="order/util/orderAmt?id="+$("#orderid").val();
	$.get(orderamturl, function(data) {
		if (data.rows && data.rows.length == 1) {
			$("#sxamt").val(data.rows[0].orderAmt);
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
		},{
			title : '编号',
			field : 'id',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
		},{
			title : '产品名称',
			field : 'sku',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : false,
		},{
			title : '物料编码',
			field : 'materialId',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : false
		},{
			title : '单位',
			field : 'unit',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
		},{
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
		},{
			title : '规格',
			field : 'specifications',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true
		},{
			title : '箱内数量',
			field : 'amounts',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true
		},{
			title : '数量',
			field : 'num',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
			formatter:function(value, row){
				if(row.amounts){
					return amtChange(value, row.amounts).toFixed(3);
				}
				/*var rows = $('#order-table').bootstrapTable('getData');
				var total = parseFloat(0);
				$.each(rows, function(i, val){
					if(val.amounts){
						total += parseFloat(amtChange(val.num, val.amounts).toFixed(3));
					}
				});
				return total.toFixed(3);*/
				return value;
			}
		},{
			title : '金额',
			field : 'amt',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true
		}]
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
		editdata = rows[0];
		for(var i=0;i<materialdata.length;i++){
			if(materialdata[i].materialId=rows[0].materialId){
				$("#editinvnum").val((materialdata[i].invnum/rows[0].amounts).toFixed(3));
			}
		}
		autoEdit(rows[0]);
		$("#smaterialId").val(rows[0].materialId);
		$("#editprice").val((rows[0].price*rows[0].amounts).toFixed(4));
		$("#editnum").val((rows[0].num/rows[0].amounts).toFixed(3));
		$("#editorderPrice").val((rows[0].orderPrice*rows[0].amounts).toFixed(4));
		$("#editLineModal").modal("show");
		$("#editmaterialId").trigger("chosen:updated");
	} else {
		$.messager.alert("提示", "请选择出合计行外要修改的记录!");
	}
}
function cleanLine() {
	$("#material").val('');
	$('#materialName').val('');
	$('#unit').val('');
	$('#price').val('0');
	$('#highPrice').val('0');
	$('#num').val('0');
	$("#amt").val('0');
	$("#orderAmt").val('0');
	$("#orderPrice").val('0');
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
function doSave(){
	if (!/^0$|(^(-?)(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$)/.test($("#freight").val()) && $("#deliveryType").val()=='2') {
		$.messager.alert("运费只能保留2位小数");
		return ;
	}
	var  url ="order/updateHeader?id="+$("#orderid").val()+"&shipid="+$("#shipId").val()+"&freight="+$("#freight").val();
	$.get(url,function(res){
		$('#orderFrom').ajaxSubmit(function(){
			window.location.href = "customerInvAllocation/InvAllocationList.html";
		});
	});
}
function validateDecimal(value){
	var ir = /^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,3})?$)/;
	return ir.test(value);
}
function amtChange(price, amounts, isMulti){
	if(isMulti){
		return (parseFloat(price)*parseFloat(amounts));
	}
	return (parseFloat(price)/parseFloat(amounts));
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

var $table = $('#order-table');
var editdata;
$(function() {
	initTable();
	$("#num").blur(function(){
		validateNum();
	});
	$("#editnum").blur(function(){
		editvalidateNum();
	});
	var materialdata;
	// 获取用户的产品信息
	$("#material").empty();
	var materialurl = "order/util/custProduct?merchid="
			+ $("#ordershipId").val() + "&orgid=" + $("#orgid").val();
	$.get(materialurl, function(data) {
		if (data.rows && data.rows.length > 0) {
			materialdata = data.rows;
			$("#material").append("<option value=''></option>");
			$("#editmaterialId").append("<option value=''></option>");
			for (var i = 0; i < data.rows.length; i++) {
				$('#material').append("<option value='" + data.rows[i].materialId + "'>"+ data.rows[i].sku + "</option>");
				$('#editmaterialId').append("<option value='" + data.rows[i].materialId + "'>"+ data.rows[i].sku + "</option>");
			}
		}
	});
	$("#material").change(
			function() {
				$('#policy').html('');
				var materialid = $("#material").val();
				for (var i = 0; i < materialdata.length; i++) {
					if (materialdata[i].materialId == materialid) {
						$("#materialName").val(materialdata[i].sku);// 物料描述
						$("#price").val(amtChange(materialdata[i].unitprice, materialdata[i].amounts, true).toFixed(2));// 订单单价
						$("#orderPrice").val(amtChange(materialdata[i].unitprice, materialdata[i].amounts, true).toFixed(2));// 订单单价
						$("#unit").val(materialdata[i].unit);// 物料基本价ID
						$("#wlPrice").val(materialdata[i].price);// 物料物流价
						$("#hPrice").val(materialdata[i].hPrice);// 物料高卖价
						$("#invnum").val(materialdata[i].invnum);// 库存
						$("#amounts").val(materialdata[i].amounts);
					}
				}
			});
	$("#btn-save").bind('click', doSave);
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
			var result = addValidator.valid();
			if(result==false){
				return result;
			}
			var amounts = 0;
			$.each(formData, function(i, val){
				if(val.name=='amounts'){
					amounts = val.value;
				}
			});
			$.each(formData, function(i, val){
				if(val.name=='price'){
					val.value=amtChange(val.value, amounts).toFixed(2);
				}else if(val.name=='orderPrice'){
					val.value=amtChange(val.value, amounts).toFixed(2);
				}else if(val.name=='num'){
					val.value=amtChange(val.value, amounts, true).toFixed(3);
				}
			});
			return result;
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("修改信息失败",responseText.errorMessage);
            }else{
            	var rec =JSON.parse(responseText.data);
            	if(rec.data=="500"){
            		$.messager.alert("修改信息失败",rec.code);
            	}else{
            		//更新扣减金额
            		updateHeaderAmt()
                	$("#addLineModal").modal("hide");
        			$("#order-table").bootstrapTable("refresh")
            	}
            	$("#num").val('0');
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
			var result = editValidator.valid();
			if(result==false){
				return result;
			}
			var amounts = 0;
			$.each(formData, function(i, val){
				if(val.name=='amounts'){
					amounts = val.value;
				}
			});
			$.each(formData, function(i, val){
				if(val.name=='price'){
					val.value=amtChange(val.value, amounts).toFixed(2);
				}else if(val.name=='orderPrice'){
					val.value=amtChange(val.value, amounts).toFixed(2);
				}else if(val.name=='num'){
					val.value=amtChange(val.value, amounts, true).toFixed(3);
				}
			});
			return result;
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("修改信息失败",responseText.errorMessage);
            }else{
            	var rec =JSON.parse(responseText.data);
            	if(rec.data=="500"){
            		$.messager.alert("修改信息失败",rec.code);
            	}else{
            		updateHeaderAmt();
                	$("#editLineModal").modal("hide");
            	}
            }
			$("#editnum").val('0');
			$("#editLineModal").modal("hide");
			$("#order-table").bootstrapTable("refresh")
		}
	});
})
function updateHeaderAmt(){
	//修改销售订单金额
	var url ="order/updateOrderAmt?id="+$("#orderid").val();
	$.get(url, function(data) {
		if (data.errorCode ==0) {
			$.messager.popup("操作成功");
		}else{
			$.messager.popup("操作失败");
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
			sortable : true,
			formatter : function(value, row) {
				if(value==0){
					return '-';
				}
				if(row.amounts){
					return amtChange(value, row.amounts, true).toFixed(2);
				}
			}
		}, {
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
				var rows = $('#order-table').bootstrapTable('getData');
				var total = parseFloat(0);
				$.each(rows, function(i, val){
					if(val.amounts){
						total += parseFloat(amtChange(val.num, val.amounts).toFixed(3));
					}
				});
				return total;
			}
		},  {
			title : '订单金额',
			field : 'orderAmt',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true
		}, {
			title : '包含数量',
			field : 'amounts',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
			visible : false
		} ]
	});

}

function doDel(){
	var rows = $("#order-table").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
			$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
				$.post("order/edit/delLine", {
					"id" : rows[0].id
				}, function(res) {
					updateHeaderAmt();
					$("#order-table").bootstrapTable("refresh");
				})
			});
	} else {
		$.messager.alert("提示", "请选择要删除的记录!");
	}
}
function validateNum(){
	if(!$("#material").val()){
		return;
	}
	numchangeamt();
//	var num =parseFloat($("#num").val());
//	var url ="order/util/validateNum?materialId="+$("#material").val()
//			+"&orgid="+$("#orgid").val()
//			+"&merchCustId="+$("#ordershipId").val()
//			+"&num="+num;
//	$.get(url,function(res){
//		if(res.data && res.data =="200"){
//			numchangeamt()
//		}else{
//			$("#num").val('0');
//			$("#orderAmt").val('0');
//			$("#amt").val('0');
//			$.messager.alert("提示", "当前可售库存不足，请联系销售内务");
//		}
//	});
}
function editvalidateNum(){
	editnumchangeamt();
//	var num =parseFloat($("#editnum").val());
//	var url ="order/util/validateNum?materialId="+$("#editmaterialId").val()
//			+"&orgid="+$("#orgid").val()
//			+"&merchCustId="+$("#ordershipId").val()
//			+"&num="+num;
//	$.get(url,function(res){
//		if(res.data && res.data =="200"){
//			editnumchangeamt()
//		}else{
//			$("#editnum").val('0');
//			$("#editorderAmt").val('0');
//			$("#editamt").val('0');
//			$.messager.alert("提示", "当前可售库存不足，请联系销售内务");
//		}
//	});
}
function doEdit(){
	var row = $("#order-table").bootstrapTable("getSelections");
	if (row && row.length > 0 && row[0].id !="0") {
		var tmp = JSON.parse(JSON.stringify(row[0]));
		tmp.num = amtChange(row[0].num, row[0].amounts).toFixed(3);
		tmp.orderPrice = amtChange(row[0].orderPrice, row[0].amounts, true).toFixed(2);
		tmp.price = amtChange(row[0].price, row[0].amounts, true).toFixed(2);
		autoEdit(tmp);
		editdata =row;
		$("#editnum2").val(row[0].num);
		$("#editLineModal").modal("show");
		//获取当前可售库存
//		var invnum = "order/util/invnum?materialId="+row[0].materialId+"&merchCustId="+$("#ordershipId").val()+"&orgid="+$("#orgid").val();
//		$.get(invnum,function(data){
//			if(data.data){
//				$("#editinvnum").val(data.data);
//				$("#editnum2").val(row[0].num);
//				$("#editLineModal").modal("show");
//			}
//		})
	}else {
		$.messager.alert("请选择要出合计外的行。");
	}
}
function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			materialId : "required",
			num : {
				required : true,
				number : true,
				num:true,
				min :0.001
			}
		}
	});
	return validator;
}
function numchangeamt(){
	var num =parseFloat($("#num").val());//订单数量
	var price =parseFloat($("#price").val());//订单单价
	var amt = parseFloat(price*num);
	$("#amt").val(parseFloat(price*num).toFixed(2));
	$("#orderAmt").val(parseFloat(price*num).toFixed(2));
	
}
function editnumchangeamt(){
	var num =parseFloat($("#editnum").val());//订单数量
	var price =parseFloat($("#editprice").val());//订单单价
	var amt = parseFloat(price*num);
	$("#editamt").val(parseFloat(price*num).toFixed(2));
	$("#editorderAmt").val(parseFloat(price*num).toFixed(2));
}

function doSave(){
	window.location.href = "order/retail/index.html";
}

function amtChange(price, amounts, isMulti){
	if(isMulti){
		return (parseFloat(price)*parseFloat(amounts));
	}
	return (parseFloat(price)/parseFloat(amounts));
}

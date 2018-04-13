var flag="0";
var materialdata;
var merchdata;
$(function() {
	$.validator.addMethod("otherMerchCustId",function(value,element){
		if($("#type").val()=='2' && ($("#otherMerchCustId").val()==null || !$("#otherMerchCustId").val())){
			return false;			
		}else{
			return true;
		}
		
	},"客户间调拨,对方客户不能为空");
	$('#invAdjustTable').bootstrapTable({
		url : 'customerInvAdjust/adjustList',
		method : 'get',
		classes : "table table-hover table-condensed",
		cache : false,
		toolbar : "#invAdjustTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "payName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : 'ck',
			title : '',
			radio : true
		}, {
			field : 'id',
			title : '编号',
			visible : false
		}, {
			field : 'orgname',
			title : '所属销售组织',
			align : 'left'
		},{
			field : 'custname',
			title : '客户名称',
			align : 'left'
		},{
			field : 'custType',
			title : '客户类型',
			align : 'left',
			formatter : function(value) {
				return getCustType(value + "");
			}
		},{
			field : 'sapCustomerId',
			title : 'SAP编码',
			align : 'left'
		},{
			field : 'materialId',
			title : '物料编码',
			align : 'left'
		},{
			field : 'unit',
			title : '单位',
			align : 'left'
		},{
			field : 'amounts',
			title : '箱内数量',
			align : 'left'
		},{
			field : 'sku',
			title : '物料',
			align : 'left'
		}, {
			field : 'adjustPrice',
			title : '单价(元)',
			align : 'left',
			formatter : function(value,row) {
				return value*row.amounts/100;
			}
		}, {
			field : 'adjustNum',
			title : '数量',
			align : 'left',
			formatter : function(value,row) {
				return value/row.amounts;
			}
		}, {
			field : 'adjustAmt',
			title : '金额(元)',
			align : 'left',
			formatter : function(value) {
				return value/100;
			}
		}, {
			field : 'createTs',
			title : '时间',
			align : 'left'
		}, {
			field : 'creater',
			title : '创建人',
			align : 'left'
		}, {
			field : 'type',
			title : '调整类型',
			align : 'left',
			formatter : function(value) {
				if(value=='1'){
					return "普通调增";
				}else if (value=='2'){
					return "客户间调整";
				}
			}
		}, {
			field : 'reason',
			title : '调整原因',
			align : 'left',
			formatter : function(value) {
				return getReasons(value + "");
			}
		}, {
			field : 'states',
			title : '状态',
			align : 'left',
			formatter : function(value) {
				return getStates(value + "");
			}
		} ]
	});
	$('#merchCustId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	$('#otherMerchCustId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	$('#materialId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	$("#organizationId").change(function(){
		//清空数据
		clean('');
		//获取销售组织下对应的客户信息
		var url ='customerInvAdjust/customer?orgid='+$("#organizationId").val()+"&invValidate=1";
		$.get(url,function(data){
			if(data.rows && data.rows.length>0){
				merchdata = data.rows;
				$("#merchCustId").append('<option></option>');
				for(var i=0;i<data.rows.length;i++){
					$("#merchCustId").append('<option value="'+data.rows[i].id+'">'+data.rows[i].sapCustomerId+data.rows[i].name+'</option>');
				}
			}
			$("#merchCustId").trigger("chosen:updated");
		});
		if($("#type").val()=='2'){
			$("#otherMerch").removeClass("hide");
			$("#otherMerchCustId").append('<option></option>');
			var url2 ='customerInvAdjust/customer?orgid='+$("#organizationId").val()+"&invValidate=2";
			$.get(url2,function(data){
				if(data.rows && data.rows.length>0){
					$("#otherMerchCustId").append('<option></option>');
					for(var i=0;i<data.rows.length;i++){
						if(data.rows[i].id !=$("#merchCustId").val()){							
							$("#otherMerchCustId").append('<option value="'+data.rows[i].id+'">'+data.rows[i].sapCustomerId+data.rows[i].name+'</option>');
						}
					}
				}
				$("#otherMerchCustId").trigger("chosen:updated");
			});
		}else{
			$("#otherMerch").addClass("hide");
		}
	});
	$("#merchCustId").change(function(){
		//清空数据
		$("#materialId").empty();
		$("#materialId").trigger("chosen:updated");
		$("#adjustPrice").val('');
		$("#invnum").val('');
		$("#adjustNum").val('');
		$("#adjustAmt").val('');
		$("#reason").val('');
		$("#remark").val('');
		//获取客户对应的物料
		var url ='customerInvAdjust/getmaterials?orgid='+$("#organizationId").val()+"&merchId="+$("#merchCustId").val();
		$.get(url,function(data){
			if(data.rows && data.rows.length>0){
				materialdata =data.rows;
				$("#materialId").append('<option></option>');
				for(var i=0;i<data.rows.length;i++){
					$("#materialId").append('<option value="'+data.rows[i].materialId+'">'+data.rows[i].materialId+data.rows[i].sku+'</option>');
				}
			}
			$("#materialId").trigger("chosen:updated");
		});
	});
	$("#type").change(function(){
		$("#otherMerchCustId").empty();
		$("#otherMerchCustId").trigger("chosen:updated");
		if($("#type").val()=='2'){
			$("#otherMerch").removeClass("hide");
			$("#otherMerchCustId").append('<option></option>');
			var url ='customerInvAdjust/customer?orgid='+$("#organizationId").val()+"&invValidate=2";
			$.get(url,function(data){
				if(data.rows && data.rows.length>0){
					//merchdata = data.rows;
					$("#otherMerchCustId").append('<option></option>');
					for(var i=0;i<data.rows.length;i++){
						if(data.rows[i].id !=$("#merchCustId").val()){							
							$("#otherMerchCustId").append('<option value="'+data.rows[i].id+'">'+data.rows[i].sapCustomerId+data.rows[i].name+'</option>');
						}
					}
				}
				$("#otherMerchCustId").trigger("chosen:updated");
			});
			/*for(var i=0;i<merchdata.length;i++){
				if(merchdata[i].id !=$("#merchCustId").val()){
					$("#otherMerchCustId").append('<option value="'+merchdata[i].id+'">'+merchdata[i].sapCustomerId+merchdata[i].name+'</option>');
				}
				
			}*/
			//$("#otherMerchCustId").trigger("chosen:updated");
		}else{
			$("#otherMerch").addClass("hide");
		}
		/*//获取客户对应的物料
		var url ='customerInvAdjust/getmaterials?orgid='+$("#organizationId").val()+"&merchId="+$("#merchCustId").val();
		$.get(url,function(data){
			if(data.rows && data.rows.length>0){
				materialdata =data.rows;
				$("#materialId").append('<option></option>');
				for(var i=0;i<data.rows.length;i++){
					$("#materialId").append('<option value="'+data.rows[i].materialId+'">'+data.rows[i].materialId+data.rows[i].sku+'</option>');
				}
			}
			$("#materialId").trigger("chosen:updated");
		});*/
	});
	$("#materialId").change(function(){
		var materialId =$("#materialId").val();
		for (var i = 0; i < materialdata.length; i++) {
			if (materialdata[i].materialId == materialId) {
				$("#invNum").val(materialdata[i].invNum/materialdata[i].amounts);//库存数量
				$("#adjustPrice").val(materialdata[i].price*materialdata[i].amounts/100);
				$("#unit").val(materialdata[i].unit);
				$("#amounts").val(materialdata[i].amounts);
				$("#specifications").val(materialdata[i].specifications);
			}
		}
		computeAmt();
	});
	var addValidator = initValidate("#addinvAdjustForm");
	var editValidator = initValidate("#editinvAdjustForm");
	$("#addinvAdjustForm").ajaxForm({
		target : '#btn-add', // target element(s) to be updated with server
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return addValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("新增录入信息失败",responseText.errorMessage);
            }else{
            	clean('');
            	$('#invAdjustTable').bootstrapTable('refresh');
            	$("#addinvAdjustModal").modal("hide");
            }
		}
	});
	$("#editinvAdjustForm").ajaxForm({
		target : '#btn-edit-save', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return editValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("修改录入信息失败",responseText.errorMessage);
            }else{
            	if(responseText.data.type=="500"){
    				$.messager.alert("错误提示",responseText.data.code);
    			}else{
    				clean("edit");
    				$('#invAdjustTable').bootstrapTable('refresh');
                	$("#editinvAdjustModal").modal("hide");
    			}
            }
		}
	});
	$(".btn-edit").bind("click",doEdit);
	//详情
	$("#btn-show").click(function(){
		var rows = $("#invAdjustTable").bootstrapTable("getSelections");
		if (rows && rows.length == 1){
			if(rows[0].type=='2'){
				//客户间调整
				$("#showmerch").removeClass("hide");
			}else{
				$("#showmerch").addClass("hide");
			}
			//获取库存数量
			$.get("customerInvAdjust/invNum?merchId="+rows[0].merchCustId+"&materialId="+rows[0].materialId,function(res){
				autoShow(rows[0]);
				if(res.errorCode ==0){
					$("#showinvNum").val(res.data/rows[0].amounts);
				}
				$("#showadjustNum").val(rows[0].adjustNum/rows[0].amounts);
				$("#showadjustAmt").val(rows[0].adjustAmt/100);
				$("#showadjustPrice").val(rows[0].adjustPrice*rows[0].amounts/100);
			})
			$("#showinvAdjustModal").modal("show");
		}
	});
	//删除
	$(".btn-del").click(function(){
		var rows = $("#invAdjustTable").bootstrapTable("getSelections");
		if (rows && rows.length == 1 &&(rows[0].states=='1'||rows[0].states=='4')){
			$.post("customerInvAdjust/del?id="+rows[0].id,function(res){
				if(res.errorCode ==0 && res.data =='S'){
					$.messager.popup("删除成功");					
				}else{
					$.messager.popup("删除失败");		
				}
				$('#invAdjustTable').bootstrapTable('refresh');
			})
		}
	});
	//送审
	$("#btn-audit").click(function(){
		var rows = $("#invAdjustTable").bootstrapTable("getSelections");
		if (rows && rows.length == 1 &&(rows[0].states=='1'||rows[0].states=='4')){
			$.post("customerInvAdjust/audit?id="+rows[0].id+"&states=2",function(res){
				if(res.errorCode ==0 && res.data =='S'){
					$.messager.popup("送审成功");					
				}else if (res.data=='E2'){
					$.messager.popup("对方客户余额不足");
				}
				else{
					$.messager.popup("送审失败");		
				}
				$('#invAdjustTable').bootstrapTable('refresh');
			})
		}
	})
	$("#adjustNum").blur(function(){
		var num =$("#adjustNum").val();
		var price =$("#adjustPrice").val();
		if(validateNum($('#adjustNum').val()) ==false){
			$.messager.popup("请输入大于0的数字，并且小于三位小数");
			return ;
		}
		var amt = parseFloat(price*num).toFixed(4);
		$("#adjustAmt").val(amt);
	});
	$("#adjustPrice").blur(function(){
		var num =$("#adjustNum").val();
		if(!num){
			num =0;
		}
		var price =$("#adjustPrice").val();
		if(validatePrice($('#adjustPrice').val()) ==false){
			$.messager.popup("请输入大于0的数字，并且小于四位小数");
			return ;
		}
		var amt = parseFloat(price*num).toFixed(4);
		$("#adjustAmt").val(amt);
		
		computeAmt();
	});
	$("#editadjustNum").blur(function(){
		var num =$("#editadjustNum").val();
		var price =$("#editadjustPrice").val();
		if(!num || !price){
			return;
		}
		if(validateNum($('#editadjustNum').val()) ==false){
			$.messager.popup("请输入大于0的数字，并且小于三位小数");
			return ;
		}
		var amt = parseFloat(price*num).toFixed(4);
		$("#editadjustAmt").val(amt);
	});
	$("#editadjustPrice").blur(function(){
		var num =$("#editadjustNum").val();
		if(!num){
			num =0;
		}
		var price =$("#editadjustPrice").val();
		if(validatePrice($('#editadjustPrice').val()) ==false){
			$.messager.popup("请输入大于0的数字，并且小于两位小数");
			return ;
		}
		var amt = parseFloat(price*num).toFixed(4);
		$("#editadjustAmt").val(amt);
		
		//computeAmt();
	});
	$("#btn-search").click(function(){
		var custname=$("#custname").val();
		var smaterialId =$("#smaterialId").val();
		var sorgid =$("#sorgid").val();
		var ssku=$("#ssku").val();
		var url ="customerInvAdjust/adjustList?custname="+custname+"&smaterialId="+smaterialId+"&sorgid="+sorgid+"&ssku="+ssku;
		$("#invAdjustTable").bootstrapTable('refresh', {
			'url' : url
		});
	})
});

function computeAmt(){
	var num =$("#adjustNum").val();
	var price =$("#adjustPrice").val();
	if(!num || !price){
		return;
	}
	if(validateNum($('#adjustNum').val()) ==false){
		$.messager.popup("请输入大于0的数字，并且小于三位小数");
		return ;
	}
	var amt = parseFloat(price*num).toFixed(4);
	$("#adjustAmt").val(amt);
//	var num =$("#editadjustNum").val();
//	var price =$("#editadjustPrice").val();
//	if(!num || !price){
//		return;
//	}
//	if(validateDecimal($('#editadjustNum').val()) ==false){
//		$.messager.popup("请输入大于0的数字，并且小于三位小数");
//		return ;
//	}
//	var amt = parseFloat(price*num).toFixed(2);
//	$("#editadjustAmt").val(amt);
}
function clean(a){
	//清空数据
	$('#'+a+'merchCustId').empty();
	$("#"+a+"merchCustId").trigger("chosen:updated");
	$("#"+a+"materialId").empty();
	$("#"+a+"materialId").trigger("chosen:updated");
	$("#"+a+"invnum").val('');
	$("#"+a+"adjustNum").val('');
	$("#"+a+"adjustPrice").val('');
	$("#"+a+"adjustAmt").val('');
	$("#"+a+"reason").val('');
	$("#"+a+"remark").val('');
}
function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			organizationId:"required",
			merchCustId:"required",
			materialId:"required",
			type :"required",
			otherMerchCustId:true,
			adjustNum : {
				required : true,
				number : true,
				num:true
			},
			adjustPrice : {
				required : true,
				number : true,
				price:true
			},
			reason  :"required"
		},
		ignore: ""
	});
	return validator;
}

function doEdit(){
	var rows = $("#invAdjustTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && (rows[0].states=='1'|| rows[0].states =='4')) {
		if(rows[0].type=='2'){
			//客户间调整
			$("#editmerch").removeClass("hide");
		}else{
			$("#editmerch").addClass("hide");
		}
		//获取库存数量
		$.get("customerInvAdjust/invNum?merchId="+rows[0].merchCustId+"&materialId="+rows[0].materialId,function(res){
			autoEdit(rows[0]);
			if(res.errorCode ==0){
				$("#editinvNum").val(res.data/rows[0].amounts);
			}
			$("#editadjustNum").val(rows[0].adjustNum/rows[0].amounts);
			$("#editadjustAmt").val(rows[0].adjustAmt/100);
			$("#editadjustPrice").val(rows[0].adjustPrice*rows[0].amounts/100);
			$("#editinvAdjustModal").modal("show");
		})
	}else{
		$.messager.alert("只允许修改可编辑的记录")
	}
}
function autoShow(Obj){
    for(var i in Obj){
        if(Obj[i]==null){
            continue;
        }
        $("#show"+i).val(Obj[i]+"");
    }
}

function validateNum(value){
	var ir = /^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,3})?$)/;
	return ir.test(value);
}

function validatePrice(value){
	var ir = /^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,4})?$)/;
	return ir.test(value);
}

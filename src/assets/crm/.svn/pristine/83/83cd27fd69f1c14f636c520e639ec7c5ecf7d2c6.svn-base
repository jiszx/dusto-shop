$(function() {
	$('#adjustTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url: 'account/adjust/list',
		cache : false,
		//data : data,
		toolbar : "#dictTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "colName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		//search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : 'ck',
			title : '编号',
			radio:true
		}, {
			field : 'id',
			title : '编号',
			visible:false
		}, {
			field : 'orgname',
			title : '销售组织',
			align : 'left'
		}, {
			field : 'custname',
			title : '客户',
			align : 'left'
		},{
			field:'sapCustomerId',
			title:'SAP客户编号',
			align:'left'
		}, {
			field : 'accountType',
			title : '账户类型',
			align : 'left',
			formatter:function(value){
					var str = value=='5'?'金额':(value=='3'?"额度":"");
					value= getaccountType(value=='5'?'3':value+"");
					return value+str;
				}
		}, {
			field : 'type',
			title : '调整类型',
			align : 'left',
			formatter:function(value){
				if(value ==1){
					return "手工调整";
				}else if(value ==2){
					return "自动调整";
				}
			}
			
		}, {
			field : 'amt',
			title : '金额',
			align : 'left',
            formatter:function(value){
            	return (value/100).formatMoney();
            }
		}, {
			field : 'orderId',
			title : '来源编号',
			align : 'left'
		}, {
			field : 'sku',
			title : 'sku',
			align : 'left'
		}, {
			field : 'orderNum',
			title : '数量',
			align : 'left'
		}, {
			field : 'orderPrice',
			title : '单价',
			align : 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
		}, {
			field : 'createTs',
			title : '调整时间',
			align : 'left'
		}, {
			field : 'name',
			title : '创建人',
			align : 'left'
		}, {
			field : 'states',
			title : '状态',
			align : 'left',
			formatter:function(value, row){
				var containerId = "container"+row.id;
				var container = $('<div>').attr("id",containerId);
				if(value && value=='5'){
					$.get("account/adjust/sap.json?id="+row.id, function(res){
						if(res.data){
							var alink = '<a href="javascript:void(0);" onclick="viewFeedback(\''+row.id+'\',\''+res.data+'\');">错误信息</a>';
        	        		container.html(alink);
        	        		if($('#'+containerId)[0]){
        	        			$('#'+containerId).html(alink);
        	        		}else{
        	        			return container.prop("outerHTML");
        	        		}
						}
					});
					return container.prop("outerHTML");
				}
				
				return getAdjustStates(value+"");
			}
		},{
			field:'reason',
			title:'原因',
			align:'left',
			formatter : function(value) {
				return getAdjustReason(value+"");
			}
		}, {
			field : 'remark',
			title : '备注',
			align : 'left'
		} ]
	});
	var addValidator = initValidate("#addAdjustForm");
	var editValidator = initValidate("#editAdjustForm");
	$("#editAdjustForm").ajaxForm({
		target : '#btn-edit-save', // target element(s) to be updated with
									// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return editValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("修改失败",responseText.errorMessage);
            }else{
            	$("#editadjustDitection").val('');
    			$("#edittmerchCsutId").val('');
    			$("#editbankAccount").val('');
    			$("#editDitectiontype").addClass("hide");
    			$("#editmerch").addClass("hide");
    			$("#editbank").addClass("hide");
            	$("#editAdjustModal").modal("hide");
    			$("#adjustTable").bootstrapTable("refresh")
            }
		}
	});
	$("#addAdjustForm").ajaxForm({
		target : '#btn-add', // target element(s) to be updated with server
								// response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return addValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("添加失败",responseText.errorMessage);
            }else{
            	$.messager.popup("添加成功");
            	$("#adjustDitection").val('');//调整方向
    			$("#targetMerchCustId").val('');//对方客户
    			$("#bankAccount").val('');//调整银行
    			$("#merchCustId").val('');//调整客户
    			//$("#merchCustId").trigger("chosen:updated");
    			//$("#targetMerchCustId").trigger("chosen:updated");
    			//$("#accountType").css("display","none");
    			$("#merch").addClass("hide");
    			$("#bank").addClass("hide");
    			$("#Ditectiontype").addClass("hide");
            	$("#addAdjustModal").modal("hide");
    			$("#adjustTable").bootstrapTable("refresh")
            }
		}
	});
	$(".btn-edit").bind("click", doEdit);
	$(".btn-del").bind("click", doDel);
	$("#btn-audit").bind("click",doAudit);
	$("#btn-search").bind("click",doSearch);
	//获取销售组织
	/*var orgurl ="account/util/adjustorg";
	$.get(orgurl,function(data){
		if(data.rows&& data.rows.length>0){
			$('#organizationId').append("<option></option>");
			$('#editorganizationId').append("<option></option>");
			for (var i = 0; i < data.rows.length; i++) {
				$("#organizationId").append("<option value='"+data.rows[i].id+"'>"+data.rows[i].name+"</option>");
				$("#editorganizationId").append("<option value='"+data.rows[i].id+"'>"+data.rows[i].name+"</option>");
			}
		}
	});*/
	$('#merchCustId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	$('#targetMerchCustId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	$('#editmerchCustId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	$('#edittargetMerchCustId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	$("#organizationId").change(function(){
		//获取调整客户
		$('#merchCustId').empty();
		$('#targetMerchCustId').empty();
		$("#merchCustId").trigger("chosen:updated");
		$("#targetMerchCustId").trigger("chosen:updated");
		var orgid=$("#organizationId").val();
		var url = 'account/util/customer?orgid='+orgid;
		$.get(url, function(data) {
			if (data.rows && data.rows.length > 0) {
				$('#merchCustId').append("<option></option>");
				$('#targetMerchCustId').append("<option></option>");
				for (var i = 0; i < data.rows.length; i++) {
					$('#merchCustId').append("<option value='" + data.rows[i].merchCustId + "'>"+data.rows[i].sapCustomerId+ data.rows[i].name + "</option>");
					$('#targetMerchCustId').append("<option value='" + data.rows[i].merchCustId + "'>"+data.rows[i].sapCustomerId+ data.rows[i].name + "</option>");
				}
			}
			$("#merchCustId").trigger("chosen:updated");
			$("#targetMerchCustId").trigger("chosen:updated");
		});
	});
	
	$("#editorganizationId").change(function(){
		//获取调整客户
		$('#editmerchCustId').empty();
		$('#edittargetMerchCustId').empty();
		$("#editmerchCustId").trigger("chosen:updated");
		$("#edittargetMerchCustId").trigger("chosen:updated");
		var orgid=$("#editorganizationId").val();
		var url = 'account/util/customer?orgid='+orgid;
		$.get(url, function(data) {
			if (data.rows && data.rows.length > 0) {
				$('#editmerchCustId').append("<option></option>");
				$('#edittargetMerchCustId').append("<option></option>");
				for (var i = 0; i < data.rows.length; i++) {
					$('#editmerchCustId').append("<option value='" + data.rows[i].merchCustId + "'>"+data.rows[i].sapCustomerId+ data.rows[i].name + "</option>");
					$('#edittargetMerchCustId').append("<option value='" + data.rows[i].merchCustId + "'>"+data.rows[i].sapCustomerId+ data.rows[i].name + "</option>");
				}
			}
			$("#editmerchCustId").trigger("chosen:updated");
			$("#edittargetMerchCustId").trigger("chosen:updated");
		});
	});
	$("#accountType").change(function(){
		var type =$("#accountType").val();
		if(type ==1){
			$("#Ditectiontype").removeClass("hide");
		}else if(type==4){
			$("#adjustDitection").val('');//调整方向
			$("#tmerchCsutId").val('');//对方客户
			$("#bankAccount").val('');//银行
			$("#Ditectiontype").addClass("hide");//调整方向
			$("#merch").addClass("hide");//对方客户
			$("#bank").removeClass("hide");//银行
		}else{
			$("#adjustDitection").val('');//调整方向
			$("#tmerchCsutId").val('');//对方客户
			$("#bankAccount").val('');//银行
			$("#Ditectiontype").addClass("hide");//调整方向
			$("#merch").addClass("hide");//对方客户
			$("#bank").addClass("hide");//银行
		}
	});
	
	$("#adjustDirection").change(function(){
		var i= $("#adjustDirection").val();
		$("#bankAccount").val('');
		if(i==1){
			//客户间调整
			$("#merch").removeClass("hide");
			$("#bank").addClass("hide");
		}else if(i==2){
			//调整银行
			$("#merch").addClass("hide");
			$("#bank").removeClass("hide");
		}else{
			$("#merch").addClass("hide");
			$("#bank").addClass("hide");
		}
	})
	
	$("#editaccountType").change(function(){
		var type =$("#editaccountType").val();
		if(type ==1){
			$("#editDitectiontype").removeClass("hide");
		}else if(type==4){
			$("#editadjustDitection").val('');//调整方向
			$("#edittmerchCsutId").val('');//对方客户
			$("#editbankAccount").val('');//银行
			$("#editDitectiontype").addClass("hide");//调整方向
			$("#editmerch").addClass("hide");//对方客户
			$("#editbank").addClass("hide");//银行
			$("#editbank").removeClass("hide");//银行
		}else{
			$("#editadjustDitection").val('');//调整方向
			$("#edittmerchCsutId").val('');//对方客户
			$("#editbankAccount").val('');//银行
			$("#editDitectiontype").addClass("hide");//调整方向
			$("#editmerch").addClass("hide");//对方客户
			$("#editbank").addClass("hide");//银行
		}
	});
	
	$("#editadjustDirection").change(function(){
		var i= $("#editadjustDirection").val();
		$("#editbankAccount").val('');
		if(i==1){
			//客户间调整
			$("#editmerch").removeClass("hide");
			$("#editbank").addClass("hide");
		}else if(i==2){
			//调整银行
			$("#editmerch").addClass("hide");
			$("#editbank").removeClass("hide");
		}else{
			$("#editmerch").addClass("hide");
			$("#editbank").addClass("hide");
		}
		$("#edittargetMerchCustId").trigger("chosen:updated");
		$('#edittargetMerchCustId').chosen({
			no_results_text : "没有找到",
			allow_single_de : true,
			search_contains: true
		});
	})
	/*$("#amt").blur(function(){
		var orgid=$("#organizationId").val();
		if(!orgid){
			$.messager.alert("提示", "请选择销售组织");
		}
		var targeturl='account/util/customer?orgid='+orgid+"&amt="+$("#amt").val();
		$('#targetMerchCustId').html('');
		$.get(targeturl, function(data) {
			if (data.rows && data.rows.length > 0) {
				$('#targetMerchCustId').append("<option></option>");
				for (var i = 0; i < data.rows.length; i++) {
					$('#targetMerchCustId').append("<option value='" + data.rows[i].merchCustId + "'>"+ data.rows[i].name + "</option>");
				}
			}else{
				$("#adc").html('没有满足金额的对方客户');
			}
		});
		$("#targetMerchCustId").trigger("chosen:updated");
		$('#targetMerchCustId').chosen({
			no_results_text : "没有找到",
			allow_single_de : true
		});
	})*/
});

function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			amt : {
				required : true,
				number : 5,
				amt:true
			},
			organizationId:"required",
			merchCustId :{
				required : true,
				minlength:1
			},
			accountType:"required",
			reason :"required"
		},
		ignore: ""
	});
	return validator;
}
/**
 * 编辑按钮
 */
function doEdit() {
	var rows = $("#adjustTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		if(rows[0].states !="1" && rows[0].states !="4"){
			$.messager.alert("提示", "请选择要编辑或者审批驳回的记录!");
			return ;
		}
		//rows[0].amt = rows[0].amt/100;
		//获取客户信息
		if(rows[0].type=='1'){
			$("#editDitectiontype").addClass("hide");
			$("#editmerch").addClass("hide");
			$("#editbank").addClass("hide");
			if(rows[0].accountType=="1"){
				$("#editDitectiontype").removeClass("hide");
				if(rows[0].adjustDirection=="1"){
					$("#editmerch").removeClass("hide");
				}
				if(rows[0].adjustDirection=="2"){
					$("#editbank").removeClass("hide");
				}
			}else if(rows[0].accountType=="4"){
				$("#editbank").removeClass("hide");
			}
			$('#editmerchCustId').empty();
			$("#editmerchCustId").trigger("chosen:updated");
			$('#edittargetMerchCustId').empty();
			$("#edittargetMerchCustId").trigger("chosen:updated");
			var url = 'account/util/customer?orgid='+rows[0].organizationId;
			$.get(url, function(data) {
				if (data.rows && data.rows.length > 0) {
					$('#editmerchCustId').append("<option></option>");
					$.each(data.rows, function(i, val){
						$('#editmerchCustId').append('<option value="' + val.merchCustId + '">'+val.sapCustomerId+ val.name + '</option>');
						$('#edittargetMerchCustId').append('<option value="' + val.merchCustId + '">'+val.sapCustomerId+ val.name + '</option>');
					});
				}
				$("#editmerchCustId").trigger("chosen:updated");
				
				$("#edittargetMerchCustId").trigger("chosen:updated");
				fillDetail(rows[0]);
				var amt = rows[0].amt/100;
				$("#editamt").val(amt);
				$("#editmerchCustId").trigger("chosen:updated");
				$("#edittargetMerchCustId").trigger("chosen:updated");
				$("#editAdjustModal").modal("show")
			});
		}else{
			$.messager.alert("提示", "请选择手工调整的记录!");
		}
	} else {
		$.messager.alert("提示", "请选择要编辑的记录!");
	}
}
function doDel(){
    var rows = $("#adjustTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
    	if(rows[0].states==1){
    		$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
    			$.post("account/adjust/del",{"id":rows[0].id},function(){$("#adjustTable").bootstrapTable("refresh");})
    		});    		
    	}else{
    		 $.messager.alert("提示", "只有编辑状态的可删除!");
    	}
    }else{
        $.messager.alert("提示", "请选择要删除的记录!");
    }
}

function doAudit(){
	 var rows = $("#adjustTable").bootstrapTable("getSelections");
	    if(rows && rows.length ==1){
	    	if(rows[0].states=='1' || rows[0].states=='4'){
	    		if(rows[0].accountType==1 && !rows[0].adjustDirection){
	    			$.messager.popup("调整方向不可以为空，请修改后再提交");
	    			return;
	    		}
	    		if(rows[0].accountType==1 && rows[0].adjustDirection==1 && !rows[0].targetMerchCustId){
	    			$.messager.popup("调整对方客户不可以为空，请修改后再提交");
	    			return;
	    		}
	    		if(rows[0].accountType==1 && rows[0].adjustDirection==2 && !rows[0].bankAccount){
	    			$.messager.popup("银行账号不可以为空，请修改后再提交");
	    			return;
	    		}
	    		if(rows[0].accountType==4 &&  !rows[0].bankAccount){
	    			$.messager.popup("银行账号不可以为空，请修改后再提交");
	    			return;
	    		}
	    		$.messager.confirm("警告", "您确认要提交记录吗?", function() {
	    			$.post("account/adjust/audit",{"id":rows[0].id,"states":"2"},function(){$("#adjustTable").bootstrapTable("refresh");})
	    		});    		
	    	}else{
	    		 $.messager.alert("提示", "只有编辑状态的可提交!");
	    	}
	    }else{
	        $.messager.alert("提示", "请选择要提交的记录!");
	    }
}

function fillDetail(Obj) {
	for ( var i in Obj) {
		if (Obj[i] == null) {
			continue;
		}
		$("#edit" + i).val(Obj[i] + "");
	}
	//$("#editamt").val(Obj.amt/100);
}

function doSearch(){
	var  custname=$("#scustname").val();
	custname =encodeURI(custname);
	custname =encodeURI(custname);
	var  accountType=$("#saccountType").val();
	var  reason =$("#sreason").val();
	var  type =$("#stype").val();
	var  orgid=$("#sorgid").val();
	var url ="account/adjust/list?custname="+custname+"&accountType="+accountType+"&reason="+reason+"&type="+type+"&organizationId="+orgid;
	$("#adjustTable").bootstrapTable("refresh",{
		'url':url
	})
}

function viewFeedback(o,msg){
	if($("#adjustTable").data("sendSap"+o) == '1'){
		$.messager.alert("提示", "正在重新推送，请勿频繁点击!");
		return;
	}
	$.messager.confirm("信息", "接口信息："+msg+"<br/>是否需要重新发送SAP?", function() {
		$("#adjustTable").data("sendSap"+o,1);
        $.post("account/adjust/audit", {"id":$.trim(o), "states":"3"}, function(result){
        	if(result.errorCode != '-1' && result.data == '1'){
        		$.messager.popup("已提交SAP处理！");
        	}else{
        		$.messager.alert("提示", "重新推送SAP错误！");
        	}
        	$("#adjustTable").bootstrapTable("refresh");
        	$("#adjustTable").data("sendSap"+o,0);
    	})
    });
}
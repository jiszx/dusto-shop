$(function() {
	$('#adjustTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url: 'account/adjust/list?accountType=4&custType=7',
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
    			$("#merchCustId").val('');//调整客户
    			$("#merchCustId").trigger("chosen:updated");
            	$("#addAdjustModal").modal("hide");
    			$("#adjustTable").bootstrapTable("refresh")
            }
		}
	});
	$(".btn-edit").bind("click", doEdit);
	$(".btn-del").bind("click", doDel);
	$("#btn-audit").bind("click",doAudit);
	$("#btn-search").bind("click",doSearch);
	$('#merchCustId').chosen({
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
	$("#organizationId").change(function(){
		//获取调整客户
		$('#merchCustId').empty();
		$("#merchCustId").trigger("chosen:updated");
		var orgid=$("#organizationId").val();
		var url = 'account/util/customer?orgid='+orgid;
		$.get(url, function(data) {
			if (data.rows && data.rows.length > 0) {
				$('#merchCustId').append("<option></option>");
				for (var i = 0; i < data.rows.length; i++) {
					$('#merchCustId').append("<option value='" + data.rows[i].merchCustId + "'>"+data.rows[i].sapCustomerId+ data.rows[i].name + "</option>");
				}
			}
			$("#merchCustId").trigger("chosen:updated");
		});
	});
	
	$("#editorganizationId").change(function(){
		//获取调整客户
		$('#editmerchCustId').empty();
		$("#editmerchCustId").trigger("chosen:updated");
		var orgid=$("#editorganizationId").val();
		var url = 'account/util/customer?orgid='+orgid;
		$.get(url, function(data) {
			if (data.rows && data.rows.length > 0) {
				$('#editmerchCustId').append("<option></option>");
				for (var i = 0; i < data.rows.length; i++) {
					$('#editmerchCustId').append("<option value='" + data.rows[i].merchCustId + "'>"+data.rows[i].sapCustomerId+ data.rows[i].name + "</option>");
				}
			}
			$("#editmerchCustId").trigger("chosen:updated");
		});
	});
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
			reason :"required",
			bankAccount:"required"
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
	if (rows && rows.length == 1 &&(rows[0].states =="1" || rows[0].states =="4")) {
		$('#editmerchCustId').empty();
		$("#editmerchCustId").trigger("chosen:updated");
		var url = 'account/util/customer?orgid='+rows[0].organizationId;
		$.get(url, function(data) {
			if (data.rows && data.rows.length > 0) {
				$('#editmerchCustId').append("<option></option>");
				$.each(data.rows, function(i, val){
					$('#editmerchCustId').append('<option value="' + val.merchCustId + '">'+val.sapCustomerId+ val.name + '</option>');
				});
			}
			$("#editmerchCustId").trigger("chosen:updated");
			fillDetail(rows[0]);
			$("#editmerchCustId").trigger("chosen:updated");
			var amt = rows[0].amt/100;
			$("#editamt").val(amt);
			$("#editAdjustModal").modal("show")
		});
	} else {
		$.messager.popup("请选择要编辑或者审批驳回的记录!");
	}
}
function doDel(){
    var rows = $("#adjustTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1 &&(rows[0].states =="1" || rows[0].states =="4")){
    	$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
    		$.post("account/adjust/del",{"id":rows[0].id},function(){$("#adjustTable").bootstrapTable("refresh");})
    	});    		
    }else{
        $.messager.popup("请选择要编辑或者审批驳回的记录!");
    }
}

function doAudit(){
	 var rows = $("#adjustTable").bootstrapTable("getSelections");
	    if(rows && rows.length ==1 && (rows[0].states=='1' || rows[0].states=='4')){
	    	if(!rows[0].bankAccount){
	    		$.messager.popup("银行账号不可以为空，请修改后再提交");
	    		return;
	    	}
	    	$.messager.confirm("警告", "您确认要提交记录吗?", function() {
	    		$.post("account/adjust/audit",{"id":rows[0].id,"states":"2"},function(){$("#adjustTable").bootstrapTable("refresh");})
	    	});    		
	    }else{
	        $.messager.popup("请选择要编辑或者审批驳回的记录!");
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
	var url ="account/adjust/list?custname="+custname+"&accountType="+accountType;//+"&reason="+reason+"&type="+type+"&organizationId="+orgid;
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
var table = null;
var $table = $('#contract-table');
var flag = 0;
$(function() {
	initLine();
	$('#add-bstb-row').click(function() {
		$('.add-bstb-box').addClass("add-bstb-box-open");
	});
	$('#btn-hide-bstb-add-box').click(function() {
		$('.add-bstb-box').removeClass("add-bstb-box-open");
	});
	$("#agentType").bind("change", realoadSelection);
	$("#settleType").change(function(){
		if($("#settleType").val() !='2'){
			$("#tPeriod").css("display","none");
			$("#aPeriod").val('');
		}else if($("#settleType").val() =='2'){
			$("#tPeriod").css("display","block");
		}
	})
	$("#addPaperForm").ajaxForm({
		target : '#btn-savePaper',
		clearForm : false,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			$(".borderColor").removeClass("borderColor");
			var falg = true;

			var ir = /^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$)/;
			if(ir.test($("#creditAmt").val())==false){
				$("#creditAmt").parent().addClass("borderColor");
				falg = false;
			}
			if ($("#creditAmt").val().length <= 0) {
				$("#creditAmt").parent().addClass("borderColor");
				falg = false;
			}
			if(ir.test($("#edityearAmt").val())==false){
				$.messager.popup("年度进货目标只能输入大于0的数字，并且小于2位小数");
				falg = false;
			}
			
			if(ir.test($("#editrebate").val())==false){
				$.messager.popup("年度返利只能输入大于0的数字，并且小于2位小数");
				falg = false;
			}if(ir.test($("#editrebate").val())>=100){
				$.messager.popup("年度返利不能大于100%");
				falg = false;
			}
			if(falg ==false){
            	$("#btn-save").removeAttr("disabled");
            	$("#btn-submit").removeAttr("disabled");
            }
			return falg;
		},
		success : function(responseText, statusText, xhr, $form) {
			$("#btn-save").removeAttr("disabled");
			if (responseText.errorCode != 0) {
				$.messager.alert("添加合同信息失败", responseText.errorMessage);
			} else {
				$.messager.popup("合同头信息保存成功");
				$("#contractId").val(responseText.data);
				if(flag==1){
					var contractId = $("#contractId").val();
					if (contractId.length > 0) {
						$.post("customer/contract/updateContractStates", {
							"contractid" : contractId,
							"states" : "2"
						}, function(data) {
							if (data.data == "200") {
								$.messager.popup("提交审批成功!");
								doSave();
							} else {
								$.messager.alert("提示", "提交审批失败!");
							}
							window.location.href = "customer/page.html";
						})
					}
				}else{
					window.location.href = "customer/page.html";
				}
			}
		}
	});

	$("#addAgentForm").ajaxForm({
		target : '#btn-add-agent', 
		clearForm : false,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {		
			var ir = /^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$)/;
        	$(".borderColor").removeClass("borderColor");
        	var falg = true;
            if($("#yAmt").val().length <=0 ||  ir.test($("#yAmt").val())==false){
            	$("#yAmt").parent().addClass("borderColor");
            	falg = false;
            }
            if($("#yRatio").val().length <=0 ||  $("#yRatio").val()>100 || ir.test($("#yRatio").val())==false){
            	$("#yRatio").parent().addClass("borderColor");
            	falg = false;
            }
            if($("#qAmt").val().length <=0 ||  ir.test($("#qAmt").val())==false){
            	$("#qAmt").parent().addClass("borderColor");
            	falg = false;
            }
            if($("#qRatio").val().length <=0 ||  $("#qRatio").val()>100 || ir.test($("#qRatio").val())==false){
            	$("#qRatio").parent().addClass("borderColor");
            	falg = false;
            }
            if($("#agentId").val().length <=0){
            	$("#agentId").parent().addClass("borderColor");
            	falg = false;
            }
            return falg;
		},
		success : function(responseText, statusText, xhr, $form) {
			if (responseText.errorCode != 0) {
				$.messager.alert("添加失败", responseText.errorMessage);
			} else {
				$.messager.popup("添加成功");
				$table.bootstrapTable("refresh");
			}
		}
	});
	var editValidator = initValidate("#editLinesForm");
	$("#editLinesForm").ajaxForm({
		target : '#btn-edit-save', 
		clearForm : false,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return editValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if (responseText.errorCode != 0) {
				$.messager.alert("修改失败", responseText.errorMessage);
			} else {
				$table.bootstrapTable("refresh");
				$("#editLinesModel").modal("hide");
			}
		}
	});
	$('#agentId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	realoadAgent();
});

function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			yAmt : {
				number:true,
				amt:true
			},
			yRatio : {
				number:true,
				amt:true
			},
			qAmt : {
				number:true,
				amt:true
			},
			qRatio : {
				number:true,
				amt:true
			}
		}
	});
	return validator;
}

function realoadAgent(){
	$("#agentId").empty();
	$("#agentId").trigger("chosen:updated");
	var agentType =$("#agentType").val();
	$.get("customer/contract/getAgents?agentType="+agentType,function(res){
		if(res.rows && res.rows.length >0){
			$("#agentId").append("<option></option>");
			for(var i=0;i<res.rows.length;i++){
				$("#agentId").append("<option value="+res.rows[i].band+">"+res.rows[i].band+"</option>");
			}
			$("#agentId").trigger("chosen:updated");
		}
	});
}

function realoadSelection() {
	var type = $(this).val();
	$("#agent_type").val(type);
	var data = $("#contract-table").bootstrapTable('getData');
	if (data.length > 0) {
		$.messager.confirm("警告", "变更代理类型后会删除原有的代理产品，您确认要变更代理类型吗?", function() {
			for (var i = 0; i < data.length; i++) {
				$.post("customer/contract/delPaperLines", {
					"id" : data[i].id
				}, function() {
					$table.bootstrapTable("refresh");
				})
			}
			realoadAgent();
			changeAgentSelect();
		});
	}else{
		changeAgentSelect();
	}
}

function changeAgentSelect(){
	var contractId =$("#contractId").val();
	$.post("customer/contract/addLineByagentType?contractId="+contractId+"&agentType="+$("#agentType").val(),function(res){
		if (res.errorCode != 0) {
				$.messager.popup(res.errorMessage);
			} else {
				$table.bootstrapTable("refresh");
			}
	})
}

function initLine() {
	$table.bootstrapTable({
		url : 'customer/contract/lines',
		sidePagination : "server",
		showRefresh : true,
		onCheck : function(row, element) {
		},
		clickToSelect : true,
		queryParams : function(params) {
			params.id = $("#contractId").val();
			return params;
		},
		columns : [ {
			field : 'ck',
			radio : true,
			rowspan : 1,
			align : 'center',
			valign : 'middle'
		}, {
			title : 'ID',
			field : 'typeId',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '名称',
			field : 'name',
			rowspan : 1,
			align : 'center',
			valign : 'middle'
		},
		/*{
			title : '代理类别',
			field : 'agentType',
			rowspan : 1,
			align : 'center',
			valign : 'middle'
		}, */
		{
			title : '年度进货目标(元)',
			field : 'yAmt',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
            formatter:function(value){
            	return (value/100).formatMoney();
            }
		}, {
			title : '年度返利比例',
			field : 'yRatio',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
			formatter : function(value) {
				return value + "%";
			}
		}, {
			title : '季度进货目标(元)',
			field : 'qAmt',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
            formatter:function(value){
            	return (value/100).formatMoney();
            }
		}, {
			title : '季度返利比例',
			field : 'qRatio',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
			formatter : function(value) {
				return value + "%"
			}
		}, {
			title : '时间',
			field : 'testTime',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false,
			editable : {
				type : 'date',
				title : '选择修改时间:'
			}
		} ]
	});
	$("#del-bstb-row").bind("click", doDelete);
	$("#edit-bstb-row").bind("click", doEdit);
	$("#btn-save").bind("click", doSave);
	$("#btn-submit").bind("click", doSubmit);
}
function doDelete() {
	var rows = $table.bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
			$.post("customer/contract/delPaperLines", {
				"id" : rows[0].id
			}, function() {
				$table.bootstrapTable("refresh");
			})
		});
	} else {
		$.messager.alert("提示", "请选择要删除的记录!");
	}
}
function doEdit() {
	var rows = $table.bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		fillEdit(rows[0]);
		$("#editLinesModel").modal("show")
	} else {
		$.messager.alert("提示", "请选择要编辑的记录!");
	}
}

function doSave() {
	$("#btn-save").attr("disabled","true");
	$("#addPaperForm").submit();
}

function doSubmit() {
	flag =1;
	$("#btn-save").attr("disabled","true");
	$("#btn-submit").attr("disabled","true");
	$("#addPaperForm").submit();
	
}

function fillEdit(Obj){
    for(var i in Obj){
        if(Obj[i]==null){
            continue;
        }
        if(i=='yAmt' || i=='qAmt'){
        	$("#edit"+i).val(Obj[i]/100);
        }else{
        	$("#edit"+i).val(Obj[i]+"");
        }
        
    }
}
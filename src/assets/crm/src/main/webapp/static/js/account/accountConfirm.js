var flag="0";
$(function() {
	$('#upAccountTable').bootstrapTable({
		url : 'account/upaccount/upAccountList',
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
		//search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : 'ck',
			title : '编号',
			radio : true
		}, {
			field : 'id',
			title : '编号',
			visible : false
		}, {
			field : 'orgname',
			title : '所属销售组织',
			align : 'left'
		}, {
			field : 'payName',
			title : '打款人',
			align : 'left'
		}, {
			field : 'payBankNo',
			title : '打款账户',
			align : 'left'
		}, {
			field : 'payAmt',
			title : '打款金额',
			align : 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
		},{
			field : 'bankSerial',
			title : '流水号',
			align : 'left'
		}, {
			field : 'payBank',
			title : '打款银行',
			align : 'left',
			formatter :function(value){
				return  getBank(value+"");
			}
		}, {
			field : 'payCity',
			title : '来款城市',
			align : 'left'
		}, {
			field : 'payDate',
			title : '打款时间',
			align : 'left'
		}, {
			field : 'payType',
			title : '资金类型',
			align : 'left',
			formatter : function(value) {
				return getUpamtType(value + "");
			}
		}, {
			field : 'createTs',
			title : '录入时间',
			align : 'left'
		}, {
			field : 'creater',
			title : '录入人员',
			align : 'left'
		}, {
			field : 'custname',
			title : '客户名称',
			align : 'left'
		}, {
			field : 'sapCustomerId',
			title : 'sap客户编码',
			align : 'left'
		}, {
			field : 'salesrepDate',
			title : '确认时间',
			align : 'left'
		}, {
			field : 'salesrep',
			title : '确认人员',
			align : 'left'
		}, {
			field : 'states',
			title : '状态',
			align : 'left',
			formatter : function(value,data) {
				return getUpStatesValue(value + "");
				/*if(value=='6'){
					var res = '<a href="javascript:void(0);" onclick="doSAP(\''+data.id+'\');">'+getUpStatesValue(value + "")+'</a>';
					return res;
				}else{					
					return getUpStatesValue(value + "");
				}*/
			}
		} ]
	});
	uptype();
	var editValidator = initValidate("#editUpaccountForm");
	$("#editUpaccountForm").ajaxForm({
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
            	if(flag=="1"){
            		$.post("account/upaccount/submit.json", {"id":$("#editid").val(),"states":"5"}, function(res){
    					$.messager.alert("提示",res.data.msg);
    				});
            	}
            	$("#editDictModal").modal("hide");
    			$("#dictTable").bootstrapTable("refresh")
            }
			$("#editUpaccountModal").modal("hide");
			$("#upAccountTable").bootstrapTable("refresh")
		}
	});
	
	$(".btn-edit").bind("click", doEdit);
	$(".btn-back").bind("click",doBack);
	$(".btn-pass").bind("click",doPass);
	$("#btn-search").bind("click",doSearch);
	$("#audit-back").bind("click",doBack);
	$("#audit-pass").bind("click",doSubmitPass);
	$("#btn-pass-agin").bind("click",doSendSap)
});

function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			payName : {
				required:true,
				maxlength:5
			},
			payAmt : {
				required : true,
				number : true
			},
			payBank : "required",
			payBankNo : {
				required : true,
				minlength: 4,
				maxlength: 4,
				digits:true
			},
			payType:"required",
			//payCity:"required",
			payDate:"required"
		}
	});
	return validator;
}

//驳回
function doBack(){
	var rows = $("#upAccountTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 ) {
		$.post("account/upaccount/submit.json", {"id":rows[0].id,"states":"4"}, function(res){
			$.messager.alert("提示",res.data.msg);
			$("#upAccountTable").bootstrapTable("refresh");
		});
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}

//通过
function doPass(){
	var rows = $("#upAccountTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		$("#submit").attr("disabled","true");
		$.post("account/upaccount/submit.json", {"id":rows[0].id,"states":"5"}, function(res){
			 $.messager.alert("提示",res.data.msg);
			 $("#submit").removeAttr("disabled");
			$("#upAccountTable").bootstrapTable("refresh");
		});
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}
function doSAP(id){
	//获取资金上账错误信息
	$.get("account/upaccount/getSapMsg?id="+id,function(res){
		if(res.data){
			$("#sapmsg").html(res.data);
			$("#upaccountid").val(id);
			$("#upaccountModal").modal("show");
		}
	});
	/*$.post("account/upaccount/submit.json", {"id":id,"states":"5"}, function(res){
		$.messager.alert("提示",res.data.msg);
		$("#upAccountTable").bootstrapTable("refresh");
	});*/
}
function doSendSap(){
	$.post("account/upaccount/submit.json", {"id":$("#upaccountid").val(),"states":"5"}, function(res){
		$.messager.alert("提示",res.data.msg);
		$("#upAccountTable").bootstrapTable("refresh");
	});
}
function  doSubmitPass(){
	flag="1";
	$("#editUpaccountForm").submit();
	
}
/**
 * 查询
 */
function doSearch(){
	var custname =$("#custname").val();
	var spayBankNo =$("#spayBankNo").val();
	var spayCity =$("#spayCity").val();
	var sorgid =$("#sorgid").val();
	var spayType =$("#spayType").val();
	var sbankSerial =$("#sbankSerial").val();
	var url="account/upaccount/upAccountList?organizationId="+sorgid+"&payType="+spayType
			+"&payCity="+spayCity+"&payBankNo="+spayBankNo+"&custname="+custname+"&bankSerial="+sbankSerial;
	$("#upAccountTable").bootstrapTable('refresh',{
		'url':url
	});
}



function doEdit() {
	var rows = $("#upAccountTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
			autoEdit(rows[0]);
			$("#editUpaccountModal").modal("show")
	} else {
		$.messager.alert("提示", "请选择要编辑的记录!");
	}
}



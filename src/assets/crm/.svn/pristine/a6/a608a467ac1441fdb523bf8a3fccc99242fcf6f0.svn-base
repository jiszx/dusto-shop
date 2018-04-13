var flag='0';
$(function() {
	$.validator.addMethod("bankSerial",function(value,element){
		var row =$('#upAccountTable').bootstrapTable("getSelections");
		if (row[0].bankSerial ==value) {
			return true;
		}else{
			return false;
		}
	},"流水号错误，请输入正确的流水号");
	
	$('#upAccountTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'account/upaccount/salesAccountList',
		// data:data,
		cache : false,
		toolbar : "#salesUpAccountTool",
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
			radio : true
		},{
			field : 'id',
			title : '编号',
			visible:false
		}, {
			field : 'orgname',
			title : '销售组织',
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
		}, {
			field : 'payBank',
			title : '打款银行',
			align : 'left',
			formatter :function(value){
				return  getBank(value + "");
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
			align : 'left'	
			,formatter : function(value) {
				return getsalesUpamtType(value + "");
			}
		}, {
			field : 'createTs',
			title : '录入时间',
			align : 'left'
		}, {
			field : 'creater',
			title : '录入人',
			align : 'left'
		}, {
			field : 'custname',
			title : '客户名称',
			align : 'left'
		}, {
			field : 'sapCustomerId',
			title : 'SAP客户编码',
			align : 'left'
		}, {
			field : 'states',
			title : '状态',
			align : 'left'
				,
			formatter : function(value) {
				return getsalesUpStatesValue(value + "");
			}
		} ]
	});
	var editValidator = initValidate("#editUpaccountForm");
	$("#editUpaccountForm").ajaxForm({
		target : '#btn-edit-save', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
				//验证流水号是否必须
			var bankSerial =$("#bankSerial").val();
			var rows = $("#upAccountTable").bootstrapTable("getSelections");
			if(rows[0].bankSerial !=bankSerial){
				$.messager.popup("流水号错误");
				return false;
			}
			return editValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("资金确认失败",responseText.errorMessage);
            }else{
            	if(flag=='1'){
            		$.post("account/upaccount/submit.json", {"id":$("#editid").val(),"states":"3"}, function(res){
    					$.messager.popup(res.data.msg);
    				});
            	}else{            		
            		$.messager.popup("资金确认成功");
            	}
            	$("#editUpaccountModal").modal("hide");
    			$("#upAccountTable").bootstrapTable("refresh")
            }
			//$("#editUpaccountModal").modal("hide");
			//$("#upAccountTable").bootstrapTable("refresh")
		}
	});
	
	//$("#fundsDetail").bind("click", showFundsDetail);
	$("#audit").bind("click",doAudit);
	$("#confirm").bind("click",doConfirm);
	$("#btn-search").bind("click",doSearch);
	$("#btn-submit").bind("click",doSubmit);
	$('#merchCusId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
});

function doConfirm(){
	var rows = $("#upAccountTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		autoEdit(rows[0]);
		$("#merchCusId").html('');
		$("#editpayTypeName").val(getsalesUpamtType(rows[0].payType + ""));
		var url ='account/upaccount/accountcustomer.json?orgid='+rows[0].organizationId;
		$.get(url,function(data){
			$("#merchCusId").append('<option value="0">空值</option>');
			if(data.rows && data.rows.length>0){
				for(var i=0;i<data.rows.length;i++){
					$("#merchCusId").append('<option value="'+data.rows[i].id+'">'+data.rows[i].sapCustomerId+data.rows[i].name+'</option>');
				}
				$("#merchCusId").val(rows[0].merchCusId);
				$("#merchCusId").trigger("chosen:updated");
			}
		});
		autoEdit(rows[0]);
		$("#editUpaccountModal").modal("show");
	} else {
		$.messager.alert("提示", "请选择要查看的记录!");
	}
}

function doAudit(){
	var rows = $("#upAccountTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		if(!rows[0].custname || rows[0].custname.length<1){
			$.messager.alert("提示","请先确认资金！");
			return;
		}
		$.messager.confirm("警告", "您确认要提交此记录吗?", function() {
			$.post("account/upaccount/submit.json", {"id":rows[0].id,"states":"3"}, function(res){
				$.messager.popup(res.data.msg);
				$("#upAccountTable").bootstrapTable("refresh");
			});
		});
	} else {
		$.messager.popup("请选择记录!");
	}
}



/**
 * 将json对象回填
 * @param Obj
 */

function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			merchCusId:"required",
			bankSerial:{
				required : true,
				bankSerial:true
			}
		}
	});
	return validator;
}


function doSearch(){
	//var custname =$("#custname").val();
	var spayBankNo =$("#spayBankNo").val();
	var spayName =$("#spayName").val();
	var sorgid =$("#sorgid").val();
	//var spayType =$("#spayType").val();
	var url="account/upaccount/salesAccountList?orgid="+sorgid+"&payName="+spayName+"&payBankNo="+spayBankNo;
	$("#upAccountTable").bootstrapTable('refresh',{
		'url':url
	});
}
function doSubmit(){
	flag='1';
	$("#editUpaccountForm").submit();
}
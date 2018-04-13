var flag="0";
var merchCustId;
var merchdata;
var editmerchdata;
$(function() {
	$('#upAccountTable').bootstrapTable({
		url : 'account/upaccount/inputList',
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
			field : 'payAmt',
			title : '金额',
			align : 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
		},{
			field : 'bankSerial',
			title : '流水号',
			align : 'left'
		},
		/*{
			field : 'payName',
			title : '打款人',
			align : 'left'
		}, {
			field : 'payBankNo',
			title : '打款账户',
			align : 'left'
		},  {
			field : 'payBank',
			title : '打款银行',
			align : 'left'
		}, {
			field : 'payCity',
			title : '来款城市',
			align : 'left'
		}, {
			field : 'payDate',
			title : '打款时间',
			align : 'left'
		},*/
		{
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
			field : 'states',
			title : '状态',
			align : 'left',
			formatter : function(value) {
				return getUpStatesValue(value + "");
			}
		} ]
	});
	uptype();
	var addValidator = initValidate("#addUpaccountForm");
	var billaddValidator = billinitValidate("#addUpaccountForm");
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
            	if(responseText.data.type=="500"){
    				$.messager.alert("错误提示",responseText.data.code);
    			}else{
    				if(flag=="1" && $("#editmerchCusId").val()){
    					$.messager.confirm("警告", "已选择客户会直接上账，请问确定提交吗", function() {
    						$.post("account/upaccount/submit.json", {"id":$("#editid").val(),"states":"5"}, function(res){
            					$.messager.alert("提示",res.data.msg);
            				});
    					});
        			}else{    				
        				$.messager.popup("来款修改信息成功");
        			}
    				$("#editmerchCusId").html('');
    				$("#editmerchCusId").trigger("chosen:updated");
    				$("#editcash").css("display","none");
    				$("#editbill").css("display","none");
    				$("#editUpaccountModal").modal("hide");
    				$("#upAccountTable").bootstrapTable("refresh")
    			}
            }
		}
	});
	$("#addUpaccountForm").ajaxForm({
		target : '#btn-add', // target element(s) to be updated with server
		// response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			if($("#payType").val() =='2' && !$("#merchCusId").val()){
				$.messager.popup("承兑票据必须选择客户");
				return false;
			}
			var result = $("#payType").val() =='2'?billaddValidator.valid():addValidator.valid();
			if(result){
				$("#addUpaccountModal").modal("hide");
			}
			return result;
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("新增录入信息失败",responseText.errorMessage);
            }else{
    			if(responseText.data.type=="500"){
    				$.messager.alert("错误提示",responseText.data.code);
    			}else{
    				if(flag =="1" && $("#merchCusId").val()){
    					$.messager.confirm("警告", "已选择客户会直接上账，请问确定提交吗", function() {
    						$.post("account/upaccount/submit.json", {"id":responseText.data.code,"states":"5"}, function(res){
            					$.messager.alert("提示",res.data.msg);
            				});
    					});
        			}else{    				
        				$.messager.popup("来款录入信息成功");
        			}
    				$("#merchCusId").html('');
    				$("#merchCusId").trigger("chosen:updated");
    				$("#cash").css("display","none");
    				$("#bill").css("display","none");
        			$("#addUpaccountModal").modal("hide");
        			$("#upAccountTable").bootstrapTable("refresh")
    			}
            }
		}
	});
	$(".btn-edit").bind("click", doEdit);
	$(".btn-del").bind("click", doDel);
	$("#btn-edit-submit").bind("click", doEditSubmit);
	$("#btn-add-submit").bind("click", doAddSubmit);
	$("#submit").bind("click",submitUpaccount);
	$("#btn-search").bind("click",doSearch);
	$("#btn-show").bind("click",doShow);
	$("#payType").change(function(){
		var type =$("#payType").val();
		cleancash('');
		cleanbill('');
		if(type==1){
			$("#cash").css("display","block");
			$("#bill").css("display","none");
		}else if(type==2){
			$("#cash").css("display","none");
			$("#bill").css("display","block");
		}else{
			$("#cash").css("display","none");
			$("#bill").css("display","none");
		}
	});
	
	$("#editpayType").change(function(){
		var type =$("#editpayType").val();
		cleancash('edit');
		cleanbill('edit');
		if(type==1){
			$("#editcash").css("display","block");
			$("#editbill").css("display","none");
		}else if(type==2){
			$("#editcash").css("display","none");
			$("#editbill").css("display","block");
		}else{
			$("#editcash").css("display","none");
			$("#editbill").css("display","none");
		}
	});

	$('#merchCusId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	$('#editmerchCusId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	$("#organizationId").change(function(){
		$('#merchCusId').empty();
		$("#merchCusId").trigger("chosen:updated");
		var url ='account/upaccount/accountcustomer.json?orgid='+$("#organizationId").val();
		$.get(url,function(data){
			if(data.rows && data.rows.length>0){
				merchdata =data.rows;
				$("#merchCusId").append('<option value="0">空值</option>');
				for(var i=0;i<data.rows.length;i++){
					$("#merchCusId").append('<option value="'+data.rows[i].id+'">'+data.rows[i].sapCustomerId+data.rows[i].name+'</option>');
				}
			}
			$("#merchCusId").trigger("chosen:updated");
		});
	})
	$("#editorganizationId").change(function(){
		$('#editmerchCusId').empty();
		$("#editmerchCusId").trigger("chosen:updated");
		var url ='account/upaccount/accountcustomer.json?orgid='+$("#editorganizationId").val();
		$.get(url,function(data){
			if(data.rows && data.rows.length>0){
				editmerchdata =data.rows;
				$("#editmerchCusId").append('<option value="0">空值</option>');
				for(var i=0;i<data.rows.length;i++){
					$("#editmerchCusId").append('<option value="'+data.rows[i].id+'">'+data.rows[i].sapCustomerId+data.rows[i].name+'</option>');
				}
			}
			$("#editmerchCusId").trigger("chosen:updated");
		});
	})
	$("#merchCusId").change(function(){
		$("#isAllocation").val('');
		$("#Allocation").addClass("hide");
		var id = $("#merchCusId").val();
		for(var i=0;i<merchdata.length;i++){
			if(merchdata[i].id ==id && (merchdata[i].custType=='7' || merchdata[i].custType=='70')){
				$("#Allocation").removeClass("hide");
			}
		}
	})
	
	$("#editmerchCusId").change(function(){
		$("#editisAllocation").val('');
		$("#editAllocation").addClass("hide");
		var id = $("#editmerchCusId").val();
		for(var i=0;i<editmerchdata.length;i++){
			if(editmerchdata[i].id ==id && (editmerchdata[i].custType=='7' || editmerchdata[i].custType=='70')){
				$("#editAllocation").removeClass("hide");
			}
		}
	})
	/*$('#addUpaccountModal').on('hide.bs.modal', function() {
		$('#addUpaccountForm')[0].reset();
	})*/
});
function cleancash(value){
	$("#"+value+"bankAccount").val("");
	$("#"+value+"payName").val("");
	$("#"+value+"payBank").val("");
	$("#"+value+"payCity").val("");
	$("#"+value+"payBankNo").val("");
	$("#"+value+"payDate").val("");
}
function cleanbill(value){
	$("#"+value+"billOutDate").val("");
	$("#"+value+"billInDate").val("");
	$("#"+value+"billNo").val("");
	$("#"+value+"billBank").val("");
	$("#"+value+"billOutName").val("");
	$("#"+value+"billInName").val("");
	$("#"+value+"merchCusId").val("");
}
function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			payName : {
				maxlength:30
			},
			payAmt : {
				required : true,
				number : true,
				min :0.01,
				amt:true
			},
			payBankNo : {
				minlength: 4,
				maxlength: 4,
				digits:true
			},
			payType:"required",
			organizationId:"required",
			payCity:{
				//"required":true,
				maxlength:5
			},
			bankSerial:"required"
		}
	});
	return validator;
}
function billinitValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			payName : {
				maxlength:5
			},
			payAmt : {
				required : true,
				number : true,
				min :0.01,
				amt:true
			},
			payType:"required",
			organizationId:"required",
			billOutDate:"required",
			billInDate:"required",
			billNo:"required",
			billBank:"required",
			billOutName:"required",
			billInName:"required",
			merchCusId :"required"
		}
	});
	return validator;
}
/*function doShow() {
	var rows = $("#dictTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		showDetail("system/dict/detail?id=" + rows[0].id);
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}

function showFundsDetail() {
	var rows = $("#upAccountTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		fillFundsDetail(rows[0]);
		showFundsList(rows[0].id);
	} else {
		$.messager.alert("提示", "请选择要查看的记录!");
	}
}*/

/*function showFundsList(upid) {
	var html = "";
	var url = "account/upaccount/funds/detail.json?upid=" + upid;
	$.get(url, function(data) {
		if (data.rows && data.rows.length > 0) {
			for (var i = 0; i < data.rows.length; i++) {
				html = html + '<tr><td>' + data.rows[i].orgname + '</td><td>'
						+ data.rows[i].custname + '</td><td>'
						+ data.rows[i].amt + '</td></tr>'
			}
		}
		$("#orderdetail").html(html);
		$("#showUpaccountModal").modal("show");
	});

}*/

function submitUpaccount(){
	var states ='2';
	var rows = $("#upAccountTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && (rows[0].states=='1' || rows[0].states=='4')) {
			$("#submit").attr("disabled","true");
			if(rows[0].merchCusId){
				var str ="";
				if(rows[0].custType=='7' || rows[0].custType=='7'){
					str =rows[0].isAllocation =="1"?"选择了自动分配会自动分配资金到对应的零售商商，请问确定提交吗?":"所选客户为仓储商(合作仓储商)资金会添加到授信中，请问确定提交吗?";
					//仓储服务商验证
					$.post("account/upaccount/validateStorage?id="+rows[0].id+"&merchCustId="+rows[0].merchCusId,function(res){
						if(res.data.type =='S'){
							//验证成功
							submit(rows[0].id,str,"5");
						}else{
							$("#submit").removeAttr("disabled");
							$.messager.popup(res.data.msg);
						}
					})
				}
				/*else if(rows[0].custType=='70'){
					str="已选择客户会直接上账，请问确定提交吗?";
					//合资仓储服务商
					submit(rows[0].id,str,"5");
				}*/
				else{
					str  =rows[0].custType=="2"?"所选客户为配送商客户，提交后可匹配订单，请问确定提交吗":"已选择客户会直接上账，请问确定提交吗";
					var states = rows[0].custType=="2"?"3":"5";
					submit(rows[0].id,str,states);
				}
			}else{
				submit(rows[0].id,"请确定是否提交",states);
			}
	} else {
		$.messager.popup("请选择记录!");
	}
}
function submit(id,str,states){
	$.messager.confirm("警告", str, function() {
		$.post("account/upaccount/submit.json", {"id":id,"states":states}, function(res){
			$("#submit").removeAttr("disabled");
			$.messager.popup(res.data.msg);
			$("#upAccountTable").bootstrapTable("refresh");
		});
	});
	
}
/**
 * 查询
 */
function doSearch(){
	var custname =$("#custname").val();
	custname =encodeURI(custname);
	custname =encodeURI(custname);
	var spayBankNo =$("#spayBankNo").val();
	var spayCity =$("#spayCity").val();
	var sorgid =$("#sorgid").val();
	var spayType =$("#spayType").val();
	var sbankSerial =$("#sbankSerial").val();
	var url="account/upaccount/inputList?organizationId="+sorgid+"&payType="+spayType
			+"&payCity="+spayCity+"&payBankNo="+spayBankNo+"&custname="+custname+"&bankSerial="+sbankSerial;
	$("#upAccountTable").bootstrapTable('refresh',{
		'url':url
	});
}

/**
 * 将json对象回填
 * 
 * @param Obj
 */
/*function fillFundsDetail(Obj) {
	for ( var i in Obj) {
		if (Obj[i] == null) {
			continue;
		}
		$("#show" + i).val(Obj[i] + "");
	}
}*/

function doEdit() {
	var rows = $("#upAccountTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		if (rows[0].states == 1) {
			$("#editUpaccountForm").find(':input').not(':button, :submit, :reset').val('')
				.removeAttr('checked').removeAttr('selected');
			if(rows[0].payType ==1){
				$("#editcash").css("display","block");
				$("#editbill").css("display","none");
			}else{
				$("#editcash").css("display","none");
				$("#editbill").css("display","block");
			}
			if(rows[0].billOutDate && rows[0].payType =='2'){
				rows[0].billOutDate =toYyyyMMdd(rows[0].billOutDate);
			}else{
				rows[0].billOutDate="";
			}
			if(rows[0].billInDate && rows[0].payType =='2'){
				rows[0].billInDate =toYyyyMMdd(rows[0].billInDate);
			}else{
				rows[0].billInDate="";
			}
			if(rows[0].isAllocation=='1'){
				$("#editAllocation").removeClass("hide");
			}else{
				$("#editAllocation").addClass("hide");
			}
			var url ='account/upaccount/accountcustomer.json?orgid='+rows[0].organizationId;
			$.get(url,function(data){
				$("#editmerchCusId").html('');
				$("#editmerchCusId").append('<option value="0">空值</option>')
				if(data.rows && data.rows.length>0){
					editmerchdata = data.rows;
					for(var i=0;i<data.rows.length;i++){
						$("#editmerchCusId").append('<option value="'+data.rows[i].id+'">'+data.rows[i].sapCustomerId+data.rows[i].name+'</option>');
					}
				}
				autoEdit(rows[0]);
				$("#editmerchCusId").val(rows[0].merchCusId);
				$("#editmerchCusId").trigger("chosen:updated");
				$("#editUpaccountModal").modal("show");
			});
			
		} else {
			$.messager.alert("提示", "编辑状态的录入信息可修改!");
		}
	} else {
		$.messager.alert("提示", "请选择要编辑的记录!");
	}
}
function toYyyyMMdd(date) {  
    var d = new Date(date);  
    var dd = d.getDate() < 10 ? "0" + d.getDate() : d.getDate().toString();  
    var mmm = d.getMonth()+1;  
    var yyyy = d.getFullYear().toString(); //2011  
        //var YY = YYYY.substr(2);   // 11  
    return yyyy+'-'+ mmm.toString()+'-'+dd;  
}
function doShow(){
	var rows = $("#upAccountTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		if(rows[0].billOutDate && rows[0].payType =='2'){			
			rows[0].billOutDate =toYyyyMMdd(rows[0].billOutDate);
		}
		if(rows[0].billInDate && rows[0].payType =='2'){			
			rows[0].billInDate =toYyyyMMdd(rows[0].billInDate);
		}
		var url ='account/upaccount/accountcustomer.json?orgid='+rows[0].organizationId;
		$.get(url,function(data){
			$("#showmerchCusId").html('');
			$("#showmerchCusId").append('<option></option>')
			if(data.rows && data.rows.length>0){
				for(var i=0;i<data.rows.length;i++){
					$("#showmerchCusId").append('<option value="'+data.rows[i].id+'">'+data.rows[i].sapCustomerId+data.rows[i].name+'</option>');
				}
				/*$("#showmerchCusId").trigger("chosen:updated");
				$('#showmerchCusId').chosen({
					no_results_text : "没有找到",
					allow_single_de : true
				});*/
			}
			
			autoShow(rows[0]);
			$("#showUpaccountModal").modal("show");
			});
			if(rows[0].payType ==1){
				$("#showcash").css("display","block");
				$("#showbill").css("display","none");
			}else{
				$("#showcash").css("display","none");
				$("#showbill").css("display","block");
			}
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}
/*初始化客户信息*/
/*$('#showmerchCusId').chosen({
	no_results_text : "没有找到",
	allow_single_de : true,
	width:"100%"
});*/
function autoShow(Obj){
    for(var i in Obj){
        if(Obj[i]==null){
            continue;
        }
        $("#show"+i).val(Obj[i]+"");
    }
}
function doDel() {
	var rows = $("#upAccountTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		if (rows[0].states == 1) {
			$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
				$.post("account/upaccount/del", {
					"id" : rows[0].id
				}, function() {
					$("#upAccountTable").bootstrapTable("refresh");
				})
			});
		} else {
			$.messager.alert("提示", "只有编辑状态的可删除!");
		}
	} else {
		$.messager.alert("提示", "请选择要删除的记录!");
	}
}
var flag =0;
function doEditSubmit(){
	flag ="1";
	$("#editUpaccountForm").submit();
	//submitUpaccount();
}

function doAddSubmit(){
	flag ="1";
	merchCustId=$("#merchCusId").val();
	$("#addUpaccountForm").submit();
	//submitUpaccount();
}
$(document).ready(function(){
	$("#showpayDate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
	$("#editpayDate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
	$("#payDate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
	$("#billOutDate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
	$("#billInDate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
	$("#editbillOutDate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
	$("#editbillInDate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
	$("#showbillOutDate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
	$("#showbillInDate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
});
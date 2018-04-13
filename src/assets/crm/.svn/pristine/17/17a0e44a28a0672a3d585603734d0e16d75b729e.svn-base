$(function() {
	$('#upAccountTable').bootstrapTable({
		url : 'account/upaccount/upAccountsearchList',
		method : 'get',
		classes : "table table-hover table-condensed",
		// data:data,
		cache : false,
		//toolbar : "#upAccountTool",
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
		},
		{
			field : 'payName',
			title : '打款人',
			align : 'left',
			visible : false
		}, {
			field : 'payBankNo',
			title : '打款账户',
			align : 'left',
			visible : false
		}, 
		{
			field : 'payAmt',
			title : '打款金额',
			align : 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
		},
		{
			field : 'payBank',
			title : '打款银行',
			align : 'left',
			formatter :function(value){
				return  getBank(value+"");
			},
			visible : false
		}, 
		{
			field : 'unreceviceAmt',
			title : '未核销金额',
			align : 'left',
			formatter:function(value){
	           return value.formatMoney();
	        }
		},
		{
			field : 'payCity',
			title : '来款城市',
			align : 'left',
			visible : false
		}, {
			field : 'payDate',
			title : '打款时间',
			align : 'left',
			visible : false
		},{
			field : 'bankSerial',
			title : '流水号',
			align : 'left'
		},
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
			field : 'custname',
			title : '客户名称',
			align : 'left'
		}, {
			field : 'sapCustomerId',
			title : 'sap客户编码',
			align : 'left'
		}, {
			field : 'custType',
			title : '客户类型',
			align : 'left',
			formatter : function(value) {
				return getcustTypeValue(value + "");
			}
		}, {
			field : 'salesrepDate',
			title : '确认时间',
			align : 'left',
			visible : false
		}, {
			field : 'salesrep',
			title : '确认人员',
			align : 'left',
			visible : false
		}, 
		{
			field : 'salesrepDate',
			title : '审批时间',
			align : 'left'
			,visible : false
		}, {
			field : 'salesrep',
			title : '审批人员',
			align : 'left'
			,visible : false
		},
		{
			field : 'states',
			title : '状态',
			align : 'left',
			formatter : function(value,data) {
				//return getUpStatesValue(value + "");
				if(data.attribute2){
					var res = '<a href="javascript:void(0);" onclick="doSAP(\''+data.id+'\',\''+data.attribute2+'\');">'+getUpStatesValue(value + "")+'</a>';
					return res;
				}else{					
					return getUpStatesValue(value + "");
				}
			}
		} ]
	});
	$("#btn-search").bind("click",doSearch);
	uptype();	
});


function doSAP(id,str){
	//获取资金上账错误信息
	$.messager.confirm("警告", str+",点击确定重新发送", function() {
		$.post("account/upaccount/submit.json", {"id":id,"states":"5"}, function(res){
			$.messager.alert("提示",res.data.msg);
			$("#upAccountTable").bootstrapTable("refresh");
		});
	});
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
	var url="account/upaccount/upAccountsearchList?organizationId="+sorgid+"&payType="+spayType
			+"&payCity="+spayCity+"&payBankNo="+spayBankNo+"&custname="+custname+"&bankSerial="+sbankSerial;
	$("#upAccountTable").bootstrapTable('refresh',{
		'url':url
	});
}




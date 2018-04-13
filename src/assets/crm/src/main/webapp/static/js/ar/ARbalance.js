var flag="0";
var merchCustId;
$(function() {
	$('#ARbalanceTable').bootstrapTable({
		url : 'account/ARbalance/ARbalancelist',
		method : 'get',
		classes : "table table-hover table-condensed",
		cache : false,
		toolbar : "#ARbalanceTool",
		striped: true,
	    pagination: true,
	    searchOnEnterKey:true,
	    sidePagination:"server",
	    idField:"id",
	    sortName:"custname",
	    smartDisplay:true,
	    pageSize: 10,
	    pageList:["10","20","50","100"],
	    showColumns: true,
	    showRefresh: true,
	    clickToSelect: true,
	    singleSelect:true,
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
			field : 'sapCustomerId',
			title : '客户编码',
			align : 'left'
		},
		{
			field : 'custType',
			title : '客户类型',
			align : 'left',
			formatter:function(value){
            	return getcustTypeValue(value + "");
            }
		},
		{
			field : 'ytd',
			title : '期初',
			align : 'left',
			formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'dAmt',
			title : '本期增加',
			align : 'left',
			formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'cAmt',
			title : '本期减少',
			align : 'left',
			formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'ptd',
			title : '期末余额',
			align : 'left',
			formatter : function(value, data) {
				var date = new Date;
				var year = date.getFullYear();
				var month = date.getMonth() + 1;
				month = (month < 10 ? "0" + month : month);
				var mydate = (year.toString() + '-' + month.toString());
				if (data.period == mydate) {
					return (data.ytd + data.dAmt - data.cAmt).formatMoney();
				} else {
					return value.formatMoney();
				}
			}
		}, {
			field : 'period',
			title : '期间',
			align : 'left'
		}, {
			field : 'sapAmt',
			title : 'CRM无订单应收发票金额',
			align : 'left'
		}]
	});
	
	
	$('#ARbalanceDetailsTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		cache : false,
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		smartDisplay : true,
		pageSize : 20,
		pageList : ["20", "50", "100" ],
		search :true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : 'type',
			title : '类型(应收发票/收款)',
			align : 'left'
		},{
			field : 'invoiceNo',
			title : 'SAP编号',
			align : 'left'
		},{
			field : 'crmid',
			title : 'CRM编号',
			align : 'left'
		},
		{
			field : 'amt',
			title : '金额',
			align : 'left',
			formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'drawDate',
			title : '时间',
			align : 'left'
		}, {
			field : 'remark',
			title : '备注',
			align : 'left'
		}]
	});
	loadUpdateByperiod();
	$("#btn-search").bind("click",doSearch);
	$("#btn-details").bind("click",doDetails);
	$("#btn-update-period").click(function(){
		$.post("account/ARbalance/updateByPeriod",function(res){
			if(res.errorCode !=0){
				$.messager.popup("更新数据失败");
			}else{
				$.messager.popup(res.data.p_result);
				loadUpdateByperiod();
			}
			$("#ARbalanceTable").bootstrapTable('refresh');
		});
	});
	
	$("#btn-update—merch").click(function(){
		var rows = $("#ARbalanceTable").bootstrapTable("getSelections");
		if(rows && rows.length >0){
			$.post("account/ARbalance/updateByMerchId?merchCustId="+rows[0].merchCustId,function(res){
				$.messager.popup(res.data.p_result);
				$("#ARbalanceTable").bootstrapTable('refresh')
			})
		}else{
			$.messager.popup("请选择记录!");
		}
	});
	var url = "account/period/periodData";
	$.get(url, function(data) {
		if (data.rows && data.rows.length > 0) {
			$("#period").append("<option value=''>全部</option>");
			for(var i=0;i<data.rows.length;i++){
                $("#period").append("<option value='"+data.rows[i].period+"'>"+data.rows[i].period+"</option>")
            }
			/*$("#period").trigger("chosen:updated");*/
		}
	});
});
function doDetails(){
	var rows = $("#ARbalanceTable").bootstrapTable("getSelections");
	if(rows && rows.length >0){
		$('#ARbalanceDetailsTable').bootstrapTable('refresh',{
			'url':"account/ARbalance/ARbalanceDetailslist?id="+rows[0].id
		});
		$("#md-choose-customer").modal("show");
	}else{
		$.messager.popup("请选择记录!");
	}
}

function loadUpdateByperiod(){
	var periodurl="account/ARbalance/balanceValidate";
	$.post(periodurl,function(res){
		if(res.data==2){
			$("#btn-update-period").removeClass("hide");
		}else{
			$("#btn-update-period").addClass("hide");
		}
	});
}
/**
 * 查询
 */
function doSearch(){
	var custname =$("#custname").val();
	var period =$("#period").val();
	var sapCustomerId =$("#sapCustomerId").val();
	var sorgid =$("#sorgid").val();
	var custType =$("#custType").val();
	var url="account/ARbalance/ARbalancelist?organizationId="+sorgid+"&custname="+custname
			+"&sapCustomerId="+sapCustomerId+"&period="+period+"&custType="+custType;
	$("#ARbalanceTable").bootstrapTable('refresh',{
		'url':url
	});
}

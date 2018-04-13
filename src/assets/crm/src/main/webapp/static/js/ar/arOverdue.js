var flag="0";
var merchCustId;
$(function() {
	$('#arOverdueTable').bootstrapTable({
		url : 'account/ARbalance/arOverdueList',
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
		},{
			field : 'arPeriod',
			title : '账期(天)',
			align : 'left'
		},
		{
			field : 'aramt',
			title : '应收总额',
			align : 'left',
			formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'overdue1',
			title : '未逾期金额',
			align : 'left',
			formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'overdue2',
			title : '逾期1-30天',
			align : 'left',
			formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'overdue3',
			title : '逾期31-60天',
			align : 'left',
			formatter : function(value, data) {
				return value.formatMoney();
			}
		}, {
			field : 'overdue4',
			title : '逾期61-90天',
			align : 'left',
			formatter : function(value, data) {
				return value.formatMoney();
			}
		}, {
			field : 'overdue5',
			title : '逾期91-120天',
			align : 'left',
			formatter : function(value, data) {
				return value.formatMoney();
			}
		}, {
			field : 'overdue6',
			title : '逾期121-180天',
			align : 'left',
			formatter : function(value, data) {
				return value.formatMoney();
			}
		}, {
			field : 'overdue7',
			title : '逾期超过180天',
			align : 'left',
			formatter : function(value, data) {
				return value.formatMoney();
			}
		}]
	});
	
	
	$('#arOverdueDetailsTable').bootstrapTable({
		classes : "table table-hover table-condensed",
		cache : false,
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
		columns : [{
			field : 'invoiceNo',
			title : 'SAP编号',
			align : 'left'
		},{
			field : 'drawDate',
			title : '开票日期',
			align : 'left'
		},{
			field : 'overdueNum',
			title : '逾期天数',
			align : 'left'
		},{
			field : 'materialId',
			title : '物料编码',
			align : 'left'
		},{
			field : 'sku',
			title : '物料描述',
			align : 'left'
		},
		{
			field : 'price',
			title : '单价',
			align : 'left',
			formatter:function(value){
            	return value.formatMoney();
            }
		},{
			field : 'num',
			title : '数量',
			align : 'left',
			formatter:function(value){
            	return value.formatMoney();
            }
		},{
			field : 'amt',
			title : '金额',
			align : 'left',
			formatter:function(value){
            	return value.formatMoney();
            }
		},{
			field : 'tax',
			title : '税额',
			align : 'left',
			formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'weight',
			title : '重量',
			align : 'left'
		}]
	});
	$("#btn-search").bind("click",doSearch);
	$("#btn-details").bind("click",doDetails);
	$("#btn-details-export").bind("click",diDetailsExport);
	$("#btn-export").bind("click",doExport);
});
function doDetails(){
	var rows = $("#arOverdueTable").bootstrapTable("getSelections");
	if(rows && rows.length >0){
		$('#arOverdueDetailsTable').bootstrapTable('refresh',{
			'url':"account/ARbalance/arOverdueDetailslist?merchId="+rows[0].merchId+"&arPeriod="+rows[0].arPeriod
		});
		$("#arOverdueDetailsModal").modal("show");
	}else{
		$.messager.popup("请选择记录!");
	}
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
	var url="account/ARbalance/arOverdueList?organizationId="+sorgid+"&custname="+custname
			+"&sapCustomerId="+sapCustomerId+"&period="+period+"&custType="+custType;
	$("#arOverdueTable").bootstrapTable('refresh',{
		'url':url
	});
}

function diDetailsExport(){
	var rows =$("#arOverdueTable").bootstrapTable("getSelections");
    if(!rows || rows.length !=1){
    	$.messager.popup("选择记录")
    	return;
    }
	$("#generateFile").html("正在生成excel，请耐心等待...");
	$("#exportModal").modal();
	$.ajax({
		url:'account/ARbalance/exportDetails?merchId='+rows[0].merchId+'&arPeriod='+rows[0].arPeriod,
		timeout:300000,
		error:function(){
		},
		success:function(res, textStatus, XMLHttpRequest){
		    if(res.data.length<2){
		    	$.messager.alert("提示","数据太多，无法导出");
		    }else{
		    	$("#generateFile").html('<a href="'+window.IMAGE_BASEURI+res.data+'" target="_blank">生成成功，点击下载</a>')
		    }
		} 
	});
};

function doExport(){
	$("#generateFile").html("正在生成excel，请耐心等待...");
	$("#exportModal").modal();
	$.ajax({
		url:'account/ARbalance/exportAll',
		timeout:300000,
		error:function(){
		},
		success:function(res, textStatus, XMLHttpRequest){
		    if(res.data.length<2){
		    	$.messager.alert("提示","数据太多，无法导出");
		    }else{
		    	$("#generateFile").html('<a href="'+window.IMAGE_BASEURI+res.data+'" target="_blank">生成成功，点击下载</a>')
		    }
		} 
	});
}

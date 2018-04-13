
var stationData;
$(function(){
    $('#customerTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'customer/payer/list',
        cache: false,
        toolbar:"#customerTool",
        striped: true,
        pagination: true,
        searchOnEnterKey:true,
        sidePagination:"server",
        idField:"id",
        sortName:"colName",
        smartDisplay:true,
        pageSize: 10,
        pageList:["10","20","50","100"],
        exportDataType :'all',
        search: false,
        showColumns: true,
        showRefresh: true,
        clickToSelect: true,
        singleSelect:true,
        columns: [{
            field: 'ck',
            title: '编号',
            radio:true
        }, {
            field: 'merchId',
            title: '客户编号',
            align: 'left',
            visible :false
        }, {
            field: 'merchSap',
            title: '客户sap编码',
            align: 'left'
        }, {
            field: 'merchName',
            title: '客户名称',
            align: 'left'
        }, {
            field: 'payerId',
            title: '付款方编号',
            align: 'left',
            visible :false
        }, {
            field: 'payerSap',
            title: '付款方sap编码',
            align: 'left'
        }, {
            field: 'payerName',
            title: '付款方名称',
            align: 'left'
        }, {
            field: 'statesDescri',
            title: '客户状态',
            align: 'left'
        }]
    });
    
    $("#deleteBtn").bind("click", doDel);
    $("#searchBtn").bind("click",searchCustomer);
    $("#relateBtn").bind("click", showRelateModal);
    $("#relatePayerBtn").bind("click", relatePayer);
    
});


//查询客户
function searchCustomer(){
	$("#doUpStates").addClass("hide");
	$("#customerTable").bootstrapTable("refresh",
		{'url' : 'customer/payer/list?'+$("#searchForm").serialize()}
	);
	return false;
}

function showRelateModal(){
	var rows = $("#customerTable").bootstrapTable("getSelections");
	if(rows && rows.length ==1){
		$("#payerModal").modal();
		showPayer();
	}else{
		$.messager.alert("提示", "请选择一条记录!");
	}
}

function showPayer(){
	if($('#payerTable').data("loaded")){
		$("#payerTable").bootstrapTable("refresh",
			{
			'url' : 'customer/list?states=3&custType=3',
			method:'get'
			}
		);
	}else{
		$('#payerTable').bootstrapTable({
			method: 'get',
			classes:"table table-hover table-condensed",
			url: 'customer/list?states=3&custType=3',
			cache: false,
			striped: true,
			pagination: true,
			sidePagination:"server",
			searchOnEnterKey:true,
			idField:"id",
			sortName:"colName",
			pageList:["10","20"],
			smartDisplay:true,
			search: true,
			showColumns: false,
			showRefresh: true,
			clickToSelect: true,
			singleSelect:true,
			columns: [{
				field: 'ck',
				title: '编号',
				radio:true
			}, {
				field: 'name',
				title: '客户名称',
				align: 'left'
			}, {
				field: 'sapCustomerId',
				title: 'sap编码',
				align: 'left'
			}]
		});
		$('#payerTable').data("loaded",true);
	}
}

function relatePayer(){
	var rows = $("#customerTable").bootstrapTable("getSelections");
	if(!rows || rows.length !=1){
		$.messager.alert("提示", "请选择客户!");
		return;
	}
	var payers = $("#payerTable").bootstrapTable("getSelections");
	if(!payers || payers.length !=1){
		$.messager.alert("提示", "请选择付款方!");
		return;
	}
	if(rows[0].merchId==payers[0].id){
		$.messager.popup("关联的付款方不能是自己");
		return;
	}
	$.post("customer/payer/relate.json",{"merchid":rows[0].merchId,"payer":payers[0].id}, function(res){
		if(res && res.data==1){
			$.messager.popup("关联成功");
			$("#payerModal").modal("hide");
			$('#customerTable').bootstrapTable('refresh');
		}else{
			$.messager.popup("关联失败");
		}
	});
	
}

/**
 * 删除
 */
function doDel(){
	var rows = $("#customerTable").bootstrapTable("getSelections");
	if(!rows || rows.length !=1){
		$.messager.popup("请选择要删除的记录!");
		return;
	}
	if(!rows[0].payerId){
		$.messager.popup("不能删除");
		return;
	}
	$.messager.confirm("警告", "您确认要删除此关联吗?", function() {
		$.post("customer/payer/delete.json",{"merchid":rows[0].merchId,"payer":rows[0].payerId},function(data){
			if(data.data != 1){
				$.messager.alert("提示", data.errorMessage? data.errorMessage:data.data);
				return;
			}
		$("#customerTable").bootstrapTable("refresh");})
	});
}


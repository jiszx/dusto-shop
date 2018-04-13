
var stationData;
$(function(){
    $('#customerTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'customer/config/inv/warning/list',
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
        singleSelect: false,
        columns: [{
            field: 'ck',
            title: '编号',
            checkbox: true
        }, {
            field: 'id',
            title: '客户编号',
            align: 'left'
        }, {
            field: 'name',
            title: '客户名称',
            align: 'left'
        }, {
            field: 'email',
            title: '通知邮箱',
            align: 'left'
        },{
            field: 'sapCustomerId',
            title: 'SAP客户编码',
            align: 'left'
        }, {
            field: 'custWarning.isWarning',
            title: '是否预警',
            align: 'left',
            formatter : function(value,data){
            	if(value){
            		return "是";
            	}else{
            		return "否";
            	}
            }
        }, {
            field: 'custWarning.updateTs',
            title: '最后预警更新时间',
            align: 'left'
        }, {
            field: '',
            title: '操作',
            align: 'left',
            formatter : function(value,data){
            	var custId = data.id;
            	var alink = $('<a type="button" class="btn btn-primary btn-xs" id="setBtn"><i class="icon icon-edit"></i>设定</a>');
        		alink.attr("onclick","editDetail('"+custId+"')");
        		return alink.prop("outerHTML");
            }
        }]
    });
    
    $("#stopWarningBtn").bind("click",stopWarning);
    $("#startWarningBtn").bind("click",startWarning);
    
});


//查询客户
function searchCustomer(){
	$("#doUpStates").addClass("hide");
	$("#customerTable").bootstrapTable("refresh",
		{'url' : 'customer/config/inv/warning/list?'+$("#searchForm").serialize()}
	);
	return false;
}

/**
 * 设定按钮
 */
function editDetail(custId){
    window.location.href="customer/config/inv/warning/setting?custId="+custId;
}

function stopWarning(){
	toggleWarning(0);
}

function startWarning(){
	toggleWarning(1);
}

function toggleWarning(type){
	var customerRows = $("#customerTable").bootstrapTable("getSelections");
	if(customerRows && customerRows.length > 0){
		var custIds = [];
		$.each(customerRows,function(i,row){
			custIds.push(row.id);
		})
		$.post("customer/config/inv/warning/toggle",
				{"cids":custIds.join(","),"type":type},
				function(data){
					if(data.data != '0'){
						$.messager.alert("提示", data.data);
						return;
					}
					$("#customerTable").bootstrapTable("refresh");
				});
	}else{
		$.messager.alert("提示", "请至少选择一条记录!");
	}
}


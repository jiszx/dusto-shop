$(function(){
    $("#searchForm .form-group").hide();
    $("#searchForm .mustShow").show();
    $("#moreSearch").bind("click", function () {
        if ($(this).html() == "筛选") {
            $(this).html("收起")
            $("#searchForm .form-group").show();
        } else {
            $("#searchForm .form-group").hide();
            $("#searchForm .mustShow").show();
            $(this).html("筛选")
        }
    })

    $('#contractTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'customer/contract/list.json',
        cache: false,
        toolbar:"#contractTool",
        striped: true,
        pagination: true,
        searchOnEnterKey:true,
        sidePagination:"server",
        idField:"id",
        sortName:"colName",
        smartDisplay:true,
        pageSize: 10,
        pageList:["10","20","50","100"],
        search: false,
        showExport:true,
        showColumns: true,
        showRefresh: true,
        clickToSelect: true,
        singleSelect:true,
        columns: [{
            field: 'ck',
            title: '编号',
            radio:true
        },{
            field: 'id',
            title: '编号',
            visible : false
        },{
            field: 'custname',
            title: '客户名称',
            align: 'left'
        },{
            field: 'custType',
            title: '客户类型',
            align: 'left',
            formatter : function(value) {
				return getCustType(value + "");
			}
        }, {
            field: 'orgname',
            title: '销售组织',
            align: 'left'
        },
        /*{
            field: 'factoryName',
            title: '所属工厂',
            align: 'left'
        }, {
            field: 'virtualWarehouse',
            title: '所属虚拟仓',
            align: 'left',
            formatter : function(value) {
				return getvirtualWarehouse(value + "");
			}
        },*/
        {
            field: 'createTs',
            title: '创建时间',
            align: 'left'
        }, {
            field: 'contractBdate',
            title: '合同起始时间',
            align: 'left'
        }, {
            field: 'contractEdate',
            title: '合同截止时间',
            align: 'left'
        },{
            field: 'settleType',
            title: '结算方式',
            align: 'left',
            formatter : function(value) {
				return getSettle(value + "");
			}
        }, {
            field: 'creditAmt',
            title: '授信额度',
            align: 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
        }, {
            field: 'aPeriod',
            title: '账期(天)',
            align: 'left',
            formatter : function(value) {
            	return getaperiod(value + "");            		
			}
        }, {
            field: 'states',
            title: '合同状态',
            align: 'left',
            formatter : function(value) {
				return getstates(value + "");
			}
        }]
    });
    $(".btn-edit").bind("click",doEdit);
    $(".btn-del").bind("click",doDel);
    $("#btn-audit").bind("click",doAudit);
    $("#btn-detail").bind("click",doShow);
    $("#searchButton").bind('click', search);
    var orgurl="org/powerOrg"
    	$.get(orgurl,function(data){
    		if(data.rows && data.rows.length>0){
    			$("#organizationId").html('');
    			$("#organizationId").append("<option></option>");
    			for(var i=0;i<data.rows.length;i++){
    				$("#organizationId").append("<option value='"+data.rows[i].orgid+"'>"+data.rows[i].orgname+"</option>")
    			}
    		}
    })
});


/**
 * 编辑按钮
 */
function doEdit(){
    var rows = $("#contractTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
    	if(rows[0].states=='1' ||rows[0].states=='3'){ 
    		var url  =null;
    		if(rows[0].custType=='1' || rows[0].custType=='4'|| rows[0].custType=='9'){
    			url ="customer/contract/preEdit";
    		}else if(rows[0].custType=='3'){
    			url ="customer/contract/kaContractEdit";
    		}
    		else{    			
    			url ="customer/contract/distributionPaperEdit";
    		}
    		location.href=url+"?id="+rows[0].id;
    	}else{
    		$.messager.alert("提示", "请选择要编辑或者驳回的记录!");
    	}
    }else{
        $.messager.alert("提示", "请选择记录!");
    }
}
function doShow(){
    var rows = $("#contractTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
    	window.location.href="customer/contract/contractDetails.html?id="+rows[0].id;
    }else{
        $.messager.alert("提示", "请选择记录!");
    }
}
function doDel(){
    var rows = $("#contractTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1 && (rows[0].states=='1' || rows[0].states=='3')){
        $.messager.confirm("警告", "您确认要删除此记录吗?", function() {
            $.post("customer/contract/delContract",{"contractid":rows[0].id},function(data){
            	if(data.errorCode == 0){
            		$.messager.popup("合同删除成功!");
            		$("#contractTable").bootstrapTable("refresh");
            	}else{
            		$.messager.alert("提示", data.errorMessage);
            	}
            	})
        });
    }else{
        $.messager.alert("提示", "请选择编辑状态或者驳回状态的记录!");
    }
}
function doAudit(){
	var rows = $("#contractTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1 ){
    	if(rows[0].states=='1' ||rows[0].states=='3'){
    		 $.messager.confirm("警告", "您确认要提交此记录吗?", function() {
    	            $.post("customer/contract/updateContractStates",
    	            		{"contractid":rows[0].id,"states":"2"},
    	            		function(data){
    	            			if(data.data=="200"){
    	            				$.messager.popup("提交审批成功!");
    	            			}else{
    	            				$.messager.alert("提示", "提交审批失败!");
    	            			}
    	            			$("#contractTable").bootstrapTable("refresh");
    	            })
    	        });
    	}else{
    		 $.messager.alert("提示", "请选择要编辑或者审批驳回的记录!");
    	}
    }else{
        $.messager.alert("提示", "请选择要编辑或者审批驳回的记录!");
    }
}
function search(){
	var custname = $("#selectcust").val();
	//custname =encodeURI(custname);
	///custname =encodeURI(custname);
	var level = $("#selectlevel").val();
	var states = $("#selectstates").val();
	var orgid	=$("#organizationId").val();
	var settleType = $("#selectsettleType").val();
	var custType =$("#selectcustType").val();
	var url = "customer/contract/list.json?organizationId="+orgid+"&custname="+custname+"&merchLevels="+level+"&states="+states
			+"&custType="+custType+"&settleType="+settleType;
	$('#contractTable').bootstrapTable('refresh', {'url':url});
    return false;
}
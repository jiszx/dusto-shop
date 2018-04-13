$(function(){
    $('#accountTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'account/merchaccount',
        //data:data,
        cache: false,
        toolbar:"#merchAccountTool",
        striped: true,
        pagination: true,
        searchOnEnterKey:true,
        sidePagination:"server",
        idField:"id",
        sortName:"merchCustId",
        smartDisplay:true,
        pageSize: 10,
        pageList:["10","20","50","100"],
        //search: true,
        showColumns: true,
        showRefresh: true,
        clickToSelect: true,
        singleSelect:true,
        onCheck:function(row){
            if(row.custType =='2' || row.custType =='7'||row.custType=='70'){
            	$("#details").css("display","block");
            }else{
            	$("#details").css("display","none");
            }
        },
        onUncheck:function(){
        	$("#details").css("display","none");
        },
        columns: [{
            field: 'ck',
            title: '',
            radio:true
        },
        /*{
            field: 'merchCustId',
            title: 'CRM客户编号',
            align: 'left'
        },*/
        {
            field: 'sapCustomerId',
            title: 'sap客户编号',
            align: 'left'
        }, {
            field: 'custname',
            title: '客户名',
            align: 'left'
        }, {
            field: 'orgname',
            title: '销售组织',
            align: 'left'
        }, {
            field: 'custType',
            title: '客户类型',
            align: 'left',
            formatter : function(value) {
				return getcustTypeValue(value + "");
			}
        }, {
            field: 'cashAmt',
            title: '可用现金',
            align: 'left'
            ,formatter:function(value){
            	if(!value){
            		return "0";
            	}
            	return value.formatMoney();
            }
        }, {
            field: 'subsidyAmt',
            title: '可用货补',
            align: 'left',
            formatter:function(value){
            	if(!value){
            		return "0";
            	}
                return value.formatMoney();
            }	
        }, {
            field: 'creditAmt',
            title: '可用授信',
            align: 'left',
            formatter:function(value){
            	if(!value){
            		return "0";
            	}
            	return value.formatMoney();
            }
        },
        {
            field: 'allamt',
            title: '可用合计',
            align: 'left',
            formatter:function(value){
            	if(!value){
            		return "0";
            	}
            	return value.formatMoney();
            }
        }, {
            field: 'creditLines',
            title: '授信额度',
            align: 'left',
            formatter:function(value){
            	if(!value){
            		return "0";
            	}
            	return value.formatMoney();
            }
        }, {
            field: 'frozenAmt',
            title: '冻结现金授信合计',
            align: 'left',
            formatter:function(value){
            	if(!value){
            		return "0";
            	}
            	return value.formatMoney();
            }
        }, {
            field: 'frozenSubsidy',
            title: '冻结货补',
            align: 'left',
            formatter:function(value){
            	if(!value){
            		return "0";
            	}
            	return value.formatMoney();
            }
        }, {
            field: 'bondAmt',
            title: '保证金',
            align: 'left',
            formatter:function(value){
            	if(!value){
            		return "0";
            	}
            	return value.formatMoney();
            }
        }
        ]
    });
    $("#btn-search").bind('click',doSearch);
    $("#details").bind("click",doDetails);
    $("#exportBtn").bind("click", doExport);
    
    if($("#usertype").val() =='1'){
    	$('#accountTable').bootstrapTable('hideColumn', 'allamt');
    	$('#accountTable').bootstrapTable('hideColumn', 'custType');
    	$('#accountTable').bootstrapTable('hideColumn', 'subsidyAmt');
    	$('#accountTable').bootstrapTable('hideColumn', 'creditAmt');
    	$('#accountTable').bootstrapTable('hideColumn', 'creditLines');
    	$('#accountTable').bootstrapTable('hideColumn', 'frozenSubsidy');
    }
});
function doSearch(){
	var custname=$("#custname").val();
	custname =encodeURI(custname);
	custname =encodeURI(custname);
	var orgid =$("#orgid").val();
	var custType = $("#custType").val();
	var url='account/merchaccount?custname='+custname+"&orgid="+orgid+"&custType="+custType;
	$('#accountTable').bootstrapTable('refresh',{
		'url':url
	});
	return false;
}
function doDetails(){
	var row = $("#accountTable").bootstrapTable("getSelections");
	if(row &&row[0].custType=='2' || row &&row[0].custType=='7'||row[0].custType=='70'){
		window.location.href="account/accountDetails.html?merchCustId="+row[0].merchCustId;
	}else{
		$.messager.alert("提示", "请选择配送商,仓储服务商或者合作仓储服务商");
	}
}

function doExport(){
	$("#generateFile").html("正在生成excel，请耐心等待...");
	$("#exportModal").modal();
	$.ajax({
		url:'account/generate.json',
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


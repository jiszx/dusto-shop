$(function() {
	$('#accountMonthTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url: 'account/period/list',
		cache : false,
		//data:data,
		toolbar : "#dictTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "period",
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
		},{
			field : 'custname',
			title : '客户',
			align : 'left'
		},{
            field: 'sapCustomerId',
            title: 'SAP客户编码',
            align: 'left'
        },{
            field: 'custType',
            title: '客户类型',
            align: 'left',
            formatter :function(value){
            	return getcustTypeValue(value + "");
            }
        }, {
			field : 'accountType',
			title : '账户类型',
			align : 'left',
			formatter : function(value) {
				return getTypeValue(value + "");
			}
		}, {
			field : 'orgname',
			title : '销售组织',
			align : 'left'
		}, {
			field : 'ytd',
			title : '期初金额(元)',
			align : 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'dAmt',
			title : '本期增加(元)',
			align : 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'cAmt',
			title : '本期减少(元)',
			align : 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
		}, {
			field : 'ptd',
			title : '期末余额(元)',
			align : 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
		}
		/*, {
			field : 'ptd',
			title : '冻结资金(元)',
			align : 'left'
		}*/
		, {
			field : 'period',
			title : '期间',
			align : 'left'
		} ]
	});
	$("#btn-search").bind("click",doSearch);
	$("#btn-detail").bind("click", showDetail);
	$("#btn-update").bind("click",doUpdate);
	$("#btn-statement").bind("click",showStatement);
	$("#printStatement").bind("click",printStatement);
	$('#period').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"80%"
	});
	var url = "account/period/periodData";
	$.get(url, function(data) {
		if (data.rows && data.rows.length > 0) {
			$("#period").append("<option value='0'>全部</option>");
			for(var i=0;i<data.rows.length;i++){
                $("#period").append("<option value='"+data.rows[i].period+"'>"+data.rows[i].period+"</option>")
            }
			$("#period").trigger("chosen:updated");
		}
	});
	var periodurl="account/period/balanceValidate";
	$.post(periodurl,function(res){
		if(res.data==2){
			$("#btn-update").removeClass("hide");
		}else{
			$("#btn-update").addClass("hide");
		}
	});
	
});

function showDetail(){
	var rows = $("#accountMonthTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		window.location.href="account/period/custPeriod.html?custid="+rows[0].merchCustId+"&orgid="+rows[0].organizationId+
		"&period="+rows[0].period+"&accountType="+rows[0].accountType+"&id="+rows[0].id;
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}

function doSearch(){
	var  custname=$("#custname").val();
	custname =encodeURI(custname);
	custname =encodeURI(custname);
	var  accountType=$("#accountType").val();
	var period =$("#period").val();
	var custType = $("#custType").val();
	var sorgid =$("#sorgid").val();
	var url="account/period/list?custname="+custname+"&accountType="+accountType+"&period="+period+"&sorgid="+sorgid+"&custType="+custType;
	$("#accountMonthTable").bootstrapTable('refresh',{
		'url':url
	});
}

function doUpdate(){
	var mydate = new Date();
	var str = mydate.getFullYear()+'-';
	str += (mydate.getMonth()+1);
	$.messager.confirm("提示","生成"+str+"数据将会结算上月数据，所需时间会较长，请耐心等待",function () {
		 $.post("account/period/upBalance",function(res){
			 $.messager.popup(res.data);
			 window.location.href="account/month.html";
			}) 
	 });
}

function showStatement(){
	var rows = $("#accountMonthTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		var period = rows[0].period.replace('-','');
		var curr = new Date();
		var currMonth = curr.getMonth()+1;
		var currPeriod = curr.getFullYear()+(currMonth<10?'0'+currMonth:currMonth);
		
		if((rows[0].custType == '70' || rows[0].custType == '7') && rows[0].accountType == '4' && period < currPeriod){
			/*window.location.href="account/statement.html?custId="+rows[0].merchCustId+"&orgId="+rows[0].organizationId+
			"&period="+rows[0].period+"&custName="+rows[0].custname;*/
		    var statementurl = "account/statement.html?custId="+rows[0].merchCustId+"&orgId="+rows[0].organizationId+
			"&period="+rows[0].period+"&custName="+rows[0].custname;
		    $('#statementContent').attr('src',statementurl);
		    $('#statementContent').css({'height':'650px'});
		    $('#statementModal').modal({
		    	keyboard: true
		    })
		}else{
			$.messager.popup('本月之前的仓储服务商或者合作仓储服务商的保证金数据才能查看对账单！');
		}
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}

function printStatement(){
	$('#statementContent')[0].contentWindow.preview();
}
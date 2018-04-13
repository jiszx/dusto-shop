$(function() {
	$('#periodPoolTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url: 'costBalance/list',
		cache : false,
		toolbar : "#dictTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "colName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : '',
			title : '编号',
			radio : true
		}, {
			field : 'costTypeid',
			title : '账户类型',
			align : 'left',
			formatter : function(value) {
				return getCostType(value + "");
			}
		}, {
			field : 'orgname',
			title : '销售组织',
			align : 'left'
		}, {
			field : 'regionName',
			title : '大区',
			align : 'left'
		}, {
			field : 'provName',
			title : '省区',
			align : 'left'
		}, {
			field : 'ytd',
			title : '期初金额',
			align : 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
		}, {
			field : 'dAmt',
			title : '本期增加',
			align : 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
		}, {
			field : 'cAmt',
			title : '本期减少',
			align : 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
		}, {
			field : 'ptd',
			title : '期末余额',
			align : 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
		}, {
			field : 'period',
			title : '期间',
			align : 'left'
		} ]
	});
	
	$("#btn-detail").bind("click",doDetail);
	var periodurl="costBalance/balanceValidate";
	$.post(periodurl,function(res){
		if(res.data==2){
			$("#btn-update").removeClass("hide");
		}else{
			$("#btn-update").addClass("hide");
		}
	});
	initOrg();
	$("#btn-update").bind("click",doUpdate);
	$("#searchButton").bind('click', search);
});

function doDetail(){
	var row = $("#periodPoolTable").bootstrapTable("getSelections");
	if(row && row[0].id){
		window.location.href="cost/costPeriod.html?id="+row[0].id;
	}else{
		$.messager.alert("提示", "请选择要配送是的记录!");
	}
}
function initOrg(){
	$.get('Org/list.json', function(res){
		if(res.errorCode==0){
			areas = res.data;
//			var orgHtml = "";
//			$.each(res.data, function(n, value){
//				if(value.levels && value.levels=='2'){
//					orgHtml += '<option value="'+value.id+'">'+value.name+'</option>';
//				}
//			});
//			$("#selectorg").append(orgHtml);
		}
	});
}
function search(){
	var org = $("#selectorg").val();
	var regin = $("#selectregin").val();
	var cost = $("#selectcost").val();
	var url = "costBalance/list.json?regionId=" + regin
			+ "&organizationId=" + org + "&costTypeid=" + cost;
	$("#periodPoolTable").bootstrapTable("refresh", {'url':url});
}
function changeRegin(pid){
	var reginHtml = '<option value="">全部</option>';
	$.each(areas, function(n, value){
		if(value && value.pid==pid){
			reginHtml += '<option value="'+value.id+'">'+value.name+'</option>';
		}
	});
	$("#selectregin").html(reginHtml);
}
function doUpdate(){
	var mydate = new Date();
	var str = mydate.getFullYear()+'-';
	str += (mydate.getMonth()+1);
	$.messager.confirm("提示","生成"+str+"数据将会结算上月数据，所需时间会较长，请耐心等待",function () {
		 $.post("costBalance/upBalance?period="+str,function(res){
			 $.messager.popup(res.data);
			 $("#periodPoolTable").bootstrapTable('refresh');
			}) 
	 });
}

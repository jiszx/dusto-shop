var areas = {};
$(function(){
    $('#costTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'cost/adjust/main/list.json',
        cache: false,
       // toolbar:"#dictTool",
        striped: true,
        pagination: true,
        searchOnEnterKey:true,
        sidePagination:"server",
        idField:"id",
        sortName:"orgname",
        smartDisplay:true,
        pageSize: 10,
        pageList:["10","20","50","100"],
        search: false,
        showColumns: true,
        showRefresh: true,
        clickToSelect: true,
        singleSelect:true,
        columns: [/*{
            field: 'ck',
            title: '',
            radio:true
        },*/ {
            field: 'id',
            title: '编号',
            align: 'left',
            visible : false
        }, {
            field: 'orgname',
            title: '销售组织',
            align: 'left'
        }, {
            field: 'reginname',
            title: '大区',
            align: 'left'
        }, {
            field: 'provname',
            title: '省区',
            align: 'left'
        }, {
            field: 'costTypeid',
            title: '费用类型',
            align: 'left',
            formatter : function(value) {
				return getCostType(value + "");
			}
        }, {
            field: 'amt',
            title: '金额',
            align: 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
        }]
    });
    
    initOrg();
    $("#btn-update").bind("click",doUpdate);
    $("#searchButton").bind('click', search);
});

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

function changeRegin(pid){
	var reginHtml = '<option value="">全部</option>';
	$.each(areas, function(n, value){
		if(value && value.pid==pid){
			reginHtml += '<option value="'+value.id+'">'+value.name+'</option>';
		}
	});
	$("#selectregin").html(reginHtml);
}

function search(){
	var org = $("#selectorg").val();
	var regin = $("#selectregin").val();
	var cost = $("#selectcost").val();
	var url = "cost/adjust/main/list.json?regionId=" + regin
			+ "&organizationId=" + org + "&costTypeid=" + cost;
	$("#costTable").bootstrapTable("refresh", {'url':url});
}
function doUpdate(){
	$.post("cost/adjust/updateCost",function(res){
		$("#costTable").bootstrapTable("refresh");
	})
}
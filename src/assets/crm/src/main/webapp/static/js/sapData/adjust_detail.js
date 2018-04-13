var orgObj = new Object();
$(function() {
	initSearchbar();
	var adjustId = $("#adjustId").val();
	var adjustType = $("#adjustType").val();
	var url = "product/price/adjust/detail/list/"+adjustId;
	
	$('table#detail-table').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : url,
		cache : false,
		striped : true,
		pagination : false,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "colName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		search : false,
		clickToSelect : true,
		singleSelect : false,
		columns : [{
			field : 'materialId',
			title : '物料编码',
			align : 'left'
		}, {
			field : 'organizationId',
			title : '销售组织',
			align : 'left',
			formatter:function(val, row){
				if(typeof val == 'undefined' || val == ''){
					return '-'
				}
				return orgObj['c'+val];
			}
		}, {
			field : 'channel',
			title : '渠道',
			align : 'left'
		}, {
			field : 'oprice',
			title : '原价格',
			align : 'left',
			formatter:function(val, row){
				if(typeof val == 'undefined' || val == ''){
					return '-'
				}
            	return Number(val/100).formatMoney();
			}
		}, {
			field : 'oadjustPrice',
			title : '原调整价',
			align : 'left',
			formatter:function(val, row){
				if(typeof val == 'undefined' || val == ''){
					return '-'
				}
            	return Number(val/100).formatMoney();
			}
		}, {
			field : 'obdate',
			title : '原有效期开始日',
			align : 'left',
			formatter:function(val, row){
				if(typeof val == 'undefined' || val == ''){
					return '-'
				}
				return new Date(Date.parse(val)).format("yyyy-MM-dd");
			}
		}, {
			field : 'oedate',
			title : '原有效期截止日',
			align : 'left',
			formatter:function(val, row){
				if(typeof val == 'undefined' || val == ''){
					return '-'
				}
				return new Date(Date.parse(val)).format("yyyy-MM-dd");
			}
		}, {
			field : 'price',
			title : (adjustType=='1'?'新价格':'新调整价'),
			align : 'left',
			formatter:function(val, row){
				var mval = Number(val/100).formatMoney();
				if(row.opt && row.opt != ''){
					if(row.opt == '1'){
						return '标准价+'+mval;
					}else if(row.opt == '2'){
						return '标准价*'+mval;
					}
				}
            	return mval;
			}
		}, {
			field : 'bdate',
			title : (adjustType=='1'?'新有效期开始日':'新调整价有效期开始日'),
			align : 'left',
			formatter:function(val, row){
            	return new Date(Date.parse(val)).format("yyyy-MM-dd");
			}
		}, {
			field : 'edate',
			title : (adjustType=='1'?'新有效期截止日':'新调整价有效期截止日'),
			align : 'left',
			formatter:function(val, row){
            	return new Date(Date.parse(val)).format("yyyy-MM-dd");
			}
		}]
	});
	
});

function buttonWait(jbutton, active){
	if(active){
		jbutton.css('cursor','wait');
		jbutton.css('opacity','0.3');
		jbutton.attr('disabled','true');
	}else{
		jbutton.css('cursor','pointer');
		jbutton.css('opacity','1');
		jbutton.removeAttr('disabled');
	}
}

function initSearchbar(){
	$.get('Org/list/level/2', function(res){
		if(!res.data){
			return;
		}
		var html = '';
		var orgs = new Array();
		$.each(res.data, function(i, val){
			var cukey = val.sapId+'='+val.name;
			if(orgs.indexOf(cukey) != -1){
				return;
			}else{
				orgObj['c'+val.sapId] = val.name;
				orgs.push(cukey);
			}
		});
	});
}

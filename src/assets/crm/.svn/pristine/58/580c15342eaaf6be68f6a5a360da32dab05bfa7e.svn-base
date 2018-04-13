var table=null;
$(function(){
    table = $('#rfcExecuteTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'monitor/rfc/current/list',
        cache: false,
        toolbar:"#tools",
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
        showColumns: true,
        showRefresh: true,
        clickToSelect: true,
        singleSelect:true,
        columns: [{
            field: 'id',
            title: '编号',
            radio:true
        }, {
            field: 'executeType',
            title: '任务类别',
            align: 'left',
            formatter:function(value){
            	var text = '';
            	$('#search_type option').each(function(){
            		var optionval = $(this).attr("value");
            		if(optionval == value){
            			text = $(this).text();
            		}
            	});
            	if(typeof text != 'undefined' && text.length > 0){
            		return text;
            	}else{
            		return value;
            	}
            }
        }, {
            field: 'serialNo',
            title: '序列号',
            align: 'left'
        }, {
            field: 'parameters',
            title: '参数',
            align: 'left',
            width: '40%',
            formatter:function(value){
            	var content = $("<div>").css("word-break","break-all").css("word-wrap","break-word").text(value || "");
        		return content.prop("outerHTML");
            }
        }, {
            field: 'status',
            title: '状态',
            align: 'left',
            formatter:function(value){
            	var text = '';
            	$('#search_status option').each(function(){
            		var optionval = $(this).attr("value");
            		if(optionval == value){
            			text = $(this).text();
            		}
            	});
            	if(typeof text != 'undefined' && text.length > 0){
            		return text;
            	}else{
            		return value;
            	}
            }
        }, {
            field: 'result',
            title: '执行结果',
            align: 'left',
            width: '15%',
            formatter:function(value){
            	var content = $("<div>").css("word-break","break-all").css("word-wrap","break-word").text(value || "");
        		return content.prop("outerHTML");
            }
        }, {
            field: 'createDate',
            title: '首次处理时间',
            align: 'left'
        }]
    });
    
    $("#searchBtn").bind("click",searchRfc);
    $("#manualBtn").bind("click",manualSend);
    $("#neverBtn").bind("click",neverExecute);
    $("#refreshBtn").bind("click",refreshParam);
    
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        // 获取已激活的标签页
        var activeTab = $(e.target).attr("id");
        // 获取前一个激活的标签页
        // var previousTab = $(e.relatedTarget).attr("id");
        if(activeTab == 'rfccurrent'){
        	table.bootstrapTable("refresh",
        			{'url' : 'monitor/rfc/current/list?'+$("#searchForm").serialize()});
        }else if(activeTab == 'rfchistory'){
        	table.bootstrapTable("refresh",
        			{'url' : 'monitor/rfc/history/list?'+$("#searchForm").serialize()});
        }
    });
});


/**
 * 查询任务
 */
function searchRfc(){
	var activeTab = $("#executeTab .active a").attr("id");
	if(activeTab == 'rfccurrent'){
    	table.bootstrapTable("refresh",
    			{'url' : 'monitor/rfc/current/list?'+$("#searchForm").serialize()});
    }else if(activeTab == 'rfchistory'){
    	table.bootstrapTable("refresh",
    			{'url' : 'monitor/rfc/history/list?'+$("#searchForm").serialize()});
    }
}

/**
 * 手工执行
 */
function manualSend(){
	var rows = table.bootstrapTable("getSelections");
	if(rows && rows.length ==1){
		if(table.data("sendSap"+rows[0].serialNo) == '1'){
			$.messager.popup("正在推送，请勿频繁点击!");
			return;
		}
		$.messager.confirm("信息", "是否手动推送?", function() {
			table.data("sendSap"+rows[0].serialNo,1);
			$.post("monitor/rfc/current/manual",{"serialNo":$.trim(rows[0].serialNo)},function(result){
	        	if(result.errorCode != '-1' && $.trim(result.data) == 'S'){
	        		$.messager.popup("已加入执行队列！");
	        	}else{
	        		$.messager.alert("提示","加入执行队列失败！<br/>"+"错误信息："+(result.data||result.errorMessage));
	        	}
	        	table.bootstrapTable("refresh");
	        	table.data("sendSap"+rows[0].serialNo,0);
	    	})
	    });
	}else{
		$.messager.alert("提示", "请选择一条记录!");
	}
}
/**
 * 刷新参数
 */
function refreshParam(){
	var rows = table.bootstrapTable("getSelections");
	if(rows && rows.length ==1){
		$.messager.confirm("信息", "确认刷新请求参数?", function() {
			$.post("monitor/rfc/current/refresh",{"serialNo":$.trim(rows[0].serialNo)},function(result){
				if(result.errorCode != '-1' && $.trim(result.data) == 'S'){
					$.messager.popup("刷新完成！");
				}else{
					$.messager.popup("刷新失败！");
				}
				table.bootstrapTable("refresh");
			})
		});
	}else{
		$.messager.alert("提示", "请选择一条记录!");
	}
}

/**
 * 不再执行
 */
function neverExecute(){
	var rows = table.bootstrapTable("getSelections");
	if(rows && rows.length ==1){
		if(table.data("neverExecute"+rows[0].serialNo) == '1'){
			$.messager.popup("提示", "正在处理，请勿频繁点击!");
			return;
		}
		$.messager.confirm("信息", "是否确认不再执行该RFC接口?", function() {
			table.data("neverExecute"+rows[0].serialNo,1);
			$.post("monitor/rfc/current/disable",{"serialNo":$.trim(rows[0].serialNo)},function(result){
				if(result.errorCode != '-1' && $.trim(result.data) == 'S'){
					$.messager.popup("处理完成，该任务已移至历史记录！");
				}else{
					$.messager.alert("提示","操作失败！<br/>"+"错误信息："+(result.data||result.errorMessage));
				}
				table.bootstrapTable("refresh");
				table.data("neverExecute"+rows[0].serialNo,0);
			})
		});
	}else{
		$.messager.alert("提示", "请选择一条记录!");
	}
}

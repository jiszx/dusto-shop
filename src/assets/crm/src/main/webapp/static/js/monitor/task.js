var table=null;
$(function(){
    table = $('#dictTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'monitor/task/list.json',
        cache: false,
        toolbar:"#dictTool",
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
            field: 'ck',
            title: '编号',
            radio:true
        }, {
            field: 'id',
            title: 'id',
            visible : false
        }, {
            field: 'taskType',
            title: '任务类型',
            align: 'left'
        }, {
            field: 'taskSystem',
            title: '任务系统',
            align: 'left'
        }, {
            field: 'taskDescription',
            title: '任务说明',
            align: 'left'
        }, {
            field: 'schedule',
            title: '执行时间',
            align: 'left'
        }, {
            field: 'tryTimes',
            title: '失败尝试次数',
            align: 'left'
        }, {
            field: 'states',
            title: '状态',
            align: 'left',
            formatter:function(value){
            	return getStates(value + "");
            }
        }]
    });
    
    $('#logBtn').bind('click', showLog);
    $('#manualBtn').bind('click', manualRun);
});

function showLog(){
	var rows = $("#dictTable").bootstrapTable("getSelections");
	if(rows && rows.length==1){
		if($('#taskLogTable').data("loaded")){
			$("#taskLogTable").bootstrapTable("refresh",
				{
				'url' : 'monitor/task/log.json?taskid='+rows[0].id
				}
			);
		}else{
			$('#taskLogTable').bootstrapTable({
				method: 'get',
				classes:"table table-hover table-condensed",
				url: 'monitor/task/log.json?taskid='+rows[0].id,
				cache: false,
				striped: true,
				pagination: true,
				sidePagination:"server",
				searchOnEnterKey:true,
				idField:"id",
				sortName:"colName",
				smartDisplay:true,
				search: false,
				showExport : false,
				showColumns: false,
				showRefresh: false,
				clickToSelect: false,
				singleSelect:true,
				pageSize: 10,
		        pageList:["10","20","50","100"],
				columns: [{
					field: 'taskName',
					title: '任务名',
					align: 'left'
				}, {
					field: 'bdate',
					title: '开始时间',
					align: 'left'
				}, {
					field: 'edate',
					title: '结束时间',
					align: 'left'
				}, {
					field: 'states',
					title: '状态',
					align: 'left',
		            formatter:function(value){
		            	return getLogStates(value + "");
		            }
				}, {
					field: 'remark',
					title: '备注',
					align: 'left'
				}]
			});
			$('#taskLogTable').data("loaded",true);
		}
		$('#logModal').modal();
	}else{
		$.messager.alert('提示','请选择任务')
	}
}

function manualRun(){
	var rows = $("#dictTable").bootstrapTable("getSelections");
	if(rows && rows.length==1){
		$.get('monitor/task/manual.json?taskid='+rows[0].id, function(res){
			if(res.errorCode==0 && res.data==1){
				$.messager.alert('提示','已手动执行');
			}else{
				$.messager.alert('提示','不能手动执行')
			}
		});
	}
}

var table = null;
$(function(){
    table = $('#dictTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'process/waitMe',
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
        search: true,
        showColumns: true,
        showRefresh: true,
        clickToSelect: true,
        singleSelect:true,
        columns: [{
            field: 'ck',
            title: '编号',
            radio:true
        }, {
            field: 'defName',
            title: '申请名称',
            align: 'left'
        }, {
            field: 'name',
            title: '当前处理环节',
            align: 'left'
        },{
            field: 'startTime',
            title: '开始时间',
            align: 'left'
        }, {
            field: 'assignee',
            title: '处理组',
            align: 'left'
        },{
            field: 'priority',
            title: '优先级',
            align: 'left'
        },{
            field: 'startUser',
            title: '申请人',
            align: 'left'
        }]
    });
    $("#btn-detail").bind("click",doShow);
    $(".btn-look").bind("click",function(){
        var rows = table.bootstrapTable("getSelections");
        if(rows && rows.length ==1){
            location.href="process/look?taskId="+rows[0].id
        }else{
            $.messager.alert("提示", "请选择要审批的记录!");
        }
    })
});
function doShow(){
    var rows = table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        //showDetail("process/view?id="+rows[0].procInstId);
        $("#processImg").attr("src","process/view?id="+rows[0].procInstId);
        $("#addDictModal").modal("show")
    }else{
        $.messager.alert("提示", "请选择记录!");
    }
}
$(function(){
    $('#dictTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'process/listMy',
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
            field: 'startTime',
            title: '开始时间',
            align: 'left'
        }, {
            field: 'startUserId',
            title: '申请人',
            align: 'left'
        }]
    });
    $(".btn-show").bind("click",doShow);
});

/**
 * 编辑按钮
 */
function doShow(){
    var rows = $("#dictTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        $("#processImg").attr("src","process/view?id="+rows[0].id);
        $("#addDictModal").modal("show")
    }else{
        $.messager.alert("提示", "请选择记录");
    }
}
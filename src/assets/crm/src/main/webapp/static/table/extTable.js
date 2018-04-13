$(function(){
    $.extend($.fn.bootstrapTable.defaults,{
        onCheck:function(row,element){
            element.parents("table").find(".bg-blue").removeClass("bg-blue");
            element.parents("tr").children("td").addClass("bg-blue");
        },
        showExport:true,
        //exportDataType: 'basic', // basic, all, selected
        exportDataType: 'all',
        exportTypes: ['csv', 'txt','excel']
    });
});
var table = null;
$(function(){
    table = $('#notesTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'system/notes/showall_data',
        cache: false,
        toolbar:"",
        striped: true,
        pagination: true,
        search: true,
        searchOnEnterKey: true,
        sidePagination:"server",
        idField:"id",
        sortName:"releaseTs",
        smartDisplay:true,
        pageSize: 10,
        pageList:["10","20","50","100"],
        showColumns: true,
        showRefresh: true,
        columns: [{
            field: 'title',
            title: '',
            align: 'left',
            searchable: true,
            formatter:function(value,row){
                return '<a target="_blank" href="system/notes/noteDetail/'+row.id+'.html" >'+value+' </a>&nbsp&nbsp&nbsp<small>'+row.descTxt+'</small>';
            }
        },{
            field: 'releaseTs',
            title: '',
            align: 'left',
            searchable: false,
            formatter:function(value){
                return typeof value == 'undefined'?'':value;
            },
            cellStyle:function(value, row, index, field) {
            	return {
            		css: {"color": "#444", "font-size": "85%", "width":"15%"}
            	};
			}
        }]
    });
});
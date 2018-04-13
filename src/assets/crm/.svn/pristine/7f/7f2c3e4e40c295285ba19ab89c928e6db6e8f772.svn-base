$(function(){
    $('#accountTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'account/accountDetails?pid='+$("#merchCustId").val(),
        //data:data,
        cache: false,
        //toolbar:"#merchAccountTool",
        striped: true,
        pagination: true,
        searchOnEnterKey:true,
        sidePagination:"server",
        idField:"id",
        sortName:"merchCustId",
        smartDisplay:true,
        pageSize: 10,
        pageList:["10","20","50","100"],
        //search: true,
        showColumns: true,
        showRefresh: true,
        clickToSelect: true,
        singleSelect:true,
        columns: [{
            field: 'ck',
            title: '编号',
            radio:true
        },
        {
            field: 'custname',
            title: '客户名称',
            align: 'left'
        }, {
            field: 'cashAmt',
            title: '现金',
            align: 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
        }
        , {
            field: 'subsidyAmt',
            title: '货补',
            align: 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
        }, {
            field: 'creditAmt',
            title: '授信',
            align: 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
        },{
            field: 'allamt',
            title: '合计',
            align: 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
        }
        ,{
            field: 'frozenAmt',
            title: '冻结资金',
            align: 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
        }]
    });
});



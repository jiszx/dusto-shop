$(function(){
	
	initTable();
	$("#btn-back").bind("click",doBack);
})

function doBack(){
	window.location.href="customer/page.html";
}

function initTable(){
	if(custType=='2'){
		showDistributorTable();
	}else{
		showNormalTable();
	}
}

function showNormalTable(){
	$("#contract-table").bootstrapTable({
        url: 'customer/contract/lines',
        sidePagination:"server",
        showRefresh: true,
        onCheck:function(row,element){
        },
        clickToSelect: true,
        queryParams:function(params){
            params.id=$("#contractId").val();
            return params;
        },
        columns: [
            {
                field: 'ck',
                radio: false,
                rowspan: 1,
                align: 'center',
                valign: 'middle',
                visible: false
            },
            {
                title: 'ID',
                field: 'typeId',
                rowspan: 1,
                align: 'center',
                valign: 'middle',
                visible: false
            },
            {
                title: '名称',
                field: 'name',
                rowspan: 1,
                align: 'center',
                valign: 'middle'
            },
            /*{
                title: '代理类别',
                field: 'agentType',
                rowspan: 1,
                align: 'center',
                valign: 'middle'
            },*/
            {
                title: '年度进货目标(元)',
                field: 'yAmt',
                rowspan: 1,
                align: 'center',
                valign: 'middle',
                formatter:function (value) {
                    return (value/100).formatMoney();
                }
            },
            {
                title: '年度进货比例',
                field: 'yRatio',
                rowspan: 1,
                align: 'center',
                valign: 'middle',
                sortable: true,
                formatter:function (value) {
                    return value+"%";
                }
            },
            {
                title: '季度进货目标(元)',
                field: 'qAmt',
                rowspan: 1,
                align: 'center',
                valign: 'middle',
                formatter:function (value) {
                    return (value/100).formatMoney();
                }
            },
            {
                title: '季度进货比例',
                field: 'qRatio',
                rowspan: 1,
                align: 'center',
                valign: 'middle',
                sortable: true,
                formatter:function (value) {
                    return value+"%"
                }
            }, {
                title: '时间',
                field: 'testTime',
                rowspan: 1,
                align: 'center',
                valign: 'middle',
                visible: false,
                editable: {
                    type: 'date',
                    title: '选择修改时间:'
                }
            }]
    });
}

function showDistributorTable(){
	$("#contract-table").bootstrapTable({
        url: 'customer/contract/lines',
        sidePagination:"server",
        showRefresh: true,
        onCheck:function(row,element){
        },
        clickToSelect: true,
        queryParams:function(params){
            params.id=$("#contractId").val();
            return params;
        },
        columns: [
            {
                field: 'ck',
                radio: false,
                rowspan: 1,
                align: 'center',
                valign: 'middle',
                visible: false
            },
            {
                title: 'ID',
                field: 'typeId',
                rowspan: 1,
                align: 'center',
                valign: 'middle',
                visible: false
            },
            {
                title: '名称',
                field: 'name',
                rowspan: 1,
                align: 'center',
                valign: 'middle'
            },
            /*{
                title: '代理类别',
                field: 'agentType',
                rowspan: 1,
                align: 'center',
                valign: 'middle'
            },*/
            {
                title: '配送目标(元)',
                field: 'yAmt',
                rowspan: 1,
                align: 'center',
                valign: 'middle',
                formatter:function (value) {
                    return (value/100).formatMoney();
                }
            },
            {
                title: '配送返利点',
                field: 'yRatio',
                rowspan: 1,
                align: 'center',
                valign: 'middle',
                sortable: true,
                formatter:function (value) {
                    return value+"%";
                }
            }, {
                title: '时间',
                field: 'testTime',
                rowspan: 1,
                align: 'center',
                valign: 'middle',
                visible: false,
                editable: {
                    type: 'date',
                    title: '选择修改时间:'
                }
            }]
    });
}
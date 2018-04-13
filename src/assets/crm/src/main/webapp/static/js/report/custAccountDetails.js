$(function(){
    $('#custUpTable').bootstrapTable({
    	method : 'get',
		classes : "table table-hover table-condensed",
		 url: 'report/account/accountDetailsList',
		cache : false,
		//data:data,
		toolbar : "#dictTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "colName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "50", "100", "500", "1000" ],
		search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
        columns: [ {
            field: 'orgname',
            title: '销售组织',
            align: 'left'
        }, {
            field: 'reginName',
            title: '大区',
            align: 'left'
        }, {
            field: 'provName',
            title: '省区',
            align: 'left'
        }, {
            field: 'custName',
            title: '客户名称',
            align: 'left'
        }, {
            field: 'display',
            title: '客户编码',
            align: 'left'
        }, {
            field: 'display',
            title: '客户对应销售人员',
            align: 'left'
        }, {
            field: 'showText',
            title: '客户所属岗位',
            align: 'left'
        }, {
            field: 'accountType',
            title: '账户类型',
            align: 'left'
        }, {
            field: 'type',
            title: '收支来源',
            align: 'left'
        }, {
            field: 'orderId',
            title: '收支来源编号',
            align: 'left'
        }, {
            field: 'dAmt',
            title: '增加金额',
            align: 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
        }, {
            field: 'cAmt',
            title: '减少金额',
            align: 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
        }, {
            field: 'remark',
            title: '备注',
            align: 'left'
        }]
    });
})
$(function(){
    $('#orderTable').bootstrapTable({
    	method : 'get',
		classes : "table table-hover table-condensed",
		// url: 'system/dict/list',
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
		pageList : [ "10", "20", "50", "100" ],
		search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
        columns: [{
            field: 'ck',
            title: '编号',
            radio:true
        }, {
            field: 'colName',
            title: '销售组织',
            align: 'left'
        }, {
            field: 'colName',
            title: '大区',
            align: 'left'
        }, {
            field: 'colName',
            title: '省区',
            align: 'left'
        }, {
            field: 'chooseVal',
            title: '客户名称',
            align: 'left'
        }, {
            field: 'showText',
            title: '订单编号',
            align: 'left'
        }, {
            field: 'orders',
            title: '下单时间',
            align: 'left'
        }, {
            field: 'canChoose',
            title: '销售人员',
            align: 'left'
        }, {
            field: 'sku',
            title: 'sku',
            align: 'left'
        }, {
            field: 'display',
            title: '订单行ID',
            align: 'left'
        }, {
            field: 'display',
            title: '标准价',
            align: 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
        }, {
            field: 'display',
            title: '高卖价',
            align: 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
        }, {
            field: 'display',
            title: '购买数量',
            align: 'left'
        }, {
            field: 'display',
            title: '搭增数量',
            align: 'left'
        }, {
            field: 'display',
            title: '货补数量',
            align: 'left'
        }, {
            field: 'display',
            title: '订单金额',
            align: 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
        }, {
            field: 'display',
            title: '折扣总额',
            align: 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
        }, {
            field: 'display',
            title: '订单净额',
            align: 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
        }, {
            field: 'display',
            title: '订单头状态',
            align: 'left'
        }, {
            field: 'display',
            title: '行状态',
            align: 'left'
        }, {
            field: 'display',
            title: '已发货数量',
            align: 'left'
        }, {
            field: 'display',
            title: '物流公司',
            align: 'left'
        }]
    });

    // $("#addSKU").bind("click",function(){
    //     $('#orderLineTable').bootstrapTable("insertRow",{
    //         "index":0,
    //         "row":{"id":1,"colName":"sdf"}
    //     });
    // });
    $('.chosen-select').chosen({
        allow_single_deselect: true,
        max_selected_options: 1,
        no_results_text:'未找到匹配!'
    });
})
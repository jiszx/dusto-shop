$(function(){
    $('#orderTable').bootstrapTable({
    	method : 'get',
		classes : "table table-hover table-condensed",
		url: 'report/order/details.json',
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
		exportDataType :'all',
		search : false,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,	
        columns: [{
            field: 'ck',
            title: '编号',
            radio:true
        }, {
            field: 'department',
            title: '销售部门',
            align: 'left'
        }, {
            field: 'orgname',
            title: '销售组织',
            align: 'left'
        }, {
            field: 'regin',
            title: '大区',
            align: 'left'
        }, {
            field: 'prov',
            title: '省区',
            align: 'left'
        }, {
            field: 'merchname',
            title: '客户名称',
            align: 'left'
        }, {
            field: 'custType',
            title: '客户类型',
            align: 'left',
            formatter : function(value) {
				return getcustType(value + "");
			}
        },{
            field: 'shipname',
            title: '送达方名称',
            align: 'left'
        },{
            field: 'merchsapid',
            title: 'SAP客户编码',
            align: 'left'
        }, {
            field: 'ordersapid',
            title: 'sap订单编码',
            align: 'left'
        }, {
            field: 'ordercrmid',
            title: 'crm订单编号',
            align: 'left'
        }, {
			field : 'orderType',
			title : '订单类型',
			align : 'left',
			formatter : function(value) {
				return getorderType(value + "");
			}
		},{
            field: 'sku',
            title: 'sku',
            align: 'left',
            width : '100px'
        }, {
            field: 'brand',
            title: '品牌',
            align: 'left'
        },{
            field: 'specifications',
            title: '规格',
            align: 'left',
            width : '100px'
        },{
            field: 'unit',
            title: '单位',
            align: 'left',
            width : '100px'
        },
        {
            field: 'price',
            title: '标准价',
            align: 'left',
            visible : false
			,formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
        },
        {
            field: 'orderprice',
            title: '售卖单价',
            align: 'left'
			,formatter : function(value) {
				return value ? parseFloat(value).formatMoney() : value;
			}
        },{
            field: 'num',
            title: '数量',
            align: 'left'
        },{
            field: 'weigth',
            title: '重量',
            align: 'left'
        },{
            field: 'hbnum',
            title: '货补数量',
            align: 'left'
        },{
            field: 'sendNum',
            title: '已发货数量',
            align: 'left'
        },{
            field: 'sendWeigth',
            title: '已发货重量',
            align: 'left'
            ,formatter : function(value,data) {
            	if(data.unit=='TO'){
            		return data.sendNum;
            	}else{
            		return (parseFloat(data.sendNum)*parseFloat(data.specifications)/1000000).toFixed(3);
            	}
            	
    		}
        },{
            field: 'description',
            title: '销售政策',
            align: 'left'
        }, {
            field: 'description',
            title: '政策类型',
            align: 'left'
        }, {
            field: 'amt',
            title: '应收金额',
            align: 'left'
			,formatter : function(value) {
				return value ? parseFloat(value).formatMoney() : value;
			}
        }, {
            field: 'discount',
            title: '折扣额',
            align: 'left'
			,formatter : function(value) {
				return value ? parseFloat(value).formatMoney() : value;
			}
        }, {
            field: 'orderamt',
            title: '实收金额',
            align: 'left'
			,formatter : function(value) {
				return value ? parseFloat(value).formatMoney() : value;
			}
        },
        {
            field: 'tax',
            title: '税额',
            align: 'left',
            visible : false
			,formatter : function(value) {
				return value ? parseFloat(value).formatMoney() : value;
			}
        },
        {
            field: 'createtime',
            title: '创建时间',
            align: 'left'
        }, {
            field: 'salename',
            title: '销售人员',
            align: 'left'
        }, {
            field: 'posttime',
            title: '最后发货时间',
            align: 'left',
            visible : false
        }, {
            field: 'display',
            title: '开票时间',
            align: 'left',
            visible : false
        }, {
            field: 'invoice',
            title: '应收发票总额',
            align: 'left',
            visible : false,
			formatter : function(value) {
				return value ? parseFloat(value).formatMoney() : value;
			}
        }, {
            field: 'display',
            title: '金穗发票编号',
            align: 'left',
            visible : false
        }]
    });
    
    //销售大区联动下拉
    $('#salesOrgSelect').change(function(){
    	loadSalesAreaSelect();
    	loadSalesProv();
    	showPosition();
    });
    
    //销售省区联动下拉
    $('#salesAreaSelect').change(function(){
    	loadSalesProv();
    	showPosition();
    });
    
    //岗位联动下拉
    $('#salesProvSelect').change(function(){
    	showPosition();
    });
    
    $("#exportBtn").bind('click', exportDetail);
})

$(document).ready(function() {
	$("#startTime").datetimepicker({
		format : 'yyyy-mm-dd',
		minView : 2,
		language : 'zh-CN',
		autoclose:true
	});
	$("#endTime").datetimepicker({
		format : 'yyyy-mm-dd',
		minView : 2,
		language : 'zh-CN',
		autoclose:true
	});
});

function doSearch() {
	var customer = $("#custname").val();
	var bdate = $("#startTime").val();
	var edate = $("#endTime").val();
	var saler =$("#saler").val();
	var materialid =$("#materialId").val();
	var orgid =$("#salesOrgSelect").val();
	var reginid =$("#salesAreaSelect").val();
	var provid =$("#salesProvSelect").val();
	var url = "report/order/details.json?merchname=" + customer+ "&bdate=" + bdate 
			+ "&edate=" + edate+"&salename="+saler+"&materialId="+materialid+
			"&orgid="+orgid+"&reginid="+reginid+"&provid="+provid;
	$("#orderTable").bootstrapTable('refresh', {
		'url' : url
	});
	return false;
}

//加载销售大区
function loadSalesAreaSelect(){
	var selectedSalesOrg = $('#salesOrgSelect option:selected').val();
	var salesAreas = new Array();
	var salesAreaOpt = "<option value=''>全部</option>";
	$.ajax({
        type: 'POST',
        url: 'Org/listPid',
        dataType: 'json',
        data: {pid: selectedSalesOrg},
        success: function (result) {
            if (typeof result != 'undefined' && result.errorCode == '0') {
                data = result.data;
                for (var i in data) {
                	salesAreas.push(data[i]);
                	salesAreaOpt = salesAreaOpt + "\r <option value=" + data[i].id + " >" + data[i].name + "</option>";
                }
                $('#salesAreaSelect').html(salesAreaOpt);
                $('#salesAreaSelect').data(selectedSalesOrg, salesAreas);
            } else {
                $.messager.popup("无法获取销售大区信息！");
            }
        }
    });
}

//加载销售省
function loadSalesProv(){
	var selectedSalesArea = $('#salesAreaSelect option:selected').val();
	var salesAreas = new Array();
	var salesAreaOpt = "<option value=''>全部</option>";
	$.ajax({
		type: 'POST',
		url: 'Org/listPid',
		dataType: 'json',
		data: {pid: selectedSalesArea},
		success: function (result) {
			if (typeof result != 'undefined' && result.errorCode == '0') {
				data = result.data;
				for (var i in data) {
					salesAreas.push(data[i]);
					salesAreaOpt = salesAreaOpt + "\r <option value=" + data[i].id + " >" + data[i].name + "</option>";
				}
				$('#salesProvSelect').html(salesAreaOpt);
				$('#salesProvSelect').data(selectedSalesArea, salesAreas);
			} else {
				$.messager.popup("无法获取业务省信息！");
			}
		}
	});
}

//处理岗位显示
function showPosition(){
	var selectedSalesProv = $('#salesProvSelect option:selected').val();
	$('#positionSelect option').each(function(){
		var salesProvId = $(this).attr("flag");
		if(selectedSalesProv == salesProvId || salesProvId == 'main'){
			$(this).show();
		}else{
			$(this).hide();
		}
	});
}

function exportDetail(){
	$("#generateFile").html("正在生成excel，请耐心等待...");
	$("#exportModal").modal();
	var bdate = $("#startTime").val();
	var edate = $("#endTime").val();
	$.ajax({
		url:'report/order/orderdetail/generate?bdate=' + bdate + '&edate=' + edate,
		timeout:300000,
		error:function(){
		},
		success:function(res, textStatus, XMLHttpRequest){
		    if(res.data.length<2){
		    	$.messager.alert("提示","数据太多，无法导出");
		    }else{
		    	$("#generateFile").html('<a href="'+window.IMAGE_BASEURI+res.data+'" target="_blank">生成成功，点击下载</a>')
		    }
		} 
	});
}
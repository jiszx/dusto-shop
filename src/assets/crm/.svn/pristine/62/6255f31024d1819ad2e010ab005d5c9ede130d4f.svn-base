var $table = $('#contract-table');
var $remove = $('#del-bstb-row');
function initTable() {
	$table.bootstrapTable({
				url : 'customer/sku/list?custType='+$("#custType").val(),
				pagination : true,
				 toolbar: "#dictTool",
				searchOnEnterKey : true,
				sidePagination : "server",
				idField : "id",
				pageSize : 10,
				pageList : [ "10", "20", "50", "100" ],
				clickToSelect : true,
				singleSelect : true,
				showExport : true,
				exportDataType : 'all', 
				exportTypes : [ 'csv', 'txt', 'excel' ],
				cache: false,
				columns : [
						{
							field : 'state',
							radio : true,
							rowspan : 1,
							align : 'center',
							valign : 'middle'
						},
						{
							title : 'ID',
							field : 'id',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							visible : false
						},
						{
							title : 'SKU',
							field : 'sku',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
						},{
							title : '物料编号',
							field : 'materialId',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
						},{
							title : '物料单位',
							field : 'unit',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
						},
						{
							title : '客户名称',
							field : 'custname',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
						},
						{
							title : '所属销售组织',
							field : 'orgname',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
						},
						{
							title : '标准价格',
							field : 'basePrice',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							formatter : function(value) {
								if(!value){
									return '0';
								}
								var price = parseFloat(value);
								price /= 100;
								return price.formatPrice();
							}
						},
						{
							title : '到岸价',
							field : 'cifPrice',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							editable : {
								type : 'text',
								title : '修改到岸价:',
								validate : function(value) {
									value = $.trim(value);
									if (!value) {
										return '请输入正确的数值';
									}
									//^-?([1-9]\d*|0)(?:\.\d{1,4})?$
									if (!/^0$|(^(-?)(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,4})?$)/.test(value)) {
										return '价格只能保留4位小数'
									}
									var data = $table.bootstrapTable('getData'), index = $(
											this).parents('tr').data('index');
									var rows = $table.bootstrapTable('getSelections');
									editCifPrice(rows[0], value, null);
									return '';
								}
							},
							formatter : function(value) {
								if(!value){
									return '0';
								}
								var price = parseFloat(value);
								price /= 100;
								return price.formatPrice();
							}
						},
						/*{
							title : '物流价格',
							field : 'price',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							visible : false,
							editable : {
								type : 'text',
								title : '修改物流价格:',
								validate : function(value) {
									value = $.trim(value);
									if (!value) {
										return '请输入正确的数值';
									}
									if (!/^[1-9]\d*$/.test(value)) {
										return '请输入正确的数值,如100'
									}
									var data = $table.bootstrapTable('getData'), index = $(
											this).parents('tr').data('index');
									var rows = $table.bootstrapTable('getSelections');
									editSkuPrice(rows[0], value, null);
									return '';
								}
							},
							formatter : function(value) {
								if(!value){
									return '0';
								}
								var price = parseFloat(value);
								price /= 100;
								return price.formatPrice();
							}
						},*/
						
						{
							title : '调整价格',
							field : 'hPrice',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							editable : {
								type : 'text',
								title : '修改调整价格:',
								validate : function(value) {
									value = $.trim(value);
									if (!value) {
										return '请输入正确的数值';
									}
									//^-?([1-9]\d*|0)(?:\.\d{1,4})?$
									if (!/^0$|(^(-?)(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,4})?$)/.test(value)) {
										return '价格只能保留4位小数'
									}
									var data = $table.bootstrapTable('getData'), index = $(
											this).parents('tr').data('index');
									var rows = $table.bootstrapTable('getSelections');
									editSkuPrice(rows[0], null, value);
									return '';
								}
							},
							formatter : function(value) {
								if(!value){
									return '0';
								}
								var price = parseFloat(value);
								price /= 100;
								return price.formatPrice();
							}
						},
						{
							title : '调整价起效日期',
							field : 'bDate',
							rowspan : 1,
							align : 'center',
							valign : 'middle'
						},
						{
							title : '调整价失效日期',
							field : 'eDate',
							rowspan : 1,
							align : 'center',
							valign : 'middle'
						},
						{
							title : '启用状态',
							field : 'states',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							formatter : function(value) {
								if(value=='4'){
									return '已启用';
								}
								return '已停用';
							}
						}
						]
			});
	$("#priceBdate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
	$("#priceEdate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
	$.validator.addMethod("Edate",function(value,element){
		function p(s) {
    	    return s < 10 ? '0' + s: s;
    	}
		//当前时间
		var nowDate = new Date();
    	var year = nowDate.getFullYear();
    	var month=nowDate.getMonth()+1;
    	var date=nowDate.getDate(); 
    	var nowtime=year+'/'+p(month)+"/"+p(date);
    	var timestamp = Date.parse(new Date(nowtime));
    	timestamp = timestamp / 1000;
    	//录入截止时间
    	var endtime = $("#priceEdate").val();
    	var endtimetemp = Date.parse(new Date(endtime));
    		endtimetemp = endtimetemp/1000;
    	var begintime = $("#priceBdate").val();
        var begintimetemp = Date.parse(new Date(begintime));
        	begintimetemp = begintimetemp/1000;
        if(endtimetemp<timestamp){
        	return false;
        }
        if(endtimetemp<begintimetemp){
        	return false;
        }
        return true;
	},"截止日期必须大于开始日期和当前时间");
    $("#updatePrice").bind("click",updatePrice);
    $('#materialId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
    var addValidator = initValidate("#updatePriceFrom");
    $("#updatePriceFrom").ajaxForm({
		target : '#btn-edit-save', // target element(s) to be updated with server
		// response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			var result = addValidator.valid();
			return result;
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("新增录入信息失败",responseText.errorMessage);
            }else{
    			if(responseText.data=="S"){
    				$.messager.popup("价格维护成功");
    				$("#updatePriceModal").modal("hide");
        			$("#contract-table").bootstrapTable("refresh")
    			}else if(responseText.data=="E1"){
    				$.messager.popup("存在相同有效区间的价格数据，请重新维护有效期");
    			}else{
    				$.messager.alert("错误提示",responseText.data.code);
    			}
            }
		}
	});
    $("#batchMaintain").click(function(){
    	window.location.href="customer/sku/batchMaintain.html";
    })
    $("#saltAdjust").click(function(){
    	window.location.href="customer/sku/saltAdjust.html";
    })
}
function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			hPrice : {
				required : true,
				price:true
			},
			priceBdate:"required",
			priceEdate:{
				required:true,
				Edate:true
			}
		}
	});
	return validator;
}
function updatePrice(){
	var row = $("#contract-table").bootstrapTable("getSelections");
	$("#updatePriceModal").modal("show");
	/*if (row && row.length == 1 ) {
		$("#id").val(row[0].id);
		$("#materialId").val(row[0].materialId);
		$("#sku").val(row[0].sku);
		$("#basePrice").val(row[0].basePrice/100);
	} else {
		$.messager.alert("提示", "请选择记录!");
	}*/
}
// functions by table oprate
function getIdSelections() {
	return $.map($table.bootstrapTable('getSelections'), function(row) {
		return row.agentID; // getID by our column
	});
}

function responseHandler(res) {
	$.each(res.rows, function(i, row) {
		row.state = $.inArray(row.id, selections) !== -1;
	});
	return res;
}

function detailFormatter(index, row) {
	var html = [];
	$.each(row, function(key, value) {
		html.push('<p><b>' + key + ':</b> ' + value + '</p>');
	});
	return html.join('');
}

function operateFormatter(value, row, index) {
	return [ '<a class="remove" href="javascript:;" title="删除本行">',
			'<i class="icon icon-trash"></i>', '</a>' ].join('');
}
window.operateEvents = {
	'click .remove' : function(e, value, row, index) {
		$table.bootstrapTable('remove', {
			field : 'agentID',
			values : [ row.agentID ]
		// must set the delete col id by our data col id.
		});
	}
};

function totalTextFormatter(data) {
	return 'Total';
}

function totalNameFormatter(data) {
	return data.length;
}

function totalPriceFormatter(data) {
	var total = 0;
	$.each(data, function(i, row) {
		total += +(row.price.substring(1));
	});
	return '$' + total;
}

function getHeight() {
	return $(window).height() - $('h1').outerHeight(true);
}

function detailFormatter(index, row) {
	var html = [];
	$.each(row, function(key, value) {
		html.push('<p><b>' + key + ':</b> ' + value + '</p>');
	});
	return html.join('');
}

// page oprate functions
function addAgent(agent) {
	$table.bootstrapTable('insertRow', {
		index : 0,
		row : agent
	});
}

var categorys = [];

function initSearchbar(){
	$.get('product/series.json?limit=100', function(res){
		if(!res.rows){
			return;
		}
		var html = '<option value="">全部</option>';
		$.each(res.rows, function(i, val){
			html += '<option value="'+val.series+'">'+val.series+'</option>';
		});
		$("#selectseries").html(html);
	});
	$.get('product/brand.json?limit=100', function(res){
		if(!res.rows){
			return;
		}
		var html = '<option value="">全部</option>';
		$.each(res.rows, function(i, val){
			html += '<option value="'+val.brand+'">'+val.brand+'</option>';
		});
		$("#selectbrand").html(html);
	});
	$.get('product/iPackage.json?limit=100', function(res){
		if(!res.rows){
			return;
		}
		var html = '<option value="">全部</option>';
		$.each(res.rows, function(i, val){
			html += '<option value="'+val.iPackage+'">'+val.iPackage+'</option>';
		});
		$("#selectipackage").html(html);
	});
}

function changeBrand(id){
	var html = '<option value="">全部</option>';
	$.each(categorys, function(n, value){
		if(value && value.pid==id){
			html += '<option value="'+value.id+'">'+value.name+'</option>';
		}
	});
	$("#selectbrand").html(html);
}

function changeSeries(id){
	var html = '<option value="">全部</option>';
	$.each(categorys, function(n, value){
		if(value && value.pid==id){
			html += '<option value="'+value.id+'">'+value.name+'</option>';
		}
	});
	$("#selectseries").html(html);
}

function getName(id){
	var name = "";
	$.each(categorys, function(i, val){
		if(val.id==id){
			name = val.name;
			return false;
		}
	});
	return name;
}

function search() {
	var selectbrand = $("#selectbrand").val();
	var selectSeries = $("#selectseries").val();
	var selectipackage = $("#selectipackage").val();
	var selectOrg = $("#selectorg").val();
	var selectCust = $("#selectcust").val();
	var selectSap = $("#selectsap").val();
	var custType =$("#custType").val();
	var url = 'customer/sku/list.json?brand=' + selectbrand + '&series='
			+ selectSeries + '&organizationId=' + selectOrg + '&custname='
			+ selectCust + '&materialId=' + selectSap+'&iPackage='+selectipackage+"&custType="+custType;
	$("#contract-table").bootstrapTable("refresh", {
		'url' : url
	});
}

function editSkuPrice(row, price, hprice){
	$.post('customer/sku/edit.json',{'id':row.id, 'price':price, 'hPrice':hprice}, function(res){
		if(res.errorCode!=0){
			$.messager.alert('错误','更改失败');
		}
	});
}
function editCifPrice(row, price, hprice){
	$.post('customer/sku/edit.json',{'id':row.id, 'cifPrice':price, 'hPrice':hprice}, function(res){
		if(res.errorCode!=0){
			$.messager.alert('错误','更改失败');
		}
	});
}

initTable();
$(function() {
	initSearchbar();
	$('#add-bstb-row').click(function() {
		$('.add-bstb-box').addClass("add-bstb-box-open");
	});
	$('#btn-hide-bstb-add-box').click(function() {
		$('.add-bstb-box').removeClass("add-bstb-box-open");
	});
	$('#btn-add-agent').click(function() {
		var agentItem = {
			agentID : '612',
			agentName : $('#input-agent-name').val(),
			agentType : $('#input-agent-type').val(),
			agentDestYear : $('#input-agent-destYear').val(),
			agentPoitYear : $('#input-agent-poitYear').val() + "%",
			agentDestQuarter : $('#input-agent-destYear').val() / 4,
			agentPoitQuarter : ($('#input-agent-poitYear').val() / 4) + "%",
			testTime : "2015-10-13"
		}
		addAgent(agentItem);
	});
	
	$("#searchButton").bind('click', search);
	$("#upMaterial").bind("click",upMaterial);
	$("#changeStatusBtn").bind('click', changeEnablestatus);
});

function upMaterial(){
	 $.messager.confirm("提示","更新物料时间有可能会较长，请耐心等待",function () {
		 $.post("customer/sku/upMaterial",function(res){
			 if(res.errorCode == "0"){
	                $.messager.popup("更新成功")
	            }else{
	                $.messager.alert("提示","更新失败");
	            }
			 $("#contract-table").bootstrapTable("refresh");
			}) 
	 });
	
}

function changeEnablestatus(){
	var rows = $("#contract-table").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        $.post("customer/sku/changeEnable.json",{"id":rows[0].id}, function(res){
        	if(res.data==1){
        		$.messager.popup("更改成功");
        		$("#contract-table").bootstrapTable("refresh");
        	}else{
        		$.messager.popup("更改失败");
        	}
        });
    }else{
        $.messager.alert("提示", "请选择要更改的记录!");
    }
}
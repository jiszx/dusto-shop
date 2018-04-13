var $table = $('#order-table');
var orderdata;
$(function() {
	initTable();
	// 初始化可退货订单数据
	$('#orderList').chosen({
		no_results_text : "没有找到",
		placeholder_text : "请选择订单信息...",
		allow_single_de : true,
		search_contains : true
	});
	$('#rdcCode').chosen({
		no_results_text : "没有找到",
		search_contains: true,
		allow_single_de : true
	});
	$('#back_reason').chosen({
		no_results_text : "没有找到",
		search_contains: true,
		allow_single_de : true
	});
	var url = 'orderBack/backOrderHeaderList';
	$.get(url, function(data) {
		if (data.rows && data.rows.length > 0) {
			$('#orderList').html();
			$('#orderList').append("<option></option>");
			orderdata=data.rows;
			for (var i = 0; i < data.rows.length; i++) {
				$('#orderList').append("<option value='" + data.rows[i].id + "'>"+data.rows[i].id +"-"+ data.rows[i].custname + "</option>");
			}
			$("#orderList").trigger("chosen:updated");
		}
	});
	$("#orderList").change(function(){
		var orderid = $("#orderList").val();
		for(var i=0;i<orderdata.length;i++){
			if(orderdata[i].id ==orderid){
				$("#custname").val(orderdata[i].custname+"("+getCustType(orderdata[i].custType)+")");
				$("#sapOrderId").val(orderdata[i].sapOrderId);
				$("#rdcCode").val(orderdata[i].rdcCode);
				$("#orderType").val(getverification(orderdata[i].orderType));
				$("#rdcCode").trigger("chosen:updated");
				//获取行数据
				var url ='orderBack/getOldOrderLine?headerid='+orderid;
				$("#order-table").bootstrapTable('refresh', {
					'url' : url
				});
			}
		}
	});
	$("#btn-save").bind('click', doSave);
});

function doSave(){
	var data = $("#order-table").bootstrapTable('getData');
	var back_reason =$("#back_reason").val();
	var oaOrderNo =$("#oaOrderNo").val();
	var remark =$("#remark").val();
	if(!back_reason){
		$.messager.popup("请选择退货原因");
		return;
	}
	if(!remark){
		$.messager.alert("备注信息为必填项");
		return;
	}
	if(!oaOrderNo){
		$.messager.alert("OA单号不可以为空");
		return;
	}
	remark ="OA单号："+oaOrderNo+remark;
	var lines=[];
	var num_all=0;
	var hbnum_all =0;
	for(var i=0;i<data.length;i++){
		if(data[i].type !='合计'){
			var str={};
			str.id=data[i].id;
			if(data[i].num >0){
				str.num=0;
			}
			if(data[i].hbNum >0){
				str.hbNum=0;
			}
			if(data[i].backNum && data[i].backNum >0){
				str.num=data[i].backNum*data[i].amounts;
				num_all =num_all+data[i].backNum;
			}
			if(data[i].backhbNum && data[i].backhbNum >0){
				str.hbNum=data[i].backhbNum*data[i].amounts;
				hbnum_all=hbnum_all+data[i].backhbNum;
			}
			if(str.id && str.id !=null){
				lines.push(str);				
			}
		}
	}
	if(num_all ==0 && hbnum_all ==0){
		$.messager.popup("退货合计数量不能为0");
	}
	if(lines.length <1){
		$.messager.popup("请填写退货数量");
		return;
	}
	$("#btn-save").attr("disabled","true");
	var orderid = $("#orderList").val();
	$.post("orderBack/addBackOrder", {
		"orderid":orderid,
		"backReason":back_reason,
		"lines" : JSON.stringify(lines),
		"remark":remark
	}, function(res) {
		if(res.data=='E'){
			$.messager.popup("保存退货订单出错");
			$("#btn-save").removeAttr("disabled");
		}else{
			$('#headerId').val(res.data);
			$('#orderFrom').ajaxSubmit(function(){
				window.location.href = "orderBack/orderBackList.html";
			});
		}
	});
}
function initTable(){
	$table.bootstrapTable({
		url:'order/util/linedata',
		method : 'get',
		classes : "table table-hover table-condensed",
		cache : false,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "payName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		clickToSelect : true,
		singleSelect : true,
		columns : [{
			title : '编号',
			field : 'id',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '产品名称',
			field : 'sku',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : false,
		}, {
			title : '物料编码',
			field : 'materialId',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : false
		}, {
			title : '单位',
			field : 'unit',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
		}, {
			title : '规格',
			field : 'specifications',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
		}, {
			title : '箱内数量',
			field : 'amounts',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
		}, {
			title : '单价(元)',
			field : 'orderPrice',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
			formatter : function(value, row) {
				if(value==0){
					return '-';
				}
				if(row.amounts){
					return amtChange(value, row.amounts, true).toFixed(4);
				}
			}
		}, {
			title : '可退下单数量',
			field : 'num',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
			formatter:function(value, row){
				if(row.amounts){
					return amtChange(value, row.amounts).toFixed(3);
				}else{
					return value;
				}
			}
		},{
			title : '退货下单数量',
			field : 'backNum',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
			editable : {
				type : 'text',
				title : '退货数量:',
				validate : function(value) {
					value = $.trim(value);
					if (!value) {
						return '请输入正确的数值';
					}
					if (!/^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,3})?$)/.test(value)) {
						return '请输入大于0的3位小数'
					}
					var data = $table.bootstrapTable('getData');
					index = $(this).parents('tr').data('index');
					if(parseFloat(value) > parseFloat(amtChange(data[index].num, data[index].amounts).toFixed(3))){
						return '退货数量必须小于原数量';
					}
					var amt = amtChange(data[index].orderPrice, data[index].amounts, true).toFixed(4)*value;
					$table.bootstrapTable('updateCell', {
						index : index,
						field : "orderAmt",
						value :amt
					});
					$table.bootstrapTable('updateCell', {
						index : index,
						field : "backNum",
						value :value
					});
					return '';
				}
			},
			formatter : function(value) {
				if(!value){
					return '0';
				}
				return value;
			}
		},{
			title : '可退货补数量',
			field : 'hbNum',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			formatter:function(value, row){
				if(row.amounts){
					return amtChange(value, row.amounts).toFixed(3);
				}else{
					return value;
				}
			}
		},{
			title : '退货货补数量',
			field : 'backhbNum',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
			editable : {
				type : 'text',
				title : '退货数量:',
				validate : function(value) {
					value = $.trim(value);
					if (!value) {
						return '请输入正确的数值';
					}
					if (!/^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,3})?$)/.test(value)) {
						return '请输入大于0的3位小数'
					}
					var data = $table.bootstrapTable('getData');
					index = $(this).parents('tr').data('index');
					if(parseFloat(value) > parseFloat(amtChange(data[index].hbNum, data[index].amounts).toFixed(3))){
						return '退货数量必须小于原数量';
					}
					var amt = amtChange(data[index].orderPrice, data[index].amounts, true).toFixed(4)*value;
					$table.bootstrapTable('updateCell', {
						index : index,
						field : "hbAmt",
						value :amt
					});
					$table.bootstrapTable('updateCell', {
						index : index,
						field : "backhbNum",
						value :value
					});
					return '';
				}
			},
			formatter : function(value) {
				if(!value){
					return '0';
				}
				return value;
			}
		}, {
			title : '下单退货金额',
			field : 'orderAmt',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true
		}, {
			title : '货补退货金额',
			field : 'hbAmt',
			rowspan : 1,
			align : 'center',
			valign : 'middle'
		} ] 
	});
	//删除附件
    $(".file-list").on("click", "a.del-file", function () {
    	var delFileId = $(this).attr("id");
    	var fileItem = $(this).parent();
    	fileItem.remove();
    	if(typeof delFileId != 'undefined'){
    		//删除刚上传的附件
    		var fileInputEle = $("#file_"+delFileId.split("del_file_")[1]);
    		fileInputEle.remove();
    	}else{
    		//删除已存在的附件
    		var delAtts = $("#delAtts").val();
    		if(typeof delAtts != 'undefined' && $.trim(delAtts).length >0){
    			var delAttArray = delAtts.split(",");
    			delAttArray.push($(this).attr("source"));
    			$("#delAtts").val(delAttArray.join(","));
    		}else{
    			$("#delAtts").val($(this).attr("source"));
    		}
    	}
        checkFile();
    });
}
function amtChange(price, amounts, isMulti){
	if(isMulti){
		return (parseFloat(price)*parseFloat(amounts));
	}
	return (parseFloat(price)/parseFloat(amounts));
}
/**
 * 添加待上传的文件
 */
function addFiles() {
	var newFileId = '' + new Date().getTime() + Math.floor(Math.random() * 10);
	var fileBox = $("#file-select");
	var fileBoxNew = fileBox.clone();
	fileBox.after(fileBoxNew);
	var file = fileBox.prop('files')[0];
	var filePath = fileBox.val()
	var fileName = file.name;
	//upfiles = upfiles.push(0, filePath);
	fileBox.attr("id", "file_" + newFileId).removeAttr("onchange").attr("name",
			"files");
	fileBox.hide();
	if (file) {
		var item = '<li>'
				+ '<span class="file-name" data-file-src="'
				+ filePath
				+ '">'
				+ fileName
				+ '</span>'
				+ '<a href="javascript:;" class="pull-right del-file" id="del_file_'
				+ newFileId
				+ '" title="删除附件"><i class="icon icon-remove"></i></a>'
				+ $('<div>').append().html() + '</li>';
		$(".file-list").append(item);

	} else {
		$.messager.alert('文件读取错误。');
	}
	checkFile();
}
function checkFile() {
	var fileList = $(".file-list>li");
	if (fileList.length > 0) {
		$(".file-list-box").fadeIn();
	} else {
		$(".file-list-box").fadeOut();
	}
}
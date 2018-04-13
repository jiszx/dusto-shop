var $table = $('#order-table');
$(function() {
	initTable();
	$("#btn-save").bind('click', doSave);
	$("#backOrderEditForm").ajaxForm({
		//target : '#btn-save', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return true;
		},
		success : function(responseText, statusText, xhr, $form) {
			window.location.href = "orderBack/orderBackList.html";
		}
	});
})

function initTable() {
	$table.bootstrapTable({
		url : 'orderBack/editLineData?headerid=' + $("#orderid").val(),
		method : 'get',
		classes : "table table-hover table-condensed",
		cache : false,
		toolbar : "#upAccountTool",
		striped : true,
		pagination : true,
		sidePagination : "server",
		idField : "id",
		sortName : "payName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [
				{
					title : '编号',
					field : 'id',
					rowspan : 1,
					align : 'center',
					valign : 'middle',
					visible : false
				},
				{
					title : '类型',
					field : 'type',
					rowspan : 1,
					align : 'center',
					valign : 'middle',
					formatter : function(value) {
						if (value =='1') {
							return '标准';
						}else if(value=='2'){
							return '货补';
						}
						return value;
					}
				},
				{
					title : '产品名称',
					field : 'sku',
					rowspan : 1,
					align : 'center',
					valign : 'middle',
					sortable : false,
				},
				{
					title : '物料编码',
					field : 'materialId',
					rowspan : 1,
					align : 'center',
					valign : 'middle',
					sortable : false
				},
				{
					title : '单位',
					field : 'unit',
					rowspan : 1,
					align : 'center',
					valign : 'middle',
					sortable : true,
				},
				{
					title : '箱内数量',
					field : 'amounts',
					rowspan : 1,
					align : 'center',
					valign : 'middle',
					sortable : true,
				},
				{
					title : '单价(元)',
					field : 'orderPrice',
					rowspan : 1,
					align : 'center',
					valign : 'middle',
					sortable : true,
					formatter : function(value, row) {
						if (row.amounts) {
							return (amtChange((value !=null && value !='')?value:row.price , row.amounts, true)/100).toFixed(4);
						}
						return value;
					}
				},
				{
					title : '原订单可退数量',
					field : 'oldNum',
					rowspan : 1,
					align : 'center',
					valign : 'middle',
					sortable : true,
					formatter : function(value, row) {
						if (row.amounts) {
							return amtChange(value, row.amounts).toFixed(
									3);
						}
						return value;
					}
				},
				{
					title : '退货数量',
					field : 'num',
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
								return '输入大于0的3位小数';
							}
							var data = $table.bootstrapTable('getData');
							index = $(this).parents('tr').data('index');
							if(parseFloat(value) > parseFloat(amtChange(data[index].oldNum, data[index].amounts).toFixed(3))){
								return '退货数量必须小于原数量';
							}
							editNum(data[index].id,value*data[index].amounts);
							return '';
						}
					},
					formatter : function(value, row) {
						if (row.amounts) {
							return amtChange(value, row.amounts).toFixed(3);
						}
						return value;
					}
				}, {
					title : '金额',
					field : 'amt',
					rowspan : 1,
					align : 'center',
					valign : 'middle',
					visible : true,
					formatter : function(value) {
						return (value/100).toFixed(2);
					}
				} ]
	});

}
function doSave(){
	if(!$("#remark").val()){
		$.messager.alert("备注不可以为空");
		return false;
	}
	$("#sremark").val($("#remark").val());
	$("#sbackReason").val($("#back_reason").val());
	$("#backOrderEditForm").attr("action","orderBack/backOrderEditSave").submit();
}

function delFile(id){
	$.post("order/delOrderFile?id="+id+"&orderId="+$("#orderid").val(),function(res){
		if(res.data ==1){
			$("#"+id).remove();
		}else{
			$.messager.popup("删除附近失败");
		}
	})
}

/**
 * 添加待上传的文件
 */
function addFiles() {
	//$("#fileUpForm").attr("action","order/addOrder").submit();
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
function editNum(spiltId,num){
	$.post("orderBack/editNum", {
		"spiltId" : spiltId,
		"num" : num
	}, function(res) {
		$table.bootstrapTable('refresh');
	})
}

function amtChange(price, amounts, isMulti) {
	if (isMulti) {
		return (parseFloat(price) * parseFloat(amounts));
	}
	return (parseFloat(price) / parseFloat(amounts));
}
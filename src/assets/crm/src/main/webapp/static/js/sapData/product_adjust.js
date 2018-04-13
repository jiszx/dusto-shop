var orgObj = new Object();
$(function() {
	$('#dictTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'product/price/adjust/list',
		cache : false,
		toolbar : "#dictTool",
		striped : true,
		showExport : true,
		exportDataType : 'all', // basic, all, selected
		exportTypes : [ 'csv', 'txt', 'excel' ],
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
//		sortName : "colName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		search : false,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [{
			field : 'ck',
			title : '编号',
			radio : true
		}, {
			field : 'adjustType',
			title : '调整类型',
			align : 'left',
			formatter:function(val, row){
				var detaillink = $('<a href="javascript:void(0);" vtype="'+val+'" name="detailView">');
				detaillink.attr('title','点击查看影响的物料信息');
				if("1" == val){
					detaillink.text("标准价");
				}else if("2" == val){
					detaillink.text( "调整价");
				}else{
					detaillink.text("-");
				}
            	return detaillink.prop("outerHTML");
			}
		}, {
			field : 'adjustDesc',
			title : '描述',
			align : 'left'
		}, {
			field : 'createTs',
			title : '处理时间',
			align : 'left'
		}, {
			field : 'adjustVal',
			title : '调整值',
			align : 'left',
			formatter:function(val, row){
				if(typeof val == 'undefined' && row.adjustDesc.indexOf('导入') != -1){
					return '<a href="javascript:void(0);" class="tooltip-link" style="color:#333;cursor:help;" data-toggle="tooltip" title="导入文件时没有统一的调整值">-</a>';
				}
            	return Number(val/100).formatMoney();;
			}
		}, {
			field : 'adjustOpt',
			title : '调整操作',
			align : 'left',
			formatter:function(val, row){
				if(typeof val == 'undefined' && row.adjustDesc.indexOf('导入') != -1){
					return '<a href="javascript:void(0);" class="tooltip-link" style="color:#333;cursor:help;" data-toggle="tooltip" title="导入文件时没有统一的调整操作">-</a>';
				}else if(val == '3' && row.adjustType == '1'){
					return '<a href="javascript:void(0);" class="tooltip-link" style="color:#333;cursor:help;" data-toggle="tooltip" title="标准价调整时价格直接替换标准价">覆盖</a>';
				}else if(val == '1'){
					return '<a href="javascript:void(0);" class="tooltip-link" style="color:#333;cursor:help;" data-toggle="tooltip" title="调整价调整时在标准价基础上处理">加</a>';
				}else if(val == '2'){
					return '<a href="javascript:void(0);" class="tooltip-link" style="color:#333;cursor:help;" data-toggle="tooltip" title="调整价调整时在标准价基础上处理">乘</a>';
				}
				return '-';
			}
		}, {
			field : 'status',
			title : '当前状态',
			align : 'left',
			formatter:function(val, row){
				if("1" == val){
					return "草稿";
				}else if("2" == val){
					return "已提交";
				}else if("3" == val){
					return "审批通过";
				}else if("4" == val){
					return "驳回";
				}
            	return "-";
			}
		} ],
		onLoadSuccess:function(){
			$('a.tooltip-link').tooltip()
        }
	});
	
	var addValidator = initValidate("#importForm");
	var editValidator = initValidate("#editBasePriceForm");
	var editAdjustValidator = initValidate("#editAdjustPriceForm");
	var editAdjustBatchValidator = initValidate("#editAdjustPriceBatchForm");
	
	$("#importForm").ajaxForm({
		target : '#btn-import', // target element(s) to be updated with server
		// response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			buttonWait($('#btn-import'), true);
			return addValidator.valid();
		},
		success : function(res, statusText, xhr, $form) {
			buttonWait($('#btn-import'), false);
			if (typeof res.errorCode == 'undefined' || res.errorCode != '-1') {
				$('#dictTable').bootstrapTable('refresh');
				$("#importModal").modal('hide');
			} else {
				$.messager.alert("提示", res.errorMessage? res.errorMessage:"上传失败");
			}
		}
	});
	$("#editBasePriceForm").ajaxForm({
		target : '#btn-edit-save', // target element(s) to be updated with server
		// response
		resetForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			buttonWait($('#btn-edit-save'), true);
			return editValidator.valid();
		},
		success : function(res, statusText, xhr, $form) {
			buttonWait($('#btn-edit-save'), false);
			if (typeof res.errorCode == 'undefined' || res.errorCode != '-1') {
				$('#dictTable').bootstrapTable('refresh');
				$("#editBasePriceModal").modal('hide');
			} else {
				$.messager.alert("提示", res.errorMessage? res.errorMessage:"保存失败");
			}
		}
	});
	$("#editAdjustPriceForm").ajaxForm({
		target : '#btn-edit-adjust-save', // target element(s) to be updated with server
		// response
		resetForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			buttonWait($('#btn-edit-adjust-save'), true);
			return editValidator.valid();
		},
		success : function(res, statusText, xhr, $form) {
			buttonWait($('#btn-edit-adjust-save'), false);
			if (typeof res.errorCode == 'undefined' || res.errorCode != '-1') {
				$('#dictTable').bootstrapTable('refresh');
				$("#editAdjustPriceModal").modal('hide');
			} else {
				$.messager.alert("提示", res.errorMessage? res.errorMessage:"保存失败");
			}
		}
	});
	$("#editAdjustPriceBatchForm").ajaxForm({
		target : '#btn-edit-adjust-batch-save', // target element(s) to be updated with server
		// response
		resetForm : false,
		dataType : 'json',
		beforeSerialize : function(jqForm, options){
			var adjustParams = jqForm.find('[name^="adjustParam"]');
			adjustParams.each(function(index){
				if($(this).is(':visible')){
					$(this).attr("name","adjustParam");
				}else{
					$(this).attr("name","adjustParam"+index);
				}
			})
		},
		beforeSubmit : function(formData, jqForm, options) {
			buttonWait($('#btn-edit-adjust-batch-save'), true);
			return editValidator.valid();
		},
		success : function(res, statusText, xhr, $form) {
			buttonWait($('#btn-edit-adjust-batch-save'), false);
			if (typeof res.errorCode == 'undefined' || res.errorCode != '-1') {
				$('#dictTable').bootstrapTable('refresh');
				$("#editBatchAdjustPriceModal").modal('hide');
				$form[0].reset();
				$form.find('select option:eq(0)').attr('selected','true').parent().find('option:not(:eq(0))').removeAttr('selected');
				$form.find('select').change();
			} else {
				$.messager.alert("提示", res.errorMessage? res.errorMessage:"保存失败");
			}
		}
	});
	$("#importPriceButton").bind("click", importPrice);
	$("#editBasePriceButton").bind("click", editPrice);
	$("#editAdjustPriceButton").bind("click", editAdjustPrice);
	$("#editBatchAdjustPriceButton").bind("click", editBatchAdjustPrice);
	$("#delAdjustButton").bind("click", delAdjust);
	$("#submitAdjustButton").bind("click", submitAdjust);
	$("#searchBtn").bind("click", searchList);
	
	$("#dictTable").on("click","a[name='detailView']",function(){
		var currtr = $(this).closest('tr');
		var nexttr = currtr.next();
		var detailDiv = $("#dictTable div[name='detailTbDiv']");
		if(detailDiv && detailDiv.length>0){
			detailDiv.slideUp("normal",function(){detailDiv.closest("tr").remove()});
			if(!nexttr || nexttr.attr("name") != 'detailTr'){
				expandDetail(currtr);
			}
		}else{
			expandDetail(currtr);
		}
	});
	
	$('#input_adjustCategory').on('change',function(){
		var sval = $(this).val();
		$('div[id^="look_"]').each(function(){
			var thisid = $(this).attr('id'); 
			if(thisid == "look_"+sval){
				$(this).show();
				editAdjustBatchValidator = initValidate("#editAdjustPriceBatchForm");
			}else{
				$(this).hide();
			}
		})
	});
	
	initSearchbar();
	initTimepicker();
});

function expandDetail(currtr){
	var tdl = currtr.find("td").length;
	var dtr = $('<tr name="detailTr">');
	var dtd = $('<td colspan='+tdl+'></td>');
	var dtb = '<div name="detailTbDiv" style="display:none;"><table name="detailTable" ></table></div>';
	dtd.append(dtb);
	dtr.append(dtd.prop("outerHTML"))
	currtr.after(dtr.prop("outerHTML"));
	
	//展开前先加载
	var adjustId = currtr.find("td:eq(0) input").val();
	var adjustType = currtr.find("a[name='detailView']:eq(0)").attr('vtype');
	var url = "product/price/adjust/detail/list/"+adjustId;
	$('table[name="detailTable"]').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : url,
		cache : false,
		striped : true,
		pagination : false,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "colName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		search : false,
		clickToSelect : true,
		singleSelect : true,
		columns : [{
			field : 'materialId',
			title : '物料编码',
			align : 'left'
		}, {
			field : 'organizationId',
			title : '销售组织',
			align : 'left',
			formatter:function(val, row){
				if(typeof val == 'undefined' || val == ''){
					return '-'
				}
				/*if(val == '1420'){
					return '辽宁益盐堂';
				}
				if(val == '1161'){
					return '广盐华源';
				}
				if(val == '1100'){
					return '应城益盐堂(盐)';
				}*/
				return orgObj['c'+val];
			}
		}, {
			field : 'channel',
			title : '渠道',
			align : 'left'
		}, {
			field : 'oprice',
			title : '原价格',
			align : 'left',
			formatter:function(val, row){
				if(typeof val == 'undefined' || val == ''){
					return '-'
				}
            	return Number(val/100).formatMoney();
			}
		}, {
			field : 'oadjustPrice',
			title : '原调整价',
			align : 'left',
			formatter:function(val, row){
				if(typeof val == 'undefined' || val == ''){
					return '-'
				}
            	return Number(val/100).formatMoney();
			}
		}, {
			field : 'obdate',
			title : '原有效期开始日',
			align : 'left',
			formatter:function(val, row){
				if(typeof val == 'undefined' || val == ''){
					return '-'
				}
				return new Date(Date.parse(val)).format("yyyy-MM-dd");
			}
		}, {
			field : 'oedate',
			title : '原有效期截止日',
			align : 'left',
			formatter:function(val, row){
				if(typeof val == 'undefined' || val == ''){
					return '-'
				}
				return new Date(Date.parse(val)).format("yyyy-MM-dd");
			}
		}, {
			field : 'price',
			title : (adjustType=='1'?'新价格':'新调整价'),
			align : 'left',
			formatter:function(val, row){
				var mval = Number(val/100).formatMoney();
				if(row.opt && row.opt != ''){
					if(row.opt == '1'){
						return '标准价+'+mval;
					}else if(row.opt == '2'){
						return '标准价*'+mval;
					}
				}
            	return mval;
			}
		}, {
			field : 'bdate',
			title : (adjustType=='1'?'新有效期开始日':'新调整价有效期开始日'),
			align : 'left',
			formatter:function(val, row){
            	return new Date(Date.parse(val)).format("yyyy-MM-dd");
			}
		}, {
			field : 'edate',
			title : (adjustType=='1'?'新有效期截止日':'新调整价有效期截止日'),
			align : 'left',
			formatter:function(val, row){
            	return new Date(Date.parse(val)).format("yyyy-MM-dd");
			}
		}]
	});
	
	//展开
	$("div[name='detailTbDiv']").slideDown("normal");
}

function searchList(){
	var params = $("form#searchForm").serialize();
	
	var url = 'product/price/adjust/list?'+params;
	$("#dictTable").bootstrapTable("refresh", {
		'url' : url
	});
}

function importPrice(){
	$("#importModal").modal('show');
}

function editPrice(){
	$("#editBasePriceModal").modal('show');
}

function editAdjustPrice(){
	$("#editAdjustPriceModal").modal('show');
}

function editBatchAdjustPrice(){
	$("#editBatchAdjustPriceModal").modal('show');
}

function submitAdjust(){
	var rows = $("#dictTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		if(rows[0].status != '1' && rows[0].status != '4'){
			$.messager.alert("提示", "只能提交草稿状态的记录!");
			return;
		}
		$.messager.confirm("警告", "您确认要提交此记录吗?", function() {
			$.post("product/price/adjust/apply", {
				"adjustId" : rows[0].id
			}, function(res) {
				if(res.data == '0'){
					$.messager.popup("提交成功!");
					$("#dictTable").bootstrapTable("refresh");
				}else{
					$.messager.popup("提交失败!");
				}
			})
		});
	} else {
		$.messager.alert("提示", "请选择要提交的记录!");
	}
}

function delAdjust(){
	var rows = $("#dictTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		if(rows[0].status != '1' && rows[0].status != '4'){
			$.messager.alert("提示", "只能删除草稿或退回状态的记录!");
			return;
		}
		$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
			$.post("product/price/adjust/del", {
				"adjustId" : rows[0].id
			}, function() {
				$("#dictTable").bootstrapTable("refresh");
			})
		});
	} else {
		$.messager.alert("提示", "请选择要删除的记录!");
	}
}

function buttonWait(jbutton, active){
	if(active){
		jbutton.css('cursor','wait');
		jbutton.css('opacity','0.3');
		jbutton.attr('disabled','true');
	}else{
		jbutton.css('cursor','pointer');
		jbutton.css('opacity','1');
		jbutton.removeAttr('disabled');
	}
}

function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			file : "required",
			
			materialId:"required",
			organizationId:"required",
			channel:"required",
			price:{number:true, required:true},
			bdate:"required",
			edate:"required",
			
			adjustVal:{number:true, required:true},
			adjustCategory:{required:true},
			adjustParam:{required:true},
			adjustParam0:{required:true},
			adjustParam1:{required:true},
			adjustParam2:{required:true}
		},
		ignore: ":not(:visible)"
	});
	return validator;
}

function initTimepicker(){
	$("#input_bdate").datetimepicker({
		format : 'yyyy-mm-dd',
		minView: 2,
		language : 'zh-CN',
		autoclose : true
	});
	$("#input_edate").datetimepicker({
		format : 'yyyy-mm-dd',
		minView: 2,
		language : 'zh-CN',
		autoclose : true
	});
	$("#input_bdate_1").datetimepicker({
		format : 'yyyy-mm-dd',
		minView: 2,
		language : 'zh-CN',
		autoclose : true
	});
	$("#input_edate_1").datetimepicker({
		format : 'yyyy-mm-dd',
		minView: 2,
		language : 'zh-CN',
		autoclose : true
	});
	$("#input_bdate_2").datetimepicker({
		format : 'yyyy-mm-dd',
		minView: 2,
		language : 'zh-CN',
		autoclose : true
	});
	$("#input_edate_2").datetimepicker({
		format : 'yyyy-mm-dd',
		minView: 2,
		language : 'zh-CN',
		autoclose : true
	});
	$("#s_bdate").datetimepicker({
		format : 'yyyy-mm-dd',
		minView: 2,
		language : 'zh-CN',
		autoclose : true
	});
	$("#s_edate").datetimepicker({
		format : 'yyyy-mm-dd',
		minView: 2,
		language : 'zh-CN',
		autoclose : true
	});
}

function initSearchbar(){
	$.get('product/vicebrand.json?limit=100', function(res){
		if(!res.rows){
			return;
		}
		var html = '';
		$.each(res.rows, function(i, val){
			html += '<option value="'+val.brand+'">'+val.brand+'</option>';
		});
		$("#selectvicebrand").html(html);
	});
	$.get('product/series.json?limit=100', function(res){
		if(!res.rows){
			return;
		}
		var html = '';
		$.each(res.rows, function(i, val){
			html += '<option value="'+val.series+'">'+val.series+'</option>';
		});
		$("#selectseries").html(html);
	});
	$.get('Org/list/level/2', function(res){
		if(!res.data){
			return;
		}
		var html = '';
		var orgs = new Array();
		$.each(res.data, function(i, val){
			var cukey = val.sapId+'='+val.name;
			if(orgs.indexOf(cukey) != -1){
				return;
			}else{
				orgObj['c'+val.sapId] = val.name;
				orgs.push(cukey);
				var selectedOpt='';
				if(val.sapId == '1420'){
					selectedOpt='selected';
				}
				html += '<option value="'+val.sapId+'" '+selectedOpt+'>'+val.name+'</option>';
			}
		});
		$("select[name='organizationId']").html(html);
	});
}

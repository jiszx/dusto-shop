var $table = $('#contract-table');
$(function() {
	$('#batchMaintain').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url: 'customer/sku/batchMaintainList',
		cache : false,
		//data : data,
		toolbar : "#batchMaintainTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "colName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		//search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : 'ck',
			title : '编号',
			radio:true
		}, {
			field : 'orgname',
			title : '团队',
			align : 'left'
		}, {
			field : 'reginName',
			title : '大区',
			align : 'left'
		}, {
			field : 'areaName',
			title : '地区',
			align : 'left'
		}, {
			field : 'custname',
			title : '客户',
			align : 'left'
		},{
			field:'brand',
			title: '品牌',
			align:'left'
		}, {
			field : 'materialId',
			title : '物料编码',
			align : 'left'
		}, {
			field : 'sku',
			title : 'sku',
			align : 'left'
		}, {
			field : 'createTs',
			title : '调整时间',
			align : 'left'
		}, {
			field : 'creater',
			title : '创建人',
			align : 'left'
		}, {
			field : 'bDate',
			title : '有效开始时间',
			align : 'left'
		}, {
			field : 'eDate',
			title : '有效截止时间',
			align : 'left'
		}, {
			field : 'type',
			title : '调整类型',
			align : 'left',
			formatter : function(value) {
				return getType(value+"");
			}
		}, {
			field : 'adjustDirection',
			title : '调整方向',
			align : 'left',
			formatter : function(value) {
				return getDirection(value+"");
			}
		}, {
			field : 'price',
			title : '调整金额',
			align : 'left',
			formatter : function(value) {
				if(value){
					return value/100;					
				}else{
					return "";
				}
			}
		}, {
			field : 'states',
			title : '状态',
			align : 'left',
			formatter : function(value) {
				return getStates(value+"");
			}
		}]
	});
	
	//初始化品牌信息
	$.get('product/brand.json?limit=100', function(res){
		if(!res.rows){
			return;
		}
		var html = '<option value=""></option>';
		$.each(res.rows, function(i, val){
			html += '<option value="'+val.brand+'">'+val.brand+'</option>';
		});
		$("#brand").html(html);
		$("#sbrand").html(html);
		$("#editbrand").html(html);
	});
	//初始化地区信息
	$.get("pub/area/list",function(res){
		if(!res.data){
			return;
		}
		var html = '<option value=""></option>';
		$.each(res.data, function(i, val){
			html += '<option value="'+val.id+'">'+val.name+'</option>';
		});
		$("#sarea").html(html);
		$("#areaId").html(html);
		$("#editareaId").html(html);
	})
	//初始化物料信息
	$.get("customer/sku/materials",function(res){
		if(!res.rows){
			return;
		}
		var html = '<option value="">&nbsp;</option>';
		$.each(res.rows, function(i, val){
			html += '<option value="'+val.sapId+'">'+val.sapId+"-"+val.sku+'</option>';
		});
		$("#materialId").html(html);
		$("#materialId").trigger("chosen:updated");
		$("#editmaterialId").html(html);
		$("#editmaterialId").trigger("chosen:updated");
	})
	
	$('#merchCustId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	$('#editmerchCustId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	$('#materialId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	
	$('#editmaterialId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	
	$("#orgId").change(function(){
		var orgid =$("#orgId").val();
		$('#reginId').attr("disabled",false); 
		$('#merchCustId').attr("disabled",false); 
		$.get('Org/regin?orgid='+orgid, function(res){
			if(!res.rows){
				return;
			}
			var html = '<option value=""></option>';
			$.each(res.rows, function(i, val){
				html += '<option value="'+val.id+'">'+val.name+'</option>';
			});
			$("#reginId").html(html);
		});
		$.get('customer/sku/customers?orgid='+orgid, function(res){
			if(!res.rows){
				return;
			}
			var html = '<option value="">&nbsp;</option>';
			$.each(res.rows, function(i, val){
				html += '<option value="'+val.id+'">'+val.name+'</option>';
			});
			$("#merchCustId").html(html);
			$("#merchCustId").trigger("chosen:updated");
		});
	});
	$("#editorgid").change(function(){
		var orgid =$("#editorgid").val();
		$('#editreginId').attr("disabled",false); 
		$('#editmerchCustId').attr("disabled",false); 
		$.get('Org/regin?orgid='+orgid, function(res){
			if(!res.rows){
				return;
			}
			var html = '<option value=""></option>';
			$.each(res.rows, function(i, val){
				html += '<option value="'+val.id+'">'+val.name+'</option>';
			});
			$("#editreginId").html(html);
		});
		$.get('customer/sku/customers?orgid='+orgid, function(res){
			if(!res.rows){
				return;
			}
			var html = '<option value="">&nbsp;</option>';
			$.each(res.rows, function(i, val){
				html += '<option value="'+val.id+'">'+val.name+'</option>';
			});
			$("#editmerchCustId").html(html);
			$("#editmerchCustId").trigger("chosen:updated");
		});
	});
	$("#bDate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
	$("#eDate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
	$("#importbDate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
	$("#importeDate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
	$("#editbDate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
	$("#editeDate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
	$.validator.addMethod("Edate",function(value,element){
		function p(s) {
    	    return s < 10 ? '0' + s: s;
    	}
		// 当前时间
		var nowDate = new Date();
    	var year = nowDate.getFullYear();
    	var month=nowDate.getMonth()+1;
    	var date=nowDate.getDate(); 
    	var nowtime=year+'/'+p(month)+"/"+p(date);
    	var timestamp = Date.parse(new Date(nowtime));
    	timestamp = timestamp / 1000;
    	// 录入截止时间
    	var endtime = $("#eDate").val();
    	var endtimetemp = Date.parse(new Date(endtime));
    		endtimetemp = endtimetemp/1000;
    	var begintime = $("#bDate").val();
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
	$.validator.addMethod("importEdate",function(value,element){
		function p(s) {
    	    return s < 10 ? '0' + s: s;
    	}
		// 当前时间
		var nowDate = new Date();
    	var year = nowDate.getFullYear();
    	var month=nowDate.getMonth()+1;
    	var date=nowDate.getDate(); 
    	var nowtime=year+'/'+p(month)+"/"+p(date);
    	var timestamp = Date.parse(new Date(nowtime));
    	timestamp = timestamp / 1000;
    	// 录入截止时间
    	var endtime = $("#importeDate").val();
    	var endtimetemp = Date.parse(new Date(endtime));
    		endtimetemp = endtimetemp/1000;
    	var begintime = $("#importbDate").val();
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
	$.validator.addMethod("editEdate",function(value,element){
		function p(s) {
    	    return s < 10 ? '0' + s: s;
    	}
		// 当前时间
		var nowDate = new Date();
    	var year = nowDate.getFullYear();
    	var month=nowDate.getMonth()+1;
    	var date=nowDate.getDate(); 
    	var nowtime=year+'/'+p(month)+"/"+p(date);
    	var timestamp = Date.parse(new Date(nowtime));
    	timestamp = timestamp / 1000;
    	// 录入截止时间
    	var endtime = $("#editeDate").val();
    	var endtimetemp = Date.parse(new Date(endtime));
    		endtimetemp = endtimetemp/1000;
    	var begintime = $("#editbDate").val();
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
	
	var addValidator = addValidate("#addBatchMaintainFrom");
	var importValidator=importValidate("#importForm");
	var editValidator = editValidate("#editBatchMaintainFrom");
    $("#addBatchMaintainFrom").ajaxForm({
		target : '#btn-add-save', // target element(s) to be updated with server
		// response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			if(!$("#orgId").val() && !$("#reginId").val() && !$("#areaId").val() && 
					!$("#brand").val() && !$("#merchCustId").val()&& !$("#materialId").val()){
				$.messager.alert("错误提示","团队，大区，地区，品牌，客户，物料必须选择一个");
				return false;
			}
			var result = addValidator.valid();
			return result;
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("新增录入信息失败",responseText.errorMessage);
            }else{
            	$('#reginId').attr("disabled",true); 
        		$('#merchCustId').attr("disabled",true); 
            	$("#addModal").modal("hide");
            	$("#batchMaintain").bootstrapTable("refresh")
            }
		}
	});
    $("#editBatchMaintainFrom").ajaxForm({
		target : '#btn-edit-save', 
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			if(!$("#editorgid").val() && !$("#editreginId").val() && !$("#editareaId").val() && 
					!$("#editbrand").val() && !$("#editmerchCustId").val()&& !$("#editmaterialId").val()){
				$.messager.alert("错误提示","团队，大区，地区，品牌，客户，物料必须选择一个");
				return false;
			}
			var result = editValidator.valid();
			return result;
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("新增录入信息失败",responseText.errorMessage);
            }else{
            	$('#editreginId').attr("disabled",true); 
        		$('#editmerchCustId').attr("disabled",true); 
            	$("#editModal").modal("hide");
            	$("#batchMaintain").bootstrapTable("refresh")
            }
		}
	});
    $("#importForm").ajaxForm({
		target : '#btn-import', // target element(s) to be updated with server
		// response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			buttonWait($('#btn-import'), true);
			return importValidator.valid();
		},
		success : function(res, statusText, xhr, $form) {
			buttonWait($('#btn-import'), false);
			if (typeof res.errorCode == 'undefined' || res.errorCode != '-1') {
				$('#batchMaintain').bootstrapTable('refresh');
				$("#importModal").modal('hide');
			} else {
				$.messager.alert("提示", res.errorMessage? res.errorMessage:"上传失败");
			}
		}
	});
    $(".btn-edit").bind("click", doEdit);
    $(".btn-del").bind("click", doDel);
    $("#searchButton").bind("click", doSearch);
    $("#auditButton").bind("click", doAudit);
    $("#btn-detail").bind("click", doDetail);
});
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
function addValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			bDate:"required",
			eDate:{
				required:true,
				Edate:true
			},
			price:{
				required:true,
				price :true
			},
			type:"required",
			adjustDirection:"required"
		}
	});
	return validator;
}
function importValidate(formId){
	var validator = $(formId).validate({
		rules : {
			importbDate:"required",
			importeDate:{
				required:true,
				importEdate:true
			},
			file:"required"
		}
	});
	return validator;
}
function editValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			bDate:"required",
			eDate:{
				required:true,
				editEdate:true
			},
			price:{
				required:true,
				price :true
			},
			type:"required",
			editadjustDirection:"required"
		}
	});
	return validator;
}
function doDel(){
	var rows = $("#batchMaintain").bootstrapTable("getSelections");
	if(rows && rows.length ==1){
    	if(rows[0].states==1||rows[0].states==4){
    		$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
    			$.post("customer/sku/del",{"id":rows[0].id},function(){$("#batchMaintain").bootstrapTable("refresh");})
    		});    		
    	}else{
    		 $.messager.alert("提示", "请选择编辑或审批驳回的记录!");
    	}
    }else{
        $.messager.alert("提示", "请选择要删除的记录!");
    }
}
function doDetail(){
	var rows = $("#batchMaintain").bootstrapTable("getSelections");
	if(rows && rows.length ==1){
		window.location.href = "customer/sku/merchPriceBatchMaintainDetail.html?id="+rows[0].id;
	}else{
        $.messager.alert("提示", "请选择记录!");
    }
}
function doEdit(){
	var rows = $("#batchMaintain").bootstrapTable("getSelections");
	if(rows && rows.length ==1 && rows.type !='3'){
		if(rows[0].states === '1' || rows[0].states === '4'){// 1编辑状态 4审批驳回
			if(rows[0].orgid){
				$('#editreginId').attr("disabled",false); 
				$('#editmerchCustId').attr("disabled",false); 
				$.get('Org/regin?orgid='+rows[0].orgid, function(res){
					if(!res.rows){
						return;
					}
					var html = '<option value=""></option>';
					$.each(res.rows, function(i, val){
						html += '<option value="'+val.id+'">'+val.name+'</option>';
					});
					$("#editreginId").html(html);
					$("#editreginId").val(rows[0].reginId);
				});
				$.get('customer/sku/customers?orgid='+rows[0].orgid, function(res){
					if(!res.rows){
						return;
					}
					var html = '<option value="">&nbsp;</option>';
					$.each(res.rows, function(i, val){
						html += '<option value="'+val.id+'">'+val.name+'</option>';
					});
					$("#editmerchCustId").html(html);
					$("#editmerchCustId").trigger("chosen:updated");
					$("#editmerchCustId").val(rows[0].merchCustId);
					$("#editmerchCustId").trigger("chosen:updated");
				});
			}
			autoEdit(rows[0]);
			$("#editprice").val(rows[0].price/100)
			$("#editModal").modal("show")
		}else{
			 $.messager.alert("提示", "请选择编辑或者审批驳回的记录!");
		}
	}else{
        $.messager.alert("提示", "请选择出导入外编辑或者审批驳回的记录!");
    }
}
function doSearch(){
	var url = constructUrl();
	$("#batchMaintain").bootstrapTable('refresh', {
		'url' : url
	});
}
function constructUrl(){
	var url = "customer/sku/batchMaintainList?"+getSearchParams();
	return url;
}
function getSearchParams(){
	var custname = $("#scust").val();
	var orgid =$("#sorg").val();
	var reginId =$("#sregin").val();
	var areaId =$("#sarea").val();
	var brand =$("#sbrand").val();
	var materialId =$("#smateiralId").val();
	var param = "custname=" + custname+ "&orgid="+orgid+"&reginId="+reginId+"&areaId="+areaId+"&materialId="+materialId+"&brand="+brand;
	return param;
}
function doAudit(){
	var rows = $("#batchMaintain").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
    	if(rows[0].states=='1' || rows[0].states=='4'){
    		$.messager.confirm("警告", "您确认要提交记录吗?", function() {
    			$.post("customer/sku/audit",{"id":rows[0].id,"states":"2"},function(){$("#batchMaintain").bootstrapTable("refresh");})
    		});    		
    	}else{
    		 $.messager.alert("提示", "只有编辑或者审批驳回状态的可提交!");
    	}
    }else{
        $.messager.alert("提示", "请选择要提交的记录!");
    }
}
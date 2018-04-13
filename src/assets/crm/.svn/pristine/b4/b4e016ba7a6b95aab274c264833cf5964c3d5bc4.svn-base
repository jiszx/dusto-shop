
var policytypedata;
var materialdata;
var policyheaderid ;
var verification =$("#verification").val();
var type;
var custtreeObj=null;
var treeObj=null;
var areadata=null;
var custdata=null;
$(function() {
	initTree();
	$('.tokenfield').tokenfield();
	initpolicyarea();
	$("#btn-add").bind("click", openModal);
	$("#btn-del").bind("click",doDel);
	$("#btn-edit").bind("click",openEditModal);
	initTable() ;
	var addCXValidator = initValidate("#addDZPromotionForm");
	var editCXValidator = initValidate("#editDZPromotionForm");
	var addDZValidator = initValidate("#addDZPromotionForm");
	var editDZValidator = initValidate("#editDZPromotionForm");
	var addZKValidator = initValidate("#addZKPromotionForm");
	var editZKValidator = initValidate("#editZKPromotionForm");
	var addJHValidator = initValidate("#addJHPromotionForm");
	var editJHValidator = initValidate("#editJHPromotionForm");
	$("#btn_choose_area").bind("click",doAddArea);
	$("#btn_choose_cust").bind("click",doAddCust);
	$("#addPolicyArea").bind("click",doAddPolicyArea);
	$("#addPolicyCust").bind("click",doAddPolicyCust);
	/*//获取销售政策类型
	var url="salePolicy/policytype";
	$.get(url,function(data){
		if(data.rows && data.rows.length>0){
			policytypedata =data.rows;
			for(var i=0;i<data.rows.length;i++){
				if($("#spolicyType").val() ==data.rows[i].id){
					$("#policyType").append("<option value='"+data.rows[i].id+"'>"+data.rows[i].name+"</option>");
				}
			}
		}
	});*/
	
		//获取该销售组织下的所有物料信息
		var orgid =$("#sorganizationId").val();
		var  materialurl ="salePolicy/policymaterial?orgid="+orgid;
		$.get(materialurl,function(data){
			if(data.rows && data.rows.length>0){
				materialdata=data.rows;
				$("#dzmaterialId").append("<option></option>");
				$("#cxmaterialId").append("<option></option>");
				$("#dzdiscount").append("<option></option>");
				$("#zkmaterialId").append("<option></option>");
				$("#jhmaterialId").append("<option></option>");
				for (var i=0;i<data.rows.length;i++){
					$("#dzmaterialId").append("<option value='"+data.rows[i].sapId+"'>"+data.rows[i].sku+"</option>")
					$("#cxmaterialId").append("<option value='"+data.rows[i].sapId+"'>"+data.rows[i].sku+"</option>")
					$("#dzdiscount").append("<option value='"+data.rows[i].sapId+"'>"+data.rows[i].sku+"</option>")
					$("#zkmaterialId").append("<option value='"+data.rows[i].sapId+"'>"+data.rows[i].sku+"</option>")
					$("#jhmaterialId").append("<option value='"+data.rows[i].sapId+"'>"+data.rows[i].sku+"</option>")
					$("#editcxmaterialId").append("<option value='"+data.rows[i].sapId+"'>"+data.rows[i].sku+"</option>")
					$("#editdzmaterialId").append("<option value='"+data.rows[i].sapId+"'>"+data.rows[i].sku+"</option>")
					$("#editzkmaterialId").append("<option value='"+data.rows[i].sapId+"'>"+data.rows[i].sku+"</option>")
					$("#editjhmaterialId").append("<option value='"+data.rows[i].sapId+"'>"+data.rows[i].sku+"</option>")
					$("#editdzdiscount").append("<option value='"+data.rows[i].sapId+"'>"+data.rows[i].sku+"</option>")
				}
			}
		});
		$("#cxmaterialId").change(function(){
			var cxmaterialId =$("#cxmaterialId").val();
			for(var i=0;i<materialdata.length;i++){
				if(cxmaterialId==materialdata[i].sapId){
					$("#cxunit").val(materialdata[i].unit);
					$("#cxprice").val(materialdata[i].price);
				}
			}
		})
		$("#editcxmaterialId").change(function(){
			var cxmaterialId =$("#editcxmaterialId").val();
			for(var i=0;i<materialdata.length;i++){
				if(cxmaterialId==materialdata[i].sapId){
					$("#editcxunit").val(materialdata[i].unit);
					$("#editcxprice").val(materialdata[i].price);
				}
			}
		})
	$("#dzmaterialId").change(function(){
		var dzmaterialId =$("#dzmaterialId").val();
		for(var i=0;i<materialdata.length;i++){
			if(dzmaterialId==materialdata[i].sapId){
				$("#dzunit").val(materialdata[i].unit);
				$("#dzprice").val(materialdata[i].price);
			}
		}
	});
	$("#zkmaterialId").change(function(){
		var zkmaterialId =$("#zkmaterialId").val();
		for(var i=0;i<materialdata.length;i++){
			if(zkmaterialId==materialdata[i].sapId){
				$("#zkunit").val(materialdata[i].unit);
				$("#zkprice").val(materialdata[i].price);
			}
		}
	});
	$("#jhmaterialId").change(function(){
		var jhmaterialId =$("#jhmaterialId").val();
		for(var i=0;i<materialdata.length;i++){
			if(jhmaterialId==materialdata[i].sapId){
				$("#jhunit").val(materialdata[i].unit);
				$("#jhprice").val(materialdata[i].price);
			}
		}
	});
	
	$("#editdzmaterialId").change(function(){
		var editdzmaterialId =$("#editdzmaterialId").val();
		for(var i=0;i<materialdata.length;i++){
			if(editdzmaterialId==materialdata[i].sapId){
				$("#editdzunit").val(materialdata[i].unit);
				$("#editdzprice").val(materialdata[i].price);
			}
		}
	});
	$("#addCXPromotionForm").ajaxForm({
		target : '.btn-cx-save', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return addCXValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("添加失败",responseText.errorMessage);
            }else{
            	$.messager.popup("新增成功");
            	$("#policysku-table").bootstrapTable("refresh");
            }
			$("#cxPolicyModal").modal("hide");
		}
	});
	$("#editCXPromotionForm").ajaxForm({
		target : '.btn-cx-edit', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return editCXValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("添加失败",responseText.errorMessage);
            }else{
            	$.messager.popup("修改成功");
            	$("#policysku-table").bootstrapTable("refresh");
            }
			$("#editcxPolicyModal").modal("hide");
		}
	});
	$("#addDZPromotionForm").ajaxForm({
		target : '.btn-dz-save', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return addDZValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("添加失败",responseText.errorMessage);
            }else{
            	$.messager.popup("新增成功");
            	$("#policysku-table").bootstrapTable("refresh");
            }
			$("#dzPolicyModal").modal("hide");
		}
	});
	$("#editDZPromotionForm").ajaxForm({
		target : '.btn-dz-edit', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return editDZValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("添加失败",responseText.errorMessage);
            }else{
            	$.messager.popup("修改成功");
            	$("#policysku-table").bootstrapTable("refresh");
            }
			$("#editdzPolicyModal").modal("hide");
		}
	});
	$("#addZKPromotionForm").ajaxForm({
		target : '.btn-zk-save', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return addZKValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("添加失败",responseText.errorMessage);
            }else{
            	$.messager.popup("新增成功");
            	$("#policysku-table").bootstrapTable("refresh");
            }
			$("#zkPolicyModal").modal("hide");
		}
	});
	$("#editZKPromotionForm").ajaxForm({
		target : '.btn-zk-edit', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return editZKValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("添加失败",responseText.errorMessage);
            }else{
            	$.messager.popup("修改成功");
            	$("#policysku-table").bootstrapTable("refresh");
            }
			$("#editzkPolicyModal").modal("hide");
		}
	});
	$("#addJHPromotionForm").ajaxForm({
		target : '.btn-jh-save', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return addJHValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("添加失败",responseText.errorMessage);
            }else{
            	$.messager.popup("新增成功");
            	$("#policysku-table").bootstrapTable("refresh");
            }
			$("#jhPolicyModal").modal("hide");
		}
	});
	$("#editJHPromotionForm").ajaxForm({
		target : '.btn-jh-edit', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return editJHValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("添加失败",responseText.errorMessage);
            }else{
            	$.messager.popup("新增成功");
            	$("#policysku-table").bootstrapTable("refresh");
            }
			$("#editjhPolicyModal").modal("hide");
		}
	});
});

$(document).ready(function() {
	$("#startTime").datetimepicker({
		format : 'yyyy-mm-dd',
		minView : 2,
		language : 'zh-CN'
	});
	$("#endTime").datetimepicker({
		format : 'yyyy-mm-dd',
		minView : 2,
		language : 'zh-CN'
	});
});
function initTable() {
	$('#policysku-table').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'salePolicy/policyLines.json?id='+$("#policyid").val(),
		// data:data,
		cache : false,
		//toolbar : "#dictTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		//sortName : "colName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : 'ck',
			checkbox : true,
			rowspan : 1,
			align : 'center',
			valign : 'middle'
		}, {
			title : '编号',
			field : 'id',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '核销方式',
			field : 'verification',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			formatter : function(value) {
				return getverificationType(value + "");
			}
		}, {
			title : '订单SKU',
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
			valign : 'middle'
		}, {
			title : '单位',
			field : 'unit',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true,
		}, {
			title : '单价(元)',
			field : 'price',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			sortable : true

		}, {
			title : '目标',
			field : 'primary',
			rowspan : 1,
			align : 'center',
			valign : 'middle'
		}, {
			title : '政策奖励bi',
			field : 'discount',
			rowspan : 1,
			align : 'center',
			valign : 'middle',
			visible : false
		}, {
			title : '政策奖励',
			field : 'discountsku',
			rowspan : 1,
			align : 'center',
			valign : 'middle'
		}, {
			title : '政策奖励力度',
			field : 'discountIntensity',
			rowspan : 1,
			align : 'center',
			valign : 'middle'
		}, {
			title : '政策奖励限制',
			field : 'limit',
			rowspan : 1,
			align : 'center',
			valign : 'middle'
		} ]
	});
    if(verification=='1'||verification=='5'){
    	$('#policysku-table').bootstrapTable('showColumn', 'primary');
    	$('#policysku-table').bootstrapTable('showColumn', 'discountsku');
    	$('#policysku-table').bootstrapTable('showColumn', 'limit');
	}else{
		$('#policysku-table').bootstrapTable('hideColumn', 'primary');
		$('#policysku-table').bootstrapTable('hideColumn', 'discountsku');
		$('#policysku-table').bootstrapTable('hideColumn', 'limit');
	}
}

//时间验证
function validate() {
	var bdate = $('#bdate').val();
	var edate = $('#eDate').val();
	if (edate < bdate) {
		$.messager.alert("提示", "政策结束时间必须大于开始时间");
	}
}

function dosave(value) {
	type =value;
	$("#policyheaderForm").submit();
	window.location.href="salePolicy/index.html";
}

function openModal() {
	var value = verification;
	if (value == '1') {
		$("#dzPolicyModal").modal("show");
	} else if (value == '2') {
		$("#zkPolicyModal").modal("show");
	} else if (value == '3'|| value=='4') {
		$("#jhPolicyModal").modal("show");
	}else if (value == '5') {
		 //获取促销品物料
		 var promotionurl ="promotion/material/invlist?orgid="+$("#sorganizationId").val();
		 $.get(promotionurl,function(data){
			if(data.rows && data.rows.length>0){
				promotiondata = data.rows;
				$("#cxdiscount").append("<option></option>");
				for(var i=0;i<data.rows.length;i++){
					$("#cxdiscount").append("<option value='"+data.rows[i].promotionId+"'>"+data.rows[i].materialname+"</option>")
				}
				$("#cxPolicyModal").modal("show");
			} 
		 });
	}
}
function openEditModal(){
	var rows = $("#policysku-table").bootstrapTable("getSelections");
	if(rows && rows.length==1){
		var value = verification;
		if (value == '1') {
			autoLineEdit(rows[0],'dz');
			$("#editdzPolicyModal").modal("show");
		} else if (value == '2') {
			autoLineEdit(rows[0],'zk');
			$("#editzkPolicyModal").modal("show");
		} else if (value == '3'|| value=='4') {
			autoLineEdit(rows[0],'jh');
			$("#editjhPolicyModal").modal("show");
		}else if (value == '5') {
			 var promotionurl ="promotion/material/invlist?orgid="+$("#sorganizationId").val();
			 $.get(promotionurl,function(data){
				if(data.rows && data.rows.length>0){
					//promotiondata = data.rows;
					$("#editcxdiscount").append("<option></option>");
					for(var i=0;i<data.rows.length;i++){
						$("#editcxdiscount").append("<option value='"+data.rows[i].promotionId+"'>"+data.rows[i].materialname+"</option>")
					}
					autoLineEdit(rows[0],'cx');
					$("#editcxPolicyModal").modal("show");
				} 
			 });
		}
	}else{
		$.messager.alert("提示", "请选择要修改的记录!");
	}
	
}

/**
 * 将json对象回填
 * @param Obj
 */
function autoLineEdit(Obj,type){
    for(var i in Obj){
        if(Obj[i]==null){
            continue;
        }
        $("#edit"+type+i).val(Obj[i]+"");
    }
}
/**
 * 验证数据
 * 
 * @param formId
 * @returns
 */
function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			materialId : "required",
			discount : "required",
			primary : {
				required : true,
				number : true
			},
			discountIntensity : {
				required : true,
				number : true
			},
			limit : {
				required : true,
				number : true
			}
		}
	});
	return validator;
}
function initTree(){
	var setting = {
    		check : {
    	        enable : true,
    	        chkboxType: { "Y": "", "N": "" }
    	    },
    	    view : {
    	        dblClickExpand : false,
    	        showLine : true,
    	        selectedMulti : false
    	    },
    	    async : {
    	        dataType : "json",
    	        enable : true,
    	        type : "post",
    	        url : "salePolicy/areaList?orgid="+$("#sorganizationId").val(),
    	        dataFilter: function(treeId, parentNode, responseData){
    	            return responseData.data;
    	        }
    	    },
    	    data : {
    	        key : {
    	            name : "name"
    	        },
    	        simpleData : {
    	            enable : true,
    	            idKey : "id",
    	            pIdKey : "pid",
    	            rootPId : "0"
    	        }
    	    }
    };
    var t = $("#areaTrea");
    t = $.fn.zTree.init(t, setting);
    treeObj = t;
    var data=treeObj.getNodeByParam("id", 'T010101', null);
}
function CustTree(){
	var orgids=$('#agentArea').val();
	if(!orgids){
		orgids=$("#policyareas").val();
	}
	var setting = {
    		check : {
    	        enable : true,
    	        chkboxType: { "Y": "", "N": "" }
    	    },
    	    view : {
    	        dblClickExpand : false,
    	        showLine : true,
    	        selectedMulti : false
    	    },
    	    async : {
    	        dataType : "json",
    	        enable : true,
    	        type : "post",
    	        url : "salePolicy/areaCust?orgids="+orgids,
    	        dataFilter: function(treeId, parentNode, responseData){
    	            return responseData.data;
    	        }
    	    },
    	    data : {
    	        key : {
    	            name : "name"
    	        },
    	        simpleData : {
    	            enable : true,
    	            idKey : "id",
    	            pIdKey : "pid",
    	            rootPId : "0"
    	        }
    	    }
    };
    var t = $("#areaCust");
    t = $.fn.zTree.init(t, setting);
    custtreeObj = t;
}
function doAddPolicyArea(){
	var orgid = $("#sorganizationId").val();
	if(!orgid){
		$.messager.alert("提示", "请选择申请部门");
		return ;
	}
	$.each(areadata,function(n,value){
		var node = treeObj.getNodeByParam("id", value.id, null);
		treeObj.checkNode(node, true, true);
	});
	$("#chooseArea").modal("show");
}
function doAddPolicyCust(){
	var orgid = $("#sorganizationId").val();
	if(!orgid){
		$.messager.alert("提示", "请选择申请部门");
		return ;
	}
	CustTree();
	$.each(custdata,function(n,value){
		var node = custtreeObj.getNodeByParam("id", value.id, null);
		custtreeObj.checkNode(node, true, true);
	});
	$("#chooseCust").modal("show");
}

function doAddArea(){
	var checks = treeObj.getCheckedNodes(true);
	if(checks || checks.length > 0){
		var tokenArray = new Array();
		for(var i=0;i<checks.length;i++){
			tokenArray.push({ value: checks[i].id, label: checks[i].name })
			$('#agentArea').tokenfield('setTokens',tokenArray);
		}
	}
	$("#chooseArea").modal("hide");
	var policyarae=$('#agentArea').val();
	if(policyarae){
		$("#custs").css("display","block");
	}
}
function doAddCust(){
	var checks = custtreeObj.getCheckedNodes(true);
	if(checks || checks.length > 0){
		var tokenArray = new Array();
		for(var i=0;i<checks.length;i++){
			tokenArray.push({ value: checks[i].id, label: checks[i].name })
			$('#agentCust').tokenfield('setTokens',tokenArray);
		}
	}
	$("#chooseCust").modal("hide");
}
function initpolicyarea(){
	var tokenArray = new Array();
	var tokenArray2 = new Array();
	//获取组织数据
	var areaurl="salePolicy/edit/policyarea?id="+$("#policyid").val();
	$.get(areaurl,function(data){
		if(data.rows && data.rows.length>0){
			areadata =data.rows;
			for(var i=0;i<data.rows.length;i++){
				tokenArray.push({ value: data.rows[i].id, label: data.rows[i].name })
			}
			$('#agentArea').tokenfield('setTokens',tokenArray);
		}
	})
	//客户数据
	var custurl="salePolicy/edit/policycustarea?id="+$("#policyid").val();
	$.get(custurl,function(data){
		if(data.rows && data.rows.length>0){
			$("#custs").css("display","block");
			custdata =data.rows;
			for(var i=0;i<data.rows.length;i++){
				tokenArray2.push({ value: data.rows[i].id, label: data.rows[i].name })
			}
			$('#agentCust').tokenfield('setTokens',tokenArray2);
		}
	})
}

function doDel(){
	var rows = $("#policysku-table").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
			$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
				$.post("salePolicy/delPolicyLine", {
					"id" : rows[0].id
				}, function(data) {
					if(data.data=="200"){
						$.messager.popup("删除成功!");
						$("#policysku-table").bootstrapTable("refresh");
					}else{
						$.messager.alert("提示", "删除失败!");
					}
				})
			});
	} else {
		$.messager.alert("提示", "请选择要删除的记录!");
	}
}
var jsondata = {};
var dzjsonarry = [];
var zkjsonarry = [];
var jhjsonarry = [];
var policy = $('#policycontent');
var policytypedata;
var $table = $('#policysku-table');
var $remove = $('#del-bstb-row');
var policyverification;
var materialdata;
var promotiondata;
var dataid = 0;
var policyheaderid ;
var type;
$(function() {
	$('#dzmaterialId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	$('#dzdiscount').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	
	$('#zkmaterialId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	
	$('#jhmaterialId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	
	$('#cxmaterialId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	$('.tokenfield').tokenfield();
	$("#btn-add").bind("click", openModal);
	initTable() ;
	var addDZValidator = initValidate("#addDZPromotionForm");
	var addZKValidator = initValidate("#addZKPromotionForm");
	var addJHValidator = initValidate("#addJHPromotionForm");
	$(".btn-dz-save").bind("click", doDZsave);
	$(".btn-zk-save").bind("click", doZKsave);
	$(".btn-jh-save").bind("click", doJHsave);
	$(".btn-cx-save").bind("click", doCXsave);
	$("#btn_choose_area").bind("click",doAddArea);
	$("#btn_choose_cust").bind("click",doAddCust);
	$("#addPolicyArea").bind("click",doAddPolicyArea);
	$("#addPolicyCust").bind("click",doAddPolicyCust);
	//获取销售政策类型
	var url="salePolicy/policytype";
	$.get(url,function(data){
		if(data.rows && data.rows.length>0){
			$("#policyType").append("<option></option");
			policytypedata =data.rows;
			for(var i=0;i<data.rows.length;i++){
				$("#policyType").append("<option value='"+data.rows[i].id+"'>"+data.rows[i].name+"</option>");
			}
		}
	});
	/*//获取申请可选销售组织
	var orgurl="org/powerOrg";
	$.get(orgurl,function(data){
		if(data.rows && data.rows.length>0){
			$("#organizationId").append("<option></option>");
			for(var i=0;i<data.rows.length;i++){
				$("#organizationId").append("<option value='"+data.rows[i].orgid+"'>"+data.rows[i].orgname+"</option>")
			}
		}
	})*/
	$("#policyType").change(function(){
		var policyType =$("#policyType").val();
		for(var i=0;i<policytypedata.length;i++){
			if(policyType==policytypedata[i].id){
				$table.bootstrapTable('removeAll');
				dataid=0;
				verification=policytypedata[i].verification;
			    if(verification=='1' || verification =='5'){
					$table.bootstrapTable('showColumn', 'primary');
					$table.bootstrapTable('showColumn', 'discountsku');
					$table.bootstrapTable('showColumn', 'limit');
				}else{
					$table.bootstrapTable('hideColumn', 'primary');
					$table.bootstrapTable('hideColumn', 'discountsku');
					$table.bootstrapTable('hideColumn', 'limit');
				}
				$("#policysku").css('display','none');
			}
			if(policyType==policytypedata[i].id && policytypedata[i].type=='1'){
				$("#policysku").css('display','block');
			}
		}
	});
	/*$('#dzmaterialId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true
	});*/
	$("#organizationId").change(function(){
		//获取该销售组织下的所有物料信息
		var orgid =$("#organizationId").val();
		var  materialurl ="salePolicy/policymaterial?orgid="+orgid;
		$.get(materialurl,function(data){
			if(data.rows && data.rows.length>0){
				$("#dzmaterialId").empty();
				$("#dzdiscount").empty();
				$("#zkmaterialId").empty();
				$("#cxmaterialId").empty();
				$("#jhmaterialId").empty();
				$("#dzmaterialId").trigger("chosen:updated");
				$("#dzdiscount").trigger("chosen:updated");
				$("#zkmaterialId").trigger("chosen:updated");
				$("#jhmaterialId").trigger("chosen:updated");
				$("#cxmaterialId").trigger("chosen:updated");
				materialdata=data.rows;
				$("#dzmaterialId").append("<option></option>");
				$("#cxmaterialId").append("<option></option>");
				$("#dzdiscount").append("<option></option>");
				$("#zkmaterialId").append("<option></option>");
				$("#jhmaterialId").append("<option></option>");
				for (var i=0;i<data.rows.length;i++){
					$("#dzmaterialId").append("<option value='"+data.rows[i].sapId+"'>"+data.rows[i].sapId+'-'+data.rows[i].sku+"</option>")
					$("#dzdiscount").append("<option value='"+data.rows[i].sapId+"'>"+data.rows[i].sapId+'-'+data.rows[i].sku+"</option>")
					$("#zkmaterialId").append("<option value='"+data.rows[i].sapId+"'>"+data.rows[i].sapId+'-'+data.rows[i].sku+"</option>")
					$("#jhmaterialId").append("<option value='"+data.rows[i].sapId+"'>"+data.rows[i].sapId+'-'+data.rows[i].sku+"</option>")
					$("#cxmaterialId").append("<option value='"+data.rows[i].sapId+"'>"+data.rows[i].sapId+'-'+data.rows[i].sku+"</option>")
				}
				$("#dzmaterialId").trigger("chosen:updated");
				$("#dzdiscount").trigger("chosen:updated");
				$("#zkmaterialId").trigger("chosen:updated");
				$("#jhmaterialId").trigger("chosen:updated");
				$("#cxmaterialId").trigger("chosen:updated");
			}
		});
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
	
	$("#policyheaderForm").ajaxForm({
		//target : '#btn-edit-save', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return true;
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("添加失败",responseText.errorMessage);
            }else{
            	policyheaderid=responseText.data;
            	var data = $table.bootstrapTable('getData');
            	var url = "salePolicy/lines/add"
            		$.post(url, {
            			"headerid" : policyheaderid,
            			"lines" : JSON.stringify(data),
            			"orgids": $('#agentArea').val(),
            			"custs" : $("#agentCust").val()
            		}, function(res) {
            			if (res.data == '500') {
            				$.messager.alert("提示", "保存出错!");
            			}else if(res.data=='200'){
            				$.messager.popup("保存成功!");
            				if(type ==2){
            					//提交审批
            					$.post("salePolicy/submit", {
                        			"headerid" : policyheaderid,
                        			"states" : '2'
                        		}, function(res) {
                        			$.messager.popup("提交成功!");
                        			window.location.href="salePolicy/index.html";
                        		});
            				}else{
            					window.location.href="salePolicy/index.html";
            				}
            				
            			}
            		});
            }
		}
	});
});
window.operateEvents = {
		'click .remove' : function(e, value, row, index) {
			dataid=dataid-1;
			$table.bootstrapTable('remove', {
				field : 'id',
				values : [ row.id ]
			});
		}
};
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
function initTable() {
	$table
			.bootstrapTable({
				// url : 'static/table/new/data.json',
				//data : data,
				idField : 'id',
				uniqueId : 'id',
				columns : [
						{
							field : 'state',
							checkbox : true,
							rowspan : 1,
							align : 'center',
							valign : 'middle'
						},
						{
							title : '编号',
							field : 'id',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							visible : false
						},
						{
							title : '核销方式',
							field : 'verification',
							rowspan : 1,
							align : 'center',
							valign : 'middle'
						},
						{
							title : '订单SKU',
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
							valign : 'middle'
							/*,visible : false*/
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
							title : '单价(元)',
							field : 'price',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							sortable : true
							
						},
						{
							title : '目标',
							field : 'primary',
							rowspan : 1,
							align : 'center',
							valign : 'middle'
						},
						{
							title : '政策奖励bi',
							field : 'discount',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							visible : false
						},
						{
							title : '政策奖励',
							field : 'discountsku',
							rowspan : 1,
							align : 'center',
							valign : 'middle'
						},
						{
							title : '政策奖励力度',
							field : 'discountIntensity',
							rowspan : 1,
							align : 'center',
							valign : 'middle'
						},
						{
							title : '政策奖励限制',
							field : 'limit',
							rowspan : 1,
							align : 'center',
							valign : 'middle'
						}, {
							field : 'operate',
							width : 40,
							title : '删除',
							align : 'center',
							events : 'operateEvents',
							formatter : operateFormatter
						} ]
			});

	// sometimes footer render error.
	setTimeout(function() {
		$table.bootstrapTable('resetView');
	}, 200);
	$table.on('check.bs.table uncheck.bs.table '
			+ 'check-all.bs.table uncheck-all.bs.table', function() {
		$remove
				.prop('disabled',
						!$table.bootstrapTable('getSelections').length);

		// save your data, here just save the current page
		selections = getIdSelections();
		// push or splice the selections if you want to save all data selections
	});
	$table.on('expand-row.bs.table', function(e, index, row, $detail) {
		if (index % 2 == 1) {
			$detail.html('Loading from ajax request...');
			$.get('LICENSE', function(res) {
				$detail.html(res.replace(/\n/g, '<br>'));
			});
		}
	});
	$table.on('all.bs.table', function(e, name, args) {
		console.log(name, args);
	});
	$remove.click(function() {
		var ids = getIdSelections();
		if (ids.length > 0) {
			$table.bootstrapTable('remove', {
				field : 'id',
				values : ids
			});
			$remove.prop('disabled', true);
		} else {
			alert("请选择要删除的行。");
		}

	});
	$(window).resize(function() {
		$table.bootstrapTable('resetView', {
		// height: getHeight()
		});
	});

}
function doAddPolicyArea(){
	var orgid = $("#organizationId").val();
	if(!orgid){
		$.messager.alert("提示", "请选择申请部门");
		return ;
	}
	initTree();
	$("#chooseArea").modal("show");
}
function doAddPolicyCust(){
	var orgid = $("#organizationId").val();
	if(!orgid){
		$.messager.alert("提示", "请选择申请部门");
		return ;
	}
	CustTree();
	$("#chooseCust").modal("show");
}
var treeObj=null;
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

var custtreeObj=null;
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
function addAgent(agent) {
	$table.bootstrapTable('insertRow', {
		index : 0,
		row : agent
	});
	dataid = dataid + 1;
}
function operateFormatter(value, row, index) {
	return [ '<a class="remove" href="javascript:;" title="删除本行">',
			'<i class="icon icon-trash"></i>', '</a>' ].join('');
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
    	        url : "salePolicy/areaList?orgid="+$("#organizationId").val(),
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
}
function CustTree(){
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
    	        url : "salePolicy/areaCust?orgids="+$('#agentArea').val(),
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
		if(verification=='5'){
		 var promotionurl ="promotion/material/invlist?orgid="+$("#organizationId").val();
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
// 添加搭赠
function doDZsave() {
	var addDZValidator = initValidate("#addDZPromotionForm");
	$("#addDZPromotionForm").submit();
	if(addDZValidator.valid()==false){
		return;
	}
	var materialId =$("#dzmaterialId").val();
	var sku = $('#dzmaterialId').find("option:selected").text();
	var discount = $('#dzdiscount').val();
	var discountsku = $('#dzdiscount').find("option:selected").text();
	var primary = $('#dzprimary').val();
	var discountIntensity = $('#dzdiscountIntensity').val();
	var limit = $('#dzlimit').val();
	var unit =$("#dzunit").val();
	var price =$("#dzprice").val();
	var verificationname=getverificationType(verification);
	var agentItem = {
			id : dataid,
			sku : sku,
			materialId : materialId,
			unit : unit,
			price : price,
			discount:discount,
			discountsku:discountsku,
			primary:primary,
			limit:limit,
			verification:verificationname,
			discountIntensity:discountIntensity
		}
		addAgent(agentItem);
	$("#dzPolicyModal").modal("hide");
}

// 添加价格折扣
function doZKsave() {
	var validator = initValidate("#addZKPromotionForm");
	$("#addZKPromotionForm").submit();
	if(validator.valid()==false){
		return;
	}
	var materialId =$("#zkmaterialId").val();
	var sku = $('#zkmaterialId').find("option:selected").text();
	var discount = verification;
	var discountIntensity = $('#zkdiscountIntensity').val()+"%";
	var unit =$("#zkunit").val();
	var price =$("#zkprice").val();
	var verificationname=getverificationType(verification);
	var agentItem = {
			id : dataid,
			sku : sku,
			materialId : materialId,
			unit : unit,
			price : price,
			discount:discount,
			verification:verificationname,
			discountIntensity:discountIntensity
		}
		addAgent(agentItem);
	$("#zkPolicyModal").modal("hide");
}

// 添加进货奖励
function doJHsave() {
	var validator = initValidate("#addJHPromotionForm");
	$("#addJHPromotionForm").submit();
	if(validator.valid()==false){
		return;
	}
	var materialId =$("#jhmaterialId").val();
	var sku = $('#jhmaterialId').find("option:selected").text();
	var discount = verification;
	var discountIntensity = $('#jhdiscountIntensity').val()+"%";
	var unit =$("#jhunit").val();
	var price =$("#jhprice").val();
	var verificationname=getverificationType(verification);
	var agentItem = {
			id : dataid,
			sku : sku,
			materialId : materialId,
			unit : unit,
			price : price,
			discount:discount,
			verification:verificationname,
			discountIntensity:discountIntensity
		}
		addAgent(agentItem);
	$("#jhPolicyModal").modal("hide");
}

//添加促销品
function doCXsave() {
	var validator = initValidate("#addcxPromotionForm");
	$("#addcxPromotionForm").submit();
	if(validator.valid()==false){
		return;
	}
	var materialId =$("#cxmaterialId").val();
	var sku = $('#cxmaterialId').find("option:selected").text();
	var discount = $('#cxdiscount').val();
	var discountsku = $('#cxdiscount').find("option:selected").text();
	var primary = $('#cxprimary').val();
	var discountIntensity = $('#cxdiscountIntensity').val();
	var limit = $('#cxlimit').val();
	var unit =$("#cxunit").val();
	var price =$("#cxprice").val();
	var verificationname=getverificationType(verification);
	var agentItem = {
			id : dataid,
			sku : sku,
			materialId : materialId,
			unit : unit,
			price : price,
			discount:discount,
			discountsku:discountsku,
			primary:primary,
			limit:limit,
			verification:verificationname,
			discountIntensity:discountIntensity
		}
		addAgent(agentItem);
	$("#cxPolicyModal").modal("hide");
}
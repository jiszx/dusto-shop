$(function(){
    $('#customerTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'customer/retailCustomer',
        cache: false,
        toolbar:"#customerTool",
        striped: true,
        pagination: true,
        searchOnEnterKey:true,
        sidePagination:"server",
        idField:"id",
        sortName:"colName",
        smartDisplay:true,
        pageSize: 10,
        pageList:["10","20","50","100"],
        search: false,
        showColumns: true,
        showRefresh: true,
        clickToSelect: true,
        singleSelect:true,
        columns: [{
            field: 'ck',
            title: '编号',
            radio:true
        }, {
            field: 'name',
            title: '客户名称',
            align: 'left'
        }, {
            field: 'pname',
            title: '所属配送商',
            align: 'left'
        }, {
            field: 'sapCustomerId',
            title: 'SAP客户编码',
            align: 'left',
            formatter : function(value,data) {
            	var containerId = "container"+data.id;
            	var container = $('<div>').attr("id",containerId);
            	if(typeof value == 'undefined' || $.trim(value) == ''){
            		$.ajax({
            	          type : "get",  
            	          url : "customer/feedback?customerId="+data.id,  
            	          success : function(result){
            	        	if(typeof result.data != 'undefined' && $.trim(result.data) != ''){
            	        		var alink = '<a href="javascript:void(0);" onclick="viewFeedback(\''+data.id+'\',\''+result.data+'\');">有接口消息</a>';
            	        		container.html(alink);
            	        		if($('#'+containerId)[0]){
            	        			$('#'+containerId).html(alink);
            	        		}else{
            	        			return container.prop("outerHTML");
            	        		}
                  			}
            	          }  
            	    });
            		return container.prop("outerHTML");
            	}else{
            		return value;
            	}
			}
        }, {
            field: 'createTs',
            title: '创建时间',
            align: 'left'
        }, {
            field: 'saleOrgName',
            title: '所属销售组织',
            align: 'left'
        }, {
            field: 'states',
            title: '客户状态',
            align: 'left',
            formatter:function(val){
            	if(val=='1'){
            		return '潜在客户';
            	}else if(val=='2'){
            		return '待审批客户';
            	}else if(val=='3'){
            		return '正式客户';
            	}else{
            		return '状态异常';
            	}
            }
        }]
    });
    var addValidator = initValidate("#addCustomerForm");
    var editValidator = initValidate("#editCustomerForm");
    var importValidator = initValidate("#importForm");
    
    $("#editBtn").bind("click",doEdit);
    $(".btn-del").bind("click",doDel);
    $("#searchBtn").bind("click",searchCustomer);
    $("#applyBtn").bind("click",apply);
    $("#detailBtn").bind("click",detail);
    $("#changeBtn").bind("click",doChange);
    $("#setPositionBtn").bind("click",searchPosition);
    $("#choosePosiBtn").bind("click",choosePosition);
    $("#exportBtn").bind("click", doExport);
    $("#importBtn").bind("click", doImport)
    
    $("#importForm").ajaxForm({
        target:'#btn-add',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit : function(formData, jqForm, options) {
        	return importValidator.valid();
		},
        success:function(responseText, statusText, xhr, $form){
        	if(responseText.errorCode != 0){
                $.messager.alert("新增信息失败",'无法导入模板');
            }else if(responseText.data==-1){
            	$.messager.alert("信息有误，请确认客户名，联系人，联系电话（2个），税号及营业执照号不为空");
            }else{
            	$("#importModal").modal("hide");
                $("#customerTable").bootstrapTable("refresh");
            }
        	$("#addPid").val("");
        	$("#addPid").trigger("chosen:updated");
        }
    });
    
    
    // 初始化配送商选择框
    $.get("customer/list?custType=2,7,70&status=3&type=1", function(res){
    	var html = "<option value=''>无</option>";
    	if(res && res.rows){
    		$.each(res.rows, function(i, val){
    			html += '<option value="'+val.id+'">'+val.name+'</option>';
    		});	
    		$("#addPid").html(html);
//    		$("#addPid").trigger("chosen:updated");
    	}
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
    
});

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
                alert("无法获取销售大区信息！");
            }
        }
    });
}

//查询客户
function searchCustomer(){
	var custname =$("#custname").val();
	var orgid =$("#salesOrgSelect").val();
	$("#customerTable").bootstrapTable("refresh",
		{
		'url' : 'customer/retailCustomer?orgid='+orgid+"&custname="+custname
		}
	);
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
				alert("无法获取业务省信息！");
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

function initValidate(formId){
    var validator = $(formId).validate({
        rules: {
            colName: "required",
            chooseVal: "required",
           /* showText: {required: true,minlength: 2},*/
            orders: {required: true, number: 5}
        }
    });
    return validator;
}
/**
 * 编辑按钮
 */
function doEdit(){
    var rows = $("#customerTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
    	if(rows[0].states != '1'){
        	$.messager.alert("提示", "客户状态不支持修改!");
        	return;
        }
        var editId = rows[0].id;
        window.location.href="customer/editRetail/"+editId+".html";
    }else{
        $.messager.alert("提示", "请选择要编辑的记录!");
    }
}

/**
 * 详情
 */
function detail(){
    var rows = $("#customerTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        window.location.href="customer/viewRetail/"+rows[0].id+".html";
    }else{
        $.messager.alert("提示", "请选择记录!");
    }
}

/**
 * 提交审批
 */
function apply(){
    var rows = $("#customerTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
    	if(rows[0].states != '1'){
        	$.messager.alert("提示", "客户状态不支持!");
        	return;
        }
        $.messager.confirm("警告", "您确认要提交此条记录吗?", function() {
            $.post("customer/apply",{"id":rows[0].id},function(data){
            	if(data.data != '0'){
            		$.messager.alert("提示", data.errorMessage? data.errorMessage:data.data);
            		return;
            	}
            	$("#customerTable").bootstrapTable("refresh");
            	})
        });
    }else{
        $.messager.alert("提示", "请选择要提交审批的记录!");
    }
}

/**
 * 删除
 */
function doDel(){
	var rows = $("#customerTable").bootstrapTable("getSelections");
	if(rows && rows.length ==1){
		if(rows[0].states != '1'){
        	$.messager.alert("提示", "客户状态不支持!");
        	return;
        }
		$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
			$.post("customer/del",{"id":rows[0].id},function(data){if(data.data != '0'){ $.messager.alert("提示", data.data);return;}$("#customerTable").bootstrapTable("refresh");})
		});
	}else{
		$.messager.alert("提示", "请选择要删除的记录!");
	}
}

function doChange(){
    var rows = $("#customerTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        if(rows[0].states != "3"){
            $.messager.alert("提示", "正式客户才能进行资料变更!");
            return;
        }else{

            $.messager.confirm("警告", "变更客户资料需要审批，审批之后生效，是否继续?", function() {
                window.location.href="customer/change/change"+rows[0].id+".html";
                return;
            });
        }

    }else{
        $.messager.alert("提示", "请选择要提交变更的记录!");
    }
}

/**
 * 选择岗位
 */
function searchPosition(){
	var rows = $("#customerTable").bootstrapTable("getSelections");
	if(rows && rows.length ==1){
		$("#choosePosition").modal();
		loadPosition(rows[0].salesOrgId)
	}else{
		$.messager.alert("提示", "请选择一条记录!");
	}
}

function choosePosition(){
	var positionRows = $("#positionTable").bootstrapTable("getSelections");
	var customerRows = $("#customerTable").bootstrapTable("getSelections");
	if(positionRows && positionRows.length ==1 && customerRows && customerRows.length ==1){
		var custId = customerRows[0].id;
		var positionId = positionRows[0].stationid;
		$.post("customer/setPosition",
				{"custId":custId,"positionId":positionId},
				function(data){
					if(data.data != '0'){
						$.messager.alert("提示", data.data);
						return;
					}
					$("#customerTable").bootstrapTable("refresh");
					$("#choosePosition").modal('hide');
				});
	}else{
		$.messager.alert("提示", "请选择一条记录!");
	}
}

/**
 * 加载岗位列表
 * @param salesOrgId
 */
function loadPosition(salesOrgId){
	if($('#positionTable').data("loaded")){
		$("#positionTable").bootstrapTable("refresh",
			{
			'url' : 'customer/station?salesOrgId='+salesOrgId,
			method:'get'
			}
		);
	}else{
		$('#positionTable').bootstrapTable({
			method: 'get',
			classes:"table table-hover table-condensed",
			url: 'customer/station?salesOrgId='+salesOrgId,
			cache: false,
			striped: true,
			pagination: false,
			searchOnEnterKey:true,
			idField:"id",
			sortName:"colName",
			smartDisplay:true,
			search: false,
			showColumns: true,
			showRefresh: true,
			clickToSelect: true,
			singleSelect:true,
			columns: [{
				field: 'ck',
				title: '编号',
				radio:true
			}, {
				field: 'stationname',
				title: '岗位名称',
				align: 'left'
			}, {
				field: 'orgname',
				title: '销售组织',
				align: 'left'
			}]
		});
		$('#positionTable').data("loaded",true);
	}
}

function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			file : "required"
		}
	});
	return validator;
}

/**
 * 查看接口信息并重新推送
 * @param o
 * @param msg
 */
function viewFeedback(o,msg){
	if($("#customerTable").data("sendSap"+o) == '1'){
		$.messager.alert("提示", "正在重新推送，请勿频繁点击!");
		return;
	}
	$.messager.confirm("信息", "接口信息："+msg+"<br/>是否需要重新发送SAP?", function() {
		$("#customerTable").data("sendSap"+o,1);
		$.post("customer/manualSend",{"id":$.trim(o)},function(result){
        	if(result.errorCode != '-1' && $.trim(result.data) == 'S'){
        		$.messager.popup("重新发送成功！");
        	}else{
        		$.messager.alert("提示", "重新推送SAP错误！");
        	}
        	$("#customerTable").bootstrapTable("refresh");
        	$("#customerTable").data("sendSap"+o,0);
    	})
    });
}

function doExport(){
	$("#generateFile").html("正在生成excel，请耐心等待...");
	$("#exportModal").modal();
	$.ajax({
		url:'customer/generate/retail',
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

$('#importModal').on('shown.bs.modal', function () {
	$('#addPid').chosen({
    	no_results_text : "没有找到",
    	placeholder_text : "请选择客户信息...",
    	search_contains: true,
    	disable_search_threshold: 10
    });
})

function doImport(){
	$("#importModal").modal();
}
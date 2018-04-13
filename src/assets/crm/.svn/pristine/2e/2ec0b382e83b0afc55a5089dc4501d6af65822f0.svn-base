$(function(){
    $('#customerTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'customer/receiver/list',
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
        }, /*{
            field: 'custTypeName',
            title: '客户类型',
            align: 'left'
        }, */{
            field: 'salesOrgName',
            title: '所属销售组织',
            align: 'left'
        },{
            field: 'pid',
            title: '售达方',
            align: 'left',
            formatter : function(value,data) {
            	var containerId = "saleTo"+value;
            	var container = $('<div>').attr("id",containerId);
            	var text = '';
            	$('#pidSelect option').each(function(){
            		var optionval = $(this).attr("value");
            		if(optionval == value){
            			text = $(this).text();
            		}
            	});
            	if(typeof text != 'undefined' && text.length > 0){
            		return text;
            	}else{
            		container.html(value);
            		return container.prop("outerHTML");
            	}
			}
        }, /*{
            field: 'salesAreaName',
            title: '所在大区',
            align: 'left'
        }, {
            field: 'salesProvName',
            title: '所在省区',
            align: 'left'
        }, {
            field: 'positionName',
            title: '对应岗位',
            align: 'left'
        },*/ /*{
            field: 'salesman',
            title: '对应销售人员',
            align: 'left'
        }, */{
            field: 'statesDesc',
            title: '客户状态',
            align: 'left',
            formatter : function(value,data){
            	var states = data.states;
            	var processId = data.processId;
            	var alink = $("<a title='点击查看审批意见'>");
            	alink.html(value+"&nbsp;"+"<i class=\"icon icon-eye-open\"></i>");
            	//当流程号不为空且客户为潜在客户时，说明客户审批被驳回，驳回时显示link
            	if(typeof processId != 'undefined' && $.trim(processId) != '' && states==='1'){
            		alink.attr("onclick","viewComments('"+processId+"')");
            		return alink.prop("outerHTML");
            	}else{
            		return value;
            	}
            }
        }]
    });
    var addValidator = initValidate("#addCustomerForm");
    var editValidator = initValidate("#editCustomerForm");
    
    $("#editBtn").bind("click",doEdit);
    $(".btn-del").bind("click",doDel);
    $("#searchBtn").bind("click",searchCustomer);
    $("#applyBtn").bind("click",apply);
    $("#detailBtn").bind("click",detail);
    $("#changeBtn").bind("click",doChange);
//    $("#setPositionBtn").bind("click",searchPosition);
    $("#choosePosiBtn").bind("click",choosePosition);
    
    loadCustomerSelection();
    
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
    
    $('#pidSelect').chosen({
		no_results_text : "没有找到",
		placeholder_text : "请选择客户信息...",
		search_contains: true
	});
    
});

/**
 * 查看审批意见
 * @param processId
 */
function viewComments(processId){
	$.get('process/viewCommons?processId='+processId,function(result){
		if(typeof result === "string"){
			data = $.parseJSON(result.data);
		}else{
			data = result.data;
		}
		var containedHtml = $('<div>');
		for ( var i in data) {
			var containedRows = $('<div style="clear:both;">');
			var column1 = $('<div style="float: left;padding:6px 0;">');
			var column2 = $('<div style="float: left;padding:6px 12px;">');
			column1.text(data[i].userId+'('+data[i].time+') :');
			column2.text(data[i].fullMessage);
			containedRows.append(column1);
			containedRows.append(column2);
			containedHtml.append(containedRows);
		}
		containedHtml.append($('<div style="clear:both;">'));
		$.messager.alert('审批意见',containedHtml.prop("outerHTML"));
	});
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

//查询客户
function searchCustomer(){
	$("#customerTable").bootstrapTable("refresh",
		{'url' : 'customer/receiver/list?'+$("#searchForm").serialize()}
	);
	return false;
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
        var editId = rows[0].id;
        var potentialCode = $("#search_cust_status").find('option[en="POTENTIAL"]').val();
        if(rows[0].states != potentialCode){
        	$.messager.alert("提示", "客户状态不支持修改!");
        	return;
        }
        window.location.href="customer/receiver/edit"+editId+".html";
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
        window.location.href="customer/receiver/view"+rows[0].id+".html";
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
        $.messager.confirm("警告", "您确认要提交此条记录吗?", function() {
            $.post("customer/receiver/apply",{"id":rows[0].id},function(data){
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
		$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
			$.post("customer/del",{"id":rows[0].id},function(data){if(data.data != '0'){
				$.messager.alert("提示", data.errorMessage? data.errorMessage:data.data);
				return;
			}
			$("#customerTable").bootstrapTable("refresh");})
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
 * 加载售达方
 */
function loadCustomerSelection(){
	$.ajax({
	        type:'POST',
	        url:'order/util/customer',
	        data:'custType=1,4,7,70,9&type=1',
	        dataType : 'json',
	        success : function(result) {
	        	if(typeof result != 'undefined' && result.rows && result.rows.length > 0){
	        		data = result.rows;
	  	          	var custOpts = '<option value="" flag="main">全部</option>';
	  	          	for(var i in data){
	  	          		if(data[i].id == $("#customerHiddenInput").val()){
	  	          			custOpts = custOpts + "\r <option value="+ data[i].id +" selected>"+data[i].custname+"</option>";
	  	          		}else{
	  	          			custOpts = custOpts + "\r <option value="+ data[i].id +" >"+data[i].custname+"</option>";
	  	          		}
	  	          		if($("#saleTo"+(data[i].id)+"")[0]){
	  	          			$("#saleTo"+data[i].id).html(data[i].custname);
	  	          		}
	  	            }
	  	          	$('#pidSelect').html(custOpts);
	        	}else{
	        		$.messager.popup("无法获取售达方信息！");
	        	}
	        	$("#pidSelect").trigger("chosen:updated");
	        	$('#pidSelect').chosen({
					no_results_text : "没有找到",
					allow_single_de : true
				});
	        },
	        error : function(){
	        	$.messager.popup("无法获取售达方信息！");
	        	$('#customerSelection').html("");
	        }
  	});
}
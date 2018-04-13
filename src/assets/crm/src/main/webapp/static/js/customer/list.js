
var stationData;
$(function(){
    $('#customerTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'customer/list',
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
        exportDataType :'all',
        search: false,
        showColumns: true,
        showRefresh: true,
        clickToSelect: true,
        singleSelect:true,
        onCheck:function(row){
            if(row.statesDesc =='休眠客户'){
            	$("#doUpStates").html("激活");
            	$("#doUpStates").removeClass("hide");
            	$("#upStates").val('3');
            }else if(row.statesDesc =='正式客户'){
            	$("#doUpStates").html("休眠");
            	$("#doUpStates").removeClass("hide");
            	$("#upStates").val('4');
            }else{
            	$("#doUpStates").addClass("hide");
            	$("#upStates").val('');
            }
        },
        onUncheck:function(){
        	$("#doUpStates").addClass("hide");
        	$("#upStates").val('');
        },
        columns: [{
            field: 'ck',
            title: '编号',
            radio:true
        }, {
            field: 'id',
            title: '客户编号',
            align: 'left',
            visible :false
        }, {
            field: 'businessLicense',
            title: '营业执照号',
            align: 'left',
            visible :false
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
        }, {
            field: 'code',
            title: '登陆编码',
            align: 'left'
        }, {
            field: 'createTs',
            title: '创建时间',
            align: 'left'
        }, {
            field: 'custTypeName',
            title: '客户类型',
            align: 'left'
        }, {
            field: 'salesOrgName',
            title: '所属销售组织',
            align: 'left'
        }, {
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
        }, {
            field: 'salesman',
            title: '对应销售人员',
            align: 'left'
        }, {
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
    $("#setPositionBtn").bind("click",searchPosition);
    $("#choosePosiBtn").bind("click",choosePosition);
    $("#changeBtn").bind("click",doChange);
    $("#exportBtn").bind("click", doExport);
    
    
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
    //变更销售组织按钮
    $("#changeOrg").bind("click",changeOrg);
    
    //变更销售组织确定事件
    $("#change_org_save").bind("click",changeOrgSave);
    
    //
	$("#newOrgId").change(function() {
		var newOrgId = $("#newOrgId").val();
		$("#stationId").html('');
		$.get('customer/station.json?salesOrgId=' + newOrgId
						+ "&limit=100&offset=0", function(res) {
			if (res.rows && res.rows.length > 0) {
				stationData = res.rows;
				$('#stationId').append('<option></option>');
				for (var i = 0; i < res.rows.length; i++) {
					$('#stationId').append(
							"<option value='" + res.rows[i].id + "'>"
										/*+ $("#newOrgId").html() + '-'*/
									+ res.rows[i].areaName + '-'
									+ res.rows[i].name + '-'
									+ res.rows[i].salename
									+ "</option>");
					}
				}
		});
	});
	$("#doUpStates").click(function(){
		var rows = $("#customerTable").bootstrapTable("getSelections");
		if(rows && rows.length ==1){
			var upStates= $("#upStates").val();
			$.post("customer/updateStates?merchId="+rows[0].id+"&states="+upStates,function(res){
				$.messager.popup(res.data);
				searchCustomer();
			});
		}else{
			$.messager.popup("请选择休眠或者正式客户");
		}
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
	/*if($("#customerTable").data("sendSap"+o) == '1'){
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
    });*/
	$.messager.alert("信息", "接口信息："+msg);//只查看消息，推送功能在rfc消息里处理
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
	$("#doUpStates").addClass("hide");
	$("#customerTable").bootstrapTable("refresh",
		{'url' : 'customer/list?'+$("#searchForm").serialize()}
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
        	$.messager.confirm("警告", "当前客户状态仅支持修改送货地址信息，是否继续?", function() {
        		window.location.href="customer/edit"+editId+".html";
        		return;
            });
        }else{
        	window.location.href="customer/edit"+editId+".html";
        }
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
        window.location.href="customer/view"+rows[0].id+".html";
    }else{
        $.messager.alert("提示", "请选择记录!");
    }
}

/**
 * 提交审批
 */
function apply(){
    var rows = $("#customerTable").bootstrapTable("getSelections");
    var potentialCode = $("#search_cust_status").find('option[en="POTENTIAL"]').val();
    if(rows && rows.length ==1){
    	if(rows[0].states != potentialCode){
        	$.messager.alert("提示", "客户状态不支持必须为编辑!");
        	return;
        }
        $.messager.confirm("警告", "您确认要提交此条记录吗?", function() {
        	$("#applyBtn").attr("disabled","disabled");
            $.post("customer/apply",{"id":rows[0].id},function(data){
            	if(data.data != '0'){
            		$.messager.alert("提示", data.errorMessage? data.errorMessage:data.data);
            		return;
            	}
            	$("#customerTable").bootstrapTable("refresh");
            	$("#applyBtn").removeAttr("disabled");
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

/**
 * 选择岗位
 */
function searchPosition(){
	var rows = $("#customerTable").bootstrapTable("getSelections");
	if(rows && rows.length ==1){
		$("#choosePosition").modal();
		loadPosition(rows[0].salesOrgId,rows[0].salesOrgName)
	}else{
		$.messager.alert("提示", "请选择一条记录!");
	}
}

function choosePosition(){
	var positionRows = $("#positionTable").bootstrapTable("getSelections");
	var customerRows = $("#customerTable").bootstrapTable("getSelections");
	if(positionRows && positionRows.length ==1 && customerRows && customerRows.length ==1){
		var custId = customerRows[0].id;
		var positionId = positionRows[0].id;
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
function loadPosition(salesOrgId,salesOrgName){
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
			url: 'customer/station.json?salesOrgId='+salesOrgId,
			cache: false,
			striped: true,
			pagination: true,
			sidePagination:"server",
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
				field: 'name',
				title: '岗位名称',
				align: 'left'
			}, {
				field: 'salename',
				title: '销售人员',
				align: 'left'
			}, {
				field: 'organizationId',
				title: '销售组织',
				align: 'left',
				formatter:function(){
					return salesOrgName;
				}
			}, {
				field: 'areaName',
				title: '所在地区',
				align: 'left'
			}]
		});
		$('#positionTable').data("loaded",true);
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

function doExport(){
	$("#generateFile").html("正在生成excel，请耐心等待...");
	$("#exportModal").modal();
	$.ajax({
		url:'customer/generate',
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

var oldOrgId;
function changeOrg(){
	var row =$("#customerTable").bootstrapTable("getSelections");
	if(row && row[0].states=='3'){
		$("#merchId").val(row[0].id);
		$("#oldOrgId").val(row[0].organizationId);
		oldOrgId =row[0].organizationId;
		$("#changeOrgModal").modal("show");
	}else{
		$.messager.popup("请选择生效客户");
	}
}
function changeOrgSave(){
	$("#change_org_save").attr("disabled","true");
	var newOrgId =$("#newOrgId").val();
	if(!newOrgId){
		$.messager.popup("请新销售组织");
		return;
	};
	var stationId =$("#stationId").val();
	if(!stationId){
		$.messager.popup("请选择岗位");
		return;
	}
	var salesrepId;
	for (var i=0;i<stationData.length;i++){
		if(stationData[i].id = stationId){
			salesrepId = stationData[i].salesrepId;
		}
	}
	var merchId =$("#merchId").val();
	var changeType =$("#changeType").val();
	$.post("customer/changeOrg", {
		"merchId" : merchId,
		"newOrgId" : newOrgId,
		"oldOrgId" : oldOrgId,
		"changeType" : changeType,
		"states":$("#states").val(),
		"stationId":stationId,
		"salesrepId":salesrepId
	}, function(res) {
		$.messager.popup(res.data);
		$("#change_org_save").removeAttr("disabled");
		$("#changeOrgModal").modal("hide");
		$("#newOrgId").val('');
		$("#stationId").val('');
		//$("#customerTable").bootstrapTable("refresh");
		searchCustomer();
	})
}
var a = $("#areatree");
$(function() {
	$('#logistics').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'logisticsRdcArea/list',
		cache : false,
		toolbar : "#logisticsTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "org",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		//search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		onCheck:function(row){
			a.checkAllNodes(false);
			$.getJSON("logisticsRdcArea/getRdcAreaByUserId?id="+row.logisticsRdcId,function(data){
	            if(data.errorCode == 0){
	                var ids = data.data;
	                for(var i=0;i<ids.length;i++){
	                    a.checkNode(a.getNodeByParam("id",ids[i]), true, false);
	                }
	            }
	        });
        },
		columns : [ {
			field : 'ck',
			title : '编号',
			radio : true
		}, {
			field : 'username',
			title : '用户名称',
			align : 'left'
		}, {
			field : 'rdcCode',
			title : 'RDC',
			align : 'left'
		}, {
			field : 'rdcName',
			title : 'RDC描述',
			align : 'left'
		}]
	});
	a = $.fn.zTree.init(a,settings);
	var addValidator = initValidate("#addUserRdcForm");
	$("#addUserRdcForm").ajaxForm({
		target : '#btn-save', // target element(s) to be updated with server
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return addValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
				$.messager.alert("添加失败",responseText.errorMessage);
			}else{
				$.messager.popup("添加成功");
				$("#addUserRdcModal").modal("hide");
				$("#logistics").bootstrapTable("refresh")
			}
		}
	});
	
	$('#userId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
	$("#btn-search").bind("click", doSearch);
	$(".btn-area_save").bind("click",doAreaSave);
	$("#btn-del").bind("click",doDel);
	$("#btn-add").bind("click", doAdd);
	$("#exportBtn").bind("click", doExport2);
});
function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			userId:"required",
			rdcCode:"required"
		},
		ignore: ""
	});
	return validator;
}
var settings = {
	    check : {
	        enable : true
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
	        url : "pub/area/list",
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
	    },
	    callback : {
	        // onClick : function(event, treeId, treeNode, clickFlag) {
	        //     showNodeInfo(treeNode);
	        // }
	    }
	};

function doAdd(){
	$("#addUserRdcModal").modal("show");
	$.get("system/emp/list?limit=6500",function(data){
		if (data.rows && data.rows.length > 0) {
			$('#userId').append("<option></option>");
			for (var i = 0; i < data.rows.length; i++) {
				$('#userId').append("<option value='" + data.rows[i].id + "'>"+ data.rows[i].name + "</option>");
			}
		}
		$("#userId").trigger("chosen:updated");
    });
}
function doDel() {
	var rows = $("#logistics").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
			$.post("logisticsRdcArea/del", {
				"id" : rows[0].logisticsRdcId
			}, function() {
				$("#logistics").bootstrapTable("refresh");
			})
		});
	} else {
		$.messager.alert("请选择记录")
	}
}
function doAreaSave(){
	var rows = $("#logistics").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		var logisticsRdcId = rows[0].logisticsRdcId
		var areaNodes = a.getCheckedNodes(true);
		if(areaNodes && areaNodes.length > 0){
			var params = "";
			for(var i=0;i<areaNodes.length;i++){
				params+="areaId="+areaNodes[i].id+"&";
			}
			params+="logisticsRdcId="+logisticsRdcId;
			$.post("logisticsRdcArea/saveRdcArea",params,function(data){
				if(data.errorCode == 0){
					$.messager.popup("区域保存成功")
				}else{
					$.messager.popup("错误","授权失败");
				}
			})
		}
	}else{
		$.messager.alert("请选择记录")
	}
}
/**
 * 查询
 */
function doSearch() {
	var url = "logisticsRdcArea/list?username="+$("#username").val();
	$("#logistics").bootstrapTable('refresh', {
		'url' : url
	});
	return false;
}

function doExport2(){
	var url = "logisticsRdcArea/exportExl?username="+$("#username").val()+"&offset=0&limit=650000";
	window.open(url);
}

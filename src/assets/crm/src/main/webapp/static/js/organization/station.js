var setting = {
    check : {
        enable : false
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
        url : "Org/listArea",
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
        onClick : function(event, treeId, treeNode, clickFlag) {
            currentNode = treeNode;
            $('#stationTable').bootstrapTable('refresh',{'url':'station/list.json?orgid='+treeNode.id});
        }
    }
};
var customerSetting = {
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
        url : "customer/list.json?limit=1000&status=3",
        dataFilter: function(treeId, parentNode, responseData){
            return responseData.rows;
        }
    },
    data : {
        key : {
            name : "name"
        },
        simpleData : {
            enable : true,
            idKey : "id"
        }
    },
    callback : {
    }
};
var customerTree = $("#customerTree");
$(function() {
    var t = $("#tree");
    t = $.fn.zTree.init(t, setting);
    customerTree = $.fn.zTree.init(customerTree, customerSetting);
    initTable();
    
    $("#editStation").bind('click',editStation);
    $("#deleteStation").bind('click',deleteStation);
    $("#addStation").bind('click', addStation);
    $("#relateSave").bind('click', relateCustomer);
    $("#relateBtn").bind('click', showRelate);
    $("#relateUser").bind('click', relateUser);
    var editValidate = formValidater("#editForm");
    var addValidate = formValidater("#addForm");
    $("#editForm").ajaxForm({
		target : '#btn-add', // target element(s) to be updated with server
		// response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return editValidate.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("新增录入信息失败",responseText.errorMessage);
            }else{
            	$("#editModal").modal("hide");
    			$("#stationTable").bootstrapTable("refresh")
            }
		}
    });
    $("#addForm").ajaxForm({
		target : '#btn-add', // target element(s) to be updated with server
		// response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return addValidate.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("新增录入信息失败",responseText.errorMessage);
            }else{
            	$("#addModal").modal("hide");
    			$("#stationTable").bootstrapTable("refresh")
            }
		}
    });
});
function initTable(){
    $('#stationTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'station/list.json',
        cache: false,
        toolbar:"#tools",
        striped: true,
        pagination: true,
        searchOnEnterKey:true,
        sidePagination:"server",
        idField:"id",
        sortName:"colName",
        smartDisplay:true,
        pageSize: 10,
        pageList:["10","20","50","100"],
        search: true,
        showColumns: true,
        showRefresh: true,
        clickToSelect: true,
        singleSelect:true,
        columns: [{
            field: 'ck',
            title: '编号',
            radio:true
        }, {
            field: 'id',
            title: 'id',
            visible: false
        }, {
            field: 'name',
            title: '岗位名称',
            align: 'left'
        }, {
            field: 'salename',
            title: '销售人员',
            align: 'left'
        }, {
            field: 'createTs',
            title: '创建时间',
            align: 'left'
        }, {
            field: 'showText',
            title: '客户',
            align: 'left',
            formatter:function(){
                return "<a href='customer/list.html' >客户</a>"
            }
        }]
    });
}

function addStation(){
	if(currentNode && currentNode.id && currentNode.level==5){
		var parentN = currentNode.getParentNode();
		$("#saveorganizationId").val(parentN.id);
		var tmp = getOrgid(currentNode);
		$("#saveorgAreaId").val(currentNode.id.substring(parentN.id.length));
		$("#savesale").val("0");
		$("#addModal").modal("show");
//		$.get('system/emp/list?order=asc&limit=100&offset=0&orgId='+getPersonRoot(currentNode), function(res){
//			var persons = res.rows;
//			var html = '<option value="0"></option>';
//			$.each(persons, function(n, value){
//				if(value.isSalesman=="1"){
//					html += '<option value="'+value.id+'">'+value.name+'</option>';
//				}
//			});
//			$("#savesale").html(html);
//			$("#addModal").modal("show");
//		});
		
	}else{
		$.messager.alert("提示","请选择到省节点")
	}
	
}

function getOrgid(node){
	if(node.level==4){
		return node.id;
	}
	var tmpNode = node.getParentNode();
	for(var i=0;i<20;i++){
		if(tmpNode.level==4){
			return tmpNode.id;
		}
		tmpNode = tmpNode.getParentNode();
	}
}

function showRelate(){
	setTimeout(function(){
		var rows = $("#stationTable").bootstrapTable("getSelections");
		checkNodes(rows[0].id);
		$('#relateModel').modal("show");
	},200)
	
}

function relateCustomer(){
	var rows = $("#stationTable").bootstrapTable("getSelections");
	var nodes = customerTree.getCheckedNodes(true);
	var param = "";
	$.each(nodes, function(i, val){
		param += 'customerId='+val.id+'&';
	});
	param += 'stationId='+rows[0].id;
	$.post("station/relate.json",param,function(data){
        if(data.errorCode == 0 && data.data>0){
            $.messager.popup("关联成功")
        }else{
            $.messager.alert("错误","关联失败");
        }
        $('#relateModel').modal("hide");
    });
	
}

function getPersonRoot(node){
	if(node.level<3){
		return node.id;
	}
	var tmpNode = node.getParentNode();
	for(var i=0;i<20;i++){
		if(tmpNode.level==2){
			return tmpNode.id;
		}
		tmpNode = tmpNode.getParentNode();
	}
}

function checkNodes(id){
	customerTree.checkAllNodes(false);
    $.getJSON("station/related/customer.json?stationId="+id,function(data){
        if(data.rows && data.rows.length>0){
            var ids = data.rows;
            for(var i=0;i<ids.length;i++){
            	var node = customerTree.getNodeByParam("id",ids[i].merchCustId);
            	if(node){
            		customerTree.checkNode(node, true, false);
            	}
            }
        }
    });
}

function editStation(){
	var rows = $("#stationTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		fillEditform(rows[0]);
		$("#editdescription").val(rows[0].description);
		$("#editModal").modal("show");
		
//		$.get('system/emp/list?order=asc&limit=1&offset=0&orgId='+rows[0].organizationId.substr(0, 5), function(res){
//			var persons = res.rows;
//			var html = '<option value="0"></option>';
//			$.each(persons, function(n, value){
//				if(value.isSalesman=="1"){
//					html += '<option value="'+value.id+'">'+value.name+'</option>';
//				}
//			});
//			$("#editsalesrepId").html(html);
//			fillEditform(rows[0]);
//			$("#editdescription").val(rows[0].descri);
//			$("#editModal").modal("show");
//		});
		
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}

function relateUser(){
	var stations = $("#stationTable").bootstrapTable("getSelections")
	var users = $('#userTable').bootstrapTable("getSelections");
	if(users && users.length==1){
		$.post('station/edit.json', {'name':stations[0].name,'salesrepId':users[0].id,'id':stations[0].id}, function(res){
			if(res.errorCode==0 && res.data==1){
				$("#relateModal").modal('hide');
				$.messager.popup("关联成功");
				$("#stationTable").bootstrapTable("refresh");
			}else{
				$.messager.popup("关联失败");
			}
		});
	}else{
		$.messager.popup("请选择用户！");
	}
}
function deleteStation(){
	var rows = $("#stationTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		$.post("station/delete.json",{'id':rows[0].id}, function(res){
			$("#stationTable").bootstrapTable("refresh");
		});
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}

function showRelate(){
	var rows = $("#stationTable").bootstrapTable("getSelections");
	if(rows && rows.length ==1){
		$("#relateModal").modal();
		loadUser(rows[0]);
	}else{
        $.messager.alert("提示", "请选择岗位!");
    }
}

function formValidater(formId) {
	var validator = $(formId).validate({
		rules : {
			name : {
				required : true,
				minlength: 1
			}
		
		}
	});
	return validator;
}

function fillEditform(Obj) {
	for ( var i in Obj) {
		if (Obj[i] == null) {
			continue;
		}
		$("#edit" + i).val(Obj[i] + "");
	}
}

function loadUser(line){
	if($('#userTable').data("loaded")){
		$('#userTable').bootstrapTable('refresh',{'url':'system/emp/list'});
	}else{
		$('#userTable').bootstrapTable({
			method: 'get',
			classes:"table table-hover table-condensed",
			url: 'system/emp/list?states=1',
			cache: false,
			striped: true,
			pagination: true,
			sidePagination:"server",
			searchOnEnterKey:true,
			idField:"id",
			sortName:"colName",
			smartDisplay:true,
			search: true,
			showExport : false,
			showColumns: false,
			showRefresh: true,
			clickToSelect: true,
			singleSelect:true,
			pageSize: 10,
	        pageList:["10","20","50","100"],
			columns: [{
				field: 'ck',
				title: '编号',
				radio:true
			}, {
				field: 'id',
				title: 'id',
				align: 'left',
				visible : false
			}, {
				field: 'name',
				title: '用户名',
				align: 'left'
			}, {
				field: 'loginName',
				title: '登陆名',
				align: 'left'
			}]
		});
		$('#userTable').data("loaded",true);
	}
}
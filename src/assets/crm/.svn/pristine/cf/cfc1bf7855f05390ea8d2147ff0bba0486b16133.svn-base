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
        url : "Org/list",
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
            realodTable(treeNode.id);
        }
    }
};
var currentNode = {};
var table=null;
var t = $("#tree");
$(function() {
    t = $.fn.zTree.init(t, setting);
    initTable();
    
    $("#addStation").bind('click', addStation);
    $("#editStation").bind('click', editStation);
    $("#deleteStation").bind('click', deleteStation);
    $("#showPerson").bind('click', showPerson);
    
    var addValidate = formValidater("#addForm");
    var editValidate = formValidater("#editForm");
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
    			$("#jobTable").bootstrapTable("refresh")
            }
		}
    });
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
    			$("#jobTable").bootstrapTable("refresh")
            }
		}
    });
});
function initTable(){
    table = $('#jobTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'jobstation/list',
        cache: false,
        striped: true,
        sidePagination:"server",
        idField:"id",
        clickToSelect: true,
        showExport:false,
        singleSelect:true,
        pagination: true,
        pageSize: 10,
        search: true,
        showRefresh: true,
        pageList:["10","20","50","100"],
        queryParams:function(params){
            var nodes = t.getSelectedNodes();
            if(nodes && nodes.length > 0){
                params.orgId = t.getSelectedNodes()[0].id;
            }else{
                params.orgId="T"
            }
            return params;
        },
        columns: [{field:"ck",radio:true}, {
            field: 'name',
            title: '职位名称',
            align: 'left'
        }, {
            field: 'jobType',
            title: '职位类型',
			formatter:function(value){
				return getDictValue(value)
			}
        }, {
            field: 'orgname',
            title: '所属组织',
            align: 'left'
        }, {
            field: 'num',
            title: '分配人员数',
            align: 'left'
        }]
    });
}

function addStation(){
	if(currentNode && currentNode.id ){
		$("#saveorgId").val(currentNode.id);
		$("#addModal").modal("show");
	}else{
		$.messager.alert("提示","请选择机构节点")
	}
	
}

function getOrgid(node){
	if(node.level==4){
		return node.id;
	}
	var tmpNode = node.getParentNode();
	for(var i=0;i<10;i++){
		tmpNode = tmpNode.getParentNode();
		if(tmpNode.level==4){
			return tmpNode.id;
		}
	}
}

function editStation(){
	var rows = $("#jobTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		fillEditform(rows[0]);
		$("#editteam").val(rows[0].team);
		$("#editModal").modal("show");
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}
function deleteStation(){
	var rows = $("#jobTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		$.post("jobstation/delete.json",{'id':rows[0].id}, function(res){
			$("#jobTable").bootstrapTable("refresh");
		});
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}

function editFormValidater(formId) {
	var validator = $(formId).validate({
		rules : {
			editname : {
				required : {
			        depends:function(){
			            $(this).val($.trim($(this).val()));
			            return true;
			        }
			    },
				minlength: 1
			}
		}
	});
	return validator;
}

function saveFormValidater(formId) {
	var validator = $(formId).validate({
		rules : {
			savename : {
				required : {
			        depends:function(){
			            $(this).val($.trim($(this).val()));
			            return true;
			        }
			    },
				minlength: 1
			}
		}
	});
	return validator;
}
function realodTable(id){
    table.bootstrapTable("refresh");
}

function formValidater(formId) {
	var validator = $(formId).validate({
		rules : {
			name : {
				required : {
			        depends:function(){
			            $(this).val($.trim($(this).val()));
			            return true;
			        }
			    },
				minlength: 1
			},
			jobType:{
				required : true,
				minlength : 1
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
		var value = Obj[i]+"";
		if(i=='name'){
			var index = value.lastIndexOf('-');
			if(index>-1){
				value = value.substring(index+1);
			}
		}
		
		$("#edit" + i).val(value);
	}
}

function showPerson(){
	var rows = $("#jobTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		loadUser(rows[0].id);
		$("#userModal").modal();
	} else {
		$.messager.popup("请选择记录!");
	}
}

function loadUser(jobId){
	if($('#userTable').data("loaded")){
		$('#userTable').bootstrapTable('refresh',{'url':'jobstation/jobset/list.json?jobId='+jobId});
	}else{
		$('#userTable').bootstrapTable({
			method: 'get',
			classes:"table table-hover table-condensed",
			url: 'jobstation/jobset/list.json?jobId='+jobId,
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
	        queryParams:function(params){
	        	params.name = params.search;
	        	params.search = "";
	            return params;
	        },
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


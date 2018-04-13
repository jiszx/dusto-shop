var currentNode = null;
var table = null;
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
        	$.each(responseData.data, function(i, val){
        		if(val.pid=="0"){
        			currentNode = val;
        			return false;
        		}
        	});
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
            showNodeInfo(treeNode);
        },
        onAsyncSuccess:function(){
            t.selectNode(t.getNodeByTId("tree_1"));
        }
    }
};
var t = $("#tree");
$(function(){
    t = $.fn.zTree.init(t, setting);
    table = $('#jobTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'jobstation/jobset/list.json',
        cache: false,
        toolbar:"#dictTool",
        striped: true,
        pagination: true,
        searchOnEnterKey:true,
        sidePagination:"server",
        idField:"id",
        smartDisplay:true,
        pageSize: 10,
        pageList:["10","20","50","100"],
        search: true,
        showColumns: true,
        showRefresh: true,
        clickToSelect: true,
        singleSelect:true,
        queryParams:function(params){
            var nodes = t.getSelectedNodes();
            if(nodes && nodes.length > 0){
                params.orgId = t.getSelectedNodes()[0].id;
            }
            return params;
        },
        columns: [{
            field: 'ck',
            title: '编号',
            radio:true
        }, {
            field: 'stationname',
            title: '职位',
            align: 'left'
        }, {
            field: 'loginName',
            title: '关联用户',
            align: 'left'
        }, {
            field: 'name',
            title: '用户名称',
            align: 'left'
        },{
            field: 'isSalesman',
            title: '是否销售人员',
            align: 'left',
            formatter:function(value){
            	if(!value){
            		return "-";
            	}
                return genBoolean(value);
            }
        }, {
            field: 'contactTel',
            title: '联系电话',
            align: 'left'
        }, {
            field: 'email',
            title: '邮箱',
            align: 'left'
        }, {
            field: 'roleId',
            title: '角色',
            align: 'left',
            formatter:function (value) {
                return getRoleName(value);
            }
        }, {
            field: 'states',
            title: '状态',
            align: 'left',
            formatter:function (value) {
            	if(!value){
            		return "-";
            	}
                return getDictValue(value);
            }
        }]
    });
    var editValidator = initValidate("#editEmpForm");
    
    $("#relationButton").bind("click",showRelate);
    $("#deleteBtn").bind("click",deleteRelation);
    $("#relateUser").bind("click",relateUserAndJob);
});

function initValidate(formId){
    var validator = $(formId).validate({
        rules: {
            jobId:{required:true}
        }
    });
    return validator;
}

function relation(){
    var rows = table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        autoEdit(rows[0]);
        $.get('jobstation/list?limit=100&offset=0&orgId='+currentNode.id, function(res){
        	if(res.rows){
        		var html = "";
        		$.each(res.rows, function(i, val){
        			html += '<option value="'+val.id+'">'+val.name+'</option>';
        		});
        		$("#editjob").html(html);
        		$("#editjob").val(rows[0].jobId);
        	}
        });
        $("#editEmpModal").modal("show")
    }else{
        $.messager.alert("提示", "请选择人员!");
    }
}

function deleteRelation(){
	var rows = table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
    	if(!rows[0].jobId){
    		$.messager.popup("用户无职位！");
    		return;
    	}
    	$.post("jobstation/cancel.json", {"userid":rows[0].empId,"jobid":rows[0].jobId}, function(res){
    		if(res.data && res.data==1){
    			$.messager.popup("删除成功");
    			table.bootstrapTable("refresh");
    		}else{
    			$.messager.popup("删除失败");
    		}
    	});
    }else{
    	$.messager.alert("提示", "请选择记录!");
    }
}

function showNodeInfo(node){
    table.bootstrapTable("refresh",{"orgId":node.id});
    $("#organizationId").val(node.id);
    $("#organizationName").val(node.name);
}

function relateUserAndJob(){
	var user = $("#jobTable").bootstrapTable("getSelections")[0];
	var rows = $("#empTable").bootstrapTable("getSelections");
	if(rows && rows.length ==1){
		$.post("jobstation/jobset/relation.json",{"empId":rows[0].id,"jobId":user.jobId}, function(res){
			if(res.errorCode != 0){
                $.messager.popup("添加失败");
            }else{
            	$("#relateModal").modal("hide");
                $("#dictTable").bootstrapTable("refresh");
            }
            if(res.errorCode == 0){
                $("#relateModal").modal("hide");
                $.messager.popup("添加成功");
                table.bootstrapTable("refresh")
            }
		});
	}else{
		$.messager.alert("提示", "请选择职位!");
	}
}

function showRelate(){
	var rows = $("#jobTable").bootstrapTable("getSelections");
	if(rows && rows.length ==1){
		$("#relateModal").modal();
		loadUser(rows[0]);
	}else{
        $.messager.alert("提示", "请选择人员!");
    }
}

function loadUser(line){
	if($('#empTable').data("loaded")){
		$('#empTable').bootstrapTable('refresh',{'url':'system/emp/list'});
	}else{
		$('#empTable').bootstrapTable({
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
		$('#empTable').data("loaded",true);
	}
}
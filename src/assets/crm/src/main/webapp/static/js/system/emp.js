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
        url : "Org/list?level=1",
        dataFilter: function(treeId, parentNode, responseData){
            initOrgSelect(responseData.data);
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
            showNodeInfo(treeNode);
        },
        onAsyncSuccess:function(event, treeId, treeNode, msg){
            t.selectNode(t.getNodeByTId("tree_1"));
        }
    }
};
function initOrgSelect(data){
    for(var i=0;i<data.length;i++){
        $("#editorganizationId").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
    }
}
var t = $("#tree");
$(function(){
	
	$("#organizationId").val("T");
    $("#organizationName").val("调味品事业部");
	
    t = $.fn.zTree.init(t, setting);
    table = $('#empTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'system/emp/list',
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
            field: 'loginName',
            title: '登录名',
            align: 'left',
        }, {
            field: 'cardNo',
            title: '工号',
        }, {
            field: 'name',
            title: '姓名',
            align: 'left'
        },{
            field: 'isSalesman',
            title: '是否销售人员',
            align: 'left',
            formatter:function(value){
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
                return getDictValue(value);
            }
        }]
    });
    
    jQuery.validator.addMethod("charLength", function(value, element, len) {   
    	return this.optional(element) || mbStringLength(value)<=len;
    }, $.validator.format("请确保输入的值不超过{0}个字节(一个中文3个字节)"));
    
    var addValidator = initValidate("#addEmpForm");
    var editValidator = initValidate("#editEmpForm");
    $("#addEmpForm").ajaxForm({
        target:'#btn-add',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return editValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
        	if(responseText.errorCode != 0){
                $.messager.alert("新增员工信息失败",responseText.errorMessage);
            }else{
                $("#addEmpModal").modal("hide");
                table.bootstrapTable("refresh");
            }
        }
    });

    $("#editEmpForm").ajaxForm({
        target:'#btn-edit-save',   // target element(s) to be updated with server response
        resetForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return addValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
        	if(responseText.errorCode != 0){
                $.messager.alert("修改员工失败",responseText.errorMessage);
            }else{
            	$("#editEmpModal").modal("hide")
                $("#dictTable").bootstrapTable("refresh");
            }
            if(responseText.errorCode == 0){
                $("#addEmpModal").modal("hide");
                table.bootstrapTable("refresh")
            }
        }
    });
    $(".btn-edit").bind("click",doEdit);
    $(".btn-del").bind("click",doDel);
    $("#btn-detail").bind("click",doShow);
    $(".btn-reset").bind("click",doReset);
    $(".btn-user-stop").bind("click",doStop);
    $(".btn-user-start").bind("click",doStart);
});

function doReset(){
    var rows = table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        $.messager.confirm("警告", "您确认要对此用户密码重置吗?", function() {
            $.post("system/emp/pwdReset",{"id":rows[0].id},function(data){
                if(data.errorCode == 0){
                    $.messager.popup("重置成功");
                }else{
                    $.messager.popup("重置失败")
                }
            });
        });
    }else{
        $.messager.alert("提示", "请选择要重置的用户!");
    }
}
function doStop(){
    var rows = table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        $.messager.confirm("警告", "您确认要对此用户进行停用吗?", function() {
            $.post("system/emp/userStop",{"id":rows[0].id},function(data){
                if(data.errorCode == 0){
                    $.messager.popup("停用成功");
                    table.bootstrapTable("refresh");
                }else{
                    $.messager.popup("停用失败")
                }
            });
        });
    }else{
        $.messager.alert("提示", "请选择要停用的用户!");
    }
}

function doStart(){
    var rows = table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        $.messager.confirm("警告", "您确认要对此用户进行启用吗?", function() {
            $.post("system/emp/userStart",{"id":rows[0].id},function(data){
                if(data.errorCode == 0){
                    $.messager.popup("启用成功")
                    table.bootstrapTable("refresh");
                }else{
                    $.messager.popup("启用失败")
                }
            });
        });
    }else{
        $.messager.alert("提示", "请选择要启用的用户!");
    }
}

function initValidate(formId){
    var validator = $(formId).validate({
        rules: {
        	loginName:{
            	required: true,
            	remote:'system/emp/checkUser',
            	charLength:20
            },
            name:{
            	required:true,
            	charLength:20
            },
            isSalesman:"required",
            organizationId:"required",
            contactTel:{required:true,minlength:11},
            email: {required: true,email:true}
        },
        message:{
        	loginName:{
        		remote:"该用户名已存在"
        	}
        }
    });
    return validator;
}
/**
 * 编辑按钮
 */
function doEdit(){
    var rows = table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        autoEdit(rows[0]);
        $("#editEmpModal").modal("show")
    }else{
        $.messager.alert("提示", "请选择要编辑的记录!");
    }
}
function doShow(){
    var rows = table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        showDetail("system/emp/detail?id="+rows[0].id);
    	 /*autoEdit(rows[0]);
         $("#showEmpModal").modal("show");*/
    }else{
        $.messager.alert("提示", "请选择记录!");
    }
}
function doDel(){
    var rows = table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        $.messager.confirm("警告", "您确认要删除此记录吗?", function() {
            $.post("system/emp/del",{"id":rows[0].id},function(data){
            	if(data.errorCode == 0){
            		table.bootstrapTable("refresh");
            	}else{
            		$.messager.alert("提示","删除失败"+data.errorMessage);
            	}
            	})
        });
    }else{
        $.messager.alert("提示", "请选择要删除的记录!");
    }
}

function mbStringLength(s) {
	var totalLength = 0;
	var i;
	var charCode;
	for (i = 0; i < s.length; i++) {
		charCode = s.charCodeAt(i);
		if (charCode < 0x007f) {
			totalLength = totalLength + 1;
		} else if ((0x0080 <= charCode) && (charCode <= 0x07ff)) {
			totalLength += 2;
		} else if ((0x0800 <= charCode) && (charCode <= 0xffff)) {
			totalLength += 3;
		}
	}
	return totalLength;
}

function showNodeInfo(node){
    table.bootstrapTable("refresh",{"orgId":node.id});
    $("#organizationId").val(node.id);
    $("#organizationName").val(node.name);
}
var treeObj = null;
var userTable = null;
var table;
var t = $("#org-tree");
var org_setting = {
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
$(function () {
	$("#organizationId").val("T");
    $("#organizationName").val("调味品事业部");
	
    t = $.fn.zTree.init(t, org_setting);
    
    table = $('#roleTable').bootstrapTable({
        method: 'get',
        classes: "table table-hover table-condensed",
        url: 'system/role/list',
        cache: false,
        toolbar: "#roleTool",
        striped: true,
        pagination: true,
        searchOnEnterKey: true,
        sidePagination: "server",
        idField: "id",
        sortName: "roleName",
        smartDisplay: true,
        pageSize: 10,
        pageList: ["10", "20", "50", "100"],
        search: true,
        showColumns: true,
        showRefresh: true,
        clickToSelect: true,
        singleSelect: true,
        queryParams:function(params){
            var nodes = t.getSelectedNodes();
            if(nodes && nodes.length > 0){
                params.orgId = t.getSelectedNodes()[0].id;
            }
            return params;
        },
        columns: [{
            field: 'ck',
            title: '操作',
            radio: true
        }, {
            field: 'id',
            title: '编号',
            width: 20
        }, {
            field: 'roleName',
            title: '角色名称',
            align: 'left'
        }, {
            field: 'roleDesc',
            title: '角色描述',
            align: 'left'
        }, {
            field: 'roleType',
            title: '类型',
            align: 'center',
            formatter: function (value) {
                if (value == "1") {
                    return "系统角色"
                } else {
                    return "配送商角色"
                }
            }
        }, {
            field: 'roleCount',
            title: '授权人数',
            width: 20,
            align: 'center'
        }]
    });
    var addValidator = initValidate("#addRoleForm");
    var editValidator = initValidate("#editRoleForm");
    $("#editRoleForm").ajaxForm({
        target: '#btn-edit-save',   // target element(s) to be updated with server response
        dataType: 'json',
        beforeSubmit: function (formData, jqForm, options) {
            return editValidator.valid();
        },
        success: function (responseText, statusText, xhr, $form) {
            if (responseText.errorCode != 0) {
                $.messager.alert("修改失败", responseText.errorMessage);
            } else {
                $("#editRoleModal").modal("hide");
                $("#editRoleForm").resetForm();
                $("#roleTable").bootstrapTable("refresh")
            }
        }
    });
    $("#addRoleForm").ajaxForm({
        target: '#btn-add',   // target element(s) to be updated with server response
        dataType: 'json',
        beforeSubmit: function (formData, jqForm, options) {
            return addValidator.valid();
        },
        success: function (responseText, statusText, xhr, $form) {
            if (responseText.errorCode != 0) {
                $.messager.alert("添加失败", responseText.errorMessage);
            } else {
                $("#addRoleModal").modal("hide");
                $("#addRoleForm").resetForm();
                $("#roleTable").bootstrapTable("refresh")
            }
        }
    });
    $(".btn-edit").bind("click", doEdit);
    $(".btn-del").bind("click", doDel);
    $("#btn-open-grant").bind("click", doGrant);
    $("#btn-grant").bind("click", grant);
    $("#btn-look-user").bind("click", dolookUser);
    initTree();
});

function initUserTable() {
    userTable = $('#roleUserTable').bootstrapTable({
        method: 'get',
        classes: "table table-hover table-condensed",
        striped: true,
        pagination: true,
        sidePagination: "server",
        idField: "id",
        pageSize: 5,
        pageList: ["5", "10", "15", "20"],
        search: true,
        showRefresh: true,
        columns: [{
            field: 'loginName',
            title: '登录名'
        }, {
            field: 'cardNo',
            title: '工号',
        }, {
            field: 'name',
            title: '姓名',
            align: 'left'
        }, {
            field: 'contactTel',
            title: '联系电话',
            align: 'left'
        }, {
            field: 'email',
            title: '邮箱',
            align: 'left'
        }]
    });
}

function initTree() {
    var setting = {
        check: {
            enable: true
        },
        view: {
            dblClickExpand: false,
            showLine: true,
            selectedMulti: false
        },
        async: {
            dataType: "json",
            enable: true,
            type: "post",
            url: "system/auth/list",
            dataFilter: function (treeId, parentNode, responseData) {
                return responseData.data;
            }
        },
        data: {
            key: {
                name: "resName"
            },
            simpleData: {
                enable: true,
                idKey: "resId",
                pIdKey: "pid",
                rootPId: "0"
            }
        }
    };
    var t = $("#authTree");
    t = $.fn.zTree.init(t, setting);
    treeObj = t;
}

function showNodeInfo(node){
    table.bootstrapTable("refresh",{"orgId":node.id});
    $("#organizationId").val(node.id);
    $("#organizationName").val(node.name);
}

function initValidate(formId) {
    var validator = $(formId).validate({
        rules: {
            roleName: "required",
            roleDesc: {required: true, maxlength: 20}
        }
    });
    return validator;
}
function dolookUser() {
    var rows = $("#roleTable").bootstrapTable("getSelections");
    if (rows && rows.length == 1) {
        if (userTable == null) {
            initUserTable();
        }
        userTable.bootstrapTable("refresh", {url: 'system/emp/list?roleId=' + rows[0].id})
        $("#lookUserModal").modal("show")
    } else {
        $.messager.alert("提示", "请选择要编辑的记录!");
    }
}
/**
 * 编辑按钮
 */
function doEdit() {
    var rows = $("#roleTable").bootstrapTable("getSelections");
    if (rows && rows.length == 1) {
        autoEdit(rows[0]);
        $("#editorganizationId").val(rows[0].orgId)
        $("#editRoleModal").modal("show")
    } else {
        $.messager.alert("提示", "请选择要编辑的记录!");
    }
}
function doDel() {
    var rows = $("#roleTable").bootstrapTable("getSelections");
    if (rows && rows.length == 1) {
        $.messager.confirm("警告", "您确认要删除此记录吗?", function () {
            $.post("system/role/del", {"id": rows[0].id}, function (data) {
                if (data.errorCode == 0) {
                    $("#roleTable").bootstrapTable("refresh");
                } else {
                    $.messager.alert("提示", "删除失败" + data.errorMessage);
                }
            })
        });
    } else {
        $.messager.alert("提示", "请选择要删除的记录!");
    }
}
function doGrant() {
    var rows = $("#roleTable").bootstrapTable("getSelections");
    treeObj.checkAllNodes(false);
    if (rows && rows.length == 1) {
        $.getJSON("system/role/getAuth?id=" + rows[0].id, function (data) {
            if (data.errorCode == 0) {
                var ids = data.data;
                for (var i = 0; i < ids.length; i++) {
                    treeObj.checkNode(treeObj.getNodeByParam("resId", ids[i]), true, false);
                }
            }
            $("#grantRoleModal").modal("show");
        })
    } else {
        $.messager.alert("提示", "请选择要授权的记录!");
    }
}
function grant() {
    var nodes = treeObj.getCheckedNodes(true);
    var rows = $("#roleTable").bootstrapTable("getSelections");
    if (nodes != null && nodes.length > 0) {
        var params = "";
        for (var i = 0; i < nodes.length; i++) {
            params += "auths=" + nodes[i].resId + "&";
        }
        params += "roleId=" + rows[0].id;
        $.post("system/role/grant", params, function (data) {
            if (data.errorCode == 0) {
                $.messager.popup("授权成功");
                $("#grantRoleModal").modal("hide");
            } else {
                $.messager.alert("错误", "授权失败");
            }
        })
    }
}
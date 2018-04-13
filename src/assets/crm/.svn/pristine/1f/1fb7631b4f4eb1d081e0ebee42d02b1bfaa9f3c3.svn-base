<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>管理系统-角色管理</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>角色管理
        <small>对角色进行管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 系统管理</a></li>
        <li class="active">角色管理</li>
    </ol>
</section>

<div class="panel scroll panel-info col-sm-3 col-md-3">
    <div class="panel-heading">
        组织机构
    </div>
    <div class="panel-body">
        <ul id="org-tree" class="ztree" style="width: 100%;"></ul>
    </div>
</div>

<div class="col-sm-9 col-md-9" style="padding: 0px;">
	<div class="panel scroll panel-info col-md-12">
		<div class="panel-heading">角色信息</div>
	    <div style="margin-top: 10px;" class="panel-body">
	        <div id="roleTool">
	            <div class="btn-group btn-group-sm" role="group" aria-label="...">
	                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addRoleModal"
	                        title="添加"><i class='icon icon-plus'></i></button>
	                <button type="button" id="btn-open-grant" class="btn btn-info" title="授权"><i
	                        class='icon accredit_img'></i></button>
	                <button type="button" id="btn-look-user" class="btn btn-primary" title="查看用户"><i
	                        class='icon icon-eye-open'></i></button>
	                <button type="button" class="btn btn-edit btn-warning" title="修改"><i class='icon icon-edit'></i>
	                </button>
	                <button type="button" class="btn btn-del btn-danger" title="删除"><i class='icon icon-remove'></i>
	                </button>
	            </div>
	        </div>
	        <div class="col-md-12">
	            <table id="roleTable"></table>
	        </div>
	    </div>
	</div>
</div>
<!-- addDialog-->
<div class="modal fade" id="addRoleModal" tabindex="-1" role="dialog"
     aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="dictLabel">新增角色</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" action="system/role/add" id="addRoleForm">
                    <div class="form-group">
                        <label for="roleName" class="col-sm-2 control-label">角色名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="roleName" name="roleName">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="roleDesc" class="col-sm-2 control-label">角色描述</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="roleDesc" name="roleDesc">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="roleDesc" class="col-sm-2 control-label">组织</label>
                        <div class="col-sm-6">
                            <input type="text" id="organizationName" class="form-control" readonly>
                            <input type="hidden" id="organizationId" name="orgId">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-add" class="btn btn-primary btn-save" form="addRoleForm">保存</button>
            </div>
        </div>
    </div>
</div>
<!-- editDialog-->
<div class="modal fade" id="editRoleModal" tabindex="-1" aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="editdictLabel">修改角色</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="system/role/edit" method="post" id="editRoleForm">
                    <div class="form-group">
                        <label for="roleName" class="col-sm-2 control-label">角色名称</label>
                        <div class="col-sm-6">
                            <input type="hidden" name="id" id="editid"/>
                            <input type="text" class="form-control" id="editroleName" name="roleName">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="roleDesc" class="col-sm-2 control-label">角色描述</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editroleDesc" name="roleDesc">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
					<div class="form-group">
                        <label for="roleDesc" class="col-sm-2 control-label">组织</label>
                        <div class="col-sm-6">
                            <select name="orgId" id="editorganizationId" class="form-control">
                            </select>
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-edit-save" form="editRoleForm" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- editDialog-->
<div class="modal fade" id="grantRoleModal" tabindex="-1" aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="grantdictLabel">角色授权</h4>
            </div>
            <div class="modal-body" style="height: 300px;overflow-y:auto;">
                <ul id="authTree" class="ztree" style="width: 100%;height: 100%;"></ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btn-grant" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- editDialog-->
<div class="modal fade" id="lookUserModal" tabindex="-1" aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">角色用户</h4>
            </div>
            <div class="modal-body" style="height: 350px;overflow-y:auto;">
                <table id="roleUserTable"></table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="static/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="static/js/system/role.js"></script>
</body>
</html>

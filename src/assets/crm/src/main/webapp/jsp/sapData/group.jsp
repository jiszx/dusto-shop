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
    <title>管理系统-产品分组管理</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>产品分组管理<small>产品分组查询</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 数据管理</a></li>
        <li class="active">产品组信息</li>
    </ol>
</section>
<div class="col-md-12" style="margin-bottom:10px;">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
        <button type="button" class="btn btn-primary btn-add"><i class='icon icon-plus'></i> 添加</button>
        <button type="button" class="btn btn-edit btn-warning"><i class='icon icon-edit'></i> 修改</button>
        <button type="button" class="btn btn-del btn-danger"><i class='icon icon-remove'></i> 删除</button>
    </div>
</div>
<div class="col-md-12">
    <table id="groupTree"></table>
</div>


<div class="modal fade" id="addCateModal" tabindex="-1" role="dialog"
     aria-labelledby="orgLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="dictLabel">新增种类</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" action="product/categroy/add" id="addCateForm">

                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">上级</label>
                        <div class="col-sm-6">
                            <input type="hidden" class="form-control" id="pid" name="pid">
                            <input type="text" class="form-control" readonly id="showPid">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>

                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">分类名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-add"  class="btn btn-primary btn-save" form="addCateForm" >保存</button>
            </div>
        </div>
    </div>
</div>
<!-- editDialog-->
<div class="modal fade" id="editCateModal" tabindex="-1" aria-labelledby="orgLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="editdictLabel">修改分类</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="product/categroy/edit" method="post" id="editCateForm">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">分类</label>
                        <div class="col-sm-6">
                            <input type="hidden" name="id" id="editid">
                            <input type="text" class="form-control" id="editname" name="name">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>
                <button type="submit" id="btn-edit-save" form="editCateForm" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/easyui/easyloader.js"></script>
<script type="text/javascript" src="static/js/sapData/group.js"></script>
</body>
</html>

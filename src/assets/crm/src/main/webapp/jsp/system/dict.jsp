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
    <title>管理系统-字典管理</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>字典管理
        <small>全局字典管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 系统管理</a></li>
        <li class="active">字典管理</li>
    </ol>
</section>


<!-- Main content -->
<div class="col-md-12" id="dictTool" style="padding:0;">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addDictModal"><i
                class='icon icon-plus'></i> 添加
        </button>
        <button type="button" id="btn-reInit" class="btn btn-info"><i class='icon icon-print'></i> 加载</button>
        <button type="button" class="btn btn-edit btn-warning"><i class='icon icon-edit'></i> 修改</button>
        <button type="button" class="btn btn-del btn-danger"><i class='icon icon-remove'></i> 删除</button>
    </div>
</div>
<div class="panel scroll panel-default col-md-12">
    <div class="panel-body" style="padding-left:0;min-height: 500px;">
        <div class="col-md-12">
            <table id="dictTable"></table>
        </div>
    </div>
</div>

<!-- Content Header (Page header) -->


<%--<div class="wrapper row-offcanvas row-offcanvas-left">
    <jsp:include page="/common/leftMenu.jsp"></jsp:include>
    <!-- Right side column. Contains the navbar and content of the page -->
</div><!-- ./wrapper -->--%>

<!-- addDialog-->
<div class="modal fade" id="addDictModal" tabindex="-1" role="dialog"
     aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="dictLabel">新增字典</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" action="system/dict/add" id="addDictForm">
                    <div class="form-group">
                        <label for="colName" class="col-sm-2 control-label">字典字段</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="colName" name="colName">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="colDescription" class="col-sm-2 control-label">字段说明</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="colDescription" name="colDescription">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="chooseVal" class="col-sm-2 control-label">字典值</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="chooseVal" name="chooseVal">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>

                    <div class="form-group">
                        <label for="showText" class="col-sm-2 control-label">显示文本</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showText" name="showText">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="orders" class="col-sm-2 control-label">顺序</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="orders" name="orders">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否可选</label>
                        <div class="col-sm-6">
                            <label class="radio-inline">
                                <input type="radio" name="canChoose" value="1" checked="checked">是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="canChoose" value="0">否
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否显示</label>
                        <div class="col-sm-6">
                            <label class="radio-inline">
                                <input type="radio" name="display" value="1" checked="checked">是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="display" value="0">否
                            </label>
                        </div>
                    </div>


                </form>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-add" class="btn btn-primary btn-save" form="addDictForm">保存</button>
            </div>
        </div>
    </div>
</div>


<!-- editDialog-->
<div class="modal fade" id="editDictModal" tabindex="-1" aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="editdictLabel">修改字典</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="system/dict/edit" method="post" id="editDictForm">
                    <div class="form-group">
                        <label for="colName" class="col-sm-2 control-label">字典字段</label>
                        <div class="col-sm-6">
                            <input type="hidden" id="editid" name="id">
                            <input type="text" class="form-control" id="editcolName" name="colName">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="editcolDescription" class="col-sm-2 control-label">字段说明</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editcolDescription" name="colDescription">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="chooseVal" class="col-sm-2 control-label">字典值</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editchooseVal" name="chooseVal">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="showText" class="col-sm-2 control-label">显示文本</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editshowText" name="showText">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="orders" class="col-sm-2 control-label">顺序</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editorders" name="orders">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>

                    <div class="form-group">
                        <label for="editcanChoose" class="col-sm-2 control-label">是否可选</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="editcanChoose" name="canChoose">
                                <option value="0">否</option>
                                <option value="1">是</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="editdisplay" class="col-sm-2 control-label">是否显示</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="editdisplay" name="display">
                                <option value="0">否</option>
                                <option value="1">是</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-edit-save" form="editDictForm" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript" src="static/js/system/dict.js"></script>
</body>
</html>

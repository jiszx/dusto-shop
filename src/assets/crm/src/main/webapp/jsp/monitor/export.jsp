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
    <title>配置与监控-全局参数配置</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<section class="content-header">
 <h1>通用导出<small>导出及配置管理</small>
 </h1>
 <ol class="breadcrumb">
     <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
     <li><a href="#">配置与监控</a></li>
     <li class="active">通用导出</li>
 </ol>
</section>


<!-- Main content -->
<div class="col-md-12" id="dictTool" style="padding:0;">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
    	<button id="addBtn" type="button" class="btn btn-info"><i class='icon icon-add'></i>新增</button>
        <button type="button" class="btn btn-edit btn-warning"><i class='icon icon-edit'></i> 修改</button>
        <button id="deleteBtn" type="button" class="btn btn-error"><i class='icon icon-delete'></i>删除</button>
        <button id="exportBtn" type="button" class="btn btn-primary"><i class='icon icon-share'></i>导出</button>
    </div>
</div>
<div class="col-md-12">
    <table id="dictTable"></table>
</div>

<!-- Content Header (Page header) -->


<%--<div class="wrapper row-offcanvas row-offcanvas-left">
    <jsp:include page="/common/leftMenu.jsp"></jsp:include>
    <!-- Right side column. Contains the navbar and content of the page -->
</div><!-- ./wrapper -->--%>

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
                <form class="form-horizontal" action="monitor/export/save.json" method="post" id="editDictForm">
                    <input type="hidden" id="editid" name="id">
                    <input type="hidden" class="form-control" id="editcolName" name="colName" >
                    <div class="form-group">
                        <label for="editcolDescription" class="col-sm-2 control-label">key</label>
                        <div class="col-sm-6">
                        	<input type="hidden" id="editid" name="id">
                            <input type="text" class="form-control" id="editkey" name="key">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="chooseVal" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editdescription" name="description" >
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="chooseVal" class="col-sm-2 control-label">映射</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editmapping" name="mapping" >
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="showText" class="col-sm-2 control-label">sql</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editsql" name="sql">
                        </div>
                        <small class="help-block col-sm-4"></small>
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

<div class="modal fade" id="exportModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">导出</h4>
            </div>
            <div class="modal-body" id="generateFile">
                                                         正在生成excel，请耐心等待...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>


<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript" src="static/js/monitor/export.js"></script>
</body>
</html>

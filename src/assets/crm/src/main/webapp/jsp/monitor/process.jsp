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
    <title>配置与监控-流程部署</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<section class="content-header">
 <h1>配置与监控<small>流程部署</small>
 </h1>
 <ol class="breadcrumb">
     <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
     <li><a href="#">配置与监控</a></li>
     <li class="active">流程部署</li>
 </ol>
</section>


<!-- Main content -->
<div class="col-md-12" id="tools" style="padding:0;">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
        <button type="button" class="btn btn-edit btn-warning"><i class='icon icon-edit'></i> 部署</button>
    </div>
</div>
<div class="col-md-12">
    <table id="deployTable"></table>
</div>

<!-- Content Header (Page header) -->


<%--<div class="wrapper row-offcanvas row-offcanvas-left">
    <jsp:include page="/common/leftMenu.jsp"></jsp:include>
    <!-- Right side column. Contains the navbar and content of the page -->
</div><!-- ./wrapper -->--%>

<!-- editDialog-->
<div class="modal fade" id="addDeployerModal" tabindex="-1" aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="editdictLabel">部署流程</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="config/process/deploy" method="post" id="deployForm">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">流程定义文件</label>
                        <div class="col-sm-6">
                            <input type="file" class="form-control" name="upload" accept="application/zip">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-edit-save" form="deployForm" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript" src="static/js/monitor/deploy.js"></script>
</body>
</html>

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
    <title>管理系统-我的申请</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>我的申请
        <small>我发起的申请</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-dashboard"></i> 首页</a></li>
        <li><a href="#"> 我的工作台</a></li>
        <li class="active">我的申请</li>
    </ol>
</section>
<div class="panel scroll panel-info " style="min-height: 500px;">
    <div class="col-md-12" id="dictTool">
        <div class="btn-group btn-group-sm" role="group" aria-label="...">
            <!-- <button type="button" class="btn btn-del btn-danger"><i class='icon icon-remove'></i> 撤销</button> -->
            <button type="button" class="btn btn-show btn-info"><i class='icon icon-eye-open'></i> 查看流程</button>
        </div>
    </div>
    <div class="col-md-12">
        <table id="dictTable"></table>
    </div>
</div>
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
                <h4 class="modal-title" id="dictLabel">当前流程节点</h4>
            </div>
            <div class="modal-body">
                <img id="processImg" src="" class="img-rounded img-responsive"/>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="static/js/process/mine_process.js"></script>
</body>
</html>

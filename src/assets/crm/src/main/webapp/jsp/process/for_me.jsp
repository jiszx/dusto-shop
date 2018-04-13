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
    <title>管理系统-待处理</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>待处理
        <small>需要我处理的事务</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-dashboard"></i> 首页</a></li>
        <li><a href="#"> 我的工作台</a></li>
        <li class="active">待处理</li>
    </ol>
</section>
<div class="panel scroll panel-info " style="min-height: 500px;">
    <div class="col-md-12" id="dictTool">
        <div class="btn-group btn-group-sm" role="group" aria-label="...">
            <!-- <button type="button" class="btn btn-primary"data-toggle="modal" data-target="#addDictModal"><i class='icon icon-plus'></i> 委托</button> -->
            <button type="button" class="btn btn-warning btn-look"><i class='icon icon-edit'></i> 处理</button>
            <button type="button" id="btn-detail" class="btn btn-info"><i class='icon icon-eye-open'></i> 查看流程图</button>
        </div>
    </div>
    <div class="col-md-12">
        <table id="dictTable"></table>
    </div>
</div>

<!-- addDialog-->
<div class="modal fade" id="addDictModal" tabindex="-1" role="dialog"
     aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
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
<script type="text/javascript" src="static/js/process/waitMe.js"></script>
</body>
</html>

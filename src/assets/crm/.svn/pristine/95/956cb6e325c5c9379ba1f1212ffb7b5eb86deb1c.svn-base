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
    <title>管理系统-套餐申请</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>套餐组合页面<small>组合套餐</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-dashboard"></i> 首页</a></li>
        <li><a href="#"> 促销政策管理</a></li>
        <li class="active">套餐申请</li>
    </ol>
</section>
<div class="col-md-12" id="dictTool">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
        <a href="combination/add.html" class="btn btn-warning"><i class='icon icon-edit'></i> 新增</a>
        <button type="button" id="btn-detail" class="btn btn-info"><i class='icon icon-eye-open'></i> 修改</button>
        <button type="button" class="btn btn-warning btn-look"><i class='icon icon-edit'></i> 详情</button>
        <button type="button" id="btn-details" class="btn btn-info"><i class='icon icon-eye-open'></i> 删除</button>
    </div>
</div>
<div class="col-md-12">
    <table id="dictTable"></table>
</div>

<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="static/js/combination/index.js"></script>
</body>
</html>
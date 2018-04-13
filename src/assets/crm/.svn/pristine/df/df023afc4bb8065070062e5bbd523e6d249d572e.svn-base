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
    <title>管理系统-岗位管理</title>
    <link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
    <jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>销售区域管理<small>设定销售业务省的区域范围</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 销售组织管理</a></li>
        <li class="active">销售区域设定</li>
    </ol>
</section>
<div class="col-sm-12" style="padding: 0px;">
    <div class="col-sm-4 col-md-3" style="padding: 0px;">
        <div class="panel scroll panel-info " style="min-height: 500px;">
            <div class="panel-heading">
                组织机构
            </div>
            <div class="panel-body" style="overflow: auto;clear:both;">
                <ul id="tree" class="ztree" style="width: 100%;"></ul>
            </div>
        </div>
    </div>
    <div class="col-sm-8 col-md-9" style="padding: 0px;">
        <div class="panel scroll panel-info " style="min-height: 500px;">
            <div class="panel-heading">区域信息<button class="btn btn-grant btn-info btn-xs" style="padding:6px 12px;position:absolute;top:5px;right:10%;"><i class="icon approval_img"></i>授权</button></div>
            <div class="panel-body" style="overflow-y: auto;clear:both;">
                <ul id="areatree" class="ztree" style="width: 100%;"></ul>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="static/js/organization/area.js"></script>
</body>
</html>

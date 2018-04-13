<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>管理系统-运营公司范围设定</title>
    <link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
    <jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>运营公司范围设定
        <small>运营公司范围设定</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 组织架构管理</a></li>
        <li class="active">运营公司范围设定</li>
    </ol>
</section>
<div class="col-sm-4 col-md-3" style="padding: 0px;">
    <div class="panel scroll panel-info ">
        <div class="panel-heading">
            运营主体
        </div>
        <div class="panel-body" style="overflow: auto;">
            <ul class="nav nav-pills nav-stacked" id="rdcList" role="tablist">
                <c:forEach items="${merch}" var="m">
                    <li data="${m.sapCustomerId}"><a href="#">${m.name}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<div class="col-sm-8 col-md-9" style="padding: 0px;">
    <div class="panel scroll panel-info ">
        <div class="panel-heading">区域信息
            <button class="btn btn-grant btn-info btn-xs" style="padding:6px 12px;position:absolute;top:5px;right:10%;">
                <i class="icon approval_img"></i>授权
            </button>
        </div>
        <div class="panel-body">
            <ul id="areatree" class="ztree" style="width: 100%;"></ul>
        </div>
    </div>
</div>
<!-- Modal -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="static/js/organization/companyArea.js"></script>
</body>
</html>

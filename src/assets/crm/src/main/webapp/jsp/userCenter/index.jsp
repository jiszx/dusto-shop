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
    <title>管理系统-用户中心</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>用户中心
        <small>知识库管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-dashboard"></i> 首页</a></li>
        <li class="active">用户中心</li>
    </ol>
</section>
<form class="form-horizontal col-md-8 col-md-offset-2" method="post" action="userCenter/edit" id="editUserForm">
    <div class="form-group">
        <label for="loginName" class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="loginName" readonly value="${user.loginName}">
        </div>
    </div>
    <div class="form-group">
        <label for="name" class="col-sm-2 control-label">姓名</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="name" name="name" value="${user.name}">
        </div>
        <small class="help-block col-sm-4"></small>
    </div>
    <div class="form-group">
        <label for="saleman" class="col-sm-2 control-label">是否销售人员</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="saleman" readonly value='${user.isSalesman=="1"?"是":"否"}'>
        </div>
        <small class="help-block col-sm-4"></small>
    </div>
    <div class="form-group">
        <label for="contactTel" class="col-sm-2 control-label">联系电话</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="contactTel" name="contactTel" value="${user.contactTel}">
        </div>
        <small class="help-block col-sm-4"></small>
    </div>

    <div class="form-group">
        <label for="email" class="col-sm-2 control-label">邮箱</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="email" name="email" value="${user.email}">
        </div>
        <small class="help-block col-sm-4"></small>
    </div>

    <div class="form-group">
        <label for="cardNo" class="col-sm-2 control-label">工号</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="cardNo" name="cardNo" value="${user.cardNo}">
        </div>
        <small class="help-block col-sm-4"></small>
    </div>

    <div class="form-group">
        <div class="col-sm-6 text-center">
            <button type="submit" id="btn-edit-save" class="btn btn-primary">修改</button>
        </div>
    </div>

</form>
<!-- Main content -->

<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/js/userCenter/index.js"></script>
</body>
</html>

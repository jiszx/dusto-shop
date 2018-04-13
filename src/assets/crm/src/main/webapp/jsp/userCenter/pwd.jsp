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
    <title>管理系统-修改密码</title>
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
<form class="form-horizontal col-md-8 col-md-offset-2" method="post" action="userCenter/pwd.jhtml" id="editUserForm">
    <div class="form-group">
        <label for="oldPwd" class="col-sm-2 control-label">原密码</label>
        <div class="col-sm-6">
            <input type="password" class="form-control" id="oldPwd" name="oldPwd">
        </div>
    </div>
    <div class="form-group">
        <label for="newPwd" class="col-sm-2 control-label">新密码</label>
        <div class="col-sm-6">
            <input type="password" class="form-control" id="newPwd" name="renewPwd">
        </div>
        <small class="help-block col-sm-4"></small>
    </div>
    <div class="form-group">
        <label for="renewPwd" class="col-sm-2 control-label">重复密码</label>
        <div class="col-sm-6">
            <input type="password" class="form-control" id="renewPwd" name="newPwd">
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
<script type="text/javascript" src="static/js/userCenter/pwd.js"></script>
</body>
</html>

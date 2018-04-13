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
    <title>关于我们</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body>
<div class="container-fluid">
    <h2 class="col-md-12 text-center">关于我们</h2>
    <div class="col-md-12">
        <small class="pull-right">2016-09-18</small>
    </div>
    <div class="col-md-12">
        <small class="col-md-12" style="text-align:center;"><h4>CRM系统APP简介</h4></small>
    </div>
    <hr class="col-md-12">
    <div class="col-md-12">这是我们正在开发的应用</div>
    <hr class="col-md-12">

</div>
<jsp:include page="/common/footjs.jsp"></jsp:include>
</body>
</html>

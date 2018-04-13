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
    <title>管理系统-生意模式VS品牌</title>
    <link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
    <jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>生意模式品牌设置
        <small>各个生意模式品牌</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 配置</a></li>
        <li class="active">生意模式品牌设置</li>
    </ol>
</section>
<div class="col-sm-12" style="padding: 0px;">
    <div class="col-sm-4 col-md-3" style="padding: 0px;">
        <div class="panel panel-info ">
            <div class="panel-heading">
                生意模式
            </div>
            <div class="panel-body" style="overflow: auto;">
                <ul class="nav nav-pills nav-stacked" id="rdcList" role="tablist">
                    <c:forEach items="${models}" var="r">
                        <li data="${r.chooseVal}"><a href="#">${r.showText}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <div class="col-sm-8 col-md-9" style="padding: 0px;">
        <div class="panel panel-info ">
            <div class="panel-heading">代理品牌
                <button class="btn btn-grant btn-info btn-xs"
                        style="padding:6px 12px;position:absolute;top:5px;right:10%;"><i class="icon approval_img"></i>授权
                </button>
            </div>
            <div class="panel-body" style="overflow-y: auto;">
                <form class="from form-horizontal" id="brandForm">
                    <c:forEach items="${brands}" var="s">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="brand" value="${s}">${s}
                            </label>
                        </div>
                    </c:forEach>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="static/js/monitor/modelandbrand.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var minHeight = document.body.clientHeight;
        var divheight = $(".content-header").outerHeight(true);
        var minPanelHeight = parseInt(minHeight) - parseInt(divheight) - 70 + "px";
        $(".panel-info").css("height", minPanelHeight);
        var ztreeHeight = parseInt(minHeight) - parseInt(divheight) - 150 + "px";
        $("#areatree").css({"height": ztreeHeight, "overflow": "auto"})
    });
</script>
</body>
</html>

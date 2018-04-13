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
    <title>管理系统-RDC区域设定</title>
    <link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
    <jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>RDC区域设定<small>设定RDC的覆盖区域范围</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 配置与监控</a></li>
        <li class="active">RDC仓区域设定</li>
    </ol>
</section>
<div class="col-sm-12" style="padding: 0px;">
    <div class="col-sm-4 col-md-3" style="padding: 0px;">
        <div class="panel panel-info ">
            <div class="panel-heading">
                RDC仓库
            </div>
            <div class="panel-body" style="overflow: auto;">
                <ul class="nav nav-pills nav-stacked" id="rdcList" role="tablist">
                    <c:forEach items="${rdc}" var="r">
                        <li data="${r.chooseVal}"><a href="#" >${r.showText}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <div class="col-sm-8 col-md-9" style="padding: 0px;">
        <div class="panel panel-info " >
            <div class="panel-heading">区域信息<button class="btn btn-grant btn-info btn-xs" style="padding:6px 12px;position:absolute;top:5px;right:10%;"><i class="icon approval_img"></i>授权</button></div>
            <div class="panel-body" style="overflow-y: auto;">
                <ul id="areatree" class="ztree" style="width: 100%;"></ul>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="static/js/monitor/rdcConfig.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var minHeight = document.body.clientHeight;
		var divheight = $(".content-header").outerHeight(true);
		var minPanelHeight = parseInt(minHeight) - parseInt(divheight)-70+"px";
		$(".panel-info").css("height",minPanelHeight);
		var ztreeHeight = parseInt(minHeight) - parseInt(divheight)-150+"px";
		$("#areatree").css({"height":ztreeHeight,"overflow":"auto"})
		
	});
</script>
</body>
</html>

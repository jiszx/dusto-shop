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
    <title>管理系统-公司销售组织设定</title>
    <link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
    <jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>公司销售组织设定
        <small>公司销售组织设定</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 组织架构管理</a></li>
        <li class="active">公司销售组织设定</li>
    </ol>
</section>

<div class="panel scroll panel-info col-sm-6 col-md-6">
    <div class="panel-heading">
        组织机构
        <button class="btn btn-grant btn-info btn-xs" style="padding:6px 12px;position:absolute;top:5px;right:10%;">
            <i class="icon approval_img"></i>绑定
        </button>
    </div>
    <div class="panel-body">
        <ul class="nav nav-pills nav-stacked" id="rdcList" role="tablist">
            <c:forEach items="${dict['SAP_ORG_CODE']}" var="r">
                <li data="${r.chooseVal}"><a href="#">${r.showText}（${r.chooseVal}）</a></li>
            </c:forEach>
        </ul>
    </div>
</div>
<div class="panel scroll panel-info col-sm-6 col-md-6">
    <div class="panel-heading">
        公司代码
    </div>
    <div class="panel-body" style="overflow: auto;">
        <form class="from form-horizontal" id="brandForm">
            <c:forEach items="${dict['SAP_COM_CODE']}" var="s">
                <div class="radio">
                    <label>
                        <input type="radio" name="brand" value="${s.chooseVal}">${s.showText}（${s.chooseVal}）
                    </label>
                </div>
            </c:forEach>
        </form>
    </div>
</div>

<!-- Modal -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="static/js/organization/companyConfig.js"></script>
</body>
</html>

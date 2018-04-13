<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>管理系统-客户价格设置</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link rel="stylesheet" href="static/table/new/bootstrap-editable.css">
<link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
<style>
body {
	font-family: "微软雅黑";
}

</style>
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			合作盐业公司调拨差价维护 <small> </small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 客户管理</a></li>
			<li class="active">产品价格管理</li>
		</ol>
	</section>
	<div class="form-horizontal row" style="margin: 0 0 20px 0;">
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title">合作盐业公司名称：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" id="selectcust"
					placeholder="客户名称...">
			</div>
		</div>
	</div>
	<div class="col-md-12" id="dictTool">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button id="searchButton" class="btn btn-primary" type="button">
				<i class="icon icon-search"> </i>查询
			</button>
		</div>
	</div>
	<div class="row" style="margin: 0 0 20px 0;">
		<div class="col-md-12">
			<%-- 数据表格 --%>
			<table id="price-table" class="contract-box"
				data-show-refresh="true" data-show-columns="true">
			</table>
		</div>
	</div>
	
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript"	src="static/table/new/bootstrap-table-editable.js"></script>
	<script type="text/javascript"	src="static/table/new/bootstrap-editable.js"></script>
	<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript"	src="static/js/customer/saltAdjust.js"></script>
</body>
</html>

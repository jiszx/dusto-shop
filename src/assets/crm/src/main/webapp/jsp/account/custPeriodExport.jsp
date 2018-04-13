<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
<title>管理系统-客户对账</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			客户本期发生明显<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">客户资金管理</a></li>
			<li class="active">客户本期发生明显</li>
		</ol>
	</section>

	<div class="col-md-12">
		<table class="table table-hover table-condensed table-striped"
			border="1">
			<tr>
				<td>销售组织</td>
				<td colspan="8">成都益盐堂</td>
			</tr>
			<tr>
				<td>客户名称</td>
				<td colspan="8">成都锦江盐业批发部</td>
			</tr>
			<tr>
				<td>账户类型</td>
				<td colspan="8">现金账户</td>
			</tr>
			<tr>
				<td>期间</td>
				<td colspan="8">2016-01</td>
			</tr>
			<tr>
				<td>起初余额</td>
				<td colspan="8">100,000</td>
			</tr>
			<tr>
				<td>本期增加</td>
				<td colspan="8">90,000</td>
			</tr>
			<tr>
				<td>本期减少</td>
				<td colspan="8">70,000</td>
			</tr>
			<tr>
				<td>期末余额</td>
				<td colspan="8">120,000</td>
			</tr>
		</table>
		<table id="custPeriod"></table>
	</div>

	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript"
		src="static/js/account/custPeriodExport.js"></script>
</body>
</html>

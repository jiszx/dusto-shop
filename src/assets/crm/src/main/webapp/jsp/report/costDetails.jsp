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
<title>管理系统-报表统计</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		统计报表<small>费用池资金出入明细表</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
		<li class="active">费用池明细表</li>
	</ol>
</section>
<div class="col-md-12" id="dictTool">
	<div class="btn-group btn-group-sm" role="group" aria-label="...">
		<button type="button" id="btn-detail" class="btn btn-info"
				data-toggle="modal"  onclick="opendata()">
			<i class='icon icon-search'></i> 导出
		</button>
	</div>
</div>
<div class="col-md-12">
	<table id="orderTable">

	</table>
</div>

	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<script type="text/javascript"
		src="static/table/bootstrap-table.min.js"></script>
	<script type="text/javascript"
		src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" src="static/js/report/costDetails.js"></script>
</body>
</html>

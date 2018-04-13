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
<section class="content-header">
	<h1>
		统计报表<small>客户资金流水表</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
		<li class="active">客户资金流水表</li>
	</ol>
</section>
<div class="row" style="padding: 0 0 20px 0;">
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: auto !important">客户名称：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入客户名称"
					style="padding: 0; width: 70%;" id="custname">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input3" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: auto !important">账户类型：</label>
			<div class="input-box-list-value">
				<select id="type" class="form-control no-appearance">
					<option value="1">正数发票</option>
					<option value="2">负数发票</option>
				</select>
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input3" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: auto !important">流水类型：</label>
			<div class="input-box-list-value">
				<select id="type" class="form-control no-appearance">
					<option value="1">正数发票</option>
					<option value="2">负数发票</option>
				</select>
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input3" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: auto !important">所属期间：</label>
			<div class="input-box-list-value">
				<select id="type" class="form-control no-appearance">
					<option value="1">正数发票</option>
					<option value="2">负数发票</option>
				</select>
			</div>
		</div>
		<div class="form-group col-md-offset-6 col-md-3  col-sm-6 input-box-list">
			<button class="btn btn-primary" id="btn-search">
				<i class="icon icon-search"></i>&nbsp;&nbsp;开始搜索
			</button>
		</div>
</div>
<div class="col-md-12" id="dictTool">
	<div class="btn-group btn-group-sm" role="group" aria-label="...">
		<!-- <button type="button" id="btn-detail" class="btn btn-info"
				data-toggle="modal"  onclick="opendata()">
			<i class='icon icon-search'></i> 导出
		</button> -->
	</div>
</div>
<div class="col-md-12">
	<table id="custAccountDetailsTable">
	</table>
</div>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" src="static/js/report/custAccountDetails.js"></script>
</body>
</html>

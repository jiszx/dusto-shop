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
<link href="static/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		统计报表<small>应收发票表</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
		<li class="active">应收发票</li>
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
			<label for="input2" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: auto !important">发票号码：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入来款账号"
					style="padding: 0; width: 70%;" id="invoiceNo">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input3" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: auto !important">发票类型：</label>
			<div class="input-box-list-value">
				<select id="type" class="form-control no-appearance">
					<option value="1">正数发票</option>
					<option value="2">负数发票</option>
				</select>
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: auto !important">开票日期：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="请输入开始时间" id="startTime" style="width:30%;display:inline-block"><span>至</span>
		        <input type="text" class="form-control" placeholder="请输入结束时间" id="endTime" style="width:30%;display:inline-block">
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
	<table id="invoiceItemTable">
	</table>
</div>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript"	src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="static/js/report/invoice.js"></script>
</body>
</html>

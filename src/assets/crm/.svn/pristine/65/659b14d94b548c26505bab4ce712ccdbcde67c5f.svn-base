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
<title>管理系统-销售政策核销明细</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link href="static/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<style>
body .cn {
	font-family: "微软雅黑";
}

ul, li {
	list-style: none;
}

select {
	-webkit-appearance: none;
	-webkit-border-radius: 0;
}

.nav.nav-pills>li {
	padding: 5px 0;
}

.nav.nav-pills>li>a {
	padding: 5px 15px;
	background: rgba(0, 0, 0, .05);
	border: none;
	transition: 0.2s;
	font-size: 13px;
}

.nav.nav-pills>li.active>a, .nav.nav-pills>li.active>a:hover {
	color: #fff;
	background: #3c8dbc;
	font-weight: normal;
}

.nav.nav-pills>li>a:hover {
	background: hsla(202, 68%, 74%, .35);
	font-weight: normal;
}

#stepLink2 {
	position: fixed;
	top: 0;
	right: 0;
	padding: 15px;
	z-index: 2000;
	background-color: rgba(255, 255, 255, .75);
	box-shadow: -2px 3px 5px hsla(0, 0%, 0%, .15);
	transition: 0.3s;
	opacity: 0;
}

#stepLink2:hover {
	background-color: #fff;
}

.font12 {
	font-size: 12px;
}

.form-horizontal .input-box-list {
	display: table;
	margin: 10px 0 0 0;
	padding-left: 10px;
}

.input-box-list-title, .input-box-list-value {
	display: table-cell;
}

.input-box-list-title {
	width: 100px;
	text-align: right;
	vertical-align: middle;
	padding-right: 5px;
}

.input-box-list-value textarea {
	resize: none;
}

input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
	font-size: 12px;
}

.btn-long {
	padding: 8px 6.18%;
}

.block-save-link {
	float: right;
	font-size: 13px;
	padding: 4px 10px 0;
}

.block-save-link:hover {
	text-decoration: underline;
}

.contact-box, .contact-box-new, .contact-box-add {
	border: 1px solid hsla(0, 0%, 95%, 1);
	background-color: #fff;
	height: 239px;
	padding: 20px 15px;
	max-height: 239px;
	overflow: hidden;
}

.bank-box, .bank-box-add {
	border: 1px solid hsla(0, 0%, 95%, 1);
	background-color: #fff;
	height: 190px;
	padding: 20px 15px;
	max-height: 190px;
	overflow: hidden;
}

.bank-box-add {
	text-align: center;
	line-height: 180px;
	font-size: 18px;
	color: hsla(0, 0%, 65%, 1);
	border: 1px dashed hsla(0, 0%, 90%, 1);
	cursor: pointer;
	transition: 0.2s;
}

.contact-box-add {
	text-align: center;
	line-height: 220px;
	font-size: 18px;
	color: hsla(0, 0%, 65%, 1);
	border: 1px dashed hsla(0, 0%, 90%, 1);
	cursor: pointer;
	transition: 0.2s;
}

.contact-box-add:hover, .bank-box-add:hover {
	color: hsla(0, 0%, 35%, 1);
	border-color: hsla(0, 0%, 80%, 1);
}

.contact-box-new:before {
	content: '';
	height: 3px;
	display: block;
	background-color: #f39c12;
	margin: -20px -15px 0 -15px;;
}

.contact-box:before, .bank-box:before {
	content: '';
	height: 3px;
	display: block;
	background-color: #3c8dbc;
	margin: -20px -15px 0 -15px;;
}
</style>
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			销售政策<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 销售政策管理</a></li>
			<li class="active">核销明细</li>
		</ol>
	</section>
	<%-- block 1 --%>
	<div class="page-header" id="block-1">
		<h4 class="text-info">
			<strong>1.&nbsp;</strong>申请基本信息&nbsp;-&nbsp; <small>Basic
				Information.</small>
		</h4>
	</div>
	<div class="form-horizontal row" style="margin: 0 0 20px 0;">
		<!-- <form class="form-horizontal" method="post"	action="salePolicy/header/eidt"  id="policyheaderForm"> -->
		<input type="hidden" value="${policy.id}" id="policyid">
		<input type="hidden" value="${policy.organizationId }" id="sorganizationId">
		<input type="hidden" value="${policy.attribute1}" id="policyareas">
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="dept" class=" font12 input-box-list-title">申请部门：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" value="${dept}" readonly="readonly">
			</div>
		</div>
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="description" class=" font12 input-box-list-title">描述：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" value="${policy.description}" readonly="readonly">
			</div>
		</div>
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label class=" font12 input-box-list-title" for="policyType">政策类型：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" value="${policyType.name}" readonly="readonly">
			</div>
		</div>
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="bDate" class=" font12 input-box-list-title">政策开始时间：</label>
			<div class="input-box-list-value">
				<!-- <input type="date" class="form-control" name="bDate" id="startTime"> -->
				<input type="text" class="form-control"  value="${bdate}" readonly="readonly">
			</div>
		</div>
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="eDate" class=" font12 input-box-list-title">政策结束时间：</label>
			<div class="input-box-list-value">
			    <input type="text" class="form-control" value="${eDate}" readonly="readonly">
			</div>
		</div>
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="costSubjectid" class=" font12 input-box-list-title">成本中心：</label>
			<div class="input-box-list-value">
				<select class="form-control" name="costSubjectid" id="costSubjectid" required="required" disabled="disabled">
					<option value="1">成本中心1</option>
				</select>
			</div>
		</div>

		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="costTypeid" class=" font12 input-box-list-title">费用类型：</label>
			<div class="input-box-list-value">
				<select class="form-control" name="costTypeid" id="costTypeid" required="required" disabled="disabled">
					<option value="1">市场费用</option>
				</select>
			</div>
		</div>
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="amt" class=" font12 input-box-list-title">申请金额：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" value="${policy.amt }" readonly="readonly">
			</div>
		</div>
	</div>
	<%-- block 2 --%>
	<div class="page-header" id="block-1">
		<h4 class="text-info">
			<strong>2.&nbsp;</strong>核销明细&nbsp;-&nbsp; <small>Basic
				Information.</small>
		</h4>
	</div>
	<div class="col-md-12" id="policyWriteOffTool">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button id="submit" type="button" class="btn btn-success">
				<i class='icon icon-check'></i>导出
			</button>
		</div>
	</div>
	<div class="col-md-12">
		<table id="policyWriteOffTable"></table>
	</div>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="static/js/salepolicy/policyWriteOffDetails.js"></script>
</body>
</html>

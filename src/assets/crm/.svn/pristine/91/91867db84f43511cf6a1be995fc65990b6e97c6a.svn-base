<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>退货订单录入</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
<style>
body {
	font-family: "微软雅黑";
}

ul, li {
	list-style: none;
}

select {
	-webkit-appearance: none;
	-webkit-border-radius: 0;
}

.font12 {
	font-size: 12px;
}

.form-horizontal .input-box-list {
	display: table;
	margin: 10px 0 0 0;
	padding-left: 10px;
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

input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
	font-size: 12px;
}

.factory-select-box .dropdown {
	margin-bottom: 10px;
}

.factory-select-box .dropdown a.btn {
	padding-top: 10px;
	padding-bottom: 10px;
	background-color: #fff;
	border-radius: 0;
}

.factory-select-box .dropdown a.btn span.caret {
	margin-top: 8px;
}

.factory-select-box .dropdown ul {
	width: 100%;;
}

.account-panel {
	border: 1px solid hsla(0, 0%, 90%, 1);
	border-top: 2px solid #a94442;
	padding: 20px 20px 10px;
	background-color: #fff;
}

.account-panel>div>p {
	margin: 0;
}

.account-item {
	margin-bottom: 15px;
}

.account-item>span {
	margin: 0 5px;
}

.account-item:last-child {
	border-top: 1px dashed hsla(0, 0%, 90%, 1);
	padding-top: 20px;
}

.order-item {
	float: left;
	line-height: 30px;
	margin-right: 30px;
}

.chosen-container .chosen-results {
	max-height: 120px !important;
}

.contact-box, .contact-box-new, .contact-box-add {
	border: 1px solid hsla(0, 0%, 95%, 1);
	background-color: #fff;
	height: 239px;
	padding: 20px 15px;
	max-height: 239px;
	overflow: hidden;
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

.file-list {
	list-style: none;
	font-family: 'FontAwesome';
	padding-left: 10px;
	padding-top: 20px;
	max-height: 239px;
	overflow: auto;
}

.file-list>li {
	list-style: none;
	padding: 6px 0;
	border-bottom: 1px dashed hsla(0, 0%, 90%, 1);
}

.file-list>li>span.file-name {
	padding-left: 5px;
}

.file-list>li:before {
	content: "\f0f6";
}

#file-item-add-box {
	overflow: hidden;
}

#file-select {
	position: absolute;
	width: 100%;
	height: 100%;
	left: 0;
	right: 0;
	opacity: 0;
	cursor: pointer;
}
</style>
</head>
<body class="container-fluid content">

	<section class="content-header">
		<h1>
			订单管理 <small>退货订单录入</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li class="active">退货订单录入</li>
		</ol>
	</section>

	<form id="orderFrom" enctype="multipart/form-data"  action="order/addOrderFiles" method="post">
		<div class="page-header">
			<h5>
				订单头详情&nbsp;-&nbsp;<small>Order Header.</small>
			</h5>
		</div>
		<%-- 选择用户 --%>
		<div class="row">
			<div class="form-group col-md-4 col-sm-4 input-box-list">
				<p class="text-muted order-item">原订单编号:</p>
				<div class="form-group" style="width: 100%;">
					<select class="findCustom" data-placeholder="请选择订单..."
						style="width: 350px;" tabindex="1" id="orderList">
					</select>
				</div>
			</div>
			<div class="col-md-4  col-sm-4 factory-select-box">
				<p class="text-muted order-item">客户名称:</p>
				<div class="form-group" style="width: 100%;">
					<input type="text" class="findDelivery" style="width: 350px;"
						id="custname" disabled="disabled">
				</div>
			</div>
			<div class="col-md-4  col-sm-4 factory-select-box">
				<p class="text-muted order-item">SAP订单编号:</p>
				<div class="form-group" style="width: 100%;">
					<input type="text" class="findDelivery" style="width: 350px;"
						id="sapOrderId" disabled="disabled">
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4  col-sm-4 factory-select-box">
				<p class="text-muted order-item">原订单类型:</p>
				<div class="form-group" style="width: 100%;">
					<input type="text" class="findDelivery" style="width: 350px;"
						id="orderType" disabled="disabled">
				</div>
			</div>
			<div class="col-md-4  col-sm-4 factory-select-box">
				<p class="text-muted order-item">发货RDC:</p>
				<div class="form-group" style="width: 100%;">
					<select class="findDelivery" data-placeholder="请选择RDC..."
						style="width: 350px;" tabindex="3" id="rdcCode"
						disabled="disabled">
						<option></option>
						<c:forEach items="${dict.get('VIRTUAL_WAREHOUSE_CODE') }"
							var="cust_type">
							<option value="${cust_type.chooseVal}">${cust_type.showText}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="col-md-4  col-sm-4 factory-select-box">
				<p class="text-muted order-item">退货原因:</p>
				<div class="form-group" style="width: 100%;">
					<select class="findDelivery" data-placeholder="请选择退货原因..."
						style="width: 350px;" tabindex="3" id="back_reason">
						<option></option>
						<c:forEach items="${dict.get('ORDER_BACK_REASON') }"
							var="back_reason">
							<option value="${back_reason.chooseVal}">${back_reason.showText}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4  col-sm-4 factory-select-box">
				<p class="text-muted order-item">OA系统单号:</p>
				<div class="form-group" style="width: 100%;">
					<input type="text" class="findDelivery" style="width: 350px;"
						id="oaOrderNo" >
				</div>
			</div>
		</div>
		<%-- 订单详情 --%>
		<div class="page-header" style="margin-top: 20px">
			<h5>
				订单项目详情&nbsp;-&nbsp;<small>Order Details.</small>
			</h5>
		</div>
		<%-- 数据表格 --%>
		<table id="order-table"></table>
		</div>
		<div class="page-header" id="block-5">
			<h4 class="text-info">
				附件信息&nbsp;-&nbsp; <small>Enclosure.</small>
				<!-- <a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a> -->
			</h4>
		</div>
		<input name="headerId" id="headerId" type="hidden">
		<div class="row" style="margin: 0 0 20px 0;">
			<div class="col-md-4 col-sm-6 file-list-box">
				<div class="contact-box" id="orderFiles">
					<ul class="file-list" name="files">
					</ul>
				</div>
			</div>
			<div class="col-md-4 col-sm-6">
				<div class="contact-box-add" id="file-item-add-box" title="上传附件">
					<input id="file-select" type="file" onchange="addFiles()">
					<i class="icon icon-plus"></i>&nbsp;&nbsp;添加附件 <input type="hidden"
						id="delAtts">
				</div>
			</div>
		</div>
		<div class="page-header" style="margin-top: 50px">
			<h5>
				其他信息&nbsp;-&nbsp;<small>Other info.</small>
			</h5>
		</div>
		<div class="remark col-md-offset-1 col-md-12">
			<albel>备注:</albel>
			<textarea
				style="width: 80%; min-height: 60px; display: block; margin-bottom: 20px;"
				id="remark"></textarea>
		</div>
		<%-- submit button --%>
		<div class="text-center" style="padding-top: 100px; margin-top: 40px;">
			<button class="btn btn-warning" style="padding: 8px 25px;"
				type="button" id="btn-save">
				<i class="icon icon-save"></i>&nbsp;&nbsp;保存
			</button>
		</div>

	</form>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<script type="text/javascript"	src="static/table/bootstrap-table.min.js"></script>
	<script type="text/javascript"	src="static/table/new/bootstrap-table.js"></script>
	<script type="text/javascript"	src="static/table/new/bootstrap-table-export.js"></script>
	<script type="text/javascript"	src="static/table/new/bootstrap-table-editable.js"></script>
	<script type="text/javascript"	src="static/table/new/bootstrap-editable.js"></script>
	<script type="text/javascript"	src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="static/js/order/addOrderBack.js"></script>
	<script type="text/javascript">
		function getverification(value) {
			var data = new Object();
			<c:forEach items="${dict['ORDER_TYPE']}" var="type">
			data["${type.chooseVal}"] = "${type.showText}"
			</c:forEach>
			if (data[value]) {
				return data[value];
			} else {
				return "未知";
			}
		}
		function getCustType(value) {
			var data = new Object();
			<c:forEach items="${dict['CUST_MERCH_TYPE']}" var="type">
			data["${type.chooseVal}"] = "${type.showText}"
			</c:forEach>
			if (data[value]) {
				return data[value];
			} else {
				return "未知";
			}
		}
	</script>
</body>
</html>

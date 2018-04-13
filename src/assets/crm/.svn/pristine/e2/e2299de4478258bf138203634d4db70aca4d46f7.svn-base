<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>金税发票-已开发票查询</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			金税发票已开发票查询<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 金税发票</a></li>
			<li class="active">查询</li>
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
			<label for="input1" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: auto !important">客户编号：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入客户名称"
					style="padding: 0; width: 70%;" id="sapCustomerId">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: auto !important">开票类型：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance"
					style="padding: 0; width: 70%;" id="sourcesType">
					<option value="1">订单</option>
					<option value="2">销售发票</option>
				</select>
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: auto !important">发票类型：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance"
					style="padding: 0; width: 70%;" id="invoicesType">
					<option value="2">专票</option>
					<option value="3">普票</option>
				</select>
			</div>
		</div>
		<div class="form-group col-sm-6 col-md-3  input-box-list">
			<button class="btn btn-primary" id="btn-search">
				<i class="icon icon-search"></i>&nbsp;&nbsp;开始搜索
			</button>
		</div>
	</div>
	<div class="col-md-12">
		<table id="invoiceTable"></table>
	</div>
	<script type="text/javascript">
		function getcustTypeValue(type){
		//CUST_MERCH_TYPE
			var data = new Object();
			<c:forEach items="${dict['CUST_MERCH_TYPE']}" var="Type">
			data["${Type.chooseVal}"] = "${Type.showText}"
			</c:forEach>
			if (data[type]) {
				return data[type];
			} else {
				return "未知";
			}
		}
		function getVerifieType(type) {
			var data = new Object();
			<c:forEach items="${dict['RECEIVE_VERIFIE_TYPE']}" var="a">
			data["${a.chooseVal}"] = "${a.showText}"
			</c:forEach>
			if (data[type]) {
				return data[type];
			} else {
				return "";
			}
		}
	</script>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/invoiceSecurity/aisionInvoices.js"></script>
</body>
</html>

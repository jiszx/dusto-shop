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
		<div class="form-group">
			<label class="col-sm-12 control-label" style="text-align:center;padding:14px 0 0">${balance.orgname}-${balance.custname}--${period}(期间)--${balance.ytd}(期初)--${balance.dAmt}(本期增加)--${balance.cAmt}(本期减少)--${balance.ptd}(期末)</label>
		</div>
	</div>
	<!-- <div class="col-md-12" id="custPeriodTool">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<a type="button" class="btn btn-primary"
				href="account/custperiodexport.html"><i class='icon icon-share' style="padding-right:5px;"></i>导出</a>
		</div>
	</div> -->
	<div class="col-md-12">
		<table id="custPeriod"></table>
	</div>
	<script type="text/javascript">
	function getTypeValue(state) {
		var data = new Object();
		<c:forEach items="${dict['MERCH_LOG_TYPE']}" var="type">
		data["${type.chooseVal}"] = "${type.showText}"
		</c:forEach>
		if (data[state]) {
			return data[state];
		} else {
			return "未知";
		}

	}
	function getAccountTypeValue(state) {
		var data = new Object();
		<c:forEach items="${dict['MERCH_ACCOUNT_TYPE']}" var="type">
		data["${type.chooseVal}"] = "${type.showText}"
		</c:forEach>
		if (data[state]) {
			return data[state];
		} else {
			return "未知";
		}

	}
	</script>
	<input type="hidden" id="orgid" value="${orgid}"> 
	<input type="hidden" id="custid" value="${custid}"> 
	<input type="hidden" id="accountType" value="${accountType}"> 
	<input type="hidden" id="period" value="${period}"> 
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/account/custPeriod.js"></script>
</body>
</html>

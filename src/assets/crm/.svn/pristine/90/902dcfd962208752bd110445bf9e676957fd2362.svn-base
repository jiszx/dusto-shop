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
<title>配置与监控-RFC任务列表</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<style type="text/css">
.selectWidth {
	width: 70% !important;
	margin: 10px 0;
}

.hide {
	display: none;
}

.spred {
	margin: 20px 0;
}
</style>
</head>
<body class="container-fluid content">
	<section class="content-header">
	<h1>配置与监控<small>RFC任务列表</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
		<li><a href="#">配置与监控</a></li>
		<li class="active">RFC任务列表</li>
	</ol>
	</section>
	<div class="col-md-12" style="padding: 0">
		<form class="form-inline" id="searchForm">
			<c:set var="rfcType" value="<%=com.hhnz.jco.enu.RfcExeType.values()%>" />
			<c:set var="rfcStatus" value="<%=com.hhnz.jco.enu.RfcExeStatus.values()%>" />
			<div class="form-group col-md-3 col-sm-6 mustShow">
				<label for="executeType">任务类别</label>
				<select class="form-control selectWidth" name="executeType" id="search_type">
					<option value="">全部</option>
					<c:forEach var="type" items="${rfcType}">
						<option value="${type.name()}" >${type.getBeanDesc()}</option>
					</c:forEach>
				</select>
			</div>
		    <div class="form-group col-md-3 col-sm-6">
				<label for="status">状态</label>
				<select class="form-control selectWidth" name="status" id="search_status">
					<option value="">全部</option>
					<c:forEach var="state" items="${rfcStatus}">
						<option value="${state.name()}" >${state.getDesc()}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group col-md-3 com-sm-6 mustShow" style="margin:10px 0;">
				<!-- Split button -->
				<div class="btn-group">
					<button type="button" class="btn btn-success"  id="searchBtn"><i class="icon icon-search">&nbsp;&nbsp查询</i></button>
				</div>

			</div>
	</form>
	</div>
	<ul id="executeTab" class="nav nav-tabs">
	    <li class="active"><a href="#current" data-toggle="tab" id="rfccurrent">当前任务</a></li>
	    <li><a href="#history" data-toggle="tab" id="rfchistory">历史任务</a></li>
	</ul>
	<div class="col-md-12 tab-pane fade in active" id="current" style="padding:20px 0 0 0">
		<div class="col-md-12" style="padding: 0">
			<div class="btn-group btn-group-sm" role="group" aria-label="...">
				<button type="button" id="manualBtn" class="btn btn-bitbucket">
					<i class='icon approval_img'></i> 手工触发
				</button>
				<button type="button" id="refreshBtn" class="btn btn-info">
					<i class='icon icon-refresh'></i> 刷新参数
				</button>
				<button type="button" id="neverBtn" class="btn btn-del btn-danger">
					<i class='icon icon-remove'></i> 不再执行
				</button>
			</div>
		</div>
	</div>
	<div class="col-md-12 tab-pane fade" id="history" style="padding:0">
	</div>
	
	<div class="col-md-12">
		<table id="rfcExecuteTable"></table>
	</div>

	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/monitor/rfclist.js"></script>
</body>
</html>

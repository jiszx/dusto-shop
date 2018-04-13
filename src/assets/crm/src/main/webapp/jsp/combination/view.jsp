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
<title>管理系统-套餐组合</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			套餐详情页面<small>组合套餐</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-dashboard"></i> 首页</a></li>
			<li class="active">套餐</li>
		</ol>
	</section>
	<div class="col-md-12">
		<form class="form form-horizontal" >
			<input type="hidden" id="id" name="id" value="${h.id}">
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">套餐编码</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" value="${h.code}" readonly="readonly">
				</div>
				<small class="help-block col-sm-2"></small>
			</div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">套餐名称</label>
				<div class="col-sm-8">
					<input type="text" class="form-control"  value="${h.name}" readonly="readonly">
				</div>
				<small class="help-block col-sm-2"></small>
			</div>
			<div class="form-group hide">
				<label for="inputEmail3" class="col-sm-2 control-label">套餐价格</label>
				<div class="col-sm-8 ">
					<div class="input-group">
					<input type="text" class="form-control"  value="${h.price/100}" readonly="readonly">
					<div class="input-group-addon">元</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">套餐规格</label>
				<div class="col-sm-8 ">
					<div class="input-group">
					<input type="text" class="form-control"  value="${h.weight}" readonly="readonly">
					<div class="input-group-addon">g</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">生意模式</label>
				<div class="col-sm-8">
					<select class="form-control no-appearance" disabled="disabled">
						<option></option>
						<c:forEach items="${dict['BUSINESS_MODEL']}" var="it">
							<option value="${it.chooseVal}" ${it.chooseVal == h.modelType?"selected":""}>${it.showText}</option>
						</c:forEach>
					</select>
				</div>
				<small class="help-block col-sm-2"></small>
			</div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">套餐时间</label>
				<div class="col-sm-4">
					<input type="text" class="form-control"  value="${bdate}" readonly="readonly">
				</div>
				<div class="col-sm-4">
					<input type="text" class="form-control"  value="${edate}" readonly="readonly">
				</div>
				<small class="help-block col-sm-2"></small>
			</div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">套餐内产品</label>
				<div class="col-sm-8">
					<table id="tcnsp"></table>
				</div>
				<small class="help-block col-sm-2"></small>
			</div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">返点比例</label>
				<div class="col-sm-8">
					<table id="tss"></table>
				</div>
				<small class="help-block col-sm-2"></small>
			</div>
			</form>
			<div class="form-group">
				<div class="col-sm-10 text-center">
					<button type="button" class="btn btn-primary" id="btn-back">返回</button>
				</div>
			</div>
	</div>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/combination/view.js"></script>
</body>
</html>

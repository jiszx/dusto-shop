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
<title>订单管理-退货</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link href="static/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<style type="text/css">
 .dropdown-menu{
 width:25.3%;
 }
 .table-condensed{
 width:100%;
 }

</style>
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			退货<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 订单管理管理</a></li>
			<li class="active">退货</li>
		</ol>
	</section>
	<input type="hidden" id="orgid" value="${station.orgid}">
	<div class="row" style="padding: 0 0 20px 0;margin-left: 20px;">
		<div class="form-group col-md-3 col-sm-6 input-box-list" >
			<label for="input1" class=" font12 input-box-list-title" style="width:100px;float:left;line-height:30px;">客户名称：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" 
					   placeholder="输入客户名称" style="padding:0;width:70%;" id="custname">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list" >
			<label for="input1" class=" font12 input-box-list-title" style="width:100px;float:left;line-height:30px;">退货订单编号：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" 
					   placeholder="输入退货订单编号" style="padding:0;width:70%;" id="custname">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list" >
			<label for="input1" class=" font12 input-box-list-title" style="width:100px;float:left;line-height:30px;">原订单编号：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" 
					   placeholder="输入原订单编号" style="padding:0;width:70%;" id="custname">
			</div>
		</div>
	 </div>
	 <div class="row" style="padding: 0 0 20px 0;margin-left: 20px;">
		<div class="form-group col-md-3 col-sm-6 input-box-list" >
			<label for="input1" class=" font12 input-box-list-title" style="width:100px;float:left;line-height:30px;">销售组织：</label>
			<div class="input-box-list-value">
		         <select class="form-control"  style="padding:0;width:70%;" id="sorgid">
				</select>
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list" >
			<label for="input1" class=" font12 input-box-list-title" style="width:100px;float:left;line-height:30px;">状态：</label>
			<div class="input-box-list-value">
				<select class="form-control"  style="padding:0;width:70%;" id="states">
					<option></option>
				<c:forEach items="${dict['OM_ORDER_RETURN_TYPE']}" var="it">
				   <option value="${it.chooseVal}">${it.showText}</option>
				</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<button class="btn btn-primary" id="btn-search"><i class="icon icon-search"></i>&nbsp;&nbsp;开始搜索</button>
		</div>
	</div>
	<div class="col-md-12 col-sm-12" id="orderBackTool" style="padding:0;">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn  btn-add btn-primary" id="back_add">
				<i class='icon icon-plus'></i>录入
			</button>
			<button type="button" class="btn btn-edit btn-warning">
				<i class='icon icon-edit'></i>修改
			</button>
			<button type="button" class="btn btn-detail btn-primary">
				<i class='icon icon-edit'></i>详情
			</button>
			<button type="button" class="btn btn-del btn-danger">
				<i class='icon icon-remove'></i>删除
			</button>
			<button type="button" class="btn btn-success" id="btn-audit">
				<i class='icon icon-check'></i>提交审批
			</button>
		</div>
	</div>
	<div class="col-md-12">
		<table id="orderBackTable"></table>
	</div>
	<script type="text/javascript">
		function getStatesValue(state) {
			var data = new Object();
			<c:forEach items="${dict['OM_ORDER_RETURN_TYPE']}" var="states">
			data["${states.chooseVal}"] = "${states.showText}"
			</c:forEach>
			if (data[state]) {
				return data[state];
			} else {
				return "未知";
			}

		}
		function getCustType(type) {
			var data = new Object();
			<c:forEach items="${dict['CUST_MERCH_TYPE']}" var="type">
			data["${type.chooseVal}"] = "${type.showText}"
			</c:forEach>
			if (data[type]) {
				return data[type];
			} else {
				return "未知";
			}

		}
		function getBackReason(state) {
			var data = new Object();
			<c:forEach items="${dict['ORDER_BACK_REASON']}" var="reason">
			data["${reason.chooseVal}"] = "${reason.showText}"
			</c:forEach>
			if (data[state]) {
				return data[state];
			} else {
				return "未知";
			}

		}
	</script>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="static/js/order/orderBackList.js"></script>
</body>
</html>

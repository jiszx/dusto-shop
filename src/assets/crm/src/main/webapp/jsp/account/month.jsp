<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>管理系统-客户资金期间余额</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
<style type="text/css">
.customizeWidth {
	width: 70%;
	display: inline-block;
}
label{
width:94px;}
.input-box-list-title{width:95px;}
</style>
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			客户资金本期发生情况<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">客户资金管理</a></li>
			<li class="active">客户余额余额表</li>
		</ol>
	</section>
	<div class="row" style="padding: 0 0 20px 0;">
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px;">客户名称：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入客户名称"
					style="padding: 0; width: 70%;" id="custname">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input2" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px;">账户类型:</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance"
					style="padding: 0; width: 70%;" id="accountType">
					<option></option>
					<c:forEach items="${dict.get('MERCH_ACCOUNT_TYPE')}" var="it">
						<option value="${it.chooseVal}">${it.showText}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label class=" font12 input-box-list-title"
				style="float: left; line-height: 30px;">客户类型：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance"
					style="padding: 0; width: 70%;" id="custType">
					<option></option>
					<option value="5">零售商</option>
					<c:forEach items="${dict.get('CUST_MERCH_TYPE')}" var="it">
						<option value="${it.chooseVal}">${it.showText}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label class=" font12 input-box-list-title"
				style="float: left; line-height: 30px;">销售组织：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance"
					style="padding: 0; width: 70%;" id="sorgid">
					<option></option>
					<c:forEach items="${org}" var="orgs">
						<option value="${orgs.id}">${orgs.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input3" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px;">期间:</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance"
					style="padding: 0; width: 70%;" id="period">
				</select>
			</div>
		</div>
		<div
			class="form-group col-md-offset-3 col-md-3  col-sm-6 input-box-list">
			<button type="button" class="btn btn-primary" id="btn-search">
				<i class="icon icon-search"></i>&nbsp;&nbsp;查询
			</button>
		</div>
	</div>
	<div class="col-md-12" id="dictTool">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn btn-success" id="btn-detail">
				<i class='icon icon-eye-open'></i>本期发生明细
			</button>
			<button type="button" class="btn btn-primary hide" id="btn-update">
				<i class='icon icon-comments-alt'></i>生成本月数据
			</button>
			<button type="button" class="btn btn-info" id="btn-statement">
				<i class='icon icon-eye-open'></i>查看对账单
			</button>
			<!--  href="account/custPeriod.html"> <button type="button" id="btn-detail" class="btn btn-info"><i class='icon icon-search'></i> 本期发生明细</button>-->
			<!--<button type="button" class="btn btn-edit btn-warning"><i class='icon icon-edit'></i> 差额明细</button>-->
		</div>
	</div>
	<div class="col-md-12">
		<table id="accountMonthTable"></table>
	</div>
	
	<div class="modal fade" id="statementModal" tabindex="-1" role="dialog" aria-labelledby="statementModalLabel" aria-hidden="true" >
	    <div class="modal-dialog" style="width:985px; overflow-y: hidden;">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	                <h6 class="modal-title" id="statementModalLabel"> <i class='icon icon-leaf'></i> </h6>
	            </div>
	            <div class="modal-body" style="background-color: #eee; padding:0;">
	            	<iframe id="statementContent" style="width:980px; border:0; overflow-y: hidden;"></iframe>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" id="printStatement">打印</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	</div>
	
	<script type="text/javascript">
	function getTypeValue(state) {
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
	function getcustTypeValue(type){
		//CUST_MERCH_TYPE
		var data = new Object();
			<c:forEach items="${dict['CUST_MERCH_TYPE']}" var="Type">
			data["${Type.chooseVal}"] = "${Type.showText}"
			</c:forEach>
			data[5] = "零售商";
			if (data[type]) {
				return data[type];
			} else {
				return "未知";
			}
	}
	</script>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="static/js/account/month.js"></script>
</body>
</html>

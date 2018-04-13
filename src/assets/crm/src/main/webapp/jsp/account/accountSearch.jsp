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
<title>管理系统-资金上账明细</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			资金上账明细<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">客户资金管理</a></li>
			<li class="active">资金上账明细</li>
		</ol>
	</section>
	<div class="row" style="padding: 0 0 20px 0;">
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: 95px !important">客户名称：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入客户名称"
					style="padding: 0; width: 70%;" id="custname">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input2" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: 95px !important">来款账号：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入来款账号"
					style="padding: 0; width: 70%;" id="spayBankNo">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input3" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: 95px !important">来款城市：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入来款城市名称"
					style="padding: 0; width: 70%;" id="spayCity">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: 95px !important">销售组织：</label>
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
			<label class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: 95px !important">现金类型：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance"
					style="padding: 0; width: 70%;" id="spayType">
					<option></option>
				</select>
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input3" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: 95px !important">银行流水号：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" style="padding: 0; width: 70%;" id="sbankSerial">
			</div>
		</div>
		<div class="form-group col-md-offset-3 col-md-3  input-box-list">
			<button class="btn btn-primary" id="btn-search">
				<i class="icon icon-search"></i>&nbsp;&nbsp;开始搜索
			</button>
		</div>
	</div>
	<!-- <div class="col-md-12" id="upAccountTool">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn btn-info" >
				<i class='icon icon-share'></i>导出
			</button>
		</div>
	</div> -->
	<div class="col-md-12">
		<table id="upAccountTable"></table>
	</div>
	<script type="text/javascript">
		function getUpStatesValue(state) {
			var data = new Object();
			<c:forEach items="${dict['MERCH_ACCOUNT_UP_STATES']}" var="payType">
			data["${payType.chooseVal}"] = "${payType.showText}"
			</c:forEach>
			if (data[state]) {
				return data[state];
			} else {
				return "未知";
			}

		}
		function getUpamtType(type) {
			var data = new Object();
			<c:forEach items="${dict['MERCH_UPAMT_TYPE']}" var="payType">
			data["${payType.chooseVal}"] = "${payType.showText}"
			</c:forEach>
			if (data[type]) {
				return data[type];
			} else {
				return "未知";
			}
		}
		function getBank(type) {
			var data = new Object();
			<c:forEach items="${dict['BANK']}" var="bank">
			data["${bank.chooseVal}"] = "${bank.showText}"
			</c:forEach>
			if (data[type]) {
				return data[type];
			} else {
				return "未知";
			}
		}
		function uptype() {
			<c:forEach items="${dict['MERCH_UPAMT_TYPE']}" var="payType">
			$('#payType').append("<option value='${payType.chooseVal}'>${payType.showText}</option>");
			$('#spayType').append("<option value='${payType.chooseVal}'>${payType.showText}</option>");
			</c:forEach>

		}
	</script>
	<script type="text/javascript">
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
	<script type="text/javascript" src="static/js/account/accountSearch.js"></script>
</body>
</html>

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
<title>管理系统</title>
<jsp:include page="/common/head.jsp"></jsp:include>
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
            border-left:1px solid #ddd;

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
	width: 103px;
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

.lawyer-box {
	border: 1px solid hsla(255, 0%, 90%, 1);
}

.lawyer-box thead {
	background-color: hsla(255, 0%, 95%, 1);
}

.lawyer-box td, .lawyer-box th {
	font-size: 12px;
	text-align: center;
	border-left:1px solid #ddd;
}
</style>
</head>
<body class="container-fluid content">
	<form class="col-md-12" style="min-height: 200px; padding: 10px 0;">
		<%-- block 1 --%>
		<div class="page-header" id="block-1">
			<h4 class="text-info">
				<strong>1.&nbsp;</strong>合同基本信息&nbsp;-&nbsp;<small>Basic
					Information.</small>
			</h4>
		</div>
		<div class="form-horizontal row" style="margin: 0 0 20px 0;">
			<div class="form-group col-md-4 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">客户名：</label>
				<div class="input-box-list-value">
					<input type="text" class="form-control"
						value="${merch.name}" readonly>
				</div>
			</div>
			<div class="form-group col-md-4 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">客户sap编码：</label>
				<div class="input-box-list-value">
					<input type="text" class="form-control"
						value="${merch.sapCustomerId}" readonly>
				</div>
			</div>
			<c:if test="${merch.custType !=2 }">
				<div class="form-group col-md-4  col-xs-6 input-box-list">
					<label for="input1" class=" font12 input-box-list-title">经销区域：</label>
					<div class="input-box-list-value">
						<input type="text" class="form-control" placeholder="输入经销区域..."
							value="${contract.agentArea}" readonly>
					</div>
				</div>
			<div class="form-group col-md-4 col-xs-6 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">交货地点：</label>
				<div class="input-box-list-value">
					<input type="text" class="form-control"
						value="${contract.deliveryAddress}" readonly>
				</div>
			</div>
			</c:if>
			<c:if test="${merch.custType !=2 }">
			<div class="form-group col-md-4 col-xs-6 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">结算方式：</label>
				<div class="input-box-list-value">
					<c:forEach items="${dict['MERCH_SETTLE_TYPE']}" var="it">
						<c:if test="${it.chooseVal == contract.settleType}">
							<input type="text" class="form-control" value="${it.showText}" readonly="readonly">
						</c:if>
					</c:forEach>
				</div>
			</div>
			<c:if test="${contract.settleType=='2'}">
			<div class="form-group col-md-4 input-box-list" >
				<label for="input1" class=" font12 input-box-list-title">账期：</label>
				<div class="input-box-list-value">
					<c:forEach items="${dict['MERCH_CONTRACT_ACCOUNT_PERIOD']}" var="it">
						<c:if test="${it.chooseVal == contract.aPeriod}">
							<input type="text" class="form-control" value="${it.showText}" readonly="readonly">
						</c:if>
					</c:forEach>
				</div>
			</div>
			</c:if>
			</c:if>
			<div class="form-group col-md-4 col-xs-6 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">客户等级：</label>
				<div class="input-box-list-value">
					<c:forEach items="${dict['MERCH_PAPER_LEVEL']}" var="it">
						<c:if test="${it.chooseVal == contract.merchLevels}">
							<input type="text" class="form-control" value="${it.showText}" readonly>
						</c:if>
					</c:forEach>
				</div>
			</div>
			<div class="form-group col-md-4 col-xs-6 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">合同开始时间：</label>
				<div class="input-box-list-value">
					<input type="text" class="form-control"
						value="${contract.contractBdate}" readonly>
				</div>
			</div>
			<div class="form-group col-md-4 col-xs-6 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">合同结束时间：</label>
				<div class="input-box-list-value">
					<input type="text" class="form-control"
						value="${contract.contractEdate}" readonly>
				</div>
			</div>
			<%-- <div class="form-group col-md-4 col-xs-6 input-box-list">
						<label for="input1" class=" font12 input-box-list-title">发货工厂：</label>
						<div class="input-box-list-value">
							<input type="text" class="form-control" 
							 value="${factory.name}" readonly="readonly">
						</div>
		    </div> --%>
		    <c:if test="${merch.custType !=2 }">
		    <div class="form-group col-md-4 col-sm-6 input-box-list">
					<label for="input1" class=" font12 input-box-list-title">授信额度(元)：</label>
					<div class="input-box-list-value">
						<input type="text" class="form-control"  value="${contract.creditAmt/100}"
							name="creditAmt" readonly="readonly">
					</div>
			</div>
			</c:if>
			<c:if test="${merch.custType ==3}">
			<div class="form-group col-md-4 col-sm-6 input-box-list">
					<label for="input1" class=" font12 input-box-list-title">价格折扣比例%：</label>
					<div class="input-box-list-value">
						<input type="text" class="form-control"  value="${contract.attribute5}%"
							name="creditAmt" readonly="readonly">
					</div>
			</div>
			</c:if>
			<div class="form-group col-md-4 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">生意模式：</label>
				<div class="input-box-list-value">
				   <select class="form-control no-appearance" id="agent-type" name="agentType" disabled="disabled">
						<c:forEach items="${dict['BUSINESS_MODEL']}" var="it">
							<option value="${it.chooseVal}"${it.chooseVal == contract.agentType?"selected":""}>${it.showText}</option>
						</c:forEach>
					</select> 
				</div>
			</div>
			<%-- <c:if test="${merch.custType !=2 }">
		    <div class="form-group col-md-4 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">对应虚拟仓：</label>
				<div class="input-box-list-value">
				   <select class="form-control no-appearance" id="virtualWarehouse" name="virtualWarehouse" disabled="disabled">
						<c:forEach items="${dict['VIRTUAL_WAREHOUSE_CODE']}" var="it">
							<option value="${it.chooseVal}"${it.chooseVal == contract.virtualWarehouse?"selected":""}>${it.showText}</option>
						</c:forEach>
					</select> 
				</div>
			</div>
			</c:if> --%>
			<input type="hidden" id="contractId" value="${contract.id}">
		</div>
		<%-- block 2 --%>
		<div class="page-header" id="block-2">
			<h4 class="text-info">
				<strong>2.&nbsp;</strong>代理信息&nbsp;-&nbsp;<small>SKU
					Information.</small>
			</h4>
		</div>
		<div class="row" style="margin: 0 0 20px 0;">
			<%-- 数据表格 --%>
			<table id="contract-table">
			</table>
		</div>
	</form>
	<script type="text/javascript">
    	var custType = "${merch.custType }";
    </script>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/customer/contractDetails.js"></script>
</body>
</html>

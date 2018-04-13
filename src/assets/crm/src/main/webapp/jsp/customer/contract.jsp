<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>管理系统-客户合同管理</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <style type="text/css">
    
    	.customizeWidth{
    	width:70%;
    	display:inline-block;
    	}
    </style>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>客户合同管理<small> </small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 客户管理</a></li>
        <li class="active">客户合同列表</li>
    </ol>
</section>
<div class="col-md-12" style="padding:0;">
	<form onsubmit="search();" id="searchForm" class="form">
	 <div class="form-group col-md-4 col-sm-6 mustShow">
	 	<label style="width:97px;">客户名称:</label>
	            <input id="selectcust" type="text" class="form-control customizeWidth" placeholder="客户名称">
	 </div>

	<div class="form-group col-md-4 col-sm-6 needShow">
		<label style="width:97px;">客户类型:</label>
		<select id="selectcustType" class="form-control customizeWidth">
			<option value="">全部</option>
			<c:forEach items="${dict.get('CUST_MERCH_TYPE') }" var="cust_type">
				<option value="${cust_type.chooseVal}" >${cust_type.showText}</option>
	        </c:forEach>
		</select>
	</div>

	  <div class="form-group col-md-4 col-sm-6 needShow">
            <label style="width:97px;">客户等级:</label>
            <select id="selectlevel" class="form-control customizeWidth">
                <option value="">全部</option>
                <c:forEach items="${dict['MERCH_PAPER_LEVEL']}"  var="it">
                    <option value="${it.chooseVal}" ${it.chooseVal == vo.merchLevels?"selected":""}>${it.showText}</option>
                </c:forEach>
            </select>
        </div>

	<div class="form-group col-md-4 col-sm-6 needShow">
		<label style="width:97px;">结算方式:</label>
		<select id="selectsettleType" class="form-control customizeWidth">
			<option value="">全部</option>
			<c:forEach items="${dict['MERCH_SETTLE_TYPE']}" var="it">
				<option value="${it.chooseVal}">${it.showText}</option>
			</c:forEach>
		</select>
	</div>
        <div class="form-group col-md-4 col-sm-6 needShow">
            <label style="width:97px;">合同状态:</label>
            <select id="selectstates" class="form-control customizeWidth">
                <option value="">全部</option>
                <c:forEach items="${dict['MERCH_CONTRACT_STATES']}"  var="it">
                    <option value="${it.chooseVal}" ${it.chooseVal == vo.merchLevels?"selected":""}>${it.showText}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group col-md-4 col-sm-6 needShow">
            <label style="width:97px;">所属销售组织:</label>
            <select id="organizationId" class="form-control customizeWidth">             
            </select>
        </div>
        <div class="form-group col-md-4 col-sm-12 mustShow">
			<div class="btn-group">
				<button type="submit" id="searchButton" class="btn btn-primary"><i class="icon icon-search">&nbsp;&nbsp;查询</i></button>
				<button type="button" class="btn btn-success dropdown-toggle"
						data-toggle="dropdown">
					<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li><a href="#" id="moreSearch">筛选</a></li>
				</ul>
			</div>

        </div>
	</form>
</div>
<div class="col-md-12" id="contractTool" style="padding:0;">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
		<!-- Single button -->
		<div class="btn-group btn-group-sm">
			<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
				新增 <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="customer/addPaper.html?custType=4">盐业公司合同</a></li>
				<li><a href="customer/addPaper.html?custType=1">合作盐业公司合同</a></li>
				<!-- <li><a href="customer/contract/distributionContract.html?custType=2">配送商合同</a></li> -->
				<li><a href="customer/contract/kaContract.html?custType=3">KA合同</a></li>
				<!-- <li><a href="customer/contract/distributionContract.html?custType=7">仓储服务商合同</a></li> -->
				<li><a href="customer/contract/distributionContract.html?custType=70">合作仓储服务商合同</a></li>
				<li><a href="customer/contract/distributionContract.html?custType=8">物流商合同</a></li>
				<li><a href="customer/addPaper.html?custType=9">特通商户合同</a></li>
			</ul>
		</div>


        <button type="button"  id="btn-detail" class="btn btn-primary"><i class='icon icon-eye-open'></i>合同详情</button>
        <button type="button" class="btn btn-edit btn-warning"><i class='icon icon-edit'></i> 修改</button>
        <button type="button" class="btn btn-del btn-danger"><i class='icon icon-remove'></i> 删除</button>
        <button type="button" class="btn btn-success" id="btn-audit"><i class='icon approval_img'></i>提交审批</button>
    </div>

</div>
<table id="contractTable"></table>
<%--<div class="col-md-12" style="padding:0;">

</div>--%>
<script type="text/javascript">


function getstates(type) {
	var data = new Object();
	<c:forEach items="${dict['MERCH_CONTRACT_STATES']}" var="states">
	data["${states.chooseVal}"] = "${states.showText}"
	</c:forEach>
	if (data[type]) {
		return data[type];
	} else {
		return "未知";
	}
}
function getSettle(type) {
	var data = new Object();
	<c:forEach items="${dict['MERCH_SETTLE_TYPE']}" var="settle">
	data["${settle.chooseVal}"] = "${settle.showText}"
	</c:forEach>
	if (data[type]) {
		return data[type];
	} else {
		return "未知";
	}
}

function getCustType(type) {
	var data = new Object();
	<c:forEach items="${dict['CUST_MERCH_TYPE']}" var="vo">
	data["${vo.chooseVal}"] = "${vo.showText}"
	</c:forEach>
	if (data[type]) {
		return data[type];
	} else {
		return "未知";
	}
}
function getaperiod(type) {
	var data = new Object();
	<c:forEach items="${dict['MERCH_CONTRACT_ACCOUNT_PERIOD']}" var="period">
	data["${period.chooseVal}"] = "${period.showText}"
	</c:forEach>
	if (data[type]) {
		return data[type];
	} else {
		return "-";
	}
}
function getvirtualWarehouse(type) {
	var data = new Object();
	<c:forEach items="${dict['VIRTUAL_WAREHOUSE_CODE']}" var="period">
	data["${period.chooseVal}"] = "${period.showText}"
	</c:forEach>
	if (data[type]) {
		return data[type];
	} else {
		return "-";
	}
}
</script>
<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript" src="static/table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="static/js/customer/contract.js"></script>
</body>
</html>

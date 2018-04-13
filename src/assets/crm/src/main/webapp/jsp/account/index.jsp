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
<title>管理系统-客户余额查询</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet"><style>
	body {
		font-family: "微软雅黑";
	}

	ul, li {
		list-style: none;
	}

	select.no-appearance {
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
		width: 100px;
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

	.bootstrap-table .table {
		border-bottom: none;
		border-radius: 0;
	}

	.bootstrap-table .fixed-table-container {
		border-radius: 0;
	}

	.contract-box {
		border-radius: 0;
	}

	.contract-box thead {
		background-color: hsla(255, 0%, 95%, 1);
	}

	.contract-box tbody {
		background-color: #FFF;
	}

	.contract-box td, .lawyer-box th {
		font-size: 12px;
		text-align: center;
	}

	.add-bstb-box {
		/*min-height: 100px;*/
		background-color: #fff;
		/*border-bottom:1px solid hsla(255,0%,90%,1);*/
		/*padding: 10px  0;*/
		height: 0;
		opacity: 0;
		overflow: hidden;
		transition: 0.2s;
		clear: both;
	}

	.add-bstb-box-open {
		border-bottom: 1px solid hsla(255, 0%, 90%, 1);
		padding: 10px 0;
		opacity: 1;
		height: auto;
	}
</style>
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			客户余额<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-dashboard"></i> 首页</a></li>
			<li><a href="#"> 客户资金管理</a></li>
			<li class="active">客户余额</li>
		</ol>
	</section>
	<input type="hidden" id="usertype" value="${user.userType}">
	<form class="form-horizontal row" style="padding: 0 0 20px 0;" onsubmit="doSearch()">
		<div class="form-group col-md-3 col-sm-4 input-box-list">
			<label for="input1" class=" font12 input-box-list-title">客户名称：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" id="custname"
					   placeholder="输入客户名称">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-4 input-box-list">
			<label class=" font12 input-box-list-title">销售组织：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance" id="orgid">
				<option value="">全部</option>
				<c:forEach items="${org}" var="orgs" >
                    <option value="${orgs.id}">${orgs.name}</option>
                </c:forEach>
				</select> <!-- <i class="icon icon-caret-down" style="float: right; margin: -25px 10px 0 0;"></i> -->
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-4 input-box-list">
            <label class=" font12 input-box-list-title">客户类型</label>
				<select class="form-control no-appearance" id="custType">
		            <option value="">全部</option>
		            <c:forEach items="${dict.get('CUST_MERCH_TYPE') }" var="cust_type">
		                <option value="${cust_type.chooseVal}">${cust_type.showText}</option>
		            </c:forEach>
        		</select>
        </div>
		<div class="form-group col-md-offset-1 col-md-3 col-sm-2 input-box-list">
			<button type="sumbit" class="btn btn-primary" id="btn-search" style="margin-left:35px;"><i class="icon icon-search"></i>&nbsp;&nbsp;开始搜索</button>
		</div>
	</form>
	<div class="col-md-12" id="merchAccountTool" >
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn btn-private" id="exportBtn">
                <i class='icon icon-car'></i> 导出
            </button>
			<button type="button" class="btn btn-primary" id="details" style="display: none">
				<i class='icon icon-plus'></i>查看明细
			</button>
		</div>
	</div>
	<div class="col-md-12" style="border-top:1px solid hsla(255,0%,90%,1); ">
		<table id="accountTable"></table>
	</div>
	
	<div class="modal fade" id="exportModal">
	    <div class="modal-dialog modal-lg">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal"
	                        aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	                <h4 class="modal-title">导出</h4>
	            </div>
	            <div class="modal-body" id="generateFile">
	                                                         正在生成excel，请耐心等待...
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
	        </div>
	    </div>
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
	</script>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/account/index.js"></script>
</body>
</html>

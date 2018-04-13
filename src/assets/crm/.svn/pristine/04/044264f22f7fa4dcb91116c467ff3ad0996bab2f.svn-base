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
<title>管理系统-客户价格设置</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link rel="stylesheet" href="static/table/new/bootstrap-editable.css">
<link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
<style>
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
	border-left: 1px solid #ddd;
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
			客户产品价格管理 <small> </small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 客户管理</a></li>
			<li class="active">客户产品价格管理</li>
		</ol>
	</section>
	<%-- block 1
		<div class="page-header" id="block-1">
			<h4 class="text-info">
				<strong>1.&nbsp;</strong>合同基本信息&nbsp;-&nbsp;<small>Basic
					Information.</small> <a href="javascript:;"
					class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a>
				<a href="javascript:;" class=" text-info block-save-link"><i
					class="icon icon-trash"></i>&nbsp;&nbsp;清空</a>
			</h4>
		</div>
		--%>
	<div class="form-horizontal row" style="margin: 0 0 20px 0;">
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title">客户名称：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" id="selectcust"
					placeholder="客户名称...">
			</div>
		</div>
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title">客户类型：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance" id="custType">
            		<option value="">全部</option>
					<c:forEach items="${dict.get('CUST_MERCH_TYPE') }" var="cust_type">
                		<option value="${cust_type.chooseVal}">${cust_type.showText}</option>
            		</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title">所属销售组织：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance" id="selectorg">
					<option value="">全部</option>
					<c:forEach items="${org}" var="orgs">
						<c:if test="${orgs.levels==2}">
							<option value="${orgs.id}">${orgs.name}</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title">产品品牌：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance" id="selectbrand">
					<option value="">全部</option>
				</select>
			</div>
		</div>
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title">产品系列：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance" id="selectseries">
					<option value="">全部</option>
				</select>
			</div>
		</div>
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title">产品内包装：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance" id="selectipackage">
				</select>
			</div>
		</div>
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title">物料编号：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" id="selectsap"
					placeholder="物料编号...">
			</div>
		</div>
		<!-- <div class="form-group col-md-4 col-sm-6 input-box-list" >
				<div class="input-box-list-value">
					<button id="searchButton" class="btn btn-primary" type="button" style="margin-left:35px;"><i class="icon icon-search"></i>查询</button>
				</div>
			</div> -->
	</div>
	<div class="col-md-12" id="dictTool">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button id="searchButton" class="btn btn-primary" type="button">
				<i class="icon icon-search"> </i>查询
			</button>
			<button id="changeStatusBtn" class="btn btn-warnning" type="button">
				<i class="icon icon-repeat"> </i>更改启/停状态
			</button>
			<!-- <div class="btn-group btn-group-sm" role="group" aria-label="...">
				<button type="button" class="btn btn-success" id="upMaterial">
					<i class='icon icon-plus'></i>更新客户物料
				</button>
			</div> -->
			<!-- <div class="btn-group btn-group-sm " role="group" aria-label="...">
				<button type="button" class="btn btn-success" id="updatePrice">
					<i class='icon icon-plus'></i>新增物料
				</button>
			</div> -->
			<div class="btn-group btn-group-sm " role="group" aria-label="...">
				<button type="button" class="btn btn-success" id="saltAdjust">
					<i class='icon icon-plus'></i>合作盐业调拨差价维护
				</button>
			</div>
			<div class="btn-group btn-group-sm " role="group" aria-label="...">
				<button type="button" class="btn btn-success" id="batchMaintain">
					<i class='icon icon-plus'></i>价格批量维护
				</button>
			</div>
		</div>
	</div>
	<div class="row" style="margin: 0 0 20px 0;">
		<!-- <div class="col-md-12" id="upAccountTool" style="padding-left: 0;">
			<div class="btn-group btn-group-sm" role="group" aria-label="...">
				<button type="button" class="btn btn-primary" id="upMaterial">
					<i class='icon icon-plus'></i>更新客户物料
				</button>
			</div>
		</div> -->
		<div class="col-md-12">
			<%-- 数据表格 --%>
			<table id="contract-table" class="contract-box"
				data-show-refresh="true" data-show-columns="true">
			</table>
		</div>
	</div>
	<!-- editDialog-->
	<div class="modal fade" id="updatePriceModal" tabindex="-1"
		aria-labelledby="dictLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="editdictLabel">新增客户价格有效期</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="customer/sku/addProductPrice"
						method="post" id="updatePriceFrom">
						<input type="hidden" id="id" name="id"/>
						<div class="form-group">
							<label for="amt" class="col-sm-3 control-label">物料编码</label>
							<div class="col-sm-6">
								<select class="form-control" id="materialId" name="materialId">
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="amt" class="col-sm-3 control-label">物料描述</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="sku" name="sku" disabled="disabled">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="amt" class="col-sm-3 control-label">标准价</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="basePrice" name="basePrice" disabled="disabled">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="amt" class="col-sm-3 control-label">调整价</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="hPrice" name="hPrice">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="reason" class="col-sm-3 control-label">有效开始日期</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="priceBdate"
									name="priceBdate" placeholder="开始时间">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="remark" class="col-sm-3 control-label">有效截止日期</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="priceEdate"
									name="priceEdate" placeholder="截止时间">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-edit-save" form="updatePriceFrom"
						class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript"	src="static/table/new/bootstrap-table-editable.js"></script>
	<script type="text/javascript"	src="static/table/new/bootstrap-editable.js"></script>
    <script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.min.js"charset="UTF-8"></script>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"	charset="UTF-8"></script>
	<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript"	src="static/js/customer/setCustmerPrice.js"></script>
</body>
</html>

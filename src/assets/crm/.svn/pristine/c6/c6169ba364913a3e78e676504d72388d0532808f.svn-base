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
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
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

.font12 {
	font-size: 12px;
}

.form-horizontal .input-box-list {
	display: table;
	margin: 10px 0 0 0;
	padding-left: 10px;
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

.input-box-list-title, .input-box-list-value {
	display: table-cell;
}

.input-box-list-title {
	width: 119px;
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
/* this page */
.user-select-panel {
	border: 1px solid hsla(0, 0%, 90%, 1);
	background-color: #fff;
	padding: 0 10px;
}

.user-select-list {
	height: 146px;
	overflow-y: auto;
}

.user-select-panel li {
	border-bottom: 1px solid hsla(0, 0%, 95%, 1);
	/*padding:5px 0 ;*/
}

.user-select-panel li .radio {
	padding-left: 10px;
}

.factory-select-box .dropdown {
	margin-bottom: 10px;
}

.factory-select-box .dropdown a.btn {
	padding-top: 10px;
	padding-bottom: 10px;
	background-color: #fff;
	border-radius: 0;
}

.factory-select-box .dropdown a.btn span.caret {
	margin-top: 8px;
}

.factory-select-box .dropdown ul {
	width: 100%;;
}

.account-panel {
	border: 1px solid hsla(0, 0%, 90%, 1);
	border-top: 2px solid #a94442;
	padding: 20px 20px 10px;
	background-color: #fff;
}

.account-panel>div>p {
	margin: 0;
}

.account-item {
	margin-bottom: 15px;
}

.account-item>span {
	margin: 0 5px;
}

.account-item:last-child {
	border-top: 1px dashed hsla(0, 0%, 90%, 1);
	padding-top: 20px;
}

.order-accordion-item {
	border-radius: 0;
}

.order-accordion-item div.panel {
	border-radius: 0;
}

.order-accordion-item div.panel-heading {
	border-radius: 0;
	background-color: #fff;
	transition: 0.2s;
}

.order-accordion-item div.panel-heading:hover {
	background-color: hsla(202, 68%, 74%, .15);
}

.order-item-title-block {
	padding-right: 30px;
	margin-right: 20px;
	border-right: 1px solid hsla(0, 0%, 95%, 1);
	color: #333;
}

.order-plus-box {
	margin-bottom: 0;
}

.order-plus-box thead {
	background-color: hsla(255, 0%, 95%, 1);
}

.order-plus-box td, .order-plus-box th {
	font-size: 12px;
	text-align: center;
}

.order-plus-box-add {
	padding: 10px;
	text-align: center;
	width: 100%;
	font-size: 16px;
	background-color: #fff;
	color: hsla(0, 0%, 65%, 1);
	border: 1px dashed hsla(0, 0%, 90%, 1);
	cursor: pointer;
	transition: 0.2s;
}

.order-plus-box-add:hover {
	color: hsla(0, 0%, 35%, 1);
	border-color: hsla(0, 0%, 80%, 1);
}

.order-item {
	float: left;
	line-height: 30px;
	margin-right: 30px;
}
.chosen-container .chosen-results{
max-height:120px !important;}
</style>
</head>
<body class="container-fluid content">

	<section class="content-header">
		<h1>
			订单管理 <small>零售商订单录入</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li class="active">订单管理</li>
		</ol>
	</section>

	<form>
		<%-- 选择用户 --%>
		<div class="row">
			<div class="form-group col-md-6 col-sm-6 input-box-list">
				<p class="text-muted order-item">选择客户:</p>
				<div class="form-group" style="width: 100%;">
					<select class="findCustom" data-placeholder="请选择客户信息..."
						style="width: 350px;" tabindex="1" id="merchCustId"
						name="merchCustId">
					</select>
				</div>
			</div>
			<div class="form-group col-md-6 col-sm-6 input-box-list">
				<p class="text-muted order-item">所属配送商:</p>
				<div class="input-box-list-value">
					<input type="text" class="form-control" id="distributename" readonly>
				</div>
			</div>
		    <input type="hidden" id="merchPid"> 
		    <input type="hidden" id="orgid">
		</div>
		
		<%-- 订单详情 --%>
		<div class="page-header">
			<h5>
				订单项目详情&nbsp;-&nbsp;<small>Order Details.</small>
				<!-- <a href="javascript:;" class=" text-info block-save-link"  data-toggle="modal" data-target="#addOrderModal"><i class="icon icon-plus"></i>&nbsp;&nbsp;新增项目</a> -->
			</h5>
		</div>
		<%-- 订单项目列表 --%>
		<div class="panel-group order-accordion-item" role="tablist">
			<%--新增订单 --%>
			<div class="form-horizontal row ">
				<div class="modal-body">
					<div class="form-horizontal row" style="padding-right: 20px;">
						<div class="form-group col-md-8 col-sm-12 input-box-list">
							<label for="material" class=" font12 input-box-list-title">产品SKU：</label>
							<div class="input-box-list-value">
								<select style="width: 100%;" tabindex="1" id="material"
									name="material" data-placeholder="输入SKU..." class="chosen-select">
								</select>
							</div>
						</div>

						<div class="form-group col-md-6 col-sm-6 input-box-list"
							style="display: none">
							<label for="materialName" class=" font12 input-box-list-title">产品名称：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="materialName"
									name="materialName" disabled>
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="price" class=" font12 input-box-list-title">单价(元)：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="price" name="price"
									disabled>
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="unit" class=" font12 input-box-list-title">单位：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="unit" name="unit"
									disabled>
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list hide">
							<label for="invnum" class=" font12 input-box-list-title">可售库存：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="invnum" 
									disabled>
							</div>
						</div>
						<input type="hidden" id="orderPrice" name="orderprice">
						<!-- <input type="hidden" id="unit" name="unit">  -->
						<input type="hidden" id="hPrice" name="hPrice">
						<input type="hidden" id="wlPrice" name="wlPrice">
						<input type="hidden" id="amounts">
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="num" class=" font12 input-box-list-title">产品数量：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control goodsNum" id="num"
									name="num"  placeholder="输入产品数量">
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="amt" class=" font12 input-box-list-title">金额(元)：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="amt" name="amt"
									disabled value="0">
							</div>
						</div>
						<input type="hidden" id="orderAmt" name="orderAmt">
					</div>
				</div>
				<div class="col-md-12 row" style="padding: 25px 0 0 15px;">
					<div class="col-md-4 col-sm-4 text-left "></div>
					<div class="col-md-4 col-sm-4 text-center">
						<a id="btn-add-agent" class="btn btn-danger"
							style="padding-left: 15%; padding-right: 15%; display: block"><i
							class="icon icon-check"></i>&nbsp;&nbsp;添加购物车</a>
					</div>
				</div>
				<div class="modal-footer"></div>
			</div>
			<div id="order-toolbar">
				<a id="edit-bstb-row" class="btn btn-primary  pull-left"
					style="width: auto; margin-right: 5px;"> <i
					class="icon icon-plus"></i> 修改数量
				</a>
				 <a id="del-bstb-row" class="btn btn-danger  pull-left"
					style="width: auto; margin-right: 5px;"> <i
					class="icon icon-remove"></i> 删除
				</a>
			</div>
			<%-- 数据表格 --%>
			<table id="order-table" class="order-box"
				data-toolbar="#order-toolbar" data-search="true"
				data-show-refresh="true"
				<%--data-show-toggle="true"--%>
        data-show-columns="true"
				data-show-export="true"
				<%--data-show-pagination-switch="true"--%>
        data-detail-view="false"
				data-detail-formatter="detailFormatter" data-id-field="id"<%-- data-pagination="true" data-page-size="5" data-page-list="[5,8,10]" --%>
        >
			</table>
		</div>
		<%-- submit button --%>
		<div class="text-center"
			style="padding-top: 100px; border-top: 1px solid rgba(0, 0, 0, .15); margin-top: 40px;">
			<button class="btn btn-warning" style="padding: 8px 25px;"
				type="button" id="btn-save">
				<i class="icon icon-save"></i>&nbsp;&nbsp;保存
			</button>
			<!-- <button class="btn btn-primary btn-long" type="button"
				id="btn-save-audit">
				<i class="icon icon-check"></i>&nbsp;&nbsp;提交
			</button> -->
		</div>

	</form>
	<!-- Modal -->
	<!-- editDialog-->
	<div class="modal fade" id="editLineModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">行数量修改</h4>
				</div>
				<div class="modal-body">
					<form action="" class="form-horizontal">
					<div class="form-group">
						<label for="editsku" class="col-sm-3 control-label">物料名称</label> <input
							type="hidden" id="id" name="id">
						<div class="col-sm-6">
							<input type="text" class="form-control" id="editsku"
								readonly="readonly">
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<input id="editid" type="hidden"> 
					<input type="hidden" id="editmaterialId">
					<div class="form-group">
						<label for="editunit" class="col-sm-3 control-label">单位</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="editunit"
								readonly="readonly">
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<div class="form-group">
						<label for="editorderPrice" class="col-sm-3 control-label">单价(元)</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="editorderPrice"
								readonly="readonly">
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<div class="form-group hide">
						<label for="editinvnum" class="col-sm-3 control-label">可售库存</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="editinvnum"
								readonly="readonly">
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<input type="hidden" id="editprice">
					<input type="hidden" id="edithPrice" >
					<input type="hidden" id="editwlPrice" >
					<input type="hidden" id="editamounts" >
					<div class="form-group">
						<label for="editnum" class="col-sm-3 control-label">数量</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="editnum" >
						</div>
						<small class="help-block col-sm-3" id="editerror"></small>
					</div>
					<input type="hidden" id="editamt">
					<div class="form-group">
						<label for="editorderAmt" class="col-sm-3 control-label">金额(元)</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="editorderAmt"
								readonly="readonly">
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="btn-edit-save" class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<script type="text/javascript"
		src="static/table/bootstrap-table.min.js"></script>
	<script type="text/javascript"
		src="static/table/new/bootstrap-table.js"></script>
	<script type="text/javascript"
		src="static/table/new/bootstrap-table-export.js"></script>
	<script type="text/javascript"
		src="static/table/new/bootstrap-table-editable.js"></script>
	<script type="text/javascript"
		src="static/table/new/bootstrap-editable.js"></script>
	<script type="text/javascript"
		src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="static/js/order/distributorOrder.js"></script>
	<script type="text/javascript">
		function getverification(value) {
			var data = new Object();
			<c:forEach items="${dict['POLICY_TYPE_VERIFICATION']}" var="type">
			data["${type.chooseVal}"] = "${type.showText}"
			</c:forEach>
			if (data[value]) {
				return data[value];
			} else {
				return "未知";
			}
		}
	</script>
</body>
</html>

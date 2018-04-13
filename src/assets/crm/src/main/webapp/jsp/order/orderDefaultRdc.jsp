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

.add-bstb-box {
	background-color: #fff;
	transition: 0.2s;
	clear: both;
}

.add-bstb-box-open {
	border-bottom: 1px solid hsla(255, 0%, 90%, 1);
	padding: 10px 0;
	opacity: 1;
	height: auto;
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
			订单管理 <small>订单下达</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li class="active">订单管理</li>
		</ol>
	</section>

	<form>
		<%-- 选择用户 --%>
		<div class="row">
			<div class="form-group col-md-4 col-sm-4 input-box-list">
				<p class="text-muted order-item">选择客户:</p>
				<div class="form-group" style="width: 100%;">
					<select class="findCustom" data-placeholder="请选择客户信息..."
						style="width: 350px;" tabindex="1" id="merchCustId"
						name="merchCustId">
					</select>
				</div>
			</div>
			<input type="hidden" id="stationid"> 
			<input type="hidden" id="orgid">
			<input type="hidden" id="hbratio">
			<div class="col-md-4  col-sm-4 factory-select-box">
				<p class="text-muted order-item">选择送达方信息:</p>
				<div class="form-group" style="width: 100%;">
					<select class="findDelivery" data-placeholder="请选择送达方信息..."
						style="width: 350px;" tabindex="3" id="shipId" name="shipId">
					</select>
				</div>
			</div>
			<div class="col-md-4  col-sm-4 factory-select-box">
				<p class="text-muted order-item">发货RDC:</p>
				<div class="form-group" style="width: 100%;">
					<select class="form-group" data-placeholder="请选择RDC..."
						style="width: 350px;" tabindex="3" id="rdcCode" name="rdcCode" disabled="disabled">
						<option></option>
						<c:forEach items="${dict.get('VIRTUAL_WAREHOUSE_CODE') }" var="cust_type">
		                	<option value="${cust_type.chooseVal}">${cust_type.showText}</option>
		            	</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4  col-sm-4 factory-select-box">
				<p class="text-muted order-item">发货方式:</p>
				<div class="form-group" style="width: 100%;">
					<select class="form-group" 	style="width: 350px;" tabindex="3" id="deliveryType" >
						<option></option>
						<c:forEach items="${dict.get('ORDER_DELIVERY_TYPE') }" var="type">
		                	<option value="${type.chooseVal}">${type.showText}</option>
		            	</c:forEach>
					</select>
				</div>
			</div>
			<div class="col-md-4  col-sm-4 factory-select-box hide" id="freighthide">
				<p class="text-muted order-item">运费(元):</p>
				<div class="form-group" style="width: 100%;">
					<input type="text" class="form-control" style="width: 350px;" tabindex="1" id="freight"/>
				</div>
			</div>
		</div>
		<div class="row hide customAccount">
			<div class="col-md-5 col-xs-6">
				<p class="text-muted">客户账户信息:</p>
				<div class="account-panel">
					<p class="account-item">
						<span class="account-title">现金余额(元):</span> <span
							class="account-value" id="cashAmt"><i
							class="icon icon-yen"></i>&nbsp;</span>
					</p>
					<p class="account-item">
						<span class="account-title">补货余额(元):</span> <span
							class="account-value" id="subsidyAmt"><i
							class="icon icon-yen"></i>&nbsp;</span>
					</p>
					<p class="account-item">
						<span class="account-title">授信余额(元):</span> <span
							class="account-value" id="creditAmt"><i
							class="icon icon-yen"></i>&nbsp;</span>
					</p>
					<!-- <p class="account-item">
						<span class="account-title">保证金(元):</span> <span
							class="account-value" id="bondAmt"><i
							class="icon icon-yen"></i>&nbsp;</span>
					</p> -->
					<p class="account-item" style="font-size: 18px;">
						<span class="account-title">总计余额(元):</span> <span
							class="account-value text-danger" id="allamt"><i
							class="icon icon-yen"></i>&nbsp;</span>
					</p>
				</div>
			</div>
			<!-- <div class="col-md-5 col-xs-6 ship hide">
				<p class="text-muted">客户送达信息:</p>
				<div class="account-panel">
					<p class="account-item">
						<span class="account-title">物流公司:</span> <span
							class="account-value" id="logistics"><i
							class="icon icon-yen"></i>&nbsp;</span>
					</p>
					<p class="account-item">
						<span class="account-title">到站地址:</span> <span
							class="account-value" id="site"><i
							class="icon icon-yen"></i>&nbsp;</span>
					</p>
					<p class="account-item">
						<span class="account-title">收货人姓名:</span> <span
							class="account-value" id="contacter"><i
							class="icon icon-yen"></i>&nbsp;</span>
					</p>
					<p class="account-item">
						<span class="account-title">收货人联系电话:</span> <span
							class="account-value" id="mobile"><i
							class="icon icon-yen"></i>&nbsp;</span>
					</p>
					<p class="account-item">
						<span class="account-title">收货地址:</span> <span
							class="account-value" id="address"><i
							class="icon icon-yen"></i>&nbsp;</span>
					</p>
					<p class="account-item" style="display: none;">
					</p>
					
				</div>
			</div> -->
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
			<div class="form-horizontal row add-bstb-box">
				<div class="modal-body">
					<div class="form-horizontal row" style="padding-right: 20px;">
						<div class="form-group col-md-8 col-sm-12 input-box-list">
							<label for="input1" class=" font12 input-box-list-title">产品SKU：</label>
							<div class="input-box-list-value">
								<select style="width: 100%;" tabindex="1" id="materialId"
									name="materialId" data-placeholder="输入SKU...">
								</select>
							</div>
						</div>

						<div class="form-group col-md-6 col-sm-6 input-box-list"
							style="display: none">
							<label for="input1" class=" font12 input-box-list-title">产品名称：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="materialName"
									name="materialName" disabled>
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="input1" class=" font12 input-box-list-title">单价(元)：</label>
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
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="input1" class=" font12 input-box-list-title">可售库存：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="invnum" 
									disabled>
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="input1" class=" font12 input-box-list-title">规格：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="specifications" 
									disabled>
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="input1" class=" font12 input-box-list-title">箱内数量：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="amounts" 
									disabled>
							</div>
						</div>
						<input type="hidden" id="orderPrice" name="orderPrice">
						<input type="hidden" id="highPrice" name="highPrice">
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="input1" class=" font12 input-box-list-title">产品数量：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control goodsNum" id="num"
									name="num"  placeholder="输入产品数量">
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="input1" class=" font12 input-box-list-title">销售政策：</label>
							<div class="input-box-list-value">
								<select class="form-control"
									style="border-radius: 0; padding-right: 30px;" id="policy"
									name="policy" disabled="disabled">
								</select>
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="input1" class=" font12 input-box-list-title">政策奖励：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="discountname"
									name="discountname" disabled>
							</div>
							<input type="hidden" id="policyHeaderId"> 
							<input type="hidden" id="policyLineId"> 
							<input type="hidden" id="policyVerfication"> 
							<input type="hidden" id="policyDiscount"> 
							<input type="hidden" id="policyprimary"> 
							<input type="hidden" id="policyamt">
							<input type="hidden" id="policyprice">
							<input type="hidden" id="policylimit">
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="input1" class=" font12 input-box-list-title">奖励数量/返利比例：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control"
									id="policyDiscountIntensity" name="policyDiscountIntensity"
									disabled>
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="input1" class=" font12 input-box-list-title">货补数量：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control additionNum" id="hbNum"
									placeholder="输入附加数量" >
							</div>
						</div>
						<input type="hidden" id="hbAmt" value='0'>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="input1" class=" font12 input-box-list-title">订单金额(元)：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="amt" name="amt"
									disabled value="0">
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="input1" class=" font12 input-box-list-title">折扣额(元)：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="discountAmt"
									disabled value="0">
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="input1" class=" font12 input-box-list-title">订单净额(元)：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="orderAmt"
									name="orderamt" disabled value="0">
							</div>
						</div>

					</div>
				</div>
				<div class="col-md-12 row" style="padding: 25px 0 0 15px;">
					<div class="col-md-4 col-sm-4 text-left "></div>
					<div class="col-md-4 col-sm-4 text-center">
						<a id="btn-add-agent" class="btn btn-danger"
							style="padding-left: 15%; padding-right: 15%; display: block"><i
							class="icon icon-check"></i>&nbsp;&nbsp;加入购物车</a>
					</div>
				</div>
				<div class="modal-footer"></div>
			</div>
			<div id="order-toolbar">
				<!-- <a id="add-bstb-row" class="btn btn-primary  pull-left"
					style="width: auto; margin-right: 5px;"> <i
					class="icon icon-plus"></i> 新增SKU
				</a> -->
				 <a id="edit-bstb-row" class="btn btn-primary  pull-left"
					style="width: auto; margin-right: 5px;"> <i
					class="icon icon-plus"></i> 修改数量
				</a> <a id="del-bstb-row" class="btn btn-danger  pull-left"
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
        <%--data-pagination-first-text="首页" data-pagination-pre-text="上一页" --%>
        <%--data-pagination-next-text="下一页" data-pagination-last-text="尾页" --%>
        >
			</table>
		</div>
		<%-- 金额统计 --%>
		<div class="col-md-12"
			style="padding: 20px 0; border-bottom: 1px solid rgba(0, 0, 0, .15); margin-top: 25px;">
			<dl class="dl-horizontal">
				<dt>现金扣减金额(元):</dt>
				<dd id="XJamt">0</dd>
			</dl>
			<dl class="dl-horizontal">
				<dt>授信扣减金额(元):</dt>
				<dd id="SXamt">0</dd>
			</dl>
			<dl class="dl-horizontal">
				<dt>货补扣减金额(元):</dt>
				<dd id="HBamt">0</dd>
			</dl>
		</div>
		<div class="remark col-md-offset-1 col-md-11">
        <albel>备注:</albel>
        <textarea style="width:80%;min-height:60px; display:block;margin-bottom:20px;" id="remark"></textarea>
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
						<label for="payName" class="col-sm-3 control-label">物料名称</label> <input
							type="hidden" id="editid" name="id">
						<div class="col-sm-6">
							<input type="text" class="form-control" id="editsku"
								readonly="readonly">
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<input id="editid" type="hidden"> 
					<input type="hidden" id="editmaterialId">
					<div class="form-group">
						<label for="payAmt" class="col-sm-3 control-label">单位</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="editunit"
								readonly="readonly">
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<div class="form-group">
						<label for="payAmt" class="col-sm-3 control-label">规格</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="editspecifications"
								readonly="readonly">
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<div class="form-group">
						<label for="payAmt" class="col-sm-3 control-label">箱内数量</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="editamounts"
								readonly="readonly">
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<div class="form-group">
						<label for="payAmt" class="col-sm-3 control-label">单价(元)</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="editorderPrice"
								readonly="readonly">
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<div class="form-group">
						<label for="editinvnum" class="col-sm-3 control-label">可售库存</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="editinvnum"
								readonly="readonly">
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<input type="hidden" id="editprice">
					<input type="hidden" id="edithighPrice" >
					<div class="form-group">
						<label for="payCity" class="col-sm-3 control-label">数量</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="editnum" >
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<div class="form-group">
						<label for="payBankNo" class="col-sm-3 control-label">销售政策</label>
						<div class="col-sm-6">
							<select class="form-control" id="editpolicy" disabled="disabled">
							</select>
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<div class="form-group">
						<label for="payBankNo" class="col-sm-3 control-label">政策奖励</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="editdiscountname"
								readonly="readonly">
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<div class="form-group">
						<label for="payBankNo" class="col-sm-3 control-label">政策奖励力度</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="editpolicyDiscountIntensity"
								readonly="readonly">
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<input type="hidden" id="editpolicyHeaderId"> 
					<input type="hidden" id="editpolicyLineId"> 
					<input type="hidden" id="editpolicyVerfication"> 
					<input type="hidden" id="editpolicyDiscount"> 
					<input type="hidden" id="editpolicyprimary"> 
					<input type="hidden" id="editpolicyamt">
					<input type="hidden" id="editpolicyprice">
					<input type="hidden" id="editpolicylimit">
					<div class="form-group">
						<label for="editpayDate" class="col-sm-3 control-label">货补数量</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="edithbNum">
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<input type="hidden" id="edithbAmt">
					<div class="form-group">
						<label for="editpayDate" class="col-sm-3 control-label">订单金额(元)</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="editamt"
								readonly="readonly">
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<div class="form-group">
						<label for="editpayDate" class="col-sm-3 control-label">折扣额(元)</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="editdiscountAmt"
								readonly="readonly">
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<div class="form-group">
						<label for="editpayDate" class="col-sm-3 control-label">订单净额(元)</label>
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
	<script type="text/javascript" src="static/js/order/orderDefaultRdc.js"></script>
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

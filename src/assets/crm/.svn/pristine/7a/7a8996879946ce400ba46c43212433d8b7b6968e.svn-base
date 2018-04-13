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
<title>销售政策管理-申请销售政策</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link rel="stylesheet"href="static/tokenfield/css/bootstrap-tokenfield.min.css">
<link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
<link href="static/bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
<style>
body .cn {
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

#stepLink2 {
	position: fixed;
	top: 0;
	right: 0;
	padding: 15px;
	z-index: 2000;
	background-color: rgba(255, 255, 255, .75);
	box-shadow: -2px 3px 5px hsla(0, 0%, 0%, .15);
	transition: 0.3s;
	opacity: 0;
}

#stepLink2:hover {
	background-color: #fff;
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

.contact-box, .contact-box-new, .contact-box-add {
	border: 1px solid hsla(0, 0%, 95%, 1);
	background-color: #fff;
	height: 239px;
	padding: 20px 15px;
	max-height: 239px;
	overflow: hidden;
}

.bank-box, .bank-box-add {
	border: 1px solid hsla(0, 0%, 95%, 1);
	background-color: #fff;
	height: 190px;
	padding: 20px 15px;
	max-height: 190px;
	overflow: hidden;
}

.bank-box-add {
	text-align: center;
	line-height: 180px;
	font-size: 18px;
	color: hsla(0, 0%, 65%, 1);
	border: 1px dashed hsla(0, 0%, 90%, 1);
	cursor: pointer;
	transition: 0.2s;
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

.contact-box-new:before {
	content: '';
	height: 3px;
	display: block;
	background-color: #f39c12;
	margin: -20px -15px 0 -15px;;
}

.contact-box:before, .bank-box:before {
	content: '';
	height: 3px;
	display: block;
	background-color: #3c8dbc;
	margin: -20px -15px 0 -15px;;
}
</style>
</head>
<body class="container-fluid content">
	<%-- modal 新增搭赠销售政策 --%>
	<div class="modal fade" id="dzPolicyModal" tabindex="-1" role="dialog">
		<div class="modal-dialog ">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">新增搭赠销售政策</h4>

				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="addDZPromotionForm"
						onsubmit="return false;">
						<div class="form-group  ">
							<label for="materialId" class=" col-sm-3 control-label">订单SKU：</label>
							<div class=" col-sm-5">
								<!-- <select class="form-control" data-placeholder="请选择SKU..."
									style="width: 250px;" tabindex="1" name="materialId"
									id="dzmaterialId">
								</select> -->
								<select class="form-control" data-placeholder="请选择SKU..."
									name="materialId" id="dzmaterialId">
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group  ">
							<label for="discount" class="col-sm-3 control-label">单位:</label>
							<div class=" col-sm-5">
								<input type="text" class="form-control" name="discount"
									id="dzunit" readonly="readonly">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group  ">
							<label for="discount" class="col-sm-3 control-label">单价:</label>
							<div class=" col-sm-5">
								<input type="text" class="form-control" name="discount"
									id="dzprice" readonly="readonly">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group  ">
							<label for="discount" class="col-sm-3 control-label">搭增SKU：</label>
							<div class=" col-sm-5">
								<select class="form-control" name="discount" id="dzdiscount">
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>

						<div class="form-group  ">
							<label for="primary" class="col-sm-3 control-label ">目标数量：</label>
							<div class="col-sm-5">
								<input type="text" class="  form-control" id="dzprimary"
									name="primary" placeholder="搭增标准">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group  ">
							<label for="discountIntensity" class="col-sm-3  control-label">搭增数量：</label>
							<div class=" col-sm-5">
								<input type="text" class="form-control" id="dzdiscountIntensity"
									name="discountIntensity" placeholder="搭增数量">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group  ">
							<label for="limit" class="col-sm-3 control-label ">搭增限量：</label>
							<div class=" col-sm-5">
								<input type="text" class="form-control" id="dzlimit"
									name="limit" placeholder="搭增限额">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-dz-save btn-primary">确认添加</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


	<%-- modal 新增价格折扣销售政策 --%>
	<div class="modal fade" id="zkPolicyModal" tabindex="-1" role="dialog">
		<div class="modal-dialog ">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">新增价格折扣销售政策</h4>

				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="addZKPromotionForm"
						onsubmit="return false;">
						<div class="form-group  ">
							<label for="input1" class="col-sm-3  control-label">订单SKU：</label>
							<div class="col-sm-5">
								<select class="form-control" name="materialId" id="zkmaterialId">
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group  ">
							<label for="discount" class="col-sm-3 control-label">单位:</label>
							<div class=" col-sm-5">
								<input type="text" class="form-control" name="discount"
									id="zkunit" readonly="readonly">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group  ">
							<label for="discount" class="col-sm-3 control-label">单价:</label>
							<div class=" col-sm-5">
								<input type="text" class="form-control" name="discount"
									id="zkprice" readonly="readonly">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group  ">
							<label for="input1" class=" col-sm-3 control-label">折扣：</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" name="discountIntensity"
									id="zkdiscountIntensity" placeholder="折扣：90%/9折"><span
									class="input-group-addon">%</span>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<!-- <div class="form-group  ">
							<label for="input1" class=" col-sm-3 control-label">折扣额：</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="input3"
									placeholder="折扣额" readonly="readonly">
							</div>
							<small class="help-block col-sm-4"></small>
						</div> -->
						<!-- <div class="form-group  ">
							<label for="input1" class=" col-sm-3 control-label">折扣限额：</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="input3"
									placeholder="搭增限额">
							</div>
							<small class="help-block col-sm-4"></small>
						</div> -->
					</form>
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-zk-save btn-primary">确认添加</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<%-- modal 新增进货奖励销售政策 --%>
	<div class="modal fade" id="jhPolicyModal" tabindex="-1" role="dialog">
		<div class="modal-dialog ">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">返利销售政策</h4>

				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="addJHPromotionForm"
						onsubmit="return false;">
						<div class="form-group  ">
							<label for="input1" class="col-md-3  control-label">订单SKU：</label>
							<div class="col-md-5">
								<select class="form-control" name="materialId" id="jhmaterialId">
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group  ">
							<label for="discount" class="col-sm-3 control-label">单位:</label>
							<div class=" col-sm-5">
								<input type="text" class="form-control" name="unit" id="jhunit"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group  ">
							<label for="discount" class="col-sm-3 control-label">单价:</label>
							<div class=" col-sm-5">
								<input type="text" class="form-control" name="price"
									id="jhprice" readonly="readonly">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group  ">
							<label for="input1" class=" col-sm-3 control-label">奖励比例：</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" name="discountIntensity"
									id="jhdiscountIntensity" placeholder="返点：1%"><span
									class="input-group-addon">%</span>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>

					</form>
				</div>
				<div class="modal-footer">

					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-jh-save btn-primary">确认添加</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<%-- modal 新增促销品奖励销售政策 --%>
	<div class="modal fade" id="cxPolicyModal" tabindex="-1" role="dialog">
		<div class="modal-dialog ">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">促销品销售政策</h4>

				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="addcxPromotionForm"
						onsubmit="return false;">
						<div class="form-group  ">
							<label for="materialId" class=" col-sm-3 control-label">订单SKU：</label>
							<div class=" col-sm-5">
								<!-- <select class="form-control" data-placeholder="请选择SKU..."
									style="width: 250px;" tabindex="1" name="materialId"
									id="dzmaterialId">
								</select> -->
								<select class="form-control" data-placeholder="请选择SKU..."
									name="materialId" id="cxmaterialId">
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group  ">
							<label for="discount" class="col-sm-3 control-label">单位:</label>
							<div class=" col-sm-5">
								<input type="text" class="form-control" id="cxunit"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group  ">
							<label for="discount" class="col-sm-3 control-label">单价:</label>
							<div class=" col-sm-5">
								<input type="text" class="form-control" id="cxprice"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group  ">
							<label for="discount" class="col-sm-3 control-label">促销品：</label>
							<div class=" col-sm-5">
								<select class="form-control" name="discount" id="cxdiscount">
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>

						<div class="form-group  ">
							<label for="primary" class="col-sm-3 control-label ">目标数量：</label>
							<div class="col-sm-5">
								<input type="text" class="  form-control" id="cxprimary"
									name="primary" placeholder="目标数量">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group  ">
							<label for="discountIntensity" class="col-sm-3  control-label">赠送数量：</label>
							<div class=" col-sm-5">
								<input type="text" class="form-control" id="cxdiscountIntensity"
									name="discountIntensity" placeholder="赠送数量">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group  ">
							<label for="limit" class="col-sm-3 control-label ">赠送限量：</label>
							<div class=" col-sm-5">
								<input type="text" class="form-control" id="cxlimit"
									name="limit" placeholder="赠送限量">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-cx-save btn-primary">确认添加</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<section class="content-header">
		<h1>
			销售政策申请 <small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 销售政策管理</a></li>
			<li class="active">销售政策申请</li>
		</ol>
	</section>

	<%-- block 1 --%>
	<div class="page-header" id="block-1">
		<h4 class="text-info">
			<strong>1.&nbsp;</strong>申请基本信息&nbsp;-&nbsp; <small>Basic
				Information.</small>
		</h4>
	</div>
	<div class="form-horizontal row" style="margin: 0 0 20px 0;">
		<form class="form-horizontal" method="post"
			action="salePolicy/header/add" id="policyheaderForm">
			<div class="form-group col-md-4 col-sm-6  input-box-list">
				<label for="dept" class=" font12 input-box-list-title">申请部门：</label>
				<div class="input-box-list-value">
					<select class="form-control" name="organizationId"
						id="organizationId">
						<option></option>
						<c:forEach items="${org}" var="orgs">
							<c:if test="${orgs.levels==2}">
								<option value="${orgs.id}">${orgs.name}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-md-4 col-sm-6 input-box-list">
				<label for="description" class=" font12 input-box-list-title">描述：</label>
				<div class="input-box-list-value">
					<input type="text" class="form-control" name="description"
						id="description">
				</div>
			</div>
			<div class="form-group col-md-4 col-sm-6 input-box-list">
				<label class=" font12 input-box-list-title" for="policyType">政策类型：</label>
				<div class="input-box-list-value">
					<select class="form-control" name="policyType" id="policyType">
					</select>
				</div>
			</div>
			<div class="form-group col-md-4 col-sm-6 input-box-list">
				<label for="bDate" class=" font12 input-box-list-title">政策开始时间：</label>
				<div class="input-box-list-value">
					<!-- <input type="date" class="form-control" name="bDate" id="startTime"> -->
					<input type="text" class="form-control" placeholder="请输入开始时间"
						name="bDate" id="startTime"
						style="width: 100%; display: inline-block">
				</div>
			</div>
			<div class="form-group col-md-4 col-sm-6 input-box-list">
				<label for="eDate" class=" font12 input-box-list-title">政策结束时间：</label>
				<div class="input-box-list-value">
					<!-- <input type="date" class="form-control" name="eDate" id="endTime"
					onkeyup="validate()"> -->
					<input type="text" class="form-control" placeholder="请输入结束时间"
						name="eDate" id="endTime"
						style="width: 100%; display: inline-block">
				</div>
			</div>
			<div class="form-group col-md-4 col-sm-6 input-box-list">
				<label for="costSubjectid" class=" font12 input-box-list-title">成本中心：</label>
				<div class="input-box-list-value">
					<select class="form-control" name="costSubjectid"
						id="costSubjectid">
						<option value="1">成本中心1</option>
					</select>
				</div>
			</div>

			<div class="form-group col-md-4 col-sm-6 input-box-list">
				<label for="costTypeid" class=" font12 input-box-list-title">费用类型：</label>
				<div class="input-box-list-value">
					<select class="form-control" name="costTypeid" id="costTypeid">
						<option value="1">市场费用</option>
					</select>
				</div>
			</div>
			<div class="form-group col-md-4 col-sm-6 input-box-list">
				<label for="amt" class=" font12 input-box-list-title">申请金额：</label>
				<div class="input-box-list-value">
					<input type="text" class="form-control" name="amt" id="amt">
				</div>
			</div>
		</form>
	</div>
	<%-- block 2 --%>
	<div class="page-header" id="block-1">
		<h4 class="text-info">
			<strong>2.&nbsp;</strong>执行范围&nbsp;-&nbsp; <small>Basic
				Information.</small>
		</h4>
	</div>
	<div class="form-horizontal row" style="margin: 0 0 20px 0;">
		<div class="form-group col-md-12 input-box-list">
			<label for="input1" class=" font12 input-box-list-title" style="width:120px;">执行范围：</label>
			<div class="input-box-list-value">
				<div class="input-group">
					<input type="text" class="form-control tokenfield" id="agentArea"
						name="agentArea" />
					<div class="input-group-addon" style="cursor: pointer;"
						id="addPolicyArea">
						<i class="icon icon-plus"></i>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group col-md-12 input-box-list" id="custs"
			style="display: none">
			<label for="input1" class=" font12 input-box-list-title" style="width:120px;">执行客户：</label>
			<div class="input-box-list-value">
				<div class="input-group">
					<input type="text" class="form-control tokenfield" id="agentCust"
						name="agentCust" />
					<div class="input-group-addon" style="cursor: pointer;"
						id="addPolicyCust">
						<i class="icon icon-plus"></i>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%-- block 3 --%>
	<div id="policysku" style="display: none">
		<div class="page-header" id="block-2">
			<h4 class="text-info">
				<strong>3.&nbsp;</strong>SKU政策&nbsp;-&nbsp; <small>Contact
					Information.</small>
			</h4>
		</div>
		<!-- <div class="row" style="margin: 0 0 20px 0;" id="policycontent">
			<div id="content1"></div>
			<div id="content2"></div>
			<div id="content3"></div>
			<div class="col-md-4">
				<div class="contact-box-add">
					<i class="icon icon-plus" id="btn-add"></i>&nbsp;&nbsp;新增
				</div>
			</div>
		</div> -->
		<div id="policysku-toolbar">
			<a id="btn-add" class="btn btn-primary  pull-left"
				style="width: auto; margin-right: 5px;"> <i
				class="icon icon-plus"></i> 新增SKU
			</a> <a id="del-bstb-row" class="btn btn-danger  pull-left"
				style="width: auto; margin-right: 5px;"> <i
				class="icon icon-remove"></i> 删除
			</a>
		</div>
		<%-- 数据表格 --%>
		<table id="policysku-table" class="order-box"
			data-toolbar="#policysku-toolbar" data-search="true"
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
	<%-- submit button --%>
	<div class="text-center"
		style="padding-top: 30px; border-top: 1px solid rgba(0, 0, 0, .15);">
		<button class="btn btn-warning" style="padding: 8px 25px;"
			type="button" onclick="dosave(1)">
			<i class="icon icon-save"></i>&nbsp;&nbsp;保存
		</button>
		<button class="btn btn-primary btn-long" type="button"
			onclick="dosave(2)">
			<i class="icon icon-check"></i>&nbsp;&nbsp;提交
		</button>
	</div>
	<div class="modal fade" id="chooseArea">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">选择地区</h4>
				</div>
				<div class="modal-body" style="height: 400px; overflow: auto;">
					<ul id="areaTrea" class="ztree" style="width: 100%; height: 100%;"></ul>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="btn_choose_area">选择</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<div class="modal fade" id="chooseCust">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">选择客户</h4>
				</div>
				<div class="modal-body" style="height: 400px; overflow: auto;">
					<ul id="areaCust" class="ztree" style="width: 100%; height: 100%;"></ul>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="btn_choose_cust">选择</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<script type="text/javascript">
		//核销方式
		function getverificationType(type) {
			var data = new Object();
			<c:forEach items="${dict['POLICY_TYPE_VERIFICATION']}" var="verification">
			data["${verification.chooseVal}"] = "${verification.showText}"
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
	<script type="text/javascript"
		src="static/ztree/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript"
		src="static/tokenfield/bootstrap-tokenfield.min.js"></script>
	<script type="text/javascript"
		src="static/bootstrap/js/bootstrap-datetimepicker.min.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="static/js/salepolicy/addPolicy.js"></script>
</body>
</html>

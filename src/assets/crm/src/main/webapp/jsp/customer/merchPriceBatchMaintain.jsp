<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>管理系统-客户价格设置</title>
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
			客户价格批量维护申请 <small> </small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 客户管理</a></li>
			<li class="active">客户产品价格管理</li>
		</ol>
	</section>
	<div class="form-horizontal row" style="margin: 0 0 20px 0;">
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="scust" class=" font12 input-box-list-title">客户名称：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" id="scust"
					placeholder="客户名称...">
			</div>
		</div>
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="sorg" class=" font12 input-box-list-title">所属销售组织：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance" id="sorg">
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
			<label for="sregin" class=" font12 input-box-list-title">大区：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance" id="sregin"
					disabled="disabled">
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
			<label for="sarea" class=" font12 input-box-list-title">地区：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance" id="sarea">
					<option value="">全部</option>
				</select>
			</div>
		</div>
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="sbrand" class=" font12 input-box-list-title">产品品牌：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance" id="sbrand">
					<option value="">全部</option>
				</select>
			</div>
		</div>
		<div class="form-group col-md-4 col-sm-6 input-box-list">
			<label for="smateiralId" class=" font12 input-box-list-title">物料编号：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" id="smateiralId"
					placeholder="物料编号...">
			</div>
		</div>
	</div>
	<div class="col-md-12" id="batchMaintainTool">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button id="searchButton" class="btn btn-primary" type="button">
				<i class="icon icon-search"> </i>查询
			</button>
			<button class="btn btn-success" type="button" data-toggle="modal"
				data-target="#addModal">
				<i class="icon icon-plus"> </i>新增
			</button>
			<button type="button" class="btn btn-edit btn-warning">
				<i class='icon icon-edit'></i>修改
			</button>
			<button type="button" class="btn   btn-primary" id="btn-detail">
				<i class='icon icon-edit'></i>详情
			</button>
			<button type="button" class="btn btn-del btn-danger">
				<i class='icon icon-remove'></i>删除
			</button>
			<button class="btn btn-success" type="button" data-toggle="modal"
				data-target="#importModal">
				<i class="icon icon-plus"> </i>导入
			</button>
			<button type="button" class="btn btn-success" id="auditButton">
				<i class='icon icon-plus'></i>提交审批
			</button>
		</div>
	</div>
	<div class="row" style="margin: 0 0 20px 0;">
		<div class="col-md-12">
			<%-- 数据表格 --%>
			<table id="batchMaintain" class="contract-box"
				data-show-refresh="true" data-show-columns="true">
			</table>
		</div>
	</div>
	<!-- addDialog-->
	<div class="modal fade" id="addModal" tabindex="-1"
		aria-labelledby="dictLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">新增客户价格维护申请</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal"
						action="customer/sku/addProductBatchMaintain" method="post"
						id="addBatchMaintainFrom">
						<div class="form-group">
							<label for="orgId" class="col-sm-3 control-label">团队名称</label>
							<div class="col-sm-6">
								<select class="form-control no-appearance" id="orgId"
									name="orgId">
									<option value=""></option>
									<c:forEach items="${org}" var="orgs">
										<c:if test="${orgs.levels==2}">
											<option value="${orgs.id}">${orgs.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="reginId" class="col-sm-3 control-label">大区名称</label>
							<div class="col-sm-6">
								<select class="form-control no-appearance" id="reginId"
									name="reginId" disabled="disabled">
									<option value=""></option>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="areaId" class="col-sm-3 control-label">地区名称</label>
							<div class="col-sm-6">
								<select class="form-control no-appearance" id="areaId"
									name="areaId">
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="merchCustId" class="col-sm-3 control-label">客户名称</label>
							<div class="col-sm-6">
								<select class="form-control no-appearance" id="merchCustId"
									name="merchCustId" disabled="disabled">
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="brand" class="col-sm-3 control-label">品牌名称</label>
							<div class="col-sm-6">
								<select class="form-control no-appearance" id="brand"
									name="brand">
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="materialId" class="col-sm-3 control-label">物料编码</label>
							<div class="col-sm-6">
								<select class="form-control no-appearance" id="materialId"
									name="materialId">
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="bDate" class="col-sm-3 control-label">起效日期</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="bDate" name="bDate"
									placeholder="开始时间">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="eDate" class="col-sm-3 control-label">失效日期</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="eDate" name="eDate"
									placeholder="截止时间">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="type" class="col-sm-3 control-label">调整类型</label>
							<div class="col-sm-6">
								<select class="form-control no-appearance" id="type" name="type">
									<option value="1">到岸价</option>
									<option value="2">客户调整价</option>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="adjustDirection" class="col-sm-3 control-label">调整方向</label>
							<div class="col-sm-6">
								<select class="form-control no-appearance" id="adjustDirection"
									name="adjustDirection">
									<option value="1">重新定价</option>
									<option value="2">幅度调整</option>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="price" class="col-sm-3 control-label">调整价格</label>
							<div class="col-sm-6">
								<input class="form-control " id="price" name="price" />
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-add-save" form="addBatchMaintainFrom"
						class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- editDialog-->
	<div class="modal fade" id="editModal" tabindex="-1"
		aria-labelledby="dictLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">修改客户价格维护申请</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal"
						action="customer/sku/editProductBatchMaintain" method="post"
						id="editBatchMaintainFrom">
						<input type="hidden" id="editid" name="id" />
						<div class="form-group">
							<label for="editorgid" class="col-sm-3 control-label">团队名称</label>
							<div class="col-sm-6">
								<select class="form-control no-appearance" id="editorgid"
									name="orgId">
									<option value=""></option>
									<c:forEach items="${org}" var="orgs">
										<c:if test="${orgs.levels==2}">
											<option value="${orgs.id}">${orgs.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="editreginId" class="col-sm-3 control-label">大区名称</label>
							<div class="col-sm-6">
								<select class="form-control no-appearance" id="editreginId"
									name="reginId" disabled="disabled">
									<option value=""></option>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="editareaId" class="col-sm-3 control-label">地区名称</label>
							<div class="col-sm-6">
								<select class="form-control no-appearance" id="editareaId"
									name="areaId">
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="editbrand" class="col-sm-3 control-label">品牌名称</label>
							<div class="col-sm-6">
								<select class="form-control no-appearance" id="editbrand"
									name="brand">
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="editmerchCustId" class="col-sm-3 control-label">客户名称</label>
							<div class="col-sm-6">
								<select class="form-control no-appearance" id="editmerchCustId"
									name="merchCustId" disabled="disabled">
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="editmaterialId" class="col-sm-3 control-label">物料编码</label>
							<div class="col-sm-6">
								<select class="form-control no-appearance" id="editmaterialId"
									name="materialId">
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="editbDate" class="col-sm-3 control-label">起效日期</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editbDate"
									name="bDate" placeholder="开始时间">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="editeDate" class="col-sm-3 control-label">失效日期</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editeDate"
									name="eDate" placeholder="截止时间">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="edittype" class="col-sm-3 control-label">调整类型</label>
							<div class="col-sm-6">
								<select class="form-control no-appearance" id="edittype"
									name="type">
									<option value="1">到岸价</option>
									<option value="2">客户调整价</option>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
                        <div class="form-group">
                            <label for="adjustDirection" class="col-sm-3 control-label">调整方向</label>
                            <div class="col-sm-6">
                                <select class="form-control no-appearance"
                                        id="editadjustDirection" name="adjustDirection">
                                    <option value="1">重新定价</option>
                                    <option value="2">幅度调整</option>
                                </select>
                            </div>
                            <small class="help-block col-sm-3"></small>
                        </div>
                        <div class="form-group">
                            <label for="editprice" class="col-sm-3 control-label">调整价格</label>
                            <div class="col-sm-6">
                                <input class="form-control " id="editprice" name="price" />
                            </div>
                            <small class="help-block col-sm-3"></small>
                        </div>
					</form>
				</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" id="btn-edit-save"
                            form="editBatchMaintainFrom" class="btn btn-primary">保存</button>
                </div>
			</div>
		</div>
	</div>

	<!-- importDialog-->
	<div class="modal fade" id="importModal" tabindex="-1"
		aria-labelledby="dictLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">客戶价格批量导入</h4><a href="customer/sku/exportTemplate">导入模板</a>
				</div>
				<div class="modal-body" style="overflow-y: auto">
					<!-- 客戶价格批量导入  -->
					<form class="form-horizontal" method="post"	action="customer/sku/importCustPrice" enctype="multipart/form-data"	id="importForm">
						<div class="form-group">
							<label for="importbDate" class="col-sm-3 control-label">起效日期</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="importbDate" name="priceBdate"
									placeholder="开始时间">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="importeDate" class="col-sm-3 control-label">失效日期</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="importeDate" name="priceEdate"
									placeholder="截止时间">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="uploadFile" class="col-sm-3 control-label">价格文件</label>
							<div class="col-sm-5">
								<input type="file"  accept=".xlsx" class="form-control" id="uploadFile"
									name="file">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-import"	class="btn btn-primary btn-save" form="importForm">上传</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function getStates(state) {
			var data = new Object();
			<c:forEach items="${dict['PRICE_BATCH_MAINTAIN_STATES']}" var="states">
			data["${states.chooseVal}"] = "${states.showText}"
			</c:forEach>
			if (data[state]) {
				return data[state];
			} else {
				return "未知";
			}
		}
		function getType(state) {
			var data = new Object();
			<c:forEach items="${dict['PRICE_BATCH_MAINTAIN_TYPE']}" var="states">
			data["${states.chooseVal}"] = "${states.showText}"
			</c:forEach>
			if (data[state]) {
				return data[state];
			} else {
				return "未知";
			}
		}
		function getDirection(state) {
			var data = new Object();
			<c:forEach items="${dict['PRICE_BATCH_MAINTAIN_DIRECTION']}" var="states">
			data["${states.chooseVal}"] = "${states.showText}"
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
	<script type="text/javascript"
		src="static/bootstrap/js/bootstrap-datetimepicker.min.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"
		charset="UTF-8"></script>
	<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript"
		src="static/js/customer/merchPriceBatchMaintain.js"></script>
</body>
</html>

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
<title>管理系统-套餐组合</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link href="static/table/new/bootstrap-editable.css" rel="stylesheet">
<link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
</head>
<body class="container-fluid content">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			套餐组合页面<small>组合套餐</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-dashboard"></i> 首页</a></li>
			<li><a href="#"> 销售政策管理</a></li>
			<li class="active">新增套餐</li>
		</ol>
	</section>
	<div class="col-md-12">
		<form class="form form-horizontal" action="combination/addOrUpdateCombination" method="POST"
			id="addCombinationForm">
			<div class="form-group">
				<label for="code" class="col-sm-2 control-label">套餐编码</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="code" name="code"
						placeholder="套餐编码">
				</div>
				<small class="help-block col-sm-2"></small>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">套餐名称</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="name" name="name"
						placeholder="套餐名称">
				</div>
				<small class="help-block col-sm-2"></small>
			</div>
			<div class="form-group hide">
				<label for="price" class="col-sm-2 control-label">套餐价格</label>
				<div class="col-sm-8 ">
					<div class="input-group">
					<input type="text" class="form-control" id="price" name="price"
						placeholder="套餐价格" readonly="readonly">
					<div class="input-group-addon">元</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="weight" class="col-sm-2 control-label">套餐规格(g)</label>
				<div class="col-sm-8 ">
					<input type="text" class="form-control" id="weight" name="weight"
						placeholder="套餐规格">
				</div>
				<small class="help-block col-sm-2"></small>
			</div>
			<div class="form-group">
				<label for="modelType" class="col-sm-2 control-label">生意模式</label>
				<div class="col-sm-8">
					<select class="form-control no-appearance" id="modelType"
						name="modelType">
						<option></option>
						<c:forEach items="${dict['BUSINESS_MODEL']}" var="it">
							<option value="${it.chooseVal}">${it.showText}</option>
						</c:forEach>
					</select>
				</div>
				<small class="help-block col-sm-2"></small>
			</div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">套餐时间</label>
				<div class="col-sm-4">
					<input type="date" class="form-control" id="bDate" name="bDate"
						placeholder="开始时间">
				</div>
				<div class="col-sm-4">
					<input type="date" class="form-control" id="eDate" name="eDate"
						placeholder="结束时间">
				</div>
				<small class="help-block col-sm-2"></small>
			</div>
			<input type="hidden" id="lines" name="linedata">
			<input type="hidden" id="rebates" name="rebatedata">
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">套餐内产品</label>
				<div class="col-sm-8">
					<div class="btn-group btn-group-sm" id="tcnspTools">
						<button type="button" class="btn btn-primary"
							id="btn_add_products">
							<i class="icon icon-plus"></i>
						</button>
						<!-- <button class="btn btn-info">
							<i class="icon icon-edit"></i>
						</button> -->
						<button type="button" class="btn btn-danger" id="btn_del_product">
							<i class="icon icon-remove"></i>
						</button>
					</div>
					<table id="tcnsp"></table>
				</div>
			</div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">返点比例</label>
				<div class="col-sm-8">
					<div class="btn-group btn-group-sm" id="tssTools">
						<button type="button" class="btn btn-primary" id="btn-add-point">
							<i class="icon icon-plus"></i>
						</button>
						<!-- <button class="btn btn-info">
							<i class="icon icon-edit"></i>
						</button> -->
						<button type="button" class="btn btn-danger" id="btn_del_rebate">
							<i class="icon icon-remove"></i>
						</button>
					</div>
					<table id="tss"></table>
				</div>
			</div>
		</form>
			<div class="form-group">
				<div class="col-sm-10 text-center">
					<button type="submit" class="btn btn-primary" id="btn-save" form="addCombinationForm">保存</button>
				</div>
			</div>
	</div>
	<!-- addDialog-->
	<div class="modal fade" id="addProductModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">新增套餐内商品</h4>
				</div>
				<div class="modal-body">
					<table id="materialTable"></table>
				</div>
				<div class="modal-footer">
					<button type="button" id="btn-appendData" class="btn btn-primary">选择</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- addDialog-->
	<div class="modal fade" id="addPointsModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">新增阶梯价</h4>
				</div>
				<div class="modal-body">
					<form class="form form-horizontal">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">数量限制</label>
							<div class="col-sm-8 ">
								<div class="input-group">
									<input type="text" class="form-control" id="limitNum"
										value="1">
									<div class="input-group-addon">个</div>
								</div>
							</div>
							<div class="help-block col-sm-2"></div>
						</div>

						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">返货比例</label>
							<div class="col-sm-8 ">
								<div class="input-group">
									<input type="text" class="form-control" id="rebateRatio"
										value="1">
									<div class="input-group-addon">%</div>
								</div>
							</div>
							<div class="help-block col-sm-2"></div>
						</div>

						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">返货物料</label>
							<div class="col-sm-8">
								<select class="form-control" id="rebateMaterial">
								</select>
							</div>
							<div class="help-block col-sm-2"></div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" id="btn-addRbate" class="btn btn-primary">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/table/new/bootstrap-table-editable.js"></script>
	<script type="text/javascript" src="static/table/new/bootstrap-editable.js"></script>
    <script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="static/js/combination/add.js"></script>
</body>
</html>

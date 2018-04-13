<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>套餐管理-套餐申请</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
    <link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
    <link rel="stylesheet"href="static/tokenfield/css/bootstrap-tokenfield.min.css">
</head>
<body class="container-fluid content">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>套餐申请<small>套餐管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-dashboard"></i> 首页</a></li>
        <li><a href="#"> 套餐管理</a></li>
        <li class="active">套餐申请</li>
    </ol>
</section>
<div class="col-md-12" id="combinationApplyTool">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
        <button type="button"  class="btn btn-warning" data-toggle="modal" 
				data-target="#addApplyModal"><i class='icon icon-edit'></i> 新增</button>
        <button type="button" id="btn-edit" class="btn btn-info"><i class='icon icon-eye-open'></i> 修改</button>
        <button type="button" id="btn-details" class="btn btn-warning btn-look"><i class='icon icon-edit'></i> 详情</button>
        <button type="button" id="btn-del" class="btn btn-info"><i class='icon icon-eye-open'></i> 删除</button>
        <button type="button" id="btn-merch" class="btn btn-info hide"><i class='icon icon-eye-open'></i>对应客户</button>
        <button type="button" id="btn-audit" class="btn btn-info"><i class='icon icon-eye-open'></i> 提交</button>
    </div>
</div>
<div class="col-md-12">
    <table id="combinationApplyTable"></table>
</div>

	<!-- addDialog-->
	<div class="modal fade" id="addApplyModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">套餐申请</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="combinationApply/addOrUpdateApply" id="addApplyForm">
						<div class="form-group">
							<label for="organizationId" class="col-sm-3 control-label">销售组织</label>
							<div class="col-sm-6">
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
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="modelType" class="col-sm-3 control-label">生意模式</label>
							<div class="col-sm-6">
								<select class="form-control no-appearance" id="modelType" name="modelType">
									<option></option>
									<c:forEach items="${dict['BUSINESS_MODEL']}" var="it">
										<option value="${it.chooseVal}">${it.showText}</option>
									</c:forEach>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="packageId" class="col-sm-3 control-label">套餐编码</label>
							<div class="col-sm-6">
								<select  class="form-control" id="packageId"name="packageId">
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group hide">
							<label  class="col-sm-3 control-label">套餐价格</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="price" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>

						<div class="form-group">
							<label  class="col-sm-3 control-label">套餐规格</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="weight" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label  class="col-sm-3 control-label">套餐开始时间</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="bDate" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label  class="col-sm-3 control-label">套餐结束时间</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="eDate" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label   class="col-sm-3 control-label">执行范围：</label>
							<div class="col-sm-6">
								<div class="input-group">
									<input type="text" class="form-control tokenfield"
										id="range" name="range"  readonly="readonly"/>
									<div class="input-group-addon" style="cursor: pointer;"
										id="addRange">
										<i class="icon icon-plus"></i>
									</div>
								</div>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-add" class="btn btn-primary btn-save"
						form="addApplyForm">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	<!-- editDialog-->
	<div class="modal fade" id="editApplyModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">套餐修改</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="combinationApply/addOrUpdateApply" id="addApplyForm">
						<input type="hidden" name="id" id="editid">
						<div class="form-group">
							<label for="organizationId" class="col-sm-3 control-label">销售组织</label>
							<div class="col-sm-6">
								<select class="form-control" name="organizationId"
									id="editorganizationId">
									<option></option>
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
							<label for="modelType" class="col-sm-3 control-label">生意模式</label>
							<div class="col-sm-6">
								<select class="form-control no-appearance" id="editmodelType" name="modelType">
									<option></option>
									<c:forEach items="${dict['BUSINESS_MODEL']}" var="it">
										<option value="${it.chooseVal}">${it.showText}</option>
									</c:forEach>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="packageId" class="col-sm-3 control-label">套餐编码</label>
							<div class="col-sm-6">
								<select  class="form-control" id="editpackageId"name="packageId">
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label  class="col-sm-3 control-label">套餐价格</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editprice" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>

						<div class="form-group">
							<label  class="col-sm-3 control-label">套餐规格</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editweight" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label  class="col-sm-3 control-label">套餐开始时间</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editbDate" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label  class="col-sm-3 control-label">套餐结束时间</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editeDate" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label  for="range" class="col-sm-3 control-label">执行范围：</label>
							<div class="col-sm-6">
								<div class="input-group">
									<input type="text" class="form-control tokenfield" readonly="readonly"
										id="editrange" name="range" />
									<div class="input-group-addon" style="cursor: pointer;"
										id="editaddRange">
										<i class="icon icon-plus"></i>
									</div>
								</div>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-edit-save" class="btn btn-primary btn-save"
						form="addApplyForm">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	<!-- showDialog-->
	<div class="modal fade" id="showApplyModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">套餐详情</h4>
				</div>
				<div class="modal-body">
						<div class="form-group">
							<label for="organizationId" class="col-sm-3 control-label">销售组织</label>
							<div class="col-sm-6">
								<select class="form-control" id="showorganizationId" disabled="disabled">
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
							<label for="modelType" class="col-sm-3 control-label">生意模式</label>
							<div class="col-sm-6">
								<select class="form-control no-appearance" id="showmodelType" disabled="disabled">
									<c:forEach items="${dict['BUSINESS_MODEL']}" var="it">
										<option value="${it.chooseVal}">${it.showText}</option>
									</c:forEach>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="packageId" class="col-sm-3 control-label">套餐编码</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="showcode" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="packageId" class="col-sm-3 control-label">套餐名称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="showname" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label  class="col-sm-3 control-label">套餐价格</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="showprice" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>

						<div class="form-group">
							<label  class="col-sm-3 control-label">套餐规格</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="showweight"  readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label  class="col-sm-3 control-label">套餐开始时间</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="showbDate"  readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label  class="col-sm-3 control-label">套餐结束时间</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="showeDate" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label  class="col-sm-3 control-label">执行范围：</label>
							<div class="col-sm-6">
								<input type="text" class="form-control tokenfield"	id="showrange" readonly="readonly" />
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	<div class="modal fade" id="rangeAreaModel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">选择执行范围</h4>
				</div>
				<div class="modal-body" style="height: 400px; overflow: auto;">
					<ul id="rangeTrea" class="ztree" style="width: 100%; height: 100%;"></ul>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="btn_choose_range">选择</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	<div class="modal fade" id="editrangeAreaModel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">选择执行范围</h4>
				</div>
				<div class="modal-body" style="height: 400px; overflow: auto;">
					<ul id="editrangeTrea" class="ztree" style="width: 100%; height: 100%;"></ul>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="btn_edit_range">选择</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- merchDialog-->
	<div class="modal fade" id="merchModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">套餐所属客户</h4>
				</div>
				<div class="modal-body">
					<div id="merch-toolbar">
						<a id="add-merch-row" class="btn btn-primary  pull-left"
							style="width: auto; margin-right: 5px;"> <i
							class="icon icon-plus"></i>新增客户
						</a> 
						<a id="del-merch-row" class="btn btn-danger  pull-left"
							style="width: auto; margin-right: 5px;"> <i
							class="icon icon-remove"></i>移除客户
						</a>
					</div>
					<table id="merchTable" data-toolbar="merch-toolbar"
						data-detail-formatter="detailFormatter" data-id-field="id" data-search="true" data-sortable="true"></table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	<!-- merchDialog-->
	<div class="modal fade" id="addmerchModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">新增客户</h4>
				</div>
				<div class="modal-body">
					<table id="addmerchTable" 	data-detail-formatter="detailFormatter"  data-search="true"></table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="btn_add_merch">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
	</div>
		<!-- /.modal-dialog -->
<script type="text/javascript">
	function getModelType(type) {
		var data = new Object();
		<c:forEach items="${dict['BUSINESS_MODEL']}" var="type">
		data["${type.chooseVal}"] = "${type.showText}"
		</c:forEach>
		if (data[type]) {
			return data[type];
		} else {
			return "未知";
		}
	}
	function getStates(state) {
		var data = new Object();
		<c:forEach items="${dict['PACKAGE_APPLY_STATES']}" var="s">
		data["${s.chooseVal}"] = "${s.showText}"
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
<script type="text/javascript" src="static/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="static/tokenfield/bootstrap-tokenfield.min.js"></script>
<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="static/js/combination/applyIndex.js"></script>
</body>
</html>

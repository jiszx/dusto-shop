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
<title>管理系统-促销品物料维护</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<style type="text/css">
.customizeWidth {
	width: 70%;
	display: inline-block;
}
</style>
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			促销品物料维护<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 促销品管理</a></li>
			<li class="active">促销品物料维护</li>
		</ol>
	</section>
	<div class="col-md-12" style="padding: 0;">
		<div class="form-group col-md-4 col-sm-6">
			<label>促销品名称：</label> <input type="text"
				class="form-control customizeWidth" id="searchname"
				placeholder="促销品名称">
		</div>
		<div class="form-group col-md-4 col-sm-6">
			<label>销售组织：</label> 
			<select class="form-control customizeWidth"	id="searchorgid">
					<option></option>
			<c:forEach items="${org}" var="orgs">
				<option value="${orgs.id}">${orgs.name}</option>
			</c:forEach>
			</select>
		</div>
		<div class="form-group  col-md-4 col-sm-6 ">
			<button class="btn btn-primary" id="btn-search">
				<i class="icon icon-search"></i>&nbsp;&nbsp;开始搜索
			</button>
		</div>
	</div>
	<div class="col-md-12" id="promotionMaterialTool" style="padding:0;">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#addpromotionMaterialModal">
				<i class='icon icon-plus'></i>新增
			</button>
			<button type="button" class="btn btn-edit btn-warning">
				<i class='icon icon-edit'></i>修改
			</button>
			<button type="button" class="btn btn-del btn-danger">
				<i class='icon icon-remove'></i>删除
			</button>

		</div>
	</div>
	<div class="col-md-12">
		<table id="promotionMaterialTable"></table>
	</div>
	<!-- addDialog-->
	<div class="modal fade" id="addpromotionMaterialModal" tabindex="-1"
		role="promotionMaterialTool" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">新增促销品</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="promotion/material/add" id="addPromotionMaterialForm">
						<div class="form-group">
							<label for="organizationId" class="col-sm-3 control-label">所属销售组织</label>
							<div class="col-sm-6">
								<select class="form-control" name="organizationId"
									id="organizationId">
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
							<label for="name" class="col-sm-3 control-label">促销品名称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="name" name="name">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="price" class="col-sm-3 control-label">单价(元)</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="price" name="price">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="unit" class="col-sm-3 control-label">规格</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="unit" id="unit">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="description" class="col-sm-3 control-label">描述</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="description"
									id="description">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-add" class="btn btn-primary btn-save"
						form="addPromotionMaterialForm">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- editDialog-->
	<div class="modal fade" id="editpromotionMaterialModal" tabindex="-1"
		role="promotionMaterialTool" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">修改促销品</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="promotion/material/edit" id="editPromotionMaterialForm">
						<div class="form-group">
							<label for="organizationId" class="col-sm-3 control-label">所属销售组织</label>
							<input type="hidden" name="id" id="editid">
							<div class="col-sm-6">
								<select class="form-control" name="organizationId"
									id="editorganizationId">
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
							<label for="name" class="col-sm-3 control-label">促销品名称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editname"
									name="name">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="price" class="col-sm-3 control-label">单价(元)</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editprice"
									name="pirce">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="unit" class="col-sm-3 control-label">规格</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="unit"
									id="editunit">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="description" class="col-sm-3 control-label">描述</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="description"
									id="editdescription">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-edit-save"
						class="btn btn-primary btn-save" form="editPromotionMaterialForm">保存</button>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript"
		src="static/js/promotion/promotionMaterial.js"></script>
</body>
</html>

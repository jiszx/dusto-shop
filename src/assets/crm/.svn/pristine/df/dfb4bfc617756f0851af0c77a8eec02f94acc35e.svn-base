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
<title>管理系统-促销品采购方维护</title>
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
			促销品采购方维护<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 促销品管理</a></li>
			<li class="active">促销品采购方维护</li>
		</ol>
	</section>
	<div class="col-md-12 col-sm-12" style="padding: 0;">
		<div class="form-group col-md-4 col-sm-6">
			<label>库房名称：</label> <input type="text"
				class="form-control customizeWidth" id="searchname"
				placeholder="采购方名称">
		</div>
		<div class="form-group col-md-4 col-sm-6">
			<label>销售组织：</label> <select class="form-control customizeWidth"
				id="searchorgid">
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
	<div class="col-md-12" id="promotionPurTool" style="padding: 0;">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#addpromotionPurModal">
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
		<table id="promotionPurTable"></table>
	</div>
	<!-- addDialog-->
	<div class="modal fade" id="addpromotionPurModal" tabindex="-1"
		role="promotionPurTool" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">新增采购方信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="promotion/Pur/add" id="addPromotionPurForm">
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
							<label for="name" class="col-sm-3 control-label">采购方名称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="name" name="name">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="contact" class="col-sm-3 control-label">联系人</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="contact"
									name="contact">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="contactTel" class="col-sm-3 control-label">联系电话</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="contactTel"
									name="contactTel">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-add" class="btn btn-primary btn-save"
						form="addPromotionPurForm">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- editDialog-->
	<div class="modal fade" id="editpromotionPurModal" tabindex="-1"
		role="promotionPurTool" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">修改采购方信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="promotion/Pur/edit" id="editPromotionPurForm">
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
							<label for="name" class="col-sm-3 control-label">采购方名称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editname"
									name="name">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="contact" class="col-sm-3 control-label">联系人</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editcontact"
									name="contact">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="contactTel" class="col-sm-3 control-label">联系人电话</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="contactTel"
									id="editcontactTel">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-edit-save"
						class="btn btn-primary btn-save" form="editPromotionPurForm">保存</button>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript"
		src="static/js/promotion/promotionPUR.js"></script>
</body>
</html>

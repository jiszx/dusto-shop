<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":"
      + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>管理系统-工厂管理</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<style type="text/css">
	#productTable.table {
  table-layout: fixed;
  width:200% !important;
  max-width:200% !important;
}
</style>
</head>
<body class="container-fluid content">

	<section class="content-header">
		<h1>
			工厂管理<small>工厂信息管理</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 数据管理</a></li>
			<li class="active">工厂管理</li>
		</ol>
	</section>

	<div class="col-md-12" id="dictTool" style="padding:10px 0;">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button id="showMaterials" type="button" class="btn btn-info">
				<i class='icon icon-eye-open'></i>查看物料信息
			</button>
			<button id="editOrg" type="button" class="btn btn-edit btn-warning">
				<i class='icon icon-edit'></i>修改
			</button>
		</div>
	</div>
	<div class="col-md-12">
		<div class="col-md-3 col-sm-5" style="padding:0">
			<label class="control-label" style="height:30px;line-height:30px;padding-right:20px;">工厂名称:</label><input id="nameCondition" type="text" class="form-control" placeheader="请输入工厂名称" style="width:70%;display:inline-block;">
		</div>
		<div class="col-md-3  col-sm-5 col-md-offset-1" style="padding:0">
			<label class="control-label" style="height:30px;line-height:30px;padding-right:20px;">城市:</label><input id="cityCondition" type="text" class="form-control" placeheader="请输入工厂名称" style="width:70%;display:inline-block;">
		</div>
		<div class="col-md-3 col-sm-2" style="padding:0">
		   <button id="search" class="btn btn-primary"><i class="icon icon-search"></i>查询</button>
		</div>
	</div>
	<div class="col-md-12">
		<table id="dictTable"></table>
	</div>


	<!-- addDialog-->
	<div class="modal  fade" id="showMaterialModal" tabindex="-1"
		role="dialog" aria-labelledby="dictLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">工厂物料信息</h4>
				</div>
				<div class="modal-body">
					<table id="productTable"></table>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- editDialog-->
	<div class="modal fade" id="editOrgModal" tabindex="-1"
		aria-labelledby="dictLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="editdictLabel">修改</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="factory/edit.json"
						method="post" id="editOrgForm">
						<div class="form-group">
							<label for="colName" class="col-sm-3 control-label">工厂名称</label>
							<div class="col-sm-6">
								<input type="hidden" id="editid" name="id"> <input
									type="text" class="form-control" id="editname" name="colName"
									readonly>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="colName" class="col-sm-3 control-label">工厂简称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editabbrName"
									name="colName" readonly>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="organizationId" class="col-sm-3 control-label">所属销售组织</label>
							<div class="col-sm-6">
								<select class="form-control" name="organizationId"
									id="editorganizationId">
									<option value="T0101">成都益盐堂</option>
									<option value="T">调味品事业部</option>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-edit-save" form="editOrgForm"
						class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/sapData/factory.js"></script>
</body>
</html>

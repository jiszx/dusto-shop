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
<title>管理系统-虚拟仓库管理</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<style type="text/css">
.customizeWidth {
	width: 60%;
	display: inline-block;
}
</style>
</head>
<body class="container-fluid content">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			虚拟仓库入库<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">虚拟仓库管理</a></li>
			<li class="active">虚拟仓库入库</li>
		</ol>
	</section>
	<form action="" id="searchForm">
	<div class="col-md-12" style="padding: 0;">
		<div class="form-group col-md-3 col-sm-6">
			<label>产品名称:</label> <input type="text" id="searchname" name="materialName"
				class="form-control customizeWidth" placeheader="请输入产品名称">
		</div>
		<div class="form-group col-md-3 col-sm-6">
			<label>仓库名称:</label>
			<select class="form-control customizeWidth" id="custType" name="custType">
				<option value="" selected}>全部</option>
				<c:forEach items="${dict.get('CUST_MERCH_TYPE') }" var="cust_type">
					<option value="${cust_type.chooseVal}">${cust_type.showText}仓库</option>
	            </c:forEach>
			</select>
		</div>
		<c:set var="vwStatus" value="<%=com.hhnz.virtualwarehouse.enu.VirtualWarehouseStatusEnu.values() %>"/>
		<div class="form-group col-md-3 col-sm-6" style="margin-left:29px;">
			<label>状态:</label>
			<select class="form-control customizeWidth" id="vwStatus" name="status">
				<option value="" selected}>全部</option>
				<c:forEach items="${vwStatus }" var="vms">
					<option value="${vms.getCode() }">${vms.getDesc() }</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group col-md-3 col-sm-6" style="margin-left:-29px;">
			<button type="button" class="btn btn-primary" id="btnSearch">
				<i class="icon icon-search">&nbsp;&nbsp;查询</i>
			</button>
		</div>
	</div>
	</form>
	<div class="col-md-12" id="vwEntryTool" style="padding: 0;">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn  btn-info" data-toggle="modal"
				data-target="#addvwEntryModal">
				<i class='icon icon-plus'></i> 入库申请
			</button>
			<button type="button" class="btn btn-edit btn-warning">
				<i class='icon icon-edit'></i> 修改申请
			</button>
			<button type="button" class="btn btn-del btn-danger">
				<i class='icon icon-remove'></i> 删除申请
			</button>
			<button type="button" class="btn btn-success" id="btn-audit">
				<i class='icon icon-check'></i> 提交审批
			</button>
		</div>
	</div>
	<div class="col-md-12">
		<table id="vwEntryTable"></table>
	</div>

	<!-- addDialog-->
	<div class="modal fade" id="addvwEntryModal" tabindex="-1"
		role="dialog" aria-labelledby="vwLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">虚拟仓库入库申请</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="vw/entry/add" id="addVWEntryForm">
						<div class="form-group">
							<label for="storesId" class="col-sm-3 control-label">入库仓库</label>
							<div class="col-sm-5">
								<select class="form-control" id="custType" name="custType">
									<c:forEach items="${dict.get('CUST_MERCH_TYPE') }" var="cust_type">
										<option value="${cust_type.chooseVal}" ${custType eq cust_type.chooseVal? 'selected':''}>${cust_type.showText}仓库</option>
						            </c:forEach>
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="vwId" class="col-sm-3 control-label">商品名称</label>
							<div class="col-sm-5">
								<select class="form-control" id="materialId" name="materialId">
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="num" class="col-sm-3 control-label">入库数量</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="amt" name="amt" >
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer" style="text-align:center;">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn_add" form="addVWEntryForm"
						class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- editDialog-->
	<div class="modal fade" id="editvwEntryModal" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">促销品入库申请修改</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="vw/entry/edit" id="editVWEntryForm">
						<input type="hidden" name="id" id="editid">
						<div class="form-group">
							<label for="storesId" class="col-sm-3 control-label">入库仓库</label>
							<div class="col-sm-5">
								<select class="form-control" id="editcustType" name="custType">
									<c:forEach items="${dict.get('CUST_MERCH_TYPE') }" var="cust_type">
										<option value="${cust_type.chooseVal}" ${custType eq cust_type.chooseVal? 'selected':''}>${cust_type.showText}仓库</option>
						            </c:forEach>
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="vwId" class="col-sm-3 control-label">商品名称</label>
							<div class="col-sm-5">
								<select class="form-control" id="editmaterialId" name="materialId">
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="num" class="col-sm-3 control-label">入库数量</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="editamt" name="amt" >
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-edit-save" class="btn btn-primary"
						form="editVWEntryForm">保存</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/virtualwarehouse/vwEntry.js"></script>
</body>
</html>

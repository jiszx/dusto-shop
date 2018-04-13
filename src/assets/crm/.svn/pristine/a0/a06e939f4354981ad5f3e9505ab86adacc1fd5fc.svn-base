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
<title>调拨单查询</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
<link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			物流权限 <small>物流权限配置</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li class="active">物流</li>
		</ol>
	</section>
	<div class="col-md-12 col-sm-12" id="logisticsTool"
		style="padding: 0; margin-top: 10px">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" id="btn-add" class="btn btn-primary" >
				<i class='icon icon-plus'></i> 新增
			</button>
			<!-- <button type="button" id="btn-edit" class="btn btn-edit">
				<i class='icon icon-edit'></i> 修改
			</button> -->
			<button type="button" id="btn-del" class="btn btn-del btn-danger">
            <i class='icon icon-remove'></i> 删除
       	    </button>
			<button type="button" class="btn btn-info" id="exportBtn">
				<i class='icon icon-file'></i> 导出
			</button>
		</div>
	</div>
	<div class="col-md-12" style="padding: 0;">
		<div class="col-md-8 col-sm-8">
			<div class="panel scroll panel-info org_info ">
				<div class="panel-heading">用户RDC列表</div>
				<div class="panel-body" style="overflow: auto;">
					<div class="col-md-12 col-sm-12" style="padding: 10 ;margin-top: 10px;">
						<form class=" form-inline" onsubmit="doSearch()">
								<label>用户名称 :</label> <input type="text" class="form-control"
									id="username">
								<button type="submit" class="btn btn-success" id="btn-search" style="margin-left: 250px;">
									<i class="icon icon-search"></i>&nbsp;&nbsp;查询
								</button>
						</form>
					</div>
					<div class="col-md-12 col-sm-12">
						<table id="logistics"></table>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-4 col-sm-4">
			<div class="panel scroll panel-info org_info ">
				<div class="panel-heading">地区信息 <button class="btn btn-area_save btn-info btn-xs" style="padding:6px 12px;position:absolute;top:5px;right:10%;"><i class="icon approval_img"></i>保存</button></div>
				<div class="panel-body" style="overflow: auto;">
					<ul id="areatree" class="ztree" style="width: 100%;"></ul>
				</div>
			</div>
		</div>
	</div>
	<!-- addDialog-->
	<div class="modal fade" id="addUserRdcModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">添加用户</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="logisticsRdcArea/addUserRdc" id="addUserRdcForm">
						
						<div class="form-group">
							<label for="merchCustId" class="col-sm-3 control-label">用户</label>
							<div class="col-sm-6">
								<select class="form-control" id="userId" name="userId" data-placeholder="请选择客户信息..." >
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="reason" class="col-sm-3 control-label">RDC</label>
							<div class="col-sm-6">
								<select class="form-control" name="rdcCode" id="rdcCode">
									<c:forEach items="${dict['VIRTUAL_WAREHOUSE_CODE']}" var="it">
										<option value="${it.chooseVal}" >${it.showText}</option>
									</c:forEach>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-save" class="btn btn-primary btn-save"
						form="addUserRdcForm">保存</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript"	src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" src="static/ztree/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript"	src="static/js/logistics/logisticsRdcArea.js"></script>
</body>
</html>

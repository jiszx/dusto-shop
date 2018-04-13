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
			虚拟仓库<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">虚拟仓库管理</a></li>
			<li class="active">虚拟仓库</li>
		</ol>
	</section>
	<form action="" id="searchForm">
	<div class="col-md-12" style="padding: 0;">
		<div class="form-group col-md-3 col-sm-6">
			<label>产品名称:</label> <input type="text" id="searchname" name="materialName"
				class="form-control customizeWidth" placeheader="请输入产品名称">
		</div>
		<div class="form-group col-md-3 col-sm-6">
			<label>产品编码:</label> <input type="text" id="searchid" name="materialCode"
				class="form-control customizeWidth" placeheader="请输入产品编码">
		</div>
		<div class="form-group col-md-2 col-sm-6">
			<label>仓库名称:</label>
			<select class="form-control customizeWidth" id="custType" name="custType">
				<option value="" selected}>全部</option>
				<c:forEach items="${dict.get('VIRTUAL_WAREHOUSE_CODE') }" var="vw">
					<option value="${vw.chooseVal}">${vw.showText}</option>
	            </c:forEach>
			</select>
		</div>
		<div class="form-group col-md-3 col-sm-6" style="margin-left:29px;">
			<label>工厂:</label>
			<select class="form-control customizeWidth" id="factoryCode" name="factoryCode">
				<option value="" selected}>全部</option>
				<c:forEach items="${factoryList }" var="vms">
					<option value="${vms.id }">${vms.name }</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group col-md-1 col-sm-6" style="margin-left:-29px;">
			<button type="button" class="btn btn-primary" id="btnSearch">
				<i class="icon icon-search">&nbsp;&nbsp;查询</i>
			</button>
		</div>
	</div>
	</form>
	<div class="col-md-12" id="vwTool" style="padding: 0;">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
		<button type="button" class="btn btn-warning" id="exportBtn"
                style="background-color: rgb(167, 86, 88);">
            <i class='icon icon-share'></i>导出
        </button>
		</div>
	</div>
	<div class="col-md-12">
		<table id="vwTable"></table>
	</div>
	
	<div class="modal fade" id="exportModal">
	    <div class="modal-dialog modal-lg">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal"
	                        aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	                <h4 class="modal-title">导出</h4>
	            </div>
	            <div class="modal-body" id="generateFile">
	                                                         正在生成excel，请耐心等待...
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
	        </div>
	    </div>
	</div>

	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/virtualwarehouse/vw.js"></script>
</body>
</html>
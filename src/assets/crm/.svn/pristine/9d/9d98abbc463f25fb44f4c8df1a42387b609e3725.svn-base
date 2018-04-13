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
<title>管理系统-客户库存查询</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link href="static/bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
	<link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
<style type="text/css">
.dropdown-menu {
	width: 25.3%;
}

.table-condensed {
	width: 100%;
}
.input-box-list-title{width:95px;}
</style>
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			客户库存管理<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">客户库存管理</a></li>
			<li class="active">库存数量查询</li>
		</ol>
	</section>
	<div class="row" style="padding: 0 0 20px 0;">
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px;">客户名称：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入客户名称"
					style="padding: 0; width: 70%;" id="custname">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input2" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px;">物料名称：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入物料名称"
					style="padding: 0; width: 70%;" id="sku">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input3" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px;">物料编码：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入物料编码"
					style="padding: 0; width: 70%;" id="materialId">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label class=" font12 input-box-list-title"
				style="float: left; line-height: 30px;">销售组织：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance"
					style="padding: 0; width: 70%;" id="organizationId">
					<option></option>
					<c:forEach items="${org}" var="orgs">
						<option value="${orgs.id}">${orgs.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label class=" font12 input-box-list-title"
				style="float: left; line-height: 30px;">虚拟仓：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance"
					style="padding: 0; width: 70%;" id="rdcCode">
					<option></option>
					<c:forEach items="${dict[VIRTUAL_WAREHOUSE_CODE]}" var="it">
						<option value="${it.choose_val}">${it.show_text}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group col-md-offset-3 col-md-3  col-sm-6 input-box-list">
			<button class="btn btn-primary" id="btn-search">
				<i class="icon icon-search"></i>&nbsp;&nbsp;开始搜索
			</button>
		</div>
	</div>
	<div class="col-md-12" id="invTableTool" style="padding-left:0;">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn btn-primary" id="btn-update">
				<i class='icon icon-plus'></i>更新数据
			</button>
			<button type="button" class="btn btn-primary" id="exportBtn">
				<i class='icon icon-new-window'></i>导出
			</button>
		</div>
	</div>
	<div class="col-md-12" style="padding-left:0;">
		<table id="invTable"></table>
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
	        <!-- /.modal-content -->
	    </div>
	    <!-- /.modal-dialog -->
	</div>

	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/customerInv/customerInvSearch.js"></script>
</body>
</html>

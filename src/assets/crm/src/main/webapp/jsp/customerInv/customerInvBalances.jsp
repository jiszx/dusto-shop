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
<title>管理系统-客户库存期间数据</title>
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
			<li class="active">库存期间查询</li>
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
				<input type="text" class="form-control" placeholder="输入来款账号"
					style="padding: 0; width: 70%;" id="sku">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input3" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px;">物料编码：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入来款城市名称"
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
				style="float: left; line-height: 30px;">所属期间：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance"
					style="padding: 0; width: 70%;" id="period">
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
	<div class="col-md-12" id="upAccountTool" style="padding-left:0;">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn btn-primary " id="btn-detail">
				<i class='icon icon-plus'></i>本期明细
			</button>
			<button type="button" class="btn btn-primary" id="btn-update">
				<i class='icon icon-plus'></i>更新数据
			</button>
		</div>
	</div>
	<div class="col-md-12" style="padding-left:0;">
		<table id="invBalanceTable"></table>
	</div>
	<div class="modal fade" id="balanceDetailsModal" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">本期发生明细</h4>
				</div>
				<div class="modal-body">
					<%-- 数据表格 --%>
					<table id="balanceDetailsTable"></table>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/customerInv/customerInvBalances.js"></script>
	<script type="text/javascript">
	  function getType(type){
		  var data = new Object();
			<c:forEach items="${dict['MERCH_INV_LOG_TYPE']}" var="type">
			data["${type.chooseVal}"] = "${type.showText}"
			</c:forEach>
			if (data[type]) {
				return data[type];
			} else {
				return "未知";
			}
	  }
	  
	  function getSource(type){
		  var data = new Object();
			<c:forEach items="${dict['MERCH_INV_LOG_SOURCE']}" var="source">
			data["${source.chooseVal}"] = "${source.showText}"
			</c:forEach>
			if (data[type]) {
				return data[type];
			} else {
				return "未知";
			}
	  }
	</script>
</body>
</html>

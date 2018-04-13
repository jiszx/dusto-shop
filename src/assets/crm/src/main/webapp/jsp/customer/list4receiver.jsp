<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>管理系统-送达方管理</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
<style type="text/css">
.selectWidth {
	width: 70% !important;
	margin: 10px 0;
}

.hide {
	display: none;
}

.spred {
	margin: 20px 0;
}
</style>
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			送达方管理 <small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 送达方管理</a></li>
			<li class="active">送达方列表</li>
		</ol>
	</section>
	<div class="col-md-12" style="padding: 0">
		<!--
    <div class="spred col-md-12 col-sm-12">
    	<input value="查询条件" type="button" class="btn-btn-success">
    </div>
    -->
		<form class="form-inline" id="searchForm">
			<%-- <div class="form-group col-md-3 col-sm-6">
				<label for="exampleInputEmail3">销售组织</label> <select
					class="form-control selectWidth" name="salesOrg"
					id="salesOrgSelect">
					<option value="">全部</option>
					<c:forEach items="${org }" var="salesOrg">
						<option value="${salesOrg.id }">${salesOrg.name }</option>
					</c:forEach>
				</select>
			</div> --%>
			<!-- <div class="form-group col-md-3 col-sm-6">
				<label for="exampleInputEmail3">销售大区</label> <select
					class="form-control selectWidth" name="salesArea"
					id="salesAreaSelect">
					<option value="">全部</option>
				</select>
			</div> -->
			<!-- <div class="form-group col-md-3 col-sm-6">
				<label for="exampleInputEmail3">业务省市</label> <select
					class="form-control selectWidth" name="bizProvId"
					id="salesProvSelect">
					<option value="">全部</option>
				</select>
			</div> -->
			<%-- <div class="form-group col-md-3 col-sm-6" >
				<label for="exampleInputEmail3">岗位名称</label> <select
					class="form-control selectWidth" name="position"
					id="positionSelect">
					<option value="" flag="main">全部</option>
					<c:forEach items="${stations }" var="station">
						<option value="${station.stationid }" style="display: none;"
							flag="${station.stationorgid }">${station.stationname }</option>
					</c:forEach>
				</select>
			</div> --%>
			<div class="form-group col-md-3 col-sm-6 mustShow">
				<label for="exampleInputEmail3">送达方名称</label> <input type="text"
					class="form-control selectWidth" name="customerName"
					placeheader="请输入送达方名称">
			</div>
			<div class="form-group col-md-3 col-sm-6">
				<label for="exampleInputEmail3">SAP客户编码</label> <input type="text"
					class="form-control selectWidth" name="sapCustomerId"
					placeheader="请输入SAP客户编码">
			</div>
			<div class="form-group col-md-3 col-sm-6" style="margin-top: 10px;">
				<label for="exampleInputEmail3">售达方</label> <select 
					class="form-control selectWidth" name="pid" id="pidSelect"></select>
			</div>
			<c:set var="customerStates"
				value="<%=com.hhnz.customer.enu.CustomerBaseStateEnu.values()%>" />
			<div class="form-group col-md-3 col-sm-6">
				<label for="exampleInputEmail3">送达方状态</label> <select
					class="form-control selectWidth" name="status"
					id="search_cust_status">
					<option value="">全部</option>
					<c:forEach var="state" items="${customerStates}">
						<option value="${state.getCode()}" en="${state.toString() }">${state.getDesc()}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group col-md-3 com-sm-6 mustShow" style="margin:10px 0;">
				<!-- Split button -->
				<div class="btn-group">
					<button type="button" class="btn btn-success"  id="searchBtn"><i class="icon icon-search">&nbsp;&nbsp查询</i></button>
					<button type="button" class="btn btn-success dropdown-toggle"
						data-toggle="dropdown">
						<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#" id="moreSearch">筛选</a></li>
					</ul>
				</div>

			</div>
	</form>
	</div>
	<div class="col-md-12" id="customerTool" style="padding: 0">

		<div class="col-md-12" style="padding: 0">
			<div class="btn-group btn-group-sm" role="group" aria-label="...">
				<a type="button" class="btn btn-primary" href="customer/receiver/index.html"><i
					class='icon icon-plus'></i> 添加</a> <a type="button"
					class="btn btn-warning" id="editBtn"><i class='icon icon-edit'></i>
					修改</a>
				<a type="button" class="btn btn-danger" id="changeBtn"><i class='icon icon-edit'></i>变更</a>
				<button type="button" id="detailBtn" class="btn btn-info">
					<i class='icon icon-eye-open'></i> 详情
				</button>
				<button type="button" id="applyBtn" class="btn btn-bitbucket">
					<i class='icon approval_img'></i> 提交审批
				</button>
				<button type="button" class="btn btn-del btn-danger">
					<i class='icon icon-remove'></i> 删除
				</button>
			</div>
		</div>

	</div>
	<div class="col-md-12">
		<table id="customerTable"></table>
	</div>

	<div class="modal fade" id="choosePosition">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">选择岗位</h4>
				</div>
				<div class="modal-body">
					<table id="positionTable"></table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="choosePosiBtn">选择</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="static/js/customer/list4receiver.js"></script>
	<script type="text/javascript">
		/* $(document).ready(function(){
			$(".spred").click(function(){
				$("#searchForm").removeClass("hide")
				$(".spred").hide();
			})
			$(".close").click(function(){
				$("#searchForm").addClass("hide")
				$(".spred").show();
			})
		}); */
		$(function() {
			$("#searchForm .form-group").hide();
			$("#searchForm .mustShow").show();
			$("#moreSearch").bind("click",function(){
				if($(this).html()=="筛选"){
					$(this).html("收起")
					$("#searchForm .form-group").show();
				}else{
					$("#searchForm .form-group").hide();
					$("#searchForm .mustShow").show();
					$(this).html("筛选")
				}
			})
		})
	</script>
</body>
</html>

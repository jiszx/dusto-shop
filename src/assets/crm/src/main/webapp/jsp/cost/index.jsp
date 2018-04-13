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
<title>管理系统-费用管理</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link href="static//bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="static/css/search-bar.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.form-control{
	padding:0;}
	.input-box-list-value {
    display: table-cell;
    width: 60%;
}
.modal-body{
	word-break: break-all;
}
</style>
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			预算管理<small>录入预算</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 费用管理</a></li>
			<li class="active">预算管理</li>
		</ol>
	</section>
	<div class="form-horizontal row" style="padding: 0 0 20px 0;border-bottom:1px solid hsla(255,0%,90%,1); ">
	    <div class="form-group col-sm-6 sol-md-3 input-box-list">
	        <label class=" font12 input-box-list-title">销售组织：</label>
	        <div class="input-box-list-value">
	            <select id="selectorg" class="form-control no-appearance" onchange="changeRegin(this.value)">
	            	<option value="">全部</option>
	            	<c:forEach items="${org}" var="orgs">
						<c:if test="${orgs.levels==2}">
							<option value="${orgs.id}">${orgs.name}</option>
						</c:if>
					</c:forEach>
	            </select> <!-- <i class="icon icon-caret-down" style="float: right; margin: -25px 10px 0 0;"></i> -->
	        </div>
	    </div>
	    <div class="form-group input-box-list col-sm-6 sol-md-2">
	        <label class=" font12 input-box-list-title">大区：</label>
	        <div class="input-box-list-value">
	            <select id="selectregin" class="form-control no-appearance" onchange="changeProv(this.value)">
	            	<option value="">全部</option>
	            </select> <!-- <i class="icon icon-caret-down" style="float: right; margin: -25px 10px 0 0;"></i> -->
	        </div>
	    </div>
	    <div class="form-group input-box-list col-sm-6 sol-md-2">
	        <label class=" font12 input-box-list-title">省区：</label>
	        <div class="input-box-list-value">
	            <select id="selectprov" class="form-control no-appearance">
	            	<option value="">全部</option>
	            </select> <!-- <i class="icon icon-caret-down" style="float: right; margin: -25px 10px 0 0;"></i> -->
	        </div>
	    </div>
	    <div class="form-group  input-box-list col-sm-6 sol-md-2">
	        <label class=" font12 input-box-list-title">时间：</label>
	        <div class="input-box-list-value">
	        	<input type="text" class="form-control" id="selectperiod" placeholder="时间">
	        </div>
	    </div>
	     <div class="form-group  input-box-list col-sm-6 sol-md-2">
	        <label class=" font12 input-box-list-title">客户：</label>
	        <div class="input-box-list-value">
	        	<input id="selectcust" name="sku" type="text" class="form-control" placeholder="请输入名称">
	        </div>
	    </div>
	</div>
	<div class="form-group col-md-2 col-sm-2 col-md-offset-10 input-box-list">
	        <button id="searchButton" class="btn btn-primary"><i class="icon icon-search"></i>&nbsp;&nbsp;查询</button>
	    </div>
	<div class="col-md-12" id="dictTool">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn btn-info" data-toggle="modal"
				data-target="#addDictModal">
				<i class='icon importe_img'></i> 预算导入
			</button>
		</div>
	</div>
	<div class="col-md-12">
		<table id="costTable"></table>
	</div>

	<!-- addDialog-->
	<div class="modal fade" id="addDictModal" tabindex="-1" role="dialog"
		aria-labelledby="dictLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">预算导入</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post" enctype="multipart/form-data"
						action="budget/import.json" id="importForm">
						<div class="form-group">
							<label for="colName" class="col-sm-2 control-label">文件</label>
							<div class="col-sm-6">
								<input type="file" id="lefile"class="form-control hide" id="colName"
									name="colName">
									<div class="input-append">
									<input type="file" class="form-control" id="uploadFile2" name="file"  >
								</div>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="importButton" class="btn btn-primary btn-save"
						form="importForm">保存</button>
				</div>
			</div>
		</div>
	</div>

	
	<script type="text/javascript">
    
    function getCostType(type) {
		var data = new Object();
		<c:forEach items="${dict['COST_TYPE']}" var="payType">
		data["${payType.chooseVal}"] = "${payType.showText}"
		</c:forEach>
		if (data[type]) {
			return data[type];
		} else {
			return "未知";
		}
	}
</script>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="static/js/cost/index.js"></script>
</body>
</html>

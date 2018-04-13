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
<title>管理系统-费用池管理</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link href="static/css/search-bar.css" rel="stylesheet" type="text/css" />
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			费用池管理<small> </small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 费用管理</a></li>
			<li class="active">费用池管理</li>
		</ol>
	</section>
	<div class="form-horizontal row" style="padding: 0 0 20px 0;border-bottom:1px solid hsla(255,0%,90%,1); ">
	    <div class="form-group input-box-list col-sm-6 col-md-3">
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
	    <div class="form-group input-box-list col-sm-6 col-md-2">
	        <label class=" font12 input-box-list-title">大区：</label>
	        <div class="input-box-list-value">
	            <select id="selectregin" class="form-control no-appearance" >
	            	<option value="">全部</option>
	            </select> <!-- <i class="icon icon-caret-down" style="float: right; margin: -25px 10px 0 0;"></i> -->
	        </div>
	    </div>
	    <div class="form-group input-box-list col-sm-6 col-md-2">
	        <label class=" font12 input-box-list-title">费用类型：</label>
	        <div class="input-box-list-value">
	            <select id="selectcost" class="form-control no-appearance">
	            	<option value="">全部</option>
	            	<c:forEach items="${dict['COST_TYPE']}" var="item">
	                    <option value="${item.chooseVal}">${item.showText}</option>
	                </c:forEach>
	            </select>
	        </div>
	    </div>
	    <div class="form-group col-sm-6 col-md-2 input-box-list">
	        <button id="searchButton" class="btn btn-primary"><i class="icon icon-search"></i>&nbsp;&nbsp;查询</button>
	    </div>
	</div>
	<!-- search bar end -->
	<div class="col-md-12" id="upAccountTool" style="padding-left:0;">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn btn-primary" id="btn-update">
				<i class='icon icon-plus'></i>更新记录
			</button>
		</div>
	</div>
	<div class="col-md-12">
		<table id="costTable"></table>
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
	<script type="text/javascript" src="static/js/cost/pool.js"></script>
</body>
</html>

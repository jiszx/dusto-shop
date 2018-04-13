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
<title>管理系统-促销品管理</title>
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
	<section class="content-header">
		<h1>
			促销品库存查询<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 促销品管理</a></li>
			<li class="active">促销品库存查询</li>
		</ol>
	</section>
	<div class="col-md-12 col-sm-12" style="padding: 0;">
		<div class="form-group col-md-3 col-sm-6 ">
			<label>促销品名称：</label> <input type="text"
				class="form-control customizeWidth" id="searchname"
				placeholder="促销品名称">
		</div>
		<div class="form-group col-md-3 col-sm-6">
			<label>销售组织：</label> 
			<select class="form-control customizeWidth"	id="searchorgid" >
				<option></option>
					<c:forEach items="${org}" var="orgs">
						<option value="${orgs.id}">${orgs.name}</option>
					</c:forEach>
			</select>
		</div>
		<div class="form-group col-md-3 col-sm-6">
			<label>库房名称：</label>
			<select	class="form-control customizeWidth" id="searchstore"></select>
		</div>
		<div class="form-group  col-md-3 col-sm-6 ">
			<button type="button" class="btn btn-primary" id="btn-search">
				<i class="icon icon-search"></i>&nbsp;&nbsp;开始搜索
			</button>
		</div>
	</div>
	<div class="col-md-12" id="promotionListTool" style="padding:0;">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#addUpaccountModal">
				<i class='icon icon-share-alt'></i>出库
			</button>
			<button type="button" class="btn btn-edit btn-info">
				<i class='icon  icon-circle-arrow-down'></i>入库
			</button>
		</div>
	</div>
	<div class="col-md-12">
		<table id="promotionListTable"></table>
	</div>
	
	<script type="text/javascript">
		function getUpStatesValue(state) {
			var data = new Object();
			<c:forEach items="${dict['MERCH_ACCOUNT_UP_STATES']}" var="payType">
			data["${payType.chooseVal}"] = "${payType.showText}"
			</c:forEach>
			if (data[state]) {
				return data[state];
			} else {
				return "未知";
			}

		}
		function uptype() {
			<c:forEach items="${dict['MERCH_UPAMT_TYPE']}" var="payType">
			$('#payType')
					.append(
							"<option value='${payType.chooseVal}'>${payType.showText}</option>");
			</c:forEach>

		}
	</script>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/promotion/promotionNum.js"></script>
</body>
</html>

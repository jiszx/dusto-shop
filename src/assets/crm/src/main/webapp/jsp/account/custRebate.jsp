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
<title>管理系统-客户合同返利</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			客户合同返利<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">客户资金管理</a></li>
			<li class="active">客户合同返利</li>
		</ol>
	</section>
	<div class="row" style="padding: 0 0 20px 0;">
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title" style="float:left;line-height:30px;width:auto !important">客户名称：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" 
					   placeholder="输入客户名称" style="padding:0;width:70%;" id="custname">
			</div>
		</div>
		<!-- <div class="form-group col-md-3 input-box-list">
			<label for="input2" class=" font12 input-box-list-title" style="float:left;line-height:30px;width:auto !important">来款账号：</label>
			<div class="input-box-list-value">
				<select class="form-control"  style="padding:0;width:70%;" id="spayBankNo">
			   </select>
			</div>
		</div>
		<div class="form-group col-md-3 input-box-list">
			<label for="input3" class=" font12 input-box-list-title" style="float:left;line-height:30px;width:auto !important">来款城市：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" 
					   placeholder="输入来款城市名称" style="padding:0;width:70%;" id="spayCity">
			</div>
		</div> -->
		
		<div class="form-group col-md-3  col-sm-6 input-box-list">
			<button class="btn btn-primary" id="btn-search"><i class="icon icon-search"></i>&nbsp;&nbsp;开始搜索</button>
		</div>
	</div>
	<div class="col-md-12" id="CustRebateTool" style="padding:0;">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn btn-info" id="rebateDetails">
				<i class='icon icon-eye-open'></i>查询详细订单
			</button>
			<button id="exportBtn" class="btn btn-warning" ><i class='icon icon-share'></i>导出</button>
		</div>
	</div>
	<div class="col-md-12">
		<table id="custRebateTable"></table>
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
	<script type="text/javascript">
	function getrebateTypeValue(state) {
		var data = new Object();
		<c:forEach items="${dict['AGENT_TYPE']}" var="payType">
		data["${payType.chooseVal}"] = "${payType.showText}"
		</c:forEach>
		if (data[state]) {
			return data[state];
		} else {
			return "未知";
		}

	}
	</script>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/account/custRebate.js"></script>
</body>
</html>

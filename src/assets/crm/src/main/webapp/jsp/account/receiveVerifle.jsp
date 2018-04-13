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
<title>管理系统-收款核销</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			收款核销<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">客户资金管理</a></li>
			<li class="active">收款核销</li>
		</ol>
	</section>
	<div class="row" style="padding: 0 0 20px 0;">
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: auto !important">客户名称：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入客户名称"
					style="padding: 0; width: 70%;" id="custname">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: auto !important">发票编号：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入客户名称"
					style="padding: 0; width: 70%;" id="invoiceNo">
			</div>
		</div>
		<%-- <div class="form-group col-md-3 col-sm-6 input-box-list">
			<label class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: auto !important">销售组织：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance"
					style="padding: 0; width: 70%;" id="sorgid">
					<option></option>
					<c:forEach items="${org}" var="orgs">
						 <option value="${orgs.id}">${orgs.name}</option>
					</c:forEach>
				</select>
			</div>
		</div> --%>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: auto !important">发票状态：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance"
					style="padding: 0; width: 70%;" id="type">
					<c:forEach items="${dict['INVOICE_VERIFIE_TYPE']}" var="it">
						<option value="${it.chooseVal}">${it.showText}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<!-- <div class="form-group col-md-3 col-sm-6 input-box-list">
			<label class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: auto !important">现金类型：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance"
					style="padding: 0; width: 70%;" id="spayType">
					<option></option>
				</select>
			</div>
		</div> -->
		<div class="form-group col-sm-6 col-md-3  input-box-list">
			<button class="btn btn-primary" id="btn-search">
				<i class="icon icon-search"></i>&nbsp;&nbsp;开始搜索
			</button>
		</div>
	</div>
	<div class="col-md-12" id="invoiceTool" style="padding:0;">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn btn-primary" id="btn-verifie">
				<i class='icon icon-share'></i>核销
			</button>
			<button type="button" class="btn btn-info hide"  id="verifie-details">
				<i class='icon icon-share'></i>核销明细
			</button>
			<button type="button" class="btn btn-success "  id="btn-invoice">
				<i class='icon icon-share'></i>应收发票明细
			</button>
			<button type="button" class="btn btn-warning hide" id="btn-write-off">
				<i class='icon icon-share'></i>取消核销
			</button>
			<!-- <button type="button" class="btn btn-default " data-toggle="modal" id="verifieAll"
				data-target="#verifieAllModal">
				<i class='icon icon-share'></i>全部核销
			</button> -->
		</div>
	</div>
	<div class="col-md-12">
		<table id="invoiceTable"></table>
	</div>
	 <div class="modal fade" id="chooseUpaccountModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<!-- <button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button> -->
					<h4 class="modal-title">选择资金</h4>
				</div>
				<div class="modal-body">
					<table id="upaccounts"></table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="chooseUpaccount">选择</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	 <div class="modal fade" id="invoiceDetailModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<!-- <button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button> -->
					<h4 class="modal-title">应收发票明显</h4>
				</div>
				<div class="modal-body">
					<table id="invoiceItemTable"></table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<div class="modal fade" id="verifieDetailModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<!-- <button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button> -->
					<h4 class="modal-title">核销明显</h4>
				</div>
				<div class="modal-body">
					<table id="verifieTable"></table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	<div class="modal fade" id="verifieAllModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<!-- <button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button> -->
					<h4 class="modal-title">选择销售组织</h4>
				</div>
				<div class="modal-body">
					<select class="form-control no-appearance"
					style="padding: 0; width: 70%;" id="p_orgid">
					
					<c:forEach items="${org}" var="orgs">
					     <c:if test="${orgs.levels==2}">
						 <option value="${orgs.id}">${orgs.name}</option>
					     </c:if>
					</c:forEach>
				</select>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="btn-verifieAll">选择</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<script type="text/javascript">
		function getcustTypeValue(type){
		//CUST_MERCH_TYPE
			var data = new Object();
			<c:forEach items="${dict['CUST_MERCH_TYPE']}" var="Type">
			data["${Type.chooseVal}"] = "${Type.showText}"
			</c:forEach>
			if (data[type]) {
				return data[type];
			} else {
				return "未知";
			}
		}
		function getVerifieType(type) {
			var data = new Object();
			<c:forEach items="${dict['RECEIVE_VERIFIE_TYPE']}" var="a">
			data["${a.chooseVal}"] = "${a.showText}"
			</c:forEach>
			if (data[type]) {
				return data[type];
			} else {
				return "";
			}
		}
	</script>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/account/receiveVerifle.js"></script>
</body>
</html>

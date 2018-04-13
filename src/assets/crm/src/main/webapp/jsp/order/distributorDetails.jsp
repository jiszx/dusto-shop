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
<title>管理系统-配送商订单详情</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link href="static/bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<style type="text/css">
.font-color {
	color: #ccc;
}

#logisticstails th {
	text-align: center !important;
}

#logisticstails th, #logisticstails td {
	border: 1px solid #ccc;
}

#orderdetails th, #orderdetails td {
	border: 1px solid #ccc;
}

.customizeWidth {
	width: 70%;
	display: inline-block;
}
.form-horizontal .input-box-list {
	display: table;
	margin: 10px 0 0 0;
	padding-left: 10px;
}

.input-box-list-title, .input-box-list-value {
	display: table-cell;
}

.input-box-list-title {
	width: 103px;
	text-align: right;
	vertical-align: middle;
	padding-right: 5px;
}

.input-box-list-value textarea {
	resize: none;
}

input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
	font-size: 12px;
}

.btn-long {
	padding: 8px 6.18%;
}

.block-save-link {
	float: right;
	font-size: 13px;
	padding: 4px 10px 0;
}

.block-save-link:hover {
	text-decoration: underline;
}

</style>
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			订单管理 <small>配送商订单详情</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li class="active">订单管理</li>
		</ol>
	</section>
	<form class="col-md-12" style="min-height: 200px; padding: 10px 0;">
		<div class="page-header" id="block-1">
			<h4 class="text-info">
				<strong>1.&nbsp;</strong>头信息&nbsp;-&nbsp;<small>Basic
					Information.</small>
			</h4>
		</div>
		<div class="form-horizontal row" style="margin: 0 0 20px 0;">
			<div class="form-group col-md-4 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">配送商名称</label>
				<div class="input-box-list-value">
					<input type="text" class="form-control" value="${order.merchname}"
						readonly>
				</div>
			</div>
			<input type="hidden" value="${order.merchid}" id="merchid"> 
			<input type="hidden" value="${order.shipid}" id="shipid">
			<input type="hidden" value="${order.states}" id="states">
			<input type="hidden" value="${order.isMatched}" id="isMatched">
			<div class="form-group col-md-4 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">批次号</label>
				<div class="input-box-list-value">
					<input type="text" class="form-control" id="lpno" value="${order.lpno}"
						readonly>
				</div>
			</div>
			<div class="form-group col-md-4 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">总金额</label>
				<div class="input-box-list-value">
					<input type="text" class="form-control" placeholder="输入经销区域..."
						value="${order.amt*100}" readonly="readonly">
				</div>
			</div>
			<div class="form-group col-md-4 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">送达方：</label>
				<div class="input-box-list-value">
					<input type="text" class="form-control" value="${order.shipname}"
						readonly="readonly">
				</div>
			</div>
		</div>

		<div class="page-header" id="block-2">
			<h4 class="text-info">
				<strong>1.&nbsp;</strong>终端订单信息&nbsp;-&nbsp;<small>Basic
					Information.</small>
			</h4>
		</div>
		<div class="form-horizontal row" style="margin: 0 0 20px 0;">
			<table id="orderTable">
			</table>
		</div>
		<input type="hidden" id="taskId" value="${taskId }">
	</form>
	<c:if test="${audit==1 }">
		<div class="text-center" style="padding-top: 30px; border-top: 1px solid rgba(0, 0, 0, .15);">
        	<button class="btn btn-warning"  id="makesure" batch="${order.lpno }" style="padding: 8px 25px;"
                type="button">
            <i class="icon icon-save"></i>&nbsp;&nbsp;确认发货
        	</button>
        	<button class="btn btn-warning" id="reject"  style="padding: 8px 25px;"
                type="button">
            <i class="icon icon-save"></i>&nbsp;&nbsp;驳回订单
        	</button>
    	</div>
	</c:if>
	<c:if test="${audit!=1 }">
		<div class="text-center" style="padding-top: 30px; border-top: 1px solid rgba(0, 0, 0, .15);">
        	<button class="btn btn-warning" id="btn-back" form="addPaperForm" style="padding: 8px 25px;"
                type="button">
            <i class="icon icon-save"></i>&nbsp;&nbsp;返回
        	</button>
    	</div>
	</c:if>
	
	<script type="text/javascript">
		function getorderType(value) {
			var data = new Object();
			<c:forEach items="${dict['OM_ORDER_HEADER_TYPE']}" var="type">
			data["${type.chooseVal}"] = "${type.showText}"
			</c:forEach>
			if (data[value]) {
				return data[value];
			} else {
				return "未知";
			}
		}
	</script>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript"
		src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript"
		src="static/bootstrap/js/bootstrap-datetimepicker.min.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="static/js/order/distributorDetails.js"></script>
</body>
</html>

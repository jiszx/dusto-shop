<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>管理系统-销售政策管理</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <style type="text/css">
    	.btn-submit{
    	background-color:#004AA6 !important;
    	border-color:#004AA6 !important;}
    </style>
</head>
<body class="container-fluid content">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>销售政策管理<small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 销售政策</a></li>
        <li class="active">销售政策管理</li>
    </ol>
</section>
<div class="col-md-12" id="policyTool" style="padding:0;">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
        <a type="button"  href="salePolicy/addPolicy.html" class="btn btn-info"><i class='icon icon-plus'></i> 销售政策申请</a>
        <button type="button" class="btn  btn-primary" id="btn-details" ><i class='icon icon-eye-open'></i> 查看详情</button>
        <button type="button" id="btn-write-off" class="btn btn-success " ><i></i> 核销明细</button>
        <a type="button" class="btn  btn-warning "  id="btn-edit"><i class='icon icon-edit'></i>修改</a>
        <button type="button" class="btn  btn-danger " id="btn-del"   ><i class='icon icon-remove'></i>删除</button>
        <button type="button" class="btn  btn-success " id="btn-audit" style="background-color:#0033A6 !important;border:##0033A6 !important"><i class='icon approval_img'></i>提交审批</button>
    </div>
</div>
<div class="col-md-12">
    <table id="salepolicyTable"></table>
</div>
<script type="text/javascript">
function getcostType(type) {
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
function getstates(type){
	var data = new Object();
	<c:forEach items="${dict['POLICY_STATES']}" var="payType">
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
<script type="text/javascript" src="static/table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="static/js/salepolicy/index.js"></script>
</body>
</html>

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
    <title>配置与监控-任务计划</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<section class="content-header">
 <h1>配置与监控<small>任务计划</small>
 </h1>
 <ol class="breadcrumb">
     <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
     <li><a href="#">配置与监控</a></li>
     <li class="active">任务计划</li>
 </ol>
</section>


<!-- Main content -->
<div class="col-md-12" id="dictTool" style="padding:0;">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
    	<button type="button" id="logBtn" class="btn btn-search btn-warning"><i class='icon icon-eye-open'></i>历史数据</button>
    	<button type="button" id="manualBtn" class="btn btn-search btn-primary"><i class='icon icon-eye-close'></i>手动执行</button>
    </div>
</div>
<div class="col-md-12">
    <table id="dictTable"></table>
</div>

<!-- task log -->
<div class="modal fade" id="logModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">任务历史</h4>
            </div>
            <div class="modal-body">
                <table id="taskLogTable"></table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
function getStates(type) {
	var data = new Object();
	<c:forEach items="${dict['CRM_TASK_MAIN_STATES']}" var="state">
	data["${state.chooseVal}"] = "${state.showText}"
	</c:forEach>
	if (data[type]) {
		return data[type];
	} else {
		return "未知";
	}
}
function getLogStates(type) {
	var data = new Object();
	<c:forEach items="${dict['CRM_TASK_LOG_STATES']}" var="state">
	data["${state.chooseVal}"] = "${state.showText}"
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
<script type="text/javascript" src="static/js/monitor/task.js"></script>
</body>
</html>

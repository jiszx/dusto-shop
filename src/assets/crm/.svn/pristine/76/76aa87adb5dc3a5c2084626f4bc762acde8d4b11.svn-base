<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>管理系统-查看详情</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			工作详情<small>处理工作</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-dashboard"></i> 首页</a></li>
			<li><a href="#"> 我的工作台</a></li>
			<li><a href="process/forMe.html"> 等待处理</a></li>
			<li class="active">工作详情</li>
		</ol>
	</section>
	<div class="col-md-12">
		<iframe id="lookViewFrame"
			style="width: 100%; height: auto; min-height: 300px; border: none;"
			src="${viewPage}"></iframe>
		<%--<jsp:include page="/${viewPage}"></jsp:include>--%>
	</div>
	<form class="col-md-12 form-horizontal" action="process/commit"
		method="post" id="processForm">
		<div class="form-group">
			<div class="col-md-12">
				<ul class="list-unstyled">
					<c:forEach items="${comments}" var="com">
						<li><span class="label label-primary">处理人</span>
							${com.userId}</li>
						<li><span class="label label-warning">时 间</span> <fmt:formatDate
								value="${com.time}" type="both" pattern="yyyy-MM-dd:HH:mm:ss" /></li>
						<li><span class="label label-info">意 见</span>
							${com.fullMessage}</li>
						<li class="divider"
							style="border: 1px solid #ccc; margin-top: 5px;"></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="form-group">
			<label for="comment" class="col-sm-2 control-label">意见</label>
			<div class="col-sm-6">
				<input type="hidden" id="taskId" name="taskId" value="${taskID}">
				<textarea class="form-control" name="comment" id="comment"></textarea>
			</div>
		</div>
		<div class="text-center"
			style="padding-top: 30px; border-top: 1px solid rgba(0, 0, 0, .15);">
			<button class="btn btn-primary btn-long btn-process" type="submit" id="btn-pass">
				<i class="icon icon-check"></i>&nbsp;&nbsp;${btn-pass != null?"审批通过":btn-pass}
			</button>
			<button class="btn btn-danger btn-long" type="button" id="btn-back">
				<i class="icon icon-remove"></i>&nbsp;&nbsp;${btn-rollback != null?"审批驳回":btn-pass}
			</button>
		</div>
	</form>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<script type="text/javascript">
		$(function() {
			$("#processForm").ajaxForm({
				target : '.btn-process', // target element(s) to be updated with server response
				clearForm : true,
				dataType : 'json',
				beforeSubmit : function(formData, jqForm, options) {
					$("#btn-pass").attr("disabled","true");
					$("#btn-back").attr("disabled","true");
				},
				success : function(responseText, statusText, xhr, $form) {
					$("#btn-pass").attr("disabled","");
					if (responseText.errorCode != 0) {
						$.messager.alert("错误", responseText.errorMessage);
					} else {
						history.back();
					}
				}
			});
		});
		$("#btn-back").bind("click", function() {
			$("#btn-pass").attr("disabled","true");
			$("#btn-back").attr("disabled","true");
			$.post("process/rollBack", {
				"taskId" : $("#taskId").val(),
				"comment" : $("#comment").val()
			}, function(data) {
				$("#btn-back").attr("disabled","");
				if (data.errorCode != 0) {
					$.messager.alert("错误", data.errorMessage);
				} else {
					history.back();
				}
			});
		});
		
		$("#lookViewFrame").load(function(){
		    var mainheight = $(this).contents().find("body").height()+50;
		    $(this).height(mainheight);
		});
	</script>
</body>
</html>

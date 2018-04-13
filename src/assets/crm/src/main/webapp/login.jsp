<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<base href="<%=basePath%>">
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="static/js/canvasbg.js"></script>
<title>益盐堂销售管理系统-登录</title>
<style type="text/css">
	#navigator{
	padding-left:0 !important;
	padding-right:0 !important;
	}
</style>
</head>
<body class="bg-black" class="container-fluid">
	<!--[if IE 7]><div id="navigator" class="alert alert-danger" style="display: block;padding-left:0 !important;padding=right:0 !important;margin-left:0 !important">为了更好的体验，请你使用IE9以上版本或者非IE内核版本的浏览器</div><![endif]--> 
	<!--[if IE 8]><div id="navigator" class="alert alert-danger" style="display: block;padding-left:0 !important;padding=right:0 !important;margin-left:0 !important">为了更好的体验，请你使用IE9以上版本或者非IE内核版本的浏览器</div><![endif]-->
	<div class="form-box" id="login-box"
		style="position: absolute; left: 40%; top: 20px;">
		<div class="header">
			益盐堂销售管理系统 <%--<i class="icon icon-qrcode" data-toggle="modal" data-target="#download"></i>--%>
		</div>
		<form action="j_spring_security_check" method="post">
			<div class="body bg-gray">
				<c:if test="${ERROR !=null}">
					<div class="form-group alert alert-danger"
						style="margin-left: 0px;">${ERROR}</div>
				</c:if>
				<div class="form-group">
					<input type="text" name="j_username" class="form-control"
						placeholder="登录名/手机号/邮箱地址" />
				</div>
				<div class="form-group">
					<input type="password" name="j_password" class="form-control"
						placeholder="请输入您的密码" />
				</div>
			</div>
			<div class="footer">
				<button type="submit" class="btn bg-olive btn-block">进入系统</button>
			</div>
		</form>
	</div>
	<div id="canvas-wrapper">
		<canvas id="bg-canvas"></canvas>
	</div>
	<div class="modal fade " id="download">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-body">
					<img src="static/images/code.png" class="" />
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			CanvasBG.init({
				Loc : {
					x : window.innerWidth / 2,
					y : window.innerHeight / 3.3
				}
			});
		});
		if (window.parent != window) {
			window.parent.location.href = window.href;
		}
	</script>
</body>
</html>

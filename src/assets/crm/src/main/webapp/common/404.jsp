<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>管理系统</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body style="background-color:#fff;">
<div class="container">
	<div class="col-md-12">
		<div class="col-md-3 text-right">
			<h1 class="text-danger" style="font-size: 64px;">404</h1>
		</div>
		<div class="col-md-9" style="padding:20px;font-size:24px">
			<p class="col-md-12"><strong>这地方一般人找不到<br>程序猿说这地方没有啥，请回吧</strong></p>
		</div>
	</div>
    <div clas="col-md-12">
    	<img src="static/images/404.png" class="img-responsive" style="margin: 0px auto;">
    </div>
    <div class="col-md-12 text-center">
    	<a class="btn btn-info" href="javascript:history.back();">原路返回</a>
    </div>
</div><!-- ./wrapper -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
</body>
</html>

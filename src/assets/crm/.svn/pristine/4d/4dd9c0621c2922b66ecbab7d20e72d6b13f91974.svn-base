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
    <script type="text/javascript">
    if(window.parent.name == "view_mainFrame"){
    	window.parent.location.href=window.location.href
    }
    </script>
</head>
<body style="background-color:#fff;">
<div class="container">
	<div class="col-md-12">
		<div class="col-md-3 text-right">
			<h1 class="text-danger" style="font-size: 64px;">500</h1>
		</div>
		<div class="col-md-9" style="padding:20px;font-size:24px">
			<p class="col-md-12"><strong>哎呀！这里出错了<br>程序猿BOSS喊你回来加班</strong></p>
		</div>
	</div>
    <div clas="col-md-12">
    	<img src="static/images/404.png" class="img-responsive" style="margin: 0px auto;">
    </div>
    <div clas="col-md-12 text-center">
    	<a class="btn btn-info" href="javascript:history.back();">我要回去</a>
    </div>
</div><!-- ./wrapper -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
</body>
</html>

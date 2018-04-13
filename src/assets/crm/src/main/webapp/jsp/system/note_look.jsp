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
    <title>管理系统-公告信息</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body>
<div class="container-fluid">
    <h2 class="col-md-12 text-center">${bean.title}</h2>
    <div class="col-md-12">
        <small class="pull-right">${bean.creator}-${bean.releaseTs}${attachmentBASEURI}sdfsdf</small>
    </div>
    <small class="col-md-12">${bean.descTxt}</small>
    <hr class="col-md-12">
    <div class="col-md-12">${bean.content}</div>
    <hr class="col-md-12">
    <div class="col-md-12">
        <c:forEach items="${attas}" var="att">
            <ul><a href="${attachmentBASEURI}${att.objectName}${att.fileName}"> ${att.attachmentName}</a></ul>
        </c:forEach>
    </div>
</div><!-- ./wrapper -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
</body>
</html>

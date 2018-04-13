<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>公告详情</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body>
<div class="container-fluid">
    <h2 class="col-md-12 text-center">${note.title}</h2>
    <div class="col-md-12">
        <small class="pull-right">${note.creator}&nbsp&nbsp${note.releaseTs}</small>
    </div>
    <div class="col-md-12">
        <small class="col-md-12" style="text-align:center;"><h4>${note.descTxt}</h4></small>
    </div>
    <hr class="col-md-12">
    <div class="col-md-12">${note.content}</div>
    <hr class="col-md-12">


	<!-- 
    <div class="col-md-12">
        <c:forEach items="${attas}" var="att">
            <p><a target="_blank" href="${applicationScope.attachmentBASEURI}${att.objectName}${att.fileName}">${att.attachmentName}</a></p>
        </c:forEach>
    </div>
     -->
</div>
<jsp:include page="/common/footjs.jsp"></jsp:include>
</body>
</html>

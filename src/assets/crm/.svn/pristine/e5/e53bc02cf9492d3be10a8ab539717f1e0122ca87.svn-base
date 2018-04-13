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
    <title>管理系统</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>公告添加
        <small>发布公告</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#">系统管理</a></li>
        <li><a href="system/notes/index.html">公告管理</a></li>
        <li class="active">修改公告</li>
    </ol>
</section>
<div class="col-md-12">
    <form action="system/notes/edit" method="post" enctype="multipart/form-data"
          class="form-horizontal" id="addNotesForm" role="form">
        <div class="form-group">
            <label for="title" class="col-sm-2 control-label">标题</label>
            <div class="col-sm-6">
                <input type="hidden" value="${bean.id}" name="id" id="editid">
                <input type="text" class="form-control" id="title" value="${bean.title}" name="title"
                       placeholder="公告标题">
            </div>
            <small class="help-block col-sm-4"></small>
        </div>
        <div class="form-group">
            <label for="title" class="col-sm-2 control-label">内容概要</label>
            <div class="col-sm-6">
                <textarea class="form-control" name="descTxt" placeholder="概要信息,不超过100个字">${bean.descTxt}</textarea>
            </div>
            <small class="help-block col-sm-4"></small>
        </div>
       <%-- <div class="form-group">
            <label class="col-sm-2 control-label">有效范围</label>
            <div class="col-sm-6">
                <input type="text" id="areaTree" class="form-control" name="area" value="${bean.area}">
            </div>
        </div>--%>
        <div class="form-group">
            <label class="col-sm-2 control-label">附件</label>
            <div class="col-sm-6">
                <input type="file" name="file" multiple="multiple" accept="image/jpeg,application/msword,application/vnd.ms-excel,application/pdf">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">是否置顶</label>
            <div class="col-sm-6">
                <label class="radio-inline">
                    <input type="radio" name="topFlag" value="1" checked="checked">是
                </label>
                <label class="radio-inline">
                    <input type="radio" name="topFlag" value="0">否
                </label>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-8 col-sm-offset-2">
                            <textarea id="noteContent" name="content" class="form-control"
                                      style="height: 300px;">${bean.content}</textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-10 col-sm-offset-2">
                <button type="reset" class="btn btn-default" data-dismiss="modal">重置</button>
                <button type="submit" class="btn btn-primary" id="btn-article-add">保存</button>
            </div>
        </div>
        <div class="form-group">
            <ul>
                <c:forEach items="${attas}" var="att">
                    <ul><a href="${applicationScope.attachmentBASEURI}${att.fileName}">${att.attachmentName} <span class="badge btn-delAtta" attr="${att.id}"><i class="icon icon-remove"></i></span></a></ul>
                </c:forEach>
            </ul>
        </div>
    </form>
</div>
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script src='static/editor/kindeditor-min.js' type="text/javascript"></script>
<%--<script type="text/javascript" src="static/easyui/easyloader.js"></script>--%>
<script src='static/editor/lang/zh_CN.js' type="text/javascript"></script>
<script src="static/js/system/editNotes.js" type="text/javascript"></script>
</body>
</html>

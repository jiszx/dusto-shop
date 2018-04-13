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
    <title>管理系统-公告管理</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <style type="text/css">
        .btn-appraval {
            background-color: #9CBC3C !important;
        }
    </style>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>公告管理
        <small>发布，审核公告</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 系统管理</a></li>
        <li class="active">公告管理</li>
    </ol>
</section>
<div class="col-md-12" id="dictTool">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
        <a class="btn btn-primary" href="system/notes/notesAdd.html"><i class='icon icon-plus'></i> 添加</a>
        <button type="button" id="btn-detail" class="btn btn-info"><i class='icon icon-eye-open'></i> 查看</button>
        <button type="button" id="btn-send" class="btn btn-success"><i class='icon icon-ok-sign'></i> 提交</button>
        <button type="button" id="btn-pass" class="btn btn-primary btn-appraval"><i class='icon approval_img'></i> 审批
        </button>
        <button type="button" class="btn btn-edit btn-warning"><i class='icon icon-edit'></i> 修改</button>
        <button type="button" class="btn btn-del btn-danger"><i class='icon icon-remove'></i> 删除</button>
    </div>
</div>
<div class="panel scroll panel-default col-md-12">
    <div class="panel-body" style="padding-left:0;min-height: 500px;">
        <div class="col-md-12">
            <table id="notesTable"></table>
        </div>
    </div>
</div>

<!-- editDialog-->
<div class="modal fade" id="showPassModal" tabindex="-1" aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="editdictLabel">审批公告</h4>
            </div>
            <div class="modal-body">
                审批公告:<strong id="Notetitle"></strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btn_back">驳回</button>
                <button type="button" id="btn_comit" class="btn btn-primary">通过</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript">
    function getDictValue(state) {
        var data = new Object();
        <c:forEach items="${dict['NOTES_STATE']}" var="states">
        data["${states.chooseVal}"] = "${states.showText}"
        </c:forEach>
        if (data[state]) {
            return data[state];
        } else {
            return "未知";
        }
    }
</script>
<script type="text/javascript" src="static/table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="static/js/system/notes.js"></script>
</body>
</html>

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
    <title>组织管理-职位人员设定</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>组织管理
        <small>职位人员设定</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 组织管理</a></li>
        <li class="active">职位人员设定</li>
    </ol>
</section>


<div class="col-sm-4 col-md-3" style="padding: 0px;">
    <div class="panel scroll panel-info org_info ">
        <div class="panel-heading">
            组织机构
        </div>
        <div class="panel-body" style="overflow: auto;">
            <ul id="tree" class="ztree" style="width: 100%;/* border:1px solid #ccc */"></ul>
        </div>
    </div>
</div>
<div class="col-sm-8 col-md-9" style="padding: 0px;">
    <div class="panel scroll panel-info right_table">
        <div class="panel-heading">职位信息</div>
        <div class="panel-body">
            <div class="col-md-12" id="dictTool" style="padding:0;">
                <div class="btn-group btn-group-sm" role="group" aria-label="...">
                    <button id="relationButton" type="button" class="btn btn-edit btn-warning"><i
                            class='icon icon-edit'></i>关联用户
                    </button>
                </div>
                <div class="btn-group btn-group-sm" role="group" aria-label="...">
                    <button id="deleteBtn" type="button" class="btn btn-del"><i class='icon icon-remove'></i>删除
                    </button>
                </div>
            </div>
            <table id="jobTable"></table>
        </div>

    </div>
</div>

<!-- relate model -->
<div class="modal fade" id="relateModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">选择用户</h4>
            </div>
            <div class="modal-body">
                <table id="empTable"></table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="relateUser">选择</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript">
    function getRoleName(val) {
        var obj = new Object();
        <c:forEach items="${roles}" var="role">
        obj['${role.id}'] = "${role.roleName}";
        </c:forEach>
        return obj[val];
    }
    function getDictValue(state) {
        var data = new Object();
        <c:forEach items="${dict['EMP_STATE']}" var="states">
        data["${states.chooseVal}"] = "${states.showText}"
        </c:forEach>
        if (data[state]) {
            return data[state];
        } else {
            return "未知";
        }
    }
</script>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript" src="static/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="static/easyui/easyloader.js"></script>
<script type="text/javascript" src="static/js/organization/jobset.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var minHeight = document.body.clientHeight;
        var divheight = $(".content-header").outerHeight(true);
        var minPanelHeight = parseInt(minHeight) - parseInt(divheight) - 70 + "px";
        $(".panel-default").css({"min-height": minPanelHeight, "padding": 0});

    });
</script>
</body>
</html>

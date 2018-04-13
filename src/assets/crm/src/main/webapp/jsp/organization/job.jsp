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
    <title>管理系统-职位管理</title>
    <link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>职位管理
        <small>新增/修改职位,设定职位的数据查看范围</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 组织架构管理</a></li>
        <li class="active">职位管理</li>
    </ol>
</section>
<div class="col-md-12" style="padding:0;">
    <div class="col-sm-4 col-md-3" style="padding: 0px;">
        <div class="panel scroll panel-info org_info ">
            <div class="panel-heading">
                组织机构
            </div>
            <div class="panel-body" style="overflow: auto;">
                <ul id="tree" class="ztree" style="width: 100%;"></ul>
            </div>
        </div>
    </div>
    <div class="col-sm-8 col-md-9" style="padding: 0px;">
        <div class="panel scroll panel-info right_table">
            <div class="panel-heading">职位信息</div>
            <div class="panel-body">
                <div id="tools">
                    <button id="addStation" type="button" class="btn btn-primary" style="border-radious:0"><i
                            class='icon icon-plus'></i> 添加
                    </button>
                    <button id="editStation" type="button" class="btn btn-warning"
                            style="margin-left:-6px; border-radious:0"><i class='icon icon-edit'></i> 修改
                    </button>
                    <button id="deleteStation" type="button" class="btn btn-del btn-danger"
                            style="margin-left:-6px;border-radious:0;border-radius-top-right:3px;">
                        <i class='icon icon-remove'></i> 删除
                    </button>
                    <button id="showPerson" type="button" class="btn btn-bitbucket"
                            style="margin-left:-6px; border-radious:0"><i class='icon icon-user'></i>查看人员
                    </button>
                </div>
                <table id="jobTable"></table>
            </div>

        </div>
    </div>
</div>
<!-- Modal -->
<!-- addDialog-->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
     aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="dictLabel">岗位信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" action="jobstation/save.json" id="addForm">
                    <input type="hidden" class="form-control" id="saveorgId" name="orgId">
                    <div class="form-group">
                        <label for="colName" class="col-sm-2 control-label">职位名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="savename" name="name">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">职位类型</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="jobType" id="savesale">
                                <c:forEach items="${dict['JOB_TYPE']}" var="states">
                                    <option value="${states.chooseVal}">${states.showText}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="colName" class="col-sm-2 control-label">隶属团队</label>
                        <div class="col-sm-6">
                            <select class="form-control no-appearance" name="team">
                                <option></option>
                                <c:forEach items="${dict['BELONG_TEAM']}" var="item">
                                    <option value="${item.chooseVal}">${item.showText}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-add" class="btn btn-primary btn-save" form="addForm">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- editDialog-->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
     aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="dictLabel">岗位信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" action="jobstation/edit.json" id="editForm">
                    <input type="hidden" class="form-control" id="editid" name="id">
                    <div class="form-group">
                        <label for="colName" class="col-sm-2 control-label">职位名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editname" name="name">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">职位类型</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="jobType" id="editjobType">
                                <c:forEach items="${dict['JOB_TYPE']}" var="states">
                                    <option value="${states.chooseVal}">${states.showText}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="colName" class="col-sm-2 control-label">隶属团队</label>
                        <div class="col-sm-6">
                            <select class="form-control no-appearance" id="editteam" name="team">
                                <option></option>
                                <c:forEach items="${dict['BELONG_TEAM']}" var="item">
                                    <option value="${item.chooseVal}">${item.showText}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-add" class="btn btn-primary btn-save" form="editForm">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- show user modal -->
<div class="modal fade" id="userModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">用户</h4>
            </div>
            <div class="modal-body">
                <table id="userTable"></table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function getDictValue(state) {
        var data = new Object();
        <c:forEach items="${dict['JOB_TYPE']}" var="states">
        data["${states.chooseVal}"] = "${states.showText}"
        </c:forEach>
        if (data[state]) {
            return data[state];
        } else {
            return "未知";
        }
    }
</script>
<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript" src="static/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="static/js/organization/job.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var minHeight = document.body.clientHeight;
        var divheight = $(".content-header").outerHeight(true);
        var minPanelHeight = parseInt(minHeight) - parseInt(divheight) - 100 + "px";
        $(".panel-default").css("min-height", minPanelHeight);
        $(".org_info").css("min-height", minPanelHeight);
        $(".right_table").css("min-height", minPanelHeight);

    });
</script>
</body>
</html>

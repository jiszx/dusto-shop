<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>管理系统-组织管理</title>
    <link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
    <link href="static/css/organization/index.css" rel="stylesheet">
    <script language="JavaScript" src="static/js/organization/Core4j.js"></script>
    <link rel="stylesheet" href="static/css/organization/tabletree4j.css" type="text/css"/>
    <script language="JavaScript" src="static/js/organization/TableTree4j.js"></script>
    <link rel="stylesheet" href="static/css/organization/tinyselect.css" type="text/css"/>
    <jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>组织管理
        <small>添加/修改</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 组织管理</a></li>
        <li class="active">组织架构管理</li>
    </ol>
</section>
<div class="panel scroll panel-default col-md-12">
    <div class="panel-body" style="padding-left:0;min-height: 500px;">
        <div class="col-sm-12" style="padding: 0px;">
            <div class="col-sm-12 operate-add">
                <a class="btn btn-primary" id="addOrganization">添加组织</a>
            </div>
            <div id="worldcupgird" class="tabletree" style="width:100%;"></div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">组织机构基本信息</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <form class="form-horizontal" action="#" id="editOrganization">
                        <div class="form-group">
                            <label for="organizationName" class="col-sm-2 control-label">组织名称</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="organizationName" id="organizationName"
                                       placeholder="组织名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="supOrganization" class="col-sm-2 control-label">上级组织</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="supOrganization" id="supOrganization"
                                       disabled="disabled">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <input type="submit" class="btn btn-primary" id="saveOrganization" value="保存">
                        </div>
                    </form>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
</aside><!-- /.right-side -->
</div><!-- ./wrapper -->
<!-- Modal -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="static/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="static/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="static/js/tinyselect.js"></script>
<script type="text/javascript" src="static/js/organization/index.js"></script>
</body>
</html>

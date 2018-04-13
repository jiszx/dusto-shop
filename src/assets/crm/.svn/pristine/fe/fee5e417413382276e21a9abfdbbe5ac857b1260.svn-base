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
    <title>管理系统-编辑组织</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <style type="text/css">
        .title {
            text-align: right;
            color: #555;
            padding-right: 24px;
            line-height: 40px;
            height: 40px;;
        }

        .operate {
            margin-left: 281px !important;
        }

        .operate input {
            margin-right: 30px;
        }
    </style>
</head>
<body class="skin-blue">
<jsp:include page="/common/header.jsp"></jsp:include>
<div class="wrapper row-offcanvas row-offcanvas-left">
    <jsp:include page="/common/leftMenu.jsp"></jsp:include>
    <!-- Right side column. Contains the navbar and content of the page -->
    <aside class="right-side">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>组织管理
                <small>添加/修改</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
                <li><a href="#"> 组织管理</a></li>
                <li class="active">编辑组织</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-sm-12">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-sm-2 control-label title">组织机构基本信息</label>
                        </div>
                    </div>
                    <div class="row">
                        <form class="form-horizontal" action="#" id="editOrganization">
                            <div class="form-group">
                                <label for="organizationName" class="col-sm-2 control-label">组织名称</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="organizationName" placeholder="组织名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="organizationNum" class="col-sm-2 control-label">组织编号</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="organizationNum" placeholder="组织编号">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="supOrganization" class="col-sm-2 control-label">上级组织</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="supOrganization" placeholder="上级组织">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="jobName" class="col-sm-2 control-label">岗位级别</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="jobName" placeholder="岗位级别">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="businessArea" class="col-sm-2 control-label">业务区域</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="businessArea" placeholder="业务区域">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="principal" class="col-sm-2 control-label">负责人</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="principal" placeholder="负责人">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="positionType" class="col-sm-2 control-label">职位类型</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="positionType" placeholder="职位类型">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="authotatus" class="col-sm-2 control-label">授权状态</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="authotatus" placeholder="授权状态">
                                </div>
                            </div>
                            <div class="form-group operate">
                                <input type="submit" class="btn btn-danger" id="saveOrganization" value="保存">
                                <input type="button" class="btn btn-primary" id="relSaveOrganization" value="保存并继续编辑">
                                <input type="button" class="btn btn-info" id="goback" value="返回">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </aside>
</div>
<script type="text/javascript" src="static/js/organization/editOrganization.js"></script>
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="static/js/organization/index.js"></script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <title>管理系统-岗位管理</title>
    <link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>销售岗位管理<small>新增/修改/调整岗位,为岗位设定业务人员</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 组织架构管理</a></li>
        <li class="active">销售岗位管理</li>
    </ol>
</section>
<div class="col-sm-12 col-md-12" style="padding: 0px;">
    <div class="col-sm-4 col-md-3" style="padding: 0px;">
        <div class="panel scroll panel-info ">
            <div class="panel-heading">
                组织机构
            </div>
            <div class="panel-body" style="overflow: auto;">
                <ul id="tree" class="ztree" style="width: 100%;"></ul>
            </div>
        </div>
    </div>
    <div class="col-sm-8 col-md-9" style="padding: 0px;">
        <div class="panel scroll panel-info ">
            <div class="panel-heading">岗位信息</div>
            <div class="panel-body">
                <div id="tools">
                    <button id="addStation" type="button" class="btn btn-primary"><i class='icon icon-plus'></i>添加</button>
                    <button id="editStation" type="button" class="btn btn-warning"><i class='icon icon-edit'></i>修改</button>
                    <!-- <button id="deleteStation" type="button" class="btn btn-del btn-danger" >
                    	<i class='icon icon-remove'></i>删除
                    </button> -->
                    <button id="relateBtn" type="button" class="btn btn-primary btn-danger" >
                    	<i class='icon icon-edit'></i>关联用户
                    </button>
                </div>
                <table id="stationTable"></table>
            </div>
        </div>
    </div>
</div>
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
                <form class="form-horizontal" method="post" action="station/save.json" id="addForm">
                	<input type="hidden" class="form-control" id="saveorganizationId" name="organizationId">
                    <input type="hidden" class="form-control" id="saveorgAreaId" name="orgAreaId">
                    <div class="form-group">
                        <label for="colName" class="col-sm-2 control-label">岗位名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="savename" name="name">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="colName" class="col-sm-2 control-label">隶属团队</label>
                        <div class="col-sm-6">
                            <select  class="form-control no-appearance" name="description">
                            	<option></option>
				            	<c:forEach items="${dict['BELONG_TEAM']}" var="item">
				                    <option value="${item.chooseVal}">${item.showText}</option>
				                </c:forEach>
				            </select>
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group hide">
                        <label  class="col-sm-2 control-label">岗位人员</label>
                        <div class="col-sm-6">
                            <select class="form-control" name ="salesrepId" id="savesale">
                            	<option value="0" selected></option>
                            </select>
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-add"  class="btn btn-primary btn-save" form="addForm" >保存</button>
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
                <form class="form-horizontal" method="post" action="station/edit.json" id="editForm">
                    <div class="form-group">
                        <label for="colName" class="col-sm-2 control-label">岗位名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editname" name="name">
                            <input type="hidden" class="form-control" id="editid" name="id">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="colName" class="col-sm-2 control-label">隶属团队</label>
                        <div class="col-sm-6">
                            <!-- <input type="text" class="form-control" id="editdescription" name="description"> -->
                            <select id="editdescription" class="form-control no-appearance" name="description">
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
                <button type="submit" id="btn-add"  class="btn btn-primary btn-save" form="editForm" >保存</button>
            </div>
        </div>
    </div>
</div>

<!-- station customer relate -->
<div class="modal fade" id="relateModel" tabindex="-1" role="dialog"
     aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" >关联客户</h4>
            </div>
            <div class="modal-body" >
                <ul id="customerTree" class="ztree" style="width: 100%;"></ul>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="relateSave"  class="btn btn-primary btn-save" >保存</button>
            </div>
        </div>
    </div>
</div>

<!-- relate model -->
<div class="modal fade" id="relateModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">选择用户</h4>
            </div>
            <div class="modal-body">
                <table id="userTable"></table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="relateUser">选择</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
var currentNode = {};
</script>
<!-- Modal -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript" src="static/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="static/js/organization/station.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var minHeight = document.body.clientHeight;
		var divheight = $(".content-header").outerHeight(true);
		var minPanelHeight = parseInt(minHeight) - parseInt(divheight)-110+"px";
		$(".panel-body").css("min-height",minPanelHeight);
		$(".panel-info").css("min-height",minPanelHeight);
		
	});
</script>
</body>
</html>

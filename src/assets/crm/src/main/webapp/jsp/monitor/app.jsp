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
    <title>app版本管理</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>系统监控
        <small>app版本管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-dashboard"></i> 首页</a></li>
        <li><a href="#">系统监控</a></li>
        <li class="active">app版本管理</li>
    </ol>
</section>


<!-- Main content -->
<div class="col-md-12" id="dictTool">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
        <%--<button type="button" class="btn btn-edit btn-warning"><i class='icon icon-edit'></i> 修改</button>--%>
        <button type="button" class="btn btn-addFile btn-info"><i class='icon icon-upload'></i> 上传附件</button>
        <button type="button" class="btn btn-del btn-danger"><i class='icon icon-remove'></i> 删除</button>
    </div>
</div>
<div class="col-md-12">
	<table id="knowledgeTable"></table>
</div>
<div class="col-md-12">

</div>

<!-- addDialog-->
<div class="modal fade" id="addKnowLedgeModal" tabindex="-1" role="dialog"
     aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="dictLabel">新增知识库</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" action="monitor/app/upload" id="addKnowledgeForm"
                      enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="showName" class="col-sm-2 control-label">版本</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" value="" name="objectKey">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="showName" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="attachmentName" value="">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="showName" class="col-sm-2 control-label">更新要求</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="attachmentType">
                            	<option value="0" >不强制更新</option>
                            	<option value="1" >需要强制更新</option>
                            </select>
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <input type="hidden" name="objName" value="${version }">
                    <div class="form-group">
                        <label for="upload" class="col-sm-2 control-label">附件</label>
                        <div class="col-sm-6">
                            <input type="file" class="form-control" id="upload" name="file" multiple="multiple" >
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-add" class="btn btn-primary btn-save" form="addKnowledgeForm">保存</button>
            </div>
        </div>
    </div>
</div>
<!-- editDialog-->
<div class="modal fade" id="editKnowLedgeModal" tabindex="-1" aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="editdictLabel">修改知识库</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" action="knowledge/edit" id="editKnowledgeForm"
                      enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="showName" class="col-sm-2 control-label">类别</label>
                        <div class="col-sm-6">
                            <input type="hidden" name="id" id="editid">
                            <input type="text" class="form-control" id="editshowName" readonly>
                            <input type="hidden" name="objectName" id="editobjectName">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <input type="hidden" name="objName" value="${version }">
                    <div class="form-group">
                        <label for="editupload" class="col-sm-2 control-label">附件</label>
                        <div class="col-sm-6">
                            <input type="file" class="form-control" id="editupload" name="upload" multiple="multiple" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/pdf,image/jpeg,application/vnd.openxmlformats-officedocument.wordprocessingml.document" >
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-edit-save" form="editKnowledgeForm" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript">
    var version = '${version}';
    function getDictValue(state) {
        var data = new Object();
        <c:forEach items="${dict['ATTACHMENT_TYPE']}" var="states">
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
<script type="text/javascript" src="static/js/monitor/app.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var minHeight = document.body.clientHeight;
		var divheight = $(".content-header").outerHeight(true);
		var minPanelHeight = parseInt(minHeight) - parseInt(divheight)-70+"px";
		$(".panel-default").css("min-height",minPanelHeight);
		
	});
</script>
</body>
</html>

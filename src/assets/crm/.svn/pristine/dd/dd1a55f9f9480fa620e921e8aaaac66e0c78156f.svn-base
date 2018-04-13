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
    <title>销售组织管理-组织架构管理</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>销售组织管理
        <small>组织架构</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 销售组织管理</a></li>
        <li class="active">组织架构管理</li>
    </ol>
</section>
<div class="col-md-12" id="orgTool">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
        <button type="button" class="btn btn-primary btn-add"><i class='icon icon-plus'></i> 添加</button>
        <button type="button" class="btn btn-edit btn-warning"><i class='icon icon-edit'></i> 修改</button>
        <button type="button" class="btn btn-del btn-danger"><i class='icon icon-remove'></i> 删除</button>
    </div>
</div>
<div class="col-md-12">
    <table id="orgTable"></table>
</div>
<div class="modal fade" id="addOrgModal" tabindex="-1" role="dialog"
     aria-labelledby="orgLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="dictLabel">新增销售组织</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" action="Org/add" id="addOrgForm">

                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">上级</label>
                        <div class="col-sm-6">
                            <input type="hidden" class="form-control" id="pid" name="pid">
                            <input type="text" class="form-control" readonly id="showPid">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>

                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">组织名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="abbrName" class="col-sm-2 control-label">简称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="abbrName" name="abbrName">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="sapId" class="col-sm-2 control-label">SAP组织</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="sapId" id="sapId">
                                <c:forEach items="${dict['SAP_ORG_CODE']}" var="item">
                                    <option value="${item.chooseVal}">${item.showText}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>

                    <div class="form-group">
                        <label for="contactName" class="col-sm-2 control-label">联系人</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="contactName" name="contactName">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="tel" class="col-sm-2 control-label">联系电话</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="tel" name="tel">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-add" class="btn btn-primary btn-save" form="addOrgForm">保存</button>
            </div>
        </div>
    </div>
</div>
<!-- editDialog-->
<div class="modal fade" id="editOrgModal" tabindex="-1" aria-labelledby="orgLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="editdictLabel">修改组织信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="Org/edit" method="post" id="editOrgForm">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">组织名称</label>
                        <div class="col-sm-6">
                            <input type="hidden" name="id" id="editid">
                            <input type="text" class="form-control" id="editname" name="name">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="abbrName" class="col-sm-2 control-label">简称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editabbrName" name="abbrName">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="editsapId" class="col-sm-2 control-label">SAP组织</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="sapId" id="editsapId">
                                <c:forEach items="${dict['SAP_ORG_CODE']}" var="item">
                                    <option value="${item.chooseVal}">${item.showText}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="contactName" class="col-sm-2 control-label">联系人</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editcontactName" name="contactName">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="tel" class="col-sm-2 control-label">联系电话</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="edittel" name="tel">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-edit-save" form="editOrgForm" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function getDictValue(state) {
        var data = new Object();
        <c:forEach items="${dict['SAP_ORG_CODE']}" var="states">
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
<script type="text/javascript" src="static/easyui/easyloader.js"></script>
<script type="text/javascript" src="static/js/organization/org.js"></script>
</body>
</html>

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
    <title>销售组织管理-开票信息维护</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <style type="text/css">
        .dropdown-menu {
            width: 25.3%;
        }

        .table-condensed {
            width: 100%;
        }

        .input-box-list-title {
            width: 95px;
        }
    </style>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>
        开票信息维护
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#">销售组织管理</a></li>
        <li class="active">开票信息维护</li>
    </ol>
</section>
<div class="panel scroll panel-default col-md-12">
    <div class="panel-body" style="padding-left:0;min-height: 500px;">
        <div class="col-md-12" id="addorgInvoicesTool" style="padding-left:0;">
            <div class="btn-group btn-group-sm" role="group" aria-label="...">
                <button type="button" class="btn btn-edit btn-warning">
                    <i class='icon icon-edit'></i>维护开票信息
                </button>
            </div>
        </div>
        <div class="col-md-12" style="padding-left:0;">
            <table id="orgInvoicesTable"></table>
        </div>
    </div>
</div>
<!-- addDialog-->
<div class="modal fade" id="maintainInvoicesModal" tabindex="-1"
     role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">开票信息维护</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post"
                      action="Org/maintainInfoInvoices" id="maintainInvoicesForm">
                    <div class="form-group">
                        <label for="organizationId" class="col-sm-3 control-label">销售组织</label>
                        <div class="col-sm-6">
                            <input type="hidden" id="editid" name="organizationId">
                            <input type="text" class="form-control" id="editorgname" readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="salecompany" class="col-sm-3 control-label">开票方</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editsalecompany" name="salecompany">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="salecompany" class="col-sm-3 control-label">税号</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editsaletaxno" name="saletaxno">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="salebank" class="col-sm-3 control-label">开票账户</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editsalebank"
                                   name="salebank">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="salebankno" class="col-sm-3 control-label">开票账号</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editsalebankno"
                                   name="salebankno">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="saleaddr" class="col-sm-3 control-label">开票地址</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editsaleaddr" name="saleaddr">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group ">
                        <label for="saletel" class="col-sm-3 control-label">开票电话</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editsaletel" name="saletel">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group ">
                        <label for="taxItem" class="col-sm-3 control-label">税目</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="edittaxItem" name="taxItem">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group ">
                        <label for="tax" class="col-sm-3 control-label">税率</label>
                        <div class="col-sm-6">
                            <div class="input-group">
                                <input type="text" class="form-control" id="edittax" name="tax">
                                <div class="input-group-addon">%</div>
                            </div>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group ">
                        <label for="checker" class="col-sm-3 control-label">复核人</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editchecker" name="checker">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" class="btn btn-primary btn-save"
                        form="maintainInvoicesForm">保存
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript" src="static/js/organization/infoInvoices.js"></script>
</body>
</html>

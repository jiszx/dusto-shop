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
    <title>销售组织管理-组织区域管理</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>销售组织管理<small>组织区域管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 销售组织管理</a></li>
        <li class="active">组织架构管理</li>
    </ol>
</section>
<div class="col-md-12" id="regionTool">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
        <button type="button" class="btn btn-primary"data-toggle="modal" data-target="#addRegionModal"><i class='icon icon-plus'></i> 添加</button>
        <button type="button" id="btn-detail" class="btn btn-info"><i class='icon icon-search'></i> 查看</button>
        <button type="button" class="btn btn-edit btn-warning"><i class='icon icon-edit'></i> 修改</button>
        <button type="button" class="btn btn-del btn-danger"><i class='icon icon-remove'></i> 删除</button>
    </div>
</div>
<div class="col-md-12">
    <table id="regionTable"></table>
</div>
<!-- addDialog-->
<div class="modal fade" id="addRegionModal" tabindex="-1" role="dialog"
     aria-labelledby="regionLabel" aria-hidden="true">
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
                <form class="form-horizontal" method="post" action="organization/region/add" id="addOrgForm">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">名称</label>
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
                        <label for="leaderId" class="col-sm-2 control-label">负责人</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="leaderId" name="leaderId">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="address" class="col-sm-2 control-label">地址</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="address" name="address">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
					<div class="form-group">
                        <label for="zipCode" class="col-sm-2 control-label">邮编</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="zipCode" name="zipCode">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="lpNo" class="col-sm-2 control-label">法人证件号</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="lpNo" name="lpNo">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="lpName" class="col-sm-2 control-label">法人</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="lpName" name="lpName">
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
                    <div class="form-group">
                        <label for="mobile" class="col-sm-2 control-label">联系人手机号</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="mobile" name="mobile">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="description" name="description">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-add"  class="btn btn-primary btn-save" form="addRegionForm" >保存</button>
            </div>
        </div>
    </div>
</div>
<!-- editDialog-->
<div class="modal fade" id="editRegionModal" tabindex="-1" aria-labelledby="RegionLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="editdictLabel">修改员工</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/system/emp/edit" method="post" id="editEmpForm">
                    <div class="form-group">
                        <label for="colName" class="col-sm-2 control-label">字典字段</label>
                        <div class="col-sm-6">
                            <input type="hidden" id="editid" name="id">
                            <input type="text" class="form-control" id="editcolName" name="colName">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="chooseVal" class="col-sm-2 control-label">字典值</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editchooseVal" name="chooseVal">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="showText" class="col-sm-2 control-label">显示文本</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editshowText" name="showText">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="orders" class="col-sm-2 control-label">顺序</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editorders" name="orders">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>

                    <div class="form-group">
                        <label for="canChoose" class="col-sm-2 control-label">是否可选</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="editcanChoose" name="canChoose">
                                <option value="0">否</option>
                                <option value="1">是</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="display" class="col-sm-2 control-label">display</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="editdisplay" name="display">
                                <option value="0">否</option>
                                <option value="1">是</option>
                            </select>
                        </div>
                    </div>


                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>
                <button type="submit" id="btn-edit-save" form="editRegionForm" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>



<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="static/js/Regionanization/Region.js"></script>
</body>
</html>

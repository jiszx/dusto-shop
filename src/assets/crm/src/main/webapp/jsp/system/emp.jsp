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
    <title>管理系统-用户管理</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>用户管理
        <small>系统用户管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 系统管理</a></li>
        <li class="active">用户管理</li>
    </ol>
</section>


<div class="panel scroll panel-info col-sm-3 col-md-3">
    <div class="panel-heading">
        组织机构
    </div>
    <div class="panel-body">
        <ul id="tree" class="ztree" style="width: 100%;"></ul>
    </div>
</div>
<div class="col-sm-9 col-md-9" style="padding: 0px;">
    <div class="panel scroll panel-info ">
        <div class="panel-heading">用户信息</div>
        <div class="panel-body" style="overflow-y: auto;clear:both;">
            <div class="col-md-12" id="dictTool">
                <div class="btn-group btn-group-sm" role="group" aria-label="...">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addEmpModal"><i
                            class='icon icon-plus'></i> 添加
                    </button>
                    <button type="button" class="btn btn-edit btn-warning"><i class='icon icon-edit'></i> 修改
                    </button>
                    <button type="button" class="btn btn-del btn-danger"><i class='icon icon-remove'></i> 删除
                    </button>
                    <div class="btn-group btn-group-sm" role="group">
                        <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false"><i class="icon icon-console"></i>
                            管理
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a href="#" class="btn-reset">重置密码</a></li>
                            <li><a href="#" class="btn-user-stop">用户停用</a></li>
                            <li><a href="#" class="btn-user-start">用户启用</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-12" style="padding-left:0">
                <table id="empTable"></table>
            </div>
        </div>
    </div>
</div>

<!-- addDialog-->
<div class="modal fade" id="addEmpModal" tabindex="-1" role="dialog"
     aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="dictLabel">新增员工</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" action="system/emp/add" id="addEmpForm">
                    <div class="form-group">
                        <label for="loginName" class="col-sm-3 control-label">登录名</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="loginName" name="loginName">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">姓名</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>

                    <div class="form-group">
                        <label for="cardNo" class="col-sm-3 control-label">员工工号</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="cardNo" name="cardNo">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>

                    <div class="form-group">
                        <label for="isSalesman" class="col-sm-3 control-label">是否销售人员</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="isSalesman" name="isSalesman">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="contactTel" class="col-sm-3 control-label">联系电话</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="contactTel" name="contactTel">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-3 control-label">电子邮箱</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="email" name="email">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="organizationName" class="col-sm-3 control-label">所属销售组织</label>
                        <div class="col-sm-6">
                            <input type="text" id="organizationName" class="form-control" readonly>
                            <input type="hidden" id="organizationId" name="organizationId">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="roleId" class="col-sm-3 control-label">角色</label>
                        <div class="col-sm-6">
                            <select name="roleId" id="roleId" class="form-control">
                                <c:forEach items="${roles}" var="role">
                                    <option value="${role.id}">${role.roleName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-add" class="btn btn-primary btn-save" form="addEmpForm">保存</button>
            </div>
        </div>
    </div>
</div>
<!-- editDialog-->
<div class="modal fade" id="editEmpModal" tabindex="-1" aria-labelledby="dictLabel" aria-hidden="true">
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
                <form class="form-horizontal" action="system/emp/edit" method="post" id="editEmpForm">
                    <div class="form-group">
                        <label for="editloginName" class="col-sm-3 control-label">登录名</label>
                        <div class="col-sm-6">
                            <input type="hidden" id="editid" name="id"/>
                            <input type="text" class="form-control" id="editloginName" readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editname" class="col-sm-3 control-label">姓名</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editname" name="name">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>

                    <div class="form-group">
                        <label for="editcardNo" class="col-sm-3 control-label">员工工号</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editcardNo" name="cardNo">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>

                    <div class="form-group">
                        <label for="editisSalesman" class="col-sm-3 control-label">是否销售人员</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="editisSalesman" name="isSalesman">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editcontactTel" class="col-sm-3 control-label">联系电话</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editcontactTel" name="contactTel">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editemail" class="col-sm-3 control-label">电子邮箱</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editemail" name="email">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editroleId" class="col-sm-3 control-label">角色</label>
                        <div class="col-sm-6">
                            <select name="roleId" id="editroleId" class="form-control">
                                <c:forEach items="${roles}" var="role">
                                    <option value="${role.id}">${role.roleName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editroleId" class="col-sm-3 control-label">组织</label>
                        <div class="col-sm-6">
                            <select name="organizationId" id="editorganizationId" class="form-control">
                            </select>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-edit-save" form="editEmpForm" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="showEmpModal" tabindex="-1" aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="editdictLabel">查看员工信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/system/emp/edit" method="post" id="editEmpForm">
                    <div class="form-group">
                        <label for="editloginName" class="col-sm-3 control-label">登录名</label>
                        <div class="col-sm-6">
                            <input type="hidden" id="editid" name="id"/>
                            <input type="text" class="form-control" id="editloginName" name="loginName">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editname" class="col-sm-3 control-label">姓名</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editname" name="name">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editisSalesman" class="col-sm-3 control-label">是否销售人员</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editisSalesman" name="isSalesman">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editcontactTel" class="col-sm-3 control-label">联系电话</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editcontactTel" name="contactTel">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editemail" class="col-sm-3 control-label">电子邮箱</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editemail" name="email">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editroleId" class="col-sm-3 control-label">角色</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editroleId" name="roleId"
                                   value="${role.roleName}">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                </form>
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
<script type="text/javascript" src="static/js/system/emp.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var minHeight = document.body.clientHeight;
        var divheight = $(".content-header").outerHeight(true);
        var minPanelHeight = parseInt(minHeight) - parseInt(divheight) - 70 + "px";
        $(".panel-default").css("min-height", minPanelHeight);

    });
</script>
</body>
</html>

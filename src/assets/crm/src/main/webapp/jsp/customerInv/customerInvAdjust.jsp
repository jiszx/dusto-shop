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
    <title>库存调整</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
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
        库存调整
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#">客户库存管理</a></li>
        <li class="active">库存调整</li>
    </ol>
</section>
<div class="row" style="padding: 0 0 20px 0;">
    <div class="form-group col-md-3 col-sm-6 input-box-list">
        <label for="input1" class=" font12 input-box-list-title"
               style="float: left; line-height: 30px;">客户名称：</label>
        <div class="input-box-list-value">
            <input type="text" class="form-control" placeholder="输入客户名称"
                   style="padding: 0; width: 70%;" id="custname">
        </div>
    </div>
    <div class="form-group col-md-3 col-sm-6 input-box-list">
        <label for="input2" class=" font12 input-box-list-title"
               style="float: left; line-height: 30px;">物料编码：</label>
        <div class="input-box-list-value">
            <input type="text" class="form-control" placeholder="输入物料编码"
                   style="padding: 0; width: 70%;" id="smaterialId">
        </div>
    </div>
    <div class="form-group col-md-3 col-sm-6 input-box-list">
        <label for="input2" class=" font12 input-box-list-title"
               style="float: left; line-height: 30px;">物料名称：</label>
        <div class="input-box-list-value">
            <input type="text" class="form-control" placeholder="输入物料名称"
                   style="padding: 0; width: 70%;" id="ssku">
        </div>
    </div>
    <div class="form-group col-md-3 col-sm-6 input-box-list">
        <label class=" font12 input-box-list-title"
               style="float: left; line-height: 30px;">销售组织：</label>
        <div class="input-box-list-value">
            <select class="form-control no-appearance"
                    style="padding: 0; width: 70%;" id="sorgid">
                <option></option>
                <c:forEach items="${org}" var="orgs">
                    <option value="${orgs.id}">${orgs.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group col-md-offset-3 col-md-3  col-sm-6 input-box-list">
        <button class="btn btn-primary" id="btn-search">
            <i class="icon icon-search"></i>&nbsp;&nbsp;开始搜索
        </button>
    </div>
</div>
<div class="col-md-12" id="invAdjustTool" style="padding-left:0;">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
        <button type="button" class="btn btn-primary" data-toggle="modal"
                data-target="#addinvAdjustModal">
            <i class='icon icon-plus'></i>录入
        </button>
        <button type="button" class="btn btn-edit btn-warning">
            <i class='icon icon-edit'></i>修改
        </button>
        <button type="button" class="btn btn-del btn-danger">
            <i class='icon icon-remove'></i>删除
        </button>
        <button type="button" id="btn-show" class="btn btn-info">
            <i class='icon icon-eye-open'></i> 明细
        </button>
        <button id="btn-audit" type="button" class="btn btn-success">
            <i class='icon icon-check'></i>提交
        </button>
    </div>
</div>
<div class="col-md-12" style="padding-left:0;">
    <table id="invAdjustTable"></table>
</div>

<%--<div class="modal fade" id="addinvAdjustModal" tabindex="-1"
     role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="dictLabel">库存调整录入</h4>
            </div>
            <div class="modal-body">
                sdfsfd
            </div>
        </div>
    </div>
</div>--%>

<!-- addDialog-->
<div class="modal fade" id="addinvAdjustModal" tabindex="-1"
     role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="dictLabel">库存调整录入</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tr>
                        <td>
                            <form class="form-horizontal" method="post" role="form"
                                  action="customerInvAdjust/add" id="addinvAdjustForm">
                                <div class="form-group col-md-6">
                                    <label for="organizationId" class="col-sm-4 control-label">销售组织</label>
                                    <div class="col-sm-7">
                                        <select class="form-control" name="organizationId"
                                                id="organizationId">
                                            <option></option>
                                            <c:forEach items="${org}" var="orgs">
                                                <c:if test="${orgs.levels==2}">
                                                    <option value="${orgs.id}">${orgs.name}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="type" class="col-sm-4 control-label">调整类型:</label>
                                    <div class="col-sm-7">
                                        <select class="form-control" name="type" id="type">
                                            <option value="1">普通调整</option>
                                            <option value="2">客户间调整</option>
                                        </select>
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="merchCustId" class="col-sm-2 control-label">客户名称</label>
                                    <div class="col-sm-9">
                                        <select class="form-control" id="merchCustId" name="merchCustId">
                                        </select>
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>

                                <div class="form-group col-md-12 hide" id="otherMerch">
                                    <label for="merchCustId" class="col-sm-2 control-label">对方客户</label>
                                    <div class="col-sm-9">
                                        <select class="form-control" id="otherMerchCustId" name="otherMerchCustId">
                                        </select>
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="materialId" class="col-sm-2 control-label">物料:</label>
                                    <div class="col-sm-9">
                                        <select class="form-control" name="materialId" id="materialId">
                                        </select>
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="materialId" class="col-sm-4 control-label">单位:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="unit" disabled="disabled">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="materialId" class="col-sm-4 control-label">箱内数量:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="amounts" disabled="disabled">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="materialId" class="col-sm-4 control-label">规格:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="specifications" disabled="disabled">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="invnum" class="col-sm-4 control-label">客户库存数量(件/吨):</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="invNum" readonly="readonly">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="materialId" class="col-sm-4 control-label">单价(元):</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" name="adjustPrice" id="adjustPrice">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="adjustNum" class="col-sm-4 control-label">调整数量</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="adjustNum" name="adjustNum"
                                               value="0">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="adjustAmt" class="col-sm-4 control-label">调整金额(元)</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="adjustAmt" name="adjustAmt"
                                               value="0"
                                               readonly="readonly">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="reason" class="col-sm-4 control-label">调整原因</label>
                                    <div class="col-sm-7">
                                        <select class="form-control" id="reason" name="reason">
                                            <c:forEach items="${dict['INV_ADJUST_REASON']}" var="it">
                                                <option value="${it.chooseVal}">${it.showText}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>


                                <div class="form-group col-md-12">
                                    <label for="remark" class="col-sm-2 control-label">备注</label>
                                    <div class="col-sm-9">
								<textarea rows="1" cols="3" class="form-control" id="remark" name="remark">
								</textarea>
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-add" class="btn btn-primary btn-save"
                        form="addinvAdjustForm">保存
                </button>
            </div>
        </div>
    </div>
</div>

<!-- editDialog-->
<div class="modal fade" id="editinvAdjustModal" tabindex="-1"
     role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="dictLabel">库存调整录入</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tr>
                        <td>
                            <form class="form-horizontal" method="post"
                                  action="customerInvAdjust/edit" id="editinvAdjustForm">
                                <input type="hidden" name="id" id="editid">
                                <div class="form-group col-md-6">
                                    <label for="organizationId" class="col-sm-4 control-label">所属销售组织</label>
                                    <div class="col-sm-7">
                                        <%-- <select class="form-control" name="organizationId"
                                            id="editorganizationId" disabled="disabled">
                                            <option></option>
                                            <c:forEach items="${org}" var="orgs">
                                                <c:if test="${orgs.levels==2}">
                                                    <option value="${orgs.id}">${orgs.name}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select> --%>
                                        <input type="hidden" id="editorganizationId" name="organizationId">
                                        <input type="text" class="form-control" id="editorgname" readonly="readonly">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="type" class="col-sm-4 control-label">调整类型:</label>
                                    <div class="col-sm-7">
                                        <select class="form-control" name="type" id="edittype" disabled="disabled">
                                            <option value="1">普通调整</option>
                                            <option value="2">客户间调整</option>
                                        </select>
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>

                                <div class="form-group col-md-12">
                                    <label for="merchCustId" class="col-sm-2 control-label">客户名称</label>
                                    <div class="col-sm-9">
                                        <!-- <select class="form-control" id="editmerchCustId" name="merchCustId">
                                        </select> -->
                                        <input type="hidden" id="editmerchCustId" name="merchCustId">
                                        <input type="text" class="form-control" id="editcustname" readonly="readonly">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>

                                <div class="form-group col-md-12 hide" id="editmerch">
                                    <label for="merchCustId" class="col-sm-2 control-label">对方客户名称</label>
                                    <div class="col-sm-9">
                                        <!-- <select class="form-control" id="editmerchCustId" name="merchCustId">
                                        </select> -->
                                        <input type="text" class="form-control" id="editothercustname"
                                               readonly="readonly">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="materialId" class="col-sm-4 control-label">物料编码:</label>
                                    <div class="col-sm-7">
                                        <!-- <select class="form-control" name="materialId" id="editmaterialId" disabled="disabled">
                                        </select> -->
                                        <input type="text" class="form-control" name="materialId" id="editmaterialId"
                                               readonly="readonly">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="materialId" class="col-sm-4 control-label">物料:</label>
                                    <div class="col-sm-7">
                                        <!-- <select class="form-control" name="materialId" id="editmaterialId" disabled="disabled">
                                        </select> -->
                                        <input type="text" class="form-control" id="editsku" readonly="readonly">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="materialId" class="col-sm-4 control-label">箱内数量:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="editamounts" disabled="disabled">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="materialId" class="col-sm-4 control-label">单位:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="editunit" disabled="disabled">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="materialId" class="col-sm-4 control-label">规格:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="editspecifications"
                                               disabled="disabled">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="editadjustNum" class="col-sm-4 control-label">调整数量(件/吨)</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="editadjustNum"
                                               name="adjustNum">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="materialId" class="col-sm-4 control-label">单价(元):</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="editadjustPrice" name="adjustPrice" readonly="readonly">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="invnum" class="col-sm-4 control-label">客户库存数量(件/吨):</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="editinvNum" readonly="readonly">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="editadjustAmt" class="col-sm-4 control-label">调整金额(元)</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="editadjustAmt" name="adjustAmt"
                                               value="0"
                                               readonly="readonly">
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>


                                <div class="form-group col-md-6">
                                    <label for="editreason" class="col-sm-4 control-label">调整原因</label>
                                    <div class="col-sm-7">
                                        <select class="form-control" id="editreason" name="reason">
                                            <c:forEach items="${dict['INV_ADJUST_REASON']}" var="it">
                                                <option value="${it.chooseVal}">${it.showText}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <small class="help-block col-sm-3"></small>
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="editremark" class="col-sm-2 control-label">备注</label>
                                    <div class="col-sm-9">
								<textarea rows="2" cols="3" class="form-control" id="editremark" name="remark">
								</textarea>
                                    </div>
                                    <small class="help-block col-sm-1"></small>
                                </div>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-edit-save" class="btn btn-primary btn-save"
                        form="editinvAdjustForm">保存
                </button>
            </div>
        </div>
    </div>
</div>
<!-- showDialog-->
<div class="modal fade" id="showinvAdjustModal" tabindex="-1"
     role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="dictLabel">库存调整录入</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post">
                    <input type="hidden" id="showid">
                    <div class="form-group">
                        <label for="organizationId" class="col-sm-3 control-label">所属销售组织</label>
                        <div class="col-sm-6">
                            <input type="hidden" id="showorganizationId">
                            <input type="text" class="form-control" id="showorgname" readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="merchCustId" class="col-sm-3 control-label">客户名称</label>
                        <div class="col-sm-6">
                            <!-- <select class="form-control" id="editmerchCustId" name="merchCustId">
                            </select> -->
                            <input type="hidden" id="showmerchCustId">
                            <input type="text" class="form-control" id="showcustname" readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="type" class="col-sm-3 control-label">调整类型:</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="type" id="showtype" disabled="disabled">
                                <option value="1">普通调整</option>
                                <option value="2">客户间调整</option>
                            </select>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group hide" id="showmerch">
                        <label for="merchCustId" class="col-sm-3 control-label">对方客户名称</label>
                        <div class="col-sm-6">
                            <!-- <select class="form-control" id="editmerchCustId" name="merchCustId">
                            </select> -->
                            <input type="text" class="form-control" id="showothercustname"
                                   readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="materialId" class="col-sm-3 control-label">物料编码:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showmaterialId" readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="materialId" class="col-sm-3 control-label">物料:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showsku" readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="materialId" class="col-sm-3 control-label">单位:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showunit" readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="materialId" class="col-sm-3 control-label">规格:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showspecifications"
                                   readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="materialId" class="col-sm-3 control-label">箱内数量:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showamounts" readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="materialId" class="col-sm-3 control-label">单价(元):</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showadjustPrice"
                                   readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="invnum" class="col-sm-3 control-label">客户库存数量(件/吨):</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showinvNum" readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="billOutDate" class="col-sm-3 control-label">调整数量(件/吨)</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showadjustNum" readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="billOutDate" class="col-sm-3 control-label">调整金额(元)</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showadjustAmt" readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="billInDate" class="col-sm-3 control-label">调整原因</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="showreason" disabled="disabled">
                                <c:forEach items="${dict['INV_ADJUST_REASON']}" var="it">
                                    <option value="${it.chooseVal}">${it.showText}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="billNo" class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-6">
								<textarea rows="2" cols="3" class="form-control" id="showremark" readonly="readonly">
								</textarea>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function getStates(state) {
        var data = new Object();
        <c:forEach items="${dict['INV_ADJUST_STATES']}" var="state">
        data["${state.chooseVal}"] = "${state.showText}"
        </c:forEach>
        if (data[state]) {
            return data[state];
        } else {
            return "未知";
        }
    }
    function getCustType(type) {
        var data = new Object();
        <c:forEach items="${dict['CUST_MERCH_TYPE']}" var="type">
        data["${type.chooseVal}"] = "${type.showText}"
        </c:forEach>
        if (data[type]) {
            return data[type];
        } else {
            return "未知";
        }
    }
    function getReasons(reason) {
        var data = new Object();
        <c:forEach items="${dict['INV_ADJUST_REASON']}" var="reason">
        data["${reason.chooseVal}"] = "${reason.showText}"
        </c:forEach>
        if (data[reason]) {
            return data[reason];
        } else {
            return "未知";
        }
    }
</script>
<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="static/js/customerInv/customerInvAdjust.js"></script>
</body>
</html>

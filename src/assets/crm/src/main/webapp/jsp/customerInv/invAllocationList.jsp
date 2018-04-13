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
    <title>调拨单查询</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <style type="text/css">
        .font-color {
            color: #ccc;
        }

        #logisticstails th {
            text-align: center !important;
        }

        #logisticstails th, #logisticstails td {
            border: 1px solid #ccc;
        }

        #orderdetails th, #orderdetails td {
            border: 1px solid #ccc;
        }

        .customizeWidth {
            width: 60%;
            display: inline-block;
        }

        @media screen and (max-width: 1024px) {

            label {
                width: 100px;
                text-align: right
            }
        }
    </style>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>
        客户库存管理
        <small>调拨单查询查询</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li class="active">客户库存管理</li>
    </ol>
</section>
<div class="col-md-12" id="querycondition" style="padding-left:0px;">
    <form class=" form-inline"  onsubmit="doSearch()">
        <div class="form-group col-md-3 col-sm-6">
            <label>送达方名称　:</label>
            <input type="text" class="form-control" id="custname">
        </div> 
        <div class="form-group col-md-3 col-sm-6">
            <label>调拨单号:</label>
            <input type="text" class="form-control" id="id">
        </div>
        <div class="form-group col-md-3 col-sm-6 hide">
            <label>SAP订单号:</label>
            <input type="text" class="form-control" id="sapId">
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label>开始时间　:</label>
            <input type="text" class="form-control" placeholder="请输入开始时间" id="startTime"
            >
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label>结束时间　:</label>
            <input type="text" class="form-control" placeholder="请输入结束时间" id="endTime">
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label>送货地址:</label>
            <input type="text" class="form-control" id="addr">
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label>客户类型　:</label>
            <select id="custType" class="form-control">
                <option value="">全部</option>
                <c:forEach items="${dict['CUST_MERCH_TYPE']}" var="type">
                    <option value="${type.chooseVal}">${type.showText}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <button type="submit" class="btn btn-success" id="btn-search"><i class="icon icon-search"></i>&nbsp;&nbsp;查询</button>
        </div>
    </form>
</div>
<div class="col-md-12 col-sm-12" id="ordersearchTool" style="padding:0;margin-top: 10px">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
        <button type="button" id="btn-detail" class="btn btn-info">
            <i class='icon icon-eye-open'></i> 详情
        </button>
        <button type="button" id="btn-edit" class="btn btn-edit btn-warning">
            <i class='icon icon-edit'></i> 修改
        </button>
        <button type="button" id="btn-del" class="btn btn-del btn-danger">
            <i class='icon icon-remove'></i> 删除
        </button>
        <button type="button" id="tbn-audit" class="btn btn-del btn-success">
            <i class='icon approval_img' style="height:14px;"></i> 提交
        </button>
        <!-- <button type="button" id="makesureBtn" class="btn btn-edit">
            <i class='icon icon-stop' ></i>调拨确认
        </button> -->
        <button type="button" id="closeOrder" class="btn btn-primary hide">
            <i class='icon icon-stop' ></i>关闭
        </button>
        <button type="button" class="btn btn-info" id="exportBtn">
            <i class='icon icon-file'></i> 导出
        </button>
        <button type="button" class="btn btn-info" id="exportBtn2">
            <i class='icon icon-file'></i> 调拨单导出
        </button>
    </div>
</div>
<div class="col-md-12 col-sm-12">
    <table id="orderTable">
    </table>
</div>
<!-- modal -->
<div class="modal fade" id="exportModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">导出</h4>
            </div>
            <div class="modal-body" id="generateFile">
                                                         正在生成excel，请耐心等待...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<div class="modal fade" id="sapSendAgianModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">接口信息</h4>
            </div>
            <div class="modal-body" id="sapErrorText">
            </div>
            <input type="hidden" id="sapSerialNo">
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="sendAgian">确认</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<script type="text/javascript">
    function getorderStates(value) {
        var data = new Object();
        <c:forEach items="${dict['OM_ORDER_HEADER_TYPE']}" var="type">
        data["${type.chooseVal}"] = "${type.showText}"
        </c:forEach>
        if (data[value]) {
            return data[value];
        } else {
            return "未知";
        }
    }
    /**function getorderType(value) {
        var data = new Object();
        <c:forEach items="${dict['ORDER_TYPE']}" var="type">
        data["${type.chooseVal}"] = "${type.showText}"
        </c:forEach>
        if (data[value]) {
            return data[value];
        } else {
            return "未知";
        }
    } */
    function getorderDeliveryType(value) {
        var data = new Object();
        <c:forEach items="${dict['ORDER_DELIVERY_TYPE']}" var="type">
        data["${type.chooseVal}"] = "${type.showText}"
        </c:forEach>
        if (data[value]) {
            return data[value];
        } else {
            return "未知";
        }
    }
</script>
<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="static/js/customerInv/invAllocationList.js?v=1.1"></script>
</body>
</html>

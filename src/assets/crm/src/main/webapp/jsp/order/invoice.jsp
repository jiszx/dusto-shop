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
    <title>管理系统-订单发票</title>
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
        订单发票管理
        <small>订单发票</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li class="active">订单发票</li>
    </ol>
</section>
<%--<div class="col-md-2 col-sm-2" style="border-right:1px solid #ccc;">
    <ul class="nav nav-pills nav-stacked" id="orderStates" >
        <li><a href="#">全部</a></li>
        <c:forEach items="${dict['OM_ORDER_HEADER_TYPE']}" var="type">
        <li value="${type.chooseVal}"><a href="javascript:;">${type.showText}</a></li>
		</c:forEach>
    </ul>
</div>--%>
<div class="col-md-12" id="querycondition" style="padding-left:0px;">

    <form class=" form-inline"  onsubmit="doSearch()">
        <div class="form-group col-md-3 col-sm-6">
            <label>客户名称　:</label>
            <input type="text" class="form-control" id="custname">
        </div>
       <!--  <div class="form-group col-md-3 col-sm-6">
            <label>送达方名称:</label>
            <input type="text" class="form-control" id="shipname">
        </div> -->
        <div class="form-group col-md-3 col-sm-6">
            <label>CRM订单号:</label>
            <input type="text" class="form-control" id="crmorderid">
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label>SAP订单号:</label>
            <input type="text" class="form-control" id="saporderid">
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
            <label>销售人员 　:</label>
            <input type="text" class="form-control" placeholder="请输入结束时间" id="saler">
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label>订单状态　:</label>
            <select id="orderState" class="form-control">
                <option value="0">全部</option>
                <c:forEach items="${dict['OM_ORDER_HEADER_TYPE']}" var="type">
                    <option value="${type.chooseVal}">${type.showText}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group col-md-3 col-sm-6">
            <button type="submit" class="btn btn-success" id="btn-search"><i class="icon icon-search"></i>&nbsp;&nbsp;查询</button>
        </div>
    </form>
</div>
<div class="col-md-12 col-sm-12" id="ordersearchTool" style="padding:0;">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
        <button type="button" id="btn-detail" class="btn btn-info">
            <i class='icon icon-eye-open'></i> 详情
        </button>
        <button type="button" id="exportBtn" class="btn btn-del btn-success">
            <i class='icon approval_img' style="height:14px;"></i>导出数据
        </button>
    </div>
</div>
<div class="col-md-12 col-sm-12">
    <table id="orderTable">
    </table>
</div>

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
    </div>
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
    function getorderType(value) {
        var data = new Object();
        <c:forEach items="${dict['ORDER_TYPE']}" var="type">
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
<script type="text/javascript" src="static/js/order/invoice.js"></script>
</body>
</html>

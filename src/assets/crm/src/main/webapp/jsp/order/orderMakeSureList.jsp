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
              订单管理
        <small>调拨单确认收货</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li class="active">订单管理</li>
    </ol>
</section>
<div class="col-md-12" id="querycondition" style="padding-left:0px;">
    <form class=" form-inline" >
        <div class="form-group col-md-3 col-sm-6">
            <label>客户名称　:</label>
            <input type="text" class="form-control" id="custname">
        </div> 
        <div class="form-group col-md-3 col-sm-6">
            <label>CRM订单号:</label>
            <input type="text" class="form-control" id="orderId">
        </div>
        <div class="form-group col-md-3 col-sm-6 ">
            <label>SAP订单号:</label>
            <input type="text" class="form-control" id="sapOrderId">
        </div>
    </form>
        <div class="form-group col-md-3 col-sm-6">
            <button type="submit" class="btn btn-success" id="btn-search"><i class="icon icon-search"></i>&nbsp;&nbsp;查询</button>
        </div>
</div>
<div class="col-md-12 col-sm-12" id="orderTool" style="padding:0;margin-top: 10px">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
        <button type="button" id="makesureBtn" class="btn btn-edit">
            <i class='icon icon-stop' ></i>确认收货
        </button>
        <button type="button" class="btn btn-info" id="exportBtn">
            	<i class='icon icon-file'></i> 导出
        	</button>
    </div>
</div>
<div class="col-md-12 col-sm-12">
    <table id="orderTable">
    </table>
</div>

<script type="text/javascript">
    function getOrderType(value) {
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
    function getCustType(value) {
        var data = new Object();
        <c:forEach items="${dict['CUST_MERCH_TYPE']}" var="type">
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
<script type="text/javascript" src="static/js/order/orderMakeSureList.js"></script>
</body>
</html>

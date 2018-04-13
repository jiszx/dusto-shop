<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>管理系统-客户管理</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <style type="text/css">
        .selectWidth {
            width: 70% !important;
            margin: 10px 0;
        }
		.form-horizontal .input-box-list {
			display: table;
			margin: 10px 0 0 0;
			padding-left: 10px;
		}
        .hide {
            display: none;
        }

        .spred {
            margin: 20px 0;
        }
    </style>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>
        物流商库存预警管理
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 客户管理</a></li>
        <li class="active">物流商库存预警管理</li>
    </ol>
</section>
<div class="col-md-12" style="padding: 0">
    <form class="form-inline" id="searchForm" onsubmit="searchCustomer();">
        <div class="form-group col-md-3 col-sm-6">
            <label for="exampleInputEmail3">客户名称</label> <input type="text"
                                                                class="form-control selectWidth" name="customerName"
                                                                placeheader="请输入客户名称">
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label for="exampleInputEmail3">客户编号</label> <input type="text"
                                                                 class="form-control selectWidth" name="id"
                                                                 placeheader="请输入客户编号">
        </div>
        <div class="form-group col-md-3 com-sm-6" style="margin:10px 0;">
            <!-- Split button -->
             <button type="submit" class="btn btn-success" id="searchBtn"><i class="icon icon-search">
                 &nbsp;&nbsp查询</i></button>
        </div>
</div>
</form>
</div>
<div class="col-md-12" id="customerTool" style="padding: 0">

    <div class="col-md-12" style="padding: 0">
        <div class="btn-group btn-group-sm" role="group" aria-label="...">
            <%--<a type="button" class="btn btn-primary" href="customer/index.html"><i
                    class='icon icon-plus'></i> 添加</a>--%>

            <!-- Single button -->
            <button type="button" class="btn btn-del btn-primary" id="startWarningBtn">
                <i class='icon icon-check'></i> 开启预警
            </button>
            <button type="button" class="btn btn-del btn-danger" id="stopWarningBtn">
                <i class='icon icon-check-empty'></i> 关闭预警
            </button>
        </div>
    </div>

</div>
<div class="col-md-12">
    <table id="customerTable"></table>
</div>

<div class="modal fade" id="choosePosition">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">选择岗位</h4>
            </div>
            <div class="modal-body">
                <table id="positionTable"></table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="choosePosiBtn">选择</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript" src="static/js/customerInv/invWarningList.js"></script>
</body>
</html>

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
    <title>管理系统-付款方管理</title>
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
        付款方管理
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 客户管理</a></li>
        <li class="active">付款方管理</li>
    </ol>
</section>
<div class="col-md-12" style="padding: 0">
    <form class="form-inline" id="searchForm" onsubmit="searchCustomer();">
        <div class="form-group col-md-3 col-sm-6 mustShow">
            <label for="exampleInputEmail3">客户名称</label> <input type="text"
                                                                class="form-control selectWidth" name="merchName"
                                                                placeholder="客户名称">
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label for="exampleInputEmail3">SAP编码</label> <input type="text"
                                                                 class="form-control selectWidth" name="merchSap"
                                                                 placeholder="客户SAP编码">
        </div>
        <div class="form-group col-md-3 com-sm-6 mustShow" style="margin:10px 0;">
            <!-- Split button -->
            <div class="btn-group">
                <button type="submit" class="btn btn-success" id="searchBtn"><i class="icon icon-search">
                    &nbsp;&nbsp;查询</i></button>
            </div>

        </div>
	</form>
</div>
<div class="col-md-12" id="customerTool" style="padding: 0">

    <div class="col-md-12" style="padding: 0">
        <div class="btn-group btn-group-sm" role="group" aria-label="...">
            <button type="button" id="deleteBtn" class="btn btn-danger">
                <i class='icon icon-remove'></i>删除
            </button>
            <button type="button" id="relateBtn" class="btn btn-info">
                <i class='icon icon-pencil'></i>关联付款方
            </button>
            
        </div>
    </div>

</div>
<div class="col-md-12">
    <table id="customerTable"></table>
</div>

<div class="modal fade" id="payerModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">选择付款方</h4>
            </div>
            <div class="modal-body">
                <table id="payerTable"></table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="relatePayerBtn">选择</button>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript" src="static/js/customer/payer.js"></script>
<script type="text/javascript">
    $(function () {
    	
    })
</script>
</body>
</html>

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
    <title>客户资金管理-资金分配</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/table/new/bootstrap-editable.css" rel="stylesheet">
    <link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
    <style>
        body {
            font-family: "微软雅黑";
        }

        ul, li {
            list-style: none;
        }

        select {
            -webkit-appearance: none;
            -webkit-border-radius: 0;
        }

        .nav.nav-pills > li {
            padding: 5px 0;
        }

        .nav.nav-pills > li > a {
            padding: 5px 15px;
            background: rgba(0, 0, 0, .05);
            border: none;
            transition: 0.2s;
            font-size: 13px;
        }

        .nav.nav-pills > li.active > a, .nav.nav-pills > li.active > a:hover {
            color: #fff;
            background: #3c8dbc;
            font-weight: normal;
        }

        .nav.nav-pills > li > a:hover {
            background: hsla(202, 68%, 74%, .35);
            font-weight: normal;
        }

        .form-horizontal .input-box-list {
            display: table;
            margin: 10px 0 0 0;
            padding-left: 10px;
        }

        .input-box-list-value textarea {
            resize: none;
        }

        input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
            font-size: 12px;
        }

        .input-box-list-value textarea {
            resize: none;
        }

        input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
            font-size: 12px;
        }

        .form-label {
            float: left;
            text-align: right;
            width: 80px;
            height: 30px;
            line-height: 30px;
        }

        .form-input-box {
            width: 100%;
            height: 30px;
            line-height: 30px;
            padding-left: 90px;
        }

        .form-input-box > * {
            width: 100%;
        }

        .chosen-container .chosen-results {
            max-height: 120px !important;
        }

        .bootstrap-table .table > thead > tr > th {
            font-size: 12px !important;
            font-weight: bold !important;
            background-color: rgba(0, 0, 0, 0.015) !important;
        }

        .bootstrap-table .table > tbody > tr {
            font-size: 14px;
            background-color: #fff;
        }

        .bootstrap-table .fixed-table-footer table > tbody > tr > td {
            font-size: 14px;
            background-color: rgba(0, 0, 0, 0.015);
            font-weight: bold;
        }

        .btn-file input[type="file"] {
            position: absolute;
            right: 0;
            top: 0;
            opacity: 0;
        }

        .tips {
            display: inline-block;
            position: fixed;
            z-index: 100;
            border-radius: 3px;
            padding: 6px 15px;
            left: 50%;
            bottom: 8%;
            font-size: 12px;
            text-align: center;
            background-color: rgba(0, 0, 0, .58);
            color: #fff;
            transition: opacity 0.6s;
            opacity: 0;
        }

        .tips.show {
            opacity: 1;
            z-index: 9000;
        }
    </style>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>资金分配</h1>
    <ol class="breadcrumb">
        <li><a href="javascript:;"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="javascript:;"> 客户资金管理</a></li>
        <li class="active">资金分配</li>
    </ol>
</section>
<div class="row" style="margin: 0;">
    <div class="form-horizontal">
        <div class="form-group col-md-4 col-xs-12 input-box-list">
            <p class="text-muted form-label">选择配送商:</p>
            <div class="form-input-box">
                <select data-placeholder="请选择配送商..." tabindex="1" id="merchCustId" >
                </select>
            </div>
        </div>
        <div class="form-group col-md-4 col-xs-12 input-box-list">
            <p class="text-muted form-label">打款记录:</p>
            <div class="form-input-box">
                <select data-placeholder="请选择打款记录..." tabindex="1" id="receivables">
                </select>
            </div>
        </div>
    </div>
</div>
<div id="row-balance" class="row hidden" style="margin:20px 0;">
    <div class="form-horizontal">
        <div class="form-group col-md-4 input-box-list">
            <p class="text-muted form-label">打款金额:</p>
            <div class="form-input-box">
                <div class="input-group">
                    <input id="payAmt" type="text" class="form-control" value="10000.00" readonly style="background-color: #fff;">
                    <span class="input-group-addon">元</span>
                </div>
            </div>
        </div>
        <div class="form-group col-md-4 input-box-list">
            <p class="text-muted form-label">未分配金额:</p>
            <div class="form-input-box">
                <div class="input-group">
                    <input id="allocationAmt" type="text" class="form-control" value="10000.00" readonly
                           style="background-color: #fff; color:#008800; font-weight: bold;">
                    <span class="input-group-addon">元</span>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="cuntomer-table-box" >
    <div class="row" style="margin:20px 0; padding: 20px 10px; border-top:1px solid rgba(0,0,0,.085);">
        <div id="table-toolbar">
            <a id="btn-select-user" class="btn btn-primary  pull-left" style="margin-right: 5px;" data-toggle="modal"
               data-target="#md-choose-customer">选择客户</a>
            <a id="btn-select-order" class="btn btn-primary  pull-left" style="margin-right: 5px;" >根据订单选择客户</a>
            <!-- <form action="post" enctype="multipart/form-data" style="display: inline-block;">
                <a id="btn-input-excel" class="btn btn-primary  btn-file pull-left">
                    <input id="file-input-excel" type="file"
                           accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
                   	 通过Excel导入
                </a>
            </form> -->
        </div>
        <%-- 数据表格 --%>
        <table id="assigned-table" class="table-box"
               data-toolbar="#table-toolbar" data-search="true"
               data-show-refresh="true"
               data-show-columns="true"
               data-show-export="true"
               data-show-footer="true"
               data-detail-view="false"
               data-detail-formatter="detailFormatter"
               data-id-field="id"
        >
        </table>
    </div>
    <div id="row-btn-submit" class="row text-center hidden" style="margin:0; padding:10px;">
        <a id="btn-submit" class="btn btn-danger" style="padding: 10px 7.5%">确认分配</a>
    </div>
</div>
<!-- Modal -->
<%-- 根据客户选择 --%>
<div class="modal fade" id="md-choose-customer" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">选择客户</h4>
            </div>
            <div class="modal-body">
                <%-- 数据表格 --%>
                <table id="choose-customer-table" class="table-box"
                       data-search="true"
                       data-show-refresh="true"
                       data-show-columns="true"
                       data-show-export="false"
                       data-detail-view="false"
                       data-id-field="id"
                >
                </table>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btn-confirm-customer" class="btn btn-primary" data-dismiss="modal">选择</button>
            </div>
        </div>
    </div>
</div>

<%-- 根据批次订单选择 --%>
<div class="modal fade" id="md-choose-record" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">选择订单</h4>
            </div>
            <div class="modal-body">
                <%-- 数据表格 --%>
                <table id="choose-record-table" class="table-box"
                       data-search="true"
                       data-show-refresh="true"
                       data-show-columns="true"
                       data-show-export="false"
                       data-detail-view="false"
                       data-id-field="id"
                ></table>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btn-confirm-record" class="btn btn-primary" data-dismiss="modal">选择</button>
            </div>
        </div>
    </div>
</div>
<%-- 根据订单选择 --%>
<div class="modal fade" id="md-choose-order" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">选择订单</h4>
            </div>
            <div class="modal-body">
                <%-- 数据表格 --%>
                <table id="choose-order-table" class="table-box"
                       data-search="true"
                       data-show-refresh="true"
                       data-show-columns="true"
                       data-show-export="false"
                       data-detail-view="false"
                       data-id-field="id"
                ></table>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btn-confirm-order" class="btn btn-primary" data-dismiss="modal">选择</button>
            </div>
        </div>
    </div>
</div>

<!-- 确认分配时如果没有完全分配则必须选择一个客户  -->
<div class="modal fade" id="capo-choose-customer" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">选择客户</h4>
            </div>
            <div class="modal-body">
                <%-- 数据表格 --%>
                <table id="capo-customer-table" class="table-box"
                       data-search="true"
                       data-show-refresh="true"
                       data-show-columns="true"
                       data-show-export="false"
                       data-detail-view="false"
                       data-id-field="id"
                >
                </table>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btn-capo-customer" class="btn btn-primary" data-dismiss="modal">选择</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
function getorderStates(value){
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
</script>
<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript" src="static/table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="static/table/new/bootstrap-table-export.js"></script>
<script type="text/javascript" src="static/table/new/bootstrap-table-editable.js"></script>
<script type="text/javascript" src="static/table/new/bootstrap-editable.js"></script>
<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="static/js/account/capotalAllocation.js"></script>
</body>
</html>
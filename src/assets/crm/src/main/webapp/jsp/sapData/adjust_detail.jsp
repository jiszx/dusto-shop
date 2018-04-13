<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>管理系统</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <jsp:include page="/common/lightGallery.jsp"></jsp:include>
    <link rel="stylesheet" href="static/wizards/css/bwizard.min.css">
    <link rel="stylesheet" href="static/table/new/bootstrap-table.css">
    <link rel="stylesheet" href="static/css/pub.css">
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
    <style>
        body {
            font-family: "微软雅黑";
        }
        ul,li{
            list-style: none;
        }
        select {
            -webkit-appearance: none;
            -webkit-border-radius: 0;
        }
        .nav.nav-pills > li{
            padding: 5px 0;
        }

        .nav.nav-pills > li > a{
            padding: 5px 15px;
            background: rgba(0,0,0,.05);
            border:none;
            transition: 0.2s;
            font-size: 13px;

        }
        .nav.nav-pills > li.active > a,.nav.nav-pills > li.active > a:hover{
            color: #fff;
            background: #3c8dbc;
            font-weight: normal;
        }
        .nav.nav-pills > li > a:hover{
            background: hsla(202,68%,74%,.35);
            font-weight: normal;
        }
        .font12{
            font-size: 12px;
        }
        .form-horizontal .input-box-list{
            display: table;
            margin:10px 0 0 0;
            padding-left: 10px;
        }
        .input-box-list-value textarea{
            resize: none;
        }
        input::-webkit-input-placeholder,textarea::-webkit-input-placeholder{
            font-size: 12px;
        }
        .btn-long{
            padding:8px 6.18% ;
        }
        .block-save-link{
            float: right;
            font-size: 13px;
            padding:4px 10px 0;
        }
        .block-save-link:hover{
            text-decoration: underline;
        }
        .input-box-list-title, .input-box-list-value{
            display: table-cell;
        }
        .input-box-list-title{
            width: 100px;
            text-align: right;
            vertical-align: middle;
            padding-right: 5px;
        }
        .input-box-list-value textarea{
            resize: none;
        }
        input::-webkit-input-placeholder,textarea::-webkit-input-placeholder{
            font-size: 12px;
        }
        /* this page */
        .user-select-panel{
            border: 1px solid hsla(0,0%,90%,1);
            background-color: #fff;
            padding: 0 10px;
        }
        .user-select-list{
            height: 146px;
            overflow-y: auto;

        }
        .user-select-panel li{
            border-bottom: 1px solid hsla(0,0%,95%,1);
            /*padding:5px 0 ;*/
        }
        .user-select-panel li .radio{
            padding-left: 10px;
        }
        .factory-select-box .dropdown{
            margin-bottom: 10px;
        }
        .factory-select-box .dropdown a.btn{
            padding-top:10px;
            padding-bottom:10px;
            background-color: #fff;
            border-radius: 0;
        }
        .factory-select-box .dropdown a.btn span.caret{
            margin-top:8px;
        }
        .factory-select-box .dropdown ul{
            width: 100%;;
        }
        .account-panel{
            border: 1px solid hsla(0,0%,90%,1);
            border-top: 2px solid #a94442;
            padding: 20px 20px 10px;
            background-color: #fff;
        }
        .account-panel>div>p{
            margin:0;
        }
        .account-item{
            margin-bottom: 15px;
        }
        .account-item>span{
            margin:0 5px;
        }
        .account-item:last-child{
            border-top: 1px dashed hsla(0,0%,90%,1);
            padding-top: 20px;
        }
        .order-accordion-item{
            border-radius: 0;
        }
        .order-accordion-item div.panel{
            border-radius: 0;
        }
        .order-accordion-item div.panel-heading{
            border-radius: 0;
            background-color: #fff;
            transition: 0.2s;
        }
        .order-accordion-item div.panel-heading:hover{
            background-color:hsla(202,68%,74%,.15);
        }
        .order-item-title-block{
            padding-right: 30px;
            margin-right: 20px;
            border-right: 1px solid hsla(0,0%,95%,1);
            color: #333;
        }
        .order-plus-box{
            margin-bottom: 0;

        }
        .order-plus-box thead{
            background-color: hsla(255,0%,95%,1);

        }
        .order-plus-box td,.order-plus-box th{
            font-size: 12px;
            text-align: center;

        }

        .order-plus-box-add{
            padding: 10px;
            text-align: center;
            width: 100%;
            font-size: 16px;
            background-color: #fff;
            color: hsla(0,0%,65%,1);
            border:1px dashed hsla(0,0%,90%,1);
            cursor: pointer;
            transition: 0.2s;
        }
        .order-plus-box-add:hover{
            color: hsla(0,0%,35%,1);
            border-color: hsla(0,0%,80%,1);
        }
        .add-bstb-box {
            /*min-height: 100px;*/
            background-color: #fff;
            /*border-bottom:1px solid hsla(255,0%,90%,1);*/
            /*padding: 10px  0;*/
            height: 0;
            opacity: 0;
            overflow: hidden;
            transition: 0.2s;
            clear: both;
        }

        .add-bstb-box-open {
            border-bottom: 1px solid hsla(255, 0%, 90%, 1);
            padding: 10px 0;
            opacity: 1;
            height: auto;
        }
        .order-item{
        	float:left;
        	line-height:30px;
        	margin-right:30px;
        }
    </style>
</head>
<body class="container-fluid content">

<section class="content-header">
    <h1>产品价格调整<small>调整详情</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 数据管理</a></li>
        <li class="active">产品价格调整详情</li>
    </ol>
</section>

<form>
    <div class="page-header" id="block-1">
        <h4 class="text-info"><strong>1.&nbsp;</strong>基本信息&nbsp;-&nbsp;
            <small>Basic Information.</small>
        </h4>
    </div>
    <div class="row">
        <div class="form-group col-md-6 col-sm-12 input-box-list">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">调整类型:</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="${priceAdjust.adjustType eq '1'?'标准价':'调整价'}" disabled style="border-radius:5px !important;width:60% !important;">
            </div>
        	<input type="hidden" value="${priceAdjust.id }" id="adjustId">
        	<input type="hidden" value="${priceAdjust.adjustType }" id="adjustType">
        </div>
        <div class=" form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">描述:</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="${priceAdjust.adjustDesc }" disabled style="border-radius:5px !important;width:60% !important;">
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">创建时间:</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value='<fmt:formatDate value="${priceAdjust.createTs}" pattern="yyyy-MM-dd HH:mm:ss"/>' disabled style="border-radius:5px !important;width:60% !important;">
            </div>
        </div>
        <c:if test="${priceAdjust.adjustType eq '1' }">
	        <div class="form-group col-md-6 col-sm-12 factory-select-box">
	            <p class="text-muted order-item" style="width:120px !important; text-align:left;">调整值:</p>
	            <div class="form-group" style="width:100%;">
		            <input type="text" class="form-control" value="标准价改为：<fmt:formatNumber value='${priceAdjust.adjustVal.doubleValue() div 100}' pattern='0.00##' type='number'/>" disabled style="border-radius:5px !important;width:60% !important;">
	            </div>
	        </div>
        </c:if>
        <c:if test="${priceAdjust.adjustType eq '2' }">
	        <div class="form-group col-md-6 col-sm-12 factory-select-box">
	            <p class="text-muted order-item" style="width:120px !important; text-align:left;">调整值:</p>
	            <div class="form-group" style="width:100%;">
		            <input type="text" class="form-control" value="标准价调整为：标准价${(priceAdjust.adjustOpt eq '1'?'+':'*') }<fmt:formatNumber value='${priceAdjust.adjustVal.doubleValue() div 100}' pattern='0.00##' type='number'/>" disabled style="border-radius:5px !important;width:60% !important;">
	            </div>
	        </div>
        </c:if>
        <div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">当前状态:</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="${priceAdjust.status eq '1'?'草稿':''}${priceAdjust.status eq '2'?'已提交':''}${priceAdjust.status eq '3'?'审批通过':''}${priceAdjust.status eq '4'?'驳回':''}" disabled style="border-radius:5px !important;width:60% !important;">
            </div>
        </div>
    </div>
    <%-- 详情 --%>
    <div class="page-header" id="block-1">
        <h4 class="text-info"><strong>2.&nbsp;</strong>影响的物料价格详情&nbsp;-&nbsp;
            <small>Related Materials.</small>
        </h4>
    </div>
    <%-- 项目列表 --%>
    <div class="panel-group order-accordion-item" role="tablist">
        <%-- 数据表格 --%>
        <table id="detail-table" >
        </table>
    </div>
    <%-- button --%>
    <!-- <div class="text-center" style="padding-top: 100px;border-top:1px solid rgba(0,0,0,.15); margin-top: 40px;">
        <button class="btn btn-warning" style="padding: 8px 25px;"  type="button" id="btn-back"><i class="icon icon-save"></i>&nbsp;&nbsp;返回</button>
    </div> -->
</form>

<!-- Modal -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="static/table/new/bootstrap-table.js"></script>
<script type="text/javascript" src="static/table/new/bootstrap-table-export.js"></script>
<script type="text/javascript" src="static/table/new/bootstrap-table-editable.js"></script>
<script type="text/javascript" src="static/table/new/bootstrap-editable.js"></script>
<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="static/js/sapData/adjust_detail.js"></script>
</body>
</html>

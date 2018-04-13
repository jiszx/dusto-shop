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
    <link rel="stylesheet" href="static/table/new/bootstrap-table.css">
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
    <h1>物料批量维护详情
        <small>详情</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li class="active">客户物流价格维护</li>
    </ol>
</section>

<form>
    <div class="page-header">
        <h5>批量维护头信息&nbsp;-&nbsp;Basic Information.
        </h5>
    </div>
    <div class="row">
    	<input type="hidden" value="${maintenance.id}" id="maintenanceId">
        <div class="form-group col-md-6 col-sm-12 input-box-list">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">团队:</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="${maintenance.orgname }" disabled style="border-radius:5px !important;width:60% !important;">
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">大区:</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="${maintenance.reginName }" disabled style="border-radius:5px !important;width:60% !important;">
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">地区:</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="${maintenance.areaName }" disabled style="border-radius:5px !important;width:60% !important;">
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">品牌:</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="${maintenance.brand }" disabled style="border-radius:5px !important;width:60% !important;">
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">起效日期:</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="${maintenance.bDate }" disabled style="border-radius:5px !important;width:60% !important;">
           </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">失效日期:</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="${maintenance.eDate }" disabled style="border-radius:5px !important;width:60% !important;">
           </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">调整类型:</p>
            <div class="form-group" style="width:100%;">
            	<select class="form-control" style="border-radius:5px !important;width:60% !important;" disabled="disabled">
            		<c:forEach items="${dict.get('PRICE_BATCH_MAINTAIN_TYPE') }" var="type">
            				<c:if test="${type.chooseVal == maintenance.type}">
            					<option value="${type.chooseVal}">${type.showText}</option>
            				</c:if>
		            </c:forEach>
            	</select>
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">调整方向:</p>
            <div class="form-group" style="width:100%;">
            	<select class="form-control" style="border-radius:5px !important;width:60% !important;" disabled="disabled">
            		<c:forEach items="${dict.get('PRICE_BATCH_MAINTAIN_DIRECTION') }" var="type">
            				<c:if test="${type.chooseVal == maintenance.adjustDirection}">
            					<option value="${type.chooseVal}">${type.showText}</option>
            				</c:if>
		            </c:forEach>
            	</select>
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">调整价格(元):</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="<fmt:formatNumber value="${maintenance.price * 0.01}" maxFractionDigits="4" pattern="0.00##"/> " disabled style="border-radius:5px !important;width:60% !important;">
            </div>
        </div>
    	<div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">创建人:</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="${maintenance.creater }" disabled style="border-radius:5px !important;width:60% !important;">
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">创建时间:</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="${maintenance.createTs }" disabled style="border-radius:5px !important;width:60% !important;">
            </div>
        </div>
        <c:if test="${maintenance.type =='3'}">
    	<div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">批量导入附件:</p>
            <div class="form-group" style="width:100%;">
	            <a href="${attachmentBASEURI}${attachment.objectName}${attachment.fileName}">批量导入Excle</a>
            </div>
        </div>
    	</c:if>
    </div>
    <%-- 订单详情 --%>
    <div class="page-header">
        <h5>客户详情&nbsp;-&nbsp;
            <!-- <a href="javascript:;" class=" text-info block-save-link"  data-toggle="modal" data-target="#addOrderModal"><i class="icon icon-plus"></i>&nbsp;&nbsp;新增项目</a> -->
        </h5>
    </div>
    <%-- 订单项目列表 --%>
    <div class="col-md-12" id="batchMaintainTool">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn btn-success" id="exportExcel">
				<i class='icon icon-plus'></i>导出
			</button>
		</div>
	</div>
    <div class="panel-group order-accordion-item" role="tablist">
        <%-- 数据表格 --%>
        <table id="product_table" >
        </table>
    </div>
    <c:if test="${flag =='detail'}">
    	<div class="text-center" style="padding-top: 100px;border-top:1px solid rgba(0,0,0,.15); margin-top: 40px;">
        <button class="btn btn-warning" style="padding: 8px 25px;"  type="button" id="btn-back"><i class="icon icon-save"></i>&nbsp;&nbsp;返回</button>
    	</div>
    </c:if>
</form>
   <script type="text/javascript">
		function getStates(state) {
			var data = new Object();
			<c:forEach items="${dict['PRICE_BATCH_MAINTAIN_STATES']}" var="states">
			data["${states.chooseVal}"] = "${states.showText}"
			</c:forEach>
			if (data[state]) {
				return data[state];
			} else {
				return "未知";
			}
		}
		function getType(state) {
			var data = new Object();
			<c:forEach items="${dict['PRICE_BATCH_MAINTAIN_TYPE']}" var="states">
			data["${states.chooseVal}"] = "${states.showText}"
			</c:forEach>
			if (data[state]) {
				return data[state];
			} else {
				return "未知";
			}
		}
		function getDirection(state) {
			var data = new Object();
			<c:forEach items="${dict['PRICE_BATCH_MAINTAIN_DIRECTION']}" var="states">
			data["${states.chooseVal}"] = "${states.showText}"
			</c:forEach>
			if (data[state]) {
				return data[state];
			} else {
				return "未知";
			}
		}
	</script>
<!-- Modal -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/table/new/bootstrap-table.js"></script>
<script type="text/javascript" src="static/table/new/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="static/js/customer/merchPriceBatchMaintainDetail.js"></script>
</body>
</html>

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

<c:if test="${isCenter != '1'}">
<section class="content-header">
    <h1>调拨单管理
        <small>调拨单详情</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li class="active">调拨单详情</li>
    </ol>
</section>
</c:if>

<form>
    <div class="page-header">
        <h5>客户信息&nbsp;-&nbsp;Basic Information.
            <!-- <a href="javascript:;" class=" text-info block-save-link"  data-toggle="modal" data-target="#addOrderModal"><i class="icon icon-plus"></i>&nbsp;&nbsp;新增项目</a> -->
        </h5>
    </div>
    <div class="row">
    	<input type="hidden" id="orderid" value="${id }">
        <div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">CRM调拨单编号:</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="${order.id }" disabled style="border-radius:5px !important;width:60% !important;">
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">SAP调拨单编号:</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="${order.sapOrderId }" disabled style="border-radius:5px !important;width:60% !important;">
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">客户名称:</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="${custname }" disabled style="border-radius:5px !important;width:60% !important;">
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">出库RDC:</p>
            <div class="form-group" style="width:100%;">
            	<select class="form-control" style="border-radius:5px !important;width:60% !important;" disabled="disabled">
            		<c:forEach items="${dict.get('VIRTUAL_WAREHOUSE_CODE') }" var="cust_type">
		                	<option value="${cust_type.chooseVal}" ${cust_type.chooseVal == order.rdcCode?"selected":""}>${cust_type.showText}</option>
		            </c:forEach>
            	</select>
	            <%-- <input type="text" class="form-control" value="${order.rdcCode }" disabled style="border-radius:5px !important;width:60% !important;"> --%>
            </div>
        </div>
        <div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">发货方式:</p>
            <div class="form-group" style="width:100%;">
            	<select class="form-control" style="border-radius:5px !important;width:60% !important;" disabled="disabled">
            		<c:forEach items="${dict.get('ORDER_DELIVERY_TYPE') }" var="type">
		                	<option value="${type.chooseVal}" ${type.chooseVal == order.deliveryType?"selected":""}>${type.showText}</option>
		            </c:forEach>
            	</select>
	            <%-- <input type="text" class="form-control" value="${order.rdcCode }" disabled style="border-radius:5px !important;width:60% !important;"> --%>
            </div>
        </div>
        <c:if test="${order.deliveryType=='2' }">
        <div class="form-group col-md-6 col-sm-12 factory-select-box">
            <p class="text-muted order-item" style="width:120px !important; text-align:left;">运费(元):</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="${order.freight/100  }" disabled style="border-radius:5px !important;width:60% !important;">
            </div>
        </div>
        </c:if>
    </div>
    <%-- 订单详情 --%>
    <div class="page-header">
        <h5>项目详情&nbsp;-&nbsp;
        </h5>
    </div>
    <%-- 订单项目列表 --%>
    <div class="panel-group order-accordion-item" role="tablist">
        <%-- 数据表格 --%>
        <table id="order-table" >
        </table>
    </div>
     <div class="page-header">
        <h5>物流信息&nbsp;-&nbsp;Basic Information.
        </h5>
    </div>
    <%-- 物流信息 --%>
    <div>
    	<table class="table table-condensed table-bordered">
    		<tr class="active">
                <th>物流公司</th>
                <th>到站</th>
                <th>物流单号</th>
                <th>发货时间</th>
                <th>物流费用（元）</th>
                <th>发货司机</th>
                <th>司机电话</th>
                <th>车牌号</th>
                <th>备注</th>
            </tr>
            <c:forEach items="${logistics }" var="logistics">
           		<tr>
	                <td><input class="form-control" name="logistics" value="${logistics.logistics }" readonly/></td>
	                <td><input class="form-control" name="site" value="${logistics.site }" readonly/></td>
	                <td><input class="form-control" name="logisticsNo" value="${logistics.logisticsNo }" readonly/></td>
	                <td><input class="form-control datetimepicker" name="deliveryTs" value='<fmt:formatDate value="${logistics.deliveryTs }" pattern="yyyy-MM-dd HH:mm:ss"/>' readonly/></td>
	                <td><input class="form-control" name="logisticsCost" value="${logistics.logisticsCost }" readonly/></td>
	                <td><input class="form-control" name="driverName" value="${logistics.driverName }" readonly/></td>
	                <td><input class="form-control" name="driverTel" value="${logistics.driverTel }" readonly/></td>
	                <td><input class="form-control" name="plateNo" value="${logistics.plateNo }" readonly/></td>
	                <td><input class="form-control" name="remark" value="${logistics.remark }" readonly/></td>
	            </tr>
            </c:forEach>
    	</table>
    </div>
    <c:if test="${order.orderType ==4 }">
    	<div class="remark col-md-offset-1 col-md-11">
				<albel>备注:</albel><textarea style="width:80%;min-height:60px; display:block;margin-bottom:20px;" id="remark" readonly="readonly">${order.remark }</textarea>
		</div>
    </c:if>
    <div class="page-header" id="block-7">
        <h4 class="text-info">附件&nbsp;-&nbsp;
            <small>Enclosure.</small>
            <!-- <a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a> -->
        </h4>
    </div>
    <div class="demo-gallery " >
        <ul id="lightgallery" class="list-unstyled row">
           <c:forEach items="${attachments }" var="attachment" varStatus="attStatus">
           	  <c:if test="${attachment.attachmentType=='1' }">
              	<li class="col-xs-6 col-sm-4 col-md-3" data-responsive="${attachmentBASEURI}${attachment.objectName}${attachment.fileName} 375" data-src="${attachmentBASEURI}${attachment.objectName}${attachment.fileName}" data-sub-html="<h4></h4><p>${attachment.attachmentName }</p>">
                 	<a href="#" class="thumbnail">
                    	<img class="img-rounded thumbnail-image" src="${attachmentBASEURI}${attachment.objectName}${attachment.fileName}">
                 	</a>
              	</li>
           	  </c:if>
           	  <c:if test="${attachment.attachmentType !='1' }">
              	<li class="col-xs-6 col-sm-4 col-md-3" data-responsive="${attachmentBASEURI}${attachment.objectName}${attachment.fileName} 375" data-src="${attachmentBASEURI}${attachment.objectName}${attachment.fileName}" data-sub-html="<h4></h4><p>${attachment.attachmentName }</p>">
                 	<a href="#" class="thumbnail">
                    	<img class="img-rounded thumbnail-image" src="static/lightGallery/img/file.jpg">
                 	</a>
              	</li>
           	  </c:if>
           </c:forEach>
       </ul>
   </div>
    <%-- submit button --%>
    <c:if test="${isCenter != '1'}">
    <div class="text-center" style="padding-top: 100px;border-top:1px solid rgba(0,0,0,.15); margin-top: 40px;">
        <button class="btn btn-warning" style="padding: 8px 25px;"  type="button" id="btn-back"><i class="icon icon-save"></i>&nbsp;&nbsp;返回</button>
    </div>
    </c:if>
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
<script type="text/javascript" src="static/js/customerInv/details.js"></script>
<script type="text/javascript">
	$(function(){
		$('#lightgallery').lightGallery();
	});
	var orderType = "${order.orderType }";

    function getverification(value){
    	var data = new Object();
		<c:forEach items="${dict['POLICY_TYPE_VERIFICATION']}" var="type">
		data["${type.chooseVal}"] = "${type.showText}"
		</c:forEach>
		if (data[value]) {
			return data[value];
		} else {
			return "未知";
		}
    }
</script>
</body>
</html>

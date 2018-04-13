<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="token" uri="/WEB-INF/token.tld"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<!-- 零售商 -->
<html>
<head>
    <base href="<%=basePath%>">
    <title>管理系统</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link rel="stylesheet" href="static/wizards/css/bwizard.min.css">
    <style>
        body {
            font-family: "微软雅黑";
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

        #stepLink2 {
            position: fixed;
            top: 0;
            right: 0;
            padding: 15px;
            z-index: 2000;
            background-color: rgba(255, 255, 255, .75);
            box-shadow: -2px 3px 5px hsla(0, 0%, 0%, .15);
            transition: 0.3s;
            opacity: 0;
        }

        #stepLink2:hover {
            background-color: #fff;
        }

        .font12 {
            font-size: 12px;
        }

        .form-horizontal .input-box-list {
            display: table;
            margin: 10px 0 0 0;
            padding-left: 10px;
        }

        .input-box-list-title, .input-box-list-value {
            display: table-cell;
        }

        .input-box-list-title {
            width: 113px;
            text-align: right;
            vertical-align: middle;
            padding-right: 5px;
        }

        .input-box-list-value textarea {
            resize: none;
        }

        input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
            font-size: 12px;
        }

        .btn-long {
            padding: 8px 6.18%;
        }

        .block-save-link {
            float: right;
            font-size: 13px;
            padding: 4px 10px 0;
        }

        .block-save-link:hover {
            text-decoration: underline;
        }

        .contact-box, .contact-box-new, .contact-box-add {
            border: 1px solid hsla(0, 0%, 95%, 1);
            background-color: #fff;
            height: 239px;
            padding: 20px 15px;
            max-height: 239px;
            overflow: hidden;
        }

        .bank-box, .bank-box-add {
            border: 1px solid hsla(0, 0%, 95%, 1);
            background-color: #fff;
            height: 250px;
            padding: 20px 15px;
            max-height: 250px;
            overflow: hidden;
        }

        .bank-box-add {
            text-align: center;
            line-height: 180px;
            font-size: 18px;
            color: hsla(0, 0%, 65%, 1);
            border: 1px dashed hsla(0, 0%, 90%, 1);
            cursor: pointer;
            transition: 0.2s;
        }

        .contact-box-add {
            text-align: center;
            line-height: 220px;
            font-size: 18px;
            color: hsla(0, 0%, 65%, 1);
            border: 1px dashed hsla(0, 0%, 90%, 1);
            cursor: pointer;
            transition: 0.2s;
        }

        .contact-box-add:hover, .bank-box-add:hover {
            color: hsla(0, 0%, 35%, 1);
            border-color: hsla(0, 0%, 80%, 1);
        }

        .contact-box-new:before {
            content: '';
            height: 3px;
            display: block;
            background-color: #f39c12;
            margin: -20px -15px 0 -15px;;
        }

        .contact-box:before, .bank-box:before {
            content: '';
            height: 3px;
            display: block;
            background-color: #3c8dbc;
            margin: -20px -15px 0 -15px;;
        }

        .file-list {
            list-style: none;
            font-family: 'FontAwesome';
            padding-left: 10px;
            padding-top: 20px;
            max-height: 239px;
            overflow: auto;
        }

        .file-list > li {
            list-style: none;
            padding: 6px 0;
            border-bottom: 1px dashed hsla(0, 0%, 90%, 1);
        }

        .file-list > li > span.file-name {
            padding-left: 5px;
        }

        .file-list > li:before {
            content: "\f0f6";
        }

        #file-item-add-box {
            overflow: hidden;
        }

        #file-select {
            position: absolute;
            width: 100%;
            height: 100%;
            left: 0;
            right: 0;
            opacity: 0;
            cursor: pointer;
        }

        .area-select {
            float: left;
            width: 33%;
        }

        .area-select:nth-child(1) {
            border-right: 0;
        }

        .area-select:nth-child(2) {
            border-left: 0;
            border-right: 0;
        }

        .area-select:nth-child(3) {
            border-left: 0;
            width: 34%;
        }
        .label-control{
	        width:200px;
	        text-align:right;
        }
        .label-section{
        	width:173px;
        }
        .label-base{
        	width:113px;
        }
        .exampleInputName2{
        	width:300px;
        	text-align:left;
        }
        .input-error{
        	border-color: #b94a48;
        	box-shadow: inset 0 1px 1px rgba(0,0,0,0.075);
        	color: #b94a48;
        }
        .bg-info, .bg-info[disabled], .bg-info[readonly] {
		    background-color: #d9edf7;
		}
    </style>
</head>
<body class="container-fluid content">
<%-- 提示信息  --%>
<div class="modal fade" id="infoModal" tabindex="-1" role="dialog">
</div><!-- /.modal -->
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>客户档案变更<i class="icon icon-eye-open" onclick="compare()" style="cursor: pointer;font-size:16px;margin-left: 12px;" title="点击区分变更项"></i></h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li class="#">零售客户</li>
        <li class="active">客户档案变更</li>
    </ol>
</section>
<form id="saveForm" class="col-md-12" enctype="multipart/form-data" style=" min-height: 200px; padding:10px 0;" action="customer/save.html" method="post">
    <%-- block 1 --%>
    <div class="form-horizontal" id="changeHeadDiv">
	    <div class="form-group col-md-12" style="margin-right:15px;margin-left:15px;">
		    <div class="form-horizontal row" style="margin: 0 0 20px 0;">
		        <div class="form-group col-md-6 col-sm-6 input-box-list">
		            <label for="input_name" class="font12 input-box-list-title label-base">变更编号：</label>
		            <div class="input-box-list-value">
		                <input type="text" disabled class="form-control" readonly value="${change.id }" placeholder="系统自动生成" >
		            </div>
		        </div>
		        <div class="form-group col-md-6 col-sm-6 input-box-list">
		            <label for="input_abbrName" class="font12 input-box-list-title label-base">变更主题：</label>
		            <div class="input-box-list-value">
		                <input type="text" disabled class="required form-control" id="input_name" value="${change.changeTitle }">
		            </div>
		        </div>
			</div>
		</div>
	</div>
    <div class="form-horizontal" id="contrastDiv">
    <div id="changeDiv" class="form-group col-md-6" style="border-right:solid 1px #999;margin-right:15px;margin-left:15px;">
	    <div class="page-header" id="block-1">
	        <h4 class="text-info"><strong>1.&nbsp;</strong>基础信息&nbsp;-&nbsp;
	            <small>Basic Information.</small>
	            <!-- <a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a> -->
	        </h4>
	    </div>
	    <div class="form-horizontal row" style="margin: 0 0 20px 0;">
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input_name" class=" font12 input-box-list-title label-base">客户全名：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled class="form-control" id="input_name" value="${changeVo.name }" >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input_abbrName" class=" font12 input-box-list-title label-base">客户常用名：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled class="form-control" value="${changeVo.abbrName }" id="input_abbrName" >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title label-base">所属地区：</label>
	            <div class="input-box-list-value">
	                <input type="hidden" value="${changeVo.provId }" id="provIdEle">
	                <input type="hidden" value="${changeVo.cityId }" id="cityIdEle">
	                <input type="hidden" value="${changeVo.countyId }" id="countyIdEle">
	                <select disabled class="form-control no-appearance area-select" id="provIdSelect">
	                    <option selected value="">-省份-</option>
	                    <option value="">-加载中...-</option>
	                </select>
	                <select disabled class="form-control no-appearance area-select" id="cityIdSelect">
	                    <option value="">-地市-</option>
	                    <option value="">-请选择省份-</option>
	                </select>
	                <select disabled class="form-control no-appearance area-select" id="countyIdSelect">
	                    <option value="">-区县-</option>
	                    <option value="">-请选择地市-</option>
	                </select>
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input_contactName" class=" font12 input-box-list-title label-base">采购经理：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${changeVo.contactName }" class="form-control" id="input_contactName" >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input_contactTel" class=" font12 input-box-list-title label-base">采购联系电话：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${changeVo.contactTel }" class="form-control" id="input_contactTel" >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input_lpName" class=" font12 input-box-list-title label-base">财务经理：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${changeVo.lpName }"  class="form-control" id="input_lpName" >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title label-base">财务电话：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${changeVo.tel }" class="form-control" id="input_tel" >
	            </div>
	        </div>
	        <div class="form-group col-md-6  col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title label-base">客户渠道：</label>
	            <div class="input-box-list-value">
	                <select disabled class="form-control no-appearance" id="input-agent-type">
	                	<c:forEach items="${dict.get('CUSTOMER_CHANNEL') }" var="cust_type">
							<option value="${cust_type.chooseVal}" ${changeVo.channelId eq cust_type.chooseVal?'selected':''}>${cust_type.showText}</option>
		                </c:forEach>
	                </select>
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title label-base">网点类型：</label>
	            <div class="input-box-list-value">
	                <select disabled class="form-control no-appearance" id="input-agent-type">
	                	<c:forEach items="${dict.get('CUSTOMER_WEBSITE') }" var="cust_type">
							<option value="${cust_type.chooseVal}" ${changeVo.openingType eq cust_type.chooseVal?'selected':''}>${cust_type.showText}</option>
		                </c:forEach>
	                </select>
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title label-base">是否开票：</label>
	            <div class="input-box-list-value">
	                <select disabled class="form-control no-appearance" id="input-agent-type">
	                	<c:forEach items="${dict['IS_BILLING']}" var="it">
							<option value="${it.chooseVal}" <c:if test="${changeVo.isInvoice eq it.chooseVal}">selected</c:if>>${it.showText}</option>
						</c:forEach>
	                </select>
	            </div>
	        </div>
	        <!-- <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title label-base">所属配送商：</label>
	            <div class="input-box-list-value">
	                <select disabled class="form-control no-appearance" id="addPid">
	                </select>
	            </div>
	        </div> -->
	        <div class="form-group col-md-6 col-sm-12 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title label-base">详细地址：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${changeVo.address }" class="form-control" id="required input_address" >
	            </div>
	        </div>
	        <div class="form-group col-md-4 col-sm-6 input-box-list">
	            <label for="input_businessLicense" class=" font12 input-box-list-title label-base">营业执照号：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled name="businessLicense" value="${changeVo.businessLicense }" class="form-control" id="input_businessLicense" placeholder="营业执照号">
	            </div>
	        </div>
	    </div>
	
	    <%-- block 4 --%>
	    <div class="page-header" id="block-4">
	        <h4 class="text-info"><strong>2.&nbsp;</strong>开票信息&nbsp;-&nbsp;
	            <small>Receipt Information.</small>
	        </h4>
	    </div>
	    <div class="row form-horizontal" style="margin: 0 0 20px 0;">
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title">开票名称：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${changeVo.invoiceName }" class="form-control"  >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title">税号：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${changeVo.invoiceTaxNum }" class="form-control"  >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title">注册地址：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${changeVo.invoiceAddress }" class="form-control"  >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title">银行账号：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${changeVo.invoiceAccount }" class="form-control"  >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title">电话：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${changeVo.invoiceTel }" class="form-control"  >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title">开户行：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${changeVo.invoiceBankName }" class="form-control"  >
	            </div>
	        </div>
	    </div>
    </div>
    <div class="form-group form-group col-md-6" id="oldDiv">
	    <div class="page-header" id="block-1">
	        <h4 class="text-info">原基础信息&nbsp;-&nbsp;
	            <small>Basic Information.</small>
	            <!-- <a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a> -->
	        </h4>
	    </div>
	    <div class="form-horizontal row" style="margin: 0 0 20px 0;">
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input_name" class=" font12 input-box-list-title label-base">客户全名：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled class="form-control"  value="${custBase.name }" >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input_abbrName" class=" font12 input-box-list-title label-base">客户常用名：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled class="form-control" value="${custBase.abbrName }"  >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title label-base">所属地区：</label>
	            <div class="input-box-list-value">
	                <select disabled class="form-control no-appearance area-select" >
	                    <option selected value="${custBase.provId }">${custBase.provName}</option>
	                </select>
	                <select disabled class="form-control no-appearance area-select" >
	                    <option selected value="${custBase.cityId }">${custBase.cityName}</option>
	                </select>
	                <select disabled class="form-control no-appearance area-select" >
	                    <option selected value="${custBase.countyId }">${custBase.countyName}</option>
	                </select>
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input_contactName" class=" font12 input-box-list-title label-base">采购经理：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${custBase.contactName }" class="form-control" id="input_contactName" >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input_contactTel" class=" font12 input-box-list-title label-base">采购联系电话：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${custBase.contactTel }" class="form-control" id="input_contactTel" >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input_lpName" class=" font12 input-box-list-title label-base">财务经理：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${custBase.lpName }"  class="form-control" id="input_lpName" >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title label-base">财务电话：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${custBase.tel }" class="form-control" id="input_tel" >
	            </div>
	        </div>
	        <div class="form-group col-md-6  col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title label-base">客户渠道：</label>
	            <div class="input-box-list-value">
	                <select disabled class="form-control no-appearance" id="input-agent-type">
	                	<c:forEach items="${dict.get('CUSTOMER_CHANNEL') }" var="cust_type">
							<option value="${cust_type.chooseVal}" ${custBase.channelId eq cust_type.chooseVal?'selected':''}>${cust_type.showText}</option>
		                </c:forEach>
	                </select>
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title label-base">网点类型：</label>
	            <div class="input-box-list-value">
	                <select disabled class="form-control no-appearance" id="input-agent-type">
	                	<c:forEach items="${dict.get('CUSTOMER_WEBSITE') }" var="cust_type">
							<option value="${cust_type.chooseVal}" ${custBase.openingType eq cust_type.chooseVal?'selected':''}>${cust_type.showText}</option>
		                </c:forEach>
	                </select>
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title label-base">是否开票：</label>
	            <div class="input-box-list-value">
	                <select disabled class="form-control no-appearance" id="input-agent-type">
	                	<c:forEach items="${dict['IS_BILLING']}" var="it">
							<option value="${it.chooseVal}" <c:if test="${custBase.isInvoice eq it.chooseVal}">selected</c:if>>${it.showText}</option>
						</c:forEach>
	                </select>
	            </div>
	        </div>
	       <%--  <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title label-base">所属配送商：</label>
	            <div class="input-box-list-value">
	                <select disabled class="form-control no-appearance" id="existedPid">
	                	<option value="${custBase.pid }"></option>
	                </select>
	            </div>
	        </div> --%>
	        <div class="form-group col-md-6 col-sm-12 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title label-base">详细地址：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${custBase.address }" class="form-control" id="required input_address" >
	            </div>
	        </div>
	        <div class="form-group col-md-4 col-sm-6 input-box-list">
	            <label for="input_businessLicense" class=" font12 input-box-list-title label-base">营业执照号：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled name="businessLicense" value="${custBase.businessLicense }" class="form-control" id="input_businessLicense" placeholder="营业执照号">
	            </div>
	        </div>
	    </div>
	    <%-- block 4 --%>
	    <div class="page-header" id="block-4">
	        <h4 class="text-info">原开票信息&nbsp;-&nbsp;
	            <small>Receipt Information.</small>
	            <!-- <a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a> -->
	            <%--<a href="javascript:;" class=" text-info block-save-link" data-toggle="modal" data-target="#addBankModal"><i
	                    class="icon icon-plus"></i>&nbsp;&nbsp;新增</a>--%>
	        </h4>
	    </div>
	    <div class="row form-horizontal" style="margin: 0 0 20px 0;">
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title">开票名称：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${custBase.invoiceName }" class="form-control"  >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title">税号：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${custBase.invoiceTaxNum }" class="form-control"  >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title">注册地址：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${custBase.invoiceAddress }" class="form-control"  >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title">银行账号：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${custBase.invoiceAccount }" class="form-control"  >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title">电话：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${custBase.invoiceTel }" class="form-control"  >
	            </div>
	        </div>
	        <div class="form-group col-md-6 col-sm-6 input-box-list">
	            <label for="input1" class=" font12 input-box-list-title">开户行：</label>
	            <div class="input-box-list-value">
	                <input type="text" disabled value="${custBase.invoiceBankName }" class="form-control"  >
	            </div>
	        </div>
	    </div>
    </div>
    </div>
    <%-- submit button --%>
    <c:if test="${!readonly eq 'true'}">
	    <div class="text-center" style="padding-top: 30px;">
	        <button class="btn btn-warning" style="padding: 8px 25px;" type="button" id="saveBtn"><i class="icon icon-save"></i>&nbsp;&nbsp;保存
	        </button>
	        <button class="btn btn-primary btn-long" type="button" id="submitBtn"><i class="icon icon-check"></i>&nbsp;&nbsp;提交</button>
	    </div>
    </c:if>
    <input type="hidden" id="msg" value="${msg }">
    <token:token/>
</form>


<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<%--<script type="text/javascript" src="static/wizard/jquery.bootstrap.wizard.min.js"></script>--%>
<script type="text/javascript" src="static/wizards/js/bwizard.js"></script>
<script type="text/javascript" src="static/js/customer/change.js"></script>
<script type="text/javascript">
	var msg = $("#msg").val();
	if(typeof msg != 'undefined' && $.trim(msg) != ''){
		if(msg.indexOf("Failed to convert property value of type 'java.lang.String' to required type 'java.util.Date'") != -1){
			msg="错误的日期格式！"
		}
		$.messager.alert('信息',msg);
	}
</script>
</body>
</html>
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
<html>
<head>
    <base href="<%=basePath%>">
    <title>管理系统</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link rel="stylesheet" href="static/wizards/css/bwizard.min.css">
    <link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
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
        	width:90px;
        }
        .exampleInputName2{
        	width:300px;
        	text-align:left;
        }
    </style>
</head>
<body class="container-fluid content">
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>零售客户建档<c:if test="${not empty custBase.id }">（修改）</c:if>
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li class="active">客户档案</li>
    </ol>
</section>
<form id="saveForm" class="col-md-12" enctype="multipart/form-data" style=" min-height: 200px; padding:10px 0;" action="customer/save.html" method="post">
	<token:token/>
    <%-- block 1 --%>
    <div class="page-header" id="block-1">
        <h4 class="text-info"><strong>1.&nbsp;</strong>基础信息&nbsp;-&nbsp;
            <small>Basic Information.</small>
            <!-- <a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a> -->
        </h4>
    </div>
    <div class="form-horizontal row" style="margin: 0 0 20px 0;">
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">客户全名：</label>
            <div class="input-box-list-value">
                <input type="text" name="name" class="form-control" id="input1" value="${custBase.name }" placeholder="客户名称">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">客户常用名：</label>
            <div class="input-box-list-value">
                <input type="text" name="abbrName" class="form-control" value="${custBase.abbrName }" id="input1" placeholder="客户简称">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">所属地区：</label>
            <div class="input-box-list-value">
                <input type="hidden" name="provName">
                <input type="hidden" name="cityName">
                <input type="hidden" name="countyName">
                <input type="hidden" value="${custBase.provId }" id="provIdEle">
                <input type="hidden" value="${custBase.cityId }" id="cityIdEle">
                <input type="hidden" value="${custBase.countyId }" id="countyIdEle">
                <select name="provId" class="form-control no-appearance area-select" id="provIdSelect">
                    <option selected value="">-省份-</option>
                    <option value="">-加载中...-</option>
                </select>
                <select name="cityId" class="form-control no-appearance area-select" id="cityIdSelect">
                    <option value="">-地市-</option>
                    <option value="">-请选择省份-</option>
                </select>
                <select name="countyId" class="form-control no-appearance area-select" id="countyIdSelect">
                    <option value="">-区县-</option>
                    <option value="">-请选择地市-</option>
                </select>
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">采购经理：</label>
            <div class="input-box-list-value">
                <input type="text" name="contactName" value="${custBase.contactName }" class="form-control" id="contactName" placeholder="采购经理">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">采购联系电话：</label>
            <div class="input-box-list-value">
                <input type="text" name="contactTel" value="${custBase.contactTel }" class="form-control" id="input1" placeholder="业务电话">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">财务经理：</label>
            <div class="input-box-list-value">
                <input type="text" name="lpName" value="${custBase.lpName }"  class="form-control" id="input1" placeholder="财务经理">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">财务电话：</label>
            <div class="input-box-list-value">
                <input type="text" name="tel" value="${custBase.tel }" class="form-control" id="tel" placeholder="财务电话">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input_businessLicense" class=" font12 input-box-list-title label-base">营业执照号：</label>
            <div class="input-box-list-value">
                <input type="text" name="businessLicense" value="${custBase.businessLicense }" class="form-control" id="input_businessLicense" placeholder="营业执照号">
            </div>
        </div>
        <input type="hidden" name="custType" value="5" class="form-control">
        <div class="form-group col-md-4  col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">客户渠道：</label>
            <div class="input-box-list-value">
                <select name="channelId" class="form-control no-appearance" id="input-agent-type">
                	<c:forEach items="${dict.get('CUSTOMER_CHANNEL') }" var="cust_type">
						<option value="${cust_type.chooseVal}"  ${custBase.channelId eq cust_type.chooseVal? 'selected':''}>${cust_type.showText}</option>
	                </c:forEach>
                	<!-- TODO 字典字段完成后修改字段设置默认选中值 -->
                	<%-- <c:forEach items="${dict.get('CUST_MERCH_TYPE') }" var="cust_type">
						<option value="${cust_type.chooseVal}" ${custBase.custType eq cust_type.chooseVal? 'selected':''}>${cust_type.showText}</option>
	                </c:forEach> --%>
                </select>
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">网点类型：</label>
            <div class="input-box-list-value">
                <select name="openingType" class="form-control no-appearance" id="input-agent-type">
                	<c:forEach items="${dict.get('CUSTOMER_WEBSITE') }" var="cust_type">
						<option value="${cust_type.chooseVal}"  ${custBase.openingType eq cust_type.chooseVal? 'selected':''}>${cust_type.showText}</option>
	                </c:forEach>
                	<!-- TODO 字典字段完成后修改字段设置默认选中值 -->
                	<%-- <c:forEach items="${dict.get('CUST_MERCH_TYPE') }" var="cust_type">
						<option value="${cust_type.chooseVal}" ${custBase.custType eq cust_type.chooseVal? 'selected':''}>${cust_type.showText}</option>
	                </c:forEach> --%>
                </select>
            </div>
        </div>

        <div class="form-group col-md-8 col-sm-12 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">详细地址：</label>
            <div class="input-box-list-value">
                <input type="text" name="address" value="${custBase.address }" class="form-control" id="input1" placeholder="详细地址">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">是否开票：</label>
            <div class="input-box-list-value">
                <select name="isInvoice" class="form-control no-appearance" id="input-agent-type">
                	<c:forEach items="${dict['IS_BILLING']}" var="it">
						<option value="${it.chooseVal}" <c:if test="${custBase.isInvoice eq it.chooseVal}">selected</c:if>>${it.showText}</option>
					</c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">所属配送商：</label>
            <div class="input-box-list-value">
                <select name="pid" class="form-control no-appearance" id="addPid">
                </select>
            </div>
        </div>
    </div>
    
    <%-- block 4 --%>
    <div class="page-header" id="block-4">
        <h4 class="text-info"><strong>3.&nbsp;</strong>开票信息&nbsp;-&nbsp;
            <small>Receipt Information.</small>
            <!-- <a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a> -->
            <%--<a href="javascript:;" class=" text-info block-save-link" data-toggle="modal" data-target="#addBankModal"><i
                    class="icon icon-plus"></i>&nbsp;&nbsp;新增</a>--%>
        </h4>
    </div>
    <div class="row form-horizontal" style="margin: 0 0 20px 0;">
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title">开票名称：</label>
            <div class="input-box-list-value">
                <input type="text" name="invoiceName" value="${custBase.invoiceName }" class="form-control" id="input1" placeholder="开票名称">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title">税号：</label>
            <div class="input-box-list-value">
                <input type="text" name="invoiceTaxNum" value="${custBase.invoiceTaxNum }" class="form-control" id="input1" placeholder="税号">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title">注册地址：</label>
            <div class="input-box-list-value">
                <input type="text" name="invoiceAddress" value="${custBase.invoiceAddress }" class="form-control" id="input1" placeholder="地址">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title">银行账号：</label>
            <div class="input-box-list-value">
                <input type="text" name="invoiceAccount" value="${custBase.invoiceAccount }" class="form-control" id="input1" placeholder="账户">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title">电话：</label>
            <div class="input-box-list-value">
                <input type="text" name="invoiceTel" value="${custBase.invoiceTel }" class="form-control" id="input1" placeholder="电话">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title">开户行：</label>
            <div class="input-box-list-value">
                <input type="text" name="invoiceBankName" value="${custBase.invoiceBankName }" class="form-control" id="input1" placeholder="常用打款银行名称">
            </div>
        </div>
    </div>
    <%-- block 3 --%>
    
    <%-- block 5 --%>
    <div class="page-header" id="block-5">
        <h4 class="text-info"><strong>7.&nbsp;</strong>附件&nbsp;-&nbsp;
            <small>Enclosure.</small>
            <!-- <a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a> -->
        </h4>
    </div>
    <div class="row" style="margin: 0 0 20px 0;">
        <div class="col-md-4 col-sm-6 file-list-box">
            <div class="contact-box">
                <ul class="file-list">
                	<c:forEach items="${custBase.attachments }" var="attachment" varStatus="attStatus">
                		<li>
	                        <span class="file-name" data-file-src=""><a href="${attachmentBASEURI}${attachment.objectName}${attachment.fileName}" target="_blank" >${attachment.attachmentName }</a></span>
	                        <a href="javascript:;"class="pull-right del-file" source="${attachment.id }" title="删除附件"><i class="icon icon-remove"></i></a>
	                    </li>
                	</c:forEach>
                </ul>
            </div>
        </div>
        <div class="col-md-4 col-sm-6">
            <div class="contact-box-add" id="file-item-add-box" title="上传附件">
                <input id="file-select" type="file" onchange="addFiles()">
                <i class="icon icon-plus"></i>&nbsp;&nbsp;添加附件
                <input type="hidden" name="delAtts" id="delAtts">
            </div>
        </div>
    </div>
    <%-- submit button --%>
    <div class="text-center" style="padding-top: 30px;border-top:1px solid rgba(0,0,0,.15);">
        <button class="btn btn-warning" style="padding: 8px 25px;" type="button" id="saveBtn"><i class="icon icon-save"></i>&nbsp;&nbsp;保存
        </button>
    </div>
    <input type="hidden" name="id" value="${custBase.id }">
    <input type="hidden" id="msg" value="${msg }">
</form>


<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<%--<script type="text/javascript" src="static/wizard/jquery.bootstrap.wizard.min.js"></script>--%>
<script type="text/javascript" src="static/wizards/js/bwizard.js"></script>
<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="static/js/customer/addOrUpdateRetail.js"></script>
<script type="text/javascript">
	var msg = $("#msg").val();
	var pid = "${custBase.pid}";
	if(typeof msg != 'undefined' && $.trim(msg) != ''){
		$.messager.alert('信息',msg);
	}
</script>
</body>
</html>
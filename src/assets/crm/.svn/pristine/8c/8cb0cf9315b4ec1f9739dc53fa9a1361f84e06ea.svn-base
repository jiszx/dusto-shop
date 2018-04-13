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
<!-- KA -->
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
        .input-error{
        	border-color: #b94a48;
        	box-shadow: inset 0 1px 1px rgba(0,0,0,0.075);
        	color: #b94a48;
        }
    </style>
</head>
<body class="container-fluid content">
<%-- 新增送货方信息 --%>
<div class="modal fade" id="addRecevingModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增送货地址信息</h4>

            </div>
            <div class="modal-body">
                <div class="form-horizontal row" style="padding-right: 20px;">
                    <div class="form-group col-md-12 input-box-list">
                        <label for="form_distribute_input_name" class=" font12 input-box-list-title">收货客户名称：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="form_distribute_input_name" placeholder="收货客户名称" maxlength="20">
                        </div>
                    </div>
                    <div class="form-group col-md-12 input-box-list">
                        <label for="form_distribute_input_logistics" class=" font12 input-box-list-title">物流公司：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="form_distribute_input_logistics" placeholder="物流公司" maxlength="20">
                        </div>
                    </div>
                    <div class="form-group col-md-12 input-box-list">
                        <label for="form_distribute_input_site" class=" font12 input-box-list-title">到站地址：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="form_distribute_input_site" placeholder="到站地址" maxlength="10">
                        </div>
                    </div>
                    <div class="form-group col-md-12 input-box-list">
                        <label for="form_distribute_input_contacter" class=" font12 input-box-list-title">收货人姓名：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="form_distribute_input_contacter" placeholder="收货人姓名" maxlength="10">
                        </div>
                    </div>
                    <div class="form-group col-md-12 input-box-list">
                        <label for="form_distribute_input_mobile" class=" font12 input-box-list-title">收货人联系电话：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="form_distribute_input_mobile" placeholder="收货人联系电话" maxlength="14">
                        </div>
                    </div>
                    <div class="form-group col-md-12 input-box-list">
			            <label for="input1" class="font12 input-box-list-title">所属地区：</label>
			            <div class="input-box-list-value">
			            	<input type="hidden" id="provIdEle">
			                <input type="hidden" id="cityIdEle">
			                <input type="hidden" id="countyIdEle">
			                <select id="form_distribute_input_provId" class="form-control no-appearance area-select" title="provIdSelect">
			                    <option selected value="">-省份-</option>
			                    <option value="">-加载中...-</option>
			                </select>
			                <select id="form_distribute_input_cityId" class="form-control no-appearance area-select" title="cityIdSelect">
			                    <option value="">-地市-</option>
			                    <option value="">-请选择省份-</option>
			                </select>
			                <select id="form_distribute_input_countyId" class="form-control no-appearance area-select" title="countyIdSelect">
			                    <option value="">-区县-</option>
			                    <option value="">-请选择地市-</option>
			                </select>
			            </div>
			        </div>
                    <div class="form-group col-md-12 input-box-list">
                        <label for="form_distribute_input_address" class=" font12 input-box-list-title">收货地址：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="form_distribute_input_address" placeholder="收货地址" maxlength="50">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="confirmDistributionBtn">确认添加</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<%-- 修改送货方信息 --%>
<div class="modal fade" id="editRecevingModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改送货地址信息</h4>

            </div>
            <div class="modal-body">
                <div class="form-horizontal row" style="padding-right: 20px;">
                    <div class="form-group col-md-12 input-box-list">
                        <label for="form_distribute_input_name" class=" font12 input-box-list-title">收货客户名称：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="form_distribute_input_name" placeholder="收货客户名称" maxlength="20">
                        </div>
                    </div>
                    <div class="form-group col-md-12 input-box-list">
                        <label for="form_distribute_input_logistics" class=" font12 input-box-list-title">物流公司：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="form_distribute_input_logistics" placeholder="物流公司" maxlength="20">
                        </div>
                    </div>
                    <div class="form-group col-md-12 input-box-list">
                        <label for="form_distribute_input_site" class=" font12 input-box-list-title">到站地址：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="form_distribute_input_site" placeholder="到站地址" maxlength="10">
                        </div>
                    </div>
                    <div class="form-group col-md-12 input-box-list">
                        <label for="form_distribute_input_contacter" class=" font12 input-box-list-title">收货人姓名：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="form_distribute_input_contacter" placeholder="收货人姓名" maxlength="10">
                        </div>
                    </div>
                    <div class="form-group col-md-12 input-box-list">
                        <label for="form_distribute_input_mobile" class=" font12 input-box-list-title">收货人联系电话：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="form_distribute_input_mobile" placeholder="收货人联系电话" maxlength="14">
                        </div>
                    </div>
                    <div class="form-group col-md-12 input-box-list">
			            <label for="input1" class="font12 input-box-list-title">所属地区：</label>
			            <div class="input-box-list-value">
			            	<input type="hidden" id="provIdEle">
			                <input type="hidden" id="cityIdEle">
			                <input type="hidden" id="countyIdEle">
			                <select id="form_distribute_input_provId" class="form-control no-appearance area-select" title="provIdSelect">
			                    <option selected value="">-省份-</option>
			                    <option value="">-加载中...-</option>
			                </select>
			                <select id="form_distribute_input_cityId" class="form-control no-appearance area-select" title="cityIdSelect">
			                    <option value="">-地市-</option>
			                    <option value="">-请选择省份-</option>
			                </select>
			                <select id="form_distribute_input_countyId" class="form-control no-appearance area-select" title="countyIdSelect">
			                    <option value="">-区县-</option>
			                    <option value="">-请选择地市-</option>
			                </select>
			            </div>
			        </div>
                    <div class="form-group col-md-12 input-box-list">
                        <label for="form_distribute_input_address" class=" font12 input-box-list-title">收货地址：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="form_distribute_input_address" placeholder="收货地址" maxlength="50">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="confirmDisUpdateBtn">确认修改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<%-- 提示信息  --%>
<div class="modal fade" id="infoModal" tabindex="-1" role="dialog">
</div><!-- /.modal -->
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>客户建立档案<c:if test="${not empty custBase.id }">（修改）</c:if>
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li class="#">客户档案</li>
        <li class="active">客户建立档案</li>
    </ol>
</section>
<form id="saveForm" class="col-md-12" enctype="multipart/form-data" style=" min-height: 200px; padding:10px 0;" action="customer/save.html" method="post">
    <%-- block 1 --%>
    <div class="page-header" id="block-1">
        <h4 class="text-info"><strong>1.&nbsp;</strong>基础信息&nbsp;-&nbsp;
            <small>Basic Information.</small>
            <!-- <a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a> -->
        </h4>
    </div>
    <div class="form-horizontal row" style="margin: 0 0 20px 0;">
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input_name" class=" font12 input-box-list-title label-base">客户名称：</label>
            <div class="input-box-list-value">
                <input type="text" name="name" class="form-control" id="input_name" value="${custBase.name }" placeholder="客户名称">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input_abbrName" class=" font12 input-box-list-title label-base">客户简称：</label>
            <div class="input-box-list-value">
                <input type="text" name="abbrName" class="form-control" value="${custBase.abbrName }" id="input_abbrName" placeholder="客户简称">
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
                <input type="hidden" value="${custBase.pid }" id="merchpid">
                
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
            <label for="input_lpName" class=" font12 input-box-list-title label-base">法人名称：</label>
            <div class="input-box-list-value">
                <input type="text" name="lpName" value="${custBase.lpName }"  class="form-control" id="input_lpName" placeholder="法人名称">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input_lpNo" class=" font12 input-box-list-title label-base">法人身份证号：</label>
            <div class="input-box-list-value">
                <input type="text" name="lpNo" value="${custBase.lpNo }" class="form-control" id="input_lpNo" placeholder="法人身份证号">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input_contactName" class=" font12 input-box-list-title label-base">客户联系人：</label>
            <div class="input-box-list-value">
                <input type="text" name="contactName" value="${custBase.contactName }" class="form-control" id="input_contactName" placeholder="客户联系人">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input_contactTel" class=" font12 input-box-list-title label-base">业务电话：</label>
            <div class="input-box-list-value">
                <input type="text" name="contactTel" value="${custBase.contactTel }" class="form-control" id="input_contactTel" placeholder="业务电话">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input_businessLicense" class=" font12 input-box-list-title label-base">营业执照号：</label>
            <div class="input-box-list-value">
                <input type="text" name="businessLicense" value="${custBase.businessLicense }" class="form-control" id="input_businessLicense" placeholder="营业执照号">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">客户电话：</label>
            <div class="input-box-list-value">
                <input type="text" name="tel" value="${custBase.tel }" class="form-control" id="input_tel" placeholder="客户电话">
            </div>
        </div>
        <%-- <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">客户类型：</label>
            <div class="input-box-list-value">
                <select name="custType" class="form-control no-appearance" id="input-agent-type">
                	<!-- TODO 字典字段完成后修改字段设置默认选中值 -->
                	<c:forEach items="${dict.get('CUST_MERCH_TYPE') }" var="cust_type">
						<option value="${cust_type.chooseVal}" ${custBase.custType eq cust_type.chooseVal? 'selected':''}>${cust_type.showText}</option>
	                </c:forEach>
                </select>
            </div>
        </div> --%>
		<input type="hidden" value="3" name="custType">
        <div class="form-group col-md-8 col-sm-12 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">详细地址：</label>
            <div class="input-box-list-value">
                <input type="text" name="address" value="${custBase.address }" class="form-control" id="input_address" placeholder="详细地址">
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
        <%-- <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">最小起订量：</label>
            <div class="input-box-list-value">
                <div class="input-group">
		            <input type="text" name="minOrder" value="${(empty custBase || empty custBase.minOrder)?10:custBase.minOrder }" id="input_minOrder" class="form-control" placeholder="最小起订量">
		            <span class="input-group-addon">吨</span>
		        </div>
            </div>
        </div> --%>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">上级物流商：</label>
            <div class="input-box-list-value">
                <select name="pid" class="form-control no-appearance" id="pid">
                	
                </select>
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">付款方：</label>
            <div class="input-box-list-value">
                <select name="payer" class="form-control no-appearance" id="payer">
                	
                </select>
            </div>
        </div>
    </div>
    <%-- block 3 --%>
    <div class="page-header" id="block-3">
        <h4 class="text-info"><strong>2.&nbsp;</strong>送货地址信息&nbsp;-&nbsp;
            <small>Receiving Information.</small>
            <!-- <a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a> -->
            <a href="javascript:;" class=" text-info block-save-link" data-toggle="modal"
               data-target="#addRecevingModal"><i class="icon icon-plus"></i>&nbsp;&nbsp;新增送货地址信息</a>
        </h4>
    </div>
    <div class="row" style="margin: 0 0 20px 0;">
    	<c:forEach items="${custBase.distributions }" var="distribution" varStatus="status">
	    	<div class="col-md-4 col-sm-6" id="distribution_${status.index+1}">
	    		<input name="id" type="hidden" value="${distribution.id}">
	            <div class="bank-box">
	                <div class="page-header" style="padding: 5px 0; margin-bottom: 10px;">
	                    <h4 class="">
	                        <i class="icon icon-truck"></i>&nbsp;&nbsp;<input name="name" disabled="disabled" value="${distribution.name}" type="hidden"><span>${distribution.name}</span>的物流
	                        <a href="javascript:;" class="block-save-link" name="editDisBtn"><i class="icon icon-edit"></i>&nbsp;&nbsp;编辑</a>
	                        <a href="javascript:;" class="block-save-link" name="delDisBtn"><i class="icon icon-trash"></i>&nbsp;&nbsp;删除</a>
	                    </h4>
	                </div>
	                <div class="row font12" style="line-height: 24px;">
	                    <div class="col-md-4 col-sm-6 text-right">收货客户名称：</div>
	                    <div class="col-md-8 col-sm-6" style="padding-left: 0;"><input name="name" value="${distribution.name}" type="hidden"><span>${distribution.name}</span></div>
	                </div>
	                <div class="row font12" style="line-height: 24px;">
	                    <div class="col-md-4 col-sm-6 text-right">物流公司：</div>
	                    <div class="col-md-8 col-sm-6" style="padding-left: 0;"><input name="logistics" value="${distribution.logistics}" type="hidden"><span>${distribution.logistics}</span></div>
	                </div>
	                <div class="row font12" style="line-height: 24px;">
	                    <div class="col-md-4 col-sm-6 text-right">到站(火车专用)：</div>
	                    <div class="col-md-8 col-sm-6" style="padding-left: 0;"><input name="site" value="${distribution.site}" type="hidden"><span>${distribution.site}</span></div>
	                </div>
	                <div class="row font12" style="line-height: 24px;">
	                    <div class="col-md-4 col-sm-6 text-right">收货人姓名：</div>
	                    <div class="col-md-8 col-sm-6" style="padding-left: 0;"><input name="contacter" value="${distribution.contacter}" type="hidden"><span>${distribution.contacter}</span></div>
	                </div>
	                <div class="row font12" style="line-height: 24px;">
	                    <div class="col-md-4 col-sm-6 text-right">收货人联系电话：</div>
	                    <div class="col-md-8 col-sm-6" style="padding-left: 0;"><input name="mobile" value="${distribution.mobile}" type="hidden"><span>${distribution.mobile}</span></div>
	                </div>
	                <div class="row font12" style="line-height: 24px;">
			            <div class="col-md-4 col-sm-6 text-right">所属地区：</div>
			            <div class="input-box-list-value">
			                <input type="hidden" name="provId" value="${distribution.provId}">
			                <input type="hidden" name="cityId" value="${distribution.cityId}">
			                <input type="hidden" name="countyId" value="${distribution.countyId}">
			                <span name="provName">${distribution.provName}</span><span name="cityName">${distribution.cityName}</span><span name="countyName">${distribution.countyName}</span>
			            </div>
			        </div>
	                <div class="row font12" style="line-height: 24px;">
	                    <div class="col-md-4 col-sm-6 text-right">收货地址：</div>
	                    <div class="col-md-8 col-sm-6" style="padding-left: 0;"><input name="address" value="${distribution.address}" type="hidden"><span>${distribution.address}</span></div>
	                </div>
	            </div>
	        </div>
    	</c:forEach>
        
        <div class="col-md-4 col-sm-6" style="display:none;"  id="distribution_${custBase.distributions.size()+1 }">
            <div class="bank-box">
                <div class="page-header" style="padding: 5px 0; margin-bottom: 10px;">
                    <h4 class="">
                        <i class="icon icon-truck"></i>&nbsp;&nbsp;<input name="name" disabled="disabled" type="hidden">的物流
                        <a href="javascript:;" class="block-save-link" name="editDisBtn"><i class="icon icon-edit"></i>&nbsp;&nbsp;编辑</a>
                        <a href="javascript:;" class="block-save-link" name="delDisBtn"><i class="icon icon-trash"></i>&nbsp;&nbsp;删除</a>
                    </h4>
                </div>
                <div class="row font12" style="line-height: 24px;">
                    <div class="col-md-4 col-sm-6 text-right">收货客户名称：</div>
                    <div class="col-md-8 col-sm-6" style="padding-left: 0;"><input name="name" type="hidden"></div>
                </div>
                <div class="row font12" style="line-height: 24px;">
                    <div class="col-md-4 col-sm-6 text-right">物流公司：</div>
                    <div class="col-md-8 col-sm-6" style="padding-left: 0;"><input name="logistics" type="hidden"></div>
                </div>
                <div class="row font12" style="line-height: 24px;">
                    <div class="col-md-4 col-sm-6 text-right">到站(火车专用)：</div>
                    <div class="col-md-8 col-sm-6" style="padding-left: 0;"><input name="site" type="hidden"></div>
                </div>
                <div class="row font12" style="line-height: 24px;">
                    <div class="col-md-4 col-sm-6 text-right">收货人姓名：</div>
                    <div class="col-md-8 col-sm-6" style="padding-left: 0;"><input name="contacter" type="hidden"></div>
                </div>
                <div class="row font12" style="line-height: 24px;">
                    <div class="col-md-4 col-sm-6 text-right">收货人联系电话：</div>
                    <div class="col-md-8 col-sm-6" style="padding-left: 0;"><input name="mobile" type="hidden"></div>
                </div>
                <div class="row font12" style="line-height: 24px;">
		            <div class="col-md-4 col-sm-6 text-right">所属地区：</div>
		            <div class="input-box-list-value">
		                <input type="hidden" name="provId" >
		                <input type="hidden" name="cityId" >
		                <input type="hidden" name="countyId" >
		                <span name="provName">${distribution.provName}</span><span name="cityName">${distribution.cityName}</span><span name="countyName">${distribution.countyName}</span>
		            </div>
		        </div>
                <div class="row font12" style="line-height: 24px;">
                    <div class="col-md-4 col-sm-6 text-right">收货地址：</div>
                    <div class="col-md-8 col-sm-6" style="padding-left: 0;"><input name="address" type="hidden"></div>
                </div>
            </div>
        </div>
        <div class="col-md-4 col-sm-6">
            <div class="bank-box-add" data-toggle="modal" data-target="#addRecevingModal">
                <i class="icon icon-plus"></i>&nbsp;&nbsp;新增送货地址信息
                <input type="hidden" name="delDists" id="delDists">
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
                <input type="text" name="invoiceName" value="${custBase.invoiceName }" class="form-control" id="input_invoiceName" placeholder="开票名称">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title">税号：</label>
            <div class="input-box-list-value">
                <input type="text" name="invoiceTaxNum" value="${custBase.invoiceTaxNum }" class="form-control" id="input_invoiceTaxNum" placeholder="税号">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title">地址：</label>
            <div class="input-box-list-value">
                <input type="text" name="invoiceAddress" value="${custBase.invoiceAddress }" class="form-control" id="input_invoiceAddress" placeholder="地址">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title">账户：</label>
            <div class="input-box-list-value">
                <input type="text" name="invoiceAccount" value="${custBase.invoiceAccount }" class="form-control" id="input_invoiceAccount" placeholder="账户">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title">电话：</label>
            <div class="input-box-list-value">
                <input type="text" name="invoiceTel" value="${custBase.invoiceTel }" class="form-control" id="input_invoiceTel" placeholder="电话">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title">常用打款银行名称：</label>
            <div class="input-box-list-value">
                <input type="text" name="invoiceBankName" value="${custBase.invoiceBankName }" class="form-control" id="input_invoiceBankName" placeholder="常用打款银行名称">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title">常用打款账户名称：</label>
            <div class="input-box-list-value">
                <input type="text" name="invoiceAccountName" value="${custBase.invoiceAccountName }" class="form-control" id="input_invoiceAccountName" placeholder="常用打款账户名称">
            </div>
        </div>
    </div>
    <%-- block 3
    <div class="page-header" id="block-3">
        <h4 class="text-info"><strong>4.&nbsp;</strong>新开类型&nbsp;-&nbsp;
            <small>Receiving Information.</small>
            <!-- <a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a> -->
        </h4>
    </div>
    <div class="row" style="margin: 0 0 20px 0;">
        <div class="col-md-9 col-md-offset-1 col-sm-12">
            <table class="table table-bordered form-inline">
                <tr>
                    <td>
                        <div class="checkbox">
                        	<input type="hidden" value="${custBase.openingType }" id="openingTypeEle">
                            <input type="radio" name="openingType" value="1" class="form-control"> 新开
                        </div>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td rowspan="3">
                        <div class="checkbox">
                            <input type="radio" name="openingType" value="2" class="form-control"> 替代
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="label-control"> 被替代经销商名称：</label>
                            <input type="text" name="openingMerchant" value="${custBase.openingMerchant }" class="form-control" id="exampleInputName2"
                                   placeholder="">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="label-control"> 替代原因：</label>
                            <input type="text" name="openingReason" value="${custBase.openingReason }" class="form-control" id="exampleInputName2"
                                   placeholder="">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="label-control"> 原经销商关闭日期：</label>
                            <input type="text" name="openingCloseTs" value='<fmt:formatDate value="${custBase.openingCloseTs }" pattern="yyyyMMdd"/>' class="form-control" id="exampleInputName2"
                                   placeholder="格式yyyyMMdd">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td rowspan="3">
                        <div class="checkbox">
                            <input type="radio" name="openingType" value="3" class="form-control"> 升级
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="label-control"> 原上级经销商名称：</label>
                            <input type="text" name="openingMerchant" value="${custBase.openingMerchant }" class="form-control" id="exampleInputName2"
                                   placeholder="">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="label-control"> 与原上级经销商停止合作日期：</label>
                            <input type="text" name="openingCloseTs" value='<fmt:formatDate value="${custBase.openingCloseTs }" pattern="yyyyMMdd"/>' class="form-control" id="exampleInputName2"
                                   placeholder="格式yyyyMMdd">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="label-control"> 升级原因：</label>
                            <input type="text" name="openingReason" value="${custBase.openingReason }" name="openingReason" class="form-control" id="exampleInputName2"
                                   placeholder="">
                        </div>
                    </td>
                </tr>
            </table>
        </div>

    </div>
     --%>
    <%-- block 3
    <div class="page-header" id="block-3">
        <h4 class="text-info"><strong>5.&nbsp;</strong>我司产品拓展计划&nbsp;-&nbsp;
            <small>Receiving Information.</small>
            <!-- <a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a> -->
        </h4>
    </div>
    <div class="row form-horizontal" style="margin: 0 0 20px 0;">
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">计划拓展区域：</label>
            <div class="input-box-list-value">
                <input type="text" name="planArea" value="${custBase.planArea }" class="form-control" id="input_planArea"
                       placeholder="省(直辖市)/市(州)/县(区)">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">计划拓展品牌：</label>
            <div class="input-box-list-value">
                <input type="text" name="planBrand" value="${custBase.planBrand }" class="form-control" id="input_planBrand"
                       placeholder="新繁/新飞/宴侑/云里红/望红/醇香源/哈哈厨房">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">计划拓展品类：</label>
            <div class="input-box-list-value">
                <input type="text" name="planCategory" value="${custBase.planCategory }" class="form-control" id="input_planCategory" placeholder="泡菜/全料/豆瓣/甜面酱">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">KA渠道：</label>
            <div class="input-box-list-value">
                <input type="text" name="expandKaPlace" value="${custBase.expandKaPlace }" class="form-control" id="input_expandKaPlace" placeholder="">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">BC渠道：</label>
            <div class="input-box-list-value">
                <input type="text" name="expandBcPlace" value="${custBase.expandBcPlace }" class="form-control" id="input_expandBcPlace" placeholder="">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">流通（农贸/批发）渠道：</label>
            <div class="input-box-list-value">
                <input type="text" name="expandCirculatePlace" value="${custBase.expandCirculatePlace }" class="form-control" id="input_expandCirculatePlace" placeholder="">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">工厂：</label>
            <div class="input-box-list-value">
                <input type="text" name="expandFactoryPlace" value="${custBase.expandFactoryPlace }" class="form-control" id="input_expandFactoryPlace" placeholder="">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">学校渠道：</label>
            <div class="input-box-list-value">
                <input type="text" name="expandSchoolPlace" value="${custBase.expandSchoolPlace }" class="form-control" id="input_expandSchoolPlace" placeholder="">
            </div>
        </div>

        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">零售网点数（中型以下门店）：</label>
            <div class="input-box-list-value">
                <input type="text" name="expandRetailPlace" value="${custBase.expandRetailPlace }" class="form-control" id="input_expandRetailPlace" placeholder="">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">该区域人口数：</label>
            <div class="input-box-list-value">
                <input type="text" name="expandAreaPeoples" value="${custBase.expandAreaPeoples }" class="form-control" id="input_expandAreaPeoples" placeholder="">
            </div>
        </div>

        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">该区域市场体量：</label>
            <div class="input-box-list-value">
                <input type="text" name="expandAreaVolume" value="${custBase.expandAreaVolume }" class="form-control" id="input_expandAreaVolume" placeholder="">
            </div>
        </div>

        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">计划生意体量：</label>
            <div class="input-box-list-value">
                <input type="text" name="expandBusinesVolume" value="${custBase.expandBusinesVolume }" class="form-control" id="input_expandBusinesVolume" placeholder="">
            </div>
        </div>

        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section" style="width:233px;">该客户将投入到我司产品的每月运营金额：</label>
            <div class="input-box-list-value">
                <input type="text" name="expandSpentMamt" value="${custBase.expandSpentMamt }" class="form-control" id="input_expandSpentMamt" placeholder="">
            </div>
        </div>

        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section" style="width:210px">该客户将投入到我司产品的首单金额：</label>
            <div class="input-box-list-value">
                <input type="text" name="expandSpentFamt" value="${custBase.expandSpentFamt }" class="form-control" id="input_expandSpentFamt" placeholder="">
            </div>
        </div>

    </div>
--%>
    <%-- block 3
    <div class="page-header" id="block-3">
        <h4 class="text-info"><strong>6.&nbsp;</strong>企业背景&nbsp;-&nbsp;
            <small>Receiving Information.</small>
            <!-- <a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a> -->
        </h4>
    </div>
    <div class="row " style="margin: 0 0 20px 0;">
        <div class="col-md-9 col-md-offset-1 col-sm-12">
            <table class="table table-bordered  form-inline">
                <tr>
                    <td rowspan="2">企业概况</td>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="exampleInputName2">年销售额 （与厂家/供应商直接业务）</label>
                            <input type="text" class="form-control" id="exampleInputName2" name="contextSalesYear" value="${custBase.contextSalesYear }" placeholder="XX万元">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="exampleInputName2">各厂家/供应商 生意占比</label>
                            <input type="text" class="form-control"  name="contextBusinessRatio" value="${custBase.contextBusinessRatio }" id="exampleInputName2"
                                   placeholder="XX25%、XX17%、XX13%、XX7%、……">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td rowspan="2">资金能力</td>
                    <td>
                        <div class="form-group"> 
                            <label for="exampleInputName2" class="exampleInputName2">年度总投资额</label>
                            <input type="text" class="form-control" id="exampleInputName2" name="contextInvestment" value="${custBase.contextInvestment }" placeholder="1000万">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="exampleInputName2">截止目前，每月营运资金</label>
                            <input type="text" class="form-control" name="contextOperateCapital" value="${custBase.contextOperateCapital }" id="exampleInputName2" placeholder="500万">
                        </div>
                    </td>
                </tr>

                <tr>
                    <td rowspan="7">客户网络</td>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="exampleInputName2">ＫＡ数量</label>
                            <input type="text" class="form-control" name="contextKaNum" value="${custBase.contextKaNum }" id="exampleInputName2" placeholder=" ">
                        </div>
                    </td>
                </tr>

                <tr>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="exampleInputName2">ＢＣ数量</label>
                            <input type="text" class="form-control" name="contextBcNum" value="${custBase.contextBcNum }" id="exampleInputName2" placeholder=" ">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="exampleInputName2">批发商数量</label>
                            <input type="text" class="form-control" name="contextWholesalersNum" value="${custBase.contextWholesalersNum }" id="exampleInputName2" placeholder=" ">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="exampleInputName2">零售网点数</label>
                            <input type="text" class="form-control" name="contextRetailNum" value="${custBase.contextRetailNum }" id="exampleInputName2" placeholder=" ">
                        </div>
                    </td>
                </tr>

                <tr>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="exampleInputName2">农贸网点数</label>
                            <input type="text" class="form-control" name="contextFarmersNum" value="${custBase.contextFarmersNum }" id="exampleInputName2" placeholder=" ">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="exampleInputName2">餐饮网点数</label>
                            <input type="text" class="form-control" name="contextRestaurantNum" value="${custBase.contextRestaurantNum }" id="exampleInputName2" placeholder=" ">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="exampleInputName2">其他网点数（请详细描述）</label>
                            <input type="text" class="form-control" name="contextOthersNum" value="${custBase.contextOthersNum }" id="exampleInputName2" placeholder=" ">
                        </div>
                    </td>
                </tr>

                <tr>
                    <td rowspan="2">销售队伍</td>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="exampleInputName2">销售人员数量</label>
                            <input type="text" class="form-control" name="contextSalesmanNum" value="${custBase.contextSalesmanNum }" id="exampleInputName2" placeholder=" ">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="exampleInputName2">后勤人员数量</label>
                            <input type="text" class="form-control" name="contextLogisticsNum" value="${custBase.contextLogisticsNum }" id="exampleInputName2" placeholder=" ">
                        </div>
                    </td>
                </tr>


                <tr>
                    <td rowspan="3">基本设施</td>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="exampleInputName2">卡车数量（写明吨数）</label>
                            <input type="text" class="form-control" name="contextTruckNum" value="${custBase.contextTruckNum }" id="exampleInputName2" placeholder=" ">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="exampleInputName2">货车数量（写明吨数）</label>
                            <input type="text" class="form-control" name="contextLorryNum" value="${custBase.contextLorryNum }" id="exampleInputName2" placeholder=" ">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <label for="exampleInputName2" class="exampleInputName2">仓库面积 （现有仓库面积，已使用面积）</label>
                            <input type="text" class="form-control" name="contextDepot" value="${custBase.contextDepot }" id="exampleInputName2" placeholder=" ">
                        </div>
                    </td>
                </tr>

            </table>
        </div>
    </div>
     --%>
    <%-- block 5 --%>
    <div class="page-header" id="block-5">
        <h4 class="text-info"><strong>4.&nbsp;</strong>附件&nbsp;-&nbsp;
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
        <button class="btn btn-primary btn-long" type="button" id="submitBtn"><i class="icon icon-check"></i>&nbsp;&nbsp;提交</button>
    </div>
    <input type="hidden" name="id" value="${custBase.id }">
    <input type="hidden" id="msg" value="${msg }">
    <token:token/>
</form>


<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<%--<script type="text/javascript" src="static/wizard/jquery.bootstrap.wizard.min.js"></script>--%>
<script type="text/javascript" src="static/wizards/js/bwizard.js"></script>
<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="static/js/customer/index.js"></script>
<script type="text/javascript">
	$('#pid').chosen({
		no_results_text : "没有找到",
		placeholder_text : "请选择客户信息...",
		search_contains : true,
		disable_search_threshold : 10
	});
	$('#payer').chosen({
		no_results_text : "没有找到",
		placeholder_text : "请选择客户信息...",
		search_contains : true,
		disable_search_threshold : 10
	});
	$("#pid").change(loadPayer);

	var msg = $("#msg").val();
	var pid = $("#merchpid").val();
	var payer = '${custBase.payer}';
	if(typeof msg != 'undefined' && $.trim(msg) != ''){
		if(msg.indexOf("Failed to convert property value of type 'java.lang.String' to required type 'java.util.Date'") != -1){
			msg="错误的日期格式！"
		}
		$.messager.alert('信息',msg);
	}
	
	// 初始化物流商
	$.get("customer/list?custType=8&type=1", function(res){
    	var html = "";
    	if(res && res.rows){
    		$.each(res.rows, function(i, val){
    			var appendSelect = "";
    			if((""+val.id)==pid){
    				appendSelect = "selected"
    			}
    			html += '<option value="'+val.id+'" '+appendSelect+'>'+val.name+'</option>';
    		});	
    		$("#pid").html(html);
    		$("#pid").trigger("chosen:updated");
    		// 初始化付款方
    		loadPayer();
    	}
    });
	
	function loadPayer(){
		var tpid = $("#pid").val();
		$.get("customer/list?custType=3&pid="+tpid, function(res){
	    	var html = "";
	    	if(res && res.rows){
	    		if(!payer){
	    			payer = '${custBase.id}'
	    		}
	    		if(!payer){
	    			html = '<option value="">无</option>'
	    		}
	    		var hasChoose = false;
	    		$.each(res.rows, function(i, val){
	    			var appendSelect = "";
	    			if((""+val.id)==payer){
	    				appendSelect = "selected"
	    				hasChoose = true;
	    			}
	    			html += '<option value="'+val.id+'" '+appendSelect+'>'+val.name+'</option>';
	    		});
	    		if(payer && hasChoose==false){
	    			html = '<option value="">无</option>'+html
	    		}
	    		$("#payer").html(html);
	    		$("#payer").trigger("chosen:updated");
	    	}
	    });
	}
	
</script>
</body>
</html>
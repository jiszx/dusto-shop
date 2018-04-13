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

        .area-select-last {
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
        	border: solid 1px;
    		border-radius: 5px;
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
                <h4 class="modal-title">新增到站信息</h4>

            </div>
            <div class="modal-body">
                <div class="form-horizontal row" style="padding-right: 20px;">
                    <div class="form-group col-md-12 input-box-list">
                        <label for="form_distribute_input_name" class=" font12 input-box-list-title">收货人名称：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="form_distribute_input_name" placeholder="收货人名称" maxlength="20">
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
                <h4 class="modal-title">修改到站信息</h4>

            </div>
            <div class="modal-body">
                <div class="form-horizontal row" style="padding-right: 20px;">
                    <div class="form-group col-md-12 input-box-list">
                        <label for="form_distribute_input_name" class=" font12 input-box-list-title">收货人名称：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="form_distribute_input_name" placeholder="收货人名称" maxlength="20">
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
    <h1>送达方建立档案<c:if test="${not empty custBase.id }">（修改）</c:if>
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li class="#">送达方档案</li>
        <li class="active">送达方建立档案</li>
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
            <label for="input_name" class=" font12 input-box-list-title label-base">售达方选择：</label>
            <input type="hidden" value="${custBase.pid }" id="customerHiddenInput">
            <div class="input-box-list-value">
                <select class="form-control" name="pid" data-placeholder="请选择售达方信息..." id="customerSelection">
				</select>
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input_name" class=" font12 input-box-list-title label-base">送达方名称：</label>
            <div class="input-box-list-value">
                <input type="text" name="name" class="form-control" id="input_name" value="${custBase.name }" placeholder="送达方名称">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input_abbrName" class=" font12 input-box-list-title label-base">送达方简称：</label>
            <div class="input-box-list-value">
                <input type="text" name="abbrName" class="form-control" value="${custBase.abbrName }" id="input_abbrName" placeholder="送达方简称">
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
                <select name="countyId" class="form-control no-appearance area-select-last" id="countyIdSelect">
                    <option value="">-区县-</option>
                    <option value="">-请选择地市-</option>
                </select>
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">送达方电话：</label>
            <div class="input-box-list-value">
                <input type="text" name="tel" value="${custBase.tel }" class="form-control" id="input_tel" placeholder="送达方电话">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input_contactName" class=" font12 input-box-list-title label-base">送达方联系人：</label>
            <div class="input-box-list-value">
                <input type="text" name="contactName" value="${custBase.contactName }" class="form-control" id="input_contactName" placeholder="客户联系人">
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input_address" class=" font12 input-box-list-title label-base">详细地址：</label>
            <div class="input-box-list-value">
                <input type="text" name="address" value="${custBase.address }" class="form-control" id="input_address" placeholder="详细地址">
            </div>
        </div>
    </div>
    <%-- block 3 --%>
    <div class="page-header" id="block-3">
        <h4 class="text-info"><strong>2.&nbsp;</strong>到站信息&nbsp;-&nbsp;
            <small>Receiving Information.</small>
            <!-- <a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a> -->
            <a href="javascript:;" class=" text-info block-save-link" data-toggle="modal"
               data-target="#addRecevingModal"><i class="icon icon-plus"></i>&nbsp;&nbsp;新增到站信息</a>
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
	                    <div class="col-md-4 col-sm-6 text-right">收货人名称：</div>
	                    <div class="col-md-8 col-sm-6" style="padding-left: 0;"><input name="name" value="${distribution.name}" type="hidden"><span>${distribution.name}</span></div>
	                </div>
	                <div class="row font12" style="line-height: 24px;">
	                    <div class="col-md-4 col-sm-6 text-right">物流公司：</div>
	                    <div class="col-md-8 col-sm-6" style="padding-left: 0;"><input name="logistics" value="${distribution.logistics}" type="hidden"><span>${distribution.logistics}</span></div>
	                </div>
	                <div class="row font12" style="line-height: 24px;">
	                    <div class="col-md-4 col-sm-6 text-right">到站地址：</div>
	                    <div class="col-md-8 col-sm-6" style="padding-left: 0;"><input name="site" value="${distribution.site}" type="hidden"><span>${distribution.site}</span></div>
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
                    <div class="col-md-4 col-sm-6 text-right">收货人名称：</div>
                    <div class="col-md-8 col-sm-6" style="padding-left: 0;"><input name="name" type="hidden"></div>
                </div>
                <div class="row font12" style="line-height: 24px;">
                    <div class="col-md-4 col-sm-6 text-right">物流公司：</div>
                    <div class="col-md-8 col-sm-6" style="padding-left: 0;"><input name="logistics" type="hidden"></div>
                </div>
                <div class="row font12" style="line-height: 24px;">
                    <div class="col-md-4 col-sm-6 text-right">到站地址：</div>
                    <div class="col-md-8 col-sm-6" style="padding-left: 0;"><input name="site" type="hidden"></div>
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
                <i class="icon icon-plus"></i>&nbsp;&nbsp;新增到站信息
                <input type="hidden" name="delDists" id="delDists">
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
<script type="text/javascript" src="static/js/customer/index4reveiver.js"></script>
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
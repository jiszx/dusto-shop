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
    <title>管理系统</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link rel="stylesheet" href="static/wizards/css/bwizard.min.css">
    <style>
        body .cn {
            font-family: "微软雅黑";
        }
			`																																																																																																																																																						`																																																1QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ
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
            width: 100px;
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
            height: 190px;
            padding: 20px 15px;
            max-height: 190px;
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
    </style>
</head>
<body class="skin-blue">
<jsp:include page="/common/header.jsp"></jsp:include>
<div class="wrapper row-offcanvas row-offcanvas-left">
    <jsp:include page="/common/leftMenu.jsp"></jsp:include>
    <!-- Right side column. Contains the navbar and content of the page -->
    <aside class="right-side">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>流程审批
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
                <li class="active">流程审批</li>
            </ol>
        </section>

        <%-- <ul class="nav nav-pills" id="stepLink2">
             <li role="presentation" class="active"><a href="javascript:;">基本资料</a></li>
             <li role="presentation"><a href="javascript:;">联系人信息</a></li>
             <li role="presentation"><a href="javascript:;">送达方信息</a></li>
             <li role="presentation"><a href="javascript:;">开票信息</a></li>
         </ul>--%>
        <!-- Main content -->
        <section class="content" style="padding-top: 10px; padding-bottom: 20px;">
            <%-- Edit by wuxun 2016-07-11 12:59 --%>
            <div class="row" style="padding:0 15px;">
                <%-- left step link
                <div class="col-md-12" style="padding-left:0; padding-bottom: 5px;">
                    <ul class="nav nav-pills" id="stepLink">
                        <li role="presentation" class="active"><a href="javascript:;">基本资料</a></li>
                        <li role="presentation"><a href="javascript:;">联系人信息</a></li>
                        <li role="presentation"><a href="javascript:;">送达方信息</a></li>
                        <li role="presentation"><a href="javascript:;">开票信息</a></li>
                    </ul>
                </div>
                --%>
                <%-- right step content --%>
                <form class="col-md-12" style=" min-height: 200px; padding:10px 0;">
                    <%-- block 1 --%>
                    <div class="page-header" id="block-1">
                        <h4 class="text-info"><strong>1.&nbsp;</strong>基本资料&nbsp;-&nbsp;
                            <small>Basic Information.</small>
                        </h4>
                    </div>
                    <div class="form-horizontal row" style="margin: 0 0 20px 0;">
                        <div class="form-group col-md-4 input-box-list">
                            <label for="input1" class=" font12 input-box-list-title">客户名称：</label>
                            <div class="input-box-list-value">
                                <label class="form-control">益盐堂客户</label>
                            </div>
                        </div>
                        <div class="form-group col-md-4 input-box-list">
                            <label for="input1" class=" font12 input-box-list-title">客户简称：</label>
                            <div class="input-box-list-value">
                                <label class="form-control">大客户</label>
                            </div>
                        </div>
                        <div class="form-group col-md-4 input-box-list">
                            <label for="input1" class=" font12 input-box-list-title">所属地区：</label>
                            <div class="input-box-list-value">
                                <label class="form-control">成都市</label>
                            </div>
                        </div>
                        <div class="form-group col-md-4 input-box-list">
                            <label for="input1" class=" font12 input-box-list-title">渠道类型：</label>
                            <div class="input-box-list-value">
                                <label class="form-control">现代渠道</label>
                            </div>
                        </div>
                        <div class="form-group col-md-4 input-box-list">
                            <label for="input1" class=" font12 input-box-list-title">法人名称：</label>
                            <div class="input-box-list-value">
                                <label class="form-control">隔壁王老五</label>
                            </div>
                        </div>
                        <div class="form-group col-md-4 input-box-list">
                            <label for="input1" class=" font12 input-box-list-title">法人证件：</label>
                            <div class="input-box-list-value">
                                <label class="form-control">666666666666666666666</label>
                            </div>
                        </div>
                        <div class="form-group col-md-4 input-box-list">
                            <label for="input1" class=" font12 input-box-list-title">营业执照：</label>
                            <div class="input-box-list-value">
                                <label class="form-control">6666666666666666</label>
                            </div>
                        </div>
                        <div class="form-group col-md-4 input-box-list">
                            <label for="input1" class=" font12 input-box-list-title">客户电话：</label>
                            <div class="input-box-list-value">
                                <label class="form-control">88888888</label>
                            </div>
                        </div>
                        <div class="form-group col-md-4 input-box-list">
                            <label for="input1" class=" font12 input-box-list-title">注册地址：</label>
                            <div class="input-box-list-value">
                                <label class="form-control">成都市XX区XX大厦XX层XX号</label>
                            </div>
                        </div>
                        <div class="form-group col-md-4 input-box-list">
                            <label for="input1" class=" font12 input-box-list-title">增值税登记号：</label>
                            <div class="input-box-list-value">
                                <label class="form-control">123123123123132123123</label>
                            </div>
                        </div>
                        <div class="form-group col-md-8 input-box-list">
                            <label for="input1" class=" font12 input-box-list-title">详细地址：</label>
                            <div class="input-box-list-value">
                                <label class="form-control">成都市XX区XX大厦XX层XX号</label>
                            </div>
                        </div>
                    </div>
                    <%-- block 2 --%>
                    <div class="page-header col-md-4" id="block-2">
                        <h4 class="text-info"><strong>2.&nbsp;</strong>联系人信息&nbsp;-&nbsp;
                            <small>Contact Information.</small>
                        </h4>
                    </div>
                    <div class="page-header col-md-4" id="block-3">
                        <h4 class="text-info"><strong>3.&nbsp;</strong>送达方信息&nbsp;-&nbsp;
                            <small>Receiving Information.</small>
                        </h4>
                    </div>
                    <div class="page-header col-md-4" id="block-4">
                        <h4 class="text-info"><strong>4.&nbsp;</strong>开票信息&nbsp;-&nbsp;
                            <small>Receipt Information.</small>
                        </h4>
                    </div>

                    <div class="row">
                        <div class="col-md-4">
                            <div class="contact-box">
                                <div class="page-header" style="padding: 5px 0; margin-bottom: 10px;">
                                    <h4><i class="icon icon-user"></i>&nbsp;&nbsp;猪八戒
                                    </h4>
                                </div>
                                <div class="row font12" style="line-height: 24px;">
                                    <div class="col-md-3 text-right">邮箱：</div>
                                    <div class="col-md-9" style="padding-left: 0;">zhubajie@hotmailcom</div>
                                </div>
                                <div class="row font12" style="line-height: 24px;">
                                    <div class="col-md-3 text-right">手机：</div>
                                    <div class="col-md-9" style="padding-left: 0;">13855650210</div>
                                </div>
                                <div class="row font12" style="line-height: 24px;">
                                    <div class="col-md-3 text-right">座机：</div>
                                    <div class="col-md-9" style="padding-left: 0;">028-08563321</div>
                                </div>
                                <div class="row font12" style="line-height: 24px;">
                                    <div class="col-md-3 text-right">传真：</div>
                                    <div class="col-md-9" style="padding-left: 0;">028-85021152</div>
                                </div>
                                <div class="row font12" style="line-height: 24px;">
                                    <div class="col-md-3 text-right">微信：</div>
                                    <div class="col-md-9" style="padding-left: 0;">zhuyiqun123</div>
                                </div>
                                <div class="row font12" style="line-height: 24px;">
                                    <div class="col-md-3 text-right">QQ：</div>
                                    <div class="col-md-9" style="padding-left: 0;">2566325458</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="bank-box">
                                <div class="page-header" style="padding: 5px 0; margin-bottom: 10px;">
                                    <h4 class="">
                                        <i class="icon icon-truck"></i>&nbsp;&nbsp;送达方信息
                                    </h4>
                                </div>
                                <div class="row font12" style="line-height: 24px;">
                                    <div class="col-md-4 text-right">物流：</div>
                                    <div class="col-md-8" style="padding-left: 0;">顺通快递</div>
                                </div>
                                <div class="row font12" style="line-height: 24px;">
                                    <div class="col-md-4 text-right">到站：</div>
                                    <div class="col-md-8" style="padding-left: 0;">海南省三亚市</div>
                                </div>
                                <div class="row font12" style="line-height: 24px;">
                                    <div class="col-md-4 text-right">联系人：</div>
                                    <div class="col-md-8" style="padding-left: 0;">赵淑珍</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="bank-box">
                                <div class="page-header" style="padding: 5px 0; margin-bottom: 10px;">
                                    <h4 class="">
                                        <i class="icon icon-credit-card"></i>&nbsp;&nbsp;622433 201334 253648
                                    </h4>
                                </div>
                                <div class="row font12" style="line-height: 24px;">
                                    <div class="col-md-4 text-right">开户行：</div>
                                    <div class="col-md-8" style="padding-left: 0;">中国建设银行成都分行</div>
                                </div>
                                <div class="row font12" style="line-height: 24px;">
                                    <div class="col-md-4 text-right">开户名：</div>
                                    <div class="col-md-8" style="padding-left: 0;">赵淑珍</div>
                                </div>
                                <div class="row font12" style="line-height: 24px;">
                                    <div class="col-md-4 text-right">注册地址：</div>
                                    <div class="col-md-8" style="padding-left: 0;">四川省成都市武侯区</div>
                                </div>
                                <div class="row font12" style="line-height: 24px;">
                                    <div class="col-md-4 text-right">注册电话：</div>
                                    <div class="col-md-8" style="padding-left: 0;">028-85021152</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%-- block 3 --%>
                    <div class="page-header" id="block-3">
                        <h4 class="text-info"><strong>3.&nbsp;</strong>审批意见&nbsp;-&nbsp;
                            <small>Receiving Information.</small>
                        </h4>
                    </div>
                    <div class="row" style="margin: 0 0 20px 0;">
                        <div class="col-md-12">
                            <div class="bank-box">
                                <div class="row font12" style="line-height: 48px;padding-top:20px;">
                                    <div class="col-md-2 text-right">审批意见：</div>
                                    <div class="col-md-8" style="padding-left: 0;">
                                        <textarea class="form-control"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%-- submit button --%>
                    <div class="text-center" style="padding-top: 30px;border-top:1px solid rgba(0,0,0,.15);">
                        <button class="btn btn-primary"  type="button"><i
                                class="icon icon-save"></i>&nbsp;&nbsp;通过
                        </button>
                        <button class="btn btn-danger" type="submit"><i class="icon icon-check"></i>&nbsp;&nbsp;驳回
                        </button>
                    </div>
                </form>
            </div>
            <%--<div class="row">--%>

            <%--<div id="wizard" class="col-md-12">--%>
            <%--<ol>--%>
            <%--<li>客户基本信息</li>--%>
            <%--<li>客户联系人信息</li>--%>
            <%--<li>客户送达方信息</li>--%>
            <%--<li>客户开票信息</li>--%>
            <%--</ol>--%>
            <%--<div>--%>
            <%--<form class="form-horizontal">--%>
            <%--<div class="form-group col-sm-6">--%>
            <%--<label for="merchName" class="col-sm-2 control-label">客户名称</label>--%>
            <%--<div class="col-sm-8">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-2"><span class="text-warning">*</span></div>--%>
            <%--</div>--%>

            <%--<div class="form-group col-sm-6">--%>
            <%--<label for="merchName" class="col-sm-2 control-label">客户简称</label>--%>
            <%--<div class="col-sm-8">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-2"><span class="text-warning">*</span></div>--%>
            <%--</div>--%>

            <%--<div class="form-group col-sm-6">--%>
            <%--<label for="merchName" class="col-sm-2 control-label">所属省市区</label>--%>
            <%--<div class="col-sm-8">--%>
            <%--<select class="form-control col-sm-3">--%>
            <%--<option>省</option>--%>
            <%--</select>--%>
            <%--</div>--%>
            <%--</div>--%>

            <%--<div class="form-group col-sm-6">--%>
            <%--<label for="merchName" class="col-sm-2 control-label">所属市</label>--%>
            <%--<div class="col-sm-8">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-2"><span class="text-warning">*</span></div>--%>
            <%--</div>--%>

            <%--<div class="form-group col-sm-6">--%>
            <%--<label for="merchName" class="col-sm-2 control-label">商户等级</label>--%>
            <%--<div class="col-sm-8">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-2"><span class="text-warning">*</span></div>--%>
            <%--</div>--%>


            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label"></label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1"><span class="text-warning">*</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">详细地址</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1"><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">法人</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1"><span class="text-warning">*</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">法人证件号</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1"><span class="text-warning">*</span></div>--%>
            <%--</div>--%>

            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">营业执照</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1"><span class="text-warning">*</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">公司电话</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1"><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">渠道</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<select class="form-control">--%>
            <%--<option>现代渠道</option>--%>
            <%--<option>传统渠道</option>--%>
            <%--<option>批发渠道</option>--%>
            <%--<option>特通渠道</option>--%>
            <%--</select>--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1"><span class="text-warning">*</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">注册地址</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1"><span class="text-warning">*</span></div>--%>
            <%--</div>--%>

            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">描述说明</label>--%>
            <%--<div class="col-sm-10">--%>
            <%--<textarea class="form-control" id="merchName"></textarea>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</form>--%>
            <%--</div>--%>
            <%--<div>1sdfsdf</div>--%>
            <%--<div>sdsdff</div>--%>
            <%--<div>sdf1</div>--%>
            <%--</div>--%>
            <%--<!----%>
            <%--<div id="merchAddWizard" class="col-sm-12">--%>
            <%--<div class="navbar" style="margin-bottom: 0px;">--%>
            <%--<div class="navbar-inner">--%>
            <%--<div class="container">--%>
            <%--<ul>--%>
            <%--<li><a href="#tab1" data-toggle="tab">客户基本信息</a></li>--%>
            <%--<li><a href="#tab2" data-toggle="tab">客户联系人信息</a></li>--%>
            <%--<li><a href="#tab3" data-toggle="tab">客户送达信息</a></li>--%>
            <%--&lt;%&ndash;<li><a href="#tab4" data-toggle="tab">客户SKU信息</a></li>&ndash;%&gt;--%>
            <%--<li><a href="#tab5" data-toggle="tab">开票信息</a></li>--%>
            <%--</ul>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--<div class="tab-content well well-sm" style="margin-bottom: 0px;">--%>
            <%--<div class="tab-pane" id="tab1">--%>
            <%--<form class="form-horizontal">--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">客户名称</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">客户简称</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">所属省</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">所属市</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">商户等级</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">详细地址</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">法人</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">法人证件号</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>

            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">营业执照</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">公司电话</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">渠道</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<select class="form-control">--%>
            <%--<option>现代渠道</option>--%>
            <%--<option>传统渠道</option>--%>
            <%--<option>批发渠道</option>--%>
            <%--<option>特通渠道</option>--%>
            <%--</select>--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">注册地址</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>

            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">描述说明</label>--%>
            <%--<div class="col-sm-10">--%>
            <%--<textarea class="form-control" id="merchName"></textarea>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</form>--%>

            <%--</div>--%>
            <%--<div class="tab-pane" id="tab2">--%>
            <%--<form class="form-horizontal">--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">联系人</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">电话</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">手机</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">微信</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">邮箱</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">QQ</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">传真</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">备注</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group text-center">--%>
            <%--<button class="btn btn-primary">继续添加</button>--%>
            <%--</div>--%>
            <%--<div class="form-group col-sm-12">--%>
            <%--<table class="table table-bordered">--%>
            <%--<tr>--%>
            <%--<th>联系人</th>--%>
            <%--<th>电话</th>--%>
            <%--<th>QQ</th>--%>
            <%--<th>邮箱</th>--%>
            <%--<th>微信</th>--%>
            <%--<th>传真</th>--%>
            <%--</tr>--%>
            <%--</table>--%>
            <%--</div>--%>
            <%--</form>--%>

            <%--</div>--%>
            <%--<div class="tab-pane" id="tab3">--%>
            <%--<form class="form-horizontal">--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">送达方</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="物流/到站/收货人">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group text-center">--%>
            <%--<button class="btn btn-primary">继续添加</button>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<table class="table table-bordered">--%>
            <%--<tr>--%>
            <%--<th>送达方</th>--%>
            <%--</tr>--%>
            <%--</table>--%>
            <%--</div>--%>

            <%--</form>--%>
            <%--</div>--%>
            <%--<div class="tab-pane" id="tab4">--%>
            <%--<form class="form-horizontal">--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">店招名称</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">POS类型</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">详细地址</label>--%>
            <%--<div class="col-sm-10">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">通讯类型</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">接入编号</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">联系人员</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">联系电话</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">营业起始</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">营业结束</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--</form>--%>
            <%--</div>--%>
            <%--<div class="tab-pane" id="tab5">--%>
            <%--<form class="form-horizontal">--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">客户类型</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">信息</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">增值税号</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">开户行</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">信息</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">账号</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">账户</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">一般纳税人资格照片</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">信息</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">客户登记</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
            <%--<label for="merchName" class="col-sm-1 control-label">店面照片</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">信息</span></div>--%>
            <%--<label for="merchName" class="col-sm-1 control-label">店面照片</label>--%>
            <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="merchName"--%>
            <%--placeholder="商户名称">--%>
            <%--</div>--%>
            <%--<div class="help-block col-sm-1" ><span class="text-warning">*</span></div>--%>
            <%--</div>--%>
            <%--<div class="form-group text-center">--%>
            <%--<button class="btn btn-primary">继续添加</button>--%>
            <%--</div>--%>
            <%--<div class="form-group col-sm-12">--%>
            <%--<table class="table table-bordered">--%>
            <%--<tr>--%>
            <%--<th>客户</th>--%>
            <%--<th>银行</th>--%>
            <%--<th>账户</th>--%>
            <%--<th>账号</th>--%>
            <%--<th>增值税</th>--%>
            <%--</tr>--%>
            <%--</table>--%>
            <%--</div>--%>
            <%--</form>--%>
            <%--</div>--%>
            <%--<ul class="pager wizard" style="margin-bottom: 0px;">--%>
            <%--<li class="previous"><a href="javascript:void(0);">上一步</a></li>--%>
            <%--<li class="next"><a href="javascript:void(0);">下一步</a></li>--%>
            <%--</ul>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%---->--%>
            <%--</div>--%>
        </section><!-- /.content -->

    </aside><!-- /.right-side -->
</div><!-- ./wrapper -->
<!-- Modal -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<%--<script type="text/javascript" src="static/wizard/jquery.bootstrap.wizard.min.js"></script>--%>
<script type="text/javascript" src="static/wizards/js/bwizard.js"></script>
<script type="text/javascript" src="static/js/customer/index.js"></script>
<script type="text/javascript">
    $(function () {
//    $("html,body").animate({scrollTop: $("#elementid").offset().top}, 1000);
        var _sctop = $(document).scrollTop();
        $(document).scroll(function () {
            _sctop = $(document).scrollTop();
            if (_sctop > 170) {
                $("#stepLink2").css({opacity: 1});
            }
            else if (_sctop < 170) {
                $("#stepLink2").css({opacity: 0});
            }
        });
        $(document).click(function () {

        });
        $("#stepLink > li").click(function () {
            $("#stepLink > li").removeClass("active");
            $(this).addClass("active");
        });
        function changeLinkState(activeLink) {
            $("#stepLink > li").removeClass("active");
            activeLink.addClass("active");
        }
    });
</script>
</body>
</html>

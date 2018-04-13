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
    <title>特殊订单录入</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
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

        .font12 {
            font-size: 12px;
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

        .input-box-list-title, .input-box-list-value {
            display: table-cell;
        }

        .input-box-list-title {
            width: 119px;
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

        /* this page */
        .user-select-panel {
            border: 1px solid hsla(0, 0%, 90%, 1);
            background-color: #fff;
            padding: 0 10px;
        }

        .user-select-list {
            height: 146px;
            overflow-y: auto;
        }

        .user-select-panel li {
            border-bottom: 1px solid hsla(0, 0%, 95%, 1);
            /*padding:5px 0 ;*/
        }

        .user-select-panel li .radio {
            padding-left: 10px;
        }

        .factory-select-box .dropdown {
            margin-bottom: 10px;
        }

        .factory-select-box .dropdown a.btn {
            padding-top: 10px;
            padding-bottom: 10px;
            background-color: #fff;
            border-radius: 0;
        }

        .factory-select-box .dropdown a.btn span.caret {
            margin-top: 8px;
        }

        .factory-select-box .dropdown ul {
            width: 100%;;
        }

        .account-panel {
            border: 1px solid hsla(0, 0%, 90%, 1);
            border-top: 2px solid #a94442;
            padding: 20px 20px 10px;
            background-color: #fff;
        }

        .account-panel > div > p {
            margin: 0;
        }

        .account-item {
            margin-bottom: 15px;
        }

        .account-item > span {
            margin: 0 5px;
        }

        .account-item:last-child {
            border-top: 1px dashed hsla(0, 0%, 90%, 1);
            padding-top: 20px;
        }

        .order-accordion-item {
            border-radius: 0;
        }

        .order-accordion-item div.panel {
            border-radius: 0;
        }

        .order-accordion-item div.panel-heading {
            border-radius: 0;
            background-color: #fff;
            transition: 0.2s;
        }

        .order-accordion-item div.panel-heading:hover {
            background-color: hsla(202, 68%, 74%, .15);
        }

        .order-item-title-block {
            padding-right: 30px;
            margin-right: 20px;
            border-right: 1px solid hsla(0, 0%, 95%, 1);
            color: #333;
        }

        .order-plus-box {
            margin-bottom: 0;
        }

        .order-plus-box thead {
            background-color: hsla(255, 0%, 95%, 1);
        }

        .order-plus-box td, .order-plus-box th {
            font-size: 12px;
            text-align: center;
        }

        .order-plus-box-add {
            padding: 10px;
            text-align: center;
            width: 100%;
            font-size: 16px;
            background-color: #fff;
            color: hsla(0, 0%, 65%, 1);
            border: 1px dashed hsla(0, 0%, 90%, 1);
            cursor: pointer;
            transition: 0.2s;
        }

        .order-plus-box-add:hover {
            color: hsla(0, 0%, 35%, 1);
            border-color: hsla(0, 0%, 80%, 1);
        }

        .add-bstb-box {
            background-color: #fff;
            transition: 0.2s;
            clear: both;
        }

        .add-bstb-box-open {
            border-bottom: 1px solid hsla(255, 0%, 90%, 1);
            padding: 10px 0;
            opacity: 1;
            height: auto;
        }

        .order-item {
            float: left;
            line-height: 30px;
            margin-right: 30px;
        }
    </style>
</head>
<body class="container-fluid content">

<section class="content-header">
    <h1>
        订单管理
        <small>特殊订单录入</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li class="active">订单管理</li>
    </ol>
</section>

<form>
    <%-- 选择用户 --%>
    <div class="row">
        <div class="form-group col-md-5 col-sm-6 input-box-list">
            <p class="text-muted order-item">选择下单客户:</p>
            <div class="form-group" style="width: 100%;">
                <select class="findCustom" data-placeholder="请选择客户信息..."
                        style="width: 350px;" tabindex="1" id="merchCustId"
                        name="merchCustId">
                </select>
            </div>
        </div>
        <input type="hidden" id="stationid"> <input type="hidden"
                                                    id="orgid"> <input type="hidden" id="hbratio">
        <div class="col-md-5 col-sm-6 factory-select-box">
            <p class="text-muted order-item">选择送达信息:</p>
            <div class="form-group" style="width: 100%;">
                <select class="findDelivery" data-placeholder="请选择送达方信息..."
                        style="width: 350px;" tabindex="3" id="shipId" name="shipId">
                </select>
            </div>
        </div>
    </div>
    <div class="row">
    	<div class="col-md-5 col-sm-6 factory-select-box">
            <p class="text-muted order-item">发货方式:</p>
            <div class="form-group" style="width: 100%;">
                <select class="form-group" 	style="width: 350px;" tabindex="3" id="deliveryType" >
						<option></option>
						<c:forEach items="${dict.get('ORDER_DELIVERY_TYPE') }" var="type">
		                	<option value="${type.chooseVal}">${type.showText}</option>
		            	</c:forEach>
			    </select>
            </div>
        </div>
        <div class="col-md-5 col-sm-6 factory-select-box hide" id="freighthide">
            <p class="text-muted order-item">运费(元):</p>
            <div class="form-group" style="width: 100%;">
                 <input type="text" class="form-control" style="width: 350px;" tabindex="1" id="freight"/>
            </div>
        </div>
    </div>
    <div class="row hide customAccount">
        <div class="col-md-5 col-xs-6">
            <p class="text-muted">客户账户信息:</p>
            <div class="account-panel">
                <p class="account-item">
                    <span class="account-title">现金余额(元):</span> <span
                        class="account-value" id="cashAmt"><i
                        class="icon icon-yen"></i>&nbsp;</span>
                </p>
                <p class="account-item">
                    <span class="account-title">补货余额(元):</span> <span
                        class="account-value" id="subsidyAmt"><i
                        class="icon icon-yen"></i>&nbsp;</span>
                </p>
                <p class="account-item">
                    <span class="account-title">授信余额(元):</span> <span
                        class="account-value" id="creditAmt"><i
                        class="icon icon-yen"></i>&nbsp;</span>
                </p>
                <!-- <p class="account-item">
                    <span class="account-title">保证金(元):</span> <span
                        class="account-value" id="bondAmt"><i
                        class="icon icon-yen"></i>&nbsp;</span>
                </p> -->
                <p class="account-item" style="font-size: 18px;">
                    <span class="account-title">总计余额(元):</span> <span
                        class="account-value text-danger" id="allamt"><i
                        class="icon icon-yen"></i>&nbsp;</span>
                </p>
            </div>
        </div>
    </div>
    <%-- 订单详情 --%>
    <div class="page-header">
        <h5>
            订单项目详情&nbsp;-&nbsp;
            <small>Order Details.</small>
            <!-- <a href="javascript:;" class=" text-info block-save-link"  data-toggle="modal" data-target="#addOrderModal"><i class="icon icon-plus"></i>&nbsp;&nbsp;新增项目</a> -->
        </h5>
    </div>
    <%-- 订单项目列表 --%>
    <div class="panel-group order-accordion-item" role="tablist">
        <%--新增订单 --%>
        <div class="form-horizontal row add-bstb-box">
            <div class="modal-body">
                <div class="form-horizontal row" style="padding-right: 20px;">
                    <div class="form-group col-md-8 col-xs-12 input-box-list">
                        <label for="input1" class=" font12 input-box-list-title">产品SKU：</label>
                        <div class="input-box-list-value">
                            <select style="width: 100%;" tabindex="1" id="material"
                                    name="material" data-placeholder="输入SKU...">
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-md-6 col-xs-6 input-box-list"

                         style="display: none">
                        <label for="input1" class=" font12 input-box-list-title">产品名称：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="materialName"
                                   name="materialName" disabled>
                        </div>
                    </div>
                    <div class="form-group col-md-4 col-sm-6 input-box-list">

                        <label for="input1" class=" font12 input-box-list-title">产品数量：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control goodsNum" id="num"
                                   name="num" placeholder="输入产品数量">
                        </div>
                    </div>
                    <div class="form-group col-md-4 col-sm-6 input-box-list">

                        <label for="input1" class=" font12 input-box-list-title">单价(元)：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="orderPrice" name="orderPrice"
                                   disabled>
                        </div>
                    </div>
                    <div class="form-group col-md-4 col-xs-6 input-box-list">
                        <label for="input1" class=" font12 input-box-list-title">单位：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="unit" name="unit" disabled>
                        </div>
                    </div>
                    <div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="input1" class=" font12 input-box-list-title">规格：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="specifications" 
									disabled>
							</div>
					</div>
					<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="input1" class=" font12 input-box-list-title">箱内数量：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="amounts" 
									disabled>
							</div>
					</div>
                    <input type="hidden" id="price" name="price">
                    <input type="hidden" id="highPrice" name="highPrice">
                    <div class="form-group col-md-4 col-xs-6 input-box-list">
                        <label for="input1" class=" font12 input-box-list-title">货补数量：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control additionNum" id="hbNum"
                                   placeholder="输入附加数量">
                        </div>
                    </div>
                    <input type="hidden" id="hbAmt" value='0'>

                    <div class="form-group col-md-4 col-xs-6 input-box-list">

                        <label for="input1" class=" font12 input-box-list-title">订单金额(元)：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="amt" name="amt"
                                   disabled value="0">
                        </div>
                    </div>
                    <div class="form-group col-md-4 col-xs-6 input-box-list">
                        <label for="input1" class=" font12 input-box-list-title">折扣额(元)：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="discountAmt"
                                   disabled value="0">
                        </div>
                    </div>
                    <div class="form-group col-md-4 col-xs-6 input-box-list">
                        <label for="input1" class=" font12 input-box-list-title">订单净额(元)：</label>
                        <div class="input-box-list-value">
                            <input type="text" class="form-control" id="orderAmt"
                                   name="orderamt" disabled value="0">
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-12 row" style="padding: 25px 0 0 15px;">
                <div class="col-md-4 col-sm-4 text-left "></div>
                <div class="col-md-4 col-sm-4 text-center">
                    <a id="btn-add-agent" class="btn btn-danger"
                       style="padding-left: 15%; padding-right: 15%;"><i
                            class="icon icon-check"></i>&nbsp;&nbsp;加入购物车</a>
                </div>
            </div>
            <div class="modal-footer"></div>
        </div>
        <div id="order-toolbar">
            <a id="edit-bstb-row" class="btn btn-primary  pull-left"
               style="width: auto; margin-right: 5px;"> <i
                    class="icon icon-plus"></i> 修改数量
            </a> <a id="del-bstb-row" class="btn btn-danger  pull-left"
                    style="width: auto; margin-right: 5px;"> <i
                class="icon icon-remove"></i> 删除
        </a>
        </div>
        <%-- 数据表格 --%>
        <table id="order-table" class="order-box"
               data-toolbar="#order-toolbar"
               data-detail-formatter="detailFormatter"
               data-id-field="id">
        </table>
    </div>
    <%-- 金额统计 --%>
    <div class="col-md-12"
         style="padding: 20px 0; border-bottom: 1px solid rgba(0, 0, 0, .15); margin-top: 25px;">
        <dl class="dl-horizontal">
            <dt>现金扣减金额(元):</dt>
            <dd id="XJamt">0</dd>
        </dl>
        <dl class="dl-horizontal">
            <dt>授信扣减金额(元):</dt>
            <dd id="SXamt">0</dd>
        </dl>
        <dl class="dl-horizontal">
            <dt>货补扣减金额(元):</dt>
            <dd id="HBamt">0</dd>
        </dl>
    </div>
    <div class="remark col-md-offset-1 col-md-11">
        <albel>备注:</albel>
        <textarea style="width:80%;min-height:60px; display:block;margin-bottom:20px;" id="remark"></textarea>
    </div>
    <%-- submit button --%>
    <div class="text-center"
         style="padding-top: 100px; border-top: 1px solid rgba(0, 0, 0, .15); margin-top: 40px; clear:bodth;">
        <button class="btn btn-warning" style="padding: 8px 25px;"
                type="button" id="btn-save">
            <i class="icon icon-save"></i>&nbsp;&nbsp;保存
        </button>
        <button class="btn btn-primary btn-long" type="button"
                id="btn-save-audit">
            <i class="icon icon-check"></i>&nbsp;&nbsp;提交
        </button>
    </div>

</form>
<!-- Modal -->
<!-- editDialog-->
<div class="modal fade" id="editLineModal" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="dictLabel">行数量修改</h4>
            </div>
            <div class="modal-body">
                <form action="" class="form-horizontal" id="editlineform">
                    <div class="form-group">
                        <label for="payName" class="col-sm-3 control-label">物料名称</label> <input
                            type="hidden" id="editid" name="id">
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editsku"
                                   readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <input id="editid" type="hidden">
                    <input type="hidden" id="editmaterialId">
                    <div class="form-group">
                        <label for="payAmt" class="col-sm-3 control-label">单位</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editunit"
                                   readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="payAmt" class="col-sm-3 control-label">单价</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editorderPrice"
                                   readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <input type="hidden" id="editprice">
                    <input type="hidden" id="edithPrice">
                    <input type="hidden" id="editwlPrice">
                    <div class="form-group">
                        <label for="payCity" class="col-sm-3 control-label">规格</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editspecifications"  readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editpayDate" class="col-sm-3 control-label">箱内数量</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editamounts"  readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="payCity" class="col-sm-3 control-label">数量</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editnum" name="num">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editpayDate" class="col-sm-3 control-label">货补数量</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="edithbNum" name="hbNum">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <input type="hidden" id="edithbAmt">
                    <div class="form-group">
                        <label for="editpayDate" class="col-sm-3 control-label">订单金额</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editamt"
                                   readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editpayDate" class="col-sm-3 control-label">折扣额</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editdiscountAmt"
                                   readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editpayDate" class="col-sm-3 control-label">订单净额</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editorderAmt"
                                   readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btn-edit-save" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<!-- <script type="text/javascript"
    src="static/table/bootstrap-table.min.js"></script>
<script type="text/javascript"
    src="static/table/new/bootstrap-table.js"></script>
<script type="text/javascript"
    src="static/table/new/bootstrap-table-export.js"></script>
<script type="text/javascript"
    src="static/table/new/bootstrap-table-editable.js"></script>
<script type="text/javascript"
    src="static/table/new/bootstrap-editable.js"></script> -->
<!-- 	<script type="text/javascript"
		src="static/table/locale/bootstrap-table-zh-CN.min.js"></script> -->
<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="static/js/order/specialorder.js"></script>
<script type="text/javascript">
    function getverification(value) {
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

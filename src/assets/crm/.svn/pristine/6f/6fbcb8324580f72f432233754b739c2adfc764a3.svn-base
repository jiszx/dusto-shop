<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

.nav.nav-pills>li {
	padding: 5px 0;
}

.nav.nav-pills>li>a {
	padding: 5px 15px;
	background: rgba(0, 0, 0, .05);
	border: none;
	transition: 0.2s;
	font-size: 13px;
}

.nav.nav-pills>li.active>a, .nav.nav-pills>li.active>a:hover {
	color: #fff;
	background: #3c8dbc;
	font-weight: normal;
}

.nav.nav-pills>li>a:hover {
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

.account-panel>div>p {
	margin: 0;
}

.account-item {
	margin-bottom: 15px;
}

.account-item>span {
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

.order-item {
	float: left;
	line-height: 30px;
	margin-right: 30px;
}

.contact-box, .contact-box-new, .contact-box-add {
            border: 1px solid hsla(0, 0%, 95%, 1);
            background-color: #fff;
            height: 239px;
            padding: 20px 15px;
            max-height: 239px;
            overflow: hidden;
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
</style>
</head>
<body class="container-fluid content">

	<section class="content-header">
		<h1>
			调拨单管理 <small>订单修改</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li class="active">调拨单管理</li>
		</ol>
	</section>
	<%-- 选择用户 --%>
	<div class="row">
		<div class="form-group col-md-5 col-sm-6 input-box-list">
			<p class="text-muted order-item">客户信息:</p>
			<div class="form-group" style="width: 100%;">
				<input type="text" class="form-control" value="${custname }"
					disabled style="width: 80%;">
			</div>
		</div>
		<input type="hidden" id="merchCustId" value="${order.merchCustId }">
		<input type="hidden" id="ordershipId" value="${order.shipId }">

		<input type="hidden" id="orgid" value="${order.organizationId}">
		<div class="col-md-5 col-sm-6 factory-select-box">
            <p class="text-muted order-item">送达方信息:</p>
            <div class="form-group" style="width:100%;">
	           <select class="findDelivery" data-placeholder="请选择送达方信息..."
						style="width: 350px;" tabindex="3" id="shipId" name="shipId">
					<c:forEach items="${oldDist}" var="dis">
                        <option value="${dis.id}" ${order.shipId == dis.id?"selected":""}>${dis.address}</option>
                    </c:forEach>
			   </select>
            </div>
        </div>
	</div>
	<div class="row">
        <div class="col-md-5 col-sm-6 factory-select-box">
            <p class="text-muted order-item">发货方式:</p>
            <div class="form-group" style="width:100%;">
	           <select class="form-group" style="width: 350px;" tabindex="3" id="deliveryType" name="deliveryType" disabled="disabled">
					<c:forEach items="${dict.get('ORDER_DELIVERY_TYPE') }" var="type">
		                	<option value="${type.chooseVal}" ${order.deliveryType == type.chooseVal?"selected":""}>${type.showText}</option>
		            </c:forEach>
			   </select>
            </div>
        </div>
        <c:if test="${order.deliveryType=='2' }">
        <div class="col-md-5 col-sm-6 factory-select-box">
            <p class="text-muted order-item">运费:</p>
            <div class="form-group" style="width:100%;">
	           <input type="text" class="form-control" style="width: 350px;" tabindex="1" id="freight" value="${order.freight/100 }"/>
            </div>
        </div>
        </c:if>
     </div>
	<div class="row  customAccount">
		<div class="col-md-5 col-xs-6">
			<p class="text-muted">客户账户信息:</p>
			<div class="account-panel">
				<p class="account-item">
					<span class="account-title">授信余额(元):</span> <span
						class="account-value" id="creditAmt"><i
						class="icon icon-yen"></i>&nbsp;</span>
				</p>
			</div>
		</div>
	</div>
	<%-- 订单详情 --%>
	<div class="page-header">
		<h5>
			项目详情&nbsp;-&nbsp;<small>Order Details.</small>
			<!-- <a href="javascript:;" class=" text-info block-save-link"  data-toggle="modal" data-target="#addOrderModal"><i class="icon icon-plus"></i>&nbsp;&nbsp;新增项目</a> -->
		</h5>
	</div>

	<!-- addDialog-->
	<div class="modal fade" id="addLineModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">新增SKU</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="order/edit/addline" id="addlineForm">
						<div class="form-group">
							<label for="material" class="col-sm-3 control-label">产品SKU:</label>
							<div class="col-sm-6">
								<select style="width: 100%;" tabindex="1" id="materialId"
									name="materialId" data-placeholder="输入SKU...">
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="orderid" value="${id}" name="headerId">
						<div class="form-group">
							<label for="materialName" class="col-sm-3 control-label">产品名称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="materialName"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="price" class="col-sm-3 control-label">单价(元)</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="price" name="price"
									readonly="readonly" value="0">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="price" class="col-sm-3 control-label">规格</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="specifications" readonly="readonly" value="0">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="price" class="col-sm-3 control-label">箱内数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="amounts" name="amounts" readonly="readonly" value="0">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="price" class="col-sm-3 control-label">库存数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="invnum" readonly="readonly" value="0">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="materialType" name="attribute11">
						<input type="hidden" id="orderPrice" name="orderPrice"> 
						<input type="hidden" id="highPrice" name="highPrice">
						<div class="form-group">
							<label for="unit" class="col-sm-3 control-label">单位</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="unit" name="unit"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="num" class="col-sm-3 control-label">数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="num" name="num"
									placeholder="输入产品数量" value="0">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="amt" class="col-sm-3 control-label">金额(元)</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="amt" name="amt"
									value="0" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group hide">
							<label for="amt" class="col-sm-3 control-label"></label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="orderAmt"
									name="orderAmt" value="0" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-add-save"
						class="btn btn-primary btn-save" form="addlineForm">保存</button>
				</div>
			</div>
		</div>
	</div>

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
					<h4 class="modal-title" id="dictLabel">修改SKU</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="order/edit/editline" id="editlineForm">
						<div class="form-group">
							<label for="material" class="col-sm-3 control-label">产品SKU:</label>
							<div class="col-sm-6">
								<select style="width: 100%;" tabindex="1" id="editmaterialId"
									data-placeholder="输入SKU..." disabled="disabled">
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="editid" name="id"> 
						<input
							type="hidden" id="editheaderId" name="headerId"> <input
							type="hidden" id="smaterialId" name="materialId">
						<div class="form-group" style="display: none">
							<label for="materialName" class="col-sm-3 control-label">产品名称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editsku"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="price" class="col-sm-3 control-label">单价(元)</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editprice"
									name="price" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="editmaterialType" name="attribute11">
						<div class="form-group">
							<label for="price" class="col-sm-3 control-label">规格</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editspecifications" readonly="readonly" value="0">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="price" class="col-sm-3 control-label">箱内数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editamounts" name="amounts" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="input1" class="col-sm-3 control-label">库存数量：</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editinvnum" disabled>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="editorderPrice" name="orderPrice">
						<div class="form-group">
							<label for="unit" class="col-sm-3 control-label">单位</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editunit"
									name="unit" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="num" class="col-sm-3 control-label">数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editnum" name="num"
									placeholder="输入产品数量">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="amt" class="col-sm-3 control-label">金额(元)</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editamt" name="amt"
									value="0" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group hide">
							<label for="orderAmt" class="col-sm-3 control-label">应收金额(元)</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editorderAmt"
									name="orderAmt" value="0" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-edit-save"
						class="btn btn-primary btn-save" form="editlineForm">保存</button>
				</div>
			</div>
		</div>
	</div>
	<%-- 订单项目列表 --%>
	<div class="panel-group order-accordion-item" role="tablist">
		<%--新增订单 --%>

		<div id="order-toolbar">
			<a id="btn-add" class="btn btn-primary  pull-left"
				style="width: auto; margin-right: 5px;" data-toggle="modal"
				data-target="#addLineModal"> <i class="icon icon-plus"></i>
				新增SKU
			</a> <a id="btn-edit" class="btn btn-primary  pull-left"
				style="width: auto; margin-right: 5px;"> <i
				class="icon icon-plus"></i> 修改SKU
			</a> <a id="btn-del" class="btn btn-danger  pull-left"
				style="width: auto; margin-right: 5px;"> <i
				class="icon icon-remove"></i> 删除
			</a>
		</div>
		<%-- 数据表格 --%>
		<table id="order-table" class="order-box"
			data-toolbar="#order-toolbar" data-search="true"
			data-show-refresh="true"
			<%--data-show-toggle="true"--%>
        data-show-columns="true"
			data-show-export="true"
			<%--data-show-pagination-switch="true"--%>
        data-detail-view="false"
			data-detail-formatter="detailFormatter" data-id-field="id"<%-- data-pagination="true" data-page-size="5" data-page-list="[5,8,10]" --%>
        <%--data-pagination-first-text="首页" data-pagination-pre-text="上一页" --%>
        <%--data-pagination-next-text="下一页" data-pagination-last-text="尾页" --%>
        >
		</table>
	</div>
	
	<div class="page-header" id="block-5">
		<h4 class="text-info">
			附件信息&nbsp;-&nbsp; <small>Enclosure.</small>
		</h4>
	</div>
	<form id="orderFrom" enctype="multipart/form-data"  action="order/addOrderFiles" method="post"> 
	<div class="row" style="margin: 0 0 20px 0;">
		<div class="col-md-4 col-sm-6 file-list-box">
			<div class="contact-box" id="orderFiles">
				<ul class="file-list" name="files">
				<c:forEach items="${attachments}" var="attachment" varStatus="attStatus">
                	<li id="${attachment.id }">
	                  <span class="file-name" data-file-src=""><a href="${attachmentBASEURI}${attachment.objectName}${attachment.fileName}" target="_blank" >${attachment.attachmentName }</a></span>
	                  <a href="javascript:;"class="pull-right del-file" source="${attachment.id }" title="删除附件" ><i class="icon icon-remove" ></i></a>
	                </li>
                </c:forEach>
                </ul>
			</div>
		</div>
		<div class="col-md-4 col-sm-6">
			<div class="contact-box-add" id="file-item-add-box" title="上传附件">
					<input id="file-select" type="file" onchange="addFiles()" > <i
					class="icon icon-plus"></i>&nbsp;&nbsp;添加附件 <input type="hidden"
					id="delAtts" name="delAtts">
			</div>
		</div>
	</div>
	<input name="headerId" value="${order.id}" type="hidden">
	</form>
	<%-- 金额统计 --%>
	<%-- submit button --%>
	<div class="text-center"
		style="padding-top: 100px; border-top: 1px solid rgba(0, 0, 0, .15); margin-top: 40px;">
		<button class="btn btn-warning" style="padding: 8px 25px;"
			type="button" id="btn-save">
			<i class="icon icon-save"></i>&nbsp;&nbsp;保存
		</button>
		<!-- <button class="btn btn-primary btn-long" type="button" id="btn-save-audit"><i class="icon icon-check"></i>&nbsp;&nbsp;提交</button> -->
	</div>
	<input type="hidden" id="sxamt">
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="static/js/customerInv/edit.js"></script>
	<script type="text/javascript">
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

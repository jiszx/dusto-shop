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
<link href="static/css/order/kaOrder.css" rel="stylesheet">
<link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
<link href="static/bootstrap/css/bootstrap-datetimepicker.min.css"	rel="stylesheet">
</head>
<body class="container-fluid content">

	<section class="content-header">
		<h1>
			订单管理 <small>ka订单修改</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li class="active">订单管理</li>
		</ol>
	</section>
	<%-- 选择用户 --%>
	<div class="page-header" id="block-1">
		<h4 class="text-info">
			<strong>1.&nbsp;</strong>订单头信息&nbsp;-&nbsp; <small>Enclosure.</small>
		</h4>
	</div>
	<div class="row" style="margin: 0 0 20px 0;">
		<div class="form-group col-md-6 col-sm-6 input-box-list">
			<p class="text-muted order-item">客户信息:</p>
			<div class="form-group">
				<input type="text" class="form-control" value="${custname }"
					disabled style="width: 350px;">
			</div>
		</div>
		<input type="hidden" id="merchCustId" value="${order.merchCustId }">
		<input type="hidden" id="ordershipId" value="${order.shipId }">
		<input type="hidden" id="merchPid" value="${merch.pid }"> <input
			type="hidden" id="orgid" value="${order.organizationId}">
		<div class="col-md-6 col-sm-6 factory-select-box">
			<p class="text-muted order-item">送达方信息:</p>
			<div class="form-group">
				<select class="findDelivery" data-placeholder="请选择送达方信息..."
					style="width: 350px;" tabindex="3" id="shipId">
					<c:forEach items="${distr}" var="dis">
						<option value="${dis.id}" ${order.shipId == dis.id?"selected":""}>${dis.sapCustomerId != null?dis.sapCustomerId:""}${dis.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<div class="row" style="margin: 0 0 20px 0;">
		<div class="col-md-6 col-sm-6 factory-select-box hide">
			<p class="text-muted order-item">付款客户:</p>
			<div class="form-group" >
				<select class="findDelivery" data-placeholder="请选择付款方信息..."
					style="width: 350px;" tabindex="3" id="billto" >
					<c:forEach items="${bills}" var="bill">
						<option value="${bill.id}" ${order.billTo == bill.id?"selected":""}>${bill.sapCustomerId != null?bill.sapCustomerId:""}${bill.custname}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="col-md-6  col-sm-6 factory-select-box">
				<p class="text-muted order-item">最晚送达时间:</p>
				<div class="form-group">
					<input type="date" class="form-control" placeholder="最晚送达时间"
						id="endTime" tabindex="3" style="width: 350px"  value="${order.attribute2}">
				</div>
			</div>
	</div>
	<div class="row">
		<div class="col-md-6 col-sm-6 factory-select-box hide">
			<p class="text-muted order-item">发货方式:</p>
			<div class="form-group" >
				<select class="form-group" style="width: 350px;" tabindex="3" id="deliveryType" name="deliveryType" disabled="disabled">
					<c:forEach items="${dict.get('ORDER_DELIVERY_TYPE') }" var="type">
		                	<option value="${type.chooseVal}" ${order.deliveryType == type.chooseVal?"selected":""}>${type.showText}</option>
		            </c:forEach>
			   </select>
			</div>
		</div>
		<c:if test="${order.deliveryType=='2' }">
		<div class="col-md-6  col-sm-6 factory-select-box">
			<p class="text-muted order-item">运费(元):</p>
			<div class="form-group">
				<input type="text" class="form-control" style="width: 350px;" tabindex="1" id="freight" name="freight" value="${order.freight/100 }"/>
			</div>
		</div>
		</c:if>
	</div>
	<div class="row" style="margin: 0 0 20px 0;">
		<div class="col-md-6  col-sm-6 factory-select-box">
				<p class="text-muted order-item">对方订单编号:</p>
				<div class="form-group">
					<input type="text" class="form-control" id="othersOrderId" tabindex="3" style="width: 350px"  value="${order.attribute9}" disabled>
				</div>
			</div>
	</div>
	<div class="page-header" id="block-2">
		<h4 class="text-info">
			<strong>2.&nbsp;</strong>客户账户信息&nbsp;-&nbsp; <small>Enclosure.</small>
		</h4>
	</div>
	<div class="row  customAccount">
		<div class="col-md-5 col-xs-6">
			<div class="account-panel">
				<p class="account-item">
					<span class="account-title">现金余额(元):</span> <span
						class="account-value" id="cashAmt"><i class="icon icon-yen"></i>&nbsp;</span>
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
				<p class="account-item" style="font-size: 18px;">
					<span class="account-title">总计余额(元):</span> <span
						class="account-value text-danger" id="allamt"><i
						class="icon icon-yen"></i>&nbsp;</span>
				</p>
			</div>
		</div>
	</div>
	<%-- 订单详情 --%>
	<div class="page-header" id="block-3">
		<h4 class="text-info">
			<strong>3.&nbsp;</strong>订单行信息&nbsp;-&nbsp; <small>Enclosure.</small>
		</h4>
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
							<label for="input1" class="col-sm-3 control-label">可售库存：</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="invnum" disabled>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="orderPrice" name="orderPrice"> <input
							type="hidden" id="highPrice" name="highPrice">
						<div class="form-group">
							<label for="unit" class="col-sm-3 control-label">单位</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="unit" name="unit"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="unit" class="col-sm-3 control-label">规格</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="specifications"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="unit" class="col-sm-3 control-label">箱内数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="amounts"
									name="amounts" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="num" class="col-sm-3 control-label">购买数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="num" name="num"
									placeholder="输入产品数量" value="0">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="policy" class="col-sm-3 control-label">销售政策</label>
							<div class="col-sm-6">
								<select class="form-control"
									style="border-radius: 0; padding-right: 30px;" id="policy">
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="policyHeaderId" name="policyHeaderId">
						<input type="hidden" id="policyDiscountIntensity"
							name="policyDiscountIntensity"> <input type="hidden"
							id="policyVerfication" name="policyVerfication"> <input
							type="hidden" id="policyLineId" name="policyLineId"> <input
							type="hidden" id="policyDiscount" name="policyDiscount">
						<input type="hidden" id="policyprimary" name="policyprimary">
						<input type="hidden" id="policyamt"> <input type="hidden"
							id="policyprice">
						<div class="form-group">
							<label for="discountname" class="col-sm-3 control-label">销售政策奖励</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="discountname"
									name="discountname" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="hNum" class="col-sm-3 control-label">货补数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="hbNum" name="hbNum"
									value="0">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="hbamt" name="hbAmt" value="0">
						<div class="form-group">
							<label for="amt" class="col-sm-3 control-label">订单金额(元)</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="amt" name="amt"
									value="0" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="discountAmt" class="col-sm-3 control-label">折扣金额(元)</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="discountAmt"
									value="0" name="discountAmt" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="orderAmt" class="col-sm-3 control-label">应收金额(元)</label>
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
					<form class="form-horizontal" method="post"	action="order/edit/editline" id="editlineForm">
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
						<input type="hidden" id="editheaderId" name="headerId">
					    <input type="hidden" id="smaterialId" name="materialId">
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
						<div class="form-group">
							<label for="unit" class="col-sm-3 control-label">规格</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editspecifications"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="unit" class="col-sm-3 control-label">箱内数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editamounts"
									name="amounts" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="input1" class="col-sm-3 control-label">可售库存：</label>
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
							<label for="num" class="col-sm-3 control-label">购买数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editnum" name="num"
									placeholder="输入产品数量">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="policy" class="col-sm-3 control-label">销售政策</label>
							<div class="col-sm-6">
								<select class="form-control"
									style="border-radius: 0; padding-right: 30px;" id="editpolicy">
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="editpolicyHeaderId" name="policyHeaderId">
						<input type="hidden" id="editpolicyDiscountIntensity"
							name="policyDiscountIntensity"> <input type="hidden"
							id="editpolicyVerfication" name="policyVerfication"> <input
							type="hidden" id="editpolicyLineId" name="policyLineId">
						<input type="hidden" id="editpolicyDiscount" name="policyDiscount">
						<input type="hidden" id="editpolicyprimary" name="policyprimary">
						<input type="hidden" id="editpolicyamt"> <input
							type="hidden" id="editpolicyprice">
						<div class="form-group">
							<label for="discountname" class="col-sm-3 control-label">销售政策奖励</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"
									id="editpolicydiscountname" name="policydiscountname"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="hNum" class="col-sm-3 control-label">货补数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="edithbNum"
									name="hbNum" value="0">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="edithbamt" name="hbAmt" value="0">
						<div class="form-group">
							<label for="amt" class="col-sm-3 control-label">订单金额(元)</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editamt" name="amt"
									value="0" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="discountAmt" class="col-sm-3 control-label">折扣金额(元)</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editdiscountAmt"
									value="0" name="discountAmt" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
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
	<%-- 金额统计 --%>
	<div class="page-header" id="block-4">
		<h4 class="text-info">
			<strong>4.&nbsp;</strong>资金扣减信息&nbsp;-&nbsp; <small>Enclosure.</small>
		</h4>
	</div>
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
	<div class="page-header" id="block-5">
		<h4 class="text-info">
			<strong>5.&nbsp;</strong>附件信息&nbsp;-&nbsp; <small>Enclosure.</small>
		</h4>
	</div>
	<form enctype="multipart/form-data"  action="order/kaOrderEditSave" method="post" id="kaOrderEditForm"> 
	<div class="row" style="margin: 0 0 20px 0;">
		<div class="col-md-4 col-sm-6 file-list-box">
			<div class="contact-box" id="orderFiles">
				<ul class="file-list" name="files">
				<c:forEach items="${attachments}" var="attachment" varStatus="attStatus">
                	<li id="${attachment.id }">
	                  <span class="file-name" data-file-src=""><a href="${attachmentBASEURI}${attachment.objectName}${attachment.fileName}" target="_blank" >${attachment.attachmentName }</a></span>
	                  <a href="javascript:;"class="pull-right del-file" source="${attachment.id }" title="删除附件" ><i class="icon icon-remove" onclick="delFile(${attachment.id })"></i></a>
	                </li>
                </c:forEach>
                </ul>
			</div>
		</div>
		<div class="col-md-4 col-sm-6">
			<div class="contact-box-add" id="file-item-add-box" title="上传附件">
					<input id="file-select" type="file" onchange="addFiles()" > <i
					class="icon icon-plus"></i>&nbsp;&nbsp;添加附件 <input type="hidden"
					id="delAtts">
			</div>
		</div>
		<input type="hidden" value="${id}" name="orderId">
		<input type="hidden" name="endTime" id="bdate">
		<input type="hidden" name="billto" id="sbillto">
		<input type="hidden" name="shipto" id="sshipto">
		<input type="hidden" name="remark" id="sremark">
	</div>
	<div class="remark col-md-offset-1 col-md-11">
		<albel>备注:</albel><textarea style="width:80%;min-height:60px; display:block;margin-bottom:20px;" id="remark">${order.remark }</textarea>
	</div>
	<%-- submit button --%>
	<div class="text-center"
		style="padding-top: 100px; border-top: 1px solid rgba(0, 0, 0, .15); margin-top: 40px;">
		<button class="btn btn-warning" style="padding: 8px 25px;"
			type="button" id="btn-save">
			<i class="icon icon-save"></i>&nbsp;&nbsp;保存
		</button>
		<!-- <button class="btn btn-primary btn-long" type="button" id="btn-save-audit"><i class="icon icon-check"></i>&nbsp;&nbsp;提交</button> -->
	</div>
	</form>

	<!-- Modal -->
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<script type="text/javascript"	src="static/table/bootstrap-table.min.js"></script>
	<script type="text/javascript"	src="static/table/new/bootstrap-table.js"></script>
	<script type="text/javascript"	src="static/table/new/bootstrap-table-export.js"></script>
	<script type="text/javascript"	src="static/table/new/bootstrap-table-editable.js"></script>
	<script type="text/javascript"	src="static/table/new/bootstrap-editable.js"></script>
	<script type="text/javascript"	src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="static/js/order/kaOrderEdit.js"></script>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"	charset="UTF-8"></script>
	<script type="text/javascript">
		function getverification(value) {
			var data = new Object();
			<c:forEach items="${dict['POLICY_TYPE_VERIFICATION']}" var="type">
			data["${type.chooseVal}"] = "${type.showText}";
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

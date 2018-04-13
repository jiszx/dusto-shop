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
			订单管理 <small>KA订单录入</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li class="active">订单管理</li>
		</ol>
	</section>

	<form enctype="multipart/form-data"  action="order/addOrder" method="post" id="addKaOrderForm"> 
		<%-- 选择用户 --%>
		<div class="page-header" id="block-1">
			<h4 class="text-info">
				<strong>1.&nbsp;</strong>订单头信息&nbsp;-&nbsp; <small>Enclosure.</small>
			</h4>
		</div>
		<div class="row" style="margin: 0 0 20px 0;">
			<div class="form-group col-md-6 col-sm-6 input-box-list">
				<p class="text-muted order-item">选择客户:</p>
				<div class="form-group" >
					<select class="findCustom" data-placeholder="请选择客户信息..."
						style="width: 350px;" tabindex="1" id="merchCustId"
						name="merchCustId">
					</select>
				</div>
			</div>
			<input type="hidden" id="stationid" name="stationid">
			<input type="hidden" id="orgid" name="orgid">
			<input type="hidden" id="hbratio"> 
			<input type="hidden" id="custPid">
			<div class="col-md-6  col-sm-6 factory-select-box">
				<p class="text-muted order-item">选择送达信息:</p>
				<div class="form-group" >
					<select class="findDelivery" data-placeholder="请选择送达方信息..."
						style="width: 350px;" tabindex="3" id="shipId" name="shipId">
					</select>
				</div>
			</div>
		</div>
		<div class="row"  style="margin: 0 0 20px 0;">
			<div class="col-md-6  col-sm-6 factory-select-box">
				<p class="text-muted order-item">付款客户:</p>
				<div class="form-group" >
					<select class="findDelivery" data-placeholder="请选择付款方信息..."
						style="width: 350px;" tabindex="3" id="billto" name="billto">
					</select>
				</div>
			</div>
			<div class="col-md-6  col-sm-6 factory-select-box">
				<p class="text-muted order-item">最晚送达时间:</p>
				<div class="form-group" >
					<input type="text" class="findDelivery" placeholder="最晚送达时间"
						id="endTime" tabindex="3" style="width: 350px" name="endTime">
				</div>
			</div>
		</div>
		<div class="row"  style="margin: 0 0 20px 0;">
			<div class="col-md-6  col-sm-6 factory-select-box">
				<p class="text-muted order-item">发货方式:</p>
				<div class="form-group" >
					<select class="form-group" 	style="width: 350px;" tabindex="3" id="deliveryType" name="deliveryType" >
						<option></option>
						<c:forEach items="${dict.get('ORDER_DELIVERY_TYPE') }" var="type">
		                	<option value="${type.chooseVal}">${type.showText}</option>
		            	</c:forEach>
					</select>
				</div>
			</div>
			<div class="col-md-6  col-sm-6 factory-select-box hide" id="freighthide">
				<p class="text-muted order-item">运费(元):</p>
				<div class="form-group" >
					<input type="text" class="form-control" style="width: 350px;" tabindex="1" id="freight" name="freight"/>
				</div>
			</div>
		</div>
		<div class="row"  style="margin: 0 0 20px 0;">
			<div class="col-md-6  col-sm-6 factory-select-box">
				<p class="text-muted order-item">对方订单编号:</p>
				<div class="form-group" >
					<input type="text" class="form-control" 
						id="othersOrderId" tabindex="3" style="width: 350px" name="othersOrderId">
				</div>
			</div>
		</div>
		<div class="page-header" id="block-2 hide">
			<h4 class="text-info">
				<strong>2.&nbsp;</strong>客户资金&nbsp;-&nbsp; <small>Enclosure.</small>
			</h4>
		</div>
		<div class="row hide customAccount" style="margin: 0 0 20px 0;">
			<div class="col-md-5 col-xs-6">
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
				<strong>3.&nbsp;</strong>订单详情&nbsp;-&nbsp; <small>Enclosure.</small>
			</h4>
		</div>
		<%-- 订单项目列表 --%>
		<div class="panel-group order-accordion-item" role="tablist" style="margin: 0 0 20px 0;">
			<%--新增订单 --%>
			<div class="form-horizontal row add-bstb-box">
				<div class="modal-body">
					<div class="form-horizontal row" style="padding-right: 20px;">
						<div class="form-group col-md-8 col-sm-12 input-box-list">
							<label for="materialId" class=" font12 input-box-list-title">产品SKU：</label>
							<div class="input-box-list-value">
								<select style="width: 100%;" tabindex="1" id="materialId"
									name="materialId" data-placeholder="输入SKU...">
								</select>
							</div>
						</div>

						<div class="form-group col-md-6 col-sm-6 input-box-list"
							style="display: none">
							<label for="materialName" class=" font12 input-box-list-title">产品名称：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="materialName"
									name="materialName" disabled>
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="price" class=" font12 input-box-list-title">单价(元)：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="price" name="price"
									disabled>
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="unit" class=" font12 input-box-list-title">单位：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="unit" name="unit"
									disabled>
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="invnum" class=" font12 input-box-list-title">可售库存：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="invnum" disabled>
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="specifications" class=" font12 input-box-list-title">规格：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="specifications"
									disabled>
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="amounts" class=" font12 input-box-list-title">箱内数量：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="amounts" disabled>
							</div>
						</div>
						<input type="hidden" id="orderPrice" name="orderPrice"> <input
							type="hidden" id="highPrice" name="highPrice">
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="num" class=" font12 input-box-list-title">下单数量：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control goodsNum" id="num"
									name="num" placeholder="输入产品数量">
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="policy" class=" font12 input-box-list-title">销售政策：</label>
							<div class="input-box-list-value">
								<select class="form-control"
									style="border-radius: 0; padding-right: 30px;" id="policy"
									name="policy" disabled="disabled">
								</select>
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="discountname" class=" font12 input-box-list-title">政策奖励：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="discountname"
									name="discountname" disabled>
							</div>
							<input type="hidden" id="policyHeaderId"> <input
								type="hidden" id="policyLineId"> <input type="hidden"
								id="policyVerfication"> <input type="hidden"
								id="policyDiscount"> <input type="hidden"
								id="policyprimary"> <input type="hidden" id="policyamt">
							<input type="hidden" id="policyprice"> <input
								type="hidden" id="policylimit">
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="policyDiscountIntensity" class=" font12 input-box-list-title">奖励数量/返利比例：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control"
									id="policyDiscountIntensity" name="policyDiscountIntensity"
									disabled>
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="hbNum" class=" font12 input-box-list-title">货补数量：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control additionNum" id="hbNum"
									placeholder="输入附加数量">
							</div>
						</div>
						<input type="hidden" id="hbAmt" value='0'>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="amt" class=" font12 input-box-list-title">订单金额(元)：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="amt" name="amt"
									disabled value="0">
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="discountAmt" class=" font12 input-box-list-title">折扣额(元)：</label>
							<div class="input-box-list-value">
								<input type="text" class="form-control" id="discountAmt"
									disabled value="0">
							</div>
						</div>
						<div class="form-group col-md-4 col-sm-6 input-box-list">
							<label for="orderAmt" class=" font12 input-box-list-title">订单净额(元)：</label>
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
							style="padding-left: 15%; padding-right: 15%; display: block"><i
							class="icon icon-check"></i>&nbsp;&nbsp;确定</a>
					</div>
				</div>
				<div class="modal-footer"></div>
			</div>
			<div id="order-toolbar">
				<!-- <a id="add-bstb-row" class="btn btn-primary  pull-left"
					style="width: auto; margin-right: 5px;"> <i
					class="icon icon-plus"></i> 新增SKU
				</a> -->
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
				data-toolbar="#order-toolbar" data-search="true"
				data-show-refresh="true"
				<%--data-show-toggle="true"--%>
        data-show-columns="true"
				data-show-export="true"
				<%--data-show-pagination-switch="true"--%>
        data-detail-view="false"
				data-detail-formatter="detailFormatter" data-id-field="id">
			</table>
			<input type="hidden" name="lines" id="lines">
			<input type="hidden" name="states" value="1">
			<input type="hidden" name="orderType" value="10">
		</div>
		<div class="page-header" id="block-4">
			<h4 class="text-info">
				<strong>4.&nbsp;</strong>订单扣款&nbsp;-&nbsp; <small>Enclosure.</small>
			</h4>
		</div>
		<%-- 金额统计 --%>
		<div class="col-md-12" style="margin: 0 0 20px 0;">
			<dl class="dl-horizontal">
				<dt>现金扣减金额(元):</dt>
				<dd id="XJamt" name="xjamt">0</dd>
			</dl>
			<dl class="dl-horizontal">
				<dt>授信扣减金额(元):</dt>
				<dd id="SXamt" name="sxamt">0</dd>
			</dl>
			<dl class="dl-horizontal">
				<dt>货补扣减金额(元):</dt>
				<dd id="amt" name="hbamt">0</dd>
			</dl>
		</div>
		<div class="page-header" id="block-5">
			<h4 class="text-info">
				<strong>5.&nbsp;</strong>附件信息&nbsp;-&nbsp; <small>Enclosure.</small>
				<!-- <a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a> -->
			</h4>
		</div>
		<div class="row" style="margin: 0 0 20px 0;">
			<div class="col-md-4 col-sm-6 file-list-box">
				<div class="contact-box" id="orderFiles">
					<ul class="file-list" name="files">
					</ul>
				</div>
			</div>
			<div class="col-md-4 col-sm-6">
				<div class="contact-box-add" id="file-item-add-box" title="上传附件">
					<input id="file-select" type="file" onchange="addFiles()" >
					<i class="icon icon-plus"></i>&nbsp;&nbsp;添加附件 
					<input type="hidden" id="delAtts">
				</div>
			</div>
		</div>
		<div class="remark col-md-offset-1 col-md-11">
        	<albel>备注:</albel>
       	 	<textarea style="width:80%;min-height:60px; display:block;margin-bottom:20px;" id="remark" name="remark"></textarea>
    	</div>
		<%-- submit button --%>
		<div class="text-center"
			style="padding-top: 100px; border-top: 1px solid rgba(0, 0, 0, .15); margin-top: 40px;">
			<button class="btn btn-warning" style="padding: 8px 25px;"
				type="button" id="btn-save">
				<i class="icon icon-save"></i>&nbsp;&nbsp;保存
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
					<form action="" class="form-horizontal">
						<div class="form-group">
							<label for="editsku" class="col-sm-3 control-label">物料名称</label>
							<input type="hidden" id="editid" name="id">
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editsku"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="editmaterialId">
						<div class="form-group">
							<label for="editunit" class="col-sm-3 control-label">单位</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editunit"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="editspecifications" class="col-sm-3 control-label">规格</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editspecifications"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="editamounts" class="col-sm-3 control-label">箱内数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editamounts"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="editorderPrice" class="col-sm-3 control-label">单价(元)</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editorderPrice"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="editinvnum" class="col-sm-3 control-label">可售库存</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editinvnum"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="editprice"> <input type="hidden"
							id="edithighPrice">
						<div class="form-group">
							<label for="editnum" class="col-sm-3 control-label">数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editnum">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="editpolicy" class="col-sm-3 control-label">销售政策</label>
							<div class="col-sm-6">
								<select class="form-control" id="editpolicy" disabled="disabled">
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="editdiscountname" class="col-sm-3 control-label">政策奖励</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editdiscountname"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="editpolicyDiscountIntensity" class="col-sm-3 control-label">政策奖励力度</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"
									id="editpolicyDiscountIntensity" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="editpolicyHeaderId"> <input
							type="hidden" id="editpolicyLineId"> <input type="hidden"
							id="editpolicyVerfication"> <input type="hidden"
							id="editpolicyDiscount"> <input type="hidden"
							id="editpolicyprimary"> <input type="hidden"
							id="editpolicyamt"> <input type="hidden"
							id="editpolicyprice"> <input type="hidden"
							id="editpolicylimit">
						<div class="form-group">
							<label for="edithbNum" class="col-sm-3 control-label">货补数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="edithbNum">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="edithbAmt">
						<div class="form-group">
							<label for="editamt" class="col-sm-3 control-label">订单金额(元)</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editamt"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="editdiscountAmt" class="col-sm-3 control-label">折扣额(元)</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editdiscountAmt"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="editorderAmt" class="col-sm-3 control-label">订单净额(元)</label>
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
	<script type="text/javascript"	src="static/table/bootstrap-table.min.js"></script>
	<script type="text/javascript"	src="static/table/new/bootstrap-table.js"></script>
	<script type="text/javascript"	src="static/table/new/bootstrap-table-export.js"></script>
	<script type="text/javascript"	src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="static/js/order/addKaOrder.js"></script>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"	charset="UTF-8"></script>
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

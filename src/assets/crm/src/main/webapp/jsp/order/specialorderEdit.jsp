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
<title>特殊订单修改</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
<style>
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

.dropdown-menu {
	width: 25.3%;
}

.table-condensed {
	width: 100%;
}

</style>
</head>
<body class="container-fluid content">

<section class="content-header">
    <h1>订单管理
        <small>特殊订单修改</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li class="active">订单管理</li>
    </ol>
</section>
    <%-- 选择用户 --%>
    <div class="row">
        <div class="form-group col-md-5 col-sm-6 input-box-list">
            <p class="text-muted order-item">客户信息:</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="${custname }" disabled style="width:80%">
            </div>
        </div>
        <input type="hidden" id="merchCustId"  value="${order.merchCustId }">
        <input type="hidden" id="ordershipId"  value="${order.shipId }">
        <input type="hidden" id="orgid"  value="${order.organizationId}">
        <div class="col-md-5 col-sm-6 factory-select-box">
            <p class="text-muted order-item">送达方信息:</p>
            <div class="form-group" style="width:100%;">
	           <select class="findDelivery" data-placeholder="请选择送达方信息..." tabindex="3" id="shipId" name="shipId">
					 <option><option/>
					 <c:forEach items="${distr}" var="dis">
                        <option value="${dis.id}" ${order.shipId == dis.id?"selected":""}>${dis.sapCustomerId != null?dis.sapCustomerId:""}${dis.name}</option>
                    </c:forEach>
			   </select>
            </div>
        </div>
    </div>
    <div class="row">
    	<div class="form-group col-md-5 col-sm-6 input-box-list">
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
        <div class="form-group col-md-5 col-sm-6 input-box-list">
            <p class="text-muted order-item">运费(元):</p>
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
                    <span class="account-title">现金余额(元):</span>
                    <span class="account-value" id="cashAmt"><i class="icon icon-yen"></i>&nbsp;</span>
                </p>
                <p class="account-item">
                    <span class="account-title">补货余额(元):</span>
                    <span class="account-value" id="subsidyAmt"><i class="icon icon-yen"></i>&nbsp;</span>
                </p>
                <p class="account-item">
                    <span class="account-title">授信余额(元):</span>
                    <span class="account-value" id="creditAmt"><i class="icon icon-yen"></i>&nbsp;</span>
                </p>
                <p class="account-item" style="font-size: 18px;">
                    <span class="account-title">总计余额(元):</span>
                    <span class="account-value text-danger" id="allamt"><i class="icon icon-yen"></i>&nbsp;</span>
                </p>
            </div>
        </div>
    </div>
    <%-- 订单详情 --%>
    <div class="page-header">
        <h5>订单项目详情&nbsp;-&nbsp;<small>Order Details.</small>
            <!-- <a href="javascript:;" class=" text-info block-save-link"  data-toggle="modal" data-target="#addOrderModal"><i class="icon icon-plus"></i>&nbsp;&nbsp;新增项目</a> -->
        </h5>
    </div>
    
    <!-- addDialog-->
	<div class="modal fade" id="addLineModal" tabindex="-1"
		role="dialog" aria-hidden="true">
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
							<select  class="form-control"  style="width: 270px;" id="materialId" name="materialId"data-placeholder="输入SKU..." >
                            </select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						 <input type="hidden" id="orderid"  value="${id}" name="headerId">
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
								<input type="text" class="form-control" id="orderPrice"name="orderPrice" readonly="readonly" value="0">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="price" class="col-sm-3 control-label">规格</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="specifications" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="price" class="col-sm-3 control-label">箱内数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="amounts" name="amounts" readonly="readonly" >
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="price" name="price">
						<input type="hidden" id="highPrice" name="highPrice">
						<div class="form-group">
							<label for="unit" class="col-sm-3 control-label">单位</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="unit"
									name="unit" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="num" class="col-sm-3 control-label">购买数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="num" name="num"  placeholder="输入产品数量" value="0">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="hNum" class="col-sm-3 control-label">货补数量</label>
							<div class="col-sm-6">
								 <input type="text" class="form-control" id="hbNum" name="hbNum"  value="0">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="hbamt" name="hbAmt" value="0">
						<div class="form-group">
							<label for="amt" class="col-sm-3 control-label">订单金额(元)</label>
							<div class="col-sm-6">
								 <input type="text" class="form-control" id="amt" name="amt"  value="0" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="discountAmt" class="col-sm-3 control-label">折扣金额(元)</label>
							<div class="col-sm-6">
								 <input type="text" class="form-control" id="discountAmt"   value="0" name="discountAmt" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="orderAmt" class="col-sm-3 control-label">应收金额(元)</label>
							<div class="col-sm-6">
								 <input type="text" class="form-control" id="orderAmt"  name="orderAmt" value="0" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-add-save" class="btn btn-primary btn-save"
						form="addlineForm">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	  <!-- editDialog-->
	<div class="modal fade" id="editLineModal" tabindex="-1"
		role="dialog" aria-hidden="true">
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
						action="order/edit/editline" id="editlineForm">
						<div class="form-group">
							<label for="material" class="col-sm-3 control-label">产品SKU:</label>
							<div class="col-sm-6">
								<select  style="width:100%;" tabindex="1" id="editmaterialId" name="materialId" disabled="disabled" >
                            </select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="editid"   name="id">
						<input type="hidden" id="editheaderId"   name="headerId">
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
								<input type="text" class="form-control" id="editorderPrice"
									name="orderPrice" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="editprice" name="price">
						<input type="hidden" id="edithighPrice" name="highPrice">
						<div class="form-group">
							<label for="unit" class="col-sm-3 control-label">单位</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editunit"
									name="unit" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="unit" class="col-sm-3 control-label">规格</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editspecifications" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="unit" class="col-sm-3 control-label">箱内数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editamounts" name="amounts" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="num" class="col-sm-3 control-label">购买数量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editnum" name="num"  placeholder="输入产品数量">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="hNum" class="col-sm-3 control-label">货补数量</label>
							<div class="col-sm-6">
								 <input type="text" class="form-control" id="edithbNum" name="hbNum"  value="0">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<input type="hidden" id="edithbamt" name="hbAmt" value="0">
						<div class="form-group">
							<label for="amt" class="col-sm-3 control-label">订单金额(元)</label>
							<div class="col-sm-6">
								 <input type="text" class="form-control" id="editamt" name="amt"  value="0" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="discountAmt" class="col-sm-3 control-label">折扣金额(元)</label>
							<div class="col-sm-6">
								 <input type="text" class="form-control" id="editdiscountAmt"   value="0" name="discountAmt" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="orderAmt" class="col-sm-3 control-label">应收金额(元)</label>
							<div class="col-sm-6">
								 <input type="text" class="form-control" id="editorderAmt"  name="orderAmt" value="0" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-edit-save" class="btn btn-primary btn-save"
						form="editlineForm">保存</button>
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
				data-target="#addLineModal"> <i
                    class="icon icon-plus"></i> 新增SKU
            </a>
            <a id="btn-edit" class="btn btn-primary  pull-left"
               style="width: auto; margin-right: 5px;"> <i
                    class="icon icon-plus"></i> 修改SKU
            </a> 
            <a id="btn-del" class="btn btn-danger  pull-left"
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
        data-detail-formatter="detailFormatter" data-id-field="id"
        <%-- data-pagination="true" data-page-size="5" data-page-list="[5,8,10]" --%>
        <%--data-pagination-first-text="首页" data-pagination-pre-text="上一页" --%>
        <%--data-pagination-next-text="下一页" data-pagination-last-text="尾页" --%>
        >
        </table>
    </div>
    <%-- 金额统计 --%>
    <div class="col-md-12" style="padding:20px 0;border-bottom:1px solid rgba(0,0,0,.15);margin-top:25px;">
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
    <%-- submit button --%>
    <div class="remark col-md-offset-1 col-md-11">
				<albel>备注:</albel><textarea style="width:80%;min-height:60px; display:block;margin-bottom:20px;" id="remark">${order.remark }</textarea>
	</div>
    <div class="text-center" style="padding-top: 100px;border-top:1px solid rgba(0,0,0,.15); margin-top: 40px;  clear:bodth;">
        <button class="btn btn-warning" style="padding: 8px 25px;"  type="button" id="btn-save"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</button>
       <!--  <button class="btn btn-primary btn-long" type="button" id="btn-save-audit"><i class="icon icon-check"></i>&nbsp;&nbsp;提交</button> -->
    </div>

<!-- Modal -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="static/js/order/specialorderEdit.js"></script>
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

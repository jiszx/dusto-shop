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
<title>管理系统-销售现金确认</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
<style type="text/css">
.customizeWidth {
	width: 70%;
	display: inline-block;
}
</style>
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			销售现金确认<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">客户资金管理</a></li>
			<li class="active">销售现金确认</li>
		</ol>
	</section>
	<div class="row" style="padding: 0 0 20px 0;">
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title"
			style="float: left; line-height: 30px; width: auto !important">打款姓名：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" id="spayName"
				style="padding: 0; width: 70%;"	placeholder="输入打款人名字">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title"
			style="float: left; line-height: 30px; width: auto !important">打款账户：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" id="spayBankNo"
				style="padding: 0; width: 70%;"	placeholder="输入打款账户">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label class=" font12 input-box-list-title"
			style="float: left; line-height: 30px; width: auto !important">销售组织：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance" 
				style="padding: 0; width: 70%;"
				id="sorgid">
					<option></option>
					<c:forEach items="${org}" var="orgs">
						<c:if test="${orgs.levels==2}">
							<option value="${orgs.id}">${orgs.name}</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group  col-md-3 col-sm-6  input-box-list">
			<button class="btn btn-primary" id="btn-search">
				<i class="icon icon-search"></i>&nbsp;&nbsp;开始搜索
			</button>
		</div>
	</div>
	<div class="col-md-12" id="salesUpAccountTool"
		style="padding: 20px 0 0;">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" id="confirm" class="btn btn-info">
				<i class='icon accredit_img'></i>资金确认
			</button>
			<!-- <button type="button" class="btn btn-primary"data-toggle="modal"
				data-target="#editUpaccountModal"><i class='icon icon-plus'></i>修改</button> -->
			<!--  <button id="fundsDetail" type="button" class="btn btn-primary" ><i class='icon icon-plus'></i> 打款明细</button> -->
			<button id="audit" type="button" class="btn btn-primary">
				<i class='icon approval_img'></i>提交审批
			</button>

		</div>
	</div>
	<div class="col-md-12">
		<table id="upAccountTable"></table>
	</div>
	<!-- eidtDialog -->
	<div class="modal fade" id="editUpaccountModal" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">资金确认</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="account/upaccount/salesConfirm" id="editUpaccountForm">
						<div class="form-group">
							<label class="col-sm-3 control-label">所属销售组织</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editorgname"
									name="orgname" readonly>
							</div>
							<input type="hidden" class="form-control" id="editorganizationId"
								name="organizationId"> <input type="hidden"
								class="form-control" id="editid" name="id"> <small
								class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="payName" class="col-sm-3 control-label">打款人名称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editpayName"
									name="payName" readonly>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="payAmt" class="col-sm-3 control-label">打款金额</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editpayAmt"
									name="payAmt" readonly>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="payBank" class="col-sm-3 control-label">打款银行</label>
							<div class="col-sm-6">
								<select class="form-control" name="payBank" id="editpayBank" disabled="disabled">
									<c:forEach items="${dict['BANK']}" var="it">
										<option value="${it.chooseVal}">${it.showText}</option>
									</c:forEach>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="payCity" class="col-sm-3 control-label">打款城市</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editpayCity"
									name="payCity" readonly>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="payBankNo" class="col-sm-3 control-label">卡号（后4位）</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editpayBankNo"
									name="payBankNo" readonly>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="payDate" class="col-sm-3 control-label">来款时间</label>
							<div class="col-sm-6">
								<input type="date" class="form-control" id="editpayDate"
									name="payDate" readonly>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="payType" class="col-sm-3 control-label">资金类型</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editpayTypeName"
									name="payTypeName" readonly>
							</div>
							<input type="hidden" class="form-control" id="editpayType"
								name="payType"> <small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="merchCusId" class="col-sm-3 control-label">客户名称</label>
							<div class="col-sm-6">
								<select class="form-control" name="merchCusId" id="merchCusId">
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="bankSerial" class="col-sm-3 control-label">银行流水号</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="bankSerial" name="bankSerial">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-edit-save"
						class="btn btn-primary btn-save" form="editUpaccountForm">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- showDialog
<div class="modal fade" id="showUpaccountModal" tabindex="-1" aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="showdictLabel">上账明细</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" >
                    <div class="form-group">
                        <label for="payName" class="col-sm-2 control-label">打款人名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showpayName"  readOnly="true" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="payAmt" class="col-sm-2 control-label">打款金额</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showpayAmt"  readOnly="true" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="payBank" class="col-sm-2 control-label">打款银行</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showpayBank"  readOnly="true" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="payCity" class="col-sm-2 control-label">打款城市</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showpayCity"  readOnly="true" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="payBankNo" class="col-sm-2 control-label">卡号</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showpayBankNo" readOnly="true" >
                        </div>
                    </div>
                  
                    <div class="form-group">
                        <label for="payDate" class="col-sm-2 control-label">来款时间</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showpayDate" readOnly="true" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createTs" class="col-sm-2 control-label">打款信息录入时间</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showcreateTs"  readOnly="true" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="creater" class="col-sm-2 control-label">财务操作人员</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showcreater"  readOnly="true" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="salests" class="col-sm-2 control-label">销售确认时间</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showsalests"  readOnly="true" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="salesrep" class="col-sm-2 control-label">销售人员</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="showsalesrep"  readOnly="true" >
                        </div>
                    </div>
                     <div class="form-group">
                    <table id="orderdetails" class="table" border="1">
                    	<thead>
                        <tr>
                            <th>销售组织</th>
                             <th>客户</th>
                              <th>金额</th>
                        </tr>
                        </thead>
                        <tbody id="orderdetail">
                        	
                        </tbody>
                    </table>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>
            </div>
        </div>
    </div>
</div>-->


	<script type="text/javascript">
		function getsalesUpStatesValue(state) {
			var data = new Object();
			<c:forEach items="${dict['MERCH_ACCOUNT_UP_STATES']}" var="payType">
			data["${payType.chooseVal}"] = "${payType.showText}"
			</c:forEach>
			if (data[state]) {
				return data[state];
			} else {
				return "未知";
			}

		}
		function getBank(type) {
			var data = new Object();
			<c:forEach items="${dict['BANK']}" var="bank">
			data["${bank.chooseVal}"] = "${bank.showText}"
			</c:forEach>
			if (data[type]) {
				return data[type];
			} else {
				return "未知";
			}
		}
		function getsalesUpamtType(type) {
			var data = new Object();
			<c:forEach items="${dict['MERCH_UPAMT_TYPE']}" var="payType">
			data["${payType.chooseVal}"] = "${payType.showText}"
			</c:forEach>
			if (data[type]) {
				return data[type];
			} else {
				return "未知";
			}
		}
	</script>

	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="static/js/account/salesUpList.js"></script>
</body>
</html>

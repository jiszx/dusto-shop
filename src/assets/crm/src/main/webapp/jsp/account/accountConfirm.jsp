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
<title>管理系统-资金上账</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			资金上账<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">客户资金管理</a></li>
			<li class="active">资金上账</li>
		</ol>
	</section>
	<div class="row" style="padding: 0 0 20px 0;">
		<div class="form-group col-md-3  col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: 95px !important">客户名称：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入客户名称"
					style="padding: 0; width: 70%;" id="custname">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input2" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: 95px !important">来款账号：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入来款账号"
					style="padding: 0; width: 70%;" id="spayBankNo">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input3" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: 95px !important">来款城市：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入来款城市名称"
					style="padding: 0; width: 70%;" id="spayCity">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: 95px !important">销售组织：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance"
					style="padding: 0; width: 70%;" id="sorgid">
					<option></option>
					<c:forEach items="${org}" var="orgs">
						<option value="${orgs.id}">${orgs.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: 95px !important">现金类型：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance"
					style="padding: 0; width: 70%;" id="spayType">
					<option></option>
				</select>
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input3" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px; width: 95px !important">银行流水号：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" style="padding: 0; width: 70%;" id="sbankSerial">
			</div>
		</div>
		<div class="form-group col-md-offset-3 col-md-3 col-sm-6 input-box-list">
			<button class="btn btn-primary" id="btn-search">
				<i class="icon icon-search"></i>&nbsp;&nbsp;开始搜索
			</button>
		</div>
	</div>
	<div class="col-md-12" id="upAccountTool">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn btn-edit btn-warning">
				<i class='icon icon-edit'></i>修改
			</button>
			<button type="button" class="btn btn-back btn-danger">
				<i class='icon icon-remove'></i>审批驳回
			</button>
			<!-- <button type="button" id="fundsDetail" class="btn btn-info">
				<i class='icon icon-eye-open'></i> 打款明细
			</button> -->
			<button id="submit" type="button" class="btn btn-pass btn-success">
				<i class='icon icon-check'></i>审批通过
			</button>
		</div>
	</div>
	<div class="col-md-12">
		<table id="upAccountTable"></table>
	</div>


	<!-- editDialog-->
	<div class="modal fade" id="editUpaccountModal" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">打款信息修改</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="account/upaccount/edit" id="editUpaccountForm">
						<div class="form-group">
                        <label  class="col-sm-3 control-label">所属销售组织</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editorgname" name="orgname" readonly>
                        </div>
                        <input type="hidden" class="form-control" id="editorganizationId" name="organizationId">
                        <input type="hidden" class="form-control" id="editid" name="id">
                        <small class="help-block col-sm-3"></small>
                    	</div>
						<div class="form-group">
							<label for="payName" class="col-sm-3 control-label">打款人名称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editpayName"
									name="payName" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="payAmt" class="col-sm-3 control-label">打款金额</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editpayAmt"
									name="payAmt" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="payBank" class="col-sm-3 control-label">打款银行</label>
							<div class="col-sm-6">
								<select class="form-control" name="payBank" id="editpayBank">
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
								<input type="text" name="form-control" name="paycity" id="editpayCity" style="line-height:30px;width:100%;">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="payBankNo" class="col-sm-3 control-label">卡号（后4位）</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editpayBankNo"
									name="payBankNo" readonly="readonly">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="payDate" class="col-sm-3 control-label">来款时间</label>
							<div class="col-sm-6">
								<input type="date" class="form-control" id="editpayDate"
									name="payDate">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="payType" class="col-sm-3 control-label">资金类型</label>
							<div class="col-sm-6">
								<select class="form-control" name="payType" id="editpayType">
									<option value="1">现金</option>
									<option value="2">承兑票据</option>
								</select>
							</div>
							<small class="help-block col-sm-3submitz"></small>
						</div>
						<div class="form-group">
							<label for="createTs" class="col-sm-3 control-label">到款录入时间</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editcreateTs"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-3submitz"></small>
						</div>
						<div class="form-group">
							<label for="creater" class="col-sm-3 control-label">录入人员</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editcreater"
									readOnly="readonly">
							</div>
							<small class="help-block col-sm-3submitz"></small>
						</div>
						<div class="form-group">
							<label for="salests" class="col-sm-3 control-label">销售确认时间</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editsalesrepDate"
									readOnly="readonly">
							</div>
							<small class="help-block col-sm-3submitz"></small>
						</div>
						<div class="form-group">
							<label for="salesrep" class="col-sm-3 control-label">销售人员</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editsalesrep"
									readOnly="readonly">
							</div>
							<small class="help-block col-sm-3submitz"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-edit-save" class="btn btn-primary"
						form="editUpaccountForm">保存</button>
				    <!-- <button type="button" class="btn btn-primary"
						id="audit-pass">审批通过</button>
				    <button type="button"  class="btn btn-warning"
						id="audit-back">审批驳回</button> -->
				</div>
			</div>
		</div>
	</div>
	
	<!-- upaccountModal -->
	<div class="modal fade" id="upaccountModal" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">上账信息</h4>
				</div>
				<div class="modal-body">
					 <span id="sapmsg"></span>
					 <input type="hidden" id="upaccountid">
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="btn-pass-agin" class="btn btn-primary">再次推送</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function getUpStatesValue(state) {
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
		function getUpamtType(type) {
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
		function uptype() {
			<c:forEach items="${dict['MERCH_UPAMT_TYPE']}" var="payType">
			$('#payType')
					.append(
							"<option value='${payType.chooseVal}'>${payType.showText}</option>");
			$('#spayType')
					.append(
							"<option value='${payType.chooseVal}'>${payType.showText}</option>");
			</c:forEach>

		}
	</script>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/account/accountConfirm.js"></script>
</body>
</html>

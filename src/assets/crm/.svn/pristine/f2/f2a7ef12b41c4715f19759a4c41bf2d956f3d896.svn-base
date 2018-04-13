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
<title>管理系统-客户上账</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link href="static/bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
	<link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
<style type="text/css">
.dropdown-menu {
	width: 25.3%;
}

.table-condensed {
	width: 100%;
}
.input-box-list-title{width:95px;}
</style>
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			客户打款录入<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">客户资金管理</a></li>
			<li class="active">客户打款录入</li>
		</ol>
	</section>
	<div class="row" style="padding: 0 0 20px 0;">
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input1" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px;">客户名称：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入客户名称"
					style="padding: 0; width: 70%;" id="custname">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input2" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px;">来款账号：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入来款账号"
					style="padding: 0; width: 70%;" id="spayBankNo">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input3" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px;">来款城市：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" placeholder="输入来款城市名称"
					style="padding: 0; width: 70%;" id="spayCity">
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label class=" font12 input-box-list-title"
				style="float: left; line-height: 30px;">销售组织：</label>
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
				style="float: left; line-height: 30px;">现金类型：</label>
			<div class="input-box-list-value">
				<select class="form-control no-appearance"
					style="padding: 0; width: 70%;" id="spayType">
					<option></option>
				</select>
			</div>
		</div>
		<div class="form-group col-md-3 col-sm-6 input-box-list">
			<label for="input3" class=" font12 input-box-list-title"
				style="float: left; line-height: 30px;">银行流水号：</label>
			<div class="input-box-list-value">
				<input type="text" class="form-control" style="padding: 0; width: 70%;" id="sbankSerial">
			</div>
		</div>
		<div class="form-group col-md-offset-3 col-md-3  col-sm-6 input-box-list">
			<button class="btn btn-primary" id="btn-search">
				<i class="icon icon-search"></i>&nbsp;&nbsp;开始搜索
			</button>
		</div>
	</div>
	<div class="col-md-12" id="upAccountTool" style="padding-left:0;">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn btn-primary" data-toggle="modal" 
				data-target="#addUpaccountModal">
				<i class='icon icon-plus'></i>打款信息录入
			</button>
			<button type="button" class="btn btn-edit btn-warning">
				<i class='icon icon-edit'></i>修改
			</button>
			<button type="button" class="btn btn-del btn-danger">
				<i class='icon icon-remove'></i>删除
			</button>
			<button type="button" id="btn-show" class="btn btn-info">
				<i class='icon icon-eye-open'></i> 打款明细
			</button>
			<button id="submit" type="button" class="btn btn-success">
				<i class='icon icon-check'></i>提交
			</button>
		</div>
	</div>
	<div class="col-md-12" style="padding-left:0;">
		<table id="upAccountTable"></table>
	</div>
	<!-- addDialog-->
	<div class="modal fade" id="addUpaccountModal" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">打款信息录入</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="account/upaccount/add" id="addUpaccountForm">
						<div class="form-group">
							<label for="organizationId" class="col-sm-3 control-label">所属销售组织</label>
							<div class="col-sm-6">
								<select class="form-control" name="organizationId"
									id="organizationId">
									<option></option>
									<c:forEach items="${org}" var="orgs">
										<c:if test="${orgs.levels==2}">
											<option value="${orgs.id}">${orgs.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="payAmt" class="col-sm-3 control-label">金额</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="payAmt"
									name="payAmt">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="payType" class="col-sm-3 control-label">资金类型</label>
							<div class="col-sm-6">
								<select class="form-control" name="payType" id="payType">
									<!-- <option value="1">现金</option>
                            		<option value="2">承兑票据</option> -->
									<option></option>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div id="bill" style="display: none">
							<div class="form-group">
								<label for="billOutDate" class="col-sm-3 control-label">出票日期</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="billOutDate"
										name="billOutDate">
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
							<div class="form-group">
								<label for="billInDate" class="col-sm-3 control-label">到票日期</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="billInDate"
										name="billInDate">
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
							<div class="form-group">
								<label for="billNo" class="col-sm-3 control-label">票号</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="billNo"
										name="billNo">
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
							<div class="form-group">
								<label for="billBank" class="col-sm-3 control-label">出票银行</label>
								<div class="col-sm-6">
									<select class="form-control" name="billBank" id="billBank">
										<option></option>
										<c:forEach items="${dict['BANK']}" var="it">
											<option value="${it.chooseVal}">${it.showText}</option>
										</c:forEach>
									</select>
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
							<div class="form-group">
								<label for="billOutName" class="col-sm-3 control-label">出票人</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="billOutName"
										id="billOutName">
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
							<div class="form-group">
								<label for="billInName" class="col-sm-3 control-label">收票人</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="billInName"
										name="billInName">
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
						</div>
						<div id="cash" style="display: none">
							<div class="form-group">
								<label for="payName" class="col-sm-3 control-label">收款账户</label>
								<div class="col-sm-6">
									<select class="form-control" id="bankAccount"
										name="bankAccount">
										<c:forEach items="${dict['CRM_BANK_ACCOUNT']}" var="it">
											<option value="${it.chooseVal}">${it.showText}</option>
										</c:forEach>
									</select>
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
							<div class="form-group">
								<label for="payName" class="col-sm-3 control-label">打款人名称</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="payName"
										name="payName">
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
							<div class="form-group">
								<label for="payBank" class="col-sm-3 control-label">打款银行</label>
								<div class="col-sm-6">
									<select class="form-control" name="payBank" id="payBank">
										<option></option>
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
									<input type="text" class="form-control" name="payCity"
										id="payCity">
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
							<div class="form-group">
								<label for="payBankNo" class="col-sm-3 control-label">卡号（后4位）</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="payBankNo"
										name="payBankNo">
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
							<div class="form-group">
								<label for="payDate" class="col-sm-3 control-label">来款时间</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="payDate"
										name="payDate">
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
						</div>
						<div class="form-group">
								<label for="merchCusId" class="col-sm-3 control-label">客户</label>
								<div class="col-sm-6" style="position:relative;">
									<select class="form-control" name="merchCusId" data-placeholder="请选择客户信息..." id="merchCusId" style="width: 350px;">
									</select>
								</div>
								<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="bankSerial" class="col-sm-3 control-label">流水号</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="bankSerial"
									name="bankSerial">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-add" class="btn btn-primary btn-save"
						form="addUpaccountForm">保存</button>
					<!-- <button type="button" id="btn-add-submit"
						class="btn btn-save btn-primary">提交</button> -->
				</div>
			</div>
		</div>
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
					<h4 class="modal-title" id="dictLabel">来款信息修改</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="account/upaccount/edit" id="editUpaccountForm">
						<div class="form-group">
							<label for="organizationId" class="col-sm-3 control-label">所属销售组织</label>
							<div class="col-sm-6">
								<select class="form-control" name="organizationId"
									id="editorganizationId">
									<c:forEach items="${org}" var="orgs">
										<c:if test="${orgs.levels==2}">
											<option value="${orgs.id}">${orgs.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="payAmt" class="col-sm-3 control-label">金额</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editpayAmt"
									name="payAmt">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="payType" class="col-sm-3 control-label">资金类型</label>
							<div class="col-sm-6">
								<select class="form-control" name="payType" id="editpayType">
									<option></option>
								</select>
							</div>
							<small class="help-block col-sm-3submitz"></small>
						</div>
						<div id="editbill" style="display: none">
							<div class="form-group">
								<label for="billOutDate" class="col-sm-3 control-label">出票日期</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="editbillOutDate"
										name="billOutDate">
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
							<div class="form-group">
								<label for="billInDate" class="col-sm-3 control-label">到票日期</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="editbillInDate"
										name="billInDate">
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
							<div class="form-group">
								<label for="billNo" class="col-sm-3 control-label">票号</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="editbillNo"
										name="billNo">
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
							<div class="form-group">
								<label for="billBank" class="col-sm-3 control-label">出票银行</label>
								<div class="col-sm-6">
									<select class="form-control" name="billBank" id="editbillBank">
										<option></option>
										<c:forEach items="${dict['BANK']}" var="it">
											<option value="${it.chooseVal}">${it.showText}</option>
										</c:forEach>
									</select>
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
							<div class="form-group">
								<label for="billOutName" class="col-sm-3 control-label">出票人</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="billOutName"
										id="editbillOutName">
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
							<div class="form-group">
								<label for="billInName" class="col-sm-3 control-label">收票人</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="editbillInName"
										name="billInName">
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
						</div>
						<div id="editcash" style="display: none">
							<div class="form-group">
								<label for="bankAccount" class="col-sm-3 control-label">收款账户</label>
								<div class="col-sm-6">
									<select class="form-control" id="editbankAccount"
										name="bankAccount">
										<c:forEach items="${dict['CRM_BANK_ACCOUNT']}" var="it">
											<option value="${it.chooseVal}" >${it.showText}</option>
										</c:forEach>
									</select>
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
							<div class="form-group">
								<label for="payName" class="col-sm-3 control-label">打款人名称</label>
								<input type="hidden" id="editid" name="id">
								<div class="col-sm-6">
									<input type="text" class="form-control" id="editpayName"
										name="payName">
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
							<div class="form-group">
								<label for="payBank" class="col-sm-3 control-label">打款银行</label>
								<div class="col-sm-6">
									<select class="form-control" name="payBank" id="editpayBank">
										<option></option>
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
									<input type="text" class="form-control" name="payCity"
										id="editpayCity">
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
							<div class="form-group">
								<label for="payBankNo" class="col-sm-3 control-label">卡号（后4位）</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="editpayBankNo"
										name="payBankNo">
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
							<div class="form-group">
								<label for="editpayDate" class="col-sm-3 control-label">来款时间</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="editpayDate"
										name="payDate">
								</div>
								<small class="help-block col-sm-3"></small>
							</div>
						</div>
						<div class="form-group">
								<label for="editmerchCusId" class="col-sm-3 control-label">客户</label>
								<div class="col-sm-6">
									<select class="form-control" name="merchCusId" data-placeholder="请选择客户信息..." id="editmerchCusId" style="width: 350px;">
									</select>
								</div>
								<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="bankSerial" class="col-sm-3 control-label">流水号</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editbankSerial"
									name="bankSerial">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-edit-save" class="btn btn-primary"
						form="editUpaccountForm">保存</button>
					<!-- <button type="button" id="btn-edit-submit"
						class="btn btn-save btn-primary">提交</button> -->
				</div>
			</div>
		</div>
	</div>
	<!-- showDialog-->
	<div class="modal fade" id="showUpaccountModal" tabindex="-1"
		aria-labelledby="dictLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="showdictLabel">來款明细</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="organizationId" class="col-sm-3 control-label">所属销售组织</label>
							<div class="col-sm-9">
								<select class="form-control" name="organizationId"
									id="editorganizationId" required="required" disabled="disabled">
									<c:forEach items="${org}" var="orgs">
										<c:if test="${orgs.levels==2}">
											<option value="${orgs.id}">${orgs.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
								<label for="showmerchCusId" class="col-sm-3 control-label">客户</label>
								<div class="col-sm-9">
									<select class="form-control"  id="showmerchCusId"  disabled="disabled">
									</select>
								</div>
								<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="payAmt" class="col-sm-3 control-label">金额</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="showpayAmt"	 readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="payType" class="col-sm-3 control-label">资金类型</label>
							<div class="col-sm-9">
								<select class="form-control"  id="showpayType" disabled="disabled">
									<option></option>
								</select>
							</div>
						</div>
						<div id="showbill" style="display: none">
							<div class="form-group">
								<label for="payName" class="col-sm-3 control-label">出票日期</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="showbillOutDate" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="payName" class="col-sm-3 control-label">到票日期</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="showbillInDate"	 readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="payAmt" class="col-sm-3 control-label">票号</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="showbillNo"	 readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="payBank" class="col-sm-3 control-label">出票银行</label>
								<div class="col-sm-9">
									<select class="form-control"  id="showbillBank" disabled="disabled">
										<c:forEach items="${dict['BANK']}" var="it">
											<option value="${it.chooseVal}">${it.showText}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="payCity" class="col-sm-3 control-label">出票人</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="showbillOutName" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="payBankNo" class="col-sm-3 control-label">收票人</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="showbillInName" readonly="readonly">
								</div>
							</div>
						</div>
						<div id="showcash" style="display: none">
							<div class="form-group">
								<label for="payName" class="col-sm-3 control-label">收款账户</label>
								<div class="col-sm-9">
									<select class="form-control" id="showbankAccount" disabled="disabled">
										<c:forEach items="${dict['CRM_BANK_ACCOUNT']}" var="it">
											<option value="${it.chooseVal}" >${it.showText}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="payName" class="col-sm-3 control-label">打款人名称</label>
								<input type="hidden" id="editid" name="id">
								<div class="col-sm-9">
									<input type="text" class="form-control" id="showpayName" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="payBank" class="col-sm-3 control-label">打款银行</label>
								<div class="col-sm-9">
									<select class="form-control"  id="showpayBank" disabled="disabled">
										<c:forEach items="${dict['BANK']}" var="it">
											<option value="${it.chooseVal}">${it.showText}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="payCity" class="col-sm-3 control-label">打款城市</label>
								<div class="col-sm-9">
									<input type="text" class="form-control"	id="showpayCity" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="payBankNo" class="col-sm-3 control-label">卡号（后4位）</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="showpayBankNo" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="editpayDate" class="col-sm-3 control-label">来款时间</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="showpayDate" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="bankSerial" class="col-sm-3 control-label">流水号</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="showbankSerial" readonly="readonly">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
			$('#payType').append("<option value='${payType.chooseVal}'>${payType.showText}</option>");
			$('#editpayType').append("<option value='${payType.chooseVal}'>${payType.showText}</option>");
			$('#showpayType').append("<option value='${payType.chooseVal}'>${payType.showText}</option>");
			$('#spayType').append("<option value='${payType.chooseVal}'>${payType.showText}</option>");
			</c:forEach>

		}
	</script>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript"
		src="static/bootstrap/js/bootstrap-datetimepicker.min.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"
		charset="UTF-8"></script>
		<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="static/js/account/upAccount.js"></script>
</body>
</html>

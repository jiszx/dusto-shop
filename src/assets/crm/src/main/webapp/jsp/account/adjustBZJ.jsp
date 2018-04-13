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
<title>管理系统-保证金管理</title>
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
			客户保证金管理 <small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">客户资金管理</a></li>
			<li class="active">客户保证金管理</li>
		</ol>
	</section>
	<div class="col-md-12 col-sm-12" style="padding: 0">
		<div class="form-group col-md-3 col-sm-6">
			<label>客户名称:</label> <input type="text"
				class="form-control customizeWidth" placeheader="请输入客户名称"
				id="scustname">
			<input name="saccountType" type="hidden" id="saccountType" value="4">
		</div>
		<%--<div class="form-group col-md-3 col-sm-6">
			<label>账户类型:</label> 
		     <select class="form-control customizeWidth"
				id="saccountType">
				<option></option>
				<c:forEach items="${dict['MERCH_ACCOUNT_TYPE']}" var="type">
					<option value='${type.chooseVal}'>${type.chooseVal=='3'?"授信额度":type.showText}</option>
			    </c:forEach>
			    <option value="5">授信金额</option>
			</select>
		</div>--%>
		<%--<div class="form-group col-md-3 col-sm-6">
			<label>销售组织:</label> <select class="form-control customizeWidth" id="sorgid">
				<option></option>
				<c:forEach items="${org}" var="orgs">
					<c:if test="${orgs.levels==2}">
						<option value="${orgs.id}">${orgs.name}</option>
					</c:if>
				</c:forEach>
			</select>
		</div>--%>
		<div class="form-group col-md-3 col-sm-6 col-md-offset-6">
			<button type="button" class="btn btn-primary" id="btn-search">
				<i class="icon icon-search">&nbsp;&nbsp;查询</i>
			</button>
		</div>
	</div>
	<div class="col-md-12 col-sm-12" id="dictTool">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#addAdjustModal">
				<i class='icon icon-plus'></i>保证金录入
			</button>
			<button type="button" class="btn btn-edit btn-warning ">
				<i class='icon icon-edit'></i>修改
			</button>
			<button type="button" class="btn btn-del btn-danger">
				<i class='icon icon-remove'></i> 删除
			</button>
			<button type="button" id="btn-audit" class="btn  btn-success">
				<i class='icon icon-check'></i> 提交审批
			</button>
		</div>
	</div>
	<div class="col-md-12 col-sm-12">
		<table id="adjustTable"></table>
	</div>

	<!-- addDialog-->
	<div class="modal fade" id="addAdjustModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">客户保证金录入</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="account/adjust/add" id="addAdjustForm">
						<div class="form-group">
							<label for="organizationId" class="col-sm-3 control-label">销售组织</label>
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
							<label for="merchCustId" class="col-sm-3 control-label">客户</label>
							<div class="col-sm-6">
								<select class="form-control" id="merchCustId" name="merchCustId" data-placeholder="请选择客户信息..." >
								</select><!-- <span class="hide nonull">不能为空</span> -->
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="amt" class="col-sm-3 control-label">金额（元）</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="amt" name="amt" >
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group hide">
							<label for="accountType" class="col-sm-3 control-label">调整类型</label>
							<div class="col-sm-6">
								<input type="hidden" name="accountType" id="accountType" value="4">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="bankAccount" class="col-sm-3 control-label">银行账户</label>
							<div class="col-sm-6">
								<select class="form-control" id="bankAccount" name="bankAccount">
									<option></option>
									<c:forEach items="${dict['CRM_BANK_ACCOUNT']}" var="it">
										<option value="${it.chooseVal}">${it.showText}</option>
									</c:forEach>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="reason" class="col-sm-3 control-label">调整原因</label>
							<div class="col-sm-6">
								<select class="form-control" name="reason" id="reason">
									<c:forEach items="${dict['ACCOUNT_ADJUST_REASON']}" var="it">
										<option value="${it.chooseVal}" >${it.showText}</option>
									</c:forEach>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="remark" class="col-sm-3 control-label">备注</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="remark"
									name="remark">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-add" class="btn btn-primary btn-save"
						form="addAdjustForm">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- editDialog-->
	<div class="modal fade" id="editAdjustModal" tabindex="-1"
		aria-labelledby="dictLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="editdictLabel">修改保证金信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="account/adjust/edit"
						method="post" id="editAdjustForm">
						<div class="form-group">
							<label for="organizationId" class="col-sm-3 control-label">销售组织</label>
							<div class="col-sm-6">
								<select class="form-control" name="organizationId"
									id="editorganizationId">
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
							<label for="editmerchCustId" class="col-sm-3 control-label">客户</label>
							<input type="hidden" id="editid" name="id">
							<div class="col-sm-6">
								<select class="form-control" id="editmerchCustId"
									name="merchCustId" data-placeholder="请选择客户信息..." >
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group hide">
							<label for="source" class="col-sm-3 control-label">调整类型</label>
							<div class="col-sm-6">
								<select class="form-control" id="editaccountType" name="accountType">
									<option></option>
									<c:forEach items="${dict['MERCH_ACCOUNT_TYPE']}" var="type">
										<option value='${type.chooseVal}'>${type.chooseVal=='3'?"授信额度":type.showText}</option>
			   						</c:forEach>
			    					<option value="5">授信金额</option>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
								<label for="bankAccount" class="col-sm-3 control-label">银行账户</label>
								<div class="col-sm-6">
									<select class="form-control" id="editbankAccount" name="bankAccount">
										<c:forEach items="${dict['CRM_BANK_ACCOUNT']}" var="it">
											<option value="${it.chooseVal}">${it.showText}</option>
										</c:forEach>
									</select>
								</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="amt" class="col-sm-3 control-label">金额（元）</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editamt" name="amt">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="reason" class="col-sm-3 control-label">调整原因</label>
							<div class="col-sm-6">
								<select class="form-control" name="reason" id="editreason">
									<c:forEach items="${dict['ACCOUNT_ADJUST_REASON']}" var="it">
										<option value="${it.chooseVal}" >${it.showText}</option>
									</c:forEach>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="remark" class="col-sm-3 control-label">备注</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editremark"
									name="remark">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-edit-save" form="editAdjustForm"
						class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function getAdjustStates(state) {
			var data = new Object();
			<c:forEach items="${dict['MERCH_ADJUST_STATES']}" var="states">
			data["${states.chooseVal}"] = "${states.showText}"
			</c:forEach>
			if (data[state]) {
				return data[state];
			} else {
				return "未知";
			}
		}
		function getAdjustReason(state) {
			var data = new Object();
			<c:forEach items="${dict['ACCOUNT_ADJUST_REASON']}" var="states">
			data["${states.chooseVal}"] = "${states.showText}"
			</c:forEach>
			if (data[state]) {
				return data[state];
			} else {
				return "未知";
			}
		}
		function getaccountType(type) {
			var data = new Object();
			<c:forEach items ="${dict['MERCH_ACCOUNT_TYPE']}" var ="accountType">
			 data["${accountType.chooseVal}"] = "${accountType.showText}"
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
	<script type="text/javascript" src="static/js/account/adjustBZJ.js"></script>
</body>
</html>

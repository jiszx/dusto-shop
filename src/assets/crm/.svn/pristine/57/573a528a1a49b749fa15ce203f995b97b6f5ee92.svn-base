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
<title>管理系统-销售政策管理</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			政策类型维护<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 销售政策</a></li>
			<li class="active">政策维护</li>
		</ol>
	</section>
	<div class="col-md-12" id="promotionInTool" style="padding:0;">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn  btn-info" data-toggle="modal"data-target="#addPolicyTypeModal">
				<i class='icon icon-plus'></i>新增
			</button>
			<button type="button" class="btn btn-edit btn-warning">
				<i class='icon icon-edit'></i>修改
			</button>
			<button type="button" class="btn btn-del btn-danger">
				<i class='icon icon-remove'></i> 删除
			</button>
			<!-- <button type="button" class="btn btn-success">
				<i class='icon icon-check'></i>提交审批
			</button> -->
		</div>
	</div>
	<div class="col-md-12">
		<table id="policyTypeTable"></table>
	</div>
	<!-- addDialog-->
	<div class="modal fade" id="addPolicyTypeModal" tabindex="-1"
		role="dialog" aria-labelledby="promotionLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">新增政策类型</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="salepolicy/policyType/addPolicyType"
						id="addPolicyTypeForm">
						<div class="form-group">
							<label for="name" class="col-sm-3 control-label">类型描述</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" name="name" id="name">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="type" class="col-sm-3 control-label">类型区分</label>
							<div class="col-sm-5">
								<select class="form-control" id="type" name="type">
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div id="typeid" style="display: none">
							<div class="form-group">
								<label for="benchmark" class="col-sm-3 control-label">类型基准</label>
								<div class="col-sm-5">
									<select class="form-control" name="benchmark" id="benchmark"></select>
								</div>
								<small class="help-block col-sm-4"></small>
							</div>
							<div id="cassessment" style="display: none">
								<div class="form-group">
									<label for="assessment" class="col-sm-3 control-label">考核方式</label>
									<div class="col-sm-5">
										<select class="form-control" name="assessment" id="assessment"></select>
									</div>
									<small class="help-block col-sm-4"></small>
								</div>
							</div>
							<div id="cassessmentTarget" style="display: none">
								<div class="form-group">
									<label for="assessmentTarget" class="col-sm-3 control-label"
										id="targethtml">考核目标</label>
									<div class="col-sm-5" id="targettype">
										<input type="text" class="form-control" id="assessmentTarget"
											name="assessmentTarget">
									</div>
									<small class="help-block col-sm-4"></small>
								</div>
							</div>
							<div class="form-group">
								<label for="verification" class="col-sm-3 control-label">核销方式</label>
								<div class="col-sm-5">
									<select class="form-control" id="verification"
										name="verification">
									</select>
								</div>
								<small class="help-block col-sm-4"></small>
							</div>
							<div class="form-group" style="display: none">
								<label for="verificationDiscount" class="col-sm-3 control-label"
									id="discountname">奖励</label>
								<div class="col-sm-5" id="discounttype">
									<input type="text" class="form-control"
										id="verificationDiscount" name="verificationDiscount">
								</div>
								<small class="help-block col-sm-4"></small>
							</div>
							<div class="form-group" id="discountnum" style="display: none">
								<label for="verificationDiscountNum"
									class="col-sm-3 control-label" id="num">数量</label>
								<div class="col-sm-5">
									<input type="text" class="form-control"
										id="verificationDiscountNum" name="verificationDiscountNum">
								</div>
								<small class="help-block col-sm-4"></small>
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="col-sm-3 control-label">备注</label>
							<div class="col-sm-5" id="">
								<textarea class="form-control" id="description"
									name="description"></textarea>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn_add" form="addPolicyTypeForm"
						class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- editDialog-->
	<div class="modal fade" id="editPolicyTypeModal" tabindex="-1"
		role="dialog" aria-labelledby="promotionLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">修改政策类型</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="salepolicy/policyType/editPolicyType"
						id="editPolicyTypeForm">
						<input type="hidden" id="editid" name="id">
						<div class="form-group">
							<label for="name" class="col-sm-3 control-label">类型描述</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" name="name"
									id="editname">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>

						<div class="form-group">
							<label for="type" class="col-sm-3 control-label">类型区分</label>
							<div class="col-sm-5">
								<select class="form-control" id="edittype" name="type">
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div id="edittypeid" style="display: none">
							<div class="form-group">
								<label for="benchmark" class="col-sm-3 control-label">类型基准</label>
								<div class="col-sm-5">
									<select class="form-control" name="benchmark"
										id="editbenchmark"></select>
								</div>
								<small class="help-block col-sm-4"></small>
							</div>
							<div id="ceidtassessment" style="display: none">
								<div class="form-group">
									<label for="assessment" class="col-sm-3 control-label">考核方式</label>
									<div class="col-sm-5">
										<select class="form-control" name="assessment"
											id="editassessment"></select>
									</div>
									<small class="help-block col-sm-4"></small>
								</div>
							</div>
							<div class="form-group" id="ceditassessmentTarget" style="display: none">
								<label for="assessmentTarget" class="col-sm-3 control-label"
									id="edittargethtml">考核目标</label>
								<div class="col-sm-5" id="edittargettype">
									<input type="text" class="form-control"
										id="editassessmentTarget" name="assessmentTarget">
								</div>
								<small class="help-block col-sm-4"></small>
							</div>
							<div class="form-group">
								<label for="verification" class="col-sm-3 control-label">核销方式</label>
								<div class="col-sm-5">
									<select class="form-control" id="editverification"
										name="verification">
									</select>
								</div>
								<small class="help-block col-sm-4"></small>
							</div>
							<div class="form-group" style="display: none">
								<label for="verificationDiscount" class="col-sm-3 control-label"
									id="editdiscountname">奖励</label>
								<div class="col-sm-5" id="editdiscounttype">
									<input type="text" class="form-control"
										id="verificationDiscount" name="verificationDiscount">
								</div>
								<small class="help-block col-sm-4"></small>
							</div>
							<div class="form-group" id="editdiscountnum"
								style="display: none">
								<label for="verificationDiscountNum"
									class="col-sm-3 control-label" id="editnum">数量</label>
								<div class="col-sm-5">
									<input type="text" class="form-control"
										id="editverificationDiscountNum"
										name="verificationDiscountNum">
								</div>
								<small class="help-block col-sm-4"></small>
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="col-sm-3 control-label">备注</label>
							<div class="col-sm-5" id="">
								<textarea class="form-control" id="editdescription"
									name="description"></textarea>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-edit-save" form="editPolicyTypeForm"
						class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		//核销方式
		function getverificationType(type) {
			var data = new Object();
			<c:forEach items="${dict['POLICY_TYPE_VERIFICATION']}" var="verification">
			data["${verification.chooseVal}"] = "${verification.showText}"
			</c:forEach>
			if (data[type]) {
				return data[type];
			} else {
				return "未知";
			}
		}
		function getpolicy() {
			//区分
			<c:forEach items="${dict['POLICY_TYPE']}" var="type">
			$('#type')
					.append(
							"<option value='${type.chooseVal}'>${type.showText}</option>");
			</c:forEach>
			//基准
			<c:forEach items="${dict['POLICY_TYPE_BENCHMARK']}" var="benchmark">
			$('#benchmark')
					.append(
							"<option value='${benchmark.chooseVal}'>${benchmark.showText}</option>");
			</c:forEach>

			//考核方式
			<c:forEach items="${dict['POLICY_TYPE_ASSESSMENT']}" var="assessment">
			$('#assessment')
					.append(
							"<option value='${assessment.chooseVal}'>${assessment.showText}</option>");
			</c:forEach>
		}
		function getOrderVerification() {
			//核销方式
			<c:forEach items="${dict['POLICY_TYPE_VERIFICATION']}" var="verification">
			$('#verification')
					.append(
							"<option value='${verification.chooseVal}'>${verification.showText}</option>");
			</c:forEach>

		}
		function getEditOrderVerification() {
			//核销方式
			<c:forEach items="${dict['POLICY_TYPE_VERIFICATION']}" var="verification">
			$('#editverification')
					.append(
							"<option value='${verification.chooseVal}'>${verification.showText}</option>");
			</c:forEach>
		}
		function geteditpolicy() {
			//区分
			<c:forEach items="${dict['POLICY_TYPE']}" var="type">
			$('#edittype')
					.append(
							"<option value='${type.chooseVal}'>${type.showText}</option>");
			</c:forEach>
			//基准
			<c:forEach items="${dict['POLICY_TYPE_BENCHMARK']}" var="benchmark">
			$('#editbenchmark')
					.append(
							"<option value='${benchmark.chooseVal}'>${benchmark.showText}</option>");
			</c:forEach>

			//考核方式
			<c:forEach items="${dict['POLICY_TYPE_ASSESSMENT']}" var="assessment">
			$('#editassessment')
					.append(
							"<option value='${assessment.chooseVal}'>${assessment.showText}</option>");
			</c:forEach>
		}
		//基准
		function getbenchmarkType(type) {
			var data = new Object();
			<c:forEach items="${dict['POLICY_TYPE_BENCHMARK']}" var="benchmark">
			data["${benchmark.chooseVal}"] = "${benchmark.showText}"
			</c:forEach>
			if (data[type]) {
				return data[type];
			} else {
				return "未知";
			}

		}
		//政策区分
		function getType(type) {
			var data = new Object();
			<c:forEach items="${dict['POLICY_TYPE']}" var="type">
			data["${type.chooseVal}"] = "${type.showText}"
			</c:forEach>
			if (data[type]) {
				return data[type];
			} else {
				return "未知";
			}

		}
		//考核方式
		function getassessmentType(type) {
			var data = new Object();
			<c:forEach items="${dict['POLICY_TYPE_ASSESSMENT']}" var="assessment">
			data["${assessment.chooseVal}"] = "${assessment.showText}"
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
	<script type="text/javascript" src="static/js/salepolicy/policytype.js"></script>
</body>
</html>

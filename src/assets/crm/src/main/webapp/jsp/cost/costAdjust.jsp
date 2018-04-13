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
<title>管理系统-费用调整</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link href="static/css/search-bar.css" rel="stylesheet" type="text/css" />

</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			费用调整<small> </small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 费用管理</a></li>
			<li class="active">费用池调整</li>
		</ol>
	</section>
	<div class="form-horizontal row" style="padding: 0 0 20px 0;border-bottom:1px solid hsla(255,0%,90%,1); ">
	    <div class="form-group input-box-list col-sm-6 col-md-3">
	        <label class=" font12 input-box-list-title">销售组织：</label>
	        <div class="input-box-list-value">
	            <select id="selectorg" class="form-control no-appearance" onchange="changeRegin(this.value)">
	            	<option value="">全部</option>
	            	<c:forEach items="${org}" var="orgs">
						<c:if test="${orgs.levels==2}">
							<option value="${orgs.id}">${orgs.name}</option>
						</c:if>
					</c:forEach>
	            </select> <!-- <i class="icon icon-caret-down" style="float: right; margin: -25px 10px 0 0;"></i> -->
	        </div>
	    </div>
	    <div class="form-group input-box-list col-sm-6 col-md-2">
	        <label class=" font12 input-box-list-title">大区：</label>
	        <div class="input-box-list-value">
	            <select id="selectregin" class="form-control no-appearance">
	            	<option value="">全部</option>
	            </select> <!-- <i class="icon icon-caret-down" style="float: right; margin: -25px 10px 0 0;"></i> -->
	        </div>
	    </div>
	    <div class="form-group  input-box-list col-sm-6 col-md-2">
	        <label class=" font12 input-box-list-title">费用类型：</label>
	        <div class="input-box-list-value">
	            <select id="selectcost" class="form-control no-appearance">
	            		<option value="">全部</option>
	            	<c:forEach items="${dict['COST_TYPE']}" var="costType">
						<option value='${costType.chooseVal}'>${costType.showText}</option>
					</c:forEach>
	            </select> <i class="icon icon-caret-down" style="float: right; margin: -25px 10px 0 0;"></i>
	        </div>
	    </div>
	    <div class="form-group col-sm-6 col-md-2 input-box-list">
	        <button id="searchButton" class="btn btn-primary"><i class="icon icon-search"></i>&nbsp;&nbsp;查询</button>
	    </div>
	</div>
	<div class="col-md-12" id="costAdjustTool" style="padding:0;">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#addCostAdjustModal">
				<i class='icon icon-folder-close'></i> 手工调整
			</button>
			<button id="editButton" type="button" class="btn btn-warning" >
				<i class='icon icon-edit'></i> 修改
			</button>
			<button id="deleteButton" type="button" class="btn btn-danger" data-toggle="modal"
				data-target="#addDictModal">
				<i class='icon icon-remove'></i> 删除
			</button>
			<button id="censorButton" type="button" class="btn btn-info">
				<i class='icon approval_img'></i> 提交审批
			</button>
		</div>
	</div>
	<div class="col-md-12">
		<table id="costAdjustTable"></table>
	</div>

	<!-- addDialog-->
	<div class="modal fade" id="addCostAdjustModal" tabindex="-1"
		role="dialog" aria-labelledby="dictLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">费用调整</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="cost/adjust/add" id="addCostAdjustForm"
						style="padding-left: 64px;">
						<div class="form-group">
							<label for="organizationId" class="col-sm-3 control-label">销售组织:</label>
							<div class="col-sm-5">
								<select class="form-control" name="organizationId" id="organizationId" onchange="changeFormRegin(this.value)">
									<option value=""></option>
					            	<c:forEach items="${org}" var="orgs">
										<c:if test="${orgs.levels==2}">
											<option value="${orgs.id}">${orgs.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="costTypeid" class="col-sm-3 control-label">费用类型:</label>
							<div class="col-sm-5">
								<select class="form-control" name="costTypeid" id="costTypeid">
									<option></option>
									<c:forEach items="${dict['COST_TYPE']}" var="costType">
										<option value='${costType.chooseVal}'>${costType.showText}</option>
									</c:forEach>
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="reginId" class="col-sm-3 control-label">大区:</label>
							<div class="col-sm-5">
								<select class="form-control" name="reginId" id="reginId" onchange="changeFormProv(this.value)">
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="provId" class="col-sm-3 control-label">省区:</label>
							<div class="col-sm-5">
								<select class="form-control" name="provId" id="provId">
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="adjustAmt" class="col-sm-3 control-label">调整金额(元):</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="adjustAmt"
									name="adjustAmt">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="reason" class="col-sm-3 control-label">调整原因:</label>
							<div class="col-sm-5">
								<select class="form-control" name="reason" id="reason">
									<option value="1">原因1</option>
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-add" class="btn btn-primary btn-save"
						form="addCostAdjustForm">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- editDialog -->
	<div class="modal fade" id="editModal" tabindex="-1"
		role="dialog" aria-labelledby="dictLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">费用调整</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="cost/adjust/edit.json" id="editForm"
						style="padding-left: 64px;">
						<input type="hidden" class="form-control" id="editid" name="id">
						<div class="form-group">
							<label for="organizationId" class="col-sm-3 control-label">销售组织:</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="editorgname" name="" readonly>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="costTypeid" class="col-sm-3 control-label">费用类型:</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="editcostTypeid" name="" readonly>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="reginId" class="col-sm-3 control-label">大区:</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="editreginname" name="" readonly>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="provId" class="col-sm-3 control-label">省区:</label>
							<div class="col-sm-5">
								<input class="form-control" name="provname" id="editprovname" readonly/>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="adjustAmt" class="col-sm-3 control-label">调整金额(元):</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="editadjustAmt"
									name="adjustAmt">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="reason" class="col-sm-3 control-label">调整原因:</label>
							<div class="col-sm-5">
								<select class="form-control" name="reason" id="editreason">
									<option value="1">原因1</option>
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-add" class="btn btn-primary btn-save"
						form="editForm">保存</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function getCostType(type) {
			var data = new Object();
			<c:forEach items="${dict['COST_TYPE']}" var="payType">
			data["${payType.chooseVal}"] = "${payType.showText}"
			</c:forEach>
			if (data[type]) {
				return data[type];
			} else {
				return "未知";
			}
		}
		function getAdjustType(type) {
			var data = new Object();
			<c:forEach items="${dict['CRM_COST_ADJUST_TYPE']}" var="payType">
			data["${payType.chooseVal}"] = "${payType.showText}"
			</c:forEach>
			if (data[type]) {
				return data[type];
			} else {
				return "未知";
			}
		}
		function getStatus(type) {
			var data = new Object();
			<c:forEach items="${dict['CRM_COST_ADJUST_STATES']}" var="payType">
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
	<script type="text/javascript" src="static/js/cost/costAdjust.js"></script>
</body>
</html>

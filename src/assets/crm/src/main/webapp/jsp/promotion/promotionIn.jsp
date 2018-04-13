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
<title>管理系统-促销品管理</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<style type="text/css">
.customizeWidth {
	width: 60%;
	display: inline-block;
}
</style>
</head>
<body class="container-fluid content">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			促销品入库<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 销售政策</a></li>
			<li class="active">促销品入库</li>
		</ol>
	</section>
	<div class="col-md-12" style="padding: 0;">
		<div class="form-group col-md-3 col-sm-6">
			<label>产品名称:</label> <input type="text" id="searchname"
				class="form-control customizeWidth" placeheader="请输入产品名称">
		</div>
		<div class="form-group col-md-3 col-sm-6">
			<label>所属销售组织:</label> 
			<select class="form-control customizeWidth" id="searchorgid">
			    <option></option>
				<c:forEach items="${org}" var="orgs">
					<option value="${orgs.id}">${orgs.name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group col-md-3 col-sm-6">
			<label>库房名称:</label> <select class="form-control customizeWidth" id="searchstoresid">
			</select>
		</div>
		<div class="form-group col-md-3 col-sm-6">
			<label>采购方名称:</label> <select class="form-control customizeWidth" id="searchpurid">
			</select>
		</div>
		<div class="form-group col-md-3 col-sm-6" style="margin-left:29px;">
			<label>状态:</label> <select class="form-control customizeWidth" id="searchstates">
			</select>
		</div>
		<div class="form-group col-md-3 col-sm-6" id="serchBtn"  style="float:right; margin-left:-29px;">
			<button type="button" class="btn btn-primary" id="btn-search">
				<i class="icon icon-search">&nbsp;&nbsp;查询</i>
			</button>
		</div>
	</div>
	<div class="col-md-12" id="promotionApplyTool" style="padding: 0;">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<button type="button" class="btn  btn-info" data-toggle="modal"
				data-target="#addpromotionApplyModal">
				<i class='icon icon-plus'></i> 入库申请
			</button>
			<button type="button" class="btn btn-edit btn-warning">
				<i class='icon icon-edit'></i> 修改申请
			</button>
			<button type="button" class="btn btn-del btn-danger">
				<i class='icon icon-remove'></i> 删除申请
			</button>
			<button type="button" class="btn btn-success" id="btn-audit">
				<i class='icon icon-check'></i> 提交审批
			</button>
		</div>
	</div>
	<div class="col-md-12">
		<table id="promotionApplyTable"></table>
	</div>

	<!-- addDialog-->
	<div class="modal fade" id="addpromotionApplyModal" tabindex="-1"
		role="dialog" aria-labelledby="promotionLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">促销品入库申请</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="promotion/inv/addApply" id="addPromotionApplyForm">
						<div class="form-group">
							<label for="organizationId" class="col-sm-3 control-label">所属销售组织</label>
							<input type="hidden" name="type" id="type" value="1">
							<div class="col-sm-5">
								<select class="form-control" id="organizationId"
									name="organizationId">
									<option></option>
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
							<label for="storesId" class="col-sm-3 control-label">入库库房</label>
							<div class="col-sm-5">
								<select class="form-control" id="storesId" name="storesId">
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="promotionId" class="col-sm-3 control-label">促销品名称</label>
							<div class="col-sm-5">
								<select class="form-control" id="promotionId" name="promotionId">
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="purId" class="col-sm-3 control-label">采购方名称</label>
							<div class="col-sm-5">
								<select class="form-control" id="purId" name="purId">
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="price" class="col-sm-3 control-label">价格</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="price" value="0"
									name="price" onkeyup="calculation()" readonly="readonly">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="num" class="col-sm-3 control-label">采购数量</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="num" name="num"
									onkeyup="calculation()">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="amt" class="col-sm-3 control-label">采购金额</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="amt" name="amt"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer" style="text-align:center;">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn_add" form="addPromotionApplyForm"
						class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- editDialog-->
	<div class="modal fade" id="editPromotionApplyModal" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">促销品入库申请修改</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="promotion/inv/editApply" id="editPromotionApplyForm">
						<div class="form-group">
							<label for="organizationId" class="col-sm-3 control-label">所属销售组织</label>
							<!-- <input type="hidden" name="type" id="eidttype" value="1"> -->
							<input type="hidden" name="id" id="editid">
							<div class="col-sm-5">
								<select class="form-control" id="editorganizationId"
									name="organizationId">
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
							<label for="storesId" class="col-sm-3 control-label">入库库房</label>
							<div class="col-sm-5">
								<select class="form-control" id="editstoresId" name="storesId">
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="promotionId" class="col-sm-3 control-label">促销品名称</label>
							<div class="col-sm-5">
								<select class="form-control" id="editpromotionId" name="promotionId">
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="purId" class="col-sm-3 control-label">采购方名称</label>
							<div class="col-sm-5">
								<select class="form-control" id="editpurId" name="purId">
								</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="price" class="col-sm-3 control-label">价格</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="editprice" value="0"
									name="price" onkeyup="calculation()" readonly="readonly">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="num" class="col-sm-3 control-label">采购数量</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="editnum" name="num"
									onkeyup="calculation()">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="amt" class="col-sm-3 control-label">采购金额</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="editamt" name="amt"
									readonly="readonly">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-edit-save" class="btn btn-primary"
						form="editPromotionApplyForm">保存</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function getPromotionInValue(state) {
			var data = new Object();
			<c:forEach items="${dict['T_PROMOTION_STATES']}" var="states">
			data["${states.chooseVal}"] = "${states.showText}"
			</c:forEach>
			if (data[state]) {
				return data[state];
			} else {
				return "未知";
			}

		}
		function setserachrstates(){
			<c:forEach items="${dict['T_PROMOTION_STATES']}" var="states">
			 $('#searchstates').append("<option value='${states.chooseVal}'>${states.showText}</option>");
			</c:forEach>
		}
	</script>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/promotion/promotionIn.js"></script>
	<!-- <script type="text/javascript">
	$(document).ready(function(){
		var sWidth = $("#serchBtn").width();
		sWidth = sWidth*2+'px';
		$("#serchBtn").css("margin-left",sWidth);
	})
	</script> -->
</body>
</html>

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
<title>管理系统-KA合同修改</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link rel="stylesheet" href="static/table/new/bootstrap-table.css">

<link rel="stylesheet" href="static/table/new/bootstrap-editable.css">
<link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
<style>
body {
	font-family: "微软雅黑";
}

ul, li {
	list-style: none;
}

select.no-appearance {
	-webkit-appearance: none;
	-webkit-border-radius: 0;
}

.nav.nav-pills>li {
	padding: 5px 0;
}

.nav.nav-pills>li>a {
	padding: 5px 15px;
	background: rgba(0, 0, 0, .05);
	border: none;
	transition: 0.2s;
	font-size: 13px;
}

.nav.nav-pills>li.active>a, .nav.nav-pills>li.active>a:hover {
	color: #fff;
	background: #3c8dbc;
	font-weight: normal;
}

.nav.nav-pills>li>a:hover {
	background: hsla(202, 68%, 74%, .35);
	font-weight: normal;
}

.font12 {
	font-size: 12px;
}

.form-horizontal .input-box-list {
	display: table;
	margin: 10px 0 0 0;
	padding-left: 10px;
}

.input-box-list-title, .input-box-list-value {
	display: table-cell;
}

.input-box-list-title {
	width: 112px;
	text-align: right;
	vertical-align: middle;
	padding-right: 5px;
}

.input-box-list-value textarea {
	resize: none;
}

input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
	font-size: 12px;
}

.btn-long {
	padding: 8px 6.18%;
}

.block-save-link {
	float: right;
	font-size: 13px;
	padding: 4px 10px 0;
}

.block-save-link:hover {
	text-decoration: underline;
}

.bootstrap-table .table {
	border-bottom: none;
	border-radius: 0;
}

.bootstrap-table .fixed-table-container {
	border-radius: 0;
}

.contract-box {
	border-radius: 0;
}

.contract-box thead {
	background-color: hsla(255, 0%, 95%, 1);
}

.contract-box tbody {
	background-color: #FFF;
}

.contract-box td, .lawyer-box th {
	font-size: 12px;
	text-align: center;
	border-left: 1px solid #ddd;
}

.add-bstb-box {
	/*min-height: 100px;*/
	background-color: #fff;
	/*border-bottom:1px solid hsla(255,0%,90%,1);*/
	/*padding: 10px  0;*/
	height: 0;
	opacity: 0;
	overflow: hidden;
	transition: 0.2s;
	clear: both;
}

.add-bstb-box-open {
	border-bottom: 1px solid hsla(255, 0%, 90%, 1);
	padding: 10px 0;
	opacity: 1;
	height: auto;
}

.borderColor {
	border: 1px solid red
}
</style>
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			KA合同修改 <small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">客户管理</a></li>
			<li><a href="#">客户合同管理</a></li>
			<li class="active">KA合同修改</li>
		</ol>
	</section>
	<div class="col-md-12" style="min-height: 200px; padding: 10px 0;">
		<%-- block 1 --%>
		<form action="customer/contract/addCustPaper" method="POST"
			id="addPaperForm">
			<div class="page-header" id="block-1">
				<h4 class="text-info">
					<strong>1.&nbsp;</strong>合同基本信息&nbsp;-&nbsp; <small>Basic
						Information. </small>
				</h4>
			</div>
			<div class="form-horizontal row" style="margin: 0 0 20px 0;">	
	        	<div class="form-group col-md-4 input-box-list">
					<label for="input1" class=" font12 input-box-list-title">客户名：</label>
					<div class="input-box-list-value">
						<input type="text" class="form-control"
							value="${merch.name}" readonly>
					</div>
				</div>
				<div class="form-group col-md-4 input-box-list">
					<label for="input1" class=" font12 input-box-list-title">客户sap编码：</label>
					<div class="input-box-list-value">
						<input type="text" class="form-control"
							value="${merch.sapCustomerId}" readonly>
					</div>
				</div>
				<div class="form-group col-md-4 col-sm-6 input-box-list">
					<label for="input1" class=" font12 input-box-list-title">客户等级：</label>
					<div class="input-box-list-value">
						<select class="form-control no-appearance" name="merchLevels">
							<c:forEach items="${dict['MERCH_PAPER_LEVEL']}" var="it">
								<option value="${it.chooseVal}"
									${it.chooseVal == vo.merchLevels?"selected":""}>${it.showText}</option>
							</c:forEach>
						</select> <i class="icon icon-caret-down"
							style="float: right; margin: -25px 10px 0 0;"></i>
					</div>
				</div>
				<div class="form-group col-md-4 col-sm-6 input-box-list">
					<label for="input1" class=" font12 input-box-list-title">结算方式：</label>
					<div class="input-box-list-value">
						<select class="form-control no-appearance" name="settleType"
							id="settleType">
							<c:forEach items="${dict['MERCH_SETTLE_TYPE']}" var="it">
								<option value="${it.chooseVal}"
									${it.chooseVal == vo.settleType?"selected":""}>${it.showText}</option>
							</c:forEach>
						</select> <i class="icon icon-caret-down"
							style="float: right; margin: -25px 10px 0 0;"></i>
					</div>
				</div>
				<div class="form-group col-md-4 col-sm-6 input-box-list"
					style="display: ${vo.settleType=='2'?'block':'none'}" id="tPeriod">
					<label for="input1" class=" font12 input-box-list-title">账期(天)：</label>
					<div class="input-box-list-value">
						<select class="form-control no-appearance" name="aPeriod" id="aPeriod">
							<option></option>
							<c:forEach items="${dict['MERCH_CONTRACT_ACCOUNT_PERIOD']}"
								var="it">
								<option value="${it.chooseVal}" ${it.chooseVal == vo.aPeriod?"selected":""}>${it.showText}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group col-md-4 col-sm-6 input-box-list">
					<label for="input1" class=" font12 input-box-list-title">开始时间：</label>
					<div class="input-box-list-value">
						<input type="date" class="form-control" id="contractBdate"
							name="contractBdate" value="${vo.contractBdate}"
							placeholder="开始时间" readonly="readonly">
					</div>
				</div>
				<div class="form-group col-md-4 col-sm-6 input-box-list">
					<label for="input1" class=" font12 input-box-list-title">结束时间：</label>
					<div class="input-box-list-value">
						<input type="date" class="form-control" id="contractEdate"
							name="contractEdate" value="${vo.contractEdate}"
							placeholder="结束时间" readonly="readonly">
					</div>
				</div>
				<div class="form-group col-md-4 col-sm-6 input-box-list">
					<label for="input1" class=" font12 input-box-list-title">生意模式：</label>
					<div class="input-box-list-value">
						<select class="form-control no-appearance" id="agentType" name="agentType">
							<!-- <option></option> -->
							<c:forEach items="${dict['BUSINESS_MODEL']}" var="it">
								<option value="${it.chooseVal}"${it.chooseVal == vo.agentType?"selected":""}>${it.showText}</option>
							</c:forEach>
						</select> <i class="icon icon-caret-down"
							style="float: right; margin: -25px 10px 0 0;"></i>
					</div>
				</div>
				<div class="form-group col-md-4 col-sm-6 input-box-list">
						<label for="input1" class=" font12 input-box-list-title">年度进货目标：</label>
						<div class="input-box-list-value">
							<input type="text" class="form-control" name="yearAmt" id="edityearAmt" value="${vo.yearAmt }"
								placeholder="年度进货目标">
						</div>
			    </div>
			    <div class="form-group col-md-4 col-sm-6 input-box-list">
						<label for="input1" class=" font12 input-box-list-title">年度返利%：</label>
						<div class="input-box-list-value">
							<input type="text" class="form-control" name="rebate" id="editrebate" value="${vo.rebate }"
								placeholder="年度返利">
						</div>
			    </div>
			    <div class="form-group col-md-4 col-sm-6 input-box-list">
						<label for="input1" class=" font12 input-box-list-title">价格折扣比例：</label>
						<div class="input-box-list-value">
							<div class="input-group">
								<input type="text" class="form-control" name="attribute5" id="editdiscountRatio" value="${vo.attribute5}"
									   placeholder="价格折扣比例">
								<div class="input-group-addon">%</div>
							</div>
						</div>
			    </div>
			    <input type="hidden" name="id" value="${vo.id}" id="id"/>
			    <%-- <input type="hidden" id="factoryId" name="factoryId" value="${factory.id }"> --%>
			</div>
		</form>
		<%-- block 2 --%>
		<div class="page-header hiden" id="block-2" style="margin-bottom: 0;">
			<h4 class="text-info">
				<strong>2.&nbsp;</strong>代理信息&nbsp;-&nbsp; <small>Agent
					Type Information. </small>
			</h4>
		</div>

		<div class="row " style="margin: 0 0 20px 0;">
			<div class="col-md-12">
				<form class="form-horizontal row add-bstb-box hide" id="addAgentForm"
					action="customer/contract/addPaperLine" method="post">
					<input type="hidden" name="contractId" id="contractId"	value="${vo.id}">
				    <input type="hidden" name="agentType" id="agent-type" value="${vo.agentType}">
					<div class="form-group col-md-4 col-sm-6 input-box-list">
						<label for="input1" class=" font12 input-box-list-title">代理名称：</label>
						<div class="input-box-list-value">
							<select id="agentId" class="form-control" name="agentId"></select>
						</div>
					</div>
					<div class="form-group col-md-4 col-sm-6 input-box-list">
						<label for="input1" class=" font12 input-box-list-title">配送目标(元)：</label>
						<div class="input-box-list-value">
							<input type="text" class="form-control" name="yAmt" id="yAmt"
								placeholder="年度进货目标">
						</div>
					</div>
					<div class="form-group col-md-4 col-sm-6 input-box-list">
						<label for="input1" class=" font12 input-box-list-title">配送返利点：</label>
						<div class="input-box-list-value">
							<div class="input-group">
								<input type="text" class="form-control" name="yRatio" id="yRatio"
									placeholder="年度返利点"> <span class="input-group-addon">%</span>
							</div>
						</div>
					</div>
					<div class="form-group col-md-4 col-sm-6 input-box-list hide">
						<label for="input1" class=" font12 input-box-list-title">季度进货目标(元)：</label>
						<div class="input-box-list-value">
							<input type="text" class="form-control" value="0" name="qAmt" id="qAmt"
								placeholder="季度进货目标">
						</div>
					</div>
					<!-- 季度进货比例 -->
					<div class="form-group col-md-4 col-sm-6 input-box-list hide">
						<label for="input1" class=" font12 input-box-list-title">季度返利点：</label>
						<div class="input-box-list-value">
							<div class="input-group">
								<input type="text" class="form-control" value="0" name="qRatio" id="qRatio"
									placeholder="季度返利点"> <span class="input-group-addon">%</span>
							</div>
						</div>
					</div>
					<div class="col-md-12 row" style="padding: 25px 0 0 15px;">
						<div class="col-md-4 col-sm-4 text-left "></div>
						<div class="col-md-4 col-sm-4 text-center">
							<button type="submit" id="btn-add-agent" class="btn btn-danger">
								<i class="icon icon-check"></i>&nbsp;&nbsp;添加
							</button>
						</div>
						<div class="col-md-4 col-sm-4 text-right" style="padding: 0;">
							<a id="btn-hide-bstb-add-box" class="btn btn-default"><i
								class="icon icon-caret-up"></i>&nbsp;&nbsp;收起</a>
						</div>
					</div>
				</form>
				<div id="contract-toolbar">
					<a id="edit-bstb-row" class="btn btn-info  pull-left"> <i
						class="icon icon-edit"></i> 修改
					</a> <a id="del-bstb-row" class="btn btn-primary  pull-left"
						style="width: auto; margin-right: 5px; background-color: #d9534f; border-color: #d43f3a;">
						<i class="icon icon-trash"></i> 删除
					</a>
				</div>
				<table id="contract-table" class="contract-box">
				</table>

			</div>
		</div>

		<%-- submit button --%>
		<div class="text-center"
			style="padding-top: 30px; border-top: 1px solid rgba(0, 0, 0, .15);">
			<button class="btn btn-warning" id="btn-save" form="addPaperForm"
				style="padding: 8px 25px;" type="button">
				<i class="icon icon-save"></i>&nbsp;&nbsp;保存
			</button>
		</div>
	</div>
	<!-- editDialog-->
	<div class="modal fade" id="editLinesModel" tabindex="-1"
		aria-labelledby="dictLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="editdictLabel">修改代理信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal"
						action="customer/contract/updateLines" method="post"
						id="editLinesForm">
						<div class="form-group">
							<label for="editagentType" class="col-sm-3 control-label">代理类型</label>
							<div class="col-sm-5">
								<input type="hidden" id="editid" name="id">
								 <!-- <input type="text" class="form-control" id="editagentType" readonly> -->
							   <select class="form-control no-appearance" id="editagentType" name="agentType" disabled="disabled">
								<c:forEach items="${dict['AGENT_TYPE']}" var="it">
									<option value="${it.chooseVal}">${it.showText}</option>
								</c:forEach>
						</select>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="editname" class="col-sm-3 control-label">代理名称</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="editname" readonly>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="edityAmt" class="col-sm-3 control-label">配送目标(元)</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="edityAmt"
									name="yAmt">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="edityRatio" class="col-sm-3 control-label">配送返利点：</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="edityRatio"
									name="yRatio">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-edit-save" form="editLinesForm"
						class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/customer/kaContractEdit.js"></script>
	<script>
		var agentType = "${vo.agentType}";
		function getIdSelections() {
			return $.map($table.bootstrapTable('getSelections'), function(row) {
				return row.agentID; //getID by our column
			});
		}
		function responseHandler(res) {
			$.each(res.rows, function(i, row) {
				row.state = $.inArray(row.id, selections) !== -1;
			});
			return res;
		}

		function detailFormatter(index, row) {
			var html = [];
			$.each(row, function(key, value) {
				html.push('<p><b>' + key + ':</b> ' + value + '</p>');
			});
			return html.join('');
		}
		function totalTextFormatter(data) {
			return 'Total';
		}

		function totalNameFormatter(data) {
			return data.length;
		}

		function totalPriceFormatter(data) {
			var total = 0;
			$.each(data, function(i, row) {
				total += +(row.price.substring(1));
			});
			return '$' + total;
		}

		function getHeight() {
			return $(window).height() - $('h1').outerHeight(true);
		}

		function detailFormatter(index, row) {
			var html = [];
			$.each(row, function(key, value) {
				html.push('<p><b>' + key + ':</b> ' + value + '</p>');
			});
			return html.join('');
		}
	</script>
</body>
</html>

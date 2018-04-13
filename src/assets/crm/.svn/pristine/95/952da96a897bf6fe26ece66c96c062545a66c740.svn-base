<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>管理系统-新建盐业公司合同</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link rel="stylesheet"href="static/tokenfield/css/bootstrap-tokenfield.min.css">
<link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link href="static/bootstrap/css/bootstrap-datetimepicker.min.css"rel="stylesheet">
<link href="static/table/new/bootstrap-editable.css" rel="stylesheet">
<link href="static/css/customer/paper.css"rel="stylesheet">
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			客户合同管理 <small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">客户管理</a></li>
			<li><a href="#">客户合同管理</a></li>
			<li class="active">新建客户合同</li>
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
				<div class="form-group col-md-4  col-sm-6 input-box-list">
					<label for="input1" class=" font12 input-box-list-title">客户名称:</label>
					<div class="input-box-list-value">
						<input type="hidden" name="merchCustId" id="custId"> 
						<input type="hidden" name="id" />
						<div class="input-group">
							<input type="text" class="form-control" id="custName" data-toggle="modal"
									data-target="#chooseCustomer" readonly />
							<div class="input-group-addon">
								<i class="icon icon-search" data-toggle="modal"
									data-target="#chooseCustomer"></i>
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" id="organizationId" name="organizationId">
				<div class="form-group col-md-4 col-sm-6 input-box-list">
						<label for="input1" class=" font12 input-box-list-title">生意模式：</label>
						<div class="input-box-list-value">
							<select class="form-control no-appearance" id="agent-type" name="agentType">
								<option></option>
								<c:forEach items="${dict['BUSINESS_MODEL']}" var="it">
									<option value="${it.chooseVal}">${it.showText}</option>
								</c:forEach>
							</select>
						</div>
			    </div>
				<div class="form-group col-md-4 col-sm-6 input-box-list">
					<label for="input1" class=" font12 input-box-list-title">客户等级：</label>
					<div class="input-box-list-value">
						<select class="form-control no-appearance" name="merchLevels">
							<c:forEach items="${dict['MERCH_PAPER_LEVEL']}" var="it">
								<option value="${it.chooseVal}">${it.showText}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group col-md-12 col-sm-6 input-box-list">
					<label for="input1" class=" font12 input-box-list-title">经销区域:</label>
					<div class="input-box-list-value">
						<div class="input-group" style="cursor: pointer;" data-toggle="modal"
							 data-target="#chooseArea">
							<input type="text" class="form-control tokenfield" id="agentArea"
								name="agentArea" readonly/>
							<div class="input-group-addon" >
								<i class="icon icon-plus" ></i>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group col-md-4 col-sm-6  input-box-list">
					<label for="input1" class=" font12 input-box-list-title">交货地点:</label>
					<div class="input-box-list-value">
						<input type="text" class="form-control" id="deliveryAddress"
							name="deliveryAddress" placeholder="交货地点">
					</div>
				</div>
				<div class="form-group col-md-4 col-sm-6 input-box-list">
					<label for="input1" class=" font12 input-box-list-title">结算方式:</label>
					<div class="input-box-list-value">
						<select class="form-control no-appearance" name="settleType"
							id="settleType">
							<option></option>
							<c:forEach items="${dict['MERCH_SETTLE_TYPE']}" var="it">
								<option value="${it.chooseVal}">${it.showText}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group col-md-4  col-sm-6 input-box-list"
					style="display: none" id="Period">
					<label for="input1" class=" font12 input-box-list-title">账期(天)：</label>
					<div class="input-box-list-value">
						<select class="form-control no-appearance" name="aPeriod" id="aPeriod">
						</select>
					</div>
				</div>
				<div class="form-group col-md-4  col-sm-6 input-box-list">
					<label for="input1" class=" font12 input-box-list-title">开始时间：</label>
					<div class="input-box-list-value">
						<input type="text" class="form-control" id="startTime"
							name="contractBdate" placeholder="开始时间">
					</div>
				</div>
				<div class="form-group col-md-4 col-sm-6 input-box-list">
					<label for="input1" class=" font12 input-box-list-title">结束时间：</label>
					<div class="input-box-list-value">
						<input type="text" class="form-control" id="endTime" pattern="\d{4}-\d{2}-\d{2}"
							name="contractEdate" placeholder="结束时间">
					</div>
				</div>
				<div class="form-group col-md-4 col-sm-6 input-box-list">
					<label for="input1" class=" font12 input-box-list-title">授信额度(元)：</label>
					<div class="input-box-list-value">
						<div class="input-group">
							<input type="text" class="form-control" id="creditAmt"
								   name="creditAmt" placeholder="授信额度">
							<div class="input-group-addon">元</div>
						</div>
					</div>
				</div>
			    <div class="form-group col-md-4 col-sm-6 input-box-list">
						<label for="input1" class=" font12 input-box-list-title">年度进货目标：</label>
						<div class="input-box-list-value">
							<div class="input-group">
								<input type="text" class="form-control" name="yearAmt" id="yearAmt"
									   placeholder="年度进货目标">
								<div class="input-group-addon">元</div>
							</div>
						</div>
			    </div>
			    <div class="form-group col-md-4 col-sm-6 input-box-list">
						<label for="input1" class=" font12 input-box-list-title">年度返利：</label>
						<div class="input-box-list-value">
							<div class="input-group">
								<input type="text" class="form-control" name="rebate" id="rebate"
									   placeholder="年度返利">
								<div class="input-group-addon">%</div>
							</div>
						</div>
			    </div>
			    <div class="form-group col-md-4 col-sm-6 input-box-list hide">
						<label for="input1" class=" font12 input-box-list-title">对应虚拟仓：</label>
						<div class="input-box-list-value">
							<select class="form-control" name="virtualWarehouse" >
							<option></option>
							<c:forEach items="${dict['VIRTUAL_WAREHOUSE_CODE']}" var="it">
								<option value="${it.chooseVal}">${it.showText}</option>
							</c:forEach>
							</select>
						</div> 
			    </div>
			    <input type="hidden" id="lineData" name="lineData">
			</div>
		</form>
		 <%-- block 2 --%>
		<div class="page-header" id="block-2" style="margin-bottom: 0;">
			<h4 class="text-info">
				<strong>2.&nbsp;</strong>代理信息&nbsp;-&nbsp; <small>Agent
					Type Information. </small>
				<!-- <a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a>
				<a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-plus"></i>&nbsp;&nbsp;新增</a> -->
			</h4>
		</div>

		<div class="row" style="margin: 0 0 20px 0;">
			<div class="col-md-12">
				<!-- <form class="form-horizontal row add-bstb-box" id="addAgentForm"
					action="customer/contract/addPaperLine" method="post">
					<input type="hidden" name="agentType" id="agentType">
					<div class="form-group col-md-4  col-sm-6 input-box-list">
						<label for="input1" class=" font12 input-box-list-title">代理名称：</label>
						<div class="input-box-list-value">
							<select id="agentId" class="form-control" name="agentId"></select>
						</div>
					</div>
					<div class="form-group col-md-4 col-sm-6 input-box-list">
						<label for="input1" class=" font12 input-box-list-title">年度进货目标(元)：</label>
						<div class="input-box-list-value">
							<input type="text" class="form-control" name="yAmt" id="yAmt"
								placeholder="年度进货目标">
						</div>
					</div>
					<div class="form-group col-md-4 col-sm-6 input-box-list">
						<label for="input1" class=" font12 input-box-list-title">年度返利点：</label>
						<div class="input-box-list-value">
							<div class="input-group">
								<input type="text" class="form-control" name="yRatio" id="yRatio"
									placeholder="年度返利点"> <span class="input-group-addon">%</span>
							</div>
						</div>
					</div>
					<div class="form-group col-md-4 col-sm-6 input-box-list">
						<label for="input1" class=" font12 input-box-list-title">季度进货目标(元)：</label>
						<div class="input-box-list-value">
							<input type="text" class="form-control" name="qAmt" id="qAmt"
								placeholder="季度进货目标">
						</div>
					</div>
					季度进货比例
					<div class="form-group col-md-4 col-sm-6 input-box-list">
						<label for="input1" class=" font12 input-box-list-title">季度目标：</label>
						<div class="input-box-list-value">
							<div class="input-group">
								<input type="text" class="form-control" name="qRatio" id="qRatio"
									placeholder="季度返利点"> <span class="input-group-addon">%</span>
							</div>
						</div>
					</div>
					<div class="col-md-12 row col-sm-12" style="padding: 25px 0 0 15px;">
						<div class="col-md-4 col-sm-4 text-left "></div>
						<div class="col-md-4 col-sm-4 text-center">
							<button type="button" id="btn-add-agent" class="btn btn-danger">
								<i class="icon icon-check"></i>&nbsp;&nbsp;添加
							</button>
						</div>
						<div class="col-md-4 col-sm-4 text-right" style="padding: 0;">
							<a id="btn-hide-bstb-add-box" class="btn btn-default"><i
								class="icon icon-caret-up"></i>&nbsp;&nbsp;收起</a>
						</div>
					</div>
				</form> -->
				<div id="contract-toolbar">
					<!-- <a id="add-bstb-row" class="btn btn-primary  pull-left"
						style="width: auto; margin-right: 5px;"> <i
						class="icon icon-plus"></i> 新增代理
					</a>  -->
					<a id="del-bstb-row" class="btn btn-primary  pull-left"
						style="width: auto; margin-right: 5px; background-color: #d9534f; border-color: #d43f3a;">
						<i class="icon icon-trash"></i> 删除
					</a>
				</div>
				<table id="contract-table" class="contract-box"
					data-toolbar="#contract-toolbar" data-search="true"
					data-show-refresh="true"
					data-show-toggle="true"
        			data-show-columns="true"
					data-show-export="true"
					data-show-pagination-switch="true"
        			data-detail-view="false"
					data-detail-formatter="detailFormatter" data-id-field="id">
				</table>
			</div>
		</div>
		<%-- submit button --%>
		<div class="text-center"
			style="padding-top: 30px; border-top: 1px solid rgba(0, 0, 0, .15);">
			<button class="btn btn-warning" style="padding: 8px 25px;"
				type="button" id="btn-save">
				<i class="icon icon-save"></i>&nbsp;&nbsp;保存
			</button>
			<button class="btn btn-primary btn-long" type="button"
				id="btn-submit">
				<i class="icon icon-check"></i>&nbsp;&nbsp;提交
			</button>
		</div>
	</div>

	<div class="modal fade" id="chooseCustomer">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">选择客户</h4>
				</div>
				<div class="modal-body">
					<table id="customerTable"></table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="chooseCust_btn">选择</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	
	<div class="modal fade" id="chooseArea">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">选择地区</h4>
				</div>
				<div class="modal-body" style="height: 400px;overflow:auto;">
					<ul id="areaTrea"class="ztree" style="width: 100%;height: 100%;"></ul>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="btn_choose_area">选择</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/table/new/bootstrap-table-editable.js"></script>
	<script type="text/javascript" src="static/table/new/bootstrap-editable.js"></script>
	<script type="text/javascript" src="static/ztree/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript" src="static/tokenfield/bootstrap-tokenfield.min.js"></script>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.min.js"charset="UTF-8"></script>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"	charset="UTF-8"></script>
	<script>
		var custType = "${custType}";
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
		function operateFormatter(value, row, index) {
			return [ '<a class="remove" href="javascript:;" title="删除本行">',
					'<i class="icon icon-trash"></i>', '</a>' ].join('');
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

		$(function() {
			$('.tokenfield').tokenfield();
		})
		
		function periods(){
			<c:forEach items="${dict['MERCH_CONTRACT_ACCOUNT_PERIOD']}"
				var="it">
				$("#aPeriod").append("<option value='${it.chooseVal}'>${it.showText}</option>");
			</c:forEach>
		}
	</script>
	<script type="text/javascript" src="static/js/customer/addPaper.js"></script>
</body>
</html>

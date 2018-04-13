<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
<link rel="stylesheet" href="static/table/new/bootstrap-table.css">

<link rel="stylesheet" href="static/table/new/bootstrap-editable.css">
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
	width: 100px;
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
</style>
</head>
<body class="container-fluid content">

	<section class="content-header">
		<h1>
			客户资金销售确认 <small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">客户资金管理</a></li>
			<li class="active">客户资金销售确认</li>
		</ol>
	</section>

	<div class="row" style="padding: 0 15px;">
		<%-- left step link
                <div class="col-md-12" style="padding-left:0; padding-bottom: 5px;">
                    <ul class="nav nav-pills" id="stepLink">
                        <li role="presentation" class="active"><a href="javascript:;">基本资料</a></li>
                        <li role="presentation"><a href="javascript:;">联系人信息</a></li>
                        <li role="presentation"><a href="javascript:;">送达方信息</a></li>
                        <li role="presentation"><a href="javascript:;">开票信息</a></li>
                    </ul>
                </div>
                --%>
		<%-- right step content --%>
		<!-- <form class="col-md-12" style="min-height: 200px; padding: 10px 0;"> -->
		<%-- block 1 --%>
		<div class="page-header" id="block-1">
			<h4 class="text-info">
				<strong>1.&nbsp;</strong>财务录入信息&nbsp;-&nbsp; <small>Finance
					Information.</small>
				<%--<a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a>--%>
				<%--<a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-trash"></i>&nbsp;&nbsp;清空</a>--%>
			</h4>
		</div>
		<div class="form-horizontal row" style="margin: 0 0 20px 0;">
			<div class="form-group col-md-4 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">打款人姓名：</label>

				<div class="input-box-list-value">
					<label class="form-control">${bean.payName}</label>
				</div>
			</div>
			<div class="form-group col-md-4 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">打款金额：</label>
				<div class="input-box-list-value">
					<label class="form-control">${bean.payAmt}</label>
				</div>
			</div>
			<div class="form-group col-md-4 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">打款银行：</label>
				<div class="input-box-list-value">
					<label class="form-control">${bean.payBank}</label>
				</div>
			</div>
			<div class="form-group col-md-4 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">城市：</label>
				<div class="input-box-list-value">
					<label class="form-control">${bean.payCity}</label>
				</div>
			</div>
			<div class="form-group col-md-4 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">账号(后4位)：</label>
				<div class="input-box-list-value">
					<label class="form-control">${bean.payBankNo}</label>
				</div>
			</div>
			<div class="form-group col-md-4 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">来款时间：</label>
				<div class="input-box-list-value">
					<label class="form-control">${bean.payDate}</label>
				</div>
			</div>
			<div class="form-group col-md-4 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">财务录入时间：</label>
				<div class="input-box-list-value">
					<label class="form-control">${createTs}</label>
				</div>
			</div>
			<div class="form-group col-md-4 input-box-list">
				<label for="input1" class=" font12 input-box-list-title">财务员：</label>
				<div class="input-box-list-value">
					<label class="form-control">${bean.creater}</label>
				</div>
			</div>
		</div>
		<%-- block 2 --%>
		<div class="page-header" id="block-2">
			<h4 class="text-info">
				<strong>2.&nbsp;</strong>销售上账信息&nbsp;-&nbsp; <small>Bill
					Information.</small>
			</h4>
		</div>
		<!-- addDialog-->
		<div class="modal fade" id="addUpLaccountModal" tabindex="-1"
			role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="dictLabel">销售确认客户资金</h4>
					</div>
					<div class="modal-body">
						<%-- 销售组织 --%>
						<form class="form-horizontal" method="post"
							action="account/upaccount/sales/add" id="addAccountLForm">
							<div class="form-group ">
								<label for="organizationId" class="col-sm-3 control-label">销售组织：</label>
								<input type="hidden" value="${bean.id}" id="upId" name="upId">
								<div class="col-sm-5">
									<select class="form-control" id="organizationId"
										name="organizationId">
										<option value="5">销售组织1</option>
										<option>销售组织2</option>
										<option>销售组织3</option>
										<option>客户名称4</option>
									</select> <i class="icon icon-caret-down"
										style="float: right; margin: -25px 10px 0 0;"></i>
								</div>
								<small class="help-block col-sm-4"></small>
							</div>
							<%-- 客户名称 --%>
							<div class="form-group">
								<label for="merchCustId" class="col-sm-3 control-label">客户名称：</label>
								<div class="col-sm-5">
									<select class="form-control" id="merchCustId"
										name="merchCustId">
										<option value="1">客户名称1</option>
										<option>客户名称2</option>
										<option>客户名称3</option>
										<option>客户名称4</option>
									</select> <i class="icon icon-caret-down"
										style="float: right; margin: -25px 10px 0 0;"></i>
								</div>
								<small class="help-block col-sm-4"></small>
							</div>
							<%-- 金额 --%>
							<div class="form-group">
								<label for="amt" class="col-sm-3 control-label">金额：</label>
								<div class="col-sm-5">
									<input type="number" class="form-control" id="amt" name="amt"
										value="0" placeholder="金额">
								</div>
								<small class="help-block col-sm-4"></small>
							</div>
							

						</form>
					</div>
					<div class="modal-footer">
						<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" id="btn-add-sales"
							class="btn btn-primary btn-save" form="addAccountLForm">保存</button>
					</div>
					<%-- 添加数据 --%>
					<!-- <div class="col-md-12 row" style="padding: 25px 0 0 15px;">
						<div class="col-md-4 text-left "></div>
						<div class="col-md-4 text-center">
							<button type="submit" id="btn-add-sales" class="btn btn-danger"
								style="padding-left: 15%; padding-right: 15%;"
								form="addAccountLForm">
								<i class="icon icon-check"></i>&nbsp;&nbsp;添加
							</button>
						</div>
						<div class="col-md-4 text-right" style="padding: 0;">
							<a id="btn-hide-bstb-add-box" class="btn btn-default"><i
								class="icon icon-caret-up"></i>&nbsp;&nbsp;收起</a>
						</div>
					</div> -->
				</div>
				
			</div>

		</div>
		<!-- 表格 -->
		<div>
			<%-- 表格toolbar --%>
				<div id="sales-toolbar">
					<a id="add-bstb-row" class="btn btn-primary "
						style="margin-right: 5px;" data-toggle="modal"
						data-target="#addUpLaccountModal"> <i class="icon icon-plus"></i>
						新增
					</a> <a id="del-bstb-row" class="btn btn-primary "
						style="margin-right: 5px;"> <i class="icon icon-trash"></i> 删除
					</a>
				</div>
				<%-- 数据表格 --%>
				<table id="sales-table" class="contract-box"
					data-toolbar="#sales-toolbar" data-search="true"
					data-show-refresh="true"
					<%--data-show-toggle="true"--%>
                                   data-show-columns="true"
					data-show-export="true"
					<%--data-show-pagination-switch="true"--%>
                                   data-detail-view="false"
					data-detail-formatter="detailFormatter" data-id-field="salesID"
					data-pagination="true" data-page-size="5" data-page-list="[5,8,10]"
					data-pagination-first-text="首页" data-pagination-pre-text="上一页"
					data-pagination-next-text="下一页" data-pagination-last-text="尾页">
				</table>
			
		</div>
		<%-- submit button --%>
		<div class="text-center"
			style="padding-top: 30px; border-top: 1px solid rgba(0, 0, 0, .15);">
			<button id="fundsSave" class="btn btn-warning" style="padding: 8px 25px;"
				type="button">
				<i class="icon icon-save"></i>&nbsp;&nbsp;保存
			</button>
			<button class="btn btn-primary btn-long" type="submit">
				<i class="icon icon-check"></i>&nbsp;&nbsp;提交
			</button>
		</div>
		<!--</form>  -->
	</div>

	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<%--<script type="text/javascript" src="static/table/bootstrap-table.min.js"></script>--%>
	<%--<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>--%>
	<%--<script type="text/javascript" src="static/js/system/dict.js"></script>--%>
	<script type="text/javascript"
		src="static/table/new/bootstrap-table.js"></script>
	<script type="text/javascript"
		src="static/table/new/bootstrap-table-export.js"></script>
	<script type="text/javascript"
		src="static/table/new/bootstrap-table-editable.js"></script>
	<script type="text/javascript"
		src="static/table/new/bootstrap-editable.js"></script>
	<script type="text/javascript"
		src="static/table/new/bootstrap-table-zh-CN.js"></script>
	<script>
		var $table = $('#sales-table');
		var $remove = $('#del-bstb-row');
		function initTable() {
			var upid = $('#upId').val();
			//alert(upid);
			$table.bootstrapTable({
				url : 'account/upaccount/funds/detail?upid=' + upid,
				//        height: getHeight(),
				method : 'get',
				classes : "table table-hover table-condensed",
				cache : false,
				striped : true,
				pagination : true,
				searchOnEnterKey : true,
				sidePagination : "server",
				idField : "id",
				sortName : "custname",
				smartDisplay : true,
				pageSize : 10,
				pageList : [ "10", "20", "50", "100" ],
				search : true,
				showColumns : true,
				showRefresh : true,
				clickToSelect : true,
				singleSelect : true,

				columns : [ {
					field : 'state',
					checkbox : true,
					rowspan : 1,
					align : 'center',
					valign : 'middle'
				}, {
					title : 'ID',
					field : 'id',
					rowspan : 1,
					align : 'center',
					valign : 'middle',
					visible : false
				}, {
					title : '销售组织',
					field : 'orgname',
					rowspan : 1,
					align : 'center',
					valign : 'middle',
				/* sortable : true
				,
				editable : {
					type : 'select',
					title : '修改销售组织:',
					source : [ {
						value : '1',
						text : '调味品事业部'
					}, {
						value : '新都化工哈哈农庄销售2处',
						text : '新都化工哈哈农庄销售2处'
					}, {
						value : '新都化工哈哈农庄销售3处',
						text : '新都化工哈哈农庄销售3处'
					} ]
				} */
				}, {
					title : '客户名称',
					field : 'custname',
					rowspan : 1,
					align : 'center',
					valign : 'middle'
				/* ,sortable : true,
				editable : {
					type : 'select',
					title : '修改客户名称:',
					source : [ {
						value : '1',
						text : '永贵批发部'
					}, {
						value : '加勒比海盗超市第二农贸超市化肥销售处',
						text : '加勒比海盗超市第二农贸超市化肥销售处'
					}, {
						value : '永辉超市',
						text : '永辉超市'
					} ]
				} */
				}, {
					title : '金额',
					field : 'amt',
					rowspan : 1,
					align : 'center',
					valign : 'middle'
				/* ,sortable : true,
				editable : {
					type : 'text',
					title : '修改金额:',
					validate : function(value) {
						value = $.trim(value);
						if (!value) {
							return '请输入正确的数值';
						}
						if (!/^\d+(\.\d{2})?$/.test(value)) {
							return '请输入正确的数值,如100.00'
						}
						var data = $table
								.bootstrapTable('getData'), index = $(
								this).parents('tr').data(
								'index');
						console.log(data[index]);
						return '';
					}
				} */
				}, {
					field : 'operate',
					width : 40,
					title : '删除',
					align : 'center',
					events : 'deleteEvents',
					formatter : operateFormatter
				} ]
			});

			// sometimes footer render error.
			setTimeout(function() {
				$table.bootstrapTable('resetView');
			}, 200);
			$table.on('check.bs.table uncheck.bs.table '
					+ 'check-all.bs.table uncheck-all.bs.table', function() {
				$remove.prop('disabled', !$table
						.bootstrapTable('getSelections').length);

				// save your data, here just save the current page
				selections = getIdSelections();
				// push or splice the selections if you want to save all data selections
			});
			$table.on('expand-row.bs.table', function(e, index, row, $detail) {
				if (index % 2 == 1) {
					$detail.html('Loading from ajax request...');
					$.get('LICENSE', function(res) {
						$detail.html(res.replace(/\n/g, '<br>'));
					});
				}
			});
			$table.on('all.bs.table', function(e, name, args) {
				console.log(name, args);
			});
			$remove.click(function() {
				var ids = getIdSelections();
				if (ids.length > 0) {
					$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
						$.post("account/upaccount/delupl", {
							"id" : ids.join()
						}, function() {
							$("#sales-table").bootstrapTable("refresh");
						})
					});
				} else {
					alert("请选择要删除的行。");
				}

			});
			$(window).resize(function() {
				$table.bootstrapTable('resetView', {
				//height: getHeight()
				});
			});

		}

		// functions by table oprate
		function getIdSelections() {
			/* var rows = $("#sales-table").bootstrapTable("getSelections")
			var ids = [];
			if(rows && rows.length>0){
				for(var i=0;i<rows.length;i++){
					ids.push(rows[i].id);
				}
			}
			return ids; */
			
			return $.map($('#sales-table').bootstrapTable('getSelections'), function(row) {
				return row.id; //getID by our column
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
		window.deleteEvents = {
			'click .remove' : function(e, value, row, index) {
				$.post("account/upaccount/delupl", {
					"id" : row.id
				}, function() {
					$("#sales-table").bootstrapTable("refresh");
				})
			}
		};

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

		initTable();
		

		//    page oprate functions
		function addSales(sales) {
			$table.bootstrapTable('insertRow', {
				index : 0,
				row : sales
			});
		}
		$(function() {
			var addValidator = initValidate("#addAccountLForm");
			$('#add-bstb-row').click(function() {
				$('.add-bstb-box').addClass("add-bstb-box-open");
			});
			$('#btn-hide-bstb-add-box').click(function() {
				$('.add-bstb-box').removeClass("add-bstb-box-open");
			});
			$("#fundsSave").click(function(){
				var payAmt = ${bean.payAmt};
				var total = 0;
				var rows = $('#sales-table').bootstrapTable('getData');
				if(rows && rows.length>0){
					for(var i=0;i<rows.length;i++){
						total += parseInt(rows[i].amt);
					}
				}
				if(payAmt!=total){
					$.messager.alert("提示","金额不匹配");
					return false;
				}else{
					window.location.href="account/salesUpList.html";
				}
			});

			$("#addAccountLForm").ajaxForm({
				target : '#btn-add-sales', // target element(s) to be updated with
				// server
				// response
				clearForm : true,
				dataType : 'json',
				beforeSubmit : function(formData, jqForm, options) {
					var payAmt = ${bean.payAmt};
					var total = 0;
					var rows = $('#sales-table').bootstrapTable('getData');
					if(rows && rows.length>0){
						for(var i=0;i<rows.length;i++){
							total += parseInt(rows[i].amt);
						}
					}
					total += parseInt(formData[3].value);
					if(payAmt<total){
						$.messager.alert("提示","金额过大");
						return false;
					}
					return addValidator.valid();
				},
				success : function(responseText, statusText, xhr, $form) {
					$("#addUpLaccountModal").modal("hide");
					$("#sales-table").bootstrapTable("refresh")
				}
			});
			

		});
		function initValidate(formId) {
			var validator = $(formId).validate({
				rules : {
					organizationId : "required",
					merchCustId : "required",
					amt : {
						required : true,
						number : true
					}
				}
			});
			return validator;
		}
	</script>
</body>
</html>

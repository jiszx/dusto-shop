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
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
	<section class="content-header">
		<h1>
			客户余额查询<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#">客户资金管理</a></li>
			<li class="active">客户余额</li>
		</ol>
	</section>
	<div class="col-md-12">
		<form class="form-horizontal" method="post" action="system/dict/add"
			id="addDictForm">
			<div class="form-group">
				<label for="colName" class="col-sm-2 control-label">客户</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="colName" name="colName">
				</div>
				<small class="help-block col-sm-4"></small>
			</div>
			<div class="form-group">
				<label for="chooseVal" class="col-sm-2 control-label">销售组织</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="chooseVal"
						name="chooseVal">
				</div>
				<small class="help-block col-sm-4"></small>
			</div>
			<div class="form-group">
				<label for="showText" class="col-sm-2 control-label">调整类型</label>
				<div class="col-sm-6">
					<select class="form-control">
						<option>现金</option>
						<option>货补</option>
						<option>授信</option>
					</select>
				</div>
				<small class="help-block col-sm-4"></small>
			</div>
			<div class="form-group">
				<label for="orders" class="col-sm-2 control-label">金额（元）</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="orders" name="orders">
				</div>
				<small class="help-block col-sm-4"></small>
			</div>
			<div class="form-group">
				<label for="orders" class="col-sm-2 control-label">备注</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="orders" name="orders">
				</div>
				<small class="help-block col-sm-4"></small>
			</div>
		</form>
	</div>

	<!-- addDialog-->
	<div class="modal fade" id="addDictModal" tabindex="-1" role="dialog"
		aria-labelledby="dictLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">上账</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="system/dict/add" id="addDictForm">
						<div class="form-group">
							<label for="colName" class="col-sm-2 control-label">打款人名称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="colName"
									name="colName">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="chooseVal" class="col-sm-2 control-label">打款金额</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="chooseVal"
									name="chooseVal">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="showText" class="col-sm-2 control-label">打款银行</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="showText"
									name="showText">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="orders" class="col-sm-2 control-label">打款城市</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="orders"
									name="orders">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="orders" class="col-sm-2 control-label">卡号（后4位）</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="orders"
									name="orders">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="orders" class="col-sm-2 control-label">城市</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="orders"
									name="orders">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="orders" class="col-sm-2 control-label">时间</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="orders"
									name="orders">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-add" class="btn btn-primary btn-save"
						form="addDictForm">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- editDialog-->
	<div class="modal fade" id="editDictModal" tabindex="-1"
		aria-labelledby="dictLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="editdictLabel">修改字典</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="/system/dict/edit"
						method="post" id="editDictForm">
						<div class="form-group">
							<label for="colName" class="col-sm-2 control-label">字典字段</label>
							<div class="col-sm-6">
								<input type="hidden" id="editid" name="id"> <input
									type="text" class="form-control" id="editcolName"
									name="colName">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="chooseVal" class="col-sm-2 control-label">字典值</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editchooseVal"
									name="chooseVal">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="showText" class="col-sm-2 control-label">显示文本</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editshowText"
									name="showText">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
						<div class="form-group">
							<label for="orders" class="col-sm-2 control-label">顺序</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="editorders"
									name="orders">
							</div>
							<small class="help-block col-sm-4"></small>
						</div>

						<div class="form-group">
							<label for="canChoose" class="col-sm-2 control-label">是否可选</label>
							<div class="col-sm-6">
								<select class="form-control" id="editcanChoose" name="canChoose">
									<option value="0">否</option>
									<option value="1">是</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="display" class="col-sm-2 control-label">display</label>
							<div class="col-sm-6">
								<select class="form-control" id="editdisplay" name="display">
									<option value="0">否</option>
									<option value="1">是</option>
								</select>
							</div>
						</div>


					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="btn-edit-save" form="editDictForm"
						class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>



	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<script type="text/javascript"
		src="static/table/bootstrap-table.min.js"></script>
	<script type="text/javascript"
		src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" src="static/js/system/dict.js"></script>
</body>
</html>

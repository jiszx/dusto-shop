<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>管理系统-字典管理</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
</head>
<body class="container-fluid">
	<section class="content-header">
		<h1>
			权限管理<small>添加/修改</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 系统管理</a></li>
			<li class="active">权限管理</li>
		</ol>
	</section>
	<!-- Main content -->
	<section class="content">


		<div class="panel panel-default col-md-12" style="min-height: 542px;">
			<div class="panel-body">
				<div class="col-md-12" style="padding-left:0">
					<div class="col-sm-4 col-md-4" style="border-right: 2px solid #f0f0f0">
						<ul id="tree" class="ztree" style="width: 100%; height: 100%;"></ul>
					</div>
					<div class="col-sm-8 col-md-8">
						<form class="form-horizontal col-md-12" id="editAuthForm" action="system/auth/edit" method="post">
							<div class="form-group">
								<label for="editresName" class="col-sm-4 col-md-4 control-label">权限名称</label>
								<div class="col-sm-7 col-md-7">
									<input type="text" class="form-control" id="editresName" name="resName">
									<input type="hidden" name="resId" id="editresId" >
								</div>
							</div>
							<div class="form-group">
								<label for="editresUrl" class="col-sm-4 col-md-4 control-label">URL</label>
								<div class="col-sm-7 col-md-7">
									<input type="text" class="form-control" id="editresUrl" name="resUrl">
								</div>
							</div>
							<div class="form-group">
								<label for="editorders" class="col-sm-4 col-md-4 control-label">顺序</label>
								<div class="col-sm-7 col-md-7">
									<input type="text" class="form-control" id="editorders" name="orders">
								</div>
							</div>
							<div class="form-group">
								<label for="editiconclass" class="col-sm-4 col-md-4 control-label">图标</label>
								<div class="col-sm-7 col-md-7">
									<input type="text" class="form-control" name="iconclass" id="editiconclass">
								</div>
							</div>
							<div class="form-group">
								<label for="editotherRes" class="col-sm-4 col-md-4 control-label">依赖资源</label>
								<div class="col-sm-7 col-md-7">
									<textarea class="form-control" id="editotherRes" name="otherRes"></textarea>
								</div>
							</div>
							<%--<div class="form-group">
								<label for="editxtype" class="col-sm-4 col-md-4 control-label">权限类型</label>
								<div class="col-sm-7 col-md-7">
									<select class="form-control" id="editxtype" name="xtype">
										<option value="1">公共权限</option>
										<option value="0">普通权限</option>
									</select>
								</div>
							</div>--%>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-default" id="btn-edit">修改</button>
									<button type="button" class="btn btn-danger" id="btn-del">删除</button>
									<button type="button" class="btn btn-default" id="btn-upload">上传更新</button>
								</div>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
	</section>

	<div class="modal fade" id="showAuthModal" tabindex="-1" aria-labelledby="dictLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="editdictLabel">新增资源</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="system/auth/add" method="post" id="editEmpForm">
						<div class="form-group">
							<label for="resName" class="col-sm-3 control-label">上级资源</label>
							<div class="col-sm-6">
								<input type="hidden" id="ppid" name="pid"/>
								<input type="text" class="form-control" id="pidName">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="resName" class="col-sm-3 control-label">资源编号</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="res" name="resId">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="resName" class="col-sm-3 control-label">资源名称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="resName" name="resName">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>

						<div class="form-group">
							<label for="resName" class="col-sm-3 control-label">权限类型</label>
							<div class="col-sm-6">
								<select class="form-control" name="resType">
									<option value="2">菜单</option>
									<option value="3">权限</option>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>

						<div class="form-group">
							<label for="resName" class="col-sm-3 control-label">资源路径</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="resName" name="resUrl">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>

						<div class="form-group">
							<label for="resName" class="col-sm-3 control-label">图标</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="resName" name="iconclass">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>

						<div class="form-group">
							<label for="resName" class="col-sm-3 control-label">类型</label>
							<div class="col-sm-6">
								<select class="form-control" name="xtype">
									<option value="1">公共权限</option>
									<option value="0">普通权限</option>
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>


					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>
					<button type="submit" id="btn-edit-save" form="editEmpForm" class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>



	<div class="modal fade" id="showUploadModal" tabindex="-1" aria-labelledby="dictLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">上传更新资源</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="system/auth/upload" method="post" id="uploadForm">
						<div class="form-group">
							<label for="resName" class="col-sm-3 control-label">文件</label>
							<div class="col-sm-6">
								<input type="file" class="form-control" name="upload">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>
					<button type="submit" id="btn-upload-save" form="uploadForm" class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- /.content -->
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<script type="text/javascript"
		src="static/ztree/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript" src="static/js/system/auths.js"></script>
</body>
</html>

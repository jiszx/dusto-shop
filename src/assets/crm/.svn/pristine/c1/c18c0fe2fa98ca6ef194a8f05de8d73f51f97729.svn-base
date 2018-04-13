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
<title>管理系统-区域管理</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
</head>
<body class="container-fluid">
	<section class="content-header">
		<h1>
			行政区域管理<small>行政区域管理</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 系统配置</a></li>
			<li class="active">行政区域管理</li>
		</ol>
	</section>
	<!-- Main content -->
	<section class="content">


		<div class="panel panel-default col-md-12" style="max-height: 542px;">
			<div class="panel-body">
				<div class="col-md-12" style="padding-left:0">
					<div class="col-sm-4 col-md-4" style="border-right: 2px solid #f0f0f0;height:500px;">
						<div id="areaTree"  style="width: 100%; height: 100%;">
							<ul id="tree" class="ztree" style="width: 100%; height: 100%;"></ul>
						</div>
					</div>
					<div class="col-sm-8 col-md-8">
						<form class="form-horizontal col-md-12" id="editAreaForm" action="pub/area/edit" method="post">
							<div class="form-group">
								<label for="editname" class="col-sm-2 col-md-2 control-label">系统编号</label>
								<div class="col-sm-6 col-md-6">
									<input type="text" readonly="readonly" class="form-control"  name="id" id="editid" >
								</div>
								<div class="help-block col-sm-4"></div>
							</div>
							<div class="form-group">
								<label for="editname" class="col-sm-2 col-md-2 control-label">区域名称</label>
								<div class="col-sm-6 col-md-6">
									<input type="text" class="form-control" id="editname" name="name">
								</div>
								<div class="help-block col-sm-4"></div>
							</div>
							<div class="form-group">
								<label for="editareaCode" class="col-sm-2 col-md-2 control-label">区号</label>
								<div class="col-sm-6 col-md-6">
									<input type="text" class="form-control" name="areaCode" id="editareaCode">
								</div>
								<div class="help-block col-sm-4"></div>
							</div>
							<div class="form-group">
								<label for="editcode1" class="col-sm-2 col-md-2 control-label">外联编号</label>
								<div class="col-sm-6 col-md-6">
									<input type="text" class="form-control" id="editcode1" name="code1">
								</div>
								<div class="help-block col-sm-4"></div>
							</div>
							<div class="form-group">
								<label for="editupCode" class="col-sm-2 col-md-2 control-label">银联编码</label>
								<div class="col-sm-6 col-md-6">
									<input type="text" class="form-control" id="editupCode" name="upCode">
								</div>
								<div class="help-block col-sm-4"></div>
							</div>

							<div class="form-group">
								<label for="editzipCode" class="col-sm-2 col-md-2 control-label">邮政编码</label>
								<div class="col-sm-6 col-md-6">
									<textarea class="form-control" id="editzipCode" name="zipCode"></textarea>
								</div>
								<div class="help-block col-sm-4"></div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-default" id="btn-edit">修改</button>
									<button type="button" class="btn btn-danger" id="btn-del">删除</button>
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
								<input type="text" class="form-control" name="resName">
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
								<input type="text" class="form-control" name="resUrl">
							</div>
							<small class="help-block col-sm-3"></small>
						</div>

						<div class="form-group">
							<label for="resName" class="col-sm-3 control-label">图标</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"  name="iconclass">
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
	<script type="text/javascript" src="static/js/jquery.slimscroll.min.js"></script>
	<script type="text/javascript">
		$(function () {
			$("#areaTree").slimScroll({
                height:"500px",
                size: '5px',
                position: 'right',
                color: '#ffcc00',
                alwaysVisible: false,
                distance: '0px',
                //start: $('#child_image_element'),
                railVisible: true,
                railColor: '#222',
                railOpacity: 0.3,
                wheelStep: 10,
                allowPageScroll: false,
                disableFadeOut: false
            });
        })
	</script>
	<script type="text/javascript" src="static/js/config/area.js"></script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>管理系统-零售商管理</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
    <style type="text/css">
    	.selectWidth{
    	 width:70% !important;
    	}
    	.hide{
    	display:none;}
    	#spred{
    	margin:20px 0;
    	}
    	.form-group{
    	margin:10px 0;}
    	.dropdown-menu {
    	left:15px !important;
    	min-width:109px;}
    </style>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>零售商客户
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 零售商管理</a></li>
        <li class="active">零售商客户列表</li>
    </ol>
</section>
<div class="col-md-12" style="padding:0 0 20px 0">
    <form class="form-inline" id="searchForm">
        <div class="form-group col-md-3 col-sm-6 ">
            <label for="exampleInputEmail3" style="width:94px;">销售组织</label>
            <select class="form-control selectWidth" name="salesOrg" id="salesOrgSelect">
                <option value="">全部</option>
                <c:forEach items="${org }" var="sc">
                	<c:if test="${sc.levels eq '1' }">
                		<optgroup label="${sc.name }">
	                		<c:forEach items="${org }" var="salesOrg">
	                			<c:if test="${salesOrg.pid eq sc.id}">
	                				<option value="${salesOrg.id }">${salesOrg.name }</option>
	                			</c:if>
	                		</c:forEach>
                		</optgroup>
                	</c:if>
                </c:forEach>
            </select>
        </div>
        <div class="form-group col-md-3 col-sm-6 mustShow">
            <label for="exampleInputEmail3" style="width:94px;">客户名称</label>
            <input type="text" class="form-control selectWidth" id="custname" name="customerName" placeheader="请输入客户名称">
        </div>
        <div class="form-group col-md-3 col-sm-6 mustShow">
            <button type="button" class="btn btn-primary" id="searchBtn"><i class="icon icon-search">&nbsp;&nbsp查询</i>
            <button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown" style="margin-left:-8px;">
						<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#" id="moreSearch">筛选</a></li>
					</ul>
        </div>
    </form>
</div>
<div class="col-md-12" id="customerTool" style="padding:0">

    <div class="col-md-12" style="padding:0">
        <div class="btn-group btn-group-sm" role="group" aria-label="...">
            <a type="button" class="btn btn-primary" href="customer/addRetail.html"><i
                    class='icon icon-plus'></i> 添加</a>
            <a type="button" class="btn btn-warning" id="editBtn"><i
                    class='icon icon-edit'></i> 修改</a>
            <a type="button" class="btn btn-danger" id="changeBtn"><i class='icon icon-edit'></i>变更</a>
            <button type="button" id="detailBtn" class="btn btn-info"><i
                    class='icon icon-eye-open'></i> 详情
            </button>
            <button type="button" class="btn btn-del btn-danger"><i class='icon icon-remove'></i> 删除
            </button>
            <button type="button" id="applyBtn" class="btn btn-bitbucket"><i
                    class='icon approval_img'></i> 提交审批
            </button>
            <button type="button" class="btn btn-info" id="importBtn">
				<i class='icon importe_img'></i> 档案导入
			</button>
            <button type="button" class="btn btn-error" id="exportBtn">
				<i class='icon icon-share'></i> 导出信息
			</button>
        </div>
    </div>

</div>
<div class="col-md-12">
    <table id="customerTable"></table>
</div>

<div class="modal fade" id="choosePosition">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">选择岗位</h4>
            </div>
            <div class="modal-body">
                <table id="positionTable"></table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="choosePosiBtn">选择</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="importModal" tabindex="-1" role="dialog"
		aria-labelledby="dictLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">档案导入</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post" enctype="multipart/form-data"
						action="customer/import.json" id="importForm">
						<div class="form-group">
							<label for="pid" class="col-sm-2 control-label">配送商:</label>
							<div class="col-sm-6">
								<select class="form-control" name="pid" id="addPid" >
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div>
						<div class="form-group">
							<label for="colName" class="col-sm-2 control-label">文件:</label>
							<div class="col-sm-6">
								<input type="file" id="lefile"class="form-control hide" id="colName"
									name="colName">
									<div class="input-append">
									<input type="file" class="form-control" id="uploadFile2" name="file"  >
								</div>
							</div>
							<small class="help-block col-sm-4"></small>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" id="importButton" class="btn btn-primary btn-save"
						form="importForm">保存</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="exportModal">
	    <div class="modal-dialog modal-lg">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal"
	                        aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	                <h4 class="modal-title">导出</h4>
	            </div>
	            <div class="modal-body" id="generateFile">
	                                                         正在生成excel，请耐心等待...
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
	        </div>
	        <!-- /.modal-content -->
	    </div>
	    <!-- /.modal-dialog -->
	</div>


<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="static/js/customer/retail.js"></script>
<script type="text/javascript">
$(function() {
	$("#searchForm .form-group").hide();
	$("#searchForm .mustShow").show();
	$("#moreSearch").bind("click",function(){
		if($(this).html()=="筛选"){
			$(this).html("收起")
			$("#searchForm .form-group").show();
		}else{
			$("#searchForm .form-group").hide();
			$("#searchForm .mustShow").show();
			$(this).html("筛选")
		}
	});
})
</script>
</body>
</html>

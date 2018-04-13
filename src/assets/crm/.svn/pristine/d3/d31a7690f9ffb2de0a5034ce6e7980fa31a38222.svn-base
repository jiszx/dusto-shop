<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>管理系统-客户管理</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <style type="text/css">
        .selectWidth {
            width: 70% !important;
            margin: 10px 0;
        }
		.form-horizontal .input-box-list {
			display: table;
			margin: 10px 0 0 0;
			padding-left: 10px;
		}
        .hide {
            display: none;
        }

        .spred {
            margin: 20px 0;
        }
    </style>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>
        客户管理
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 客户管理</a></li>
        <li class="active">客户列表</li>
    </ol>
</section>
<div class="col-md-12" style="padding: 0">
    <!--
<div class="spred col-md-12 col-sm-12">
    <input value="查询条件" type="button" class="btn-btn-success">
</div>
-->
    <form class="form-inline" id="searchForm" onsubmit="searchCustomer();">
        <div class="form-group col-md-3 col-sm-6">
            <label for="exampleInputEmail3">销售组织</label> <select
                class="form-control selectWidth" name="salesOrg"
                id="salesOrgSelect">
            <option value="">全部</option>
            <%-- <c:forEach items="${org }" var="sc">
            <c:if test="${sc.levels eq '1' }">
                <optgroup label="${sc.name }"> --%>
            <c:forEach items="${org }" var="salesOrg">
                <%-- <c:if test="${salesOrg.pid eq sc.id}"> --%>
                <option value="${salesOrg.id }">${salesOrg.name }</option>
                <%-- </c:if> --%>
            </c:forEach>
            <%-- </optgroup>
            </c:if>
        </c:forEach> --%>
        </select>
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label for="exampleInputEmail3">销售大区</label> <select
                class="form-control selectWidth" name="salesArea"
                id="salesAreaSelect">
            <option value="">全部</option>
        </select>
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label for="exampleInputEmail3">业务省市</label> <select
                class="form-control selectWidth" name="bizProvId"
                id="salesProvSelect">
            <option value="">全部</option>
        </select>
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label for="exampleInputEmail3">岗位名称</label> <select
                class="form-control selectWidth" name="position"
                id="positionSelect">
            <option value="" flag="main">全部</option>
            <c:forEach items="${stations }" var="station">
                <option value="${station.stationid }" style="display: none;"
                        flag="${station.stationorgid }">${station.stationname }</option>
            </c:forEach>
        </select>
        </div>
        <div class="form-group col-md-3 col-sm-6 mustShow">
            <label for="exampleInputEmail3">客户名称</label> <input type="text"
                                                                class="form-control selectWidth" name="customerName"
                                                                placeheader="请输入客户名称">
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label for="exampleInputEmail3">营业执照</label> <input type="text"
                                                                class="form-control selectWidth" name="businessLicense"
                                                                placeheader="请输入营业执照号">
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label for="exampleInputEmail3">登陆编码</label> <input type="text"
                                                                 class="form-control selectWidth" name="code"
                                                                 placeheader="登陆编码">
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label for="exampleInputEmail3">SAP编码</label> <input type="text"
                                                                 class="form-control selectWidth" name="sapCustomerId"
                                                                 placeheader="请输入SAP客户编码">
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label for="exampleInputEmail3">销售人员</label> <input type="text"
                                                                class="form-control selectWidth" name="sales"
                                                                placeheader="请输入销售人员名称">
        </div>
        <c:set var="customerStates"
               value="<%=com.hhnz.customer.enu.CustomerBaseStateEnu.values()%>"/>
        <div class="form-group col-md-3 col-sm-6">
            <label for="exampleInputEmail3">客户状态</label> <select
                class="form-control selectWidth" name="status"
                id="search_cust_status">
            <option value="">全部</option>
            <c:forEach var="state" items="${customerStates}">
                <option value="${state.getCode()}" en="${state.toString() }">${state.getDesc()}</option>
            </c:forEach>
        </select>
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label for="exampleInputEmail3">客户类型</label> <select
                class="form-control selectWidth" name="custType">
            <option value="">全部</option>
            <c:forEach items="${dict.get('CUST_MERCH_TYPE') }" var="cust_type">
                <option value="${cust_type.chooseVal}">${cust_type.showText}</option>
            </c:forEach>
        </select>
        </div>
        <div class="form-group col-md-3 com-sm-6 mustShow" style="margin:10px 0;">
            <!-- Split button -->
            <div class="btn-group">
                <button type="submit" class="btn btn-success" id="searchBtn"><i class="icon icon-search">
                    &nbsp;&nbsp查询</i></button>
                <button type="button" class="btn btn-success dropdown-toggle"
                        data-toggle="dropdown">
                    <span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="#" id="moreSearch">筛选</a></li>
                </ul>
            </div>

        </div>
</div>
</form>
</div>
<div class="col-md-12" id="customerTool" style="padding: 0">

    <div class="col-md-12" style="padding: 0">
        <div class="btn-group btn-group-sm" role="group" aria-label="...">
            <%--<a type="button" class="btn btn-primary" href="customer/index.html"><i
                    class='icon icon-plus'></i> 添加</a>--%>

            <!-- Single button -->
            <div class="btn-group btn-group-sm">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                    新增 <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="customer/index4.html">盐业公司</a></li>
                    <li><a href="customer/index1.html">合作盐业公司</a></li>
                    <!-- <li><a href="customer/index2.html">配送商</a></li> -->
                    <li><a href="customer/index3.html">KA</a></li>
                    <!-- <li><a href="customer/index7.html">仓储服务商</a></li> -->
                    <li><a href="customer/index70.html">合作仓储服务商</a></li>
                    <li><a href="customer/index8.html">物流商</a></li>
                    <li><a href="customer/index9.html">特通渠道</a></li>
                    <li><a href="customer/index10.html">NKA</a></li>
                </ul>
            </div>
            <a type="button" class="btn btn-warning" id="editBtn"><i class='icon icon-edit'></i>修改</a>
            <a type="button" class="btn btn-danger" id="changeBtn"><i class='icon icon-edit'></i>变更</a>
            <button type="button" id="detailBtn" class="btn btn-info">
                <i class='icon icon-eye-open'></i> 详情
            </button>
            <button type="button" id="applyBtn" class="btn btn-bitbucket">
                <i class='icon approval_img'></i> 提交审批
            </button>
            <button type="button" class="btn btn-del btn-danger">
                <i class='icon icon-remove'></i> 删除
            </button>
            <button type="button" class="btn btn-warning" id="setPositionBtn"
                    style="background-color: rgb(167, 86, 88);">
                <i class='icon icon-star'></i>指定岗位
            </button>
            <button type="button" class="btn btn-primary" id="changeOrg">
                <i class='icon icon-remove'></i> 变更销售组织
            </button>
            <input type="hidden" id="upStates">
            <button type="button" class="btn btn-primary hide" id="doUpStates">
                <i class='icon icon-remove'></i> 休眠/激活
            </button>
            <button type="button" class="btn btn-private" id="exportBtn">
                <i class='icon icon-car'></i> 导出
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
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">选择岗位</h4>
            </div>
            <div class="modal-body">
                <table id="positionTable"></table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="choosePosiBtn">选择</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

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

	<div class="modal fade" id="changeOrgModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">变更销售主题</h4>
				</div>
				<div class="modal-body">
					<form action="" class="form-horizontal">
					<div class="form-group">
						<label for="material" class="col-sm-3 control-label">原销售组织:</label>
						<div class="col-sm-6">
							<select  class="form-control" id="oldOrgId" name="oldOrgId" disabled="disabled">
								<c:forEach items="${org }" var="salesOrg">
									<c:if test="${salesOrg.levels==2}">
										<option value="${salesOrg.id }">${salesOrg.name }</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<input type="hidden" id="merchId" name="merchId">
					<div class="form-group">
						<label for="material" class="col-sm-3 control-label">变更销售组织:</label>
						<div class="col-sm-6">
							<select class="form-control" id="newOrgId" name="newOrgId">
								<option></option>
								<c:forEach items="${org }" var="salesOrg">
									<c:if test="${salesOrg.levels==2}">
										<option value="${salesOrg.id }">${salesOrg.name }</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<div class="form-group">
						<label for="material" class="col-sm-3 control-label">修改后客户类型:</label>
						<div class="col-sm-6">
							<select class="form-control" id="changeType" name="changeType">
								<option value='7'>仓储服务商</option>
								<option value='70'>合作仓储服务商</option>
							</select>
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<div class="form-group">
						<label for="material" class="col-sm-3 control-label">原客户是否休眠:</label>
						<div class="col-sm-6">
							<select class="form-control" id="states" name="states">
								<option value='4'>是</option>
								<option value=''>否</option>
							</select>
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					<div class="form-group">
						<label for="material" class="col-sm-3 control-label">修改后所属岗位:</label>
						<div class="col-sm-6">
							<select class="form-control" id="stationId" name="stationId">
								<option></option>
							</select>
						</div>
						<small class="help-block col-sm-3"></small>
					</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary btn-save" id="change_org_save">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript" src="static/js/customer/list.js"></script>
<script type="text/javascript">
    /* $(document).ready(function(){
     $(".spred").click(function(){
     $("#searchForm").removeClass("hide")
     $(".spred").hide();
     })
     $(".close").click(function(){
     $("#searchForm").addClass("hide")
     $(".spred").show();
     })
     }); */
    $(function () {
        $("#searchForm .form-group").hide();
        $("#searchForm .mustShow").show();
        $("#moreSearch").bind("click", function () {
            if ($(this).html() == "筛选") {
                $(this).html("收起")
                $("#searchForm .form-group").show();
            } else {
                $("#searchForm .form-group").hide();
                $("#searchForm .mustShow").show();
                $(this).html("筛选")
            }
        })
    })
</script>
</body>
</html>

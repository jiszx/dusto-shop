<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
<title>管理系统-报表统计</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
<link href="static/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		统计报表<small>销售订单明细表</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
		<li class="active">销售订单明细</li>
	</ol>
</section>
<div class="col-md-12" id="querycondition" style="padding-left:0px;">

    <form class=" form-inline"  onsubmit="doSearch();return false;">
        <div class="form-group col-md-3 col-sm-6">
            <label>客户名称　:</label>
            <input type="text" class="form-control" id="custname">
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label>开始时间　:</label>
            <input type="text" class="form-control" placeholder="请输入开始时间" id="startTime"
            >
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label>结束时间　:</label>
            <input type="text" class="form-control" placeholder="请输入结束时间" id="endTime">
        </div>

        <div class="form-group col-md-3 col-sm-6">
            <label>销售人员 　:</label>
            <input type="text" class="form-control" placeholder="销售人员" id="saler">
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label>物料编号 　:</label>
            <input type="text" class="form-control" placeholder="物料编号" id="materialId">
        </div>
        <div class="form-group col-md-3 col-sm-6">
            <label for="exampleInputEmail3">销售组织</label> <select
                class="form-control selectWidth" name="salesOrg"
                id="salesOrgSelect">
            <option value="">全部</option>
            <c:forEach items="${org }" var="salesOrg">
                <option value="${salesOrg.id }">${salesOrg.name }</option>
            </c:forEach>
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
            <button type="submit" class="btn btn-success" id="btn-search"><i class="icon icon-search"></i>&nbsp;&nbsp;查询</button>
        </div>
    </form>
</div>

<div class="col-md-12" id="dictTool">
	<div class="btn-group btn-group-sm" role="group" aria-label="...">
		<button type="button" id="exportBtn" class="btn btn-info">
			<i class='icon icon-share'></i> 导出
		</button>
	</div>
</div>
<div class="col-md-12">
	<table id="orderTable">

	</table>
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

	<script type="text/javascript">
		function getorderType(value) {
	        var data = new Object();
	        <c:forEach items="${dict['ORDER_TYPE']}" var="type">
	        data["${type.chooseVal}"] = "${type.showText}"
	        </c:forEach>
	        if (data[value]) {
	            return data[value];
	        } else {
	            return "未知";
	        }
	    }
		function getcustType(value) {
	        var data = new Object();
	        data["5"] = "零售商";
	        <c:forEach items="${dict['CUST_MERCH_TYPE']}" var="type">
	        data["${type.chooseVal}"] = "${type.showText}"
	        </c:forEach>
	        if (data[value]) {
	            return data[value];
	        } else {
	            return "未知";
	        }
	    }
	</script>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="static/js/report/orderdetails.js"></script>
</body>
</html>

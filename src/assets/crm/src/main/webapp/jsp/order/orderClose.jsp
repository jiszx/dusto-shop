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
    <title>管理系统-关闭订单</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <style type="text/css">
        .font-color {
            color: #ccc;
        }

        #logisticstails th {
            text-align: center !important;
        }

        #logisticstails th, #logisticstails td {
            border: 1px solid #ccc;
        }

        #orderdetails th, #orderdetails td {
            border: 1px solid #ccc;
        }
    
    	.customizeWidth{
    	width:70%;
    	display:inline-block;
    	}
    </style>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>
        订单管理
        <small>关闭订单</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li class="active">订单管理</li>
    </ol>
</section>
<div class="col-md-12 col-sm-12">
	<div class="col-md-12" id="querycondition" style="padding-left:0px;">
		 		<div class="form-group col-md-4 col-sm-6">
		 			<label>客户名称:</label>
		            <input type="text" class="form-control customizeWidth" id="scustomer">
	 			</div>
	 			<div class="form-group col-md-4 col-sm-12">
		 			<label>CRM订单编号:</label>
		            <input type="text" class="form-control customizeWidth" id="crmorderid">
	 			</div>
	 			<div class="form-group col-md-4 col-sm-12">
		 			<label>SAP订单编号:</label>
		            <input type="text" class="form-control customizeWidth" id="saporderid">
	 			</div>
	 			<div class="form-group col-md-6 col-sm-6">
		 			<label>时间段:</label>
		            <input type="text" class="form-control" placeholder="请输入开始时间" id="startTime" style="width:30%;display:inline-block"><span>至</span>
		            <input type="text" class="form-control" placeholder="请输入结束时间" id="endTime" style="width:30%;display:inline-block">
	 			</div>
		 		<div class="col-md-2 col-sm-2">
		 			<button class="btn btn-success" id="btn-search"><i class="icon icon-search"></i>&nbsp;&nbsp;查询</button>
		 		</div>
	</div>
    <div class="col-md-12 col-sm-12" id="ordersearchTool" style="padding:0;">
        <div class="btn-group btn-group-sm" role="group" aria-label="...">
            <button type="button" id="btn-cancel" class="btn btn-danger">
                <i class='icon icon-remove'></i> 关闭订单
            </button>
            <button type="button" id="btn-detalis" class="btn btn-info">
                <i class='icon icon-eye-open'></i> 订单详情
            </button>
        </div>
    </div>
    <div class="col-md-12 col-sm-12">
        <table id="orderTable">
        </table>
    </div>
</div>
<!-- msgModal -->
	<div class="modal fade" id="msgModal" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">错误信息</h4>
				</div>
				<div class="modal-body">
					 <span id="sapmsg"></span>
					 <input type="hidden" id="orderid">
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="btn-pass-agin" class="btn btn-primary">再次关闭</button>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
    function getorderStates(value){
    	var data = new Object();
		<c:forEach items="${dict['OM_ORDER_HEADER_TYPE']}" var="type">
		data["${type.chooseVal}"] = "${type.showText}"
		</c:forEach>
		if (data[value]) {
			return data[value];
		} else {
			return "未知";
		}
    }
    function getorderType(value){
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
</script>
<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript"src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="static/js/order/orderClose.js"></script>
</body>
</html>

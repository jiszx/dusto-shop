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
    <title>管理系统-仓储订单查询</title>
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
    	width:60%;
    	display:inline-block;
    	}
    	@media screen and (max-width: 1024px) {
        
    	label{
    	width:100px;
    	text-align:right
    	}
}
    </style>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>
        订单管理
        <small>订单查询</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li class="active">零售商订单管理</li>
    </ol>
</section>
<div >
	<div class="col-md-12" id="querycondition" style="padding-left:0px;">
		 		<div class="form-group col-md-4 col-sm-12">
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
	 			<div class="form-group col-md-6 col-sm-10">
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
            <button type="button" id="btn-detail" class="btn btn-info">
                <i class='icon icon-eye-open'></i> 详情
            </button>
            <!-- <button type="button" id="btn-edit" class="btn btn-edit btn-warning">
                <i class='icon icon-edit'></i> 修改
            </button> -->
            <button type="button" id="btn-del" class="btn btn-del btn-danger">
                <i class='icon icon-remove'></i> 删除
            </button>
            <button type="button" id="generateBatchBtn" class="btn btn-del btn-primary">
                <i class='icon icon-plus'></i>提交送审
            </button>
            <button type="button" id="cancelOrder" class="btn btn-info">
                <i class='icon icon-remove-sign'></i>取消调拨单
            </button>
            <button type="button" id="importBtn" class="btn btn-primary">
                <i class='icon icon-briefcase'></i>导入
            </button>
            <button type="button" id="exportBtn" class="btn btn-warning">
                <i class='icon icon-share'></i>导出详情
            </button>
            <button type="button" id="exportBtn2" class="btn btn-info">
                <i class='icon icon-share'></i>导出订单列表
            </button>
        </div>
    </div>
    <div class="col-md-12 col-sm-12">
        <table id="orderTable">
        </table>
    </div>
    
    <!-- import -->
    <div class="modal fade" id="importModal" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="dictLabel">订单批量导入</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post"
						action="order/retail/import" id="importForm" enctype="multipart/form-data">
						<input type="hidden" name="orgid" id="orgid">
						<input type="hidden" name="stationid" id="stationid">
						<!-- <div class="form-group">
							<label for="organizationId" class="col-sm-3 control-label">配送商</label>
							<div class="col-sm-6">
								<select class="form-control" name="merchCustId"
									id="merchCustId" onchange="changeShip(this.value)">
								</select>
							</div>
							<small class="help-block col-sm-3"></small>
						</div> -->
						<div class="form-group">
							<label for="payAmt" class="col-sm-3 control-label">资源文件:</label>
							<div class="col-sm-6">
								<input type="file" accept=".xlsx"  class="form-control" id="file"
									name="file">
							</div>
							<small class="help-block col-sm-3"></small>
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

</div>
<script type="text/javascript">
	var batchNum = "${batch}";
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
<script type="text/javascript" src="static/js/order/transfer.js"></script>
</body>
</html>

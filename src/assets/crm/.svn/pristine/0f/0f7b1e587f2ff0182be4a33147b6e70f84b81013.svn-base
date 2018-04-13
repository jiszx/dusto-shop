<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>管理系统-追加物流信息</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <style type="text/css">
    	table td input{
    	width:100%;
    	}
    </style>
</head>
<body class="container-fluid">
<div class="col-md-12" style="min-height: 200px; padding: 10px 0; background-color: #FFF;">
    <%-- block 2 --%>
    <div class="page-header" id="block-2">
        <h4 class="text-info">
            <strong>1.&nbsp;</strong>订单信息&nbsp;-&nbsp;
            <small>Order Information.
            </small>
        </h4>
    </div>
    <table class="table table-condensed table-bordered">
        <tbody>
        <tr>
            <th class="active">客户名称</th>
            <td>${order.merchCustName }</td>
            <th class="active">SAP编号</th>
            <td>${order.sapOrderId }</td>
            <th class="active">下单时间</th>
            <td><fmt:formatDate value="${order.createTs }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
        <tr>
            <th class="active">销售组织</th>
            <td>${order.organizationName }</td>
            <th class="active">大区</th>
            <td>${order.salesAreaName }</td>
            <th class="active">省区</th>
            <td>${order.salesProvName }</td>
        </tr>
        <%-- <tr>
            <th class="active">订单总额</th>
            <td>${order.orderAmt/100 }</td>
            <th class="active">折扣金额</th>
            <td>${order.discountAmt/100 }</td>
            <th class="active">现金金额</th>
            <td>${order.cashAmt/100 }</td>
        </tr>
        <tr>
            <th class="active">货补金额</th>
            <td>${order.hbAmt/100 }</td>
            <th class="active">授信金额</th>
            <td>${order.creditAmt/100 }</td>
            <th class="active">订单净额</th>
            <td>${order.amt/100 }</td>
        </tr> --%>
        <tr>
            <th class="active">销售人员</th>
            <td>${order.salesman }</td>
            <th class="active">备注</th>
            <td colspan="3">${order.remark }</td>
        </tr>
        </tbody>
    </table>
    
    <%-- block 3 --%>
    <div class="page-header" id="block-3" style="margin-bottom: 0;">
        <h4 class="text-info">
            <strong>2.&nbsp;</strong>物流信息&nbsp;-&nbsp;
            <small>Logistics Information.
            </small>
        </h4>
    </div>
    <form action="" method="post" id="saveForm">
        <c:set var="totalNum" value="0"></c:set>
        <c:choose>
        	<%-- 调拨单 --%>
        	<c:when test="${orderType eq '5' ||  orderType eq '8'}">
        		<table class="table table-condensed table-bordered">
		            <tr>
		                <th class="active">调拨单号</th>
		                <td colspan="8"><input class="form-control" name="sapDeliveryNo" type="hidden" value="${order.id }">${order.id }</td>
		            </tr>
		            <tr class="active">
		                <th>物流公司</th>
		                <th>到站</th>
		                <th>物流单号</th>
		                <th>发货时间</th>
		                <th>物流费用（元）</th>
		                <th>发货司机</th>
		                <th>司机电话</th>
		                <th>车牌号</th>
		                <th>备注</th>
		            </tr>
		            <c:choose>
		            	<c:when test="${empty logisticsList || logisticsList.size() eq 0 }">
		            		<tr>
				                <td><input class="form-control" name="logistics"/></td>
				                <td><input class="form-control" name="site"/></td>
				                <td><input class="form-control" name="logisticsNo"/></td>
				                <td><input class="form-control datetimepicker" name="deliveryTs"/></td>
				                <td><input class="form-control" name="logisticsCost"/></td>
				                <td><input class="form-control" name="driverName"/></td>
				                <td><input class="form-control" name="driverTel"/></td>
				                <td><input class="form-control" name="plateNo"/></td>
				                <td><input class="form-control" name="remark"/></td>
				            </tr>
		            	</c:when>
		            	<c:otherwise>
		            		<c:set var="hasLogistics" value="0"></c:set>
		            		<c:forEach items="${logisticsList }" var="logistics">
				            	<c:if test="${logistics.sapDeliveryNo eq order.id }">
				            		<c:set var="hasLogistics" value="1"></c:set>
				            		<tr>
						                <td><input class="form-control" name="logistics" value="${logistics.logistics }"/></td>
						                <td><input class="form-control" name="site" value="${logistics.site }"/></td>
						                <td><input class="form-control" name="logisticsNo" value="${logistics.logisticsNo }"/></td>
						                <td><input class="form-control datetimepicker" name="deliveryTs" value='<fmt:formatDate value="${logistics.deliveryTs }" pattern="yyyy-MM-dd HH:mm:ss"/>'/></td>
						                <td><input class="form-control" name="logisticsCost" value="${logistics.logisticsCost }"/></td>
						                <td><input class="form-control" name="driverName" value="${logistics.driverName }"/></td>
						                <td><input class="form-control" name="driverTel" value="${logistics.driverTel }"/></td>
						                <td><input class="form-control" name="plateNo" value="${logistics.plateNo }"/></td>
						                <td><input class="form-control" name="remark" value="${logistics.remark }"/></td>
						            </tr>
				            	</c:if>
				            </c:forEach>
				            <c:if test="${hasLogistics eq '0' }">
				            	<tr>
					                <td><input class="form-control" name="logistics"/></td>
					                <td><input class="form-control" name="site"/></td>
					                <td><input class="form-control" name="logisticsNo"/></td>
					                <td><input class="form-control datetimepicker" name="deliveryTs"/></td>
					                <td><input class="form-control" name="logisticsCost"/></td>
					                <td><input class="form-control" name="driverName"/></td>
					                <td><input class="form-control" name="driverTel"/></td>
					                <td><input class="form-control" name="plateNo"/></td>
					                <td><input class="form-control" name="remark"/></td>
					            </tr>
				            </c:if>
		            	</c:otherwise>
		            </c:choose>
		        </table>
		        <div class="form-group text-center">
				    <button class="btn btn-primary" id="saveBtn" >确定</button>
				</div>
        	</c:when>
        	<%-- 其他订单 --%>
        	<c:otherwise>
        		<c:set var="delivered" value="0"></c:set>
        		<c:forEach items="${deliveryNos }" var="dn">
        			<c:set var="delivered" value="${delivered+1 }"></c:set>
			        <table class="table table-condensed table-bordered">
			            <tr>
			                <th class="active">SAP交货单编号</th>
			                <td colspan="4"><input class="form-control" name="sapDeliveryNo" type="hidden" value="${dn.key }">${dn.key }</td>
			                <th class="active">SAP交货时间</th>
			                <td colspan="4"><fmt:formatDate value="${dn.value }" pattern="yyyy-MM-dd"/></td>
			            </tr>
			            <tr class="active">
			                <th>编号</th>
			                <th colspan="5">产品名称</th>
			                <th>订单数量</th>
			                <th colspan="3">发货数量</th>
			            </tr>
			            <c:forEach items="${items }" var="item">
			            	<c:if test="${item.deliveryNo eq dn.key }">
					            <tr>
					                <td>${item.deliveryitemNo }</td>
					                <td colspan="5">${item.materailName }</td>
					                <td>${item.orderNum }</td>
					                <td colspan="3">${item.num }</td>
					            </tr>
					            <c:set var="totalNum" value="${item.num+totalNum}"></c:set>
			            	</c:if>
			            </c:forEach>
			            <tr class="active">
			                <th>物流公司</th>
			                <th>到站</th>
			                <th>物流单号</th>
			                <th>发货时间</th>
			                <th>物流费用（元）</th>
			                <th>发货司机</th>
			                <th>司机电话</th>
			                <th>车牌号</th>
			                <th>备注</th>
			            </tr>
			            <c:choose>
			            	<c:when test="${empty logisticsList || logisticsList.size() eq 0 }">
			            		<tr>
					                <td><input class="form-control" name="logistics"/></td>
					                <td><input class="form-control" name="site"/></td>
					                <td><input class="form-control" name="logisticsNo"/></td>
					                <td><input class="form-control datetimepicker" name="deliveryTs"/></td>
					                <td><input class="form-control" name="logisticsCost"/></td>
					                <td><input class="form-control" name="driverName"/></td>
					                <td><input class="form-control" name="driverTel"/></td>
					                <td><input class="form-control" name="plateNo"/></td>
					                <td><input class="form-control" name="remark"/></td>
					            </tr>
			            	</c:when>
			            	<c:otherwise>
			            		<c:set var="hasLogistics" value="0"></c:set>
			            		<c:forEach items="${logisticsList }" var="logistics">
					            	<c:if test="${logistics.sapDeliveryNo eq dn.key }">
					            		<c:set var="hasLogistics" value="1"></c:set>
					            		<tr>
							                <td><input class="form-control" name="logistics" value="${logistics.logistics }"/></td>
							                <td><input class="form-control" name="site" value="${logistics.site }"/></td>
							                <td><input class="form-control" name="logisticsNo" value="${logistics.logisticsNo }"/></td>
							                <td><input class="form-control datetimepicker" name="deliveryTs" value='<fmt:formatDate value="${logistics.deliveryTs }" pattern="yyyy-MM-dd HH:mm:ss"/>'/></td>
							                <td><input class="form-control" name="logisticsCost" value="${logistics.logisticsCost }"/></td>
							                <td><input class="form-control" name="driverName" value="${logistics.driverName }"/></td>
							                <td><input class="form-control" name="driverTel" value="${logistics.driverTel }"/></td>
							                <td><input class="form-control" name="plateNo" value="${logistics.plateNo }"/></td>
							                <td><input class="form-control" name="remark" value="${logistics.remark }"/></td>
							            </tr>
					            	</c:if>
					            </c:forEach>
					            <c:if test="${hasLogistics eq '0' }">
					            	<tr>
						                <td><input class="form-control" name="logistics"/></td>
						                <td><input class="form-control" name="site"/></td>
						                <td><input class="form-control" name="logisticsNo"/></td>
						                <td><input class="form-control datetimepicker" name="deliveryTs"/></td>
						                <td><input class="form-control" name="logisticsCost"/></td>
						                <td><input class="form-control" name="driverName"/></td>
						                <td><input class="form-control" name="driverTel"/></td>
						                <td><input class="form-control" name="plateNo"/></td>
						                <td><input class="form-control" name="remark"/></td>
						            </tr>
					            </c:if>
			            	</c:otherwise>
			            </c:choose>
			        </table>
		    	</c:forEach>
		    	<c:if test="${delivered eq 0}">
		    		<span style="padding: 12px;">未接收到SAP出库单，无法进行物流信息的追加。</span>
		    	</c:if>
			    <c:if test="${totalNum gt 0}">
					<div class="form-group text-center">
					    <button class="btn btn-primary" id="saveBtn" >确定</button>
					</div>
				</c:if>
        	</c:otherwise>
        </c:choose>
    	<input class="form-control" name="orderId" value="${order.id }" type="hidden">
    </form>
</div>
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
</body>
<script type="text/javascript">
$(function(){
	$(".datetimepicker").datetimepicker({
		 minView: "hour",
		 format: "yyyy-mm-dd hh:ii:ss", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        showSecond: true,
        autoclose:true //选择日期后自动关闭
	});

	//保存
	$("#saveBtn").click(function(){
		if(validate("#saveForm").form()){
			renameLogistics();
			$("#saveForm").attr("action","order/submitWL.html").submit();
		}
	})
});

//form表单重命名
function renameLogistics(){
	$('form table').each(function(index,dom){
		$(dom).find("input").each(function(){
			var oname = $(this).attr("name");
			var nname = "logisticsList["+index+"]."+oname;
			$(this).attr("name",nname);
		})
	});
}

//字段校验
function validate(formId){
    var validator = $(formId).validate({
        rules: {
        	logisticsCost: {number: true},
        	logistics: {maxlength: 100},
        	site: {maxlength: 16},
        	logisticsNo: {maxlength: 16},
        	remark:{maxlength: 100}
        }
    });
    return validator;
}
</script>
</html>

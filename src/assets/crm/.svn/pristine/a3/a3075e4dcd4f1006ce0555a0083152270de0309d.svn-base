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
    <title>管理系统-关闭订单</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <style type="text/css">
    	table td input{
    	width:100%;}
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
            <td>${order.custname }</td>
            <th class="active">SAP编号</th>
            <td>${order.header.sapOrderId }</td>
            <th class="active">下单时间</th>
            <td><fmt:formatDate value="${order.header.createTs }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
        <tr>
            <th class="active">销售组织</th>
            <td>${order.orgname }</td>
            <th class="active">大区</th>
            <td>${order.region }</td>
            <th class="active">省区</th>
            <td>${order.prov }</td>
        </tr>
        <tr>
            <th class="active">订单总额</th>
            <td>${order.header.amt }</td>
            <th class="active">折扣金额</th>
            <td>${order.header.discountAmt }</td>
            <th class="active">现金金额</th>
            <td>${order.header.cashAmt }</td>
        </tr>
        <tr>
            <th class="active">货补金额</th>
            <td>${order.header.hbAmt }</td>
            <th class="active">授信金额</th>
            <td>${order.header.creditAmt }</td>
            <th class="active">订单净额</th>
            <td>${order.header.orderAmt }</td>
        </tr>
        <tr>
            <th class="active">销售人员</th>
            <td>${order.saleman }</td>
            <th class="active">备注</th>
            <td colspan="3">${order.header.remark }</td>
        </tr>
        </tbody>
    </table>
    <%-- block 3 --%>
    <div class="page-header" id="block-3" style="margin-bottom: 0;">
        <h4 class="text-info">
            <strong>2.&nbsp;</strong>产品信息&nbsp;-&nbsp;
            <small>Product Information.
            </small>
        </h4>
    </div>
    <form action="" method="post" id="saveForm">
        <table class="table table-condensed table-bordered">
            <tr  class="active">
                <th>产品名称</th>
                <th>数量</th>
            </tr>
            <c:forEach items="${order.lines }" var="line">
            	<c:if test="${line.id!=0 and line.id!=null }">
            		 <tr>
		                <td>${line.sku }</td>
		                <td>${line.num }</td>
		            </tr>
            	</c:if>
            </c:forEach>
        </table>
        <div class="page-header" id="block-3" style="margin-bottom: 0;">
	        <h4 class="text-info">
	            <strong>3.&nbsp;</strong>发票信息&nbsp;-&nbsp;
	            <small>Invoice Information.
	            </small>
	        </h4>
	    </div>
        <table class="table table-condensed table-bordered">
            <tr  class="active">
                <th>产品名称</th>
                <th>发票号</th>
                <th>金额</th>
                <th>税率</th>
                <th>税额</th>
            </tr>
            <c:forEach items="${invoice }" var="val">
           		 <tr>
	                <td>${val.sku }</td>
	                <td>${val.invoice_no }</td>
	                <td>${val.amt }</td>
	                <td>${val.tax_rate }</td>
	                <td>${val.tax }</td>
	            </tr>
            </c:forEach>
        </table>
        <input name="taskId" value="${taskId }" type="hidden">
        <div class="form-group text-center">
            <button class="btn btn-primary" id="saveBtn">关闭订单</button>
        </div>
    </form>
</div>
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript">
$(function(){

	//保存
	$("#saveBtn").click(function(){
		renameLogistics();
		$("#saveForm").attr("action","order/submitCloseOrder.html").submit();
	})
});

//form表单重命名
function renameLogistics(){
	$('form table tr:not(:eq(0))').each(function(index,dom){
		$(dom).find("input").each(function(){
			var oname = $(this).attr("name");
			var nname = "orders["+index+"]."+oname;
			$(this).attr("name",nname);
		})
	});
}
</script>
</body>
</html>

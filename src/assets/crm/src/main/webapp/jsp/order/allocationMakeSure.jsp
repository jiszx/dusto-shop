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
    <title>管理系统-调拨单收货确认</title>
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
            <th class="active">调拨单编号</th>
            <td>${order.orderid }</td>
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
            <strong>2.&nbsp;</strong>待确认产品&nbsp;-&nbsp;
            <small>Product Information.
            </small>
        </h4>
    </div>
   <form action="" method="post" id="saveForm">
        <table class="table table-condensed table-bordered">
            <tr  class="active">
            	<th>物料编码</th>
                <th>产品名称</th>
                <th>箱内数量</th>
                <th>订单数量</th>
                <th>发货数量</th>
                <th>确认数量</th>
                <th>备注</th>
            </tr>
            <c:forEach items="${order.lines }" var="line">
            	<c:if test="${line.id!=0 and line.id!=null }">
            		 <tr>
            		 	<td>${line.materialId }</td>
		                <td>${line.sku }</td>
		                <td>${line.amounts }</td>
		                <td>${line.num/line.amounts }</td>
		                <td>${line.deliverynum/line.amounts }</td>
		                <td><input name="deliveredNum"  value="${line.deliverynum/line.amounts}"/></td>
		                <td><input  name="attribute4" value="${line.attribute4 }"/></td>
		                <td><input name="lineId" value="${line.id }" type="hidden" /></td>
		                <input name="id" value="${line.spliteid }" type="hidden" />
		                <input name="attribute1" value="${line.amounts }" type="hidden" />
		                <input type="hidden" name="attribute3" value="${line.deliverynum }">
		            </tr>
            	</c:if>
            </c:forEach>
        </table>
        <input name="orderId" value="${order.orderid}" type="hidden">
        <div class="form-group text-center">
            <button class="btn btn-primary" id="saveBtn">确定</button>
        </div>
    </form>
</div>
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript">
$(function(){

	//保存
	$("#saveBtn").click(function(){
		$("#saveBtn").attr("disabled","true");
		renameLogistics();
		$("#saveForm").attr("action","order/allocationSubmitMakeSure").submit();
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

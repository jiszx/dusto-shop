<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=9"/> -->
<head>
<base href="<%=basePath%>">
<title>Insert title here</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<script type="text/javascript">
		var invoicesData = ${data};
		var invoiceType ="${invoiceType}";
		var infoInvoicer ="${InfoInvoicer}";
		var IsMerge="${IsMerge}";
		var sourcesType ="${sourcesType}"
		var ids="${ids}";
	</script>
	<jsp:include page="/common/footjs.jsp"></jsp:include>
	<jsp:include page="/common/footjs-table.jsp"></jsp:include>
	<script type="text/javascript" src="static/js/invoiceSecurity/billingInvoice.js"></script>
</head>
<body class="container-fluid content">
	<OBJECT id="MyTaxCard" codeBase="static/MyTaxCard.cab#version=1,0,0,1"
		classid="CLSID:E983B8C5-FD15-4814-9082-11A8C2E233BF"
		 WIDTH=0 HEIGHT=0>
	</OBJECT>
	<section class="content-header">
		<h1>
			金税发票开具<small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
			<li><a href="#"> 金税发票</a></li>
			<li class="active">发票信息</li>
		</ol>
	</section>
	<div class="col-md-12" id="upAccountTool" style="padding-left:0;">
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<!-- <button type="button" class="btn btn-primary" id="btn-open">
				<i class='icon icon-plus'></i>打开金税卡
			</button> -->
			<button type="button" class="btn btn-edit btn-warning" id="ben-invoice">
				<i class='icon icon-edit'></i>发票开具
			</button>
			<!-- <button type="button" class="btn btn-del btn-danger" id="btn-close">
				<i class='icon icon-remove'></i>关闭金税卡
			</button> -->
		</div>
	</div>
	<div class="col-md-12" style="margin-top:40px; margin-left:100px;text-align: left; font-size: 12px; line-height: 22px;">
	<c:forEach items="${list}" var="it">
		 <table style="width: 730px; margin-top: 20px;">
          <tr>
            <td width="8%">发票类型:</td>
            <td width="42%">
            <c:if test="${it.header.invoiceType=='2'}">专票</c:if>
            <c:if test="${it.header.invoiceType=='3'}">普票</c:if>
            </td>
            <td width="8%">
            <c:if test="${sourcesType ==1}">
            	销售单号:
            </c:if>
			<c:if test="${sourcesType ==2}">
				应收发票:
			</c:if>                                 
             </td>
            <td width="42%"><b>${it.header.sourcesNo}</b></td>
          </tr>
          <tr>
            <td>购方税号:</td>
            <td>${it.header.billTaxNo }</td>
            <td>购方单位:</td>
            <td>${it.header.billCompany }</td>
          </tr>
          <tr>
            <td>银行账号:</td>
            <td>${it.header.billBankNo }</td>
            <td>地址电话:</td>
            <td>${it.header.billAddr }</td>
          </tr>
          <tr>
            <td>销方账号:</td>
            <td>${it.header.saleBankNo}</td>
            <td>销方地址:</td>
            <td>${it.header.saleAddr}</td>
          </tr>
          <tr>
            <td>销售方:</td>
            <td>${it.header.saleCompay }</td>
            <td>销方税号</td>
            <td>${it.header.saletaxno }</td>
          </tr>
          <tr>
            <td>税率:</td>
            <td>${it.header.tax}%</td>
            <td>销货清单:</td>
            <td>0</td>
          </tr>
          <tr>
            <td>开票人:</td>
            <td>${InfoInvoicer}</td>
            <td>复核人:</td>
            <td>${it.header.checker }</td>
          </tr>
          <tr>
            <td>收款人:</td>
            <td></td>
            <td>备注:</td>
            <td></td>
          </tr>
         </table>
         <table style="margin-top: 5px; width: 730px;" border="1" >
          <tr>
            <td width = "10% ">物品编码</td>
            <td width = "50% ">物品名称</td>
            <td width = "8%">规格</td>
            <td width = "8%">单位</td>
            <td width = "8% ">数量</td>
            <td width = "8%">价格</td>
            <td width = "8%">总额</td>
          </tr>
          <c:forEach items="${it.items}" var="item">
          <tr >
            <td>${item.materialId}</td>
            <td>${item.sku}</td>
            <td>${item.specifications}</td>
            <td>${item.unit}</td>
            <td>${item.num}</td>
            <td>${item.price}</td>
            <td>${item.amt}</td>
          </tr>
          </c:forEach>
        </table>
        <br/>
	</c:forEach> 
	</div>
	
</body>
</html>
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
    <title>&nbsp;</title>
    <style type="text/css" media="print">
        @page 
        {
            size:  auto;   /* auto is the initial value */
            margin: 0mm;  /* this affects the margin in the printer settings */
        }
        html
        {
            background-color: #FFFFFF; 
            margin: 0px;  /* this affects the margin on the html before sending to printer */
        }
        body
        {
            border: dashed 1px;
            margin: 10mm 15mm 10mm 15mm; /* margin you want for the content */
        }
        .print-hide{
        	display: none;
        }
    </style>
    <style type="text/css">
        html,
        body {
          overflow-x: hidden!important;
          font-family:"Microsoft YaHei";
          -webkit-font-smoothing: antialiased;
          min-height: 100%;
          background: #f9f9f9;
          font-size: 12px;
        }
        table{
        	border-collapse:collapse;
        }
        table th{
        	text-align: center;
            white-space: nowrap;
        }
        table td{
        	font-weight: normal;
        }
        .dashedTabel td, .dashedTabel th{
        	border-right: 1px dashed;
            border-bottom: 1px dashed;
        }
        .order-head td{
        	height: 24px;
        }
    </style>
</head>
<body style="padding: 12px;">
    <!--startprint-->
	<div>
        <div style="font-size: 18px;text-align: center;font-weight: bold;">
            ${order.customer.name } 物流配送单
        </div>
        <table class="order-head" style="border: 0; text-align: left;width: 100%;margin-top: 6px;padding: 2px;">
               <tr>
                   <td>订单日期：<fmt:formatDate value="${order.order.createTs}" pattern="yyyy-MM-dd"/></td>
                   <td>最晚送货日期：2013-02-13</td>
                   <td style="width: 1px; white-space: nowrap;">订单编码：${order.order.id }</td>
               </tr>
               <tr>
                   <td>收货方名称：${order.customer.name }</td>
                   <td>客户编码：${order.order.merchCustId }</td>
                   <td>收货方联系人：${order.customer.contactName }</td>
               </tr>
               <tr>
                   <td colspan="2">收货地址：${order.customer.address }</td>
                   <td>联系电话：${order.customer.tel }</td>
               </tr>
        </table>
        <table class="dashedTabel" style="border: 1px solid; text-align: left;width: 100%;margin-top: 10px;font-weight: bold;border-collapse: collapse; text-align: center;">
               <thead>
                   <tr>
                       <th colspan="5">产品基本信息</th>
                       <th colspan="5">送货信息</th>
                   </tr>
                   <tr style="border-bottom: 1px solid;">
                       <th>品牌</th>
                       <th>产品编码</th>
                       <th>产品名称</th>
                       <th>箱规</th>
                       <th>单位</th>
                       <th>送货箱数</th>
                       <th>送货包数</th>
                       <th>箱重（吨）</th>
                       <th>总重量（吨）</th>
                       <th>备注</th>
                   </tr>
               </thead>
               <tbody>
               	<c:set var="totalBox" value="0"/>
               	<c:set var="totalBag" value="0"/>
               	<c:set var="totalBoxWeight" value="0"/>
               	<c:set var="totalFullWeight" value="0"/>
               	<c:forEach items="${order.orderItems }" var="item">
               		<c:set var="totalBox" value="${item.orderSplits.num/item.materialBase.attribute6 + totalBox }"/>
               		<c:set var="totalBag" value="${item.orderSplits.num + totalBag }"/>
               		<c:set var="totalBoxWeight" value="${item.boxWeight + totalBoxWeight }"/>
               		<c:set var="totalFullWeight" value="${item.totalWeight + totalFullWeight }"/>
	                <tr>
	                    <td>${item.materialBase.brand }</td>
	                    <td>${item.materialBase.sapId }</td>
	                    <td>${item.materialBase.materialName }</td>
	                    <td>${item.materialBase.attribute6 }</td>
	                    <td>${item.materialBase.unit }</td>
	                    <td>${item.orderSplits.num/item.materialBase.attribute6 }</td>
	                    <td>${item.orderSplits.num}</td>
	                    <td>${item.boxWeight}</td>
	                    <td>${item.totalWeight}</td>
	                    <td></td>
	                </tr>
               	</c:forEach>
                    <tr>
                        <td>合计</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>${totalBox }</td>
                        <td>${totalBag }</td>
                        <td>${totalBoxWeight }</td>
                        <td>${totalFullWeight }</td>
                        <td></td>
                    </tr>
            </tbody>
        </table>
        <div style="border-bottom: 1px solid; margin-top: 12px; padding: 2px;">
            其他信息：
        </div>
        <div style="float: left; width: 40%; margin-top: 12px; padding: 2px;">
            物流商名称：
        </div>
        <div style=" margin-top: 12px; padding: 2px;">
            物流商编码：
        </div>
        <div style=" margin-top: 12px; padding: 2px;">
            收货人（签字盖章）：
        </div>
	</div>
    <!--endprint--> 
    <div class="print-hide" style="width: 100%; text-align: center;margin-top: 12px;">
        <input style="text-align: center; line-height: 30px; height: 38px; width: 80px;" id="btnPrint" type="button" value="打印" onclick=preview() />  
    </div>
    <script type="text/javascript">
    //局部打印
    function preview(){
        bdhtml=window.document.body.innerHTML;//获取当前页的html代码  
        sprnstr="<!--startprint-->";//设置打印开始区域  
        eprnstr="<!--endprint-->";//设置打印结束区域  
        prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+17); //从开始代码向后取html  
        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html  
        window.document.body.innerHTML=prnhtml;
        window.print();
        window.document.body.innerHTML=bdhtml;  
    }
    </script>  
      
</body>
</html>

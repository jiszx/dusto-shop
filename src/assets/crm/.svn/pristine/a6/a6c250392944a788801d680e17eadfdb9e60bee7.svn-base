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
<head>
    <base href="<%=basePath%>">
    <title>管理系统</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link rel="stylesheet" href="static/css/pub.css">
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>
        客户合同详情 <small> </small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#">客户管理</a></li>
        <li><a href="#">客户合同列表</a></li>
        <li class="active">客户合同详情</li>
    </ol>
</section>
<form class="col-md-12" style="min-height: 200px; padding: 10px 0;">
    <%-- block 1 --%>
    <div class="page-header" id="block-1">
        <h4 class="text-info">
            <strong>1.&nbsp;</strong>合同基本信息&nbsp;-&nbsp;<small>Basic
            Information.</small>
        </h4>
    </div>
    <div class="form-horizontal row" style="margin: 0 0 20px 0;">
        <table class="table table-bordered info-table" id="base">
            <tbody>
                <tr>
                    <th>客户名</th>
                    <td>${merch.name}</td>
                    <th>客户sap编码</th>
                    <td>${merch.sapCustomerId}</td>
                </tr>
                <tr>
                    <c:if test="${merch.custType !=2 }">
                        <th>经销区域</th>
                        <td>${contract.agentArea}</td>
                        <th>交货地点</th>
                        <td>${contract.deliveryAddress}</td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${merch.custType !=2 }">
                        <th>结算方式</th>
                        <td>
                            <c:forEach items="${dict['MERCH_SETTLE_TYPE']}" var="it">
                                <c:if test="${it.chooseVal == contract.settleType}">
                                    ${it.showText}
                                </c:if>
                            </c:forEach>
                        </td>
                        <th>账期</th>
                        <td>
                            <c:if test="${contract.settleType=='2'}">
                                <c:forEach items="${dict['MERCH_CONTRACT_ACCOUNT_PERIOD']}" var="it">
                                    <c:if test="${it.chooseVal == contract.aPeriod}">
                                        ${it.showText}
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </td>
                    </c:if>
                </tr>
                <tr>
                    <th>合同开始时间</th>
                    <td>${contract.contractBdate}</td>
                    <th>合同结束时间</th>
                    <td>${contract.contractEdate}</td>
                </tr>
                <tr>
                	<th>生意类型</th>
                    <td>
                        <c:forEach items="${dict['BUSINESS_MODEL']}" var="it">
                            ${it.chooseVal == contract.agentType?it.showText:""}
                        </c:forEach>
                    </td>
                     <th>客户等级</th>
                    	<td>
                        <c:forEach items="${dict['MERCH_PAPER_LEVEL']}" var="it">
                            <c:if test="${it.chooseVal == contract.merchLevels}">
                                ${it.showText}
                            </c:if>
                        </c:forEach>
                    	</td>
                 </tr>
                 <tr>
                    <th>年度进货目标</th>
                    <td>
                        ${contract.yearAmt}
                    </td>
                    <th>年度返利比例</th>
                    <td>
                        ${contract.rebate}
                    </td>
                </tr>
                <c:if test="${contract.attribute5 !=null}">
                	<tr>
                		<th>价格折扣比例%</th>
                		<td>${contract.attribute5}%</td>
                	</tr>
                </c:if>
                <c:if test=" ${contract.creditAmt !=null}">
                <tr>
                    <th>授信额度(元)</th>
                    <td>
                           ${contract.creditAmt/100}
                    </td>
                </tr>
                </c:if>
                <c:if test="${contract.virtualWarehouse !=null}">
                <tr>
                    <th>rdc虚拟仓</th>
                    <td>
                           ${contract.virtualWarehouse}
                    </td>
                </tr>
                </c:if>
            </tbody>
        </table>

        <c:if test="${contract.settleType=='2'}">
            <div class="form-group col-md-4 col-sm-6 input-box-list">
                <label for="input1" class=" font12 input-box-list-title">授信额度(元)：</label>
                <div class="input-box-list-value">
                    <input type="text" class="form-control"  value="${contract.creditAmt/100}"
                           name="creditAmt" readonly="readonly">
                </div>
            </div>
        </c:if>
        <input type="hidden" id="contractId" value="${contract.id}">
    </div>
    <%-- block 2 --%>
    <div class="page-header" id="block-2">
        <h4 class="text-info">
            <strong>2.&nbsp;</strong>代理信息&nbsp;-&nbsp; <small>Agent
            Type Information. </small>
        </h4>
    </div>
    <div class="row" style="margin: 0 0 20px 0;">
        <%-- 数据表格 --%>
        <table id="contract-table">
        </table>
    </div>
</form>

<%-- submit button --%>
<div class="text-center" style="padding-top: 30px; border-top: 1px solid rgba(0, 0, 0, .15);">
    <button class="btn btn-warning" id="btn-back" form="addPaperForm" style="padding: 8px 25px;"
            type="button">
        <i class="icon icon-save"></i>&nbsp;&nbsp;返回
    </button>
</div>
<script type="text/javascript">
    var custType = "${merch.custType }";
</script>
<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript" src="static/js/customer/contractDetails.js"></script>
</body>
</html>

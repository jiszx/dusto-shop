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
    <title>客户管理</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link rel="stylesheet" href="static/wizards/css/bwizard.min.css">
    <link rel="stylesheet" href="static/table/new/bootstrap-table.css">
    <link rel="stylesheet" href="static/table/new/bootstrap-editable.css">
    <link rel="stylesheet" href="static/css/pub.css">
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>
        物流商库存预警设置<small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#">客户管理</a></li>
        <li class="active">物流商库存预警设置</li>
    </ol>
</section>
    <%-- block 1 --%>
    <div class="page-header" id="block-1">
        <h4 class="text-info"><strong>1.&nbsp;</strong>物流商基本资料&nbsp;-&nbsp;
            <small>Basic Information.</small>
        </h4>
    </div>
    <div class="form-horizontal row" style="margin: 0 0 20px 0;">
    	<input type="hidden" value="${custBase.id }" id="custId">
        <table class="table table-bordered info-table"  id="base">
            <tbody>
                <tr>
                    <th>物流商名称</th>
                    <td>${custBase.name }</td>
                    <th>物流商简称</th>
                    <td>${custBase.abbrName }</td>
                </tr>
                <tr>
                    <th>物流商电话</th>
                    <td>${custBase.tel }</td>
                    <th>物流商联系人</th>
                    <td>${custBase.contactName }</td>
                </tr>
                <tr>
                    <th>详细地址</th>
                    <td>${custBase.address }</td>
                </tr>
               <%--  <tr>
                	<th>最小起订量</th>
                	<td>${custBase.minOrder }吨</td>
                </tr> --%>
            </tbody>
        </table>
    </div>
    <%-- block 2 --%>
    <div class="page-header" id="block-2">
        <h4 class="text-info"><strong>2.&nbsp;</strong>库存预警设置&nbsp;-&nbsp;
            <small>Warning Settings.</small>
        </h4>
    </div>
    <div class="row" style="margin: 0 0 20px 0;">
        <div class="col-md-12">
            <table id="setting-table" class="contract-box">
            </table>
        </div>
    </div>


<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript"
		src="static/table/new/bootstrap-table-editable.js"></script>
<script type="text/javascript"
	src="static/table/new/bootstrap-editable.js"></script>
<script type="text/javascript"
	src="static/js/customerInv/invWarningSetting.js"></script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<style type="text/css">
			table{
			border:1px solid #ccc}
			table tr{
			border:1px solid #ccc;}
			table tr td{
			border:1px solid #ccc;
			line-height:30px;
			font-family: "Microsoft YaHei";
			}
			table tr th{
			border:1px solid #ccc;
			}
			.td-center{
			text-align:center;
			}
			.td-left{
			padding:0 10px;
			}
		</style>
	</head>
	<body>
		<table id="workdetail" cellspacing="0" cellpadding="1" border="0" width="100%">
			<tbody>
				<tr>
					<td class="td-center">销售组织</td>
					<td class="td-left">${vo.orgname} </td>
					<td class="td-center">所属大区</td>
					<td class="td-left">${vo.reginname}</td>
				</tr>
				<tr>
					<td class="td-center">调整类型</td>
					<td class="td-left" id="adjustType">${vo.type}</td>
					<td class="td-center">调整金额</td>
					<td class="td-left">${vo.adjustAmt}</td>
				</tr>
				<tr>
					<td class="td-center">调整时间 </td>
					<td class="td-left"><fmt:formatDate value="${vo.createTs}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td class="td-center" >费用类型</td>
					<td class="td-left" id="costType">${vo.costTypeid}</td>
				</tr>
				<tr>
					<td class="td-center" >调整原因</td>
					<td class="td-left" id="reason">原因1</td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table> 
		<script src="../../static/js/jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				$("#adjustType").html(getAdjustType("${vo.type}"));
				
				$("#costType").html(getCostType("${vo.costTypeid}"));
			});
			function getCostType(type) {
				var data = new Object();
				<c:forEach items="${dict['COST_TYPE']}" var="payType">
				data["${payType.chooseVal}"] = "${payType.showText}"
				</c:forEach>
				if (data[type]) {
					return data[type];
				} else {
					return "未知";
				}
			}
			function getAdjustType(type) {
				var data = new Object();
				<c:forEach items="${dict['CRM_COST_ADJUST_TYPE']}" var="payType">
				data["${payType.chooseVal}"] = "${payType.showText}"
				</c:forEach>
				if (data[type]) {
					return data[type];
				} else {
					return "未知";
				}
			}
		</script>
		
		<%-- 销售组织${vo.organizationId} <br>
	大区 ${vo.organizationId} <br>
	费用类型 ${vo.costTypeid} <br>
	调整类型 ${vo.type} <br>
	调整金额 ${vo.adjustAmt} <br>
	调整时间 ${vo.createTs} <br>
	调整人 ${vo.createOid} <br>
	调整原因 ${vo.reason} <br> --%>
	</body>
</html>
	 
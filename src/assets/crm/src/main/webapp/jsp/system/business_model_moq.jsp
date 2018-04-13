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
    <title>管理系统-业务模式最小起订量</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>订单起订量设置
        <small>订单起订量设置</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-dashboard"></i> 首页</a></li>
        <li><a href="#"> 系统管理</a></li>
        <li class="active">订单起订量设置</li>
    </ol>
</section>
<div class="row">
	<form class="form-inline" id="saveForm" method="post">
		<table class="table table-bordered table-striped table-hover"">
			<thead>
				<tr>
					<th>业务模式</th>
					<th>最小起订量</th>
					<th>浮动幅度</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${dict['BUSINESS_MODEL']}" var="it" varStatus="idx">
					<c:set var="modelId" value="${it.chooseVal}"></c:set>
					<c:set var="id" value=""></c:set>
					<c:set var="minOrderQuantity" value=""></c:set>
					<c:set var="range" value=""></c:set>
					<c:forEach items="${moqs }" var="moq">
						<c:if test="${moq.modelId eq it.chooseVal}">
							<c:set var="id" value="${moq.id}"></c:set>
							<c:set var="minOrderQuantity" value="${moq.minOrderQuantity}"></c:set>
							<c:set var="range" value="${moq.range}"></c:set>
						</c:if>
					</c:forEach>
					<tr>
						<td><label>${it.showText}</label>
							<input type="hidden" name="moqs[${idx.index }].modelId" value="${it.chooseVal}">
							<input type="hidden" name="moqs[${idx.index }].id" value="${id }">
						</td>
						<td>
							<div class="input-group">
								<input type="text" class="form-control number" id="minOrderQuantity${idx.index }" min="0" name="moqs[${idx.index }].minOrderQuantity" value="${minOrderQuantity }" placeholder="请输入最小起订量">
					            <span class="input-group-addon">吨</span>
					        </div>
						</td>
						<td>
							<div class="input-group">
								<input type="text" class="form-control number" id="range${idx.index }" min="0" name="moqs[${idx.index }].range" value="${range }" placeholder="请输入浮动幅度">
					            <span class="input-group-addon">吨</span>
					        </div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	<div class="text-center" style="padding-top: 30px;border-top:1px solid rgba(0,0,0,.15);">
        <button class="btn btn-warning" style="padding: 8px 25px;" type="button" id="saveBtn"><i class="icon icon-save"></i>&nbsp;&nbsp;保存
        </button>
    </div>
</div>

<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript">
    $(document).ready(function () {
    	//保存按钮
        $("#saveBtn").click(function(){
        	if(validate("#saveForm").form()){
    	    	$("#saveForm").attr("action","config/moq/save").submit();
        	}
        })
    });
    function validate(formId){
    	var validator = $(formId).validate({
	        showErrors: function(errorMap, errorList) {
	        	if(errorList.length > 0){
	        		var errorEle = errorList[0].element;
	        		$(errorEle).parent().tooltip({title:errorList[0].message,placement:'right'}).tooltip('toggle');
	        	}
	        	this.defaultShowErrors();
			},
			highlight: function(element) {   // <-- fires when element has error
			    $(element).parent().removeClass('has-success').addClass('has-error');
			},
			unhighlight: function(element) { // <-- fires when element is valid
			    $(element).parent().removeClass('has-error').addClass('has-success');
			    $(element).parent().tooltip('destroy');
			},
	        ignore: ""
    	})
    	return validator;
    }
</script>
</body>
</html>

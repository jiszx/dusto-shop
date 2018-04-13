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
    <title>管理系统</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/chosen/bootstrap-chosen.css" rel="stylesheet">
    <style>
        body {
            font-family: "微软雅黑";
        }
        ul,li{
            list-style: none;
        }
        select {
            -webkit-appearance: none;
            -webkit-border-radius: 0;
        }
        .nav.nav-pills > li{
            padding: 5px 0;
        }

        .nav.nav-pills > li > a{
            padding: 5px 15px;
            background: rgba(0,0,0,.05);
            border:none;
            transition: 0.2s;
            font-size: 13px;

        }
        .nav.nav-pills > li.active > a,.nav.nav-pills > li.active > a:hover{
            color: #fff;
            background: #3c8dbc;
            font-weight: normal;
        }
        .nav.nav-pills > li > a:hover{
            background: hsla(202,68%,74%,.35);
            font-weight: normal;
        }
        .font12{
            font-size: 12px;
        }
        .form-horizontal .input-box-list{
            display: table;
            margin:10px 0 0 0;
            padding-left: 10px;
        }
        .input-box-list-value textarea{
            resize: none;
        }
        input::-webkit-input-placeholder,textarea::-webkit-input-placeholder{
            font-size: 12px;
        }
        .btn-long{
            padding:8px 6.18% ;
        }
        .block-save-link{
            float: right;
            font-size: 13px;
            padding:4px 10px 0;
        }
        .block-save-link:hover{
            text-decoration: underline;
        }
        .input-box-list-title, .input-box-list-value{
            display: table-cell;
        }
        .input-box-list-title{
            width: 100px;
            text-align: right;
            vertical-align: middle;
            padding-right: 5px;
        }
        .input-box-list-value textarea{
            resize: none;
        }
        input::-webkit-input-placeholder,textarea::-webkit-input-placeholder{
            font-size: 12px;
        }
        /* this page */
        .user-select-panel{
            border: 1px solid hsla(0,0%,90%,1);
            background-color: #fff;
            padding: 0 10px;
        }
        .user-select-list{
            height: 146px;
            overflow-y: auto;

        }
        .user-select-panel li{
            border-bottom: 1px solid hsla(0,0%,95%,1);
            /*padding:5px 0 ;*/
        }
        .user-select-panel li .radio{
            padding-left: 10px;
        }
        .factory-select-box .dropdown{
            margin-bottom: 10px;
        }
        .factory-select-box .dropdown a.btn{
            padding-top:10px;
            padding-bottom:10px;
            background-color: #fff;
            border-radius: 0;
        }
        .factory-select-box .dropdown a.btn span.caret{
            margin-top:8px;
        }
        .factory-select-box .dropdown ul{
            width: 100%;;
        }
        .account-panel{
            border: 1px solid hsla(0,0%,90%,1);
            border-top: 2px solid #a94442;
            padding: 20px 20px 10px;
            background-color: #fff;
        }
        .account-panel>div>p{
            margin:0;
        }
        .account-item{
            margin-bottom: 15px;
        }
        .account-item>span{
            margin:0 5px;
        }
        .account-item:last-child{
            border-top: 1px dashed hsla(0,0%,90%,1);
            padding-top: 20px;
        }
        .order-accordion-item{
            border-radius: 0;
        }
        .order-accordion-item div.panel{
            border-radius: 0;
        }
        .order-accordion-item div.panel-heading{
            border-radius: 0;
            background-color: #fff;
            transition: 0.2s;
        }
        .order-accordion-item div.panel-heading:hover{
            background-color:hsla(202,68%,74%,.15);
        }
        .order-item-title-block{
            padding-right: 30px;
            margin-right: 20px;
            border-right: 1px solid hsla(0,0%,95%,1);
            color: #333;
        }
        .order-plus-box{
            margin-bottom: 0;

        }
        .order-plus-box thead{
            background-color: hsla(255,0%,95%,1);

        }
        .order-plus-box td,.order-plus-box th{
            font-size: 12px;
            text-align: center;

        }

        .order-plus-box-add{
            padding: 10px;
            text-align: center;
            width: 100%;
            font-size: 16px;
            background-color: #fff;
            color: hsla(0,0%,65%,1);
            border:1px dashed hsla(0,0%,90%,1);
            cursor: pointer;
            transition: 0.2s;
        }
        .order-plus-box-add:hover{
            color: hsla(0,0%,35%,1);
            border-color: hsla(0,0%,80%,1);
        }
        .add-bstb-box {
            /*min-height: 100px;*/
            background-color: #fff;
            /*border-bottom:1px solid hsla(255,0%,90%,1);*/
            /*padding: 10px  0;*/
            height: 0;
            opacity: 0;
            overflow: hidden;
            transition: 0.2s;
            clear: both;
        }

        .add-bstb-box-open {
            border-bottom: 1px solid hsla(255, 0%, 90%, 1);
            padding: 10px 0;
            opacity: 1;
            height: auto;
        }
        .order-item{
        	float:left;
        	line-height:30px;
        	margin-right:30px;
        }
    </style>
</head>
<body class="container-fluid content">

<section class="content-header">
    <h1>订单管理
        <small>订单详情</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li class="active">订单详情</li>
    </ol>
</section>

    <div class="page-header">
        <h5>客户信息&nbsp;-&nbsp;
            <!-- <a href="javascript:;" class=" text-info block-save-link"  data-toggle="modal" data-target="#addOrderModal"><i class="icon icon-plus"></i>&nbsp;&nbsp;新增项目</a> -->
        </h5>
    </div>
    <div class="row">
    	<input type="hidden" id="orderid" value="${id }">
        <div class="form-group col-md-5 col-sm-6 input-box-list">
            <p class="text-muted order-item">客户名称:</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="${custname }" disabled style="border-radius:5px !important;width:60% !important;">
            </div>
        </div>
        <div class="col-md-5 col-sm-6 factory-select-box">
            <p class="text-muted order-item">送达方名称:</p>
            <div class="form-group" style="width:100%;">
	            <input type="text" class="form-control" value="${dist.name }" disabled style="border-radius:5px !important;width:60% !important;">
            </div>
        </div>
        
    </div>
   <%--  <div class="row customAccount">
		<div class="col-md-5 col-sm-6">
			<p class="text-muted">物流信息:</p>
			<div class="account-panel">
				<p class="account-item">
					<span class="account-title">&nbsp;客户名:</span> <span>${dist.name }</span>
				</p>
				<p class="account-item">
					<span class="account-title">物流公司:</span><span>${dist.logistics }</span>
				</p>
				<p class="account-item">
					<span class="account-title">到站地址:</span> <span>${dist.site }</span>
				</p>
				<p class="account-item" >
					<span class="account-title">&nbsp;收货人:</span><span>${dist.contacter }</span>
				</p>
				<p class="account-item">
					<span class="account-title">联系电话:</span><span>${dist.mobile }</span>
				</p>
				<p class="account-item">
					<span class="account-title">收货地址:</span> <span>${dist.address }</span>
				</p>
			</div>
		</div>
	</div> --%>
    <%-- 订单详情 --%>
    <div class="page-header">
        <h5>订单项目详情&nbsp;-&nbsp;
            <!-- <a href="javascript:;" class=" text-info block-save-link"  data-toggle="modal" data-target="#addOrderModal"><i class="icon icon-plus"></i>&nbsp;&nbsp;新增项目</a> -->
        </h5>
    </div>
    <%-- 订单项目列表 --%>
    <div class="panel-group order-accordion-item" role="tablist">
        <%-- 数据表格 --%>
        <!-- <table id="order-table" >
        </table> -->
        <form action="" method="post" id="saveForm">
        <table class="table table-condensed table-bordered">
            <tr  class="active">
                <th>类型</th>
                <th>产品名称</th>
                <th>物料编码</th>
                <th>单位</th>
                <th>单价(元)</th>
                <th>下单数量</th>
                <th>金额</th>
                <th>已发货数量</th>
                <c:if test="${orderType ==4 }">
                	<th>发货工厂</th>
                </c:if>
            </tr>
            <c:set var="total" value="0"></c:set>
            <c:set var="delivery" value="0"></c:set>
            <c:forEach items="${lines }" var="line" varStatus="lindex">
            	<tr>
	                <td>${line.type }</td>
	                <td>${line.sku }</td>
	                <td>${line.materialId }</td>
	                <td>${line.unit }</td>
	                <td>${empty line.amounts ?'':line.orderPrice*line.amounts }</td>
	                <c:choose>
	                	<c:when test="${lindex.index == lines.size()-1}">
	                		<td>${total }</td>
	                	</c:when>
	                	<c:otherwise>
			                <td>${empty line.amounts?'':line.num/line.amounts }</td>
			                <c:set var="total" value="${empty line.amounts?total+0:total+line.num/line.amounts }"></c:set>
	                	</c:otherwise>
	                </c:choose>
	                <td>${line.amt }</td>
	                <c:choose>
	                	<c:when test="${lindex.index == lines.size()-1}">
	                		<td>${delivery }</td>
	                	</c:when>
	                	<c:otherwise>
			                <td>${empty line.amounts?'':line.deliverynum/line.amounts }</td>
			                <c:set var="delivery" value="${empty line.amounts?delivery+0:delivery+line.deliverynum/line.amounts }"></c:set>
	                	</c:otherwise>
	                </c:choose>
	                <c:choose>
	                	<c:when test="${orderType ==4 and line.id!=0 and line.id!=null }">
		                	<td><select name="attribute2" class="form-control no-appearance sele" readonly title="特殊订单已禁止选择1100以外的工厂">
		                			<c:forEach items="${factory }" var="fa">
		                			<%-- <option value="${ fa.id}" ${fa.id eq currentFactory? 'selected':''}>${ fa.name}</option> --%>
		                			<option value="${ fa.id}" ${fa.id eq '1100'? 'selected':'disabled title="特殊订单已禁止选择1100以外的工厂"'}>${ fa.name}</option>
		                			</c:forEach>
		                		</select></td>
	                	</c:when>
	                	<c:otherwise>
	                	</c:otherwise>
	                </c:choose>
	            	<input class="sele" name="spliteid" value="${line.spliteid }" type="hidden" />
	            	<input class="sele" name="type" value="${line.type }" type="hidden" />
				
				</tr>
            </c:forEach>
        </table>
        <input id="taskId" value="${taskId }" type="hidden">
        
    </form>
    <div class="remark col-md-offset-1 col-md-11">
			<albel>备注:</albel>${remark }
	</div>
    <c:if test="${orderType ==4 }">
    	<div class="form-group text-center">
            <button class="btn btn-primary" id="saveBtn">确认工厂并发送SAP</button>
            <button class="btn btn-primary" id="rollBack">驳回订单</button>
        </div>
    </div>
    </c:if>
    
    <%-- submit button --%>
    <!-- <div class="text-center" style="padding-top: 100px;border-top:1px solid rgba(0,0,0,.15); margin-top: 40px;">
        <button class="btn btn-warning" style="padding: 8px 25px;"  type="button" id="btn-back"><i class="icon icon-save"></i>&nbsp;&nbsp;返回</button>
    </div> -->

<!-- Modal -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="static/table/new/bootstrap-table.js"></script>
<script type="text/javascript" src="static/table/new/bootstrap-table-export.js"></script>
<script type="text/javascript" src="static/table/new/bootstrap-table-editable.js"></script>
<script type="text/javascript" src="static/table/new/bootstrap-editable.js"></script>
<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript">
$(function(){

	//保存
	$("#saveBtn").click(function(){
		var lines = renameLogistics();
		var stt = JSON.stringify(lines.slice(0, -1));
		var taskId = $("#taskId").val();
		$.post("order/editFactory", {"orders":stt,"taskId":taskId}, function(res){
			if(res && res.data && res.data>0){
				$.messager.popup("工厂信息保存成功");
				window.history.back();
			}else{
				$.messager.popup("未做修改");
			}
		});
	});
	
	$("#rollBack").click(function(){
		var lines = renameLogistics();
		var stt = JSON.stringify(lines.slice(0, -1));
		var taskId = $("#taskId").val();
		$.post("order/backProcess", {"orders":stt,"taskId":taskId}, function(res){
			if(res && res.data && res.data>0){
				$.messager.popup("驳回成功");
				window.history.back();
			}else{
				$.messager.popup("驳回失败");
			}
		});
	})
	
});


function renameLogistics(){
	var orders = new Array();
	$('form tr:not(:eq(0))').each(function(index,dom){
		var order = {};
		$(dom).find(".sele").each(function(i,val){
			var oname = $(val).attr("name");
			order[oname] = $(val).val();
		});
		orders.push(order);
	});
	return orders;
}

    function getverification(value){
    	var data = new Object();
		<c:forEach items="${dict['POLICY_TYPE_VERIFICATION']}" var="type">
		data["${type.chooseVal}"] = "${type.showText}"
		</c:forEach>
		if (data[value]) {
			return data[value];
		} else {
			return "未知";
		}
    }
</script>
</body>
</html>

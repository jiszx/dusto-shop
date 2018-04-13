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
    <style type="text/css">
    	.panel-info {
    border-color: transparent;
}
.panel {
    margin-bottom: 20px;
    background-color: transparent;
    border: nonet;
    border-radius: nnone;
    -webkit-box-shadow:none;
    box-shadow: none;
}
    </style>
</head>
<body class="container-fluid content">
        <section class="content-header">
            <h1>总览
                <small>待办事宜</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
                <li class="active">总览</li>
            </ol>
        </section>
        <div class="col-md-6">
        	<div  ${user.isSalesman !="1"?"style='display:none;'":"style='display:block;'"}>
            <div class="col-md-3 col-sm-3" style="padding-left:0;padding-right:20px;">
                <!-- small box -->
                <div class="small-box bg-aqua">
                    <div class="inner" >
                        <h3 id="auditOrderNum">
                            0
                        </h3>
                        <p>
                            <small>待审批的订单</small>
                        </p>
                    </div>
                    <div class="icon">
                        <i class="ion ion-bag"></i>
                    </div>
                    <a href="order/index.html" class="small-box-footer">
                        详情 <i class="fa fa-arrow-circle-right"></i>
                    </a>
                </div>
            </div><!-- ./col -->

            <div class="col-md-3 col-sm-3" style="padding-left:0;padding-right:20px;">
                <!-- small box -->
                <div class="small-box bg-green">
                    <div class="inner">
                        <h3>
                            0<sup style="font-size: 20px">%</sup>
                        </h3>
                        <p><small>
                            目标完成率</small>
                        </p>
                    </div>
                    <div class="icon">
                        <i class="ion ion-stats-bars"></i>
                    </div>
                    <a href="#" class="small-box-footer">
                        详情 <i class="fa fa-arrow-circle-right"></i>
                    </a>
                </div>
            </div><!-- ./col -->
            <div class="col-md-3 col-sm-3" style="padding-left:0;padding-right:20px;">
                <!-- small box -->
                <div class="small-box bg-yellow">
                    <div class="inner">
                        <h3 id="custnum">
                            0
                        </h3>
                        <p>
                            <small>客户数</small>
                        </p>
                    </div>
                    <div class="icon">
                        <i class="ion ion-person-add"></i>
                    </div>
                    <a href="customer/list.html" class="small-box-footer">
                        详情 <i class="fa fa-arrow-circle-right"></i>
                    </a>
                </div>
            </div><!-- ./col -->
            </div>
            <div class="col-md-3 col-sm-3" style="margin-left:-15px;padding-right:0;">
                <!-- small box -->
                <div class="small-box bg-red">
                    <div class="inner">
                        <h3 id="mytaskNum">0</h3>
                        <p><small>待办事件</small></p>
                    </div>
                    <div class="icon">
                        <i class="ion ion-pie-graph"></i>
                    </div>
                    <a href="process/forMe.html" class="small-box-footer">
                        详情 <i class="fa fa-arrow-circle-right"></i>
                    </a>
                </div>
            </div><!-- ./col -->
            <div class="panel panel-info col-sm-12" style="padding:0;">
                <div class="panel-heading row" style="margin-left:0;margin-right:15px;">
                    <div class="col-sm-6" style="padding:0;"><h4 style="margin:0;">公告信息</h4></div><div class="col-sm-6" style="text-align:right;"><span><a href="system/notes/showall.html">更多</a></span></div>
                </div>
                <div class="panel-body">
                    <ol class="nav nav-pills nav-stacked">
                        <c:forEach items="${notes}" var="note">
                            <li>
                                <a target="_blank" href="system/notes/noteDetail/${note.id}.html">${note.title} <small>${note.releaseTs}</small>
                                    <c:if test="${note.topFlag == 1}">
                                        <span class="badge"><i class="icon icon-fire"></i></span>
                                    </c:if>
                                </a>
                            </li>
                        </c:forEach>
                    </ol>
                </div>
            </div>
        </div>
        <div class="col-md-6 hidden-sm">
            <ul class="timeline">
            
            	
            	<c:forEach items="${lines}" var="line" >
	            	<%-- <li class="time-label">
	                    <span class="bg-purple">${line.createTs}</span>
	                </li> --%>
	                <li>
                    <!-- timeline icon -->
                    <i class="${line.categoryIcon} bg-danger"></i>
                    <div class="timeline-item">
                        <span class="time"><i class="${line.categoryIcon}"></i> ${line.createTs} ${line.timeStr}</span>
                        <h3 class="timeline-header"><a href="process/myprocess.html">${line.category} -${line.title}</a></h3>
                        <div class="timeline-body">${line.contentDesc}</div>
                        <!-- <div class='timeline-footer'>
                            <a class="btn btn-primary btn-xs">查看</a>
                        </div> -->
                    </div> 
                </li>
            	</c:forEach>
                <%-- <!-- timeline time label -->
                <li class="time-label">
                    <span class="bg-purple">2016-06-12</span>
                </li>
                <!-- /.timeline-label -->
                <!-- timeline item -->
                <li>
                    <!-- timeline icon -->
                    <i class="icon icon-bullhorn bg-green"></i>
                    <div class="timeline-item">
                        <span class="time"><i class="icon icon-clock-o"></i> 12:05</span>
                        <h3 class="timeline-header"><a href="#">费用调整 -财务处理完成</a></h3>
                        <div class="timeline-body">
                            处理意见：叮叮当当<br>
                            处理方式：通过/驳回
                        </div>
                        <div class='timeline-footer'>
                            <a class="btn btn-primary btn-xs">查看详情页面</a>
                        </div>
                    </div>
                </li>

                <li>
                    <!-- timeline icon -->
                    <i class="icon icon-shopping-cart bg-light-blue"></i>
                    <div class="timeline-item ">
                        <span class="time"><i class="icon icon-clock-o"></i> 12:05</span>
                        <h3 class="timeline-header no-border"><a href="#">订单信息</a> 下达了订单ISO032392302302323,销售金额￥100202.20,订单审批中</h3>
                    </div>
                </li>
                <li>
                    <!-- timeline icon -->
                    <i class="icon icon-eye-open bg-danger"></i>
                    <div class="timeline-item">
                        <span class="time"><i class="icon icon-clock-o"></i> 12:05</span>
                        <h3 class="timeline-header"><a href="#">费用调整 - 销售内务审批</a></h3>
                        <div class="timeline-body">
                            审批意见：埃里克森的机房
                            审批通过
                            成都益盐堂-陈少华大区-川渝大区 费用调整￥100.00元
                        </div>
                    </div>
                </li>



                <li class="time-label">
                    <span class="bg-purple">2016-06-13</span>
                </li>
                <!-- /.timeline-label -->
                <!-- timeline item -->
                <li>
                    <!-- timeline icon -->
                    <i class="icon icon-shopping-cart bg-light-blue"></i>
                    <div class="timeline-item ">
                        <span class="time"><i class="icon icon-clock-o"></i> 12:05</span>
                        <h3 class="timeline-header no-border"><a href="#">订单信息</a> 下达了订单ISO032392302302323,销售金额￥100202.20,订单审批中</h3>
                    </div>
                </li> --%>

            </ul>

        </div>
<!-- Modal -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript">
var custnumUrl = "custNum"
	$.get(custnumUrl, function(data) {
		if (data.data == 0) {
			$("#custnum").html('0');
		} else {
			$("#custnum").html(data.data);
		}
	})

	var auditOrderUrl = "auditOrder"
	$.get(auditOrderUrl, function(data) {
		if (data.data == 0) {
			$("#auditOrderNum").html('0');
		} else {
			$("#auditOrderNum").html(data.data);
		}
	})
	var mytaskNumUrl = "mytaskNum"
	$.get(mytaskNumUrl, function(data) {
		if (data.data == 0) {
			$("#mytaskNum").html('0');
		} else {
			$("#mytaskNum").html(data.data);
		}
	})
	var userOrgInfoUrl = "userorg"
	$.get(userOrgInfoUrl, function(data) {
		if (data.data) {
			$("#userOrgInfo").text(data.data);
		} else {
			$("#userOrgInfo").text("");
		}
	})
</script>
</body>
</html>

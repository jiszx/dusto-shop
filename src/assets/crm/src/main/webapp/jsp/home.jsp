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
    <title>${applicationScope.PROJECT_NAME}</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body class="skin-blue">
<jsp:include page="/common/header.jsp"></jsp:include>
<div class="wrapper row-offcanvas row-offcanvas-left">
    <jsp:include page="/common/leftMenu.jsp"></jsp:include>
    <!-- Right side column. Contains the navbar and content of the page -->
    <aside class="right-side">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>总览
                <small>待办事宜</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
                <li class="active">总览</li>
            </ol>
        </section>
        <!-- Main content -->

        <section class="content">
            <div class="row">
                <div class="col-md-6">
                    <div class="col-lg-3 col-xs-6">
                        <!-- small box -->
                        <div class="small-box bg-aqua">
                            <div class="inner">
                                <h3>
                                    150
                                </h3>
                                <p>
                                    待审批的订单
                                </p>
                            </div>
                            <div class="icon">
                                <i class="ion ion-bag"></i>
                            </div>
                            <a href="#" class="small-box-footer">
                                详情 <i class="fa fa-arrow-circle-right"></i>
                            </a>
                        </div>
                    </div><!-- ./col -->

                    <div class="col-lg-3 col-xs-6">
                        <!-- small box -->
                        <div class="small-box bg-green">
                            <div class="inner">
                                <h3>
                                    56<sup style="font-size: 20px">%</sup>
                                </h3>
                                <p>
                                    销售目标完成率
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
                    <div class="col-lg-3 col-xs-6">
                        <!-- small box -->
                        <div class="small-box bg-yellow">
                            <div class="inner">
                                <h3>
                                    44
                                </h3>
                                <p>
                                    客户数
                                </p>
                            </div>
                            <div class="icon">
                                <i class="ion ion-person-add"></i>
                            </div>
                            <a href="#" class="small-box-footer">
                                详情 <i class="fa fa-arrow-circle-right"></i>
                            </a>
                        </div>
                    </div><!-- ./col -->
                    <div class="col-lg-3 col-xs-6">
                        <!-- small box -->
                        <div class="small-box bg-red">
                            <div class="inner">
                                <h3>13</h3>
                                <p>待办事件</p>
                            </div>
                            <div class="icon">
                                <i class="ion ion-pie-graph"></i>
                            </div>
                            <a href="#" class="small-box-footer">
                                详情 <i class="fa fa-arrow-circle-right"></i>
                            </a>
                        </div>
                    </div><!-- ./col -->
                    <div class="panel panel-info col-sm-12">
                        <div class="panel-heading">
                            <h3 class="panel-title">公告信息</h3>
                        </div>
                        <div class="panel-body">
                            <ol class="nav nav-pills nav-stacked">
                                <li><a href="#">这是一条公告信息</a></li>
                                <li><a href="#">这是一条公告信息</a></li>
                                <li><a href="#">这是一条公告信息</a></li>
                                <li><a href="#">这是一条公告信息</a></li>
                                <li><a href="#">这是一条公告信息</a></li>
                                <li><a href="#">这是一条公告信息</a></li>
                                <li><a href="#">这是一条公告信息</a></li>
                                <li><a href="#">这是一条公告信息</a></li>
                                <li><a href="#">这是一条公告信息</a></li>
                            </ol>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <ul class="timeline">
                        <!-- timeline time label -->
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
                                <h3 class="timeline-header"><a href="#">公告标题</a></h3>
                                <div class="timeline-body">
                                    内容概要提示
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
                                <h3 class="timeline-header"><a href="#">OA流程信息提示</a></h3>
                                <div class="timeline-body">
                                    您的订单ISO93230203239023已通过审批
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
                        </li>

                    </ul>

                </div>
            </div>
            <div class="row">


            </div>
        </section><!-- /.content -->

    </aside><!-- /.right-side -->
</div><!-- ./wrapper -->
<!-- Modal -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
</body>
</html>

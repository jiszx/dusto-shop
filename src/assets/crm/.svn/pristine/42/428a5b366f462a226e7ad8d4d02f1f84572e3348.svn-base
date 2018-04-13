<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>管理系统-实时信息图</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
</head>
<body class="container-fluid">
<section class="content-header">
    <h1>
        实时信息图
        <small>实时信息图</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 动态数据</a></li>
        <li class="active">实时信息图</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">


    <div class="row">

        <!--
        <div class="col-md-3">
            <a href="chart/heatMap/index.html?type=2" class="thumbnail text-center" target="_blank">
                <img src="static/images/type1.png">
                <label>RDC覆盖图</label>
            </a>
        </div>
        -->
        <div class="col-md-3">
            <a href="chart/heatMap/index.html" class="thumbnail text-center" target="_blank">
                <img src="static/images/customer.png">
                <label>客户分布图</label>
            </a>
        </div>
        <div class="col-md-3">
            <a href="chart/heatMap/index.html?type=3" class="thumbnail text-center"
               class="thumbnail text-center" target="_blank">
                <img src="static/images/order_amt.png">
                <label>交易量(万）</label>
            </a>
        </div>

        <div class="col-md-3">
            <a href="chart/heatMap/index.html?type=3" class="thumbnail text-center"
               class="thumbnail text-center" target="_blank">
                <img src="static/images/type3.png">
                <label>交易量(吨）</label>
            </a>
        </div>

        <div class="col-md-3">
            <a href="chart/heatMap/index.html?type=5" class="thumbnail text-center" target="_blank">
                <img src="static/images/type5.png">
                <label>在途调拨单(发运途中）</label>
            </a>
        </div>
    </div>
    <div class="row">

        <div class="col-md-3">
            <a href="chart/heatMap/index.html?type=5" class="thumbnail text-center" target="_blank">
                <img src="static/images/type5.png">
                <label>在途订单</label>
            </a>
        </div>

    </div>
    <!--
    <div class="row">
        <div class="col-md-3">
            <a href="chart/heatMap/index.html?type=4" class="thumbnail text-center"
               class="thumbnail text-center"
               target="_blank">
                <img src="static/images/type4.png">
                <label>运营覆盖</label>
            </a>
        </div>
        <div class="col-md-3">
            <a href="chart/heatMap/index.html?type=3" class="thumbnail text-center"
               class="thumbnail text-center" target="_blank">
                <img src="static/images/type3.png">
                <label>交易量(金额）</label>
            </a>
        </div>
        <div class="col-md-3">
            <a href="chart/heatMap/index.html?type=5" class="thumbnail text-center" target="_blank">
                <img src="static/images/type5.png">
                <label>订单图(发运途中）</label>
            </a>
        </div>

    </div>
    -->
</section>
<!-- /.content -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript">
    $(function () {
        $(".btn-f11").click(function (event) {
            window.open($(this).attr("data-href"), 'big', 'fullscreen=yes')
        });
    })
</script>
</body>
</html>

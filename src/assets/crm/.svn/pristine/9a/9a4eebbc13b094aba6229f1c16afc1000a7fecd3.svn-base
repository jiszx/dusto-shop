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
<body class="container-fluid bg-navy" style="padding: 20px 10px;">
<!-- Main content -->
<div class="col-md-4">

    <div class="box box-solid bg-blue">
        <div class="box-header">
            <h3 class="box-title">本周销售</h3>
        </div>
        <div class="box-body" style="padding: 0px;">
            <div style="width: 24%;display: inline-block;box-sizing: border-box;padding: 2px;">
                <div class="small-box bg-aqua">
                    <h4 class="small-box-footer">订单量</h4>
                    <div class="inner text-center">
                        <strong>150 单</strong>
                    </div>
                </div>
            </div>

            <div style="width: 24%;display: inline-block;box-sizing: border-box;padding: 2px;">
                <div class="small-box bg-aqua">
                    <h4 class="small-box-footer">订单量</h4>
                    <div class="inner text-center">
                        <strong>150 吨</strong>
                    </div>
                </div>
            </div>
            <div style="width: 24%;display: inline-block;box-sizing: border-box;padding: 2px;">
                <div class="small-box bg-aqua">
                    <h4 class="small-box-footer">金额</h4>
                    <div class="inner text-center">
                        <strong>150 万元</strong>
                    </div>
                </div>
            </div>

            <div style="width: 24%;display: inline-block;box-sizing: border-box;padding: 2px;">
                <div class="small-box bg-aqua">
                    <h4 class="small-box-footer">计提费</h4>
                    <div class="inner text-center">
                        <strong>150 元</strong>
                    </div>
                </div>
            </div>
        </div><!-- /.box-body -->
    </div>

    <div class="box box-solid bg-light-blue">
        <div class="box-header">
            <h3 class="box-title">业务模式数据图</h3>
        </div>
        <div class="box-body">
            <div id="container"></div>
        </div><!-- /.box-body -->
    </div>
</div>
<div class="col-md-4">
    <div class="box box-solid bg-light-blue">
        <div class="box-header">
            <h3 class="box-title text-center" style="width: 100%;">调味品事业部CRM数据总览</h3>
        </div>
        <div class="box-body" style="padding: 0px;">
        </div>
    </div>

    <div class="box box-solid bg-light-blue">
        <div class="box-header">
            <h3 class="box-title text-center" style="width: 100%;">调味品事业部CRM数据总览</h3>
        </div>
        <div class="box-body" style="padding: 0px;">
        </div>
    </div>
</div>
<div class="col-md-4">
    <div class="box box-solid bg-light-blue">
        <div class="box-header">
            <h3 class="box-title">虚拟仓统计</h3>
        </div>
        <div class="box-body" style="padding: 0px;">
            <div id="RDC"></div>
        </div>
    </div>
</div>


<!-- /.content -->
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/highchats/highcharts.js"></script>
<script type="text/javascript" src="static/highchats/highcharts-3d.js"></script>

<script type="text/javascript">
    $(function () {
        Highcharts.chart('container', {
            chart: {
                type: "column"
            },
            credits:{
                enabled:false // 禁用版权信息
            },
            title: {
                text: '月度销售额（业务模式）'
            },
            xAxis: {
                categories: ['1', '2', '3', '4', '5', '6',
                    '7', '8', '9', '10', '11', '12']
            },
            yAxis: {
                title: {
                    text: '销售额（万元）'
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
                name: '盐业公司',
                data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
            }, {
                name: '特通客户',
                data: [0, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
            }, {
                name: '仓储服务商',
                data: [0, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
            }, {
                name: '零售商',
                data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
            }]
        });

        Highcharts.chart('RDC', {
            chart: {
                type: 'pie',
                options3d: {
                    enabled: true,
                    alpha: 45
                }
            },
            title: false,
            plotOptions: {
                pie: {
                    innerSize: 100,
                    depth: 45
                }
            },
            series: [{
                name: 'Delivered amount',
                data: [
                    ['成都RDC', 8],
                    ['丹东RDC', 3],
                    ['广州RDC', 1],
                    ['华东RDC', 6],
                    ['宁陵RDC', 8],
                    ['钦州RDC', 4],
                    ['天津RDC', 4],
                    ['西安RDC', 1],
                    ['应城RDC', 1]
                ]
            }]
        });


    });

</script>

</body>
</html>

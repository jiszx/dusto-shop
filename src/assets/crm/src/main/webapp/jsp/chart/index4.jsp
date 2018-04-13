<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <meta charset="UTF-8">
    <title>运营主体覆盖</title>

    <style type="text/css">
        html, body {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
            overflow: hidden;
        }

        #map {
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>

<div id="map"></div>
<canvas id="canvas"></canvas>

<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=1XjLLEhZhQNUzd93EjU5nOGQ"></script>
<script type="text/javascript" src="static/Mapv.min.js"></script>

<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("map", {
        enableMapClick: false
    });    // 创建Map实例
    map.centerAndZoom(new BMap.Point(105.403119, 38.028658), 5);  // 初始化地图,设置中心点坐标和地图级别
    //map.enableScrollWheelZoom(true); // 开启鼠标滚轮缩放
    map.disableScrollWheelZoom();
    map.disableDragging();
    //map.disableDoubleClickZoom();

    map.setMapStyle({
        style: 'midnight'
    });

    var randomCount = 1000;

    var data = [];
    $.getJSON("chart/heatMap/company",{},function(res){
        if(res.errorCode==0) {
            for (var i = 0; i < res.data.length; i++) {
                var cityCenter1 = mapv.utilCityCenter.getCenterByCityName(res.data[i].to);
                var cityCenter2 = mapv.utilCityCenter.getCenterByCityName(res.data[i].from);
                if(cityCenter1 && cityCenter2){
                    data.push({
                        geometry: {
                            type: 'LineString',
                            coordinates: [[cityCenter1.lng, cityCenter1.lat], [cityCenter2.lng, cityCenter2.lat]]
                        },
                        count: res.data[i].count
                    });
                }
            }
            var dataSet = new mapv.DataSet(data);
            var options = {
                strokeStyle: 'rgba(255, 250, 50, 0.3)',
                shadowColor: 'rgba(255, 250, 50, 1)',
                shadowBlur: 20,
                lineWidth: 0.7,
                draw: 'simple'
            }
            var mapvLayer = new mapv.baiduMapLayer(map, dataSet, options);
        }
    });


</script>

</body>
</html>

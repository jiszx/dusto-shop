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
    <title>订单发运图</title>

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
    //    map.disableScrollWheelZoom();
    //    map.disableDragging();
    //    map.disableDoubleClickZoom();

//    map.setMapStyle({
//        style: 'midnight'
//    });
    var lineData = [];
    var pointData = [];
    var textData = [];
    var timeData = [];

    var citys = {}
    $.get('chart/heatMap/listOrderWeight', function(resData) {
        var qianxi=[]
        if(resData.errorCode==0){
            qianxi=new mapv.DataSet(resData.data);
        }
        var qianxiData = qianxi.get();


//        var data = geojsonDataSet.get({
//            filter: function (item) {
//
//                if (!citys[item.name]) {
//                    return false;
//                }
//
//                item.count = citys[item.name];
//                return true;
//            }
//        });
        //geojsonDataSet = new mapv.DataSet(data);
        //省图层不要
        //var mapvLayer = new mapv.baiduMapLayer(map, geojsonDataSet, geojsonOptions);

        createLine(qianxiData,citys)

        var textDataSet = new mapv.DataSet(textData);

        var textOptions = {
            draw: 'text',
            font: '14px Arial',
            fillStyle: 'white',
            shadowColor: 'yellow',
            shadowBlue: 10,
            zIndex: 11,
            shadowBlur: 10
        }

        var textMapvLayer = new mapv.baiduMapLayer(map, textDataSet, textOptions);

        var lineDataSet = new mapv.DataSet(lineData);

        var lineOptions = {
            strokeStyle: 'rgba(255, 250, 50, 0.8)',
            shadowColor: 'rgba(255, 250, 50, 1)',
            shadowBlur: 20,
            lineWidth: 2,
            zIndex: 100,
            draw: 'simple'
        }

        var lineLayer = new mapv.baiduMapLayer(map, lineDataSet, lineOptions);

        var pointOptions = {
            fillStyle: 'rgba(254,175,3,0.7)',
            shadowColor: 'rgba(55, 50, 250, 0.5)',
            shadowBlur: 10,
            size: 5,
            zIndex: 10,
            draw: 'simple'
        }


        var pointDataSet = new mapv.DataSet(pointData);

        var pointLayer = new mapv.baiduMapLayer(map, pointDataSet, pointOptions);


        var timeDataSet = new mapv.DataSet(timeData);

        console.log(timeData);

        var timeOptions = {
            fillStyle: 'rgba(255, 250, 250, 0.5)',
            zIndex: 200,
            size: 2.5,
            animation: {
                type: 'time',
                stepsRange: {
                    start: 0,
                    end: 50
                },
                trails: 10,
                duration: 2,
            },
            draw: 'simple'
        }

        var timeMapvLayer = new mapv.baiduMapLayer(map, timeDataSet, timeOptions);

    });


    function createLine(qianxiData,citys){
        for (var i = 0; i < qianxiData.length; i++) {
            var fromCenter = mapv.utilCityCenter.getCenterByCityName(qianxiData[i].from);
            var toCenter = mapv.utilCityCenter.getCenterByCityName(qianxiData[i].to);
            if (!fromCenter || !toCenter) {
                continue;
            }
            citys[qianxiData[i].from] = qianxiData[i].count;
            citys[qianxiData[i].to] +=qianxiData[i].count;
            pointData.push(
                {
                    geometry: {
                        type: 'Point',
                        coordinates: [fromCenter.lng, fromCenter.lat]
                    }
                }
            );
            pointData.push(
                {
                    geometry: {
                        type: 'Point',
                        coordinates: [toCenter.lng, toCenter.lat]
                    }
                }
            );
            textData.push(
                {
                    geometry: {
                        type: 'Point',
                        coordinates: [fromCenter.lng, fromCenter.lat]
                    },
                    text: qianxiData[i].from
                }
            );
            textData.push(
                {
                    geometry: {
                        type: 'Point',
                        coordinates: [toCenter.lng, toCenter.lat]
                    },
                    text: qianxiData[i].to
                }
            );

            var curve = mapv.utilCurve.getPoints([fromCenter, toCenter]);

            for (j = 0; j < curve.length; j++) {
                timeData.push({
                    geometry: {
                        type: 'Point',
                        coordinates: curve[j]
                    },
                    count: 1,
                    time: j
                });
            }

            lineData.push({
                geometry: {
                    type: 'LineString',
                    coordinates: curve
                    //coordinates: [[fromCenter.lng, fromCenter.lat], [toCenter.lng, toCenter.lat]]
                },
                count: qianxiData[i].count
            });

        }
    }
</script>

</body>
</html>
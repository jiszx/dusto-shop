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
    <title>客户交易量</title>

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
        enableMapClick: false,
        click:function(type, target, point, pixel, overlay){

            console.debug(1)
            console.debug(point)
        }
    });    // 创建Map实例
    map.centerAndZoom(new BMap.Point(105.403119, 38.028658), 5);  // 初始化地图,设置中心点坐标和地图级别
    map.enableScrollWheelZoom(true); // 开启鼠标滚轮缩放
    map.addEventListener('click', function(type, target, point, pixel, overlay){
        console.debug(type)
    });
    $.get('static/china.json', function(geojson) {
        var options = {
            splitList: [
                {
                    start: 0,
                    end: 10,
                    value: '#f1eef6'
                }, {
                    start: 10,
                    end: 20,
                    value: '#d5bad9'
                }, {
                    start: 20,
                    end: 30,
                    value: '#cc97c7'
                }, {
                    start: 30,
                    end: 40,
                    value: '#e469af'
                }, {
                    start: 40,
                    end: 50,
                    value: '#ee3387'
                }, {
                    start: 50,
                    end: 60,
                    value: '#d61e53'
                }, {
                    start: 60,
                    value: '#960b3d'
                }
            ],
            globalAlpha: 0.4,
            draw: 'choropleth'
        }
        var dataSet = mapv.geojson.getDataSet(geojson);
        var citys={};
        $.getJSON("chart/heatMap/amtCount",{},function(res) {
            if (res.errorCode == 0) {
                for (var i = 0; i < res.data.length; i++) {
                    citys[res.data[i].name] = res.data[i].count;
                }
                var data = dataSet.get({
                    filter: function (item) {
                        if (!citys[item.name]) {
                            return false;
                        }
                        item.count = citys[item.name]/1000000;
                        return true;
                    }
                });
                dataSet = new mapv.DataSet(data);
                var mapvLayer = new mapv.baiduMapLayer(map, dataSet, options);

                var dd=[]
                var textOptions = {
                    draw: 'text',
                    font: '14px Arial',
                    fillStyle: 'blue',
                    shadowColor: 'white',
                    shadowBlue: 20,
                    zIndex: 11,
                    shadowBlur: 10
                }
                for (var key in citys) {
                    var center = mapv.utilCityCenter.getCenterByCityName(key.replace('省', ''));
                    dd.push(
                        {
                            geometry: {
                                type: 'Point',
                                coordinates: [center.lng, center.lat]
                            },
                            text: key+"："+(citys[key]/1000000.0).toFixed(2)+"万"
                        }
                    );
                }
                var ds = new mapv.DataSet(dd);

                var mapvLayer = new mapv.baiduMapLayer(map, ds, textOptions);
            }
        });
    })



//           $.getJSON("chart/heatMap/amtCount",{},function(res){
//        if(res.errorCode==0){
//            for(var i =0;i<res.data.length;i++){
//                //var cityCenter = mapv.utilCityCenter.getCenterByCityName(res.data[i].name);
//                data.push({
//                    geometry: {
//                        type: 'Point',
//                        coordinates: [cityCenter.lng , cityCenter.lat]
//                    },
//                    count: res.data[i].count/100.0
//                })
//                cityNames[res.data[i].name]=res.data[i].count;
//                getBoundary(res.data[i].name,res.data[i].count/100.0);
//            }
//
//
//
//        }
//    });
   // });
//    var cityLoad = { //判断加载完成的城市边界数据
//    }
//    var cityNames = {};
//    var data = [];
//    function getBoundary(cityname,count){
//        var bdary = new BMap.Boundary();
//        bdary.get(cityname, function(rs){ // 异步加载
//            cityLoad[cityname] = true;
//            var boundary = rs.boundaries[0];
//            boundary = boundary.split(";");
//            for (var i = 0; i < boundary.length; i++) {
//                boundary[i] = boundary[i].split(",");
//            }
//            data.push({
//                geo: boundary,
//                text:cityname+"交易金额:"+count/1000000.0+"万",
//                count: count
//            });
//            if (isAllComplete()) {
//                allLoadCallback();
//            }
//        });
//    }
//    function isAllComplete() {
//        for (var key in cityNames) {
//            if (!cityLoad[key]) {
//                return false;
//            }
//        }
//        return true;
  //  }
//    function allLoadCallback() {
//        var dataSet = new mapv.DataSet(data);
//
//        var options = {
//            size: 10,
//            gradient: { 0.01: "rgb(0,0,255)", 0.55: "rgb(0,255,0)", 0.85: "yellow", 1.0: "rgb(255,0,0)"},
//            max: 100000,
//            draw: 'heatmap'
//        }
//
//        var mapvLayer = new mapv.baiduMapLayer(map, dataSet, options);
//    }
//    function allLoadCallback() {
//
//        var options = {
//            gradient: {
//                    '0.01': 'blue',
//                    '0.1':'cyan',
//                    '0.5': 'lime',
//                    '0.8': 'yellow',
//                    '1.0': 'red'
//            },
//            dataType: 'polygon',
//            globalAlpha: 0.8,
//            draw: 'intensity'
//        }
        //var dataSet = new mapv.DataSet(data);
        //var mapvLayer = new mapv.baiduMapLayer(map, dataSet, options);
//
//
//        console.log(data);
//
//        var options = {
//            zIndex: 1,
//            mapv: mapv,
//            dataType: 'polygon',
//            data: data,
//            drawType: 'intensity',
//            drawOptions: {
//                //strokeStyle: 'yellow',
//                //lineWidth: 1,
//                max: 10000000,
//                draw:"text",
//                label: { // 显示label文字
//                    show: true, // 是否显示
//                    font: "18px", // 设置字号
//                    minZoom: 5, // 最小显示的级别
//                    fillStyle: 'rgba(255, 255, 255, 1)' // label颜色
//                },
//                gradient: {
//                    '0.01': 'blue',
//                    '0.1':'cyan',
//                    '0.5': 'lime',
//                    '0.8': 'yellow',
//                    '1.0': 'red'
//                }
//            }
//        };
//        var mapvLayer = new mapv.baiduMapLayer(map, dataSet, options);
////        var layer = new Mapv.Layer(
////        });

  //  }

    //var randomCount = 1000;



//    $.getJSON("chart/heatMap/amtCount",{},function(res){
//        if(res.errorCode==0){
//            for(var i =0;i<res.data.length;i++){
//                //var cityCenter = mapv.utilCityCenter.getCenterByCityName(res.data[i].name);
////                data.push({
////                    geometry: {
////                        type: 'Point',
////                        coordinates: [cityCenter.lng , cityCenter.lat]
////                    },
////                    count: res.data[i].count/100.0
////                })
//                cityNames[res.data[i].name]=res.data[i].count;
//                getBoundary(res.data[i].name,res.data[i].count/100.0);
//            }
//
//
//
//        }
//    });


    // 构造数据
//    while (randomCount--) {
//        var cityCenter = mapv.utilCityCenter.getCenterByCityName(citys[parseInt(Math.random() * citys.length)]);
//        data.push({
//            geometry: {
//                type: 'Point',
//                //coordinates: [cityCenter.lng - 2 + Math.random() * 4, cityCenter.lat - 2 + Math.random() * 4]
//                coordinates: [cityCenter.lng , cityCenter.lat]
//            },
//            count: 30 * Math.random(),
//            time: 100 * Math.random()
//        });
//    }


</script>

</body>
</html>

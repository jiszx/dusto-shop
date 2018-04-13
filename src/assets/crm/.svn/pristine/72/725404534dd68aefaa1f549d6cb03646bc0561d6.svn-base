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
    <title>客户分布图</title>
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
<script src="${applicationScope.STATIC}static/js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=1XjLLEhZhQNUzd93EjU5nOGQ"></script>
<script type="text/javascript" src="http://api.map.baidu.com/getscript?v=2.0&ak=1XjLLEhZhQNUzd93EjU5nOGQ"></script>
<script type="text/javascript" src="static/Mapv.min.js"></script>

<script type="text/javascript">
    var Prov_data_mapvLayer=null;
    var Prov_mapvLayer=null;

    var current_map = null;
    var current_data=null;

    // 百度地图API功能

    var map = new BMap.Map("map", {
        enableMapClick: false
    });    // 创建Map实例
    var geoc = new BMap.Geocoder();
    map.centerAndZoom(new BMap.Point(105.403119, 38.028658), 5);  // 初始化地图,设置中心点坐标和地图级别
    map.enableScrollWheelZoom(true); // 开启鼠标滚轮缩放
//    map.addEventListener('click', function(e){
//        console.debug(e);
//        var pt = e.point;
//        geoc.getLocation(pt, function(rs){
//            var addComp = rs.addressComponents;
//            map.setCenter(addComp.province);
//        });
//        map.setZoom(7)
////        Prov_mapvLayer.hide();
////        Prov_data_mapvLayer.hide();
//    });
//    map.addEventListener('rightclick', function(e){
//        map.setZoom(5)
//        map.setCenter("中国");
//        Prov_mapvLayer.show();
//        Prov_data_mapvLayer.show();
//    });
    initProvince();

    function setCurrent(prov){

        $.getJSON("chart/heatMap/provCount",{"pid":prov},function(res) {
            if (res.errorCode == 0) {
                for (var i = 0; i < res.data.length; i++) {
                    citys[res.data[i].name] = res.data[i].count;
                }
                var data = dataSet.get({
                    filter: function (item) {
                        if (!citys[item.name]) {
                            return false;
                        }
                        item.count = citys[item.name]*10;
                        return true;
                    }
                });
                dataSet = new mapv.DataSet(data);
                Prov_mapvLayer = new mapv.baiduMapLayer(map, dataSet, options);

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
                    console.debug(key)
                    var center = mapv.utilCityCenter.getCenterByCityName(key.replace('省', '').replace("区","").replace("市",""));
                    dd.push(
                        {
                            geometry: {
                                type: 'Point',
                                coordinates: [center.lng, center.lat]
                            },
                            text: key+"："+citys[key]
                        }
                    );
                }
                var ds = new mapv.DataSet(dd);

                Prov_data_mapvLayer = new mapv.baiduMapLayer(map, ds, textOptions);
            }
        });
    }

    function initProvince(){
        $.get('static/china.json', function(geojson) {
            var options = {
                splitList: [
                    {
                        start: 0,
                        end: 100,
                        value: '#f1eef6'
                    }, {
                        start: 100,
                        end: 200,
                        value: '#d5bad9'
                    }, {
                        start: 200,
                        end: 300,
                        value: '#cc97c7'
                    }, {
                        start: 300,
                        end: 400,
                        value: '#e469af'
                    }, {
                        start: 400,
                        end: 500,
                        value: '#ee3387'
                    }, {
                        start: 500,
                        end: 600,
                        value: '#d61e53'
                    }, {
                        start: 600,
                        value: '#960b3d'
                    }
                ],
                globalAlpha: 0.4,
                draw: 'choropleth'
            }
            var dataSet = mapv.geojson.getDataSet(geojson);
            var citys={};
            $.getJSON("chart/heatMap/provCount",{},function(res) {
                if (res.errorCode == 0) {
                    for (var i = 0; i < res.data.length; i++) {
                        citys[res.data[i].name] = res.data[i].count;
                    }
                    var data = dataSet.get({
                        filter: function (item) {
                            if (!citys[item.name]) {
                                return false;
                            }
                            item.count = citys[item.name]*10;
                            return true;
                        }
                    });
                    dataSet = new mapv.DataSet(data);
                    Prov_mapvLayer = new mapv.baiduMapLayer(map, dataSet, options);

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
                        console.debug(key)
                        var center = mapv.utilCityCenter.getCenterByCityName(key.replace('省', '').replace("区","").replace("市",""));
                        dd.push(
                            {
                                geometry: {
                                    type: 'Point',
                                    coordinates: [center.lng, center.lat]
                                },
                                text: key+"："+citys[key]
                            }
                        );
                    }
                    var ds = new mapv.DataSet(dd);

                    Prov_data_mapvLayer = new mapv.baiduMapLayer(map, ds, textOptions);
                }
            });
        })
    }
</script>

</body>
</html>

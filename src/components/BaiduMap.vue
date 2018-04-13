<template>
  <!--地图容器-->
  <el-amap ref="map" vid="amapDemo" :center="center" :zoom="zoom" class="amap-demo XSDFXPage" />
</template>
<script>
  export default {
    name: 'baiduMap',
    data() {
      return {
        map:undefined,
        zoom: 12,
        center: [121.59996, 31.197646],
      }
    },
    mounted() {

    },
    methods:{

      mapPanTo(jd,wd){

      },
    },
  }
</script>
<style>

</style>

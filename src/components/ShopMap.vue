<template>
    <div class="componentsMap">
      <!--地图容器-->
      <el-amap ref="map" vid="amapDemo" :center="currentShop.shopJD?[currentShop.shopJD,currentShop.shopWD]:['120.6746890','27.8346230']" :zoom="zoom" class="amap-dom" >
        <!--当前定位标记-->
        <el-amap-marker :visible="currentShop.shopJD" vid="current-location-marker" :position="[currentShop.shopJD,currentShop.shopWD]" :zIndex="200" :label="currentShopLabelTemplate">
          <div class="mapMarkShop locationMarker" @click="_currentMarkerCliclEvents()"><Icon name="location"></Icon></div>
        </el-amap-marker>
        <!--所有店铺位置标记-->
        <el-amap-marker v-for="(marker, index) in shopList" :position="[marker.shopJD,marker.shopWD]"  :vid="index" :key="index">
          <div class="mapMarkShop" @click="_markerCliclEvents(marker)"><Icon name="shop"></Icon></div>
        </el-amap-marker>
      </el-amap>
    </div>
</template>

<script>
    import {mapState} from "vuex"
    import { Icon } from 'vant'
    export default {
        props:{
          currentShopData:{
            type:Object,
            require:true,
          }
        },
        data(){
          return{
            locationChange:false,
            zoom: 12,
            markers:[],
            formData:{
              keyword:"",
              longitude:"120.677329",
              latitude:"27.848174"
            },
            currentShop:{

            },
          }
        },
      computed: {
        ...mapState({
          shopList: state => state.shop.shopList
        }),
        currentShopLabelTemplate(){
          var shopName = this.currentShop.shopName;
          var template = `
              <div class="mapTips">
                <p>${shopName}</p>
              </div>
            `;

          if(!this.currentShop.shopName){
            return{}
          }
          return {
            content:template,
            offset: [-47, -63]
          }
        },
      },
      created(){
        if(!this.shopList.length){
          this.$store.dispatch('getShopList',this.formData)
        }
        this.currentShop = this.currentShopData;
        if(!this.currentShopData.shopJD){
          this.currentShop.shopJD = 120.6746890;
          this.currentShop.shopWD= 27.8346230;
        }
      },
      components:{
        Icon
      },

      watch:{
        currentShopData(value){
          this.currentShop=value;
        }
      },
      methods:{
        _markerCliclEvents(shopData){
          this.currentShop=shopData;
          this.$emit('change',shopData);
        },
        _currentMarkerCliclEvents(){

        }
      },
    }
</script>

<style scoped>
.componentsMap{
  position: relative;
  width: 100%;
  height: 100%;
}
  .mapMarkShop{
    display: inline-block;
    width: 32px;
    height: 32px;
    font-size: 0.32rem;
    color: #fff;
    background: #f44335;
    border: 2px solid #fff;
    border-radius: 50%;
    text-align: center;
    line-height: 32px;
  }
  .locationMarker{
    z-index: 200;
    transition: all 0.28s;
  }
  /*写在common.css*/
.mapTips{
   }
.mapTips:after{
}

</style>

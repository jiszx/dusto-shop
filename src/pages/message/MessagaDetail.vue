<template>
  <div class="content">
    <div class="title">
      <div class="htitle">{{$route.params.id}}:{{msgDetailData.infoName}}</div>
      <div class="time">{{msgDetailData.createDate}}</div>
    </div>
    <div class="msgtopImg">
      <img src="../../assets/images/article.png"/>
    </div>
    <p class="textContent">
      {{msgDetailData.infoContent}}
    </p>
    <quick-bar></quick-bar>
  </div>
</template>

<script>
  import ToolBar from "../../components/ToolBar";
  import QuickBar from "../../components/QuickBar"
  import {mapState} from "vuex"

  export default {
    data(){
      return {
        formData:{
          infoID:'9'
        }
      }
    },
    computed:{
      ...mapState({
        msgDetailData: state => state.information.msgDetailData
      })
    },
    created(){
      console.log(this.msgDetailData);
      this.$store.dispatch('getMessageDetai',this.formData);
    },
    methods:{
    
    },
    components: {
      ToolBar, 
      QuickBar
    }
  };
</script>

<style scoped>
  .content {
    padding: 0px 15px;
  }

  .content .title {
    height: 105px;
    padding-top: 19px;
    border-bottom: 1px solid #e5e5e5;
  }

  .content .title .htitle {
    font-size: 25px;
    font-weight: bold;
    margin-bottom: 15px;
  }

  .content .title .time {
    font-size: 12px;
    color: #b3b3b3;
  }

  .content .msgtopImg img {
    width: 100%;
    margin-top: 15px;
  }
  .content .textContent{
    font-size: 16px;
    color: #1a1a1a;
  }
</style>

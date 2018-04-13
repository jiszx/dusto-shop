<template>
  <div>
    <div class="listContent" v-for="msg in msgListData">
      <div class="title">
        <p class="bigTitle" @click="turnTo(msg.infoId)">{{msg.infoName}}</p>
        <div class="time">{{msg.createDate}}</div>
      </div>
      <div class="imgDIV"><img src="../../assets/images/listImg.png" @click="turnTo(msg.infoId)"/></div>
    </div>
    {{$route.params.title}}
    <quick-bar></quick-bar>
  </div>
</template>

<script>
  import {mapState} from "vuex"
	import QuickBar from "components/QuickBar";
  export default {
    data(){
      return {
        formData:{

        },
      }
    },
    components:{
      QuickBar
    },
    methods:{
      turnTo(id) {
        this.$router.push({name: "MsgDetail", params: {id: id}})
      }
    },
    computed:{
      ...mapState({
        msgListData: state => state.information.msgListData
      })
    },
    created(){
      console.log(this.msgListData);
      this.$store.dispatch('getMsgList',this.formData);
    },
  };
</script>

<style scoped>
  .listContent {
    height: 105px;
    display: flex;
    flex-direction: row;
    padding-right: 15px;
    margin-left: 15px;
    align-items: stretch;
    justify-content: space-between;
    border-bottom: 1px solid #e5e5e5;
  }

  .listContent .title {
    margin-top: 25px;
    margin-bottom: 15px;
    width: 66.6666%;
    align-content: space-around;
  }

  .listContent .title .bigTitle {
    font-size: 18px;
    font-weight: bold;
  }

  .listContent .title .time {
    font-size: 12px;
    color: #b3b3b3;
  }

  .listContent .imgDIV {
    width: 33.3333%;
    display: flex;
    flex-direction: column;
    align-items: flex-end;
    margin: 15px;
    margin-right: 0px;
    justify-content: center;
  }

  .listContent .imgDIV img {
    height: 100%;
  }
</style>

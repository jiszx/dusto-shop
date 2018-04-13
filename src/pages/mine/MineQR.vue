<template>
  <div id="app" class="MineQR">
    <div class="myQR">
      <div class="divQR">
        <div class="userImgQR">
          <div class="imgOuterQR"></div>
        </div>

        <div class="memberNumber memberGrade">
          <!--<span class="theNumber">{{memberMsg.grade}}</span>-->
        </div>

        <div class="memberNumber ptMember" v-if="memberType===1">
          <span>会员卡号：<span class="theNumber">{{memberMsg.memberNo}}</span></span>
        </div>
        <div class="memberNumber ffMember" v-if="memberType===2">
          <span>会员卡号：<span class="theNumber">{{memberMsg.memberNo}}</span></span>
        </div>

        <div class="memberOuterQR">
          <div class="memberQR">
            <img class="weui-media-box__thumb" src="../../assets/images/mine/BR.png" alt="">
          </div>
        </div>

        <div class="prompt">
          <span>结账时，请出示二维码供扫描</span>
        </div>

        <img v-if="memberType===1" src="../../assets/images/img-hyk-pthy.png">
        <img v-if="memberType===2" src="../../assets/images/img-hyk-ffhy.png">
      </div>
    </div>
  </div>
</template>

<script>
  import { mapState } from 'vuex';
  export default {
    data(){
      return {
        formData:{
          memberNo:'25000105079',
          phone:'18200000000',
        },
        memberType:1,
      }
    },
    computed:{
      ...mapState({
        memberMsg:state => state.auth.memberMsg,
      })
    },
    created(){
      console.log(this.memberMsg)
      this.$store.dispatch('getMyMessage',this.formData)
    },
    components: {

    }
  };
</script>

<style scoped>
  .MineQR {
    position:fixed;
    top:0;
    right:0;
    bottom:0;
    left:0;
    background: #262626;
  }
  .divQR{position:relative;}
  .van-cell__title .van-icon {
    font-size: 18px;
  }
  .myQR{
    display: -webkit-box;
    display: -webkit-flex;
    display: flex;
    -webkit-box-orient: horizontal;
    -webkit-box-direction: normal;
    -webkit-flex-direction: row;
    flex-direction: row;
  }
  .divQR{
    padding:0 50px;
  }
  .divQR img{width:100%;}
  .userImgQR{width: 60px; height:60px;margin: 0 auto;}
 .imgOuterQR{position: absolute;width: 60px; height:60px;top: 15px;border-radius: 5px;background-image:url("../../assets/images/mine/img-mrtx.png");background-size: 100%;}

  .memberNumber{
    position: absolute;
    width: 100%;
    text-align: center;
    left: 0px;
    top: 135px;
    font-size:12px;
  }
  .ptMember{
    color:#8F8F8D;
  }
  .ffMember{
    color:#b09160;
  }
  .memberGrade{
    top: 90px;
    font-size: 16px;
    font-weight: bold;
    color: #fff;
  }

  .memberOuterQR{
    margin: 0 auto;
    width: 183px;
  }
  .memberQR{
    position: absolute;
    width: 100%;
    top:175px;
  }
  .memberQR img{width:183px;}

  .prompt{
    position: absolute;
    width: 100%;
    text-align: center;
    left: 0px;
    bottom:15px;
    font-size:11px;
    color:#b3b3b3;}
</style>

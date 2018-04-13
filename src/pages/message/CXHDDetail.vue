<template>
  <div>
    <div class="topImg" :style="topImg" v-for="(item,index) in advertisementinfo" :key="item.memberImg">
      <div class="hdgz" @click="showRules()">
        <img src="../../assets/images/icon-hdgz.png"/>
      </div>
    </div>
    <div class="linkText">
      <div class="text">至少邀请3个好友可获得</div>
      <div class="icons"><img src="../../assets/images/icon-fenxian-h.png"/></div>
    </div>

    <div class="tickets">
      <div class="cxhdd-ticket">
        <img src="../../assets/images/img-yhq-x-1.png"/>
      </div>
      <div class="space"></div>
      <div class="cxhdd-ticket">
        <img src="../../assets/images/img-yhq-x-2.png"/>
      </div>
    </div>
    <div>
			<goods-sort-tab :listTypeIsCol="listTypeIsCol" @toggerList="toggerList"></goods-sort-tab>
			<list-column v-if="listTypeIsCol"></list-column>
			<list-row v-else></list-row>
		</div>
		<quick-bar></quick-bar>
  </div>
</template>
<script>

  import {Dialog} from 'vant'
	import GoodsSortTab from "components/GoodsSortTab"
	import ListColumn from "components/ListColumn";
	import ListRow from "components/ListRow";
	import QuickBar from "components/QuickBar";
  import { mapState } from "vuex";
  export default {
    components: {
      Dialog,
      GoodsSortTab,
			ListColumn,
			ListRow,
			QuickBar
    },
    data(){
			return {
				listTypeIsCol: true, // 列表类型 true 竖 false横
        topImg:{
          backgroundImage: "url(" + require("../../assets/images/banner-cxhd-1.png") + ")",
          backgroundRepeat: "no-repeat",
        },
        formData:{
          positionID:'ol_top_001',
          channelType:'ONLINE'
        }
			}
		},
    computed:{
      ...mapState({
        advertisementinfo: state => state.auth.advertisementinfo
      }),
    },
    created(){
      console.log(this.advertisementinfo)
      this.$store.dispatch('getAdvertisementinfo',this.formData)
    },
    methods: {
      showRules() {
        Dialog.alert({
          title: "活动规则",
          message: '<p style="font-size: 16px;font-weight:bold;text-align: left">互动奖品：分享任务完成20元优惠券</p>' +
          '<br/>' +
          '<p style="font-size: 16px;font-weight:bold;text-align: left">活动时间：2018/1/1-2018/2/14</p>' +
          '<br/>' +
          '<p style="font-size: 16px;font-weight:bold;text-align: left">活动规则：此活动有两个任务，完成关注店铺</p>',
          confirmButtonText: "我知道啦"
        }).then(() => {
          // on confirm
        }).catch(() => {
          // on cancel
        });
      },
      // 改变列表类型
      toggerList(bool){
				this.listTypeIsCol = bool;
			}
    }
  };
</script>
<style scoped>
	@import url("../../assets/css/list.css");
  .topImg {
    height: 180px;
    background-size: 100% 100%;
    padding-top: 15px;
  }

  .topImg .hdgz {
    height: 25px;
    display: flex;
    flex-direction: row;
    align-items: flex-end;
    justify-content: flex-end;
  }

  .topImg .hdgz img {
    height: 100%;
  }

  .linkText {
    margin-top: 15px;
    padding: 0px 15px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }

  .linkText .text {
    color: #b3b3b3;

  }

  .linkText .icons {
    height: 20px;
  }

  .linkText .icons img {
    height: 100%;
  }

  .tickets {
    padding: 15px 15px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }

  .tickets .space {
    width: 20px;
  }

  .tickets .cxhdd-ticket img {
    width: 100%;
  }

  .van-dialog__header {
    font-size: 18px;
    font-weight: bold;
  }
</style>

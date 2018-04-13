<template>
  	<div class="list-item uploadVoucher">
       <div class="weui-cell">
          <div class="weui-cell__hd"><label class="weui-label">上传凭证</label></div>
      </div>
      <div class="weui-cell">
        <img class="we-img" alt="" src="../assets/images/goods/banner-zx-6.png" />
        <div class="we-img-upload" @click="showActionAS">
            <i class="photograph-icon"></i>
            <span class="we-upload-txt">上传凭证</span>
            <span class="we-upload-txt">(最多3张)</span>
        </div>
        <ActionSheet :showAs="show" @hideAs="hideAs">
            <div class="cameraModel">
                <p class="photograph">拍照</p>
                <p class="albumSelection">从手机相册选取<uploader class="albumSelection-upload" :after-read="onRead"></uploader></p>
                <p class="partingLine"></p>
                <p class="cancel" @click="hideAs">取消</p>
            </div>
        </ActionSheet>
      </div>
    </div>
</template>

<script>
  import { Icon, Field, Uploader, Button } from 'vant';
  import ActionSheet from "components/ActionSheet";
  export default {
  	data() {
	    return {
	    	  show: false
	    }
  	},
  	components: {
      Icon, 
      Field, 
      Uploader, 
      Button,
      ActionSheet
  	},
    methods: {
	    onRead(file) {
	      console.log(file)
	    },
      showActionAS(){
        this.show = true
      },
      hideAs(){
        this.show = false
      }
    }
  };
</script>

<style>
  .uploadVoucher{
    border-bottom: 0.02rem solid #e5e5e5;
    background:#fff;
  }

  /*上传凭证*/
  .we-img-upload{
    width: 1.4rem;
    height: 1.4rem;
    margin-bottom: 0.2rem;
    
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    
    background: #f7f7f7;
  }
  .we-img{
    padding-right: 0.1rem;
    margin-bottom: 0.2rem;
    width: 1.4rem;
    height: 1.4rem;
  }
  .photograph-icon{
    display: block;
    width: 0.5rem;
    height: 0.5rem;
    background: url(../assets/images/icon/icon-scpz.png) no-repeat center;
    background-size: 100% auto;
  }
  .we-upload-txt{
    font-size: 0.2rem;
    color: #ccc;
  }
  .cameraModel {
    background: #fff;
    font-size:0.24rem;
    color:#000;
  }
  .albumSelection-upload{
    width:100%;
    height:1rem;
    position:absolute;
    left:0;
  }
   /*弹层*/
  .cameraModel p{
    height:1.2rem;
   }
</style>

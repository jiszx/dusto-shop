<template>
  <div class="componnet-filtrate-popup" :class="[filtrateShow?'filtrate-show':'filtrate-hide']">
    <div class="filtrate-popup-inner">
      <div class="filtrate-min">
        <div class="border-1px-b marginB30">
          <FiltrateCheckBoxGroup
            title="鞋跟样式"
            v-model="frontType"
            :data="frontTypeSelectDataList"
            multiple></FiltrateCheckBoxGroup>
        </div>
        <div class="border-1px-b marginB30">
          <FiltrateCheckBoxGroup
            title="鞋跟样式"
            v-model="frontType"
            :data="frontTypeSelectDataList"
            multiple></FiltrateCheckBoxGroup>
        </div>
        <div class="border-1px-b marginB30">
          <FiltrateCheckBoxGroup
            title="鞋跟样式"
            v-model="frontType"
            :data="frontTypeSelectDataList"
            multiple></FiltrateCheckBoxGroup>
        </div>

        <div class="marginB30">
          <div class="title">价格区间</div>
          <div>
            <div class="inline-block filtratePrice text-center">
              <Field v-model="formData.lowerPrice" center placeholder="请输入" />
            </div>
            <span>-</span>
            <div class="inline-block filtratePrice text-center">
              <Field v-model="formData.higherPrice" @blur="blurFn" @focus="focusFn" center placeholder="请输入" />
            </div>
          </div>
        </div>

        <div class="confirm-btn-wrapper fontsize-32 text-center">
          <div class="confirm-btn border-1px-t" @click="reset">重置</div>
          <div class="confirm-btn bg-primary white" @click="confirm">完成</div>
        </div>
      </div>
      <div class="mask-wrapper" @click="closeFiltratePopup"></div>
    </div>
  </div>
</template>

<script>
    import FiltrateCheckBoxGroup from 'components/FiltrateCheckBoxGroup';
    import { Field } from 'vant'
    export default {
      props:{
        filtrateShow:{
          type:Boolean,
          default:false,
        }
      },
      data(){
        return{
          frontType:['粗跟'],
          frontTypeSelectDataList:['粗跟','平跟','中跟','方根','细跟','坡跟'],
          formData:{
            lowerPrice:'',
            higherPrice:'',
          },
        }
      },
      components:{
        FiltrateCheckBoxGroup,
        Field,
      },
      watch:{
        'formData.higherPrice':function (current,old) {
          console.log(current,old)
        }
      },
      methods:{
        closeFiltratePopup(){
          this.$emit('close');
        },
        reset(){
          this.frontType=[];
          this.formData.lowerPrice='';
          this.formData.higherPrice='';
        },
        confirm(){
          this.$emit('close');
        },
        blurFn(){
          console.log(this.formData.higherPrice)
        },
        focusFn(){
          console.log(this.formData.higherPrice)
        },
      },
    }
</script>

<style scoped>
  .componnet-filtrate-popup{
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    overflow: hidden;
    z-index: 100;
  }
  .filtrate-min{
    box-sizing: border-box;
    width: 100%;
    height: 100%;
    padding:0.3rem 0.24rem 0;
    background-color: #fff;
    overflow-x: auto;
    position: relative;
    z-index: 2;
    transition: all 0.28s;
  }
.filtrate-popup-inner{
  position: relative;
  width: 100%;
  height: 100%;
  padding-left: 20%;
}


  .mask-wrapper{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 1;
    background-color: rgba(0,0,0,.3);
  }

  /*运动*/
  .componnet-filtrate-popup.filtrate-show{
    transform: translateX(0);
    opacity: 1;
  }
  .componnet-filtrate-popup.filtrate-hide{
    transform: translateX(100%);
    opacity: 0;
  }

  .componnet-filtrate-popup.filtrate-show .filtrate-min{
    transform: translateX(0);
    opacity: 1;
  }
  .componnet-filtrate-popup.filtrate-hide .filtrate-min{
    transform: translateX(80%);
    opacity: 0.5;
  }

  /*内容区域*/
  .filtrate-min{
    padding-bottom: 0.76rem;
  }
  .confirm-btn-wrapper{
    position: absolute;
    bottom: 0;
    left: 0;
    height: 0.76rem;
    width: 100%;
    line-height: 0.76rem;
  }
  .confirm-btn{
    float: left;
    width: 50%;
  }
  .filtratePrice{
    width: 30%;
    vertical-align: middle;
  }
  .filtratePrice:last-child{
    padding-left: 15px;
  }
</style>

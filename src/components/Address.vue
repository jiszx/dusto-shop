<template>
  <div class="component-address marginB30">
    <div @click="_addressClick">
      <div class="address-name fontsize-30 marginB10">
        <span class="">{{name}}</span>
        <span class="pull-right">{{phone}}</span>
      </div>
      <div class="address-min fontsize-28 gray marginB20">
        {{address}}
      </div>
    </div>
    <div class="address-operation-btn fontsize-28 border-1px-t border-1px-b gray">
      <div class="pull-left set-default-box">
        <Checkbox v-model="checkboxValue" class="inline-block"></Checkbox>
        <span class="pull-right">默认地址</span>
        <div class="set-default-box-click" @click="_clickSetDefault"></div>
      </div>
      <div class="pull-right">
        <span class="address-btn" @click="_clickEdit">
          <img src="../assets/images/mine/icon-bianji-x.png" alt="">
          编辑
        </span>
        <span class="address-btn inline-block marginL30" @click="_clickDel">
          <img src="../assets/images/mine/icon-delete.png" alt="">
          删除
        </span>
      </div>
    </div>
  </div>
</template>

<script>
    import { Checkbox } from 'vant';
    var noFn = function () {};
    export default {
        props:{
          id:{
            type:[String,Number],
            require:true,
          },
          name:String,
          phone:String,
          address:String,
          isDefault:{
            type:Boolean,
            default:false,
          },
          addressClick:{
            type: Function,
            default: noFn
          },
          clickSetDefault:{
            type: Function,
            default: noFn
           },
          clickEdit:{
            type: Function,
            default: noFn
          },
          clickDel:{
            type: Function,
            default: noFn
          },
        },
        data(){
          return{
            checkboxValue:false,
          }
        },
        created(){
          this.checkboxValue = this.isDefault;
        },
        watch:{
          checkboxValue(val){
            if(val){
              this.$emit('clickSetDefault',(this.id))
            }
          },
          isDefault(val){
            this.checkboxValue = val;
          },
        },
        components:{
          Checkbox
        },
      methods:{
          _addressClick(){
            this.$emit('addressClick',this.id)
          },
        _clickEdit(){
          this.$emit('clickEdit',this.id)
        },
        _clickDel(){
          this.$emit('clickDel',this.id)
        },
        _clickSetDefault(){
          this.checkboxValue=true

        },
      },
    }
</script>

<style scoped>
.component-address{
  padding: 0.3rem 0;
}
  .component-address>div{
    padding: 0 0.24rem;
  }
  .address-btn{

  }
  .address-btn>img{
    display: inline-block;
    vertical-align: middle;
      width: 0.4rem;
      height: 0.4rem;
  }
  .address-operation-btn{
    height: 0.76rem;
    line-height: 0.76rem;
    overflow: hidden;
  }
  .set-default-box{
    position: relative;
  }
  .set-default-box-click{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 1;
    background: transparent;
    opacity: 0;
  }
</style>

<template>
  <div class="components-filtrate-checkbox-group fontsize-28">
      <div class="item-title">
        <span class="inline-block">{{title}}</span>
        <span class="inline-block fontsize-24 gray marginL10" v-if="multiple">可多选</span>
        <span class="inline-block paddingL10 paddingR10 pull-right fontsize-24 gray" @click="selectAll" v-if="multiple">全部</span>
      </div>
      <ul class="item-min marginT30 clearfix paddingB30">
        <li v-for="(item, index) in data" @click="itemClick(item)" :class="{active:value.includes(item)}" :key="index">
            {{item}}
        </li>
      </ul>
  </div>
</template>

<script>
    export default {
      props:{
        value:[Array, String, Number],
        title:String,
        multiple:Boolean,
        data:Array,
      },
      data(){
        return{

        }
      },
      methods:{
        selectAll(){
          this.$emit('input',this.data.slice(0));
        },
        itemClick(item){
          if(!this.multiple){
            this.$emit('input',item);
            return;
          }
          var list = this.value;
          if(list.includes(item)){
            list.splice(list.indexOf(item),1)
          }else{
            list.push(item)
          }
          this.$emit('input',list);
        },
      },
    }
</script>

<style scoped>
ul.item-min{}
ul.item-min li{
  float: left;
  width: 31%;
  text-align: center;
  background: #f2f2f2;
  height: 0.76rem;
  line-height: 0.76rem;
  margin-top: 3.5%;
}
ul.item-min li:nth-of-type(3n+2){
  margin-left: 3.5%;
  margin-right: 3.5%;
}
ul.item-min li.active{
  background: #ea5361;
  color:#fff;
}
</style>

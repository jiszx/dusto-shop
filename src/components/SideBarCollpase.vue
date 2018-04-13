<template>
  <Collapse v-model="activeName">
    <CollapseItem class="CollapseItem" :name="1">
      <div slot="title" class="CollapseItem-title nav-link-item-title fontsize-28">
        {{data.name}}
      </div>
      <div class="collapse-item-min fontsize-26">
        <div class="nav-link-item" v-for="(item,i) in data.children" :key="i" :class="[(item.isNew)?'isNew':'']" @click="linkTo(item.name)">{{item.name}}</div>
      </div>
    </CollapseItem>
  </Collapse>
</template>

<script>
  import {Collapse,CollapseItem} from '@/components/base/collapse/index.js'
    export default {
        props:{
          active:Number,
          data:{
            type:Object,
            default:{
              name:'',
              children:[],
            }
          },
        },
        data(){
          return{
            activeName:0,
          }
        },
        components:{
          Collapse,
          CollapseItem,
        },
        created(){
        },
        methods:{
          /**
           * 点击路由跳转
           */
          linkTo(){
            //todo somethids
            this.$router.push({name:"GoodsList"});
          },
        },
    }
</script>

<style scoped>
  /*内容区*/
  .side-bar-title{
    height: 1rem;
    line-height: 1rem;
    text-align: center;
  }
  .side-bar-title>p{
    /*font-weight: 600;*/
  }
  .side-bar-nav-link{
    padding: 0.2rem 0;
  }
  .nav-link-item{
    padding: 0.2rem 0;
  }
  .nav-link-item-title{
    color:#000;
    /*font-weight: 600;*/
  }

  .isNew{
    color: #ea5361;
  }
</style>

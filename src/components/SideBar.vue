<!--
  封装一个侧边栏导航业务组件
  props:
    side-show: Boolean 侧边栏是否展开
  events:
    close:当点击透明蒙版时触发关闭侧边栏事件
  slot:
    默认卡槽：内容区
-->
<template>
    <div class="component-side-bar" :class="[sideShow?'side-show':'side-hide']">
      <div class="side-bar-min">
        <div class="side-bar-title van-hairline--bottom fontsize-32">
          <p>产品分类</p>
        </div>
        <div class="side-bar-nav-link">

          <div v-for="(item,index) in navLinkData" :key="index">
            <Collapse v-model="active">
              <CollapseItem :name="index" class="CollapseItem" v-if="item.children&&item.children.length">
                <div slot="title" class="CollapseItem-title nav-link-item-title fontsize-28">
                  {{item.name}}
                </div>
                <div class="collapse-item-min fontsize-26">
                  <div class="nav-link-item" v-for="(data,i) in item.children" :key="i" :class="[(index===0&&i===1)?'isNew':'']">
                    <div v-if="data.children&&data.children.length">
                      <SideBarCollpase :active="data.active" :data="data" />
                    </div>
                    <div v-else @click="linkTo(data.name)">{{data.name}}</div>
                  </div>
                </div>
              </CollapseItem>
              <div class="nav-link-item nav-link-item-title" @click="linkTo(item.name)" v-else>
                {{item.name}}
              </div>
            </Collapse>
          </div>
        </div>
      </div>
      <div class="mask-wrapper" @click="closeSideBar"></div>
    </div>
</template>

<script>
    import {Collapse,CollapseItem} from '@/components/base/collapse/index.js'
    import SideBarCollpase from '@/components/SideBarCollpase'
    export default{
      props:{
        sideShow:{
          type:Boolean,
          default:false,
        }
      },
      data(){
        return{
          active:0,
          navLinkData:[
            {
              name:'新品',
              children:[{name:'全部新品', children:[{name:'冬靴'},{name:'单鞋'},{name:'休闲鞋'}]},{name:'11月22日新品'},{name:'11月11日新品'}]
            },{
              name:'冬靴',
              children:[{name:'全部新品'},{name:'11月21日新品'},{name:'11月11日新品'}]
            },{
              name:'单鞋',
              children:[{name:'全部新品'},{name:'11月21日新品'},{name:'11月11日新品'}]
            },{
              name:'休闲鞋',
              children:[{name:'全部新品'},{name:'11月21日新品'},{name:'11月11日新品'}]
            },{
              name:'男鞋'
            },{
              name:'童鞋'
            },{
              name:'包包'
            },{
              name:'配饰',
              children:[{name:'全部新品'},{name:'11月1日新品'},{name:'11月11日新品'}]
            },{
              name:'商场同款'
            },{
              name:'会员积分专享'
            }
          ]
        }
      },
      components:{
        Collapse,
        CollapseItem,
        SideBarCollpase
      },
      methods:{
        /**
         * 点击蒙版时向父元素抛一个close事件
         */
        closeSideBar(){
          this.$emit('close');
        },
        /**
         * 点击路由跳转
         */
        linkTo(){
          //todo somethids
          this.$router.push({name:"GoodsList"});
        },
      }
    }
</script>

<style scoped>
.component-side-bar{
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;
  z-index: 100;
}
.side-bar-min{
  box-sizing: border-box;
  width: 3.6rem;
  height: 100%;
  padding:0 0.3rem;
  background-color: #fff;
  overflow-x: auto;
  position: relative;
  z-index: 2;
  transition: all 0.28s;
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
.component-side-bar.side-show{
  transform: translateX(0);
  opacity: 1;
}
.component-side-bar.side-hide{
  transform: translateX(-100%);
  opacity: 0;
}

.component-side-bar.side-show .side-bar-min{
  transform: translateX(0);
  opacity: 1;
}
.component-side-bar.side-hide .side-bar-min{
  transform: translateX(-4.8rem);
  opacity: 0.5;
}

/*内容区*/
.side-bar-title{
  height: 1rem;
  line-height: 1rem;
  text-align: center;
}
.side-bar-title>p{
  font-weight: 600;
}
.side-bar-nav-link{
  padding: 0.2rem 0;
}
  .nav-link-item{
    padding: 0.2rem 0;
  }
  .nav-link-item-title{
    color:#000;
    font-weight: 600;
  }

  .isNew{
    color: #ea5361;
  }
</style>

<template>
	<div class="shop-list-tab">
		<div class="selectBar">
	      	<div class="title">{{selectProvince}} <span v-if="selectCity">{{'-'+selectCity}}</span> </div>
	      	<div class="title" @click="addrSelectFun">{{addrSelectText}}<i class="arrow-icon" :class="{'arrow-rotate-icon':addrSelect}"></i></div>
	      	<div class="title" @click="sortFun">{{sortTxt}}<i class="arrow-icon" :class="{'arrow-rotate-icon':aiSort}"></i></div>
	    </div>
	    <div v-show="aiSort" class="sort-list line-one-px">
      		<a
      			class="sort-list-item line-one-px"
      			v-for="(item,index) in sortData"
      			:key="item.typeId"
      			:class="{'sort-avtive':item.typeId===sortActive}"
      			@click="sortItemFun(item.typeId,index)">{{item.name}}<i class="active-icon"></i></a>
      	</div>
      	<div v-show="addrSelect" class="sort-list addr-select border-1px-b border-1px-t">
      		<tree-select
				:items="items"
				:main-active-index="mainActiveIndex"
				:active-id="parseInt(activeId)"
				@navclick="onNavClick"
				@itemclick="onItemClick"/>
      	</div>
    </div>
</template>

<script>
  import {TreeSelect} from 'vant';
  import { getAreainfo } from 'api/shop'
  export default {
  	components: {
      	TreeSelect
    },
    data(){
      return {
        aiSort: false, // 智能排序
        sortActive:0, // 选中项
        sortData:[{
        	name:"智能排序",
        	typeId: 0
        },{
        	name:"距离优先",
        	typeId: 1
        },{
        	name:"服务优先",
        	typeId: 2
        },{
        	name:"环境优先",
        	typeId: 3
        }],
        sortTxt:"智能排序",
        addrSelect: false, // 距离
        addrSelectText: "所有城市",
        items: [{
          text: '所有城市',
          children: []
        }],
        hasAreaIdList:[],
      	// 左侧高亮元素的index
     	mainActiveIndex: 0,
      	// 被选中元素的id
      	activeId: '1001',
        selectProvince:'所有城市',
        selectCity:''
      }
    },
    created(){
  	  this.getAreaData('0');
    },
    methods: {
      	// 点击智能排序
      	sortFun(){
      		this.aiSort = !this.aiSort;
      	},
      	// 点击排序列表
      	sortItemFun(typeId,index){
      		this.sortActive = typeId;
      		this.sortTxt = this.sortData[index].name;

      		this.$emit("shopListTabCB",0,typeId);
      		this.aiSort = false;
      	},
      	// 点击距离
      	addrSelectFun(){
      		this.addrSelect = !this.addrSelect;
      	},
      	// 点击左侧
      	onNavClick(index) {
	      	this.mainActiveIndex = index;
	      	this.selectProvince=this.items[index].text;
          this.selectCity = '';
          this.getAreaData(this.items[index].id);
	    },
	    // 点击右侧
	    onItemClick(data) {
	      	this.activeId = data.id;
	      	this.addrSelectText = data.text;
          this.selectCity = data.text;
	      	this.$emit("shopListTabCB",1,data.id);
	      	this.addrSelect = false;
	    },
      //以获取地址id list
      appendAreaData(parentId,dataList){
      	  //如果已经存在则不请求
          if(!this.hasAreaIdList.includes(parentId)){
            this.hasAreaIdList.push(parentId);
          }
          if(parentId==='0'){
            this.items=[...this.items,...dataList];
            console.log(this.items)
            return;
          }
          let item = this.items.find(v=>{
            return v.id === parentId;
          })
          item.children = [...item.children,...dataList];
          return;

      },
      //获取省份信息
      getAreaData(id){
        getAreainfo(id).then(res=>{
          if(res.response_code=='S000000'){
            let list = [];
            res.response_data.forEach(item=>{
              list.push({
                ...item,
                children:[],
                text:item.areaName,
                id:item.id
              })
            });
            this.appendAreaData(id, list);
          }
        })
      },
    },

    computed: {
    }
  };
</script>


<style>
	.shop-list-tab{
		position: relative;
	}
	/*tab*/
  	.selectBar {
	    display: flex;
	    height: 0.76;
	    margin-top: 5px;
	    font-size: 14px;
	    color: #1a1a1a;
	    flex-direction: row;
	    background: #f7f7f7;
	    padding: 10px 5px;
	    text-align: center;
  	}

  	.selectBar .title {
	    flex-grow: 1;
	    border-left: 1px solid #ccc;
  	}

  	.selectBar .title:nth-child(1) {
    	border: none;
  	}

  	.arrow-icon{
  		margin-left: 0.1rem;
		display: inline-block;
		width: 0.1rem;
		height: 0.2rem;

		background: url(../assets/images/icon/icon-right.png) no-repeat center;
		background-size: 100% auto;
		transform: rotate(90deg);
		transition: transform 0.2s ease-in-out;
  	}
  	.arrow-rotate-icon{
  		transform: rotate(-90deg);
  	}
  	/*tab 排序*/
  	.sort-list{
  		position: absolute;
  		top: 0.76rem;
  		left: 0;
  		right: 0;

  		padding: 0 0.3rem;
  		background: #fff;
  		z-index: 1;
  	}
  	.sort-list-item{
  		display: block;
  		line-height: 0.76rem;
  		text-align: left;
  		color: #1a1a1a;
  	}
  	.sort-list-item:last-child::after{
  		border: none;
  	}
  	.sort-avtive{
  		color: #EA5361;
  	}
  	.sort-avtive .active-icon{
  		margin-top: 0.25rem;
  		float: right;
  		width: 0.4rem;
  		height: 0.26rem;
  		background: url(../assets/images/icon/icon-xz.png) no-repeat center;
  		background-size: 100% auto;
  	}
  	.addr-select{
  		max-height: 10rem;
  		padding: 0;
  	}
  	.addr-select .van-tree-select__nav{
  		padding: 0 0.3rem;
  		width: 50%;
  		border-bottom: 1px solid #e5e5e5;
  		border-right: 1px solid #e5e5e5;
  	}
  	.addr-select .van-tree-select__nitem{
  		padding: 0;
  		line-height: 0.76rem;
  		text-align: left;
  		border-bottom: 1px solid #e5e5e5;
  	}
  	.addr-select .van-tree-select__nitem:last-child{
  		border-bottom: none;
  	}
  	.addr-select .van-tree-select__nitem--active{
  		color: #EA5361;
  		background: transparent;
  	}
  	.addr-select .van-tree-select__content{
  		margin-left: 50%;
  		border-bottom: 1px solid #e5e5e5;
  	}
  	.addr-select .van-tree-select__item{
  		line-height: 0.76rem;
  		text-align: left;
  		border-bottom: 1px solid #e5e5e5;
  	}
  	.addr-select .van-tree-select__selected{
  		margin-top: 0.24rem;
  	}
</style>

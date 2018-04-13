<template>
	<div class="star-wrap">
		<div v-for="(item,index) in starArr" :key="index">
			<img v-if="item===0" class="star" alt="" src="../assets/images/icon/img-star-wxz-l.png" @click="starCli(index,$event)" :ref="'star'+index" />
			<img v-if="item===-1" class="star" alt="" src="../assets/images/icon/img-star-xz-yb-l.png" @click="starCli(index,$event)" :ref="'star'+index" />
			<img v-if="item===1" class="star" alt="" src="../assets/images/icon/img-star-xz-l.png" @click="starCli(index,$event)" :ref="'star'+index" />
	   </div>
	</div>
</template>

<script>
	export default{
		name:"star",
		data(){
			return{
				starArr:[0,0,0,0,0]
			}
		},
		props:{
			isWrite:{
				type: Boolean,
				default: true
			},
			itemIndex:{
				type: Number,
				default: 0
			},
      value:{
        type: Number,
        default: 0
      },
		},
    mounted(){
		  let val = parseFloat(this.value)||0;
		  this.setArr(val);
    },
		methods:{
			starCli(index,ev){
				if(!this.isWrite) return;
				this.reSetArr();
				var	piont = index + 1,
					pageX = ev.pageX,
					domX = ev.target.offsetLeft,
					domW = ev.target.clientWidth/2,
					num = pageX - domX;
				(num<domW) && (piont = piont - 0.5);
        this.setArr(piont);
			},
			reSetArr(){
				for (var j = 0,len = this.starArr.length; j < len; j++) {
					this.$set(this.starArr,j,0)
				}
			},
      setArr(piont){
        var intNum = Math.ceil(piont);
        for (var i = 0; i < intNum; i++) {
          if(i!==(intNum-1)){
            this.starArr.splice(i, 1, 1)
          }else{
            i === (piont-1) ? this.starArr.splice(i, 1, 1) : this.starArr.splice(i, 1, -1);
          }
        }
        this.$emit('input',piont);
      },
		}
	}
</script>

<style>
	.star-wrap{
		display: flex;
		justify-content: space-between;
	}
	.star{
		display: block;
		width: 0.36rem;
		height: 0.36rem;
		/*background: url(../assets/images/icon/img-star-wxz-l.png) no-repeat center;*/
		/*background-size: 100% auto;
		margin-right: 0.3rem;*/
	},
	.allStar{
		background: url(../assets/images/icon/img-star-xz-l.png) no-repeat center;
		background-size: 100% auto;
	}
	.halfStar{
		background: url(../assets/images/icon/img-star-xz-yb-l.png) no-repeat center;
		background-size: 100% auto;
	}
</style>

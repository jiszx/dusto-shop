<template>
	<div class="wish-list">
		<list-column v-if="listTypeIsCol"></list-column>
		<list-row v-else></list-row>
		<quick-bar></quick-bar>
	</div>
</template>

<script>
	import ListColumn from "components/ListColumn";
	import ListRow from "components/ListRow";
	import QuickBar from "components/QuickBar";
	export default{
		name: "wishList",
		components:{
			ListColumn,
			ListRow,
			QuickBar
		},
		data(){
			return {
				listTypeIsCol: false
			}
		},
		methods:{
			toggerList(){
				this.listTypeIsCol = !this.listTypeIsCol;
			}
		}
	}
</script>

<style>
	.wish-list .save-icon{
		display: none;
	}
</style>
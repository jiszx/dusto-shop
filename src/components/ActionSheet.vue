<template>
	<div class="action-sheet"
		:class="{'show-as':showAs}"
		@click.self="hideAs">
		<div class="action-sheet-com" :class="{'as-com':showAs}" v-bind:style="{minHeight:minHei+'%'}">
			<slot></slot>
		</div>
	</div>
</template>

<script>
	export default{
		name:"actionSheet",
		props:{
			showAs:{
				type: Boolean,
				default: false
			},
			minHeight:{
				type: Number
			}
		},
		data(){
			return{
				minHei: this.minHeight || 'auto'
			}
		},
		methods:{
			hideAs(){
				this.$emit("hideAs")
			}
		}
	}
</script>

<style>
.action-sheet{
	position: fixed;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;

	pointer-events: none;
	background: transparent;
	transition: all .2s ease-in;
	z-index: 21;
}
.show-as{
	pointer-events: auto;
	background: rgba(0,0,0,.3);
}
.action-sheet-com{
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
	transform: translateY(110%);
	transition: transform .2s ease-in
}
.as-com{
	transform: translateY(0);
}
</style>

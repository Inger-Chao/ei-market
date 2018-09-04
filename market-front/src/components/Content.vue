<template>
	<!-- vue 中所有的组件要被根结点div包含起来 -->
	<div id="Content">
		

	</div>
</template>

<script>	
	export default{
		data(){
			return {
				msg:'Hello world from Content page！'
			}
		},methods:{
		},mounted(){
			console.log(this.$route.params);//获取动态路由的传值
		}
	}
</script>
<!-- scoped表示局部作用域 -->

<style lang="scss" scoped>
</style>
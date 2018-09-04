<template>
	<!-- vue 中所有的组件要被根结点div包含起来 -->
	<div id="user">
		<div class="user">
			<div class="left">
				<ul>
					<li>
						   <router-link to="/user/useradd">增加用户</router-link>
					</li>	
					<li>
						   <router-link to="/user/userlist">用户列表</router-link>
					</li>
				</ul>
			</div>
			<div class="right"><br><br>
			    <router-view></router-view>
			</div>
		</div>
	</div>
</template>

<script>	
	export default{
		data(){
			return {
				msg:'Hello world from User page！'
			}
		},methods:{
		},mounted(){
			console.log(this.$route.params);//获取动态路由的传值
		}
	}
</script>

<style lang="scss">
	.user{
		display:flex;

		.left{
			width:200px;
			min-height:400px;
			border-right:1px solid #eee;
		}

		.right{
			flex:1;
		}
	}
</style>
import Vue from 'vue'
import App from './App.vue'
import VueResource from 'vue-resource'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);
Vue.use(VueResource);
Vue.use(VueRouter);

//1. 创建组件

import Home from './components/home.vue';
import User from './components/User.vue';
import Content from './components/Content.vue';
import UserAdd from './components/User/UserAdd.vue';
import UserList from './components/User/UserList.vue';

//2.配置路由

const routes = [
	{ 
		path: '/user', 
		component: User,
		children:[
			{
				path:'useradd',component:UserAdd
			},
			{
				path:'userlist',component:UserList
			}
		]
	},
	{ path: '/home', component: Home},
	{ path: '/content/:aid', component: Content},//动态路由
	{ path: '*', redirect:'home'}, //默认跳转路由为Home

]

//3.实例化VueRouter

const router = new VueRouter({
	mode: 'history', //表示把hash模式改为history模式 URL中没#，后端服务器需按照官方文档配置
	routes //缩写，相当于routes:routes
})

//4.挂载路由

/*实例化VUE*/
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})

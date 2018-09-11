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

import Home from './components/home/Home.vue';
import Content from './components/goods/Content.vue';
import GoodsList from './components/goods/GoodsList.vue';
import Profiles from './components/profiles/Profiles.vue';
import GoodsAddPage from  './components/goods/GoodsAddPage.vue'

//2.配置路由

const routes = [

	{
	  path: '/',
    component: Home,
    children:[
      {
        name: 'type',
        path:'type/:typeName' ,
        component:GoodsList,
        //如果 props 是一个对象，它会被按原样设置为组件属性。当 props 是静态的时候有用。
        props: { newsletterPopup: false }
      },{
	      name:'find-key',
        path:':key',
        component:GoodsList,
      },
      {
        name: 'default-type',
        path: '/',
        component : GoodsList
      }
    ]
  },

  {
    name:'profile',
    path:'/user/my',
    component: Profiles,
    children :[
      {
        name:'personal-info',
        path:'info'
      }
    ]
  },
  {
    name : 'upload',
    path:'/goods/add',
    component : GoodsAddPage
  },
	{
	  name:'goods-content',
    path: '/content/:id/:sellerId',
    component: Content,
  },//动态路由
  { path : '*' , redirect:'/'}
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

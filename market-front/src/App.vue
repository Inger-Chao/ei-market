<template>
  <div id="app">

    <!-- 使用 router-link 组件来导航. -->
    <!-- 通过传入 `to` 属性指定链接. -->
    <!-- <router-link> 默认会被渲染成一个 `<a>` 标签 -->
<!--     <router-link to="/home">Home</router-link>
    <router-link to="/news">News</router-link>
    <router-link to="/userSignIn/userlist">User</router-link><hr> -->
    <!-- 路由出口 -->
    <!-- 路由匹配到的组件将渲染在这里 -->
    <router-view></router-view>
  </div>
</template>

<script>

/*
App.VUE中引用组件：1.引入组件 2.挂载组件
生命周期函数：组件挂载以及组件更新、销毁时触发的一系列方法
mounted():刷新时触发
*/

export default {
  name: 'app',
  // 业务逻辑里面定义的数据
  data () {
    //return里是定义数据的东西
    return {

    }
  },

}
</script>

<style lang="scss">
div{
  font-family: "PingFang SC"
}
</style>

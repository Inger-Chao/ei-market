<template>
  <!-- vue 中所有的组件要被根结点div包含起来 -->
  <div class="header">

    <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ff5555">
      <el-menu-item index="1"><a href="https://www.ele.me" target="_blank">登录</a> &nbsp;&nbsp;&nbsp;| &nbsp;&nbsp;&nbsp;
        <a href="https://www.ele.me" target="_blank">注册</a></el-menu-item>
      <el-menu-item index="2">
        <el-button type="danger" round>发布宝贝</el-button>
      </el-menu-item>
      <el-menu-item index="3"><a href="https://www.ele.me" target="_blank">我的闲置</a></el-menu-item>
      <el-menu-item index="4">消息中心</el-menu-item>

      <el-submenu index="5" style="float: right">
        <template slot="title">我的账户</template>
        <el-menu-item index="5-1">已购买的宝贝</el-menu-item>
        <el-menu-item index="5-2">购物车</el-menu-item>
        <el-menu-item index="5-3">收藏夹</el-menu-item>
      </el-submenu>
    </el-menu>

  </div>
</template>

<script>
  export default {
    data() {
      return {
        activeIndex: '0',
        activeIndex2: '0'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style lang="scss">

  .myli {
    float: right;
  }

  .el-menu--horizontal > .el-menu-item {
    float: right;
  }


</style>

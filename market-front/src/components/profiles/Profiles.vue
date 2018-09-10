<template>
  <div class="profile">

    <v-header></v-header>

    <el-tabs class="profile-tabs" tab-position="left">
      <el-tab-pane label="个人信息">
        <personal-info></personal-info>
      </el-tab-pane>
      <el-tab-pane label="购物车">
        <shop-cart></shop-cart>
      </el-tab-pane>
      <el-tab-pane label="我的订单">
        <trade></trade>
      </el-tab-pane>
      <el-tab-pane label="我的闲置">
        <idle></idle>
      </el-tab-pane>
    </el-tabs>

  </div>
</template>

<script>

  import Header from '../home/Header.vue'
  import PersonalInfo from "./PersonalInfo";
  import ShopCart from "./ShopCart";
  import Trade from "./Trade";
  import Idle from "./Idle";

  export default {

    name: "Profiles",

    components: {
      ShopCart,
      PersonalInfo,
      Trade,
      Idle,
      'v-header': Header
    },

    data() {
      return {}

    }
  }
</script>

<style>
  .el-tabs__item.is-active{
    color: #ff5555;
  }
  .el-tabs__item:hover{
    color: #ff5555;
  }
  .el-tabs__active-bar{
    background-color:#ff5555;
  }
  .profile-tabs{

  }
</style>

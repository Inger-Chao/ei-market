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

    <div @click="signOut">
      <el-tag style="float:right;user-select: none;cursor: pointer;" type="info">退出登陆</el-tag>
    </div>
  </div>
</template>

<script>

  import Header from '../home/Header.vue'
  import PersonalInfo from "./PersonalInfo";
  import ShopCart from "./ShopCart";
  import Trade from "./Trade";
  import Idle from "./Idle";
  import store from '../../vuex/store.js'

  export default {

    name: "Profiles",
    store,
    components: {
      ShopCart,
      PersonalInfo,
      Trade,
      Idle,
      'v-header': Header
    },

    data() {
      return {}
    },
    methods : {
      signOut(){
        console.log("我被点击了")
        this.$confirm('您确定要退出吗？').then(_ => {
          this.$store.commit('userSignOut')
          this.$router.push( { path : '/' })
        }).catch(_ => {})
      }
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

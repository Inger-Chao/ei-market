<!--suppress ALL -->
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

      <el-menu-item index="1">
        <el-button @click="handleSignBtnClick" style="color: #ff5555" round>
         {{this.$store.state.username}}
        </el-button>
      </el-menu-item>

      <el-menu-item index="2" @click="toGoodsAdd">
        <el-button type="danger" round>发布宝贝</el-button>
      </el-menu-item>

      <el-menu-item index="3" @click="toHome">
        <i class="el-icon-menu"></i>
        主页
      </el-menu-item>


    </el-menu>

    <!-- 调用 dialog 子组件 -->
    <user-dialog :visible.sync="visible"></user-dialog>

  </div>
</template>

<script>
  import UserDialog from "../User/UserDialog";
  import store from "../../vuex/store.js";

  export default {
    components: {UserDialog},

    data() {
      return {
        visible : false ,
        activeIndex: '0',
        activeIndex2: '0'
      };
    },
    //注册 vuex
    store,
    methods: {

      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },

      handleSignBtnClick(){

        if (!this.$store.state.id) {
          this.visible = true //通过data显示控制dialog
        } else {
          this.$router.push({ name : 'profile'})
        }
      },
      toHome(){
        this.$router.push({ path : '//'})
      },
      /* 发布商品按钮点击事件 */
      toGoodsAdd(){
        if (this.$store.state.hasSign ) {
          this.$router.push( { path : '/goods/add'})
        }else {
          alert('请先登陆')
        }
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

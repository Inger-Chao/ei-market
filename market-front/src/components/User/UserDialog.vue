<template>
  <div class="user-dialog">
    <div>
      <el-dialog class="dialog-detail"
                 v-bind:visible="visible"
                 width="45%"
                 :before-close="handleClose"
                 center>

      <div class="el-dialog__body">
          <!-- Dialog 主体部分 -->
          <el-tabs v-model="activeName" @tab-click="handleClick" align="center">
              <el-tab-pane label="登录" name="first">
                <!--监听子组件的关闭 Dialog 请求，并响应-->
                <user-sign-in ref="childSignIn" v-on:childClose="closeTheDialog" ></user-sign-in>
              </el-tab-pane>
              <el-tab-pane label="注册" name="second">
                <user-sign-up ref="childSignUp"></user-sign-up>
              </el-tab-pane>
          </el-tabs>
          </div>

          <span slot="footer" class="dialog-footer">
            <el-button @click=closeTheDialog>取 消</el-button>
            <el-button type="primary" @click="determine()">确 定</el-button>
          </span>

        </el-dialog>
    </div>
  </div>

</template>

<script>
  import UserSignIn from "./UserSignIn";
  import UserSignUp from "./UserSignUp";


  export default {

    name: "UserDialog",

    components: {UserSignUp, UserSignIn},

    /* 子组件向父组件传的值 */
    props: {
      visible: {
        type: Boolean,
        default: false,
      }
    },

    data() {
      return {
        activeName: 'first',
      }
    },

    methods: {

      closeTheDialog() {
        console.log("窗口要关闭了！")
        this.$emit('update:visible', false) //传递关闭事件
      },

      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },

      determine() {
        console.log("activeName 是什么东东？ " + this.activeName)
        if (this.activeName === 'first') {
          /* 调用子组件的登录方法 */
          this.$refs.childSignIn.SignIn()
        } else {
          // 调用子组件的注册方法
          this.$refs.childSignUp.SignUp()
        }
      },
      handleClick(tab, event) {
        console.log(tab, event);
      },

    },
    created() {
      this.activeName = 'first';
    }
  };
</script>

<style scoped>


</style>

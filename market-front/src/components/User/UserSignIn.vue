<template>
  <!-- 用户登录组件-->
    <div class="user-sign-in">

      <el-form :model="userSignIn">

        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="userSignIn.username" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input type="password" v-model="userSignIn.password" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item >
          <el-checkbox v-model="userSignIn.power" style="padding-left: 80px" >管理员</el-checkbox>
        </el-form-item>

      </el-form>

    </div>
</template>

<script>
  import Api from "../../server/api";
  import Axios from "axios";
  import store from "../../vuex/store";


  export default {
    name: "UserSignIn",
    store,
    data() {
      return {
        userSignIn:{
          username: '',
          password: '' ,
          power: false
        },
        formLabelWidth: '80px',
        isSuccess:0
      }
    },

    methods:{

      SignIn(){

        var self = this;
        if (this.userSignIn.username.empty || this.userSignIn.password.empty){
          alert("请输入完整信息！")
        }

        Axios.post(Api.userSignIn(this.userSignIn.username, this.userSignIn.password,
          (this.userSignIn.power) ? 1 : 0 ),
          {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            }
          }
        ).then(function (response) {
          console.log(response)
          self.isSuccess = response.data.status
          if ( self.isSuccess === 1){
            self.$notify({title : '登陆成功'})
            self.InitCurrentUserInfo()

          }else{
            alert("用户名或密码错误或权限不匹配")
          }
        }).catch(function (error) {
          console.log(error);
        })
      },

      InitCurrentUserInfo(){
        console.log("我走到这里拉")
        var self = this;
        Axios.get(Api.getUserInfo(self.userSignIn.username), {
          headers: {
            'Content-Type': 'application/x-www-userSignIn-urlencoded'
          }
        })
          .then(function (response) {
            console.log(response.data.data)

            /* 触发 store 中的初始化方法
            initCurrent (current, id, username, password, phone, qq, createTime, goodsNum, coin, credit)
             */

            self.$store.commit({

              type: 'initCurrent',

              id: response.data.data.id,
              sbname : response.data.data.username,
              password : response.data.data.password,
              phone: response.data.data.phone,
              qq: response.data.data.qq,
              createTime: response.data.data.createTime,
              goodsNum: response.data.data.goodsNum,
              coin: response.data.data.coin,
              credit: response.data.data.credit,
              hasSign : true

            })

            self.$emit('childClose') //向父组件请求关闭Dialog
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    }
  }
</script>

<style scoped>

</style>

<template>
	<!-- vue 中所有的组件要被根结点div包含起来 -->
  <!--用户注册组件-->
	<div id="user-sign-up">

    <el-form :model="user">

      <el-form-item label="用户名" :label-width="formLabelWidth">
        <el-input v-model="user.username" auto-complete="off"></el-input>
      </el-form-item>

      <el-form-item label="密码" :label-width="formLabelWidth">
        <el-input type="password" v-model="user.password" auto-complete="off"></el-input>
      </el-form-item>

      <el-form-item label="确认密码" :label-width="formLabelWidth">
        <el-input type="password" v-model="user.confirm" auto-complete="off"></el-input>
      </el-form-item>

      <el-form-item label="联系方式" :label-width="formLabelWidth">
        <el-input v-model="user.phone"></el-input>
      </el-form-item>

      <el-form-item>
        <el-checkbox v-model="user.power" style="padding-left: 80px">管理员</el-checkbox>
      </el-form-item>

    </el-form>
	</div>
</template>

<script>
  import Api from "../../server/api.js";
  import Axios from "axios";

  export default {
    name: "UserSignUp",
    data(){
      return{
        user:{
          username: '',
          password: '' ,
          confirm: '',
          phone: '',
          power: 0
        },
        formLabelWidth: '80px',
        isSuccess:0
      }
    },
    methods:{
      SignUp(){
        var self = this;
        if (this.user.username.empty || this.user.password.empty ||
          this.user.confirm.empty || this.user.phone.empty){
          alert("每一项都不能为空！")
        }else if (this.user.password.length < 6 || this.user.password.length > 16 ) {
          alert("密码必须在 6 - 16 个字符之间")
        }else if (this.user.confirm !== this.user.password){
          alert("确认密码必须与密码一致")
        }else{
          Axios.post(Api.userSignUp(this.user.username,this.user.password,
            this.user.phone,this.user.power ? 1 : 0),
            {
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              }
            }
          ).then(function (response) {
            console.log(response)
            self.isSuccess = response.data.status
            if ( self.isSuccess === 1){
              alert("注册成功，请登陆")
            }else{
              alert("注册失败，该用户名已被占用")
            }
          }).catch(function (error) {
            console.log(error);
          })
        }

      }
    }
  }
</script>

<style scoped>

</style>


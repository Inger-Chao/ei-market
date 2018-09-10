<template>
  <div class="personal-info">

    <el-form size="medium" :model="userInfo" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

      <el-form-item label="用户名" prop="name">
        <el-input v-model="userInfo.name" :disabled="true"></el-input>
      </el-form-item>

      <el-form-item label="联系方式" prop="phone">
        <el-input v-model="userInfo.phone"></el-input>
      </el-form-item>

      <el-form-item label="qq">
        <el-input v-model="userInfo.qq"></el-input>
      </el-form-item>

      <el-form-item label="创建时间">
        <el-input v-model="userInfo.createTime" :disabled="true"></el-input>
      </el-form-item>

      <el-form-item label="账户余额">
        <el-input v-model="userInfo.coin" :disabled="true"></el-input>
        <el-button @click="coinDialogVisible = true">充值</el-button>
      </el-form-item>

      <el-form-item label="信誉度">
        <el-input v-model="userInfo.credit" :disabled="true"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitUserForm()">确认修改</el-button>
        <el-button @click="pwdDialogVisible = true">修改密码</el-button>
      </el-form-item>
    </el-form>

    <el-dialog
      title="账户充值"
      :visible.sync="coinDialogVisible"
      width="30%"
      :before-close="handleClose"
      center>
      <span><el-input v-model="charge" class="input-size"></el-input></span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="coinDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="recharge">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="修改密码"
      :visible.sync="pwdDialogVisible"
      width="30%"
      :before-close="handleClose"
      center>

      <el-form>
        <el-form-item label="原始密码" :label-width="formLabelWidth">
          <el-input v-model="inputPwd" auto-complete="off" class="input-size"></el-input>
        </el-form-item>
        <el-form-item label="新密码" :label-width="formLabelWidth">
          <el-input v-model="newPwd" auto-complete="off" class="input-size"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="pwdDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updatePwd">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>

  import store from '../../vuex/store.js'
  import Api from "../../server/api";
  import axios from 'axios'

  export default {
    name: "PersonalInfo",
    store,
    data() {
      return {
        charge : 0,
        inputPwd : '',
        newPwd : '',
        //input label width
        formLabelWidth : '120px',
        userInfo: {
          name: this.$store.state.username,
          password : this.$store.state.password,
          phone: this.$store.state.phone,
          qq: this.$store.state.qq,
          createTime: this.$store.state.createTime,
          coin: this.$store.state.coin,
          credit: this.$store.state.credit
        },
        rules: {
          name: [
            { required: true, message: '请输入活动名称', trigger: 'blur' },
          ],
          phone: [
            { required: true, message: '请输入联系方式', trigger: 'blue'}
          ]
        },
        coinDialogVisible: false,
        pwdDialogVisible : false
      }
    },

    methods : {
      submitUserForm(){
        var self = this
        var params = new URLSearchParams();
        params.append('username',self.$store.state.username)
        params.append('phone',self.userInfo.phone)
        params.append('qq',self.userInfo.qq)
        params.append('coin',self.userInfo.coin)
        console.log(self.userInfo.name + self.userInfo.password + self.userInfo.coin)
        axios({
          method : 'post',
          url : Api.userUpdateInfo(),
          headers :  {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          data : params
        }).then(function (response) {
          console.log(response)
          if (response.data.status === 1){
            /* 改变 vuex 中用户的数据 */
            self.$store.commit({
              type: 'updateUserInfo',
              phone: self.userInfo.phone,
              qq: self.userInfo.qq,
              coin: self.userInfo.coin
            })

            console.log("username in update user info" + self.userInfo.name)

            const h = self.$createElement;
            self.$notify({
              title: '修改成功',
              message: h('i', { style: 'color: teal'},  '信息已更新到数据库')
            })

          }

        }).catch(function (error) {
          console.log(error)
        })
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      /* 充值 */
      recharge(){
        this.userInfo.coin = this.charge + this.userInfo.coin
        alert('充值成功，请点击确认修改按钮')
        this.coinDialogVisible = false
      },
      updatePwd(){
        console.log(this.inputPwd + "真正的密码是" + this.$store.state.password)
        console.log('username in userinfo ' + this.userInfo.name)

        if (this.inputPwd !== this.$store.state.password){
          alert('原始密码错误，无法修改')
        }else if (this.newPwd === this.inputPwd) {
          alert('新密码不能与旧密码相同')
        }else {
          var self = this;
          console.log('username in userinfo ' + this.userInfo.name)
          axios({
            method: 'post',
            url : Api.userChangePwd(self.userInfo.name, self.inputPwd, self.newPwd)
          }).then(function (response) {
            console.log(response)
            if (response.data.status === 1) {
              //更新 store 中的密码为新输入的密码
              self.$store.commit({
                type : 'updatePassword',
                password : self.newPwd
              })
              alert('密码修改成功')
              self.pwdDialogVisible = false
            }
          }).catch(function (error) {
            console.log(error)
          })
        }
      }
    }
  }
</script>

<style scoped>

  .demo-ruleForm{
    color: #ff5555;
  }


  .input-size{
    width: 150px;
  }


</style>

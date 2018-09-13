<template>

  <div class="goods-comments">

    <el-table
      :data="comments"
      stripe
      style="width: 100%">
      <el-table-column
        prop="createTime"
        label="日期"
        width="180">
      </el-table-column>
      <el-table-column
        prop="userId"
        label="用户id"
        width="180">
      </el-table-column>
      <el-table-column
        prop="comments"
        label="评论">
      </el-table-column>
    </el-table>

    <div style="margin-left: 20%; margin-top: 10px;">
      <el-input v-model="input" placeholder="请输入评论" style="width: 50%;"></el-input>
      <el-button @click="addComment" type="danger" round>评论</el-button>
    </div>


  </div>

</template>

<script>

  import axios from 'axios';
  import store from '../../vuex/store.js'
  import Api from "../../server/api";

    export default {
        name: "Comments",
      store,
      data(){
          return{
            input : '',
            comments:[]
          }
      },
      methods: {
          InitCommentsList(){
            var self = this;
            axios.get(Api.getGoodsComments(self.$parent.goods.id), {
                headers: {
                  'Content-Type': 'application/x-www-userSignIn-urlencoded',
                  'X-Requested-With': 'XMLHttpRequest'
                }
              }
            ).then(function (response) {
              self.comments = response.data.data
              console.log(response)
            })
              .catch(function (error) {
                console.log(error)
              })
          },

        addComment(){
            var self = this;

            if (self.$store.state.id){
              if (self.$parent.goods.id === self.$store.state.id){
                alert('不可以评论自己的商品')
              } else {
                if (self.input === ''){
                  alert('评论不可为空')
                } else {
                  axios.post(Api.addComment(self.$store.state.id,self.$parent.goods.id,self.input),{
                    headers: {
                      'Content-Type': 'application/x-www-form-urlencoded'
                    }
                  }).then(function (response) {
                    console.log(response)
                    self.input = ''
                    self.InitCommentsList()
                  }).catch(function (error) {
                    console.log(error)
                  })
                }
              }
            } else {
              alert('请先登陆')
            }


        }

      },
      mounted(){
          this.InitCommentsList()
      }
    }
</script>

<style scoped>
.goods-comments{
  margin-right: 10%;
  margin-left: 10%;
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>

<template>
  <div class="idle-list">
    <el-table
      :data="idleGoods"
      style="width: 100%">

      <el-table-column
        prop="name"
        label="商品名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="price"
        label="价格"
        width="180">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleIdleStatus(scope.$index, scope.row)">{{scope.row.status ? '下架' : '上架' }}</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleIdleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>

  import axios from 'axios';
  import store from '../../vuex/store.js';
  import Api from "../../server/api";

    export default {
        name: "Idle",
      store,
      data(){
          return{
            idleGoods : []
          }
      },
      methods : {

          InitIdleList(){
            var self = this;
            axios.get(Api.getIdleList(self.$store.state.id),{
              headers: {
                'Content-Type': 'application/x-www-userSignIn-urlencoded',
                'X-Requested-With':'XMLHttpRequest'
              }
            }).then(function (response) {
              self.idleGoods = response.data.data
              console.log(response)
            }).catch(function (error) {
              console.log(error)
            })
          },

        handleIdleStatus(index, row){
            var self = this;
          console.log(index, row)
          axios.post(Api.changeGoodsStatus(row.id), {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            }
          }).then(function (response) {
            console.log(response)
            if (response.data.status === 1){
              const h = self.$createElement;
              self.$notify({
                message: h('i', { style: 'color: teal'}, '商品状态已修改')
              });
              self.InitIdleList()
            }
          }).catch(function (error) {
            console.log(error)
          })
        },
        handleIdleDelete(index, row){
            var self = this
          axios.post(Api.deleteGoods(row.id),{
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            }
          }).then(function (response) {
            console.log(response)
            if (response.data.status === 1){
              const h = self.$createElement;
              self.$notify({
                message: h('i', { style: 'color: teal'}, '商品已删除')
              });
              self.InitIdleList()
            }
          }).catch(function (error) {
            console.log(error)
          })
        }
      },
      mounted() {
          this.InitIdleList()
      }
    }
</script>

<style scoped>

</style>

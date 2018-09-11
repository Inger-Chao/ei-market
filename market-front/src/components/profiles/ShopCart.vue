<template>

  <el-table
    :data="cartsGoods"
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
          @click="handleBtnBuy(scope.$index, scope.row)">购买</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDeleteCart(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

</template>

<script>

  import store from '../../vuex/store.js'
  import axios from 'axios'
  import Api from "../../server/api";

  export default {
    name: "ShopCart",
    store,
    data() {
      return {
        cartsGoods : []
      }
    },
    methods : {
      InitCartList(){
        var self = this
        axios.get(Api.getCartList(self.$store.state.id),{
          headers: {
            'Content-Type': 'application/x-www-userSignIn-urlencoded',
            'X-Requested-With':'XMLHttpRequest'
          }
        }).then(function (response) {
          self.cartsGoods = response.data.data
          console.log(response.data.data)
        }).catch(function (error) {
          console.log(error)
        })
      },
      /* 从购物车买东西 直接买，然后删除购物车表里的这一项 */
      handleBtnBuy(index, row){
        var self = this;
        if (self.$store.state.coin < row.price){
          alert('余额不足，请充值')
        } else {
          axios.post(Api.addTrade(row.userId,self.$store.state.id,row.id),{
            header:{
              'Content-Type': 'application/x-www-form-urlencoded'
            }
          })
            .then(function (response) {
              console.log(response)
              if (response.data.status === 1) {
                const h = self.$createElement;
                self.$notify({
                  title : '购买成功',
                  message: h('i', { style: 'color: teal'}, '等待商家发货')
                });
                self.handleDeleteCart(index,row)
                self.InitCartList()
              }
            })
            .catch(function (error) {
              console.log(error)
            })
        }
      },
      handleDeleteCart(index, row){
        var self = this;
        console.log(index, row);
        axios.post(Api.deleteCart(self.$store.state.id,row.id),{
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }}).then(function (response) {
            console.log(response)
            if (response.data.status === 1) {
              alert("购物车里已清除此商品")
              self.InitCartList()
            }
        }).catch(function (error) {
          console.log(error)
        })
      }
    },
    mounted(){
      this.InitCartList()
    }
  }
</script>

<style scoped>

</style>

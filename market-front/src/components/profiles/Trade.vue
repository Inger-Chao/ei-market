<template>
  <div class="profile-trade-list">

    <el-tabs v-model="activeName">
      <el-tab-pane label="未发货" name="first">

        <el-table
          :data="unSendGoods"
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
                type="danger"
                @click="recallTheTrade(scope.$index, scope.row)">取消订单</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>

      <el-tab-pane label="已发货" name="second">
        <el-table
          :data="unReceiveGoods"
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
                @click="confirmReceive(scope.$index, scope.row)">确认收货</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>

      <el-tab-pane label="未评价" name="third">
        <el-table
          :data="unRateGoods"
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
              <div class="block">
                <el-rate
                  v-model="credit"
                  :colors="['#99A9BF', '#F7BA2A', '#FF9900']">
                </el-rate>
              </div>
              <el-button
                size="mini"
                @click="rateSeller(scope.$index, scope.row)">评价</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="已完成" name="fourth">
        <el-table
          :data="finishGoods"
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
        </el-table>
      </el-tab-pane>
    </el-tabs>

  </div>
</template>

<script>

  import store from '../../vuex/store.js';
  import axios from 'axios';
  import Api from "../../server/api";

  export default {
    name: "Trade",
    store,
    data() {

      return {
        activeName: 'fourth',
        unSendGoods: [],
        unReceiveGoods: [],
        unRateGoods: [],
        finishGoods: [],
        credit : null,
        UNSEND : 2,
        UNRECEIVE : 3,
        UNRATE : 4,
        FINISH : 5
      }
    },
    methods: {
      /* 初始化每个子标签的信息 */
      InitTradeList(status) {
        var self = this;
        console.log(self.$store.state.id)
        axios.get(Api.getTradeListByStatus(self.$store.state.id,status),{
          headers: {
            'Content-Type': 'application/x-www-userSignIn-urlencoded',
            'X-Requested-With':'XMLHttpRequest'
          }
        }).then(function (response) {
          console.log(response)
          switch (status) {
            case 2:
              self.unSendGoods = response.data.data
                  break;
            case 3:
              self.unReceiveGoods = response.data.data
                  break;
            case 4:
              self.unRateGoods = response.data.data
                  break;
            case 5 :
              self.finishGoods = response.data.data
                  break;
            default:break;
          }
        }).catch(function (error) {
          console.log(error)
        })
      },

      /* 取消订单点击事件 */
      recallTheTrade(index, row){
        var self = this;
        axios.post(Api.dropTrade(row.id),{
          header:{
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        }).then(function (response) {
          console.log(response)
          if (response.data.status === 1) {
            const h = self.$createElement;
            self.$notify({
              message: h('i', { style: 'color: teal'}, '订单已取消')
            });
            //刷新未发货订单列表
            self.InitTradeList(2)
          }
        })
      },

      /* 确认收货按钮点击事件 */
      confirmReceive(index,row){
        console.log(index,row)
        var self = this;
        axios.post(Api.changeTradeStatus(row.id, self.UNRATE),{
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        }).then(function (response) {
          console.log(response)
          if (response.data.status === 1){
            const h = self.$createElement;
            self.$notify({
              title : '确认收货成功',
              message: h('i', { style: 'color: teal'}, '金额将直接充入卖家账户')
            });
            self.InitTradeList(3)
            self.InitTradeList(4)
          }
        }).catch(function (error) {
          console.log(error)
        })
      },

      /* 评价卖家 */
      rateSeller(index,row){
        console.log(index,row)
        var self = this;
        console.log("rate : " + self.credit)
         /* 提交卖家信誉度 */
        axios({
          method: 'post',
          url: Api.updateUserCredit(row.userId,self.credit),
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
        }).then(function (response) {
          console.log(response)
          if (response.data.status === 1){
            const h = self.$createElement;
            self.$notify({
              title : '评价成功',
              message: h('i', { style: 'color: teal'}, '卖家信用度已更新')
            })
            self.InitTradeList(4)
          }
        }).catch(function (error) {
          console.log(error)
        })

        /* 修改当前商品的status 为已完成 */
        axios.post(Api.changeTradeStatus(row.id, self.FINISH),{
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
          .then(function (response) {
          console.log(response)
          if (response.data.status === 1){
            self.InitTradeList(self.FINISH)
          }
        }).catch(function (error) {
          console.log(error)
        })
      }
    },
    mounted() {
      for (var i = 2; i <= 5; i++){
        this.InitTradeList(i)
      }
    }
  }
</script>

<style scoped>

</style>

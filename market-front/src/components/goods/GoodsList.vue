<template>
	<div class="goodsList" >

      <el-row>
        <el-col
          :span="4"
          v-for="(item, index) in goodsArray"
          :key="item.id"
          :offset="index%4 > 0 ? 1 : 3">

          <div class="goodsItem"  @click="goContent(item.id, item.userId)">

            <el-card class="goods-item">

              <img v-bind:src="'/dist/img/' + item.imgUrl " class="image"/>
              <div style="padding: 14px;">
						  <span>{{item.name}}</span>
                <div class="bottom clearfix">
                  <time class="time" v-model="goodsArray[index].createTime">
                    {{ item.createTime }}
                  </time>

                  <el-button type="text" class="button" v-model="goodsArray[index].price">
                    {{item.price}}
                  </el-button>
                </div>
              </div>

            </el-card>

            <span>{{resultMessage}}</span>

          </div>
        </el-col>
      </el-row>

    <el-pagination
      @current-change="handleCurrentChange"
      align="center"
      background
      layout="prev, pager, next"
      :current-page="currentPage"
      :total="1000">
    </el-pagination>

  </div>
</template>

<script>
  import Axios from 'axios'
  import Api from '../../server/api.js'

  export default {
    data() {
      return {
        goodsArray : [],
        //当前 GoodsList 依靠什么查询 0：为all 1：type 2：key
        selectBy : 0 ,
        typeName : 'all',
        key : 'default',
        currentPage : 1,
        //查询使用的页码
        page: 0,
        //展示查询结果的字符
        resultMessage : ''
      }
    },
    watch: {
      // 监测路由变化,只要变化了就调用获取路由参数方法将数据存储本组件即可
      '$route': 'getParams',

      //typeName 参数值改变时刷新页面信息
      'typeName' : 'setGoodsList',

      //key 值改变时刷新 router-view 信息
      'key' : 'setGoodsListByKey',

    },
    methods: {

      //页码变更
      handleCurrentChange: function(val) {
        this.currentPage = val
        this.page = val - 1
        console.log("当前页数" + this.currentPage)
        console.log("查询使用的页数"  + this.page)
        console.log("this.selectBy " + this.selectBy)
        if (this.selectBy === 0){
          this.InitGoodsList()
        } else if (this.selectBy === 1) {
          this.setGoodsList(this.typeName)
        } else if( this.selectBy === 2){
          this.setGoodsListByKey(this.key)
        }
      },


      getParams() {
        console.log("getParams()被调用了")
        var self = this;
        //初始化组件typeName参数
        if (this.$route.name === 'type'){
          let type = this.$route.params.typeName
          console.log(this.$route.params.typeName)
          console.log(type)
          self.typeName = type
        }

        // 改变keyValue 的值
        if (this.$route.name === 'find-key') {
          console.log("search bar 传过来的参数" +this.$route.params.key)
          self.key = this.$route.params.key
          console.log(" Goods list 中的key值" + self.key)
        }
      },

      setGoodsList(typeName) {
        var self = this;
        if (typeName !== 'all') {
          self.getGoodsListByType(typeName)
        } else {
          self.InitGoodsList()
        }
      },

      setGoodsListByKey(key){
        var self = this;
        if (key !== 'default'){
          self.selectBy = 2
          Axios.get(Api.getGoodsListByKey(key,self.page),{
            headers: {
              'Content-Type': 'application/x-www-userSignIn-urlencoded',
              'X-Requested-With':'XMLHttpRequest'
            }
          })
            .then(function (response) {
              self.goodsArray = response.data.data
              console.log(response)
            })
            .catch(function (error) {
              self.resultMessage = '没有找到任何东西'
              console.log(error)
            })
        } else{
          self.InitGoodsList()
        }

      },

      InitGoodsList() {
        var self = this;
        self.selectBy = 0
        Axios.get(Api.goodsList(self.page), {
          headers: {
            'Content-Type': 'application/x-www-userSignIn-urlencoded',
            'X-Requested-With':'XMLHttpRequest'
          }
        })
          .then(function (response) {
            self.goodsArray = []
            self.goodsArray = response.data.data
            console.log(response)
          })
          .catch(function (error) {
            self.resultMessage = '没有找到任何东西'
            console.log(error);
          });
      },
      // 获取动态路由传过来的 type，通过类型查找商品列表的方法
      getGoodsListByType(typeName){
        var self = this;
        self.selectBy = 1
        Axios.get(Api.getGoodsByType(typeName,self.page),{
          headers: {
            'Content-Type': 'application/x-www-userSignIn-urlencoded',
            'X-Requested-With':'XMLHttpRequest'
          }
        })
          .then(function (response) {
            self.goodsArray = response.data.data
            for (var i in self.goodsArray){
              console.log(self.goodsArray[i].name)
            }
          })
          .catch(function (error) {
            self.resultMessage = '没有找到任何东西'
            console.log(error)
          })
      },
      goContent(goodsId,userId){

        this.$router.push({ name : 'goods-content' , params : { id : goodsId ,sellerId : userId}})

      }
    },
    mounted() {
      //初始化商品列表，mounted 方法中的 goodsArray 元素一定是空的
      this.setGoodsList(this.typeName)
    },
    /*Update中测试是否拿到数据*/
    updated(){

/*      for (var i in this.goodsArray){
        console.log("update方法调用并输出了" +  this.goodsArray[i].name)
      }*/

    },created(){

    }

  }
</script>

<style>
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 200px;
    height: 200px;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .goods-item{
    cursor: pointer; /*鼠标悬停样式*/
  }
</style>

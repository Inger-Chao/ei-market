<template>
	<div class="goodsItem" >
		<el-row>
			<el-col
        :span="4"
        v-for="(item, index) in goodsArray"
        :key="item.id"
        :offset="index%4 > 0 ? 1 : 3">
				<el-card class="goods-item">
					<img src="../../assets/logo.png" class="image">
					<div style="padding: 14px;">
						<span v-model="goodsArray[index].name">
              {{item.name}}
            </span>
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
			</el-col>
		</el-row>
	</div>
</template>

<script>
  import Axios from 'axios'
  import Api from '../../server/api.js'
  import {Vue} from "vue";

  export default {
    data() {
      return {
        goodsArray : [],
        page: 0
      }
    },

    methods: {
      InitGoodsList() {
        var self = this;
        Axios.get(Api.goodsList(self.page), {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'X-Requested-With':'XMLHttpRequest'
          }
        })
          .then(function (response) {
            self.goodsArray = response.data.data
            console.log(response)

          })
          .catch(function (error) {
            console.log(error);
          });
      }
    },
    mounted() {
      //初始化商品列表，mounted 方法中的 goodsArray 元素一定是空的
      this.InitGoodsList()
    },
    /*Update中测试是否拿到数据*/
    updated(){
      for (var i in this.goodsArray){
        console.log(this.goodsArray[i].name)
      }
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

  .goodsItem{
  }
</style>

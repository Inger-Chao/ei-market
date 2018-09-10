<template>
  <div class="catalog">
    <el-row :gutter="20">
      <el-col
        :span="15"
        :offset="3"
        style="height:70px;border:1px solid #c3c3c3;border-radius:10px;">
        <div
          class="grid-content bg-purple"
          style="padding: 17px;align:center">

            <div v-for="item in catalogs">
              <el-tag
                class="el-tag-type"
                slot="reference">
                <router-link
                  @click="test(item)"
                  :to="{
                    name:'type',
                    params:
                    {
                      typeName:item
                     }
                    }">
                {{item}}
                </router-link>
              </el-tag>
            </div>
          <el-tag class="el-tag-type">
            <router-link :to="{ name: 'type',params: {typeName : 'all' } }">查看全部</router-link>
          </el-tag>
        </div>
      </el-col>
      <el-col :span="3">
        <div class="release-goods">
          <img src="../../assets/camera.png" class="camera">
          <strong class="text-upload-goods"><br>&nbsp;&nbsp;发&nbsp;布&nbsp;闲&nbsp;置</strong><br>
          <u class="text-upload-description">闲置换钱&nbsp;快速出手</u>
        </div>
      </el-col>
    </el-row>
    <br>
    <hr><br>
    <!-- 路由出口，GoodsList更新之后会显示在这里 -->
    <router-view>
    </router-view>

  </div>

</template>

<script>
  import Axios from 'axios'
  import Api from '../../server/api.js'
  import GoodsList from '../goods/GoodsList.vue'

  export default {
    data() {
      return {
        catalogs: [],
        page : 0
      }
    },
    methods: {
      test(name) {
        alert(name)
        console.log(name)
      },
      InitData() {
        var self = this;
        Axios.get(Api.types(), {
          headers: {
            'Content-Type': 'application/x-www-userSignIn-urlencoded'
          }
        })
          .then(function (response) {
            self.catalogs = response.data.data;
            console.log(response)
          })
          .catch(function (error) {
            console.log(error);
          });
      },
    }, mounted() {
      this.InitData();
    },components:{
      GoodsList
    },
  }
</script>

<style lang="scss">

  .el-icon-circle-plus {
    color: #ff5555;
    padding: 10px;
    height: 30px;
    width: 30px;
  }

  .camera {
    height: 40px;
    width: 40px;
    padding-left: 10px;
    padding-top: 15px;
    padding-right: 5px;
    float: left;
  }

  .text-upload_goods {
    font-size: larger;
  }

  .text-upload-description {
    font-size: small;
  }

  .el-tag-type > > > div {
    position: absolute;
  }

  .el-tag-type {
    background-color: white;
    color: #ff5555;
    border: 1px solid;
    margin-left: 10px;
    margin-right: 10px;
    float: left;
    cursor: pointer; /*鼠标悬停样式*/
    user-select: none; //用户不可选中文字
    text-decoration-line: none;
  }
  .el-tag-type:hover{
    color: #ff5555;
  }
  .release-goods{
    border: 1px solid #c3c3c3;
    border-radius:10px;
    padding-bottom: 4px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    cursor: pointer;
  }

</style>

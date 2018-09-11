<template>
	<!-- vue 中所有的组件要被根结点div包含起来 -->
	<div id="Content">

    <v-header></v-header>


    <el-card class="goods-content-card">
      <div slot="header" class="clearfix">
        <span>{{goods.name}}</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="editTheGoodsInfo">编辑</el-button>
      </div>
      <div class="goods-content-main">
        <img v-bind:src="'/dist/img/' + goods.imgUrl" class="goods-content-main-image">
        <div class="goods-content-main-text">
          <span>商品价格 ： {{goods.price}}</span><br><br>
          <span>原价 ： {{goods.realPrice}}</span><br><br>
          <span>发布时间： {{goods.createTime}}</span><br><br>
          <span>下架时间 ： {{goods.endTime}}</span><br><br>
          <span>商品状态 ： {{goods.status ? '上架' : '下架'}}</span><br><br>
          <span>商品描述： {{goods.description}}</span><br><br>
          <span>卖家id ： {{seller.id}}</span><br><br>
          <span>卖家用户名 ： {{seller.username}}</span><br><br>
          <span>联系方式：{{seller.phone}}</span><br><br>
          <span>qq: {{seller.qq}}</span><br><br>
          <span>商家信誉度： {{seller.credit}}</span>
        </div>
      </div>

      <div style="padding: 14px;">
        <div class="bottom clearfix">
          <el-button @click="addToCart" type="danger" icon="el-icon-star-off" round>加入购物车</el-button>
          <el-button @click="buyTheGoods" type="danger" icon="el-icon-goods" round>点击购买</el-button>
        </div>
      </div>
    </el-card>

    <el-dialog
      title="修改商品信息"
      :visible.sync="editDialogVisible"
      width="30%"
      :before-close="handleClose">

      <div class="goods-change-form">

        <el-form :model="goods">

          <el-form-item label="商品名称" prop="name">
            <el-input v-model="goods.name"></el-input>
          </el-form-item>

          <el-form-item label="商品价格" prop="price">
            <el-input v-model="goods.price"></el-input>
          </el-form-item>

          <el-form-item label="原价" >
            <el-input v-model="goods.realPrice"></el-input>
          </el-form-item>

          <el-form-item label="下架时间" required>
            <el-col :span="11">
              <el-form-item prop="endTime">
                <el-date-picker
                  type="date"
                  placeholder="选择下架日期"
                  value-format="yyyy-MM-dd"
                  v-model="goods.endTime"
                  style="width: 100%;"></el-date-picker>
              </el-form-item>
            </el-col>
          </el-form-item>

          <!-- 采取先选趣图片 随后与商品的其他信息一起提交到后台的策略 -->
          <el-form-item label="商品图片" prop="resource">
            <el-upload
              class="upload-demo"
              drag
              accept="image/jpeg,image/png"
              action=""
              :multiple="false"
              :on-change="onImgUploadChange"
              :auto-upload="false">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
              <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过1MB</div>
            </el-upload>
          </el-form-item>

          <el-form-item label="商品描述" >
            <el-input type="textarea" v-model="goods.description"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitNewGoodsForm">修改</el-button>
          </el-form-item>
        </el-form>

      </div>
    </el-dialog>

  </div>
</template>

<script>

  import Header from '../home/Header.vue'
  import Api from "../../server/api";
  import Axios from 'axios'
  import store from '../../vuex/store.js'


	export default{
    components : { 'v-header' : Header},
    store,
		data(){
			return {
        editDialogVisible: false,
			  goods : {
			    id : this.$route.params.id,
          name : '' ,
          userId : this.$route.params.sellerId,
          typeId : 0,
          price : 0,
          realPrice : '',
          createTime : '',
          endTime : '',
          polishTime : '',
          description : '',
          imgUrl : '',
          status : false,
        },

        seller : {
			    id : this.$route.params.sellerId,
          username : '',
          phone : '',
          qq : '',
          credit : ''
        }

			}
		},
    methods:{
      InitGoodsContent(){
        var self = this;
        Axios.get(Api.getGoodsContent(self.goods.id), {
          headers: {
            'Content-Type': 'application/x-www-userSignIn-urlencoded',
            'X-Requested-With':'XMLHttpRequest'
          }
        }).then(function (response) {
          self.goods = response.data.data
          console.log(response.data.data)
          console.log(self.goods)
        }).catch(function (error) {
          console.log(error)
        })
      },
      InitSellerContent(){
        var self = this
        Axios.get(Api.getUserById(self.seller.id),{
          headers: {
            'Content-Type': 'application/x-www-userSignIn-urlencoded',
            'X-Requested-With':'XMLHttpRequest'
          }
        }).then(function (response) {
          self.seller = response.data.data
          console.log(response.data.data)
          console.log(self.seller)
        }).catch(function (error) {
          console.log(error)
        })
      },
      onImgUploadChange(file) {

        var self = this

        const isIMAGE = (file.raw.type === 'image/jpeg' || file.raw.type === 'image/png');
        const isLt1M = file.size / 1024 / 1024 < 1;

        if (!isIMAGE) {
          alert('只能上传jpg/png图片!');
          return false;
        }
        if (!isLt1M) {
          alert('上传文件大小不能超过 1MB!');
          return false;
        }
        var reader = new FileReader();
        reader.readAsDataURL(file.raw);
        self.goods.imgUrl = file.raw.name
      },
      editTheGoodsInfo(){
        if (this.$store.state.id === this.seller.id){
          this.editDialogVisible = true
        } else {
          alert('对不起，只有商品主人可以修改商品信息')
        }
      },

      addToCart(){

        if (this.$store.state.hasSign)  {

          var self = this;
          if (self.$store.state.id === self.goods.userId){
            alert("不可以把自己的商品加入购物车")
          }else {
            Axios.post(Api.addCart(self.$store.state.id,self.goods.id),{
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              }
            }).then(function (response) {
              const h = self.$createElement;
              self.$notify({
                title: '添加成功',
                message: h('i', { style: 'color: teal'}, '商品已添加到购物车')
              });
              console.log(response)
            }).catch(function (error) {
              console.log(error)
            })
          }
        }else {
          alert('请先登录')
        }
      },

      buyTheGoods(){
        if (this.$store.state.hasSign)  {
          var self = this;
          if (self.$store.state.id === self.goods.userId){
            alert('不可以购买自己的商品')
          } else if (self.goods.status) {
            if (self.$store.coin < self.goods.price){
              alert('余额不足，请先充值')
            } else {

              const h = self.$createElement;
              self.$msgbox({
                title: '确定要购买吗？',
                showCancelButton: true,
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                beforeClose: (action, instance, done) => {
                  if (action === 'confirm') {
                    instance.confirmButtonLoading = true;
                    instance.confirmButtonText = '执行中...';
                    self.buyAction()
                    setTimeout(() => {
                      done();
                      setTimeout(() => {
                        instance.confirmButtonLoading = false;
                      }, 300);
                    }, 3000);
                  } else {
                    done();
                  }
                }
              }).then(action => {
                self.$message({
                  type: 'info',
                  message: '购买成功'
                });
              });
          }
          }else{
            alert('不可以购买下架商品')
          }
        }else {
          alert('请先登录')
        }
      },
      buyAction(){
        var self = this;
        Axios.post(Api.addTrade(self.goods.userId,self.$store.state.id,self.goods.id),{
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
              self.InitGoodsContent()
            }
          })
          .catch(function (error) {
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
      submitNewGoodsForm(){
        var self = this
        console.log('sbtypeId' + self.goods.typeId)
        var params = new URLSearchParams();
        params.append('id',self.goods.id)
        params.append('name', self.goods.name)
        params.append('typeId', self.goods.typeId)
        params.append('userId', self.goods.userId)
        params.append('price', self.goods.price)
        params.append('realPrice', self.goods.realPrice)
        params.append('endTime', self.goods.endTime)
        params.append('imgUrl', self.goods.imgUrl)
        params.append('description', self.goods.description)
        Axios({
          method: 'post',
          url: Api.uploadGoods(),
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          data: params
        }).then(function (response) {
          console.log(response)
          if (response.data.status === 1) {
            self.$notify({
              title: '商品信息已修改'
            })
            self.editDialogVisible = false
          }
        }).catch(function (error) {
          console.log(error)
        })

      }
		},
    mounted(){

      this.InitGoodsContent()
      this.InitSellerContent()

		}
	}
</script>

<!-- scoped表示局部作用域 -->
<style lang="scss" scoped>
  .goods-content-card{
    margin-left: 10%;
    margin-right: 10%;
    margin-top: 5%;
  }

  .goods-content-main-image{
    float: left;
    width: 300px;
    height: 300px;
    margin-top: 3%;
    margin-bottom: 5%;
  }

  .goods-content-main-text{
    float: right;
    margin-right: 200px;
  }

</style>

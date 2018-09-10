<template>
    <div class="goods-add-form">

      <el-form :model="goods" :rules="rules" ref="goods" label-width="100px" class="demo-ruleForm" style="margin-top: 50px;">

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

        <el-form-item label="商品类型" prop="type">
          <el-select v-model="goods.type" placeholder="请选择">
            <el-option
              v-for="(item,index) in catalogs"
              :key="index"
              :label="index+1 + '：' + item"
              :value="index+1 + '：' + item ">
            </el-option>
          </el-select>
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
          <el-button type="primary" @click="submitGoodsForm">立即创建</el-button>
          <el-button @click="">重置</el-button>
        </el-form-item>
      </el-form>

    </div>
</template>

<script>
    import Api from "../../server/api";
    import Axios from 'axios';
    import store from '../../vuex/store'

    export default {
      name: "GoodsAddForm",
      store,
      data() {
        return {
          catalogs: [],
          goods: {
            name: '',
            price: '',
            realPrice: '',
            endTime: '',
            type: '',
            description: '',
            imgUrl: '',
            status: true,
          },
          rules: {
            name: [
              {required: true, message: '请输入商品名称'},
            ],
            price: [
              {required: true, message: '请输入价格'}
            ],
            endTime: [
              {type: 'string', required: true, message: '请选择下架日期', trigger: 'change'}
            ],
            type: [
              {type: 'string', required: true, message: '请至少选择一个类型', trigger: 'change'}
            ],
          }
        }
      },
      methods: {
        InitTypeData() {
          var self = this;
          Axios.get(Api.types(), {
            headers: {
              'Content-Type': 'application/x-www-userSignIn-urlencoded'
            }
          })
            .then(function (response) {
              self.catalogs = response.data.data;
              console.log(response)
              console.log(self.catalogs)
            })
            .catch(function (error) {
              console.log(error);
            });
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

        submitGoodsForm() {
          var self = this
          var params = new URLSearchParams();
          params.append('name', self.goods.name)
          params.append('typeId', self.goods.type.split('：')[0])
          params.append('userId', self.$store.state.id)
          params.append('price', self.goods.price)
          params.append('realPrice', self.goods.realPrice)
          params.append('endTime', self.goods.endTime)
          params.append('imgUrl', self.goods.imgUrl)
          params.append('description', self.goods.description)
          console.log("物品要上传的信息 ： " + self.goods.name +"sb type : " +  self.goods.type.split('：')[0] +
           "sb id :"  + self.$store.state.id + "sb time" + self.goods.endTime)
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
                title: '商品发布成功'
              })
            }
          }).catch(function (error) {
            console.log(error)
          })
        },
      },
      mounted() {
        this.InitTypeData()
      },
    }
</script>

<style scoped>

  .el-input__inner{
    width: 50%;
  }

  .el-textarea__inner{
    width: 50%;
  }

  .goods-add-form{
    margin-left: 20%;
    margin-right: 20%;
}

</style>

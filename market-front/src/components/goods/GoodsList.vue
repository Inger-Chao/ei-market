<template>
  <div class="goodsList">
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync="currentPage"
      :page-size="100"
      layout="prev, pager, next, jumper"
      :total="1000">

    </el-pagination>
  </div>

</template>

<script>
  import GoodsItem from './GoodsItem.vue'

  export default {
    methods: {
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      }
    },
    data() {
      return {
        currentPage: 1
      }
    },
    components: {
      'goodsItem' : GoodsItem
    }
  }
</script>

<style scoped>

</style>

<template>
	<!-- vue 中所有的组件要被根结点div包含起来 -->
	<div class="home">

		<v-header></v-header>
		<h1 align="center" style="font-size: 50px;color: #333333">二手市场</h1>
		<v-searchbar></v-searchbar>
		<br><br>
		<v-catalog></v-catalog>
		<br>
		<hr><br>
		<v-goodsitem></v-goodsitem>

 	</div>
</template>

<script>
  import Header from './Header.vue'
  import SearchBar from './home/SearchBar.vue'
  import Catalog from './home/Catalog.vue'
  import GoodsItem from './goods/GoodsItem.vue'

  export default {
    data() {
      return {
        msg: 'Hello world from home！',
        title: '首页',

      }
    },
    methods: {},
    components: {
      'v-header': Header,
      'v-searchbar': SearchBar,
      'v-catalog': Catalog,
      'v-goodsitem': GoodsItem
    }, mounted() {

    }
	}

</script>
<!-- scoped表示局部作用域 -->

<style lang="scss" scoped>
hr{
	color:#c3c3c3;
}
</style>

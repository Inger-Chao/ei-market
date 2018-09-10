<template>
	<!-- vue 中所有的组件要被根结点div包含起来 -->
	<div class="home">

		<v-header></v-header>
		<h1 align="center" style="font-size: 50px;color: #333333">EI-Market</h1>
		<v-search-bar></v-search-bar>
		<br><br>
		<v-catalog></v-catalog>

 	</div>
</template>

<script>
  import Header from './Header.vue'
  import SearchBar from './SearchBar.vue'
  import Catalog from './Catalog.vue'
  import GoodsList from '../goods/GoodsList.vue'

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
      'v-search-bar': SearchBar,
      'v-catalog': Catalog,
      'v-goods-list': GoodsList
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

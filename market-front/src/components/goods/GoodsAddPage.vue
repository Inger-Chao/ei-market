<template>
    <div class="goods-add-page">
      <v-header></v-header>
      <goods-add-form></goods-add-form>
    </div>
</template>

<script>
    import Header from "../home/Header";
    import GoodsAddForm from "./GoodsAddForm";
    export default {
        name: "GoodsAddPage",
      components: {GoodsAddForm, 'v-header' : Header}
    }
</script>

<style scoped>

</style>

<template>
	<div class="searchBar" >
		<div class="demo-input-suffix" align="center">
			<span>
			<el-input
 			    placeholder="请输入你想要搜索的宝贝..."
		    	v-model="input_search"
		    	@keyup.enter.native="submit">
			</el-input>
		</span>
  		</div>
	</div>
</template>
<script>
  export default {
    data() {
      return {
        input_search: ''
      }
    }, methods: {
      submit() {
        alert("click " + this.input_search)
      }
    }
  }
</script>
<style lang="scss">

.el-input.is-active .el-input__inner, .el-input__inner:focus {
    border-color: #ff5555;

}
.el-input__inner{
	width:500px;
}

</style>

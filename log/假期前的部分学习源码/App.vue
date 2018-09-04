<!-- 根组件页面 -->
<template>
  <!-- VUE模板里所有的内容要被一个根节点包含起来 -->
  <div id="app">
    <img src="./assets/logo.png">

<!--  绑定属性的两种写法 -->  
<!--     <img v-bind:src="url" />
    <img :src="url" />  -->

    <h1>{{ msg }}</h1>
    <h2>Essential Links</h2>
      Hello vue！ This is root components.
      <!-- 获取对象里的数据{{obj.name}} -->
    <h3>{{obj.name}}</h3> 

    <hr>

<!-- v-bind:class的用法 -->
<div v-bind:class="{'red':flag,'blue':!flag}">
    <ul>
      <li v-for="item in list">
        {{item}}
      </li>
    </ul>
</div>

<!-- 使用v-bind:class让第一个元素变为蓝色 -->
    <ul>
      <li v-for="(item,key) in list" :class="{'blue':key== 0}">
        {{item}}
      </li>
    </ul>
<!--     <ul>
      <li v-for="(value,key) in contacts"> 
      {{ key }}: {{ value }} 
      </li>
    </ul> -->

    <div v-bind:title="title">Move to Here~</div>
    <!-- 另一种绑定数据的方法 -->
    <div v-text="title"></div>

    <!-- bind html:让代码可以被解析为html的方式 -->
    <div v-html="h"></div>

    <!-- bind:style动态设置div宽度 -->
    <div class="box" v-bind:style="{width:boxwidth+'px'}">Another color div</div>
  <hr>

  <h3>MVVM双向数据流动的学习</h3>
  {{msg}}<br>
  <input type="text" v-model='msg'>
  <button v-on:click="getMsg()">get msg</button>
  <button v-on:click="setMsg('after changed')">set msg</button>
  <br><br>
  <!-- ref相当于给input起名字：主要用于获取DOM结点 -->
  <input type="text" ref="userinfo" />
  <button v-on:click="getInputInfo()">get user info</button>
  <button ref="btn" @click="btnColorChange()">Change to red</button>

  <hr>

  <h3>执行方法的两种方法</h3>


  </div>
</template>
<!-- export：表示把app这个组件暴露出去 -->
<script>
  /* VUE : mvvm框架 双向数据流动只能在表单中实现*/
export default {
  name: 'app',
  // 业务逻辑里面定义的数据
  data () { 
    //return里是定义数据的东西
    return {
      msg: 'Welcome to Your Vue.js App',
      title: 'You little piggy',
      obj:{
        name: "Inger-Jo"
      },
      list:['Front','Back','DataBase'],

      contacts:[
        {
          "QQ": "396558528"
        },
        {
          "TEL": "15735659693"
        },
        {
          "SCHOOL" : "North University of China"
        },
       ],

       url:'./assets/logo.png',

       h:'<a href="https://github.com/Inger-Jo">Github</a> ',
       flag:true,
       boxwidth:300,
    }
  },methods:{
    /*放方法的地方*/
    getMsg(){
    
      //alert("方法可以执行啦！")
      alert(this.msg)

    },

    setMsg(str){
      alert(str);
      this.msg = str;
    },

    getInputInfo: function(){
      //获取ref定义的dom结点
      console.log(this.$refs.userinfo);
      alert(this.$refs.userinfo.value);
    },

    btnColorChange(){
      this.$refs.btn.style.color='red';
    }
  }
}
</script>

<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

.red{
  color: red
}
.blue{
  color:blue
}
.box{
  text-align:center;
  width:100px;
  height:50px;
  color:#cb21df;
}

a {
  color: #42b983;
}
</style>

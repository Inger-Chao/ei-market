import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

/* state 在 vuex 中用于存储数据，表示一个状态*/
var state = {
  id: '',
  username: '登录 | 注册',
  password: '',
  phone: '',
  qq: '',
  createTime: '',
  goodsNum: '',
  coin: '',
  credit: '',
  hasSign : false
}

/* mutations 中存放方法 用来改变 state 的数据*/
var mutations = {
  initCurrent (state, payload){
    console.log("init user info by store" + payload.sbname)
    state.id = payload.id
    state.username = payload.sbname
    state.password = payload.password
    state.phone = payload.phone
    state.qq = payload.qq
    state.createTime = payload.createTime
    state.goodsNum = payload.goodsNum
    state.coin = payload.coin
    state.credit = payload.credit
    state.hasSign = payload.hasSign
    console.log("username in state of store set is ok" + state.username )
  },
  updateUserInfo(state,payload){
    state.phone = payload.phone
    state.qq = payload.qq
    state.coin = payload.coin
  },
  updatePassword(state,payload){
    state.password  = payload.password
  },
  userSignOut() {
    console.log('走到vuex这里拉！')
    state.id = '',
      state.username = '登录 | 注册',
      state.password = '',
      state.phone = '',
      state.qq = '',
      state.createTime = '',
      state.goodsNum = '',
      state.coin = '',
      state.credit = '',
      state.hasSign = false
  }
}


// vuex
const store = new Vuex.Store({
  state,
  mutations: mutations
})

export default store;

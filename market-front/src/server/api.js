// 管理所有后台请求API

/* root url */

const root = 'http://localhost:8080/market';

const Api = {

  /* 获取类型目录 */
  'types': () => `${root}/types`,

  /* 商品列表， @page：页数 */
  'goodsList': (page) => `${root}/goods-list/${page}`,

  /* 根据类型获取商品列表 @type: 类型 */
  'getGoodsByType': (type,page) => `${root}/goods/${type}/${page}`,

  // 键入关键字从商品的名字和描述查找
  'getGoodsListByKey' : (key,page) => `${root}/search/${key}/${page}`,

  /* 用户注册 */
  'userSignUp':(username, password, power, phone) => `${root}/user/sign-up?username=${username}&password=${password}&power=${power}&phone=${phone}`,

  // 用户登录
  'userSignIn':(username,password,power) => `${root}/user/sign-in?username=${username}&password=${password}&power=${power}`,

  //获取用户信息
  'getUserInfo':(username) => `${root}/user/info/${username}`,

  //修改用户密码
  'userChangePwd':(username,oldpd,password) => `${root}/user/update/password?username=${username}&oldpd=${oldpd}&password=${password}`,

  'userUpdateInfo':() => `${root}/user/update`,

  'userDelete' : (username) => `${root}/user/delete/${username}`,

  'uploadGoods' :  () => `${root}/goods/add`

};
export default Api;

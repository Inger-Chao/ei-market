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

  /* 获取所有商品的总个数 */
  'getGoodsCount' : () => `${root}/goods/count/all`,

  /* 获取当前类型下的商品个数 @type：类型名 */
  'getTypeGoodsCount' : (type) => `${root}/count/${type}`,

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

  /* post: 用户修改个人信息 */
  'userUpdateInfo':() => `${root}/user/update/info`,

  /* 修改用户评分 @id: 用户id @credit：新评分*/
  'updateUserCredit' : (id,credit) => `${root}/user/update/credit/${id}/${credit}`,

  'userDelete' : (username) => `${root}/user/delete/${username}`,

  /* post ： 发布商品 */
  'uploadGoods' :  () => `${root}/goods/add`,

  /* get ：通过商品id获取商品的所有信息  @id ：商品id */
  'getGoodsContent' : (id) => `${root}/goods/content/${id}`,

  /* 通过用户 id 获取用户的所有信息 */
  'getUserById' : (id) => `${root}/user/id/${id}`,

  /* 获取用户购物车商品 */
  'getCartList' : (userid) => `${root}/cart-list/${userid}`,

  /* 添加商品到购物车 @userid: 用户ID @goodsid*/
  'addCart' : (userid, goodsid) => `${root}/cart/add/${userid}/${goodsid}`,

  /* 用户删除购物车商品 */
  'deleteCart' : (userid,goodsid) => `${root}/cart/delete/${userid}/${goodsid}`,

  /* 获取某用户的所有发布过的商品 */
  'getIdleList' : (userid) => `${root}/goods/user/${userid}`,

  /* 改变商品的状态 上架or下架 */
  'changeGoodsStatus' : (id) => `${root}/goods/change/status/${id}`,

  /* 删除商品条目(只能由商品的发布者在个人信息页面删除) */
  'deleteGoods' : (id) => `${root}/goods/delete/${id}`,

  /* 获取交易表中所有的所有商品信息，即用户买的所有商品 @buyerid: 买家id */
  'getOnesBoughtGoods' : (buyerid) => `${root}/trade-list/${buyerid}`,

  /* 获取用户某交易状态下载所有商品信息 @buyerid: 买家id @status:交易状态 */
  'getTradeListByStatus' : (buyerid,status) => `${root}/trade/status/${buyerid}/${status}`,

  /* 获取交易表中的商品的交易信息 @goodsid: 商品id */
  'getTradeInfoByGoodsId' : (goodsid) => `${root}/trade/info/${goodsid}`,

  /* 修改商品的交易状态 @goodsid : 商品id ,@status : 修改后的商品交易状态*/
  'changeTradeStatus' : (goodsid,status) => `${root}/trade/change/${goodsid}/${status}`,

  /* 取消订单，删除交易 : @goodsid : 商品id */
  'dropTrade' : (goodsid) => `${root}/trade/delete/${goodsid}`,

  /* 提交交易 @salerid: 卖家id @buyerid：买家id @goodsid：商品id*/
  'addTrade' : (salerid,buyerid,goodsid) => `${root}/trade/add/${salerid}/${buyerid}/${goodsid}`,

  'getGoodsComments' : (goodsid) => `${root}/comments/${goodsid}`,

  'addComment' : (userid,goodsid,comment) => `${root}/comments/add/${goodsid}/${userid}/${comment}`

};
export default Api;

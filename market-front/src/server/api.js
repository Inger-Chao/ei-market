// 管理所有后台请求API

/* root url */

const root = 'http://localhost:8080/market';

const Api = {

  /* 获取类型目录 */
  'types': () => `${root}/types`,

  /* 商品列表， @page：页数 */
  'goodsList': (page) => `${root}/goods-list/${page}`


};
export default Api;

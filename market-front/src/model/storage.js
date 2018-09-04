// 封装操作localStorage本地存储的方法，模块化的文件

var storage={
	set(key,value){
		localStorage.setItem(key,JSON.stringify(value));
	},
	get(key){
		return JSON.parse(localStorage.getItem(key));
	},remove(key){
		localStorage.removeItem(key);
	}
}

export default storage;//storage暴露出去
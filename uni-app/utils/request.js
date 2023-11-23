import {
	setTimeout
} from "timers"
const domain = require('./domain.js')

// #ifdef APP-PLUS
const URL = domain.url
// #endif
// #ifdef H5
const URL = '/dev'
// #endif
function request(val) {
	let {
		path,
		data,
		method,
		loading
	} = val
	let token = ''
	if (uni.getStorageSync('token')) {
		token = uni.getStorageSync('token')
	}

	return new Promise((resolve, reject) => {

		if (loading == undefined || loading == true) {
			if (method == "get" || method == "GET") {
				uni.showLoading({
					title: '努力加载中..'
				});
			} else {
				uni.showLoading({
					title: '努力加载中..',
					mask: true
				});
			}
		}
		uni.request({
			url: URL + path,
			method: method ? method : "POST",
			header: {
				Authorization: token
			},
			data,
			success: (res) => {
				if (res.data.code == 10006) {
					uni.showToast({
						icon: 'none',
						title: '未登录,即将跳转到登录页面!'
					})
					setTimeout(() => {
						uni.reLaunch({
							url: '/pages/login/index'
						})
					}, 1000)
					return
				}
				if (res.data.code == 200) {
					resolve(res.data)
				} else {
					console.log(res.data)
					console.log(path)
					console.log(data)
					uni.showToast({
						icon: 'none',
						title: res.data.msg
					})
					reject(res.data)
				}
			},
			fail: (res) => {
				reject(res)
			},
			complete: (res) => {
				uni.hideLoading();
			}
		})

	})
}
export default request

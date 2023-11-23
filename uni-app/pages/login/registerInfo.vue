<template>
	<view class="login">
		<app-head :show="true"></app-head>
		<view class="login-form">
			<view class="avatar-wrap">
				<view class="avatar" @click="selectAvator">
					<image class="avatar-images" v-if="images !==''" :src="images" mode=""></image>
					<image class="avatar-icon1" v-if="images ===''" src="../../static/login/camera.png" mode=""></image>
					<image class="avatar-icon2" v-if="images ===''" src="../../static/login/add-camera.png" mode="">
					</image>
				</view>
			</view>
			<view class="form-item">
				<text>昵称</text>
				<input type="text" v-model="user" placeholder="请输入">
			</view>
			<view class="form-item" @click="selectBirthday">
				<text>生日</text>
				<view class="form-item-text">{{ Birthday != '' ? Birthday: '请输入' }}</view>
				<view class="see-date"></view>
			</view>
			<view class="form-item" @click="selectCity">
				<text>所在城市</text>
				<view class="form-item-text">{{ city != '' ? city : '请输入' }}</view>
				<view class="see-loction"></view>
			</view>
			<view class="form-item">
				<text>登陆密码</text>
				<input type="text" v-model="pw" :password="pwState" placeholder="请输入">
				<view class="see-pw" @click="pwState = !pwState"></view>
			</view>
			<view class="form-item">
				<text>确认密码</text>
				<input type="text" v-model="confirmPw" :password="pwState" placeholder="请输入">
				<view class="see-pw" @click="pwState = !pwState"></view>
			</view>
			<view class="login-submit" @click="register">
				完成注册
			</view>
		</view>
		<view class="login-bottom" @click="toLogin">
			已有账号？立即登录
		</view>
		<!-- 日历 -->
		<uni-datetime-picker :hide-second="true" type="single" ref="time" @change="confirmBirthday" :clear-icon="false"
			:border="false" rangeSeparator="至" />
	</view>
</template>

<script>
	export default {
		data() {
			return {
				user: '',
				images: '',
				imagesUrl: '',
				calendarShow: false,
				Birthday: '',
				pw: '',
				confirmPw: '',
				pwState: true,
				city: '',
				lon: '',
				lat: '',
				cityCode: 0,
			}
		},
		onLoad(e) {

		},
		methods: {
			toLogin() {
				uni.reLaunch({
					url: '/pages/login/index?loginMode=PW'
				})
			},
			confirmBirthday(res) {
				this.Birthday = res
				this.$refs.time.close()
			},
			selectCity() {
			
				uni.getLocation({
					type:'gcj02',
					geocode: true, //设置该参数为true可直接获取经纬度及城市信息
					success: (res) => {
						console.log("获取位置成功")
					    console.log(res);
						this.city = res.address != undefined ? res.address.city : "定位失败"
						this.cityCode = res.address != undefined ? res.address.cityCode : "0"
						this.lon = res.longitude
						this.lat = res.latitude
					},
					fail: function(res) {
						console.log("获取位置失败")
						console.log(res)
						uni.showToast({
							title: '获取位置失败',
							icon: 'none'
						});
						this.city = '定位失败'
						this.cityCode = '0'
					}
				});
			},
			selectBirthday() {
				// this.calendarShow = true
				this.$refs.time.show()
			},
			selectAvator() {
				uni.chooseImage({
					count: 1,
					sourceType: ['album'], //从相册选择
					success: res => {
						this.images = res.tempFilePaths[0]
					}
				})
			},
			uploadImages() {
				uni.showLoading({
					title: '注册中',
					mask: true
				})
				uni.uploadFile({
					url: this.$domain + '/file/upload', //接口地址
					filePath: this.images,
					name: 'file',
					formData: {
						Authorization: ''
					},
					success: (res) => {
						this.imagesUrl = JSON.parse(res.data).data.url
						this.register()
					},
					fail: (res) => {
						uni.hideLoading()
						uni.showToast({
							title: '头像上传失败',
							icon: 'none'
						})
					}
				});
			},
			confirmRegister() {
				this.$request({
					path: '/token/register',
					data: {
						headPortrait: this.imagesUrl,
						phone: getApp().globalData.phone,
						code: this.code,
						nickName: this.user,
						birthday: this.Birthday,
						lat: this.lat,
						lon: this.lon,
						city: this.city,
						gender: getApp().globalData.sex,
						verificationCode: getApp().globalData.code,
						password: this.pw,
						truePassword: this.confirmPw,
						cityCode: this.cityCode
					}
				}).then(res => {
					uni.hideLoading();
					this.login();
					/* uni.showModal({
						title: '登录',
						content: '是否要自动登录',
						success: (res) => {
							if(res.confirm){
								this.login()
							}else{
								uni.reLaunch({
									url: '/pages/login/index'
								})
							}
						}
					}) */
				})
			},
			login() {
				this.$request({
					path: '/token/login',
					data: {
						username: getApp().globalData.phone,
						password: this.pw
					}
				}).then(res => {
					uni.setStorageSync('token', res.data.token)
					uni.setStorageSync('info', res.data.info)
					uni.reLaunch({
						url: '/pages/home/index'
					})
				})
			},
			registerInputErrTips() {
				const KeyData = [{
						key: 'images',
						err: '请选择头像'
					},
					{
						key: 'user',
						err: '请输入昵称'
					},
					{
						key: 'Birthday',
						err: '请输入生日'
					},
					{
						key: 'city',
						err: '请输入所在城市'
					},
					{
						key: 'pw',
						err: '请输入登陆密码'
					},
					{
						key: 'confirmPw',
						err: '请输入确认密码'
					},
				]

				return new Promise((resolve, reject) => {
					KeyData.some((res, index) => {
						if (this[res.key] === '') {
							uni.showToast({
								title: res.err,
								icon: 'none'
							})
							return true
						}
						if (index === KeyData.length - 1) {
							resolve(true)
						}
					})
				})
			},
			register() {
				this.registerInputErrTips().then(res => {
					if (this.imagesUrl != '') {
						this.confirmRegister()
					} else {
						this.uploadImages()
					}
				})

			},
		}
	}
</script>

<style lang="scss" scoped>
	/deep/.uni-date-editor--x {
		display: none;
	}

	.login {
		width: 100%;
		height: 100%;
		min-height: 100vh;
		background-image: url(@/static/login/login-background.png);
		background-size: contain;
		background-repeat: no-repeat;
		background-position: bottom;

		&-form {
			padding: 50rpx 76rpx 0;

			.avatar-wrap {
				width: 100%;
				margin-bottom: 100rpx;

				.avatar {
					width: 180rpx;
					height: 180rpx;
					background-color: #FAFAFA;
					border-radius: 100%;
					position: relative;
					display: flex;
					justify-content: center;
					align-items: center;
					margin: auto;

					.avatar-images {
						width: 180rpx;
						height: 180rpx;
						border-radius: 100%;
					}

					.avatar-icon1 {
						width: 86rpx;
						height: 84rpx;
					}

					.avatar-icon2 {
						width: 48rpx;
						height: 48rpx;
						position: absolute;
						bottom: -20rpx;
					}
				}
			}

			.form-item {
				width: 100%;
				height: 76rpx;
				border-radius: 36rpx;
				background-color: rgba(250, 250, 250, 1);
				padding: 0 20rpx 0 40rpx;
				box-sizing: border-box;
				color: rgba(78, 85, 136, 1);
				font-size: 28rpx;
				display: flex;
				align-items: center;
				margin-bottom: 32rpx;
				font-family: SourceHanSansCN-Bold;

				&>text {
					width: 112rpx;
				}

				&>input,
				.form-item-text {
					flex: 1;
					margin-left: 24rpx;
					color: grey;
					font-size: 28rpx;
					font-weight: 400;
					font-family: SourceHanSansCN-Regular;
				}

				.see-loction,
				.see-pw,
				.see-date {
					width: 28rpx;
					margin-left: 15rpx;
					margin-right: 32rpx;
					background-repeat: no-repeat;
					background-size: cover;
				}

				.see-loction {
					height: 28rpx;
					background-image: url(@/static/login/loction.png);
				}

				.see-pw {
					height: 16rpx;
					background-image: url(@/static/login/password.png);
				}

				.see-date {
					height: 28rpx;
					background-image: url(@/static/login/date.png);
				}
			}

			.login-submit {
				width: 100%;
				height: 80rpx;
				border: 2rpx solid rgba(78, 85, 136, 1);
				color: rgba(78, 85, 136, 1);
				font-size: 32rpx;
				text-align: center;
				line-height: 80rpx;
				border-radius: 36rpx;
				margin-top: 48rpx;
				font-family: SourceHanSansCN-Medium;
			}
		}

		&-bottom {
			margin-top: 100rpx;
			width: 100%;
			text-align: center;
			font-size: 28rpx;
			color: #656C97;
			font-family: SourceHanSansCN-Regular;
		}
	}
</style>

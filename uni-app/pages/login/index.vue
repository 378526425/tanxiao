<template>
	<view class="login">
		<app-head></app-head>
		<view class="login-form">
			<view class="form-item">
				{{ loginMode === 'PW' ? '账号' : '手机号' }}
				<input type="text" v-model="user" placeholder="请输入">
			</view>
			<view class="form-item" v-if="loginMode === 'PW'">
				密码
				<input :password="pwState"  type="text" v-model="pw" placeholder="请输入">
				<view class="see-pw" @click="showpw"></view>
			</view>
			
			<view class="form-item" v-if="loginMode === 'CODE'">
				验证码
				<input type="number" v-model="code" placeholder="请输入">
				<view class="getCode" @click="getCode" v-if="codeState">
					获取验证码
				</view>
				<view class="codeCountDown" v-else>
					{{ countDown }}秒后可重发
				</view>
			</view>
			<view class="login-submit" @click="login">
				立即登录
			</view>
		</view>
		<view class="login-bottom-wrap">
			<view class="login-bottom" @click="toRegister">
				新用户注册
			</view>
			<view class="login-bottom" @click="toNext">
				{{ loginMode === 'PW' ? '验证码登录' : '密码登录' }}
			</view>
			<view class="login-bottom" @click="toHome">
				游客访问
			</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				user: '',
				pw: '',
				pwState: true,
				loginMode: 'PW', //  PW 密码登录 CODE验证码登录
				code: '',
				codeState: true,
				countDown: 60
			}
		},
		watch:{
		},
		onLoad(e) {
			if(e.loginMode){
				this.loginMode = e.loginMode
			}
		},
		methods: {
			toHome(){
				uni.reLaunch({
					url: '/pages/home/index'
				})
			},
			toNext(){
				if(this.loginMode === 'PW'){
					uni.navigateTo({
						url: '/pages/login/index?loginMode=CODE'
					})
				}else if(this.loginMode === 'CODE'){
					uni.reLaunch({
						url: '/pages/login/index?loginMode=PW'
					})
				}
			},
			toRegister(){
				uni.navigateTo({
					url: '/pages/login/register'
				})
			},
			getCode(){
				if(this.user == ''){
					uni.showToast({
						title: '请输入手机号',
						icon: 'none'
					})
					return
				}
				this.$request({
					path: '/token/sendsms',
					data: {
						 messageType : 'LOGIN',
						 phone: this.user
					}
				}).then(res => {
					this.codeState = false
					setInterval(()=>{
						this.countDown--
						if(this.countDown < 2){
							this.codeState = true
						}
					},1000)
				})			
			},
			login(){
				if(this.loginMode === 'PW'){
					if(this.user == '' || this.pw == ''){
						return uni.showToast({
							icon: 'none',
							title: '请输入账号密码'
						})
					}
					this.pwLogin()
				}else{
					if(this.user == '' || this.code == ''){
						return uni.showToast({
							icon: 'none',
							title: '请输入手机号或者验证码'
						})
					}
					this.codeLogin()
				}
			},
			pwLogin(){
				this.$request({
					path: '/token/login',
					data: {
						 username: this.user,
						 password: this.pw
					}
				}).then(res => {
					uni.setStorageSync('token',res.data.token)
					uni.setStorageSync('info',res.data.info)
					uni.reLaunch({
						url: '/pages/home/index'
					})
				})
			},
			codeLogin(){
				this.$request({
					path: '/community/fruser/sms/login',
					data: {
						 phone: this.user,
						 code: this.code
					}
				}).then(res => {
					uni.setStorageSync('token',res.data.token)
					uni.setStorageSync('info',res.data.info)
					uni.reLaunch({
						url: '/pages/home/index'
					})
				})
			},
			showpw(){
				this.pwState = !this.pwState
			}
		}
	}
</script>

<style lang="scss" scoped>
	.login{
		width: 100%;
		height: 100%;
		min-height: 100vh;
		background-image: url(@/static/login/login-background.png);
		background-size: contain;
		background-repeat: no-repeat;
		background-position: bottom;
		&-form{
			padding: 106rpx 76rpx 0;
			
			.form-item{
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
				& > input{
					flex: 1;
					margin-left: 80rpx;
					color: rgba(78, 85, 136, 1);
					font-size: 28rpx;
					font-weight: 400;
					font-family: SourceHanSansCN-Regular;
				}
				.see-pw{
					width: 28rpx;
					height: 16rpx;
					margin-left: 15rpx;
					margin-right: 34rpx;
					background-image: url(@/static/login/password.png);
					background-repeat: no-repeat;
					background-size: cover;
				}
				.getCode{
					font-size: 28rpx;
					color: rgba(78, 85, 136, 1);
					border-left: 2rpx solid rgba(78, 85, 136, 1);
					padding-left: 22rpx;
					font-family: SourceHanSansCN-Regular;
				}
			}
			.login-submit{
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
		.login-bottom-wrap{
			position: fixed;
			left: 0;
			bottom: 64rpx;
			width: 100%;
			display: flex;
			justify-content: space-between;
			box-sizing: border-box;
			padding: 0 75rpx;
			.login-bottom{			
				text-align: center;
				font-size: 28rpx;
				color: #656C97;
				font-family: SourceHanSansCN-Regular;
			}
		}
		
	}
	
</style>

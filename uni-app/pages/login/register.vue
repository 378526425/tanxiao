<template>
	<view class="login">
		<app-head :show="appHeadShow"></app-head>
		<view class="login-form">
			<view class="form-item-wrap" v-if="registerStage == 1">
				<view class="form-item">
					手机号
					<input type="text" v-model="phone" placeholder="请输入">
				</view>
				<view class="form-item" >
					验证码
					<input type="number" v-model="code" placeholder="请输入">
					<view class="getCode" @click="getCode" v-if="codeState">
						获取验证码
					</view>
					<view class="codeCountDown" v-else>
						{{ countDown }}秒后可重发
					</view>
				</view>
			</view>
			<view class="_select-gender" v-if="registerStage == 2">
				<view :class="['gender-item',{'gender-item-active': gender === 'male'}]" @click="selectGender('male')">
					<image src="../../static/login/avataMan.png" mode="aspectFit"></image>
					我是男生
				</view>
				
				<view :class="['gender-item',{'gender-item-active': gender === 'female'}]" @click="selectGender('female')">
					<image class="gender-icon-woman" src="../../static/login/avatarWoman.png" mode="aspectFit"></image>
					我是女生
				</view>
			</view>
			
			
			<view class="login-submit" @click="next">
				下一步
			</view>
		</view>
		<view class="login-bottom" @click="toLogin">
			已有账号？立即登录
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				phone: '',
				code: '',
				codeState: true,
				countDown: 60,
				registerStage: 1,
				gender: 'male',
				appHeadShow: false
			}
		},
		onLoad(e) {
			if(e.registerStage){
				this.registerStage = Number(e.registerStage)
				this.appHeadShow = true
			}
		},
		methods: {
			selectGender(e){
				this.gender = e
			},
			toLogin(){
				uni.reLaunch({
					url: '/pages/login/index?loginMode=PW'
				})
			},
			next(){
				if(this.registerStage === 2){
					getApp().globalData.sex = this.gender
					return uni.navigateTo({
						url: '/pages/login/registerInfo'
					})
				}
				
				if(this.phone == ''){
					return uni.showToast({
						title: '请输入手机号',
						icon: 'none'
					})
					
				}
				if(this.code == ''){
					return uni.showToast({
						icon: 'none',
						title: '请输入验证码'
					})
				}
				
				this.confirmCode()				
			},
			confirmCode(){
				this.$request({
					path: '/token/checksms',
					data: {
						 messageType : 'REGISTER',
						 phone: this.phone,
						 code: this.code
					}
				}).then(res => {
					uni.navigateTo({
						url: '/pages/login/register?registerStage=2'
					})
					getApp().globalData.code = this.code
					getApp().globalData.phone = this.phone
				})		
			},
			getCode(){
				if(this.phone == ''){
					return uni.showToast({
						title: '请输入手机号',
						icon: 'none'
					})
					
				}				
				this.$request({
					path: '/token/sendsms',
					data: {
						 messageType: 'REGISTER',
						 phone: this.phone
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
			.form-item-wrap{
				margin-bottom: 48rpx;
			}
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
				.codeCountDown{
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
				font-family: SourceHanSansCN-Medium;
			}
			._select-gender{
				width: 100%;
				margin: 0 0 80rpx;
				display: flex;
				justify-content: space-around;
			}
			.gender-item-active{
				border: 2rpx solid #4E5588!important;
			}
			.gender-item{
				width: 234rpx;
				height: 230rpx;
				border-radius: 32rpx;
				background-color: #fff;
				border: 2rpx solid #ffffff;
				display: flex;
				flex-direction: column;
				align-items: center;
				justify-content: center;
				font-size: 28rpx;
				color: #3D3D3D;
				font-family: SourceHanSansCN-Regular;
				& > image {
					width: 54rpx;
					height: 70rpx;
					margin-bottom: 24rpx;
				}
				.gender-icon-woman{
					width: 80rpx;
				}
			}
		}
		&-bottom{
			position: fixed;
			left: 0;
			bottom: 64rpx;
			width: 100%;
			text-align: center;
			font-size: 28rpx;
			color: #656C97;
			font-family: SourceHanSansCN-Regular;
		}
	}
	
</style>

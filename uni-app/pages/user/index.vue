<template>
	<view class="user-wrap">
		<view class="user">
			<status-bar></status-bar>
			<view class="user-info">
				<view class="not—login" v-if="!loginState" @click="goLogin">
					<image class="not—login-img" src="../../static/user/watch.png" mode=""></image>
					<view class="not-login-button">
						请登录
					</view>
				</view>
				<view class="login" v-if="loginState">
					<view class="login-info">
						<image class="Profile" :src="userInfo.headPortrait" mode=""></image>
						<view class="name-info">
							<view class="name">
								{{ userInfo.nickName }}
								<view class="name-other-info">
									<view class="user-money">
										<image src="../../static/user/money_icon.png" mode=""></image>
										余额
										<text>{{ userInfo.goldBalance }}</text>
									</view>
									<view class="user-city">
										IP {{ userInfo.city }}
									</view>
								</view>
							</view>
							<view class="icon_right_white"></view>
						</view>
					</view>
					<view class="other-info">
						<view class="other-info-item">
							{{ userInfo.blogCount }}
							<view class="other-info-tips">
								动态
							</view>
						</view>
						<view class="other-info-item" @click="goAttention(0)">
							{{ userInfo.followCount }}
							<view class="other-info-tips">
								关注
							</view>
						</view>
						<view class="other-info-item" @click="goAttention(1)">
							{{ userInfo.fansCount }}
							<view class="other-info-tips">
								粉丝
							</view>
						</view>
					</view>
					<view class="dynamic" v-if="userInfo.blog && userInfo.blog.length > 0">
						<view class="dynamic-title">
							我的动态
							<view class="dynamic-title-right">
								查看全部
							</view>
						</view>
						<view class="dynamic-content">
							<view class="dynamic-item" v-for="(item,index) in userInfo.blog" :key="index">
								<image :src="item.image" mode=""></image>
								<view class="dynamic-info">
									<view class="dynamic-info-text">
										{{ item.content }}
									</view>
									<view class="dynamic-info-time">
										{{ item.time }}
									</view>
								</view>
							</view>
						</view>
					</view>
				</view>
			</view>
			<view class="list-buton">
				<view class="buton-title">
					用户中心
				</view>
				<view class="buton-wrap">
					<view class="buton-item" @click="goPages(item)" v-for="(item,index) in listButton" :key="index">
						<image class="item-icon" :src="item.icon"></image>
						<view class="item-info">
							<view class="button-text">
								{{ item.text }}
							</view>
							<view class="icon_right"></view>
						</view>
					</view>
				</view>
			</view>
		</view>
		<tab-bar :selectTabbarCurrent="3"></tab-bar>
		<view class="empty"></view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				listButton: [{
						icon: '../../static/user/user_item_icon1.png',
						text: '账户充值',
						path: '',
					},
					{
						icon: '../../static/user/user_item_icon2.png',
						text: '隐私设置',
						path: '/pages/user/privacy'
					}
				],
				loginState: true,
				userInfo: {},
			}
		},
		onLoad() {
			const token = uni.getStorageSync('token')
			if (!token) this.loginState = false
			else this.getUserInfo()
			console.log(uni.getStorageSync('info'))
		},
		onShow() {
			uni.hideTabBar({
				animation: false
			})
		},
		methods: {
			goPages(e) {
				if (e.path !== '') {
					uni.navigateTo({
						url: e.path
					})
				} else {
					if (e.text === '退出登录') {
						this.signOut()
					}
				}
			},
			
			goAttention(e){
				uni.navigateTo({
					url:'/pages/user/attention?type='+e
				})
			},
			goLogin() {
				uni.navigateTo({
					url: '/pages/login/index'
				})
			},
			signOut() {
				uni.removeStorageSync('token');
				uni.removeStorageSync('info');
				this.loginState = false
				this.userInfo = {}
				this.listButton.splice(this.listButton.length - 1, 1)
				uni.setStorageSync('signOut', 1);
				this.$request({
					path: '/token/logout',
					method: 'DELETE',
				}).then(res => {})
				/** uni.showModal({
					title: '退出',
					content: '确定退出登陆吗',
					success: (res) => {
						if(res.confirm){
							uni.removeStorageSync('token');
							uni.removeStorageSync('info');
							this.loginState = false
							this.userInfo = {}
							this.listButton.splice(this.listButton.length-1 , 1)
							uni.setStorageSync('signOut',1);
							this.$request({
								path: '/token/logout',
								method: 'DELETE',
							}).then(res => {
							})	
						}
					}
				}) */
			},
			getUserInfo() {
				this.$request({
					path: '/community/fruser/personalCenter',
					method: 'GET',
				}).then(res => {
					this.userInfo = res.data
					this.listButton.push({
						icon: '../../static/user/exitlogin.png',
						text: '退出登录',
						path: ''
					})
				})
			},
		}
	}
</script>

<style lang="scss" scoped>
	.user-wrap {
		width: 100%;
		height: 100%;
		min-height: 100vh;
		background-color: rgba(2, 16, 31, 1);
	}

	.user {
		width: 100%;
		height: 100%;
		background-image: url('../../static/user/user_index.png');
		background-size: cover;
		background-repeat: no-repeat;
		padding: 0 32rpx;
		box-sizing: border-box;
	}

	.user-info {
		.not—login {
			width: 100%;
			margin-top: 120rpx;
			margin-bottom: 96rpx;
			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;

			.not—login-img {
				width: 200rpx;
				height: 200rpx;
				border-radius: 100%;
			}

			.not-login-button {
				width: 220rpx;
				height: 72rpx;
				background-color: rgba(243, 203, 96, 1);
				border-radius: 58rpx;
				margin-top: 30rpx;
				text-align: center;
				line-height: 72rpx;
				color: rgba(0, 0, 0, 1);
				font-size: 36rpx;
				font-family: SourceHanSansCN-Regular;
			}
		}

		.login {
			margin-top: 88rpx;
			margin-bottom: 48rpx;

			.login-info {
				display: flex;

				.Profile {
					width: 176rpx;
					height: 176rpx;
					border-radius: 100%;
				}

				.name-info {
					margin-left: 46rpx;
					display: flex;
					align-items: center;
					justify-content: space-between;
					flex: 1;
				}

				.name {
					font-size: 44rpx;
					color: #FFFFFF;
					font-family: SourceHanSansCN-Bold;
				}

				.name-other-info {
					display: flex;
					align-items: center;
					margin-top: 24rpx;

					.user-money {
						width: 168rpx;
						height: 38rpx;
						border-radius: 24rpx;
						background-image: url('../../static/user/money_bgd.png');
						background-repeat: no-repeat;
						background-size: cover;
						font-size: 24rpx;
						color: #FFFFFF;
						font-family: SourceHanSansCN-Normal;
						display: flex;
						align-items: center;
						justify-content: center;

						&>image {
							width: 20rpx;
							height: 20rpx;
							margin-right: 8rpx;
						}

						&>text {
							margin-left: 4rpx;
							font-size: 27rpx;
							color: #FFFFFF;
							font-family: SourceHanSansCN-Bold;
							font-weight: bold;
							font-style: italic;
						}
					}

					.user-city {
						color: #44475B;
						font-size: 28rpx;
						font-family: SourceHanSansCN-Regular;
						margin-left: 26rpx;
					}
				}
			}

			.other-info {
				display: flex;
				justify-content: space-around;
				align-items: center;
				margin-top: 60rpx;

				.other-info-item {
					font-size: 36rpx;
					color: #FFFFFF;
					font-family: SourceHanSansCN-Bold;
					text-align: center;
				}

				.other-info-tips {
					color: #44475B;
					font-size: 32rpx;
					font-family: SourceHanSansCN-Regular;
				}
			}

			.dynamic {
				margin-top: 70rpx;

				.dynamic-title {
					display: flex;
					justify-content: space-between;
					font-size: 32rpx;
					font-family: SourceHanSansCN-Bold;
					color: #FFFFFF;

					.dynamic-title-right {
						color: #CFCFCF;
						font-size: 28rpx;
						font-family: SourceHanSansCN-Regular;
					}
				}

				.dynamic-content {
					display: flex;
					justify-content: space-between;

					.dynamic-item {
						width: 332rpx;
						height: 452rpx;
						border-radius: 24rpx;
						background-color: #233340;
						margin-top: 32rpx;

						&>image {
							width: 100%;
							height: 314rpx;
							border-radius: 24rpx 24rpx 0 0;
						}

						.dynamic-info {
							padding: 30rpx;

							.dynamic-info-text {
								font-size: 28rpx;
								color: #FFFFFF;
								font-family: SourceHanSansCN-Medium;
								white-space: nowrap;
								overflow: hidden;
								text-overflow: ellipsis;
							}

							.dynamic-info-time {
								color: #BCBCBC;
								font-size: 24rpx;
								font-family: SourceHanSansCN-Regular;
							}
						}
					}
				}
			}
		}
	}

	.list-buton {
		.buton-title {
			font-size: 32rpx;
			color: rgba(255, 255, 255, 1);
			font-family: SourceHanSansCN-Bold;
			margin-bottom: 40rpx;
		}

		.buton-item {
			width: 100%;
			display: flex;
			margin-bottom: 30rpx;

			.item-icon {
				width: 46rpx;
				height: 46rpx;
			}

			.item-info {
				flex: 1;
				display: flex;
				justify-content: space-between;
				align-items: center;
				margin-left: 34rpx;

				.button-text {
					font-size: 28rpx;
					color: rgba(217, 217, 217, 1);
					font-family: SourceHanSansCN-Regular;
				}
			}
		}
	}
</style>

<template>
	<view class="app">
		<view class="wrap">
			<app-head color="#DFE3FA" type="HOME">
				<view class="head-functionButton">
					<view :class="['music',{'music-black':!musicShow}]" @click="musicShow = !musicShow">
					</view>
					<view class="screen">
						<image src="@/static/screen.png" mode=""></image>
						筛选
					</view>
				</view>
			</app-head>
			<view class="user-float-wrap" @click="locti">
				<view :class="['user-item',`user-item-active${index+1}`]" v-for="(item,index) in userList" :key="index">
					<image :src="item.headPortrait" mode=""></image>
					{{ item.nickName }}
				</view>
			</view>
			<view class="function-Butoon">
				<view class="function-Butoon1" @click="goPage('/pages/videoMatching/index')">
					<image src="../../static/functionButoon1.png" mode="aspectFit"></image>
					<text>视频匹配</text>
				</view>
				<view class="function-Butoon2">
					<image src="../../static/functionButoon2.png" mode="aspectFit"></image>
					<text>点缀星空</text>
				</view>
			</view>
			<tab-bar :selectTabbarCurrent="0"></tab-bar>
			<!-- <view class="empty"></view> -->
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello',
				type: false,
				userList: [],
				musicShow: true
			}
		},
		onLoad() {
			this.getHomeData()
		},
		onShow() {
			uni.hideTabBar({
				animation: false
			})
			if (uni.getStorageSync('signOut') === 1) {
				this.userList = []
				this.getHomeData()
				uni.removeStorageSync('signOut');
			}	
		},
		methods: {
			getHomeData() {
				this.$request({
					path: '/community/fruser/dynamic/user',
					method: 'GET'
				}).then(res => {
					if (res.code === 200) {
						console.log(res.data.length)
						res.data.map((res, index) => {
							setTimeout(() => {
								this.userList.push(res)
							}, ` ${index == 0 ? '0': index+'000'}`)
						})
					} else {
						uni.showToast({
							icon: 'none',
							title: res.msg
						})
					}
				})
			},
			locti() {
				this.type = true
			},
			goPage(v) {
				uni.navigateTo({
					url: v
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.app {
		width: 100vw;
		height: 100vh;
		position: relative;

		.wrap {
			position: absolute;
			width: 100%;
			height: 100%;
			min-height: 100vh;
			background-image: url(@/static/home.gif);
			background-color: black;
			background-size: cover;
			background-repeat: no-repeat;
			background-position: bottom;

			.head-functionButton {
				display: flex;
				align-items: center;

				.music {
					width: 54rpx;
					height: 54rpx;
					display: flex;
					justify-content: center;
					align-items: center;
					margin-right: 20rpx;
					background-image: url('../../static/music-white.png');
					background-repeat: no-repeat;
					background-size: cover;
				}
				
				.music-black{
					background-image: url('../../static/music-black.png')!important;
				}

				.screen {
					width: 120rpx;
					height: 54rpx;
					border: 2rpx solid #FFFFFF;
					border-radius: 32rpx;
					display: flex;
					justify-content: center;
					align-items: center;
					background-image: url('../../static/screen-bgd.png');
					background-repeat: no-repeat;
					background-size: cover;
					font-family: SourceHanSansCN-Normal;

					&>image {
						width: 20rpx;
						height: 20rpx;
						margin-right: 8rpx;
					}

					color: #FFFFFF;
					font-size: 24rpx;

				}
			}

			.user-float-wrap {
				width: 100%;
				height: 500rpx;
				// background-color: red;
				position: relative;
				border-radius: 100%;
				padding-top: 100rpx;

				.user-item-active1 {
					animation-name: animation1;
				}

				.user-item-active2 {
					animation-name: animation2;
				}

				.user-item-active3 {
					animation-name: animation3;
				}

				.user-item-active4 {
					animation-name: animation4;
				}

				.user-item-active5 {
					animation-name: animation5;
				}

				.user-item-active6 {
					animation-name: animation6;
				}

				.user-item-active7 {
					animation-name: animation7;
				}

				.user-item-active8 {
					animation-name: animation8;
				}

				.user-item-active9 {
					animation-name: animation9;
				}

				.user-item-active10 {
					animation-name: animation10;
				}

				@keyframes animation1 {
					0% {
						transform: scale(0);
						position: absolute;
						top: 40%;
						left: 40%;
					}

					25% {
						transform: scale(0.3);
					}

					50% {
						transform: scale(0.6);
					}

					75% {
						transform: scale(0.9);
					}

					85% {
						transform: scale(1);
						opacity: 1;
					}

					100% {
						opacity: 0;
						transform: scale(1);
						position: absolute;
						top: 5%;
						left: 5%;
					}
				}

				@keyframes animation2 {
					0% {
						transform: scale(0);
						position: absolute;
						top: 40%;
						left: 40%;
					}

					25% {
						transform: scale(0.3);
					}

					50% {
						transform: scale(0.6);
					}

					75% {
						transform: scale(0.9);
					}

					85% {
						transform: scale(1);
						opacity: 1;
					}

					100% {
						opacity: 0;
						transform: scale(1);
						position: absolute;
						top: 85%;
						left: 40%;
					}
				}

				@keyframes animation3 {
					0% {
						transform: scale(0);
						position: absolute;
						top: 40%;
						left: 40%;
					}

					25% {
						transform: scale(0.3);
					}

					50% {
						transform: scale(0.6);
					}

					75% {
						transform: scale(0.9);
					}

					85% {
						transform: scale(1);
						opacity: 1;
					}

					100% {
						opacity: 0;
						transform: scale(1);
						position: absolute;
						top: 10%;
						left: 80%;
					}
				}

				@keyframes animation4 {
					0% {
						transform: scale(0);
						position: absolute;
						top: 40%;
						left: 40%;
					}

					25% {
						transform: scale(0.3);
					}

					50% {
						transform: scale(0.6);
					}

					75% {
						transform: scale(0.9);
					}

					85% {
						transform: scale(1);
						opacity: 1;
					}

					100% {
						opacity: 0;
						transform: scale(1);
						position: absolute;
						top: 0%;
						left: 20%;
					}
				}

				@keyframes animation5 {
					0% {
						transform: scale(0);
						position: absolute;
						top: 40%;
						left: 40%;
					}

					25% {
						transform: scale(0.3);
					}

					50% {
						transform: scale(0.6);
					}

					75% {
						transform: scale(0.9);
					}

					85% {
						transform: scale(1);
						opacity: 1;
					}

					100% {
						opacity: 0;
						transform: scale(1);
						position: absolute;
						top: 70%;
						left: 0%;
					}
				}

				@keyframes animation6 {
					0% {
						transform: scale(0);
						position: absolute;
						top: 40%;
						left: 40%;
					}

					25% {
						transform: scale(0.3);
					}

					50% {
						transform: scale(0.6);
					}

					75% {
						transform: scale(0.9);
					}

					85% {
						transform: scale(1);
						opacity: 1;
					}

					100% {
						opacity: 0;
						transform: scale(1);
						position: absolute;
						top: 45%;
						left: 85%;
					}
				}

				@keyframes animation7 {
					0% {
						transform: scale(0);
						position: absolute;
						top: 40%;
						left: 40%;
					}

					25% {
						transform: scale(0.3);
					}

					50% {
						transform: scale(0.6);
					}

					75% {
						transform: scale(0.9);
					}

					85% {
						transform: scale(1);
						opacity: 1;
					}

					100% {
						opacity: 0;
						transform: scale(1);
						position: absolute;
						top: 80%;
						left: 70%;
					}
				}

				@keyframes animation8 {
					0% {
						transform: scale(0);
						position: absolute;
						top: 40%;
						left: 40%;
					}

					25% {
						transform: scale(0.3);
					}

					50% {
						transform: scale(0.6);
					}

					75% {
						transform: scale(0.9);
					}

					85% {
						transform: scale(1);
						opacity: 1;
					}

					100% {
						opacity: 0;
						transform: scale(1);
						position: absolute;
						top: 85%;
						left: 23%;
					}
				}

				@keyframes animation9 {
					0% {
						transform: scale(0);
						position: absolute;
						top: 40%;
						left: 40%;
					}

					25% {
						transform: scale(0.3);
					}

					50% {
						transform: scale(0.6);
					}

					75% {
						transform: scale(0.9);
					}

					85% {
						transform: scale(1);
						opacity: 1;
					}

					100% {
						opacity: 0;
						transform: scale(1);
						position: absolute;
						top: 55%;
						left: 0%;
					}
				}

				@keyframes animation10 {
					0% {
						transform: scale(0);
						position: absolute;
						top: 40%;
						left: 40%;
					}

					25% {
						transform: scale(0.3);
					}

					50% {
						transform: scale(0.6);
					}

					75% {
						transform: scale(0.9);
					}

					85% {
						transform: scale(1);
						opacity: 1;
					}

					100% {
						opacity: 0;
						transform: scale(1);
						position: absolute;
						top: 10%;
						left: 40%;
					}
				}

				.user-item {
					width: 106rpx;
					font-size: 30rpx;
					color: rgba(255, 255, 255, 1);
					text-align: center;
					animation-direction: normal;
					animation-timing-function: linear;
					animation-delay: 0s;
					animation-iteration-count: infinite;
					animation-duration: 7s;
					animation-fill-mode: forwards;
					font-family: SourceHanSansCN-Bold;

					// position: absolute;
					// top: 40%;
					// left: 40%;
					&>image {
						width: 104rpx;
						height: 104rpx;
						margin-bottom: 20rpx;
						border-radius: 100%;
						border: 6rpx solid rgba(145, 157, 226, 1);
					}


				}
			}

			.function-Butoon {
				width: 100%;
				display: flex;
				justify-content: space-around;
				position: fixed;
				left: 0;
				bottom: 266rpx;

				// margin-bottom: ;
				.function-Butoon1,
				.function-Butoon2 {
					display: flex;
					flex-direction: column;
					font-family: SourceHanSansCN-Medium;
					align-items: center;

					&>text {
						color: #FFFFFF;
						font-size: 32rpx;
						font-weight: bold;
						margin-top: 28rpx;
						text-shadow: 0px 0px 10rpx rgba(223, 235, 247, 0.56);
						background-image: url('../../static/text-bgd.png');
						background-size: cover;
						background-repeat: no-repeat;
					}

					&>image {
						width: 128rpx;
						height: 128rpx;
					}
				}
			}
		}
	}
</style>

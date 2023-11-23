<template>
	<view class="box">
		<view class="bg">
			<navigation :backClick="true" :iconBackColor="true" :shadowState="true" title="视频匹配" @back="back">
			</navigation>
			<view class="conetnt">
				<view class="dot1">

				</view>
				<view class="dot2">

				</view>
				<view class="dot3">

				</view>
				<view class="con">
					<view class="con1">
						<image :src="matchInfo.headPortrait" mode=""></image>
					</view>
				</view>
				<view class="info">
					<view class="name">
						<view class="line"></view>
						<view class="n">{{matchInfo.nickName&&matchInfo.nickName.slice(0,8)}}</view>
						<view class="line"></view>
					</view>
					<view class="name2">
						{{matchInfo.message}}
					</view>
					<view class="line3">

					</view>
					<view class="end">
						通话已结束
					</view>
				</view>
			</view>
			<view class="btn" @click="">
				添加好友
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				info: {

				},
				matchInfo: {

				}
			}
		},
		onLoad() {
			uni.getStorage({
				key: 'info',
				success: (res) => {
					this.info = res.data
				}
			})
			this.matchInfo = JSON.parse(this.$store.state.matchInfo);
		},
		methods: {
			back() {
				this.matchingEnd();
				uni.navigateBack({
					delta: 3
				})
			},
			matchingEnd() {
				this.$request({
					path: `/community/fruser/end/matching`,
					method: 'GET'
				}).then(res => {
					if (res.code === 200) {

					} else {
						uni.showToast({
							icon: 'none',
							title: res.msg
						})
					}
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	* {
		box-sizing: border-box;
	}

	.box {
		width: 100vw;
		height: 100vh;
		background-image: url('@/static/video.gif');
		background-size: 100% 100%;

		.bg {
			width: 100%;
			height: 100%;
			background-image: url('@/static/videoMatching/bg.png');
			background-size: 100% 100%;

			.btn {
				width: 550rpx;
				height: 86rpx;
				margin: 0 auto;
				background: rgba(243, 203, 96, 0.18);
				border-radius: 36rpx;
				border: 2rpx solid #F3CB60;
				text-align: center;
				line-height: 86rpx;
				font-size: 32rpx;
				font-family: Source Han Sans CN-Medium, Source Han Sans CN;
				color: #F3CB60;
				margin-top: 128rpx;
			}

			.conetnt {
				position: relative;

				.dot1 {
					width: 20rpx;
					height: 20rpx;
					background-color: rgba(248, 207, 97, 0.59);
					border-radius: 50% 50%;
					position: absolute;
					top: 100rpx;
					left: 420rpx;

					@-webkit-keyframes rotation {
						from {
							-webkit-transform: rotate(360deg);
						}

						to {
							-webkit-transform: rotate(0deg);
						}
					}

					-webkit-transform: rotate(360deg);
					animation: rotation 6s linear infinite;
					-moz-animation: rotation 6s linear infinite;
					-webkit-animation: rotation 6s linear infinite;
					-o-animation: rotation 6s linear infinite;
					transform-origin:-40rpx 274rpx;
				}

				.dot2 {
					width: 20rpx;
					height: 20rpx;
					background-color: rgba(248, 207, 97, 0.8);
					border-radius: 50% 50%;
					position: absolute;
					top: 340rpx;
					left: 572rpx;

					@-webkit-keyframes rotation {
						from {
							-webkit-transform: rotate(360deg);
						}

						to {
							-webkit-transform: rotate(0deg);
						}
					}

					-webkit-transform: rotate(360deg);
					animation: rotation 6s linear infinite;
					-moz-animation: rotation 6s linear infinite;
					-webkit-animation: rotation 6s linear infinite;
					-o-animation: rotation 6s linear infinite;
					transform-origin:-200rpx 38rpx;
				}

				.dot3 {
					width: 20rpx;
					height: 20rpx;
					background-color: rgba(248, 207, 97, 0.5);
					border-radius: 50% 50%;
					position: absolute;
					top: 340rpx;
					left: 572rpx;

					@-webkit-keyframes rotation {
						from {
							-webkit-transform: rotate(360deg);
						}

						to {
							-webkit-transform: rotate(0deg);
						}
					}

					-webkit-transform: rotate(360deg);
					animation: rotation 10s linear infinite;
					-moz-animation: rotation 10s linear infinite;
					-webkit-animation: rotation 10s linear infinite;
					-o-animation: rotation 10s linear infinite;
					transform-origin:-200rpx 38rpx;
				}

				.info {
					width: 550rpx;
					height: 538rpx;
					background: rgba(23, 30, 55, 0.7);
					border-radius: 36rpx;
					margin: 0 auto;
					margin-top: -140rpx;
					padding: 80rpx 100rpx;

					.name {
						display: flex;
						justify-content: space-between;
						align-items: center;
						margin-top: 134rpx;

						.n {
							font-size: 40rpx;
							font-family: Source Han Sans CN-Bold, Source Han Sans CN;
							font-weight: bold;
							color: #DFE3FA;
							text-shadow: 0px 0px 10rpx rgba(223, 235, 247, 0.56);
						}

						.line {
							width: 14rpx;
							height: 4rpx;
							background: #F8CF61;
							border-radius: 2rpx;
						}
					}

					.name2 {
						font-size: 28rpx;
						font-family: Source Han Sans CN-Normal, Source Han Sans CN;
						font-weight: 400;
						color: #595D71;
						text-align: center;
						margin-top: 40rpx;
					}

					.line3 {
						width: 4rpx;
						height: 14rpx;
						background: #F8CF61;
						border-radius: 2rpx;
						margin: 0 auto;
						margin-top: 60rpx;

					}

					.end {
						font-size: 36rpx;
						font-family: Source Han Sans CN-Regular, Source Han Sans CN;
						font-weight: 400;
						color: #F3CB60;
						margin-top: 26rpx;
						text-align: center;
					}
				}

				.con {
					width: 202rpx;
					height: 204rpx;
					background: #B7BEEC;
					display: flex;
					justify-content: center;
					align-items: center;
					border-radius: 50% 50%;
					margin: 0 auto;
					margin-top: 128rpx;
					position: relative;
					z-index: 9;

					.con1 {
						width: 178rpx;
						height: 180rpx;
						margin: auto;
						background: #919DE2;
						box-shadow: 0px 0px 8rpx 2rpx #4754BE;
						border: 10rpx solid #919DE2;
						display: flex;
						justify-content: center;
						align-items: center;
						border-radius: 50% 50%;

						image {
							width: 158rpx;
							height: 160rpx;
							border-radius: 50% 50%;
						}
					}
				}
			}

		}

	}
</style>

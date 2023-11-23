<template>
	<view class="box">
		<view class="bg">
			<navigation :backClick="true" :iconBackColor="true" :shadowState="true" title="视频匹配" @back="back">
			</navigation>
			<view class="content">
				<view class="dot1"></view>
				<view class="dot2"></view>
				<view class="dot3"></view>
				<div class="circle">
					<div class="mask"></div>
				</div>
				<view class="con">
					<view class="con1">
						<image :src="info.headPortrait" mode=""></image>
					</view>
				</view>
			</view>
			<view class="hint">
				<view class="one move" ref="one" style="left:100%">
					<view class="item" v-for="i in txt1">
						{{i}}
					</view>
				</view>
			</view>
			<view class="hint">
				<view class="two move" ref="two" style="left:100%">
					<view class="item" v-for="i in txt2">
						{{i}}
					</view>
				</view>
			</view>
			<view class="match">
				正在匹配中...
			</view>
			<view class="txt">
				正在努力寻找与你高度共鸣度的Ta
			</view>
		</view>
	</view>
</template>

<script>
	import {
		WS
	} from '@/utils/wsConfig.js'
	import {
		log
	} from 'util';
	// #ifdef APP-PLUS
	const TUICallKit = uni.requireNativePlugin('TencentCloud-TUICallKit');
	// #endif
	export default {
		data() {
			return {
				timer: null,
				timer2: null,
				txt1: [],
				txt2: [],
				info: {},
				type: '',
				socketTask: null,
				isClose: false,
			}
		},
		onLoad(options) {
			uni.getStorage({
				key: 'info',
				success: (res) => {
					console.log(111);
					this.info = res.data;
					//建立腾讯视频连接
					// #ifdef APP-PLUS
					this.tencentConnect();
					// #endif

					//匹配连接开始
					this.connectSocket();
					this.message();
				},
				fail: (err) => {
					uni.reLaunch({
						url: '/pages/login/index'
					})
				}
			})

		},
		onShow() {
			this.isClose = false;
			// this.connectSocket();
		},
		onUnload() {
			this.isClose = true;
			// this.socketTask.close();
			clearInterval(this.timer2)
			clearInterval(this.timer)
		},
		onHide() {},
		methods: {
			//连接websocket
			connectSocket() {
				let that = this;
				console.log('调用连接websocket')
				this.socketTask = uni.connectSocket({
						url: WS,
						header: {
							'content-type': 'text/plain'
						},
						success(res) {
							console.log("websocket连接成功");
						},
						fail(err) {
							console.log("报错", err);
						}
					},


				);
				this.socketTask.onOpen(function(res) {
					// console.log('WebSocket连接已打开！');
					that.matching()

				})
				this.socketTask.onMessage(function(res) {
					// console.log('收到服务器内容：' + res.data);
					that.isClose = true;
					clearInterval(that.timer2)
					that.socketTask.close();
					that.$store.commit('setMatchInfo', res.data);
					setTimeout(() => {
						uni.navigateTo({
							url: `/pages/videoMatching/videoStart`
						})
					}, 500)

				});


				this.socketTask.onError(function(res) {
					console.log('WebSocket连接打开失败，请检查！');
					//进入重新连接
					that.reconnect();

				})
				// // 监听连接关闭 -
				this.socketTask.onClose((e) => {
					// console.log('WebSocket连接关闭！');
					clearInterval(that.timer2)
					that.timer = ''
					if (!that.isClose) {
						that.reconnect()
					}
				})
				// console.log(this.socketTask)
			},
			//进入重新连接
			reconnect() {
				console.log('进入断线重连');
				// this.socketTask.close();
				this.socketTask = null;
				this.connectSocket()

			},
			//发送消息
			sendSocketMessage(msg) {
				// console.log('发送信息')
				// console.log(msg)
				return new Promise((reslove, reject) => {
					this.socketTask.send({
						data: msg,
						success(res) {
							// console.log('发送成功')
							reslove(res)
						},
						fail(res) {
							console.log('发送失败')
							console.log(res)
							reject(res)
						}
					});
				})
			},

			//心跳
			heart() {
				let that = this;
				clearInterval(that.timer2);
				that.timer2 = null;
				let msg = {
					messageType: "MATCHING",
					info: {
						type: this.type,
						userId: this.info.id
					}
				};
				let str = `{"messageType":"MATCHING","info":"{'type':'${this.type}','userId':${this.info.id}}"}`
				that.timer2 = setInterval(() => {
					that.sendSocketMessage(String(str)).then(res => {
						// console.log('心跳成功')
					}).catch(res => {
						console.log('发送失败')
						console.log((res))
					})

				}, 1000)
			},
			matching() {
				let that = this
				this.$request({
					path: `/community/fruser/start/matching`,
					method: 'GET'
				}).then(res => {
					if (res.code === 200) {
						let str = `{"messageType":"CONNECT","info":"${that.info.id}"}`
						console.log(str, typeof str)
						// that.sendSocketMessage(JSON.stringify({
						// 	messageType: 'CONNECT',
						// 	info: that.info.id
						// }))
						that.sendSocketMessage(String(str))
						that.heart()

					} else {
						uni.showToast({
							icon: 'none',
							title: res.msg
						})
					}
				})
			},
			message() {
				console.log(111);
				this.$request({
					path: `/community/fruser/message`,
					method: 'GET'
				}).then(res => {
					if (res.code === 200) {
						console.log(res, 'res');
						let arr = [...res.data]
						this.txt1 = [...arr];
						this.txt2 = arr.reverse();
					} else {
						uni.showToast({
							icon: 'none',
							title: res.msg
						})
					}
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
			},
			back() {
				this.matchingEnd()
				uni.navigateBack({})
			},
			tencentConnect() {
				this.$request({
					path: '/community/fruser/tuicallkit',
					method: 'GET',
				}).then(res => {
					const options = {
						SDKAppID: res.data.sdkAppId, // 请替换为步骤一取到的 SDKAppID
						userID: res.data.userId, // 请替换为您的 UserID
						userSig: res.data.userSig, // 您可以在控制台中计算一个 UserSig 并填在这个位置
					};
					console.log(options)
					TUICallKit.login(options, (callres) => {
						if (callres.code === 0) {
							//建立连接成功 设置头像和昵称
							const userCallInfo = {
							  nickName: res.data.nickName,
							  avatar: res.data.avatar
							}
							
							console.log("用户信息"+userCallInfo)
							TUICallKit.setSelfInfo(userCallInfo, (response) => {
							  if (response.code === 0) {
							    console.log('setSelfInfo success');
							  } else {
							    console.log(`setSelfInfo failed, error message = ${response.msg}`);
							  }
							});
							
							//设置背景音乐
							const filePath = '/sdcard/android/data/com.tanxiaoapp/static/bgm.mp3';
							TUICallKit.setCallingBell(filePath, (response) => {
							  if (response.code === 0) {
							    console.log('setCallingBell success');
							  } else {
							    console.log(`setCallingBell failed, error message = ${response.msg}`);
							  }
							});
						} else {
							uni.showModal({
								title: '腾讯视频建立连接失败',
								content: res.msg
							})
						}
					});
				})
			}
		},

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
			position: relative;

			.match {
				font-size: 36rpx;
				font-family: Source Han Sans CN-Medium, Source Han Sans CN;
				color: #F3CB60;
				text-align: center;
				width: 100%;
				position: absolute;
				bottom: 8%;
			}

			.txt {
				margin-top: 50rpx;
				font-size: 32rpx;
				font-family: Source Han Sans CN-Normal, Source Han Sans CN;
				font-weight: 400;
				color: #848DA8;
				text-align: center;
				width: 100%;
				position: absolute;
				bottom: 12%;
			}

			.hint {
				min-width: 100%;
				position: absolute;
				bottom: 30%;

				.move {
					left: 180rpx;
					-webkit-animation: mymove 20s infinite;
					/* Chrome, Safari, Opera */
					animation: mymove 20s infinite;
					animation-timing-function: linear;
				}

				@-webkit-keyframes mymove {
					from {
						left: 100%;
					}

					to {
						left: -140%;
					}
				}

				/* Standard syntax */
				@keyframes mymove {
					from {
						left: 100%;
					}

					to {
						left: -140%;
					}
				}

				.one,
				.two {
					position: absolute;
					min-width: 100%;
					display: flex;

					.item {
						width: fit-content;
						height: 64rpx;
						padding: 0 40rpx;
						background: rgba(255, 255, 255, 0.17);
						border-radius: 36rpx;
						border: 2rpx solid #CAD0FB;
						line-height: 64rpx;
						font-size: 28rpx;
						font-family: Source Han Sans CN-Normal, Source Han Sans CN;
						font-weight: 400;
						color: #D2D8FF;
						flex-shrink: 0;
						margin: 0 20rpx;
					}
				}

				.two {
					top: 96rpx;
				}
			}

			.content {
				width: 750rpx;
				height: 758rpx;
				background-image: url('@/static/videoMatching/yuanquan.png');
				background-size: 100% 100%;
				margin: 48rpx auto;
				display: flex;
				justify-content: center;
				align-items: center;
				position: relative;
				margin-bottom: -80rpx;

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

				.circle {
					@-webkit-keyframes rotation {
						from {
							-webkit-transform: rotate(360deg);
						}

						to {
							-webkit-transform: rotate(0deg);
						}
					}

					-webkit-transform: rotate(360deg);
					animation: rotation 3s linear infinite;
					-moz-animation: rotation 3s linear infinite;
					-webkit-animation: rotation 3s linear infinite;
					-o-animation: rotation 3s linear infinite;
					transform-origin:50% 100%;
					width: 750rpx;
					height: 750rpx;
					line-height: 375rpx;
					background: linear-gradient(89deg, rgba(20, 37, 135, 0.49) 0%, rgba(2, 16, 31, 0) 100%);
					border-radius: 750rpx 750rpx 0 0;
					height: 375rpx;
					position: absolute;
					top: 0;
				}

				.con {
					width: 202rpx;
					height: 204rpx;
					background: #B7BEEC;
					display: flex;
					justify-content: center;
					align-items: center;
					border-radius: 50% 50%;
					position: absolute;

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

<template>
	<view class="box">
		<view class="bg">
			<!-- <button @click="go">1111</button> -->

			<navigation :iconBackColor="true" :shadowState="true" title="视频匹配"></navigation>
			<view class="content">
				<view class="dot1">
					
				</view>
				<view class="dot2">
					
				</view>
				<view class="dot3">
					
				</view>
				<view class="con">
					<view class="con1">
						<image :src="info.headPortrait" mode=""></image>
					</view>
				</view>
			</view>
			<view class="btns">
				<view class="btn" @click="matching('SENIOR')">
					<image src="@/static/videoMatching/icon1.png" mode=""></image>
					<text>加速匹配</text>
				</view>
				<view class="btn" @click="matching('ORDINARY')">
					<text>普通匹配</text>
				</view>
			</view>
			
		</view>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				info:{}
			}
		},
		onLoad() {
			
			uni.getStorage({
				key: 'info',
				success: (res) => {
					this.info = res.data
				},
				fail:(err)=> {
					  uni.reLaunch({
					  	url:'/pages/login/index'
					  })
				}
			})
		},
		methods:{
			matching(v){
				uni.navigateTo({
					url:`/pages/videoMatching/matching?type=${v}`
				})
			},
			go(){
				
			}
		}
	}
</script>

<style lang="scss" scoped>
	*{
		box-sizing: border-box;
	}
	.box{
		width: 100vw;
		height: 100vh;
		background-image: url('@/static/video.gif');
		background-size: 100% 100%;
		.bg{
			width: 100%;
			height: 100%;
			background-image: url('@/static/videoMatching/bg.png');
			background-size: 100% 100%;
			.content{
				width: 750rpx;
				height: 758rpx;
				background-image: url('@/static/videoMatching/yuanquan.png');
				background-size: 100% 100%;
				margin: 48rpx auto;
				display: flex;
				justify-content: center;
				align-items: center;
				position: relative;
				.dot1{
					width: 20rpx;
					height: 20rpx;
					background-color: rgba(248,207,97, 0.59);
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
				.dot2{
					width: 20rpx;
					height: 20rpx;
					background-color: rgba(248,207,97, 0.8);
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
				.dot3{
					width: 20rpx;
					height: 20rpx;
					background-color: rgba(248,207,97, 0.5);
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
				.con{
					width: 202rpx;
					height: 204rpx;
					background: #B7BEEC;
					display: flex;
					justify-content: center;
					align-items: center;
					border-radius: 50% 50%;
					.con1{
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
						image{
							width: 158rpx;
							height: 160rpx;
							border-radius: 50% 50%;
						}
					}
				}
			}
			.btns{
				display: flex;
				width: 572rpx;
				margin: 0 auto;
				justify-content: space-between;
				// margin-top: 1018rpx;
				.btn{
					width: 280rpx;
					height: 74rpx;
					background: rgba(243,203,96,0.18);
					border-radius: 38rpx;
					border: 2rpx solid #F3CB60;
					font-family: Source Han Sans CN-Medium, Source Han Sans CN;
					color: #F3CB60;
					display: flex;
					justify-content: center;
					align-items: center;
					image{
						width: 44rpx;
						height: 48rpx;
						margin-right: 14rpx;
					}
				}
			}
		}
		
	}
</style>
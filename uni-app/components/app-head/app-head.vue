<template>
	<view class="head">
		<status-bar></status-bar>
		<view class="close" v-if="show " @click="close">
			<view class="close-left"></view>
			<view class="close-right"></view>
		</view>
		<view class="title" >
			<text class="title-text" :style="{'color': color ,'background-image': color != '' ? 'url(../../static/text-bgd2.png)': 'none'}">谈笑</text>
			<slot></slot>
		</view>
		<view class="tips" :style="{'color': color}">
			视频聊，更靠谱
		</view>
	</view>
</template>

<script>
	export default{
		name: 'app-head',
		props:{
			color:{
				type: String,
				default: 'rgba(74, 74, 74, 1)'
			},
			show: {
				type: Boolean,
				default: false
			}
		},
		methods:{
			close(){
				if(getCurrentPages().length > 1){
					uni.navigateBack()
				}
			}
		}
	}
</script>

<style lang="scss" scoped>
	.head{
		width: 100%;
		.close{
			width: 100%;
			height: 24rpx;
			position: relative;
			box-sizing: border-box;
			&-left,
			&-right{
				width: 2.5px;
				height: 28rpx;
				background-color: rgba(57, 57, 57, 1);
				border-radius: 20rpx;
				position: absolute;
				right: 36rpx;
				top: 0;
			}
			&-left{
				transform: rotateZ(40deg);
			}
			&-right{
				transform: rotateZ(-40deg);
			}
		}
		.title{
			
			padding: 0 56rpx;
			display: flex;
			justify-content: space-between;
			
			.title-text{
				font-size: 48rpx;
				font-weight: bold;
				background-size: cover;
				background-repeat: no-repeat;
				font-family: SourceHanSansCN-Bold;
			}
		}
		.tips{
			margin-top: 34rpx;
			font-size: 36rpx;
			font-weight: 400;
			padding: 0 56rpx;
			font-family: SourceHanSansCN-Regular;
		}
	}
</style>
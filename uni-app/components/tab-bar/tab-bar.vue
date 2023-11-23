<template>
	<view class="tab-bar-wrap">
		<view class="tab-bar">
			<view class="tab-bar-item" @click="switchTab(index)" v-for="(item,index) in tabbar" :key="index">
				<image class="white" v-if="selectTabbarCurrent === index" :src="item.iconPath" mode=""></image>
				<image class="black" v-else :src="item.selectedIconPath" mode=""></image>
				{{ item.text }} 				
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name:"tab-bar",
		data() {
			return {
				tabbar: [
					{
						"pagePath": "/pages/home/index",
						"iconPath": "../../static/tabbar/tabbar1White.png",
						"selectedIconPath": "../../static/tabbar/tabbar1Black.png",
						"text": "星空"
					}, {
						"pagePath": "/pages/community/index",
						"iconPath": "../../static/tabbar/tabbar2White.png",
						"selectedIconPath": "../../static/tabbar/tabbar2Black.png",
						"text": "发现"
					}, {
						"pagePath": "/pages/message/index",
						"iconPath": "../../static/tabbar/tabbar3White.png",
						"selectedIconPath": "../../static/tabbar/tabbar3Black.png",
						"text": "消息"
					},
					{
						"pagePath": "/pages/user/index",
						"iconPath": "../../static/tabbar/tabbar4White.png",
						"selectedIconPath": "../../static/tabbar/tabbar4Black.png",
						"text": "我的"
					}
				]
			};
		},
		props: {
			selectTabbarCurrent: {
				type: Number,
				default: 0
			},
		},
		methods:{
			switchTab(v){
				uni.switchTab({
					url:  this.tabbar[v].pagePath
				})
			}
		}
	}
</script>

<style lang="scss">
	.tab-bar-wrap{
		width: 100%;
		height: 132rpx;
		position: fixed;
		bottom: 48rpx;
		left: 0;
		padding: 0 40rpx;
		box-sizing: border-box;
		background-image: url(@/static/tabbar/tabbarBackground.png);
		background-repeat: no-repeat;
		background-size: contain;
	}
	.tab-bar{
		display: flex;
		justify-content: space-between;
		.tab-bar-item{
			width: 25%;
			height: 132rpx;
			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;
			color: #D1DEEB;
			font-size: 18rpx;
			font-family: SourceHanSansCN-Bold;
			& > image{
				width: 44rpx;
				height: 44rpx;
			}
		}
	}
</style>
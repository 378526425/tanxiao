<template>
	<view class="navigation-wrap">
		<status-bar></status-bar>
		<view class="navigation">
			<view :class="['back',{'white-Back':iconBackColor}]" @click="back"></view>
			<view :class="['title',{'text-shadow' : shadowState}]">
				{{ title }}
			</view>
			<view class="other">
				<image class="other-title" @click="otherFunction" v-if="images != ''" src="images" mode="heightFix"></image>
				<view v-else-if="Text != ''" class="other-text"> {{ Text }} </view>
				<view v-else class="other-empty"></view>
			</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		name:"navigation",
		props: {
			title: {
				type: String,
				default: ''
			},
			images:{
				type: String,
				default: ''
			},
			Text:{
				type: String,
				default: ''
			},
			shadowState: {
				type: Boolean,
				default: false
			},
			iconBackColor: {
				type: Boolean,
				default: false
			},
			backClick: {
				type: Boolean,
				default: false
			},
		},
		methods:{
			back(){
				if(this.backClick) this.$emit('back')
				else uni.navigateBack({})
			},
			otherFunction(){
				this.$emit('otherFunction')
			}
		}
	}
</script>

<style lang="less">
	.navigation{
		width: 100%;
		height: 44rpx;
		padding: 0 32rpx;
		display: flex;
		justify-content: space-between;
		align-items: center;
		box-sizing: border-box;
	}
	
	.back{
		width: 44rpx;
		height: 44rpx;
		background-image: url('../../static/blackBack.png');
		background-repeat: no-repeat;
		background-size: cover;
	}
	.white-Back{
		background-image: url('../../static/whiteBack.png')!important;
	}
	.text-shadow{
		text-shadow: 0px 0px 10rpx rgba(223,235,247,0.56);
		color: #DFE3FA!important;
	}
	.title{
		font-size:36rpx ;
		color:rgba(0, 0, 0, 1) ;
		font-family: SourceHanSansCN-Medium;
	}
	.other-title{
		
	}
	.other-empty{
		width: 44rpx;
		height: 44rpx;
	}
</style>
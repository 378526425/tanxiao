<template>
	<scroll-view scroll-y='true' @scroll="onScroll" :scroll-top="contentTop" :style="{height:height==-1?'100%':height+'rpx'}" @scrolltolower='loadMore' :lower-threshold='lowerThreshold' @touchstart="doTouchStart" @touchend="doTouchEnd" @touchmove="doTouchMove">
		<!-- 下拉提示 -->
		<view class="upDown" :style="{height: upDownTipsHeight+'px',maxHeight:maxHeight+'px'}" :class="isHeightChange?'heightAnimation':''">
			<text class="arrow" :class="upDownTipsHeight>=pullHeight?'upArrow':''" v-if="!isAllowLoading&&upDownTipsHeight>20">↓</text>
			<view class="loading" v-if="isAllowLoading">
				<text class="list"></text>
				<text class="list"></text>
				<text class="list"></text>
				<text class="list"></text>
				<text class="list"></text>
				<text class="list"></text>
				<text class="list"></text>
				<text class="list"></text>
			</view>
			<text class="tips">{{upDownTips}}</text>
		</view>
		<!-- 内容 -->
		<slot></slot>
		<!-- 底部提示 -->
		<view class='bottomTips' v-if="bottomTips">
			<view class="loading" v-if="bottomTips=='loading'">
				<text class="list"></text>
				<text class="list"></text>
				<text class="list"></text>
				<text class="list"></text>
				<text class="list"></text>
				<text class="list"></text>
				<text class="list"></text>
				<text class="list"></text>
			</view>
			<view>{{bottomTips | judgeBottomTips}}</view>
		</view>
	</scroll-view>
</template>

<script>
	export default{
		data(){
			return{
				upDownTipsHeight:0,//下拉时提示框的高度
				upDownTips:'',//下拉时提示框的文字内容
				startY:0,//第一次点击屏幕的Y坐标
				startX:0,//第一次点击屏幕的X坐标
				distanceY:0,//滑动的距离
				isAllowLoading:false,//下拉释放时是否显示loading样式
				isHeightChange:false,//下拉释放时提示框高度变化是否有动画
				isAllowPullDown:false,//是否触发下拉刷新
				isInterval:false,//防止鼠标或者手指移动时多次执行逻辑判断
				scrollTop:0
			}
		},
		props: {
			contentTop:{
				type:Number,
				default :0
			},
			height:{
				type:Number,
				default :700
			},
			pullHeight: {
				type: Number,
				default: 50
			},
			maxHeight: {
				type: Number,
				default: 100
			},
			bottomTips:{
				type: String,
				default: ''
			},
			lowerThreshold:{
				type: Number,
				default: 20
			},
			isTab:{
				type: Boolean,
				default: false
			},
			isAllowPull:{
				type: Boolean,
				default: true
			}
		},
		created() {
			//对传入的参数进行判断
			this.pullHeight<=20?this.pullHeight = 40:null;
			this.maxHeight<50?this.maxHeight = 50:null;
		},
		filters:{
			//判断底部提示文字
			judgeBottomTips(type){
				switch (type){
					case 'nomore':
						return '没有更多数据了'
						break;
					case 'loading':
						return '加载中...'
						break;
					case 'more':
						return '上拉加载更多'
						break;
					default:
						break;
				}
			}
		},
		methods:{
			onScroll(e){
				console.log(22);
				this.scrollTop=e.detail.scrollTop
			},
			//鼠标点击
			doTouchStart(e){
				if(!this.isAllowPull){
					return;
				}
				this.isHeightChange = false;
				this.isInterval = true;
				this.startY = e.touches[0].clientY;
				this.startX = e.touches[0].clientX;
			},
			//鼠标移动
			doTouchMove(e){
				if(!this.isAllowPull){
					return;
				}
				//滑动的距离
				this.distanceY = e.touches[0].clientY - this.startY;
				
				//只检测一次
				if(this.isInterval){
					this.isInterval = false;
					//这里判断横向滑动的距离差，主要是为了防止一个页面多个该组件时（横向tab切换）,出现的误操作
					if(Math.abs(this.startX-e.touches[0].clientX)>10&&this.isTab){
						this.isAllowPullDown = false;
					}
					else{
						this.isAllowPullDown = true;
					}
				}
				
				//根据横向滑动的距离判断是否触发下拉
				if(!this.isAllowPullDown){
					return;
				}
				
				//提示框的高度
				this.upDownTipsHeight = this.distanceY;
				
				//根据提示框的高度提示不同的内容
				if(this.upDownTipsHeight<=20){
					this.upDownTips = '';
				}
				else if(this.upDownTipsHeight>20&&this.upDownTipsHeight<this.pullHeight){
					this.upDownTips = '下拉刷新';
				}
				else if(this.upDownTipsHeight>=this.pullHeight){
					this.upDownTips = '松开刷新';
				}
			},
			//鼠标松开
			doTouchEnd(e){
				if(!this.isAllowPull){
					return;
				}
				if(this.upDownTipsHeight>=this.pullHeight){
					this.isAllowLoading = true;
					this.upDownTips = '刷新中...';
					this.currentPage = 1;
					this.loadingType = "more";
					this.$emit('refresh',1);
				}
				else{
					this.distanceY = 0;
					this.upDownTipsHeight = 0;
					this.upDownTips = '';
				}
				this.isInterval = true;
				this.isAllowPullDown = false;
				this.isHeightChange = true;
				// console.log(e.changedTouches[0].clientY);
			},
			//重置
			reSet(){
				if(!this.isAllowPull){
					return;
				}
				this.isAllowLoading = false;
				this.upDownTips = '';
				this.upDownTipsHeight = 0;
				this.distanceY = 0;
				this.isInterval = true;
				this.isAllowPullDown = false;
			},
			loadMore(){
				this.$emit('loadMore');
			}
		}
	}
</script>

<style lang="scss" scoped>
	.loading{
		width: 110rpx;
		height: 110rpx;
		position: relative;
		transform: scale(.4);
		margin-right: -20rpx;
		.list {
			width:15rpx;
			height:15rpx;
			border-radius:30px;
			background:#090909;
			position:absolute;
			animation:opaticyAnimation linear 0.7s infinite;
			-webkit-animation:opaticyAnimation linear 0.7s infinite;
		}
		.list:nth-child(1) {
			left:24px;
			top:2px;
			animation-delay:0s;
		}
		.list:nth-child(2) {
			left:40px;
			top:8px;
			animation-delay:0.1s;
		}
		.list:nth-child(3) {
			left:47px;
			top:24px;
			animation-delay:0.2s;
		}
		.list:nth-child(4) {
			left:40px;
			top:40px;
			animation-delay:0.3s;
		}
		.list:nth-child(5) {
			left:24px;
			top:47px;
			animation-delay:0.4s;
		}
		.list:nth-child(6) {
			left:8px;
			top:40px;
			animation-delay:0.5s;
		}
		.list:nth-child(7) {
			left:2px;
			top:24px;
			animation-delay:0.6s;
		}
		.list:nth-child(8) {
			left:8px;
			top:8px;
			animation-delay:0.7s;
		}
		@keyframes opaticyAnimation{
			from{
				opacity: 1;
			}
			to{
				opacity: 0;
			}
		}
	}
	.heightAnimation{
		transition: all .2s linear;
	}
	.upDown{
		width: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
		position: relative;
		top: 80px;
		.arrow{
			font-size: 40rpx;
			margin-right: 10rpx;
		}
		.upArrow{
			transition: all .3s linear;
			transform: rotate(180deg);
		}
		.tips{
			font-size: 30rpx;
		}
	}
	.bottomTips{
		width: 100%;
		height: 80rpx;
		font-size: 30rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		// margin-top: 50px;
		position: relative;
		top: 62px;
	}
	.loadingRotate{
		display: inline-block;
		font-size: 40rpx;
		animation: loadingAnimation 1s linear infinite;
	}
	@keyframes loadingAnimation{
		0%{
			transform: rotate(0);
		}
		100%{
			transform: rotate(360deg);
		}
	}
</style>

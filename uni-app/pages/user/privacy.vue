<template>
	<view class="privacy-wrap">
		<navigation title="隐私设置"></navigation>
		<view class="privacy" v-for="(item,index) in list" :key="index">
			<view class="title">
				{{ item.title }}
			</view>
			<view class="privacy-content">
				<view class="privacy-item" v-for="(value,indexItem) in item.listItem" :key="indexItem">
					<view class="text">
						{{ value.text }} 
					</view>
					<view class="switch-wrap">
						{{ value.state ? '打开' : '关闭' }}
						<view class="switch-icon" @click="switchState(index,indexItem)">
							<view class="switch-gray" v-show="!value.state"></view>
							<view class="switch-yellow" v-show="value.state"></view>
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				index: false,
				list: [
					{
						title: '通知设置',
						listItem: [
							{
								text: '接收新消息通知',
								state: false,
							},
							{
								text: '通知显示消息详情',
								state: false,
							}
						]
					},
					{
						title: '其他设置',
						listItem: [
							{
								text: '屏蔽手机通讯录的联系人',
								state: false,
							}
						]
					}
				]
			}
		},
		methods: {
			switchState(e,v){
				this.list[e].listItem[v].state  = !this.list[e].listItem[v].state
			},
			getConfing(){
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
			}
		}
	}
</script>

<style lang="scss" scoped>
	.privacy-wrap{
		width: 100%;
		height: 100vh;
		max-height: 100vh;
		background-color: rgba(246, 246, 246, 1);
	}
	.privacy{
		padding-top: 64rpx;
	}
	.title{
		font-size: 32rpx;
		color:rgba(74, 74, 74, 1);
		font-family: SourceHanSansCN-Medium;
		// font-weight: bold;
		padding: 0 32rpx;
	}
	.privacy-content{
		background-color: #fff;
		.privacy-item{
			display: flex;
			justify-content: space-between;
			align-items: center;
			margin: 36rpx 32rpx 0;
			height: 100rpx;
			&:nth-child(1){
				border-bottom: 2rpx solid rgba(246, 246, 246, 1);
			}
			.text{
				font-size: 28rpx;
				font-family: SourceHanSansCN-Regular;
				color: rgba(55, 55, 55, 1);
			}
			.switch-wrap{
				color: rgba(55, 55, 55, 1);
				font-size: 28rpx;
				font-family: SourceHanSansCN-Regular;
				display: flex;
				align-items: center;
				.switch-icon{
					width: 96rpx;
					height: 48rpx;
					background-color: rgba(245, 245, 245, 1);
					border-radius: 24rpx;
					padding: 4rpx 8rpx;
					margin-left: 18rpx;
					box-sizing: border-box;
					.switch-gray,
					.switch-yellow{
						width: 40rpx;
						height: 40rpx;
						border-radius: 100%;
					}
					.switch-yellow{
						background-color: rgba(243, 203, 96, 1);
						float: right;
					}
					.switch-gray{
						background-color: rgba(214, 214, 214, 1);
					}
				}
			}
		}
	}
</style>

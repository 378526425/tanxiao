<template>
	<view class="content">
		<view class="top">
			<view class="left" @click="goBack">
				<image src="../../static/001.png" mode=""></image>
			</view>
			<view class="right">
				<view class="item" @click="chanage(0)" :class="[currenIndex==0 ? 'tab-active' : 'tab-active1']">
					关注列表
				</view>
				<view class="item" @click="chanage(1)" :class="[currenIndex==1 ? 'tab-active' : 'tab-active1']">
					粉丝列表
				</view>
			</view>
		</view>
		<view class="examples">
			<view class="example" @touchmove.stop.prevent v-if="currenIndex == 0">
				<view class="content">
					<hr-pull-load @refresh='refresh' @loadMore='loadMore' :height='-1' :pullHeight='50' :maxHeight='100'
						:lowerThreshold='20' :bottomTips='bottomTips' :isTab='false' :isAllowPull="true"
						:contentTop="contentTop" ref='hrPullLoad'>
						<!-- 插入自己的数据-->
						<view class="list">
							<view class="list_item" v-for="(item,index) in exampleInfo" :key='index'>
								<view class="left">
									<view class="item">
										<image :src="item.headPortrait" mode=""></image>
										<view class="item1">
											<view class="f30">
												{{item.nickName}}
											</view>
											<view class="f31">
												{{item.city}}
											</view>
										</view>
									</view>
								</view>
								<view class="right">
									<view class="right1" @click="guanzhuStatus(item.id,index,true,item.followStatus)"
										v-if="!item.check && item.check1 == undefined">
										关注
									</view>
									<view class="right1" @click="guanzhuStatus(item.id,index,false,item.followStatus)"
										v-if="item.check  && item.check1 == undefined">
										取消关注
									</view>

									<view class="right1" @click="guanzhuStatus(item.id,index,true,item.followStatus)"
										v-if="!item.check1  && item.check == undefined">
										取消互关
									</view>
									<view class="right1" @click="guanzhuStatus(item.id,index,false,'huguan')"
										v-if="item.check1  && item.check == undefined">
										互关
									</view>
								</view>

							</view>

						</view>
					</hr-pull-load>
				</view>
			</view>
			<view class="example" @touchmove.stop.prevent v-if="currenIndex == 1">
				<view class="content">
					<hr-pull-load @refresh='refresh1' @loadMore='loadMore1' :height='-1' :pullHeight='50'
						:maxHeight='100' :lowerThreshold='20' :bottomTips='bottomTips1' :isTab='false'
						:isAllowPull="true" :contentTop="contentTop" ref='hrPullLoad'>
						<!-- 插入自己的数据-->
						<view class="list">
							<view class="list_item" v-for="(item,index) in exampleInfo1" :key='index'>
								<view class="left">
									<view class="item">
										<image :src="item.headPortrait" mode=""></image>
										<view class="item1">
											<view class="f30">
												{{item.nickName}}
											</view>
											<view class="f31">
												{{item.city}}
											</view>
										</view>
									</view>
								</view>
								<view class="right">
									<view class="right1" @click="guanzhuStatus(item.id,index,true,item.followStatus)"
										v-if="!item.check && item.check1 == undefined">
										关注
									</view>
									<view class="right1" @click="guanzhuStatus(item.id,index,false,item.followStatus)"
										v-if="item.check  && item.check1 == undefined">
										取消关注
									</view>

									<view class="right1" @click="guanzhuStatus(item.id,index,true,item.followStatus)"
										v-if="!item.check1  && item.check == undefined">
										取消互关
									</view>
									<view class="right1" @click="guanzhuStatus(item.id,index,false,'huguan')"
										v-if="item.check1  && item.check == undefined">
										互关
									</view>
								</view>

							</view>

						</view>
					</hr-pull-load>
				</view>
			</view>
		</view>
		<!-- <z-paging ref="paging" v-model="list" loading-more-enabled="false"  @query="queryList" v-if="currenIndex == 0">
			<custom-refresher slot="refresher" slot-scope="{refresherStatus}" :status="refresherStatus" />
			<custom-nomore slot="loadingMoreNoMore"></custom-nomore>
			<view class="list">
				<view class="list_item" v-for="(item,index) in list" :key="index">
					<view class="left">
						<view class="item">
							<image :src="item.headPortrait" mode=""></image>
							<view class="item1">
								<view class="f30">
									{{item.nickName}}
								</view>
								<view class="f31">
									{{item.city}}
								</view>
							</view>
						</view>
					</view>
					<view class="right">
						<view class="right1" @click="guanzhuStatus(item.id,index,true,item.followStatus)"
							v-if="!item.check && item.check1 == undefined">
							关注
						</view>
						<view class="right1" @click="guanzhuStatus(item.id,index,false,item.followStatus)"
							v-if="item.check  && item.check1 == undefined">
							取消关注
						</view>

						<view class="right1" @click="guanzhuStatus(item.id,index,true,item.followStatus)"
							v-if="!item.check1  && item.check == undefined">
							取消互关
						</view>
						<view class="right1" @click="guanzhuStatus(item.id,index,false,'huguan')"
							v-if="item.check1  && item.check == undefined">
							互关
						</view>
					</view>

				</view>

			</view>
		</z-paging>

		<z-paging ref="paging" v-model="list1" loading-more-enabled="false" @query="getFenSi" v-else>
			<custom-refresher slot="refresher" slot-scope="{refresherStatus}" :status="refresherStatus" />
			<custom-nomore slot="loadingMoreNoMore"></custom-nomore>
			<view class="list">
				<view class="list_item" v-for="(item,index) in list1" :key="index">
					<view class="left">
						<view class="item">
							<image :src="item.headPortrait" mode=""></image>
							<view class="item1">
								<view class="f30">
									{{item.nickName}}
								</view>
								<view class="f31">
									{{item.city}}
								</view>
							</view>
						</view>
					</view>
					<view class="right">
						<view class="right1" @click="fansStatus(item.id,index,true,item.followStatus)"
							v-if="!item.check && item.check1 == undefined">
							关注
						</view>
						<view class="right1" @click="fansStatus(item.id,index,false,item.followStatus)"
							v-if="item.check && item.check1 == undefined">
							取消互关
						</view>
						<view class="right1" @click="fansStatus(item.id,index,true,item.followStatus)"
							v-if="item.check1==false  && item.check == undefined">
							移除粉丝
						</view>
						<view class="right1" @click="fansStatus(item.id,index,false,'msr')"
							v-if="item.check1==true  && item.check == undefined">
							关注
						</view>
					</view>
				</view>

			</view>
		</z-paging>
	 -->
	</view>
</template>

<script>
	import hrPullLoad from '@/components/hr-pull-load/hr-pull-load.vue';
	export default {
		components: {
			hrPullLoad,
		},
		data() {
			return {
				currenIndex: 0,
				pageIndex: 1,
				pageSize: 10,
				list: [],
				list1: [],
				count: 0,
				totalCount: 0,
				totalCount1: 0,
				pageIndex1: 1,
				pageSize1: 10,

				exampleInfo: [],
				exampleInfo1: [],
				bottomTips: '',
				bottomTips1: '',
				contentTop: 0
			}
		},
		onLoad(e) {
			// if (this.currenIndex == 0) {
			// 	this.getGuanZhu();
			// }
			var that = this;
			that.currenIndex = e.type;

			that.queryList();
			that.getFenSi();

		},

		methods: {
			chanage(e) {
				this.currenIndex = e;
				// this.pageIndex = 1;
				// this.pageSize = 10;
				this.contentTop = 1;
				if(this.currenIndex == 0){
					if(this.pageIndex*this.pageSize > this.totalCount){
						console.log('123');
						this.bottomTips = 'nomore'
					}
				}else if(this.currenIndex == 1){
					if(this.pageIndex1*this.pageSize1 > this.totalCount1){
						console.log('234');
						this.bottomTips1 = 'nomore'
					}
				}
				this.$nextTick(function() {
					this.contentTop = 0;
				});
			},

			queryList(type) {
				var that = this;
				this.$request({
					path: '/community/fruser/follow/page',
					method: 'GET',
					data: {
						pageIndex: that.pageIndex,
						pageSize: that.pageSize
					},
					loading: false
				}).then(res => {
					that.totalCount = res.data.totalCount;
					res.data.rows.forEach(item => {
						if (item.followStatus == "FOLLOW") {
							item.check = true;
						} else if (item.followStatus == "FRIEND") {
							item.check1 = false;
						} else if (item.followStatus == "STRANGER") {
							item.check = true;
						} else {
							item.check1 = false;
						}
					})

					that.list = res.data.rows;
					if (type == 1) {
						that.exampleInfo = res.data.rows;
					} else {
						that.exampleInfo = that.exampleInfo.concat(res.data.rows);
					}
					if (this.exampleInfo.length >= res.data.totalCount) {
						that.bottomTips = "nomore";
					} else {
						that.bottomTips = "more";
					}
					that.$refs.hrPullLoad.reSet();
					// this.$refs.paging.complete(this.list);
					// this.$refs.paging.setLocalPaging(this.list)
				})

			},
			//自定义上拉加载更多
			loadMore() {

				if (this.pageIndex * this.pageSize < this.totalCount) {
					this.pageIndex++;
					this.queryList(2);
					this.bottomTips = "loading";
				}
				console.log(this.totalCount);


			},
			//自定义下拉刷新
			refresh() {
				this.pageIndex = 1;
				this.queryList(1);
			},
			loadMore1() {

				if (this.pageIndex1 * this.pageSize1 < this.totalCount1) {
					this.pageIndex1++;
					this.bottomTips1 = "loading";
					this.getFenSi(2);
				}
				console.log(this.totalCount1);

			},
			//自定义下拉刷新
			refresh1() {
				this.pageIndex1 = 1;
				this.getFenSi(1);
			},
			getFenSi(type) {
				var that = this;
				this.$request({
					path: '/community/fruser/fans/page',
					method: 'GET',
					data: {
						pageIndex: this.pageIndex1,
						pageSize: this.pageSize1
					},
					loading: false
				}).then(res => {
					that.totalCount1 = res.data.totalCount;
					res.data.rows.forEach(item => {

						if (item.followStatus == "FOLLOW") {
							item.check = false;
						} else if (item.followStatus == "FRIEND") {
							item.check = true;
						} else if (item.followStatus == "STRANGER") {
							item.check1 = true;
						} else if (item.followStatus == "FANS") {
							item.check1 = false;
						}
						// if (item.followStatus !== 'FANS') {
						// 	item.check = true;
						// } else {
						// 	item.check1 = false
						// }
					})
					if (type == 1) {
						that.exampleInfo1 = res.data.rows;
					} else {
						that.exampleInfo1 = that.exampleInfo1.concat(res.data.rows);
					}

					if (this.exampleInfo1.length >= res.data.totalCount) {
						that.bottomTips1 = "nomore";
					} else {
						that.bottomTips1 = "more";
					}
					that.$refs.hrPullLoad.reSet();
					// that.$refs.hrPullLoad.reSet();
					// this.$refs.paging.complete(this.list1);
					// this.$refs.paging.setLocalPaging(this.list1)
				})
			},
			goBack() {
				uni.navigateBack({
					delta: 1
				})
			},
			// 粉丝状态
			fansStatus(id, index, type, status) {
				// type = false 移除粉丝
				this.list1[index].check = type;
				if (this.list1[index].check == true && this.list1[index].followStatus !== 'FANS') {
					this.$request({
						path: '/community/fruser/follow/user/' + id,
						method: 'GET',
						loading: false,
					}).then(res => {

					})
				} else if (this.list1[index].check == false && this.list1[index].followStatus == 'FRIEND') {
					this.$request({
						path: '/community/fruser/cancel/follow/' + id,
						method: 'GET',
						loading: false,
					}).then(res => {

					})
				}
				if (status == 'FANS') {
					delete this.list1[index].check;
					this.list1[index].check1 = !this.list1[index].check1;
					this.$request({
						path: '/community/fruser/remove/fans/' + id,
						method: 'GET',
						loading: false,
					}).then(res => {

					})
				} else if (status == 'msr') {
					delete this.list1[index].check;
					this.list1[index].check1 = !this.list1[index].check1;
					this.$request({
						path: '/community/fruser/follow/user/' + id,
						method: 'GET',
						loading: false,
					}).then(res => {
						this.$set(this.list1[index], 'check', true);
						console.log(this.list1[index]);
						delete this.list1[index].check1;
					})
				}


			},
			guanzhuStatus(id, index, type, status) {
				// type = false 取消关注 
				this.list[index].check = type;
				if (status == "FRIEND") {
					this.list[index].check1 = !this.list[index].check1;
					delete this.list[index].check
					this.$request({
						path: '/community/fruser/cancel/follow/' + id,
						method: 'GET',
						loading: false,
					}).then(res => {

					})
				} else if (status == "huguan") {
					this.list[index].check1 = !this.list[index].check1;
					delete this.list[index].check;
					this.$request({
						path: '/community/fruser/follow/user/' + id,
						method: 'GET',
						loading: false,
					}).then(res => {

					})
				}
				if (this.list[index].check == false) {

					this.$request({
						path: '/community/fruser/cancel/follow/' + id,
						method: 'GET',
						loading: false,
					}).then(res => {

					})
				} else if (this.list[index].check == true) {

					this.$request({
						path: '/community/fruser/follow/user/' + id,
						method: 'GET',
						loading: false,
					}).then(res => {

					})
				}


			},

		}
	}
</script>

<style lang="scss">
	.content {
		height: calc(100vh - 10px);

		.item {
			position: relative;
			height: 150rpx;
			display: flex;
			align-items: center;
			justify-content: space-between;
			padding: 0rpx 30rpx;
		}

		.item-detail {
			padding: 5rpx 15rpx;
			border-radius: 10rpx;
			font-size: 28rpx;
			color: white;
			background-color: #007AFF;
		}

		.item-line {
			position: absolute;
			bottom: 0rpx;
			left: 0rpx;
			height: 1px;
			width: 100%;
			background-color: #eeeeee;
		}

		.tab-active {
			color: #000;
			font-weight: bold;
			font-size: 16px;
		}

		.tab-active1 {
			color: #7A7A7A;
			font-size: 14px;
		}

		.top {
			display: flex;
			align-items: center;
			padding: 20px 10px 10px 10px;
			background: #fff;
			position: absolute;
			top: 0;
			width: 100%;
			z-index: 99;
			height: 40px;
			border-bottom: 1px solid #ededed;

			.left {
				image {
					width: 22px;
					height: 22px;
				}
			}

			.right {
				display: flex;
				align-items: center;
				margin-top: -5px;
				margin-left: 36px;

				.item {
					padding: 0 10px;

				}
			}
		}

		.list {
			position: relative;
			top: 70px;
			width: 95%;
			z-index: 1;
			font-family: SourceHanSansCN-Medium;

			.list_item {
				display: flex;
				align-items: center;
				justify-content: space-between;
				border-bottom: 1px solid #F6F6F6;
				// margin:0 10px;
				height: 80px;
			}

			.left {
				image {
					width: 51px;
					height: 51px;
					border-radius: 51px;
				}

				.item {
					display: flex;

					.item1 {
						margin-left: 12px;

						.f30 {
							font-size: 16px;
							color: #000;
						}

						.f31 {
							font-size: 14px;
							color: #F3CB60;
							margin-top: 5px;
						}
					}
				}
			}

			.right {
				.right1 {
					width: 80px;
					height: 30px;
					text-align: center;
					line-height: 30px;
					border-radius: 30px;
					font-size: 14px;
					color: #000;
					background: #F3CB60;
				}
			}
		}
	}
</style>

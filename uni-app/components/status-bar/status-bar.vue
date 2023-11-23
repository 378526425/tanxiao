<template>
	<block>
		<view :style="{ height: statusBarHeight }" class="uni-status-bar">
			<slot />
		</view>
	</block>
	
</template>

<script>
	// #ifdef APP-PLUS
	const statusBarHeight = uni.getSystemInfoSync().statusBarHeight + 'px'
	// #endif
	// #ifndef APP-PLUS
	const statusBarHeight = '27px'
	// #endif
	export default {
		name: 'status-bar',
		data() {
			return {
				statusBarHeight: statusBarHeight
			}
		},
	}
</script>

<style scoped>
	.uni-status-bar {
		width: 750rpx;
		height: 20px;
		/* opacity: 0; */
		/* background-color: blue; */
		height: var(--status-bar-height);

	}
</style>
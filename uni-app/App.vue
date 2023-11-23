<script>
	export default {
		globalData: {
			code: '',
			phone: '',
			sex: ''
		},
		onLaunch: function() {
			uni.hideTabBar()
			
			// #ifdef APP-PLUS
			var color = plus.android.newObject('android.graphics.Color');
			var ac = plus.android.runtimeMainActivity();
			var c2int = plus.android.invoke(color, 'parseColor', '#02101f');
			var win = plus.android.invoke(ac, 'getWindow');
			plus.android.invoke(win, 'setNavigationBarColor', c2int);
			plus.android.invoke(win, 'setTabBarStyle', c2int);
			// #endif

		},
		onShow: function() {
			
		},
		onHide: function() {},

		methods: {}
	}
</script>

<style lang="scss">
	/*每个页面公共css */
	.icon_right {
		width: 10rpx;
		height: 18rpx;
		background-image: url('static/icon_right.png');
		background-repeat: no-repeat;
		background-size: cover;
	}

	.icon_right_white {
		width: 18rpx;
		height: 30rpx;
		background-image: url('static/icon-right_white.png');
		background-repeat: no-repeat;
		background-size: cover;
	}

	.empty {
		width: 100%;
		height: 200rpx;
	}

	@font-face{
		font-family: 'SourceHanSansCN-Bold';
		src: url('@/static/fonts/SourceHanSansCN-Bold.ttf');
	}
	@font-face{
		font-family: 'SourceHanSansCN-ExtraLight';
		src: url('@/static/fonts/SourceHanSansCN-ExtraLight.ttf');
	}
	@font-face{
		font-family: 'SourceHanSansCN-Heavy';
		src: url('@/static/fonts/SourceHanSansCN-Heavy.ttf');
	}
	@font-face{
		font-family: 'SourceHanSansCN-Light';
		src: url('@/static/fonts/SourceHanSansCN-Light.ttf');
	}
	@font-face{
		font-family: 'SourceHanSansCN-Medium';
		src: url('@/static/fonts/SourceHanSansCN-Medium.ttf');
	}
	@font-face{
		font-family: 'SourceHanSansCN-Normal';
		src: url('@/static/fonts/SourceHanSansCN-Normal.ttf');
	}
	@font-face{
		font-family: 'SourceHanSansCN-Regular';
		src: url('@/static/fonts/SourceHanSansCN-Regular.ttf');
	}
	@font-face{
		font-family: 'ExtraLight';
		src: url('@/static/fonts/ExtraLight.ttf');
	}
	
	@font-face{
		font-family: 'arial';
		src: url('@/static/fonts/arial.ttf');
	}
	@font-face{
		font-family: 'arialbd';
		src: url('@/static/fonts/arialbd.ttf');
	}
	@font-face{
		font-family: 'arialbi';
		src: url('@/static/fonts/arialbi.ttf');
	}
	@font-face{
		font-family: 'ariali';
		src: url('@/static/fonts/ariali.ttf');
	}
	@font-face{
		font-family: 'ARIALN';
		src: url('@/static/fonts/ARIALN.ttf');
	}
	@font-face{
		font-family: 'ARIALNB';
		src: url('@/static/fonts/ARIALNB.ttf');
	}	
	@font-face{
		font-family: 'ARIALNBI';
		src: url('@/static/fonts/ARIALNBI.ttf');
	}
	@font-face{
		font-family: 'ARIALNI';
		src: url('@/static/fonts/ARIALNI.ttf');
	}
	@font-face{
		font-family: 'ariblk';
		src: url('@/static/fonts/ariblk.ttf');
	}
</style>

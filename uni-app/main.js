import App from './App'
import store from './store'
import request from "./utils/request.js"
const domain = require('./utils/domain.js')
Vue.prototype.$store=store
// #ifndef VUE3
import Vue from 'vue'
Vue.prototype.$domain = domain.url
Vue.prototype.$request = request
Vue.config.productionTip = false
App.mpType = 'app'
// 如此配置即可
const app = new Vue({
	store,
    ...App
})
app.$mount()

// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif
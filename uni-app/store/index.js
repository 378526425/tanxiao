import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)


const store = new Vuex.Store({
	state: {
		matchInfo:{}
	},
	mutations: {
		setMatchInfo(state,data){
			console.log(data,'data');
			state.matchInfo = data;
		}
	}
})

export default store
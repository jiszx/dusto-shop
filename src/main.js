import Vue from 'vue';
import App from './App.vue';
import router from './router'
import store from './store'
import fetch from 'assets/js/fetch'
import * as mUtils from 'assets/js/mUtils'
import FastClick from 'fastclick'
//高德地图组件
import VueAMap from 'vue-amap';
Vue.use(VueAMap);
// 初始化vue-amap
VueAMap.initAMapApiLoader({
  // 高德的key
  key: '37bfdc3a9771f8ba329f0ce1ddc05dc0',
  // 插件集合
  // 高德 sdk 版本，默认为 1.4.4
  v: '1.4.4'
});



import { Cookie } from 'assets/js/mUtils'

//全局挂载axiso
Vue.prototype.$fetch = fetch;
Vue.prototype.$mUtils = mUtils;

import {
  Lazyload
} from 'vant'

Vue.use(Lazyload);



/**
 * 引入公共css
 */
import '@/assets/css/common.css'

FastClick.attach(document.body);

Vue.config.productionTip = false;

router.beforeEach((to, from, next) => {
  //todo 路由拦截待完善

  next();
});
router.afterEach((to, from, next) => {
  document.title = to.meta.title||'大东鞋业';
});

/**
 * 自定义指令
 */
Vue.directive('permission-click', {
  bind: (el, binding, vnode) => {
    el.addEventListener('click', (e) => {
      if (!store.getters.hasLogin) {
        store.dispatch('showLogin')
      } else {
        typeof binding.value === 'function' && binding.value()
      }
    })
  }
});


new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
});


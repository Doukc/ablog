// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App'
import Axios from './components/config/AxiosConfig'
import animated from 'animate.css'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import router from './router'

var axios = require('axios')
axios.defaults.baseURL = Axios.axiosBaseURL

Vue.use(ElementUI)
Vue.use(animated)
Vue.config.productionTip = false
Vue.prototype.$axios = axios
Vue.prototype.router = router

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

NProgress.configure({
  easing: 'ease',
  speed: 500,
  showSpinner: false,
  trickleSpeed: 200,
  minimum: 0.3
})

router.beforeEach((to, from, next) => {
  // 每次切换页面时，调用进度条
  NProgress.start()

  // 这个一定要加，没有next()页面不会跳转的。这部分还不清楚的去翻一下官网就明白了
  next()
})

router.afterEach(() => {
  // 在即将进入新的页面组件前，关闭掉进度条
  NProgress.done()
})

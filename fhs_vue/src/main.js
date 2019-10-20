import Vue from 'vue'
import HttpVueLoader from 'http-vue-loader'
import Cookies from 'js-cookie'
import ErtComponents from '@/components'

import 'normalize.css/normalize.css' // a modern alternative to CSS resets
import '@/utils/map/gaodeKey.js' // 引入地图相关

import Element from 'element-ui'
import './styles/element-variables.scss'
import yxUtils from '@/utils/yxUtil'

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import i18n from './lang' // internationalization
import './icons' // icon
import './permission' // permission control
import './utils/error-log' // error log
import request from './utils/request'

import * as filters from './filters' // global filters
import vRegion from 'v-region'
Vue.use(vRegion)
/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */
// import { mockXHR } from '../mock'
// if (process.env.NODE_ENV === 'production') {
//   mockXHR()
// }
Vue.use(ErtComponents)
Vue.use(HttpVueLoader)
Vue.use(VueQuillEditor)
Vue.prototype.request = request
// 添加全局变量
window.U = yxUtils
Vue.use(Element, {
  size: Cookies.get('size') || 'medium', // set element-ui default size
  i18n: (key, value) => i18n.t(key, value)
})

// register global utility filters
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  i18n,
  render: h => h(App)
})

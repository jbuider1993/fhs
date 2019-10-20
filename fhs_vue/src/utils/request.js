import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'
import settings from '@/settings.js'
// create an axios instance
const service = axios.create({
  // baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  retry: 5,
  retryDelay: 1000,
  timeout: 15000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent
    config.headers['loginType'] = settings['systemType']
    config.headers['source'] = settings['source']
    if (store.getters.token) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situation
      config.headers['token'] = getToken()
      // config.headers['Content-type'] =''
    }
    return config
  },
  error => {
    // do something with request error
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    /**
     * 下面的注释为通过response自定义code来标示请求状态，当code返回如下情况为权限有问题，登出并返回到登录页
     * 如通过xmlhttprequest 状态码标识 逻辑可写在下面error中
     */
    const res = response.data


    if (res.code === 403) {
      // MessageBox.confirm('登录过期，请重新登录', '确定登出', {
      //   confirmButtonText: '重新登录',
      //   cancelButtonText: '取消',
      //   type: 'warning'
      // }).then(() => {
      //   store.dispatch('user/logout').then(() => {
      //     location.reload() // 为了重新实例化vue-router对象 避免bug
      //   })
      // }).catch(() => {
      //   store.dispatch('user/logout').then(() => {
      //     location.reload() // 为了重新实例化vue-router对象 避免bug
      //   })
      // })
      store.dispatch('user/logout').then(() => {
        location.reload() // 为了重新实例化vue-router对象 避免bug
      })
      return Promise.reject(res.message)
    }
    if (res.code !== 200) {
      Message({
        message: res.message,
        type: 'error',
        duration: 3 * 1000
      })
      return Promise.reject(res.message)
    }
    if (response.status === 200 && response.data.code === 200) {
      return Promise.resolve(res)
    } else {
      return Promise.reject(res)
    }
  },
  error => {
    error.message = '系统异常，请稍后在试'
    return Promise.reject(error)
  }
)

export default service

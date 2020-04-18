import request from '@/utils/request'

// 登录方法
export function login(userLoginName, password, identifyCode, uuid) {
  const data = {
    userLoginName,
    password,
    identifyCode,
    uuid
  }
  return request({
    url: '/vueLogin',
    method: 'post',
    data: data
  })
}

// 获取用户详细信息
export function getInfo() {
  return request({
    url: '/getUserForVue',
    method: 'get'
  })
}

// 退出方法
export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: '/defaultKaptchaForVue',
    method: 'get'
  })
}

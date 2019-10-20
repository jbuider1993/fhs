import request from '@/utils/request'
import crtModule from '@/config'

/**
 * @description 平台登陆
 * @param params
 */
export function login(params) {
  return request({
    url: crtModule.BASE_SYSTEM + '/vueLogin',
    method: 'POST',
    params
  })
}

/**
 * 获取用户信息
 */
export function getUserInfo(params) {
  return request({
    url: crtModule.BASE_SYSTEM + '/getUserByToken',
    method: 'POST',
    params
  })
}


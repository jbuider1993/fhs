import request from '@/utils/request.js'
import crtModule from '@/config'

export function findUserById(params) {
  return request({
    url: `${crtModule.BASE_SYSTEM}/user/findUerById`,
    method: 'get',
    params
  })
}

/**
 * 添加用户
 * @param data
 */
export function addUser(data) {
  return request({
    url: `${crtModule.BASE_SYSTEM}/user/add`,
    method: 'POST',
    data: data
  })
}
/**
 * @description 轮播图
 * @param params
 */
export function preview(params) {
  return request({
    url: crtModule.PLATFORM_CMS + '/websiteMap/preview',
    method: 'POST',
    params
  })
}

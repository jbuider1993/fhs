import request from '@/utils/request'
import crtModule from '@/config'

/**
 * @description 获取列表
 * @param query
 */
export function page(query) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/opu/pages',
    method: 'POST',
    data: query
  })
}
/**
 * @description 新增接口
 * @param data
 */
export function addObj(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/opu/save',
    method: 'POST',
    data: data
  })
}
/**
 * @description 通过id获取详情
 * @param data
 */
export function getObj(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/opu/details',
    method: 'POST',
    data: data
  })
}
/**
 * @description 通过id删除
 * @param id
 */
export function delObj(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/opu/delete',
    method: 'POST',
    data: data
  })
}
/**
 * @description 修改接口
 * @param id
 */
export function putObj(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/opu/modify',
    method: 'POST',
    data: data
  })
}

/**
 * @description 获取全部用户
 *
 */
export function getUserListAll() {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/opu/userInfoList',
    method: 'POST'
  })
}

/**
 * 根据组织code获取岗位
 * @param params
 */
export function getPostByOrgCode(params) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/opu/postListByOrgCode',
    method: 'POST',
    params
  })
}


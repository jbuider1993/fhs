import request from '@/utils/request'
import crtModule from '@/config'

/**
 * @description 获取列表
 * @param query
 */
export function page(query) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/usm/pages',
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
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/usm/save',
    method: 'POST',
    data: data
  })
}

/**
 * @description 通过id获取详情
 * @param id
 */
export function getObj(params) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/usm/findUserByUserCode',
    method: 'POST',
    params
  })
}

/**
 * @description 通过id删除
 * @param id
 */
export function delObj(params) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/usm/delete',
    method: 'POST',
    params
  })
}
/**
 * @description 修改接口
 * @param id
 */
export function putObj(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/usm/modify',
    method: 'POST',
    data: data
  })
}

/**
 * @description 重置密码
 * @param id
 */
export function resetPassword(params) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/usm/resetPassWord',
    method: 'POST',
    params
  })
}


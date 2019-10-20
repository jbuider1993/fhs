import request from '@/utils/request'
import crtModule from '@/config'

/**
 * @description 获取组织树
 * @param params
 */
export function fetchTree(params) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/orm/tree',
    method: 'POST',
    params
  })
}

/**
 * 获取组织分页列表
 * @param data
 */
export function page(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/orm/pages',
    method: 'POST',
    data: data
  })
}

/**
 * 获取全部组织列表
 * @param data
 */
export function getOrgListAll(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/orm/orgList',
    method: 'POST'
  })
}

/**
 * @description 获取字典类型树
 * @param params
 */
export function addObj(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/orm/save',
    method: 'POST',
    data: data
  })
}
/**
 * @description 查询详情
 * @param params
 */
export function getObj(params) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/orm/entity',
    method: 'POST',
    params
  })
}

/**
 * @description 更新
 * @param params
 */
export function putObj(id, data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/orm/modify?id=' + id,
    method: 'POST',
    data: data
  })
}

/**
 * @description 删除
 * @param params
 */
export function delObj(params) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/orm/delete',
    method: 'POST',
    params
  })
}


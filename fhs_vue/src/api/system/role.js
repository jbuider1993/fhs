import request from '@/utils/request'
import crtModule from '@/config'
import settings from '@/settings'
/**
 * @description 获取列表
 * @param query
 */
export function page(query, systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/rom/findRoleList',
    method: 'POST',
    data: query
  })
}

/**
 * @description 新增接口
 * @param data
 */
export function addObj(data, systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/rom/save',
    method: 'POST',
    data: data
  })
}

/**
 * @description 通过id获取详情
 * @param id
 */
export function getObj(params, systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/rom/findById',
    method: 'POST',
    params
  })
}
// /MIDDLE-MAINDATA-USER/middle/rom/modify
/**
 * @description 通过id删除
 * @param id
 */
export function delObj(params, systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/rom/delete',
    method: 'POST',
    params
  })
}

/**
 * @description 修改接口
 * @param id
 */
export function putObj(id, data, systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/rom/modify?id=' + id,
    method: 'POST',
    data: data
  })
}

/**
 * @description 查询全部
 */
export function getRolesListAll(systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/orm/findRoles',
    method: 'POST'
  })
}


import request from '@/utils/request'
import crtModule from '@/config'
import settings from '@/settings'
/**
 * @description 获取字典类型树
 * @param params
 * @param systemType
 */
export function fetchTree(params, systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/mem/tree',
    method: 'POST',
    params
  })
}

/**
 * @description 添加资源
 * @param data
 * @param systemType
 */
export function addObj(data, systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/mem/save',
    method: 'POST',
    data: data
  })
}

/**
 * @description 根据Id获取实体
 * @param params
 * @param systemType
 */
export function getObj(params, systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/mem/findById',
    method: 'POST',
    params
  })
}

/**
 * @description 更新
 * @param id
 * @param data
 * @param systemType
 */
export function putObj(id, data, systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/mem/modify?id=' + id,
    method: 'POST',
    data: data
  })
}

/**
 * @description 删除
 * @param params
 * @param systemType
 */
export function delObj(params, systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/mem/delete',
    method: 'POST',
    params
  })
}

/**
 *@description 设置资源对应的数据库表
 * @param data
 */
export function setListByTable(data, systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/mem/saveListByTables ',
    method: 'POST',
    data: data
  })
}
/**
 *@description 获取资源对应的数据库表
 * @param data
 */
export function getListByTable(data, systemType = 1) {
  settings['source'] = systemType

  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/mem/listByTables ',
    method: 'POST',
    data: data
  })
}

/**
 *@description 移除数据库关联表
 * @param data
 */
export function delListByTables(data, systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/mem/delListByTables ',
    method: 'POST',
    data: data
  })
}


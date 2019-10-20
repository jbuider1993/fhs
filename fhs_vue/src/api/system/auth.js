import request from '@/utils/request'
import crtModule from '@/config'
import settings from '@/settings'

/**
 * @description 获取列表
 * @param query
 */
export function getAuthList(params, systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/atm/atmDetail',
    method: 'POST',
    params
  })
}
/**
 * @description 添加
 * @param data
 */
export function addObj(data, systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/atm/saveAtm',
    method: 'POST',
    data: data
  })
}
/**
 * @description 获取实体
 * @param params
 */
export function getObj(params, systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/mem/findResource',
    method: 'POST',
    params
  })
}
/**
 * @description 更新
 * @param params
 */
export function putObj(data, systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/mem/modifyResource',
    method: 'POST',
    data: data
  })
}

/**
 * @description 删除
 * @param params
 */
export function delObj(params, systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/mem/delResource',
    method: 'POST',
    params
  })
}


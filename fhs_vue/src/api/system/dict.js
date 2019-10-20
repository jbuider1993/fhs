import request from '@/utils/request'
import crtModule from '@/config'
/**
 * @description 获取字典类型树
 * @param params
 */
export function page(params) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/dict/findTypeTree',
    method: 'POST',
    params
  })
}

/**
 * 通过ID查询类型详情
 * @param params
 */
export function getDictType(params) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/dict/findTypeById',
    method: 'POST',
    params
  })
}

/**
 * 通过ID查询类型详情
 * @param params
 */
export function putDictType(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/dict/updateByType',
    method: 'POST',
    data: data
  })
}

/**
 * 删除字典类型
 * @param params
 */
export function delDictType(params) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/dict/deleteTypeById',
    method: 'POST',
    params
  })
}
/**
 * 添加类型
 * @param params
 */
export function addDictType(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/dict/saveType',
    method: 'POST',
    data: data
  })
}
/**
 * @description 根据typeCode查询字典
 * @param data
 */
export function getDictListByType(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/dict/findPageEntityListByType',
    method: 'POST',
    data: data
  })
}
/**
 * 添加字典
 * @param data
 */
export function addDictObj(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/dict/saveEntity',
    method: 'POST',
    data: data
  })
}
/**
 * 删除字典
 * @param data
 */
export function delDictObj(params) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/dict/deleteEntityById',
    method: 'POST',
    params
  })
}

/**
 * 修改字典
 * @param data
 */
export function putDictObj(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/dict/updateByEntity',
    method: 'POST',
    data: data
  })
}

/**
 * @description 根据Id获取字典详情
 * @param data
 */
export function getDictObj(params) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/dict/findById',
    method: 'POST',
    params
  })
}

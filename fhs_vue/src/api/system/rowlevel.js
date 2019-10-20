import request from '@/utils/request'
import crtModule from '@/config'
import settings from '@/settings'
/**
 * @description 获取全部数据库表
 * @param id
 */
export function getDataTableList() {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/dataAuth/tables',
    method: 'POST'
  })
}
export function getCloumsByDataTable(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/dataAuth/columns',
    method: 'POST',
    data
  })
}

/**
 * 新增行级权限
 * @param data
 */
export function setDataAuth(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/dataAuth/save',
    method: 'POST',
    data
  })
}

/**
 * 删除行级权限
 * @param data
 */
export function delDataAuth(params) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/dataAuth/delete',
    method: 'POST',
    params
  })
}

/**
 * 获取行级权限列表
 * @param data
 */
export function page(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/dataAuth/pages',
    method: 'POST',
    data
  })
}

/**
 * @description 根据code获取数据库表
 * @param data
 */
export function getListTableByCode(data, systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/mem/listByTables',
    method: 'POST',
    data: data
  })
}

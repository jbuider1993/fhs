import request from '@/utils/request'
import crtModule from '@/config'

/**
 * 根据组织ID获取角色分页列表
 * @param data
 */
export function page(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/orm/findRolePagesByOrgCode',
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
 * @description 添加角色
 * @data data
 */
export function addObj(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/orm/saveOrgRole',
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
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/orm/findOrgRole',
    method: 'POST',
    params
  })
}
/**
 * @description 更新
 * @param params
 */
export function putObj(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/orm/modifyOrgRole',
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
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/orm/delRole',
    method: 'POST',
    params
  })
}

/**
 * 根据组织角色编码获取用户列表
 * @param data
 */
export function getRoleUserByRoleId(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/oru/pages',
    method: 'POST',
    data: data
  })
}

/**
 * @description 给角色设置用户
 * @param data
 */
export function saveRoleUser(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/oru/save',
    method: 'POST',
    data: data
  })
}

export function delRoleUser(params) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/oru/delete',
    method: 'POST',
    params
  })
}

/**
 * @description 根据orgCode获取角色列表
 * @param params
 */
export function getRolesByOrgCode(params) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/orm/findOrgRoles',
    method: 'POST',
    params
  })
}

/**
 * @description 复制角色信息
 * @param data
 */
export function copyOrgRole(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/orm/copyOrgRole',
    method: 'POST',
    data
  })
}


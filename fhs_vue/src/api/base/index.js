import request from '@/utils/request.js'
import crtModule from '@/config'
import settings from '@/settings'
/**
 * 上传
 * @param params
 * @constructor
 */
export function uploadFile(params) {
  return request({
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    url: `${crtModule.MIDDLE_FILEUPLOAD}/api/upload/fildUploadPublicByInputStream`,
    method: 'POST',
    data: params
  })
}

/**
 * 下载
 * @param params
 * @constructor
 */
export function downLoadFile(params) {
  return request({
    url: `${crtModule.MIDDLE_FILEUPLOAD}/api/upload/downloadByFileId`,
    method: 'POST',
    params
  })
}

/**
 * 图片预览
 * @param params  5c76d66b34134cff80c45f672d1c20a8
 *  @description fileId:ca18c927ac334c4e8ef06eafe737d474
 */
export function previewImg(data) {
  return request({
    url: crtModule.MIDDLE_FILEUPLOAD + '/api/upload/findUrlByFileId',
    method: 'POST',
    data: data
  })
}

/**
 *
 *@description 导出 泛型
 * @constructor
 */
export function ExportExcel() {
  return window.open(crtModule.PLATFORM_MODULE_UAC + '/uac/userdoc/exportExcel')
}

/**
 * 导入
 * @param data
 * @constructor
 */
export function ImportExcel(data) {
  return request({
    url: crtModule.PLATFORM_MODULE_UAC + '/uac/userdoc/importExcel',
    method: 'POST',
    data: data
  })
}

/**
 * 字典
 * @param data
 * @constructor
 */
export function queryDicByCode(data) {
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/dict/findEntityListByType',
    method: 'POST',
    data: data
  })
}

/**
 * @description 获取所有数据库名
 */
export function getDataBaseAll(systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/dataAuth/database',
    method: 'POST'
  })
}

/**
 * @description 获取所有数据库名
 */
export function getTableDataByName(data,systemType = 1) {
  settings['source'] = systemType
  return request({
    url: crtModule.MIDDLE_MAINDATA_USER + '/middle/dataAuth/tables',
    method: 'POST',
    data: data
  })
}

/**
 * @description 用户信息同步到redis缓存中
 */
export function syncSysInfoAndUserInfo() {
  return request({
    url: crtModule.BASE_SYSTEM + '/middle/usm/synchronismUser',
    method: 'POST'
  })
}

/**
 * @description 获取全部字典类型和字典
 */
export function getDictTypeAndDict() {
  return request({
    url: crtModule.BASE_SYSTEM + '/middle/dict/dictList',
    method: 'POST'
  })
}



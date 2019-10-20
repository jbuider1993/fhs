/**
 * Created by Sun Update on 16/11/18.
 */

/* 是否是公司邮箱*/
export function isEmail(str) {
  // const reg = /^[a-z0-9](?:[-_.+]?[a-z0-9]+)*@wallstreetcn\.com$/i
  var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/
  if (!str) {
    return { 'status': 0, 'msg': '请输入邮箱' }
  }
  if (reg.test(str)) {
    return { 'status': 1, 'msg': '验证通过' }
  } else {
    return { 'status': 0, 'msg': '请输入正确的邮箱地址' }
  }
}

/**
 * @description 是否含有中文
 * @param str
 * @returns {boolean}
 */
export function isChineseChar(str) {
  const reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/
  if (!str) {
    return { 'status': 0, 'msg': '不能为空' }
  }
  if (!reg.test(str)) {
    return { 'status': 1, 'msg': '验证通过' }
  } else {
    return { 'status': 0, 'msg': '非法字符、不能包含中文' }
  }
}

/* 合法uri*/
export function validateURL(textval) {
  const urlregex = /^(https?|ftp):\/\/([a-zA-Z0-9.-]+(:[a-zA-Z0-9.&%$-]+)*@)*((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]?)(\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}|([a-zA-Z0-9-]+\.)*[a-zA-Z0-9-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(:[0-9]+)*(\/($|[a-zA-Z0-9.,?'\\+&%$#=~_-]+))*$/
  return urlregex.test(textval)
}

/* 小写字母*/
export function validateLowerCase(str) {
  const reg = /^[a-z]+$/
  return reg.test(str)
}

/* 验证key*/
// export function validateKey(str) {
//     var reg = /^[a-z_\-:]+$/;
//     return reg.test(str);
// }

/* 大写字母*/
export function validateUpperCase(str) {
  const reg = /^[A-Z]+$/
  return reg.test(str)
}

/* 大小写字母*/
export function validatAlphabets(str) {
  const reg = /^[A-Za-z]+$/
  return reg.test(str)
}

/* 是否是手机号*/
export const isTelePhone = function(tel) {
  const rep = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/
  if (!tel) {
    return { 'status': 0, 'msg': '请输入手机号' }
  }
  if (rep.test(tel)) {
    return { 'status': 1, 'msg': '验证通过' }
  } else {
    return { 'status': 0, 'msg': '请输入正确的手机号' }
  }
}

export const formValidUrl = function(url) {
  debugger
  const reg = /^(https?:|mailto:|tel:)/
  if (!url) {
    return { 'status': 0, 'msg': '请输入网址' }
  }
  if (reg.test(url)) {
    return { 'status': 1, 'msg': '验证通过' }
  } else {
    return { 'status': 0, 'msg': '请输入正确的网址' }
  }
}

/* 身份证号是否正确*/
export const isIdCard = function(id) {
  const format = /^(([1][1-5])|([2][1-3])|([3][1-7])|([4][1-6])|([5][0-4])|([6][1-5])|([7][1])|([8][1-2]))\d{4}(([1][9]\d{2})|([2]\d{3}))(([0][1-9])|([1][0-2]))(([0][1-9])|([1-2][0-9])|([3][0-1]))\d{3}[0-9xX]$/

  if (!id) {
    return { 'status': 0, 'msg': '请输入身份证号码' }
  }
  // 号码规则校验
  if (!format.test(id)) {
    return { 'status': 0, 'msg': '身份证号码不合规' }
  }
  // 区位码校验
  // 出生年月日校验  前正则限制起始年份为1900;
  const year = id.substr(6, 4); var // 身份证年
    month = id.substr(10, 2); var // 身份证月
    date = id.substr(12, 2); var // 身份证日
    time = Date.parse(month + '-' + date + '-' + year); var // 身份证日期时间戳date
    now_time = Date.parse(new Date()); var // 当前时间戳
    dates = (new Date(year, month, 0)).getDate()// 身份证当月天数
  if (time > now_time || date > dates) {
    return { 'status': 0, 'msg': '出生日期不合规' }
  }
  return { 'status': 1, 'msg': '校验通过' }
}

/* 是否是中文*/
export const isHanZi = function(rule, value, callback) {
  const TEL_REGEXP = /^[\u4e00-\u9fa5]+$/
  if (TEL_REGEXP.test(value)) {
    callback()
  } else {
    callback(new Error('请输入中文'))
  }
}
/**
 * 正整数校验
 */
export const integerP = (rule, value, callback) => {
  if (value && !(/^[1-9]\d*$/).test(value)) {
    callback(new Error('请输入正整数'))
  } else {
    callback()
  }
}

/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validUsername(str) {
  const valid_map = ['admin', 'editor']
  return valid_map.indexOf(str.trim()) >= 0
}

/**
 * @param {string} url
 * @returns {Boolean}
 */
export function validURL(url) {
  const reg = /^(https?|ftp):\/\/([a-zA-Z0-9.-]+(:[a-zA-Z0-9.&%$-]+)*@)*((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]?)(\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}|([a-zA-Z0-9-]+\.)*[a-zA-Z0-9-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(:[0-9]+)*(\/($|[a-zA-Z0-9.,?'\\+&%$#=~_-]+))*$/
  return reg.test(url)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validLowerCase(str) {
  const reg = /^[a-z]+$/
  return reg.test(str)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validUpperCase(str) {
  const reg = /^[A-Z]+$/
  return reg.test(str)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validAlphabets(str) {
  const reg = /^[A-Za-z]+$/
  return reg.test(str)
}

/**
 * @param {string} email
 * @returns {Boolean}
 */
export function validEmail(email) {
  const reg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
  return reg.test(email)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function isString(str) {
  if (typeof str === 'string' || str instanceof String) {
    return true
  }
  return false
}

/**
 * @param {Array} arg
 * @returns {Boolean}
 */
export function isArray(arg) {
  if (typeof Array.isArray === 'undefined') {
    return Object.prototype.toString.call(arg) === '[object Array]'
  }
  return Array.isArray(arg)
}

/**
 *
 * 自定义表单验证
 * created By Sun 2019/08/05
 *
 */
import { isTelePhone, isIdCard, isEmail, isChineseChar, formValidUrl } from './validate'

export const checkUrl = (rule, value, callback) => {
  debugger
  const res = formValidUrl(value)
  if (res.status === 1) {
    return callback()
  } else {
    return callback(new Error(res.msg))
  }
}
/**
 * @description 校验身份证号
 * @param rule
 * @param value
 * @param callback
 * @returns {*}
 */
export const checkIdCard = (rule, value, callback) => {
  const res = isIdCard(value)
  if (res.status === 1) {
    return callback()
  } else {
    return callback(new Error(res.msg))
  }
}

/**
 * @description 校验手机号
 * @param rule
 * @param value
 * @param callback
 * @returns {*}
 */
export const checkPhone = (rule, value, callback) => {
  const res = isTelePhone(value)
  if (res.status === 1) {
    return callback()
  } else {
    return callback(new Error(res.msg))
  }
}
/**
 * @description 校验邮箱
 * @param rule
 * @param value
 * @param callback
 * @returns {*}
 */
export const checkEmail = (rule, value, callback) => {
  const res = isEmail(value)
  if (res.status === 1) {
    return callback()
  } else {
    return callback(new Error(res.msg))
  }
}

/**
 * @description 校验不能含有中文
 * @param rule
 * @param value
 * @param callback
 * @returns {*}
 */
export const checkChineseStr = (rule, value, callback) => {
  const res = isChineseChar(value)
  if (res.status === 1) {
    return callback()
  } else {
    return callback(new Error(res.msg))
  }
}

/**
 * 密码必须同事包含英文和数字
 */
export const numAndEn = (rule, value, callback) => {
  const reg = /^(?=.*[0-9])(?=.*[a-zA-Z])(?!.*[\u4e00-\u9fa5])(?!.*[ ]).{8,12}$/
  if (reg.test(value)) {
    callback()
  } else {
    callback(new Error('长度8~12个字符，必须包含数字和英文，英文区分大小写，可包含除空格外的特殊字符'))
  }
}

/**
 * @description 不能含有空格
 * @param msg
 * @returns {{pattern: RegExp, trigger: string, message: string}}
 */
export const inputValidator = msg => ({ pattern: /^[^ ]+$/, message: msg + '不能含有空格', trigger: 'blur' })

/**
 * 联系人，姓名，用户名校验，不能是纯数字，或者纯特殊字符，或者纯空格
 */
export const nameValidator = (rule, value, callback) => {
  const reg = /^(?=.*[^`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、])(?=.*[^0-9])(?!.*[ ]).*$/
  if (reg.test(value)) {
    callback()
  } else {
    callback(new Error('不能包含空格/纯数字/纯特殊字符'))
  }
}

const yxUtils = {

  /**
   * @description Log
   * @param type
   * @param str
   */
  Log: function(type = 'log', str) {
    if (type === 'log') {
      console.log(str)
    }
    if (type === 'error') {
      console.error(str)
    }
    if (type === 'warn') {
      console.warn(str)
    }
  },
  /**
   * @description 判断对象是否为空
   * @param obj
   * @returns {boolean}
   */
  isEmpty: function(obj) {
    return obj === undefined || obj === 'undefined' || obj === null || obj === 'null' || obj === ''
  },

  /**
   * @description 判断一个元素是否在一个数组中
   * @param arr
   * @param val
   * @returns {boolean}
   */
  contains: function(arr, val) {
    return this.indexOf(arr, val) !== -1
  },

  /**
   * @description 判断数组是否有重复的项
   * @param arr
   * @returns {boolean}
   */
  isRepeat: function(arr) {
    var hash = {}
    for (const i in arr) {
      if (hash[arr[i]]) return true
      hash[arr[i]] = true
    }
    return false
  },

  /**
   * @description 判断字符串是否是汉字，字段中有空格返回false
   * @param str
   * @returns {boolean}
   */
  isChinese: function(str) {
    if (/^([\u4e00-\u9fa5]|[\ufe30-\uffA0])*$/.test(this.trim(str))) { return true }
  },

  parseJSONtoParams: function(data) {
    try {
      var tempArr = []
      for (var i in data) {
        var key = encodeURIComponent(i)
        var value = encodeURIComponent(data[i])
        tempArr.push(key + '=' + value)
      }
      var urlParamsStr = tempArr.join('&')
      return urlParamsStr
    } catch (err) {
      return ''
    }
  }

}

export default yxUtils

// 验证数字（只能输入整数）
export const CheckNumber = function(rule, value, callback) {
  if (/[^\d]/g.test(value)) {
    callback(new Error("请输入正确的数字"));
  } else {
    callback();
  }
};
// 验证数字（只能输入整数或小数）
export const CheckPriceOrDistance = function(rule, value, callback) {
  if (!/^((0{1}\.\d+)|([1-9]\d*\.{1}\d+)|([1-9]+\d*))$/.test(value)) {
    callback(new Error("请输入正确的整数或小数"));
  } else {
    callback();
  }
};
// 验证数字长度
export const CheckNumberLength = function(rule, value, callback) {
  if (value.length > 10) {
    callback(new Error("请输入10个字符以内"));
  } else {
    callback();
  }
};
// 验证备注长度
export const CheckRemarkLength = function(rule, value, callback) {
  if (value.length > 255) {
    callback(new Error("请输入255个字符以内"));
  } else {
    callback();
  }
};
// 验证手机号
export const CheckPhone = function(rule, value, callback) {
  if (!/^1[34578]\d{9}$/.test(value)) {
    callback(new Error("手机号格式不正确"));
  } else {
    callback();
  }
};

// 验证是否数值类型
export function isDecimal(rule, value, callback) {
    setTimeout(() => {
        if(value === "" || value === null || value === undefined) {
            callback(new Error('必须为非负数最多三位小数'));
        } else {
            const re = /^(([0-9][0-9]*)|(([0]\.\d{1,3}|[1-9][0-9]*\.\d{1,3})))$/;
            const rsCheck = re.test(value);
            if (!rsCheck) {
                callback(new Error('必须为非负数最多三位小数'));
            } else {
                callback();
            }
        }
    }, 0);
};

//正整数验证
export  function validatorIntNum(rule, value, callback) {
    if (value !== "" && value !== undefined) {
        if (!/^[0-9]*$/.test(value)) {
            callback(new Error("请填写正整数"));
        } else {
            callback();
        }
    } else {
        callback();
    }
};

// 验证邮箱格式
export const CheckEmail = function(rule, value, callback) {
    if (!/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(value)) {
        callback(new Error("邮箱格式不正确"));
    } else {
        callback();
    }
};

// 验证是否数值类型
export function isNumber6Decimal3(rule, value, callback) {
    setTimeout(() => {
        if(value === "" || value === null || value === undefined) {
            callback();
        } else {
            const re = /^\d{1,6}(\.\d{1,3})?$/;
            const rsCheck = re.test(value);
            if (!rsCheck) {
                callback(new Error('必须为非负数最多六位整数三位小数'));
            } else {
                callback();
            }
        }
    }, 0);
};
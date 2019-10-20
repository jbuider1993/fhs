import request from "_private/utils/request";
const serverUrl = "http://localhost:182";
// 根据父级获取地区下拉框
export function FetchGetArea(parentId, type) {
  return request({
    url: "/base/common/getArea?parentId=" + parentId + "&type=" + type,
    method: "get"
  });
}
export default { serverUrl };

/**
 * 获取下拉框的数据源
 * @param {*} data 
 */
export function FetGetDropDownList(data) {
  return request({
    url: "/base/common/getDropDownList",
    method: "post",
    data
  });
}
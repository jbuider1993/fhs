
const TokenKey = 'Authorization'
const DataBase = 'DataBase'
const UserCode = 'Code'
const DictList = 'dictList'

export function getToken() {
  return sessionStorage.getItem(TokenKey)
}

export function setToken(token) {
  return sessionStorage.setItem(TokenKey, token)
}

export function removeToken() {
  return sessionStorage.removeItem(TokenKey)
}

export function getDataBase() {
  const data = sessionStorage.getItem(DataBase)
  return JSON.parse(data)
}

export function setDataBase(arr) {
  const data = JSON.stringify(arr)
  return sessionStorage.setItem(DataBase, data)
}

export function removeDataBase() {
  return sessionStorage.removeItem(DataBase)
}

export function getUserCode() {
  const data = sessionStorage.getItem(UserCode)
  return data
}

export function setUserCode(str) {
  return sessionStorage.setItem(UserCode, str)
}

export function removeUserCode() {
  return sessionStorage.removeItem(UserCode)
}

export function setDictList(arr) {
  const data = JSON.stringify(arr)
  return sessionStorage.setItem(DictList, data)
}

export function getDictList() {
  const data = sessionStorage.getItem(DictList)
  return JSON.parse(data)
}

export function removeDictList() {
  return sessionStorage.removeItem(DictList)
}

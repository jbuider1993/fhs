import { login, getUserInfo } from '@/api/login/user'
import { getToken, setToken, removeToken, getUserCode, setUserCode } from '@/utils/auth'
import { syncSysInfoAndUserInfo } from '@/api/base'
import { resetRouter } from '@/router'
import { Message } from 'element-ui'

const state = {
  token: getToken(),
  userInfo: undefined
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_USERINFO: (state, user) => {
    state.userInfo = user
  }
}

const actions = {
  // user login

  /**
   * @description 登录
   * @param commit
   * @param userInfo
   * @returns {Promise<any>}
   */
  login({ commit }, userInfo) {
    return new Promise(async(resolve, reject) => {
      await login(userInfo).then(res => {
        commit('SET_TOKEN', res.data.token)
        setToken(res.data.token)
        Message.success({ message: '登录成功', duration: 2000 })
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  /**
   * @description 获取用户信息
   * @param commit
   * @param dispatch
   * @param data
   * @returns {Promise<any>}
   */
  getUserInfo({ commit, dispatch }, data) {
    return new Promise(resolve => {
      getUserInfo(data).then(async res => {
        commit('SET_USERINFO', res.data.user)
        const addRouters = await dispatch('permission/generateRoutes', res.data.menu, { root: true })
        const userCode = getUserCode()
        if (res.data && res.data.user.userId && res.data.user.userId !== userCode) {
          await dispatch('tagsView/delAllViews', [], { root: true })
        }
        setUserCode(res.data.user.userId || '')
        //  dispatch('permission/generateAuth', res.auth, { root: true })
        dispatch('permission/generateAuth', [], { root: true })
        resetRouter()
        resolve(addRouters)
      })
    })
  },

  /**
   * @description 退出登录
   * @param commit
   * @param state
   * @returns {Promise<any>}
   */
  logout({ dispatch, commit, state }) {
    return new Promise(async(resolve, reject) => {
      await dispatch('permission/clearPermission', null, { root: true })
      commit('SET_TOKEN', null)
      removeToken()
      resetRouter()
      resolve()
    })
  },

  /**
   * 同步信息
   * @param commit
   * @returns {Promise<any>}
   */
  syncSystemInfoAndUerInfo({ commit }) {
    return new Promise(async(resolve, reject) => {
      try {
        await syncSysInfoAndUserInfo()
      } catch (e) {
        console.error(e)
      }
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

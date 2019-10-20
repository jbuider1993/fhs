import Cookies from 'js-cookie'
import { getLanguage } from '@/lang/index'
import { setDictList } from '@/utils/auth'
import settings from '@/settings'
import { getDictTypeAndDict } from '@/api/base'
import { Message } from 'element-ui'

const state = {
  sidebar: {
    opened: Cookies.get('sidebarStatus') ? !!+Cookies.get('sidebarStatus') : true,
    withoutAnimation: false
  },
  systemType: settings.systemType,
  device: 'desktop',
  language: getLanguage(),
  size: Cookies.get('size') || 'medium',
  dictSync: 0
}

const mutations = {
  TOGGLE_SIDEBAR: state => {
    state.sidebar.opened = !state.sidebar.opened
    state.sidebar.withoutAnimation = false
    if (state.sidebar.opened) {
      Cookies.set('sidebarStatus', 1)
    } else {
      Cookies.set('sidebarStatus', 0)
    }
  },
  CLOSE_SIDEBAR: (state, withoutAnimation) => {
    Cookies.set('sidebarStatus', 0)
    state.sidebar.opened = false
    state.sidebar.withoutAnimation = withoutAnimation
  },
  TOGGLE_DEVICE: (state, device) => {
    state.device = device
  },
  SET_LANGUAGE: (state, language) => {
    state.language = language
    Cookies.set('language', language)
  },
  SET_SIZE: (state, size) => {
    state.size = size
    Cookies.set('size', size)
  },
  SET_DICTSYNC: (state, num) => {
    state.dictSync = num
    Cookies.set('dictSync', num)
  }
}

const actions = {
  toggleSideBar({ commit }) {
    commit('TOGGLE_SIDEBAR')
  },
  closeSideBar({ commit }, { withoutAnimation }) {
    commit('CLOSE_SIDEBAR', withoutAnimation)
  },
  toggleDevice({ commit }, device) {
    commit('TOGGLE_DEVICE', device)
  },
  setLanguage({ commit }, language) {
    commit('SET_LANGUAGE', language)
  },
  setSize({ commit }, size) {
    commit('SET_SIZE', size)
  },
  /**
   * 同步字典项
   * @param commit
   * @returns {Promise<any>}
   */
  getDictTypeAndDict({ commit }) {
    return new Promise(async resolve => {
      try {
        const res = await getDictTypeAndDict()
        setDictList(res.result)
        commit('SET_DICTSYNC', 1)
      } catch (e) {
        commit('SET_DICTSYNC', 0)
        Message.error(e.message)
      }
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

import { getDataTableList } from '@/api/system/rowlevel'
import { getDataBase, setDataBase } from '@/utils/auth'
import { Message } from 'element-ui'

const state = {
  dataBase: getDataBase || []
}

const mutations = {
  SET_DATABASE: (state, dataBase) => {
    state.dataBase = dataBase
  }
}

const actions = {
  getDataBaseAll({ commit }) {
    return new Promise(async(resolve, reject) => {
      try {
        const res = await getDataTableList()
        commit('SET_DATABASE', res)
        resolve(res)
        setDataBase(res)
      } catch (e) {
        console.error('获取表失败' + e)
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

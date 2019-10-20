import { constantRoutes, resetRouter } from '@/router'
import { copyTree } from '@/utils'
import Layout from '@/layout'
import HttpVueLoader from 'http-vue-loader'
// const view = path => () => import(`@/views/${path}`)
const view = path => resolve => require([`@/views/${path}`], resolve)
import menu from '@/data/menu'

/**
 * 生成路由
 * @param routes
 * @returns {*}
 */
function addComponent(routes, code) {
  const newRoutes = routes.concat()
  newRoutes.forEach((item, index) => {
    delete item.parentId
    delete item.menuCode
    item.name = item.title
    item.ascription = code || item.id
    if (!code) {
      item.path = '/' + item.id
    } else {
      item.path = item.id + ''
    }

    item.meta = {
      icon: item.icon,
      title: item.title,
      noCache: item.nocache === 1,
      ascription: code || item.ascription
    }

    if (item.linkType === 1) {
      routes.splice(index, 1)
    } else {
      if (item.url && item.url.indexOf('Layout') !== -1) {
        item.component = Layout
      } else {
        if (item.url.indexOf('url:') === -1) {
          item.component = view(item.component)
        } else {
          var url = item.url.replace('url:', '')
          url = 'http://localhost/user.vue'
          console.log('---' + url + '--')
          item.component = HttpVueLoader(url)
        }
      }
      item.children = item.sonMenu
      if (item && item.children && item.children.length > 0) {
        addComponent(item.children, item.id)
      }
    }
  })
  return newRoutes
}

/**
 * 生成权限
 * @param data
 */
function checkAuthList(data) {
  const arr = [...data]
  const _temp = {}
  arr.forEach(item => {
    _temp[item.web_tag] = true
  })
  return _temp
}

/**
 * 添加菜单
 * @param routes
 * @returns {*}
 */
function addMenus(routes, code) {
  const menu = routes.concat()
  menu.forEach(item => {
    if (!code) {
      item.path = '/' + item.id
    } else {
      item.path = item.id + ''
    }
    item.title = item.name
    item.name = item.id
    item.ascription = code || item.id
    if (item.menuState === 1) {
      item.hidden = true
    }
    item.meta = {
      icon: item.icon,
      title: item.title,
      ascription: code || item.ascription
    }
    item.children = item.sonMenu
    if (item && item.children && item.children.length) {
      addMenus(item.children, item.id)
    }
  })
  return menu
}

/**
 * Use meta.role to determine if the current user has permission
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.includes(role))
  } else {
    return true
  }
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(routes, roles) {
  const res = []

  routes.forEach(route => {
    const tmp = { ...route }
    if (hasPermission(['admin'], tmp)) {
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, ['admin'])
      }
      res.push(tmp)
    }
  })

  return res
}

const state = {
  routes: [],
  menus: menu,
  auth: null,
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  },
  SET_MENUS: (state, routes) => {
    state.menus = routes
  },
  SET_AUTH: (state, auth) => {
    state.auth = auth
  }
}

const actions = {
  generateRoutes({ commit }, route) {
    return new Promise(resolve => {
      let accessedRoutes = []
      let menus = []
      accessedRoutes = addComponent(copyTree(route))
      console.log(accessedRoutes)
      menus = addMenus(copyTree(route))
      commit('SET_ROUTES', accessedRoutes)
      commit('SET_MENUS', menus)
      resetRouter()
      resolve(accessedRoutes)
    })
  },
  generateAuth({ commit }, auth) {
    return new Promise(resolve => {
      const authList = checkAuthList(auth)
      commit('SET_AUTH', authList)
      resolve(auth)
    })
  },
  clearPermission({ commit }) {
    return new Promise(resolve => {
      commit('SET_AUTH', null)
      commit('SET_MENUS', [])
      commit('SET_ROUTES', [])
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

import Layout from '@/layout'
import Vue from 'vue'
import HttpVueLoader from 'http-vue-loader'
const view = path => resolve => require([`@/views/${path}`], resolve)
Vue.use(HttpVueLoader)
const newRouter = [
  {
    alwaysShow: false,
    ascription: 490192,
    component: Layout,
    componentName: 'System',
    icon: 'setting',
    linkType: 0,
    menuState: 0,
    menuType: 0,
    name: 'System',
    nocache: 0,
    path: '/system',
    redirect: '',
    tabMold: 0,
    title: '系统设置',
    url: '',
    meta: {
      ascription: 490192,
      icon: 'setting',
      noCache: false,
      title: '系统设置'
    },
    children: [
      {
        alwaysShow: false,
        ascription: 490192,
        children: [],
        component: HttpVueLoader('http://localhost/user.vue'),
        componentName: 'user',
        icon: 'file-a',
        linkType: 0,
        menuState: 0,
        menuType: 1,
        name: 'user',
        nocache: 0,
        path: 'Users',
        redirect: '',
        tabMold: 0,
        title: '用户管理',
        url: '',
        meta: {
          ascription: 490192,
          icon: 'setting',
          noCache: false,
          title: '用户管理'
        }
      }
    ]

  }

]
export default newRouter

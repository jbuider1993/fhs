const menu = [
  {
    alwaysShow: false,
    ascription: 490192,
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
        ascription: 952044,
        children: [],
        component: 'system/user',
        componentName: 'user',
        icon: 'file-a',
        id: 97,
        linkType: 0,
        menuCode: 4817,
        menuState: 0,
        menuType: 1,
        name: 'user',
        nocache: 0,
        parentId: 100,
        path: 'Users',
        redirect: '',
        tabMold: 0,
        title: '用户管理',
        url: '',
        meta: {
          ascription: 952044,
          icon: 'file-a',
          noCache: false,
          title: '用户管理'
        }

      }
    ]
  }
]

export default menu

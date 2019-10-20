const getters = {
  sidebar: state => state.app.sidebar,
  language: state => state.app.language,
  size: state => state.app.size,
  device: state => state.app.device,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  token: state => state.user.token,
  userInfo: state => state.user.userInfo,
  permission_routes: state => state.permission.routes,
  permission_addRoutes: state => state.permission.addRoutes,
  menus: state => state.permission.menus,
  auth: state => state.permission.auth,
  errorLogs: state => state.errorLog.logs,
  isFullScreen: state => state.system.isFullScreen

}
export default getters

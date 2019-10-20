import Vue from 'vue'
import SvgIcon from '@/components/SvgIcon'// svg component
import { setLocal } from '@/utils'
const icons = []
// register globally
Vue.component('svg-icon', SvgIcon)

const req = require.context('./svg', false, /\.svg$/)
const requireAll = requireContext => requireContext.keys().map(requireContext)
// requireAll(req)
const list = requireAll(req)
for (let i = 0; i < list.length; i++) {
  const a = list[i]
  const o = {}
  o.id = a.default.id.replace(/^icon-/, '')
  o.name = a.default.id.replace(/^icon-/, '')
  icons.push(o)
}

setLocal('icons', JSON.stringify(icons))

// requireAll(req)

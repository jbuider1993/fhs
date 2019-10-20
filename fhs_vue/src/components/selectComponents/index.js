import DictSelect from './src/dictSelect.vue'
import Develop from './src/develop.vue'

const components = {
install: function (Vue) {
    Vue.component(DictSelect.name, DictSelect);
    Vue.component('develop', Develop);
}
}

export default components
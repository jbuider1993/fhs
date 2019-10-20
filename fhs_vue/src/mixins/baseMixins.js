/**
 * 此文件禁止修改
 */
import { getDictList } from '@/utils/auth'
import { mapGetters } from 'vuex'
import settings from '@/settings'
export default {
  data() {
    return {
      tableData: [],
      listLoading: true,
      total: 0,
      listQuery: {
        page: 1,
        limit: 10,
        entity: {}
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建',
        query: '查看',
        audit: '审核'
      }
    }
  },
  computed: {
    ...mapGetters([
      'auth', 'userInfo'
    ])
  },
  beforeCreate() {
    /**
     * 由于平台里面需要设置官网的菜单和角色，会在官网设置里面将部分的headers.source设置为2，所以在这里面初始化成为1
     * @type {number}
     */
    settings['source'] = 1
  },
  methods: {
    /**
     * @param val
     * @description 不同页码，序号随之变化
     */
    indexMethod(index) {
      return (this.listQuery.page - 1) * this.listQuery.limit + index + 1
      // return (this.listQuery.page - 1) * 10 + val + 1
    },
    /**
     * @param val
     * @description 当前第几页
     */
    handleCurrentChange(val) {
      this.listQuery.page = val
      this.getList()
    },

    /**
     * @param val
     * @description 设置分页条数
     */
    handleSizeChange(val) {
      this.listQuery.limit = val
      this.getList()
    },

    /**
     *@description 新增打开Dialog
     */
    handleCreate() {
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.resetTemp()
    },
    /**
     *@param formName
     *@description 关闭Dialog窗口
     */
    cancel(formName) {
      this.dialogFormVisible = false
      if (this.$refs[formName]) {
        this.$refs[formName].resetFields()
      }
    },

    /**
     * 根据字典的Key 返回匹配的列表
     * @param key
     * @param fn
     */
    getDicByCode(key, fn) {
      const tempList = []
      const _dictArray = getDictList()
      for (const item of _dictArray) {
        if (item.dict_type_code === key) {
          tempList.push({ value: item.dict_code, label: item.dict_name })
        }
      }
      fn(tempList)
    },

    /**
     * 根据code获取name
     * @param _arr
     * @param _code
     * @returns {*}
     */
    getLabelByCode(_arr, _code) {
      const arr = [..._arr]
      let label = ''
      for (let i = 0; i < arr.length; i++) {
        if (arr[i].value === _code) {
          label = arr[i].label
          return label
        }
      }
      return _code
    },

    /**
     * 搜索
     */
    handleSearch() {
      this.listQuery.page = 1
      this.getList()
    },

    /**
     * 初始化分页参数
     */
    resetListQuery() {
      this.listQuery = {
        page: 1,
        limit: 10,
        entity: {}
      }
    },

    confirmCrt(opt, confirm, cancel) {
      const option = {
        title: opt.title || '提示',
        button: opt.button || ['确定', '取消'],
        message: opt.message,
        type: opt.type || 'warning'
      }
      this.$confirm(option.message, option.title, { confirmButtonText: option.button[0], cancelButtonText: option.button[1], type: option.type }).then(() => {
        confirm && typeof confirm === 'function' && confirm()
      }).catch(() => {
        cancel && typeof cancel === 'function' && cancel()
      })
    }

  },
  filters: {
  },
  created() {
  }
}

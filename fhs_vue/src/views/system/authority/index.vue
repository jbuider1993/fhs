<template>
  <div v-loading="listLoading" class="app-container">
    <el-row :gutter="20">
      <el-col :span="24">
        <el-button v-if="authManager_save_btn" type="primary" size="small" class="blue-btn" @click="update">保存
        </el-button>
      </el-col>
      <el-col :span="8" style="margin-top:15px;">
        <el-input v-model="filterText" placeholder="输入关键字进行过滤" />
        <el-tree
          ref="menuTree"
          class="filter-tree menuTree"
          node-key="menuCode"
          :data="treeData"
          show-checkbox
          highlight-current
          :props="defaultProps"
          :filter-node-method="filterNode"
          default-expand-all
          @check-change="checkTree"
          @node-click="getNodeData"
        />
      </el-col>
      <el-col :span="16" style="margin-top:15px;">
        <el-table
          ref="elementTable"
          :data="tableData"
          border
          fit
          highlight-current-row
          style="width: 100%"
          @select-all="handleSelectAll"
          @select="handleSelectionChange"
        >
          <el-table-column type="selection" width="65" align="center" />
          <el-table-column width="200px" align="center" label="资源名称">
            <template slot-scope="scope">
              {{ scope.row.title }}
            </template>
          </el-table-column>
          <el-table-column width="200px" align="center" label="资源类型">
            <template slot-scope="scope">
              <el-tag>
                {{ scope.row.menuType | getAuthType }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column align="center" label="资源地址">
            <template slot-scope="scope">
              <span>
                {{ scope.row.url }}</span>
            </template>
          </el-table-column>
          <el-table-column fixed="right" align="center" label="操作" width="150">
            <template slot-scope="scope">
              <el-button
                v-if="scope.row.menuType === 2 && rowAuthManager_btn"
                size="mini"
                type="primary"
                class="blue-btn"
                @click="handleRowLevel(scope.row)"
              >
                行级权限分配
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--<div v-show="!listLoading" class="pagination-container">-->
        <!--<el-pagination-->
        <!--:current-page.sync="listQuery.page"-->
        <!--:page-sizes="[10,20,30, 50]"-->
        <!--:page-size="listQuery.limit"-->
        <!--layout="total, sizes, prev, pager, next, jumper"-->
        <!--:total="total"-->
        <!--@size-change="handleSizeChange"-->
        <!--@current-change="handleCurrentChange"-->
        <!--/>-->
        <!--</div>-->
      </el-col>

    </el-row>
    <!--<el-dialog title="行级权限分配" :visible.sync="dialogFormVisible" @close="cancel">-->
    <!--<data-base v-if="dialogFormVisible" :menu-code="baseForm.menuCode" :org-role-code="baseForm.orgRoleCode" />-->
    <!--</el-dialog>-->
    <data-base v-if="dialogFormVisible" ref="database" :menu-code="baseForm.menuCode" :org-role-code="baseForm.orgRoleCode" @close="dialogFormVisible = false" />

  </div>
</template>
<script>
import {
  fetchTree
} from '@/api/system/menu'
import store from '@/store'
import {
  page
} from '@/api/system/resource'
import { getAuthList, addObj } from '@/api/system/auth'
import baseMixins from '@/mixins/baseMixins'
import dataBase from '../components/dataBase'
export default {
  name: 'Authority',
  components: { dataBase },
  mixins: [baseMixins],
  props: {
    // orgRoleCode: {
    //   default: undefined
    // }
  },
  data() {
    return {
      parentId: undefined,
      filterText: '',
      authList: {},
      treeList: [],
      treeData: [],
      menuList: [],
      defaultProps: {
        children: 'children',
        label: 'title'
      },
      listQuery: {
        page: 1,
        limit: 200
      },
      baseForm: {
        menuCode: '',
        orgRoleCode: ''
      },
      form: {
        menuCodes: '',
        orgRoleCode: ''
      },
      authManager_save_btn: false,
      rowAuthManager_btn: false,
      groupManager_menu: false,
      groupManager_element: false
    }
  },
  watch: {
    orgRoleCode: {
      immediate: true,
      handler(newVal) {
        this.form.orgRoleCode = newVal
      }
    },
    filterText(val) {
      this.$refs.menuTree.filter(val)
    }
  },
  beforeRouteEnter(to, from, next) {
    if (to.params.code) {
      next()
    } else {
      store.dispatch('tagsView/delView', to).then(() => {
        next('/system/orgrole')
      })
    }
  },
  async created() {
    this.authManager_save_btn = this.auth['authManager:save_btn']
    this.rowAuthManager_btn = this.auth['rowAuthManager:btn']
    this.form.orgRoleCode = this.$route.params.code
    this.baseForm.orgRoleCode = this.$route.params.code
    if (this.form.orgRoleCode) {
      await this.getTreeList()
      await this.getAuthList()
    }
  },
  methods: {
    /**
       * @description 获取菜单列表
       * @param datad
       */
    async getTreeList() {
      try {
        const res = await fetchTree({ parentId: 0 })
        this.treeData = res
        this.checkMenuTypeCreateObj(res)
        this.treeToList(this.treeData)
      } catch (e) {
        this.$message.error(e.message)
      }
      this.listLoading = false
    },
    /**
       * @description 获取权限信息
       * @param data
       */
    async getAuthList() {
      try {
        const res = await getAuthList({ orgRoleCode: this.form.orgRoleCode })
        this.authList = res
        const authList = this.menuTreeToList(JSON.parse(JSON.stringify(this.authList)))
        this.initCheckTree(authList)
      } catch (e) {
        this.$message.error(e.message)
      }
    },
    /**
       * @description 生成权限列表
       * @param data
       */
    initAuthList(data) {
      const auth = []
      for (let i = 0; i < data.length; i++) {
        auth.push(data[i].menuCode)
      }
      this.authList = auth
    },

    /**
       * @description 回填菜单树
       * @param data
       */
    initCheckTree(data) {
      this.$refs.menuTree.setCheckedKeys(data)
    },

    async getList() {
      try {
        const res = await page(this.listQuery)
        this.tableData = res.data
        this.total = res.total
      } catch (e) {
        this.$message.error(e.message)
      }
    },

    /**
       * @description 菜单树点击,获取当前菜单的按钮权限
       * @param obj
       */
    async getNodeData(data) {
      this.parentId = this.getRootNode(data.parentId, this.treeList)
      this.listLoading = true
      this.listQuery.entity.menuCode = data.menuCode
      try {
        await this.getList()
      } catch (e) {
        this.tableData = []
      }
      this.$nextTick(() => {
        for (let i = 0; i < this.tableData.length; i++) {
          const result = this.backFillAuth(this.tableData[i], i)
          if (result) {
            this.$refs.elementTable.toggleRowSelection(this.tableData[i], true)
          }
        }
      })
      this.listLoading = false
    },

    async update() {
      // this.form.menuCodes = this.authList.join(',')
      this.listLoading = true
      this.form.menuCodes = JSON.stringify(this.authList)
      try {
        await addObj(this.form)
        this.$notify({
          title: '成功',
          message: '保存成功',
          type: 'success',
          duration: 2000
        })
      } catch (e) {
        this.$notify({
          title: '失败',
          message: e.message,
          type: 'error',
          duration: 2000
        })
      }
      this.listLoading = false
      this.closeSelectedTag(this.$route)
    },

    /**
       * 选中菜单树checkBox
       * @param data
       * @param state
       * @param child
       */
    checkTree(data, state, child) {
      if (data.menuType === 1) {
        const key = this.getRootNode(data.parentId, this.treeList)
        if (state) {
          const index = this.checkList(this.authList[key], data.menuCode)
          if (index === -1) {
            this.authList[key].push(data.menuCode)
          }
        } else {
          const index = this.checkList(this.authList[key], data.menuCode)
          if (index !== -1) {
            this.authList[key].splice(index, 1)
          }
        }
      }
    },

    treeToList(list) {
      list.forEach(item => {
        this.treeList.push(item)
        if (item.children.length > 0) {
          this.treeToList(item.children)
        }
      })
    },

    menuTreeToList(obj) {
      let arr = []
      for (const key in obj) {
        arr = [...arr, ...obj[key]]
      }
      return arr
    },

    /**
       * 通过menuCode 获取到跟节点
       * @param obj
       */
    getRootNode(parentId, arr) {
      for (const item of arr) {
        if (item.menuCode === parentId) {
          if (item.parentId === 0) {
            return item.menuCode
          } else {
            return this.getRootNode(item.parentId, arr)
          }
        }
      }
    },
    /**
       * 回填按钮权限
       * @param obj
       */
    backFillAuth(obj) {
      const authArr = this.authList[this.parentId]
      for (let i = 0; i < authArr.length; i++) {
        if (obj.menuCode === authArr[i]) {
          return true
        }
      }
      return false
    },
    /**
       * 按钮权限单选
       * @param obj
       */
    handleSelectionChange(val, row) {
      const selected = val.length && val.indexOf(row) !== -1
      if (selected) {
        this.authList[this.parentId].push(row.menuCode)
      } else {
        const index = this.checkList(this.authList[this.parentId], row.menuCode)
        this.authList[this.parentId].splice(index, 1)
      }
    },

    /**
     * 按钮权限全选
     * @param val
     */
    handleSelectAll(val) {
      if (val.length > 0) {
        for (let i = 0; i < this.tableData.length; i++) {
          const a = this.tableData[i].menuCode
          const index = this.checkList(this.authList[this.parentId], a)
          if (index === -1) {
            this.authList[this.parentId].push(a)
          }
        }
      } else {
        for (let i = 0; i < this.tableData.length; i++) {
          const currentData = this.tableData[i].menuCode
          const index = this.checkList(this.authList[this.parentId], currentData)
          if (index !== -1) {
            this.authList[this.parentId].splice(index, 1)
          }
        }
      }
    },

    /**
       * 菜单筛选
       * @param value
       * @param data
       * @returns {boolean}
       */
    filterNode(value, data) {
      if (!value) return true
      return data.title.indexOf(value) !== -1
    },
    /**
       * 是否存在
       * @param arr
       * @param _temp
       * @returns {boolean}
       */
    checkList(arr, _temp) {
      for (let i = 0; i < arr.length; i++) {
        if (arr[i] === _temp) {
          return i
        }
      }
      return -1
    },
    handleRowLevel(row) {
      this.baseForm.menuCode = row.menuCode
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['database'].dialogFormVisible = true
        // this.$refs['database'].form.menuCode = row.menuCode
        // this.$refs['database'].getListByTable()
      })
    },

    checkMenuTypeCreateObj() {
      this.queryParentIdByMenuTree(this.treeData)
    },

    queryParentIdByMenuTree(arr) {
      for (const item of arr) {
        if (item.menuType === 0) {
          this.authList[item.menuCode] = []
        }
        if (item.children && item.children.length > 0) {
          this.queryParentIdByMenuTree(item.children)
        }
      }
    },

    closeSelectedTag(view) {
      this.$store.dispatch('tagsView/delView', view).then(({ visitedViews }) => {
        this.toLastView(visitedViews, view)
      })
    },

    toLastView(visitedViews, view) {
      const latestView = visitedViews.slice(-1)[0]
      if (latestView) {
        this.$router.push(latestView)
      } else {
        // now the default is to redirect to the home page if there is no tags-view,
        // you can adjust it according to your needs.
        if (view.name === 'Dashboard') {
          // to reload home page
          this.$router.replace({ path: '/redirect' + view.fullPath })
        } else {
          this.$router.push('/')
        }
      }
    }
  }
}
</script>
<style lang="scss" scoped>
  .el-tree {
    cursor: default;
    background: #fff;
    border: 1px solid #d1dbe5;
  }

  .menuTree {
    height: 60vh;
    overflow-y: auto;
  }
</style>

<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true" size="small">
        <el-form-item>
          <el-input v-model="listQuery.entity.realName" size="small" class="filter-item" placeholder="请输入用户名" style="width:200px;" />
        </el-form-item>
        <el-form-item>
          <el-button class="filter-item blue-btn" type="primary" size="small" @click="handleSearch">
            {{ $t('system.users.search') }}
          </el-button>
          <el-button v-if="userOrgPostManager_add_btn" class="filter-item blue-btn" style="margin-left: 10px;" size="small" type="primary" @click="handleCreate">
            添加
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table v-loading.body="listLoading" :data="tableData" size="mini" border fit highlight-current-row>
      <el-table-column align="center" label="序号" type="index" width="65" :index="indexMethod" />
      <el-table-column width="200" align="center" label="用户姓名"> <template slot-scope="scope">
        <span>{{ scope.row.realName }}</span>
      </template> </el-table-column>
      <el-table-column width="65" align="center" label="性别"> <template slot-scope="scope">
        <span>{{ getLabelByCode(sexList,scope.row.sex) }}</span>
      </template> </el-table-column>
      <el-table-column align="center" width="300" label="组织名称"> <template slot-scope="scope">
        <span>{{ scope.row.orgName }}</span>
      </template>
      </el-table-column>
      <el-table-column align="center" width="200" label="联系电话"> <template slot-scope="scope">
        <span>{{ scope.row.tel }}</span>
      </template>
      </el-table-column>
      <el-table-column align="center" label="邮箱" width="500"> <template slot-scope="scope">
        <span>{{ scope.row.email }}</span>
      </template>
      </el-table-column>
      <el-table-column v-if="userOrgPostManager_del_btn" align="center" label="操作" :width="tableBtnColumnWidth" fixed="right"> <template slot-scope="scope">

        <el-button v-if="userOrgPostManager_del_btn" class="red-btn" size="mini" type="primary" @click="handleDelete(scope.row,scope.$index)">删除
        </el-button>
      </template>
      </el-table-column>
    </el-table>
    <div v-show="!listLoading" class="pagination-container">
      <el-pagination
        :current-page.sync="listQuery.page"
        :page-sizes="[10,20,30, 50]"
        :page-size="listQuery.limit"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" @close="cancel('form')">
      <el-form ref="form" :disabled="dialogStatus === 'query'" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="组织名称">
          <check-org ref="checkOrgTree" :tree="treeData" :status="dialogStatus" :name="form.orgName" @getNodeData="getNodeDataParent" />
        </el-form-item>
        <el-form-item label="用户" prop="userCode">
          <el-select v-model="form.userCode" filterable placeholder="请选择用户" style="width:100%;">
            <el-option v-for="(item,index) of userList" :key="index" :label="item.realName" :value="item.userCode" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel('form')">
          {{ $t('permission.cancel') }}
        </el-button>
        <el-button v-if="dialogStatus === 'create' || dialogStatus === 'update'" class="blue-btn" type="primary" @click="confirmUser('form')">
          {{ $t('permission.confirm') }}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { fetchTree } from '@/api/system/organization'
import { page, addObj, getObj, delObj, putObj, getUserListAll } from '@/api/system/opu'
import checkOrg from '../components/checkOrg'
import baseMixins from '@/mixins/baseMixins'
export default {
  name: 'Opu',
  components: { checkOrg },
  mixins: [baseMixins],
  data() {
    return {
      sexList: [],
      treeData: [],
      userList: [],
      form: {
        orgName: undefined,
        orgCode: undefined,
        userCode: undefined
      },
      rules: {
        userCode: [
          {
            required: true,
            trigger: 'blur',
            message: '请选择用户'
          }
        ]

      },
      userOrgPostManager_add_btn: false,
      userOrgPostManager_edit_btn: false,
      userOrgPostManager_del_btn: false,
      userOrgPostManager_detail_btn: false
    }
  },
  computed: {
    tableBtnColumnWidth() {
      let width = 120
      this.userOrgPostManager_del_btn ? width += 45 : width += 0
      return width
    }
  },
  watch: {
    'form.orgName': {
      handler(newValue, oldValue) {
        this.form.orgName = newValue
      },
      deep: true
    }

  },
  async created() {
    this.getDicByCode(80, data => {
      this.sexList = data
    })
    this.getAuth()
    this.getUserListAll()
    this.getTreeList()
    this.getList()
  },
  methods: {

    // 获取用户列表
    async getList() {
      this.listLoading = true
      try {
        const res = await page(this.listQuery)
        this.tableData = res.data
        this.total = res.total
      } catch (e) {
        console.error('获取用户列表错误' + e)
      }
      this.listLoading = false
    },

    // 查看用户详情
    async handleView(row) {
      try {
        const res = await getObj({ orgCode: row.orgCode || 0, userCode: row.userCode || 0 })
        this.form = res
      } catch (e) {
        console.error('查看用户详情失败' + e)
      }
      this.dialogStatus = 'query'
      this.dialogFormVisible = true
    },

    // 打开编辑用户弹框
    async handleUpdate(row) {
      this.resetTemp()
      try {
        const res = await getObj({ orgCode: row.orgCode || 0, userCode: row.userCode || 0 })
        this.form = res
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
      } catch (e) {
      }
    },

    // 删除用户
    async handleDelete(row, index) {
      this.confirmCrt({ message: '是否确认删除此用户？' }, async() => {
        try {
          await delObj({ orgCode: row.orgCode, id: row.id, userCode: row.userCode })
          this.$notify({ title: '成功', message: '删除成功', type: 'success' })
          this.$delete(this.tableData, index)
        } catch (e) {
          this.$notify({ title: '失败', message: e.message, type: 'error' })
          console.error('删除失败' + e)
        }
        if (this.tableData.length === 0 && this.listQuery.page > 1) {
          this.listQuery.page -= 1
        }
        this.getList()
      })
    },

    confirmUser(formName) {
      const isEdit = this.dialogStatus === 'update'
      const set = this.$refs
      set[formName].validate(async valid => {
        if (valid) {
          if (isEdit) {
            try {
              const row = this.form
              await putObj({ id: row.id, orgCode: row.orgCode || 0, userCode: row.userCode || 0 })
              this.$notify({ title: '成功', message: '修改成功', type: 'success', duration: 2000 })
            } catch (e) {
              this.$notify({ title: '失败', message: e.message, type: 'error', duration: 2000 })
            }
          } else {
            try {
              await addObj(this.form)
              this.$notify({ title: '成功', message: '添加成功', type: 'success', duration: 2000 })
            } catch (e) {
              this.$notify({ title: '失败', message: e.message, type: 'error', duration: 2000 })
            }
          }
          set[formName].resetFields()
          this.dialogFormVisible = false
          await this.getList()
        }
      })
    },

    // Dailog组织树选择
    getNodeDataParent(data) {
      this.form.orgCode = data.orgCode
    },

    /**
     * 获取组织树
     * @returns {Promise<void>}
     */
    async getTreeList() {
      try {
        const res = await fetchTree({ parentId: 0 })
        this.treeData = res
      } catch (e) {
        console.error('获取组织树失败' + e)
      }
    },

    /**
     * 获取全部用户
     * @returns {Promise<void>}
     */
    async getUserListAll() {
      try {
        const res = await getUserListAll()
        this.userList = res
      } catch (e) {
        console.error('获取组织树失败' + e)
      }
    },

    getAuth() {
      this.userOrgPostManager_add_btn = this.auth['userOrgPostManager:add_btn']
      this.userOrgPostManager_edit_btn = this.auth['userOrgPostManager:edit_btn']
      this.userOrgPostManager_del_btn = this.auth['userOrgPostManager:del_btn']
      this.userOrgPostManager_detail_btn = this.auth['userOrgPostManager:detail_btn']
    },

    cancel(formName) {
      this.dialogFormVisible = false
      if (this.$refs[formName]) {
        this.$refs['checkOrgTree'].orgName = ''
        this.$refs[formName].resetFields()
      }
    },
    resetTemp() {
      this.form = {
        orgCode: undefined,
        userCode: undefined,
        orgName: undefined
      }
    }

  }
}
</script>

<style lang="scss" scoped>

</style>

<template>
  <div v-loading="listLoading" class="app-container calendar-list-container">

    <el-row :gutter="20">
      <el-col :span="6" style="margin-top:15px;">
        <org-tree @getTree="getTree" @getNodeData="getNodeData" />
      </el-col>
      <el-col :span="18" style="margin-top:15px;">
        <el-card class="box-card">
          <template v-slot:header>
            <el-button-group>
              <el-button
                v-if="orgRoleManager_add_btn"
                size="small"
                type="primary"
                icon="plus"
                class="blue-btn"
                @click="handleCreate"
              >添加
              </el-button>
              <el-button
                v-if="orgRoleManager_copy_btn"
                size="small"
                type="primary"
                icon="plus"
                class="blue-btn"
                @click="handleCopyCreate"
              >复制角色
              </el-button>
            </el-button-group>
          </template>
          <el-table
            v-loading.body="listLoading"
            :data="tableData"
            size="mini"
            border
            fit
            highlight-current-row
            @row-click="rowClick"
          >
            <el-table-column align="center" label="序号" type="index" :index="indexMethod" width="65" />
            <el-table-column width="200px" align="center" label="角色名称">
              <template slot-scope="scope">
                <span>{{ scope.row.roleName }}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" width="120px" label="状态">
              <template slot-scope="scope">
                <span v-text="(scope.row.deleted === 0) ?'正常':'冻结' " />
              </template>
            </el-table-column>
            <el-table-column align="center" label="描述">
              <template slot-scope="scope">
                {{ scope.row.orgRoleDesc }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="操作" :width="tableBtnColumnWidth">
              <template slot-scope="scope">
                <el-button
                  v-if="orgRoleManager_assign_btn"
                  size="mini"
                  class="blue-btn"
                  type="primary"
                  @click="handleViewAuth(scope.row)"
                >权限分配
                </el-button>
                <el-button
                  v-if="orgRoleManager_edit_btn"
                  size="mini"
                  class="blue-btn"
                  type="primary"
                  @click="handleUpdate(scope.row)"
                >修改
                </el-button>
                <el-button
                  v-if="orgRoleManager_del_btn"
                  size="mini"
                  class="blue-btn"
                  type="primary"
                  @click="handleDelete(scope.row,scope.$index)"
                >删除
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
        </el-card>
        <el-card v-show="userFrom.orgRoleCode" class="box-card" style="margin-top:15px;">
          <template v-slot:header>
            <el-button-group>
              <el-button
                v-if="orgRoleOruManager_add_btn"
                size="small"
                type="primary"
                icon="plus"
                class="blue-btn"
                @click="handleViewUser"
              >添加
              </el-button>
            </el-button-group>
          </template>
          <el-table :data="userTableData" size="mini" border fit highlight-current-row>
            <el-table-column align="center" label="序号" type="index" :index="indexMethod" width="65" />
            <el-table-column width="200px" align="center" label="用户姓名">
              <template slot-scope="scope">
                <span>{{ scope.row.realName }}</span>
              </template>
            </el-table-column>
            <el-table-column width="100px" align="center" label="性别">
              <template slot-scope="scope">
                <span>{{ getLabelByCode(sexList,scope.row.sex) }}</span>
              </template>
            </el-table-column>
            <el-table-column width="200px" align="center" label="联系电话">
              <template slot-scope="scope">
                <span>{{ scope.row.tel }}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" label="描述">
              <template slot-scope="scope">
                {{ scope.row.orgroleDesc }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="操作" width="120">
              <template slot-scope="scope">
                <el-button
                  v-if="orgRoleOruManager_del_btn"
                  size="mini"
                  class="red-btn"
                  type="primary"
                  @click="delUserRole(scope.row,scope.$index)"
                >删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div v-show="!listLoading" class="pagination-container">
            <el-pagination
              :current-page.sync="userQuery.page"
              :page-sizes="[10,20,30, 50]"
              :page-size="userQuery.limit"
              layout="total, sizes, prev, pager, next, jumper"
              :total="userTotal"
              @size-change="handleSizeChangeUser"
              @current-change="handleCurrentChangeUser"
            />
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="form" :disabled="dialogStatus === 'query'" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="角色名称" prop="roleCode">
          <el-select
            v-model="form.roleCode"
            filterable
            :disabled="dialogStatus === 'update'"
            placeholder="请选择角色"
            style="width:100%;"
          >
            <el-option v-for="(item,index) of roleList" :key="index" :label="item.roleName" :value="item.roleCode" />
          </el-select>
        </el-form-item>
        <el-form-item label="部门名称" prop="orgCode">
          <input v-model="form.orgCode" type="hidden">
          <check-org
            ref="checkOrgTree"
            :tree="treeData"
            :status="dialogStatus"
            :name="orgName"
            @getNodeData="getNodeDataParent"
          />
        </el-form-item>
        <el-form-item label="状态" >
          <el-select v-model="form.deleted" filterable placeholder="请选择状态" style="width:100%;">
            <el-option v-for="(item,index) of orgRoleState" :key="index" :label="item.label" :value="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="form.orgRoleDesc" placeholder="请输入描述信息" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel('form')">取 消</el-button>
        <el-button
          v-if="dialogStatus === 'create' || dialogStatus === 'update'"
          class="blue-btn"
          type="primary"
          @click="confirmUser('form')"
        >
          {{ $t('permission.confirm') }}
        </el-button>
      </div>
    </el-dialog>

    <el-dialog title="复制角色" :visible.sync="dialogFormVisibleCopy" @close="cancelCopyRole('copyForm')">
      <el-form ref="copyForm" :model="copyForm" :rules="copyRules" label-width="100px">
        <el-form-item label="被复制部门">
          <check-org
            ref="checkCopyOrgTree0"
            :tree="treeData"
            @getNodeData="getRoleListByOrgCode"
          />
        </el-form-item>
        <el-form-item label="被复制角色" prop="sourceCode">
          <el-select v-model="copyForm.sourceCode" filterable placeholder="请选择角色" style="width:100%;">
            <el-option
              v-for="(item,index) of beCopiedRoleList"
              :key="index"
              :label="item.roleName"
              :value="item.orgRoleCode"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="新部门" prop="orgCode">
          <input v-model="copyForm.orgCode" type="hidden">
          <check-org
            ref="checkCopyOrgTree1"
            :tree="treeData"
            @getNodeData="getNodeDataOrgCode"
          />
        </el-form-item>
        <el-form-item label="新角色" prop="copyCode">
          <el-select v-model="copyForm.copyCode" filterable placeholder="请选择角色" style="width:100%;">
            <el-option v-for="(item,index) of roleList" :key="index" :label="item.roleName" :value="item.roleCode" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelCopyRole('copyForm')">取 消</el-button>
        <el-button
          class="blue-btn"
          type="primary"
          @click="confirmCopyRole('copyForm')"
        >
          确认
        </el-button>
      </div>
    </el-dialog>
    <el-dialog title="用户设置" :visible.sync="dialogAddUserVisible" @close="cancelUser('userFrom')">
      <el-form ref="userFrom" :model="userFrom" :rules="userFromRules" label-width="100px">
        <el-form-item label="用户" prop="userCode">
          <el-select v-model="userFrom.userCode" filterable placeholder="请选择用户" style="width:100%;">
            <el-option v-for="(item,index) of userList" :key="index" :label="item.realName" :value="item.userCode" />
          </el-select>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="userFrom.orgroleDesc" type="textarea" placeholder="请输入描述信息" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelUser('userFrom')">取 消</el-button>
        <el-button
          class="blue-btn"
          type="primary"
          @click="confirmAddUser('userFrom')"
        >
          {{ $t('permission.confirm') }}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getRolesListAll } from '@/api/system/role'
import {
  page,
  addObj,
  getObj,
  putObj,
  delObj,
  getRoleUserByRoleId,
  delRoleUser,
  saveRoleUser,
  getRolesByOrgCode,
  copyOrgRole
} from '@/api/system/orgrole'
import { getUserListAll } from '@/api/system/opu'
import baseMixins from '@/mixins/baseMixins'
import orgTree from '../components/orgTree'
import checkOrg from '../components/checkOrg'

export default {
  name: 'OrgRole',
  components: { orgTree, checkOrg },
  mixins: [baseMixins],
  data() {
    return {
      orgRoleState: [
        {
          label: '正常',
          value: 0
        },
        {
          label: '冻结',
          value: 1
        }
      ],
      sexList: [],
      dialogFormVisibleCopy: false,
      dialogAddUserVisible: undefined,
      currentOrgRoleCode: undefined,
      userList: [],
      treeData: [],
      roleList: [],
      beCopiedRoleList: [],
      orgName: undefined,
      orgCode: undefined,
      userFrom: {
        orgRoleCode: undefined,
        orgroleDesc: undefined,
        userCode: undefined
      },
      userTableData: [],
      userTotal: 0,
      userQuery: {
        page: 1,
        limit: 10,
        entity: {
          orgRoleCode: 0
        }
      },
      form: {
        orgCode: undefined,
        orgRoleCode: undefined,
        orgRoleDesc: undefined,
        roleCode: undefined,
        deleted:0
      },
      copyForm: {
        sourceCode: undefined,
        copyCode: undefined,
        orgCode: undefined
      },
      orgRoleManager_add_btn: false,
      orgRoleManager_edit_btn: false,
      orgRoleManager_del_btn: false,
      orgRoleManager_assign_btn: false,
      orgRoleOruManager_add_btn: false,
      orgRoleOruManager_del_btn: false,
      orgRoleManager_copy_btn: false,
      userFromRules: {
        userCode: [
          {
            required: true,
            message: '请选择用户',
            trigger: 'blur'
          }
        ]
      },
      copyRules: {
        orgCode: [
          {
            required: true,
            message: '请选择新部门',
            trigger: 'change'
          }
        ],
        copyCode: [
          {
            required: true,
            message: '请选择复制项',
            trigger: 'blur'
          }
        ],
        sourceCode: [
          {
            required: true,
            message: '请选择被复制项',
            trigger: 'blur'
          }
        ]
      },
      rules: {
        roleCode: [
          {
            required: true,
            message: '请选择角色',
            trigger: 'blur'
          }
        ],
        orgCode: [
          {
            required: true,
            message: '请选择组织',
            trigger: 'change'
          }
        ]
      }
    }
  },
  computed: {
    tableBtnColumnWidth() {
      let width = 120
      this.orgRoleManager_assign_btn ? width += 55 : width += 0
      this.orgRoleManager_edit_btn ? width += 45 : width += 0
      this.orgRoleManager_del_btn ? width += 45 : width += 0
      return width
    }
  },
  created() {
    this.getList()
    this.getAuth()
    this.getRoleList()
    this.getUsetListAll()
    this.getDicByCode(80, data => {
      this.sexList = data
    })
  },
  methods: {
    // 组织树点击事件
    getNodeData(data) {
      this.orgCode = data.orgCode
      this.orgName = data.orgName
      this.listQuery.entity.orgCode = data.orgCode
      this.userFrom.userCode = undefined
      this.userFrom.orgroleDesc = undefined
      this.userFrom.orgRoleCode = undefined
      this.getList()
    },
    // 获取组织树
    getTree(data) {
      this.treeData = data
    },
    // Dailog组织树选择
    getNodeDataParent(data) {
      this.form.orgCode = data.orgCode
    },

    // 删除
    handleDelete(row, index) {
      this.confirmCrt({ message: '是否确认删除此角色？' }, async() => {
        try {
          await delObj({ orgRoleCode: row.orgRoleCode })
          this.$delete(this.tableData, index)
        } catch (e) {
          this.$notify({ title: '失败', message: e.message, type: 'error', duration: 2000 })
        }
        if (this.tableData.length === 0 && this.listQuery.page > 1) {
          this.listQuery.page -= 1
        }
        this.getList()
        this.$notify({ title: '成功', message: '删除成功', type: 'success', duration: 2000 })
      })
    },
    // 获取列表
    getList() {
      page(this.listQuery).then((res) => {
        this.tableData = res.data
        this.total = res.total
      }).catch(e => {
        this.$message.error(e.message)
      })
      this.listLoading = false
    },

    // 获取角色列表
    async getRoleList() {
      try {
        const res = await getRolesListAll()
        this.roleList = res
      } catch (e) {
        this.$message.error(e.message)
      }
    },
    // 查看详情
    handleView(row) {
      getObj({ orgRoleCode: row.orgRoleCode }).then(res => {
        this.form.id = res.id
        this.form.orgCode = res.orgCode
        this.form.orgRoleCode = res.orgRoleCode
        this.form.roleCode = res.roleCode
        this.form.orgRoleDesc = res.orgRoleDesc
        this.dialogStatus = 'query'
        this.dialogFormVisible = true
      })
    },
    // 编辑
    handleUpdate(row) {
      this.resetTemp()
      getObj({ orgRoleCode: row.orgRoleCode }).then(res => {
        this.form.id = res.id
        this.form.deleted = res.deleted
        this.form.orgCode = res.orgCode
        this.form.orgRoleCode = res.orgRoleCode
        this.form.roleCode = res.roleCode
        this.form.orgRoleDesc = res.orgRoleDesc
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
      })
    },

    // 点击角色表格
    rowClick(row) {
      this.userFrom.orgRoleCode = row.orgRoleCode
      this.userQuery.entity.orgRoleCode = row.orgRoleCode
      this.getUserByRole()
      // if (row && row.orgRoleCode) {
      //   this.userFrom.entity.orgRoleCode = row.orgRoleCode
      // }
    },
    // 确认
    async confirmUser(formName) {
      const isEdit = this.dialogStatus === 'update'
      const set = this.$refs
      set[formName].validate(async valid => {
        if (valid) {
          if (isEdit) {
            try {
              await putObj(this.form)
              this.$notify({ title: '编辑成功', message: '编辑成功', type: 'success', duration: 2000 })
            } catch (e) {
              this.$notify({ title: '编辑失败', message: e.message, type: 'error', duration: 2000 })
            }
          } else {
            try {
              await addObj(this.form)
              this.$notify({ title: '添加成功', message: '添加成功', type: 'success', duration: 2000 })
            } catch (e) {
              this.$notify({ title: '添加失败', message: e.message, type: 'error', duration: 2000 })
            }
          }
          set[formName].resetFields()
          this.dialogFormVisible = false
          this.getList()
        }
      })
    },
    // 打开权限弹框
    handleViewAuth(row) {
      this.currentOrgRoleCode = row.orgRoleCode
      this.$router.push({ name: 'Authority', params: { code: this.currentOrgRoleCode }})
      // this.dialogAuthorityVisible = true
    },

    /**
       * 获取全部用户
       * @returns {Promise<void>}
       */
    async getUsetListAll() {
      try {
        const res = await getUserListAll()
        this.userList = res
      } catch (e) {
        console.error('获取用户失败' + e)
      }
    },

    // 添加用户弹框
    handleViewUser() {
      this.dialogAddUserVisible = true
    },

    // 获取组织角色下的用户
    async getUserByRole() {
      try {
        const res = await getRoleUserByRoleId(this.userQuery)
        this.userTableData = res.data
        this.userTotal = res.total
      } catch (e) {
        this.$message.error(e.message)
      }
    },

    // 删除该角色下的用户
    async delUserRole(row, index) {
      try {
        await delRoleUser({ id: row.id })
        this.$delete(this.userTableData, index)
        this.$notify({ title: '成功', message: '删除成功', type: 'success', duration: 2000 })
      } catch (e) {
        this.$notify({ title: '失败', message: '删除失败', type: 'error', duration: 2000 })
      }
      if (this.userTableData.length === 0 && this.userQuery.page > 1) {
        this.userQuery.page -= 1
      }
      this.getUserByRole()
    },

    // 关闭选择用户Dialog
    cancelUser(formName) {
      this.userFrom.orgroleDesc = undefined
      this.userFrom.userCode = undefined
      this.dialogAddUserVisible = false
      if (this.$refs[formName]) {
        this.$refs[formName].resetFields()
        this.$refs['checkOrgTree'].orgName = ''
      }
    },

    // 添加用户
    async confirmAddUser(formName) {
      const set = this.$refs
      set[formName].validate(async valid => {
        if (valid) {
          try {
            await saveRoleUser(this.userFrom)
            this.$notify({ title: '成功', message: '添加成功', type: 'success', duration: 2000 })
          } catch (e) {
            this.$notify({ title: '失败', message: e.message, type: 'error', duration: 2000 })
          }
          this.dialogAddUserVisible = false
          this.userFrom.orgroleDesc = undefined
          this.userFrom.userCode = undefined
          set[formName].resetFields()
          this.getUserByRole()
        }
      })
    },
    // 用户列表分页方法
    handleCurrentChangeUser(val) {
      this.userQuery.page = val
      this.getUserByRole()
    },
    // 用户列表分页方法
    handleSizeChangeUser(val) {
      this.userQuery.limit = val
      this.getUserByRole()
    },

    // 新部门code
    getNodeDataOrgCode(data) {
      this.copyForm.orgCode = data.orgCode
    },
    // 根据orgCode获取角色列表
    async getRoleListByOrgCode(data) {
      this.copyForm.sourceCode = undefined
      try {
        const res = await getRolesByOrgCode({ 'orgCode': data.orgCode })
        this.beCopiedRoleList = res
      } catch (e) {
        this.$message.error(e.message)
      }
    },

    // 复制角色信息
    confirmCopyRole(formName) {
      const set = this.$refs
      set[formName].validate(async valid => {
        if (valid) {
          try {
            await copyOrgRole(this.copyForm)
            this.$notify({ title: '成功', message: '复制成功', type: 'success', duration: 2000 })
          } catch (e) {
            this.$notify({ title: '失败', message: e.message, type: 'error', duration: 2000 })
          }
          this.dialogFormVisibleCopy = false
          this.copyForm.sourceCode = undefined
          this.copyForm.copyCode = undefined
          this.copyForm.orgCode = undefined
          set[formName].resetFields()
          this.getList()
        }
      })
    },
    // 关闭复制信息弹框
    cancelCopyRole(formName) {
      this.$refs['checkCopyOrgTree0'].orgName = undefined
      this.$refs['checkCopyOrgTree1'].orgName = undefined
      this.copyForm.sourceCode = undefined
      this.copyForm.copyCode = undefined
      this.copyForm.orgCode = undefined
      this.dialogFormVisibleCopy = false
      if (this.$refs[formName]) {
        this.$refs[formName].resetFields()
      }
    },
    getAuth() {
      this.orgRoleManager_add_btn = this.auth['orgRoleManager:add_btn']
      this.orgRoleManager_edit_btn = this.auth['orgRoleManager:edit_btn']
      this.orgRoleManager_del_btn = this.auth['orgRoleManager:del_btn']
      this.orgRoleManager_assign_btn = this.auth['orgRoleManager:assign_btn']
      this.orgRoleOruManager_add_btn = this.auth['orgRoleOruManager:add_btn']
      this.orgRoleOruManager_del_btn = this.auth['orgRoleOruManager:del_btn']
      this.orgRoleManager_copy_btn = this.auth['orgRoleManager:copy_btn']
    },
    handleCopyCreate() {
      this.dialogFormVisibleCopy = true
    },
    // 重置
    resetTemp() {
      this.form = {
        orgCode: this.orgCode,
        orgRoleCode: undefined,
        orgRoleDesc: undefined,
        roleCode: undefined,
        deleted:0
      }
    }
  }
}
</script>
<style lang="scss" scoped>
  .el-button-group {
    .blue-btn {
      margin-right: 1px !important;
    }
  }

  .filter-tree {
    height: 600px;
  }

  .ORGTree {
    position: absolute;
    cursor: default;
    background: #fff;
    color: #606266;
    z-index: 100;
    border: 1px solid #dcdfe6;
    border-radius: 5px;
    width: 100%;
    height: 300px;
    overflow: auto;
  }

  /deep/ .el-card {
    &.is-always-shadow {
      box-shadow: none;
    }

    .el-card__header {
      padding-top: 10px;
      padding-bottom: 10px;
    }
  }

  /deep/ .authDialog {
    .el-dialog {
      width: 88%;
    }
  }
</style>

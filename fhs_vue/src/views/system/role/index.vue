<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true" size="small">
        <el-form-item>
          <el-input v-model="listQuery.entity.roleName" size="small" class="filter-item" placeholder="请输入角色名称" style="width:200px;" />
        </el-form-item>
        <el-form-item>
          <el-button class="filter-item blue-btn" type="primary" size="small" @click="handleSearch">
            {{ $t('system.users.search') }}
          </el-button>
          <el-button v-if="roleManager_add_btn" class="filter-item blue-btn" style="margin-left: 10px;" size="small" type="primary" @click="handleCreate">
            添加
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table v-loading.body="listLoading" :data="tableData" size="mini" border fit highlight-current-row>
      <el-table-column align="center" label="序号" type="index" width="65" :index="indexMethod" />
      <el-table-column width="200px" align="center" label="角色名称">
        <template slot-scope="scope">
          {{ scope.row.roleName }}
        </template>
      </el-table-column>
      <el-table-column min-width="250px" align="center" label="角色下用户">
        <template slot-scope="scope">
          <el-tag v-for="(item,index) in scope.row.realName" :key="index" style="margin-left:5px;margin-top:5px;">{{ item }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column width="250px" align="center" label="角色描述" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <span>{{ scope.row.roleDesc }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" :width="tableBtnColumnWidth"> <template slot-scope="scope">
        <el-button v-if="roleManager_edit_btn" size="mini" class="blue-btn" type="primary" @click="handleUpdate(scope.row)">修改
        </el-button>
        <el-button v-if="roleManager_detail_btn" type="primary" class="blue-btn" size="mini" @click="handleView(scope.row)">查看
        </el-button>
        <el-button v-if="roleManager_del_btn" size="mini" class="blue-btn" type="primary" @click="handleDelete(scope.row,scope.$index)">删除
        </el-button>
      </template>
      </el-table-column>
    </el-table>
    <div v-show="!listLoading" class="pagination-container">
      <el-pagination :current-page.sync="listQuery.page" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange" @current-change="handleCurrentChange" />
    </div>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="form" :disabled="dialogStatus === 'query'" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="form.roleName" placeholder="请输入角色名称" />
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="form.roleDesc" type="textarea" :rows="3" placeholder="请输入描述" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel('form')">取 消</el-button>
        <el-button v-if="dialogStatus === 'create' || dialogStatus === 'update'" class="blue-btn" type="primary" @click="confirmUser('form')">
          {{ $t('permission.confirm') }}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { page, addObj, getObj, delObj, putObj } from '@/api/system/role'
import baseMixins from '@/mixins/baseMixins'
import { inputValidator } from '@/utils/formValidation'
import util from '@/utils/yxUtil'

export default {
  name: 'Role',
  mixins: [baseMixins],
  data() {
    return {
      form: {
        roleCode: undefined,
        roleName: undefined,
        remark: undefined
      },
      rules: {
        roleName: [{
          required: true,
          message: '请输入角色名称',
          trigger: 'blur'
        },
        inputValidator('角色名称'),
        {
          min: 1,
          max: 50,
          message: '长度在 1 到 50 个字符',
          trigger: 'blur'
        }]
      },
      roleManager_add_btn: false,
      roleManager_edit_btn: false,
      roleManager_del_btn: false,
      roleManager_detail_btn: false
    }
  },
  computed: {
    tableBtnColumnWidth() {
      let width = 120
      this.roleManager_edit_btn ? width += 45 : width += 0
      this.roleManager_del_btn ? width += 45 : width += 0
      this.roleManager_detail_btn ? width += 45 : width += 0
      return width
    }
  },
  created() {
    this.getAuth()
    this.getList()
  },
  methods: {
    async getList() {
      try {
        const res = await page(this.listQuery)
        console.log(res)
        if (res.data && res.data.length > 0) {
          res.data.forEach(item => {
            if (!util.isEmpty(item.realName)) {
              item.realName = item.realName.split(',')
            } else {
              item.realName = []
            }
          })
        }
        this.tableData = res.data
        this.total = res.total
      } catch (e) {
        console.error('获取角色列表失败' + e)
      }
      this.listLoading = false
    },

    handleView(row) {
      getObj({ id: row.id }).then(res => {
        this.form = res
        this.dialogFormVisible = true
        this.dialogStatus = 'query'
      })
    },

    handleUpdate(row) {
      this.resetTemp()
      getObj({ id: row.id }).then(res => {
        this.form = res
        this.dialogFormVisible = true
        this.dialogStatus = 'update'
      })
    },

    handleDelete(row, index) {
      this.confirmCrt({ message: '是否确认删除此角色？' }, async() => {
        try {
          await delObj({ id: row.id })
          this.$delete(this.tableData, index)
          this.$notify({ title: '成功', message: '删除成功', type: 'success', duration: 2000 })
        } catch (e) {
          this.$notify({ title: '失败', message: '删除失败', type: 'error', duration: 2000 })
          console.error('删除失败' + e)
        }
        if (this.tableData.length === 0 && this.listQuery.page > 1) {
          this.listQuery.page -= 1
        }
        this.getList()
      })
    },

    async confirmUser(formName) {
      const isEdit = this.dialogStatus === 'update'
      const set = this.$refs
      set[formName].validate(valid => {
        if (valid) {
          if (isEdit) {
            this.updateData()
          } else {
            this.addData()
          }
        }
      })
    },

    // 更新数据
    async updateData() {
      try {
        await putObj(this.form.id, this.form)
        this.$notify({
          title: '成功',
          message: `修改成功`,
          type: 'success',
          duration: 2000
        })
      } catch (e) {
        console.log(e)
        this.$notify({
          title: '失败',
          message: `修改失败`,
          type: 'error',
          duration: 2000
        })
      }
      this.dialogFormVisible = false
      this.getList()
    },
    // 添加数据
    async addData() {
      try {
        await addObj(this.form)
        this.$notify({
          title: '成功',
          message: `添加成功`,
          type: 'success'
        })
      } catch (e) {
        console.log(e)
        this.$notify({
          title: '失败',
          message: `添加失败`,
          type: 'error'
        })
      }
      this.dialogFormVisible = false
      this.getList()
    },

    getAuth() {
      this.roleManager_add_btn = this.auth['roleManager:add_btn']
      this.roleManager_edit_btn = this.auth['roleManager:edit_btn']
      this.roleManager_del_btn = this.auth['roleManager:del_btn']
      this.roleManager_detail_btn = this.auth['roleManager:detail_btn']
    },
    resetTemp() {
      this.form = {
        roleCode: undefined,
        roleName: undefined,
        remark: undefined
      }
    }
  }
}
</script>

<style lang="scss" scoped>

</style>

<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.name" size="small" class="filter-item" placeholder="请输入用户名" style="width:200px;" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="small" @click="handleSearch">
        {{ $t('system.users.search') }}
      </el-button>
      <el-button
        v-if="baseUserManager_btn_add"
        class="filter-item"
        style="margin-left: 10px;"
        size="small"
        type="primary"
        icon="el-icon-plus"
        @click="handleCreate"
      >
        添加
      </el-button>
    </div>
    <el-table v-loading.body="listLoading" :data="tableData" size="mini" border fit highlight-current-row>
      <el-table-column align="center" label="序号" type="index" width="65" />
      <el-table-column width="150px" align="center" label="用户角色">
        <template scope="scope">
          <span>{{ scope.row.type }}</span>
        </template>
      </el-table-column>
      <el-table-column width="150px" align="center" label="用户名">
        <template scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="姓名">
        <template scope="scope">
          <span>{{ scope.row.name }}</span>
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
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form
        ref="form"
        :disabled="dialogStatus === 'query'"
        :inline="true"
        :model="form"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item label="角色类型" prop="groupType">
          <el-select v-model="form['groupType']" placeholder="请选择角色类型" @change="groupTypeChange">
            <el-option
              v-for="item in groupTypeStore"
              :key="item.GROUPID"
              :label="item.NAME"
              :value="item.GROUPID"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="角色" prop="groupId">
          <el-select v-model="form['groupId']" placeholder="请选择角色">
            <el-option
              v-for="item in groupStore"
              :key="item.ID"
              :label="item.NAME"
              :value="item.ID"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="用户" prop="userInfo">
          <el-input v-model="form.userInfo" placeholder="请输入用户名或者中文名" style="width:483px;" />
        </el-form-item>
        <el-button type="primary">搜索</el-button>
      </el-form>
      <el-table
        v-loading.body="userLoading"
        :data="userList"
        border
        fit
        highlight-current-row
        style="width: 100%"
        height="243"
        @current-change="handleAddChange"
      >
        <el-table-column align="center" label="序号" type="index" width="65px" />
        <el-table-column width="150px" align="center" label="用户名">
          <template scope="scope">
            <span>{{ scope.row.username }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="姓名">
          <template scope="scope">
            <span>{{ scope.row.name }}</span>
          </template>
        </el-table-column>
      </el-table>
      <div v-show="!userLoading" class="pagination-container">
        <el-pagination
          :current-page.sync="userListQuery.page"
          :page-sizes="[10,20,30, 50]"
          :page-size="userListQuery.limit"
          layout="total, sizes, prev, pager, next, jumper"
          :total="userTotal"
          @size-change="userHandleSizeChange"
          @current-change="userHandleCurrentChange"
        />
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="danger" class="el-close-btn" @click="cancel('form')">
          {{ $t('permission.cancel') }}
        </el-button>
        <el-button
          v-if="dialogStatus === 'create' || dialogStatus === 'update'"
          type="primary"
          @click="confirmUser('form')"
        >
          {{ $t('permission.confirm') }}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { deepClone } from '@/utils'
import { page, addObj, getObj, delObj, putObj } from '@/api/system/user'
import baseMixins from '@/mixins/baseMixins'

export default {
  name: 'BaseUser',
  mixins: [baseMixins],
  data() {
    return {
      form: {
        groupType: undefined, groupId: undefined, userInfo: undefined, userId: undefined
      },
      userLoading: true,
      userList: [],
      userListQuery: {
        page: 1,
        limit: 20
      },
      rules: {},
      baseUserManager_btn_add: true
    }
  },
  methods: {
    getList() {
      page(this.listQuery).then((res) => {
        this.tableData = res.result
      }).catch((err) => {
        console.error(err)
      })
      this.listLoading = false
    },

    handleSearch() {
    },

    handleView(row) {
      this.listLoading = true
      getObj({ userId: row.id }).then(res => {
        this.form = res.result
        this.dialogStatus = 'query'
        this.dialogFormVisible = true
      })
      this.listLoading = false
    },

    handleUpdate(row) {
      this.resetTemp()
      getObj({ userId: row.id }).then(res => {
        this.form = res.result
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
      })
    },

    handleDelete(row) {
      this.$confirm(this.$t('system.users.confirmtext'), this.$t('system.users.warning'), {
        confirmButtonText: this.$t('system.users.confirm'),
        cancelButtonText: this.$t('system.users.cancel'),
        type: 'warning'
      })
        .then(() => {
          delObj({ userId: row.id }).then(() => {
            this.$notify({
              title: '成功',
              message: `删除成功`,
              type: 'success'
            })
            this.getList()
          })
        })
        .catch(err => {
          console.error(err)
        })
    },

    resetTemp() {
      this.form = {
        username: undefined,
        name: undefined,
        sex: 0,
        email: undefined,
        mobile: undefined
      }
    },
    async confirmUser(formName) {
      const isEdit = this.dialogStatus === 'update'
      const { name } = this.form.username
      const set = this.$refs
      set[formName].validate(valid => {
        if (valid) {
          if (isEdit) {
            this.form.userId = this.form.id
            putObj(this.form)
            this.$notify({
              title: '编辑成功',
              dangerouslyUseHTMLString: true,
              message: `
              <div>用户名: ${name}</div>
            `,
              type: 'success',
              duration: 1000
            })
          } else {
            addObj(this.form).then(res => {
              this.$notify({
                title: '添加成功',
                dangerouslyUseHTMLString: true,
                message: `
              <div>用户名: ${name}</div>
            `,
                type: 'success',
                duration: 1000
              })
            })
          }
          set[formName].resetFields()
          this.dialogFormVisible = false
          this.getList()
        }
      })
    },
    handleAddChange(val) {
      const id = val['id']
      if (id) {
        this.form.userId = id
      }
    },
    userHandleSizeChange(val) {
      this.userListQuery.limit = val
    },
    userHandleCurrentChange(val) {
      this.userListQuery.page = val
    }
  }

}
</script>

<style scoped>

</style>

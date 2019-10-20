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
          <el-button  class="filter-item blue-btn" style="margin-left: 10px;" size="small" type="primary" @click="handleCreate">
            添加
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table v-loading.body="listLoading" :data="tableData" size="mini" border fit highlight-current-row>
      <el-table-column align="center" label="序号" type="index" :index="indexMethod" width="65" />
      <el-table-column width="150" align="center" label="用户姓名"> <template slot-scope="scope">
        <span>{{ scope.row.realName }}</span>
      </template> </el-table-column>
      <el-table-column width="150" align="center" label="性别"> <template slot-scope="scope">
        <span>{{ getLabelByCode(sexList,scope.row.sex) }}</span>
      </template> </el-table-column>
      <el-table-column width="150" align="center" label="登录名"> <template slot-scope="scope">
        <span>{{ scope.row.accountNumber }}</span>
      </template> </el-table-column>
      <el-table-column align="center" width="150" label="联系电话"> <template slot-scope="scope">
        <span>{{ scope.row.tel }}</span>
      </template>
      </el-table-column>
      <el-table-column align="center" label="微信号"> <template slot-scope="scope">
        <span>{{ scope.row.wechat }}</span>
      </template>
      </el-table-column>
      <el-table-column align="center" label="邮箱"> <template slot-scope="scope">
        <span>{{ scope.row.email }}</span>
      </template>
      </el-table-column>
      <el-table-column align="center" fixed="right" label="操作" :width="tableBtnColumnWidth">
        <template slot-scope="scope">
          <el-button size="mini" class="blue-btn" type="primary" @click="handleUpdate(scope.row)">修改
          </el-button>
          <el-button size="mini" class="blue-btn" type="primary" @click="handleView(scope.row)">查看
          </el-button>
          <el-button class="blue-btn" size="mini" type="primary" @click="handleDelete(scope.row,scope.$index)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div v-show="!listLoading" class="pagination-container">
      <el-pagination
        :current-page.sync="listQuery.page"
        :page-sizes="[10,20,30, 50]"
        :page-size.sync="listQuery.limit"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" @close="cancel('form')">
      <input v-model="form.orgCode" type="hidden">
      <el-form ref="form" :disabled="dialogStatus === 'query'" :model="form" label-width="100px">
        <el-form-item label="用户账号" prop="accountNumber">
          <el-input v-model="form.username" :disabled="dialogStatus === 'update'" placeholder="请输入账户号" />
        </el-form-item>
        <el-form-item label="用户密码" prop="password" placeholder="请输入密码">
          <el-input v-model="form.password" type="password" />
        </el-form-item>
        <el-form-item label="UID" prop="realName">
          <el-input v-model="form.uid" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="联系电话" prop="tel" placeholder="请输入联系电话">
          <el-input v-model="form.mobile" />
        </el-form-item>
      </el-form>
      <template v-slot:footer>
        <div class="dialog-footer">
          <el-button @click="cancel('form')">
            {{ $t('permission.cancel') }}
          </el-button>
          <el-button v-if="dialogStatus === 'create' || dialogStatus === 'update'" class="blue-btn" type="primary" @click="confirmUser('form')">
            {{ $t('permission.confirm') }}
          </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import { page, addObj, getObj, delObj, putObj, resetPassword } from '@/api/system/user'
import { inputValidator, checkIdCard, checkPhone, checkEmail, checkChineseStr } from '@/utils/formValidation'
import baseMixins from '@/mixins/baseMixins'
import {addUser} from '@/api/test'
export default {
  name: 'User',
  mixins: [baseMixins],
  data() {
    return {
      sexList: [],
      form: {
        username: undefined,
        password: undefined,
        uid: 10,
        id: 11,
        mobile: '19929029263'
      },
      rules: {
        accountNumber: [
          {
            required: true,
            trigger: 'blur',
            message: '请输入用户账号'
          },
          {
            required: true,
            trigger: 'blur',
            validator: checkChineseStr
          },
          inputValidator('用户账号')
        ],
        password: [
          {
            required: true,
            message: '请输入用户密码',
            trigger: 'blur'
          },
          inputValidator('用户密码')
        ],
        realName: [
          {
            required: true,
            message: '请输入用户姓名',
            trigger: 'blur'
          },
          inputValidator('用户姓名')
        ],
        idCard: [
          {
            required: true,
            trigger: 'blur',
            validator: checkIdCard
          }
        ],
        tel: [
          {
            required: true,
            trigger: 'blur',
            validator: checkPhone
          }
        ],
        email: [
          {
            required: true,
            trigger: 'blur',
            validator: checkEmail
          }
        ],
        wechat: [
          {
            required: true,
            message: '请输入微信号',
            trigger: 'blur'
          }
        ]
      },
      // user: Object.assign({}, defaultUser),
      userManager_add_btn: false,
      userManager_edit_btn: false,
      userManager_del_btn: false,
      userManager_detail_btn: false

    }
  },
  computed: {
    tableBtnColumnWidth() {
      let width = 120
      this.userManager_edit_btn ? width += 45 : width += 0
      this.userManager_del_btn ? width += 45 : width += 0
      this.userManager_detail_btn ? width += 45 : width += 0
      return width
    }
  },
  async created() {
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
        const res = await getObj({ userCode: row.userCode })
        this.form = res.userVO
        this.dialogStatus = 'query'
      } catch (e) {
        console.error('查看用户详情失败' + e)
      }
      this.dialogFormVisible = true
    },

    // 打开编辑用户弹框
    async handleUpdate(row) {
      this.resetTemp()
      try {
        const res = await getObj({ userCode: row.userCode })
        this.form = res.userVO
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
      } catch (e) {
        this.dialogFormVisible = true
      }
    },

    // 删除用户
    async handleDelete(row, index) {
      this.confirmCrt({ message: '是否确认删除此用户？' }, async() => {
        try {
          await delObj({ userCode: row.userCode })
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
      addUser(this.form)
    },
    /**
       * 重置密码
       * @param row
       * @returns {Promise<void>}
       */
    async resetPwd(row) {
      try {
        const res = await resetPassword({ userCode: row.userCode })
        this.$notify({ title: '成功', message: res, type: 'success', duration: 2000 })
      } catch (e) {
        this.$notify({ title: '失败', message: e.message, type: 'error', duration: 2000 })
      }
    },

    getAuth() {
      this.userManager_add_btn = this.auth['userManager:add_btn']
      this.userManager_edit_btn = this.auth['userManager:edit_btn']
      this.userManager_del_btn = this.auth['userManager:del_btn']
      this.userManager_detail_btn = this.auth['userManager:detail_btn']
    },
    resetTemp() {
      this.form = {
        accountLocation: undefined,
        accountNumber: undefined,
        accountNumberState: undefined,
        birthdate: undefined,
        cardNo: undefined,
        email: undefined,
        englishName: undefined,
        homeAddress: undefined,
        housbankbrn: undefined,
        housebank: undefined,
        idCard: undefined,
        officeTel: undefined,
        password: undefined,
        qq: undefined,
        realName: undefined,
        remark: undefined,
        sex: undefined,
        tel: undefined,
        userCode: undefined,
        userType: undefined,
        wechat: undefined,
        workAddress: undefined
      }
    }

  }
}
</script>

<style lang="scss" scoped>
  .ORGTree{
    position:absolute;
    cursor:default;
    background:#fff;
    color:#606266;
    z-index:100;
    border:1px solid #dcdfe6;
    border-radius:5px;
    width:100%;
    height:300px;
    overflow:auto;
  }
</style>

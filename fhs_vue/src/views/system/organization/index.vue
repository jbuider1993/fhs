<template>
  <div v-loading="listLoading" class="app-container calendar-list-container">

    <el-row :gutter="20">
      <el-col :span="6" style="margin-top:15px;">
        <org-tree ref="orgTree" :add="orgManager_add_btn" :edit="orgManager_edit_btn" :del="orgManager_del_btn" :is-operation="true" @getTree="getTree" @create="handleCreate" @update="handleUpdate" @delete="handleDelete" @getNodeData="getNodeData" />
      </el-col>
      <el-col :span="18" style="margin-top:15px;">
        <el-card class="box-card">
          <template v-if="orgName" v-slot:header>
            <span class="orgTitle">{{ orgName }}</span>
          </template>
          <el-table v-loading.body="listLoading" :data="tableData" size="mini" border fit highlight-current-row>
            <el-table-column align="center" label="序号" type="index" width="65" />
            <el-table-column width="200px" align="center" label="部门代码">
              <template slot-scope="scope">
                <span>{{ scope.row.orgEncrypt }}</span>
              </template>
            </el-table-column>
            <el-table-column width="200px" align="center" label="部门名称">
              <template slot-scope="scope">
                {{ scope.row.orgName }}
              </template>
            </el-table-column>
            <el-table-column width="150px" align="center" label="部门简称">
              <template slot-scope="scope">
                {{ scope.row.orgAlias }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="上级部门">
              <template scope="scope">
                <el-tag>{{ scope.row.parentName }}</el-tag>
              </template>
            </el-table-column>
            <!--<el-table-column v-if="orgManager_edit_btn || orgManager_del_btn" align="center" label="操作" width="240"> <template slot-scope="scope">-->
            <!--<el-button v-if="orgManager_edit_btn" size="mini" class="blue-btn" type="primary" @click="handleUpdate(scope.row)">修改-->
            <!--</el-button>-->
            <!--<el-button v-if="orgManager_del_btn" size="mini" class="red-btn" type="primary" @click="handleDelete(scope.row,scope.$index)">删除-->
            <!--</el-button>-->
            <!--</template>-->
            <!--</el-table-column>-->
          </el-table>
          <div v-show="!listLoading" class="pagination-container">
            <el-pagination :current-page.sync="listQuery.page" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange" @current-change="handleCurrentChange" />
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="form" :disabled="dialogStatus === 'query'" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="上级部门" prop="orgCode">
          <check-org ref="checkOrgTree" :tree="treeData" :status="dialogStatus" :name="orgName" @getNodeData="getNodeDataParent" />
        </el-form-item>
        <el-form-item label="部门名称" prop="orgName">
          <el-input v-model="form.orgName" placeholder="请输入部门名称" />
        </el-form-item>
        <el-form-item label="部门代码">
          <el-input v-model="form.orgEncrypt" placeholder="请输入部门代码" />
        </el-form-item>
        <el-form-item label="部门简称">
          <el-input v-model="form.orgAlias" placeholder="请输入部门简称" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button class="red-btn" type="primary" @click="cancel('form')">取 消</el-button>
        <el-button v-if="dialogStatus === 'create' || dialogStatus === 'update'" class="blue-btn" type="primary" @click="confirmUser('form')">
          {{ $t('permission.confirm') }}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { page, addObj, getObj, putObj, delObj } from '@/api/system/organization'
import baseMixins from '@/mixins/baseMixins'
import { inputValidator } from '@/utils/formValidation'
import orgTree from '../components/orgTree'
import checkOrg from '../components/checkOrg'
export default {
  name: 'Organization',
  components: { orgTree, checkOrg },
  mixins: [baseMixins],
  data() {
    return {
      orgName: undefined,
      orgCode: undefined,
      treeLoading: true,
      listLoading: false,
      formEdit: true,
      formAdd: true,
      treeData: [],
      parentName: undefined,
      form: {
        orgAlias: undefined,
        orgCode: undefined,
        orgEncrypt: undefined,
        orgName: undefined,
        parentId: undefined
      },
      orgManager_add_btn: false,
      orgManager_edit_btn: false,
      orgManager_del_btn: false,
      rules: {
        // orgEncrypt: [
        //   {
        //     required: true,
        //     message: '请输入部门代码',
        //     trigger: 'blur'
        //   }
        // ],
        orgName: [
          {
            required: true,
            message: '请输入部门名称',
            trigger: 'blur'
          },
          inputValidator('部门名称')
        ]
        // parentName: [
        //   {
        //     required: true,
        //     message: '请选择上级部门',
        //     trigger: 'blur'
        //   }
        // ]
      }
    }
  },
  created() {
    this.getAuth()
    this.getList()
  },
  methods: {
    // 获取组织树
    getTree(data) {
      this.treeData = data
    },
    // 组织树点击事件
    getNodeData(data) {
      this.orgCode = data.orgCode
      this.orgName = data.orgName
      this.listQuery.entity.orgCode = data.orgCode
      this.getList()
    },

    // Dailog组织树选择
    getNodeDataParent(data) {
      this.form.parentId = data.orgCode
    },
    handleDelete(code) {
      this.confirmCrt({ message: '确认删除此条数据？' }, async() => {
        try {
          await delObj({ id: code })
          this.$notify({ title: '成功', message: '删除成功', type: 'success', duration: 2000 })
        } catch (e) {
          console.error('删除失败' + e)
          this.$notify({ title: '失败', message: e.message, type: 'error', duration: 2000 })
        }
        this.getList()
        this.$refs['orgTree'].getTreeList()
      })
    },
    // 获取列表
    getList() {
      page(this.listQuery).then((res) => {
        this.tableData = res.data
        this.total = res.total
      }).catch((err) => {
        console.error(err)
      })
      this.listLoading = false
    },

    handleUpdate(row) {
      this.resetTemp()
      getObj({ orgCode: this.orgCode }).then(res => {
        this.orgName = res.parentName
        this.form = res
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
      })
    },

    async confirmUser(formName) {
      const isEdit = this.dialogStatus === 'update'
      const set = this.$refs
      set[formName].validate(async valid => {
        if (valid) {
          if (isEdit) {
            try {
              await putObj(this.form.id, this.form)
              this.$notify({ title: '成功', message: '编辑成功', type: 'success', duration: 2000 })
            } catch (e) {
              console.error('编辑部门失败' + e)
              this.$notify({ title: '失败', message: e.message, type: 'success', duration: 2000 })
            }
            set[formName].resetFields()
            this.dialogFormVisible = false
            this.$refs['orgTree'].getTreeList()
          } else {
            try {
              await addObj(this.form)
              this.$notify({ title: '成功', message: '添加成功', type: 'success', duration: 2000 })
            } catch (e) {
              console.error('添加部门失败' + e)
              this.$notify({ title: '失败', message: e.message, type: 'error', duration: 2000 })
            }
            set[formName].resetFields()
            this.dialogFormVisible = false
            this.getList()
            this.$refs['orgTree'].getTreeList()
          }
        }
      })
    },
    getAuth() {
      this.orgManager_add_btn = this.auth['orgManager:add_btn']
      this.orgManager_edit_btn = this.auth['orgManager:edit_btn']
      this.orgManager_del_btn = this.auth['orgManager:del_btn']
    },
    resetTemp() {
      this.form = {
        orgAlias: undefined,
        orgCode: undefined,
        orgEncrypt: undefined,
        orgName: undefined,
        orgStatus: undefined,
        orgType: undefined,
        parentId: this.orgCode,
        remark: undefined,
        parentName: undefined
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
    height:600px;
  }

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
  /deep/ .el-card {
    &.is-always-shadow {
      box-shadow: none;
    }
   .el-card__header {
      padding-top:10px;
      padding-bottom:10px;
    }
  }
  .orgTitle {
    height:32px;
    line-height: 32px;
    font-weight: 400;
    color:#333333;
    font-size:16px;
  }
</style>

<template>
  <div class="app-container">
    <el-row :gutter="10">
      <el-col :span="6">
        <div class="filter-container">
          <el-button-group v-if="dictTypeManager_add_btn || dictTypeManager_edit_btn || dictTypeManager_detail_btn|| dictTypeManager_del_btn">
            <el-button v-if="dictTypeManager_add_btn" type="primary" class="blue-btn" size="small" @click="handleCreateType">添加类型</el-button>
            <el-button v-if="dictTypeManager_edit_btn" type="primary" class="blue-btn" size="small" @click="handleUpdateType">修改</el-button>
            <el-button v-if="dictTypeManager_detail_btn" type="primary" class="blue-btn" size="small" @click="handleViewType">查看</el-button>
            <el-button v-if="dictTypeManager_del_btn" type="primary" class="blue-btn" size="small" @click="handleDeleteType">删除</el-button>
          </el-button-group>
        </div>
        <div class="dictionariesTree">
          <el-tree
            ref="tree"
            v-loading.body="treeLoading"
            :data="treeList"
            node-key="id"
            highlight-current
            :props="defaultProps"
            @node-click="nodeClick"
          />
        </div>
      </el-col>
      <el-col :span="18">
        <div class="filter-container">
          <el-button v-if="dictManager_add_btn" class="blue-btn" type="primary" size="small" @click="handleCreate">添加</el-button>
        </div>
        <el-table v-loading.body="listLoading" :data="tableData" size="mini" border fit highlight-current-row style="margin-top:10px;">
          <el-table-column
            type="index"
            width="65"
            label="序号"
            align="center"
          />
          <el-table-column
            prop="dict_type_code"
            label="字典类型编码"
            width="120"
            align="center"
          />
          <el-table-column
            prop="dict_code"
            label="字典明细编码"
            width="120"
            align="center"
          />
          <el-table-column
            prop="dict_name"
            label="字典明细名称"
            width="200"
            align="center"
          />
          <el-table-column
            prop="dict_desc"
            label="描述"
            align="center"
          />
          <el-table-column  align="center" label="操作" :width="tableBtnColumnWidth">
            <template slot-scope="scope">
              <el-button
                v-if="dictManager_edit_btn"
                size="mini"
                type="primary"
                class="blue-btn"
                @click="handleUpdate(scope.row)"
              >修改
              </el-button>
              <el-button v-if="dictManager_detail_btn" type="primary" class="blue-btn" size="mini" @click="handleView(scope.row)">查看
              </el-button>
              <el-button
                v-if="dictManager_del_btn"
                size="mini"
                type="primary"
                class="blue-btn"
                @click="handleDelete(scope.row,scope.$index)"
              >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="pagination-container">
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
      </el-col>
    </el-row>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" @close="cancel('form')">
      <el-form ref="form" :disabled="dialogStatus === 'query'" :rules="rules" :model="form" label-width="120px">
        <el-form-item label="字典明细编码" prop="dict_code">
          <el-input v-model="form.dict_code" :disabled="dialogStatus === 'update'" placeholder="请输入字典明细编码" />
        </el-form-item>
        <el-form-item label="字典明细名称" prop="dict_name" placeholder="请输入字典明细名称">
          <el-input v-model="form.dict_name" />
        </el-form-item>
        <el-form-item label="描述" placeholder="请输入描述">
          <el-input v-model="form.dict_desc" type="textarea" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" class="red-btn" @click="cancel('form')">
          {{ $t('permission.cancel') }}
        </el-button>
        <el-button
          v-if="dialogStatus === 'create' || dialogStatus === 'update'"
          class="blue-btn"
          type="primary"
          @click="confirmDict('form')"
        >
          {{ $t('permission.confirm') }}
        </el-button>
      </div>
    </el-dialog>
    <el-dialog
      :title="textMap[dialogTreeStatus]"
      :visible.sync="dialogTreeFormVisible"
      @close="cancelDictType('formDictType')"
    >
      <el-form
        ref="formDictType"
        :disabled="dialogTreeStatus === 'query'"
        :rules="rulesType"
        :model="dictTypeForm"
        label-width="120px"
      >
        <el-form-item label="字典类型编码" prop="dict_type_code">
          <el-input
            v-model="dictTypeForm.dict_type_code"
            :disabled="dialogTreeStatus === 'update'"
            placeholder="请输入字典类型编码"
          />
        </el-form-item>
        <el-form-item label="字典类型名称" prop="dict_type_name" placeholder="请输入字典类型名称">
          <el-input v-model="dictTypeForm.dict_type_name" />
        </el-form-item>
        <el-form-item label="描述" placeholder="请输入描述">
          <el-input v-model="dictTypeForm.remark" type="textarea" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" class="red-btn" @click="cancelDictType('formDictType')">
          {{ $t('permission.cancel') }}
        </el-button>
        <el-button
          v-if="dialogTreeStatus === 'create' || dialogTreeStatus === 'update'"
          type="primary"
          class="blue-btn"
          @click="confirmDictType('formDictType')"
        >
          {{ $t('permission.confirm') }}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  page,
  getDictListByType,
  addDictType,
  getDictType,
  putDictType,
  delDictType,
  getDictObj,
  addDictObj,
  delDictObj,
  putDictObj
} from '@/api/system/dict'
import baseMixins from '@/mixins/baseMixins'

export default {
  name: 'Dict',
  components: {},
  mixins: [baseMixins],
  props: {},
  data() {
    return {
      treeLoading: true,
      listLoading: false,
      dict_node_id: undefined,
      dict_type_code: undefined,
      dialogTreeStatus: '',
      dialogTreeFormVisible: false,
      treeList: [],
      form: {
        deleted: undefined,
        dict_code: undefined,
        dict_desc: undefined,
        dict_name: undefined,
        dict_type_code: undefined,
        dict_type_name: undefined,
        modified_at: undefined,
        modified_id: undefined,
        parent_code: undefined,
        sort_no: undefined,
        state: undefined
      },
      dictTypeManager_add_btn: false,
      dictTypeManager_edit_btn: false,
      dictTypeManager_detail_btn: false,
      dictTypeManager_del_btn: false,
      dictManager_add_btn: false,
      dictManager_edit_btn: false,
      dictManager_detail_btn: false,
      dictManager_del_btn: false,
      dictTypeForm: {
        id: undefined,
        parent_code: 0,
        dict_type_code: undefined,
        dict_type_name: undefined
      },
      defaultProps: {
        children: 'child',
        label: 'dict_type_name'
      },
      rules: {
        dict_code: [{
          required: true,
          message: '请输入字典明细编码',
          trigger: 'blur'
        }],
        dict_name: [{
          required: true,
          message: '请输入字典明细名称',
          trigger: 'blur'
        },
        {
          min: 1,
          max: 10,
          message: '长度在 1 到 10 个字符',
          trigger: 'blur'
        }]
      },
      rulesType: {
        dict_type_code: [{
          required: true,
          message: '请输入字典类型编码',
          trigger: 'blur'
        }],
        dict_type_name: [{
          required: true,
          message: '请输入字典类型名称',
          trigger: 'blur'
        },
        {
          min: 1,
          max: 50,
          message: '长度在 1 到 50 个字符',
          trigger: 'blur'
        }]
      }
    }
  },
  computed: {
    tableBtnColumnWidth() {
      let width = 120
      this.dictManager_edit_btn ? width += 45 : width += 0
      this.dictManager_detail_btn ? width += 45 : width += 0
      this.dictManager_del_btn ? width += 45 : width += 0
      return width
    }
  },
  watch: {
    dict_type_code(val) {
      this.$set(this.dictTypeForm, 'parent_code', val)
    }
  },
  created() {
    this.getAuth()
    this.getTreeList()
  },

  methods: {
    // 点击节点树
    nodeClick(item, node, self) {
      if (item && item.dict_type_code) {
        this.dict_type_code = item.dict_type_code
        this.dict_node_id = item.id
        this.listQuery.entity.dict_type_code = item.dict_type_code
        this.$nextTick(() => {
          this.getList()
        })
      }
    },
    // 获取字典树
    getTreeList() {
      page({ id: 0 }).then(res => {
        this.treeLoading = false
        this.dict_node_id = undefined
        this.dict_type_code = undefined
        this.treeList = res
      })
    },
    // 获取字典列表
    async getList() {
      this.listLoading = true
      try {
        const res = await getDictListByType(this.listQuery)
        this.tableData = res.data
        this.total = res.total
      } catch (e) {
        console.error(e)
      }
      this.listLoading = false
    },

    // 查看字典详情
    handleView(row) {
      getDictObj({ id: row.id }).then(res => {
        this.form = res
        this.dialogStatus = 'query'
        this.dialogFormVisible = true
      })
    },
    // 编辑字典
    handleUpdate(row) {
      getDictObj({ id: row.id }).then(res => {
        this.form = res
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
      })
    },
    // 删除字典
    handleDelete(row, index) {
      this.confirmCrt({ message: '确认删除此条数据？' }, async() => {
        try {
          await delDictObj({ id: row.id })
          this.$delete(this.tableData, index)
          this.$notify({ title: '成功', message: '删除成功', type: 'success', duration: 2000 })
        } catch (e) {
          console.error('删除失败' + e)
          this.$notify({ title: '失败', message: '删除失败', type: 'error', duration: 2000 })
        }
        if (this.tableData.length === 0 && this.listQuery.page > 1) {
          this.listQuery.page -= 1
        }
        this.getList()
      })
    },
    // 打开创建字典弹框
    handleCreate() {
      if (!this.dict_type_code) {
        this.$message.error('请选择字典类型')
        return false
      }
      this.dialogStatus = 'create'
      this.resetTemp()
      this.dialogFormVisible = true
    },

    // 打开创建字典类型弹框
    handleCreateType() {
      this.dialogTreeStatus = 'create'
      this.dialogTreeFormVisible = true
    },
    // 根据ID获取类型详情
    handleUpdateType() {
      if (!this.dict_node_id) {
        this.$message.error('请选择类型')
        return false
      }
      getDictType({ id: this.dict_node_id }).then(res => {
        this.dictTypeForm = res
        this.dialogTreeStatus = 'update'
        this.dialogTreeFormVisible = true
      })
    },
    // 查看类型详情
    handleViewType() {
      if (!this.dict_node_id) {
        this.$message.error('请选择类型')
        return false
      }
      getDictType({ id: this.dict_node_id }).then(res => {
        this.dictTypeForm = res
        this.dialogTreeStatus = 'query'
        this.dialogTreeFormVisible = true
      })
    },
    // 删除类型
    handleDeleteType() {
      if (!this.dict_node_id) {
        this.$message.error('请选择类型')
        return false
      }

      this.$confirm('确认删除此条数据', this.$t('system.users.warning'), {
        confirmButtonText: this.$t('system.users.confirm'),
        cancelButtonText: this.$t('system.users.cancel'),
        type: 'warning'
      })
        .then(() => {
          delDictType({ id: this.dict_node_id }).then(() => {
            this.$notify({
              title: '成功',
              message: `删除成功`,
              type: 'success'
            })
            this.getTreeList()
          })
        })
        .catch(err => {
          console.error(err)
        })
    },
    // 确认
    async confirmDict(formName) {
      const isEdit = this.dialogStatus === 'update'
      const set = this.$refs
      set[formName].validate(valid => {
        if (valid) {
          if (isEdit) {
            putDictObj(this.form).then(res => {
              this.$notify({
                title: '编辑成功',
                message: '编辑成功',
                type: 'success',
                duration: 1000
              })
              set[formName].resetFields()
              this.dialogFormVisible = false
              this.getList()
            })
          } else {
            this.form.dict_type_code = this.dict_type_code
            addDictObj(this.form).then(res => {
              this.$notify({
                title: '添加成功',
                message: '添加成功',
                type: 'success',
                duration: 1000
              })
              set[formName].resetFields()
              this.dialogFormVisible = false
              this.getList()
            })
          }
        }
      })
    },
    async confirmDictType(formName) {
      const isEdit = this.dialogTreeStatus === 'update'
      const set = this.$refs
      set[formName].validate(valid => {
        if (valid) {
          if (isEdit) {
            putDictType(this.dictTypeForm).then(res => {
              this.$notify({
                title: '编辑成功',
                message: '编辑成功',
                type: 'success',
                duration: 1000
              })
              set[formName].resetFields()
              this.dialogTreeFormVisible = false
              this.resetTypeTemp()
              this.resetListQuery()
              this.getTreeList()
            })
          } else {
            addDictType(this.dictTypeForm).then(res => {
              this.$notify({
                title: '添加成功',
                message: '添加成功',
                type: 'success',
                duration: 1000
              })
              set[formName].resetFields()
              this.dialogTreeFormVisible = false
              this.resetTypeTemp()
              this.resetListQuery()
              this.getTreeList()
            })
          }
        }
      })
    },
    cancelDictType(formName) {
      this.dialogTreeFormVisible = false
      if (this.$refs[formName]) {
        this.$refs[formName].resetFields()
      }
      this.resetTypeTemp()
    },

    getAuth() {
      this.dictTypeManager_add_btn = this.auth['dictTypeManager:add_btn']
      this.dictTypeManager_edit_btn = this.auth['dictTypeManager:edit_btn']
      this.dictTypeManager_del_btn = this.auth['dictTypeManager:del_btn']
      this.dictTypeManager_detail_btn = this.auth['dictTypeManager:detail_btn']
      this.dictManager_add_btn = this.auth['dictManager:add_btn']
      this.dictManager_edit_btn = this.auth['dictManager:edit_btn']
      this.dictManager_del_btn = this.auth['dictManager:del_btn']
      this.dictManager_detail_btn = this.auth['dictManager:detail_btn']
    },

    // 重置
    resetTemp() {
      this.form = {
        deleted: undefined,
        dict_code: undefined,
        dict_desc: undefined,
        dict_name: undefined,
        dict_type_code: undefined,
        modified_at: undefined,
        modified_id: undefined,
        parent_code: undefined,
        sort_no: undefined,
        state: undefined,
        dict_type_name: undefined
      }
    },
    resetTypeTemp() {
      this.dictTypeForm = {
        id: undefined,
        parent_code: 0,
        dict_type_code: undefined,
        dict_type_name: undefined
      }
    }
  }

}
</script>

<style lang="scss" scoped>
  .dictionariesTree {
    min-height: 200px;
    max-height: 570px;
    border: 1px #dfe6ec solid;
    overflow: auto;
    margin-top:10px;
    padding:10px;
    box-sizing: border-box;
  }

  .el-button-group {
    .blue-btn {
      margin-right: 1px !important;
    }
  }
</style>

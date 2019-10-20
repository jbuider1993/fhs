<template>
  <el-dialog title="行级权限分配" :visible.sync="dialogFormVisible" @close="cancel">
    <el-row :gutter="20">
      <el-col :span="10">
        <el-form ref="form" :model="rowLevelForm" :rules="rules" style="min-height: 240px;">
          <el-form-item label="表名" label-width="70px" prop="setTable">
            <el-select
              v-model="rowLevelForm.setTable"
              filterable
              placeholder="请输入表名"
            >
              <el-option
                v-for="item in dataBase"
                :key="item.setTable"
                :label="item.setTable"
                :value="item.setTable"
              >
                <span style="float: left">{{ item.setTable }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px;padding-left:5px;">{{ item.tableNote }}</span>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="字段" label-width="70px" prop="setColumn">
            <el-select
              v-model="rowLevelForm.setColumn"
              filterable
              placeholder="请输入表字段"
            >
              <el-option
                v-for="item in columnList"
                :key="item.COLUMN_NAME"
                :label="item.COLUMN_COMMENT"
                :value="item.COLUMN_NAME"
              >
                <span style="float: left">{{ item.COLUMN_NAME }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px;padding-left:5px;">{{ item.COLUMN_COMMENT }}</span>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item v-if="columnType" label="操作符" label-width="70px" prop="setOperator">
            <el-select
              v-model="rowLevelForm.setOperator"
              filterable
              placeholder="请输入关键词"
            >
              <el-option
                v-for="item in operatorList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item v-if="rowLevelForm.setOperator" prop="setValue" label="值" label-width="70px">
            <el-input v-model="rowLevelForm.setValue" style="width:200px;" />
          </el-form-item>
          <el-form-item label-width="60px" size="small" style="marin-top:20px;">
            <el-button v-if="rowAuthManager_save_btn" type="primary" class="blue-btn" @click="saveAuth('form')">保存</el-button>
            <el-button type="primary" class="red-btn" @click="resetForm('form')">清空</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="14">
        <el-table v-loading.body="listLoading" :data="tableData" size="mini" border fit highlight-current-row>
          <el-table-column align="center" label="序号" type="index" :index="indexMethod" width="65" />
          <el-table-column align="center" label="表名"> <template slot-scope="scope">
            <span>{{ scope.row.setTable }}</span>
          </template>
          </el-table-column>
          <el-table-column align="center" label="列名"> <template slot-scope="scope">
            <span>{{ scope.row.setColumn }}</span>
          </template>
          </el-table-column>
          <el-table-column align="center" label="条件" width="200"> <template slot-scope="scope">
            <span>{{ scope.row.setOperator }}</span>
            <span>{{ scope.row.setValue }}</span>
          </template>
          </el-table-column>
          <el-table-column fixed="right" align="center" label="操作" width="90"> <template slot-scope="scope">
            <el-button v-if="rowAuthManager_del_btn" class="red-btn" size="mini" type="primary" @click="handleDelete(scope.row,scope.$index)">移除
            </el-button>
          </template>
          </el-table-column>
        </el-table>
        <div v-show="!listLoading" class="pagination-container">
          <el-pagination
            :current-page.sync="listQuery.page"
            :page-sizes="[5,10,15, 20]"
            :page-size="listQuery.limit"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
          />
        </div>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { getCloumsByDataTable, setDataAuth, getListTableByCode, page, delDataAuth } from '@/api/system/rowlevel'
import { dbFieldConversion, getDbOperator } from '@/utils/dbUtils'
import baseMixins from '@/mixins/baseMixins'
const _tempOperatorList = getDbOperator()
export default {
  name: 'DataBase',
  mixins: [baseMixins],
  props: {
    orgRoleCode: {
      type: Number,
      default: 0
    },
    menuCode: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      dataBase: [],
      listQuery: {
        page: 1,
        limit: 5
      },
      rowAuthManager_save_btn: false,
      rowAuthManager_del_btn: false,
      loading: false,
      columnList: [],
      tableList: [],
      rowLevelForm: {
        setTable: undefined,
        setOperator: undefined,
        setValue: undefined,
        setColumn: undefined
      },
      rules: {
        setTable: [
          {
            required: true,
            trigger: 'blur',
            message: '请选择表名'
          }
        ],
        setColumn: [
          {
            required: true,
            trigger: 'blur',
            message: '请选择字段名'
          }
        ],
        setOperator: [
          {
            required: true,
            trigger: 'blur',
            message: '请选择操作符'
          }
        ],
        setValue: [
          {
            required: true,
            trigger: 'blur',
            message: '请输入条件只'
          }
        ]
      }
    }
  },
  computed: {
    columnType() {
      let type = ''
      if (this.columnList && this.rowLevelForm.setColumn) {
        for (const item of this.columnList) {
          if (item.COLUMN_NAME === this.rowLevelForm.setColumn) {
            type = item.DATA_TYPE
            break
          }
        }
      }
      return dbFieldConversion(type)
    },
    operatorList() {
      const arr = []
      for (const item of _tempOperatorList) {
        if (item.type.indexOf(this.columnType) !== -1) {
          arr.push(item)
        }
      }
      return arr
    }
  },
  watch: {
    'rowLevelForm.setTable': {
      handler(newValue, oldValue) {
        this.columnList = []
        this.rowLevelForm.setColumn = undefined
        this.rowLevelForm.setOperator = undefined
        this.rowLevelForm.setValue = undefined
        if (newValue && newValue !== oldValue) {
          this.getColums()
        }
      },
      deep: true
    },
    'rowLevelForm.operator': {
      handler(newValue, oldValue) {
        this.rowLevelForm.setValue = undefined
      },
      deep: true
    },
    orgRoleCode: {
      immediate: true,
      handler(newVal) {
        this.rowLevelForm.orgRoleCode = newVal
      }
    },
    menuCode: {
      immediate: true,
      handler(newVal) {
        this.rowLevelForm.menuCode = newVal
      }
    }
  },
  async created() {
    this.rowAuthManager_save_btn = this.auth['rowAuthManager:save_btn']
    this.rowAuthManager_del_btn = this.auth['rowAuthManager:del_btn']
    this.listQuery.entity.menuCode = this.menuCode
    this.listQuery.entity.orgRoleCode = this.orgRoleCode
    await this.getDataBaseByCode()
    await this.getList()
    this.listLoading = false
  },
  methods: {
    /**
     * 根据code获取表
     * @returns {Promise<void>}
     */
    async getDataBaseByCode() {
      try {
        const res = await getListTableByCode({ menuCode: this.menuCode })
        this.dataBase = res
      } catch (e) {
        console.error(e)
        this.$message.error(e.message)
      }
    },

    // 获取字段
    async getColums() {
      try {
        const res = await getCloumsByDataTable({ tableName: this.rowLevelForm.setTable })
        this.columnList = res
      } catch (e) {
        console.error(e.message)
      }
    },
    // 保存
    saveAuth(formName) {
      const set = this.$refs
      set[formName].validate(async valid => {
        if (valid) {
          try {
            this.rowLevelForm.columnType = this.getColumnTypeByName(this.rowLevelForm.setColumn, this.columnList)
            await setDataAuth(this.rowLevelForm)
            if (this.tableData.length === this.listQuery.limit) {
              this.handleCurrentChange(this.listQuery.page + 1)
            }
            this.$notify({ title: '成功', message: '添加成功', type: 'success', duration: 2000 })
          } catch (e) {
            this.$notify({ title: '失败', message: e.message, type: 'error', duration: 2000 })
          }
          set[formName].resetFields()
          this.getList()
        }
      })
    },

    // 重置表单
    resetForm(formName) {
      const set = this.$refs
      set[formName].resetFields()
    },

    // 获取列表
    async getList() {
      try {
        const res = await page(this.listQuery)
        this.tableData = res.data
        this.total = res.total
      } catch (e) {
        console.error('获取行级权限列表失败' + e)
      }
    },
    // 移除
    async handleDelete(row, index) {
      try {
        await delDataAuth({ id: row.id })
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
    },
    getColumnTypeByName(name, arr) {
      let type = ''
      for (const item of arr) {
        if (name === item.COLUMN_NAME) {
          type = item.DATA_TYPE
          return type
        }
      }
      return type
    },
    cancel() {
      this.dialogFormVisible = false
      this.$emit('close')
    }
  }
}
</script>

<style lang="scss" scoped>

</style>

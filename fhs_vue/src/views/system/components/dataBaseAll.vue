<template>
  <el-dialog title="关联列表" :visible.sync="dialogFormVisible" :before-close="handleDataSourceClose">
    <div class="container">
      <el-table :data="tableData" size="small" border fit highlight-current-row style="width: 100%;">
        <el-table-column align="center" label="序号" type="index" width="65" />
        <el-table-column width="200px" align="center" label="数据库名称"><template slot-scope="scope">
          <span>
            {{ scope.row.setDatabase }}</span>
        </template>
        </el-table-column>
        <el-table-column width="200px" align="center" label="表名"><template slot-scope="scope">
          <span>
            {{ scope.row.setTable }}</span>
        </template>
        </el-table-column>
        <el-table-column min-width="260px" align="center" label="备注"><template slot-scope="scope">
          <span>
            {{ scope.row.tableNote }}</span>
        </template>
        </el-table-column>
        <el-table-column fixed="right" align="center" width="120px" label="操作">
          <template slot-scope="scope">
            <el-button size="mini" type="primary" class="red-btn" @click="handleDelete(scope.row.id)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="padding-top:40px;padding-bottom:30px; line-height: 24px;font-size: 18px;color: #303133;">设置库表</div>
      <el-form ref="form" :model="form" size="small" :rules="rules" label-width="65px">
        <el-form-item label="库名" prop="setDatabase">
          <el-select
            v-model="form.setDatabase"
            filterable
            placeholder="请选择数据库"
            style="width:100%;"
          >
            <el-option
              v-for="(item,index) in dataBaseList"
              :key="index"
              :label="item.SCHEMA_NAME"
              :value="item.SCHEMA_NAME"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="表名" prop="tables">
          <el-select
            v-model="form.tables"
            filterable
            multiple
            placeholder="请选择数据库表名"
            style="width:100%;"
          >
            <el-option
              v-for="(item,index) in dataTableList"
              :key="index"
              :label="item.TABLE_NAME"
              :value="item.TABLE_NAME"
            >
              <span>{{ item.TABLE_NAME }}</span>
              <span style="color: #8492a6; font-size: 13px;padding-left:15px;">{{ item.TABLE_COMMENT || '无备注信息' }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="blue-btn" size="small" @click="resetForm('form')">重置</el-button>
          <el-button type="primary" class="blue-btn" size="small" @click="save('form')">添加</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" class="blue-btn" @click="handleDataSourceClose">关闭</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { getDataBaseAll, getTableDataByName } from '@/api/base'
import { getListByTable, setListByTable, delListByTables } from '@/api/system/menu'

import baseMixins from '@/mixins/baseMixins'
export default {
  name: 'DataBaseAll',
  mixins: [baseMixins],
  data() {
    return {
      dataBaseList: null,
      dataTableList: null,
      form: {
        menuCode: undefined,
        setDatabase: undefined,
        tables: undefined
      },
      rules: {
        setDatabase: [
          {
            required: true,
            trigger: 'blur',
            message: '请选择数据库'
          }
        ],
        tables: [
          {
            required: true,
            trigger: 'blur',
            message: '请选择数据库表名'
          }
        ]
      }
    }
  },
  computed: {
    currentDataName() {
      return this.form.setDatabase
    }
  },
  watch: {
    'currentDataName': {
      handler(newValue, oldValue) {
        if (newValue && newValue !== oldValue) {
          this.getDataTable()
        }
      },
      deep: true
    }
  },
  mounted() {
    this.getDataBase()
  },
  methods: {
    /**
     * @description 获取全部数据库
     * @returns {Promise<void>}
     */
    async getDataBase() {
      try {
        const res = await getDataBaseAll()
        this.dataBaseList = res
      } catch (e) {
        console.error(e.message)
      }
    },
    /**
     * @description 获取根据库名全部表名
     * @returns {Promise<void>}
     */
    async getDataTable() {
      try {
        const res = await getTableDataByName({ databaseName: this.currentDataName })
        this.dataTableList = res
      } catch (e) {
        console.error(e.message)
      }
    },

    /**
     * 保存
     * @returns {Promise<void>}
     */
    save(formName) {
      const set = this.$refs
      set[formName].validate(async valid => {
        if (valid) {
          try {
            const form = Object.assign({}, this.form)
            form.tables = form.tables.join(',')
            await setListByTable(form)
            this.$notify({ title: '成功', message: '添加成功', type: 'success', duration: 2000 })
          } catch (e) {
            this.$notify({ title: '失败', message: e.message, type: 'error', duration: 2000 })
            console.error(e.message)
          }
          this.resetForm('form')
          this.getListByTable()
        }
      })
    },

    /**
     * 获取关联的数据库及表
     * @returns {Promise<void>}
     */
    async getListByTable() {
      try {
        const res = await getListByTable({ menuCode: this.form.menuCode })
        this.tableData = res
      } catch (e) {
        console.error(e.message)
      }
    },
    async handleDelete(id) {
      try {
        await delListByTables({ id: id })
        this.$notify({ title: '成功', message: '添加成功', type: 'success', duration: 2000 })
      } catch (e) {
        this.$notify({ title: '失败', message: e.message, type: 'error', duration: 2000 })
      }
      this.getListByTable()
    },
    resetForm(formName) {
      const set = this.$refs
      set[formName].resetFields()
    },

    handleDataSourceClose() {
      this.dialogFormVisible = false
      this.$emit('close')
    }
  }

}
</script>

<style scoped>

</style>

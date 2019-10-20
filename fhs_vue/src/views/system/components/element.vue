<template>
  <div class="app-container calendar-list-container">
    <div class="filter-container">
      <!--<el-input v-model="listQuery.name" style="width: 200px;" class="filter-item" placeholder="资源名称" @keyup.enter.native="handleFilter" />-->
      <!--<el-button class="filter-item blue-btn" type="primary" icon="search" @click="handleFilter">搜索</el-button>-->
      <el-button v-if="resourceManager_add_btn" class="filter-item blue-btn" type="primary" size="mini" @click="handleCreate">添加</el-button>
    </div>
    <el-table v-loading.body="listLoading" :data="tableData" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="序号" type="index" width="65" />
      <el-table-column width="240px" align="center" label="资源标识"><template scope="scope">
        <span>
          {{ scope.row.webTag }}</span>
      </template>
      </el-table-column>
      <el-table-column width="160px" align="center" label="资源名称"><template scope="scope">
        <span>
          {{ scope.row.title }}</span>
      </template>
      </el-table-column>
      <el-table-column width="160px" align="center" label="资源类型"><template scope="scope">
        <el-tag>
          {{ scope.row.menuType | getAuthType }}
        </el-tag>
      </template>
      </el-table-column>

      <el-table-column align="center" label="资源地址"><template scope="scope">
        <span>
          {{ scope.row.url }}</span>
      </template>
      </el-table-column>
      <el-table-column  fixed="right" align="center" label="操作" :width="tableBtnColumnWidth"><template scope="scope">
        <el-button v-if="scope.row.menuType === 2" size="mini" type="primary" class="blue-btn" @click="handleDataSource(scope.row)">
          设置库表
        </el-button>
        <el-button v-if="resourceManager_edit_btn" size="mini" type="primary" class="blue-btn" @click="handleUpdate(scope.row)">
          编辑
        </el-button>
        <el-button v-if="resourceManager_del_btn" size="mini" type="primary" class="blue-btn" @click="handleDelete(scope.row)">删除
        </el-button>
      </template>
      </el-table-column>
    </el-table>
    <div v-show="!listLoading" class="pagination-container">
      <el-pagination :current-page.sync="listQuery.page" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange" @current-change="handleCurrentChange" />
    </div>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :before-close="handleClose">
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="资源标识" prop="webTag">
          <el-input v-model="form.webTag" placeholder="请输入资源名称" />
        </el-form-item>
        <el-form-item label="资源类型" prop="menuType">
          <el-select v-model="form.menuType" class="filter-item" placeholder="请选择资源类型" style="width:100%;">
            <el-option v-for="item in typeOptions" :key="item.code" :label="item.label" :value="item.code" />
          </el-select>
        </el-form-item>
        <el-form-item label="资源名称" prop="title">
          <el-input v-model="form.title" placeholder="请输入资源名称" />
        </el-form-item>
        <el-form-item label="资源地址">
          <el-input v-model="form.url" placeholder="请输入资源地址" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel('form')">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="create('form')">确 定</el-button>
        <el-button v-else type="primary" @click="update('form')">确 定</el-button>
      </div>
    </el-dialog>
    <data-base-all v-if="dialogFormDataSourceVisible" ref="database" @close="dialogFormDataSourceVisible=false" />

  </div>
</template>

<script>
import {
  page,
  addObj,
  getObj,
  delObj,
  putObj
} from '@/api/system/resource'
import baseMixins from '@/mixins/baseMixins'
import { checkChineseStr, inputValidator } from '@/utils/formValidation'
import dataBaseAll from './dataBaseAll'

export default {
  name: 'Element',
  components: { dataBaseAll },
  mixins: [baseMixins],
  props: {
    pid: {
      type: Number,
      default: undefined
    }
  },
  data() {
    return {
      isRowAuth: false,
      dialogFormDataSourceVisible: false,
      typeOptions: [{ label: 'uri', code: 2 }, { label: 'button', code: 3 }],
      form: {
        title: undefined,
        menuType: undefined,
        parentId: undefined,
        webTag: undefined,
        url: undefined
      },
      menuId: undefined,
      rules: {
        webTag: [
          {
            required: true,
            trigger: 'blur',
            message: '请输入资源标识'
          },
          {
            required: true,
            trigger: 'blur',
            validator: checkChineseStr
          },
          inputValidator('资源标识')
        ],
        menuType: [
          {
            required: true,
            trigger: 'blur',
            message: '请选择资源类型'
          }
        ],
        title: [
          {
            required: true,
            trigger: 'blur',
            message: '请输入资源名称'
          }
        ]
        // url: [
        //   {
        //     required: true,
        //     trigger: 'blur',
        //     validator: checkChineseStr
        //   },
        //   inputValidator('资源地址')
        // ]
      },
      resourceManager_add_btn: false,
      resourceManager_edit_btn: false,
      resourceManager_del_btn: false
    }
  },
  computed: {
    tableBtnColumnWidth() {
      let width = 120
      this.resourceManager_edit_btn ? width += 45 : width += 0
      this.resourceManager_del_btn ? width += 45 : width += 0
      this.isRowAuth ? width += 60 : width += 0
      return width
    }
  },
  created() {
    // this.resourceManager_add_btn = this.auth['resourceManager:add_btn']
    // this.resourceManager_edit_btn = this.auth['resourceManager:edit_btn']
    // this.resourceManager_del_btn = this.auth['resourceManager:del_btn']
    this.resourceManager_add_btn = true
    this.resourceManager_edit_btn = true
    this.resourceManager_del_btn = true
    this.listLoading = false
  },
  methods: {
    handleClose() {
      this.closeDig()
      this.dialogFormVisible = false
    },
    closeDig() {
      const set = this.$refs
      set.form.resetFields()
    },
    getList() {
      this.listLoading = true
      this.listQuery.entity.menuCode = this.menuId
      page(this.listQuery).then(res => {
        this.tableData = res.data
        this.total = res.total
        this.listLoading = false
        let flag = false
        for (const item of this.tableData) {
          if (item.menuType === 2) {
            flag = true
            break
          }
        }
        this.isRowAuth = flag
      })
    },


    handleFilter() {
      this.getList()
    },
    handleSizeChange(val) {
      this.listQuery.limit = val
      this.getList()
    },
    handleCurrentChange(val) {
      this.listQuery.page = val
      this.getList()
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
    },
    handleUpdate(row) {
      getObj({ menuCode: row.menuCode }).then(res => {
        this.form = res
        this.dialogFormVisible = true
        this.dialogStatus = 'update'
      })
    },
    handleDelete(row) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delObj({ id: row.id }).then(() => {
          this.$notify({
            title: '成功',
            message: '删除成功',
            type: 'success',
            duration: 2000
          })
          const index = this.tableData.indexOf(row)
          this.tableData.splice(index, 1)
        })
      })
    },
    create(formName) {
      const set = this.$refs
      this.form.parentId = this.menuId
      set[formName].validate(valid => {
        if (valid) {
          addObj(this.form).then(() => {
            this.dialogFormVisible = false
            this.getList()
            this.$notify({
              title: '成功',
              message: '创建成功',
              type: 'success',
              duration: 2000
            })
          })
        } else {
          return false
        }
      })
    },
    cancel(formName) {
      this.dialogFormVisible = false
      const set = this.$refs
      set[formName].resetFields()
    },
    update(formName) {
      const set = this.$refs
      this.form.parentId = this.menuId
      set[formName].validate(valid => {
        if (valid) {
          putObj(this.form).then(() => {
            this.$notify({
              title: '成功',
              message: '编辑成功',
              type: 'success',
              duration: 2000
            })
            set[formName].resetFields()
            this.dialogFormVisible = false
            this.getList()
          })
          this.dialogFormVisible = false
        } else {
          return false
        }
      })
    },
    handleDataSource(row) {
      this.dialogFormDataSourceVisible = true
      this.$nextTick(() => {
        this.$refs['database'].dialogFormVisible = true
        this.$refs['database'].form.menuCode = row.menuCode
        this.$refs['database'].getListByTable()
      })
    },
    resetTemp() {
      this.form = {
        title: undefined,
        menuType: undefined,
        parentId: undefined,
        url: undefined
      }
    }
  }
}
</script>

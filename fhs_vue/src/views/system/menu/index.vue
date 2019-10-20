<template>
  <div v-loading="listLoading" class="app-container calendar-list-container">
    <div class="filter-container">
      <el-button-group>
        <el-button v-if="menuManager_add_btn" size="small" type="primary" icon="plus" class="blue-btn" @click="handlerAdd">添加</el-button>
        <el-button v-if="menuManager_edit_btn" size="small" type="primary" icon="edit" class="blue-btn" @click="handlerEdit">编辑</el-button>
        <el-button v-if="menuManager_del_btn" size="small" type="primary" icon="delete" class="blue-btn" @click="handleDelete">删除</el-button>
      </el-button-group>
    </div>
    <el-row :gutter="20">
      <el-col :span="8" style="margin-top:15px;">
        <el-input
          v-model="filterText"
          placeholder="输入关键字进行过滤"
        />
        <el-tree
          ref="menuTree"
          class="filter-tree"
          :data="treeData"
          node-key="id"
          highlight-current
          :accordion="true"
          :props="defaultProps"
          :default-expanded-keys="defaultExpandedKeys"
          :filter-node-method="filterNode"
          @node-click="getNodeData"
        />
      </el-col>
      <el-col :span="16" style="margin-top:15px;">
        <el-card class="box-card">
          <el-form ref="form" label-position="right" :rules="rules" label-width="80px" :model="form">
            <el-form-item label="菜单类型" prop="menuType">
              <el-radio-group v-model="form.menuType" :disabled="formEdit">
                <el-radio v-for="item of typeOptions" :key="item.value" :label="item.value">{{ item.label }}</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item v-if="form.menuType" label="链接类型" prop="linkType">
              <el-radio-group v-model="form.linkType" :disabled="formEdit">
                <el-radio v-for="item of linkTypeOpt" :key="item.value" :label="item.value">{{ item.label }}</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item v-if="!form.linkType && form.menuType ==1" label="是否缓存">
              <el-radio-group v-model="form.nocache" :disabled="formEdit">
                <el-radio v-for="item of noCacheOptions" :key="item.value" :label="item.value">{{ item.label }}</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item v-if="form.linkType === 0" label="路径编码" prop="path">
              <el-input v-model="form.path" :disabled="formEdit" placeholder="请输入路径编码" />
            </el-form-item>
            <el-form-item v-if="form.linkType === 1" label="请求地址" prop="path">
              <el-input v-model="form.path" :disabled="formEdit" placeholder="请输入请求地址" />
            </el-form-item>
            <el-form-item label="菜单名称" prop="title">
              <el-input v-model="form.title" :disabled="formEdit" placeholder="请输入标题" />
            </el-form-item>
            <el-form-item label="父级节点" prop="parentId">
              <el-input v-model="form.parentId" :disabled="formEdit" placeholder="请输入父级节点" />
            </el-form-item>
            <el-form-item v-if="!form.linkType" label="前端组件" prop="component">
              <el-input v-model="form.component" :disabled="formEdit" placeholder="请输入资源路径" />
            </el-form-item>
            <el-form-item v-if="!form.linkType && form.menuType ==1" label="组件名称" prop="componentName">
              <el-input v-model="form.componentName" :disabled="formEdit" placeholder="请输入资源路径" />
            </el-form-item>
            <el-form-item label="菜单图标" prop="icon">
              <el-input v-model="form.icon" :disabled="formEdit" placeholder="请选择图标" readonly @click.native="projectOrgFun($event)" />
              <div v-show="iconBox" ref="iconBox" class="iconBox">
                <div class="icon-warp">
                  <div v-for="item in icons" :key="item.id" class="icon-item" @click="checkIcon(item.id)">
                    <svg-icon :icon-class="item.id" class-name="svg-icon-menu" />
                  </div>
                </div>
              </div>
            </el-form-item>
            <el-form-item v-if="form.menuType === 0" label="所属分类" prop="redirect">
              <el-select v-model="form.ascription" class="filter-item" :disabled="formEdit" placeholder="请选择所属分类" style="width:100%;">
                <el-option v-for="(item,index) in classType" :key="index" :label="item.label" :value="item.value" />
              </el-select>
            </el-form-item>
            <el-form-item label="排序" prop="menuSort">
              <el-input v-model.number="form.menuSort" onkeypress="return( /[\d]/.test(String.fromCharCode(event.keyCode) ) )" :disabled="formEdit" placeholder="请输入序号" />
            </el-form-item>
            <el-form-item v-if="form.menuType === 0" label="重定向" prop="redirect">
              <el-input v-model="form.redirect" :disabled="formEdit" placeholder="请输入重新定页面" />
            </el-form-item>
            <el-form-item label="状态">
              <el-select v-model="form.menuState" class="filter-item" :disabled="formEdit" placeholder="请选择菜单状态" style="width:100%;">
                <el-option v-for="(item,index) in menuShowOptions" :key="index" :label="item.label" :value="item.value" />
              </el-select>
            </el-form-item>
            <el-form-item label="描述">
              <el-input v-model="form.remark" :disabled="formEdit" placeholder="请输入描述" />
            </el-form-item>
            <el-form-item v-if="formStatus == 'update'">
              <el-button type="primary" class="blue-btn" size="mini" @click="update('form')">更新</el-button>
              <el-button type="primary" class="red-btn" size="mini" @click="onCancel">取消</el-button>
            </el-form-item>
            <el-form-item v-if="formStatus == 'create'">
              <el-button type="primary" class="blue-btn" size="mini" @click="create('form')">保存</el-button>
              <el-button type="primary" class="red-btn" size="mini" @click="onCancel">取消</el-button>
            </el-form-item>
          </el-form>
        </el-card>
        <el-card v-if="!form.linkType" class="box-card">
          <span>按钮或资源</span>
          <menu-element ref="menuElement" :pid="currentMenuCode" />
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { fetchTree, addObj, getObj, putObj, delObj } from '@/api/system/menu'
import { checkUrl } from '@/utils/formValidation'
import baseMixins from '@/mixins/baseMixins'
import { getLocal } from '@/utils'
export default {
  name: 'Menu',
  components: {
    'menu-element': () => import('../components/element')
  },
  mixins: [baseMixins],
  data() {
    return {
      iconBox: false,
      filterText: '',
      list: null,
      total: null,
      formEdit: true,
      formAdd: true,
      formStatus: '',
      showElement: false,
      defaultExpandedKeys: [],
      classType: [
        {
          label: '多式联运综合服务平台 ',
          value: -1
        }
      ],
      typeOptions: [{
        label: 'dirt',
        value: 0
      }, {
        label: 'menu',
        value: 1
      }],
      linkTypeOpt: [
        {
          label: '内部资源',
          value: 0
        },
        {
          label: '外部资源',
          value: 1
        }
      ],
      noCacheOptions: [{
        label: '缓存',
        value: 0
      }, {
        label: '不缓存',
        value: 1
      }],
      menuShowOptions: [{
        label: '显示',
        value: 0
      }, {
        label: '隐藏',
        value: 1
      }],
      defaultProps: {
        children: 'children',
        label: 'title'
      },
      listQuery: {
        name: undefined
      },
      treeData: [],
      form: {
        id: undefined,
        ascription: -1,
        alwaysShow: 0,
        linkType: 0,
        nocache:1,
        component: undefined,
        componentName: undefined,
        icon: undefined,
        label: undefined,
        menuCode: undefined,
        redirect: undefined,
        name: undefined,
        path: undefined,
        title: undefined,
        parentId: undefined,
        description: undefined,
        enabled: undefined,
        menuType: 0,
        menuSort:0,
        menuState: undefined
      },
      currentMenuCode: 0,
      currentId: 0,
      ascription: -1,
      menuManager_add_btn: false,
      menuManager_edit_btn: false,
      menuManager_del_btn: false,
      rules: {
        menuType: [
          {
            required: true,
            message: '请选择菜单类型',
            trigger: 'blur'
          }
        ],
        linkType: [
          {
            required: true,
            message: '请选择链接类型',
            trigger: 'blur'
          }
        ],
        path: [
          { required: true,
            trigger: 'blur',
            message: '请输入路径编码'
          }
        ],
        pathUrl: [
          { required: true,
            trigger: 'blur',
            validator: checkUrl
          }
        ],
        title: [
          {
            required: true,
            message: '请输入菜单名称',
            trigger: 'blur'
          }
        ],
        parentId: [
          {
            required: true,
            message: '请输入父级节点',
            trigger: 'blur'
          }
        ],
        component: [
          {
            required: true,
            message: '请输入资源路径',
            trigger: 'blur'
          }
        ],
        componentName: [
          {
            required: true,
            message: '请输入组件名称',
            trigger: 'blur'
          }
        ],
        menuSort: [
          {
            min: 0, max: 9999,
            type: 'number',
            message: '必须为数字且值必须在0~9999之间', trigger: 'blur'
          }
        ]
      }
    }
  },
  computed: {
    icons() {
      return JSON.parse(getLocal('icons')) || []
    },
    codeName() {
      if (this.linkType) {
        return '访问路径'
      }
      return '路径编码'
    },
    codePlaceholder() {
      if (this.linkType) {
        return '请输入访问路径'
      }
      return '请输入路径编码'
    }
  },
  watch: {
    filterText(val) {
      this.$refs.menuTree.filter(val)
    }
  },
  created() {
    this.getList()
    this.getAuth()
  },
  methods: {
    async getList() {
      try {
        const res = await fetchTree({ parentId: 0 })
        this.treeData = res
      } catch (e) {
        this.$message.error(e)
      }
      this.listLoading = false
    },
    filterNode(value, data) {
      if (!value) return true
      return data.title.indexOf(value) !== -1
    },
    getNodeData(data) {
      if (!this.formEdit) {
        this.formStatus = 'update'
      }
      getObj({ id: data.id }).then(res => {
        this.form = res
      })
      this.currentMenuCode = data.menuCode
      this.currentId = data.id
      this.ascription = data.ascription
      // this.showElement = true
      this.$refs.menuElement.menuId = data.menuCode
      this.$refs.menuElement.form.parentId = data.menuCode
      this.$refs.menuElement.getList()
    },
    handlerEdit() {
      if (this.form.id) {
        this.formEdit = false
        this.formStatus = 'update'
      }
    },
    handlerAdd() {
      if (this.form.menuType === 1) {
        this.$message.warning('此菜单下禁止添加子菜单')
        return false
      }
      this.resetForm()
      this.formEdit = false
      this.formStatus = 'create'
    },
    handleDelete() {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delObj({ id: this.currentId }).then(() => {
          this.getList()
          this.resetForm()
          this.onCancel()
          this.$notify({
            title: '成功',
            message: '删除成功',
            type: 'success',
            duration: 2000
          })
        })
      })
    },
    update(formName) {
      const set = this.$refs
      set[formName].validate(async valid => {
        if (valid) {
          if (this.form.menuType === 0) {
            if (!this.form.path.startsWith('/')) {
              this.$message.error('目录的资源路径前缀必须为"/"')
              return false
            }
          }
          try {
            await putObj(this.form.id, this.form)
            this.$notify({ title: '成功', message: '更新成功', type: 'success', duration: 2000 })
          } catch (e) {
            this.$notify({ title: '失败', message: e.message, type: 'error', duration: 2000 })
          }
          await this.getList()
        }
      })
    },
    create(formName) {
      const set = this.$refs
      set[formName].validate(async valid => {
        if (valid) {
          if (this.form.menuType === 0) {
            if (!this.form.path.startsWith('/')) {
              this.$message.error('目录的资源路径前缀必须为"/"')
              return false
            }
          }
          try {
            await addObj(this.form)
            this.$notify({ title: '成功', message: '创建成功', type: 'success', duration: 2000 })
          } catch (e) {
            this.$notify({ title: '失败', message: e.message, type: 'error', duration: 2000 })
          }
          await this.getList()
        }
      })
    },
    onCancel() {
      this.formEdit = true
      this.formStatus = ''
    },
    checkIcon(icon) {
      this.form.icon = icon
    },
    projectOrgFun(e) {
      if (this.formEdit) {
        return false
      }
      if (this.iconBox) {
        this.iconBox = false
      } else {
        this.iconBox = true
      }
      document.onclick = function() {
        that.iconBox = false
      }
      e.stopPropagation()// 阻止冒泡
      var that = this
      that.$refs.iconBox.onmouseleave = function() {
        that.iconBox = false
      }
    },
    getAuth() {
      // this.menuManager_add_btn = this.auth['menuManager:add_btn']
      // this.menuManager_edit_btn = this.auth['menuManager:edit_btn']
      // this.menuManager_del_btn = this.auth['menuManager:del_btn']
      // this.menuManager_detail_btn = this.auth['menuManager:detail_btn']
      this.menuManager_add_btn = true
      this.menuManager_edit_btn = true
      this.menuManager_del_btn = true
    },
    resetForm() {
      this.form = {
        id: undefined,
        alwaysShow: 0,
        linkType: 0,
        ascription: this.ascription,
        component: undefined,
        componentName: undefined,
        icon: undefined,
        label: undefined,
        menuCode: undefined,
        name: undefined,
        path: undefined,
        title: undefined,
        parentId: this.currentMenuCode,
        description: undefined,
        enabled: undefined,
        redirect: undefined,
        menuState: undefined,
        menuType: 0,
        menuSort:0,
        nocache:1,
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

  .iconBox{
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

  .icon-warp {
    display: flex;
    justify-content: flex-start;
    align-content: flex-start;
    flex-wrap: wrap;
    .icon-item {
      display: flex;
      justify-content: center;
      align-content: center;
      align-items: center;
      flex-flow: column;
      background: #f5f5f5;
      padding:20px;
      width:10%;
      height:100px;
      cursor: pointer;
      border-right: 5px solid #ffffff;
      border-bottom: 5px solid #ffffff;
      >span{
        margin-top:15px;
      }
    }
    .icon-item-b {
      margin-right:5px;
      margin-bottom:5px;
    }
  }
  .svg-icon-menu {
    width:20px;
    height:20px;
  }
</style>

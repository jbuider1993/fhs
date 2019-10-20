<template>
  <div class="orgTree">
    <el-card class="box-card">
      <template v-if="isOperation" v-slot:header>
        <el-button-group>
          <el-button v-if="add" size="small" type="primary" icon="plus" class="blue-btn" @click="handlerAdd">添加</el-button>
          <el-button v-if="edit" size="small" type="primary" icon="edit" :disabled="!orgCode" class="blue-btn" @click="handleUpdate">编辑</el-button>
          <el-button v-if="del" size="small" type="primary" icon="delete" :disabled="!orgCode" class="blue-btn" @click="handleDelete">删除</el-button>
        </el-button-group>
      </template>
      <el-input
        v-model="filterText"
        placeholder="输入关键字进行过滤"
      />
      <el-tree
        ref="orgTree"
        class="filter-tree"
        :data="treeData"
        node-key="id"
        highlight-current
        :accordion="true"
        :props="defaultProps"
        default-expand-all
        :default-expanded-keys="defaultExpandedKeys"
        :filter-node-method="filterNode"
        @node-click="getNodeData"
      />
    </el-card>
  </div>
</template>

<script>
import { fetchTree } from '@/api/system/organization'
export default {
  name: 'OrgTree',
  props: {
    isOperation: {
      type: Boolean,
      default: false
    },
    add: {
      type: Boolean,
      default: false
    },
    edit: {
      type: Boolean,
      default: false
    },
    del: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      filterText: '',
      treeData: [],
      defaultProps: {
        children: 'children',
        label: 'orgName'
      },
      id: undefined,
      orgCode: undefined,
      defaultExpandedKeys: []
    }
  },
  watch: {
    add: {
      immediate: true,
      handler(newVal) {
        this.add = newVal
      }
    },
    edit: {
      immediate: true,
      handler(newVal) {
        this.edit = newVal
      }
    },
    del: {
      immediate: true,
      handler(newVal) {
        this.del = newVal
      }
    },
    filterText(val) {
      this.$refs.orgTree.filter(val)
    }
  },
  created() {
    this.getTreeList()
  },
  methods: {
    async getTreeList() {
      try {
        const res = await fetchTree({ parentId: 0 })
        this.treeData = res
        this.$emit('getTree', res)
      } catch (e) {
        console.error('获取组织树失败' + e)
      }
    },
    getNodeData(data) {
      this.orgCode = data.orgCode
      this.id = data.id
      this.$emit('getNodeData', data)
    },
    filterNode(value, data) {
      if (!value) return true
      return data.orgName.indexOf(value) !== -1
    },
    handlerAdd() {
      this.$emit('create')
    },
    handleUpdate() {
      this.$emit('update')
    },
    handleDelete() {
      this.$emit('delete', this.id)
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
</style>

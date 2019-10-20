<template>
  <div class="checkOrg sun">
    <el-input
      v-model="orgName"
      :disabled="dialogStatus === 'query'"
      placeholder="请选择部门"
      readonly
      @click.native="projectOrgFun($event)"
    />
    <el-tree
      v-show="isShow"
      ref="tree"
      class="orgTree"
      :data="treeData"
      node-key="id"
      highlight-current
      :accordion="true"
      :props="defaultProps"
      :default-expanded-keys="defaultExpandedKeys"
      @node-click="getNodeData"
    />
  </div>
</template>

<script>
export default {
  name: 'CheckOrg',
  props: {
    tree: {
      type: Array
    },
    status: {
      type: String,
      default: ''
    },
    name: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      treeData: [],
      isShow: false,
      dialogStatus: '',
      orgName: '',
      defaultProps: {
        children: 'children',
        label: 'orgName'
      },
      defaultExpandedKeys: []
    }
  },
  watch: {
    name: {
      handler(newVal) {
        this.orgName = newVal
      },
      immediate: true
    },
    status: {
      immediate: true,
      handler(newVal) {
        this.dialogStatus = newVal
      }
    },
    tree: {
      immediate: true,
      handler(newVal) {
        this.treeData = newVal
      }
    }
  },
  created() {
  },
  methods: {
    getNodeData(data) {
      this.orgName = data.orgName
      this.$emit('getNodeData', data)
    },
    projectOrgFun(e) {
      if (this.status === 'query') {
        return false
      }
      if (this.isShow) {
        this.isShow = false
      } else {
        this.isShow = true
      }
      document.onclick = function() {
        that.isShow = false
      }
      e.stopPropagation()// 阻止冒泡
      var that = this
      that.$refs.tree.$el.onmouseleave = function() {
        that.isShow = false
      }
    }

  }
}
</script>

<style lang="scss" scoped>
  /deep/ .el-tree{
    position: absolute;
    cursor: default;
    background: #fff;
    color: #606266;
    z-index: 100;
    border: 1px solid #dcdfe6;
    border-radius: 5px;
    width: 100%;
    height: 300px;
    overflow: auto;
  }
</style>

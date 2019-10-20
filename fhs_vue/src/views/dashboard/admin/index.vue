<template>
  <div class="dashboard-editor-container">
    <el-form :model="form">
      <el-form-item label="用户ID">
        <el-input type="text" v-model="form.id" />
      </el-form-item>
      <el-form-item>
        <el-button @click="handelQuery">查询</el-button>
      </el-form-item>
    </el-form>
    <el-form :model="result">
      <el-form-item label="返回数据">
        <el-input v-model="result.content" type="textarea"  />
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

import { findUserById } from '@/api/test'
export default {
  name: 'DashboardAdmin',

  data() {
    return {
      form: {
        id: undefined
      },
      result: {
        content: undefined
      }
    }
  },
  methods: {
    async handelQuery() {
      try {
        const res = await findUserById({ id: this.form.id })
        console.log(res)
        this.result.content = JSON.stringify(res)
      } catch (e) {
        this.$message.error(e.message)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .github-corner {
    position: absolute;
    top: 0px;
    border: 0;
    right: 0;
  }

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}
</style>

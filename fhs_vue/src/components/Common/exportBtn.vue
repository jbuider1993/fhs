<template>
  <el-button type="primary" :size="size" :class="className" @click="handleClick">
    <i v-show="isIcon" class="el-icon-download el-icon--left" />
    {{ btnText }}
  </el-button>
</template>

<script>
export default {
  name: 'ExportBtn',
  props: {
    isIcon: {
      type: Boolean,
      default: true
    },
    size: {
      type: String,
      default: 'mini'
    },
    btnText: {
      type: String,
      default: '导出'
    },
    className: {
      type: String,
      default: 'blue-btn'
    }
  },
  methods: {
    handleClick() {
      this.$emit('exportFile')
    }
  }

}
</script>

<style scoped>

</style>

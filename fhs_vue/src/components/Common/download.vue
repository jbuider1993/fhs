<template>
  <el-button type="primary" :size="size" :class="className" @click="handleClick">
    <i v-show="isIcon" class="el-icon-download el-icon--left" />
    {{ btnText }}
  </el-button>
</template>

<script>
export default {
  name: 'Download',
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
      default: '下载'
    },
    className:{
      type: String,
      default: 'blue-btn'
    },
  },
  methods: {
    handleClick() {
      this.$emit('download')
    }
  }

}
</script>

<style scoped>

</style>

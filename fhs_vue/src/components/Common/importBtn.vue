<template>

  <el-upload
    ref="upload"
    action=""
    :multiple="multiple"
    :drag="drag"
    :limit="limit"
    :accept="accept"
    :before-remove="beforeRemove"
    :list-type="listType"
    :http-request="importFile"
    :before-upload="beforeUpload"
    :show-file-list="showFileList"
  >
    <el-button type="primary" :size="size" :class="className">
      <i v-show="isIcon" class="el-icon-upload2 el-icon--left" />
      {{ btnText }}
    </el-button>
  </el-upload>

</template>

<script>
import { ImportExcel } from '@/api/base'
export default {
  name: 'ImportBtn',
  props: {
    limit: {
      type: Number,
      default: 1
    },
    showFileList: {
      type: Boolean,
      default: false
    },
    listType: {
      type: String,
      default: 'text'
    },
    drag: {
      type: Boolean,
      default: false
    },
    multiple: {
      type: Boolean,
      default: false
    },
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
      default: '导入'
    },
    className: {
      type: String,
      default: 'blue-btn'
    },
    accept: {
      type: String,
      default: ''
    },
    isExtend: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      fileList: []
    }
  },
  methods: {
    importFile(file) { // 上传成功时返回 {url: '---.jpg', id: '123321'}
      const loading = this.$loading({
        lock: true,
        text: '文件上传中',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      const formData = new FormData()
      formData.append('file', file.file)
      // formData.append('customerId', 'e6yun')
      // formData.append('businessTypeKey', 'driver')
      // formData.append('busibessJoin', '')
      formData.append('isExtend', this.isExtend)

      ImportExcel(formData).then(res => {
        if (res.code === 200) {
          this.$message({
            message: '上传成功！',
            type: 'success'
          })
        } else {
          this.fileList.push(...[])
          this.$message({
            message: res.message,
            type: 'error'
          })
        }
        this.$emit('success', { data: res.result })
        loading.close()
      }).catch(() => {
        this.fileList.push(...[])
        loading.close()
      })
    },
    beforeUpload(file) { // 限制上传文件大小
      const isToBig = (file.size / 1024 / 1024) > 10
      if (isToBig) {
        this.$message.warning('图片不能大于10M')
        return false
      }
    }
  }

}
</script>

<style scoped>

</style>

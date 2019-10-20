<template>
  <el-upload
    ref="upload"
    action=""
    :multiple="multiple"
    :on-change="onChangeFile"
    :drag="drag"
    :limit="limit"
    :on-remove="onRemove"
    :before-remove="beforeRemove"
    :list-type="listType"
    :http-request="uploadFile"
    :on-exceed="onExceed"
    :before-upload="beforeUpload"
    :show-file-list="showFileList"
  >
    <el-button :size="size" :class="className" type="primary">{{ btnText }}</el-button>
  </el-upload>
</template>

<script>
import { uploadFile } from '@/api/base'
export default {
  name: 'UploadFile',
  props: {
    showFileList: {
      type: Boolean,
      default: false
    },
    listType: {
      type: String,
      default: 'text'
    },
    btnText: {
      type: String,
      default: '选择文件'
    },
    size: {
      type: String,
      default: 'mini'
    },
    drag: {
      type: Boolean,
      default: false
    },
    multiple: {
      type: Boolean,
      default: false
    },
    className: {
      type: String,
      default: 'blue-btn'
    }
  },
  data() {
    return {
      fileList: []
    }
  },
  computed: {

  },
  methods: {
    beforeRemove(file, fileList) {
      fileList.forEach((ele, index) => {
        if (file.uid === ele.uid) {
          this.index = index
        }
      })
    },
    uploadFile(file) { // 上传成功时返回 {url: '---.jpg', id: '123321'}
      const loading = this.$loading({
        lock: true,
        text: '文件上传中',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      const formData = new FormData()
      formData.append('files', file.file)
      formData.append('customerId', 'plateform')
      formData.append('businessTypeKey', 'admin1')
      formData.append('busibessJoin', '')
      uploadFile(formData).then(res => {
        this.$message({
          message: '上传成功！',
          type: 'success'
        })
        this.$emit('success', { data: res })
        loading.close()
      }).catch(() => {
        this.fileList.push(...[])
        loading.close()
      })
    },
    beforeUpload(file) { // 限制上传文件大小
      const isToBig = (file.size / 1024 / 1024) > 1024
      if (isToBig) {
        this.$message.warning('图片不能大于1G')
        return false
      }
    },
    onChangeFile() {
    },
    onExceed() { // 上传数量超过限制
      this.$message({
        message: '上传数量超过限制！',
        type: 'warning'
      })
      this.$emit('exceed')
    },
    onRemove() { // 删除
      this.$emit('remove', this.index)
    }
  }
}
</script>

<style lang="scss" scoped>

</style>

<template>
  <div class="upload-file">
    <el-upload
      ref="upload"
      action=""
      name="filecontent"
      :on-change="onChangeFile"
      :drag="true"
      :limit="limit"
      :on-remove="onRemove"
      :before-remove="beforeRemove"
      list-type="picture-card"
      accept=".jpg,.jpeg,.png,.gif,.JPG,.JPEG,.GIF"
      :http-request="uploadFile"
      :on-exceed="onExceed"
      :before-upload="beforeUpload"
      :show-file-list="showFileList"
    >
      <img v-if="imgUrl" :src="imgUrl" alt="" width="104" height="104">
      <i v-else class="el-icon-plus" />
      <slot v-if="!imgUrl" class="tip-text" />
    </el-upload>
  </div>
</template>

<script>
// import { uploadFile } from '@/api/uploadFileApi'
export default {
  name: 'Upload',
  props: {
    limit: {
      type: Number,
      default: 20
    },
    showFileList: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      imgUrl: '',
      fileList: ''
    }
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
      const formData = new FormData()
      formData.append('multipartFile', file.file)
      uploadFile(formData).then(res => {
        if (res.rel) {
          if (!this.showFileList) {
            this.imgUrl = URL.createObjectURL(file.file)
          }
          this.$emit('uploadSuccess', res)
          this.$message({
            message: '上传成功！',
            type: 'success'
          })
        } else {
          this.$message({
            message: res.message,
            type: 'warning'
          })
        }
      })
    },
    beforeUpload(file) { // 限制上传文件大小
      const isToBig = (file.size / 1024 / 1024) > 10
      if (isToBig) {
        this.$message.warning('图片不能大于10M')
        return false
      }
    },
    onChangeFile() {
    },
    onExceed() { // 上传数量超过限制
      this.$emit('exceed')
    },
    onRemove() { // 删除
      this.$emit('remove', this.index)
    },
    removeUploadImg() {
      this.imgUrl = undefined
    }
  }
}
</script>

<style lang="scss" scoped>
  .upload-file {
    display: inline-block;
    border-radius: 4px;
    > div {
      height: 104px;
    }
    /deep/ .el-upload-dragger {
      width: 104px;
      height: 104px;
      text-align: center;
      line-height: 104px;
      position: relative;
      .el-icon-plus {
      }
      > span {
        position: absolute;
        top: 70px;
        left: 50%;
        font-size: 12px;
        color: #8c939d;
        line-height: 18px;
        -webkit-transform: translateX(-50%);
        transform: translateX(-50%);
      }
    }
    /deep/ .el-upload-list__item {
      width: 104px;
      height: 104px;
    }
    /deep/ .el-upload--picture-card {
      border: none;
      width: 104px;
      height: 104px;
    }
    /deep/ .el-upload--text {
      width: 100%;
      height: 100%;
      text-align: center;
      .el-icon-plus {
        font-size: 32px;
        line-height: 104px;;
        color: #dcdfe6;
      }
    }
  }
</style>


<template>
  <!--上传单张图片-->
  <el-upload
    class="avatar-uploader"
    action=""
    accept=".jpg,.jpeg,.png,.gif,.JPG,.JPEG,.GIF"
    :show-file-list="false"
    :disabled="disabled"
    :http-request="uploadFile"
    :before-upload="beforeUpload"
  >
    <div class="el-upload">
      <img v-if="imgId" :src="imageUrl" class="avatar">
      <i v-else class="el-icon-plus avatar-uploader-icon" />
    </div>
  </el-upload>
</template>

<script>
import { uploadFile } from '@/api/base'
export default {
  name: 'UploadImage',
  props: ['fileId', 'disabled'],
  data() {
    return {
      imgId: this.fileId
    }
  },
  computed: {
    imageUrl() {
      if (this.imgId) {
        // return 'http://ibs-web-portal.dev.chinacrt.com:23456/MIDDLE-COMMON-FILEUPLOAD/api/upload/findFileByFileId?fileId=' + this.imgId
        return '/MIDDLE-COMMON-FILEUPLOAD/api/upload/findFileByFileId?fileId=' + this.imgId
      } else {
        return ''
      }
    }
  },
  watch: {
    fileId: function (newVal, oldVal) { // 箭头函数  不然会发生this改变
      console.log(newVal, oldVal)
      console.log(this)
      this.imgId = newVal
    }
  },
  methods: {
    beforeUpload(file) {
      const isLt5M = file.size / 1024 / 1024 < 5
      if (!isLt5M) {
        this.$message.error('上传图片大小不能超过 5MB!')
      }
      return isLt5M
    },
    uploadFile(file) {
      const formData = new FormData()
      formData.append('files', file.file)
      formData.append('customerId', 'platform')
      formData.append('businessTypeKey', 'admin1')
      formData.append('busibessJoin', '')
      uploadFile(formData).then(res => {
        this.imgId = res[0].fileId
        this.$message({
          message: '上传成功！',
          type: 'success'
        })
        this.$emit('success', { data: res })
      }).catch(err => {
        console.error(err)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
  .avatar-uploader {
    .el-upload {
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
      width: 100%;
      height: 100%;
    }
    /deep/ >.el-upload {
      width: 100%;
      height: 100%;
    }
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width:inherit;
    height:inherit;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    display: block;
    width: 100%;
    object-fit: scale-down;
  }
</style>

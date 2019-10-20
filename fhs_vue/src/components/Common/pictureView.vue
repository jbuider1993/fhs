<template>
  <div>
    <img :src="imageUrl" @click="openPre" class="preViewPic">
    <div v-show="isShow" class="picture-view-modal">
      <span class="picture-view-close" @click="isShow=false">x</span>
      <img class="pictureImg" :src="imageUrl">
    </div>
  </div>
</template>
<script>
export default {
  name: 'PictureView',
  props: ['imgId'],
  data() {
    return {
      isShow: false
      // picUrl:undefined
    }
  },
  computed: {
    imageUrl() {
      if (this.imgId) {
        return '/MIDDLE-COMMON-FILEUPLOAD/api/upload/findFileByFileId?fileId=' + this.imgId
      } else {
        return ''
      }
    }
  },
  watch: {
    fileId: function(newVal, oldVal) { // 箭头函数  不然会发生this改变
      console.log(newVal, oldVal)
      console.log(this)
      this.imgId = newVal
    }
  },
  methods: {
    openPre() {
      if (this.imgId) {
        console.log('=============')
        this.isShow = true
      }
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .picture-view-modal {
    position: fixed;
    z-index: 9900;
    top:0;
    left:0;
    width:100%;
    height:100%;
    box-sizing: border-box;
    overflow: hidden;
    background-color: rgba(0,0,0,0.9);
  }
  .picture-view-close {
    position:absolute;
    top:15px;
    right:40px;
    font-size:40px;
    font-weight: bold;
    transition: 0.3s;
    z-index: 9999;
    color:#ffffff;
    cursor: pointer;
  }
  .pictureImg {
    position: relative;
    top: 50%;
    left: 50%;
    max-height: 850px;
    transform: translate(-50%,-50%)

  }
  .preViewPic {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    width: 280px;
    height: 160px;
  }
</style>

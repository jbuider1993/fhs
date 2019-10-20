<template>
  <div>
    <div class="block">
      <el-carousel height="350px">
        <el-carousel-item v-for="item in resultList" :key="item.index">
          <!--<h3>{{ item }}</h3>-->
          <img :src="'/MIDDLE-COMMON-FILEUPLOAD/api/upload/findFileByFileId?fileId=' + item.photo" alt="" style="width: 100%;height: 100%;">
        </el-carousel-item>
      </el-carousel>
    </div>
    <!-- 拖拽交换位置效果 -->
    <el-row style="height: 80px;">
      <el-col :span="24">
        <transition-group tag="div" class="container">
          <!-- 循环出三个颜色不同的div -->
          <div
            v-for="(item,index) in resultList"
            :key="index"
            class="item"
            :style="{backgroundImage: 'url(\'' + '/MIDDLE-COMMON-FILEUPLOAD/api/upload/findFileByFileId?fileId=' + item.photo + '\')'}"
            draggable="true"
            @dragstart="handleDragStart($event, item)"
            @dragover.prevent="handleDragOver($event, item)"
            @dragenter="handleDragEnter($event, item)"
            @dragend="handleDragEnd($event, item)"
          >
            <!--<img :src="'http://ibs-web-portal.dev.chinacrt.com:23456/MIDDLE-COMMON-FILEUPLOAD/api/upload/findFileByFileId?fileId=' + item.photo" alt="" style="width: 100%;height: 100%">-->
          </div>
        </transition-group>
      </el-col>
    </el-row>
  </div>
</template>

<script>
// draggable 属性规定元素是否可拖动
// dragstart 事件在用户开始拖动元素或选择的文本时触发
// dragover 当某被拖动的对象在另一对象容器范围内拖动时触发此事件
// dragenter - 当被鼠标拖动的对象进入其容器范围内时触发此事件
// dragend - 用户完成元素拖动后触发
export default {
  name: 'Toolbar',
  props: ['previewList'],
  data() {
    return {
      dragging: null,
      resultList: this.previewList
    }
  },
  watch: {
    previewList: function(val) {
      this.resultList = val
    }
  },
  created() {
  },

  methods: {
    handleDragStart(e, item) {
      this.dragging = item
    },
    handleDragEnd(e, item) {
      this.dragging = null
    },
    // 首先把div变成可以放置的元素，即重写dragenter/dragover
    // DataTransfer 对象用来保存，通过拖放动作，拖动到浏览器的数据。
    // 如果dropEffect 属性设定为none,则不允许被拖放到目标元素中。
    handleDragOver(e) {
      e.dataTransfer.dropEffect = 'move'// e.dataTransfer.dropEffect="move";//在dragenter中针对放置目标来设置!
    },
    handleDragEnter(e, item) {
      e.dataTransfer.effectAllowed = 'move'// 为需要移动的元素设置dragstart事件
      if (item === this.dragging) {
        return
      }
      const newItems = [...this.resultList]
      // console.log(newItems)
      const src = newItems.indexOf(this.dragging)
      const dst = newItems.indexOf(item)
      // 替换
      newItems.splice(dst, 0, ...newItems.splice(src, 1))
      // 让item的颜色等于新交换的颜色
      this.resultList = newItems
      this.$emit('sortList', this.resultList)
    }
  }
}
</script>

<style scoped>
  .container{
    width: 100%;
    position: absolute;
    left: 0;
    display:flex;
    flex-direction: row;
    padding: 0;
  }
  .item {
    width: 140px;
    height: 80px;
    background-size: 100%;
    margin-top: 10px;
    transition: all linear .3s
  }
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>

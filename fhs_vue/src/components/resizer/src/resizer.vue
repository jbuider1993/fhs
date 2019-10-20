<template>
  <div>
    <div v-show="isMask" class="resizer-mask" ref="resizerMask" :style="{ top : maskTop + 'px'}"></div>
    <div
      class="resizer-drag"
      :style="{ top : dragTop + 'px'}"
      ref="resizer"
      @mousedown.prevent="mouseDown"
    >
      <p>拖拽</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "e6Resizer",
  props: {
    top: {
      type: Number,
      default: 0
    },
    min: {
      type: Number,
      default: 0
    },
    move: {
      type: Function,
      default: function() {
        return;
      }
    }
  },
  data() {
    return {
      isMask: false,
      clickTop: 0, // 点击时的 top
      dragTop: 0, // 拖拽层的 top
      dy: 0
    };
  },
  watch: {
    top: {
      handler(val) {
        this.dragTop = val;
      }
    }
  },
  mounted() {
    this.dragTop = this.top;
  },
  computed: {
    maskTop() {
      return this.dragTop + 6;
    }
  },
  methods: {
    mouseDown(ev) {
      var iEvent = ev || event;
      this.dy = iEvent.clientY; //当你第一次单击的时候，储存Y轴的坐标。
      this.isMask = true;
      this.clickTop = this.dragTop;
      document.addEventListener("mousemove", this.mouseMove);
      document.addEventListener("mouseup", this.mouseUp);
    },
    mouseMove(ev) {
      if (this.isMask) {
        var iEvent = ev || event;
        var toTop = this.clickTop + (iEvent.clientY - this.dy);

        var pageHeight = this.getPageSize().pageHeight;

        if (toTop + 50 + this.min >= pageHeight) return;

        this.dragTop = toTop <= 0 ? 0 : toTop;
      }
    },
    mouseUp() {
      this.isMask = false;
      this.$emit("move", this.dragTop);
      document.removeEventListener("mousemove", this.mouseMove);
      document.removeEventListener("mouseup", this.mouseUp);
    },
    getPageSize() {
      try {
        var xScroll; //页面滚动宽度
        var yScroll; //页面滚动高度
        var pageHeight;
        var pageWidth;

        if (window.innerHeight && window.scrollMaxY) {
          xScroll = document.body.scrollWidth;
          yScroll = window.innerHeight + window.scrollMaxY;
        } else if (document.body.scrollHeight > document.body.offsetHeight) {
          xScroll = document.body.scrollWidth;
          yScroll = document.body.scrollHeight;
        } else {
          xScroll = document.body.offsetWidth;
          yScroll = document.body.offsetHeight;
        }

        var windowWidth; //屏幕宽度
        var windowHeight; //屏幕高度

        if (self.innerHeight) {
          // all except Explorer
          windowWidth = self.innerWidth;
          windowHeight = self.innerHeight;
        } else if (
          document.documentElement &&
          document.documentElement.clientHeight
        ) {
          // Explorer 6 Strict Mode
          windowWidth = document.documentElement.clientWidth;
          windowHeight = document.documentElement.clientHeight;
        } else if (document.body) {
          // other Explorers
          windowWidth = document.body.clientWidth;
          windowHeight = document.body.clientHeight;
        }
        /*当页面滚动高度比屏幕高度小的时候，页面高度为屏幕高度，反之，为页面滚动高度*/
        if (yScroll < windowHeight) {
          pageHeight = windowHeight;
        } else {
          pageHeight = yScroll;
        }
        /*当页面滚动宽度比屏幕宽度小的时候，页面宽度为屏幕宽度，反之，为页面滚动宽度*/
        if (xScroll < windowWidth) {
          pageWidth = windowWidth;
        } else {
          pageWidth = xScroll;
        }

        var arrayPageSize = {
          pageWidth: pageWidth,
          pageHeight: pageHeight,
          windowWidth: windowWidth,
          windowHeight: windowHeight
        };
        return arrayPageSize;
      } catch (ex) {
        console.log("GetPageSize", ex);
      }
    }
  }
};
</script>

<style lang="scss"  scoped>
.resizer-mask {
  width: 100%;
  background-color: #e0f2fd;
  opacity: 0.6;
  bottom: 0;
  position: absolute;
  z-index: 9999999;
  box-sizing: border-box;
}
.resizer-drag {
  top: auto !important;
  margin-top: -5px;
  height: 6px;
  min-width: 1150px;
  background-color: #f3f3f3;
  display: flex;
  flex-direction: row;
  justify-content: center;
  position: absolute;
  p {
    margin: 0;
    height: 5px;
    background: url("~@/assets/images/map/icon_drag.png") no-repeat 50% 0;
    overflow: hidden;
    text-indent: -9999px;
  }
}

.resizer-drag:hover {
  cursor: ns-resize;
  background-color: #e0f2fd;
}
</style>

<template>
  <transition name="el-zoom-in-top">
    <div class="e6-box-layor" v-show="isShowBox">
      <div class="e6-box-top" v-drag>
        <div>{{ title }}</div>
        <i class="e6-icon-delete_line" title="关闭" @click="closeBox(true)"></i>
      </div>
      <slot></slot>
    </div>
  </transition>
</template>

<script>
import { getPageSize } from "@/utils/map/e6Common.js";
export default {
  name: "E6Box",
  props: {
    title: {
      type: String,
      default: ""
    },
    closeFun: {
      type: Function,
      default: function() {
        return;
      }
    }
  },
  data() {
    return {
      isShowBox: false
    };
  },
  methods: {
    showBox() {
      this.isShowBox = true;
    },
    closeBox(isCallback = false) {
      this.isShowBox = false;
      if (isCallback) {
        this.$emit("closeFun");
      }
    }
  },
  directives: {
    drag(el) {
      var wh = getPageSize();
      el.onmousedown = function(e) {
        var boxW = el.parentElement.offsetWidth;
        var disx = e.pageX - el.parentElement.offsetLeft;
        var disy = e.pageY - el.parentElement.offsetTop;
        var menuW = document.querySelector(".e6-menu-section").clientWidth;
        var h = el.parentElement.offsetHeight;
        var w = el.parentElement.offsetWidth;
        document.onmousemove = function(e) {
          var movex = e.pageX - disx;
          var movey = e.pageY - disy;
          movex =
            movex < 0
              ? 0
              : movex + w + menuW > wh.windowWidth
              ? wh.windowWidth - w - menuW
              : movex;
          movey =
            movey < 37
              ? 37
              : movey + h + 37 + 15 > wh.windowHeight
              ? wh.windowHeight - h - 37 - 15
              : movey;
          el.parentElement.style.left = movex + "px";
          el.parentElement.style.top = movey + "px";
        };
        document.onmouseup = function(e) {
          document.onmouseup = document.onmousemove = null;
        };
        e.preventDefault();
      };
    }
  }
};
</script>

<style lang="scss" scoped>
.e6-box-layor {
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.15);
  border-radius: 3px;
  position: absolute;
  z-index: 200;
  .e6-box-top {
    cursor: move;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    border-radius: 3px 3px 0 0 !important;
    background: #f8f8f8 !important;
    color: #222;
    font-weight: bold;
    height: 35px;
    line-height: 35px;
    padding: 0 10px;
    i {
      cursor: pointer;
    }
  }
}
</style>
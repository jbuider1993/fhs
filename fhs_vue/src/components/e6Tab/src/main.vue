<template>
  <div class="tContainer">
    <ul class="clearfix">
      <li
        :style="{height:height+'px',lineHeight:height+'px'}"
        :class="['clearfix',item.isActive?'active':'']"
        v-for="(item, index) in tabList"
        :key="index"
        @click="tabClick(item)"
      >
        <i
          v-if="item.color"
          class="colorTag"
          :style="{marginTop:(height-16)/2 + 'px',backgroundColor:item.color,borderColor:item.color=='#fff' || item.color=='#ffffff'?'#cecece':item.color}"
        ></i>
        <span>{{item.selected.name == "全部"?item.type:item.selected.name}}</span>
        <em
          :class="[item.color?'specialNum':'']"
        >{{item.color?'':'('}}{{item.selected.num}}{{item.color?'':')'}}</em>
        <div v-if="item.children" class="pop" :style="{bottom:height+'px'}">
          <ul class="clearfix">
            <li
              :class="[childItem.name == item.selected.name?'active':'']"
              v-for="(childItem,childIndex) in item.children"
              :key="childIndex"
              @click.stop="childItemClick(item.type, childItem)"
            >{{childItem.name}} （{{childItem.num}}）</li>
          </ul>
        </div>
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  name: "e6Tab",
  props: {
    tabList: {
      type: Array,
      default() {
        return [];
      }
    },
    height: { type: Number, default: 32 }
  },
  data() {
    return {};
  },
  methods: {
    tabClick(opts) {
      this.$emit("tabChange", { type: opts.type, selected: opts.selected });
    },
    childItemClick(type, opts) {
      this.$emit("tabChange", { type, selected: opts });
    }
  },
  mounted() {}
};
</script>
<style lang="scss" scoped>
.tContainer {
  > ul {
    > li {
      float: left;
      color: #888;
      font-size: 12px;
      box-sizing: border-box;
      border: 1px solid transparent;
      border-bottom: 0;
      border-radius: 3px;
      border-bottom-left-radius: 0;
      border-bottom-right-radius: 0;
      padding: 0 10px;
      margin: -15px 5px 0px 5px;
      position: relative;
      cursor: pointer;
      i {
        float: left;
      }
      &.active {
        background-color: #fff;
        border-color: #ddd;
        color: #000;
      }
      &:hover {
        background-color: #fff;
        border-color: #ddd;
        color: #000;
        .pop {
          display: block;
        }
      }
      .colorTag {
        width: 16px;
        height: 16px;
        display: inline-block;
        border-radius: 3px;
        box-sizing: border-box;
        border: 1px solid transparent;
        margin-right: 5px;
      }
      em {
        font-style: normal;
        &.specialNum {
          position: relative;
          background: #319be1;
          padding: 1px 4px;
          border: 1px solid #f8f8f8;
          border-radius: 11px;
          font-size: 10px;
          line-height: 12px;
          color: #fff;
          margin-left: -10px;
          float: left;
          max-width: 41;
          overflow: hidden;
          top: 1px;
        }
      }
      .pop {
        display: none;
        position: absolute;
        box-shadow: 0 0 3px rgba(0, 0, 0, 0.2);
        background: #fff;
        border-radius: 3px;
        font-size: 12px;
        width: 300px;
        left: 0;
        z-index: 9999;
        ul {
          li {
            float: left;
            width: 50%;
            padding: 2px 10px;
            color: #444;
            box-sizing: border-box;
            &.active,
            &:hover {
              color: #219bd0;
            }
          }
        }
      }
    }
  }
}
.clearfix {
  zoom: 1;
}
.clearfix:after {
  content: ".";
  display: block;
  clear: both;
  visibility: hidden;
  height: 0px;
}
</style>

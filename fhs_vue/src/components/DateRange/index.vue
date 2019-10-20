<template>
  <div>
    <el-date-picker
      v-model="startDateTime"
      clearable
      type="date"
      format="yyyy-MM-dd"
      value-format="yyyy-MM-dd"
      :picker-options="pickerOptionsStart"
      placeholder="请选择开始时间"
      @change="change"
    />
    <span class="to-text">{{ toText }}</span>
    <el-date-picker
      v-model="endDateTime"
      clearable
      type="date"
      format="yyyy-MM-dd"
      value-format="yyyy-MM-dd"
      :picker-options="pickerOptionsEnd"
      placeholder="请选择结束时间"
      @change="change"
    />
  </div>
</template>

<script>
export default {
  name: 'DateRange',
  props: {
    startTime: {
      type: String,
      default: ''
    },
    endTime: {
      type: String,
      default: ''
    },
    option: {
      type: Object
    },
    toText: {
      type: String,
      default: '-'
    }
  },
  data() {
    return {
      startDateTime: this.startTime,
      endDateTime: this.endTime,
      pickerOption: Object.assign({}, this.option),
      pickerOptionsStart: {
        disabledDate: (time) => {
          if (this.endDateTime) {
            return time.getTime() > new Date() && time.getTime() > new Date(this.endDateTime)
          }
        }
      },
      pickerOptionsEnd: {
        disabledDate: (time) => {
          if (this.startDateTime) {
            return time.getTime() < new Date(this.startDateTime)
          }
        }
      }
    }
  },
  watch: {
    startTime(val) {
      this.startDateTime = val
    },
    endTime(val) {
      this.endDateTime = val
    }
  },
  methods: {
    change() {
      if (!U.isEmpty(this.startDateTime) && !U.isEmpty(this.endDateTime)) {
        this.$emit('getValue', { startTime: this.startDateTime, endTime: this.endDateTime })
      }
    }
  }
}
</script>

<style scoped>

</style>

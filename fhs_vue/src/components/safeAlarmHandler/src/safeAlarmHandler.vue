<template>
  <el-dialog title="主动安全事件处理" :visible="dialogShow" width="600px" :before-close="handleClose">
    <el-form :model="alarmData" label-position="right" label-width="80px" ref="alarmForm">
      <el-form-item label="时间">
        <span>{{alarmData.time}}</span>
      </el-form-item>
      <el-form-item label="车牌号">
        <span>{{alarmData.vehicleNo}}</span>
      </el-form-item>
      <el-form-item label="司机名称">
        <span>{{alarmData.driverName}}</span>
      </el-form-item>
      <el-form-item label="事件名称">
        <span>{{alarmData.eventTypeName}}</span>
      </el-form-item>
      <el-form-item label="危险等级">
        <span>{{alarmData.eventLevelName}}</span>
      </el-form-item>
      <el-form-item label="位置">
        <span>{{alarmData.position}}</span>
      </el-form-item>
      <el-form-item label="下发短信">
        <el-switch v-model="alarmData.isSendMsg"></el-switch>
      </el-form-item>
      <el-form-item
        label="处理意见"
        prop="content"
        :rules="[
{ required: alarmData.isSendMsg, message: '处理意见不能为空', trigger: 'blur' }
    ]"
      >
        <el-input type="textarea" v-model="alarmData.content"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="handleClose">返回</el-button>
      <el-button type="primary" @click="onSubmit">处理</el-button>
    </span>
  </el-dialog>
</template>
<script>
export default {
  name: "safeAlarmHandler",
  props: {
    detailData: {
      type: Object,
      default: function() {
        return {};
      }
    },
    dialogShow: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      alarmData: {
        time: "",
        vehicleNo: "",
        driverName: "",
        eventTypeName: "",
        eventLevel: "",
        eventLevelName: "",
        position: "",
        isSendMsg: false,
        content: ""
      }
    };
  },
  watch: {
    detailData: {
      handler: function() {
        Object.assign(this.alarmData, this.detailData);
      },
      deep: true
    }
  },
  methods: {
    onSubmit() {
      this.$refs.alarmForm.validate(valid => {
        if (valid) {
          this.$emit("handle", this.alarmData);
        } else {
          return false;
        }
      });
    },
    handleClose() {
      this.alarmData = {
        time: "",
        vehicleNo: "",
        driverName: "",
        eventTypeName: "",
        eventLevel: "",
        eventLevelName: "",
        position: "",
        isSendMsg: false,
        content: ""
      };
      this.$emit("close");
    }
  },
  mounted() {}
};
</script>
<style lang="scss" scoped>
</style>

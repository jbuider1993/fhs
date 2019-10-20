<template>
  <el-dialog title="报警处理" :visible="dialogShow" width="600px" :before-close="handleClose">
    <el-form :model="alarmData" label-position="right" label-width="80px" ref="alarmForm">
      <el-form-item label="车牌号">
        <span>{{alarmData.licensePlateNumber}}</span>
      </el-form-item>
      <el-form-item label="报警分类">
        <span>{{alarmData.alarmTypeName}}</span>
      </el-form-item>
      <el-form-item label="报警名称">
        <span>{{alarmData.alarmName}}</span>
      </el-form-item>
      <el-form-item label="报警位置">
        <span>{{alarmData.bPlaceName}}</span>
      </el-form-item>
      <el-form-item label="开始时间">
        <span>{{alarmData.bTime}}</span>
      </el-form-item>
      <el-form-item label="结束时间">
        <span>{{alarmData.eTime}}</span>
      </el-form-item>
      <el-form-item label="持续时间">
        <span>{{alarmData.duration}}</span>
      </el-form-item>
      <el-form-item
        label="处理意见"
        prop="processingMsg"
        :rules="[
{ required: true, message: '处理意见不能为空', trigger: 'blur' }
    ]"
      >
        <el-input type="textarea" v-model="alarmData.processingMsg"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="handleClose">取消</el-button>
      <el-button type="primary" @click="onSubmit">处理</el-button>
    </span>
  </el-dialog>
</template>
<script>
export default {
  name: "alarmHandler",
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
        guid: "",
        licensePlateNumber: "",
        alarmTypeName: "",
        alarmName: "",
        alarmStatusMsg: "",
        bPlaceName: "",
        bTime: "",
        eTime: "",
        duration: "",
        processingMsg: ""
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
        guid: "",
        licensePlateNumber: "",
        alarmTypeName: "",
        alarmName: "",
        alarmStatusMsg: "",
        bPlaceName: "",
        bTime: "",
        eTime: "",
        duration: "",
        processingMsg: ""
      };
      this.$emit("close");
    }
  },
  mounted() {}
};
</script>
<style lang="scss" scoped>
</style>

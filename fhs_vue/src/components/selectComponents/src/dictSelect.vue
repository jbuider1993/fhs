<template>
  <el-select ref="dictSelect" v-model="modelValue" :placeholder="placeholder" :disabled="isDisabled" @change="dictSelectChange" size="mini"  clearable filterable>
    <el-option v-for="item in dictOptions"
               :disabled="item.dicId==noClick ? true:false"
               :key="item.dicId"
               :label="item.dicName"
               :value="item.dicId">
    </el-option>
  </el-select>
</template>

<script>
  export default {
    name: "dict-select",
    props: ['defaultValue','pkey','defaultVal','isDisabled','placeholder','noClick'],
    model:{
      prop:"defaultValue",
      event:"changeValues"
    },
    data: function () {
      return {
        modelValue:this.defaultValue,
        dictOptions:[],
        dataInfo:{}
      }
    },
    watch: {
      "defaultValue": function (newV, oldV) {
        this.modelValue = newV
      },
    },
    methods: {
      dictSelectChange(data) {
        //改变父组件值
        this.$emit("changeValues", data);
        //向父组件发change事件
        this.$emit("change", data);
      },
      /**
       *  初始化字典列表
       */
      initDictSelect() {
        this.$axios.post('/base/dictionary/getDictByDicTypeKey',this.$qs.stringify({dicTypeKey:this.pkey})
        ).then(e => {
          if (e.data.code === 1) {
            this.dictOptions = e.data.result
          } else {
            this.errorMessage(e.data.message, e.data.code)
          }
        })
          .catch(err => {
            console.log(err)
          })
      },
    },
    mounted(){
      this.initDictSelect();
      if((this.defaultVal>=0)){
        this.modelValue = this.defaultVal;
        this.dictSelectChange(this.defaultVal)
      }
    }
  }
</script>
<style lang="scss" scoped>

</style>

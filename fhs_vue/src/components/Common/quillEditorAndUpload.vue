<template>
  <div>
    <el-upload
      id="quillUploadImg"
      style="display: none"
      class="avatar-uploader"
      action=""
      :http-request="uploadFile"
      name="img"
      :show-file-list="false"
    />
    <quill-editor
      ref="myQuillEditor"
      v-model="tempContent"
      class="yx-quill"
      :options="editorOption"
      @input="onEditorInput"
    />

  </div>
</template>

<script>
import quillEditor from '@/mixins/quillEditor'
export default {
  name: 'QuillEditorAndUpload',
  mixins: [quillEditor],
  model: {
    prop: 'content',
    event: 'input'
  },
  props: {
    content: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      tempContent: this.content  // eslint-disable-line
    }
  },
  watch: {
  },
  methods: {
    onEditorInput(html) {
      this.$emit('input', html)
    }
  }

}
</script>

<style lang="scss" scoped>
  /deep/ .yx-quill {
    .ql-editor {
      line-height: normal;
      height:150px;
    }
  }

</style>

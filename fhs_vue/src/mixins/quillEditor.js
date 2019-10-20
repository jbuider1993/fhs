
// 富文本空格不显示的问题，在v-html的地方添加class  ql-editor

import { uploadFile } from '@/api/base'
import { imgAddress } from '@/config/base'
export default {
  data() {
    return {
      editorOption: {
        modules: {
          toolbar: {
            container: [
              ['bold', 'italic', 'underline', 'strike'], // toggled buttons
              ['blockquote', 'code-block'],
              [{ header: 1 }, { header: 2 }], // custom button values
              [{ list: 'ordered' }, { list: 'bullet' }],
              [{ script: 'sub' }, { script: 'super' }], // superscript/subscript
              [{ indent: '-1' }, { indent: '+1' }], // outdent/indent
              [{ direction: 'rtl' }], // text direction
              [{ size: ['small', false, 'large', 'huge'] }], // custom dropdown
              [{ header: [1, 2, 3, 4, 5, 6, false] }],
              [{ color: [] }, { background: [] }], // dropdown with defaults from theme
              [{ font: [] }],
              [{ align: [] }],
              ['link', 'image', 'video'],
              ['clean'] // remove formatting button
            ],
            handlers: {
              image(value) {
                if (value) {
                  document.querySelector('#quillUploadImg input').click()
                } else {
                  this.quill.format('image', false)
                }
              }
            }
          }
        }
      }
    }
  },
  methods: {

    beforeUpload(file) {
      const isLt5M = file.size / 1024 / 1024 < 5
      if (!isLt5M) {
        this.$message.error('上传图片大小不能超过 5MB!')
      }
      return isLt5M
    },

    uploadFile(file) {
      const quill = this.$refs.myQuillEditor.quill
      const formData = new FormData()
      formData.append('files', file.file)
      formData.append('customerId', 'platform')
      formData.append('businessTypeKey', 'admin1')
      formData.append('busibessJoin', '')
      uploadFile(formData).then(res => {
        const length = quill.getSelection().index
        // 插入图片  res.url为服务器返回的图片地址
        quill.insertEmbed(length, 'image', imgAddress + res[0].fileId)
        // 调整光标到最后
        quill.setSelection(length + 1)
        this.$message({
          message: '上传成功！',
          type: 'success'
        })
        this.$emit('success', { data: res })
      }).catch(err => {
        console.error(err)
      })
    }

  }

}

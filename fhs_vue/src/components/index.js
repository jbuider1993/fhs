
import UploadFile from '@/components/Common/uploadFile'
import UploadImage from '@/components/Common/uploadImage'
import Download from '@/components/Common/download'
import ExportBtn from '@/components/Common/exportBtn'
import ImportBtn from '@/components/Common/importBtn'
import PictureView from '@/components/Common/pictureView'

const components = {
  install: function(Vue) {
    Vue.component('UploadFile', UploadFile)
    Vue.component('UploadImage', UploadImage)
    Vue.component('Download', Download)
    Vue.component('ExportBtn', ExportBtn)
    Vue.component('ImportBtn', ImportBtn)
    Vue.component('PictureView', PictureView)
  }
}
export default components

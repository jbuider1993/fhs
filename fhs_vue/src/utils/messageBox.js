import {
  MessageBox
} from 'element-ui'
import i18n from '@/lang'
export const handleCofirm = function(text, title = '提示', type = 'warning') {
  return MessageBox.confirm(text, title, {
    confirmButtonText: i18n.t('confirm.define_btn'),
    cancelButtonText: i18n.t('confirm.cancel_btn'),
    type: type
  })
}



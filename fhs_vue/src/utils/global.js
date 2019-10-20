/**
 * 功能:为了解决全局公用一种Message弹出框
 * @Author  LiuYunYang【liuyunyang@e6yun.com】
 * @Date    2019/1/12 14:23
 */
//消息弹出是否可关闭参数
let messageShowClose = true;
//弹出存在时间默认3000毫秒
let messageDuration = 3000;
// 如需自定其他特殊参数只需在此处声明该参数默认值,在messageOption中引入,创建弹窗时引入该变量在相应的vue文件中重新声明该参数即可
export default {
    install(Vue, options) {
        Vue.prototype.messageOption = {
            messageDuration,
            messageShowClose
        };
        /**
         * 成功全局方法
         * value: 提示框消息内容
         * fun:关闭时触发的方法
         */
        Vue.prototype.successMessage = function (value, fun) {
            this.$message({
                showClose: this.messageOption.messageShowClose,
                message: value,
                type: 'success',
                duration: this.messageOption.messageDuration,
                onClose: () => {
                    if (fun) {
                        fun();
                    }
                }
            });
        };
        /**
         * 警告全局方法
         * value 提示框消息内容
         * fun 关闭时触发的方法
         */
        Vue.prototype.warnMessage = function (value, fun) {
            this.$message({
                showClose: this.messageOption.messageShowClose,
                message: value,
                type: 'warning',
                duration: this.messageOption.messageDuration,
                onClose: () => {
                    if (fun) {
                        fun();
                    }
                }
            });
        };
        /**
         * 失败全局方法
         * value 提示框消息内容
         * code 错误码
         * fun 关闭时触发的方法
         */
        Vue.prototype.errorMessage = function (value, code, fun) {
            let msg = "";
            if (code) {
                msg = "错误信息：" + value + "，错误码：【 " + code + "】";
            } else {
                msg = "错误信息：" + value;
            }
            this.$message({
                showClose: this.messageOption.messageShowClose,
                message: msg,
                type: 'error',
                duration: this.messageOption.messageDuration,
                onClose: () => {
                    if (fun) {
                        fun();
                    }
                }
            });
        };

        /**
         * 默认弹出全局方法
         * options 接受一个数组用于自定义参数实现特殊弹窗
         */
        Vue.prototype.defaultMessage = function (options) {
            this.$message(options);
        };

        /**
         * 全局方法
         * value 提示框消息内容
         * type 提示框的类型
         * fun 关闭时触发的方法
         */
        Vue.prototype.changeTypeMessage = function (msg, type, fun) {
            this.$message({
                type: type,
                showClose: this.messageOption.messageShowClose,
                message: msg,
                duration: this.messageOption.messageDuration,
                onClose: () => {
                    if (fun) {
                        fun();
                    }
                }
            });
        };

        /**
         * 确认框
         * message  MessageBox 消息正文内容
         * title MessageBox 标题
         */
        Vue.prototype.confirmMessage = function (message, title) {
            return this.$confirm(
                message,
                title,
                {type: 'warning'}
            )
        };

    }
}

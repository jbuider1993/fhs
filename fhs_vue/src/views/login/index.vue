<template>
  <div class="login-container pull-height">
    <div class="login-weaper animated slideInDown">
      <div class="login-left ">
        <div class="login-name">FHS-demo</div>
        <div class="login-time" v-text="currentTime" />
        <img src="/images/track.jpg" alt="" class="img">
      </div>
      <div class="login-border ">
        <div class="login-main">
          <lang-select class="set-language" />
          <h4 class="login-title">
            {{ $t('login.title') }}
          </h4>
          <el-form
            ref="loginForm"
            class="login-form"
            status-icon
            :rules="loginRules"
            :model="loginForm"
            label-width="0"
          >
            <el-form-item prop="userLoginName">
              <el-input
                ref="userLoginName"
                v-model="loginForm.userLoginName"
                size="small"
                name="userLoginName"
                auto-complete="off"
                placeholder="请输入用户名"
                @keyup.enter.native="handleLogin"
              >
                <svg-icon slot="prefix" icon-class="user" />
              </el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input
                ref="password"
                v-model="loginForm.password"
                size="small"
                :type="passwordType"
                name="password"
                auto-complete="off"
                placeholder="请输入密码"
                @keyup.enter.native="handleLogin"
              >
                <i
                  slot="suffix"
                  class="el-icon-view el-input__icon"
                  @click="showPassword"
                />
                <svg-icon slot="prefix" :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
              </el-input>
            </el-form-item>
            <el-checkbox v-model="checked">记住账号</el-checkbox>
            <el-form-item>
              <el-button
                type="primary"
                :loading="loading"
                size="small"
                class="login-submit"
                @click.native.prevent="handleLogin"
              >
                {{ $t('login.logIn') }}
              </el-button>
            </el-form-item>
          </el-form>
          <!--<codeLogin v-else-if="activeName==='code'"></codeLogin>-->
          <!--<thirdLogin v-else-if="activeName==='third'"></thirdLogin>-->
        </div>
        <div class="versions">v1.0.1</div>
      </div>
    </div>
  </div>
</template>

<script>
import LangSelect from '@/components/LangSelect'
// var timer = null
export default {
  name: 'Login',
  components: { LangSelect },
  data() {
    return {
      timer: null,
      currentTime: null,
      loginForm: {
        userLoginName: '',
        password: ''
      },
      loginRules: {
        userLoginName: [{ required: true, trigger: 'blur', message: '请输入用户名' }],
        password: [{ required: true, trigger: 'blur', message: '请输入密码' }]
      },
      checked: false,
      passwordType: 'password',
      capsTooltip: false,
      loading: false,
      showDialog: false,
      redirect: undefined,
      otherQuery: {}
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        const query = route.query
        if (query) {
          this.redirect = query.redirect
          this.otherQuery = this.getOtherQuery(query)
        }
      },
      immediate: true
    }
  },
  created() {
    // timer = setInterval(() => {
    //   // this.currentTime += 1
    //   // this.currentTime = this.currentClockTime()
    // }, 1000)
  },
  beforeDestroy() {
    // clearInterval(timer)
  },
  methods: {
    showPassword() {
      this.passwordType === 'text' ? (this.passwordType = 'password') : (this.passwordType = 'text')
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', this.loginForm)
            .then(() => {
              // this.$router.push({ path: this.redirect || '/' })
              this.$router.push('/')
              this.loading = false
            })
            .catch(err => {
              console.log(err)
              if (err && err.message) {
                this.$message.error(err.message)
              }
              this.loading = false
            })
        } else {
          return false
        }
      })
    },
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== 'redirect') {
          acc[cur] = query[cur]
        }
        return acc
      }, {})
    },
    currentClockTime() {
      let vWeek_s = null
      const vWeek = ['星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
      const date = new Date()
      const year = date.getFullYear()
      const month = date.getMonth() + 1
      const day = date.getDate()
      const hours = date.getHours()
      const minutes = date.getMinutes()
      const seconds = date.getSeconds()
      vWeek_s = date.getDay()
      return year + '年' + month + '月' + day + '日' + '\t' + hours + ':' + minutes + ':' + seconds + '\t' + vWeek[vWeek_s]
    }
  }
}
</script>
<style lang="scss" scoped>
  .login-container {
    display: flex;
    align-items: center;
    position: relative;
    width: 100%;
    height: 100%;
    margin: 0 auto;
    background: url("/images/cloud.jpg") 0 bottom repeat-x #049ec4;
    animation: animate-cloud 20s linear infinite;
  }

  .login-weaper {
    margin: 0 auto;
    width: 1000px;
    -webkit-box-shadow: -4px 5px 10px rgba(0, 0, 0, .4);
    box-shadow: -4px 5px 10px rgba(0, 0, 0, .4);
  }

  .login-left {
    border-top-left-radius: 5px;
    border-bottom-left-radius: 5px;
    -webkit-box-pack: center;
    -ms-flex-pack: center;
    justify-content: center;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    -ms-flex-direction: column;
    flex-direction: column;
    background-color: #409eff;
    color: #fff;
    float: left;
    width: 60%;
    position: relative;
  }
  .login-name{
    position: absolute;
    right: 25px;
    bottom: 60px;
    color: #fff;
    font-size: 30px !important;
    font-weight: 550;
    opacity: .9;
    font-size: 18px;
    overflow: hidden;
  }

  .login-time {
    position: absolute;
    right: 25px;
    bottom: 25px;
    color: #fff;
    font-weight: 200;
    opacity: .9;
    font-size: 18px;
    overflow: hidden;
  }

  .login-left .img {
    height: 500px;
    width: 600px;
  }

  .login-border {
    border-left: none;
    border-top-right-radius: 5px;
    border-bottom-right-radius: 5px;
    color: #fff;
    background-color: #fff;
    width: 40%;
    float: left;
  }

  .login-main {
    margin: 0 auto;
    width: 65%;
    position: relative;
  }
  .versions{
    position: absolute;
    right: 25px;
    bottom: 25px;
    color: #000000;
  }

  .login-title {
    color: #333;
    margin-bottom: 40px;
    font-weight: 500;
    font-size: 22px;
    text-align: center;
    letter-spacing: 4px;
  }

  .login-form {
    margin: 10px 0;
    width: 100%;
  }

  .login-left .title {
    margin-top: 60px;
    text-align: center;
    color: #fff;
    font-weight: 300;
    letter-spacing: 2px;
    font-size: 25px;
  }

  .login-border, .login-left {
    position: relative;
    min-height: 500px;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
  }

  .login-code-img {
    margin-top: 2px;
    width: 100px;
    height: 38px;
    background-color: #fdfdfd;
    border: 1px solid #f0f0f0;
    color: #333;
    font-size: 14px;
    font-weight: 700;
    letter-spacing: 5px;
    line-height: 38px;
    text-indent: 5px;
    text-align: center;
  }

  .login-code {
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    -ms-flex-pack: distribute;
    justify-content: space-around;
    margin: 0 0 0 10px;
  }

  .login-form .el-input input {
    padding-bottom: 10px;
    text-indent: 5px;
    background: transparent;
    border: none;
    border-radius: 0;
    color: #333;
    border-bottom: 1px solid #ebedf2;
  }

  .login-submit {
    width: 100%;
    height: 45px;
    border: 1px solid #409eff;
    background: none;
    font-size: 18px;
    letter-spacing: 2px;
    font-weight: 300;
    color: #409eff;
    cursor: pointer;
    margin-top: 30px;
    font-family: neo;
    -webkit-transition: .25s;
    transition: .25s;
  }

  .set-language {
    position: absolute;
    right: 0px;
    top: -25px;
  }
</style>

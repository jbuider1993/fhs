<template>
  <div class="login-container">
    <div class="header">
      <div class="header-left fl">
        <img src="images/newlogo.png" alt="">
      </div>
      <div class="header-right fr">
        <img src="images/flag.png" alt="" class="flag">
        <span style="margin-right:5px">简体中文</span>
        <img src="images/arrow.png" alt="">
      </div>
    </div>
    <div class="middle">
      <div class="loginBox ">
        <el-form ref="form" :model="form" :rules="rules">
          <el-form-item prop="userLoginName">
            <div class="setFont">账号</div>
            <div style="position: relative;">
              <el-input
                v-model="form.userLoginName"
                class="editInput"
                placeholder="账号/手机号/邮箱"
                clearable
              >
                <svg-icon slot="prefix" icon-class="user" />
              </el-input>
              <img src="images/smallma.png" alt="" class="smallCode" @click="isShow=!isShow">
              <img v-show="isShow" src="images/bigma.png" alt="" class="bigCode">
            </div>
          </el-form-item>
          <el-form-item prop="password">
            <div class="setFont">密码</div>
            <el-input
              v-model="form.password"
              class="editInput"
              placeholder="请输入登录密码"
              clearable
            >
              <i
                slot="suffix"
                class="el-icon-view el-input__icon"
                @click="showPassword"
              />
              <svg-icon slot="prefix" :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
            </el-input>
          </el-form-item>
          <div>
            <span style="text-align:left">忘记密码?</span><span class="fr" style="color: #3F57B0;text-align:right">注册账号</span>
          </div>
        </el-form>
        <el-button type="primary" style="width:100%">登录</el-button>
      </div>
    </div>
    <div class="bottom">
      COPYRIGHT © 2019 fhs-framework ALLRIGHT RESERVED.
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      form: {
        userLoginName: '',
        password: ''
      },
      rules: {
        userLoginName: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      },
      passwordType: 'password',
      isShow: false
    }
  },
  methods: {
    showPassword() {
      this.passwordType === 'text' ? (this.passwordType = 'password') : (this.passwordType = 'text')
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    }
  }
}
</script>

<style  lang="scss" scoped>
  html {
    overflow: hidden;
  }
  body {
    overflow: hidden;
  }
  .login-container {
    background: url("/images/bg.png")  no-repeat;
    background-size: cover;
    width: 100%;
    height: 100%;
    position: relative;
    overflow: hidden;
  }
.header{
  height: 68px;
  padding: 16px 30px;
  background-color: #3F57B0;
}
  .header-left, .header-right {
    height: 34px;
    line-height: 34px;
    color: #fff;
    font-size: 16px;
  }
  .header-right .flag {
    margin-right: 5px;
    transform: translateY(4px);
  }
  /deep/.el-input__inner {
    height: 48px;
    line-height: 48px;
  }
  .svg-icon {
    vertical-align: -8px;
  }
  .middle {
      width:1200px;
      margin:0 auto;
      height: calc(100% - 68px - 58px);
    .loginBox {
      margin-top:200px;
      width:400px;
      float:right;
      line-height: 45px;
      .smallCode {
        position: absolute;
        top: 15px;
        right: 8px;
        width: 20px;
        height: 20px;
      }
      .bigCode {
        position: absolute;
        top:-110px;
        right:-28px;
        /*display:none;*/
      }
      .setFont {
        font-size: 21px;
        color: #333;
      }
    }
  }
  .bottom {
    height: 58px;
    width: 100%;
    line-height: 58px;
    background-color: #3F57B0;
    color: #DDE3FF;
    font-size: 14px;
    text-align: center;
  }
</style>

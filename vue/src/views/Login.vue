<template>
  <div class="all" v-loading="loading">
  <div class="container" id="container">
    <div class="form-container sign-up-container">
      <!-- 注册 -->
      <el-form :model="userRegister" :rules="userRegister1" ref="userForm">
        <h1>用户注册</h1>
        <el-form-item prop="uname" >
          <el-input v-model="userRegister.uname" type="text" placeholder="请输入你的用户名"></el-input>
        </el-form-item>
        <el-form-item prop="unickname" >
          <el-input v-model="userRegister.unickname" type="text" placeholder="请输入你的昵称"></el-input>
        </el-form-item>
        <el-form-item prop="upwd" >
          <el-input v-model="userRegister.upwd" type="password" show-password placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item prop="tryupwd" >
          <el-input v-model="userRegister.tryupwd" type="password" show-password placeholder="请确认密码"></el-input>
        </el-form-item>
        <el-form-item prop="uphone" >
          <el-input v-model="userRegister.uphone" type="text"  placeholder="请输入手机号"></el-input>
        </el-form-item>
        <el-form-item prop="uemail" >
          <el-input v-model="userRegister.uemail" type="text" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-button @click="register">注册</el-button>
      </el-form>
    </div>
    <div class="form-container sing-in-container">
      <!-- 登陆 -->
      <el-form :model="user" :rules="rules" ref="userForm">
        <h1 style="padding-bottom: 20px">用户登陆</h1>
        <br>
        <br>
        <el-form-item prop="uname" >
          <el-input type="text" prefix-icon="el-icon-user" style="position: relative;margin-bottom: 10px" v-model="user.uname" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="upwd">
          <el-input type="password" style="margin-bottom: 5px" prefix-icon="el-icon-lock" show-password  v-model="user.upwd" placeholder="密码"></el-input>
        </el-form-item>

        <div class="pwd">
        </div>
        <div style="margin-top: 40px">
        <router-link to="/"><el-button @click="login">登录</el-button></router-link>
        </div>
      </el-form>
    </div>
    <!-- 侧边栏内容 -->
    <div class="overlay-container">
      <div class="overlay">
        <div class="overlay-pannel overlay-left">
          <h1>已有账号?</h1>
          <p>点我登陆</p>
          <el-button class="ghost" id="signIn" @click="click">登陆</el-button>
        </div>
        <div class="overlay-pannel overlay-right">
          <h1>没有帐号？</h1>
          <p>点击注册</p>
          <el-button class="ghost" id="signUp" @click="click">注册</el-button>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import Cookies from "js-cookie"
export default {
  name: "Login",
  data() {
    return {
      userRegister: {
      },
      user: {
      },
      loading:false,
      userRegister1:{
        uname: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
        unickname:[
          {required: true, message: '请输入昵称', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 8 个字符', trigger: 'blur'}
        ],
        upwd: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur'}
        ],
        tryupwd: [
          {required: true, message: '请确认密码', trigger: 'blur'},
          {min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur'}
        ],
        uphone: [
          {required: true, message: '请输入正确的手机号码', trigger: 'blur'},
          {min: 6, max: 20, message: '长度在 6 到 11 个字符', trigger: 'blur'}
        ],
        uemail:[
          {required: true, message: '请正确的邮箱地址', trigger: 'blur'},
          {min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur'}
        ],
      },
      rules: {
        uname: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
        upwd: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur'}
        ]
      }
    }
  },
  mounted() {
    this.click()
  },
  methods: {
    login(){
      console.log(this.user.unickname)
      console.log(this.user.uname)
      this.$refs['userForm'].validate((valid) => {
        if(valid){
          this.request.post("/user/login",this.user).then(res =>{
            if (res.code === '200') {
              this.loading = true
              setTimeout(() => {
                this.loading = false
                // this.$router.push("/manage")
                this.$router.replace('/manage')
                this.$router.go(-1)
                localStorage.setItem("user",JSON.stringify(res.data)) //保存转换成JSON字符串的数据 localStorage 存放到浏览器 这里用的是localStorage

                this.$message.success("登录成功")
              }, 1000)
            }else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    },
    register(){
      this.$refs['userForm'].validate((valid) => {
        if(valid){
          if(this.userRegister.upwd !== this.userRegister.tryupwd){
            alert("密码不一致")
          }
          this.request.post("/user/register",this.userRegister).then(res =>{
            if (!res) {
              this.$message.error("注册失败")
            }else {
              this.loading = true
              setTimeout(() => {
                this.loading = false
                this.router.push("/")
                this.$message.success("注册成功")
              }, 500)
            }
          })
        }
      })
    },
    click() {
      const container = document.querySelector('#container');
      const signInButton = document.querySelector('#signIn');
      const signUpButton = document.querySelector('#signUp');
      signUpButton.addEventListener('click', () => container.classList.add('right-panel-active'))
      signInButton.addEventListener('click', () => container.classList.remove('right-panel-active'))
    }
  },
  beforeCreate () {
    document.querySelector('html').setAttribute('style',
        'font-family: Arial, Helvetica, sans-serif;' +
        '/* global 94%+ browsers support */\n' +
        'background: linear-gradient(  50deg, rgba(183, 182, 182, 1) 20.58%,rgba(226, 150, 150, 1) 34%,rgba(201, 180, 180, 1) 52.17%,rgba(172, 224, 249, 1) 80.03%); \n' +
        '\n' +
        '/* safari 5.1+,chrome 10+ */\n' +
        'background: -webkit-linear-gradient(  50deg, rgba(183, 182, 182, 1) 20.58%,rgba(226, 150, 150, 1) 34%,rgba(201, 180, 180, 1) 52.17%,rgba(172, 224, 249, 1) 80.03%);\n' +
        '\n' +
        '/* ff 3.6+ */\n' +
        'background: -moz-linear-gradient(  50deg, rgba(183, 182, 182, 1) 20.58%,rgba(226, 150, 150, 1) 34%,rgba(201, 180, 180, 1) 52.17%,rgba(172, 224, 249, 1) 80.03%);\n' +
        '\n' +
        '/* opera 11.10+ */ \n' +
        'background: -o-linear-gradient(  50deg, rgba(183, 182, 182, 1) 20.58%,rgba(226, 150, 150, 1) 34%,rgba(201, 180, 180, 1) 52.17%,rgba(172, 224, 249, 1) 80.03%);\n' +
        '\n' +
        '/* ie 10+ */\n' +
        'background: -ms-linear-gradient(  50deg, rgba(183, 182, 182, 1) 20.58%,rgba(226, 150, 150, 1) 34%,rgba(201, 180, 180, 1) 52.17%,rgba(172, 224, 249, 1) 80.03%);' +
        '  /* 弹性布局 */\n' +
        '  display: flex;\n' +
        '  justify-content: center;\n' +
        '  align-items: center;\n' +
        '  height: 100%;\n' +
        '  margin:  0 auto;')
  },
//销毁前清除
  beforeDestroy () {
    document.querySelector('html').removeAttribute('style')
  },
  }
</script>

<style scoped src="../assets/login.css"></style>

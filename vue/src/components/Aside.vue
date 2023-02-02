<template>
    <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden" id="el-menu"
             background-color="rgb(48, 65, 86)"
             text-color="#fff"
             active-text-color="#ffd04b"
             :collapse-transition="false"
             :collapse="isCollapse"
             width="200"
             router
    >
      <div style="height: 60px; line-height: 60px; text-align: center">
<!--        <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; margin-right: 5px">-->
        <b style="color: white" v-show="logoTextShow">小说平台信息管理系统</b>
      </div>
<!--      <el-submenu index="/">-->
<!--        <template slot="title">-->
<!--          <i class="el-icon-message"></i>-->
<!--          <span slot="title">主页</span>-->
<!--        </template>-->
<!--      </el-submenu>-->
      <el-menu-item index="/information">
        <template>
          <i class="el-icon-user"></i>
          <span slot="title">个人信息</span>
        </template>
      </el-menu-item>
      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span slot="title">用户管理</span>
        </template>
        <el-menu-item-group title="用户">
          <template slot="title"></template>
          <el-menu-item index="/user">用户列表</el-menu-item>
          <el-menu-item index="/author">作者列表</el-menu-item>
          <el-menu-item index="/reader">读者列表</el-menu-item>
          <el-menu-item index="/root">管理员列表</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-s-order"></i>
          <span slot="title">小说管理</span>
        </template>
        <el-menu-item-group title="小说">
          <el-menu-item index="/novel">小说列表</el-menu-item>
        </el-menu-item-group>
      </el-submenu>

    </el-menu>
</template>

<script>
export default {
  name: 'Aside',
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean,
    expands: Boolean
  },
  methods: {
    toggle() {// 点击收缩按钮触发
      let aside = document.querySelector('.el-aside')
      let menu = document.querySelector('#el-menu')
      let toggle = document.querySelector('.toggle')
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {  // 收缩
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {   // 展开
        // this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
      aside.classList.toggle('active')
      menu.classList.toggle('active')
      toggle.classList.toggle('active')
    },
  }
}
</script>
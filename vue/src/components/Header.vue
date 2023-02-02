<template>
  <div style="display: flex;align-items: center;font-size: 20px;width: 80%;flex: 1">
  <div style="margin-bottom: 20px;position: absolute;flex: 1;padding-top: 25px;">
    <el-breadcrumb separator=">">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>{{ this.$route.name }}</el-breadcrumb-item>
    </el-breadcrumb>
  </div>
    <div style="position:relative;left: 50%">
  <el-dropdown style=" cursor: pointer; " >
    <span @click="information" style="font-size: 15px;position: relative;left: 100%"><i>欢迎，{{user.unickname}}</i></span>
  </el-dropdown>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Header',
  data(){
    return{
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  methods:{
    information(){
        this.$router.push("/information")
      console.log(this.user.avatarUrl)
    }
  }
}
</script>
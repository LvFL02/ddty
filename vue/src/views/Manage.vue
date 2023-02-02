<template>
  <el-container style="min-height: 100vh;max-width: 1550px">
    <el-aside :width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246); box-shadow: 2px 0 6px rgb(0 21 41);">
      <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow"/>
      <div class="toggle" @click="toggle"><div class="dm3"></div></div>
    </el-aside>

    <el-container>
      <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px; display: flex">
<!--        <div style="flex: 1; font-size: 20px">-->
<!--          <div class="menu-btn" @click="collapse">-->
<!--            <span></span>-->
<!--            <span></span>-->
<!--            <span></span>-->
<!--          </div>-->
<!--          <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>-->
<!--        </div>-->
      <Header />
      </el-header>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>


<style scoped src="../assets/global.css">

</style>
<style scoped>
.el-header {
  background-color: rgba(176, 186, 201, 0.49);
  color: #333;
  line-height: 60px;
  border-radius: 0 0 4px 4px;
}

.el-aside {
  color: #333;
}
.el-main{
  background-color: #e3e3e3;
  border-radius: 10px;
}
.el-main .el-table{
  transition: all 0.6s ease-in-out;
}

</style>
<script>
import row from "element-ui/packages/row/src/row";
import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'Manage',
  components:{
    Header,
    Aside
  },
  data() {
    return {
      collapseBtnClass: 'el-icon-arrow-right',
      isCollapse: true,
      sideWidth:64,
      logoTextShow: false,

    }

  },
  methods: {
    toggle(){// 点击收缩按钮触发
        let aside = document.querySelector('.el-aside')
      let  menu = document.querySelector('#el-menu')
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

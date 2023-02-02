<template>
  <div style="width: 60vh;margin: 0 auto;top: 100px;position: relative;">
  <el-card style="width: 500px;box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25),0 10px 10px rgba(0, 0, 0, 0.22);border-radius: 18px;padding-top:10%;background-color: #e3e3e3">
    <div style="display: flex;align-items: center;justify-content: center;flex-grow: 1;flex-direction: column;">
      <div style="width: 100%;padding-bottom: 50px"><span>用户名：{{form.uname}}</span></div>
      <div style="width: 100%;padding-bottom: 50px"><span>用户昵称：{{form.unickname}}</span></div>
      <div style="width: 100%;padding-bottom: 50px"><span>邮箱：{{form.uemail}}</span></div>
      <div style="width: 100%;padding-bottom: 50px"><span>电话：{{form.uphone}}</span></div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="userEdit">修改</el-button>
        <el-popconfirm
            confirm-button-text='是'
            cancel-button-text='否'
            icon="el-icon-info"
            icon-color="red"
            title="确定退出吗？"
            @confirm="exit"
        >
        <el-button type="primary" style="margin-left: 10px" slot="reference">退出登录</el-button>
        </el-popconfirm>
      </div>
    </div>
  </el-card>
    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small" :model="form">
        <el-form-item label="用户名">
          <el-input v-model="form.uname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.unickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.uemail" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.uphone" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="userSave">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data(){
    return{
      form:{
      },
      dialogFormVisible: false,
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      this.request.get("/user/information/" +this.user.uid).then(res => {
        if (!res){
          this.$message.error("读取失败")
        }else {
          this.form = res.data
        }
      })
    },
    userEdit(row) {
      this.dialogFormVisible = true
    },
    userSave() {
      this.request.post("/user", this.form).then(res => {
        if (res) {
          this.$message.success("修改成功")
          this.dialogFormVisible = false
          console.log(this.form)
          this.load()
        } else {
          this.$message.error("修改失败")
          console.log(this.form)
        }
      })
    },
    exit(){
      this.$router.push("/")
      localStorage.removeItem("user")
      this.$message.success("退出成功")
    }
  }
}
</script>
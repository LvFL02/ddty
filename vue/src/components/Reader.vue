<template>
  <div>
    <div style="padding: 5px 0 0 0;text-align: right;display: flex;flex-direction: row;">
      <el-input style="width: 200px"  placeholder="请输入要查询的读者名" suffix-icon="el-icon-user" v-model="uname"></el-input>
      <el-input style="width: 200px"  placeholder="请输入要查询的读者昵称" suffix-icon="el-icon-star-off" v-model="unickname"></el-input>
      <el-button type="primary" style="height: 30px;margin-left: 10px" @click="load">搜索</el-button>
      <el-button type="primary" style="height: 30px;margin-left: 10px" @click="reset">还原</el-button>
    </div>
    <div style="text-align: right;width: 100%;position: relative">
      <el-button  type="primary" @click="userAdd" style="margin:5px 10px 5px 10px;">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='是'
          cancel-button-text='否'
          icon="el-icon-info"
          icon-color="red"
          title="您是否要批量删除这些数据？"
          @confirm="batchDel"
      >
        <el-button type="danger" slot="reference" style="margin:5px 10px 5px 0">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>
    <div style="height: 400px;max-width: 1800px">
      <el-table header-row-class-name="headerbg" max-height="450" v-loading="loading"
                :data="tableData"
                @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="50" align="center"></el-table-column>
        <el-table-column prop="uid" label="ID" width="50" >
        </el-table-column>
        <el-table-column prop="uname" label="读者名" width="100">
        </el-table-column>
        <el-table-column prop="unickname" label="昵称" width="180">
        </el-table-column>
        <el-table-column prop="upwd" label="密码" width="100">
        </el-table-column>
        <el-table-column prop="uphone" label="手机" width="150">
        </el-table-column>
        <el-table-column prop="uemail" label="邮箱" width="150">
        </el-table-column>
        <el-table-column prop="status" label="身份" width="100">
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="250">
        </el-table-column>
        <el-table-column label="操作"   text-align="left" >
          <template slot-scope="scope" >
            <el-button style="background-color: #eee" @click="userEdit(scope.row)">编辑</el-button>
            <el-popconfirm
                confirm-button-text='是'
                cancel-button-text='否'
                icon="el-icon-info"
                icon-color="red"
                title="是否删除此读者"
                @confirm="userDel(scope.row.uid)"
            >
              <el-button style="background-color: #ccc;margin-left: 5px" slot="reference" >删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div style="padding: 75px 0;bottom: 45px;" class="a">
      <el-pagination style="width: 100px;right: 20%;"
                     background
                     @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :current-page="pageNum"
                     :page-sizes="[8, 10]"
                     :page-size="pageSize"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="total">
      </el-pagination>
    </div>
    <el-dialog title="读者信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small" :model="form">
        <el-form-item label="用户名">
          <el-input v-model="form.uname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.unickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.upwd" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.uemail" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.uphone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="身份">
          <el-input v-model="form.status" placeholder="请输入 1" autocomplete="off"></el-input>
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
  name:"userTable",
  data(){
    return{
      tableData: [],
      form: {},
      multipleSelection: [],
      total: 0,
      pageNum: 1,
      pageSize: 8,
      uname: "",
      upwd:"",
      unickname: "",
      uemail: "",
      uphone: "",
      avatarUrl:"",
      loading: true,
      dialogFormVisible: false,
    }
  },
  created() {
    // 请求分页查询数据
    this.load()
  },
  methods:{
    load() {
      this.loading = true
      this.request.get("/user/readerPage", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          uname: this.uname,
          unickname: this.unickname,
          avatarUrl: this.avatarUrl
        }
      }).then(res => {
        console.log(res)
        this.loading = true
        setTimeout(() => {
          this.loading = false
        }, 270);
        this.tableData = res.records
        this.total = res.total
      })
    },
    reset(){
      this.uname = ""
      this.unickname = ""
      this.load()
      this.handleCurrentChange(this.pageNum = 1)
    },
    userAdd(){
      this.dialogFormVisible = true
      this.form = {}
    },
    userSave() {
      this.request.post("/user", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("添加成功")
          this.dialogFormVisible = false
          console.log(this.form)
          this.load()
        } else {
          this.$message.error("不能为空")
          console.log(this.form)
        }
      })
    },
    userEdit(row){
      this.$data.form=JSON.parse(JSON.stringify(row))
      // this.form = Object.assign({},row)  //将row拷贝到空对象中 解决没点确定数据改变的问题
      // this.form = row
      this.dialogFormVisible = true
    },
    userDel(uid){
      this.request.delete("/user/" + uid).then(res => {
        if (res) {
          this.$message.success("删除成功")
          console.log(this.uid)
          this.load()
        } else {
          this.$message.error("删除失败")
          console.log(this.form)
        }
      })
    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection = val
    },
    batchDel(){
      let uids = this.multipleSelection.map(v => v.uid)
      this.request.post("/user/batchDel" , uids).then(res => {
        if (res) {
          this.$message.success("删除成功")
          console.log(uids)
          this.load()
        } else {
          this.$message.error("删除失败")
          console.log(this.form)
        }
      })
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    exp(){
      window.open("http://localhost:8080/user/export")
    },
    importSuccess(){
      this.$message.success("文件上传成功")
      this.load()
    },
    importError(){
      this.$message.success("文件上传失败")
    }
  }
}
</script>
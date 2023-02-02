<template>
  <div>
    <div style="padding: 5px 0 0 0;text-align: right;display: flex;flex-direction: row;">
      <el-input style="width: 200px"  placeholder="请输入要查询的小说" suffix-icon="el-icon-user" v-model="nname"></el-input>
      <el-button type="primary" style="height: 30px;margin-left: 10px" @click="load">搜索</el-button>
      <el-button type="primary" style="height: 30px;margin-left: 10px" @click="reset">还原</el-button>
    </div>
    <div style="text-align: right;width: 100%;position: relative">
      <el-button  type="primary" @click="novelAdd" style="margin:5px 10px 5px 10px;">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='是'
          cancel-button-text='否'
          icon="el-icon-info"
          icon-color="red"
          title="您是否要批量删除这些小说？"
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
        <el-table-column prop="nid" label="作品ID" width="100" >
        </el-table-column>
        <el-table-column prop="nname" label="小说名" width="180" :show-overflow-tooltip="true">
        </el-table-column>
        <el-table-column prop="unickname" label="作者" width="180">
        </el-table-column>
        <el-table-column prop="ntname" label="小说分类" width="100">
        </el-table-column>
        <el-table-column prop="blurb" label="小说简介" width="350" :show-overflow-tooltip="true">
        </el-table-column>
        <el-table-column label="操作"   text-align="left" >
          <template slot-scope="scope">
            <router-link :to="{path: '/novelChapter',query:{nid:scope.row.nid}}" style="margin-right: 5px">
<!--              <router-link to="/novelChapter" style="margin-right: 5px">-->
              <el-button style="background-color: #ccc;margin-left: 5px">查看章节</el-button>
            </router-link>
            <el-button style="background-color: #eee" @click="novelEdit(scope.row)">编辑</el-button>
            <el-popconfirm
                confirm-button-text='是'
                cancel-button-text='否'
                icon="el-icon-info"
                icon-color="red"
                title="是否删除此小说"
                @confirm="novelDel(scope.row.nid)"
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
    <el-dialog title="小说信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small" :model="form">
        <el-form-item label="小说名">
          <el-input v-model="form.nname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="作者ID">
          <el-input v-model="form.uid" placeholder="请输入作者ID" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="分类ID">
          <el-input v-model="form.ntid"  placeholder="请输入分类ID" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="简介">
          <el-input v-model="form.blurb"  autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="小说路径">
          <el-input v-model="form.chapterUrl"  autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="novelSave">确 定</el-button>
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
      ntname: "",
      ntid: "",
      blurb: "",
      nname: "",
      unickname: "",
      chapterUrl:"",
      loading: true,
      dialogFormVisible: false,
    }
  },
  created() {
    // 请求分页查询数据
    this.load()
  },
  methods:{
    test(row){
      console.log()
    },
    load() {
      this.loading = true
      this.request.get("/novel/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          nname: this.nname,
          ntid: this.ntid,
          uid: this.uid,
          author: this.author,
          noveltype: this.noveltype,
          chapterUrl: this.chapterUrl
        }
      }).then(res => {
        console.log(res)
        this.loading = true
        setTimeout(() => {
          this.loading = false
        }, 270);
        this.tableData = res.data
        this.total = res.total
      })
    },
    reset(){
      this.nname = ""
      this.unickname = ""
      this.load()
      this.handleCurrentChange(this.pageNum = 1)
    },
    novelAdd(){
      this.dialogFormVisible = true
      this.form = {}
    },
    novelSave() {
      this.request.post("/novel", this.form).then(res => {
        if (res) {
          this.$message.success("添加成功")
          this.dialogFormVisible = false
          console.log(this.form)
          this.load()
        } else {
          this.$message.error("添加失败")
          console.log(this.form)
        }
      })
    },
    selectChapter(nid){
      console.log(nid)
      this.loading = true
      this.request.get("/novel/chapter",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          nid: nid,
        }
      }).then(res => {
        console.log(res)
        this.loading = true
        setTimeout(() => {
          this.loading = false
        }, 270);
        this.tableData = res.data
        this.total = res.total
      })
    },
    novelEdit(row){
      this.$data.form=JSON.parse(JSON.stringify(row))
      // this.form = Object.assign({},row)  //将row拷贝到空对象中 解决没点确定数据改变的问题
      // this.form = row
      this.dialogFormVisible = true
      console.log(row)
    },
    novelDel(nid){
      console.log(nid)
      this.request.delete("/novel/" + nid).then(res => {
        if (res) {
          this.$message.success("删除成功")
          console.log(this.nid)
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
      let nids = this.multipleSelection.map(v => v.nid)
      this.request.post("/novel/batchDel" , nids).then(res => {
        if (res) {
          this.$message.success("删除成功")
          console.log(nids)
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
      window.open("http://localhost:8080/user/novelExport")
    }
  }
}
</script>
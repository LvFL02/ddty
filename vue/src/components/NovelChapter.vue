<template>
  <div>
    <div  style="font-size: 40px">
      {{nname}}
    </div>
    <div style="font-size: 20px;margin-top: 10px">
    {{blurb}}
    </div>
    <br>
    <ul>
      <li v-for="(ct,index) in tableData" style="float: left;list-style-type: none;">
        <div style="margin-right: 20px">
          <router-link :to="{path:'/novelContent', query:{cid:ct.cid}}">
          {{ct.cname}}
          </router-link>
        </div>
      </li>
    </ul>

  </div>
</template>

<script>
export default {
  name: 'NovelChapter',
  data(){
      return{
        form: {},
        tableData: [],
        text: [],
        nid:this.$route.query.nid,
        nname:"",
        blurb:"",
        path:""
      }
  },
  created() {
    // 请求分页查询数据
    this.load()

  },
  methods:{
    load(){
      this.loading = true
      let nid = this.$route.query.nid;
      this.request.get("/novelchapter/"+ nid, {
        params: {
          // pageNum: this.pageNum,
          // pageSize: this.pageSize,
          cid: this.cid,
          cname: this.cname,
          nname: this.nname,
          nid: this.$route.query.nid,
          path: this.path
        },
      }).then(res => {
        this.loading = true
        setTimeout(() => {
          this.loading = false
        }, 270);
        console.log(res.data)
        this.tableData = res.data
        this.nname = res.data[0].nname
        this.blurb = res.data[0].blurb
      })
    },
    // read(cid){
    //   console.log(cid)
    //   this.request.get("/novelchapter/read/" + cid, {
    //     params: {
    //       path: this.path
    //     },
    //   }).then(res => {
    //     this.loading = true
    //     setTimeout(() => {
    //       this.loading = false
    //       this.$router.push("/novelContent")
    //     }, 270);
    //     this.text = res.data
    //     console.log(text)
    //   })
    // }
  }
}
</script>
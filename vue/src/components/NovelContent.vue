<template>
  <div style="width: 100%;height: 680px;">
    <div style="height: 100%;width: 80%;background-color:#e9d7c8;margin: 0 auto;border-radius: 15px">
    </div>
  </div>
</template>

<script>
export default {
  name: 'NovelContent',
  data() {
    return {
      cid:"",
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      this.loading = true
      let cid = this.$route.query.cid;
      this.request.get("/novelchapter/content/"+ cid, {
        params: {
          // pageNum: this.pageNum,
          // pageSize: this.pageSize,
          cid: this.$route.query.cid,
          cname: this.cname,
          nname: this.nname,
          path: this.path
        },
      }).then(res => {
        this.loading = true
        setTimeout(() => {
          this.loading = false
        }, 270);
        console.log(res.data)
        this.tableData = res.data
        this.nname = res.data.path
      })
    }
  }
}
</script>
<template>
  <el-card class="box-card">
    <div style="background-color: #6e8cd7;height: 50px">
      <div style="padding: 12px 0px 0px 15px;color: white;text-align: left">
        仪表盘
      </div>
    </div>
    <div class="text item" style="padding: 20px 20px 20px 20px;margin-left: 35px">
      <el-row :gutter="30">
        <el-col :span="8" style="width: 500px;height: 120px">
          <div class="navHeader" style="background-color: #29b6f6">
            <div class="navContent">
              <a style="color: #fff" href="">
                <span class="nav-icon">
                  <i class="el-icon-reading"></i>
                </span>
              </a>
              <div class="count-content">
                发表了
                <span class="counter">{{articleNum}}</span>
                篇文章
              </div>
            </div>

          </div>
        </el-col>
        <el-col :span="8" style="width: 500px;height: 120px">
          <div class="navHeader" style="background-color: #7e57c2">
            <div class="navContent">
              <a style="color: #fff" href="">
                <span class="nav-icon">
                  <i class="el-icon-chat-round"></i>
                </span>
              </a>
              <div class="count-content">
                收到了
                <span class="counter">{{commentNum}}</span>
                条留言
              </div>
            </div>
          </div>
        </el-col>
        <el-col :span="8" style="width: 500px;height: 120px">
          <div class="navHeader" style="background-color: #33b86c">
            <div class="navContent">
              <a style="color: #fff" href="">
                <span class="nav-icon">
                  <i class="el-icon-upload"></i>
                </span>
              </a>
              <div class="count-content">
                上传了
                <span class="counter">6</span>
                个附件
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>

<script>
export default {
  name: 'AdminIndex',
  data () {
    return {
      articleNum: 0,
      commentNum: 0,
      attachNum: 0
    }
  },
  mounted () {
    this.loadArticleNum()
    this.loadCommentNum()
  },
  methods: {
    loadArticleNum () {
      this.$axios.get('/article/allArticle').then(response => {
        this.articleNum = response.data.length
      })
    },
    loadCommentNum () {
      this.$axios.get('/comment/allComment').then(response => {
        this.commentNum = response.data.length
      })
    }
  }
}
</script>

<style scoped>
  .navHeader{
    width: 100%;
    height: 100%;
    border-radius: 5px;
  }

  .navContent{
    width: 430px;
    height: 75px;
    padding: 20px
  }

  .nav-icon{
    border-radius: 100%;
    width: 60px;
    height: 60px;
    float: left;
    background-color: rgba(255,255,255,0.2);
    font-size: xx-large;
    line-height: 60px
  }

  .count-content{
    text-align: right;
    color: #eee;
    font-size: 14px;
    line-height: 1.5
  }

  .counter{
    display: block;
    font-size: 24px;
    font-weight: 600;
    font-family: 'Noto Sans',sans-serif
  }
</style>

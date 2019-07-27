<template>
  <div>
    <div class="footer-social">
      <div class="footer-container clearfix">
        <div class="social-list">
<!--          <a class="social rss" target="blank" href="/feed">RSS</a>-->
        </div>
      </div>
    </div>
    <div class="footer-meta" style="text-align:-webkit-center ">
      <div class="footer-container" style="width: max-content">
        <el-row>
          <el-col :span="8">
            <div class="meta-item meta-copyright">
              <div class="meta-copyright-info">
  <!--              <a href="https://www.kcdou.com.cn" class="info-logo">-->
  <!--                <img src="../../../static/image/ablog.png" style="height: 100px" alt="kcdou">-->
  <!--              </a>-->
                <div class="info-text" style="font-size: 13px;color: #767676">
  <!--                <p>一生有所追求.</p>-->
                  <p>Powered by <a href="https://github.com/otale/tale" style="line-height: 20px;margin: 0" target="_blank" rel="nofollow">kcdou</a>
                  </p>
                  <p>© 2019 <a href="https://github.com/biezhi" style="line-height: 20px;margin: 0" >kcdou</a></p>
                </div>
              </div>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="meta-item meta-posts">
              <h3 class="meta-title" style="font-size: 14px;padding: 0 0 10px 0;text-decoration: none;color: #5f5f5f">最新文章</h3>
              <li v-for="(item,index) in articles" :key="index">
                <a href="#" @click="articleDetail(item.id)">
                  {{item.title}}
                </a>
              </li>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="meta-item meta-comments">
              <h3 class="meta-title" style="font-size: 14px;padding: 0 0 10px 0;text-decoration: none;color: #5f5f5f">最新评论</h3>
              <li v-for="(item,index) in comments" :key="index">
                <a href="https://www.kcdou.com.cn/article/7#comment-3">{{item.commentatorName}}:{{item.content}}</a>
              </li>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Footer',
  data () {
    return {
      articles: [],
      comments: []
    }
  },
  mounted () {
    this.loadArticles()
    this.loadComments()
  },
  methods: {
    loadArticles () {
      this.$axios.post('/article/articles/1/6').then(resp => {
        if (resp && resp.status === 200) {
          this.articles = resp.data.content
        }
      })
    },
    loadComments () {
      this.$axios.get('/comment/list/1/6').then(resp => {
        if (resp && resp.status === 200) {
          this.comments = resp.data.content
        }
      })
    },
    articleDetail (id) {
      this.$router.push({path: '/article/detail', query: {id: id}})
    }
  }
}
</script>

<style scoped>
  .footer-social{border-bottom:1px solid rgba(184,197,214,.2)}.footer-social .footer-container{max-width:940px;margin:0 auto}.footer-social .social-list{padding:20px;text-align:center}.footer-social .social-list a{font-size:13px;padding:0 25px;text-decoration:none;color:#5f5f5f}
  .footer-meta .meta-posts li{font-size:13px;line-height:25px;display:block;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;color:#767676}
  .footer-meta .meta-comments li{font-size:13px;line-height:25px;display:block;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;color:#5f5f5f}
</style>

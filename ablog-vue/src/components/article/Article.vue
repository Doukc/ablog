<template>
  <div>
    <el-card shadow="never" class="box-card" style="width: 700px;text-align: left;margin: 0 auto;margin-top: 90px;border: none">
      <div slot="header" class="clearfix">
        <span style="color: #1b1f23;font-size: 25px;font-family: -apple-system,SF UI Text,Arial,PingFang SC,Hiragino Sans GB,Microsoft YaHei,WenQuanYi Micro Hei,sans-serif">{{article.title}}</span>
        <br/>
        <span style="font-size: 10px">
          发布于{{new Date(article.createTime).getTime() | formatDate}}
          <span v-if="article.category != null">/{{article.category}}</span>
        </span>
        <br/><br/>
        <el-tag style="border-radius:20px;border: none;margin-right: 10px" size="small" :color="'rgb(' + Math.floor(Math.random() * 50 + 180) + ',' + Math.floor(Math.random() * 50 + 180) + ',' + Math.floor(Math.random() * 50 + 180) + ')'" effect="dark" v-for="(item,key) in article.categoryList" :key="key" v-if="item.categoryType === 1">#{{item.categoryName}}</el-tag>
      </div>
      <div class="markdown-body" >
        <div class="content" v-html="article.html"></div>
      </div>
      <el-divider></el-divider>
      <p style="font-size: 10px">本站文章除注明转载/出处外，均为本站原创或翻译，转载前请务必署名</p>
      <p style="font-size: 10px">
        最后编辑时间为
        <span v-if="article.modifyTime != null">
          {{new Date(article.modifyTime).getTime() | formatDate}}
        </span>
        <span v-else>
          {{new Date(article.createTime).getTime() | formatDate}}
        </span>
      </p>
      <br/>
    </el-card>
  </div>
</template>

<script>
import {formatDate} from '../../../static/js/date.js'
export default {
  name: 'ArticleDetail',
  data () {
    return {
      id: this.$route.query.id,
      article: []
    }
  },
  filters: {
    formatDate (time) {
      var date = new Date(time)
      return formatDate(date, 'yyyy-MM-dd hh:mm')
    }
  },
  mounted () {
    this.$axios.post('/article', {
      id: this.id
    }).then(resp => {
      this.article = resp.data
    })
  }
}
</script>

<style>
  @import '../../../static/css/mavon-markdown.css';
</style>

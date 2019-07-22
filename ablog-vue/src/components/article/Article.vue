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
        <el-tag style="border-radius:20px;border: none;margin-right: 10px" size="small" :color="'rgb(' + Math.floor(Math.random() * 50 + 180) + ',' + Math.floor(Math.random() * 50 + 180) + ',' + Math.floor(Math.random() * 50 + 180) + ')'" effect="dark" v-for="(item,key) in article.categoryList" :key="key" v-if="item.categoryType === 1">
          <a :href="'/categories?id=' + item.id + '&categoryName=' + item.categoryName + '&categoryType=' + item.categoryType">#{{item.categoryName}}</a>
        </el-tag>
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
    <div v-if="article.hasComments === 100" style="background-color: #f7f7f7;padding-top: 70px;padding-bottom: 70px;text-align: -webkit-center">
      <el-card class="box-card" style="width: 680px;height: 184px;padding-bottom: 70px">
        <div class="text-item">
          <el-form>
            <el-form-item>
              <el-input v-model="comment.commentatorName" style="border: none;width: 200px;border-bottom: dashed 1px #ddd;" placeholder="姓名(*)"></el-input>
              <el-input v-model="comment.commentatorEmail" style="border: none;width: 200px;border-bottom: dashed 1px #ddd;" placeholder="邮箱(*)"></el-input>
              <el-input v-model="comment.commentatorWebsite" style="border: none;width: 200px;border-bottom: dashed 1px #ddd;" placeholder="网址(http://)"></el-input>
            </el-form-item>
            <el-form-item>
              <el-input
                type="textarea"
                placeholder="基佬，请开始你的表演"
                v-model="comment.content"
                maxlength="300"
                show-word-limit
              ></el-input>
              <el-button style="margin-left: 580px;background-color: #f7f7f7" round size="mini" @click="onSubmit">提交</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-card>
      <div style="height: 30px"></div>
      <el-card class="box-card" style="width: 680px;margin-bottom: 10px" v-for="(item,key) in comments" v-if="comments.length !== 0 && item.state !== 0" :key="key">
        <div class="text-item" v-if="item.state === 1">
          <div style="display: flex">
            <img style="height: 38px;width: 38px;padding: 1px 1px 1px 1px;border-radius:50%" src="/static/image/comment_avatar.jpg"/>
            <span style="padding-top: 9px;padding-left: 10px;font-size: 13px">{{item.commentatorName}}</span>
          </div>
          <div style="height: 5px"></div>
          <div style="display: flex;text-align: left">
            <span style="font-size: 14px">{{item.content}}</span>
          </div>
          <div style="height: 15px"></div>
          <div style="display: flex">
            <span style="font-size: 10px">{{new Date(item.time).getTime() | formatDate}}</span>
          </div>
        </div>
        <div class="text-item" v-if="item.state === 2">
          <div style="display: flex">
            <img style="height: 38px;width: 38px;padding: 1px 1px 1px 1px;border-radius:50%" src="/static/image/comment_avatar.jpg"/>
            <span style="padding-top: 9px;padding-left: 10px;font-size: 13px;color: red">{{item.commentatorName}}</span>
          </div>
          <div style="height: 5px"></div>
          <div style="display: flex;text-align: left">
            <span style="font-size: 14px">@{{returnParent(item.parentId)}}   {{item.content}}</span>
          </div>
          <div style="height: 15px"></div>
          <div style="display: flex">
            <span style="font-size: 10px">{{new Date(item.time).getTime() | formatDate}}</span>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import {formatDate} from '../../../static/js/date.js'
export default {
  name: 'ArticleDetail',
  data () {
    return {
      id: this.$route.query.id,
      article: [],
      comment: {
        commentatorName: '',
        commentatorEmail: '',
        commentatorWebsite: '',
        content: ''
      },
      comments: []
    }
  },
  filters: {
    formatDate (time) {
      var date = new Date(time)
      return formatDate(date, 'yyyy-MM-dd hh:mm')
    }
  },
  mounted () {
    this.loadArticle()
    this.loadComments()
  },
  methods: {
    loadArticle () {
      this.$axios.post('/article', {
        id: this.id
      }).then(resp => {
        this.article = resp.data
      })
    },
    loadComments () {
      this.$axios.get('/comment/comments/' + this.id).then(response => {
        this.comments = response.data
      })
    },
    onSubmit () {
      this.$axios.post('/comment/new/' + this.id, {
        commentatorName: this.comment.commentatorName,
        commentatorEmail: this.comment.commentatorEmail,
        commentatorWebsite: this.comment.commentatorWebsite,
        content: this.comment.content
      }).then(resp => {
        if (resp.status === 200) {
          this.$message({
            message: '评论成功',
            type: 'success'
          })
          this.loadComments()
        }
      })
    },
    returnParent (id) {
      for (let i = 0; i < this.comments.length; i++) {
        if (id === this.comments[i].id) {
          return this.comments[i].commentatorName
        }
      }
    }
  }
}
</script>

<style>
  @import '../../../static/css/mavon-markdown.css';
  .el-input__inner {
    border: none;
  }

  .el-textarea__inner {
    border: none;
    height: 130px;
  }

  a{
    text-decoration: none;
    color: white;
    font-family: Arial;
  }
</style>

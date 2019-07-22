<template>
  <div style="height:auto;background-color: #f7f7f7;display: flow-root">
    <div style="width: 900px;height:auto;padding-top: 50px;margin: 0 auto;margin-top:10px;margin-bottom:10px;text-align: left">
      <div>
        <span style="color: red;font-size: 14px">#</span>
        <span style="font-size: 14px" v-if="categoryType == 0">分类：</span>
        <span style="font-size: 14px" v-if="categoryType == 1">标签：</span>
        <span style="font-size: 14px">{{categoryName}}</span>
      </div>
      <div style="margin-top: 30px">
        <el-col :span="12" v-for="(item,key) in articles" :key="key">
          <el-card class="box-card" style="margin: 10px 10px 10px 10px">
            <div class="text item">
              <div style="height: 65px">
                <a href="#">
                  <span style="font-size: 14px" @click="articleDetail(item.id)">{{item.title}}</span>
                </a>
              </div>
              <div>
                <span style="font-size: 12px">发布于 {{new Date(item.createTime).getTime() | formatDate}}</span>
                <span style="margin-left: 230px">
                  <i class="el-icon-s-management" ></i>
                </span>
              </div>
            </div>
          </el-card>
        </el-col>
      </div>
    </div>
  </div>
</template>

<script>
import {formatDate} from '../../../static/js/date.js'
export default {
  name: 'Categories',
  data () {
    return {
      id: this.$route.query.id,
      categoryName: this.$route.query.categoryName,
      categoryType: this.$route.query.categoryType,
      articles: []
    }
  },
  filters: {
    formatDate (time) {
      var date = new Date(time)
      return formatDate(date, 'yyyy-MM-dd hh:mm')
    }
  },
  mounted () {
    this.loadArticlesByCid()
  },
  methods: {
    loadArticlesByCid () {
      this.$axios.post('/article/getArticlesByCid', {
        id: this.id
      }).then(resp => {
        this.articles = resp.data
      })
    },
    articleDetail (id) {
      this.$router.push({path: '/article/detail', query: {id: id}})
    }
  }
}
</script>

<style scoped>
  a{
    text-decoration: none;
    color: #535353;
    font-family: Arial;
  }
</style>

<template>
  <div class="box animated fadeIn" style="padding-top: 50px;padding-bottom: 100px;background-color: #f7f7f7;">
    <div style="width: 810px;margin: 0 auto;">
      <el-row style="display: flow-root;" type="flex" justify="center" align="center">
        <el-col :span="8" v-for="(item,index) in article" v-if="index<3" :key="index">
          <el-card shadow="hover" :body-style="{ padding: '0px' }">
            <el-tooltip placement="bottom">
              <div slot="content">多行信息<br/>第二行信息</div>
              <el-button style="width: 270px;height: 250px" @click="articleDetail(item.id)">
                <el-image
                  style="width: 270px; height: 250px"
                  :src="item.cover"
                  :fit="fit"></el-image>
              </el-button>
            </el-tooltip>
            <div align="left" style="padding: 14px;">
              <a href="#">
                <span style="font-family: 'Arial'" @click="articleDetail(item.id)">{{item.title}}</span>
              </a>
              <div align="right" class="bottom clearfix">
                <a href="#">
                  <span style="font-size: 5px" v-for="(i,key) in item.categoryList" :key="key" v-if="i.categoryType === 0">{{i.categoryName}}</span>
                </a>
                <i class="el-icon-paperclip" style="font-size: 22px;"></i>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <el-row style="display: flow-root;" type="flex" justify="center" align="center">
        <el-col :span="8" v-for="(item,index) in article" v-if="index>=3 && index<6" :key="index">
          <el-card shadow="hover" :body-style="{ padding: '0px' }">
            <el-tooltip placement="bottom">
              <div slot="content">多行信息<br/>第二行信息</div>
              <el-button style="width: 270px;height: 250px" @click="articleDetail(item.id)">
                <el-image
                  style="width: 270px; height: 250px"
                  :src="item.cover"
                  :fit="fit"></el-image>
              </el-button>
            </el-tooltip>
            <div align="left" style="padding: 14px;">
              <a href="#">
                <span style="font-family: 'Arial'">{{item.title}}</span>
              </a>
              <div align="right" class="bottom clearfix">
                <a href="#">
                  <span style="font-size: 5px">默认分类</span>
                </a>
                <i class="el-icon-paperclip" style="font-size: 22px;"></i>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <el-row style="display: flow-root;" type="flex" justify="center" align="center">
        <el-col :span="8" v-for="(item,index) in article" v-if="index>=6 && index<9" :key="index">
          <el-card shadow="hover" :body-style="{ padding: '0px' }">
            <el-tooltip placement="bottom">
              <div slot="content">多行信息<br/>第二行信息</div>
              <el-button style="width: 270px;height: 250px" @click="articleDetail(item.id)">
                <el-image
                  style="width: 270px; height: 250px"
                  :src="item.cover"
                  :fit="fit"></el-image>
              </el-button>
            </el-tooltip>
            <div align="left" style="padding: 14px;">
              <a href="#">
                <span style="font-family: 'Arial'">{{item.title}}</span>
              </a>
              <div align="right" class="bottom clearfix">
                <a href="#">
                  <span style="font-size: 5px">默认分类</span>
                </a>
                <i class="el-icon-paperclip" style="font-size: 22px;"></i>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        style="padding: 20px"
        :hide-on-single-page="value"
        small="true"
        layout="prev, pager, next"
        page-size="9"
        :total="totalRecord">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Index',
  data () {
    return {
      pager: [],
      article: [],
      totalRecord: '',
      currentPage: 1
    }
  },
  mounted: function () {
    this.loadArticles()
  },
  methods: {
    loadArticles () {
      this.$axios.post('/article/articles/' + this.currentPage + '/9').then(resp => {
        if (resp && resp.status === 200) {
          this.pager = resp.data
          this.totalRecord = resp.data.totalElements
          this.article = resp.data.content
        }
      })
    },
    handleCurrentChange (currentPage) {
      this.currentPage = currentPage
      this.loadArticles()
    },
    articleDetail (id) {
      this.$router.push({path: '/article/detail', query: {id: id}})
    }
  }
}
</script>

<style>
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .el-col{
    width: 270px;
    height: 350px;
    padding: 15px 15px 15px 15px;
  }

  .el-button{
    padding: 0px 0px 0px 0px;
  }

  a{
    text-decoration: none;
    color: #535353;
    font-family: Arial;
  }
</style>

<template>
  <div onload="loadArticles">
    <div style="padding-top: 50px;padding-bottom: 100px;background-color: #f7f7f7;">
      <div style="width: 1000px;margin: 0 auto;">
        <el-row type="flex" justify="center" align="center" style="margin-bottom: 30px;display: table;padding-left: 50px">
          <template v-for="(item,index) in article">
            <el-col :span="8"  v-if="index<3" :key="index" style="width: 300px;height: 415px">
              <el-card class="box animated fadeIn" shadow="always" :body-style="{ padding: '0px' }" style="border-radius:15px;">
                <el-tooltip placement="bottom">
                  <div slot="content" style="width: 220px">
                    {{item.doc.slice(0,33)}} ...
                  </div>
                  <el-button style="width: 100%;height: 250px" @click="articleDetail(item.id)">
                    <el-image
                      style="width: 100%; height: 300px"
                      :src="item.cover"
                      :fit="fit"></el-image>
                  </el-button>
                </el-tooltip>
                <div align="left" style="padding: 14px;">
                  <div style="height: 65px">
                    <a href="#">
                      <span style="font-family: 'Arial'" @click="articleDetail(item.id)">{{item.title}}</span>
                    </a>
                  </div>
                  <div align="right" class="bottom clearfix">
                    <i>
                      <template v-for="(i,key) in item.categoryList">
                        <a :href="'/categories?id=' + i.id + '&categoryName=' + i.categoryName + '&categoryType=' + i.categoryType"  :key="key" v-if="i.categoryType === 0">
                          <span style="font-size: 5px" >{{i.categoryName}}</span>
                        </a>
                      </template>
                    </i>
                    <i class="el-icon-paperclip" style="font-size: 22px;"></i>
                  </div>
                </div>
              </el-card>
            </el-col>
          </template>
        </el-row>
        <el-row  type="flex" justify="center" align="center" style="margin-bottom: 30px;display: table;padding-left: 50px">
          <template v-for="(item,index) in article">
            <el-col :span="8"  v-if="index>=3 && index<6" :key="index" style="width: 300px;height: 415px">
              <el-card shadow="hover" :body-style="{ padding: '0px' }" style="border-radius:10px;">
                <el-tooltip placement="bottom">
                  <div slot="content" style="width: 220px">
                    {{item.doc.slice(0,33)}} ...
                  </div>
                  <el-button style="width: 100%;height: 250px" @click="articleDetail(item.id)">
                    <el-image
                      style="width: 100%; height: 300px"
                      :src="item.cover"
                      :fit="fit"></el-image>
                  </el-button>
                </el-tooltip>
                <div align="left" style="padding: 14px;">
                  <div style="height: 65px">
                    <a href="#">
                      <span style="font-family: 'Arial'" @click="articleDetail(item.id)">{{item.title}}</span>
                    </a>
                  </div>
                  <div align="right" class="bottom clearfix">
                    <i>
                      <template v-for="(i,key) in item.categoryList">
                        <a :href="'/categories?id=' + i.id + '&categoryName=' + i.categoryName + '&categoryType=' + i.categoryType"  :key="key" v-if="i.categoryType === 0">
                          <span style="font-size: 5px" >{{i.categoryName}}</span>
                        </a>
                      </template>
                    </i>
                    <i class="el-icon-paperclip" style="font-size: 22px;"></i>
                  </div>
                </div>
              </el-card>
            </el-col>
          </template>
        </el-row>
        <el-row type="flex" justify="center" align="center" style="margin-bottom: 30px;display: table;padding-left: 50px">
          <template v-for="(item,index) in article">
            <el-col :span="8"  v-if="index>=6 && index<9" :key="index" style="width: 300px;height: 415px">
              <el-card shadow="hover" :body-style="{ padding: '0px' }" style="border-radius:10px;">
                <el-tooltip placement="bottom">
                  <div slot="content" style="width: 220px">
                    {{item.doc.slice(0,33)}} ...
                  </div>
                  <el-button style="width: 100%;height: 250px" @click="articleDetail(item.id)">
                    <el-image
                      style="width: 100%; height: 300px"
                      :src="item.cover"
                      :fit="fit"></el-image>
                  </el-button>
                </el-tooltip>
                <div align="left" style="padding: 14px;">
                  <div style="height: 65px">
                    <a href="#">
                      <span style="font-family: 'Arial'" @click="articleDetail(item.id)">{{item.title}}</span>
                    </a>
                  </div>
                  <div align="right" class="bottom clearfix">
                    <i>
                      <template v-for="(i,key) in item.categoryList">
                        <a :href="'/categories?id=' + i.id + '&categoryName=' + i.categoryName + '&categoryType=' + i.categoryType"  :key="key" v-if="i.categoryType === 0">
                          <span style="font-size: 5px" >{{i.categoryName}}</span>
                        </a>
                      </template>
                    </i>
                    <i class="el-icon-paperclip" style="font-size: 22px;"></i>
                  </div>
                </div>
              </el-card>
            </el-col>
          </template>
        </el-row>
        <el-pagination
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          style="padding: 20px"
          :hide-on-single-page="value"
          :small="true"
          layout="prev, pager, next"
          :page-size="9"
          :total="totalRecord">
        </el-pagination>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from '../common/Footer'
export default {
  name: 'Index',
  components: {Footer},
  data () {
    return {
      pager: [],
      article: [],
      totalRecord: 0,
      currentPage: 1,
      value: '',
      fit: ''
    }
  },
  component: {
    Footer
  },
  mounted: function () {
    this.loadArticles()
  },
  watch: {
    $route () {
      console.log('来了老弟')
      this.loadArticles()
    }
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
      this.$router.push({path: '/ui/article/detail', query: {id: id}})
    }
  }
}
</script>

<style scoped>
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

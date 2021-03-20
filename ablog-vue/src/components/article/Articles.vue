<template>
  <el-card class="box-card">
    <div style="background-color: #6e8cd7;height: 50px">
      <div style="padding: 12px 0px 0px 15px;color: white;text-align: left">
        文章管理
      </div>
    </div>
    <br/>
    <el-form ref="form"  label-width="10px" label-position="left">
      <el-form-item style="width: auto;text-align: left">
        <el-col>
          <el-input v-model="articleTitle"  size="small" placeholder="文章名称" style="width: 150px"></el-input>
          <el-select size="small" style="width: 150px" v-model="categoryValue" placeholder="所属分类">
            <el-option
              v-for="item in categoryOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
          <el-select size="small" style="width: 150px" v-model="articleStateValue" placeholder="发布状态">
            <el-option
              v-for="item in articleState"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
          <el-button icon="el-icon-search" circle @click="searchArticle"></el-button>
        </el-col>
      </el-form-item>
      <el-table
        :data="article"
        style="width: 100%">
        <el-table-column
          label="文章标题"
          width="500">
          <template slot-scope="scope">
            <span>{{ scope.row.title }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="发布时间"
          width="180">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">{{new Date(scope.row.createTime).getTime() | formatDate}}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="浏览量"
          width="180">
          <template>
            <span>0</span>
          </template>
        </el-table-column>
        <el-table-column
          label="所属分类"
          width="180">
          <template slot-scope="scope" >
            <template v-for="(item,key) in scope.row.categoryList">
              <span :key="key" v-if="item.categoryType === 0">{{item.categoryName}}</span>
            </template>
          </template>
        </el-table-column>
        <el-table-column
          label="发布状态"
          width="180">
          <template slot-scope="scope">
            <span v-if="scope.row.state === 0">
              <el-tag
                type="success"
                effect="dark">
                已发布
              </el-tag>
            </span>
            <span v-else-if="scope.row.state === 1">
              <el-tag
                type="info"
                effect="dark">
                草稿
              </el-tag>
            </span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="small"
              @click="handleEdit(scope.$index, scope.row)">
              <i class="el-icon-edit"></i>
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">
              <i class="el-icon-delete"></i>
              删除
            </el-button>
            <el-button
              size="small"
              type="warning"
              @click="handleEdit(scope.$index, scope.row)">
              <i class="el-icon-s-promotion"></i>
              预览
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        style="text-align: right"
        background
        layout="prev, pager, next"
        :page-size="9"
        :current-page="currentPage"
        :total="totalRecord">
      </el-pagination>
    </el-form>
  </el-card>
</template>

<script>
import {formatDate} from '../../../static/js/date.js'
export default {
  name: 'Articles',
  data () {
    return {
      pager: [],
      article: [],
      totalRecord: 0,
      currentPage: 1,
      articleTitle: '',
      categoryOptions: [],
      categoryValue: 0,
      articleState: [
        {
          value: 100,
          label: '发布状态'
        },
        {
          value: 1,
          label: '草稿'
        },
        {
          value: 0,
          label: '已发布'
        }
      ],
      articleStateValue: 100
    }
  },
  mounted () {
    this.loadArticles()
    this.loadCategorys()
  },
  filters: {
    formatDate (time) {
      let date = new Date(time)
      return formatDate(date, 'yyyy-MM-dd hh:mm')
    }
  },
  methods: {
    loadArticles () {
      let url = '/article/list/' + this.currentPage + '/9/' + this.categoryValue
      let data = {
        title: this.articleTitle,
        state: this.articleStateValue
      }
      this.$axios.post(url, data).then(resp => {
        if (resp && resp.status === 200) {
          this.pager = resp.data
          this.totalRecord = resp.data.totalElements
          this.article = resp.data.content
        }
      })
    },
    loadCategorys () {
      this.$axios.post('/category/categoryList', {
        categoryType: '0'
      }).then(resp => {
        if (resp && resp.status === 200) {
          this.categoryOptions.push({value: 0, label: '全部分类'})
          for (var i = 0; i < resp.data.length; i++) {
            let obj = {}
            obj.value = resp.data[i].id
            obj.label = resp.data[i].categoryName
            this.categoryOptions.push(obj)
          }
        }
      })
    },
    searchArticle () {
      this.loadArticles()
    },
    handleEdit (index, row) {
      this.$router.push({path: '/ui/admin/article/modify', query: {id: row.id}})
    },
    handleDelete (index, row) {
      this.$axios.get('/article/delete/' + row.id).then(resp => {
        if (resp.status === 200) {
          this.$message({
            message: '删除成功',
            type: 'success'
          })
          this.loadArticles()
        }
      })
    }
  }
}
</script>

<style scoped>

</style>

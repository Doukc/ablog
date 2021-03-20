<template>
  <el-card class="box-card">
    <el-table
      :data="comment"
      style="width: 100%">
      <el-table-column
        label="评论内容"
        width="330">
        <template slot-scope="scope">
          <span>{{ scope.row.content }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="评论人"
        width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{scope.row.commentatorName}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="评论时间"
        width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span>{{new Date(scope.row.time).getTime() | formatDate}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="评论邮箱"
        width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{scope.row.commentatorEmail}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="评论人网址"
        width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{scope.row.commentatorWebsite}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="评论人ip"
        width="150">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{scope.row.commentatorIP}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="评论状态"
        width="180">
        <template slot-scope="scope">
          <span>
            <el-tag
              type="info"
              effect="dark" v-if="scope.row.state === 0">
              待审核
            </el-tag>
            <el-tag
              type="success"
              effect="dark" v-if="scope.row.state === 2">
              后台回复
            </el-tag>
            <el-tag
              type="success"
              effect="dark" v-if="scope.row.state === 1 || scope.row.state === 2">
              审核通过
            </el-tag>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="220">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="small"
            @click="review(scope.$index, scope.row)" v-if="scope.row.state === 0">
            <i class="el-icon-edit"></i>
            审核通过
          </el-button>
          <el-button
            type="primary"
            size="small"
            @click="handleReply(scope.$index, scope.row)" v-if="scope.row.state === 1">
            <i class="el-icon-edit"></i>
            回复
          </el-button>
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)" >
            <i class="el-icon-delete"></i>
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @current-change="handleCurrentChange"
      style="text-align: right"
      background
      layout="prev, pager, next"
      :page-size="9"
      :current-page="currentPage"
      :total="totalRecord">
    </el-pagination>
  </el-card>
</template>

<script>
import {formatDate} from '../../../static/js/date.js'
export default {
  name: 'Comments',
  data () {
    return {
      comment: [],
      pager: [],
      totalRecord: 0,
      currentPage: 1
    }
  },
  filters: {
    formatDate (time) {
      let date = new Date(time)
      return formatDate(date, 'yyyy-MM-dd hh:mm')
    }
  },
  mounted () {
    this.loadComments()
  },
  methods: {
    loadComments () {
      let url = '/comment/list/' + this.currentPage + '/9'
      this.$axios.get(url).then(resp => {
        if (resp && resp.status === 200) {
          this.pager = resp.data
          this.totalRecord = resp.data.totalElements
          this.comment = resp.data.content
        }
      })
    },
    review (index, row) {
      this.$axios.get('/comment/review/' + row.id).then(resp => {
        if (resp.status === 200) {
          this.$message({
            message: '审核通过',
            type: 'success'
          })
          this.loadComments()
        }
      })
    },
    handleReply (index, row) {
      this.$prompt('请输入你要回复的内容', '回复评论', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        this.$axios.get('/comment/reply/' + row.id + '/' + value).then(resp => {
          if (resp.status === 200) {
            this.$message({
              message: '回复成功',
              type: 'success'
            })
            this.loadComments()
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        })
      })
    },
    handleDelete (index, row) {
      this.$axios.get('/comment/delete/' + row.id).then(resp => {
        if (resp.status === 200) {
          this.$message({
            message: '删除成功',
            type: 'success'
          })
          this.loadComments()
        }
      })
    },
    handleCurrentChange (currentPage) {
      this.currentPage = currentPage
      this.loadComments()
    }
  }
}
</script>

<style scoped>

</style>

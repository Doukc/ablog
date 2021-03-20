<template>
  <el-card class="box-card">
    <el-table
      :data="logs"
      style="width: 100%">
      <el-table-column
        label="模块"
        width="300">
        <template slot-scope="scope">
          <span>{{ scope.row.module }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        width="300">
        <template slot-scope="scope">
          <span>{{ scope.row.action }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="ip"
        width="300">
        <template slot-scope="scope">
          <span>{{ scope.row.ip }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="时间"
        width="300">
        <template slot-scope="scope">
          <span>{{new Date(scope.row.time).getTime() | formatDate}}</span>
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
  name: 'OperateLog',
  data () {
    return {
      logs: [],
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
    this.loadLogs()
  },
  methods: {
    loadLogs () {
      let url = '/log/list/' + this.currentPage + '/9'
      this.$axios.get(url).then(resp => {
        if (resp && resp.status === 200) {
          this.pager = resp.data
          this.totalRecord = resp.data.totalElements
          this.logs = resp.data.content
        }
      })
    },
    handleCurrentChange (currentPage) {
      this.currentPage = currentPage
      this.loadLogs()
    }
  }
}
</script>

<style scoped>

</style>

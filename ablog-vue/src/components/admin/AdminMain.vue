<template>
  <div style="background-color: #f7f7f7;height: 100%">
    <el-container>
      <el-aside width="auto">
        <AdminNav />
      </el-aside>
      <el-container >
        <el-header>
        </el-header>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import AdminNav from './AdminNav'
export default {
  name: 'AdminIndex',
  components: {
    AdminNav
  }
}
</script>

<style scoped>
  .el-container {
    height: 100%;
  }

  .el-header {
    background-color: white;
    line-height: 60px;
    box-shadow:0px 5px 5px rgba(0,0,0,0.1);
  }

  .el-aside {
    display: inline-flex;
    left: 0;
    top: 70px;
    bottom: 0;
    background-color: #2f353f;
    height: -webkit-fill-available;
  }

</style>

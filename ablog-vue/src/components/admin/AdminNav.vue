<template>
  <div>
    <el-button @click="changeCollapse" v-model="isCollapse" style="background-color: #2f353f;border-style:none;" size="mini" type="primary" icon="el-icon-more"></el-button>
    <div align="left">
      <el-menu
        :default-active=activerouter
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        :collapse="isCollapse"
        background-color="#2f353f"
        text-color="#fff"
        active-text-color="#ffd04b"
        :router="true"
      >
        <el-menu-item index="/ui/admin/index">
          <i class="el-icon-odometer"></i>
          <span slot="title">仪表盘</span>
        </el-menu-item>
        <el-menu-item index="/ui/admin/article/new">
          <i class="el-icon-edit"></i>
          <span slot="title">发布文章</span>
        </el-menu-item>
        <el-menu-item index="/ui/admin/article/articles">
          <i class="el-icon-document"></i>
          <span slot="title">文章管理</span>
        </el-menu-item>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>安全管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/ui/admin/security/log">
              <i class="el-icon-chat-dot-round"></i>
              <span>操作日志</span>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>其他管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/ui/admin/comment/comments">
              <i class="el-icon-chat-round"></i>
              <span>评论管理</span>
            </el-menu-item>
            <el-menu-item index="/ui/admin/category">
              <i class="el-icon-price-tag"></i>
              <span>分类/标签</span>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AdminNav',
  data () {
    return {
      isCollapse: false,
      activerouter: ''
    }
  },
  mounted () {
    this.activerouter = window.location.pathname
  },
  methods: {
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    },
    changeCollapse () {
      if (this.isCollapse === true) {
        this.isCollapse = false
      } else if (this.isCollapse === false) {
        this.isCollapse = true
      }
    }
  }
}
</script>

<style scoped>

  .el-menu-vertical-demo{
    border-style:none;
  }

  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 240px;
    height: 100%;
    border-style:none;
  }

  .el-submenu .el-menu-item {
    min-width: 0px;
  }

  .el-menu-item.is-active {
    color: rgb(255,255,255) !important;
    background-color: #272c35 !important;
  }

</style>

<style>

</style>

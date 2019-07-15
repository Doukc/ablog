<template>
  <div>
    <el-button @click="changeCollapse" v-model="isCollapse" style="background-color: #2f353f;border-style:none;" size="mini" type="primary" icon="el-icon-more"></el-button>
    <div align="left">
      <el-menu
        default-active="1-4-1"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        :collapse="isCollapse"
        background-color="#2f353f"
        text-color="#fff"
        active-text-color="#ffd04b"
        :router="true"
      >
        <el-menu-item index="/admin/article/new">
          <i class="el-icon-edit"></i>
          <span slot="title">发布文章</span>
        </el-menu-item>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>其他管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/admin/category">
              <i class="el-icon-discount"></i>
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
      isCollapse: false
    }
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
    height: auto;
    border-style:none;
  }

  .el-submenu .el-menu-item {
    min-width: 0px;

  }
</style>

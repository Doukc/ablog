<template>
  <el-card class="box-card">
    <div style="background-color: #6e8cd7;height: 50px">
      <div style="padding: 12px 0px 0px 15px;color: white;text-align: left">
        分类/标签管理
      </div>
    </div>
    <br/>
    <div>
      <el-row :gutter="20" style="padding-left: 30px;padding-right: 30px">
        <el-col :span="12">
          <el-card class="box-card" style=" border: none">
            <div style="color: white;text-align: left" slot="header" class="clearfix">
              <span style="margin-left: 15px;font-size: 14px">分类列表</span>
            </div>
            <div class="text item" style="text-align: left">
              <span style="padding-right: 10px" v-for="(item,index) in categories" v-bind:key="index">
                <el-tag v-if="item[0] === 1 && item[2] === 0" :color="'rgb(' + Math.floor(Math.random() * 50 + 180) + ',' + Math.floor(Math.random() * 50 + 180) + ',' + Math.floor(Math.random() * 50 + 180) + ')'" style="color: white;border: none">
                  <strong>
                    {{item[1]}}({{item[3]}})
                  </strong>
                </el-tag>
                <el-dropdown v-else-if="item[2] === 0" trigger="click">
                  <span class="el-dropdown-link">
                    <el-tag :color="'rgb(' + Math.floor(Math.random() * 50 + 180) + ',' + Math.floor(Math.random() * 50 + 180) + ',' + Math.floor(Math.random() * 50 + 180) + ')'" style="color: white;border: none">
                      <strong>
                        {{item[1]}}({{item[3]}})
                        <i class="el-icon-arrow-down el-icon--right" style="padding-left: 0px"></i>
                      </strong>
                    </el-tag>
                  </span>
                  <el-dropdown-menu  slot="dropdown">
                    <el-dropdown-item @click.native="modifyCategory(item[0],item[1])">修改</el-dropdown-item>
                    <el-dropdown-item @click.native="deleteCategory(item[0])">删除</el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
              </span>

            </div>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card class="box-card" style=" border: none">
            <div style="color: white;text-align: left" slot="header" class="clearfix">
              <span style="margin-left: 15px;font-size: 14px">标签列表</span>
            </div>
            <div class="text item" style="text-align: left">
              <span style="padding-right: 10px" v-for="(item,index) in categories" v-bind:key="index">
                <el-tag v-if="item[2] === 1" :color="'rgb(' + Math.floor(Math.random() * 50 + 180) + ',' + Math.floor(Math.random() * 50 + 180) + ',' + Math.floor(Math.random() * 50 + 180) + ')'" style="color: white;border: none">
                  <strong>
                    {{item[1]}}({{item[3]}})
                  </strong>
                </el-tag>
              </span>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <br/><br/>
    <div style="padding-left: 30px;padding-right: 30px">
      <el-card class="box-card">
        <div class="text item" style="text-align: left">
          <el-input v-model="categoryName" placeholder="请输入分类名称" style="width: 300px"></el-input>
          <el-button type="primary" @click="saveCategory">保存分类</el-button>
          <el-input v-model="categoryTagName" placeholder="请输入标签名称" style="width: 300px;padding-left: 100px"></el-input>
          <el-button type="primary" @click="saveCategoryTag">保存标签</el-button>
        </div>
      </el-card>
    </div>
  </el-card>
</template>

<script>
export default {
  name: 'Category',
  data () {
    return {
      categories: [],
      categoryName: '',
      categoryTagName: '',
      category: []
    }
  },
  mounted () {
    this.loadCategories()
  },
  methods: {
    loadCategories () {
      this.$axios.post('/category/categories').then(resp => {
        if (resp && resp.status === 200) {
          this.categories = resp.data
        }
      })
    },
    saveCategory () {
      this.$axios.post('/category/save', {
        id: this.category.id,
        categoryName: this.categoryName,
        categoryType: 0
      }).then(resp => {
        if (resp && resp.status === 200) {
          this.$message({
            message: '保存成功',
            type: 'success'
          })
          this.categoryName = ''
          this.loadCategories()
        }
      })
    },
    saveCategoryTag () {
      this.$axios.post('/category/save', {
        id: this.category.id,
        categoryName: this.categoryTagName,
        categoryType: 1
      }).then(resp => {
        if (resp && resp.status === 200) {
          this.$message({
            message: '保存成功',
            type: 'success'
          })
          this.categoryTagName = ''
          this.loadCategories()
        }
      })
    },
    modifyCategory (cid, categoryName) {
      this.category.id = cid
      this.categoryName = categoryName
    },
    deleteCategory (cid) {
      this.$axios.post('/category/delete', {
        id: cid
      }).then(resp => {
        if (resp && resp.status === 200) {
          this.$message({
            message: '删除成功',
            type: 'success'
          })
          this.loadCategories()
        }
      })
    }
  }
}
</script>

<style>
  .el-card__header{
    background-color: #7e57c2;
    padding: 5px 0px 5px 0px;
    height: 40px;
  }
</style>

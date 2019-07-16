<template>
  <el-card class="box-card">
    <div style="background-color: #6e8cd7;height: 50px">
      <div style="padding: 12px 0px 0px 15px;color: white;text-align: left">
        发布文章
      </div>
    </div>
    <br/>
    <el-form ref="form" :model="form" label-width="10px" label-position="left">
      <el-form-item style="width: auto">
        <el-col :span="12">
          <el-input v-model="form.title" placeholder="请输入文章标题" style="width: 720px"></el-input>
        </el-col>
        <el-col :span="12"></el-col>
      </el-form-item>
      <el-form-item style="width: auto">
        <el-col :span="12">
          <el-select
            v-model="tagValue"
            multiple
            filterable
            remote
            reserve-keyword
            placeholder="请输入文章标签"
            :remote-method="selectTagCategories"
            :loading="tagLoading"
            style="width: 720px">
            <el-option
              v-for="item in tagOptions"
              :key="item.value"
              :label="item.codeValue"
              :value="item.value">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="12">
          <el-select
            v-model="value"
            multiple
            filterable
            remote
            reserve-keyword
            multiple-limit="1"
            placeholder="请输入文章分类"
            :remote-method="selectCategories"
            :loading="loading"
            style="width: 720px">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.codeValue"
              :value="item.value">
            </el-option>
          </el-select>
        </el-col>
      </el-form-item>
      <br/><br/>
      <mavon-editor ref="editor" scrollStyle="true" @imgAdd="$imgAdd" v-model="form.doc" boxShadow="false"/>
      <br/>
      <el-form-item>
        <el-col :span="8">
          <el-form-item label-width="100px" label="开启评论">
            <el-switch
              v-model="form.hasComments"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-value="100"
              inactive-value="0"
              style="width: 200px">
            </el-switch>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label-width="100px" label="添加缩略图">
            <el-switch
              v-model="hasCover"
              active-color="#13ce66"
              inactive-color="#ff4949"
              style="width: 200px"
              @change="cover">
            </el-switch>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label-width="100px" label="发布时间">
            <el-date-picker
              v-model="form.createTime"
              type="datetime"
              placeholder="选择日期时间"
              readonly="true"
              style="width: 200px">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="24" :style="[showCover]">
          <el-upload
            action="string"
            list-type="picture-card"
            :auto-upload="true"
            limit="1"
            :http-request="uploadCover">
            <i slot="default" class="el-icon-plus"></i>
            <div slot="file" slot-scope="{file}">
              <img
                class="el-upload-list__item-thumbnail"
                :src="file.url" alt=""
              >
              <span class="el-upload-list__item-actions">
                <span
                  class="el-upload-list__item-preview"
                  @click="handlePictureCardPreview(file)"
                >
                  <i class="el-icon-zoom-in"></i>
                </span>
                <span
                  v-if="!disabled"
                  class="el-upload-list__item-delete"
                  @click="handleDownload(file)"
                >
                  <i class="el-icon-download"></i>
                </span>
                <span
                  v-if="!disabled"
                  class="el-upload-list__item-delete"
                  @click="handleRemove(file)"
                >
                  <i class="el-icon-delete"></i>
                </span>
              </span>
            </div>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-col>
      </el-form-item>
      <div align="right">
        <el-button type="primary" icon="el-icon-back">返回列表</el-button>
        <el-button type="primary" icon="el-icon-folder-add" style="background-color: #6e8cd7;border: 1px solid #6e8cd7" @click="onSubmit">发布文章</el-button>
        <el-button type="primary" icon="el-icon-tickets" style="background-color: #ffd740;border: 1px solid #ffd740;color: black">存为草稿</el-button>
      </div>
    </el-form>
  </el-card>
</template>

<script>
import {mavonEditor} from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import AxiosConfig from '../config/AxiosConfig'
export default {
  data () {
    return {
      loading: false,
      options: [],
      list: [],
      tagLoading: false,
      tagOptions: [],
      tagList: [],
      hasCover: false,
      value: [],
      tagValue: [],
      form: {
        title: '',
        tag: '',
        doc: '',
        html: '',
        hasComments: '100',
        cover: '',
        createTime: new Date()
      },
      dialogImageUrl: '',
      dialogVisible: false,
      disabled: false,
      showCover: {
        display: 'none'
      },
      upload_url: AxiosConfig.axiosBaseURL + '/upload/article'
    }
  },
  mounted () {
    this.loadCategories()
  },
  methods: {
    loadCategories () {
      this.$axios.get('/category/query').then(resp => {
        if (resp.status === 200) {
          for (var i = 0; i < resp.data.length; i++) {
            if (resp.data[i].categoryType === 0) {
              let obj = {}
              obj.value = resp.data[i].id
              obj.codeValue = resp.data[i].categoryName
              this.list.push(obj)
            } else {
              let obj = {}
              obj.value = resp.data[i].id
              obj.codeValue = resp.data[i].categoryName
              this.tagList.push(obj)
            }
          }
        } else {
          this.list = []
        }
        this.options = this.list
        this.tagOptions = this.tagList
      })
    },
    selectCategories (query) {
      if (query !== '') {
        this.loading = true
        setTimeout(() => {
          this.loading = false
          this.options = this.list.filter(item => {
            return item.codeValue.toLowerCase()
              .indexOf(query.toLowerCase()) > -1
          })
        }, 200)
      } else {
        this.options = []
      }
    },
    selectTagCategories (query) {
      if (query !== '') {
        this.tagLoading = true
        setTimeout(() => {
          this.tagLoading = false
          this.tagOptions = this.tagList.filter(item => {
            return item.codeValue.toLowerCase()
              .indexOf(query.toLowerCase()) > -1
          })
        }, 200)
      } else {
        this.tagOptions = []
      }
    },
    onSubmit () {
      this.$axios
        .post('/article/new/' + this.value + '/' + this.tagValue, {
          title: this.form.title,
          tag: this.form.tag,
          doc: this.form.doc,
          html: this.$refs.editor.d_render,
          hasComments: this.form.hasComments,
          cover: this.form.cover,
          createTime: this.form.createTime
        }).then(resp => {
          if (resp.data.code === 200) {
            this.$alert('发布成功', null)
          }
        })
    },
    $imgAdd (pos, $file) {
      let formdata = new FormData()
      formdata.append('image', $file)
      this.$axios({
        url: '/upload/article',
        method: 'Post',
        data: formdata,
        headers: {'Content-Type': 'multipart/form-data'}
      }).then(url => {
        this.$refs.editor.$img2Url(pos, url.data)
      })
    },
    cover () {
      if (this.hasCover) {
        this.showCover.display = '-webkit-box'
      } else {
        this.showCover.display = 'none'
      }
    },
    handleRemove (file) {
      console.log(file)
    },
    handlePictureCardPreview (file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    handleDownload (file) {
      console.log(file)
    },
    uploadCover (item) {
      let formdata = new FormData()
      formdata.append('image', item.file)
      this.$axios({
        url: '/upload/article',
        method: 'Post',
        data: formdata,
        headers: {'Content-Type': 'multipart/form-data'}
      }).then(res => {
        if (res.status === 200) {
          this.form.cover = res.data
        }
      })
    }
  },
  name: 'NewArticle',
  components: {
    mavonEditor
  }
}
</script>

<style>
  #editor {
    margin: auto;
    width: 80%;
    height: 580px;
  }

  .v-left-item{
    background-color: rgb(242,242,242);
  }

  .v-right-item{
    background-color: rgb(242,242,242);
  }

</style>

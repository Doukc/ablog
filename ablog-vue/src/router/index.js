import Vue from 'vue'
import Router from 'vue-router'
import Index from '../components/home/index'
import Home from '../components/Home'
import AdminIndex from '../components/admin/AdminIndex'
import NewArticle from '../components/article/NewArticle'
import ArticleDetail from '../components/article/Article'
import Category from '../components/category/Category'
import Articles from '../components/article/Articles'
import ModifyArticle from '../components/article/ModifyArticle'
import Comments from '../components/comment/Comments'
import Categories from '../components/category/Categories'
import OperateLog from '../components/security/OperateLog'
import AdminMain from '../components/admin/AdminMain'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/ui/home',
      name: 'Home',
      redirect: '/ui/index',
      component: Home,
      children: [
        {
          path: '/ui/index',
          name: 'Index',
          component: Index
        },
        {
          path: '/ui/article/detail',
          name: 'ArticleDetail',
          component: ArticleDetail
        },
        {
          path: '/ui/categories',
          name: 'Categories',
          component: Categories
        }
      ]
    },
    {
      path: '/ui/admin',
      name: 'AdminMain',
      component: AdminMain,
      children: [
        {
          path: '/ui/admin/index',
          name: 'AdminIndex',
          component: AdminIndex
        },
        {
          path: '/ui/admin/article/new',
          name: 'NewArticle',
          component: NewArticle
        },
        {
          path: '/ui/admin/category',
          name: 'Category',
          component: Category
        },
        {
          path: '/ui/admin/article/articles',
          name: 'Articles',
          component: Articles
        },
        {
          path: '/ui/admin/article/modify',
          name: 'ModifyArticle',
          component: ModifyArticle
        },
        {
          path: '/ui/admin/comment/comments',
          name: 'Comments',
          component: Comments
        },
        {
          path: '/ui/admin/security/log',
          name: 'OperateLog',
          component: OperateLog
        }
      ]
    }
  ]
})

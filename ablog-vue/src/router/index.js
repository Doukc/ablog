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

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Index',
      redirect: '/index',
      component: Index
    },
    {
      path: '/home',
      name: 'Home',
      redirect: '/index',
      component: Home,
      children: [
        {
          path: '/index',
          name: 'Index',
          component: Index
        },
        {
          path: '/article/detail',
          name: 'ArticleDetail',
          component: ArticleDetail
        },
        {
          path: '/categories',
          name: 'Categories',
          component: Categories
        }
      ]
    },
    {
      path: '/admin/index',
      name: 'AdminIndex',
      component: AdminIndex,
      children: [
        {
          path: '/admin/article/new',
          name: 'NewArticle',
          component: NewArticle
        },
        {
          path: '/admin/category',
          name: 'Category',
          component: Category
        },
        {
          path: '/admin/article/articles',
          name: 'Articles',
          component: Articles
        },
        {
          path: '/admin/article/modify',
          name: 'ModifyArticle',
          component: ModifyArticle
        },
        {
          path: '/admin/comment/comments',
          name: 'Comments',
          component: Comments
        }
      ]
    }
  ]
})

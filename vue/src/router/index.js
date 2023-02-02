import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Manage.vue'
import Login from "@/views/Login";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/manage',
    name: 'Manage',
    component: () => import( '../views/Manage.vue'),
    redirect: "/information",
    children: [
        {
          path: '/home', name: '', component: () => import('../views/Home.vue')
        },
        {
          path: '/user', name: '用户管理', component: () => import( '../components/User.vue')
        },
        {
          path: '/information', name: '用户信息', component: () => import( '../views/Information.vue')
        },
        {
            path: '/reader', name: '读者列表', component: () => import( '../components/Reader.vue')
        },
        {
            path: '/author', name: '作者管理', component: () => import( '../components/Author.vue')
        },
        {
            path: '/root', name: '管理员列表', component: () => import( '../components/Root.vue')
        },
        {
            path: '/novel', name: '小说管理', component: () => import( '../components/Novel.vue')
        },
        {
            path: '/novelChapter', name: '小说管理 > 章节列表', component: () => import( '../components/NovelChapter.vue')
        },
        {
            path: '/novelContent', name: '小说管理 > 章节列表 > 内容', component: () => import( '../components/NovelContent.vue')
        },
    ]
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]
const originalPush = VueRouter.prototype.push

VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


export default router

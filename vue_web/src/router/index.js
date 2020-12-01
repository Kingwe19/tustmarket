import Vue from 'vue'
import Router from 'vue-router'
import Index from '../components/index'
import Login from '../components/pages/login'
import Register from '../components/pages/register'
import PersonManage from '../components/personManage'
import OldSail from '../components/oldSail'
import ShopCar from '../components/pages/shopCar'
import FeedBack from "../components/pages/feedback";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      components: {
        "web-body": Index
      }
    },
    {
      path: '/login',
      name: 'login',
      components: {
        "web-body": Login
      }
    },
    {
      path: '/feedback',
      name: 'feedback',
      components: {
        "web-body": FeedBack
      }
    },
    {
      path: '/register',
      name: 'register',
      components: {
        "web-body": Register
      }
    },
    {
      path: '/person',
      name: 'personManage',
      components: {
        "web-body": PersonManage
      }
    },
    {
      path: '/oldSail',
      name: 'oldSail',
      components: {
        "web-body": OldSail
      }
    },
    {
      path: '/shopCar',
      name: 'shopCar',
      components: {
        "web-body": ShopCar
      }
    },
    {
      path: '/:selectNum',
      name: 'selectIndex',
      components: {
        "web-body": Index
      }
    },
  ],
  //路由回滚和锚点跳转
  scrollBehavior(to, from, savedPosition) {
    if (to.hash) {
      return {
        selector: to.hash
      }
    } else if (savedPosition) {
      return savedPosition
    } else {
      return {x: 0, y: 0}
    }
  }
});

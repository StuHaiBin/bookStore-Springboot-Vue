import router from './router'
import store from "./store/store";
import {Message} from "element-ui";
router.beforeEach((to, from, next) => {
  console.log("=======路由跳转需要匹配！！=======")
  console.log(to.matched);
  if (to.matched.some(record => record.meta.requiresManage)) {
    let token = localStorage.getItem("token");
    let user = JSON.parse(sessionStorage.getItem("userInfo"));
    if (!token) {  // 管理员没登录
      Message({
        type: 'waring',
        message: "未登录,请先登录！",
        duration: 2000
      })
      setTimeout(() => {
        next({
          path: '/login',
        })
      }, 2000);
    } else if(!user.manage){
      Message({
        type: 'waring',
        message: "未登录,请先登录！",
        duration: 2000
      })
      setTimeout(() => {
        next({
          path: '/login',
        })
      }, 2000);
    }else {
      next();
    }
  } else if(to.matched.some(record => record.meta.requiresUser)){
    let token = localStorage.getItem("token");
    let user = JSON.parse(sessionStorage.getItem("userInfo"));
    if (!token) {  // 管理员没登录
      Message({
        type: 'waring',
        message: "未登录,请先登录！",
        duration: 2000
      })
      setTimeout(() => {
        next({
          path: '/login',
        })
      }, 2000);
    } else if(user.manage){
      Message({
        type: 'waring',
        message: "未登录,请先登录！",
        duration: 2000
      })
      setTimeout(() => {
        next({
          path: '/login',
        })
      }, 2000);
    }else {
      next();
    }
  } else{
    next(); //确保一定要调用 next()
  }
})

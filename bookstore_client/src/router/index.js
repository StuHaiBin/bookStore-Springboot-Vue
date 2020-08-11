import Vue from 'vue';
import Router from 'vue-router';
import Login from "../pages/Login/Login";
import Index from "../pages/Index/Index";
import AxiosTest from "../components/AxiosTest";
import UserHome from "../pages/UserHome/UserHome";
import Book from "../pages/Book/Book";
import Register from "../pages/Register/Register";
import Spike from "../pages/Spike/Spike";
import Admin from "../pages/AdminHome/Admin";
import BookTopic from "../pages/BookList/BookTopic";
import BookList from "../components/AdminHome/bookNav/book/BookList";
import AddBook from "../components/AdminHome/bookNav/book/components/BookDetail";
import OrderList from "../components/AdminHome/orderNav/OrderList";
import OrderSet from "../components/AdminHome/orderNav/OrderSet";
import ReturnOrder from "../components/AdminHome/orderNav/ReturnOrder";
import ReturnReason from "../components/AdminHome/orderNav/ReturnReason";
import BookRecommend from "../components/AdminHome/market/BookRecommend";
import Coupon from "../components/AdminHome/market/Coupon";
import SpikeSet from "../components/AdminHome/market/SpikeSet";
import MenuList from "../components/AdminHome/authority/MenuList";
import RolesList from "../components/AdminHome/authority/RolesList";
import UserList from "../components/AdminHome/authority/UserList";
import AdminHome from "../components/AdminHome/AdminHome";
import BookTopicSet from "../components/AdminHome/bookNav/topic/BookTopicSet";
import Search from "../pages/Search/Search";
import Upload from "../components/Upload";
import Upload2 from "../components/Upload2";
import Upload3 from "../components/Upload3";
import Cart from "../pages/Cart/Cart";
import CheckBoxTest from "../components/Test/CheckBoxTest";
import cascaderTest from "../components/Test/cascaderTest";
import Blog from "../components/Test/Blog";
import AddSort from "../components/AdminHome/bookNav/sort/components/SortDetail";
import Publish from "../components/AdminHome/bookNav/publish/Publish";
import RouterTest from "../components/Test/RouterTest";
import TestRouter from "../components/Test/TestRouter";
import AddPublish from "../components/AdminHome/bookNav/publish/AddPublish";
import UpdatePublish from "../components/AdminHome/bookNav/publish/UpdatePublish";
import SwitchTest from "../components/Test/SwitchTest";
import UpdateFirstSort from "../components/AdminHome/bookNav/sort/UpdateFirstSort";
import UpdateSecondSort from "../components/AdminHome/bookNav/sort/UpdateSecondSort";
import FirstSortList from "../components/AdminHome/bookNav/sort/FirstSortList";
import SecondSortList from "../components/AdminHome/bookNav/sort/SecondSortList";
import UpdateBook from "../components/AdminHome/bookNav/book/UpdateBook";
import BookTopicDetail from "../components/AdminHome/bookNav/topic/component/BookTopicDetail";
import UpdateBookTopic from "../components/AdminHome/bookNav/topic/UpdateBookTopic";
import AddBookTopic from "../components/AdminHome/bookNav/topic/AddBookTopic";
import SubTopicList from "../components/AdminHome/bookNav/topic/SubTopicList";
import Address from "../components/UserHome/home/Address";
import MesNotice from "../components/UserHome/home/MesNotice";
import UserCenter from "../components/UserHome/home/UserCenter";
import PwdManage from "../components/UserHome/manage/PwdManage";
import UserInfo from "../components/UserHome/manage/UserInfo";
import Evaluate from "../components/UserHome/order/Evaluate";
import UserOrder from "../components/UserHome/order/UserOrder";
import ApplyService from "../components/UserHome/service/ApplyService";
import Record from "../components/UserHome/service/Record";
import UserCoupon from "../components/UserHome/home/UserCoupon";
import BuyPage from "../pages/BuyPage/BuyPage";
import OrderDetail from "../components/AdminHome/orderNav/OrderDetail";
import Deliver from "../components/AdminHome/orderNav/Deliver";
import UserOrderDetail from "../components/UserHome/order/UserOrderDetail";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: "/register",
      name: 'Register',
      component: Register
    },
    {
      path: '/admin',
      name: 'Admin',
      component: Admin,
      meta: {
        title: '首页',
        url: '/admin/home',
        requiresAuth: true,// 添加该字段，表示进入这个路由是需要登录的
        requiresManage: true,
      },
      children:[
        {
          path: 'home',
          name: 'home',
          component: AdminHome,
          meta: {
            title: '统计页面',
            url: '/admin/home',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'bookList',
          name: 'BookList',
          component: BookList,
          meta: {
            title: '图书列表',
            url: '/admin/bookList',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'addBook',
          name: 'AddBook',
          component: AddBook,
          meta: {
            title: '添加图书',
            url: '/admin/addBook',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'updateBook',
          name: 'UpdateBook',
          component: UpdateBook,
          meta: {
            title: '更新图书',
            url: '/admin/updateBook',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'publish',
          name: 'Publish',
          component: Publish,
          meta: {
            title: '出版社列表',
            url: '/admin/publish',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'addPublish',
          name: 'AddPublish',
          component: AddPublish,
          meta: {
            title: '添加出版社',
            url: '/admin/addPublish',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'updatePublish',
          name: 'UpdatePublish',
          component: UpdatePublish,
          meta: {
            title: '更新出版社',
            url: '/admin/updatePublish',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'addSort',
          name: 'AddSort',
          component: AddSort,
          meta: {
            title: '添加分类',
            url: '/admin/addSort',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'updateFirstSort',
          name: 'UpdateFirstSort',
          component: UpdateFirstSort,
          meta: {
            title: '更新一级分类',
            url: '/admin/updateFirstSort',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'updateSecondSort',
          name: "UpdateSecondSort",
          component: UpdateSecondSort,
          meta: {
            title: '更新二级分类',
            url: '/admin/updateSecondSort',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'firstSortList',
          name: "FirstSortList",
          component: FirstSortList,
          meta: {
            title: '一级分类列表',
            url: '/admin/firstSortList',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: "secondSortList",
          name: "SecondSortList",
          component: SecondSortList,
          meta: {
            title: '二级分类列表',
            url: '/admin/secondSortList',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'orderList',
          name: 'OrderList',
          component: OrderList,
          meta: {
            title: '订单列表',
            url: '/admin/orderList',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'orderSet',
          name: 'OrderSet',
          component: OrderSet,
          meta: {
            title: '订单设置',
            url: '/admin/orderSet',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'orderDetail',
          name: 'OrderDetail',
          component: OrderDetail,
          meta: {
            title: '订单详情',
            url: '/admin/orderDetail',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'deliver',
          name: 'Deliver',
          component: Deliver,
          meta: {
            title: '发货',
            url: '/admin/deliver',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'returnOrder',
          name: 'ReturnOrder',
          component: ReturnOrder,
          meta: {
            title: '退单页面',
            url: '/admin/returnOrder',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'returnReason',
          name: 'ReturnReason',
          component: ReturnReason,
          meta: {
            title: '退单理由',
            url: '/admin/returnReason',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'bookRecommend',
          name: 'BookRecommend',
          component: BookRecommend,
          meta: {
            title: '图书推荐',
            url: '/admin/bookRecommend',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'bookTopicSet',
          name: 'BookTopicSet',
          component: BookTopicSet,
          meta: {
            title: '图单专题',
            url: '/admin/bookTopicSet',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'coupon',
          name: 'Coupon',
          component: Coupon,
          meta: {
            title: '优惠券',
            url: '/admin/coupon',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'spikeSet',
          name: 'SpikeSet',
          component: SpikeSet,
          meta: {
            title: '秒杀设置',
            url: '/admin/spikeSet',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'menuList',
          name: 'MenuList',
          component: MenuList,
          meta: {
            title: '菜单列表',
            url: '/admin/menuList',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'rolesList',
          name: 'RolesList',
          component: RolesList,
          meta: {
            title: '角色列表',
            url: '/admin/rolesList',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'userList',
          name: 'UserList',
          component: UserList,
          meta: {
            title: '用户列表',
            url: '/admin/userList',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },

        {
          path: 'bookTopicDetail',
          name: "BookTopicDetail",
          component: BookTopicDetail,
          meta: {
            title: '书单详情',
            url: '/admin/bookTopicDetail',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: "updateBookTopic",
          name: "UpdateBookTopic",
          component: UpdateBookTopic,
          meta: {
            title: '更新书单',
            url: '/admin/updateBookTopic',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: "addBookTopic",
          name: "AddBookTopic",
          component: AddBookTopic,
          meta: {
            title: '添加书单',
            url: '/admin/addBookTopic',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        },
        {
          path: 'subTopicList',
          name: "SubTopicList",
          component: SubTopicList,
          meta: {
            title: '子书单',
            url: '/admin/subTopicList',
            requiresAuth: true // 添加该字段，表示进入这个路由是需要登录的
          },
        }
      ]
    },
    {
      path: '/user',
      name: 'UserHome',
      component: UserHome,
      meta: {
        title: '应用详情',
        url: '/user/userCenter', //这里用户界面第一步是进入到用户中心
        requiresAuth: true, // 添加该字段，表示进入这个路由是需要登录的
        requiresUser: true,
      },
      children:[
        {
          path: 'address',
          name: "Address",
          component: Address
        },
        {
          path: 'coupon',
          name: "Coupon",
          component: UserCoupon
        },
        {
          path: 'mesNotice',
          name: "MesNotice",
          component: MesNotice
        },
        {
          path: 'userCenter',
          name: "UserCenter",
          component: UserCenter
        },
        {
          path: 'pwdManage',
          name: "PwdManage",
          component: PwdManage
        },
        {
          path: "userInfo",
          name: "UserInfo",
          component: UserInfo
        },
        {
          path: "evaluate",
          name: "Evaluate",
          component: Evaluate
        },
        {
          path: "userOrder",
          name: "UserOrder",
          component: UserOrder
        },
        {
          path: "userOrderDetail",
          name: "UserOrderDetail",
          component: UserOrderDetail
        },
        {
          path: "applyService",
          name: "ApplyService",
          component: ApplyService
        },
        {
          path: "record",
          name: "Record",
          component: Record
        }
      ]
    },
    {
      path: '/bookTopic',
      name: 'BookTopic',
      component: BookTopic
    },
    {
      path: '/book',
      name: 'Book',
      component: Book
    },
    {
      path: '/spike',
      name: 'Spike',
      component: Spike
    },
    {
      path: '/search',
      name: 'Search',
      component: Search
    },



    //下面的是一些测试页面的路由
    {
      path: '/upload',
      name: 'Upload',
      component: Upload
    },
    {
      path: '/upload2',
      name: 'Upload2',
      component: Upload2
    },
    {
      path: '/upload3',
      name: 'upload3',
      component: Upload3
    },
    {
      path: '/cart',
      name: 'Cart',
      component: Cart,
      meta: {
        title: '购物车',
        requiresAuth: true, // 添加该字段，表示进入这个路由是需要登录的
        requiresUser: true,
      },
    },
    {
      path: "/buyPage",
      name: 'BuyPage',
      component: BuyPage,
      meta: {
        title: '购买页面',
        requiresAuth: true, // 添加该字段，表示进入这个路由是需要登录的
        requiresUser: true,
      },
    },
    {
      path: '/check',
      name: 'CheckBoxTest',
      component: CheckBoxTest
    },
    {
      path: '/eas',
      name: 'cascaderTest',
      component: cascaderTest
    },
    {
      path: '/blog',
      name: "blog",
      component: Blog
    },
    {
      path: '/routerTest',
      name: 'RouterTest',
      component: RouterTest
    },
    {
      path: '/testRouter',
      name: 'TestRouter',
      component: TestRouter
    },
    {
      path: '/switch',
      name: 'SwitchTest',
      component: SwitchTest
    },
    {
      path: "/axiosTest",
      name: "AxiosTest",
      component: AxiosTest
    }
  ]
})

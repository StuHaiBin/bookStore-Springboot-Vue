<template>
  <div class="content">
    <el-container style="height:100vh;">
      <el-aside style="height:100vh;" :width="tabWidth+'px'">
          <el-menu :default-active="$route.path"
                   :default-openeds="open"
                   :unique-opened="unique_open"
                   class="el-menu-vertical-demo el_muen_class"
                   @open="handleOpen"
                   @close="handleClose"
                   background-color="#304156"
                   text-color="#fff"
                   active-text-color="#409eff"
                   :collapse="isCollapse"
                   router>
          <el-menu-item index="/admin/home">
            <i class="el-icon-s-home"></i>
            <span slot="title">首页</span>
          </el-menu-item>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-tickets"></i>
              <span slot="title">图书</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/admin/bookList">图书列表</el-menu-item>
              <el-menu-item index="/admin/addBook">添加图书</el-menu-item>
              <el-menu-item index="/admin/FirstSortList">图书分类</el-menu-item>
              <el-menu-item index="/admin/publish">出版社管理</el-menu-item>
              <el-menu-item index="/admin/bookTopicSet">书单专题</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span slot="title">订单</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/admin/orderList">订单列表</el-menu-item>
              <el-menu-item index="/admin/orderSet">订单设置</el-menu-item>
              <el-menu-item index="/admin/returnOrder">退货申请处理</el-menu-item>
              <el-menu-item index="/admin/returnReason">退货原因设置</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-shopping-bag-1"></i>
              <span slot="title">营销</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/admin/spikeSet">秒杀活动</el-menu-item>
              <el-menu-item index="/admin/bookRecommend">人气推荐</el-menu-item>
              <el-menu-item index="/admin/coupon">优惠券</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="5">
            <template slot="title">
              <i class="el-icon-lock"></i>
              <span slot="title">权限</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/admin/userList">用户列表</el-menu-item>
<!--              <el-menu-item index="/admin/rolesList">角色列表</el-menu-item>-->
<!--              <el-menu-item index="/admin/menuList">菜单列表</el-menu-item>-->
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header style="text-align: right; font-size: 14px">
          <div @click="isClossTabFun" style="background-color: white;height: 40px;text-align: center;float: left;font-size: 25px">
            <i :class="isCollapse?'el-icon-s-unfold':'el-icon-s-fold'"></i>
          </div>

          <div style="height: 60px;float: left;margin-left: 12px">
            <el-breadcrumb separator=">">
              <el-breadcrumb-item
                v-for="(item,index) in breadList"
                :key="index"
                :to="{ path: item.path }"
              >
                <span style="font-size: 16px;line-height: 60px">{{item.meta.title}}</span>
              </el-breadcrumb-item>
            </el-breadcrumb>
          </div>

          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item><a href="#/">首页</a></el-dropdown-item>
              <el-dropdown-item><a @click="logout">退出</a></el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span>黄龙</span>
        </el-header>

        <el-main>
          <div style="width: 100%">
<!--            <a href="#/admin/info">用户信息</a> <a href="#/admin/update">修改信息</a>-->
            <router-view></router-view>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
    import {mapState,mapMutations,mapGetters} from 'vuex'
    export default {
        name: "Admin",
        data() {
            return {
                screenWidth: 0,
                open: ['1'],
                unique_open: false,
                isCollapse: false,
                tabWidth: 200,
                intel: null,
                breadList: [] // 路由集合
            }
        },
        created(){
            this.getBreadcrumb();
        },
        watch:{
            $route(){
                this.getBreadcrumb();
            }
        },
        mounted() {
            this.screenWidth = document.body.clientWidth;
            window.onresize = () => {
                if(this.isCollapse==false){
                    this.screenWidth = document.body.clientWidth;
                    if(this.screenWidth<800){
                        this.tabWidth = 64;
                        this.isCollapse = true;
                    }
                }
            };
        },
        computed: function(){
            this.height = window.innerHeight;
            console.log("computed执行了")
        },
        methods: {
            isHome(route) {
                return route.name === "首页";
            },
            getBreadcrumb() {
                let matched = this.$route.matched;
                if (!this.isHome(matched[0])) {
                    // matched = [{ path: "/admin", meta: { title: "首页" } }].concat(matched);
                    matched = matched;
                }
                this.breadList = matched;
                console.log("this.breadList:"+this.breadList);
            },

            logout(){
                this.REMOVE_INFO();
                this.$message({
                    type: 'success',
                    message: "退出成功！",
                    duration: 1000
                })
                setTimeout(() => {
                    this.$router.push({path:'/'});
                }, 1000);
            },
            handleOpen(key, keyPath) {
                // console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                // console.log(key, keyPath);
            },
            isClossTabFun(){
                clearInterval(this.intel);
                if(!this.isCollapse){
                    this.intel = setInterval(()=>{
                        if(this.tabWidth<= 64)
                            clearInterval(this.intel);
                        this.tabWidth -= 4;
                    }, 1);
                }else{
                    this.tabWidth = 200;
                }
                this.isCollapse = !this.isCollapse;
            },
            ...mapMutations(['REMOVE_INFO']),
        },
        computed: {
            ...mapState(['active']),
            ...mapGetters(['showActive'])
        }
    }
</script>

<style scoped>
  .el-header {
    color: #333;
    line-height: 60px;
    border-bottom: 1px #B3C0D1 solid;
  }
  .el-aside {
    color: #333;
    border: none;
  }
  .el-aside::-webkit-scrollbar {display:none}
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
  .el_muen_class{
    height: 100vh;
  }
</style>


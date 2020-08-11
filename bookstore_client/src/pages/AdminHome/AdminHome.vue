<template>
    <div class="content">
      <el-container style="height:100vh;">
        <el-aside width="200px" style="height:100vh;">
          <el-menu :default-openeds="open"
                   :unique-opened="unique_open"
                   class="el-menu-vertical-demo"
                   @open="handleOpen"
                   @close="handleClose"
                   background-color="#304156"
                   text-color="#fff"
                   active-text-color="#ffd04b"
          >
            <el-menu-item index="1">
              <i class="el-icon-menu"></i>
              <span slot="title">首页</span>
            </el-menu-item>
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-menu"></i>图书</template>
              <el-menu-item-group>
                <a href="#/admin/info"><el-menu-item index="2-1">图书列表</el-menu-item></a>
                <a href="#/admin/update"><el-menu-item index="2-2">添加图书</el-menu-item></a>
                <a href="#/admin/axios"><el-menu-item index="2-3">图书分类</el-menu-item></a>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title"><i class="el-icon-setting"></i>订单</template>
              <el-menu-item-group>
                <el-menu-item index="3-1">订单列表</el-menu-item>
                <el-menu-item index="3-2">订单设置</el-menu-item>
                <el-menu-item index="3-3">退货申请处理</el-menu-item>
                <el-menu-item index="3-4">退货原因设置</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="4">
              <template slot="title"><i class="el-icon-setting"></i>营销</template>
              <el-menu-item-group>
                <el-menu-item index="4-1">秒杀活动</el-menu-item>
                <el-menu-item index="4-2">书单专题</el-menu-item>
                <el-menu-item index="4-3">人气推荐</el-menu-item>
                <el-menu-item index="4-4">优惠卷</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="5">
              <template slot="title"><i class="el-icon-setting"></i>权限</template>
              <el-menu-item-group>
                <el-menu-item index="5-1">用户列表</el-menu-item>
                <el-menu-item index="5-2">角色列表</el-menu-item>
                <el-menu-item index="5-3">菜单列表</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-container>
          <el-header style="text-align: right; font-size: 14px">
            <el-dropdown>
              <i class="el-icon-setting" style="margin-right: 15px"></i>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item><a href="#/">首页</a></el-dropdown-item>
                <el-dropdown-item><a href="#/">退出</a></el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
            <span>黄龙</span>
          </el-header>

          <el-main>
            <div style="height: 1900px; width: 100%">
              <a href="#/admin/info">用户信息</a> <a href="#/admin/update">修改信息</a>
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
        name: "AdminHome",
        data() {
            return {
                height: 0,
                open: ['3'],
                unique_open: true
            }
        },
        created: function () {
            this.height = window.innerHeight;
            console.log("hhh");
        },
        computed: function(){
            this.height = window.innerHeight;
            console.log("computed执行了")
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
                this.setActive(key);
                this.open[1] = this.active;
                console.log("active:"+this.active);
                console.log("包装后的active:"+this.showActive);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            },
            ...mapMutations(['setActive']),
        },
        computed: {
            ...mapState(['active']),
            ...mapGetters(['showActive'])
        }
    }
</script>

<style scoped>
  .content{
    margin: 0px;
    padding: 0px;
    box-sizing: border-box;
  }
  .el-header {
    color: #333;
    line-height: 60px;
    border-bottom: 1px #B3C0D1 solid;
  }
  .el-aside {
    color: #333;
    background-color: #304156;
  }
  .el-aside::-webkit-scrollbar {display:none}
  a{
    text-decoration: none;
    font-size: 14px;
    line-height: 40px;
    color: #545c64;
  }
</style>

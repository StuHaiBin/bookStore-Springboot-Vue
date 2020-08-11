<template>
  <div class="navClass">
    <div class="content">
      <div class="nav_content">
        <el-menu
          :default-active="$route.path"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          background-color="#304156"
          text-color="#fff"
          active-text-color="#ffd04b"
          router>
          <el-menu-item index="/">首页</el-menu-item>
          <el-submenu index="/">
            <template slot="title">登陆|注册</template>
            <el-menu-item index="/login">登陆</el-menu-item>
            <el-menu-item index="/register">注册</el-menu-item>
          </el-submenu>
          <el-menu-item index="/cart">购物车</el-menu-item>
<!--          <el-submenu index="/cart">-->
<!--            <template slot="title">购物车</template>-->
<!--            <el-menu-item index="2-1">选项1</el-menu-item>-->
<!--            <el-menu-item index="2-2">选项2</el-menu-item>-->
<!--            <el-menu-item index="2-3">选项3</el-menu-item>-->
<!--            <el-submenu index="2-4">-->
<!--              <template slot="title">选项4</template>-->
<!--              <el-menu-item index="2-4-1">选项1</el-menu-item>-->
<!--              <el-menu-item index="2-4-2">选项2</el-menu-item>-->
<!--              <el-menu-item index="2-4-3">选项3</el-menu-item>-->
<!--            </el-submenu>-->
<!--          </el-submenu>-->
          <el-menu-item index="/user/userInfo">消息</el-menu-item>
          <el-menu-item index="/user/userOrder">我的订单</el-menu-item>
          <el-menu-item index="/user/userCenter">个人中心</el-menu-item>
          <el-menu-item index="/user/applyService">服务</el-menu-item>
        </el-menu>
      </div>
    </div>
  </div>
</template>

<script>
    import $route from "element-ui/packages/cascader-panel/src/node";
    export default {
        name: "Nav",
        data() {
            return {
                activeIndex: "/",
            };
        },
        methods: {
            handleSelect(key, keyPath) {
                // console.log(key, keyPath);
            },
        }
    }
</script>

<style scoped>
  .navClass{
    width: 100%;
    /*background-color: #545c64;*/
    background-color: #304156;
    height: 40px;
  }
  .content{
    margin: 0px auto;
    width: 1240px;
    height: 40px;
    /*background-color: #545c64;*/
    background-color: #304156;
  }
  .nav_content{
    float: right;
  }
  .el-menu-item,.el-submenu{
    height: 40px;
    font-size: 12px;
    line-height: 40px;
  }
 .el-submenu>>>.el-submenu__title {
   height: 40px;
   font-size: 12px;
   line-height: 40px;
  }
</style>

<template>
  <div>
    <el-container style="height:100vh;">
      <el-aside style="height:100vh;" :width="tabWidth+'px'">
        <el-menu default-active="1-4-1"
                 class="el-menu-vertical-demo"
                 background-color="#304156"
                 text-color="#fff"
                 active-text-color="#ffd04b"
                 @open="handleOpen"
                 @close="handleClose"
                 :collapse="isCollapse">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span slot="title">导航一</span>
            </template>
            <el-menu-item-group>
              <span slot="title">分组一</span>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <span slot="title">选项4</span>
              <el-menu-item index="1-4-1">选项1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">导航二</span>
          </el-menu-item>
          <el-menu-item index="3" disabled>
            <i class="el-icon-document"></i>
            <span slot="title">导航三</span>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-setting"></i>
            <span slot="title">导航四</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header style="text-align: right; font-size: 14px">
          <div @click="isClossTabFun" style="background-color: white;height: 40px;text-align: center;float: left;font-size: 25px">
            <i :class="isCollapse?'el-icon-s-unfold':'el-icon-s-fold'"></i>
          </div>
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
    export default {
        name: "HomeTest",
        data() {
            return {
                isCollapse: false,
                tabWidth: 200,
                intel: null,
            };
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
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
            }
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
    /*background-color: #304156;*/
    border: none;
  }
  .el-aside::-webkit-scrollbar {display:none}

  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
</style>


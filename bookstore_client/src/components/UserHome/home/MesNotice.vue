<template>
  <div class="content">
    <h1>消息通知</h1>
    <div class="box_info">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="全部消息" name="first">
          <div class="tab_box">
            <p class="noMesInfo">暂无数据</p>
          </div>
        </el-tab-pane>
        <el-tab-pane label="物流动态" name="second">
          <div class="tab_box">
            <p class="noMesInfo">暂无数据</p>
          </div>
        </el-tab-pane>
        <el-tab-pane label="特惠活动" name="third">
          <div class="tab_box">
            <p class="noMesInfo">暂无数据</p>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
    // <!--消息通知页面-->
    export default {
        name: "MesNotice",
        data() {
            return {
                activeName: 'second'
            };
        },
        methods: {
            handleClick(tab, event) {
                console.log(tab, event);
            }
        }
    }
</script>

<style scoped>
.content{
  margin: 10px auto;
  width:1000px;
  padding: 30px 20px;
  background-color: white;
}
  h1{
    color: #757575;
    font-family: 新宋体;
  }
  .box_info{
    width: 960px;
    margin: 10px auto;
  }
/deep/ .el-tabs__item {
  height: 50px;
  line-height: 50px;
  font-size: 16px;
  color: #757575;
}
  .tab_box{
    width:960px;
  }

  .noMesInfo{
    text-align: center;
    font-size: 18px;
    color: #757575;
    line-height: 60px
  }

</style>

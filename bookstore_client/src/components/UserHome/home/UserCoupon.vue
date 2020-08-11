<template>
  <div class="content">
    <h1>优惠券</h1>
    <div class="box_info">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="已使用" name="first">
          <div class="tab_box">
            <p class="noMesInfo">暂无数据</p>
          </div>
        </el-tab-pane>
        <el-tab-pane label="未使用" name="second">
          <div class="tab_box">
            <p class="noMesInfo">暂无数据</p>
          </div>
        </el-tab-pane>
        <el-tab-pane label="已失效" name="third">
          <div class="tab_box">
            <p class="noMesInfo">暂无数据</p>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
    // <!--用户订单页面-->
    export default {
        name: "UserCoupon",
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
    background-color: white;
    padding: 30px 20px;
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

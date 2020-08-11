<template>
  <div class="content">
    <h1>请选择申请方式</h1>
    <div class="box_info">
      <div class="service">
        <div class="service_box">
          <i class="el-icon-s-promotion" style="font-size: 90px;color: #8bc34a"></i>
          <p class="text_title">快速申请</p>
          <p class="text_info">小米网 / 小米商城App购买的订单</p>
          <p class="text_info">支持通过订单快速申请</p>
          <button class="plainBtn">订单详情</button>
        </div>
      </div>
      <div class="service">
        <div class="service_box">
          <i class="el-icon-edit" style="font-size: 90px;color: #8bc34a"></i>
          <p class="text_title">填写申请单</p>
          <p class="text_info">其他渠道购买的产品</p>
          <p class="text_info">根据产品唯一识别码和购买凭证申请售后</p>
          <button class="plainBtn">申请售后</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    // <!--用户订单页面-->
    export default {
        name: "ApplyService",
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
  .service{
    width: 450px;
    height: 320px;
    /*border: 1px solid #cacaca;*/
    display: inline-block;
    margin-right: 20px;
    margin-top: 10px;
    padding: 20px;
    vertical-align: top;
  }
  .service_box{
    width: 300px;
    height: 240px;
    margin-top: 10px;
    margin-left: 60px;
    text-align: center;
  }
  .plainBtn{
    margin-top: 20px;
    width: 150px;
    height: 40px;
    background-color: white;
    color: #8bc34a;
    border: 1px #8bc34a solid;
  }
  .plainBtn:hover{
    color: white;
    background-color: #8bc34a;
  }
  .text_title{
    font-size: 23px;
    line-height: 40px
  }
  .text_info{
    font-size: 14px;
    line-height: 25px;
    color: #757575
  }
</style>


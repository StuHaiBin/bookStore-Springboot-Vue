<template>
  <div style="width: 100%;background-color: #f7f7f6;min-width: 1240px">
    <Nav></Nav>
    <HeadNav></HeadNav>
    <div class="content">
      <div class="title">
        <a href="#">首页</a><span> / </span><span>个人中心</span>
      </div>
      <div class="content_main">
        <div class="content_main_left">
          <div class="uc-box">
            <div class="uc-nav-box">
              <div class="box-hd">订单中心</div>
              <div class="box-bd">
                <ul class="uc-nav-list">
                  <a href="#/user/userOrder"><li>我的订单</li></a>
                  <a href="#/user/evaluate"><li>晒单评价</li></a>
                </ul>
              </div>
            </div>
            <div class="uc-nav-box">
              <div class="box-hd">个人中心</div>
              <div class="box-bd">
                <ul class="uc-nav-list">
                  <a href="#/user/userCenter"><li>我的个人中心</li></a>
                  <a href="#/user/mesNotice"><li>消息通知</li></a>
                  <a href="#/cart" target="_blank"><li>购物车</li></a>
                  <a href="#/user/address"><li>收货地址</li></a>
                  <a href="#/user/coupon"><li>优惠券</li></a>
                </ul>
              </div>
            </div>
            <div class="uc-nav-box">
              <div class="box-hd">售后服务</div>
              <div class="box-bd">
                <ul class="uc-nav-list">
                  <a href="#/user/record"><li>服务记录</li></a>
                  <a href="#/user/applyService"><li>申请服务</li></a>
                </ul>
              </div>
            </div>
            <div class="uc-nav-box">
              <div class="box-hd">账户管理</div>
              <div class="box-bd">
                <ul class="uc-nav-list">
                  <a href="#/user/userInfo"><li>个人管理</li></a>
                  <a href="#/user/pwdManage"><li>修改密码</li></a>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <div class="content_main_right">
          <router-view></router-view>
        </div>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
    import Nav from "../../components/Common/Nav";
    import HeadNav from "../../components/Common/HeadNav";
    import Footer from "../../components/Common/Footer";

    export default {
        name: "UserHome",
        components:{Nav,HeadNav,Footer},
        data(){
            return{

            }
        }
    }
</script>

<style scoped>
  .content{
    background-color: #f7f7f6;
    /*background-color: #3df742;*/
    width: 1240px;
    margin: 0px auto;
  }
  .title{
    margin: 0px auto;
    width: 1240px;
    /*background-color: #8acfd1;*/
    height: 40px;
    line-height: 40px;
    font-size: 14px;
  }
  .title a{
    text-decoration: none;
    color: #333333;
  }
  .title a:hover{
    color: #9d232c;
  }

  .content_main{
    margin: 0px auto;
    background-color: #ffffff;
    width: 1240px;
  }

  .content_main_left{
    width: 17%;
    /*background-color: #f7f7f6;*/
    float: left;
    margin-bottom: 30px;
  }
  .content_main_right{
    width: 82%;
    float: right;
    background-color: #ffffff;
    margin-bottom: 30px;
  }
  .uc-box{
    padding: 36px 0px;
    width: 100%;
    background-color: #ffffff;
  }
  .uc-nav-box{
    margin-bottom: 20px;
  }
  .uc-nav-box .box-hd {
    height: 52px;
    font-size: 18px;
    line-height: 52px;
    padding: 0px 40px;
  }
  .uc-nav-box .box-bd {
    padding: 0px 40px;
    line-height: 25px;
  }
  .uc-nav-list {
    margin: 0;
    padding: 0;
    list-style-type: none;
  }
  .uc-nav-list li {
    padding: 6px 0px;
  }
  .uc-nav-list a{
    text-decoration: none;
    color: #999;
  }
  .uc-nav-list a:hover{
    text-decoration: none;
    color: #15171a;
  }
  li {
    display: list-item;
    text-align: -webkit-match-parent;
  }
  li:hover{
    background-color: #d9d9d9;
  }

</style>

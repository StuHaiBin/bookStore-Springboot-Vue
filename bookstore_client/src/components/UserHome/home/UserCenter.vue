<template>
  <div class="content">
    <div class="info">
      <div class="user_card">
        <el-image style="width: 160px; height: 160px;vertical-align: middle;border-radius: 50%;float: left;margin: 20px 10px"
            :src="user.imgUrl"
        fit="fill"></el-image>
        <div class="user_card_info">
          <p style="font-size: 22px;color: #616161">{{user.name}}</p>
          <p><span class="link" @click="gotoModUserInfo">修改个人信息></span></p>
        </div>
      </div>
      <div class="user_action">
        <p> <span>账号安全:</span><span>普通</span></p>
        <p> <span>绑定手机:</span><span>18370098989</span></p>
        <p> <span>绑定邮箱:</span><span>{{user.account}}</span></p>
      </div>
    </div>
    <div class="portal-sub">
      <div class="info-list">
        <el-image style="width: 120px; height: 120px;vertical-align: middle;border-radius: 50%;float: left;margin: 15px 10px"
          :src="imgS1"
          fit="fill"></el-image>
        <div class="list_info">
          <p> <span class="title">待支付的订单:</span> <span class="num">0</span></p>
          <p> <span>查看待支付的订单></span></p>
        </div>
      </div>

      <div class="info-list">
        <el-image style="width: 120px; height: 120px;vertical-align: middle;border-radius: 50%;float: left;margin: 15px 10px"
                  :src="imgS2"
                  fit="fill"></el-image>
        <div class="list_info">
          <p> <span class="title">待收货的订单:</span> <span class="num">0</span></p>
          <p> <span>查看待收货的订单></span></p>
        </div>
      </div>

      <div class="info-list">
        <el-image style="width: 120px; height: 120px;vertical-align: middle;border-radius: 50%;float: left;margin: 15px 10px"
                  :src="imgS3"
                  fit="fill"></el-image>
        <div class="list_info">
          <p> <span class="title">待评价的商品数:</span> <span class="num">0</span></p>
          <p> <span>查看待评价商品></span></p>
        </div>
      </div>

      <div class="info-list">
        <el-image style="width: 120px; height: 120px;vertical-align: middle;border-radius: 50%;float: left;margin: 15px 10px"
                  :src="imgS4"
                  fit="fill"></el-image>
        <div class="list_info">
          <p> <span class="title">喜欢的商品:</span> <span class="num">0</span></p>
          <p> <span>喜欢的商品></span></p>
        </div>
      </div>

    </div>
    <div id="myChart" v-show="false" :style="{width: '300px', height: '300px'}"></div>
  </div>
</template>

<script>
    // <!--用户中心-->
    import {reqGetUserInfo} from "../../../api/user";

    export default {
        name: "UserCenter",
        data () {
            return {
                msg: 'Welcome to Your Vue.js App',
                imgS: require('../../../assets/image/head.jpg'),
                imgS1: require('../../../assets/image/icon1.png'),
                imgS2: require('../../../assets/image/icon2.png'),
                imgS3: require('../../../assets/image/icon3.png'),
                imgS4: require('../../../assets/image/icon4.png'),
                user:{
                    id: null,
                    account: "",
                    name: "",
                    gender: "",
                    enable: false,
                    info: "",
                    imgUrl: "",
                    registerTime: null,
                }
            }
        },
        mounted(){
        },
        created(){
            this.initUserCenter();
        },
        methods: {
            initUserCenter(){
                reqGetUserInfo(this.$store.getters.getUser.account).then(response=>{
                    console.log(response);
                    if(response.code==200){
                        this.user = response.user;
                    }else{
                        this.$message({
                            message: response.message,
                            type: "warning"
                        })
                    }
                }).catch(err=>{
                    this.$message({
                        message: "获取数据错误了，请检查网络是否连接",
                        type: "warning"
                    })
                })
            },
            //操作表格
            gotoModUserInfo(){
                this.$router.push({
                    path: "/user/userInfo",
                })
            },
        }
    }
</script>

<style scoped>

  .content{
    width: 1000px;
    margin: 40px auto;
    background-color: white;
  }

  .info{
    width: 940px;
    height: 200px;
    margin: 0px auto;
    background-color: #ffffff;
    border-bottom: 1px solid #d9d9d9;
  }

  .user_card{
    width: 540px;
    height: 200px;
    float: left;
  }

  .user_card_info{
    margin: 75px 0px;
    width: 200px;
    height: 100px;
    display: inline-block;
    line-height: 30px;
  }
  .user_action{
    float: left;
    width: 400px;
    height: 200px;
    padding-top: 65px;
  }
  .link{
    font-size: 14px;
    color: #ff6700;
  }
  span{
    color: #757575;
    font-size: 15px;
    margin: 0px 5px;
    line-height: 30px;
  }

  .portal-sub{
    padding-top: 20px;
    width: 940px;
    height: 400px;
    margin: 0px auto;
  }

  .info-list{
    margin: 15px 4px;
    box-sizing: border-box;
    width: 460px;
    height: 150px;
    float: left;
  }
  .list_info{
    width: 300px;
    height: 150px;
    padding: 40px 10px;
    display: inline-block;
  }
  .num{
    color: #ff6700;
  }
  .title{
    font-size: 20px;
    color: #757575;
  }
</style>

<template>
  <div class="content">
    <h1>我的服务单</h1>
    <div class="box_info">
      <div class="tab_box">
        <div class="order_list">
          <div class="order_summary">
            <p class="order_status">已收货</p>
            <p class="caption-info">
              2019年09月03日 22:46
              <span>|</span>
              黄龙
              <span>|</span>
              订单号：5190903913608409
            </p>
          </div>
          <div class="bookInfo">
            <div class="book_item">
              <el-image class="bookImg" :src="imgS" fit="fill"></el-image>
              <div class="book_detail">
                <p>平凡的世界 路遥</p>
                <p>图书isbn号：6878797688</p>
              </div>
            </div>
            <div class="book_action">
              <button class="plainBtn">服务单详情</button>
            </div>
          </div>
        </div>
        <div class="order_list"></div>
        <div class="order_list"></div>
        <div class="order_list"></div>
        <div class="order_list"></div>
      </div>
    </div>
    <div style="margin: 10px 0px 20px;width: 100%">
      <div class="block" style="float: right">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20, 50]"
          :page-size="page_size"
          background
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
    // <!--用户订单页面-->
    export default {
        name: "UserOrder",
        data() {
            return {
                activeName: 'first',
                imgS: require('../../../assets/image/head.jpg'),
                currentPage: 1,
                page_size: 5,
                total:20,
            };
        },
        methods: {
            handleClick(tab, event) {
                console.log(tab, event);
            },
            //对书单列表的分页函数
            handleSizeChange(val) {
                // console.log(`每页 ${val} 条`);
                this.page_size = val;
                // this.GetTopic(1,this.page_size);
            },
            handleCurrentChange(val) {
                // console.log(`当前页: ${val}`);
                this.currentPage = val;
            },
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
  .tab_box{
    width:960px;
  }

  .order_list{
    border: 1px #f3f0e9 solid;
    margin: 12px auto;
    width: 960px;
    height: 250px;
    max-height: 240px;
    line-height: 35px;
    border: 1px solid #83c44e;
  }
  .order_summary{
    width: 958.5px;
    height: 105px;
    background-color: #fbfff6;
    padding: 15px 25px;
    border-bottom: 0.5px solid #a6c49c;
  }
  .order_status{
    font-size: 22px;
    color: #757575;
  }
  .caption-info{
    font-size: 14px;
    color: #757575;
  }
  .money{
    font-size: 23px;
    color: black;
  }
  .bookInfo{
    width: 960px;
    height: 145px;
    padding: 15px 25px;
  }
  .book_item{
    width: 660px;
    height: 105px;
    float: left;
  }
  .bookImg{
    float: left;
    width: 80px;
    height: 105px;
  }
  .book_detail{
    margin: 25px 20px;
    height: 55px;
    line-height: 27.5px;
    float: left;
    font-size: 14px;
  }
  .book_action{
    width: 250px;
    font-size: 14px;
    color: #757575;
    float: left;
    height: 105px;
  }
  .plainBtn{
    width: 120px;
    height: 30px;
    margin: 2px 0px;
    background-color: white;
    color: #757575;
    border: 1px #cacaca solid;
    float: right;
  }
  .plainBtn:hover{
    border: 1px #393a42 solid;
    background-color: #e7e7e7;
  }

</style>

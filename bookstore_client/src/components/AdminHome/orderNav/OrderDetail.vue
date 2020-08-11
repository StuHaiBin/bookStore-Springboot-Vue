<template>
  <div class="content">
    <div class="step">
      <el-steps :active="active" finish-status="success" align-center>
        <el-step title="已付款" :description="order.orderTime"></el-step>
        <el-step title="已发货"></el-step>
        <el-step title="已收货"></el-step>
        <el-step title="已评价"></el-step>
      </el-steps>
    </div>
    <div class="detail_title">
      <span style="color: red">当前订单状态：{{order.orderStatus}}</span>
      <span style="float: right">
        <el-button size="small">删除订单</el-button>
        <el-button size="small">备注订单</el-button>
      </span>
    </div>
    <div class="info">
      <div class="info_title">
        <i class="el-icon-s-flag"></i><span> 基本信息</span>
      </div>
      <div class="table-layout">
        <el-row>
          <el-col :span="7" class="table-cell-title">订单编号</el-col>
          <el-col :span="6" class="table-cell-title">发货单流水号</el-col>
          <el-col :span="6" class="table-cell-title">用户账号</el-col>
          <el-col :span="5" class="table-cell-title">下单时间</el-col>
        </el-row>
        <el-row>
          <el-col :span="7" class="table-cell">{{order.orderId}}</el-col>
          <el-col :span="6" class="table-cell">{{order.logisticsNum}}</el-col>
          <el-col :span="6" class="table-cell">{{order.account}}</el-col>
          <el-col :span="5" class="table-cell">{{order.orderTime}}</el-col>
        </el-row>
      </div>

      <div class="info_title">
        <i class="el-icon-s-flag"></i><span> 收货人信息</span>
      </div>
      <div class="table-layout">
        <el-row>
          <el-col :span="4" class="table-cell-title">收货人姓名</el-col>
          <el-col :span="4" class="table-cell-title">电话</el-col>
          <el-col :span="13" class="table-cell-title">地址</el-col>
          <el-col :span="3" class="table-cell-title">标签</el-col>
        </el-row>
        <el-row>
          <el-col :span="4" class="table-cell">{{order.address.name}}</el-col>
          <el-col :span="4" class="table-cell">{{order.address.phone}}</el-col>
          <el-col :span="13" class="table-cell">{{order.address.addr}}</el-col>
          <el-col :span="3" class="table-cell">{{order.address.label}}</el-col>
        </el-row>
      </div>

      <div class="info_title">
        <i class="el-icon-s-flag"></i><span> 商品信息</span>
      </div>
      <div class="table-layout">
        <el-table
          ref="multipleTable"
          border
          :data="order.orderDetailDtoList"
          tooltip-effect="dark"
          style="width: 100%"
          >
          <el-table-column
            label="图书封面"
            width="110"
            align="center">
            <template slot-scope="scope">
              <el-image style="height: 120px"
                        :src="scope.row.book.coverImg">
                <div slot="error" class="image-slot">
                  <i class="el-icon-picture-outline"></i>
                </div>
              </el-image>
            </template>
          </el-table-column>
          <el-table-column
            prop="book.bookName"
            label="书名"
            align="center">
          </el-table-column>
          <el-table-column
            prop="book.isbn"
            label="isbn"
            align="center">
          </el-table-column>
          <el-table-column
            prop="book.author"
            label="作者"
            align="center">
          </el-table-column>
          <el-table-column
            label="单价"
            align="center">
            <template slot-scope="scope">
              {{ scope.row.price}}元
            </template>
          </el-table-column>
          <el-table-column
            prop="num"
            width="100px"
            label="数量"
            align="center">
          </el-table-column>
          <el-table-column
            label="小计"
            width="120" align="center">
            <template slot-scope="scope">
              {{ scope.row.price * scope.row.num}}元
            </template>
          </el-table-column>
        </el-table>
      </div>

      <div class="info_title">
        <i class="el-icon-s-flag"></i><span> 费用信息</span>
      </div>
      <div class="table-layout">
        <el-row>
          <el-col :span="4" class="table-cell-title">商品总价</el-col>
          <el-col :span="4" class="table-cell-title">运费</el-col>
          <el-col :span="4" class="table-cell-title">优惠券</el-col>
          <el-col :span="4" class="table-cell-title">活动优惠</el-col>
          <el-col :span="4" class="table-cell-title">订单总金额</el-col>
          <el-col :span="4" class="table-cell-title">实付金额</el-col>
        </el-row>
        <el-row>
          <el-col :span="4" class="table-cell">{{order.expense.productTotalMoney}}</el-col>
          <el-col :span="4" class="table-cell">{{order .expense.freight}}</el-col>
          <el-col :span="4" class="table-cell">{{order.expense.coupon}}</el-col>
          <el-col :span="4" class="table-cell">{{order.expense.activityDiscount}}</el-col>
          <el-col :span="4" class="table-cell">{{order.expense.allPrice}}</el-col>
          <el-col :span="4" class="table-cell">{{order.expense.finallyPrice}}</el-col>
        </el-row>
      </div>

      <div class="info_title">
        <i class="el-icon-s-flag"></i><span> 操作者信息</span>
      </div>
      <div class="table-layout">
        <el-table
          ref="multipleTable"
          border
          :data="operatorList"
          tooltip-effect="dark"
          style="width: 100%"
        >
          <el-table-column
            label="操作者"
            width="110"
            align="center">
            <template slot-scope="scope">
              {{scope.row.name}}
            </template>
          </el-table-column>
          <el-table-column
            label="操作时间"
            align="center">
            <template slot-scope="scope">
              {{scope.row.operateTime}}
            </template>
          </el-table-column>
          <el-table-column
            label="订单状态"
            align="center">
            <template slot-scope="scope">
              {{scope.row.orderStatus}}
            </template>
          </el-table-column>
          <el-table-column
            label="发货状态"
            align="center">
            <template slot-scope="scope">
              {{scope.row.logisticsStatus}}
            </template>
          </el-table-column>
          <el-table-column
            label="备注"
            align="center">
            <template slot-scope="scope">
              {{scope.row.remark}}
            </template>
          </el-table-column>
        </el-table>
      </div>

    </div>
  </div>
</template>

<script>
    import {reqAdminGetOrderDetail} from "../../../api/order";

    export default {
        name: "OrderDetail",
        data(){
            return{
                active: 2,
                operatorList:[
                    {
                        name: "黄龙",
                        operateTime:null,
                        orderStatus: null,
                        logisticsStatus: null,//发货状态
                        remark:null,//备注
                    }
                ],
                order:{
                    id:null,
                    orderId:null,
                    account:null,
                    orderTime:null,
                    shipTime:null,
                    getTime:null,
                    evaluateTime:null,
                    closeTime:null,
                    confirmTime:null,
                    orderStatus:null,
                    logisticsNum:null,
                    orderDetailDtoList:[
                        {
                            book:{
                                id: null,
                                bookName: '',
                                author: '',
                                isbn: '',
                                publish: '',
                                birthday: '',
                                marketPrice: '',
                                price: '',
                                stock: '',
                                description: '',
                                put: true,
                                coverImg: '',
                                rank: '',
                            },
                            num:1,
                            price: 0.0,
                        }
                    ],
                    expense:{
                        orderId:null,//订单编号
                        productTotalMoney:null,//商品总价
                        freight:null,//运费 默认为0元
                        coupon:null,//优惠券 默认为0元
                        activityDiscount:null,//活动优惠 默认为0元
                        allPrice:null,//订单总金额
                        finallyPrice:null,//最终实付总额
                    },
                    address:{
                        id: 1,
                        account: "黄小龙",
                        name: "小胖",
                        phone: "18988798892",
                        addr: "江西抚州市临川区西大街街道东华理工大学长江学院本部(330006)",
                        label: "家",
                        off: false,
                    }
                }
            }
        },
        created() {
            let id = this.$route.query.id;
            console.log("============"+id+"==========")
            this.getOrderList();
        },
        methods:{
            next() {
                if (this.active++ > 2) this.active = 0;
            },
            getOrderList(){
                let id = this.$route.query.id;
                this.loading=false;
                reqAdminGetOrderDetail(id).then(response=>{
                    if(response.code==200){
                        console.log(response.orderDto);
                        this.order = response.orderDto;
                    }else {
                        this.$message({
                            message: "获取订单明细出错了！",
                            type: "warning"
                        })
                    }
                }).catch(err=>{
                    console.log(err);
                })
            },
        }
    }
</script>

<style scoped>
  .content{
    margin: 0px auto;
    width: 100%;
    box-sizing: border-box;
  }
  .step{
    width: 900px;
    height: 100px;
    margin: 0px auto;
  }
  .detail_title{
    width: 940px;
    height: 90px;
    margin: 0px auto;
    line-height: 90px;
    background-color: #f2f6fc;
    border: 1px solid #dcdcdc;
    border-bottom: none;
    padding: 0px 15px;
  }
  .info{
    width: 940px;
    margin: 0px auto;
    padding: 0px 20px 20px;
    border: 1px solid #dcdcdc;
    border-top: none;
  }
  .info_title{
    width: 100%;
    height: 60px;
    margin: 0px auto;
    line-height: 60px;
    padding: 0px 15px;
  }
  .table-layout {
    border-left: 1px solid #DCDFE6;
    border-top: 1px solid #DCDFE6;
  }
  .table-cell {
    height: 60px;
    line-height: 40px;
    border-right: 1px solid #DCDFE6;
    border-bottom: 1px solid #DCDFE6;
    padding: 10px;
    font-size: 14px;
    color: #606266;
    text-align: center;
    overflow: hidden;
  }

  .table-cell-title {
    border-right: 1px solid #DCDFE6;
    border-bottom: 1px solid #DCDFE6;
    padding: 10px;
    background: #F2F6FC;
    text-align: center;
    font-size: 14px;
    color: #303133;
  }
</style>

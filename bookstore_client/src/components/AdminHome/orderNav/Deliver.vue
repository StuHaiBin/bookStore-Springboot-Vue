<template>
  <div class="content">
    <el-card class="box-card" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>发货列表</span>
    </el-card>
    <div class="table_content">
      <el-table
        ref="multipleTable"
        border
        :data="order"
        tooltip-effect="dark"
        style="width: 100%"
      >
        <el-table-column
          label="订单编号"
          width="320"
          align="center">
          <template slot-scope="scope">
            {{scope.row.orderId}}
          </template>
        </el-table-column>
        <el-table-column
          label="收货人"
          width="100"
          align="center">
          <template slot-scope="scope">
            {{scope.row.address.name}}
          </template>
        </el-table-column>
        <el-table-column
          label="手机号"
          width="170"
          align="center">
          <template slot-scope="scope">
            {{scope.row.address.phone}}
          </template>
        </el-table-column>
        <el-table-column
          label="收货地址"
          align="center">
          <template slot-scope="scope">
            {{scope.row.address.addr}}
          </template>
        </el-table-column>
        <el-table-column
          label="配送方式"
          align="center">
          <template slot-scope="scope">
            <el-select v-model="finalDelivery" placeholder="请选择物流公司">
              <el-option
                v-for="item in deliverySort"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column
          label="物流单号"
          align="center">
          <template slot-scope="scope">
            <el-input v-model="logisticsNum"></el-input>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="table_content" style="text-align: center">
      <el-button
        style="margin-left: 10px"
        size="medium">
        取消
      </el-button>
      <el-button
        style="margin-left: 10px"
        type="primary"
        @click="deliverOrder"
        size="medium">
        确定
      </el-button>
    </div>
  </div>
</template>

<script>
    import {reqAdminGetOrderDetail,reqDeliverOrder} from "../../../api/order";
    export default {
        name: "Deliver",
        data(){
            return{
                order:[
                    {
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
                    },
                ],
                finalDelivery:1,
                deliverySort:[
                    {
                        id: 1,
                        name: "圆通快递"
                    },
                    {
                        id: 2,
                        name: "中通快递"
                    },
                    {
                        id: 3,
                        name: "顺丰快递"
                    },
                    {
                        id: 4,
                        name: "韵达快递"
                    },
                ],
                logisticsNum: "",//快递单号
            }
        },
        created:function () {
          this.getOrderList();
        },
        methods: {
            //获取快递到分类的分类值
            handleChange(bookSort) {
                console.log("图书的分类是:"+bookSort[0]);
            },
            getOrderList(){
                let id = this.$route.query.id;
                this.loading=false;
                reqAdminGetOrderDetail(id).then(response=>{
                    if(response.code==200){
                        console.log(response.orderDto);
                        // this.order = response.orderDto;
                        this.order = [];
                        this.order.push(response.orderDto);
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
            deliverOrder(){
                let id = this.$route.query.id;
                reqDeliverOrder(id,this.finalDelivery,this.logisticsNum).then(response=>{
                    if(response.code==200){
                        this.$message({
                            message: response.message,
                            type: "success"
                        })
                    }else {
                        this.$message({
                            message: response.message,
                            type: "warning"
                        })
                    }
                }).catch(err=>{
                    this.$message({
                        message: "发货失败！",
                        type: "warning"
                    })
                })
            },
        }

    }
</script>

<style scoped>
  .content{
    margin: 0px auto;
    width: 100%;
  }
  .box-card{
    margin-top: 20px;
    width: 100%;
  }
  .table_content{
    width: 100%;
    margin-top: 20px;
  }
  p{
    line-height: 40px;
  }


</style>

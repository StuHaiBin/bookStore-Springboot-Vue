<template>
  <div class="content">
    <el-card class="box-card" shadow="never">
      <div>
        <i class="el-icon-search"></i>
        <span>筛选搜索</span>
        <el-button
          style="float: right"
          type="primary"
          size="small">
          查询
        </el-button>
        <el-button
          style="float: right;margin-right: 15px"
          size="small">
          重置
        </el-button>
      </div>
      <div style="margin-top: 25px">
        <el-form :inline="true" :model="search" size="small" label-width="140px">
          <el-form-item label="订单编号">
            <el-input v-model="search.bookName"></el-input>
          </el-form-item>
          <el-form-item label="用户账号">
            <el-input v-model="search.isbn"></el-input>
          </el-form-item>
          <el-form-item label="订单状态">
            <el-select v-model="book.publish" placeholder="请选择出版社" prop="publish">
              <el-option
                v-for="item in publishList"
                :key="item"
                :label="item"
                :value="item"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <el-card class="box-card" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>数据列表</span>
    </el-card>

    <div class="table_content">
      <el-table
        ref="multipleTable"
        border
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="55"
          align="center"
        >
        </el-table-column>
        <el-table-column
          label="编号"
          width="70"
          align="center">
          <template slot-scope="scope">{{ scope.row.id }}</template>
        </el-table-column>
        <el-table-column
          label="订单号"
          align="center">
          <template slot-scope="scope">{{ scope.row.orderId }}</template>
        </el-table-column>
        <el-table-column
          label="提交时间"
          width="170"
          align="center">
          <template slot-scope="scope">{{ scope.row.orderTime }}</template>
        </el-table-column>
<!--        h.format("yyyy-MM-dd hh:mm:ss")-->
        <el-table-column
          prop="account"
          label="用户账号"
          width="190"
          align="center">
        </el-table-column>
        <el-table-column
          label="订单金额"
          width="90"
          align="center">
          <template slot-scope="scope">{{ scope.row.expense.finallyPrice }}元</template>
        </el-table-column>
        <el-table-column
          prop="orderStatus"
          label="订单状态"
          width="90"
          align="center">
        </el-table-column>
        <el-table-column
          label="操作"
          width="200"
          align="center">
          <template slot-scope="scope">
            <p>
              <el-button
                size="mini"
                @click="goToOrderDetail(scope.$index,scope.row)"
              >查看订单
              </el-button>
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)"
              >删除订单
              </el-button>
            </p>
            <p>
              <el-button
                size="mini"
                @click="goToDeliver(scope.$index,scope.row)"
              >订单发货
              </el-button>
              <el-button
                size="mini"
                @click="handleDelete(scope.$index, scope.row)"
              >订单跟踪
              </el-button>
            </p>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div style="margin-top: 20px;width: 100%;">
<!--      <div style="float: left;padding: 0px 0px 10px">-->
<!--        <el-select v-model="operator" placeholder="批量操作">-->
<!--          <el-option-->
<!--            v-for="item in operates"-->
<!--            :key="item.value"-->
<!--            :label="item.label"-->
<!--            :value="item.value">-->
<!--          </el-option>-->
<!--        </el-select>-->
<!--        <el-button-->
<!--          style="margin-left: 10px"-->
<!--          @click="operatorBook"-->
<!--          type="primary"-->
<!--          size="medium">-->
<!--          确定-->
<!--        </el-button>-->
<!--      </div>-->
      <div class="block" style="float: right;padding: 0px 0px 10px">
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
    import {reqGetPublishNames} from "../../../api/publish";
    import {reqAdminGetOrderList,reqDelOrder} from "../../../api/order";
    import axios from 'axios';
    export default {
        name: "OrderList",
        data(){
            return{
                loading: true,
                currentPage: 1,
                page_size: 5,

                orderList:{
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
                    OrderDetailDtoList:[],
                    expense:{
                        orderId:null,//订单编号
                        productTotalMoney:null,//商品总价
                        freight:null,//运费 默认为0元
                        coupon:null,//优惠券 默认为0元
                        activityDiscount:null,//活动优惠 默认为0元
                        allPrice:null,//订单总金额
                        finallyPrice:null,//最终实付总额
                    },
                    address:null
                },

                tableData: [],
                total:null,
                book: {
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
                    ImgSrc: [],
                    put: true,
                    coverImg: '',
                    rank: '',
                    newProduct: true,
                    recommend: true,
                    bookSort: [],//图书分类的绑定值
                },
                search: {
                    bookName: '',
                    author: '',
                    isbn: '',
                    publish: '',
                    put: '',
                    bookSort: '',
                },
                publishList: [],//出版社下拉选择器
                options: [],//图书分类的联机选择器

                operator: null,
                //批量操作
                operates: [
                    {
                        label: "批量删除",
                        value: "del"
                    },
                    {
                        label: "批量上架",
                        value: "put"
                    },
                    {
                        label: "批量下架",
                        value: "putOff"
                    },
                    {
                        label: "设为推荐",
                        value: "recommend"
                    },
                    {
                        label: "取消推荐",
                        value: "recommendOff"
                    },
                    {
                        label: "设为新品",
                        value: "newProduct"
                    },
                    {
                        label: "取消新品",
                        value: "newProductOff"
                    }
                ],
                multipleSelection: []
            }
        },
        created:function () {
            this.getPublishName();
            this.getOrderList(1,5);
            console.log("init起作用了！")
        },
        methods: {
            //处理选项框的操作，获取表格中哪些选项被选中
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            operatorBook(){
                if(this.multipleSelection.length<1){
                    this.$message({
                        type: 'warning',
                        message: "请至少选择一项进行操作"
                    })
                }else{
                    let dataList = [];
                    for(let i=0;i<this.multipleSelection.length;i++){
                        dataList.push(this.multipleSelection[i].id);
                    }
                    console.log(this.operator);
                    let formData = new FormData();
                    formData.append("ids", dataList);
                    formData.append("operator",this.operator);
                    axios({
                        method: 'POST',
                        url: 'http://localhost:8082/batchDel',
                        data: formData
                    }).then((response) => {
                        if(response.data.code==200){
                            this.$message({
                                message: response.data.message,
                                type: "success"
                            })
                        }else{
                            this.$message({
                                message: response.data.message,
                                type: "warning"
                            })
                        }
                        this.GetSort(this.currentPage,this.page_size);
                    }).catch(err=>{
                        console.log("出错了！")
                    })
                    switch (this.operator) {
                        case "del":
                            console.log(this.operator);
                            break;
                        case "put":
                            console.log(this.operator);
                            break;
                        case "putOff":
                            console.log(this.operator);
                            break;
                        case "recommend":
                            console.log(this.operator);
                            break;
                        case "recommendOff":
                            console.log(this.operator);
                            break;
                        case "newProduct":
                            console.log(this.operator);
                            break;
                        case "newProductOff":
                            console.log(this.operator);
                            break;
                        default:
                            console.log("至少需要选择一项");
                    }
                }
            },


            //获取图书的分类值
            handleChange(bookSort) {
                console.log("图书的分类是:"+bookSort[0],bookSort[1]);
            },


            //分页函数
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.page_size = val;
                this.getOrderList(1,this.page_size);
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.currentPage = val;
                console.log(this.currentPage+":"+this.page_size);
                this.getOrderList(this.currentPage,this.page_size);
            },
            //得到订单列表
            getOrderList(page,pageSize){
                this.loading=false;
                reqAdminGetOrderList(page,pageSize).then(response=>{
                    if(response.code==200){
                        this.total = response.total;
                        console.log(this.total);
                        this.tableData = response.orderDtoList;
                    }else {
                        this.$message({
                            message: response.message,
                            type: "warning"
                        })
                    }
                }).catch(err=>{
                    console.log(err);
                })
            },


            //操作表格
            updateBook(index,row){
                console.log("row.id:"+row.isbn);
                this.$router.push({
                    path: "/admin/updateBook",
                    query: {
                        id: row.id
                    }
                })
            },

            //跳转到订单明细页面
            goToOrderDetail(index,row) {
                this.$router.push({
                    path:'/admin/orderDetail',
                    query: {
                        id: row.id
                    }
                });
            },
            //跳转到发货页面
            goToDeliver(index,row) {
                this.$router.push({
                    path:'/admin/deliver',
                    query: {
                        id: row.id
                    }
                });
            },

            //处理删除函数
            handleDelete(index, row){
                this.$confirm('是否要进行删除操作?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    reqDelOrder(row.id).then(response=>{
                        console.log(response);
                        if(response.code==200){
                            this.$message({
                                message: response.message,
                                type: "success"
                            })
                        }else{
                            this.$message({
                                message: response.message,
                                type: "warning"
                            })
                        }
                        this.getOrderList(this.currentPage,this.page_size);
                    }).catch(err=>{
                        console.log(err);
                    })
                }).catch(()=>{
                    this.$message({
                        message: "删除订单失败",
                        type: "warning"
                    })
                });
            },


            //得到并设置出版的下拉选择器
            getPublishName(){
                reqGetPublishNames().then(response=>{
                    console.log(response);
                    this.publishList=response.publishList;
                }).then(err=>{
                    console.log(err);
                })
            }
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

  /deep/ .el-input__inner {
    padding-right: 0px;
  }

</style>

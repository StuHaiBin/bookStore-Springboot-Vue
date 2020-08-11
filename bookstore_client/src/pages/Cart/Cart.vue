<template>
  <div class="content">
    <Nav></Nav>
    <HeadNav></HeadNav>
    <div class="table_content">
      <el-table
        ref="multipleTable"
        :data="cartList"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="80"
          align="center"
        >
        </el-table-column>
        <el-table-column
          width="110"
          align="center">
          <template slot-scope="scope">
            <el-image style="height: 120px"
                      :src="scope.row.coverImg">
              <div slot="error" class="image-slot">
                <i class="el-icon-picture-outline"></i>
              </div>
            </el-image>
          </template>
        </el-table-column>
        <el-table-column
          prop="bookName"
          label="书名"
          align="center">
        </el-table-column>
        <el-table-column
          prop="author"
          label="作者"
          align="center">
        </el-table-column>
        <el-table-column
          label="原价/售价"
          width="160" align="center">
          <template slot-scope="scope">
            原价：{{ scope.row.marketPrice }}元<br>
            售价：{{ scope.row.price }}元
          </template>
        </el-table-column>
        <el-table-column
          prop="author"
          width="230"
          label="数量"
          align="center">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.num" @change="modifyCart(scope.$index,scope.row)" :min="1" :max="20" label="描述文字"></el-input-number>
          </template>
        </el-table-column>
        <el-table-column
          label="小计"
          width="130"
          align="center">
          <template slot-scope="scope">
            <p style="color: red">{{scope.row.price * scope.row.num}}元</p>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          width="130"
          align="center">
          <template slot-scope="scope">
              <i class="el-icon-delete" style="font-size: 25px;" @click="handleDelete(scope.$index,scope.row)"></i>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="buyBox">
      <div style="float: left;margin-left: 10px">
        继续购物
      </div>
      <div style="float: right">
        <el-button
          class="buyBtn"
          type="primary"
          @click="goBuyPage">
          去购买
        </el-button>
      </div>
    </div>
    <div style="height: 40px;width: 10px"></div>
    <Footer></Footer>
  </div>
</template>

<script>
    import Nav from "../../components/Common/Nav";
    import HeadNav from "../../components/Common/HeadNav";
    import Footer from "../../components/Common/Footer";
    import {reqGetCartList,reqBatchDelCart,reqModCart,reqDelCart} from "../../api/cart";


    export default {
        name: "BookTopic",
        components:{Nav,HeadNav,Footer},

        data(){
            return{
                account: "",
                cartList:[{
                    coverImg: "static/image/bookdefault.jpg",
                    bookName: '平凡的世界',
                    author: '路遥',
                    marketPrice:200,
                    price: 180,
                    num:10,
                    sales: 1000
                },{
                    coverImg: "static/image/bookdefault.jpg",
                    bookName: '平凡的世界',
                    author: '路遥',
                    marketPrice:200,
                    price: 180,
                    num:10,
                    sales: 1000
                },{
                    coverImg: "static/image/bookdefault.jpg",
                    bookName: '平凡的世界',
                    author: '路遥',
                    marketPrice:200,
                    price: 180,
                    num:10,
                    sales: 1000
                },{
                    coverImg: "static/image/bookdefault.jpg",
                    bookName: '平凡的世界',
                    author: '路遥',
                    marketPrice:200,
                    price: 180,
                    num:10,
                    sales: 1000
                },{
                    coverImg: "static/image/bookdefault.jpg",
                    bookName: '平凡的世界',
                    author: '路遥',
                    marketPrice:200,
                    price: 180,
                    num:10,
                    sales: 1000
                },{
                    coverImg: "static/image/bookdefault.jpg",
                    bookName: '平凡的世界',
                    author: '路遥',
                    marketPrice:200,
                    price: 180,
                    num:10,
                    sales: 1000
                },{
                    coverImg: "static/image/bookdefault.jpg",
                    bookName: '平凡的世界',
                    author: '路遥',
                    marketPrice:200,
                    price: 180,
                    num:10,
                    sales: 1000
                },
                ],
                multipleSelection: []
            }
        },
        created:function () {
            this.account = this.$store.getters.getUser.account;
            this.getCartList();
        },
        methods: {
            //处理选项框的操作，获取表格中哪些选项被选中
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },

            //处理购物车数量变化 也就是修改购物车图书数量
            modifyCart(index, row) {
                reqModCart(this.account,row.id,row.num).then(response=>{
                    if(response.code==200){
                        //修改成功，这里不给提示，体验感更好
                    }else{
                        this.$message({
                            message: response.message,
                            type: "warning"
                        })
                    }
                }).catch(err=>{
                    this.$message({
                        message: "修改失败",
                        type: "warning"
                    })
                })
            },


            //处理删除函数
            handleDelete(index, row){
                this.$confirm('是否要进行删除操作?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    reqDelCart(this.account,row.id).then(response=>{
                        if(response.code==200){
                            this.$message({
                                message: response.message,
                                type: "success"
                            })
                            this.getCartList();
                        }else{
                            this.$message({
                                message: response.message,
                                type: "warning"
                            })
                        }
                    }).catch(err=>{
                        this.$message({
                            message: "删除失败",
                            type: "warning"
                        })
                    })
                }).catch(()=>{
                    console.log("取消删除了");
                });
            },

            getCartList(){
                console.log("====this.account===="+this.account+"========")
                reqGetCartList(this.account,1,10).then(response=>{
                    if(response.code==200){
                        this.cartList = response.cartBookDtoList;
                    }else{
                        this.$message({
                            message: response.message,
                            type: "warning"
                        })
                    }
                }).catch(err=>{
                    this.$message({
                        message: "获取购物车信息出错",
                        type: "warning"
                    })
                })
            },

            goBuyPage(){
                if(this.multipleSelection.length>0){
                    let arr = [];
                    for(let i=0;i<this.multipleSelection.length;i++){
                        arr.push(this.multipleSelection[i].id);
                    }
                    arr.push(1);
                    let ids = JSON.stringify(arr);
                    this.$router.push({
                        path: "/buyPage",
                        query: {
                            ids: ids
                        }
                    })
                }else{
                    this.$message({
                        message: "请至少选择一件商品进行购买",
                        type: "warning"
                    })
                }
            },
        }

    }
</script>

<style scoped>
  .content{
    background-color: #f7f7f6;
  }
  .table_content{
    width: 1240px;
    margin: 40px auto;
  }
  .buyBox{
    margin: 0px auto;
    width: 1240px;
    background-color: white;
    height: 52px;
    line-height: 52px;
  }
  .buyBtn{
    width: 200px;
    height: 50px;
    background-color: #9d232c;
    border: none;
  }
</style>


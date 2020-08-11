<template>
    <div style="background-color: #f7f7f6;width: 100%;min-width: 1240px">
      <Nav></Nav>
      <HeadNav></HeadNav>
      <div class="content">
        <div class="title"><h3>收货地址</h3></div>
        <div class="list_box">
<!--           v-bind:class="{selected_address:address.select}"-->
          <div class="address_list" v-for="(address,index) in OrderInitDto.addressList"
               v-bind:class="{selected_address:address.select}"
               @click="selectOrderAddress(index,address)"
               :key="address.id">
            <div class="name">{{address.name}}
              <span style="float: right;font-size: 14px;color: #757575;">{{address.label}}</span>
            </div>
            <div class="tel">{{address.phone}}</div>
            <div class="detail">{{address.addr}}</div>
            <div class="foot">
              <span style="float: right" @click="handleMod(address)">修改</span>
            </div>
          </div>
          <div class="address_list">
            <div class="add" @click="handleAdd">
              <i class="el-icon-edit"></i>
              <p style="font-size: 14px">添加新地址</p>
            </div>
          </div>
        </div>

        <div class="title"><h3>商品清单</h3></div>
        <div class="list_box" v-for="book in OrderInitDto.bookList">
          <div class="bookInfo">
            <div class="book_item">
              <el-image class="bookImg" :src="book.coverImg" fit="fill"></el-image>
              <div class="book_detail">
                <p>{{book.bookName}} {{book.author}}</p>
              </div>
            </div>
            <div class="book_num">
              <p>{{book.price}} x {{book.num}}</p>
            </div>
            <div class="book_money">
              <p class="height_text">{{book.price * book.num}}元</p>
            </div>
          </div>
        </div>
        <div class="title"><h3 style="float: left;width: 100px">配送方式</h3><span class="height_text" style="margin-left: 60px">包邮</span></div>
        <div class="title"><h3 style="float: left;width: 100px">发票</h3><span class="height_text" style="margin-left: 60px">电子普通发票个人商品明细</span></div>
        <div class="list_box">
          <div class="order_detail">
<!--            <div class="bill_item">-->
<!--              <div class="bill_title">商品件数:</div>-->
<!--              <div class="bill_money height_text">1件</div>-->
<!--            </div>-->
            <div class="bill_item">
              <div class="bill_title">商品总价:</div>
              <div class="bill_money height_text">{{OrderInitDto.expense.productTotalMoney}}元</div>
            </div>
            <div class="bill_item">
              <div class="bill_title">活动优惠:</div>
              <div class="bill_money height_text">-{{OrderInitDto.expense.activityDiscount}}元</div>
            </div>
            <div class="bill_item">
              <div class="bill_title">优惠卷抵扣:</div>
              <div class="bill_money height_text">-{{OrderInitDto.expense.coupon}}元</div>
            </div>
            <div class="bill_item">
              <div class="bill_title">运费:</div>
              <div class="bill_money height_text">{{OrderInitDto.expense.freight}}元</div>
            </div>
            <div class="bill_item">
              <div class="bill_title">应付总额:</div>
              <div class="bill_money height_text" style="font-size: 22px">{{OrderInitDto.expense.allPrice}}元</div>
            </div>
          </div>
        </div>
        <div class="list_box">
          <div class="order_action">
            <div class="address_box">
              <p>{{OrderInitDto.address.name}} {{OrderInitDto.address.phone}}</p>
              <p>{{OrderInitDto.address.addr}}<span class="height_text">修改</span></p>
            </div>
            <div class="action">
              <button class="plainBtn" style="background-color: #ff6600;color: white" @click="submitOrder">立刻下单</button>
<!--              <button class="plainBtn">返回购物车</button>-->
            </div>
          </div>
        </div>
      </div>
      <Footer></Footer>


      <!--添加图书的弹出框-->
      <el-dialog title="添加收货地址" :visible.sync="dialogVisible" width="30%"  center>
        <el-form ref="form" :model="address" >
          <el-form-item>
            <el-input placeholder="姓名" v-model="address.name"></el-input>
          </el-form-item>
          <el-form-item>
            <el-input placeholder="手机号" v-model="address.phone"></el-input>
          </el-form-item>
          <el-form-item>
            <el-input type="textarea" placeholder="详细地址" v-model="address.addr"></el-input>
          </el-form-item>
          <el-form-item>
            <el-input placeholder="地址标签" v-model="address.label"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit('form')">确 定</el-button>
      </span>
      </el-dialog>

    </div>
</template>

<script>
    import Nav from "../../components/Common/Nav";
    import HeadNav from "../../components/Common/HeadNav";
    import Footer from "../../components/Common/Footer";
    import {reqGetAddressList,reqAddAddress,reqModAddress} from "../../api/address";
    import {reqInitOrder,reqAddOrder} from "../../api/order";

    export default {
        name: "BuyPage",
        components:{Nav,HeadNav,Footer},
        data(){
            return{
                account: "",
                dialogVisible: false,
                isEdit:false,//用来判断是添加地址还是修改地址 false:添加 true:修改
                selectId:null,
                selectClass:false,
                //添加修改的地址
                address:{
                    id: null,
                    account: "黄小龙",
                    name: "",
                    phone: "",
                    addr: "",
                    label: "",
                },
                OrderInitDto:{
                    account: "",
                    addressList:[
                        {
                            id: 1,
                            account: "黄小龙",
                            name: "小胖",
                            phone: "18988798892",
                            addr: "江西抚州市临川区西大街街道东华理工大学长江学院本部(330006)",
                            label: "家",
                            off: false,
                            select:false,
                        },
                        {
                            id: 2,
                            account: "黄小龙",
                            name: "小胖",
                            phone: "18988798892",
                            addr: "江西抚州市临川区西大街街道东华理工大学长江学院本部(330006)",
                            label: "家",
                            off: false,
                            select: false
                        },
                    ],
                    //选中的地址
                    address:{
                        id: null,
                        account: "黄小龙",
                        name: "",
                        phone: "",
                        addr: "",
                        label: "",
                    },
                    expense:{
                        activityDiscount: null,
                        allPrice: null,
                        coupon: null,
                        finallyPrice: null,
                        freight: null,
                        orderId: null,
                        productTotalMoney: null,
                    },
                    bookList:[{
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
                        newProduct: true,
                        recommend: true,
                        num: 1
                    }]
                },
            }
        },
        created(){
            this.account = this.$store.getters.getUser.account;
            this.address.account = this.$store.getters.getUser.account;
            let list = this.$route.query.ids;
            let idList = JSON.parse(list);
            let ids = [];
            for(let i=0;i<idList.length-1;i++){
                ids.push(idList[i]);
            }
            this.initOrder(ids,idList[idList.length-1],this.account);
        },
        methods:{
            //处理添加操作
            handleAdd(){
                this.dialogVisible = true;
                this.isEdit = false;
            },

            //处理修改地址
            handleMod(addr){
                this.dialogVisible = true;
                this.isEdit = true;
                this.address.id = addr.id;
                this.address.account = addr.account;
                this.address.name = addr.name;
                this.address.phone = addr.phone;
                this.address.addr = addr.addr;
                this.address.label = addr.label;
            },

            //提交处理
            onSubmit(formName) {
                if(this.isEdit){
                    this.modifyAddress();
                }else {
                    this.addAddress();
                }

                // this.$refs[formName].validate((valid)=>{
                //     // console.log(this.publish.isShow);
                //     if(valid){
                //         if(this.isEdit){
                //             this.modifyAddress();
                //         }else {
                //             this.addAddress();
                //         }
                //     }else {
                //         this.$message.error("地址信息不符合要求，请重试");
                //     }
                // });
            },
            //得到用户地址列表
            getAddressList(){
                reqGetAddressList(this.account).then(response=>{
                    console.log(response);
                    if(response.code==200){
                        this.OrderInitDto.addressList = response.addressList;
                        if(this.OrderInitDto.addressList.length>0){
                            console.log(this.OrderInitDto.addressList[0]);
                            this.OrderInitDto.selectAddress = this.OrderInitDto.addressList[0];//设置地址为排序第一的地址
                            this.selectId = this.OrderInitDto.addressList[0].id;//被选中的id
                        }
                        // console.log("===response.addressList.length==="+response.addressList.length);
                    }else{
                        this.$message({
                            message: response.message,
                            type: "warning"
                        })
                    }
                }).catch(err=>{
                    console.log(err);
                })
            },

            //添加地址
            addAddress(){
                reqAddAddress(this.address).then(response=>{
                    console.log(response);
                    if(response.code==200){
                        this.$message({
                            message: response.message,
                            type: "success"
                        });
                        this.dialogVisible = false;
                        this.getAddressList();
                    }else{
                        this.$message({
                            message: response.message,
                            type: "warning"
                        })
                    }
                }).catch(err=>{
                    console.log(err);
                })
            },

            //修改地址
            modifyAddress(){
                reqModAddress(this.address).then(response=>{
                    if(response.code==200){
                        this.$message({
                            message: response.message,
                            type: "success"
                        });
                        this.dialogVisible = false;
                        this.getAddressList();
                    }else{
                        this.$message({
                            message: response.message,
                            type: "warning"
                        })
                    }
                }).catch(err=>{
                    console.log(err);
                })
            },

            //选择订单地址
            selectOrderAddress(index,address){
                for(let i=0;i<this.OrderInitDto.addressList.length;i++){
                    this.OrderInitDto.addressList[i].select=false;
                }
                this.OrderInitDto.addressList[index].select = true;
                this.OrderInitDto.address = address;
            },



            //初始化订单
            initOrder(ids,from,account){
                reqInitOrder(ids,from,this.account).then(response=>{
                    if(response.code==200){
                        console.log("=========OrderInitDto==========="+response.orderInitDto+"==============")
                        this.OrderInitDto.expense = response.orderInitDto.expense;
                        this.OrderInitDto.bookList = response.orderInitDto.bookList;
                        this.OrderInitDto.addressList = response.orderInitDto.addressList;
                        if(this.OrderInitDto.addressList.length>0){
                            this.OrderInitDto.address = this.OrderInitDto.addressList[0];//设置地址为排序第一的地址
                            this.selectId = this.OrderInitDto.addressList[0].id;//被选中的id
                        }
                        console.log(response);
                    }else{
                        this.$message({
                            message: response.message,
                            type: "warning"
                        })
                    }
                }).catch(err=>{
                    this.$message({
                        message: "初始化订单出错了，请检查网络连接",
                        type: "error"
                    })
                })
            },

            //提交订单
            submitOrder(){
                this.OrderInitDto.account = this.$store.getters.getUser.account;
                console.log("====this.OrderInitDto.account===="+this.OrderInitDto.account+"=====")
                reqAddOrder(this.OrderInitDto).then(response=>{
                    if(response.code==200){
                        this.$message({
                            type: 'success',
                            message: response.message,
                            duration: 1000
                        })
                        setTimeout(() => {
                            this.$router.push({path:'/user/userOrder'});
                        }, 1000);
                    }else{
                        this.$message({
                            message: response.message,
                            type: "warning"
                        })
                    }
                }).catch(err=>{
                    this.$message({
                        message: "下单失败了",
                        type: "warning"
                    })
                })
            }


        }
    }
</script>

<style scoped>
  .content{
    width: 1240px;
    margin: 20px auto;
    background-color: white;
    padding-left: 10px;
    /*border: 1px solid #B3C0D1;*/
  }
  .title{
    width: 1230px;
    padding: 10px;
    line-height: 30px;
  }
  .list_box{
    width: 1230px;
    padding: 10px;
  }
  .address_list{
    width: 280px;
    height: 180px;
    border: 1px solid #cacaca;
    display: inline-block;
    margin-right: 18px;
    margin-bottom: 20px;
    padding: 20px;
    vertical-align: top;
  }
  .selected_address{
    border: 1px solid #ff6700;
  }
  .name{
    width: 240px;
    height: 40px;
    font-size: 18px;
  }
  .tel{
    width: 240px;
    height: 30px;
    font-size: 14px;
    color: #757575;
  }
  .detail{
    width: 240px;
    height: 30px;
    font-size: 14px;
    color: #757575;
  }
  .foot{
    width: 240px;
    height: 60px;
    font-size: 14px;
    color: #ff6700;
    line-height: 60px;
  }
  .add{
    width:80px;
    height: 80px;
    margin-top: 40px;
    margin-left: 75px;
    font-size: 30px;
    color: #757575;
    text-align: center;
  }

  .bookInfo{
    width: 1200px;
    height: 125px;
    padding: 10px;
    border-bottom: 1px solid #B3C0D1;
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
    line-height: 55px;
    float: left;
    font-size: 14px;
  }
  .book_num{
    width: 360px;
    font-size: 14px;
    float: left;
    height: 105px;
    line-height: 105px;
  }
  .book_money{
    width: 150px;
    font-size: 14px;
    float: left;
    height: 105px;
    line-height: 105px;
  }
  .height_text{
    color: #ff6700;
  }

  .order_detail{
    margin-left: 940px;
    width: 250px;
    height: 220px;
  }
  .bill_item{
    line-height: 35px;
  }
  .bill_title{
    width: 145px;
    text-align: right;
    float: left;
  }
  .bill_money{
    width: 95px;
    text-align: right;
    float: left;
  }
  .order_action{
    width: 1210px;
    padding-left:20px;
    padding-right: 20px;
    padding-top: 5px;
    border-top: 1px solid #B3C0D1;
    height: 50px;
  }
  .address_box{
    width: 700px;
    height: 50px;
    line-height: 23px;
    float: left;
    font-size: 14px;
    color: #757575;
  }
  .action{
    width: 400px;
    height: 50px;
    float: right;
    vertical-align: center;
    padding-top: 5px;
  }
  .plainBtn{
    width: 120px;
    height: 35px;
    background-color: white;
    color: #757575;
    border: 1px #cacaca solid;
    float: right;
    margin-left: 10px;
  }
  .plainBtn:hover{
    border: 1px #a2a4ac solid;
    background-color: #e7e7e7;
  }
</style>

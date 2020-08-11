<template>
  <div class="content">
    <h1>收货地址</h1>
    <div class="box_info">
      <div class="address_list">
        <div class="add" @click="handleAdd">
          <i class="el-icon-edit"></i>
          <p style="font-size: 14px">添加新地址</p>
        </div>
      </div>
      <div class="address_list" v-for="address in addressList" :key="address.id">
        <div class="name">{{address.name}}
          <span style="float: right;font-size: 14px;color: #757575;">{{address.label}}</span>
        </div>
        <div class="tel">{{address.phone}}</div>
        <div class="detail">{{address.addr}}</div>
        <div class="foot">
          <span style="float: right" @click="delAddress(address.id)">删除</span>
          <span style="float: right;margin-right: 10px" @click="handleMod(address)">修改</span>
        </div>
      </div>
    </div>

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
  import {reqDelAddress,reqAddAddress,reqGetAddressList,reqModAddress} from "../../../api/address";
  // <!--用户地址页面-->
    export default {
        name: "Address",
        data() {
            return {
                dialogVisible: false,
                isEdit:false,//用来判断是添加地址还是修改地址 false:添加 true:修改
                addressList:[
                    {
                        id: 1,
                        account: "黄小龙",
                        name: "小胖",
                        phone: "18988798892",
                        addr: "江西抚州市临川区西大街街道东华理工大学长江学院本部(330006)",
                        label: "家",
                        off: false,
                    },
                    {
                        id: 2,
                        account: "黄小龙",
                        name: "小胖",
                        phone: "18988798892",
                        addr: "江西抚州市临川区西大街街道东华理工大学长江学院本部(330006)",
                        label: "家",
                        off: false,
                    },
                ],
                address:{
                    id: null,
                    account: "黄小龙",
                    name: "",
                    phone: "",
                    addr: "",
                    label: "",
                },
            };
        },
        created(){
            this.address.account = this.$store.getters.getUser.account;
            console.log("=========this.address.account:============"+this.address.account+"===")
            this.getAddressList();
        },
        methods: {
            //处理添加操作
            handleAdd(){
                this.dialogVisible = true;
                this.isEdit = false;
            },
            //处理修改
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
                console.log("===获取的地址列表：==="+this.$store.getters.getUser.account+"=====");
                reqGetAddressList(this.$store.getters.getUser.account).then(response=>{
                    console.log(response);
                    if(response.code==200){
                        this.addressList = response.addressList;
                        console.log("===response.addressList.length==="+response.addressList.length);
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
            //处理删除地址
            delAddress(id){
                this.$confirm('是否要进行删除操作?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    reqDelAddress(id).then(response=>{
                        console.log(response);
                        if(response.code==200){
                            this.$message({
                                message: response.message,
                                type: "success"
                            });
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
                }).catch(()=>{
                    console.log("取消删除了");
                });
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
  .address_list{
    width: 280px;
    height: 180px;
    border: 1px solid #cacaca;
    display: inline-block;
    margin-right: 20px;
    margin-top: 20px;
    padding: 20px;
    vertical-align: top;
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
    /*background-color: #8acfd1;*/
    font-size: 30px;
    color: #757575;
    text-align: center;
  }
</style>

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
          <el-form-item label="用户名/账号">
            <el-input v-model="search.account"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <el-card class="box-card" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>数据列表</span>
      <el-button
        class="btn-add"
        style="float: right"
        size="mini">
        添加
      </el-button>
    </el-card>

    <div class="table_content">
      <el-table
        ref="multipleTable"
        border
        :data="userList"
        tooltip-effect="dark"
        style="width: 100%"
       >
        <el-table-column
          label="编号"
          width="70"
          align="center">
          <template slot-scope="scope">{{ scope.row.id }}</template>
        </el-table-column>
        <el-table-column
          prop="account"
          label="账号"
          width="250px"
          align="center">
        </el-table-column>
        <el-table-column
          prop="name"
          label="昵称"
          align="center">
        </el-table-column>
        <el-table-column
          prop="registerTime"
          label="注册时间"
          align="center">
        </el-table-column>
        <el-table-column
          label="是否禁用"
          align="center">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.enable" @change="handleStatus($event,scope.row,scope.$index)"></el-switch>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div style="margin-top: 20px;width: 100%;">
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
    import {reqGetUserList,reqModifyUserStatus} from "../../../api/user";

    export default {
        name: "UserList",
        data(){
            return{
                loading: true,
                currentPage: 1,
                page_size: 5,
                total:null,
                search: {
                    account: ""
                },
                multipleSelection: [],
                userList:[
                    {
                        id: null,
                        account: "",
                        name: "",
                        gender: "",
                        enable: false,
                        info: "",
                        imgUrl: "",
                        registerTime: null,
                    }
                ]
            }
        },
        created:function () {
            this.getUserList(1,5);
        },
        methods: {
            //处理选项框的操作，获取表格中哪些选项被选中
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },

            //分页函数
            handleSizeChange(val) {
                // console.log(`每页 ${val} 条`);
                this.page_size = val;
                this.getUserList(1,this.page_size);
            },
            handleCurrentChange(val) {
                // console.log(`当前页: ${val}`);
                this.currentPage = val;
                console.log(this.currentPage+":"+this.page_size);
                this.getUserList(this.currentPage,this.page_size);
            },
            //得到用户列表
            getUserList(page,pageSize){
                this.loading=false;
                reqGetUserList(page,pageSize).then(response=>{
                    console.log(response)
                    if(response.code==200){
                        this.total = response.total;
                        this.userList = response.userList;
                    }
                }).catch(err=>{
                    console.log(err);
                })
            },
            //处理是否禁用
            handleStatus(e,row,index){
                console.log("row.enable:"+row.enable);
                console.log("row.id:"+row.id)
                reqModifyUserStatus(row.id,row.enable).then(response=>{
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
                }).catch(err=>{
                    this.$message({
                        message: "操作出错，请检查网络是否连接",
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

  /deep/ .el-input__inner {
    padding-right: 0px;
  }

</style>

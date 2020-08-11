<template>
  <div class="content">
    <el-card class="box-card" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>数据列表</span>
      <el-button
        class="btn-add"
        @click="handleAddProduct"
        style="float: right"
        size="mini">
        添加
      </el-button>
    </el-card>
    <div class="table_content">
      <el-table
        ref="multipleTable"
        border
        :data="tableData"
        v-loading="loading"
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
          width="100"
          prop="id"
          align="center">
        </el-table-column>
        <el-table-column
          label="上一级"
          prop="upperName"
          align="center">
        </el-table-column>
        <el-table-column
          prop="sortName"
          align="center"
          label="分类名称">
        </el-table-column>
        <el-table-column
          prop="level"
          label="级别"
          width="110"
          align="center">
        </el-table-column>
        <el-table-column
          prop="num"
          label="图书数量"
          width="110" align="center">
        </el-table-column>
        <el-table-column
          prop="rank"
          label="排序"
          width="110" align="center">
        </el-table-column>
        <el-table-column
          label="操作"
          width="200"
          align="center">
          <template slot-scope="scope">
            <p>
              <el-button
                size="mini"
                v-bind:disabled="isDisabled"
                @click="goToSecond(scope.$index, scope.row)"
              >查看下级
              </el-button>
              <el-button
                size="mini"
              >查看图书
              </el-button>
            </p>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          width="160"
          align="center">
          <template slot-scope="scope">
            <p>
              <el-button
                size="mini"
                @click="editSort(scope.$index, scope.row)"
              >编辑
              </el-button>
              <el-button
                size="mini"
                type="danger"
                @click="delSort(scope.$index,scope.row)"
              >删除
              </el-button>
            </p>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div style="margin-top: 20px;width: 100%">
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
  import {reqDelFirstSort,reqDelSecondSort,reqGetFirstSortList,reqGetSecondSortList} from "../../../../../api/sort";

  export default {
        name: "BookSort",
        props: {
            value: Object,
            isFirst: {
                type: Boolean,
                default: true
            }
        },
        data(){
            return{
                isDisabled: false,
                loading: true,
                currentPage: 1,
                page_size: 5,
                tableData: [],
                total:null,
                tab:[{
                    id: '20',
                    upperName: "无",
                    sortName: '文学',
                    level: '级别一',
                    num: 10,
                    rank: 35,
                },{
                    id: '20',
                    upperName: "无",
                    sortName: '文学',
                    level: '级别一',
                    num: 10,
                    rank: 35,
                },{
                    id: '20',
                    upperName: "无",
                    sortName: '文学',
                    level: '级别一',
                    num: 10,
                    rank: 35,
                },{
                    id: '20',
                    upperName: "无",
                    sortName: '文学',
                    level: '级别一',
                    num: 10,
                    rank: 35,
                },{
                    id: '20',
                    upperName: "无",
                    sortName: '文学',
                    level: '级别一',
                    num: 10,
                    rank: 35,
                }
                ],
                multipleSelection: []
            }
        },
        created:function () {
            if(!this.isFirst){
                this.isDisabled=true;
            }
            this.GetSort(1,5);
        },
        methods: {
            toggleSelection(rows) {
                if (rows) {
                    rows.forEach(row => {
                        this.$refs.multipleTable.toggleRowSelection(row);
                    });
                } else {
                    this.$refs.multipleTable.clearSelection();
                }
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },

            //分页函数
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.page_size = val;
                this.GetSort(1,this.page_size);
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.currentPage = val;
                console.log(this.currentPage+":"+this.page_size);
                this.GetSort(this.currentPage,this.page_size);
            },
            //得到出版社列表
            GetSort(page,pageSize){
                if(this.isFirst){
                    this.loading=false;
                    reqGetFirstSortList(page,pageSize).then(response=>{
                        if(response.code==200){
                            this.total = response.total;
                            console.log(this.total);
                            this.tableData = response.bookSortList;
                        }
                        console.log(response);
                    }).catch(err=>{
                        console.log(err);
                    })
                }else{
                    let upperName = this.$route.query.upperName;
                    console.log("跳转过来的upperName:"+upperName);
                    this.loading=false;
                    reqGetSecondSortList(upperName,page,pageSize).then(response=>{
                        if(response.code==200){
                            this.total = response.total;
                            console.log(this.total);
                            this.tableData = response.bookSortList;
                        }
                        console.log(response);
                    }).catch(err=>{
                        console.log(err);
                    })
                }
            },

            //处理添加分类的函数
            handleAddProduct() {
                this.$router.push({path:'/admin/addSort'});
            },

            //查看下级
            goToSecond(index, row){
                this.$router.push({
                    path: "/admin/SecondSortList",
                    query: {
                        upperName: row.sortName
                    }
                })
            },

            //更新分类名
            editSort(index, row){
                console.log("row.sortName:"+row.id);
                if(row.upperName=="无"){
                    this.$router.push({
                        path: "/admin/updateFirstSort",
                        query: {
                            sortName: row.sortName,
                            upperName: row.upperName
                        }
                    })
                }else{
                    this.$router.push({
                        path: "/admin/updateSecondSort",
                        query: {
                            sortName: row.sortName,
                            upperName: row.upperName
                        }
                    })
                }
            },

            //处理删除分类
            delSort(index,row){
                this.$confirm('是否要进行删除操作?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    if(this.isFirst){
                        console.log("删除一级分类")
                        reqDelFirstSort(row.sortName).then(response=>{
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
                            this.GetSort(this.currentPage,this.page_size);
                        }).catch(err=>{
                            console.log(err);
                        })
                    }else {
                        console.log("删除二级分类");
                        reqDelSecondSort(row.upperName,row.sortName).then(response=>{
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
                            this.GetSort(this.currentPage,this.page_size);
                        }).catch(err=>{
                            console.log(err);
                        })
                    }

                }).catch(()=>{
                    console.log("取消删除了");
                });

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
</style>

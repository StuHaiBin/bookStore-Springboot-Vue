<template>
  <div class="content">
    <el-card class="box-card" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>数据列表</span>
      <el-button
        class="btn-add"
        @click="handleAddTopic"
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
          width="80"
          prop="id"
          align="center">
        </el-table-column>
        <el-table-column
          prop="cover"
          label="封面"
          width="160" align="center">
          <template slot-scope="scope">
            <el-image style="height: 120px"
                      :src="scope.row.cover">
              <div slot="error" class="image-slot">
                <i class="el-icon-picture-outline"></i>
              </div>
            </el-image>
          </template>
        </el-table-column>
        <el-table-column
          prop="topicName"
          label="书单名">
        </el-table-column>
        <el-table-column
          prop="rank"
          label="子标题"
          align="center">
        </el-table-column>
        <el-table-column
          prop="rank"
          label="排序"
          width="90" align="center">
        </el-table-column>
        <el-table-column
          label="上架状态"
          width="100" align="center">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.put" @change="handlePut($event,scope.row,scope.$index)"></el-switch>
          </template>
        </el-table-column>
        <el-table-column
          label="相关"
          width="120" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="goToSubTopic(scope.$index,scope.row)"
            >查看下级
            </el-button>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          width="200"
          align="center">
          <template slot-scope="scope">
            <p>
              <el-button
                size="mini"
                @click="updateRank(scope.$index,scope.row)"
              >设置排序
              </el-button>
              <el-button
                size="mini"
                @click="updateBook(scope.$index,scope.row)"
              >编辑
              </el-button>
            </p>
            <p style="margin-top: 10px">
              <el-button
                size="mini"
                @click="updateTopicBook(scope.$index,scope.row)"
              >添加图书
              </el-button>
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)"
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


    <!--修改排序的弹出框-->
    <el-dialog title="修改排序" :visible.sync="dialogVisible" >
      <el-form :model="modRank" :rules="rules" ref="bookTopic">
        <el-form-item label="排序值" label-width="120px" prop="rank">
          <el-input v-model.number="modRank.rank" autocomplete="off" @keyup.native="proving"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitModRank('bookTopic')">确 定</el-button>
      </div>
    </el-dialog>

    <!--添加图书的弹出框-->
    <el-dialog title="图书列表" :visible.sync="bookDialogVisible">
      <el-table
        ref="multipleTable"
        :data="book_tableData"
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
          width="55"
          align="center">
          <template slot-scope="scope">{{ scope.row.id }}</template>
        </el-table-column>
        <el-table-column
          prop="bookName"
          label="书名"
          align="center">
        </el-table-column>
        <el-table-column
          prop="isbn"
          label="isbn"
          width="100"
          align="center">
        </el-table-column>
        <el-table-column
          prop="author"
          label="作者"
          align="center">
        </el-table-column>
        <el-table-column
          label="添加至书单"
          width="120" align="center">
          <template slot-scope="scope">
            <span style="margin-right: 10px">上架</span>
            <el-switch v-model="scope.row.put" @change="handleTopicBook($event,scope.row,scope.$index)"></el-switch>
          </template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <div style="float: left;padding: 0px 0px 10px">
          <el-select v-model="operator" placeholder="批量操作">
            <el-option
              v-for="item in operates"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
          <el-button
            style="margin-left: 10px"
            @click="operatorBook"
            type="primary"
            size="small">
            确定
          </el-button>
        </div>
        <el-pagination
          @size-change="handleBookSizeChange"
          @current-change="handleBookCurrentChange"
          :current-page="book_currentPage"
          :page-sizes="[5, 10, 20, 50]"
          :page-size="book_page_size"
          :pager-count="5"
          background
          layout="sizes, prev, pager, next"
          :total="book_total">
        </el-pagination>
        <div style="height: 10px;width: 100%"></div>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import {reqGetTopicList,reqModifyPut,reqModifyRank,reqDelTopic,reqGetNoAddBookList,reqAddSubTopic,reqDelSubTopic} from "../../../../api/bookTopic";
  import {reqGetBookList} from "../../../../api/book";
  import axios from "axios"
  export default {
        name: "BookTopicSet",
        data(){
            let checkRank = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('排序不能为空'));
                }
                setTimeout(() => {
                    if (!Number.isInteger(value)) {
                        callback(new Error('请输入数字值'));
                    } else {
                        if (value < 0) {
                            callback(new Error('数字值必须大于0'));
                        } else {
                            callback();
                        }
                    }
                }, 1000);
            };


            return{
                dialogVisible: false,
                modRank: {
                    rank: 1,
                    id: 12
                },



                topicId: 1,
                bookDialogVisible: false,
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
                    ImgSrc: [],
                    put: true,
                    coverImg: '',
                    rank: '',
                    newProduct: true,
                    recommend: true,
                    bookSort: [],//图书分类的绑定值
                },
                loading: true,
                book_currentPage: 1,
                book_page_size: 5,
                book_tableData: [],
                book_total:3,
                book_multipleSelection: [],
                operator: null,
                //批量操作
                operates: [
                    {
                        label: "批量添加",
                        value: "add"
                    }
                ],



                currentPage: 1,
                page_size: 5,
                tableData: [],
                total:20,
                bookTopic: {
                    id: null,
                    topicName: "",
                    subTitle: "",
                    cover: null,
                    rank: 1,
                    put: true
                },
                multipleSelection: [],

                rules: {
                    rank: [
                        { validator: checkRank, trigger: 'change' }
                    ]

                }
            }
        },
        created(){
            this.GetTopic(1,5);
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

            //处理添加分类的函数
            handleAddTopic() {
                this.$router.push({path:'/admin/addBookTopic'});
            },
            goToSubTopic(index,row){
                // console.log("进入下级书单")
                this.$router.push({
                        path:'/admin/subTopicList',
                        query: {
                            topicId: row.id,
                            topicName: row.topicName
                        }
                    });
            },


            //编辑书单
            updateBook(index,row){
                console.log("row.id:"+row.isbn);
                this.$router.push({
                    path: "/admin/updateBookTopic",
                    query: {
                        id: row.id
                    }
                })
            },
            //处理删除函数
            handleDelete(index, row){
                this.$confirm('是否要进行删除操作?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    reqDelTopic(row.id).then(response=>{
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
                        this.GetTopic(this.currentPage,this.page_size);
                    }).catch(err=>{
                        console.log(err);
                    })
                }).catch(()=>{
                    console.log("取消删除了");
                });
            },
            //修改排序值
            updateRank(index,row){
                this.dialogVisible = true;
                this.modRank.rank  = row.rank;
                this.modRank.id = row.id;
            },

            submitModRank(formName) {
                // console.log("提交修改排序");
                this.$refs[formName].validate((valid)=>{
                    if(valid){
                        reqModifyRank(this.modRank.id,this.modRank.rank).then(response=>{
                            if(response.code==200){
                                this.dialogVisible = false;
                                this.$message({
                                    type: 'success',
                                    message: response.message
                                })
                            }else{
                                this.$message({
                                    type: 'warning',
                                    message: response.message
                                })
                            }
                        }).catch(err=>{
                            this.$message({
                                type: 'success',
                                message: "修改排序失败"
                            })
                        })
                        this.GetTopic(this.currentPage,this.page_size);
                    }else {
                        this.$message.error("排序值不符合要求");
                        return false;
                    }
                });
            },


            //对书单列表的分页函数
            handleSizeChange(val) {
                // console.log(`每页 ${val} 条`);
                this.page_size = val;
                this.GetTopic(1,this.page_size);
            },
            handleCurrentChange(val) {
                // console.log(`当前页: ${val}`);
                this.currentPage = val;
                // console.log(this.currentPage+":"+this.page_size);
                this.GetTopic(this.currentPage,this.page_size);
            },
            //得到书单列表
            GetTopic(page,pageSize){
                this.loading=false;
                reqGetTopicList(page,pageSize).then(response=>{
                    if(response.code==200){
                        this.total = response.total;
                        console.log(+this.total);
                        this.tableData = response.bookTopicList;
                    }
                    console.log(response);
                }).catch(err=>{
                    console.log(err);
                })
            },

            //处理上下架的改变
            handlePut(e,row,index){
                console.log(row.put);
                reqModifyPut(row.id,row.put).then(response=>{
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
                    console.log(err);
                })
            },


            proving(e){
                let keyNum = window.event ? e.keyCode : e.which;//获取键盘吗
                let keyChar = String.fromCharCode(keyNum);//获取键盘码对应的字符
                if(keyNum==189 || keyNum==190 || keyNum==110 || keyNum==109){
                    this.$message.warning("禁止输入小数以及负数");
                    e.target.value='';
                }
            },





            //添加书单的图书
            updateTopicBook(index,row){
                this.bookDialogVisible = true;
                this.topicId = row.id;
                console.log(this.topicId);
                this.getBookList(this.topicId,1,5);
            },
            //处理书单图书的上下架的改变
            handleTopicBook(e,row,index){
                console.log(row.put);
                if(row.put == true){
                    reqAddSubTopic(this.topicId,row.id).then(response=>{
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
                        console.log(err);
                    })
                }else {
                    reqDelSubTopic(this.topicId,row.id).then(response=>{
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
                        console.log(err);
                    })
                }
            },



            //下面是对图书列表的操作
            handleSelectionChange(val) {
                this.book_multipleSelection = val;
            },
            //分页函数
            handleBookSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.book_page_size = val;
                this.getBookList(this.topicId,1,this.book_page_size);
            },
            handleBookCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.book_currentPage = val;
                console.log(this.book_currentPage+":"+this.book_page_size);
                this.getBookList(this.topicId,this.book_currentPage,this.book_page_size);
            },
            //按页得到未添加到指定书单的图书
            getBookList(topicId,page,pageSize){
                this.loading=false;
                reqGetNoAddBookList(topicId,page,pageSize).then(response=>{
                    if(response.code==200){
                        this.book_total = response.total;
                        console.log("this.book_total:"+this.book_total);
                        this.book_tableData = response.bookList;
                    }
                    console.log(response);
                }).catch(err=>{
                    console.log(err);
                })
            },

            operatorBook(){
                if(this.book_multipleSelection.length<1){
                    this.$message({
                        type: 'warning',
                        message: "请至少选择一项进行操作"
                    })
                }else{
                    let dataList = [];
                    for(let i=0;i<this.book_multipleSelection.length;i++){
                        dataList.push(this.book_multipleSelection[i].id);
                    }
                    console.log("this.operator:"+this.operator);
                    let formData = new FormData();
                    formData.append("ids", dataList);
                    formData.append("operator",this.operator);
                    formData.append("topicId",this.topicId);
                    console.log("dataList:"+dataList);
                    axios({
                        method: 'POST',
                        url: 'http://localhost:8082/batchTopic',
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
                        this.getBookList(this.topicId,this.currentPage,this.page_size);
                    }).catch(err=>{
                        console.log("出错了！")
                    })
                }
            },

        },

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
  /deep/ .el-input--suffix>.el-input__inner {
    padding-right: 0px;
    width: 110px;
    height: 33px;
  }

</style>


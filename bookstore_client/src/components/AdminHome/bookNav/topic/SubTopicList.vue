<template>
  <div class="content">
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
          align="center">
          {{topicId}}
        </el-table-column>
        <el-table-column
          label="书单名">
          {{topicName}}
        </el-table-column>
        <el-table-column
          prop="bookName"
          label="书名"
          align="center">
        </el-table-column>
        <el-table-column
          prop="isbn"
          label="isbn"
          align="center">
        </el-table-column>
        <el-table-column
          prop="author"
          label="作者"
          width="160" align="center">
        </el-table-column>
        <el-table-column
          label="操作"
          width="210"
          align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="updateRec(scope.$index,scope.row)"
            >推荐理由
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
            >删除
            </el-button>
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

    <el-dialog title="添加推荐理由" :visible.sync="dialogVisible" >
      <el-form :model="modRec" :rules="rules" ref="bookTopic">
        <el-form-item label="排序值" label-width="120px" prop="reason">
          <el-input type="textarea" v-model.number="modRec.reason" autocomplete="off" ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitModRank('bookTopic')">确 定</el-button>
      </div>
    </el-dialog>



  </div>
</template>

<script>
    import {reqGetSubTopicList,reqModifySubTopic,reqDelSubTopic,reqGetReason} from "../../../../api/bookTopic";
    export default {
        name: "SubTopicList",
        data(){

            return{
                dialogVisible: false,
                modRec: {
                    reason: "",
                    id: 12
                },
                rules: {
                    reason: [
                        { max: 25, message: '书名不能超过25个字符', trigger: 'blur' }
                    ]
                },


                currentPage: 1,
                page_size: 5,
                tableData: [],
                total:20,
                multipleSelection: [],

                topicId: null,
                topicName: "",
                bookTopic: {
                    bookName: null,
                    isbn: null,
                    author: null
                },

            }
        },
        created(){
            this.topicId = this.$route.query.topicId;
            this.topicName = this.$route.query.topicName;
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
                this.$router.push({path: '/admin/addBookTopic'});
            },

            //处理删除函数
            handleDelete(index, row) {
                this.$confirm('是否要进行删除操作?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    reqDelSubTopic(this.topicId,row.id).then(response => {
                        console.log(response);
                        if (response.code == 200) {
                            this.$message({
                                message: response.message,
                                type: "success"
                            })
                        } else {
                            this.$message({
                                message: response.message,
                                type: "warning"
                            })
                        }
                        this.GetTopic(this.currentPage, this.page_size);
                    }).catch(err => {
                        console.log(err);
                    })
                }).catch(() => {
                    console.log("取消删除了");
                });
            },

            //编辑书单
            updateRec(index, row) {
                this.dialogVisible = true;
                reqGetReason(this.topicId,row.id).then(response=>{
                    if (response.code == 200) {
                        this.modRec.reason = response.reason;
                    } else {
                        this.$message({
                            type: 'warning',
                            message: "获取图书推荐理由失败"
                        })
                    }
                }).catch(err => {
                    this.$message({
                        type: 'warning',
                        message: "获取图书推荐理由失败"
                    })
                })
                console.log("row.id:" + row.id);
                this.dialogVisible = true;
                this.modRec.id = row.id;//图书的id
            },

            submitModRank(formName) {
                console.log("提交修改推荐理由");
                console.log("this.topicId"+this.topicId+"this.modRec.id"+this.modRec.id+"this.modRec.reason"+this.modRec.reason)
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        reqModifySubTopic(this.topicId,this.modRec.id, this.modRec.reason).then(response => {
                            if (response.code == 200) {
                                this.dialogVisible = false;
                                this.$message({
                                    type: 'success',
                                    message: response.message
                                })
                            } else {
                                this.$message({
                                    type: 'warning',
                                    message: response.message
                                })
                            }
                        }).catch(err => {
                            this.$message({
                                type: 'success',
                                message: "修改推荐理由失败"
                            })
                        })
                        this.GetTopic(this.currentPage, this.page_size);
                    } else {
                        this.$message.error("推荐理由不符合要求");
                        return false;
                    }
                });
            },


            //对书单列表的分页函数
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.page_size = val;
                this.GetTopic(1, this.page_size);
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.currentPage = val;
                console.log(this.currentPage + ":" + this.page_size);
                this.GetTopic(this.currentPage, this.page_size);
            },
            //得到出版社列表
            GetTopic(page, pageSize) {
                this.loading = false;
                reqGetSubTopicList(this.topicId,page, pageSize).then(response => {
                    if (response.code == 200) {
                        this.total = response.total;
                        this.tableData = response.subTopicList;
                    }
                    console.log(response);
                }).catch(err => {
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



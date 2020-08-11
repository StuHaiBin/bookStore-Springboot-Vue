<template>
  <div class="content">
    <div class="tab_content">
      <div class="first_tab">
        <el-form ref="bookSort" :model="bookSort" :rules="rules" label-width="80px">
          <el-form-item label="分类名称" prop="sortName">
            <el-input v-model="bookSort.sortName"></el-input>
          </el-form-item>
          <el-form-item v-show="!isFirst" label="分类上级">
            <el-select v-model="bookSort.upperName" placeholder="请选择活动区域">
              <el-option
                v-for="item in publishList"
                :key="item"
                :label="item"
                :value="item"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="排序" prop="rank">
            <el-input v-model.number="bookSort.rank" @keyup.native="proving"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit('bookSort')">确认</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
  import {reqGetUpperName,reqGetBookSort,reqAddBookSort,reqModifyBookSort} from "../../../../../api/sort";

  export default {
        name: "SortDetail",
        props: {
            value: Object,
            isEdit: {
                type: Boolean,
                default: false
            },
            isFirst: {
                type: Boolean,
                default: false
            }
        },
        data() {
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
            return {
                bookSort: {
                    id: 1,
                    sortName: '',
                    upperName: '',
                    rank: '',
                    level: ''
                },
                rules: {
                    sortName: [
                        { required: true, message: '分类名不能为空', trigger: 'blur' },
                        { min: 1, max: 10, message: '分类长度在 1 到 15 个字符', trigger: 'blur' }
                    ],
                    upperName: [
                        { required: true, message: '上级分类名不能为空', trigger: 'blur' },
                    ],
                    rank: [
                        { validator: checkRank, trigger: 'blur' }
                    ]
                },
                publishList: [],
                publishLists: [
                    {
                        label: "批量删除",
                        value: "recommendOn"
                    },
                    {
                        label: "批量上架",
                        value: "publishOn"
                    },
                    {
                        label: "批量下架",
                        value: "publishOff"
                    },
                    {
                        label: "设为推荐",
                        value: "recommendOff"
                    },
                    {
                        label: "取消推荐",
                        value: "newOn"
                    },
                    {
                        label: "设为新品",
                        value: "transferCategory"
                    },
                    {
                        label: "取消新品",
                        value: "recycle"
                    }
                ],
            };
        },

        methods: {
            onSubmit(formName) {
                this.$refs[formName].validate((valid)=>{
                    if(valid){
                        if(this.isEdit){
                             this.modifySort();
                        }else {
                             this.addSort();
                        }
                    }else {
                        this.$message.error("添加出版社失败");
                    }
                });
            },
            addSort(){
                if(this.bookSort.upperName=="无"){
                    this.bookSort.level="级别一";
                }else {
                    this.bookSort.level="级别二";
                }
                console.log(this.bookSort.level);
                reqAddBookSort(this.bookSort).then(response=>{
                    if(response.code==200){
                        this.$message({
                            message: response.message,
                            type: "success"
                        })
                    }else {
                        this.$message({
                            message: response.message,
                            type: 'warning'
                        })
                    }
                }).catch(err=>{
                    console.log(err);
                    this.$message.error("添加分类名失败");
                })
            },

            modifySort(){
                reqModifyBookSort(this.bookSort).then(response=>{
                    if(response.code==200){
                        this.$message({
                            message: response.message,
                            type: "success"
                        })
                    }else {
                        this.$message({
                            message: response.message,
                            type: 'warning'
                        })
                    }
                }).catch(err=>{
                    console.log(err);
                    this.$message.error("修改分类名失败");
                })
            },


            handleChange(value) {
                console.log(value);
            },
            hhh(){
                console.log("哈哈哈");
            },
            proving(e){
                let keyNum = window.event ? e.keyCode : e.which;//获取键盘吗
                let keyChar = String.fromCharCode(keyNum);//获取键盘码对应的字符
                if(keyNum==189 || keyNum==190 || keyNum==110 || keyNum==109){
                    this.$message.warning("禁止输入小数以及负数");
                    e.target.value='';
                }
            },
        },
        created() {
            if(this.isEdit){
                let upperName=this.$route.query.upperName;
                let sortName=this.$route.query.sortName;
                console.log(upperName+sortName);
                if(!this.isFirst){
                    reqGetUpperName().then(response=>{
                        console.log(response);
                        this.publishList = response.upperNames;
                        this.bookSort.upperName = "无";
                    }).catch(err=>{
                        console.log(err);
                    })
                    reqGetBookSort(upperName,sortName).then(response=>{
                        this.bookSort=response.bookSort;
                        console.log(this.bookSort);
                    }).catch(err=>{
                        console.log(err);
                    })
                }else {
                    reqGetBookSort(upperName,sortName).then(response=>{
                        this.bookSort=response.bookSort;
                        console.log(this.bookSort);
                    }).catch(err=>{
                        console.log(err);
                    })
                }
            }else {
                reqGetUpperName().then(response=>{
                    console.log(response);
                    this.publishList = response.upperNames;
                    this.bookSort.upperName = "无";
                }).catch(err=>{
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
  .tab_content{
    margin: 10px auto;
    width:800px;
    border: 1px #e8e8e8 solid;
    padding: 50px 25px;
  }

  .first_tab{
    margin: 10px auto 0px;
    width: 600px;
    padding: 20px 20px 0px;
  }


</style>


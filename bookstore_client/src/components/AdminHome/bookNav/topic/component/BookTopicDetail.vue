<template>
  <div class="content">
    <div class="tab_content">
      <div class="first_tab">
        <el-form :model="bookTopic" :rules="rules" ref="bookTopic" label-width="100px">
          <el-form-item label="书单主题" prop="topicName">
            <el-input v-model="bookTopic.topicName"></el-input>
          </el-form-item>
          <el-form-item label="子标题" prop="subTitle">
            <el-input v-model.number="bookTopic.subTitle"></el-input>
          </el-form-item>
          <el-form-item label="排序" prop="rank">
            <el-input v-model.number="bookTopic.rank" @keyup.native="proving"></el-input>
          </el-form-item>
          <el-form-item label="是否上架">
            <el-switch v-model="bookTopic.put"></el-switch>
          </el-form-item>
          <el-form-item label="书单封面">
            <el-upload
              ref="book"
              :action="isEdit?modifyUrl:addUrl"
              list-type="picture-card"
              :on-preview="handlePictureCardPreview"
              :on-success="handleSuccess"
              :on-remove="handleRemove"
              :limit="1"
              :data="bookTopic"
              :file-list="fileList"
              :auto-upload="auto"
            >
              <i class="el-icon-plus"></i>
            </el-upload>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit('bookTopic')">确认</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
    import {reqDelTopic,reqGetTopic,reqDelTopicImg,reqModifyTopic} from "../../../../../api/bookTopic";
    export default {
        name: "BookTopicDetail",
        props: {
            value: Object,
            isEdit: {
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
                addUrl:'http://localhost:8082/addTopic',
                modifyUrl:'http://localhost:8082/uploadTopicImg',
                auto: false,

                fileList: [],
                dialogImageUrl: '',
                dialogVisible: false,//添加的图片是否可见
                editId: null,
                bookTopic: {
                    id: null,
                    topicName: "",
                    subTitle: "",
                    cover: null,
                    rank: 1,
                    put: true
                },
                rules: {
                    topicName: [
                        { required: true, message: '书单名名不能为空', trigger: 'blur' },
                        { min: 1, max: 15, message: '书单名长度在 3 到 10 个字符', trigger: 'blur' }
                    ],
                    subTitle: [
                        { required: true, message: '书单子标题名不能为空', trigger: 'blur' },
                        { min: 1, max: 15, message: '书单子标题名长度在 3 到 10 个字符', trigger: 'blur' }
                    ],
                    rank: [
                        { validator: checkRank, trigger: 'blur' }
                    ]

                }
            };
        },


        methods: {
            handleEditCreated(){
                console.log("this.$route.query.id"+this.$route.query.id);
                if(this.isEdit==true){
                    console.log("哈哈哈哈");
                    this.editId=this.$route.query.id;
                    if(this.isEdit) {
                        let id = this.$route.query.id;
                        reqGetTopic(id).then(response => {
                            this.bookTopic = response.bookTopic;
                            this.fileList.push({name: this.bookTopic.id, url: this.bookTopic.cover})
                        }).catch(err => {
                            console.log(err);
                        })
                    }
                }
            },
            onSubmit(formName) {
                this.$refs[formName].validate((valid)=>{
                    if(valid){
                        if(this.isEdit){
                            reqModifyTopic(this.bookTopic).then(response=>{
                                if(response.code==200){
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
                                    message: "修改图书失败"
                                })
                            })
                            // console.log("修改书单");
                        }else {
                            this.$refs.book.submit();
                        }
                    }else {
                        this.$message.error("添加书单失败");
                        return false;
                    }
                });
            },

            proving(e){
                let keyNum = window.event ? e.keyCode : e.which;//获取键盘吗
                let keyChar = String.fromCharCode(keyNum);//获取键盘码对应的字符
                if(keyNum==189 || keyNum==190 || keyNum==110 || keyNum==109){
                    this.$message.warning("禁止输入小数以及负数");
                    e.target.value='';
                }
            },

            //图片被移除后
            handleRemove(file, fileList) {
                this.$message({
                    type: 'info',
                    message: "书单封面图不可以为空，请重新添加"
                })
                if(this.isEdit){
                    reqDelTopicImg(this.bookTopic.id,file.url).then(response=>{
                        if(response.code==200){
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
                            message: "删除图片失败"
                        })
                    })
                }
                // console.log("url:"+file.url);
                // console.log("fileList:"+fileList);
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            handleSuccess(response, file, fileList){
                if(response.code==200){
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
            },

        },
        created() {
            if(this.editId==false)
                return;
            else {
                this.auto = true;
                this.handleEditCreated();
            }
        },
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


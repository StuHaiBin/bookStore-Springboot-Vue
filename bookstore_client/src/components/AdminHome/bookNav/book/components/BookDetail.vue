<template>
  <div class="content">
    <div class="tab_content">
      <div class="step">
        <el-steps :active="active" finish-status="success">
          <el-step title="图书信息"></el-step>
          <el-step title="图书属性"></el-step>
          <el-step title="图书促销"></el-step>
        </el-steps>
      </div>
      <div class="first_tab" v-if="active===0">
        <el-form ref="book" :model="book" :rules="rules" label-width="80px">

          <el-form-item label="图书分类:">
            <el-cascader
              v-model="book.bookSort"
              :options="options"
              @change="handleChange">
            </el-cascader>
         </el-form-item>

          <el-form-item label="ISBN" prop="isbn">
            <el-input v-model="book.isbn"></el-input>
          </el-form-item>
          <el-form-item label="书名" prop="bookName">
            <el-input v-model="book.bookName"></el-input>
          </el-form-item>
          <el-form-item label="作者" prop="author">
            <el-input v-model="book.author"></el-input>
          </el-form-item>
          <el-form-item label="出版社">
            <el-select v-model="book.publish" placeholder="请选择活动区域" prop="publish">
              <el-option
                v-for="item in publishList"
                :key="item"
                :label="item"
                :value="item"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="出版时间" prop="birthday">
            <el-col :span="12">
              <el-date-picker type="date" placeholder="选择日期" v-model="book.birthday" style="width: 100%;"></el-date-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="原价" prop="marketPrice">
            <el-input v-model.number="book.marketPrice"></el-input>
          </el-form-item>
          <el-form-item label="售价" prop="price">
            <el-input v-model.number="book.price"></el-input>
          </el-form-item>
          <el-form-item label="库存" prop="stock">
            <el-input v-model.number="book.stock" @keyup.native="proving"></el-input>
          </el-form-item>
          <el-form-item label="权重值" prop="rank">
            <el-input v-model.number="book.rank" @keyup.native="proving"></el-input>
          </el-form-item>
        </el-form>
        <div class="step_btn">
          <el-button style="margin-top: 12px;" @click="nextToAttr('book')">下一步</el-button>
        </div>
      </div>

      <div class="markdown_tab" v-if="active===1">
        <el-form :model="book" label-width="80px">
          <el-form-item label="图书相册">
            <el-upload
              ref="book"
              action="http://localhost:8082/uploadBookImg"
              list-type="picture-card"
              :on-preview="handlePictureCardPreview"
              :on-success="handleSuccess"
              :on-remove="handleRemove"
              :limit="5"
              :data="book"
              :file-list="fileList"
              :headers="myHeader"
            >
<!--              :auto-upload="false"-->
              <i class="el-icon-plus"></i>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible">
              <img width="100%" :src="dialogImageUrl" alt="">
            </el-dialog>
          </el-form-item>
          <el-form-item label="详情介绍" prop="content">
            <mavon-editor v-model="book.description"></mavon-editor>
          </el-form-item>
        </el-form>
        <div class="step_btn">
          <el-button style="margin-top: 12px;" @click="prev">上一步</el-button>
          <el-button style="margin-top: 12px;" @click="next">下一步</el-button>
        </div>
      </div>

      <div class="first_tab" v-if="active===2">
        <el-form ref="form" :model="book" label-width="80px">
          <el-form-item label="是否上架">
            <el-switch v-model="book.put"></el-switch>
          </el-form-item>
          <el-form-item label="图书推荐">
            <span style="margin-right: 10px">新品</span>
            <el-switch v-model="book.newProduct"></el-switch>
            <span style="margin-right: 10px">推荐</span>
            <el-switch v-model="book.recommend"></el-switch>
          </el-form-item>
        </el-form>
        <div class="step_btn">
          <el-button style="margin-top: 12px;" @click="prev">上一步</el-button>
          <el-button style="margin-top: 12px;" @click="onSubmit">提交</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    import {reqGetPublishNames} from "../../../../../api/publish";
    import {reqGetSortList} from "../../../../../api/sort";
    import {reqAddBook,reqGetBookImgPathList,reqGetBook,reqDelBookImg,reqModifyBook} from "../../../../../api/book";
    export default {
        name: "BookDetail",
        props:{
            isEdit: {
                type: Boolean,
                default: false
            }
        },
        data() {
            let checkRank = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('不能为空'));
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
            let checkPrice = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('价格不能为空'));
                }
                setTimeout(() => {
                    if (!Number.isInteger(value)) {
                        callback(new Error('价格必须是数字值'));
                    } else {
                        if (value < 0) {
                            callback(new Error('价格必须大于0'));
                        } else {
                            callback();
                        }
                    }
                }, 1000);
            };
            return {
                myHeader:{
                    'Authorization':""
                },
                fileList: [],
                active: 0,//激活的过程卡片选择器
                dialogImageUrl: '',
                dialogVisible: false,//添加的图片是否可见
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
                    imgSrc: [],
                    put: true,
                    coverImg: '',
                    rank: '',
                    newProduct: true,
                    recommend: true,
                    bookSort: [],//图书分类的绑定值
                },
                publishList: [],//出版社下拉选择器
                options: [],//图书分类的联机选择器

                rules: {
                    bookName: [
                        { required: true, message: '书名不能为空', trigger: 'blur' },
                        { min: 1, max: 20, message: '书名不能超过20个字符', trigger: 'blur' }
                    ],
                    author: [
                        { required: true, message: '作者名不能为空', trigger: 'blur' },
                        { min: 1, max: 20, message: '作者名不能超过20个字符', trigger: 'blur' }
                    ],
                    isbn: [
                        { required: true, message: 'ISBN不能为空', trigger: 'blur' },
                        { min: 1, max: 13, message: 'ISBN长度在 10 到 13 个字符', trigger: 'blur' }
                    ],
                    publish: [
                        { required: true, message: '请选择活动区域', trigger: 'change' }
                    ],
                    birthday: [
                        {  required: true, message: '请选择日期', trigger: 'change' },
                    ],
                    marketPrice:[
                        { validator: checkPrice, trigger: 'blur' }
                    ],
                    price:[
                        { validator: checkPrice, trigger: 'blur' }
                    ],
                    stock:[
                        { validator: checkRank, trigger: 'blur' }
                    ],
                    rank:[
                        { validator: checkRank, trigger: 'blur' }
                    ]
                },
            };
        },

        methods: {

            initMyHeader(){
                this.myHeader.Authorization = localStorage.getItem("token");
            },

            //过程链的操作函数
            nextToAttr(formName){
                this.$refs[formName].validate((valid)=>{
                    if(valid){
                        this.active++;
                    }else {
                        this.$message.error("数据未通过验证");
                        return false;
                    }
                });
            },
            nextToSale(){
                this.active++;
            },
            next() {
                if (this.active < 2){
                    this.active++;
                }else{
                    this.active=2;
                }
            },
            prev(){
                if (this.active > 1) {
                    this.active--;
                }
                else{
                    this.active=0;
                }
            },

            //验证函数，验证值只能是正整数
            proving(e){
                let keyNum = window.event ? e.keyCode : e.which;//获取键盘吗
                let keyChar = String.fromCharCode(keyNum);//获取键盘码对应的字符
                if(keyNum==189 || keyNum==190 || keyNum==110 || keyNum==109){
                    this.$message.warning("禁止输入小数以及负数");
                    e.target.value='';
                }
            },


            onSubmit() {
                if(!this.isEdit){
                    console.log(this.fileList);
                    // this.$refs.book.submit();
                    reqAddBook(this.book).then(response=>{
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
                    })
                }else {
                    reqModifyBook(this.book).then(response=>{
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
                            type: 'warning',
                            message: "修改图书失败"
                        })
                    })
                }
            },

            //获取图书的分类值
            handleChange(bookSort) {
                console.log("图书的分类是:"+bookSort[0]+bookSort[1]);
            },


            //图片被移除后
            handleRemove(file, fileList) {
                if(this.isEdit){
                    reqDelBookImg(this.book.isbn,file.url).then(response=>{
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
                console.log("url:"+file.url);
                console.log("fileList:"+fileList);
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            handleSuccess(response, file, fileList){
                console.log(response);
            },


            //得到并设置图书分类的联级选择器
            getSortList() {
                reqGetSortList().then(response => {
                    let list = response.sortResponseList;
                    this.options = [];
                    for (let i = 0; i < list.length; i++) {
                        let children = [];
                        if (list[i].children != null && list[i].children.length > 0) {
                            for (let j = 0; j < list[i].children.length; j++) {
                                children.push({label: list[i].children[j].sortName, value: list[i].children[j].id});
                            }
                        }
                        console.log(list[i]);
                        this.options.push({label: list[i].upperSort.sortName, value: list[i].upperSort.id, children: children});
                    }
                });
            },
            //得到并设置出版的下拉选择器
            getPublishName(){
                reqGetPublishNames().then(response=>{
                    console.log(response);
                    this.publishList=response.publishList;
                    this.book.publish=this.publishList[0];
                }).then(err=>{
                    console.log(err);
                })
            },

            //得到图书的相册集
            getBookImgPathList(){
                reqGetBookImgPathList("1345222").then(response=>{
                    let list = response.imgPaths;
                    for (let i = 0; i < list.length; i++) {
                        this.fileList.push({name: list[i], url: list[i]});
                    }
                    console.log(response);
                }).catch(err=>{
                    console.log(err);
                })
            },

        },
        created() {
            this.getPublishName();
            this.getSortList();
            this.initMyHeader();
            if(this.isEdit){
                let id = this.$route.query.id;
                reqGetBook(id).then(response=>{
                    console.log(response.book);
                    console.log(response.upperId);
                    console.log(response.childId);
                    this.book = response.book;
                    this.book.bookSort = [];
                    this.book.bookSort.push(response.upperId);
                    this.book.bookSort.push(response.childId);
                    let list = response.book.imgSrc;
                    console.log(list.length);
                    for (let i = 0; i < list.length; i++) {
                        this.fileList.push({name: list[i], url: list[i]});
                    }
                }).catch(err=>{
                    console.log(err);
                })
            }
        },
    }
</script>

<style scoped>
  .content{
    margin: 0px auto;
    width: 100%;
    /*background-color: #B3C0D1;*/
  }
  .tab_content{
    margin: 10px auto;
    width:1000px;
    border: 1px #e8e8e8 solid;
    padding: 50px 25px;
  }
  .step{
    margin: 10px auto;
    width: 660px;
    padding: 20px;
  }
  .first_tab{
    margin: 10px auto 0px;
    width: 600px;
    padding: 20px 20px 0px;
  }
  .markdown_tab{
    margin: 10px auto 0px;
    width: 940px;
    padding: 20px 20px 0px;
  }

  .step_btn{
    margin: 0px auto;
    width: 400px;
    padding: 10px;
  }

</style>

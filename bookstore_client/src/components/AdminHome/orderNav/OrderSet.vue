<template>
  <div class="content">
    <div class="tab_content">
      <div class="first_tab">
        <el-form :model="publish" :rules="rules" ref="publish" label-width="150px">
          <el-form-item label="秒杀订单超过" prop="name">
            <el-input v-model="publish.rank" class="input-width">
              <template slot="append">分</template>
            </el-input>
            <span class="note-margin">未付款，订单自动关闭</span>
          </el-form-item>
          <el-form-item label="秒杀订单超过" prop="name">
            <el-input v-model="publish.rank" class="input-width">
              <template slot="append">分</template>
            </el-input>
            <span class="note-margin">未付款，订单自动关闭</span>
          </el-form-item>
          <el-form-item label="正常订单超过" prop="name">
            <el-input v-model="publish.rank" class="input-width">
              <template slot="append">分</template>
            </el-input>
            <span class="note-margin">未收货，订单自动关闭</span>
          </el-form-item>
          <el-form-item label="发货超过" prop="name">
            <el-input v-model="publish.rank" class="input-width">
              <template slot="append">分</template>
            </el-input>
            <span class="note-margin">自动结束交易</span>
          </el-form-item>

          <el-form-item label="订单完成超过" prop="name">
            <el-input v-model="publish.rank" class="input-width">
              <template slot="append">分</template>
            </el-input>
            <span class="note-margin">自动五星好评</span>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit('publish')">确认</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
    import  {reqAddPublish,reqGetPublish,reqModifyPublish} from "../../../api/publish";

    export default {
        name: "OrderSet",
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
                editId: null,
                publish: {
                    id: 1,
                    name: '',
                    rank: 1,
                    showPublish: false
                },
                rules: {
                    name: [
                        { required: true, message: '出版社名不能为空', trigger: 'blur' },
                        { min: 3, max: 10, message: '出版社长度在 3 到 10 个字符', trigger: 'blur' }
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
                    reqGetPublish(this.editId).then(response=>{
                        this.publish = response.publish;
                        console.log("编辑页面")
                        console.log(response);
                    }).catch(err=>{
                        console.log(err);
                    })
                }
            },
            onSubmit(formName) {
                this.$refs[formName].validate((valid)=>{
                    // console.log(this.publish.isShow);
                    if(valid){
                        if(this.isEdit){
                            this.modifyPublish();
                        }else {
                            this.addPublish();
                        }
                    }else {
                        this.$message.error("添加出版社失败");
                        return false;
                    }
                });
            },

            addPublish(){
                console.log("提交的值为："+this.publish.showPublish);
                reqAddPublish(this.publish).then(response=>{
                    console.log(this.publish);
                    console.log("成功提交");
                    console.log(response);
                    this.$message.info("添加出版社成功");
                }).catch(err=>{
                    console.log(err);
                    this.$message.error("添加出版社失败");
                })
            },

            modifyPublish(){
                console.log("值为："+this.publish.show);
                reqModifyPublish(this.publish).then(response=>{
                    console.log(this.publish);
                    console.log("成功提交");
                    console.log(response);
                    this.$message.info("修改出版社成功");
                }).catch(err=>{
                    console.log(err);
                    this.$message.error("修改出版社出错");
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

        },
        created() {
            if(this.editId==false)
                return;
            else {
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
    padding: 50px 15px;
  }

  .first_tab{
    margin: 10px auto 0px;
    width: 600px;
    padding-top: 20px;
  }

  .input-width {
    width: 50%;
  }

</style>

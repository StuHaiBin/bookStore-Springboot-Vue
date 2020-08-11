<template>
  <div>
    <Nav></Nav>
    <div class="content">
      <div class="login_content">
        <h2 style="margin: 20px auto">注册</h2>
        <div style="margin: 30px auto;width: 300px">
          <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" class="demo-ruleForm" size="medium">
            <el-form-item prop="account">
              <el-input type="text" v-model="ruleForm.account" autocomplete="off" placeholder="请输入手机号或者邮箱"></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input type="password" v-model="ruleForm.password" autocomplete="off" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item prop="rePassword">
              <el-input type="password" v-model="ruleForm.rePassword" autocomplete="off" placeholder="密码确认"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')" style="width: 100%">注册</el-button>
            </el-form-item>
            <el-form-item>
              <center><span>已阅读并同意：浮游书店 用户协议</span></center>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
    import Nav from "../../components/Common/Nav";
    import Footer from "../../components/Common/Footer";
    import {reqRegister,reqAccountVerify} from "../../api/user";

    export default {
        name: "Register",
        components: {Nav,Footer},
        data() {
            let checkAccount = (rule, value, callback) => {
                reqAccountVerify(value).then((response)=>{
                    if(response.data.code=200){
                        callback();
                    }else{
                        callback(response.data.message);
                    }
                }).catch(err=>{
                    callback();
                })
            };
            let validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else if(value.length<3) {
                    callback(new Error('密码长度不能小于8'));
                }else {
                    callback();
                }
            };
            let validateRePass = (rule, value, callback) => {
                if (value !==this.ruleForm.password){
                    callback(new Error('密码不一致'));
                }else{
                    callback();
                }
            }
            return {
                ruleForm: {
                    account: '',
                    password: '',
                    rePassword: ''
                },
                rules: {
                    account: [
                        { validator: checkAccount, trigger: 'blur' },
                        { type: 'email', required: true, message: '请输入正确格式邮箱', trigger: 'change'},
                    ],
                    password: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    rePassword: [
                        { validator: validateRePass, trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //数据校验成功，可以进行提交操作
                        reqRegister(this.ruleForm.account,this.ruleForm.password).then((response)=>{
                            if(response.code==200){
                                console.log("=====注册成功=====");
                                this.$message({
                                    type: 'success',
                                    message: response.message,
                                    duration: 1000
                                })
                                setTimeout(() => {
                                    this.$router.push({path:'/login'});
                                }, 1000);
                            }else{
                                this.$message({
                                    type: 'waring',
                                    message: response.message,
                                    duration: 1000
                                })
                            }
                        }).catch(err=>{
                            this.$message({
                                type: 'waring',
                                message: "注册失败",
                                duration: 1000
                            })
                        })
                    } else {
                        this.$message.error("数据不符合要求，不能进行注册");
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
    }
</script>

<style scoped>
  .content{
    background-color: #B3C0D1;
    height: 100vh;
    overflow: hidden;
    min-width: 1240px;
    background-image: url("../../assets/image/15124.jpg");
    background-repeat: no-repeat;
    background-size: 100% 100%;
    -moz-background-size: 100% 100%;
  }
  .login_content{
    margin: 130px auto;
    padding: 15px;
    width: 550px;
    height: 400px;
    background-color: white;
    border-radius: 10px;
  }
  h2{
    text-align: center;
    margin-bottom: 30px;
  }
</style>

